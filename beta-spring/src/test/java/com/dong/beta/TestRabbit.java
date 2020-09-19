package com.dong.beta;

import com.dong.beta.entity.Book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbit {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new FanoutExchange("amqp.exchange"));
        System.out.println("创建exchange完成");
    }

    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("amqp.queue", true));
        System.out.println("queue");
    }


    @Test
    public void bind(){
        amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,
                "amqp.exchange", "amqp.dzq", null));
        System.out.println("绑定");
    }



    @Test
    public void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dongzhaoqi");
        map.put("gender", "male");
        rabbitTemplate.convertAndSend("exchange-direct", "dong.news", map);
    }

    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("dong.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 广播
     */
    @Test
    public void send(){
        rabbitTemplate.convertAndSend("exchange-fanout", "", new Book("12", "红楼梦", "四大名著-曹雪芹", 120));
    }
}
