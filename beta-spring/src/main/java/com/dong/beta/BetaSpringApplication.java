package com.dong.beta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
//        SpringApplication.run(BetaSpringApplication.class, args);
        SpringApplication sp = new SpringApplication(BetaSpringApplication.class);
        sp.setBannerMode(Banner.Mode.OFF);
        sp.run(args);
    }

}