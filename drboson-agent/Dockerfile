FROM python:3

WORKDIR {{workdir}}

RUN pip install confluent-kafka

ENV BOOTSTRAP_SERVERS {{bootstrap_servers}}

ENV PRODUCER_TOPIC {{communication_topic}}

CMD python3 executor.py

