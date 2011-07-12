/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * --------------------
 * SampleXYDataset.java
 * --------------------
 * (C) Copyright 2000-2004, by Object Refinery Limited;
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   -;
 *
 * $Id: SampleXYDataset.java,v 1.1 2005/04/28 16:29:16 harrym_nu Exp $
 *
 * Changes (from 24-Aug-2001)
 * --------------------------
 * 24-Aug-2001 : Added standard source header. Fixed DOS encoding problem (DG);
 * 15-Oct-2001 : Parent class has changed package (DG);
 * 22-Oct-2001 : Renamed DataSource.java --> Dataset.java etc. (DG);
 *               Added translate factor, used for demonstrating dynamic chart (DG);
 * 07-Nov-2001 : Updated source header (DG);
 * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 *
 */

package com.netblizzard.jfreechart;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.AbstractSeriesDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.XYDataset;

/**
 * A dummy dataset for an XY plot.
 * <P>
 * Note that the aim of this class is to create a self-contained data source for demo purposes -
 * it is NOT intended to show how you should go about writing your own datasets.
 *
 * @author David Gilbert
 */
public class SampleXYDataset extends AbstractSeriesDataset implements XYDataset {

    /** Use the translate to change the data and demonstrate dynamic data changes. */
    private double translate;

    /**
     * Default constructor.
     */
    public SampleXYDataset() {
        this.translate = 0.0;
    }

    /**
     * Returns the translation factor.
     *
     * @return  the translation factor.
     */
    public double getTranslate() {
        return this.translate;
    }

    /**
     * Sets the translation constant for the x-axis.
     *
     * @param translate  the translation factor.
     */
    public void setTranslate(double translate) {
        this.translate = translate;
        notifyListeners(new DatasetChangeEvent(this, this));
    }

    /**
     * Returns the x-value for the specified series and item.  Series are numbered 0, 1, ...
     *
     * @param series  the index (zero-based) of the series.
     * @param item  the index (zero-based) of the required item.
     *
     * @return the x-value for the specified series and item.
     */
    public double getXValue(int series, int item) {
        return new Double(-10.0 + this.translate + (item / 10.0));
    }

    /**
     * Returns the y-value for the specified series and item.  Series are numbered 0, 1, ...
     *
     * @param series  the index (zero-based) of the series.
     * @param item  the index (zero-based) of the required item.
     *
     * @return the y-value for the specified series and item.
     */
    public double getYValue(int series, int item) {
        if (series == 0) {
            return new Double(Math.cos(-10.0 + this.translate + (item / 10.0)));
        }
        else {
            return new Double(2 * (Math.sin(-10.0 + this.translate + (item / 10.0))));
        }
    }

    /**
     * Returns the number of series in the dataset.
     *
     * @return the number of series in the dataset.
     */
    public int getSeriesCount() {
        return 2;
    }

    /**
     * Returns the name of the series.
     *
     * @param series  the index (zero-based) of the series.
     *
     * @return the name of the series.
     */
    public String getSeriesName(int series) {
        if (series == 0) {
            return "y = cosine(x)";
        }
        else if (series == 1) {
            return "y = 2*sine(x)";
        }
        else {
            return "Error";
        }
    }

    /**
     * Returns the number of items in the specified series.
     *
     * @param series  the index (zero-based) of the series.
     * @return the number of items in the specified series.
     *
     */
    public int getItemCount(int series) {
        return 200;
    }

	@Override
	public Comparable getSeriesKey(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainOrder getDomainOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getX(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getY(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}







