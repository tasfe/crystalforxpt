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

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created on 01.06.2006
 */

public class ObjectCreator {

    private DateFormat dateFormat;

    public ObjectCreator() {
	// STUB
	dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public static class ObjectValue {

	private boolean forceStringType;

	private Object value;

	public ObjectValue(Object value) {
	    this.value = value;
	}

	public ObjectValue(Object value, boolean forceStringType) {
	    this.value = value;
	    this.forceStringType = forceStringType;
	}

	public boolean isForceStringType() {
	    return forceStringType;
	}

	public void setForceStringType(boolean forceStringType) {
	    this.forceStringType = forceStringType;
	}

	public Object getValue() {
	    return value;
	}

	public void setValue(Object value) {
	    this.value = value;
	}
    }

    public ObjectValue createObjectValueByString(Class klass, String str) {
	if (klass == null || str == null) {
	    return null;
	}

	try {
	    if (klass.equals(String.class)) {
		// String
		return new ObjectValue(str);

	    } else if (klass.equals(Byte.class) || klass.equals(Byte.TYPE)) {
		// Byte
		return new ObjectValue(new Byte(str));

	    } else if (klass.equals(Integer.class) || klass.equals(Integer.TYPE)) {
		// Integer
		return new ObjectValue(new Integer(str));

	    } else if (klass.equals(Float.class) || klass.equals(Float.TYPE)) {
		// Float
		return new ObjectValue(new Float(str));

	    } else if (klass.equals(Double.class) || klass.equals(Double.TYPE)) {
		// Double
		return new ObjectValue(new Double(str));

	    } else if (klass.equals(BigInteger.class)) {
		// BigInteger
		return new ObjectValue(new BigInteger(str));

	    } else if (klass.equals(BigDecimal.class)) {
		// BigDecimal
		return new ObjectValue(new BigDecimal(str));

	    } else if (klass.equals(Date.class)) {
		// Date
		return new ObjectValue(getDateFormat().parse(str));

	    } else if (klass.equals(Boolean.class) || klass.equals(Boolean.TYPE)) {
		// Boolean
		return new ObjectValue(new Boolean(str));

	    } else {

		return new ObjectValue(str, true);
	    }
	} catch (Exception e) {
	    return null;
	    // return new ObjectValue(str, true);
	}

    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    
}
