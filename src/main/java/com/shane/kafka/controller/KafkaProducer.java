package com.shane.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    @GetMapping("/kafka/normal/{message}")
    public void sendNormalMessage(@PathVariable String message) {
        for (int i = 0; i < 10000; i++) {
            kafkaTemplate.send("test-topic", message + i);
            kafkaTemplate.send("census", "census-topic" + message + i);
        }
    }
}
