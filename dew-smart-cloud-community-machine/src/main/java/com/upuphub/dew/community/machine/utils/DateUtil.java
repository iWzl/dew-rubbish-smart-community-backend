package com.upuphub.dew.community.machine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:41
 */
public class DateUtil {
    public static String date2String(Long date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(date));
    }

    public static String getTodayKey(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format((new Date()));
    }
}
