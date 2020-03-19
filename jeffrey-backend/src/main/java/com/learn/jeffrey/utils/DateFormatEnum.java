package com.learn.jeffrey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日期格式生成枚举类
 */
@AllArgsConstructor
@Getter
public enum DateFormatEnum {
	
	/**
	 * 格式：yyyy-MM-dd HH:mm:ss
	 */
	YMDHMS_SEPERATED("数据库存储格式日期", "yyyy-MM-dd HH:mm:ss"),

	/**
	 * 格式：yyyy-MM-dd
	 */
	YMD_SEPERATED("时间计算格式日期", "yyyy-MM-dd"),

	/**
	 * 格式：yyyyMMddHHmmssSSS
	 */
	YMDHMS_NO_SEPERATED("数据库存储格式日期", "yyyyMMddHHmmssSSS"),

	/**
	 * 格式：yyyyMMdd
	 */
	YMD_NO_SEPERATED("yyyyMMdd日期格式", "yyyyMMdd");

	private String name;
	private String format;
}
