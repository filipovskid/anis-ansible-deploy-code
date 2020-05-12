from file_management import prepare_workspace, prepare_dataset, prepare_code, upload_file_to_s3
from config import config
import producers
import pathlib
import messages
import schemas
import uuid
import copy
import json
import sys


def __run_setup(run_item):
    workspaces_path = pathlib.Path(config['workspace']['dir'])
    dataset_dir = config['workspace']['dataset']['dir']
    data_dir = config['workspace']['data']
    local_dir_paths = prepare_workspace(workspaces_path, dataset_dir, data_dir, run_name=run_item['id'])

    datasets_bucket = config['buckets']['dataset']
    dataset_key = run_item['dataset_location']
    dataset_path = local_dir_paths['dataset_path']
    prepare_dataset(datasets_bucket, dataset_key, dataset_path)

    repo_url = 'https://github.com/filipovskid/run-conformant-repo.git'
    executor_path = pathlib.Path(config['exec']['executor'])
    local_workdir_path = local_dir_paths['workdir_path']
    prepare_code(repo_url, workdir_path=local_workdir_path, executor_path=executor_path)

    environment_file = local_workdir_path.joinpath(config['exec']['env_file'])
    container_workdir_path = config['workspace']['container']

    if environment_file.exists() is False:
        pass

    return local_dir_paths, container_workdir_path, environment_file


def handle_run_execution(container_manager, run_item):
    # run_item = json.loads(run_bytes.decode('utf-8'))
    local_dir_paths, container_workdir_path, env_file = __run_setup(run_item)
    safe_run = copy.deepcopy(run_item)

    container_manager.create_run_container(safe_run, local_workdir=local_dir_paths['workdir_path'],
                                           container_workdir=container_workdir_path, env_file=env_file)

# {
#     'id': 'run-id',
#     'type': 'type',
#     'payload': 'status | filename | log'
# }


class CommunicationHandler:
    def __init__(self):
        self.producer = producers.json_messages_producer()
        self.status_producer = producers.avro_messages_producer(schemas.status_record_schema)
        self.log_producer = producers.avro_messages_producer(schemas.log_record_schema)
        self.file_producer = producers.avro_messages_producer(schemas.file_record_schema)

    @staticmethod
    def __delivery_callback(err, msg):
        if err:
            sys.stderr.write('%% Message failed delivery: %s\n' % err)
        else:
            sys.stderr.write('%% Message delivered to %s [%d] @ %d\n' %
                             (msg.topic(), msg.partition(), msg.offset()))

    @staticmethod
    def __navigate_communication(producer, topic, message, **kwargs):
        try:
            producer.produce(topic, value=message, **kwargs)
            producer.poll(1)
        except BufferError as e:
            producer.poll(10)
            producer.produce(topic, value=message, **kwargs)

    def handle_status_change(self, run_id, project_id, payload):
        run_status = messages.create_status_message(run_id=run_id, project_id=project_id, status=payload)
        topic = config['kafka']['statuses-topic']

        self.__navigate_communication(producer=self.status_producer, topic=topic, message=run_status, key=run_id,
                                      on_delivery=CommunicationHandler.__delivery_callback)

    def handle_metric_logs(self, run_id, project_id, payload):
        metric_log = messages.create_log_message(run_id=run_id, project_id=project_id, log=payload)
        topic = config['kafka']['logs-topic']

        self.__navigate_communication(producer=self.log_producer, topic=topic, message=metric_log,
                                      on_delivery=CommunicationHandler.__delivery_callback)

    def handle_file_creation(self, run_id, project_id, payload):
        topic = config['kafka']['files-topic']
        files_bucket = config['buckets']['files']
        container_workspace = pathlib.Path(config['workspace']['container'])
        run_workspaces = pathlib.Path(config['workspace']['dir'])
        run_workspace_path = run_workspaces.joinpath(run_id)
        container_file_path = pathlib.Path(payload)
        file_path = run_workspace_path.joinpath(container_file_path.relative_to(container_workspace))

        if not run_workspace_path.exists():
            print(f'{run_id} Run workspace: {run_workspace_path} does not exist')

        if not file_path.exists():
            print(f'[{run_id}] Run file: {file_path} does not exist')

        if not file_path.is_file():
            print(f'[{run_id}] {file_path} is not a file')

        s3_file_name = f'{uuid.uuid4()}--{file_path.name}'

        uploaded = upload_file_to_s3(file_path, files_bucket, s3_file_name)

        if not uploaded:
            print(f'[{run_id}] {file_path} upload failed')
            return

        file_creation_message = messages.create_file_message(run_id=run_id, project_id=project_id, file_id=str(uuid.uuid4()),
                                                             file_name=file_path.name, file_key=s3_file_name)
        self.__navigate_communication(producer=self.file_producer, topic=topic, message=file_creation_message,
                                      on_delivery=CommunicationHandler.__delivery_callback)

    def handle_run_communication(self, message_bytes):
        type_handlers = {
            'status': self.handle_status_change,
            'log': self.handle_metric_logs,
            'file': self.handle_file_creation
        }

        message = json.loads(message_bytes.decode('utf-8'))

        if 'type' in message and message['type'] in type_handlers:
            type_handlers[message['type']](run_id=message['run_id'],
                                           project_id=message['project_id'],
                                           payload=message['payload'])
