package com.dong.controller;

import com.dong.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzq
 * @date 2021/12/3 7:37 下午
 */
@RestController
public class FeignController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/feign/hello")
    public String hello(){
        return helloService.hello();
    }
}
