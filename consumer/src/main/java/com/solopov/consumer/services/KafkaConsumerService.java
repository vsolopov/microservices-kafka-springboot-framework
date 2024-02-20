package com.solopov.consumer.services;

import com.solopov.consumer.models.TransactionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = {"transaction-topic"}, groupId = "group-id")
    public void consume(TransactionMessage message) {
        log.info("Received the transaction with the id: {} The status id: {}", message.getTransactionId(), message.getStatus());
    }
}
