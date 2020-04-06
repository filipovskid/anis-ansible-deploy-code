from file_management import prepare_workspace, prepare_dataset, prepare_code
from config import config
from pathlib import Path
from confluent_kafka import Producer
import socket
import copy
import json
import sys


def __run_setup(run_item):
    workspaces_path = Path(config['workspace']['dir'])
    dataset_dir = config['workspace']['dataset']['dir']
    data_dir = config['workspace']['data']
    dir_paths = prepare_workspace(workspaces_path, dataset_dir, data_dir, run_name=run_item['id'])

    datasets_bucket = config['buckets']['dataset']
    dataset_key = run_item['dataset']['location']
    dataset_path = dir_paths['dataset_path']
    prepare_dataset(datasets_bucket, dataset_key, dataset_path)

    prepare_code('https://github.com/filipovskid/run-conformant-repo.git', dir_paths['workdir_path'])

    return dir_paths


def handle_run_execution(container_manager, run_bytes):
    run_item = json.loads(run_bytes.decode('utf-8'))
    dir_paths = __run_setup(run_item)
    safe_run = copy.deepcopy(run_item)

    dockerfile_conf = {
        'dir': config['docker']['dockerfiles_dir'],
        'name': config['docker']['dockerfile_name'],
    }
    opts = {
        'workdir': str(dir_paths['workdir_path']),
        # 'data': str(dir_paths['data_path'])
    }

    container_manager.create_run_container(safe_run, dockerfile_conf, opts)

# {
#     'id': 'run-id',
#     'type': 'type',
#     'payload': 'status | filename | log'
# }


def __delivery_callback(err, msg):
    if err:
        sys.stderr.write('%% Message failed delivery: %s\n' % err)
    else:
        sys.stderr.write('%% Message delivered to %s [%d] @ %d\n' %
                         (msg.topic(), msg.partition(), msg.offset()))


def __navigate_communication(producer, topic, message, **kwargs):
    json_message = json.dumps(message)

    try:
        producer.produce(topic, value=json_message, **kwargs)
        producer.poll(1)
    except BufferError as e:
        producer.poll(10)
        producer.produce(topic, value=json_message, **kwargs)


def __handle_status_change(producer, message):
    run_status = {
        'id': message['id'],
        'status': message['payload']
    }
    topic = config['kafka']['statuses-topic']

    __navigate_communication(producer, topic=topic, message=run_status, key=message['id'], callback=__delivery_callback)


def __handle_file_creation(producer, message):
    file_creation_message = {
        'id': message['id'],
        'file_name': message['payload']
    }
    topic = config['kafka']['files-topic']

    __navigate_communication(producer, topic=topic, message=file_creation_message, callback=__delivery_callback)


def __handle_metric_logs(producer, message):
    metric_log = {
        'id': message['id'],
        'log': message['payload']
    }
    topic = config['kafka']['logs-topic']

    __navigate_communication(producer, topic=topic, message=metric_log, callback=__delivery_callback)


def handle_run_communication(message_bytes):
    type_handlers = {
        'status': __handle_status_change,
        'file': __handle_file_creation,
        'log': __handle_metric_logs
    }
    conf = {'bootstrap.servers': '192.168.1.4',
            'client.id': socket.gethostname(),
            'retries': 10,
            'retry.backoff.ms': 1000,
            'queue.buffering.max.ms': 100}

    producer = Producer(conf)

    message = json.loads(message_bytes.decode('utf-8'))

    if 'type' in message and message['type'] in type_handlers:
        type_handlers[message['type']](producer, message)
