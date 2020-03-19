package com.learn.jeffrey.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils{
    /**
     * Patterns
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Parse date by 'yyyy-MM-dd' pattern
     *
     * @param str
     * @return
     */
    public static Date parseByDayPattern(String str) {
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat(DAY_PATTERN);
    		return sdf.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Format date by 'yyyy-MM-dd HH:mm:ss' pattern
     *
     * @param date
     * @return
     */
    public static String formatByDateTimePattern(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
		return sdf.format(date);
    }
    
    /**
     * Format date by 'yyyy-MM-dd' pattern
     *
     * @param date
     * @return
     */
    public static String formatByDatePattern(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_PATTERN);
		return sdf.format(date);
    }
    
    /**
     * 生成符合格式的时间格式
     * <p>用法：DateFormatUtils.formatDBYmdHms(DateFormatEnum.YMDHMS_SEPERATED);</p>
     *
     * @param dateFormat {@link DateFormatEnum}
     * @return
     */
    public static final String formatDBYmdHms(DateFormatEnum dateFormat) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);
        return DateFormatUtils.format(Calendar.getInstance(), dateFormat.getFormat());
    }
}