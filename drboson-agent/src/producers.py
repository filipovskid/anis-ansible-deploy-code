from confluent_kafka import Producer
import socket


def messages_producer():
    conf = {'bootstrap.servers': '192.168.1.4',
            'client.id': socket.gethostname(),
            'retries': 10,
            'retry.backoff.ms': 1000,
            'queue.buffering.max.ms': 100}

    return Producer(conf)
