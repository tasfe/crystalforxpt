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

import com.raisepartner.chartfusion.api.Node;

public final class StyleDefinitions extends Node {

	public StyleDefinitions() {
		super("definition");
	}

	public AnimationStyle defineAnimationStyle(String styleName) {
		AnimationStyle s = new AnimationStyle(styleName);
		addNode(s);
		return s;
	}

	public ShadowStyle defineShadowStyle(String styleName) {
		ShadowStyle s = new ShadowStyle(styleName);
		addNode(s);
		return s;
	}

	public GlowStyle defineGlowStyle(String styleName) {
		GlowStyle s = new GlowStyle(styleName);
		addNode(s);
		return s;
	}

	public BevelStyle defineBevelStyle(String styleName) {
		BevelStyle s = new BevelStyle(styleName);
		addNode(s);
		return s;
	}

	public BlurStyle defineBlurStyle(String styleName) {
		BlurStyle s = new BlurStyle(styleName);
		addNode(s);
		return s;
	}

	public FontStyle defineFontStyle(String styleName) {
		FontStyle s = new FontStyle(styleName);
		addNode(s);
		return s;
	}
}
