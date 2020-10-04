package com.learn.jeffrey.jeffreylearnquartz.service.impl;

import com.learn.jeffrey.jeffreylearnquartz.service.IQuartzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.ExceptionUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/10/3 16:59
 **/
@Service
@Slf4j
public class QuartzService implements IQuartzService {
    //private final Log log = LogFactory.getLog(QuartzService.class);
    @Autowired
    Scheduler scheduler;

    @Override
    public void addJob(Class<? extends Job> clazz, String jobId, String groupName, String cronExp) {
        try {
            // 启动调度器
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("启动调度器失败", e);
        }

        // 创建Job信息
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobId, groupName)
                .requestRecovery(true)
                .build();
        // 表达式调度构建器
        CronScheduleBuilder cronschedulebuilder = CronScheduleBuilder.cronSchedule(cronExp);

        // 按新的cron表达式，创建trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobId, groupName)
                .withSchedule(cronschedulebuilder)
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("向调度器中添加任务失败");
            e.printStackTrace();
        }
    }

    @Override
    public void pauseJob(String jobId, String groupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobId,groupName));
        } catch (SchedulerException e) {
            log.error("停止调度器中的任务【所属组:{},任务名：{}】",groupName,jobId,e);
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(String jobId, String groupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobId, groupName));
        } catch (SchedulerException e) {
            log.error("恢复调度器中的任务【所属组：{}，任务名：{}】失败", groupName, jobId, e);
        }
    }

    @Override
    public void updateJob(String jobId, String groupName, String cronExp) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId, groupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (cronExp != null) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("修改调度器的任务【所属组：{}，任务名：{}】失败", groupName, jobId, e);
        }
    }

    @Override
    public void batchDeleteJob(String[] jobIds, String groupName) {
        for (String jobId : jobIds) {
            try {
                scheduler.pauseTrigger(TriggerKey.triggerKey(jobId, groupName));
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobId, groupName));
                scheduler.deleteJob(JobKey.jobKey(jobId, groupName));
            } catch (SchedulerException e) {
                log.error("删除调度器的任务【所属组：{}，任务名：{}】失败", groupName, jobId, e);
            }
        }
    }

    @Override
    public void startAllJobs() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void shutdownAllJobs() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
}
