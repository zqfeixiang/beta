package com.dong.beta;

import com.google.common.collect.Lists;

import com.dong.beta.email.EmailSender;
import com.dong.beta.entity.Article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzq
 * @date 2019/06/28
 */

@SpringBootApplication
@MapperScan("com.dong.beta.mapper")
@EnableScheduling
@EnableTransactionManagement
//@EnableKafka
public class BetaSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BetaSpringApplication.class, args);
        EmailSender emailSender = context.getBean(EmailSender.class);
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article();
        article1.setId(1);
        article1.setAuthor("Tom");
        article1.setTitle("title1");
        article1.setList(Lists.newArrayList("1", "2"));
        article1.setContent("article1");
        articleList.add(article1);
        //emailSender.sendEmail(articleList);
    }

}