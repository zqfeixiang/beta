package com.dong.beta.controller;

import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.kafka.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2022/4/9 10:23 上午
 */
@Api(tags = "Kafka管理")
//@RestController
@Slf4j
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @ApiOperation("test kafka")
    @GetMapping("/kafka")
    public ResponseModel<String> testKafka(@RequestParam("message") String message){
        kafkaProducer.sendMessage(message);
        return ResponseModel.successResponse("Message sent success!");
    }
}
