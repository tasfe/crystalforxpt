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
 * Created on 22.12.2004
 *
 */

/**
 * @author Oleh Hapon
 * $Id: MoneyEnvironment.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */
package crystal.common.data;

import java.util.Currency;
import java.util.Locale;

public class MoneyEnvironment {

    private static MoneyEnvironment ourInstance;

    private static int defaultFractionsDigits;

    private static int priceFractionsDigits;

    public synchronized static MoneyEnvironment getInstance() {
	if (ourInstance == null) {
	    ourInstance = new MoneyEnvironment();
	}
	return ourInstance;
    }

    private MoneyEnvironment() {

    }

    static {
	defaultFractionsDigits = Currency.getInstance(Locale.getDefault())
		.getDefaultFractionDigits();
	priceFractionsDigits = defaultFractionsDigits;
    }

    public static int getDefaultFractionsDigits() {
	return defaultFractionsDigits;
    }

    public static void setDefaultFractionsDigits(int defaultFractionsDigits) {
	MoneyEnvironment.defaultFractionsDigits = defaultFractionsDigits;
    }

    public static int getPriceFractionsDigits() {
	return priceFractionsDigits;
    }

    public static void setPriceFractionsDigits(int priceFractionsDigits) {
	MoneyEnvironment.priceFractionsDigits = priceFractionsDigits;
    }

    public static double roundMoney(double money, int digits) {
	return NumberUtilities.round(money, digits);
    }

    public static double roundMoney(double money) {
	return roundMoney(money, getDefaultFractionsDigits());
    }

    public static double roundPrice(double price) {
	return roundMoney(price, getPriceFractionsDigits());
    }

}
