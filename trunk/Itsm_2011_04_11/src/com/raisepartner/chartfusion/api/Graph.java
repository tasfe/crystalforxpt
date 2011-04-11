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

public class Graph extends com.raisepartner.chartfusion.api.StyledChart {

	public final static String PARAMETER_NUMBERSCALEVALUE = "numberScaleValue";
	public final static String PARAMETER_BGANGLE = "bgAngle";
	public final static String PARAMETER_CANVASBORDERALPHA = "canvasBorderAlpha";
	public final static String PARAMETER_SHOWLABELS = "showLabels";
	public final static String PARAMETER_DIVLINEDASHLEN = "divLineDashLen";
	public final static String PARAMETER_LABELPADDING = "labelPadding";
	public final static String PARAMETER_SHOWLIMITS = "showLimits";
	public final static String PARAMETER_CHARTTOPMARGIN = "chartTopMargin";
	public final static String PARAMETER_DEFAULTANIMATION = "defaultAnimation";
	public final static String PARAMETER_ANIMATION = "animation";
	public final static String PARAMETER_DEFAULTNUMBERSCALE = "defaultNumberScale";
	public final static String PARAMETER_SLANTLABELS = "slantLabels";
	public final static String PARAMETER_ZEROPLANEALPHA = "zeroPlaneAlpha";
	public final static String PARAMETER_THOUSANDSEPARATOR = "thousandSeparator";
	public final static String PARAMETER_BORDERTHICKNESS = "borderThickness";
	public final static String PARAMETER_BGRATIO = "bgRatio";
	public final static String PARAMETER_LEGENDPOSITION = "legendPosition";
	public final static String PARAMETER_ZEROPLANETHICKNESS = "zeroPlaneThickness";
	public final static String PARAMETER_SHOWBORDER = "showBorder";
	public final static String PARAMETER_LEGENDBORDERTHICKNESS = "legendBorderThickness";
	public final static String PARAMETER_XAXISNAMEPADDING = "xAxisNamePadding";
	public final static String PARAMETER_BASEFONTSIZE = "baseFontSize";
	public final static String PARAMETER_CANVASBGRATIO = "canvasBgRatio";
	public final static String PARAMETER_DECIMALS = "decimals";
	public final static String PARAMETER_BGALPHA = "bgAlpha";
	public final static String PARAMETER_TOOLTIPBGCOLOR = "toolTipBgColor";
	public final static String PARAMETER_BASEFONT = "baseFont";
	public final static String PARAMETER_NUMDIVLINES = "numDivLines";
	public final static String PARAMETER_SUBCAPTION = "subCaption";
	public final static String PARAMETER_NUMBERSCALEUNIT = "numberScaleUnit";
	public final static String PARAMETER_BASEFONTCOLOR = "baseFontColor";
	public final static String PARAMETER_BGCOLOR = "bgColor";
	public final static String PARAMETER_CANVASBGANGLE = "canvasBgAngle";
	public final static String PARAMETER_LEGENDBORDERCOLOR = "legendBorderColor";
	public final static String PARAMETER_BGSWF = "bgSWF";
	public final static String PARAMETER_FORCEDECIMALS = "forceDecimals";
	public final static String PARAMETER_LEGENDSCROLLBARCOLOR = "legendScrollBarColor";
	public final static String PARAMETER_LEGENDSHADOW = "legendShadow";
	public final static String PARAMETER_BGSWFALPHA = "bgSWFAlpha";
	public final static String PARAMETER_SERIESNAMEINTOOLTIP = "seriesNameInToolTip";
	public final static String PARAMETER_CANVASBGALPHA = "canvasBgAlpha";
	public final static String PARAMETER_ALTERNATEHGRIDALPHA = "alternateHGridAlpha";
	public final static String PARAMETER_YAXISNAMEPADDING = "yAxisNamePadding";
	public final static String PARAMETER_NUMBERPREFIX = "numberPrefix";
	public final static String PARAMETER_NUMBERSUFFIX = "numberSuffix";
	public final static String PARAMETER_CHARTLEFTMARGIN = "chartLeftMargin";
	public final static String PARAMETER_CANVASBORDERTHICKNESS = "canvasBorderThickness";
	public final static String PARAMETER_CLICKURL = "clickURL";
	public final static String PARAMETER_DIVLINEDASHGAP = "divLineDashGap";
	public final static String PARAMETER_DECIMALSEPARATOR = "decimalSeparator";
	public final static String PARAMETER_LEGENDALLOWDRAG = "legendAllowDrag";
	public final static String PARAMETER_CANVASBGCOLOR = "canvasBgColor";
	public final static String PARAMETER_YAXISNAMEWIDTH = "yAxisNameWidth";
	public final static String PARAMETER_OUTCNVBASEFONTCOLOR = "outCnvBaseFontColor";
	public final static String PARAMETER_FORMATNUMBERSCALE = "formatNumberScale";
	public final static String PARAMETER_DIVLINETHICKNESS = "divLineThickness";
	public final static String PARAMETER_CAPTION = "caption";
	public final static String PARAMETER_YAXISNAME = "yAxisName";
	public final static String PARAMETER_SHOWTOOLTIP = "showToolTip";
	public final static String PARAMETER_LEGENDBORDERALPHA = "legendBorderAlpha";
	public final static String PARAMETER_INTHOUSANDSEPARATOR = "inThousandSeparator";
	public final static String PARAMETER_YAXISVALUEDECIMALS = "yAxisValueDecimals";
	public final static String PARAMETER_LABELSTEP = "labelStep";
	public final static String PARAMETER_ZEROPLANECOLOR = "zeroPlaneColor";
	public final static String PARAMETER_DIVLINECOLOR = "divLineColor";
	public final static String PARAMETER_XAXISNAME = "xAxisName";
	public final static String PARAMETER_SHOWZEROPLANE = "showZeroPlane";
	public final static String PARAMETER_LEGENDBGALPHA = "legendBgAlpha";
	public final static String PARAMETER_CHARTRIGHTMARGIN = "chartRightMargin";
	public final static String PARAMETER_PALETTE = "palette";
	public final static String PARAMETER_BORDERCOLOR = "borderColor";
	public final static String PARAMETER_OUTCNVBASEFONTSIZE = "outCnvBaseFontSize";
	public final static String PARAMETER_YAXISVALUESSTEP = "yAxisValuesStep";
	public final static String PARAMETER_SHOWVALUES = "showValues";
	public final static String PARAMETER_YAXISMINVALUE = "yAxisMinValue";
	public final static String PARAMETER_YAXISMAXVALUE = "yAxisMaxValue";
	public final static String PARAMETER_ALTERNATEHGRIDCOLOR = "alternateHGridColor";
	public final static String PARAMETER_LEGENDSCROLLBGCOLOR = "legendScrollBgColor";
	public final static String PARAMETER_SETADAPTIVEYMIN = "setAdaptiveYMin";
	public final static String PARAMETER_STAGGERLINES = "staggerLines";
	public final static String PARAMETER_TOOLTIPSEPCHAR = "toolTipSepChar";
	public final static String PARAMETER_CHARTBOTTOMMARGIN = "chartBottomMargin";
	public final static String PARAMETER_TOOLTIPBORDERCOLOR = "toolTipBorderColor";
	public final static String PARAMETER_ADJUSTDIV = "adjustDiv";
	public final static String PARAMETER_CANVASBORDERCOLOR = "canvasBorderColor";
	public final static String PARAMETER_SHOWDIVLINEVALUES = "showDivLineValues";
	public final static String PARAMETER_OUTCNVBASEFONT = "outCnvBaseFont";
	public final static String PARAMETER_ROTATELABELS = "rotateLabels";
	public final static String PARAMETER_FORMATNUMBER = "formatNumber";
	public final static String PARAMETER_DIVLINEALPHA = "divLineAlpha";
	public final static String PARAMETER_SHOWYAXISVALUES = "showYAxisValues";
	public final static String PARAMETER_CAPTIONPADDING = "captionPadding";
	public final static String PARAMETER_XAXISMAXVALUE = "xAxisMaxValue";
	public final static String PARAMETER_LEGENDSCROLLBTNCOLOR = "legendScrollBtnColor";
	public final static String PARAMETER_ROTATEYAXISNAME = "rotateYAxisName";
	public final static String PARAMETER_YAXISVALUESPADDING = "yAxisValuesPadding";
	public final static String PARAMETER_LEGENDBGCOLOR = "legendBgColor";
	public final static String PARAMETER_LEGENDPADDING = "legendPadding";
	public final static String PARAMETER_DIVLINEISDASHED = "divLineIsDashed";
	public final static String PARAMETER_SHOWALTERNATEHGRIDCOLOR = "showAlternateHGridColor";
	public final static String PARAMETER_LABELDISPLAY = "labelDisplay";
	public final static String PARAMETER_BORDERALPHA = "borderAlpha";
	public final static String PARAMETER_INDECIMALSEPARATOR = "inDecimalSeparator";
	public final static String PARAMETER_SHOWLEGEND = "showLegend";
	public final static String PARAMETER_XAXISMINVALUE = "xAxisMinValue";

