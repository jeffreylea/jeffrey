package com.learn.jeffrey.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/18 17:07
 **/
@Configuration
public class TaskDemo1 extends DynamicScheduleConfiguration {

    @Value(value = "${task.cron}")
    private String cron;

    @Override
    protected void processTask() {
        System.out.println("线程"+Thread.currentThread().getName()+"正在执行任务");
    }

    @Override
    protected String getCron() {
        return cron;
    }
}
