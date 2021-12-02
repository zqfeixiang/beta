package com.dong.beta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author dzq
 * @date 2021/12/3 12:46 下午
 */
@SpringBootApplication
@EnableEurekaClient
public class Provider02Application {
    public static void main(String[] args) {
        SpringApplication.run(Provider02Application.class, args);
    }
}
