from file_management import prepare_workspace, prepare_dataset, prepare_code
from config import config
import producers
from pathlib import Path
import messages
import schemas
import copy
import json
import sys


def __run_setup(run_item):
    workspaces_path = Path(config['workspace']['dir'])
    dataset_dir = config['workspace']['dataset']['dir']
    data_dir = config['workspace']['data']
    dir_paths = prepare_workspace(workspaces_path, dataset_dir, data_dir, run_name=run_item['id'])

    datasets_bucket = config['buckets']['dataset']
    dataset_key = run_item['dataset_location']
    dataset_path = dir_paths['dataset_path']
    prepare_dataset(datasets_bucket, dataset_key, dataset_path)

    repo_url = 'https://github.com/filipovskid/run-conformant-repo.git'
    executor_path = Path(config['exec']['executor'])
    workdir_path = dir_paths['workdir_path']
    prepare_code(repo_url, workdir_path=workdir_path, executor_path=executor_path)

    environment_file = workdir_path.joinpath(config['exec']['env_file'])

    if environment_file.exists() is False:
        pass

    return dir_paths, environment_file


def handle_run_execution(container_manager, run_item):
    # run_item = json.loads(run_bytes.decode('utf-8'))
    dir_paths, env_file = __run_setup(run_item)
    safe_run = copy.deepcopy(run_item)

    container_manager.create_run_container(safe_run, dir_paths['workdir_path'], env_file)

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

    def handle_status_change(self, run_id, payload):
        run_status = messages.create_status_message(run_id=run_id, status=payload)
        topic = config['kafka']['statuses-topic']

        self.__navigate_communication(producer=self.status_producer, topic=topic, message=run_status, key=run_id,
                                      on_delivery=CommunicationHandler.__delivery_callback)

    def handle_metric_logs(self, run_id, payload):
        metric_log = messages.create_log_message(run_id=run_id, log=payload)
        topic = config['kafka']['logs-topic']

        self.__navigate_communication(producer=self.log_producer, topic=topic, message=metric_log,
                                      on_delivery=CommunicationHandler.__delivery_callback)

    def handle_file_creation(self, run_id, payload):
        file_creation_message = messages.create_file_message(run_id=run_id, file_key=payload)
        topic = config['kafka']['files-topic']

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
            type_handlers[message['type']](message['id'], message['payload'])
