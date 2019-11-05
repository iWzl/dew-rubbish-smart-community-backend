package com.upuphub.dew.community.account.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 计算与时间相关的工具类
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/18 20:58
 */

public class DateTimeUtil {

    /**
     * 计算两个Data间隔的时间
     *
     * @param fDate 开始时间
     * @param oDate 结束时间
     * @return 两个时间之间间隔的时间
     */
    public static int daysOfTwoDate(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        aCalendar.set(Calendar.MILLISECOND, 0);
        aCalendar.set(Calendar.SECOND, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);
        long time2 = aCalendar.getTime().getTime() / 3600000;
        aCalendar.setTime(oDate);
        aCalendar.set(Calendar.MILLISECOND, 0);
        aCalendar.set(Calendar.SECOND, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);
        long time1 = aCalendar.getTime().getTime() / 3600000;
        return (int) ((time2 - time1) / (24));
    }


    /**
     * 转换序列化后时间字符串为Date
     *
     * @param formatTime 需要转换的时间字符串
     * @param format     转换后的字符串格式
     * @return 还原后的时间
     */
    public static Date dateFromFormatString(String formatTime, String format) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            date = formatter.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转换Date时间为序列化的字符串
     *
     * @param time   需要转换的时间
     * @param format 转换的格式
     * @return 转换后的时间字符串
     */
    public static String dateToFormatString(Date time, String format) {
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        date = formatter.format(time);
        return date;
    }

    /**
     * 转换时间为按照指定时区和序列化格式
     *
     * @param time   需要转换的时间
     * @param format 序列化的格式
     * @param locale 时区格式
     * @return 序列化的格式
     */
    public static String dateToFormatStringWithLocal(Date time, String format, Locale locale) {

        String date;
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        date = formatter.format(time);
        return date;
    }


    /**
     * 获取开始时间
     *
     * @param date 时间
     * @return 转换后的时间
     */
    public static Date getDayStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null == date) {
            return null;
        }
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 通过日期计算生日
     *
     * @param birthDay 毫秒级时间戳
     * @return 计算出的年龄
     */
    public static int birthToAge(Long birthDay) {
        return birthToAge(new Date(birthDay));
    }



    /**
     * 通过日期计算生日
     *
     * @param birthDay 生日的Data
     * @return 计算出的年龄
     */
    public static int birthToAge(Date birthDay) {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        //出生日期晚于当前时间，无法计算
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        //当前年份
        int yearNow = cal.get(Calendar.YEAR);
        //当前月份
        int monthNow = cal.get(Calendar.MONTH);
        //当前日期
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //计算整岁数
        age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    //当前日期在生日之前，年龄减一
                    age--;
                }
            } else {
                //当前月份在生日之前，年龄减一
                age--;
            }
        }
        return age;
    }

    public static Date lastWeekDate(Date date) {

        Date beginDayOfWeek = getBeginDayOfWeek(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDayOfWeek);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        return calendar.getTime();
    }


    public static Date lastWeekDate() {
        return lastWeekDate(null);
    }


    public static Date getBeginDayOfWeek(Date date) {
        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek += 7;
        }
        calendar.add(Calendar.DATE, 2 - dayOfWeek);
        return getDayStartTime(calendar.getTime());
    }


    public static Date getBeginDayOfWeek() {
        return getBeginDayOfWeek(null);
    }
}
