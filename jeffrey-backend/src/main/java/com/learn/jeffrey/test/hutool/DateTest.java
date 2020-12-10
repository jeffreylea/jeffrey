package com.learn.jeffrey.test.hutool;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/26 15:37
 **/
public class DateTest {
    public static void main(String[] args) {
        System.out.println(DateUtil.date());
        System.out.println(DateUtil.dateSecond());
        System.out.println("util.date:" + new Date() + "datetime:" + new DateTime());
        System.out.println(new DateTime().toString());

        System.out.println(DateUtil.date(new Date()));

        System.out.println(DateUtil.today());
        System.out.println("当前时间的年份：" + DateUtil.year(new Date()));
        System.out.println("当前时间所属季度：" + DateUtil.quarter(new Date()));
        System.out.println("当前时间所属季度：" + DateUtil.quarterEnum(new Date()));
        System.out.println("当前时间月份：" + DateUtil.month(new Date()));
        System.out.println("当前时间月份：" + DateUtil.monthEnum(new Date()));
        System.out.println("当前时间毫秒数：" + DateUtil.millisecond(new Date()));
        System.out.println("当前时间毫秒数：" + DateUtil.thisMillisecond());

        List<DateTime> range = DateUtil.rangeToList(DateUtil.offsetDay(new Date(), -10), new Date(), DateField.HOUR);
        System.out.println(range);

    }
}
