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
 * Created on 31.12.2003
 *
 */
package crystal.common.data;

import java.io.PrintStream;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * @author Oleh Hapon
 */

public class SystemUtilities {

    public static void printStackTrace(PrintStream ps) {
	if (ps == null) {
	    throw new IllegalArgumentException("PrintStream is null");
	}
	try {
	    throw new Exception();
	} catch (Exception ex) {
	    ps.println(getStackTrace(ex));
	}
    }

    public static String getStackTrace(Throwable th) {
	if (th == null) {
	    throw new IllegalArgumentException("Throwable is null");
	}

	StringWriter sw = new StringWriter();
	try {
	    PrintWriter pw = new PrintWriter(sw);
	    try {
		th.printStackTrace(pw);
		return sw.toString();
	    } finally {
		pw.close();
	    }
	} finally {
	    try {
		sw.close();
	    } catch (IOException ignore) {
		//
	    }
	}
    }

    public static String changeClassNameToFileName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("Class Name is null");
	}
	return name.replace('.', '/').concat(".class");
    }

    public static String changeFileNameToClassName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("File Name is null");
	}
	String className = null;
	if (name.toLowerCase().endsWith(".class")) {
	    className = name.replace('/', '.');
	    className = className.replace('\\', '.');
	    className = className.substring(0, className.length() - 6);
	}
	return className;
    }

    public static String getFileNameSuffix(String fileName) {
	if (fileName == null) {
	    throw new IllegalArgumentException("File name is null");
	}
	int pos = fileName.lastIndexOf('.');
	if (pos > 0 && pos < fileName.length() - 1) {
	    return fileName.substring(pos + 1);
	}
	return "";
    }

    public static String removeFileNameSuffix(String fileName) {
	if (fileName == null) {
	    throw new IllegalArgumentException("File name is null");
	}
	int pos = fileName.lastIndexOf('.');
	if (pos > 0 && pos < fileName.length() - 1) {
	    return fileName.substring(0, pos);
	}
	return fileName;
    }

    public static String formatSize(long longSize) {
	return formatSize(longSize, -1);
    }

    public static String formatSize(long longSize, int decimalPos) {
	NumberFormat fmt = NumberFormat.getNumberInstance();
	if (decimalPos >= 0) {
	    fmt.setMaximumFractionDigits(decimalPos);
	}
	final double size = longSize;
	double val = size / (1024 * 1024);
	if (val > 1) {
	    return fmt.format(val).concat(" MB");
	}
	val = size / 1024;
	if (val > 10) {
	    return fmt.format(val).concat(" KB");
	}
	return fmt.format(val).concat(" bytes");
    }

    public static boolean equals(Object obj1, Object obj2) {
	if (obj1 == null && obj2 == null) {
	    return true;
	}
	if (obj1 != null) {
	    return obj1.equals(obj2);
	}
	return obj2.equals(obj1);
    }

    ////
    
    /**
     * Fixed year digits (Y2K). Replace 'yy' to 'yyyy'.
     * 
     */
    public static String normalizeYear(String pattern) {
	if (pattern == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	char[] chars = pattern.toCharArray();
	int yearDigits = 0;
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == 'y') {
		yearDigits++;
	    } else {
		if (yearDigits > 0) {
		    buf.append(getYearPattern(buf, yearDigits));
		    yearDigits = 0;
		}

		buf.append(chars[i]);

	    }
	    if (i == chars.length - 1 && yearDigits > 0) {
		buf.append(getYearPattern(buf, yearDigits));
	    }
	}
	return buf.toString();
    }

    /**
     * Normalize day and month. Replace one digit of day or month to two
     * digits. Replace 'M' to 'MM' Replace 'd' to 'dd'
     * 
     * @param parentPattern
     * @return
     */
    public static String normalizeDayAndMonth(String parentPattern) {
	if (parentPattern == null) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	char[] chars = parentPattern.toCharArray();
	int dayDigits = 0;
	int monthDigits = 0;
	for (int i = 0; i < chars.length; i++) {
	    if (chars[i] == 'd') {
		dayDigits++;
	    } else if (chars[i] == 'M') {
		monthDigits++;
	    } else {

		if (dayDigits > 0) {
		    buf.append(getDigitPattern("d", dayDigits));
		    dayDigits = 0;
		} else if (monthDigits > 0) {
		    buf.append(getDigitPattern("M", monthDigits));
		    monthDigits = 0;
		}

		buf.append(chars[i]);

	    }
	    if (i == chars.length - 1) {
		if (dayDigits > 0) {
		    buf.append(getDigitPattern("d", dayDigits));
		} else if (monthDigits > 0) {
		    buf.append(getDigitPattern("M", monthDigits));
		}
	    }
	}
	return buf.toString();
    }

    private static String getDigitPattern(String pattern, int digits) {
	if (digits < 2) {
	    digits = 2;
	}
	return StringUtilities.replicate(pattern, digits);
    }

    private static String getYearPattern(StringBuffer buf, int yearDigits) {
	if (yearDigits == 2) {
	    return "yyyy";
	} else {
	    return StringUtilities.replicate("y", yearDigits);
	}
    }
    
    public static boolean isValidDatePattern(String pattern) {
	return isValidDatePattern(pattern, null);
    }
    
    public static boolean isValidDatePattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateDatePattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }

    public static boolean isValidTimePattern(String pattern) {
	return isValidTimePattern(pattern, null);
    }
    
    public static boolean isValidTimePattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateTimePattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }

    public static boolean isValidNumberPattern(String pattern) {
	return isValidNumberPattern(pattern, null);
    }
    
    public static boolean isValidNumberPattern(String pattern, String charSequence) {
	boolean isValid = isValidPattern(pattern, charSequence);
	if (!isValid) {
	    return isValid;
	}
	isValid = false;
	try {
	    validateNumberPattern(pattern);
	    isValid = true;
	} catch (IllegalArgumentException ex) {
	    
	}
	return isValid;
    }
    
    public static void validateDatePattern(String pattern) {
	new SimpleDateFormat(pattern);
    }
    
    public static void validateTimePattern(String pattern) {
	new SimpleDateFormat(pattern);
    }

    public static void validateNumberPattern(String pattern) {
	new DecimalFormat(pattern);
    }

    public static boolean isValidPattern(String pattern) {
	return isValidPattern(pattern, null);
    }
    
    public static boolean isValidPattern(String pattern, String charSequence) {
	if (pattern == null || pattern.trim().length() == 0) {
	    return false;
	}
	if (charSequence != null && charSequence.trim().length() != 0) {
	    char[] patternArray = pattern.toCharArray();
	    char[] charArray = charSequence.toCharArray();
	    for (int i = 0; i < patternArray.length; i++) {
		boolean b = false;
		for (int j = 0; j < charArray.length; j++) {
		    if (patternArray[i] == charArray[j]) {
			b = true;
			break;
		    }
		}
		if (!b) {
		    return false;
		}
	    }
	    
	}
	return true;
    }
    
    

}
