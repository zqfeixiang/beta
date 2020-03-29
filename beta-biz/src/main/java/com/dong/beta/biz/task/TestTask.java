package com.dong.beta.biz.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Scheduled(cron = "0 */10 * * * ?")
    public void reportCurrentDate(){
        System.out.println("现在时间：" + sdf.format(new Date()));
    }
}
