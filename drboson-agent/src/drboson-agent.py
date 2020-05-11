from container_manager import ContainerManager
import consumers
from handlers import CommunicationHandler
import threading
from config import config


def main():
    container_manager = ContainerManager(config)
    communication_handler = CommunicationHandler()

    threading.Thread(target=consumers.run_consumer, args=(container_manager,)).start()
    threading.Thread(target=consumers.run_communication_consumer, args=(communication_handler,)).start()
    # docker_event_consumer


if __name__ == '__main__':
    main()
