package com.learn.jeffrey.schedule;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Description <P>动态调度配置类</P>
 *
 * @Author lijianfei
 * @Date 2020/7/17 16:45
 **/
public abstract class DynamicScheduleConfiguration implements SchedulingConfigurer {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(scheduledExecutorService);
        scheduledTaskRegistrar.addTriggerTask(
                // 执行定时任务
                () -> {
                    processTask();
                },
                //  设置触发器
                triggerContext -> {
                    // 初始化定时任务周期
                    String cron = getCron();
                    CronTrigger cronTrigger = new CronTrigger(cron);
                    return cronTrigger.nextExecutionTime(triggerContext);
                }
        );
    }

    /**
     * 任务处理函数
     * 本函数需要由派生类根据业务逻辑来实现
     */
    protected abstract void processTask();

    /**
     * 本函数由派生类实现，从配置文件，数据库等方式获取参数值
     * 获取定时任务周期表达式
     *
     * @return String
     */
    protected abstract String getCron();
}
