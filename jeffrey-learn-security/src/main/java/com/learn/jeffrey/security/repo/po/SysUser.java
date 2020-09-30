package com.learn.jeffrey.security.repo.po;

import lombok.Data;

import java.util.Date;
@Data
public class SysUser {
    /**
     * 用户ID
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * 用户名称
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 用户账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 用户密码
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private String telephone;

    /**
     * 用户头像
     *
     * @mbggenerated
     */
    private String photo;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 密码修改时间
     *
     * @mbggenerated
     */
    private Date pwdModifyTime;

    /**
     * 上次登录租户
     *
     * @mbggenerated
     */
    private String recentTenantId;

    /**
     * 0:停用 1:正常
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

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
