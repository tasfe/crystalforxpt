package com.combanc.itsm.util;

public class UserTypeUtil {

	public static String USER = "user";
	public static String ADMIN = "admin";
	public static String ENG = "engineer";
	public static String SYSTEM = "system";
	public static String ITMANAGER = "itmanager";
	public static String CH_USER = "用户";
	public static String CH_ADMIN = "系统管理员";
	public static String CH_ENG = "工程师";
	public static String CH_SYSTEM = "管理员";
	public static String CH_ITMANAGER = "经理";

	public static int getIntByENType(String type) {
		if (type.equals(USER))
			return 3;
		else if (type.equals(ADMIN))
			return 0;
		else if (type.equals(ENG))
			return 1;
		else if (type.equals(SYSTEM))
			return 4;
		else if (type.equals(ITMANAGER))
			return 2;
		else
			return -1;

	}

	public static String getCHStringByInt(int type) {
		String ch = "";
		if (type == 0) {
			ch = CH_ADMIN;
		} else if (type == 1) {
			ch = CH_ENG;
		} else if (type == 2) {
			ch = CH_ITMANAGER;

		} else if (type == 3) {
			ch = CH_USER;
		} else if (type == 4) {
			ch = CH_SYSTEM;
		}
		return ch;

	}

}