	public Graph() {
		super("graph");
	}

	/**
	 * Type: String Range value: Description : Range of the various blocks that
	 * constitute the scale. For more details, please see Advanced Number
	 * Formatting section.
	 */
	public void setNumberScaleValue(String value) {
		setAttribute(PARAMETER_NUMBERSCALEVALUE, value);
	}

	/**
	 * Type: Number Range value: 0-360 Description : Angle of the background
	 * color, in case of a gradient. See the gradient specification page for
	 * more details.
	 */
	public void setBgAngle(String value) {
		setAttribute(PARAMETER_BGANGLE, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Lets you control
	 * transparency of canvas border.
	 */
	public void setCanvasBorderAlpha(String value) {
		setAttribute(PARAMETER_CANVASBORDERALPHA, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : It sets the configuration
	 * whether the x-axis labels will be displayed or not.
	 */
	public void setShowLabels(String value) {
		setAttribute(PARAMETER_SHOWLABELS, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If the divisional lines
	 * are to be displayed as dash, this attribute lets you control the length
	 * of each dash.
	 */
	public void setDivLineDashLen(String value) {
		setAttribute(PARAMETER_DIVLINEDASHLEN, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute sets the
	 * vertical space between the labels and canvas bottom edge. If you want
	 * more space between the canvas and the x-axis labels, you can use this
	 * attribute to control it.
	 */
	public void setLabelPadding(String value) {
		setAttribute(PARAMETER_LABELPADDING, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show chart limit
	 * values? showYAxisValues is the single new attribute in v3 which
	 * over-rides this value.
	 */
	public void setShowLimits(String value) {
		setAttribute(PARAMETER_SHOWLIMITS, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Amount of empty space
	 * that you want to put on the top of your chart. Nothing is rendered in
	 * this space.
	 */
	public void setChartTopMargin(String value) {
		setAttribute(PARAMETER_CHARTTOPMARGIN, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : By default, each chart
	 * animates some of its elements. If you wish to switch off the default
	 * animation patterns, you can set this attribute to 0. It can be
	 * particularly useful when you want to define your own animation patterns
	 * using STYLE feature.
	 */
	public void setDefaultAnimation(String value) {
		setAttribute(PARAMETER_DEFAULTANIMATION, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : This attribute lets you set
	 * the configuration whether the chart should appear in an animated fashion.
	 * If you do not want to animate any part of the chart, set this as 0.
	 */
	public void setAnimation(String value) {
		setAttribute(PARAMETER_ANIMATION, value);
	}

	/**
	 * Type: String Range value: Description : The default unit of the numbers
	 * that you're providing to the chart . For more details, please see
	 * Advanced Number Formatting section.
	 */
	public void setDefaultNumberScale(String value) {
		setAttribute(PARAMETER_DEFAULTNUMBERSCALE, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : If you've opted to show
	 * rotated labels on chart, this attribute lets you set the configuration
	 * whether the labels would show as slanted labels or fully vertical ones.
	 */
	public void setSlantLabels(String value) {
		setAttribute(PARAMETER_SLANTLABELS, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of zero plane.
	 */
	public void setZeroPlaneAlpha(String value) {
		setAttribute(PARAMETER_ZEROPLANEALPHA, value);
	}

	/**
	 * Type: String Range value: Character Description : This option helps you
	 * specify the character to be used as the thousands separator in a number.
	 * For more details, please see Advanced Number Formatting section.
	 */
	public void setThousandSeparator(String value) {
		setAttribute(PARAMETER_THOUSANDSEPARATOR, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Border thickness of the
	 * chart.
	 */
	public void setBorderThickness(String value) {
		setAttribute(PARAMETER_BORDERTHICKNESS, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : If you've opted for a
	 * gradient background, this attribute lets you set the ratio of each color
	 * constituent. See the gradient specification page for more details.
	 */
	public void setBgRatio(String value) {
		setAttribute(PARAMETER_BGRATIO, value);
	}

	/**
	 * Type: String Range value: BOTTOM or RIGHT Description : The legend can be
	 * plotted at two positions on the chart - below the chart (BOTTOM) and on
	 * the RIGHT side of the chart.
	 */
	public void setLegendPosition(String value) {
		setAttribute(PARAMETER_LEGENDPOSITION, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Thickness of zero
	 * plane.
	 */
	public void setZeroPlaneThickness(String value) {
		setAttribute(PARAMETER_ZEROPLANETHICKNESS, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show a border
	 * around the chart or not?
	 */
	public void setShowBorder(String value) {
		setAttribute(PARAMETER_SHOWBORDER, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Border thickness for
	 * the legend.
	 */
	public void setLegendBorderThickness(String value) {
		setAttribute(PARAMETER_LEGENDBORDERTHICKNESS, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Using this, you can set
	 * the distance between the top end of x-axis title and the bottom end of
	 * data labels (or canvas, if data labels are not to be shown).
	 */
	public void setXAxisNamePadding(String value) {
		setAttribute(PARAMETER_XAXISNAMEPADDING, value);
	}

	/**
	 * Type: Number Range value: 0-72 Description : This attribute sets the base
	 * font size of the chart i.e., all the values and the names in the chart
	 * which lie on the canvas will be displayed using the font size provided
	 * here.
	 */
	public void setBaseFontSize(String value) {
		setAttribute(PARAMETER_BASEFONTSIZE, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Helps you specify canvas
	 * background ratio for gradients.
	 */
	public void setCanvasBgRatio(String value) {
		setAttribute(PARAMETER_CANVASBGRATIO, value);
	}

	/**
	 * Type: Number Range value: 0-10 Description : Number of decimal places to
	 * which all numbers on the chart would be rounded to.
	 */
	public void setDecimals(String value) {
		setAttribute(PARAMETER_DECIMALS, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Sets the alpha
	 * (transparency) for the background. If you've opted for gradient
	 * background, you need to set a list of alpha(s) separated by comma. See
	 * the gradient specification page for more details.
	 */
	public void setBgAlpha(String value) {
		setAttribute(PARAMETER_BGALPHA, value);
	}

	/**
	 * Type: Color Range value: Description : Background Color for tool tip.
	 */
	public void setToolTipBgColor(String value) {
		setAttribute(PARAMETER_TOOLTIPBGCOLOR, value);
	}

	/**
	 * Type: String Range value: Font Name Description : This attribute lets you
	 * set the font face (family) of all the text (data labels, values etc.) on
	 * chart. If you specify outCnvBaseFont attribute also, then this attribute
	 * controls only the font face of text within the chart canvas bounds.
	 */
	public void setBaseFont(String value) {
		setAttribute(PARAMETER_BASEFONT, value);
	}

	/**
	 * Type: Number Range value: Numeric value > 0 Description : Number of
	 * horizontal axis division lines that you want on the chart.
	 */
	public void setNumDivLines(String value) {
		setAttribute(PARAMETER_NUMDIVLINES, value);
	}

	/**
	 * Type: String Range value: Description : Sub-caption of the chart.
	 */
	public void setSubCaption(String value) {
		setAttribute(PARAMETER_SUBCAPTION, value);
	}

	/**
	 * Type: String Range value: Description : Unit of each block of the scale.
	 * For more details, please see Advanced Number Formatting section.
	 */
	public void setNumberScaleUnit(String value) {
		setAttribute(PARAMETER_NUMBERSCALEUNIT, value);
	}

	/**
	 * Type: Color Range value: Description : This attribute sets the base font
	 * color of the chart i.e., all the values and the names in the chart which
	 * lie on the canvas will be displayed using the font color provided here.
	 */
	public void setBaseFontColor(String value) {
		setAttribute(PARAMETER_BASEFONTCOLOR, value);
	}

	/**
	 * Type: Color Range value: Description : This attribute sets the background
	 * color for the chart. You can set any hex color code as the value of this
	 * attribute. To specify a gradient as background color, separate the hex
	 * color codes of each color in the gradient using comma. Example:
	 * FF5904,FFFFFF. Remember to remove # and any spaces in between. See the
	 * gradient specification page for more details.
	 */
	public void setBgColor(String value) {
		setAttribute(PARAMETER_BGCOLOR, value);
	}

	/**
	 * Type: Number Range value: Description : Helps you specify canvas
	 * background angle in case of gradient.
	 */
	public void setCanvasBgAngle(String value) {
		setAttribute(PARAMETER_CANVASBGANGLE, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : Border Color for the
	 * legend.
	 */
	public void setLegendBorderColor(String value) {
		setAttribute(PARAMETER_LEGENDBORDERCOLOR, value);
	}

	/**
	 * Type: String Range value: Description : To place any Flash movie as
	 * background of the chart, enter the (path and) name of the background SWF.
	 * It should be in the same domain as the chart.
	 */
	public void setBgSWF(String value) {
		setAttribute(PARAMETER_BGSWF, value);
	}

	/**
	 * Type: Boolean Range value: 0-1 Description : Whether to add 0 padding at
	 * the end of decimal numbers? For example, if you set decimals as 2 and a
	 * number is 23.4. If forceDecimals is set to 1, FusionCharts will convert
	 * the number to 23. 40 (note the extra 0 at the end)
	 */
	public void setForceDecimals(String value) {
		setAttribute(PARAMETER_FORCEDECIMALS, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : If you've too many items
	 * on the legend, a scroll bar shows up on the same. This attribute lets you
	 * configure the bar color of the scroll bar.
	 */
	public void setLegendScrollBarColor(String value) {
		setAttribute(PARAMETER_LEGENDSCROLLBARCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show a shadow for
	 * legend?
	 */
	public void setLegendShadow(String value) {
		setAttribute(PARAMETER_LEGENDSHADOW, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Helps you specify alpha for
	 * the loaded background SWF.
	 */
	public void setBgSWFAlpha(String value) {
		setAttribute(PARAMETER_BGSWFALPHA, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : For multi-series and
	 * combination charts, FusionCharts shows the following information in tool
	 * tip (unless tool text is explicitly defined):
	 * "Series Name, Category Name, Data Value". This attribute lets you control
	 * whether series name would show up in tool tip or not?
	 */
	public void setSeriesNameInToolTip(String value) {
		setAttribute(PARAMETER_SERIESNAMEINTOOLTIP, value);
	}

	/**
	 * Type: Number Range value: Description : Sets alpha for Canvas Background.
	 * For gradient, enter alpha list separated by commas.
	 */
	public void setCanvasBgAlpha(String value) {
		setAttribute(PARAMETER_CANVASBGALPHA, value);
	}

	/**
	 * Type: Number Range value: Description : Alpha (transparency) of the
	 * alternate horizontal grid bands.
	 */
	public void setAlternateHGridAlpha(String value) {
		setAttribute(PARAMETER_ALTERNATEHGRIDALPHA, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Using this, you can set
	 * the distance between the right end of y- axis title and the start of
	 * y-axis values (or canvas, if the y-axis values are not to be shown).
	 */
	public void setYAxisNamePadding(String value) {
		setAttribute(PARAMETER_YAXISNAMEPADDING, value);
	}

	/**
	 * Type: String Range value: Character Description : Using this attribute,
	 * you could add prefix to all the numbers visible on the graph. For
	 * example, to represent all dollars figure on the chart, you could specify
	 * this attribute to ' $' to show like $40000, $50000. For more details,
	 * please see Advanced Number Formatting section.
	 */
	public void setNumberPrefix(String value) {
		setAttribute(PARAMETER_NUMBERPREFIX, value);
	}

	/**
	 * Type: String Range value: Character Description : Using this attribute,
	 * you could add suffix to all the numbers visible on the graph. For
	 * example, to represent all figure quantified as per annum on the chart,
	 * you could specify this attribute to ' /a' to show like 40000/a, 50000/a.
	 * For more details, please see Advanced Number Formatting section.
	 */
	public void setNumberSuffix(String value) {
		setAttribute(PARAMETER_NUMBERSUFFIX, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Amount of empty space
	 * that you want to put on the left side of your chart. Nothing is rendered
	 * in this space.
	 */
	public void setChartLeftMargin(String value) {
		setAttribute(PARAMETER_CHARTLEFTMARGIN, value);
	}

	/**
	 * Type: Number Range value: 0-5 Description : Lets you specify canvas
	 * border thickness.
	 */
	public void setCanvasBorderThickness(String value) {
		setAttribute(PARAMETER_CANVASBORDERTHICKNESS, value);
	}

	/**
	 * Type: String Range value: Description : The entire chart can now act as a
	 * hotspot. Use this URL to define the hotspot link for the chart. The link
	 * can be specified in FusionCharts Link Format.
	 */
	public void setClickURL(String value) {
		setAttribute(PARAMETER_CLICKURL, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : If the divisional lines
	 * are to be displayed as dash, this attribute lets you control the length
	 * of each gap between dash.
	 */
	public void setDivLineDashGap(String value) {
		setAttribute(PARAMETER_DIVLINEDASHGAP, value);
	}

	/**
	 * Type: String Range value: Character Description : This option helps you
	 * specify the character to be used as the decimal separator in a number.
	 * For more details, please see Advanced Number Formatting section.
	 */
	public void setDecimalSeparator(String value) {
		setAttribute(PARAMETER_DECIMALSEPARATOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : The legend can be made
	 * drag-able by setting this attribute to 1. End viewers of the chart can
	 * drag the legend around on the chart.
	 */
	public void setLegendAllowDrag(String value) {
		setAttribute(PARAMETER_LEGENDALLOWDRAG, value);
	}

	/**
	 * Type: Color Range value: Description : Sets Canvas background color. For
	 * Gradient effect, enter colors separated by comma.
	 */
	public void setCanvasBgColor(String value) {
		setAttribute(PARAMETER_CANVASBGCOLOR, value);
	}

	/**
	 * Type: Number Range value: (In Pixels) Description : If you opt to not
	 * rotate y-axis name, you can choose a maximum width that will be applied
	 * to y-axis name.
	 */
	public void setYAxisNameWidth(String value) {
		setAttribute(PARAMETER_YAXISNAMEWIDTH, value);
	}

	/**
	 * Type: Color Range value: Description : This attribute sets the base font
	 * color of the chart i.e., all the values and the names in the chart which
	 * lie outside the canvas will be displayed using the font color provided
	 * here.
	 */
	public void setOutCnvBaseFontColor(String value) {
		setAttribute(PARAMETER_OUTCNVBASEFONTCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Configuration whether to add
	 * K (thousands) and M (millions) to a number after truncating and rounding
	 * it - e.g., if formatNumberScale is set to 1, 1043 would become 1.04K
	 * (with decimals set to 2 places). Same with numbers in millions - a M will
	 * added at the end. For more details, please see Advanced Number Formatting
	 * section.
	 */
	public void setFormatNumberScale(String value) {
		setAttribute(PARAMETER_FORMATNUMBERSCALE, value);
	}

	/**
	 * Type: Number Range value: 1-5 Description : Thickness of divisional lines
	 */
	public void setDivLineThickness(String value) {
		setAttribute(PARAMETER_DIVLINETHICKNESS, value);
	}

	/**
	 * Type: String Range value: Description : Caption of the chart.
	 */
	public void setCaption(String value) {
		setAttribute(PARAMETER_CAPTION, value);
	}

	/**
	 * Type: String Range value: Description : Y-Axis Title of the chart.
	 */
	public void setYAxisName(String value) {
		setAttribute(PARAMETER_YAXISNAME, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show tool tip on
	 * chart?
	 */
	public void setShowToolTip(String value) {
		setAttribute(PARAMETER_SHOWTOOLTIP, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Border alpha for the
	 * legend.
	 */
	public void setLegendBorderAlpha(String value) {
		setAttribute(PARAMETER_LEGENDBORDERALPHA, value);
	}

	/**
	 * Type: String Range value: Character Description : In some countries,
	 * commas are used as decimal separators and dots as thousand separators. In
	 * XML, if you specify such values, it will give a error while converting to
	 * number. So, FusionCharts accepts the input decimal and thousand separator
	 * from user, so that it can covert it accordingly into the required format.
	 * This attribute lets you input the thousand separator. For more details,
	 * please see Advanced Number Formatting section.
	 */
	public void setInThousandSeparator(String value) {
		setAttribute(PARAMETER_INTHOUSANDSEPARATOR, value);
	}

	/**
	 * Type: Number Range value: 0-10 Description : If you've opted to not
	 * adjust div lines, you can specify the div line values decimal precision
	 * using this attribute.
	 */
	public void setYAxisValueDecimals(String value) {
		setAttribute(PARAMETER_YAXISVALUEDECIMALS, value);
	}

	/**
	 * Type: Number Range value: 1 or above Description : By default, all the
	 * labels are displayed on the chart. However, if you've a set of streaming
	 * data (like name of months or days of week), you can opt to hide every
	 * n-th label for better clarity. This attributes just lets you do so. It
	 * allows to skip every n(th) X-axis label.
	 */
	public void setLabelStep(String value) {
		setAttribute(PARAMETER_LABELSTEP, value);
	}

	/**
	 * Type: Color Range value: Description : Color for the Zero Plane. Zero
	 * Plane is the line/plane that appears at 0 y-position on canvas, when
	 * negative data is being shown on the chart.
	 */
	public void setZeroPlaneColor(String value) {
		setAttribute(PARAMETER_ZEROPLANECOLOR, value);
	}

	/**
	 * Type: Color Range value: Description : Color for divisional lines
	 */
	public void setDivLineColor(String value) {
		setAttribute(PARAMETER_DIVLINECOLOR, value);
	}

	/**
	 * Type: String Range value: Description : X-Axis Title of the Chart.
	 */
	public void setXAxisName(String value) {
		setAttribute(PARAMETER_XAXISNAME, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show zero plane?
	 */
	public void setShowZeroPlane(String value) {
		setAttribute(PARAMETER_SHOWZEROPLANE, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Background alpha for the
	 * legend.
	 */
	public void setLegendBgAlpha(String value) {
		setAttribute(PARAMETER_LEGENDBGALPHA, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Amount of empty space
	 * that you want to put on the right side of your chart. Nothing is rendered
	 * in this space.
	 */
	public void setChartRightMargin(String value) {
		setAttribute(PARAMETER_CHARTRIGHTMARGIN, value);
	}

	/**
	 * Type: Number Range value: 1-5 Description : FusionCharts v3 introduces
	 * the concept of Color Palettes. Each chart has 5 pre-defined color
	 * palettes which you can choose from. Each palette renders the chart in a
	 * different color theme. Valid values are 1-5.
	 */
	public void setPalette(String value) {
		setAttribute(PARAMETER_PALETTE, value);
	}

	/**
	 * Type: Color Range value: Description : Border color of the chart.
	 */
	public void setBorderColor(String value) {
		setAttribute(PARAMETER_BORDERCOLOR, value);
	}

	/**
	 * Type: Number Range value: 0-72 Description : This attribute sets the base
	 * font size of the chart i.e., all the values and the names in the chart
	 * which lie outside the canvas will be displayed using the font size
	 * provided here.
	 */
	public void setOutCnvBaseFontSize(String value) {
		setAttribute(PARAMETER_OUTCNVBASEFONTSIZE, value);
	}

	/**
	 * Type: Number Range value: 1 or above Description : By default, all div
	 * lines show their values. However, you can opt to skip every x(th) div
	 * line value using this attribute.
	 */
	public void setYAxisValuesStep(String value) {
		setAttribute(PARAMETER_YAXISVALUESSTEP, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Sets the configuration
	 * whether data values would be displayed along with the data plot on chart.
	 */
	public void setShowValues(String value) {
		setAttribute(PARAMETER_SHOWVALUES, value);
	}

	/**
	 * Type: Number Range value: Description : This attribute helps you
	 * explicitly set the lower limit of the chart. If you don't specify this
	 * value, it is automatically calculated by FusionCharts based on the data
	 * provided by you.
	 */
	public void setYAxisMinValue(String value) {
		setAttribute(PARAMETER_YAXISMINVALUE, value);
	}

	/**
	 * Type: Number Range value: Description : This attribute helps you
	 * explicitly set the upper limit of the chart. If you don't specify this
	 * value, it is automatically calculated by FusionCharts based on the data
	 * provided by you.
	 */
	public void setYAxisMaxValue(String value) {
		setAttribute(PARAMETER_YAXISMAXVALUE, value);
	}

	/**
	 * Type: Color Range value: Description : Color of the alternate horizontal
	 * grid bands.
	 */
	public void setAlternateHGridColor(String value) {
		setAttribute(PARAMETER_ALTERNATEHGRIDCOLOR, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : If you've too many items
	 * on the legend, a scroll bar shows up on the same. This attribute lets you
	 * configure the background color of the scroll bar.
	 */
	public void setLegendScrollBgColor(String value) {
		setAttribute(PARAMETER_LEGENDSCROLLBGCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : This attribute lets you set
	 * whether the y-axis lower limit would be 0 (in case of all positive values
	 * on chart) or should the y-axis lower limit adapt itself to a different
	 * figure based on values provided to the chart.
	 */
	public void setSetAdaptiveYMin(String value) {
		setAttribute(PARAMETER_SETADAPTIVEYMIN, value);
	}

	/**
	 * Type: Number Range value: 2 or above Description : If you've opted for
	 * STAGGER mode as labelDisplay, using this attribute you can control how
	 * many lines to stagger the label to. By default, all labels are displayed
	 * in a single line.
	 */
	public void setStaggerLines(String value) {
		setAttribute(PARAMETER_STAGGERLINES, value);
	}

	/**
	 * Type: String Range value: Description : The character specified as the
	 * value of this attribute separates the name and value displayed in tool
	 * tip.
	 */
	public void setToolTipSepChar(String value) {
		setAttribute(PARAMETER_TOOLTIPSEPCHAR, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Amount of empty space
	 * that you want to put on the bottom of your chart. Nothing is rendered in
	 * this space.
	 */
	public void setChartBottomMargin(String value) {
		setAttribute(PARAMETER_CHARTBOTTOMMARGIN, value);
	}

	/**
	 * Type: Color Range value: Description : Border Color for tool tip.
	 */
	public void setToolTipBorderColor(String value) {
		setAttribute(PARAMETER_TOOLTIPBORDERCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : FusionCharts automatically
	 * tries to adjust divisional lines and limit values based on the data
	 * provided. However, if you want to set your explicit lower and upper limit
	 * values and number of divisional lines, first set this attribute to false.
	 * That would disable automatic adjustment of divisional lines.
	 */
	public void setAdjustDiv(String value) {
		setAttribute(PARAMETER_ADJUSTDIV, value);
	}

	/**
	 * Type: Color Range value: Description : Lets you specify canvas border
	 * color.
	 */
	public void setCanvasBorderColor(String value) {
		setAttribute(PARAMETER_CANVASBORDERCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show div line
	 * values? showYAxisValues is the single new attribute in v3 which
	 * over-rides this value.
	 */
	public void setShowDivLineValues(String value) {
		setAttribute(PARAMETER_SHOWDIVLINEVALUES, value);
	}

	/**
	 * Type: String Range value: Font Name Description : This attribute sets the
	 * base font family of the chart font which lies outside the canvas i.e.,
	 * all the values and the names in the chart which lie outside the canvas
	 * will be displayed using the font name provided here.
	 */
	public void setOutCnvBaseFont(String value) {
		setAttribute(PARAMETER_OUTCNVBASEFONT, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : This attribute lets you set
	 * whether the data labels would show up as rotated labels on the chart.
	 */
	public void setRotateLabels(String value) {
		setAttribute(PARAMETER_ROTATELABELS, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : This configuration
	 * determines whether the numbers displayed on the chart will be formatted
	 * using commas, e.g., 40,000 if formatNumber='1' and 40000 if
	 * formatNumber='0 '
	 */
	public void setFormatNumber(String value) {
		setAttribute(PARAMETER_FORMATNUMBER, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Alpha of divisional lines.
	 */
	public void setDivLineAlpha(String value) {
		setAttribute(PARAMETER_DIVLINEALPHA, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : FusionCharts y-axis is
	 * divided into vertical sections using div (divisional) lines. Each div
	 * line assumes a value based on its position. Using this attribute you can
	 * set whether to show those div line (y-axis) values or not.
	 */
	public void setShowYAxisValues(String value) {
		setAttribute(PARAMETER_SHOWYAXISVALUES, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute lets you
	 * control the space (in pixels) between the sub-caption and top of the
	 * chart canvas. If the sub-caption is not defined, it controls the space
	 * between caption and top of chart canvas . If neither caption, nor
	 * sub-caption is defined, this padding does not come into play.
	 */
	public void setCaptionPadding(String value) {
		setAttribute(PARAMETER_CAPTIONPADDING, value);
	}

	/**
	 * Type: Number Range value: Numeric Value Description : The bubble/scatter
	 * chart have both x and y axis as numeric. This attribute lets you
	 * explicitly set the x-axis upper limit. If you do not specify this value,
	 * FusionCharts will automatically calculate the best value for you.
	 */
	public void setXAxisMaxValue(String value) {
		setAttribute(PARAMETER_XAXISMAXVALUE, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : If you've too many items
	 * on the legend, a scroll bar shows up on the same. This attribute lets you
	 * configure the color of buttons of the scroll bar.
	 */
	public void setLegendScrollBtnColor(String value) {
		setAttribute(PARAMETER_LEGENDSCROLLBTNCOLOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : If you do not wish to rotate
	 * y-axis name, set this as 0. It specifically comes to use when you've
	 * special characters (UTF8) in your y-axis name that do not show up in
	 * rotated mode.
	 */
	public void setRotateYAxisName(String value) {
		setAttribute(PARAMETER_ROTATEYAXISNAME, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : This attribute helps
	 * you set the horizontal space between the canvas left edge and the y-axis
	 * values or trend line values (on left/ right side). This is particularly
	 * useful, when you want more space between your canvas and y-axis values.
	 */
	public void setYAxisValuesPadding(String value) {
		setAttribute(PARAMETER_YAXISVALUESPADDING, value);
	}

	/**
	 * Type: Color Range value: Hex Code Description : Background color for the
	 * legend.
	 */
	public void setLegendBgColor(String value) {
		setAttribute(PARAMETER_LEGENDBGCOLOR, value);
	}

	/**
	 * Type: Number Range value: In Pixels Description : Padding of legend from
	 * right/bottom side of canvas
	 */
	public void setLegendPadding(String value) {
		setAttribute(PARAMETER_LEGENDPADDING, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether the divisional lines
	 * should display as dash?
	 */
	public void setDivLineIsDashed(String value) {
		setAttribute(PARAMETER_DIVLINEISDASHED, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show alternate
	 * colored horizontal grid bands?
	 */
	public void setShowAlternateHGridColor(String value) {
		setAttribute(PARAMETER_SHOWALTERNATEHGRIDCOLOR, value);
	}

	/**
	 * Type: String Range value: WRAP, STAGGER, ROTATE or NONE Description :
	 * Using this attribute, you can control how your data labels (x-axis
	 * labels) would appear on the chart. There are 4 options: WRAP, STAGGER,
	 * ROTATE or NONE. WRAP wraps the label text if it's longer than the
	 * allotted area. ROTATE rotates the label in vertical or slanted position.
	 * STAGGER divides the labels into multiple lines.
	 */
	public void setLabelDisplay(String value) {
		setAttribute(PARAMETER_LABELDISPLAY, value);
	}

	/**
	 * Type: Number Range value: 0-100 Description : Border alpha of the chart.
	 */
	public void setBorderAlpha(String value) {
		setAttribute(PARAMETER_BORDERALPHA, value);
	}

	/**
	 * Type: String Range value: Character Description : In some countries,
	 * commas are used as decimal separators and dots as thousand separators. In
	 * XML, if you specify such values, it will give a error while converting to
	 * number. So, FusionCharts accepts the input decimal and thousand separator
	 * from user, so that it can covert it accordingly into the required format.
	 * This attribute lets you input the decimal separator. For more details,
	 * please see Advanced Number Formatting section.
	 */
	public void setInDecimalSeparator(String value) {
		setAttribute(PARAMETER_INDECIMALSEPARATOR, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show legend for
	 * the chart (only multi-series and combination charts)?
	 */
	public void setShowLegend(String value) {
		setAttribute(PARAMETER_SHOWLEGEND, value);
	}

	/**
	 * Type: Number Range value: Numeric Value Description : The bubble/scatter
	 * chart have both x and y axis as numeric. This attribute lets you
	 * explicitly set the x-axis lower limit. If you do not specify this value,
	 * FusionCharts will automatically calculate the best value for you.
	 */
	public void setXAxisMinValue(String value) {
		setAttribute(PARAMETER_XAXISMINVALUE, value);
	}
}
