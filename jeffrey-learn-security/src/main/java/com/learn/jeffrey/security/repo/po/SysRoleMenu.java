package com.learn.jeffrey.security.repo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleMenu {
    /**
     * 关联ID
     *
     * @mbggenerated
     */
    private String relId;

    /**
     * 角色ID
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * 菜单ID
     *
     * @mbggenerated
     */
    private String menuId;

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
