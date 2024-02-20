package com.solopov.gateway.services;

import com.solopov.gateway.model.TransactionMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    KafkaTemplate<UUID, TransactionMessage> kafkaTemplate;

    public void send(String topicName, UUID key, TransactionMessage transactionMessage) {
        var future = kafkaTemplate.send(topicName, key, transactionMessage);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
                LOGGER.error(exception.getMessage());

            } else {
                future.complete(sendResult);
            }

            LOGGER.info("The id is:{} Transaction status to Kafka topic: {}", transactionMessage.getTransactionId(), transactionMessage.getStatus());
        });
    }
}
