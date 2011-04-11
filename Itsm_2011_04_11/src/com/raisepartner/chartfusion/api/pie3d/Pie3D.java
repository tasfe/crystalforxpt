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
package com.raisepartner.chartfusion.api.pie3d;

public class Pie3D extends com.raisepartner.chartfusion.api.Chart {

	public final static String PARAMETER_SHOWZEROPIES = "showZeroPies";
	public final static String PARAMETER_SHOWPERCENTVALUES = "showPercentValues";
	public final static String PARAMETER_SHOWPERCENTINTOOLTIP = "showPercentInToolTip";
	public final static String PARAMETER_LABELSEPCHAR = "labelSepChar";
	public final static String PARAMETER_SHOWPLOTBORDER = "showPlotBorder";
	public final static String PARAMETER_PLOTBORDERCOLOR = "plotBorderColor";
	public final static String PARAMETER_PLOTBORDERTHICKNESS = "plotBorderThickness";
	public final static String PARAMETER_PLOTBORDERALPHA = "plotBorderAlpha";
	public final static String PARAMETER_PLOTFILLALPHA = "plotFillAlpha";
	public final static String PARAMETER_USE3DLIGHTING = "use3DLighting";
	public final static String PARAMETER_SLICINGDISTANCE = "slicingDistance";
	public final static String PARAMETER_PIERADIUS = "pieRadius";
	public final static String PARAMETER_STARTINGANGLE = "startingAngle";
	public final static String PARAMETER_ENABLEROTATION = "enableRotation";
	public final static String PARAMETER_PIEINNERFACEALPHA = "pieInnerFaceAlpha";
	public final static String PARAMETER_PIEOUTERFACEALPHA = "pieOuterFaceAlpha";
	public final static String PARAMETER_PIEYSCALE = "pieYScale";
	public final static String PARAMETER_PIESLICEDEPTH = "pieSliceDepth";
	public final static String PARAMETER_ENABLESMARTLABELS = "enableSmartLabels";
	public final static String PARAMETER_SKIPOVERLAPLABELS = "skipOverlapLabels";
	public final static String PARAMETER_ISSMARTLINESLANTED = "isSmartLineSlanted";
	public final static String PARAMETER_SMARTLINECOLOR = "smartLineColor";
	public final static String PARAMETER_SMARTLINETHICKNESS = "smartLineThickness";
	public final static String PARAMETER_SMARTLINEALPHA = "smartLineAlpha";
	public final static String PARAMETER_LABELDISTANCE = "labelDistance";
	public final static String PARAMETER_SMARTLABELCLEARANCE = "smartLabelClearance";

