import json


def create_status_change_json_message(run_id, status):
    message = {
        'id': run_id,
        'status': status
    }

    return json.dumps(message)


def create_log_json_message(run_id, log):
    message = {
        'id': run_id,
        'log': log
    }

    return json.dumps(message)


def create_file_creation_json_message(run_id, file_key):
    message = {
        'id': run_id,
        'file_key': file_key
    }

    return json.dumps(message)