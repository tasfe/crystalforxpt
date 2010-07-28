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

/**
 * Number utilities
 * 
 * @author hapon
 * 
 */
public class NumberUtilities {

    private static final int MINUTE_RANK = 2;

    private static NumberUtilities instance;

    private NumberUtilities() {

    }

    public synchronized static NumberUtilities getInstance() {
	if (instance == null) {
	    instance = new NumberUtilities();
	}
	return instance;
    }

    public static double round(double number, int digits) {
	return getInstance().roundNumber(number, digits);
    }

    public static float round(float number, int digits) {
	return (float) getInstance().roundNumber(number, digits);
    }

    /**
     * Round time. Time presentation in numberFormat HH,MM
     * 
     * @param number
     * @param digits
     * @return
     */
    public static double roundTime(double number) {
	return getInstance().roundNumber(number, MINUTE_RANK);
    }

    /**
     * Round time. Time presentation in numberFormat HH,MM
     * 
     * @param number
     * @param digits
     * @return
     */
    public static float roundTime(float number) {
	return (float) getInstance().roundNumber(number, MINUTE_RANK);
    }

    private double roundNumber(double number, int digits) {
	double dec = getDec(digits);
	return Math.round(number * dec) / dec;
    }

    private double getDec(int k) {
	if (k < 1) {
	    return 1d;
	}
	double d = 10;
	for (int i = 1; i < k; i++) {
	    d *= 10;
	}
	return d;
    }

}
