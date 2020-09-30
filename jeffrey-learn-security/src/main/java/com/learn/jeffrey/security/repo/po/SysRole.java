package com.learn.jeffrey.security.repo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    /**
     * 角色ID
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * 角色编码
     *
     * @mbggenerated
     */
    private String roleCode;

    /**
     * 角色名称
     *
     * @mbggenerated
     */
    private String roleName;

    /**
     * 0:平台 1:租户 2:默认
     *
     * @mbggenerated
     */
    private Byte type;

    /**
     * 角色描述
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 0:停用 1:正常
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 创建人ID
     *
     * @mbggenerated
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

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
