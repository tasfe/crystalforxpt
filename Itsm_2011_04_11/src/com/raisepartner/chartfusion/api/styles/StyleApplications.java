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

public final class StyleApplications extends Node {
	/**
	 * BACKGROUND refers to the entire background of the chart.
	 */
	public final static String OBJECT_BACKGROUND = "Background";
	/**
	 * In 2D Charts, CANVAS refers to the area in which the actual chart is
	 * plotted. It is represented by a bounded rectangle. In 3D charts, it
	 * refers to the 3D base on which the columns are built.
	 */
	public final static String OBJECT_CANVAS = "Canvas";
	/**
	 * CAPTION refers to the heading of the chart.
	 */
	public final static String OBJECT_CAPTION = "Caption";
	/**
	 * DATALABELS refer to the x-axis labels of the data.
	 */
	public final static String OBJECT_DATALABELS = "DataLabels";
	/**
	 * DATAPLOT refers to the actual plot of the chart. For example, in Column
	 * 2D chart, columns are referred to as DATAPLOT. In Pie chart, it's the
	 * pies. In Bubble chart, it's the bubbles and so on.
	 */
	public final static String OBJECT_DATAPLOT = "DataPlot";
	/**
	 * DATAVALUES refer to the plot values i.e., value of each data (line,
	 * column, bar, pie etc.), which is displayed beside the data plot.
	 */
	public final static String OBJECT_DATAVALUES = "DataValues";
	/**
	 * DIVLINES are horizontal or vertical lines running through the canvas.
	 * Each divisional line signifes a smaller unit of the entire axis thus
	 * aiding the users in interpreting the chart.
	 */
	public final static String DIVLINES = "DivLines";
	/**
	 * VDIVLINES are vertical divisional lines which aid in data interpretation.
	 */
	public final static String VDIVLINES = "VDivLines";
	/**
	 * HGRID refers to alternate color bands between two successive horizontal
	 * divisional lines.
	 */
	public final static String OBJECT_HGRID = "HGrid";
	/**
	 * VGRID refers to vertical color bands between two successive vertical
	 * divisional lines.
	 */
	public final static String OBJECT_VGRID = "VGrid";
	/**
	 * SUBCAPTION refers to the sub-heading of the chart. Animation
	 */
	public final static String OBJECT_SUBCAPTION = "SubCaption";
	/**
	 * TOOLTIP refers to the tool tip shown when mouse is hovered over the data
	 * plots
	 */
	public final static String OBJECT_TOOLTIP = "ToolTip";
	/**
	 * TRENDLINES refer to horizontal/vertical lines spanning the chart canvas
	 * which aid in interpretation of data with respect to some pre-determined
	 * value.
	 */
	public final static String OBJECT_TRENDLINES = "TrendLines";
	/**
	 * TRENDVALUES refer to the display values of trend-lines (if any).
	 */
	public final static String OBJECT_TRENDVALUES = "TrendValues";
	/**
	 * VTRENDLINES refer to vertical lines spanning the chart canvas which aid
	 * in interpretation of data with respect to some pre-determined value.
	 */
	public final static String OBJECT_VTRENDLINES = "VTrendLines";
	/**
	 * VTRENDVALUES refer to the display values of vertical trend-lines.
	 */
	public final static String OBJECT_VTRENDVALUES = "TrendValues";
	/**
	 * VLINES are vertical separator lines that help you separate blocks of
	 * data. These lines run through the height of the chart, thereby
	 * segregating data into different blocks. In case of bar charts, they are
	 * horizontal and run through the width of chart.
	 */
	public final static String OBJECT_VLINES = "vLines";
	/**
	 * XAXISNAME refers to the x-axis title of the chart.
	 */
	public final static String OBJECT_XAXISNAME = "XAxisName";
	/**
	 * YAXISNAME refers to the y-axis title of the chart.
	 */
	public final static String OBJECT_YAXISNAME = "YAxisName";
	/**
	 * YAXISVALUES refers to the limit values or divisional line values, which
	 * are displayed along the y-axis of the chart.
	 */
	public final static String OBJECT_YAXISVALUES = "YAxisValues";
	/**
	 * LEGEND is the object in which the series names of all data-sets show up.
	 */
	public final static String OBJECT_LEGEND = "Legend";
	/**
	 * On line/area charts, ANCHORS (or marker points) are polygons which appear
	 * at the joint of two consecutive lines/area points. These are indicators
	 * to show the position of data points. The anchors handle tool tips and
	 * links for the data points.
	 */
	public final static String OBJECT_ANCHORS = "Anchors";
	/**
	 * DATAPLOTAREA refers to the area plot on the chart.
	 */
	public final static String OBJECT_DATAPLOTAREA = "DataPlotArea";
	/**
	 * DATAPLOTCOLUMN refers to the column plot on the chart.
	 */
	public final static String OBJECT_DATAPLOTCOLUMN = "DataPlotColumn";
	/**
	 * DATAPLOTLINE refers to the line plot on the chart.
	 */
	public final static String OBJECT_DATAPLOTLINE = "DataPlotLine";
	/**
	 * SCROLLPANE refers to the scroll bar on the chart.
	 */
	public final static String OBJECT_SCROLLPANE = "ScrollPane";

	public StyleApplications() {
		super("application");
	}

	public void applyStyles(String toObject, String styles) {
		addNode(new Apply(toObject, styles));
	}
}
