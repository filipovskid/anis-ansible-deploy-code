from confluent_kafka import Consumer
from confluent_kafka.cimpl import KafkaException
from config import config
import handlers


def run_consumer(container_manager):
    conf = {'bootstrap.servers': config['kafka']['servers'],
            'group.id': "runs-consumers",
            'auto.offset.reset': 'earliest',
            'enable.auto.commit': 'false'}
    consumer = Consumer(conf)
    print('RUN')

    try:
        consumer_topics = [config['kafka']['runs-topic']]
        consumer.subscribe(consumer_topics)

        while True:
            msg = consumer.poll(timeout=1.0)
            if msg is None:
                continue

            if msg.error():
                raise KafkaException(msg.error())
            else:
                print(msg.value())
                consumer.commit(asynchronous=False)
                handlers.handle_run_execution(container_manager, msg.value())
    finally:
        consumer.close()


def run_communication_consumer(communication_handler):
    conf = {'bootstrap.servers': config['kafka']['servers'],
            'group.id': "communication",
            'auto.offset.reset': 'earliest',
            'enable.auto.commit': 'false'}
    consumer = Consumer(conf)
    print('COMMUNICATION')

    try:
        consumer_topics = [config['kafka']['communication-topic']]
        consumer.subscribe(consumer_topics)

        while True:
            msg = consumer.poll(timeout=1.0)

            if msg is None:
                continue

            if msg.error():
                raise KafkaException(msg.error())
            else:
                print(msg.value())
                consumer.commit(asynchronous=False)
                communication_handler.handle_run_communication(msg.value())
    finally:
        consumer.close()
