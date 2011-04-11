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
package com.raisepartner.chartfusion.api.styles;

public final class FontStyle extends Style {

	public final static String PARAMETER_font = "font";
	public final static String PARAMETER_size = "size";
	public final static String PARAMETER_bold = "bold";
	public final static String PARAMETER_isHTML = "isHTML";
	public final static String PARAMETER_face = "face";
	public final static String PARAMETER_bgColor = "bgColor";
	public final static String PARAMETER_italic = "italic";
	public final static String PARAMETER_underline = "underline";
	public final static String PARAMETER_borderColor = "borderColor";

	public final static String PARAMETER_color = "color";
	public final static String PARAMETER_leftMargin = "leftMargin";
	public final static String PARAMETER_letterSpacing = "letterSpacing";

	public FontStyle(String styleName) {
		super(styleName, "font");
	}

	/**
	 * Sets the font family for the text.
	 */
	public void setFont(String value) {
		setAttribute(PARAMETER_font, value);
	}

	/**
	 * Specifies the font size
	 */
	public void setSize(String value) {
		setAttribute(PARAMETER_size, value);
	}

	/**
	 * Flag indicating whether font should be bold or not
	 */
	public void setBold(boolean value) {
		setAttribute(PARAMETER_bold, value ? "1" : "0");
	}

	/**
	 * Helps you set whether the text should be rendered as HTML or plain text.
	 */
	public void setIsHTML(boolean value) {
		setAttribute(PARAMETER_bold, value ? "1" : "0");
	}

	/**
	 * Flag indicating whether font should be italics or not
	 */
	public void setItalic(boolean value) {
		setAttribute(PARAMETER_italic, value ? "1" : "0");
	}

	/**
	 * Flag indicating whether font should be underlined
	 */
	public void setUnderline(boolean value) {
		setAttribute(PARAMETER_underline, value ? "1" : "0");
	}

	/**
	 * If you need a border around your text, you can use this parameter
	 */
	public void setBorderColor(String value) {
		setAttribute(PARAMETER_borderColor, value);
	}

	/**
	 * Sets the font color, should be hex color code without #
	 */
	public void setColor(String value) {
		setAttribute(PARAMETER_color, value);
	}

	/**
	 * The left margin of the text, in points
	 */
	public void setLeftMargin(String value) {
		setAttribute(PARAMETER_leftMargin, value);
	}

	/**
	 * The amount of space that is uniformly distributed between characters of
	 * the text
	 */
	public void setLetterSpacing(String value) {
		setAttribute(PARAMETER_letterSpacing, value);
	}

	/**
	 * Sets the background color for a text box, should be hex color code
	 * without #
	 */
	public void setBackgroundColor(String value) {
		setAttribute(PARAMETER_bgColor, value);
	}
}
