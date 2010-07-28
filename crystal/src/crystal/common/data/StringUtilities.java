/*
 * Copyright (C) 2005-2007 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

/*
 * Created on 13.11.2003
 *
 */
package crystal.common.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 * @author Oleh Hapon
 */

public class StringUtilities {

    private static int DEFAULT_COL = 100;

    private static int DEFAULT_ROW = 0;

    private static int DEFAULT_ERROR_ROW = 10;

    public static String textFormat(Object obj, int col, int row) {
	if (obj == null)
	    return "";
	String st = obj.toString().trim();
	StringTokenizer t = new StringTokenizer(st, " \t\r\f");
	StringBuffer buf = new StringBuffer();
	int len = 0;
	int rowNumber = 0;
	int tokenNumber = 0;
	while (t.hasMoreTokens()) {
	    String token = t.nextToken();
	    if (len + token.length() > col) {
		buf.append('\n');
		rowNumber++;
		len = 0;
	    } else {
		if (tokenNumber > 0)
		    buf.append(' ');
		len += token.length();
	    }

	    if (row > 0 && rowNumber > row) {
		buf.append(token + "...");
		break;
	    } else {
		buf.append(token);
	    }
	    tokenNumber++;
	}
	return buf.toString();
    }

    public static String textFormat(Object obj, int col) {
	return textFormat(obj, col, DEFAULT_ROW);
    }

    public static String textFormat(Object obj) {
	return textFormat(obj, DEFAULT_COL);
    }

    public static String textErrorFormat(Object obj) {
	if (obj instanceof StackTraceElement[]) {
	    StringBuffer buf = new StringBuffer();

	    StackTraceElement[] trace = (StackTraceElement[]) obj;
	    for (int i = 0; i < trace.length; i++) {
		buf.append("\tat " + trace[i]);
	    }
	    obj = buf.toString();
	}
	return textFormat(obj, DEFAULT_COL, DEFAULT_ERROR_ROW);
    }

    /**
     * Return n combination string s
     * 
     * @param s
     * @param n
     * @return result
     */
    public static String replicate(String s, int n) {
	if (isEmpty(s) || n < 1)
	    return "";
	StringBuffer buff = new StringBuffer();
	for (int i = 0; i < n; i++) {
	    buff.append(s);
	}
	return buff.toString();
    }

    public static String replaceAll(String str, String s1, String s2) {
	if (str == null) {
	    return null;
	}
	if (s1 == null || s2 == null) {
	    return str;
	}
	StringBuffer sbuf = new StringBuffer();
	int pos = 0;
	int index = str.indexOf(s1);
	int patLen = s1.length();
	while (index >= 0) {
	    sbuf.append(str.substring(pos, index));
	    sbuf.append(s2);
	    pos = index + patLen;
	    index = str.indexOf(s1, pos);
	}
	sbuf.append(str.substring(pos));
	return sbuf.toString();
    }

    public static String replaceAll(String str, String s) {
	return replaceAll(str, s, "");
    }

    public static boolean equals(String s1, String s2) {
	if (s1 == null && s2 == null) {
	    return true;
	}
	if (s1 != null) {
	    return s1.equals(s2);
	}
	return s2.equals(s1);
    }

    public static String cleanString(String str) {
	StringBuffer buf = new StringBuffer(str.length());
	char lastCh = ' ';

	for (int i = 0, limit = str.length(); i < limit; ++i) {
	    char ch = str.charAt(i);

	    if (Character.isWhitespace(ch)) {
		if (!Character.isWhitespace(lastCh)) {
		    buf.append(' ');
		}
	    } else {
		buf.append(ch);
	    }
	    lastCh = ch;
	}

	return buf.toString();
    }

    // public static String lpad(String str, int len, String pad) {
    // return "";
    // }
    //
    //  
    // public static String lpad(String str, int len, char pad) {
    // char[] ch = new char[1];
    // ch[0] = pad;
    // return lpad(str, len, new String(ch));
    // }
    //
    //
    //   
    // public static String lpad(String str, int len) {
    // return lpad(str, len, " ");
    // }

    public static String rpad(String str, int len, String pad) {
	if (str == null)
	    return null;
	if (len <= str.length())
	    return str;
	int ln = len - str.length();
	ln = (int) Math.floor(ln / pad.length());
	String s = str + replicate(pad, ln);
	return s.substring(0, len);
    }

    public static String rpad(String str, int len, char pad) {
	char[] ch = new char[1];
	ch[0] = pad;
	return rpad(str, len, new String(ch));
    }

    public static String rpad(String str, int len) {
	return rpad(str, len, " ");
    }

    // public static String convert(String str, String set1, String set2) {
    // return "";
    // }
    //
    // public static String translate(String str, String set1, String set2)
        // {
    // return "";
    // }

