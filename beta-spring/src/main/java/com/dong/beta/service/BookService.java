package com.dong.beta.service;

import com.dong.beta.entity.Book;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = "dong.news")
    public void receive(Book book){
        System.out.println("收到消息：" + book);
    }
}
