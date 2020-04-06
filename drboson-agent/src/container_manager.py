import docker
from tempfile import TemporaryFile
from jinja2 import Environment, FileSystemLoader


class ContainerManager:
    def __init__(self):
        self.client = docker.from_env()

    def create_run_container(self, run, dockerfile_conf, opts):
        image_name = f"{run['id']}-image"

        self.__build_image(image_name, dockerfile_conf, opts)
        container = self.client.containers.run(image_name, command='sleep 20', detach=True)

        return container.id

    def __build_image(self, name, dockerfile_conf, opts):
        env = Environment(loader=FileSystemLoader(dockerfile_conf['dir']))
        dockerfile_template = env.get_template(dockerfile_conf['name'])

        with TemporaryFile() as dockerfile:
            dockerfile.write(dockerfile_template.render(workdir=opts['workdir']).encode('utf-8'))
            dockerfile.seek(0)
            self.client.images.build(fileobj=dockerfile, tag=name)

    # def consume_events(self):
    #     event_filter = {'type': 'container'}
    #
    #     for event in self.client.events(decode=True, filters=event_filter):
    #         print(event)

