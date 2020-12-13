package com.dong.beta;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzq
 * @date 2019/06/28
 */

@SpringBootApplication()
@MapperScan("com.dong.beta.mapper")
@RestController
@EnableScheduling
@EnableCaching
//@EnableRabbit
@EnableAsync
public class BetaSpringApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BetaSpringApplication.class, args);
        SpringApplication sp = new SpringApplication(BetaSpringApplication.class);
        sp.setBannerMode(Banner.Mode.OFF);
        sp.run(args);
    }

}