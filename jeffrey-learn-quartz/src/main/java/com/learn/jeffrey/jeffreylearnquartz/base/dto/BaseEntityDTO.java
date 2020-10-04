package com.learn.jeffrey.jeffreylearnquartz.base.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseEntityDTO<T> extends BaseDTO {

    private T data;

    public BaseEntityDTO(String code, String msg, T entity) {
        super(code, msg);
        this.data = entity;
    }
}
