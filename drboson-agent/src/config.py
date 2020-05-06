config = {
    'buckets': {
        'dataset': 'dataset-filipovski'
    },
    'workspace': {
        'dir': '/Users/darko/run-workspaces',
        'dataset': {
            'dir': 'dataset'
        },
        'data': 'data'
    },
    'docker': {
        'dockerfiles_dir': '/Users/darko/Documents/Projects/DRBoson/drboson-agent/',
        'dockerfile_name': 'Dockerfile'
    },
    'kafka': {
        'servers': '192.168.1.6',
        'runs-topic': 'runs',
        'statuses-topic': 'run_statuses',
        'logs-topic': 'run_logs',
        'files-topic': 'run_files',
        'communication-topic': 'run_messages',
        'schema_registry': 'http://localhost:8081'
    },
    'exec': {
        'executor': '/Users/darko/Documents/Projects/DRBoson/drboson-agent/executor.py',
        'env_file': 'environment.yml'
    }
}