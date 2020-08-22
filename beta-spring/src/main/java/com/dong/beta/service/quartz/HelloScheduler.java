package com.dong.beta.service.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建一个JobDetail实例，将该实例与HelloJob class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("hello", "group1")
                .usingJobData("Message", "JobDetailDong")
                .usingJobData("FloatJobValue", "3.14F")
                .build();

        //创建一个trigger，定义该job立即执行，并每隔2s执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .usingJobData("Message", "TriggerDong")
                .usingJobData("DoubleTriggerValue", "2.0D")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2)
                                .repeatForever())
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        System.out.println("Current time : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(new Date()));
        scheduler.scheduleJob(jobDetail, trigger);

    }
}
