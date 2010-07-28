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
 * Created on 24.07.2004
 *
 */
package crystal.common.data;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * @author Oleh Hapon $Id: MoneyUtilities.java,v 1.1 2009/07/13 09:08:10 cvs Exp $
 */
public class MoneyUtilities implements MoneyConstants {

    // TODO: You must use Locale
    private static final String DEFAULT_CURRENCY = USD;

    private static final String DEFAULT_LANGUAGE = EN;

    private static MoneyEnv moneyEnv;

    // TODO: You must use Locale
    private static String defaultCurrency;

    private static String defaultLanguage;

    static {

	defaultCurrency = DEFAULT_CURRENCY;
	defaultLanguage = DEFAULT_LANGUAGE;

	moneyEnv = new MoneyEnv();

    }

    public static String writeSum(Number sum, String currency, String language) {
	if (sum == null)
	    return "";
	return writeSum(sum.doubleValue(), currency, language);
    }

    public static String writeSum(Number sum) {
	return writeSum(sum, null, null);
    }

    public static String writeSum(double sum, String currency, String language) {
	moneyEnv.init(currency, language);
	return writeSum(sum, true, false);
    }

    public static String writeSum(double sum) {
	return writeSum(sum, null, null);
    }

    private static int vseg[] = { 1000000000, 1000000, 1000, 1 };

    private static int howTell(long n) {
	n %= 100;
	if (n >= 10 && n <= 20)
	    return 3;
	n %= 10;
	return n == 1 ? 1 : n <= 4 && n > 0 ? 2 : 3;
    }

    /*
         * static double Round (double op) { return Math.floor(op + 0.5); }
         * 
         * static double Round (double op, double pow) { if (pow <= 0.) return
         * op; if (op>0. && op<pow || op<0. && op>pow) return 0.; op =
         * Math.floor(op/pow + 0.5) * pow; return op>0. && op<pow || op<0. &&
         * op>pow? 0.: op; }
         */

    private static String writeSum(double s, boolean kop, boolean up) {

	// if (s >= 1e12 || s <= 0.009) return "";
	if (s >= 1e12)
	    return "";
	StringBuffer buf = new StringBuffer();
	int v = (int) s;
	int k = (int) Math.round((s - v) * 100);
	for (int seg = 0; seg < 4; seg++) {
	    int vt = v / vseg[seg];
	    v -= vseg[seg] * vt;
	    if (vt > 0 || seg == 3) {
		int how = howTell(vt);
		if (vt > 0) {

		    buf.append(moneyEnv.getDsot()[vt / 100]
			    + getBlank(vt / 100));
		    vt %= 100;
		    if (vt >= 20 || vt <= 10) {
			buf.append(moneyEnv.getDdec()[vt / 10]
				+ getBlank(vt / 10));
			vt %= 10;
			if (seg == 2 && (vt == 1 || vt == 2))
			    buf.append(moneyEnv.getDedt()[vt - 1] + getBlank());
			else
			    buf.append(moneyEnv.getDed()[vt] + getBlank(vt));
		    } else
			buf.append(moneyEnv.getDde()[vt - 11] + getBlank());
		} else if (buf.length() == 0) {
		    buf.append(moneyEnv.getZero() + getBlank());
		}
		buf.append(moneyEnv.getDseg()[seg]);
		buf.append(moneyEnv.getDsem()[seg][how - 1] + getBlank());
	    }
	}

	if (kop) {
	    buf.append(((k < 10) ? "0" : "") + k + " " + moneyEnv.getDseg()[4]
		    + moneyEnv.getDsem()[4][howTell(k) - 1] + getBlank());
	}

	String result = buf.toString();
	if (up) {
	    return result.toUpperCase();
	}
	if (result.length() < 2) {
	    return result;
	}
	return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    private static String getBlank() {
	return " ";
    }

    private static String getBlank(int i) {
	return i < 1 ? "" : " ";
    }

    private static class MoneyEnv {

	public static final String DSOT = "dsot";

	public static final String DDEC = "ddec";

	public static final String DDE = "dde";

	public static final String DED = "ded";

	public static final String DEDT = "dedt";

	public static final String DSEG = "dseg";

	public static final String DSEM = "dsem";

	public static final String ZERO = "zero";

	public static final String BLANK = "BLANK";

	private String[] dsot;

	private String[] ddec;

	private String[] dde;

	private String[] ded;

	private String[] dedt;

	private String[] dseg;

	private String[][] dsem;

	private String zero;

	public void init(String currency, String language) {
	    if (currency == null) {
		currency = getDefaultCurrency();
	    }
	    if (language == null) {
		language = getDefaultLanguage();
	    }

	    Locale locale = new Locale(language, "", "");
	    ResourceBundle res = ResourceBundle.getBundle(
		    "org.plazmaforge.framework.resources." + currency, locale);

	    dsot = new String[10];
	    ddec = new String[10];
	    dde = new String[10];
	    ded = new String[10];
	    dedt = new String[2];
	    dseg = new String[5];
	    dsem = new String[5][3];

	    populate(res.getString(DSOT), dsot, 10);
	    populate(res.getString(DDEC), ddec, 10);
	    populate(res.getString(DDE), dde, 10);
	    populate(res.getString(DED), ded, 10);

	    populate(res.getString(DEDT), dedt, 2);
	    populate(res.getString(DSEG), dseg, 5);

	    String[] arr = null;
	    for (int i = 0; i < 5; i++) {
		arr = new String[3];
		populate(res.getString(DSEM + (i + 1)), arr, 3);
		dsem[i][0] = arr[0];
		dsem[i][1] = arr[1];
		dsem[i][2] = arr[2];
	    }

	    zero = res.getString(ZERO);
	}

	private void populate(String value, String[] arr, int len) {
	    StringTokenizer t = new StringTokenizer(value, ",");
	    int i = 0;
	    String e = null;
	    while (t.hasMoreTokens() && i < len) {
		e = t.nextToken();
		if (BLANK.equals(e.trim())) {
		    arr[i] = "";
		} else {
		    arr[i] = e;
		}
		i++;
	    }
	}

	public String[] getDsot() {
	    return dsot;
	}

	public void setDsot(String[] dsot) {
	    this.dsot = dsot;
	}

	public String[] getDdec() {
	    return ddec;
	}

	public void setDdec(String[] ddec) {
	    this.ddec = ddec;
	}

	public String[] getDde() {
	    return dde;
	}

	public void setDde(String[] dde) {
	    this.dde = dde;
	}

	public String[] getDed() {
	    return ded;
	}

	public void setDed(String[] ded) {
	    this.ded = ded;
	}

	public String[] getDedt() {
	    return dedt;
	}

	public void setDedt(String[] dedt) {
	    this.dedt = dedt;
	}

	public String[] getDseg() {
	    return dseg;
	}

	public void setDseg(String[] dseg) {
	    this.dseg = dseg;
	}

	public String[][] getDsem() {
	    return dsem;
	}

	public void setDsem(String[][] dsem) {
	    this.dsem = dsem;
	}

	public String getZero() {
	    return zero;
	}

	public void setZero(String zero) {
	    this.zero = zero;
	}
    }

    public static String getDefaultCurrency() {
	return defaultCurrency;
    }

    public static void setDefaultCurrency(String defaultCurrency) {
	MoneyUtilities.defaultCurrency = defaultCurrency;
    }

    public static String getDefaultLanguage() {
	return defaultLanguage;
    }

    public static void setDefaultLanguage(String defaultLanguage) {
	MoneyUtilities.defaultLanguage = defaultLanguage;
    }

}
