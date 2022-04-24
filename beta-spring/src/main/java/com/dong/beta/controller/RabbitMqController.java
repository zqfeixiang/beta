package com.dong.beta.controller;

import com.dong.beta.controller.vo.ResponseModel;
import com.dong.beta.constants.RoutingKey;
import com.dong.beta.rabbit.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2022/4/9 10:35 上午
 */
@Api(tags = "RabbitMq管理")
@RestController
@Slf4j
public class RabbitMqController {

    private final Producer producer;

    @Autowired
    public RabbitMqController(Producer producer) {
        this.producer = producer;
    }

    @ApiOperation("test RabbitMQ")
    @GetMapping("/rabbit")
    public ResponseModel<String> testRabbitMQ(){
        producer.produceWorkQueue();
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test fanout exchange")
    @GetMapping("/fanoutExchange")
    public ResponseModel<String> testFanoutExchange(){
        producer.sendFanoutExchange();
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test direct exchange")
    @PostMapping("/directExchange")
    public ResponseModel<String> testDirectExchange(@RequestParam("message") String message,
                                                    @RequestParam("routingKey") RoutingKey routingKey){
        producer.sendDirectExchange(message, routingKey);
        return ResponseModel.successResponse("Message sent success!");
    }

    @ApiOperation("test topic exchange")
    @PostMapping("/topicExchange")
    public ResponseModel<String> testTopicExchange(@RequestParam("message") String message,
                                                   @RequestParam("routingKey") String routingKey){
        producer.sendTopicExchange(message, routingKey);
        return ResponseModel.successResponse("Message sent success!");
    }
}
