package com.learn.jeffrey.utils;

import com.learn.jeffrey.consts.ReturnCodeConsts;
import com.learn.jeffrey.utils.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils{
    /**
     * Patterns
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //时间格式定义
    private static final String TIME_MS_MIN = " 000";
    public static final String TIME_HOUR_AS_START = " 00:00:00";
    private static final String TIME_HOUR_MIN = TIME_HOUR_AS_START + TIME_MS_MIN;
    private static final String TIME_MS_MAX = " 999";
    public static final String TIME_HOUR_AS_END = " 23:59:59";
    private static final String TIME_HOUR_MAX = TIME_HOUR_AS_END + TIME_MS_MAX;
    private static final String TIME_DAY_MAX = "-01";

    //正则表达式预编译
    private static final Pattern PATTERN_MONTH_FORMAT = Pattern.compile("\\d\\d\\d\\d-\\d\\d");
    private static final Pattern PATTERN_DATE_FORMAT = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d");
    private static final Pattern PATTERN_DATE_TIME_FORMAT = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d " +
            "\\d\\d:\\d\\d:\\d\\d");
    private static final Pattern PATTERN_DATE_TIME_MS_FORMAT = Pattern.compile("\\d\\d\\d\\d-\\d\\d-\\d\\d " +
            "\\d\\d:\\d\\d:\\d\\d \\d\\d\\d");

    private static final String DATE_FORMAT_ERROR_LOG = "非法的时间格式: {} - {}";

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

    /**
     * 根据精度获取起止日期内的时间刻度
     *
     * @param startDate     开始日期
     * @param endDate       截止日期
     * @param statisticsWay 统计方式
     * @return 时间刻度集合
     */
    public static List<String> getDatePeriodByStatisticsWay(String startDate, String endDate, String statisticsWay) {
        int timesBetween;
        int calendarUnit;
        DateFormatEnum dateFormatEnum;
        if (statisticsWay.equals(StatisticsPercisionEnum.PERCISION_HOUR.getPercision())) {
            timesBetween = 23;
            calendarUnit = Calendar.HOUR_OF_DAY;
            dateFormatEnum = DateFormatEnum.H_SEPERATED;
        } else if (statisticsWay.equals(StatisticsPercisionEnum.PERCISION_DAY.getPercision())) {
            timesBetween = daysBetween(startDate, endDate);
            calendarUnit = Calendar.DAY_OF_MONTH;
            dateFormatEnum = DateFormatEnum.YMD_SEPERATED;
        } else if (statisticsWay.equals(StatisticsPercisionEnum.PERCISION_MONTH.getPercision())) {
            timesBetween = monthsBetween(startDate, endDate);
            calendarUnit = Calendar.MONTH;
            dateFormatEnum = DateFormatEnum.YM_SEPERATED;
        } else if (statisticsWay.equals(StatisticsPercisionEnum.PERCISION_WEEK.getPercision())) {
            timesBetween = weeksBetween(startDate, endDate);
            calendarUnit = Calendar.WEEK_OF_YEAR;
            dateFormatEnum = DateFormatEnum.YU_SEPERATED;

        } else {
            log.error("无效的统计方式: {}", statisticsWay);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
            timesBetween = 0;
            calendarUnit = Calendar.MONTH;
            dateFormatEnum = DateFormatEnum.YM_SEPERATED;
        }
        return IntStream.rangeClosed(0, timesBetween).mapToObj(i ->
                incrementByAmount(startDate, calendarUnit, i, dateFormatEnum)).collect(Collectors.toList());
    }

    /**
     * 根据日期单位自增
     *
     * @param dateTime       日期
     * @param calendarUnit   单位
     * @param amount         数量
     * @param dateFormatEnum 时间格式
     * @return 自增后日期
     */
    private static String incrementByAmount(String dateTime, int calendarUnit, int amount, DateFormatEnum dateFormatEnum) {
        Calendar calendar = getCalendarByDateTime(dateTime, DateFormatEnum.YMDHMS_SEPERATED);
        calendar.add(calendarUnit, amount);
        return DateFormatUtils.format(calendar, dateFormatEnum.getFormat());
    }

    /**
     * 根据日期获取星期数
     *
     * @param datetime 日期
     * @return
     */
    private static String getDayOfWeekByDate(String datetime) {
        Calendar calendar = getCalendarByDateTime(datetime, DateFormatEnum.YMDHMS_SEPERATED);
        return String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    /**
     * 计算两个日期的月数差
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 月数差
     */
    public static int monthsBetween(String startDate, String endDate) {
        log.info("计算两个日期的月数差: {} - {}", startDate, endDate);
        int monthsBetween = getIntervalsByUnit(startDate, endDate, Calendar.MONTH);
        log.info("两个日期的月数差为: {}", monthsBetween);
        return monthsBetween;
    }

    /**
     * 计算两个日期的周数差
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 周数差
     */
    public static int weeksBetween(String startDate, String endDate) {
        log.info("计算两个日期的周数差: {} - {}", startDate, endDate);
        Calendar startCalendar = getCalendarByDateTime(startDate, DateFormatEnum.YMDHMS_SEPERATED);
        Calendar endCalendar = getCalendarByDateTime(endDate, DateFormatEnum.YMDHMS_SEPERATED);
        int startWeek = startCalendar.get(Calendar.WEEK_OF_YEAR);
        int endWeek = endCalendar.get(Calendar.WEEK_OF_YEAR);
        int weeksBetween = endWeek - startWeek;
        log.info("两个日期的周数差为: {}", weeksBetween);
        return weeksBetween;
    }


    /**
     * 获取两个日期的天数差
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 天数差
     */
    public static int daysBetween(String startDate, String endDate) {
        log.info("计算两个日期的天数差: {} - {}", startDate, endDate);
        if (checkDateFormat(startDate, endDate)) {
            startDate = startDate.substring(0, 10) + TIME_HOUR_AS_START;
            endDate = endDate.substring(0, 10) + TIME_HOUR_AS_START;
        } else {
            log.error(DATE_FORMAT_ERROR_LOG, startDate, endDate);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
        }
        long timesBetween = timesBetween(startDate, endDate);
        int daysBetween = (int) timesBetween / 3600 / 24;
        log.info("两个日期的天数差为: {}", daysBetween);
        return daysBetween;
    }

    /**
     * 获取两个时间的时间差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static int hoursBetween(String startTime, String endTime) {
        long timeOfSeconds = timesBetween(startTime, endTime);
        return (int) (timeOfSeconds / 3600);
    }

    /**
     * 根据日历单位获取时间差值
     *
     * @param startDate    开始日期
     * @param endDate      结束日期
     * @param calendarUnit 日历单位（支持月、年）
     * @return 时间差值
     */
    private static int getIntervalsByUnit(String startDate, String endDate, int calendarUnit) {
        if (checkDateFormat(startDate, endDate)) {
            Calendar startCalendar = getCalendarByDateTime(startDate, DateFormatEnum.YMD_SEPERATED);
            Calendar endCalendar = getCalendarByDateTime(endDate, DateFormatEnum.YMD_SEPERATED);
            int startYear = startCalendar.get(Calendar.YEAR);
            int endYear = endCalendar.get(Calendar.YEAR);
            int startMonth = startCalendar.get(Calendar.MONTH);
            int endMonth = endCalendar.get(Calendar.MONTH);
            int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
            int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
            // 获取年的差值 
            int yearInterval = endYear - startYear;
            // 如果 end 的 月-日 小于 start 的 月-日 那么 yearInterval-- 这样就得到了相差的年数
            if (endMonth < startMonth || (startMonth == endMonth && endDay < startDay)) {
                yearInterval--;
            }
            if (calendarUnit == Calendar.YEAR) {
                return yearInterval;
            } else {
                // 获取月数差值
                int monthInterval = (endMonth + 12) - startMonth;
                if (endDay < startDay) {
                    monthInterval--;
                }
                monthInterval %= 12;
                return yearInterval * 12 + monthInterval;
            }
        } else {
            log.error(DATE_FORMAT_ERROR_LOG, startDate, endDate);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
            return 0;
        }
    }

    /**
     * 计算两个日期的秒数差
     *
     * @param startDate 开始日期（yyyy-MM-dd HH:mm:ss）
     * @param endDate   截止日期（yyyy-MM-dd HH:mm:ss）
     * @return 秒数差
     */
    public static long timesBetween(String startDate, String endDate) {
        boolean isFormat = PATTERN_DATE_TIME_FORMAT.matcher(startDate).matches() &&
                PATTERN_DATE_TIME_FORMAT.matcher(endDate).matches();
        if (!isFormat) {
            log.error(DATE_FORMAT_ERROR_LOG, startDate, endDate);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
        }
        Calendar calStart = getCalendarByDateTime(startDate, DateFormatEnum.YMDHMS_SEPERATED);
        Calendar calEnd = getCalendarByDateTime(endDate, DateFormatEnum.YMDHMS_SEPERATED);
        //得到两个日期相差的天数
        return (calEnd.getTime().getTime() / 1000) - (calStart.getTime().getTime() / 1000);
    }

    /**
     * 校验时间格式是否正确
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return 是否都符合时间格式
     */
    private static boolean checkDateFormat(String startDate, String endDate) {
        boolean isStartDateFormat = PATTERN_DATE_FORMAT.matcher(startDate).matches() ||
                PATTERN_DATE_TIME_FORMAT.matcher(startDate).matches() ||
                PATTERN_DATE_TIME_MS_FORMAT.matcher(startDate).matches();
        boolean isEndDateFormat = PATTERN_DATE_FORMAT.matcher(endDate).matches() ||
                PATTERN_DATE_TIME_FORMAT.matcher(endDate).matches() ||
                PATTERN_DATE_TIME_MS_FORMAT.matcher(endDate).matches();
        return isStartDateFormat && isEndDateFormat;
    }

    /**
     * 根据日期获取日历
     *
     * @param dateTime   日期
     * @param dateFormat 日期格式
     * @return 日历对象
     */
    public static Calendar getCalendarByDateTime(String dateTime, DateFormatEnum dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getFormat());
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            log.error("日期转换异常: {}", dateTime);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取精度统计时间样式
     *
     * @param statisticsWay 统计方式
     * @return 时间样式
     */
    public static String getStatisticsWayFormat(String statisticsWay) {
        String percisionFormat = "";
        if (StringUtils.isNotEmpty(statisticsWay)) {
            for (StatisticsPercisionEnum statisticsPercision : StatisticsPercisionEnum.values()) {
                if (statisticsWay.equals(statisticsPercision.getPercision())) {
                    percisionFormat = statisticsPercision.getPercisionFormat();
                    log.info("按方式: {} 进行报表统计", statisticsPercision.getPercisionDesc());
                }
            }
        }
        if (StringUtils.isEmpty(percisionFormat)) {
            log.error("无效的统计方式: {}", statisticsWay);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
        }
        return percisionFormat;
    }

    /**
     * BI驾驶舱获取精度统计时间样式
     *
     * @param statisticsWay 统计方式
     * @return 时间样式
     */
    public static String getBICarStatisticsWayFormat(String statisticsWay) {

        String percisionFormat = "";
        if (StringUtils.isNotEmpty(statisticsWay)) {
            for (StatisticsPercisionEnum statisticsPercision : StatisticsPercisionEnum.values()) {
                if (statisticsWay.equals(statisticsPercision.getPercision())) {
                    percisionFormat = statisticsPercision.getPercisionFormat();
                    log.info("按方式: {} 统计查询", statisticsPercision.getPercisionDesc());
                }
            }
        }
        if (StringUtils.isEmpty(percisionFormat)) {
            log.error("无效的统计方式: {}", statisticsWay);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
        }
        return percisionFormat;
    }

    /**
     * 根据统计方式获取开始时间结束时间
     *
     * @param statisticsWay 统计方式
     * @return 起止时间
     */
    public static String[] getStartAndEndTimeByStatisticsWay(String statisticsWay) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern(DateFormatEnum.YMDHMS_SEPERATED.getFormat());
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        switch (statisticsWay) {
            case "day":
                startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
                endTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
                break;
            case "week":
                startTime = LocalDateTime.of(LocalDate.now().plusDays(-6), LocalTime.MIN);
                endTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
                break;
            case "month":
                startTime = LocalDateTime.of(LocalDate.now().plusDays(-29), LocalTime.MIN);
                endTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
                break;
            default:
                break;
        }
        return new String[]{df.format(startTime), df.format(endTime)};
    }

    /**
     * 根据统计方式获取时间刻度
     *
     * @param statisticsWay 统计方式
     * @return 时间刻度
     */
    public static List<String> listDateTimeByStatisticsWay(String statisticsWay) {
        String[] startAndEndTime = getStartAndEndTimeByStatisticsWay(statisticsWay);
        String startTime = startAndEndTime[0];
        String endTime = startAndEndTime[1];
        int timesBetween;
        int calendarUnit;
        DateFormatEnum dateFormatEnum;

        if (statisticsWay.equals("month")) {
            timesBetween = daysBetween(startTime, endTime);
            calendarUnit = Calendar.DAY_OF_MONTH;
            dateFormatEnum = DateFormatEnum.YMD_SEPERATED;
        } else if (statisticsWay.equals("week")) {
            timesBetween = daysBetween(startTime, endTime);
            calendarUnit = Calendar.DAY_OF_WEEK;
            dateFormatEnum = DateFormatEnum.YMD_SEPERATED;
            List<String> timeList = IntStream.rangeClosed(0, timesBetween).mapToObj(i ->
                    incrementByAmount(startTime, calendarUnit, i, dateFormatEnum)).collect(Collectors.toList());
            return timeList.stream().map(p -> getDayOfWeekByDate(p + TIME_HOUR_AS_START)).collect(Collectors.toList());
        } else if (statisticsWay.equals("day")) {
            timesBetween = hoursBetween(startTime, endTime);
            calendarUnit = Calendar.HOUR_OF_DAY;
            dateFormatEnum = DateFormatEnum.H_SEPERATED;
        } else {
            log.error("无效的统计方式: {}", statisticsWay);
            ExceptionUtils.newModelException(ReturnCodeConsts.CODE_A20001);
            timesBetween = 0;
            calendarUnit = Calendar.MONTH;
            dateFormatEnum = DateFormatEnum.YM_SEPERATED;
        }
        return IntStream.rangeClosed(0, timesBetween).mapToObj(i ->
                incrementByAmount(startTime, calendarUnit, i, dateFormatEnum)).collect(Collectors.toList());

    }

}
