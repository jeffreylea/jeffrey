package com.learn.jeffrey.jeffreylearnquartz.repo.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobCallbackPO implements Serializable {
    /**
     * ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 任务名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 服务Id
     *
     * @mbggenerated
     */
    private String serviceId;

    /**
     * HTTP method
     *
     * @mbggenerated
     */
    private String method;

    /**
     * 回调路径
     *
     * @mbggenerated
     */
    private String callbackUrl;

    /**
     * 参数(JSON字符串)
     *
     * @mbggenerated
     */
    private String params;

    /**
     * 执行cron表达式
     *
     * @mbggenerated
     */
    private String cron;

    /**
     * 执行状态
     *
     * @mbggenerated
     */
    private String status;

    private static final long serialVersionUID = 1L;

}
