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

public class VLine extends com.raisepartner.chartfusion.api.Node {

	public final static String PARAMETER_DASHLEN = "dashLen";
	public final static String PARAMETER_DASHGAP = "dashGap";
	public final static String PARAMETER_THICKNESS = "thickness";
	public final static String PARAMETER_DASHED = "dashed";
	public final static String PARAMETER_ALPHA = "alpha";

	public VLine() {
		super("vLine");
	}

	/**
	 * Type: Number Range value: In Pixels Description : If the vertical
	 * separator line is to appear as dashed, this attribute defines the length
	 * of dash.
	 */
	public void setDashLen(String value) {
		setAttribute(PARAMETER_DASHLEN, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If the vertical
	 * separator line is to appear as dashed, this attribute defines the length
	 * of dash gap.
	 */
	public void setDashGap(String value) {
		setAttribute(PARAMETER_DASHGAP, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Thickness in pixels of
	 * the vertical separator line.
	 */
	public void setThickness(String value) {
		setAttribute(PARAMETER_THICKNESS, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the vertical
	 * separator line should appear as dashed?
	 */
	public void setDashed(String value) {
		setAttribute(PARAMETER_DASHED, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of the vertical
	 * separator line.
	 */
	public void setAlpha(String value) {
		setAttribute(PARAMETER_ALPHA, value);
	}
}
