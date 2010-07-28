package crystal.common;

import crystal.common.data.JCrypt;

public class Authentication {
	public static boolean comparePassword(String passwd, String dbpasswd) {
//		if(dbpasswd.length() < 2)
//			return false;
		String tmp = JCrypt.crypt(dbpasswd.substring(0, 2), passwd);

		return (dbpasswd.equals(tmp));
	}

	public static String createNewPassword(String newpasswd) {
		return JCrypt.crypt("BC", newpasswd);
	}
}
