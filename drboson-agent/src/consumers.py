from confluent_kafka import Consumer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka import DeserializingConsumer
from confluent_kafka.schema_registry.avro import AvroDeserializer
from confluent_kafka.serialization import StringDeserializer
from confluent_kafka.cimpl import KafkaException
from config import config
import schemas
import handlers


def run_consumer(container_manager):
    schema_registry_conf = { 'url': config['kafka']['schema_registry'] }
    schema_registry_client = SchemaRegistryClient(schema_registry_conf)

    avro_deserializer = AvroDeserializer(schemas.run_record_schema, schema_registry_client)
    string_deserializer = StringDeserializer('utf_8')

    conf = {'bootstrap.servers': config['kafka']['servers'],
            'key.deserializer': string_deserializer,
            'value.deserializer': avro_deserializer,
            'group.id': "runs-consumers",
            'auto.offset.reset': 'earliest',
            'enable.auto.commit': 'false'}

    consumer = DeserializingConsumer(conf)
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
