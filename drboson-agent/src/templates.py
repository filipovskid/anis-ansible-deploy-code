from jinja2 import Environment, FileSystemLoader
from config import config

def create_dockerfile(**kwargs):
    dockerfiles_dir = config['docker']['dockerfiles_dir']
    dockerfile_name = config['docker']['dockerfile_name']
    bootstrap_server = config['kafka']['servers']
    producer_topic = config['kafka']['communication-topic']

    env = Environment(loader=FileSystemLoader(dockerfiles_dir))
    dockerfile_template = env.get_template(dockerfile_name)

    return dockerfile_template.render(communication_topic=producer_topic,
                                      bootstrap_servers=bootstrap_server,
                                      **kwargs).encode('utf-8')
