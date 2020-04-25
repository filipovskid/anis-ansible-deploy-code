import boto3
from botocore.exceptions import ClientError
import magic
from pathlib import Path
import zipfile
from git import Repo
import shutil


def download_file_from_s3(file_path, bucket, object_name):
    s3 = boto3.client('s3')
    s3.download_file(bucket, object_name, str(file_path))


def upload_file_to_s3(file_path, bucket, object_name):
    s3 = boto3.client('s3')
    try:
        response = s3.upload_file(str(file_path), bucket, object_name)
    except ClientError as e:
        print(e)
        return False

    return True


def prepare_dataset(bucket_name, dataset_key, dataset_path):
    file_path = Path.joinpath(dataset_path, dataset_key)
    download_file_from_s3(file_path, bucket_name, dataset_key)

    mime_type = magic.from_file(str(file_path), mime=True)

    archive_mime_types = ['application/zip']
    if mime_type in archive_mime_types:
        decompress_archive(file_path)


def decompress_archive(file_path):
    with zipfile.ZipFile(file_path, 'r') as zip_file:
        zip_file.extractall(file_path.parent)


def prepare_code(repo_url, workdir_path, executor_path, branch='master'):
    repo = Repo.init(workdir_path)
    remote = repo.create_remote('origin', repo_url)
    remote.pull(branch)

    shutil.copy2(executor_path, workdir_path)


def prepare_workspace(workspaces_path, dataset_dir, data_dir, run_name):
    run_directory = Path(str(workspaces_path)).joinpath(run_name)
    dataset_directory = run_directory.joinpath(dataset_dir)
    data_directory = run_directory.joinpath(data_dir)

    run_directory.mkdir()
    dataset_directory.mkdir()
    data_directory.mkdir()

    return {'workdir_path': run_directory, 'dataset_path': dataset_directory, 'data_path': data_directory}