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
 * Created on 09.03.2007
 *
 */

package crystal.common.data;

/**
 * @author Oleh Hapon
 * 
 * $Id: BooleanUtilities.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */

public class BooleanUtilities {

    public static boolean parse(String str) {
	return parse(str, false);
    }

    public static boolean parse(String str, boolean trim) {
	if (str == null) {
	    return false;
	}
	if (trim) {
	    str = str.trim();
	}
	if ((str.equalsIgnoreCase("y") || str.equalsIgnoreCase("yes")
		|| str.equalsIgnoreCase("true") || str.equalsIgnoreCase("1"))) {
	    return true;
	}
	return false;
    }
    
    public static String getBooleanString(boolean value, boolean isNullIfFalse) {
	if (isNullIfFalse && !value) {
	    return null;
	}
	return value ? "true" : "false";
    }
    
    public static String getBooleanString(boolean value) {
	return getBooleanString(value, false);
    }
    
    public static String getBooleanStringNull(boolean value) {
	return getBooleanString(value, true);
    }

}
