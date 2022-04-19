package com.dong.beta.rabbit;

import com.google.common.collect.Lists;

import com.dong.beta.entity.Book;
import com.dong.beta.enu.RoutingKey;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @author dzq
 * @date 2021/12/7 10:54 上午
 */
@Component
public class Producer {

    final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "* 0/45 * * * ? ")
    public void produce(){
        String message = "hello rabbit mq";
        rabbitTemplate.convertAndSend("dong.news", message);
    }

    @Scheduled(cron = "* 0/30 * * * ? ")
    public void produce2(){
        List<Book> bookList = Lists.newArrayList();
        Book book = Book.builder().id("101").name("论语").price(100).summary("Written by 1").build();
        Book book2 = Book.builder().id("102").name("大学").price(110).summary("Written by 2").build();
        Book book3 = Book.builder().id("103").name("中庸").price(120).summary("Written by 3").build();
        Book book4 = Book.builder().id("104").name("孟子").price(130).summary("Written by 4").build();
        bookList.add(book);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        Random random = new Random();
        int index = random.nextInt(bookList.size());
        rabbitTemplate.convertAndSend("dong.book", bookList.get(index));
    }

    public void produceWorkQueue(){
        for (int i = 1; i <= 20; i++){
            String message = "hello rabbit mq " + i;
            rabbitTemplate.convertAndSend("dong.work", message);
        }
    }

    /**
     * 发送消息到 fanout 交换机
     */
    public void sendFanoutExchange(){
        String exchangeName = "dong.fanout";
        String message = "Hello, every body!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    /**
     * 发送消息到 direct 交换机
     */
    public void sendDirectExchange(String message, RoutingKey routingKey){
        String exchangeName = "direct.exchange";
        rabbitTemplate.convertAndSend(exchangeName, routingKey.name(), message);
    }
    /**
     * 发送消息到 topic 交换机
     */
    public void sendTopicExchange(String message, String routingKey){
        String exchangeName = "topic.exchange";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

}
