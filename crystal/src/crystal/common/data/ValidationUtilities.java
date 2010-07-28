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

package crystal.common.data;

public class ValidationUtilities {

    public static boolean isValidRequired(String str) {
	return !StringUtilities.isEmpty(str);
    }

    public static boolean isValidRequired(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (obj instanceof String) {
	    return isValidRequired((String) obj);
	}
	
	if (obj instanceof Number) {
	    return isValidRequired((Number) obj);
	}

	return obj != null;
    }

    public static boolean isValidRequired(int value) {
	return value != 0;
    }

    public static boolean isValidRequired(float value) {
	return value != 0.0f;
    }

    public static boolean isValidRequired(double value) {
	return value != 0.0d;
    }

    public static boolean isValidRequired(Number number) {
	if (number == null) {
	    return false;
	}
	return isValidRequired(number.doubleValue());
    }

}
