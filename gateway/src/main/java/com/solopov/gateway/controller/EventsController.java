package com.solopov.gateway.controller;

import com.solopov.gateway.config.KafkaConfig;
import com.solopov.gateway.model.TransactionMessage;
import com.solopov.gateway.services.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EventsController {

    private final KafkaProducerService service;

    @PostMapping("/event")
    ResponseEntity<String> event(@RequestBody TransactionMessage message) {
        UUID key = UUID.randomUUID();
        log.info("Received the transaction with the key {}", key);
        service.send(KafkaConfig.TOPIC_NAME, key, message);
        return ResponseEntity.ok("Sent");
    }
}
