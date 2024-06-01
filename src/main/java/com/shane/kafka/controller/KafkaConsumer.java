package com.shane.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "test-topic",
                    partitions = {"1", "2"},
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "0")
                    })
    },
            groupId = "testGroup",
            concurrency = "10")
    public void onNormalMessage(ConsumerRecords<String, Object> records, Acknowledgment ack) {
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println("consumer: " + record.topic() + "-" + record.partition() + "=" + record.value());
            ack.acknowledge();
        }
    }
}
