package com.netblizzard.syslog;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class Tools {

	public Tools() {
	}
	
	// lsj 2010-03-15
	public static String decodeISO8859(String str) {
		try {
			// ���Ĳ�����
			// str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			// ���Ŀ���
			// str = new String(str.getBytes("ISO8859-1"),"GBK");
			// ���Ŀ���
			// str = new String(str.getBytes("ISO8859-1"),"GB2312");
			str = new String(str.getBytes("ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}
	
	public static String decodeGBK(String str) {
		String result = new String();
		byte[] bytes = new byte[256];
		int i = 0, j = 0, len = 0;
		// һ��������UNICODE������Ҫ�����ֽڣ������м�ո���Ҫ5���ַ���
		if (str.length() < 5)
			return str;
		if (str.length() > 768)
			return str;

		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ')
				continue;
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
				continue;
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'f')
				continue;
			break;
		}

		if (i == str.length()) {
			for (i = 0; i <= str.length() - 2; i++) {
				// ����޷��������򷵻�ԭֵ
				try {
					bytes[j] = (byte) Integer.parseInt(str.substring(i, i + 2),
							16);
				} catch (NumberFormatException e) {
					return str;
				}
				// ���ֵĸ��ֽں͵��ֽڶ�Ϊ�����Դ��������ֺͷǺ��֡�һ�����ֵ��ֽ������ַ�
				if (bytes[j] < 0) {
					len++;
				} else {
					len += 2;
				}
				;
				i += 2;
				j++;
			}

			java.io.ByteArrayInputStream bin = new java.io.ByteArrayInputStream(
					bytes);
			try {
				java.io.BufferedReader reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(bin, "GBK"));
				result = reader.readLine();
			} catch (Exception e) {
				System.err.println(e);
			}
			if (result.length() >= len / 2)
				return result.substring(0, len / 2);
			else
				return result;
		} else {
			return str;
		}
	}
}

