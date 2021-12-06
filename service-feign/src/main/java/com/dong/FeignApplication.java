package com.dong;

import com.dong.config.FeignConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author dzq
 * @date 2021/12/3 7:25 下午
 */
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FeignConfiguration.class)
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