	public Pie3D() {
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Configuration whether to
	 * show pies with 0 values (and their values). Otherwise, they won't show up
	 * in the chart.
	 */
	public void setShowZeroPies(String value) {
		setAttribute(PARAMETER_SHOWZEROPIES, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show percentage
	 * values in labels of the chart?
	 */
	public void setShowPercentValues(String value) {
		setAttribute(PARAMETER_SHOWPERCENTVALUES, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show percentage
	 * values in tool tip?
	 */
	public void setShowPercentInToolTip(String value) {
		setAttribute(PARAMETER_SHOWPERCENTINTOOLTIP, value);
	}

	/**
	 * Type: String Range value: Character Description : The character to
	 * separate the data label and data values on the chart.
	 */
	public void setLabelSepChar(String value) {
		setAttribute(PARAMETER_LABELSEPCHAR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the column, area,
	 * pie etc. border would show up.
	 */
	public void setShowPlotBorder(String value) {
		setAttribute(PARAMETER_SHOWPLOTBORDER, value);
	}

	/**
	 * Type: Color Range value: Description : Color for column, area, pie border
	 */
	public void setPlotBorderColor(String value) {
		setAttribute(PARAMETER_PLOTBORDERCOLOR, value);
	}

	/**
	 * Type: Number Range value: 0-5 (Pixels) Description : Thickness for
	 * column, area, pie border
	 */
	public void setPlotBorderThickness(String value) {
		setAttribute(PARAMETER_PLOTBORDERTHICKNESS, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha for column, area, pie
	 * border
	 */
	public void setPlotBorderAlpha(String value) {
		setAttribute(PARAMETER_PLOTBORDERALPHA, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : This attribute lets you set
	 * the fill alpha for plot.
	 */
	public void setPlotFillAlpha(String value) {
		setAttribute(PARAMETER_PLOTFILLALPHA, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to use advanced
	 * gradients and shadow effects to create better looking 3D charts?
	 */
	public void setUse3DLighting(String value) {
		setAttribute(PARAMETER_USE3DLIGHTING, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If you've opted to
	 * slice a particular pie/doughnut slice, using this attribute you can
	 * control the distance between the slice and the center of chart.
	 */
	public void setSlicingDistance(String value) {
		setAttribute(PARAMETER_SLICINGDISTANCE, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute lets you
	 * explicitly set the outer radius of the chart. FusionCharts automatically
	 * calculates the best fit pie radius for the chart. This attribute is
	 * useful if you want to enforce one of your own values.
	 */
	public void setPieRadius(String value) {
		setAttribute(PARAMETER_PIERADIUS, value);
	}

	/**
	 * Type: Number Range value: 0-360 Description : By default, the pie chart
	 * starts from angle 0 i.e., the first pie starts plotting from 0 angle. If
	 * you want to change the starting angle of the chart, use this attribute.
	 */
	public void setStartingAngle(String value) {
		setAttribute(PARAMETER_STARTINGANGLE, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : The doughnut/pie charts have
	 * three modes: Slicing, Rotation and Link. If any links are defined, the
	 * chart works in Link mode. Otherwise, it starts in Slicing mode. If you
	 * need to enable rotation by default, set this attribute to 1.
	 */
	public void setEnableRotation(String value) {
		setAttribute(PARAMETER_ENABLEROTATION, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of the pie inner face
	 */
	public void setPieInnerFaceAlpha(String value) {
		setAttribute(PARAMETER_PIEINNERFACEALPHA, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of the pie outer face
	 */
	public void setPieOuterFaceAlpha(String value) {
		setAttribute(PARAMETER_PIEOUTERFACEALPHA, value);
	}

	/**
	 * Type: Number Range value: 30-80 Description : This attribute alters the
	 * y-perspective of the pie in percentage figures. 100 percent means the
	 * full pie face is visible and 0 percent means only the side face is
	 * visible.
	 */
	public void setPieYScale(String value) {
		setAttribute(PARAMETER_PIEYSCALE, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute controls
	 * the pie 3D Depth.
	 */
	public void setPieSliceDepth(String value) {
		setAttribute(PARAMETER_PIESLICEDEPTH, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to use smart labels
	 * or not?
	 */
	public void setEnableSmartLabels(String value) {
		setAttribute(PARAMETER_ENABLESMARTLABELS, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to skip labels that
	 * are overlapping even when using smart labels? If not, they might overlap
	 * if there are too many labels.
	 */
	public void setSkipOverlapLabels(String value) {
		setAttribute(PARAMETER_SKIPOVERLAPLABELS, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : The smart lines (smart label
	 * connector lines) can appear in two ways: Slanted or Straight. This
	 * attribute lets you choose between them .
	 */
	public void setIsSmartLineSlanted(String value) {
		setAttribute(PARAMETER_ISSMARTLINESLANTED, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : Color of smart label
	 * connector lines.
	 */
	public void setSmartLineColor(String value) {
		setAttribute(PARAMETER_SMARTLINECOLOR, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Thickness of smart
	 * label connector lines.
	 */
	public void setSmartLineThickness(String value) {
		setAttribute(PARAMETER_SMARTLINETHICKNESS, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of smart label
	 * connector lines.
	 */
	public void setSmartLineAlpha(String value) {
		setAttribute(PARAMETER_SMARTLINEALPHA, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute helps
	 * you set the distance of the label/value text boxes from the pie/doughnut
	 * edge.
	 */
	public void setLabelDistance(String value) {
		setAttribute(PARAMETER_LABELDISTANCE, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Clearance distance of a
	 * label (for sliced-in pies) from an adjacent sliced out pies.
	 */
	public void setSmartLabelClearance(String value) {
		setAttribute(PARAMETER_SMARTLABELCLEARANCE, value);
	}

	/**
	 * @see com.raisepartner.chartfusion.api.pie3d.Pie3DSet
	 */
	public Pie3DSet createSetNode() {
		Pie3DSet node = new Pie3DSet();
		addNode(node);
		return node;
	}

}
