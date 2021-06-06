package com.dong.beta.controller;

import com.dong.beta.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CacheService {

    @Autowired
    DemoService demoService;

    @PostConstruct
    public void test(){
        System.out.println("================");
        System.out.println(demoService.test());
    }

//    @Scheduled(cron = "0/6 * * * * ?")
    public void test2(){
        System.out.println("test cron");
    }
}
