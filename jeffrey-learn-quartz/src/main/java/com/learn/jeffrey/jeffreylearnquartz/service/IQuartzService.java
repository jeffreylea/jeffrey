package com.learn.jeffrey.jeffreylearnquartz.service;

import org.quartz.Job;
import org.quartz.SchedulerException;

/**
 * Description <P>任务操作接口</P>
 *
 * @Author lijianfei
 * @Date 2020/10/3 16:51
 **/
public interface IQuartzService {

    /**
     * 添加任务可以传参数
     *
     * @param clazz     job执行类
     * @param jobId     job标识
     * @param groupName job所属组
     * @param cronExp   cron表达式
     */
    void addJob(Class<? extends Job> clazz, String jobId, String groupName, String cronExp);

    /**
     * 暂停任务
     * @param jobId
     * @param groupName
     */
    void pauseJob(String jobId,String groupName);

    /**
     * 恢复任务
     *
     * @param jobId             job标识
     * @param groupName         job所属组
     */
    void resumeJob(String jobId, String groupName);

    /**
     * 更新任务
     *
     * @param jobId             job标识
     * @param groupName         job所属组
     * @param cronExp           cron表达式
     */
    void updateJob(String jobId, String groupName, String cronExp);

    /**
     * 删除任务
     *
     * @param jobIds             job标识数组
     * @param groupName         job所属组
     */
    void batchDeleteJob(String[] jobIds, String groupName);

    /**
     * 启动所有任务
     */
    void startAllJobs() throws SchedulerException;

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs() throws SchedulerException;
}
