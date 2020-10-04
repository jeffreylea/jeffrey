package com.learn.jeffrey.jeffreylearnquartz.service.impl;

import com.learn.jeffrey.jeffreylearnquartz.job.PrintWordsJob;
import com.learn.jeffrey.jeffreylearnquartz.repo.mapper.JobCallbackMapper;
import com.learn.jeffrey.jeffreylearnquartz.repo.po.JobCallbackPO;
import com.learn.jeffrey.jeffreylearnquartz.service.IJobCallbackService;
import com.learn.jeffrey.jeffreylearnquartz.service.IQuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/10/4 8:59
 **/
@Service
public class JobCallbackServiceImpl implements IJobCallbackService {
    @Autowired
    private IQuartzService quartzService;

    @Autowired
    private JobCallbackMapper jobCallbackMapper;

    private static String GROUP_NAME = "group_name";
    @Override
    @Transactional
    public int addJob(JobCallbackPO po){
        po.setStatus("未执行");
        int result = jobCallbackMapper.insertSelective(po);
        quartzService.addJob(PrintWordsJob.class,po.getId(),GROUP_NAME,po.getCron());
        return result;
    }

    @Override
    public void pauseJob(String jobId, String groupName) {
         quartzService.pauseJob(jobId,groupName);
    }

    @Override
    @Transactional
    public int updateJob(JobCallbackPO jobCallbackPO) {

        int updateResult = jobCallbackMapper.updateByPrimaryKeySelective(jobCallbackPO);
        quartzService.updateJob(jobCallbackPO.getId(), GROUP_NAME, jobCallbackPO.getCron());
        return updateResult;
    }

    @Override
    @Transactional
    public int batchDeleteJob(String[] ids) {
        int deleteResult = jobCallbackMapper.batchDelete(ids);
        quartzService.batchDeleteJob(ids, GROUP_NAME);
        return deleteResult;
    }
}
