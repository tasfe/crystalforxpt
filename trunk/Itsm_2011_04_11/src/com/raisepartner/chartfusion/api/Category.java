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

public class Category extends com.raisepartner.chartfusion.api.Node {

	public final static String PARAMETER_LABEL = "label";
	public final static String PARAMETER_TOOLTEXT = "toolText";
	public final static String PARAMETER_SHOWLABEL = "showLabel";

	public Category() {
		super("category");
	}

	/**
	 * Type: String Range value: Description : This attribute determines the
	 * label for the data item. The label appears on the x-axis of chart.
	 */
	public void setLabel(String value) {
		setAttribute(PARAMETER_LABEL, value);
	}

	/**
	 * Type: String Range value: Description : The label of each category shows
	 * up on the x-axis. However, there might be cases where you want to display
	 * short label (or abbreviated label) on the axis and show the full label as
	 * tool tip. This attribute lets you specify the tool tip text for the
	 * label.
	 */
	public void setToolText(String value) {
		setAttribute(PARAMETER_TOOLTEXT, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : You can individually opt to
	 * show/hide labels of individual data items using this attribute.
	 */
	public void setShowLabel(String value) {
		setAttribute(PARAMETER_SHOWLABEL, value);
	}
}
