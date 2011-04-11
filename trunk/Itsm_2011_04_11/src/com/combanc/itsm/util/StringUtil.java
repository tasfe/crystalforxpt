package com.combanc.itsm.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Decoder;
public class StringUtil {

	public static String generateFileName(String fileName) {
		String formatDate = new SimpleDateFormat("yyMMddHHmmss")
				.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);

		return formatDate + random + extension;
	}
	 
	 public static synchronized String getRequestNo()
	 {
		
	  
		 Long time=System.currentTimeMillis();
		
		 
		 return String.valueOf(time);
	 }
	 
	 public static String getUserTypeNameByUserType(String userType)
	 {
		 if(userType.equals("system"))
		 {
			 return "系统管理员";
		 }
		 else if(userType.equals("engineer"))
		 {
			 return "工程师";
		 }
		 else if(userType.equals("user"))
		 {
			 return "一般用户";
		
		 }
		 else{
			 return null;
		 }
	 }
	 /*
	  * 特殊字符处理,对于查询中输入的字符含有'的进行替换，换为''
	  */
	 public static final String decrateString(String string) {
			if (string == null || string.length() == 0)
				return "";
			if(string.indexOf("'")>=0){
	    		string=string.replaceAll("'", "''");
	    	}
			return string;
		}
	 /*
	  * 特殊字符处理
	  */
	 public static final String reDecrateString(String string) {
			if (string == null || string.length() == 0)
				return "";
			if(string.indexOf("''")>=0){
	    		string=string.replaceAll("''", "'");
	    	}
			return string;
		}
	 public static String[] splitEx(String str, String spilter) {
			if (str == null) {
				return null;
			}
			if (spilter == null || spilter.equals("") || str.length() < spilter.length()) {
				String[] t = { str };
				return t;
			}
			ArrayList al = new ArrayList();
			char[] cs = str.toCharArray();
			char[] ss = spilter.toCharArray();
			int length = spilter.length();
			int lastIndex = 0;
			for (int i = 0; i <= str.length() - length;) {
				boolean notSuit = false;
				for (int j = 0; j < length; j++) {
					if (cs[i + j] != ss[j]) {
						notSuit = true;
						break;
					}
				}
				if (!notSuit) {
					al.add(str.substring(lastIndex, i));
					i += length;
					lastIndex = i;
				} else {
					i++;
				}
			}
			if (lastIndex <= str.length()) {
				al.add(str.substring(lastIndex, str.length()));
			}
			String[] t = new String[al.size()];
			for (int i = 0; i < al.size(); i++) {
				t[i] = (String) al.get(i);
			}
			return t;
		}
	 public static byte[] hexDecode(String str) {
			try {
				if (str.endsWith("\n")) {
					str = str.substring(0, str.length() - 1);
				}
				char[] cs = str.toCharArray();
				return Hex.decodeHex(cs);
			} catch (DecoderException e) {
				e.printStackTrace();
			}
			return null;
		}
		public static Mapx splitToMapx(String str, String entrySpliter, String keySpliter) {
			Mapx map = new Mapx();
			String[] arr = StringUtil.splitEx(str, entrySpliter);
			for (int i = 0; i < arr.length; i++) {
				String[] arr2 = StringUtil.splitEx(arr[i], keySpliter);
				String key = arr2[0];
				if (StringUtil.isEmpty(key)) {
					continue;
				}
				key = key.trim();
				String value = null;
				if (arr2.length > 1) {
					value = arr2[1];
				}
				map.put(key, value);
			}
			return map;
		}
		public static boolean isEmpty(String str) {
			return str == null || str.length() == 0;
		}

		public static boolean isNotEmpty(String str) {
			return !StringUtil.isEmpty(str);
		}
		public static byte[] base64Decode(String s) {
			if (s != null) {
				BASE64Decoder decoder = new BASE64Decoder();
				try {
					return decoder.decodeBuffer(s);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		public static String hexEncode(byte[] bs) {
			return new String(new Hex().encode(bs));
		}

	

}
