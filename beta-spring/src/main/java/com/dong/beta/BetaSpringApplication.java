package com.dong.beta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dzq
 * @date 2019/06/28
 */

@SpringBootApplication
@MapperScan("com.dong.beta.mapper")
@RestController
@EnableScheduling
@EnableCaching
@EnableTransactionManagement
@EnableRabbit
//@EnableKafka
@EnableAsync
@EnableSwagger2
public class BetaSpringApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BetaSpringApplication.class, args);
        SpringApplication sp = new SpringApplication(BetaSpringApplication.class);
        sp.setBannerMode(Banner.Mode.OFF);
        sp.run(args);
    }

}