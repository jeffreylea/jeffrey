package com.learn.jeffrey.base.model.dto;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;



/**
 * 返回对象封装类
 */
public interface MessageBuilder {
    MessageSource getMessageSource();


    /**
     * 封装查询出的实体数据，转化为BaseEntityDTO
     *
     * @param entity
     * @param code
     * @param args
     * @return
     */
    default <T> BaseEntityDTO<T> buildEntityDTO(T entity, String code, String... args) {
        BaseEntityDTO<T> basePageDto = new BaseEntityDTO<T>(code, entity);
        basePageDto.setMsg(getReturnCodeMsg(code, args));
        return basePageDto;
    }
    
    /**
     * 封装查询出的实体数据，转化为BaseEntityDTO
     *
     * @param entity
     * @param code
     * @param args
     * @return
     */
    default <T> BaseEntityDTO<T> buildEntityDTO(T entity, String... args) {
        BaseEntityDTO<T> basePageDto = new BaseEntityDTO<T>("000000", entity);
        return basePageDto;
    }

    /**
     * 封装查询出的实体数据，转化为BaseEntityDTO
     *
     * @param code
     * @param args
     * @return
     */
    default <T> BaseEntityDTO<T> buildEntityDTO(String code, String... args) {
        return buildEntityDTO(null, code, args);
    }

    /**
     * 获取返回码对应的消息
     *
     * @param key
     * @param args
     * @return
     */
    default String getReturnCodeMsg(String key, String... args) {
        return getMessageSource().getMessage("pages.tooltip." + key, args, LocaleContextHolder.getLocale());
    }
}
