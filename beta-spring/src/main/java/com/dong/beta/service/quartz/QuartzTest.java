package com.dong.beta.service.quartz;

import org.springframework.scheduling.annotation.Scheduled;

public class QuartzTest {


    @Scheduled(fixedRate = 2)
    public static void print(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
    }
}
