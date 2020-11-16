package com.learn.jeffrey.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName StatisticsPercisionEnum
 * @Description 趋势统计精度枚举
 * @Author lijianfei
 * @Date 2020/4/10 16:42
 **/
@AllArgsConstructor
@Getter
public enum StatisticsPercisionEnum {

    PERCISION_DAY_HOUR("dayHour", "%Y-%m-%d %H", "每天小时"),
    PERCISION_HOUR("hour", "%H", "小时"),
    PERCISION_DAY("day", "%Y-%m-%d", "天"),
    PERCISION_WEEK("week", "%Y-%u", "周"),
    PERCISION_MONTH("month", "%Y-%m", "月");

    private String percision;

    private String percisionFormat;

    private String percisionDesc;
}
