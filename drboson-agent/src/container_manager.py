import docker
from tempfile import NamedTemporaryFile
from jinja2 import Environment, FileSystemLoader
import templates
from pathlib import Path


class ContainerManager:
    def __init__(self):
        self.client = docker.from_env()

    def create_run_container(self, run, workdir, env_file):
        image_name = f"{run['id']}-image"
        volumes = {workdir: {'bind': str(workdir), 'mode': 'rw'}}

        self.__build_image(image_name, workdir, env_file)
        container = self.client.containers.run(image_name, volumes=volumes, detach=True)

        return container.id

    def __build_image(self, name, workdir, env_file):
        with NamedTemporaryFile(dir=workdir) as dockerfile:
            dockerfile_name = Path(dockerfile.name).name
            dockerfile.write(templates.create_dockerfile(workdir=workdir, env_file_path=env_file))
            dockerfile.seek(0)
            self.client.images.build(path=str(workdir), dockerfile=dockerfile_name, custom_context=False, tag=name)

    # def consume_events(self):
    #     event_filter = {'type': 'container'}
    #
    #     for event in self.client.events(decode=True, filters=event_filter):
    #         print(event)

