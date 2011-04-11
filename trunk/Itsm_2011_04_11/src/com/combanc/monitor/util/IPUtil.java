package com.combanc.monitor.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtil {

	/**
	 *  检查IP是否合法
	 * @param ip 地址
	 * @return 合法 true,不合法 false
	 */
	public static boolean isIP(String ip) {
		if (ip == null || ip.length() == 0)
			return false;
		String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
		String regex1 = "1\\d{2}";
		String regex2 = "[1-9]\\d";
		String regex3 = "\\d";
		String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
				+ regex3 + ")";
		regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
				+ ")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(ip);
		return m.matches();
	}
}
