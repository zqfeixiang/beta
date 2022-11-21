package com.dong.beta.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class KafkaConsumer {

    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.consumer.group-id}")
    private String group;

    @KafkaListener(topics = {"${spring.kafka.topic}"}, groupId = "myGroup")
    public void listen(ConsumerRecord<?, ?> record, Acknowledgment ack) {
        ack.acknowledge();
        Optional.ofNullable(record.value())
                .ifPresent(message -> {
                    log.info("【+++++++++++++++++ record = {} 】", record);
                    log.info("【+++++++++++++++++ message = {}】", message);
                });
    }
}