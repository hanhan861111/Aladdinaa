package com.aladdin.hxe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${韩永光} on on 2018/3/23 0023 11:42..
 */

public class TimeUtils {

    private String currentTime;

    //获取当前时间前一天
    public static String getBeforeTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        String nextTime = formatter.format(c.getTime());
        return nextTime;
    }

    public static String GetCurrentTime() {
        //获取当前时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date curDate = new Date(System.currentTimeMillis());
        String currentTime = formatter.format(curDate);
        return currentTime;
    }

    //获取当前时间后一天
    public static String getNextTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        String nextTime = formatter.format(c.getTime());
        return nextTime;
    }

    public static String getAddTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatter.parse(time)); //注意在此处将 specialDate 的值改为特定日期
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 1);
        String addTime = formatter.format(c.getTime());
        return addTime;
    }

}
