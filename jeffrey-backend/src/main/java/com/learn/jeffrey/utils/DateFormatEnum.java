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
    YMD_NO_SEPERATED("yyyyMMdd日期格式", "yyyyMMdd"),

    /**
     * 格式：yyyy-MM-dd HH:mm:ss SSS
     */
    YMDHMS_SEPERATED_MS("到毫秒的数据库存储格式日期", "yyyy-MM-dd HH:mm:ss SSS"),

    /**
     * yyyy-MM
     */
    YM_SEPERATED("以月为单位的数据统计时间格式", "yyyy-MM"),

    /**
     * yyyy-ww
     */
    YU_SEPERATED("以周为单位的数据统计时间格式", "yyyy-ww"),

    /**
     * E
     */
    YW_SEPERATED("以周为单位的数据统计时间格式", "E"),

    /**
     * yyMMdd
     */
    YYMD_NO_SEPERATED("yyMMdd日期格式", "yyMMdd"),

    /**
     * HH
     */
    H_SEPERATED("以小时为单位的数据统计时间格式", "HH"),
    /**
     * yyyy-MM-dd HH
     */
    YMDH_SEPERATED("以小时为单位的数据统计时间格式", "yyyy-MM-dd HH");

    private String name;
    private String format;
}
