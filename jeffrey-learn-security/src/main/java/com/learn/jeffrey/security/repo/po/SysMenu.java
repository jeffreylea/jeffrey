package com.learn.jeffrey.security.repo.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {
    /**
     * 菜单ID
     *
     * @mbggenerated
     */
    private String menuId;

    /**
     * 菜单编号
     *
     * @mbggenerated
     */
    private String menuCode;

    /**
     * 菜单名称
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * 父菜单ID
     *
     * @mbggenerated
     */
    private String parentId;

    /**
     * 级联ID
     *
     * @mbggenerated
     */
    private String cascadeId;

    /**
     * 图标
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 访问路径
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 排序号
     *
     * @mbggenerated
     */
    private Short sortNo;

    /**
     * 简拼
     *
     * @mbggenerated
     */
    private String shortAlias;

    /**
     * 全拼
     *
     * @mbggenerated
     */
    private String fullAlias;

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
