import boto3
import magic
from pathlib import Path
import zipfile


archive_mime_types = ['application/zip']


def prepare_dataset(bucket_name, dataset, dataset_path):
    s3 = boto3.client('s3')

    file_path = Path.joinpath(dataset_path, dataset['key'])
    s3.download_file(bucket_name, dataset['key'], str(file_path))

    mime_type = magic.from_file(str(file_path), mime=True)
    if mime_type in archive_mime_types:
        decompress_archive(file_path)


def decompress_archive(file_path):
    with zipfile.ZipFile(file_path, 'r') as zip_file:
        zip_file.extractall(file_path.parent)


def prepare_workspace(workspaces_dir, run_name):
    run_directory = Path(str(workspaces_dir)).joinpath(run_name)
    dataset_directory = run_directory.joinpath('dataset')

    run_directory.mkdir()
    dataset_directory.mkdir()

    return {'workdir': run_directory, 'dataset_dir': dataset_directory}

# prepare_dataset('dataset-filipovski', {'key': 'house-prices-19f3de8c-5abb-49f0-b93e-7246ef5ace21'}, Path('./datasets'))
