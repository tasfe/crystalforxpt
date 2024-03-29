package crystal.common.data;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtils {

	public static final char[] HexDigits = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	public static final Pattern PTitle = Pattern.compile(
			"<title>(.+?)</title>", 34);
	public static Pattern patternHtmlTag = Pattern.compile("<[^<>]+>", 32);
	public static final Pattern PLetterOrDigit = Pattern.compile("^\\w*$", 34);
	public static final Pattern PLetter = Pattern.compile("^[A-Za-z]*$", 34);
	public static final Pattern PDigit = Pattern.compile("^\\d*$", 34);
	private static Pattern chinesePattern = Pattern.compile("[^一-龥]+", 34);
	private static Pattern idPattern = Pattern.compile("[\\w\\_\\.\\,]*", 34);

	public static byte[] md5(String src) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(src.getBytes());
			return md;
		} catch (Exception e) {
		}
		return null;
	}

	public static byte[] md5(byte[] src) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(src);
			return md;
		} catch (Exception e) {
		}
		return null;
	}

	public static String md5Base64(String str) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return base64Encode(md5.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5Base64FromHex(String md5str) {
		char[] cs = md5str.toCharArray();
		byte[] bs = new byte[16];
		for (int i = 0; i < bs.length; ++i) {
			char c1 = cs[(i * 2)];
			char c2 = cs[(i * 2 + 1)];
			byte m1 = 0;
			byte m2 = 0;
			for (byte k = 0; k < 16; k = (byte) (k + 1)) {
				if (HexDigits[k] == c1)
					m1 = k;

				if (HexDigits[k] == c2)
					m2 = k;
			}

			bs[i] = (byte) (m1 << 4 | 0 + m2);
		}

		String newstr = base64Encode(bs);
		return newstr;
	}

	public static String byteToBin(byte[] bs) {
		char[] cs = new char[bs.length * 9];
		for (int i = 0; i < bs.length; ++i) {
			byte b = bs[i];
			int j = i * 9;
			cs[j] = (((b >>> 7 & 0x1) == 1) ? 49 : '0');
			cs[(j + 1)] = (((b >>> 6 & 0x1) == 1) ? 49 : '0');
			cs[(j + 2)] = (((b >>> 5 & 0x1) == 1) ? 49 : '0');
			cs[(j + 3)] = (((b >>> 4 & 0x1) == 1) ? 49 : '0');
			cs[(j + 4)] = (((b >>> 3 & 0x1) == 1) ? 49 : '0');
			cs[(j + 5)] = (((b >>> 2 & 0x1) == 1) ? 49 : '0');
			cs[(j + 6)] = (((b >>> 1 & 0x1) == 1) ? 49 : '0');
			cs[(j + 7)] = (((b & 0x1) == 1) ? 49 : '0');
			cs[(j + 8)] = ',';
		}
		return new String(cs);
	}

	public static String base64Encode(byte[] b) {
		if (b == null)
			return null;

		return new BASE64Encoder().encode(b);
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

	public static String javaEncode(String txt) {
		if ((txt == null) || (txt.length() == 0))
			return txt;

		txt = replaceEx(txt, "\\", "\\\\");
		txt = replaceEx(txt, "\r\n", "\n");
		txt = replaceEx(txt, "\n", "\\n");
		txt = replaceEx(txt, "\"", "\\\"");
		txt = replaceEx(txt, "'", "\\'");
		return txt;
	}

	public static String javaDecode(String txt) {
		if ((txt == null) || (txt.length() == 0))
			return txt;

		txt = replaceEx(txt, "\\\\", "\\");
		txt = replaceEx(txt, "\\n", "\n");
		txt = replaceEx(txt, "\\r", "\r");
		txt = replaceEx(txt, "\\\"", "\"");
		txt = replaceEx(txt, "\\'", "'");
		return txt;
	}

	public static String[] splitEx(String str, String spilter) {
		if (str == null)
			return null;

		if ((spilter == null) || (spilter.equals(""))
				|| (str.length() < spilter.length())) {
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
			for (int j = 0; j < length; ++j)
				if (cs[(i + j)] != ss[j]) {
					notSuit = true;
					break;
				}

			if (!(notSuit)) {
				al.add(str.substring(lastIndex, i));
				i += length;
				lastIndex = i;
			}
			++i;
		}

		if (lastIndex <= str.length())
			al.add(str.substring(lastIndex, str.length()));

		String[] t = new String[al.size()];
		for (int i = 0; i < al.size(); ++i)
			t[i] = ((String) al.get(i));

		return t;
	}

	public static String replaceEx(String str, String subStr, String reStr) {
		if (str == null)
			return null;

		if ((subStr == null) || (subStr.equals(""))
				|| (subStr.length() > str.length()) || (reStr == null))
			return str;

		StringBuffer sb = new StringBuffer();
		String tmp = str;
		int index = -1;
		while (true) {
			index = tmp.indexOf(subStr);
			if (index < 0)
				break;

			sb.append(tmp.substring(0, index));
			sb.append(reStr);
			tmp = tmp.substring(index + subStr.length());
		}

		sb.append(tmp);
		return sb.toString();
	}

	public static String replaceAllIgnoreCase(String source, String oldstring,
			String newstring) {
		Pattern p = Pattern.compile(oldstring, 34);
		Matcher m = p.matcher(source);
		return m.replaceAll(newstring);
	}

	public static String quotEncode(String txt) {
		if ((txt == null) || (txt.length() == 0))
			return txt;

		txt = replaceEx(txt, "&", "&amp;");
		txt = replaceEx(txt, "\"", "&quot;");
		return txt;
	}

	public static String quotDecode(String txt) {
		if ((txt == null) || (txt.length() == 0))
			return txt;

		txt = replaceEx(txt, "&quot;", "\"");
		txt = replaceEx(txt, "&amp;", "&");
		return txt;
	}

	public static String escape(String src) {
		StringBuffer sb = new StringBuffer();
		sb.ensureCapacity(src.length() * 6);
		for (int i = 0; i < src.length(); ++i) {
			char j = src.charAt(i);
			if ((Character.isDigit(j)) || (Character.isLowerCase(j))
					|| (Character.isUpperCase(j))) {
				sb.append(j);
			} else if (j < 256) {
				sb.append("%");
				if (j < '\16')
					sb.append("0");

				sb.append(Integer.toString(j, 16));
			} else {
				sb.append("%u");
				sb.append(Integer.toString(j, 16));
			}
		}
		return sb.toString();
	}

	public static String unescape(String src) {
		StringBuffer sb = new StringBuffer();
		sb.ensureCapacity(src.length());
		int lastPos = 0;
		int pos = 0;

		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				char ch;
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					sb.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					sb.append(ch);
					lastPos = pos + 3;
				}
			} else if (pos == -1) {
				sb.append(src.substring(lastPos));
				lastPos = src.length();
			} else {
				sb.append(src.substring(lastPos, pos));
				lastPos = pos;
			}
		}

		return sb.toString();
	}

	public static String leftPad(String srcString, char c, int length) {
		if (srcString == null)
			srcString = "";

		int tLen = srcString.length();

		if (tLen >= length)
			return srcString;
		int iMax = length - tLen;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < iMax; ++i)
			sb.append(c);

		sb.append(srcString);
		return sb.toString();
	}

	public static String subString(String src, int length) {
		if (src == null)
			return null;

		int i = src.length();
		if (i > length)
			return src.substring(0, length);

		return src;
	}

	public static String subStringEx(String src, int length) {
		length *= 2;
		if (src == null)
			return null;

		int k = lengthEx(src);
		if (k > length) {
			int m = 0;
			boolean unixFlag = false;
			String osname = System.getProperty("os.name").toLowerCase();
			if ((osname.indexOf("sunos") > 0)
					|| (osname.indexOf("solaris") > 0)
					|| (osname.indexOf("aix") > 0))
				unixFlag = true;
			try {
				byte[] b = src.getBytes("Unicode");
				for (int i = 2; i < b.length; i += 2) {
					byte flag = b[(i + 1)];
					if (unixFlag)
						flag = b[i];

					if (flag == 0)
						++m;
					else
						m += 2;

					if (m > length)
						return src.substring(0, (i - 2) / 2);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException("执行方法getBytes(\"Unicode\")时出错！");
			}
		}
		return src;
	}

	public static int lengthEx(String src) {
		int length = 0;
		boolean unixFlag = false;
		String osname = System.getProperty("os.name").toLowerCase();
		if ((osname.indexOf("sunos") > 0) || (osname.indexOf("solaris") > 0)
				|| (osname.indexOf("aix") > 0))
			unixFlag = true;
		try {
			byte[] b = src.getBytes("Unicode");
			for (int i = 2; i < b.length; i += 2) {
				byte flag = b[(i + 1)];
				if (unixFlag)
					flag = b[i];

				if (flag == 0)
					++length;
				else
					length += 2;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("执行方法getBytes(\"Unicode\")时出错！");
		}
		return length;
	}

	public static String rightPad(String srcString, char c, int length) {
		if (srcString == null)
			srcString = "";

		int tLen = srcString.length();

		if (tLen >= length)
			return srcString;
		int iMax = length - tLen;
		StringBuffer sb = new StringBuffer();
		sb.append(srcString);
		for (int i = 0; i < iMax; ++i)
			sb.append(c);

		return sb.toString();
	}

	public static void printStringWithAnyCharset(String str) {
		Map map = Charset.availableCharsets();
		Object[] keys = map.keySet().toArray();
		for (int i = 0; i < keys.length; ++i) {
			System.out.println(keys[i]);
			for (int j = 0; j < keys.length; ++j) {
				System.out.print("\t");
				try {
					System.out.println("From "
							+ keys[i]
							+ " To "
							+ keys[j]
							+ ":"
							+ new String(str.getBytes(keys[i].toString()),
									keys[j].toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String toSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; ++i)
			if (c[i] == ' ') {
				c[i] = 12288;
			} else if ((c[i] <= '@') || (c[i] >= '[')) {
				if ((c[i] > '`') && (c[i] < '{')) {
					continue;
				}

				if (c[i] < '')
					c[i] = (char) (c[i] + 65248);
			}
		return new String(c);
	}

	public static String toNSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; ++i)
			if (c[i] == ' ') {
				c[i] = 12288;
			} else if (c[i] < '')
				c[i] = (char) (c[i] + 65248);

		return new String(c);
	}

	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; ++i)
			if (c[i] == 12288) {
				c[i] = ' ';
			} else if ((c[i] > 65280) && (c[i] < 65375))
				c[i] = (char) (c[i] - 65248);

		return new String(c);
	}

	public static String getHtmlTitle(String html) {
		Matcher m = PTitle.matcher(html);
		if (m.find())
			return m.group(1).trim();

		return null;
	}

	public static String getTextFromHtml(String html) {
		String text = patternHtmlTag.matcher(html).replaceAll("");
		if (isEmpty(text))
			return "";

		return text.replaceAll("[\\s　]{2,}", " ");
	}

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		return (!(isEmpty(str)));
	}

	public static final String noNull(String string, String defaultString) {
		return ((isEmpty(string)) ? defaultString : string);
	}

	public static final String noNull(String string) {
		return noNull(string, "");
	}

	public static String join(Object[] arr) {
		return join(arr, ",");
	}

	public static String join(Object[][] arr) {
		return join(arr, ",");
	}

	public static String join(Object[] arr, String spliter) {
		if (arr == null)
			return null;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			if (i != 0)
				sb.append(spliter);

			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public static String join(Object[][] arr, String spliter) {
		if (arr == null)
			return null;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			if (i != 0)
				sb.append(spliter);

			sb.append(join(arr[i], spliter));
		}
		return sb.toString();
	}

	public static int count(String str, String findStr) {
		int lastIndex = 0;
		int length = findStr.length();
		int count = 0;
		int start = 0;
		while ((start = str.indexOf(findStr, lastIndex)) >= 0) {
			lastIndex = start + length;
			++count;
		}
		return count;
	}

	public static boolean isLetterOrDigit(String str) {
		return PLetterOrDigit.matcher(str).find();
	}

	public static boolean isLetter(String str) {
		return PLetter.matcher(str).find();
	}

	public static boolean isDigit(String str) {
		return PDigit.matcher(str).find();
	}

	public static boolean containsChinese(String str) {
		return (!(chinesePattern.matcher(str).matches()));
	}

	public static boolean checkID(String str) {
		if (isEmpty(str)) {
			return true;
		}

		return (idPattern.matcher(str).matches());
	}

	public static String getURLExtName(String url) {
		if (isEmpty(url))
			return null;

		int index1 = url.indexOf(63);
		if (index1 == -1)
			index1 = url.length();

		int index2 = url.lastIndexOf(46, index1);
		if (index2 == -1)
			return null;

		int index3 = url.indexOf(47, 8);
		if (index3 == -1)
			return null;

		String ext = url.substring(index2 + 1, index1);
		if (ext.matches("[^\\/\\\\]*"))
			return ext;

		return null;
	}

	public static String getURLFileName(String url) {
		if (isEmpty(url))
			return null;

		int index1 = url.indexOf(63);
		if (index1 == -1)
			index1 = url.length();

		int index2 = url.lastIndexOf(47, index1);
		if ((index2 == -1) || (index2 < 8))
			return null;

		String ext = url.substring(index2 + 1, index1);
		return ext;
	}

	public static byte[] GBKToUTF8(String chenese) {
		char[] c = chenese.toCharArray();
		byte[] fullByte = new byte[3 * c.length];
		for (int i = 0; i < c.length; ++i) {
			int m = c[i];
			String word = Integer.toBinaryString(m);
			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; ++j)
				sb.append("0");

			sb.append(word);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");
			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);
			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();
			byte[] bf = new byte[3];
			bf[0] = b0;
			fullByte[(i * 3)] = bf[0];
			bf[1] = b1;
			fullByte[(i * 3 + 1)] = bf[1];
			bf[2] = b2;
			fullByte[(i * 3 + 2)] = bf[2];
		}
		return fullByte;
	}

	public static String toUpperCaseFirstLetter(String s) {

		return isNullStr(s) ? s : s.substring(0, 1).toUpperCase()
				+ s.substring(1);

	}

	/**
	 * 把空字符串转换为empty
	 */

	public static final String nullToEmptyOfStr(String s) {

		if (s != null)
			return s.trim();
		else
			return "";
	}

	public static final String escapeErrorChar(String s) {

		String s1 = null;

		s1 = s;

		if (s1 == null) {

			return s1;

		} else {

			s1 = replace(s1, "\\", "\\\\");

			s1 = replace(s1, "\"", "\\\"");

			return s1;

		}

	}

	/**
	 * 
	 * 取指定字符串的指定长度子字串
	 * 
	 * @param strAll
	 * 
	 * @param strLen
	 * 
	 * @return
	 */

	public static final String subStr(String strAll, int strLen) {

		String strNew = nullToEmptyOfStr(strAll);

		String myStr = "";

		if (strNew.length() >= strLen) {

			myStr = strNew.substring(0, strLen);

		} else {

			myStr = strNew;

		}

		return myStr;

	}

	public static final String replaceIgnoreCase(

	String s,

	String s1,

	String s2) {

		if (s == null)

			return null;

		String s3 = s.toLowerCase();

		String s4 = s1.toLowerCase();

		int i = 0;

		if ((i = s3.indexOf(s4, i)) >= 0) {

			char ac[] = s.toCharArray();

			char ac1[] = s2.toCharArray();

			int j = s1.length();

			StringBuffer stringbuffer = new StringBuffer(ac.length);

			stringbuffer.append(ac, 0, i).append(ac1);

			i += j;

			int k;

			for (k = i; (i = s3.indexOf(s4, i)) > 0; k = i) {

				stringbuffer.append(ac, k, i - k).append(ac1);

				i += j;

			}

			stringbuffer.append(ac, k, ac.length - k);

			return stringbuffer.toString();

		} else {

			return s;

		}

	}

	public static final String formatInputStr(String s) {

		String s1 = s;

		s1 = nullToEmptyOfStr(s1);

		s1 = escapeHTMLTags(s1);

		return s1;

	}

	public static final String escapeHTMLTags(String s) {

		if (s == null || s.length() == 0)

			return s;

		StringBuffer stringbuffer = new StringBuffer(s.length());

		byte byte0 = 32;

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if (c == '<')

				stringbuffer.append("<");

			else if (c == '>')

				stringbuffer.append(">");

			else

				stringbuffer.append(c);

		}

		return stringbuffer.toString();

	}

	// 字符串替换 s 搜索字符串 s1 要查找字符串 s2 要替换字符串
	public static String replace(String s, String s1, String s2) {

		if (s == null)

			return null;

		int i = 0;

		if ((i = s.indexOf(s1, i)) >= 0) {

			char ac[] = s.toCharArray();

			char ac1[] = s2.toCharArray();

			int j = s1.length();

			StringBuffer stringbuffer = new StringBuffer(ac.length);

			stringbuffer.append(ac, 0, i).append(ac1);

			i += j;

			int k;

			for (k = i; (i = s.indexOf(s1, i)) > 0; k = i) {

				stringbuffer.append(ac, k, i - k).append(ac1);

				i += j;

			}

			stringbuffer.append(ac, k, ac.length - k);

			return stringbuffer.toString();

		} else {

			return s;

		}

	}

	public static String replace(String s, String s1, String s2, int ai[]) {

		if (s == null)

			return null;

		int i = 0;

		if ((i = s.indexOf(s1, i)) >= 0) {

			int j = 0;

			j++;

			char ac[] = s.toCharArray();

			char ac1[] = s2.toCharArray();

			int k = s1.length();

			StringBuffer stringbuffer = new StringBuffer(ac.length);

			stringbuffer.append(ac, 0, i).append(ac1);

			i += k;

			int l;

			for (l = i; (i = s.indexOf(s1, i)) > 0; l = i) {

				j++;

				stringbuffer.append(ac, l, i - l).append(ac1);

				i += k;

			}

			stringbuffer.append(ac, l, ac.length - l);

			ai[0] = j;

			return stringbuffer.toString();

		} else {

			return s;

		}

	}

	// 把字符串转换为向量

	public static Vector splite(String s, String s1) {

		Vector vector = new Vector();

		Object obj = null;

		Object obj1 = null;

		boolean flag = false;

		int i;

		for (; s.length() >= 0; s = s.substring(i + s1.length(), s.length())) {

			i = s.indexOf(s1);

			if (i < 0) {

				vector.addElement(s);

				break;

			}

			String s2 = s.substring(0, i);

			vector.addElement(s2);

		}

		return vector;

	}

	/**
	 * 
	 * convert Array to ArrayList
	 * 
	 * @param sz
	 *            String[]
	 * 
	 * @param len
	 *            int
	 * 
	 * @return ArrayList
	 */

	public static ArrayList getArrayListFromArray(String[] sz, int len) {

		ArrayList aTmp = new ArrayList();

		if (isNullStr(sz)) {

			for (int i = 0; i < len; i++) {

				aTmp.add("");

			}

		} else {

			for (int i = 0; i < sz.length; i++) {

				aTmp.add(sz[i]);

			}

		}

		return aTmp;

	}

	/**
	 * 
	 * Convert to int Base200312291149
	 * 
	 * @param o
	 *            Object
	 * 
	 * @return int
	 */

	public static int cNum(Object o) {

		try {

			return Integer.parseInt(cStr(o));

		} catch (Exception ex) {

			return 0;

		}

	}

	/**
	 * 
	 * Convert to String from object Base200312291148
	 * 
	 * @param o
	 *            Object
	 * 
	 * @return String
	 */

	public static String cStr(Object o) {

		return o == null ? "" : o.toString();

	}

	/**
	 * 
	 * 判断此字符串是否为空、空字符串，或"null"
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static boolean isNullStr(String s) {

		return (s == null || s.equals("null") || s.equals("")) ? true : false;

	}

	/**
	 * 
	 * 如果字符串为空、空字符串，或"null"时返回"0"
	 * 
	 * @param str
	 * 
	 * @return String
	 */

	public static String emptyToZero(String str) {

		if (isNullStr(str))

			return "0";

		else

			return str.trim();

	}

	/**
	 * 
	 * 判断此字符串是否为空、空字符串，或"null"
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static boolean isNullStr(Object o) {

		return (

		o == null

		|| o.toString().equals("null")

		|| o.toString().equals(""))

		?

		// return (o==null||o.toString().equals(""))?

		true
				: false;

	}

	/**
	 * 
	 * 如果字符串str为空则转换为str1
	 * 
	 * @param str
	 * 
	 * @param str1
	 * 
	 * @return
	 */

	public static String getNullStr(String str, String str1) {

		if (isNullStr(str))

			return str1;

		else

			return str;

	}

	/**
	 * 
	 * 将字符串str转换为GBK编码格式
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static String getGBKStr(String str) {

		try {

			String temp_p;

			temp_p = str;

			// temp_p = getNullStr(temp_p, "");

			temp_p = nullToEmptyOfStr(temp_p);

			byte[] temp_t = temp_p.getBytes("ISO8859_1");

			String temp = new String(temp_t, "GBK");

			return temp;

		} catch (Exception e) {

			return "";

		}

	}

	public static String getISOStr(String str) {

		try {

			String temp_p;

			temp_p = str;

			temp_p = getNullStr(temp_p, "");

			byte[] temp_t = temp_p.getBytes("ISO8859_1");

			String temp = new String(temp_t);

			return temp;

		} catch (Exception e) {
		}

		return "null";

	}

	/**
	 * 
	 * 中文转换--文章换行的转换
	 * 
	 * @param str
	 * 
	 * @return
	 */

	public static String getText(String str) {

		if (str == null)

			return ("");

		if (str.equals(""))

			return ("");

		// 建立一个StringBuffer来处理输入数据

		StringBuffer buf = new StringBuffer(str.length() + 6);

		char ch = '\n';

		for (int i = 0; i < str.length(); i++) {

			ch = str.charAt(i);

			if (ch == '\n') {

				buf.append("<br>");

			} else if (ch == '\t') {

				buf.append("    ");

			} else if (ch == ' ') {

				buf.append(" ");

			} else {

				buf.append(ch);

			}

		}

		return buf.toString();

	}

	/**
	 * 
	 * 格式化复选框生成字串
	 * 
	 * @param selectID
	 * 
	 * @return
	 * 
	 * @deprecated
	 * 
	 * @see this.combineArray(String[] array)
	 */

	public static String getCheckbox(String[] selectID) {

		String[] ss;

		ss = selectID;

		String temp = "";

		try {

			for (int i = 0; i < ss.length; i++)

				temp += ss[i] + ",";

			if (temp.length() > 0)

				temp = temp.substring(0, temp.length() - 1);

			return temp;

		} catch (NullPointerException e) {

			return "";

		}

	}

	/**
	 * 
	 * 将字串转成日期，字串格式: yyyy/MM/dd
	 * 
	 * @author Base200312111725
	 * 
	 * @param string
	 *            String
	 * 
	 * @return Date
	 */

	public static Date toDate(String string) {

		try {

			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

			return (Date) formatter.parse(string);

		} catch (Exception ex) {

			System.err.println(

			"[ com.huanglq.util.StringUtil.toDate(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	/**
	 * 
	 * 将字串转成日期和时间，字串格式: yyyy/MM/dd HH:mm:ss
	 * 
	 * @author Base200312111726
	 * 
	 * @param string
	 *            String
	 * 
	 * @return Date
	 */

	public static Date toDatetime(String string) {

		try {

			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			return (Date) formatter.parse(string);

		} catch (Exception ex) {

			System.err.println(

			"[ com.huanglq.util.StringUtil.toDatetime(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	/**
	 * 
	 * 判断单选框是否选中
	 * 
	 * @author tempnc20031222
	 * 
	 * @param inparam
	 * 
	 * @param val
	 * 
	 * @return 是否选中
	 */

	public static String isChecked(String inparam, String val) {

		try {

			if (inparam.trim().equals(val.trim()))

				return "checked";

			else

				return "";

		} catch (Exception ex) {

			System.err.println(

			"[ com.huanglq.util.StringUtil.toDatetime(string) ]"

			+ ex.getMessage());

			return null;

		}

	}

	public static void main(String[] argv) {

		// StringUtil su = new StringUtil();

		/*
		 * 
		 * System.out.println(
		 * 
		 * "............:" + StringUtil.toUpperCaseFirstLetter(""));
		 */

		String strTest = "具备的资格：\n"
				+

				"- 高级经理岗位资格证书(No.A100xxx),上海市人事局 \n"
				+

				"- 科技管理专业高级工程师任职资格证书(No.01C4050xxx/1077xxx),上海市政府人事局 \n"
				+

				"- ISO9000 执照 上海质量管理协会 \n"
				+

				"- 原产地证书(No.960xxx/37xx),中国国际贸易促进委员会(CCPIT)上海市分会 \n"
				+

				"- 中国通信学会有线传输专业委员会会员(No.31xx) \n"
				+

				"- 上海化学化工学会高分子专业委员会会员(No.9-71xx) \n"
				+

				"- 上海塑料工程学会塑料加工专业委员会会员 \n"
				+

				"- 联系方式:手机/137 0168 8348石晓东, 电话及传真/(21)6433 8067石晓东, Email: rudyshi@hotmail.com, rudyshi@126.com \n"
				+

				"- 联系方式：mail：piaojin2@hotmail.com \n" +

				"传真/（21）6433 8067石晓东" +

				"手机：137-2557-6014\n";

		System.out.println(checkStr(strTest));

	}

	/**
	 * 
	 * 根据转义列表对字符串进行转义(escape)。
	 * 
	 * @param source
	 *            待转义的字符串
	 * 
	 * @param escapeCharMap
	 *            转义列表
	 * 
	 * @return 转义后的字符串
	 */

	public static String escapeCharacter(

	String source,

	HashMap escapeCharMap) {

		if (source == null || source.length() == 0) {

			return source;

		}

		if (escapeCharMap.size() == 0) {

			return source;

		}

		StringBuffer sb = new StringBuffer(source.length() + 100);

		StringCharacterIterator sci = new StringCharacterIterator(source);

		for (char c = sci.first();

		c != StringCharacterIterator.DONE;

		c = sci.next()) {

			String character = String.valueOf(c);

			if (escapeCharMap.containsKey(character)) {

				character = (String) escapeCharMap.get(character);

			}

			sb.append(character);

		}

		return sb.toString();

	}

	/**
	 * 
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * 
	 * @param delim
	 *            单词的分隔字符串
	 * 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 * 
	 *         如果delim为null则使用逗号作为分隔字符串。
	 * 
	 * @since 0.1
	 */

	public static String[] split(String source, String delim) {

		String[] wordLists;

		if (source == null) {

			wordLists = new String[1];

			wordLists[0] = source;

			return wordLists;

		}

		if (delim == null) {

			delim = ",";

		}

		StringTokenizer st = new StringTokenizer(source, delim);

		int total = st.countTokens();

		wordLists = new String[total];

		for (int i = 0; i < total; i++) {

			wordLists[i] = st.nextToken();

		}

		return wordLists;

	}

	/**
	 * 
	 * 此方法将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * 
	 * @param delim
	 *            单词的分隔字符
	 * 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * 
	 * @since 0.2
	 */

	public static String[] split(String source, char delim) {

		return split(source, String.valueOf(delim));

	}

	/**
	 * 
	 * 此方法将给出的字符串source使用逗号划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * 
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 * 
	 * @since 0.1
	 */

	public static String[] split(String source) {

		return split(source, ",");

	}

	/**
	 * 
	 * 将set的所有记录并成一个以 delim 分隔的字符串
	 * 
	 * @param set
	 * 
	 * @param delim
	 * 
	 * @return
	 */

	public static String combine(Set set, String delim) {

		if (set == null || set.size() == 0) {

			return "";

		}

		if (delim == null) {

			delim = "";

		}

		StringBuffer sb = new StringBuffer(100);

		for (Iterator iter = set.iterator(); iter.hasNext();) {

			sb.append(iter.next());

			sb.append(delim);

		}

		if (sb.length() >= delim.length()) {

			sb.delete(sb.length() - 1 - delim.length(), sb.length() - 1);

		}

		return sb.toString();

	}

	/**
	 * 
	 * 将set的所有记录并成一个以 , 分隔的字符串
	 * 
	 * @param set
	 * 
	 * @param delim
	 * 
	 * @return
	 */

	public static String combine(Set set) {

		return combine(set, ",");

	}

	/**
	 * 
	 * 将字符串数组合并成一个以 delim 分隔的字符串
	 * 
	 * @param array
	 *            字符串数组
	 * 
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * 
	 * @return 合并后的字符串
	 */

	public static String combineArray(String[] array, String delim) {

		if (array == null || array.length == 0) {

			return "";

		}

		int length = array.length - 1;

		if (delim == null) {

			delim = "";

		}

		StringBuffer result = new StringBuffer(length * 8);

		for (int i = 0; i < length; i++) {

			result.append(array[i]);

			result.append(delim);

		}

		result.append(array[length]);

		return result.toString();

	}

	/**
	 * 
	 * 将字符串数组合并成一个以,号分隔的字符串
	 * 
	 * @param array
	 *            字符串数组
	 * 
	 * @return 合并后的字符串
	 */

	public static String combineArray(String[] array) {

		return combineArray(array, ",");

	}

	/**
	 * 
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * 
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * 
	 * @return 合并后的字符串
	 * 
	 * @deprecated
	 */

	public static String combineStringArray(String[] array, String delim) {

		return combineArray(array, delim);

	}

	/**
	 * 
	 * 将int数组使用指定的分隔符合并成一个字符串
	 * 
	 * @param array
	 *            int[] int 数组
	 * 
	 * @param delim
	 *            String 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * 
	 * @return String 合并后的字符串
	 */

	public static String combineArray(int[] array, String delim) {

		if (array == null || array.length == 0) {

			return "";

		}

		int length = array.length - 1;

		if (delim == null) {

			delim = "";

		}

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < length; i++) {

			result.append(Integer.toString(array[i]));

			result.append(delim);

		}

		result.append(Integer.toString(array[length]));

		return result.toString();

	}

	/**
	 * 
	 * 将int数组合并成一个以,号分隔的字符串
	 * 
	 * @param array
	 *            字符串数组
	 * 
	 * @return 合并后的字符串
	 */

	public static String combineArray(int[] array) {

		return combineArray(array, ",");

	}

	/**
	 * 
	 * 将字符串List使用指定的分隔符合并成一个字符串。
	 * 
	 * @param list
	 *            List
	 * 
	 * @param delim
	 *            String
	 * 
	 * @return String
	 */

	public static String combineList(List list, String delim) {

		if (list == null || list.size() == 0) {

			return "";

		} else {

			StringBuffer result = new StringBuffer();

			for (int i = 0; i < list.size() - 1; i++) {

				result.append(list.get(i));

				result.append(delim);

			}

			result.append(list.get(list.size() - 1));

			return result.toString();

		}

	}

	/**
	 * 
	 * 将字符串List使用 , 合并成一个字符串。
	 * 
	 * @param list
	 *            List
	 * 
	 * @return String
	 */

	public static String combineList(List list) {

		return combineList(list, ",");

	}

	/**
	 * 
	 * 将字符串List使用指定的分隔符合并成一个字符串。
	 * 
	 * @param list
	 *            List
	 * 
	 * @param delim
	 *            String
	 * 
	 * @return String
	 * 
	 * @deprecated see combineList(list, delim)
	 */

	public static String combineStringList(List list, String delim) {

		return combineList(list, delim);

	}

	/**
	 * 
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。
	 * 
	 * @param c
	 *            指定的字符
	 * 
	 * @param length
	 *            指定的长度
	 * 
	 * @return 最终生成的字符串
	 * 
	 * @since 0.6
	 */

	public static String fillString(char c, int length) {

		String ret = "";

		for (int i = 0; i < length; i++) {

			ret += c;

		}

		return ret;

	}

	/**
	 * 
	 * 字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * 
	 * @param string
	 *            字符串
	 * 
	 * @param caseSensitive
	 *            是否大小写敏感
	 * 
	 * @return 包含时返回true，否则返回false
	 * 
	 * @since 0.4
	 */

	public static boolean contains(String[] strings, String string,

	boolean caseSensitive) {

		for (int i = 0; i < strings.length; i++) {

			if (caseSensitive == true) {

				if (strings[i].equals(string)) {

					return true;

				}

			} else {

				if (strings[i].equalsIgnoreCase(string)) {

					return true;

				}

			}

		}

		return false;

	}

	/**
	 * 
	 * 字符串数组中是否包含指定的字符串。大小写敏感。
	 * 
	 * @param strings
	 *            字符串数组
	 * 
	 * @param string
	 *            字符串
	 * 
	 * @return 包含时返回true，否则返回false
	 * 
	 * @since 0.4
	 */

	public static boolean contains(String[] strings, String string) {

		return contains(strings, string, true);

	}

	/**
	 * 
	 * 不区分大小写判定字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * 
	 * @param string
	 *            字符串
	 * 
	 * @return 包含时返回true，否则返回false
	 * 
	 * @since 0.4
	 */

	public static boolean containsIgnoreCase(String[] strings, String string) {

		return contains(strings, string, false);

	}

	/**
	 * 
	 * 得到字符串的字节长度
	 * 
	 * @param source
	 *            字符串
	 * 
	 * @return 字符串的字节长度
	 * 
	 * @since 0.6
	 */

	public static int getByteLength(String source) {

		int len = 0;

		for (int i = 0; i < source.length(); i++) {

			char c = source.charAt(i);

			int highByte = c >>> 8;

			len += highByte == 0 ? 1 : 2;

		}

		return len;

	}

	/**
	 * 
	 * 判断字符是否为双字节字符，如中文
	 * 
	 * @param c
	 *            char
	 * 
	 * @return boolean
	 */

	public static boolean isDoubleByte(char c) {

		return !((c >>> 8) == 0);

	}

	/**
	 * 
	 * 输出固定字节长度的字符串
	 * 
	 * @param source
	 *            String
	 * 
	 * @param len
	 *            int
	 * 
	 * @param exChar
	 *            String
	 * 
	 * @param exStr
	 *            String
	 * 
	 * @return String
	 */

	public static String getSubStr(String source, int len, String exChar,

	String exStr) {

		if (source == null || getByteLength(source) <= len) {

			return source;

		}

		StringBuffer result = new StringBuffer();

		char c = '\u0000';

		int i = 0, j = 0;

		for (; i < len; j++) {

			result.append(c);

			c = source.charAt(j);

			i += isDoubleByte(c) ? 2 : 1;

		}

		/**
		 * 
		 * 到这里i有两种情况：等于len或是len+1，如果是len+1，说明是双字节，并多出一个字节
		 * 
		 * 这时候就只能append(exChar)，否则就append(c)
		 */

		if (i > len) {

			result.append(exChar);

		} else {

			result.append(c);

		}

		result.append(exStr);

		return result.toString();

	}

	public static String getSubStr(String source, int len) {

		return getSubStr(source, len, ".", "...");

	}

	/**
	 * 
	 * 判断输入参数是否为null返回一个非null的值
	 * 
	 * @param s
	 *            String 判断的值
	 * 
	 * @return String
	 */

	public static String getNotNullStr(String s) {

		return (s == null) ? "" : s.trim();

	}

	/**
	 * 
	 * 返回一个十进制的整数
	 * 
	 * @param s
	 *            String 要转换为整数的字符串
	 * 
	 * @param defaultValue
	 *            int 当s不为整数时返回的默认值
	 * 
	 * @return int
	 * 
	 * @deprecated
	 * 
	 * @see NumberUtil
	 */

	public static int parseInt(String s, int defaultValue) {

		int rt = defaultValue;

		try {

			rt = Integer.parseInt(s);

		} catch (NumberFormatException e) {

			rt = defaultValue;

		}

		return rt;

	}

	/**
	 * 
	 * 将字符串的第一个字母大写
	 * 
	 * @param s
	 *            String
	 * 
	 * @return String
	 */

	public static String firstCharToUpperCase(String s) {

		if (s == null || s.length() < 1) {

			return "";

		}

		char[] arrC = s.toCharArray();

		arrC[0] = Character.toUpperCase(arrC[0]);

		return String.copyValueOf(arrC);

		/*
		 * 
		 * char c = s.charAt(0);
		 * 
		 * c = Character.toUpperCase(c);
		 * 
		 * return Character.toString(c) + s.substring(1);
		 */

	}

	/**
	 * 
	 * 根据当前字节长度取出加上当前字节长度不超过最大字节长度的子串
	 * 
	 * @param str
	 * 
	 * @param currentLen
	 * 
	 * @param MAX_LEN
	 * 
	 * @return
	 */

	public static String getSubStr(String str, int currentLen, int MAX_LEN) {

		int i;

		for (i = 0; i < str.length(); i++) {

			if (str.substring(0, i + 1).getBytes().length + currentLen > MAX_LEN) {

				break;

			}

		}

		if (i == 0) {

			return "";

		} else {

			return str.substring(0, i);

		}

	}

	private static String[] arrAntiKeys = new String[] { "抗日", "日货", "游行",
			"示威", "抗议", "钓鱼岛", "法轮", "网络警察", "WWTTJJLL" };

	/**
	 * 
	 * 关键字替换成??
	 * 
	 * @param keys
	 * 
	 * @param arg
	 * 
	 * @return
	 */

	public static String replaceByKeys(String[] keys, String arg) {

		String sbf = arg;

		String[] getarrAntiKeys = arrAntiKeys;

		if ((keys != null) && (keys.length > 0))
			getarrAntiKeys = keys;

		for (int i = 0; i < getarrAntiKeys.length; i++) {

			sbf = sbf.replaceAll(getarrAntiKeys[i], "??");

		}

		return sbf;

	}

	/**
	 * 
	 * 关键字删除
	 * 
	 * @param parm
	 * 
	 * @return
	 */

	public static String unicodeReplDel(String parm) {

		int area1min = 9601;

		int area1max = 9794;

		int area2min = 12288;

		int area2max = 12311;

		StringBuffer result = new StringBuffer("");

		if (parm.trim().length() > 0) {

			for (int i = 0; i < parm.length(); i++) {

				char c = parm.charAt(i);

				if (!(((int) c >= area1min && (int) c <= area1max) || ((int) c >= area2min && (int) c <= area2max))) {

					result.append(c);

				}

			}

		}

		return result.toString();

	}

	/**
	 * 
	 * 将电话号码及邮件地址关键字替换成xx
	 * 
	 * @param parm
	 *            String
	 * 
	 * @return String
	 */

	public static String checkStr(String parm) {

		String sbf = parm;

		String destsbf = sbf;

		String tempstr = "";

		Pattern pattern;

		Matcher matcher;

		Vector regxs = new Vector();

		regxs
				.add("([0０零]{0,1}(([0-9０１２３４５６７８９零壹贰参肆伍陆柒捌玖一二三四五六七八九]{3})|(10)|(21)|(22)|(23)|(24)|(25)|(26)|(27)|(28)|(29)|(壹零)|(贰壹)|(贰贰)|(贰参)|(贰肆)|(贰伍)|(贰陆)|(贰柒)|(贰捌)|(贰玖)|(一(0|０))|(二一)|(二二)|(二三)|(二四)|(二五)|(二六)|(二七)|(二八)|(二九))[ 　]{0,}[\\)\\）]{0,}[ 　]{0,}([-－]{1}|[ 　]{0,})[ 　]{0,}[0-9０１２３４５６７８９零壹贰参肆伍陆柒捌玖一二三四五六七八九 　]{6,}[0-9０１２３４５６７８９零壹贰参肆伍陆柒捌玖一二三四五六七八九]{1})|([零壹贰参肆伍陆柒捌玖一二三四五六七八九 ]{6,}[零壹贰参肆伍陆柒捌玖一二三四五六七八九]{1})");

		regxs
				.add("[1１壹一]{1}[3３参三]{1}[0-9０１２３４５６７８９零壹贰参肆伍陆柒捌玖一二三四五六七八九 　－-]{7,}[0-9０１２３４５６７８９零壹贰参肆伍陆柒捌玖一二三四五六七八九]{2}");

		regxs
				.add("[Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+([-+.][Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+)*((@)|(＠))[Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+([-.][Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+)*((\\.)|(。)|(．))[Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+([-.][Ａ-Ｚａ-ｚA-Za-z0-9_０１２３４５６７８９]+)*");

		for (int i = 0; i <= regxs.size() - 2; i++) {

			pattern = Pattern.compile((String) regxs.get(i));

			matcher = pattern.matcher(sbf);

			while (matcher.find()) {

				tempstr = matcher.group();

				if ((tempstr.indexOf(")") > 0) || (tempstr.indexOf("）") > 0)) {

					if (tempstr.indexOf(")") > 0) {

						tempstr = tempstr.substring(0, tempstr.indexOf(")"))
								+ "\\"
								+ tempstr.substring(tempstr.indexOf(")"));

					}

					if (tempstr.indexOf("）") > 0) {

						tempstr = tempstr.substring(0, tempstr.indexOf("）"))
								+ "\\"
								+ tempstr.substring(tempstr.indexOf("）"));

					}

					Pattern secpattern;

					Matcher secmatcher;

					secpattern = Pattern.compile(tempstr);

					secmatcher = secpattern.matcher(destsbf);

					destsbf = secmatcher.replaceFirst(tempstr.substring(0,
							tempstr.length() - 2)
							+ "...");

				} else

					destsbf = destsbf.replaceFirst(tempstr, tempstr.substring(
							0, tempstr.length() - 2)
							+ "...");

			}

		}

		int ipos = -1;

		pattern = Pattern.compile((String) regxs.get(2));

		matcher = pattern.matcher(sbf);

		while (matcher.find()) {

			tempstr = matcher.group();

			if (tempstr.indexOf("＠") > 0)

				ipos = tempstr.indexOf("＠");

			else

				ipos = tempstr.indexOf("@");

			destsbf = destsbf.replaceFirst(tempstr, "..."
					+ tempstr.substring(ipos));

		}

		return destsbf;

	}

}