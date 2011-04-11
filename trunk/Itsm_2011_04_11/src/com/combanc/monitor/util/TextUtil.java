package com.combanc.monitor.util;

public class TextUtil {

	public static int getRealLen(String paramString) {
		if (paramString == null)
			return 0;
		return (paramString.length() + getDoubleByteCount(paramString));
	}
	
	public static boolean isDoubleByteChar(char paramChar) {
		return (paramChar > '~');
	}
	
	public static int getDoubleByteCount(String paramString) {
		if (paramString == null)
			return 0;
		int i = 0;
		for (int j = 0; j < paramString.length(); ++j) {
			if (!(isDoubleByteChar(paramString.charAt(j))))
				continue;
			++i;
		}
		return i;
	}
	
	
}