    public static String trimLine(String text) {
	if (text == null)
	    return null;
	StringBuffer buf = new StringBuffer();
	try {
	    BufferedReader r = new BufferedReader(new StringReader(text.trim()));
	    int c = 0;
	    String s = r.readLine();
	    while (s != null) {
		if (c++ > 0)
		    buf.append('\n');
		buf.append(s.trim());
		s = r.readLine();
	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	return buf.toString();
    }

    public static String getTrimStringEmpty(String str) {
	return getStringEmpty(str).trim();
    }

    public static String getStringEmpty(String str) {
	if (str == null)
	    return "";
	return str;
    }

    public static String getStringNull(String str) {
	if (isEmpty(str))
	    return null;
	return str;
    }

    /**
     * Returns <code>true</code> if str is empty
     * 
     * @param str
     * @return result
     */
    public static boolean isEmpty(String str) {
	return str == null || str.trim().equals("");
    }

    /**
     * Returns <code>true</code> if all elements is empty
     * 
     * @param str
     * @return result
     */
    public static boolean isEmpty(String[] str) {
	if (str == null || str.length == 0) {
	    return true;
	}
	for (int i = 0; i < str.length; i++) {
	    if (!isEmpty(str[i]))
		return false;
	}
	return true;
    }

    public static String getKeyString(String str) {
	return getKeyString(str, false);
    }

    public static String getKeyString(String str, boolean isCheck) {
	if (isCheck && isEmpty(str)) {
	    throw new IllegalArgumentException("String is empty");
	}
	if (str == null) {
	    return null;
	}
	// return str.replaceAll(" ", "").replaceAll("_", "").toUpperCase();
	return str.replaceAll(" ", "_").toUpperCase();
    }

    /*
    public static String getCodeString(String str) {
	return getCodeString(str, true);
    }

    public static String getCodeString(String str, boolean isCheck) {
	if (isCheck && isEmpty(str)) {
	    throw new IllegalArgumentException("String is empty");
	}
	if (str == null) {
	    return null;
	}
	String s = str.trim();

	char[] ca = s.toCharArray();
	StringBuffer buf = new StringBuffer();
	boolean isStart = true;
	for (int i = 0; i < ca.length; i++) {

	    if (ca[i] == '_') {
		isStart = true;
		continue;
	    }

	    if (isStart) {
		// TODO: Bad code
		buf.append((new Character(ca[i])).toString().toUpperCase());
	    } else {
		// TODO: Bad code
		buf.append((new Character(ca[i])).toString().toLowerCase());
	    }

	    isStart = false;
	}

	return buf.toString();
    }
    */
    
    public static String xmlEncode(String text) {
	int length = text.length();
	if (text != null && length > 0) {
	    StringBuffer ret = new StringBuffer(length * 12 / 10);

	    int last = 0;
	    for (int i = 0; i < length; i++) {
		char c = text.charAt(i);
		switch (c) {
		//case ' ' : ret.append("&nbsp;"); break;
		case '&':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&amp;");
		    break;
		case '>':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&gt;");
		    break;
		case '<':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&lt;");
		    break;
		case '\"':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&quot;");
		    break;
		case '\'':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&apos;");
		    break;

		default:
		    break;
		}
	    }

	    if (last < length) {
		ret.append(text.substring(last));
	    }

	    return ret.toString();
	}

	return text;
    }

    public static String htmlEncode(String text) {
	int length = text.length();
	if (text != null && length > 0) {
	    StringBuffer ret = new StringBuffer(length * 12 / 10);

	    boolean isEncodeSpace = true;
	    int last = 0;
	    for (int i = 0; i < length; i++) {
		char c = text.charAt(i);
		switch (c) {
		case ' ':
		    if (isEncodeSpace) {
			if (last < i) {
			    ret.append(text.substring(last, i));
			}
			last = i + 1;

			ret.append("&nbsp;");
			isEncodeSpace = false;
		    } else {
			isEncodeSpace = true;
		    }
		    break;
		case '&':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&amp;");
		    isEncodeSpace = false;
		    break;
		case '>':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&gt;");
		    isEncodeSpace = false;
		    break;
		case '<':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&lt;");
		    isEncodeSpace = false;
		    break;
		case '\"':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("&quot;");
		    isEncodeSpace = false;
		    break;
		// it does not work in IE
		//					case '\'' :
		//						if (last < i)
		//						{
		//							ret.append(text.substring(last, i));
		//						}
		//						last = i + 1;
		//						
		//						ret.append("&apos;");
		//						isEncodeSpace = false;
		//						break;
		case '\n':
		    if (last < i) {
			ret.append(text.substring(last, i));
		    }
		    last = i + 1;

		    ret.append("<br/>");
		    isEncodeSpace = false;
		    break;

		default:
		    isEncodeSpace = false;
		    break;
		}
	    }

	    if (last < length) {
		ret.append(text.substring(last));
	    }

	    return ret.toString();
	}

	return text;
    }

    /**
     * Return string to upper case
     * @param str
     * @return
     */
    public static String getUpperCaseString(String str) {
	if (str == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	int length = str.length();
	for (int i = 0; i < length; i++) {
	    char ch = str.charAt(i);
	    if (Character.isUpperCase(ch)) {
		buf.append(ch);
	    } else {
		buf.append(Character.toUpperCase(ch));
	    }
	}
	return buf.toString(); 
    }
    
    
    /**
     * Return string to lower case
     * @param str
     * @return
     */
    public static String getLowerCaseString(String str) {
	if (str == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	int length = str.length();
	for (int i = 0; i < length; i++) {
	    char ch = str.charAt(i);
	    if (Character.isLowerCase(ch)) {
		buf.append(ch);
	    } else {
		buf.append(Character.toLowerCase(ch));
	    }
	}
	return buf.toString(); 
    }

    
    
}
