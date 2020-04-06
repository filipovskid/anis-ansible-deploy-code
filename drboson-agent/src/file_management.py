import boto3
import magic
from pathlib import Path
import zipfile
from git import Repo


def prepare_dataset(bucket_name, dataset_key, dataset_path):
    s3 = boto3.client('s3')

    file_path = Path.joinpath(dataset_path, dataset_key)
    s3.download_file(bucket_name, dataset_key, str(file_path))

    mime_type = magic.from_file(str(file_path), mime=True)

    archive_mime_types = ['application/zip']
    if mime_type in archive_mime_types:
        decompress_archive(file_path)


def decompress_archive(file_path):
    with zipfile.ZipFile(file_path, 'r') as zip_file:
        zip_file.extractall(file_path.parent)


def prepare_code(repo_url, workdir_path, branch='master'):
    repo = Repo.init(workdir_path)
    remote = repo.create_remote('origin', repo_url)
    remote.pull(branch)


def prepare_workspace(workspaces_path, dataset_dir, data_dir, run_name):
    run_directory = Path(str(workspaces_path)).joinpath(run_name)
    dataset_directory = run_directory.joinpath(dataset_dir)
    data_directory = run_directory.joinpath(data_dir)

    run_directory.mkdir()
    dataset_directory.mkdir()
    data_directory.mkdir()

    return {'workdir_path': run_directory, 'dataset_path': dataset_directory, 'data_path': data_directory}
