package com.combanc.itsm.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private final static long DAY_MILLIS = 86400000; // 一天的毫秒数 60*60*1000*24
	private final static long HOUR_MILLIS = 3600000; // 一小时的毫秒数 60*60*1000
	private final static long MINUTE_MILLIS = 60000; // 一分钟的毫秒数 60*1000
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 日期格式化串

	public static void showTimedif(Date start, Date end) {
		show(start.getTime(), end.getTime());
	}
	public static String toString(Date date, String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}
	public static void showTimedif(Calendar start, Calendar end) {
		show(start.getTimeInMillis(), end.getTimeInMillis());
	}
	
	public static String show(long start, long end) {  //计算两个日期相差的天、小时、分钟
		long temp = end - start;
		String str=temp/DAY_MILLIS+"d"+(temp%DAY_MILLIS)/HOUR_MILLIS+"h"+((temp%DAY_MILLIS)%HOUR_MILLIS)/MINUTE_MILLIS+"m";
		return str;
	}
	
	public static int show2(long start, long end) {  //计算两个日期相差的天
		long temp = end - start;
		int i=(int) (temp/DAY_MILLIS);
		return i;
	}
	
	public static String getMMDD(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		return formatter.format(date);
	}
	
	public static String getHHMM(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(timeStamp);
	}
	public static String getYYYYMMDDHHMM(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(timeStamp);
	}
	public static String getMMDDHHMM(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		return formatter.format(timeStamp);
	}
	/**
	 * 日期date上加count月，count为负表示减
	 */
	public static Date addMonth(Date date, int count) {
		/* ${_ZVING_LICENSE_CODE_} */

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, count);
		return c.getTime();
	}
}
