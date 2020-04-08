import docker
from tempfile import TemporaryFile
from jinja2 import Environment, FileSystemLoader
import templates


class ContainerManager:
    def __init__(self):
        self.client = docker.from_env()

    def create_run_container(self, run, dockerfile_conf, opts):
        image_name = f"{run['id']}-image"
        volumes = {opts['workdir']: {'bind': opts['workdir'], 'mode': 'rw'}}

        self.__build_image(image_name, opts)
        container = self.client.containers.run(image_name, 'sleep 15', volumes=volumes, detach=True)

        return container.id

    def __build_image(self, name, opts):
        with TemporaryFile() as dockerfile:
            dockerfile.write(templates.create_dockerfile(workdir=opts['workdir']))
            dockerfile.seek(0)
            self.client.images.build(fileobj=dockerfile, tag=name)

    # def consume_events(self):
    #     event_filter = {'type': 'container'}
    #
    #     for event in self.client.events(decode=True, filters=event_filter):
    #         print(event)

