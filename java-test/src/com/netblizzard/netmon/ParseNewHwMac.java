package com.netblizzard.netmon;

public class ParseNewHwMac {
	public static String getNewHwMac(String cstr) {
		String[] oids = cstr.split("\\.");
		StringBuilder str = new StringBuilder();
		String tmp = "";
		if(oids.length < 6) {
			return "";
		} else {
			for (int i = oids.length - 4; i > oids.length - 10; i--) {
				tmp = Integer.toHexString(Integer.parseInt(oids[i]));// 转换成16进制的字符串
				if (tmp.length() == 1) {
					tmp = "0" + tmp;// 补齐0
				}
				if(i == oids.length - 4) {
					str.insert(0, tmp);
				} else {
					str.insert(0, tmp + ".");
				}
			}
			return str.toString();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ParseNewHwMac.getNewHwMac("hwDynFdbPort.0.12.118.14.117.55.143.1.48"));
	}
}
