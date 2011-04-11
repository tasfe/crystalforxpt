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

import com.raisepartner.chartfusion.api.styles.AnimationStyle;
import com.raisepartner.chartfusion.api.styles.BevelStyle;
import com.raisepartner.chartfusion.api.styles.BlurStyle;
import com.raisepartner.chartfusion.api.styles.FontStyle;
import com.raisepartner.chartfusion.api.styles.GlowStyle;
import com.raisepartner.chartfusion.api.styles.ShadowStyle;
import com.raisepartner.chartfusion.api.styles.Styles;

public class StyledChart extends Node {

	private Styles styles;

	public StyledChart(String name) {
		super(name);
	}

	public Styles createStyles() {
		if (styles == null) {
			styles = new Styles();
			addNode(styles);
		}
		return styles;
	}

	public Styles getStyles() {
		return styles;
	}

	/**
	 * Defines a style of animation
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public AnimationStyle defineAnimationStyle(String styleName) {
		return styles.getDefintions().defineAnimationStyle(styleName);
	}

	/**
	 * Defines a style of shadow
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public ShadowStyle defineShadowStyle(String styleName) {
		return styles.getDefintions().defineShadowStyle(styleName);
	}

	/**
	 * Defines a style of glow
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public GlowStyle defineGlowStyle(String styleName) {
		return styles.getDefintions().defineGlowStyle(styleName);
	}

	/**
	 * Defines a style of bevel
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public BevelStyle defineBevelStyle(String styleName) {
		return styles.getDefintions().defineBevelStyle(styleName);
	}

	/**
	 * Defines a style of blur
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public BlurStyle defineBlurStyle(String styleName) {
		return styles.getDefintions().defineBlurStyle(styleName);
	}

	/**
	 * Defines a style of font
	 * 
	 * @param styleName
	 *            is the name of the style to create
	 * @return a new definition of a style
	 */
	public FontStyle defineFontStyle(String styleName) {
		return styles.getDefintions().defineFontStyle(styleName);
	}

	/**
	 * Applies styles to an object
	 * 
	 * @param toObject
	 *            is the type of the object
	 * @param styles
	 *            is a comma list of style names
	 */
	public void applyStylesToObject(String toObject, String styles) {
		this.styles.getApplications().applyStyles(toObject, styles);
	}

}
