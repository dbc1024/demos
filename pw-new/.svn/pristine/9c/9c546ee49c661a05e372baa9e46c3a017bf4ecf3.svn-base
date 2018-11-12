package com.ectrip.base.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;


/**
 * 实现描述：时间操作工具类
 * 
 * @author 
 * @version v1.0.0
 * @see
 * @since 13-8-12上午10:37
 */
public class DateUtils {
    public final static String DATE_CHINESE_PATTERN = "yyyy年MM月dd日";
    /**
     * 标准日期格式
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    public final static String DATE_SHORT_PATTERN = "yyyyMMdd";

    public final static String DATE_SLASH_PATTERN = "yyyy/MM/dd";

    /**
     * 标准日期时分秒毫秒格式
     */
    public final static String DATETIME_MILL_SECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 标准时间格式
     */
    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 特殊的格式 针对创建订单，拼凑的最晚支付时间
     */
    public final static String DATETIME_PATTERN_CREAT_ORDER = "yyyy-MM-dd HH:mm";

    public final static String DATETIME_SHORT_PATTERN = "yyyyMMddHHmmss";

    /**
     * 标准年小时分钟格式
     */
    public final static String HOUR_MINUTE = "HH:mm";

    /**
     * 标准年小时分钟秒格式
     */
    public final static String HOUR_MINUTE_SECOND = "HH:mm:ss";

    /**
     * Number of milliseconds in a standard day.
     */
    public static final long MILLIS_PER_DAY = 24 * DateUtils.MILLIS_PER_HOUR;

    /**
     * Number of milliseconds in a standard hour.
     */
    public static final long MILLIS_PER_HOUR = 60 * DateUtils.MILLIS_PER_MINUTE;

    /**
     * Number of milliseconds in a standard minute.
     */
    public static final long MILLIS_PER_MINUTE = 60 * DateUtils.MILLIS_PER_SECOND;

    /**
     * Number of milliseconds in a standard second.
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * 标准年月格式
     */
    public final static String MONTH_PATTERN = "yyyy-MM";

    private final static String[] WEEK_NAMES = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天" };

