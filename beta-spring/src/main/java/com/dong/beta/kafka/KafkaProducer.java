package com.dong.beta.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.dong.beta.entity.KafkaMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2021/12/10 5:59 下午
 */
@Component
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    @Value("${spring.kafka.topic}")
    private String topic;

    //构造器方式注入 kafkaTemplate
    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        KafkaMessage message = new KafkaMessage();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("【++++++++++++++++++ message ：{}】", gson.toJson(message));
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, gson.toJson(message));
        future.addCallback(result -> log.info("生产者成功发送消息到 topic:{} partition:{} 的消息",
                result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
                ex -> log.error("生产者发送消失败，原因：{}", ex.getMessage()));
    }
}
