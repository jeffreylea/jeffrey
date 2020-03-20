package com.learn.jeffrey.base.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.learn.jeffrey.consts.ReturnCodeConsts;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller 基础类
 */
@SuppressWarnings("all")
@Slf4j
public abstract class BaseController implements MessageBuilder {
    @Autowired
    private MessageSource messageSource;

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * 成功删除后的返回数据
     *
     * @param delNum 删除的条数
     * @return
     */
    protected <T> BaseEntityDTO<T> successDeleteReturn(int delNum) {
        return this.buildEntityDTO(ReturnCodeConsts.CODE_SUCCESS_000002, new String[]{String.valueOf(delNum)});
    }

    /**
     * 成功执行后的封装返回
     *
     * @param data
     * @return
     */
    protected <T> BaseEntityDTO<T> successReturn(T entity, String... args) {
        return this.buildEntityDTO(entity, ReturnCodeConsts.CODE_SUCCESS, args);
    }

}
