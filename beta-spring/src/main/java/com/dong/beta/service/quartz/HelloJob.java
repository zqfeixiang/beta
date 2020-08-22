package com.dong.beta.service.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    private String Message;
    private String FloatJobValue;
    private String DoubleTriggerValue;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getFloatJobValue() {
        return FloatJobValue;
    }

    public void setFloatJobValue(String floatJobValue) {
        FloatJobValue = floatJobValue;
    }

    public String getDoubleTriggerValue() {
        return DoubleTriggerValue;
    }

    public void setDoubleTriggerValue(String doubleTriggerValue) {
        DoubleTriggerValue = doubleTriggerValue;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date()));
        JobKey key = context.getJobDetail().getKey();
        System.out.println("My job name and group are:" +
                key.getName() + ", " + key.getGroup());

        TriggerKey triggerKey = context.getTrigger().getKey();
        System.out.println("My trigger name and group are:" +
                triggerKey.getName() + ", " + triggerKey.getGroup());

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();

        System.out.println("Job message is :" + Message);
        System.out.println("Job float value is :" + FloatJobValue);
        System.out.println("Trigger message is :" + Message);
        System.out.println("Trigger double value is:" + DoubleTriggerValue);

        System.out.println("hello job");

    }
}
