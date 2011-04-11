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
package com.raisepartner.chartfusion.api.msline;

public class MSLineLine extends com.raisepartner.chartfusion.api.Line {

	public final static String PARAMETER_SHOWONTOP = "showOnTop";
	public final static String PARAMETER_VALUEONRIGHT = "valueOnRight";

	public MSLineLine() {
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the trend line/zone
	 * would be displayed over data plots or under them?
	 */
	public void setShowOnTop(String value) {
		setAttribute(PARAMETER_SHOWONTOP, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show the trend
	 * line value on left side or right side of chart? This is particularly
	 * useful when the trend line display values on the chart are colliding with
	 * divisional lines values on the chart.
	 */
	public void setValueOnRight(String value) {
		setAttribute(PARAMETER_VALUEONRIGHT, value);
	}
}
