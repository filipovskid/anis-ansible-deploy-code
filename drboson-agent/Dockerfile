FROM continuumio/miniconda3

WORKDIR {{workdir}}

COPY environment.yml .

RUN cat environment.yml

RUN conda env create -f environment.yml

SHELL ["conda", "run", "-n", "{{env_name}}", "/bin/bash", "-c"]

RUN conda install -c conda-forge python-confluent-kafka

ENV BOOTSTRAP_SERVERS {{bootstrap_servers}}

ENV PRODUCER_TOPIC {{communication_topic}}

CMD python executor.py
