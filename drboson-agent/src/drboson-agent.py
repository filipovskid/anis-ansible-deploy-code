from container_manager import ContainerManager
import consumers
import threading


def main():
    container_manager = ContainerManager()
    consumers.run_consumer(container_manager)
    consumers.run_communication_consumer()

if __name__ == '__main__':
    main()
