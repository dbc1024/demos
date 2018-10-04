package com.its.sims.utils;

import java.util.Calendar;

/**
 * Created by csz on 2017/6/27.
 */
public class DateUtil {

    public static String getToday(){

        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar);
//        System.out.println(calendar.getTime());
//        System.out.println(calendar.getTimeInMillis());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int min = calendar.get(Calendar.MINUTE);
//        int sec = calendar.get(Calendar.SECOND);
//        int msec = calendar.get(Calendar.MILLISECOND);


//        String code = "" + year + "0"+month + day + hour + min + sec + "-"+msec;
        String code = "" + year + "0"+month + day;
//        System.out.println(code);

        return code;
    }



}
