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

public class Set extends com.raisepartner.chartfusion.api.Node {

	public final static String PARAMETER_TOOLTEXT = "toolText";
	public final static String PARAMETER_LINK = "link";

	public Set() {
		super("set");
	}

	/**
	 * Type: String Range value: Description : By default, FusionCharts shows
	 * the data item name and value as tool tip text for that data item. But, if
	 * you want to display more information for the data item as tool tip, you
	 * can use this attribute to specify the same.
	 */
	public void setToolText(String value) {
		setAttribute(PARAMETER_TOOLTEXT, value);
	}

	/**
	 * Type: String Range value: Description : You can define links for
	 * individual data items. That enables the end user to click on data items
	 * (columns, lines, bars etc.) and drill down to other pages. To define the
	 * link for data items, use the link attribute. You can define links that
	 * open in same window, new window, pop-up window or frames. Please see
	 * "Advanced Charting > Drill Down Charts" for more information. Also,
	 * you'll need to URL Encode all the special characters (like ? and &)
	 * present in the link.
	 */
	public void setLink(String value) {
		setAttribute(PARAMETER_LINK, value);
	}
}
