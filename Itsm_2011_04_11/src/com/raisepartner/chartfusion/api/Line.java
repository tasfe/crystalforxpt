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

public class Line extends com.raisepartner.chartfusion.api.Node {

	public final static String PARAMETER_DASHLEN = "dashLen";
	public final static String PARAMETER_ISTRENDZONE = "isTrendZone";
	public final static String PARAMETER_DASHGAP = "dashGap";
	public final static String PARAMETER_THICKNESS = "thickness";
	public final static String PARAMETER_COLOR = "color";
	public final static String PARAMETER_ENDVALUE = "endValue";
	public final static String PARAMETER_DASHED = "dashed";
	public final static String PARAMETER_STARTVALUE = "startValue";
	public final static String PARAMETER_DISPLAYVALUE = "displayValue";
	public final static String PARAMETER_ALPHA = "alpha";

	public Line() {
		super("line");
	}

	/**
	 * Type: Number Range value: In Pixels Description : If you've opted to show
	 * trend line as dash, this attribute lets you control the length of each
	 * dash.
	 */
	public void setDashLen(String value) {
		setAttribute(PARAMETER_DASHLEN, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the trend would
	 * display a line, or a zone (filled colored rectangle).
	 */
	public void setIsTrendZone(String value) {
		setAttribute(PARAMETER_ISTRENDZONE, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If you've opted to show
	 * trend line as dash, this attribute lets you control the length of each
	 * dash gap.
	 */
	public void setDashGap(String value) {
		setAttribute(PARAMETER_DASHGAP, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If you've opted to show
	 * the trend as a line, this attribute lets you define the thickness of
	 * trend line.
	 */
	public void setThickness(String value) {
		setAttribute(PARAMETER_THICKNESS, value);
	}

	/**
	 * Type: String Range value: Hex Code Description : Color of the trend line
	 * and its associated text.
	 */
	public void setColor(String value) {
		setAttribute(PARAMETER_COLOR, value);
	}

	/**
	 * Type: Number Range value: Numeric Value Description : The ending y-axis
	 * value for the trendline. Say, if you want to plot a slanted trendline
	 * from value 102 to 109, the endValue would be 109. If you do not specify a
	 * value for endValue, it would automatically assume the same value as
	 * startValue.
	 */
	public void setEndValue(String value) {
		setAttribute(PARAMETER_ENDVALUE, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : If you've opted to show the
	 * trend as a line, this attribute lets you define whether the trend line
	 * would appear as dashed?
	 */
	public void setDashed(String value) {
		setAttribute(PARAMETER_DASHED, value);
	}

	/**
	 * Type: Number Range value: Numeric Value Description : The starting value
	 * for the trendline. Say, if you want to plot a slanted trendline from
	 * value 102 to 109, the startValue would be 102.
	 */
	public void setStartValue(String value) {
		setAttribute(PARAMETER_STARTVALUE, value);
	}

	/**
	 * Type: String Range value: Description : If you want to display a string
	 * caption for the trend line by its side, you can use this attribute.
	 * Example: displayValue='Last Month High'. When you don't supply this
	 * attribute, it automatically takes the value of startValue.
	 */
	public void setDisplayValue(String value) {
		setAttribute(PARAMETER_DISPLAYVALUE, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of the trend line.
	 */
	public void setAlpha(String value) {
		setAttribute(PARAMETER_ALPHA, value);
	}
}
