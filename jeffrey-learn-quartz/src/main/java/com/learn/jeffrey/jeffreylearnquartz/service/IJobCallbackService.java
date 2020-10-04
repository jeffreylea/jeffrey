package com.learn.jeffrey.jeffreylearnquartz.service;

import com.learn.jeffrey.jeffreylearnquartz.repo.po.JobCallbackPO;
import org.quartz.SchedulerException;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/10/4 8:58
 **/
public interface IJobCallbackService {

    /**
     * 添加任务
     * @param po
     */
    int addJob(JobCallbackPO po);

    /**
     * 停止任务
     * @param jobId
     * @param groupName
     * @return
     */
    void pauseJob(String jobId,String groupName);

    /**
     * 修改任务执行时间
     *
     * @param jobCallbackPO
     */
    int updateJob(JobCallbackPO jobCallbackPO);

    /**
     * 批量删除任务
     *
     * @param ids       任务Id数组
     */
    int batchDeleteJob(String[] ids);
}

