package com.dong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * @author dzq
 * @date 2021/12/6 1:51 下午
 * 配置 feign 日志级别
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}
