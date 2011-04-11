package com.combanc.monitor.util;


public class StringUtils {
	
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}
	
	/**
	 * 字符串解析为int值，异常返回0
	 * @param str
	 * @return
	 */
	public static int strToInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			result = 0;
		}
		return result;
	}
	
	/**
	 * 字符串返回boolean值，1返回true，否则为false
	 * @param str
	 * @return
	 */
	public static boolean strToBool(String str) {
		if(str != null && "1".equals(str))
			return true;
		else
			return false;
	}
}