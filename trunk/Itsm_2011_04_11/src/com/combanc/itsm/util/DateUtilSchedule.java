package com.combanc.itsm.util;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilSchedule {

	  public static final String Format_Date = "yyyy-MM-dd";
	  public static final String Format_Time = "HH:mm:ss";
	  public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";
	  public static final SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	  public static final SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
	  public static final SimpleDateFormat sdfdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	  public static String getCurrentDate()
	  {
	    return sdfd.format(new Date());
	  }

	  public static String getCurrentDate(String format)
	  {
	    SimpleDateFormat t = new SimpleDateFormat(format);
	    return t.format(new Date());
	  }

	  public static String getCurrentTime()
	  {
	    return sdft.format(new Date());
	  }

	  public static String getCurrentTime(String format)
	  {
	    SimpleDateFormat t = new SimpleDateFormat(format);
	    return t.format(new Date());
	  }

	  public static String getCurrentDateTime()
	  {
	    String format = "yyyy-MM-dd HH:mm:ss";
	    return getCurrentDateTime(format);
	  }

	  public static int getDayOfWeek() {
	    Calendar cal = Calendar.getInstance();
	    return cal.get(7);
	  }

	  public static int getDayOfWeek(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal.get(7);
	  }

	  public static int getDayOfMonth() {
	    Calendar cal = Calendar.getInstance();
	    return cal.get(5);
	  }

	  public static int getDayOfMonth(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal.get(5);
	  }

	  public static int getMaxDayOfMonth(Date date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal.getActualMaximum(5);
	  }

	  public static String getFirstDayOfMonth(String date)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(parse(date));
	    cal.set(5, 1);
	    return sdfd.format(cal.getTime());
	  }

	  public static int getDayOfYear() {
	    Calendar cal = Calendar.getInstance();
	    return cal.get(6);
	  }

	  public static int getDayOfYear(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return cal.get(6);
	  }

	  public static int getDayOfWeek(String date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(parse(date));
	    return cal.get(7);
	  }

	  public static int getDayOfMonth(String date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(parse(date));
	    return cal.get(5);
	  }

	  public static int getDayOfYear(String date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(parse(date));
	    return cal.get(6);
	  }

	  public static String getCurrentDateTime(String format) {
	    SimpleDateFormat t = new SimpleDateFormat(format);
	    return t.format(new Date());
	  }

	  public static String toString(Date date)
	  {
	    if (date == null)
	      return "";

	    return sdfd.format(date);
	  }

	  public static String toDateTimeString(Date date)
	  {
	    if (date == null)
	      return "";

	    return sdfdt.format(date);
	  }

	  public static String toString(Date date, String format)
	  {
	    SimpleDateFormat t = new SimpleDateFormat(format);
	    return t.format(date);
	  }

	  public static String toTimeString(Date date)
	  {
	    if (date == null)
	      return "";

	    return sdft.format(date);
	  }

	  public static int compare(String date1, String date2) {
	    return compare(date1, date2, "yyyy-MM-dd");
	  }

	  public static int compareTime(String time1, String time2) {
	    return compareTime(time1, time2, "HH:mm:ss");
	  }

	  public static int compare(String date1, String date2, String format) {
	    Date d1 = parse(date1);
	    Date d2 = parse(date2);
	    return d1.compareTo(d2);
	  }

	  public static int compareTime(String time1, String time2, String format) {
	    String[] arr1 = time1.split(":");
	    String[] arr2 = time2.split(":");
	    if (arr1.length < 2)
	      throw new RuntimeException("�����ʱ��ֵ:" + time1);

	    if (arr2.length < 2)
	      throw new RuntimeException("�����ʱ��ֵ:" + time2);

	    int h1 = Integer.parseInt(arr1[0]);
	    int m1 = Integer.parseInt(arr1[1]);
	    int h2 = Integer.parseInt(arr2[0]);
	    int m2 = Integer.parseInt(arr2[1]);
	    int s1 = 0; int s2 = 0;
	    if (arr1.length == 3)
	      s1 = Integer.parseInt(arr1[2]);

	    if (arr2.length == 3)
	      s2 = Integer.parseInt(arr2[2]);

	    if ((h1 < 0) || (h1 > 23) || (m1 < 0) || (m1 > 59) || (s1 < 0) || (s1 > 59))
	      throw new RuntimeException("�����ʱ��ֵ:" + time1);

	    if ((h2 < 0) || (h2 > 23) || (m2 < 0) || (m2 > 59) || (s2 < 0) || (s2 > 59))
	      throw new RuntimeException("�����ʱ��ֵ:" + time2);

	    if (h1 != h2)
	      return ((h1 > h2) ? 1 : -1);

	    if (m1 == m2) {
	      if (s1 == s2)
	        return 0;

	      return ((s1 > s2) ? 1 : -1);
	    }

	    return ((m1 > m2) ? 1 : -1);
	  }

	  public static boolean isTime(String time)
	  {
	    String[] arr = time.split(":");
	    if (arr.length < 2)
	      return false;
	    try
	    {
	      int h = Integer.parseInt(arr[0]);
	      int m = Integer.parseInt(arr[1]);
	      int s = 0;
	      if (arr.length == 3)
	        s = Integer.parseInt(arr[2]);

	      if ((h >= 0) && (h <= 23) && (m >= 0) && (m <= 59) && (s >= 0) && (s <= 59));
	      return false;
	    }
	    catch (Exception e) {
	      return false;
	    }
	  }

	  public static boolean isWeekend(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int t = cal.get(7);

	    return ((t == 7) || (t == 1));
	  }

	  public static boolean isWeekend(String str)
	  {
	    return isWeekend(parse(str));
	  }

	  public static Date parse(String str) {
	    if (StringUtilSchedule.isEmpty(str))
	      return null;
	    try
	    {
	      return sdfd.parse(str);
	    } catch (ParseException e) {
	      e.printStackTrace(); }
	    return null;
	  }

	  public static Date parse(String str, String format)
	  {
	    if (StringUtilSchedule.isEmpty(str))
	      return null;
	    try
	    {
	      SimpleDateFormat t = new SimpleDateFormat(format);
	      return t.parse(str);
	    } catch (ParseException e) {
	      e.printStackTrace(); }
	    return null;
	  }

	  public static Date parseDateTime(String str)
	  {
	    if (StringUtilSchedule.isEmpty(str))
	      return null;

	    if (str.length() == 10)
	      return parse(str);
	    try
	    {
	      return sdfdt.parse(str);
	    } catch (ParseException e) {
	      e.printStackTrace(); }
	    return null;
	  }

	  public static Date parseDateTime(String str, String format)
	  {
	    if (StringUtilSchedule.isEmpty(str))
	      return null;
	    try
	    {
	      SimpleDateFormat t = new SimpleDateFormat(format);
	      return t.parse(str);
	    } catch (ParseException e) {
	      e.printStackTrace(); }
	    return null;
	  }

	  public static Date addMinute(Date date, int count)
	  {
	    return new Date(date.getTime() + 60000L * count);
	  }

	  public static Date addHour(Date date, int count)
	  {
	    return new Date(date.getTime() + 3600000L * count);
	  }

	  public static Date addDay(Date date, int count)
	  {
	    return new Date(date.getTime() + 86400000L * count);
	  }

	  public static Date addWeek(Date date, int count)
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(3, count);
	    return c.getTime();
	  }

	  public static Date addMonth(Date date, int count)
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(2, count);
	    return c.getTime();
	  }

	  public static Date addYear(Date date, int count)
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(1, count);
	    return c.getTime();
	  }
	public static Date nowDate() {
		return new Date();
	}

	/*
	 * ���ص�ǰ������ ��ʽ yyyy-MM-dd
	 */
	public static String nowTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}

	/*
	 * ���ص�ǰ������ ��ʽ yyyy-MM-dd
	 */
	public static String nowYearMonthDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}

	/*
	 * ���ص�ǰ���� ��ʽ yyyy-MM
	 */
	public static String nowYearMonth() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(new Date());
	}

	/*
	 * ���ص�ǰ��� ��ʽ yyyy
	 */
	public static String nowYear() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(new Date());
	}

	/**
	 * ��ʽ�� ʱ��Ϊ��Ӧ��ʱ���ʽ
	 * 
	 * @param date
	 * @return
	 */
	public static String getYYYY(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(date);
	}

	public static String getYYYYMM(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		return formatter.format(date);
	}
	
	public static String getYYYYMM2(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format(date);
	}
	
	public static String getMM(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(date);
	}

	public static String getYYYYMMDD(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	
	public static String getYYYYMMDD(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(timeStamp);
	}
	
	public static String getMMDD(Date date) {
		if (null == date)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		return formatter.format(date);
	}
	
	public static String getYYYYMMDDHHMMSS(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(timeStamp);
	}
	
	public static String getHHMMSS(Timestamp timeStamp) {
		if (null == timeStamp)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(timeStamp);
	}
	
	public static Date getDate(String date) {
		if (null == date)
			return null;
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date newdate = myFmt.parse(date);
			return newdate;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	* ���ڵ��ַ��ʽת��ΪTimeStamp
	* @param timestampStr
	* @param pattern
	* @return
	* @throws ParseException
	*/
	public static Timestamp StrToTimestamp(String timestampStr, String pattern)
		throws ParseException {
		java.util.Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(timestampStr);
		} catch (ParseException e) {
			throw e;
		}
		return date == null ? null : new Timestamp(date.getTime());
	}
	
	// ת���������0��
	public static void toDayStart(Timestamp ts) {
		ts.setHours(0);
		ts.setMinutes(0);
		ts.setSeconds(0);
		ts.setNanos(0);
	}
	// ת���������23:59:59.999
	public static void toDayEnd(Timestamp ts) {
		ts.setHours(23);
		ts.setMinutes(59);
		ts.setSeconds(59);
		ts.setNanos(999);
	}

	public static void main(String[] args) {
		// getDate("2007-09-10");
		String str = getYYYYMMDD(new Date());
		System.out.println(str);
	}
	
}
