package com.learn.jeffrey.jeffreylearnquartz.base.controller;

import com.learn.jeffrey.jeffreylearnquartz.base.dto.BaseDTO;
import com.learn.jeffrey.jeffreylearnquartz.base.dto.BaseEntityDTO;

/**
 * Description <P>基础Controller</P>
 *
 * @Author lijianfei
 * @Date 2020/10/4 11:02
 **/
public abstract class BaseController {


    /**
     * 默认返回
     *
     * @return
     */
    protected BaseDTO buildDTO() {
        return new BaseDTO("000000", "成功");
    }

    /**
     * 自定义返回码和信息
     *
     * @param code
     * @param msg
     * @return
     */
    protected BaseDTO buildDTO(String code, String msg) {
        return new BaseDTO(code, msg);
    }

    /**
     * 对象默认返回
     * @param entity 对象
     * @param <T>
     * @return
     */
    protected <T> BaseEntityDTO<T> buildEntityDTO(T entity) {
        return new BaseEntityDTO<T>("000000", "成功", entity);
    }


}
