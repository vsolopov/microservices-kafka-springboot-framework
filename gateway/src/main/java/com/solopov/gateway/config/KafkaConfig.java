package com.solopov.gateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_NAME = "transaction-topic";

    @Bean
    public NewTopic transactionTopic() {
        return TopicBuilder
                .name(TOPIC_NAME)
                .partitions(2)
                .replicas(1)
                .build();
    }
}
