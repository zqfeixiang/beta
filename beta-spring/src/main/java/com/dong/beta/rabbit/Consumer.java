package com.dong.beta.rabbit;

import com.dong.beta.entity.Book;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dzq
 * @date 2021/12/7 10:54 上午
 */
@Component
@Slf4j
public class Consumer {
    @RabbitListener(queues = "dong.news")
    public void receiveMsg(String msg){
        log.info("consumer received msg: {}", msg);
    }

    @RabbitListener(queues = "dong.book")
    public void receive(Book book){
        log.info("收到消息：{}", book);
    }

    @RabbitListener(queues = "dong.work")
    public void receiveWork1(String message){
        log.info("消费者1收到消息：{}, {}", message, LocalTime.now());
    }

    @RabbitListener(queues = "dong.work")
    public void receiveWork2(String message){
        log.error("消费者2收到消息：{}, {}", message, LocalTime.now());
    }

    @RabbitListener(queues = "fanout.queue1")
    public void receiveFanoutQueue1(String message){
        log.error("收到 fanout queue1 消息：{}, {}", message, LocalTime.now());
    }

    @RabbitListener(queues = "fanout.queue2")
    public void receiveFanoutQueue2(String message){
        log.error("收到 fanout queue2 消息：{}, {}", message, LocalTime.now());
    }

    // direct exchange demo
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
        )
    })
    public void listenDirectExchangeMessage(String msg){
        log.info("消费者收到direct.queue1消息.{} ", msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
        )
    })
    public void listenDirectExchangeMessage2(String msg){
        log.info("消费者收到direct.queue2消息.{} ", msg);
    }

    // topic exchange demo
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"China.#"}
    )
    })
    public void listenTopicExchangeMessage(String msg){
        log.info("消费者收到topic.queue消息.{} ", msg);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    )
    })
    public void listenTopicExchangeMessage2(String msg){
        log.info("消费者收到topic.queue2消息.{} ", msg);
    }

}
