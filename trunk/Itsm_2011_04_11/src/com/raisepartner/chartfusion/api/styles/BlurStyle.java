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

public final class BlurStyle extends Style {

	public final static String PARAMETER_blurX = "blurX";
	public final static String PARAMETER_blurY = "blurY";
	public final static String PARAMETER_quality = "quality";

	public BlurStyle(String styleName) {
		super(styleName, "blur");
	}

	/**
	 * The amount to blur horizontally. Valid values are from 0 to 255. The
	 * default value is 4. Values that are a power of 2 (such as 2, 4, 8, 16 and
	 * 32) are optimized to render quicker than other values
	 */
	public void setBlurX(String value) {
		setAttribute(PARAMETER_blurX, value);
	}

	/**
	 * The amount to blur vertically
	 */
	public void setBlurY(String value) {
		setAttribute(PARAMETER_blurY, value);
	}

	/**
	 * The number of times to apply the filter. The default value is 1, which is
	 * equivalent to low quality. A value of 2 is medium quality, and a value of
	 * 3 is high quality and approximates a Gaussian blur
	 */
	public void setQuality(String value) {
		setAttribute(PARAMETER_quality, value);
	}
}
