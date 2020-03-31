package com.jeffrey.base.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

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
     * 成功执行后的封装返回
     *
     * @param data
     * @return
     */
    protected <T> BaseEntityDTO<T> successReturn(T entity) {
        return this.buildEntityDTO(entity);
    }

}
