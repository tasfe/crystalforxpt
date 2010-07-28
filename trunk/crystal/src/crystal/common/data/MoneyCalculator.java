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
 * Created on 30.12.2004
 *
 */

/**
 * @author Oleh Hapon
 * $Id: MoneyCalculator.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */
package crystal.common.data;

public class MoneyCalculator {
    private static MoneyCalculator ourInstance;

    public synchronized static MoneyCalculator getInstance() {
	if (ourInstance == null) {
	    ourInstance = new MoneyCalculator();
	}
	return ourInstance;
    }

    private MoneyCalculator() {
    }

    public static double calculatePrice(double quantity, double amount) {
	if (quantity == 0.0d) {
	    return 0.0d;
	}
	return MoneyEnvironment.roundPrice(amount / quantity);
    }

    public static double calculateAmount(double quantity, double price) {
	return MoneyEnvironment.roundMoney(quantity * price);
    }

    public static double calculateTaxAmount(double percent, double amount) {
	return calculateTaxAmount(percent, amount, false);
    }

    public static double calculateTaxAmount(double percent, double amount,
	    boolean isIncludeTaxInAmount) {
	if (percent < 0) {
	    throw new IllegalArgumentException("Percent must be positive value");
	}
	if (isIncludeTaxInAmount) {
	    return MoneyEnvironment.roundMoney(percent * amount
		    / (100 + percent));
	} else {
	    return MoneyEnvironment.roundMoney(percent * amount / 100);
	}

    }

}
