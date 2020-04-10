from container_manager import ContainerManager
import consumers
import threading


def main():
    container_manager = ContainerManager()
    threading.Thread(consumers.run_consumer(container_manager)).start()
    threading.Thread(target=consumers.run_communication_consumer()).start()
    # docker_event_consumer

if __name__ == '__main__':
    main()
