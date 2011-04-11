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

public final class AnimationStyle extends Style {

	public final static String PARAMETER_param = "param";
	public final static String PARAMETER_start = "start";
	public final static String PARAMETER_duration = "duration";
	public final static String PARAMETER_easing = "easing";

	public AnimationStyle(String styleName) {
		super(styleName, "anaimation");
	}

	/**
	 * This attribute lets you specify the property of chart object which you
	 * want to animate e.g., _x, _y, _xscale etc
	 */
	private void setParam(String v) {
		setAttribute(PARAMETER_param, v);
	}

	public void setParamToX() {
		setParam("_x");
	}

	public void setParamToY() {
		setParam("_y");
	}

	public void setParamToXScale() {
		setParam("_xScale");
	}

	public void setParamToYScale() {
		setParam("_yScale");
	}

	public void setParamToAlpha() {
		setParam("_alpha");
	}

	public void setParamToRotation() {
		setParam("_rotation");
	}

	/**
	 * The start value of animation e.g., if you're animating the data plot for
	 * fade in alpha effect, the start value of alpha would be 0
	 */
	public void setStart(String v) {
		setAttribute(PARAMETER_start, v);
	}

	/**
	 * Using this, you can control the duration of animation in seconds
	 */
	public void setDuration(String v) {
		setAttribute(PARAMETER_duration, v);
	}

	/**
	 * This attribute lets you specify the pattern of animation easing. Valid
	 * values are "elastic", "bounce", "regular", "strong" or "none".
	 */
	private void setEasing(String v) {
		setAttribute(PARAMETER_easing, v);
	}

	/**
	 * Sets the pattern of animation easing to 'bounce'
	 */
	public void setEasingToBounce() {
		setEasing("bounce");
	}

	/**
	 * Sets the pattern of animation easing to 'regular'
	 */
	public void setEasingToRegular() {
		setEasing("regular");
	}

	/**
	 * Sets the pattern of animation easing to 'strong'
	 */
	public void setEasingToStrong() {
		setEasing("strong");
	}

	/**
	 * Sets the pattern of animation easing to 'none'
	 */
	public void setEasingToNone() {
		setEasing("none");
	}

}
