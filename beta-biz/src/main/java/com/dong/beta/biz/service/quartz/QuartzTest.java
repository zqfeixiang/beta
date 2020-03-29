package com.dong.beta.biz.service.quartz;

import org.quartz.JobBuilder;
import org.springframework.scheduling.annotation.Scheduled;

public class QuartzTest {


    @Scheduled(fixedRate = 2)
    public static void print(){
        System.out.println("hello");
    }
    public static void main(String[] args) {
    }
}
