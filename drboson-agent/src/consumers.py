from confluent_kafka import Consumer
from confluent_kafka.cimpl import KafkaException
from config import config
import handlers
import uuid
import string
import random


def __random_word(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))


def run_consumer(container_manager):
    conf = {'bootstrap.servers': '192.168.1.4',
            'group.id': "runs-consumers",
            'auto.offset.reset': 'earliest',
            'enable.auto.commit': 'false'}
    consumer = Consumer(conf)

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
