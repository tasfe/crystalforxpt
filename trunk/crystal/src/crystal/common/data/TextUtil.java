package crystal.common.data;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
	private static final Pattern PROP_PATTERN = Pattern
			.compile("\\$\\{\\S+?\\}");
	public static final String HTML_SPACE = "&nbsp;";
	public static final String SEPARATOR = ";";
	public static final String SEPARATOR_CHAR3 = new String(new char[] { '\3' });
	public static final String PATH_SEPARATE_SYMBOL = "/";
	public static final int MATCH_ALL = 0;
	public static final int MATCH_STARTWITH = 1;
	public static final int MATCH_ENDWITH = 2;
	public static final int MATCH_CONTAIN = 3;
	public static final int MATCH_REGEX = 4;
	public static final String[] MATCH_NMAE = { "全部匹配", "首部匹配", "尾部匹配", "包含匹配",
			"正则表达式" };
	private static Pattern IPPattern = Pattern
			.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

	public static String convMatchName(int paramInt) {
		if ((paramInt > MATCH_NMAE.length - 1) || (paramInt < 0))
			return MATCH_NMAE[0];
		return MATCH_NMAE[paramInt];
	}

	public static boolean matches(String paramString1, String paramString2,
			boolean paramBoolean, int paramInt) {
		if ((paramString1 == null) || (paramString2 == null))
			return false;
		switch (paramInt) {
		case 1:
			return ((paramBoolean) ? paramString1.startsWith(paramString2)
					: paramString1.regionMatches(true, 0, paramString2, 0,
							paramString2.length()));
		case 2:
			return ((paramBoolean) ? paramString1.endsWith(paramString2)
					: paramString1.regionMatches(true, paramString1.length()
							- paramString2.length(), paramString2, 0,
							paramString2.length()));
		case 3:
			if (!(paramBoolean)) {
				paramString1 = paramString1.toLowerCase();
				paramString2 = paramString2.toLowerCase();
			}
			return (paramString1.indexOf(paramString2) > -1);
		case 4:
			return paramString1.matches(paramString2);
		}
		return paramString1.equals(paramString2);
	}

	public static boolean matches(String paramString1, String paramString2,
			int paramInt) {
		return matches(paramString1, paramString2, paramInt);
	}

	public static String substring(String paramString, int paramInt) {
		return substring(paramString, paramInt, "...");
	}

	public static String substring(String paramString1, int paramInt,
			String paramString2) {
		if (paramString1 == null)
			return "";
		String str = "";
		if (paramString2 == null)
			paramString2 = "";
		if (paramString2.length() > 0)
			str = " ";
		paramInt *= 2;
		int i = 0;
		int j = 0;
		for (int k = 0; k < paramString1.length(); ++k) {
			if (i >= paramInt)
				return paramString1.substring(0, k) + ((j % 2 == 0) ? "" : str)
						+ paramString2;
			if (isDoubleByteChar(paramString1.charAt(k))) {
				i += 2;
			} else {
				++i;
				++j;
			}
		}
		return paramString1;
	}

	public static String convLine(String paramString1, int paramInt,
			String paramString2) {
		String str = "";
		int i = 0;
		int j = 1;
		int k = paramString1.length();
		i = k / paramInt;
		try {
			if (i > 0)
				str = paramString1.substring(0, paramInt);
			else
				str = paramString1;
			for (j = 1; j < i; ++j)
				str = str
						+ paramString2
						+ paramString1.substring(j * paramInt, (j + 1)
								* paramInt);
			str = str + paramString2 + paramString1.substring(j * paramInt);
		} catch (Exception localException) {
			str = paramString1;
		}
		return str;
	}

	public static String second2Text(long paramLong) {
		String str = "";
		if (paramLong / 3600L > 0L)
			str = (paramLong / 3600L) + "小时";
		paramLong %= 3600L;
		if (paramLong / 60L > 0L)
			str = str + (paramLong / 60L) + "分";
		paramLong %= 60L;
		str = str + paramLong + "秒";
		return str;
	}

	public static String replace(String paramString1, String paramString2,
			String paramString3) {
		if (paramString1 == null)
			return "";
		if ((paramString2 == null) || (paramString2.equals("")))
			return paramString1;
		if (paramString3 == null)
			return paramString1;
		return paramString1.replaceAll(paramString2, paramString3);
	}

	public static String getXmlStr(String paramString) {
		paramString = replace(paramString, "&", "&amp;");
		paramString = replace(paramString, "<", "&lt;");
		paramString = replace(paramString, ">", "&gt;");
		paramString = replace(paramString, "\"", "&quot;");
		paramString = replace(paramString, "'", "&apos;");
		return paramString;
	}

	public static String getStandardStr(String paramString) {
		paramString = replace(paramString, "&amp;", "&");
		paramString = replace(paramString, "&lt;", "<");
		paramString = replace(paramString, "&gt;", ">");
		paramString = replace(paramString, "&quot;", "\"");
		paramString = replace(paramString, "&apos;", "'");
		return paramString;
	}

	public static String toHTMLString(String paramString,
			boolean paramBoolean1, boolean paramBoolean2) {
		paramString = empty2space(paramString);
		if ("&nbsp;".equals(paramString))
			return paramString;
		paramString = getXmlStr(paramString);
		if (paramBoolean1)
			paramString = replace(paramString, "\n", "<br>");
		if (paramBoolean2)
			paramString = replace(paramString, " ", "&nbsp;");
		return paramString;
	}

	public static String toHTMLString(String paramString) {
		return toHTMLString(paramString, false, false);
	}

	public static String getValidString(String paramString) {
		if (paramString == null)
			paramString = "";
		return paramString;
	}

	public static String[] split(String paramString1, String paramString2) {
		if (paramString1 == null)
			return null;
		if (paramString1.equals(""))
			return new String[0];
		String[] arrayOfString = null;
		if ((paramString2 == null) || (paramString2.equals(""))) {
			arrayOfString = new String[1];
			arrayOfString[0] = paramString1;
			return arrayOfString;
		}
		ArrayList localArrayList = new ArrayList();
		int i = paramString2.length();
		int j = paramString1.indexOf(paramString2);
		String str = null;
		while (j >= 0) {
			str = paramString1.substring(0, j);
			localArrayList.add(str);
			paramString1 = paramString1.substring(j + i);
			j = paramString1.indexOf(paramString2);
		}
		localArrayList.add(paramString1);
		arrayOfString = new String[localArrayList.size()];
		localArrayList.toArray(arrayOfString);
		return arrayOfString;
	}

	public static String[] trimSplit(String paramString1, String paramString2) {
		if (paramString1 == null)
			return null;
		if (paramString1.equals(""))
			return new String[0];
		String[] arrayOfString = null;
		if ((paramString2 == null) || (paramString2.equals(""))) {
			arrayOfString = new String[1];
			arrayOfString[0] = paramString1;
			return arrayOfString;
		}
		ArrayList localArrayList = new ArrayList();
		int i = paramString2.length();
		int j = paramString1.indexOf(paramString2);
		String str = null;
		while (j >= 0) {
			str = paramString1.substring(0, j);
			if (!("".equals(str.trim())))
				localArrayList.add(str);
			paramString1 = paramString1.substring(j + i);
			j = paramString1.indexOf(paramString2);
		}
		if (!("".equals(paramString1.trim())))
			localArrayList.add(paramString1);
		arrayOfString = new String[localArrayList.size()];
		localArrayList.toArray(arrayOfString);
		return arrayOfString;
	}

	public static int[] splitToIntArr(String paramString1, String paramString2) {
		String[] arrayOfString = trimSplit(paramString1, paramString2);
		return parseIntArr(arrayOfString);
	}

	public static int[] parseIntArr(String[] paramArrayOfString) {
		if (paramArrayOfString == null)
			return null;
		int[] arrayOfInt = new int[paramArrayOfString.length];
		for (int i = 0; i < paramArrayOfString.length; ++i)
			arrayOfInt[i] = Integer.parseInt(paramArrayOfString[i]);
		return arrayOfInt;
	}

	public static String[] parseStrArr(int[] paramArrayOfInt) {
		if (paramArrayOfInt == null)
			return null;
		String[] arrayOfString = new String[paramArrayOfInt.length];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = String.valueOf(paramArrayOfInt[i]);
		return arrayOfString;
	}

	public static String[] parseStrArr(Object[] paramArrayOfObject) {
		if (paramArrayOfObject == null)
			return null;
		String[] arrayOfString = new String[paramArrayOfObject.length];
		for (int i = 0; i < arrayOfString.length; ++i)
			arrayOfString[i] = String.valueOf(paramArrayOfObject[i]);
		return arrayOfString;
	}

	public static boolean isEmpty(String paramString) {
		if (paramString == null)
			return true;
		return (paramString.trim().length() == 0);
	}

	public static boolean notEmpty(String paramString) {
		return (!(isEmpty(paramString)));
	}

	public static String notNull(String paramString) {
		return ((paramString == null) ? "" : paramString);
	}

	public static String empty2space(Object paramObject) {
		if ((paramObject == null) || (isEmpty(paramObject.toString())))
			return "&nbsp;";
		return paramObject.toString();
	}

	public static String empty2space(String paramString) {
		return empty2space(paramString);
	}

	public static int parseInt(String paramString) {
		if ((paramString == null) || (paramString.length() == 0))
			paramString = "-1";
		return Integer.parseInt(paramString);
	}

	public static String createChars(String paramString, int paramInt) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramInt; ++i)
			localStringBuffer.append(paramString);
		return localStringBuffer.toString();
	}

	public static String encode(String paramString) {
		if (paramString == null)
			return null;
		try {
			return new String(paramString.getBytes("GB2312"), "ISO8859_1");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			localUnsupportedEncodingException.printStackTrace();
		}
		return paramString;
	}

	public static String decode(String paramString) {
		if (paramString == null)
			return null;
		try {
			return new String(paramString.getBytes("ISO8859_1"), "GB2312");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			localUnsupportedEncodingException.printStackTrace();
		}
		return paramString;
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

	public static int getRealLen(String paramString) {
		if (paramString == null)
			return 0;
		return (paramString.length() + getDoubleByteCount(paramString));
	}

	public static List getProperties(String paramString) {
		if (paramString == null)
			return new ArrayList();
		Matcher localMatcher = PROP_PATTERN.matcher(paramString);
		ArrayList localArrayList = new ArrayList();
		while (localMatcher.find()) {
			String str = localMatcher.group();
			str = str.substring(2, str.length() - 1);
			localArrayList.add(str);
		}
		return localArrayList;
	}

	public static String convStringToSpotAlgorism(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		if (paramString == null)
			paramString = "";
		for (int i = 0; i < paramString.length(); ++i) {
			int j = paramString.charAt(i);
			localStringBuffer.append(j + ".");
		}
		return localStringBuffer
				.substring(0,
						(localStringBuffer.length() > 0) ? localStringBuffer
								.length() - 1 : 0);
	}

	public static String convSpotAlgorismToString(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		String[] arrayOfString = split(paramString, ".");
		for (int i = 0; i < arrayOfString.length; ++i) {
			if ("".equals(arrayOfString[i]))
				continue;
			localStringBuffer.append((char) Integer.parseInt(arrayOfString[i]));
		}
		return localStringBuffer.toString();
	}

	public static void main(String[] paramArrayOfString) {
		System.out.println("# " + convStringToSpotAlgorism("_RVr."));
		System.out.println("# " + convStringToSpotAlgorism(null));
		System.out
				.println("@ " + convSpotAlgorismToString("95.82..86.114.46."));
		System.out.println(" :" + SEPARATOR_CHAR3 + ": ");
	}

	public static String nvl(String paramString1, String paramString2) {
		return ((isEmpty(paramString1)) ? paramString2 : paramString1);
	}

	public static boolean isIPaddress(String paramString) {
		if (paramString == null)
			return false;
		Matcher localMatcher = IPPattern.matcher(paramString.trim());
		if (localMatcher.matches())
			return ((Integer.parseInt(localMatcher.group(1)) < 256)
					&& (Integer.parseInt(localMatcher.group(2)) < 256)
					&& (Integer.parseInt(localMatcher.group(3)) < 256) && (Integer
					.parseInt(localMatcher.group(4)) < 256));
		return false;
	}

	public static boolean isRegexStatement(String paramString) {
		if (paramString == null)
			return false;
		try {
			Pattern.compile(paramString);
			return true;
		} catch (Exception localException) {
		}
		return false;
	}
}
