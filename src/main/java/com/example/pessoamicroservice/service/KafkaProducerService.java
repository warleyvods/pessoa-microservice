package com.example.pessoamicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String KAFKA_TOPIC = "people-topic";

    public void send(String message) {
        kafkaTemplate.send(KAFKA_TOPIC, message);
    }
}
