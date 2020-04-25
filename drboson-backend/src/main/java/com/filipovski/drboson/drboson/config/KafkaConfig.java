package com.filipovski.drboson.drboson.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    private final String RUNS_TOPIC = "runs";

    @Bean
    public NewTopic runsTopic() {
        return TopicBuilder.name(RUNS_TOPIC)
                .partitions(2)
                .build();
    }
}
