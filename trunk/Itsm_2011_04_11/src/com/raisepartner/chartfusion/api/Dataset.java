/**
 * Copyright (c) 2008 Raise Partner
 * 22, av. Doyen Louis Weil,
 * 38000 Grenoble, France
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Contact: sebastien@chassande.fr
 */
package com.raisepartner.chartfusion.api;

public class Dataset extends com.raisepartner.chartfusion.api.Node {

	public final static String PARAMETER_SERIESNAME = "seriesName";
	public final static String PARAMETER_SHOWVALUES = "showValues";
	public final static String PARAMETER_COLOR = "color";
	public final static String PARAMETER_INCLUDEINLEGEND = "includeInLegend";
	public final static String PARAMETER_ALPHA = "alpha";

	public Dataset() {
		super("dataset");
	}

	/**
	 * Type: String Range value: Description : Lets you specify the series name
	 * for a particular data-set. For example, if you're plotting a chart to
	 * indicate monthly sales analysis for 2005 and 2006, the seriesName for the
	 * first dataset would be 2005 and that of the second would be 2006. The
	 * seriesName of a data-set is shown in legend.
	 */
	public void setSeriesName(String value) {
		setAttribute(PARAMETER_SERIESNAME, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show the values
	 * for this data-set?
	 */
	public void setShowValues(String value) {
		setAttribute(PARAMETER_SHOWVALUES, value);
	}

	/**
	 * Type: Color Range value: Hex Code (Or Comma separated list) Description :
	 * This attribute sets the color using which columns, lines, area of that
	 * data-set would be drawn. For column chart, you can specify a list of
	 * comma separated hex codes to get a gradient plot.
	 */
	public void setColor(String value) {
		setAttribute(PARAMETER_COLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to include the
	 * seriesName of this data-set in legend? This can be particularly useful
	 * when you're using combination charts and you've used the area/line chart
	 * to plot a trend, and you do not want the seriesName of that trend to
	 * appear in legend.
	 */
	public void setIncludeInLegend(String value) {
		setAttribute(PARAMETER_INCLUDEINLEGEND, value);
	}

	/**
	 * Type: String Range value: 0-100 or Comma Separated List Description :
	 * This attribute sets the alpha (transparency) of the entire data- set.
	 */
	public void setAlpha(String value) {
		setAttribute(PARAMETER_ALPHA, value);
	}
}
