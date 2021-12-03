package com.dong.fallback;

import com.dong.service.HelloService;

import org.springframework.stereotype.Component;

/**
 * @author dzq
 * @date 2021/12/3 7:48 下午
 */
@Component
public class MyFallBack implements HelloService {
    @Override
    public String hello() {
        return "exception happened !!!";
    }
}
