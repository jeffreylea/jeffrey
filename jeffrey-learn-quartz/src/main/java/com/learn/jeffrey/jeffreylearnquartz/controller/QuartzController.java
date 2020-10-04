package com.learn.jeffrey.jeffreylearnquartz.controller;

import com.learn.jeffrey.jeffreylearnquartz.base.controller.BaseController;
import com.learn.jeffrey.jeffreylearnquartz.base.dto.BaseDTO;
import com.learn.jeffrey.jeffreylearnquartz.base.dto.BaseEntityDTO;
import com.learn.jeffrey.jeffreylearnquartz.job.PrintWordsJob;
import com.learn.jeffrey.jeffreylearnquartz.repo.po.JobCallbackPO;
import com.learn.jeffrey.jeffreylearnquartz.service.IJobCallbackService;
import com.learn.jeffrey.jeffreylearnquartz.service.IQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/10/4 8:47
 **/
@RestController
@RequestMapping("/apis/v1/callbackJobs")
public class QuartzController extends BaseController {
    @Autowired
    private IJobCallbackService callbackService;

    @PostMapping
    public BaseEntityDTO<Integer> addJob(JobCallbackPO po) {
        int n = callbackService.addJob(po);
        return buildEntityDTO(n);
    }


    /**
     * 修改任务执行时间
     *
     * @return 影响数据数
     */
    @PatchMapping("/{id}")
    public BaseDTO updateJob(JobCallbackPO jobCallbackPO) {
        callbackService.updateJob(jobCallbackPO);
        return new BaseDTO();
    }

    /**
     * 删除任务
     *
     * @return 影响数据数
     */
    @DeleteMapping("/{ids}")
    public BaseDTO batchDeleteJob(@PathVariable("ids") String[] ids) {
        callbackService.batchDeleteJob(ids);
        return new BaseDTO();
    }
}
