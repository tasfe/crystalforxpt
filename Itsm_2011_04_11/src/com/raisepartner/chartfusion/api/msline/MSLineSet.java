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

public class MSLineSet extends com.raisepartner.chartfusion.api.Set {

	public final static String PARAMETER_VALUE = "value";
	public final static String PARAMETER_COLOR = "color";
	public final static String PARAMETER_SHOWVALUE = "showValue";
	public final static String PARAMETER_DASHED = "dashed";
	public final static String PARAMETER_ALPHA = "alpha";
	public final static String PARAMETER_ANCHORSIDES = "anchorSides";
	public final static String PARAMETER_ANCHORRADIUS = "anchorRadius";
	public final static String PARAMETER_ANCHORBORDERCOLOR = "anchorBorderColor";
	public final static String PARAMETER_ANCHORBORDERTHICKNESS = "anchorBorderThickness";
	public final static String PARAMETER_ANCHORBGCOLOR = "anchorBgColor";
	public final static String PARAMETER_ANCHORALPHA = "anchorAlpha";
	public final static String PARAMETER_ANCHORBGALPHA = "anchorBgAlpha";

	public MSLineSet() {
	}

	/**
	 * Type: Number Range value: Description : Numerical value for the data
	 * item. This value would be plotted on the chart.
	 */
	public void setValue(String value) {
		setAttribute(PARAMETER_VALUE, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : For multi-series and
	 * combination charts, you can define the color of data-sets at dataset
	 * level. However, if you wish to highlight a particular data element, you
	 * can specify it's color at "set" level using this attribute. This
	 * attribute accepts hex color codes without # .
	 */
	public void setColor(String value) {
		setAttribute(PARAMETER_COLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : You can individually opt to
	 * show/hide values of individual data items using this attribute. This
	 * value over-rides the data-set level value.
	 */
	public void setShowValue(String value) {
		setAttribute(PARAMETER_SHOWVALUE, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the border of this
	 * data item should appear as dashed? This is particularly useful when you
	 * want to highlight a data (such as forecast or trend etc.). This value
	 * over-rides the data-set level value.
	 */
	public void setDashed(String value) {
		setAttribute(PARAMETER_DASHED, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : For multi-series and
	 * combination charts, you can define the alpha of data-sets at dataset
	 * level. However, if you wish to highlight a particular data element, you
	 * can specify it's alpha at "set" level using this attribute.
	 */
	public void setAlpha(String value) {
		setAttribute(PARAMETER_ALPHA, value);
	}

	/**
	 * Type: Number Range value: 43891.0 Description : Lets you specify "set"
	 * specific sides of the anchor.
	 */
	public void setAnchorSides(String value) {
		setAttribute(PARAMETER_ANCHORSIDES, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Lets you specify "set"
	 * specific radius (in pixels) of the anchor.
	 */
	public void setAnchorRadius(String value) {
		setAttribute(PARAMETER_ANCHORRADIUS, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : Lets you specify "set"
	 * specific border color of the anchor.
	 */
	public void setAnchorBorderColor(String value) {
		setAttribute(PARAMETER_ANCHORBORDERCOLOR, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Lets you specify "set"
	 * specific border thickness of the anchor.
	 */
	public void setAnchorBorderThickness(String value) {
		setAttribute(PARAMETER_ANCHORBORDERTHICKNESS, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : Lets you specify "set"
	 * specific background color of the anchor.
	 */
	public void setAnchorBgColor(String value) {
		setAttribute(PARAMETER_ANCHORBGCOLOR, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Lets you specify "set"
	 * specific alpha of the anchor.
	 */
	public void setAnchorAlpha(String value) {
		setAttribute(PARAMETER_ANCHORALPHA, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Lets you specify "set"
	 * specific alpha of the anchor background.
	 */
	public void setAnchorBgAlpha(String value) {
		setAttribute(PARAMETER_ANCHORBGALPHA, value);
	}
}
