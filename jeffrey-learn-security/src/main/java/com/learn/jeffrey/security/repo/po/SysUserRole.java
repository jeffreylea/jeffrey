package com.learn.jeffrey.security.repo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserRole {
    /**
     * 关联ID
     *
     * @mbggenerated
     */
    private String relId;

    /**
     * 用户ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 租户ID
     *
     * @mbggenerated
     */
    private String tenantId;

    /**
     * 角色ID
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * 修改人ID
     *
     * @mbggenerated
     */
    private String modifierId;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date modifyTime;

}