    /**
     * 在指定日期增加：天数
     * 
     * @param date 指定日期
     * @param days 指定天数
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (days == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }
    /**
     * 在指定日期增加：月数
     * 
     * @param date 指定日期
     * @param months 指定月数
     * @return
     */
    public static Date addMonth(Date date, int months) {
        if (months == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    /**
     * 在指定日期增加：年数
     *
     * @param date 指定日期
     * @param year 指定年数
     * @return
     */
    public static Date addYear(Date date, int year) {
        if (year == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }
    
    /**
     * 获取当前时间
     * 格式： String
     */
    public static String getTodayStr(){
	    String nowString = "";
	    String monthString = "";
	    String dayString = "";
	    String hourString = "";
	    String minString = "";
	    String secString = "";

	    Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
	    nowString = nowString + now.get(1) + "-";
	    Integer month = now.get(2) + 1;
	    if (month < 10) {
	      monthString = "0" + month;
	    }
	    else {
	      monthString = month.toString();
	    }

	    Integer day = now.get(5);
	    if (day < 10) {
	      dayString = "0" + day;
	    }
	    else {
	      dayString = day.toString();
	    }

	    nowString = nowString + monthString + "-" + dayString;

	    Integer hour = now.get(11);
	    if (hour < 10) {
	      hourString = "0" + hour;
	    }
	    else {
	      hourString = hour.toString();
	    }

	    nowString = nowString + " " + hourString;
	    Integer min = now.get(12);
	    if (min < 10) {
	      minString = "0" + min;
	    }
	    else {
	      minString = min.toString();
	    }

	    nowString = nowString + ":" + minString;
	    Integer sec = now.get(13);
	    if (sec < 10) {
	      secString = "0" + sec;
	    }
	    else {
	      secString = sec.toString();
	    }

	    nowString = nowString + ":" + secString;
	    return nowString;
    }
    
    /**
     * 获取当前时间
     * 格式： Date
     */
    public static Date getTodayDate(){
    	 return convertDate(getTodayStr(), DATETIME_PATTERN);
    }
    /**
     * 获取当前时间 : 根据规范格式
     */
    public static Date getTodayDate(String pattern){
   	 return convertDate(getTodayStr(), pattern);
   }
   
    
    /**
     * 获取当前: 时分秒
     */
    public static String getTodayTime(){
      return getTodayStr().substring(11);
    }
    
    /**
     * 在指定日期增加：小时
     * 
     * @param date 指定日期
     * @param days 指定天数
     * @return
     */
    public static Date addHour(Date date, int hour) {
        if (hour == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    /**
     * 在指定日期增加指定天数
     * 
     * @param date 指定日期
     * @param days 指定天数
     * @return
     */
    public static Date addDay(String date, int days) {
        return DateUtils.addDay(DateUtils.convertDate(date), days);
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * 当前日期之后
     * 
     * @param date
     * @return
     */
    public static boolean afterToday(Object date) {
        Date currentDate = getTodayDate();
        return DateUtils.compareDate(date, currentDate) == 1;
    }

    /**
     * 当前时间之后
     * 
     * @param date
     * @return
     */
    public static boolean afterTodayDate(Date date) {
        Date currentDate = getTodayDate();
        return currentDate.compareTo(date) == -1;
    }

    /**
     * 当前日期之前
     * 
     * @param date
     * @return
     */
    public static boolean beforeToday(Object date) {
        Date currentDate = getTodayDate();
        return DateUtils.compareDate(date, currentDate) == -1;
    }

    /**
     * 当前时间之前
     * 
     * @param date
     * @return
     */
    public static boolean beforeTodayDate(Date date) {
        Date currentDate = getTodayDate();
        return currentDate.compareTo(date) == 1;
    }

    /**
     * 比较两个日期date1大于date2 返回1 等于返回0 小于返回-1
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Object date1, Object date2) {
        if (date1 == null || date2 == null) {
            String msg = "illegal arguments,date1 and date2 must be not null.";
            throw new IllegalArgumentException(msg);
        }
        Date d1 = (Date) (date1 instanceof String ? DateUtils.convertDate((String) date1) : date1);
        Date d2 = (Date) (date2 instanceof String ? DateUtils.convertDate((String) date2) : date2);
        return d1.compareTo(d2);
    }

    public static Date convertDate(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            String msg = "the date or pattern is empty.";
            throw new IllegalArgumentException(msg);
        }
        String dateForPattern = DateUtils.formatDate(date, pattern);
        return DateUtils.convertDate(dateForPattern, pattern);
    }
    
    /**
     * 将long型整数转化为时间。
     * 
     * @param date 时间对应的long值
     * @return 时间对象
     */
    public static Date convertDate(Long date) {
        return new Date(date);
    }
    
    /**
     * 将日期或者时间戳转化为日期对象
     * 
     * @param date yyyy-MM-dd or yyyy-MM-dd HH:mm:ss or yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static Date convertDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (date.indexOf(":") > 0) {
            return DateUtils.convertDate(date, DateUtils.DATETIME_PATTERN);
        } else if (date.indexOf(".") > 0) {
            return DateUtils.convertDate(date, DateUtils.DATETIME_MILL_SECOND);
        } else {
            return DateUtils.convertDate(date, DateUtils.DATE_PATTERN);
        }
    }

    /**
     * 将日期或者时间字符串转化为日期对象
     * 
     * @param date 日期字符串
     * @param pattern 格式字符串</br> yyyy-MM-DD, yyyy/MM/DD, yyyyMMdd</br> yyyy-MM-dd-HH:mm:ss, yyyy-MM-dd HH:mm:ss
     *            格式字符串可选字符："GyMdkHmsSEDFwWahKzZ"
     * @return Date
     * @see java.text.DateFormatSymbols.patternChars</br>
     */
    public static Date convertDate(String date, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date)) {
                String msg = "the date or pattern is empty.";
                throw new IllegalArgumentException(msg);
            }
            SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
            return df.parse(date.trim());
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    /**
     * 将时间字符串转化为时间对象Time
     * 
     * @param time 时间字符串
     * @param pattern 格式字符串 yyyy-MM-dd HH:mm:ss or yyyy-MM-dd HH:mm:ss.SSS
     * @return
     */
    public static Time convertTime(String time, String pattern) {
        Date d = DateUtils.convertDate(time, pattern);
        return new Time(d.getTime());
    }

    /**
     * 获得日期相差天数
     * 
     * @param date1 日期
     * @param date2 日期
     * @return
     */
    public static int diffDate(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / DateUtils.MILLIS_PER_DAY);
    }
    
    /**
     * 获得日期相差月数
     * 
     * @param date1 日期
     * @param date2 日期
     * @return
     */
    public static int diffMonth(String date1, String date2) {
    	int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(getDateFromShortString(date1));
		c2.setTime(getDateFromShortString(date2));

		result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);

		return result == 0 ? 1 : Math.abs(result);
    }

    /**
     * 获取两个日期相差的分钟数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int diffMinute(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / DateUtils.MILLIS_PER_MINUTE);
    }

    /**
     * 格式为时间字符串
     * 
     * @param date 日期
     * @return yyyy-MM-dd Date
     */
    public static String formatDate(Date date) {
        try {
            return DateUtils.formatDate(date, DateUtils.DATE_PATTERN);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 按指定格式字符串格式时间
     * 
     * @param date 日期或者时间
     * @param pattern 格式化字符串 yyyy-MM-dd， yyyy-MM-dd HH:mm:ss, yyyy年MM月dd日 etc.</br>
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }

    /**
     * 格式为时间戳字符串
     * 
     * @param date 时间
     * @return yyyy-MM-dd HH:mm:ss Date
     */
    public static String formatDateTime(Date date) {
        try {
            return DateUtils.formatDate(date, DateUtils.DATETIME_PATTERN);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将制定时间格式为字符串'yyyyMMddHHmmss'.
     * 
     * @return
     */
    public static String formatDateToYMDHMS(Date date) {
        return DateUtils.formatDate(date, DateUtils.DATETIME_SHORT_PATTERN);
    }

    public static String formatMonth(Date date) {
        return DateUtils.formatDate(date, DateUtils.MONTH_PATTERN);
    }

    /**
     * 将当前时间格式为字符串'yyyyMMddHHmmss'.
     * 
     * @return
     */
    public static String formatNowToYMDHMS() {
        return DateUtils.formatDateToYMDHMS(getTodayDate());
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(getTodayDate().getTime());
    }

    public static Date getDateFromShortString(String str) {
        SimpleDateFormat simpleDF = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDF.parse(str);
        } catch (ParseException e) {
        	e.printStackTrace();
       	}
        return null;
    }

    /**
     * 获得本周第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfThisWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获得小时
     * 
     * @param date
     * @return
     */
    public static int getHourOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static Date getLastMonth() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month - 1);
        return c.getTime();
    }

    /**
     * 获得分钟数
     * 
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取后续第n天日期
     * 
     * @param date
     * @param n 第n天
     * @return
     */
    public static Date getNextNDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 获得星期数
     * 
     * @param date 日期
     * @return
     */
    public static int getWeekNumber(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int number = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (number == 0) {
            number = 7;
        }
        return number;
    }

    /**
     * 获得星期名称
     * 
     * @param date
     * @return
     */
    public static String getWeekNumberString(Date date) {
        int dayNum = DateUtils.getWeekNumber(date);
        return DateUtils.WEEK_NAMES[dayNum - 1];
    }

    /**
     * 是否同一天
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Object date1, Object date2) {
        return DateUtils.compareDate(date1, date2) == 0;
    }

    /**
     * 检查时间或者字符串是否合法
     * 
     * @param date 时间
     * @param pattern 格式串
     * @return
     */
    public static boolean isValidDate(String date, String pattern) {
        try {
            DateUtils.convertDate(pattern, date);
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }

    /**
     * 获得当前时间戳
     * 
     * @return Timestamp
     */
    public static Timestamp now() {
        return new Timestamp(getTodayDate().getTime());
    }

    /**
     * 获得当前时间字符串,格式为：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String nowDateTime() {
        return DateUtils.formatDate(getTodayDate(), DateUtils.DATETIME_PATTERN);
    }

    /**
     * 按指定roundType格式化日期。
     * 
     * @param date 日期
     * @param roundType
     * @return Date
     * @see java.util.Calendar.MONTH, java.util.Calendar.DATE, java.util.Calendar.HOUR, java.util.Calendar.MINUTE, java.util.Calendar.SECOND
     */
    public static Date round(Date date, int roundType) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime());
        switch (roundType) {
        case Calendar.MONTH:
            c.set(Calendar.DAY_OF_MONTH, 1);
        case Calendar.DATE:
            c.set(Calendar.HOUR_OF_DAY, 0);
        case Calendar.HOUR:
            c.set(Calendar.MINUTE, 0);
        case Calendar.MINUTE:
            c.set(Calendar.SECOND, 0);
        case Calendar.SECOND:
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
        default:
            throw new IllegalArgumentException("invalid round roundType.");
        }
    }

    /**
     * 获得当前日期对象
     * 
     * @return
     */
    public static Date today() {
        return DateUtils.convertDate(DateUtils.formatDate(getTodayDate()), DateUtils.DATE_PATTERN);
    }

    /**
     * 获得当前日期字符串,格式为：yyyy-MM-dd
     * 
     * @return
     */
    public static String todayDate() {
        return DateUtils.formatDate(getTodayDate());
    }

    /**
     * 
     * 将日期或者时间字符串转化为Timestamp对象
     * 
     * @param date 日期字符串
     * @param pattern 格式字符串</br> yyyy-MM-DD, yyyy/MM/DD, yyyyMMdd</br> yyyy-MM-dd-HH:mm:ss, yyyy-MM-dd HH:mm:ss
     * @return Timestamp
     * @author reeboo
     */
    public static Timestamp toTimestamp(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        try {
            return new Timestamp(format.parse(date).getTime());
        } catch (ParseException e) {
        }
        return null;
    }
    /**
     * 获得当天日期
     * Describe:
     * @author:hezhihong
     * @return
     * return:String 日期 yyyy-MM-dd
     * Date:2014-5-26
     */
    public static String getDays(){
		return getTodayStr().substring(0,10);
	}
    
    /**
     * 
     * Describe:当前月剩余天数
     * @author:zhangwubin
     * @param date 
     * @param split日期分割分 
     * @return
     * return:List<String>
     * Date:2014-6-27
     */
    public static List<String> getSurplusDate(Date date, String split){
    	  Calendar c = Calendar.getInstance();
		  c.setTime(new Date());
		  int year = c.get(c.YEAR);
		  int month = c.get(c.MONTH) + 1;
		  int day = c.get(c.DATE);
		  int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		  List<String> list = new ArrayList<String>();
		  for(int i = 0; i <= maxday - day; i++){
			 int num = day + i;
			  list.add(year + split + (month < 10 ? "0" + month : month) + split + (num < 10 ? "0" + num : num));
		  }
		  return list;
    }
    
    /**
     * 
     * Describe:获取当前日期月所指定的星期的所有日期
     * @author:zhangwubin
     * @param date
     * @param week
     * @return
     * return:List<String>
     * Date:2014-7-11
     */
    public static List<String> getDateByWeek(Date date, int week){
    	Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		int day = c.getMaximum(Calendar.DAY_OF_MONTH);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < day; i++){
			if(c.get(Calendar.DAY_OF_WEEK) == week){
				list.add(formatDate(c.getTime()));
			} 
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		return list;
    }
    
    /**
     * 
     * Describe:获得两个日期之间的所有天数，不包含开始和结束时间
     * @author:zhangwubin
     * @param startDate
     * @param endDate
     * @return
     * return:List<String>
     * Date:2014-7-11
     */
    public static List<String> getTimeDifference(String startDate, String endDate){
    	List<String> list = new ArrayList<String>();
    	if(startDate.equals(endDate)){
    		list.add(startDate);
    		return list;
    	}
    	if(compareDate(endDate, startDate) == -1){
    		return null;
    	}
    	Date sDate = getDateFromShortString(startDate);
    	Date eDate = getDateFromShortString(endDate);
    	while(compareDate(sDate,eDate) == -1){
    		list.add(formatDate(sDate));
    		sDate = addDay(sDate,1);
    	}
    	list.remove(0);
    	return list;
    }
    /**
     * 获得日期相差月数
     * @author:zhangwubin
     * @param date1 日期
     * @param date2 日期
     * @return
     */
    public static int differMonth(String date1, String date2) {
    	int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(getDateFromShortString(date1));
		c2.setTime(getDateFromShortString(date2));
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		return Math.abs(result);
    }
    
    public static Map getMonthDate(int year, int month) {
		Map map = new HashMap();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		String thisMonthStart = format.format(cal.getTime());// 本月开始（本月1号）;
		System.out.println("开始->" + thisMonthStart);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String thisMonthEnd = format.format(cal.getTime());
		cal.clear();
		System.out.println("结束->" + thisMonthEnd);
		map.put("startDate", thisMonthStart);
		map.put("endDate", thisMonthEnd);
		return map;
	}
}
