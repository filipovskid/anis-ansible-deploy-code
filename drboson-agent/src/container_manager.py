import docker
from tempfile import TemporaryFile
from jinja2 import Environment, FileSystemLoader


class ContainerManager:
    def __init__(self):
        self.client = docker.from_env()

    def create_run_container(self, run, dockerfile_path):
        image_name = f'{run["id"]}image'

        self.__build_image(image_name, dockerfile_path, {'workdir': run['workdir']})
        container = self.client.containers.run(image_name, detach=True)

        return container.id

    def __build_image(self, name, dockerfile_path, opts):
        env = Environment(loader=FileSystemLoader('/Users/darko/Documents/Projects/DRBoson/drboson-agent'))
        dockerfile_template = env.get_template(dockerfile_path)

        with TemporaryFile() as dockerfile:
            dockerfile.write(dockerfile_template.render(workdir=opts['workdir']).encode('utf-8'))
            dockerfile.seek(0)
            self.client.images.build(fileobj=dockerfile, tag=name)



