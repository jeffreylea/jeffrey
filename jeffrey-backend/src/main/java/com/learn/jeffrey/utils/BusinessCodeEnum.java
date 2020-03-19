package com.learn.jeffrey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijianfei
 */
@AllArgsConstructor
@Getter
public enum BusinessCodeEnum {

	BUSINESSCODE("描述", "CODE");

	private String desc;
	private String code;

}
