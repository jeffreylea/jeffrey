package com.learn.jeffrey.base.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lijianfei
 *<p></p>
 * 2020年3月20日
 */
@Setter
@Getter
public class BaseEntityDTO<T> extends BaseDTO{
	private T data;
	 
	/**
	 * 当json在反序列化时，默认选择类的无参构造函数创建类对象，当没有无参构造函数时会报错，@JsonCreator作用就是指定反序列化时用的无参构造函数。构造方法的参数前面需要加上@JsonProperty,否则会报错。

    @JsonCreator
    public Person(@JsonProperty("id") String id) {
        this.id = id;
    }
json反序列化时调用此构造函数
	 */
	@JsonCreator
    public BaseEntityDTO(@JsonProperty("code") String code, @JsonProperty("data") T entity) {
        super(code);
        this.data = entity;
    }

}
