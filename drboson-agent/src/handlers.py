from file_management import prepare_workspace, prepare_dataset
from config import config
from pathlib import Path
import copy
import json


def __run_setup(run_item):
    workspaces_path = Path(config['workspace']['dir'])
    dataset_dir = config['workspace']['dataset']['dir']
    dir_paths = prepare_workspace(workspaces_path, dataset_dir, run_name=run_item['id'])

    datasets_bucket = config['buckets']['dataset']
    dataset_key = run_item['dataset']['location']
    dataset_path = dir_paths['dataset_path']
    prepare_dataset(datasets_bucket, dataset_key, dataset_path)

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
        'workdir': str(dir_paths['workdir_path'])
    }

    container_manager.create_run_container(safe_run, dockerfile_conf, opts)
