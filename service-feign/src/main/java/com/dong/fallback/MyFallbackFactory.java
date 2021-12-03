package com.dong.fallback;

import com.dong.service.HelloService;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * @author dzq
 * @date 2021/12/3 7:54 下午
 */
@Component
public class MyFallbackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return throwable.getMessage();
            }
        };
    }
}
