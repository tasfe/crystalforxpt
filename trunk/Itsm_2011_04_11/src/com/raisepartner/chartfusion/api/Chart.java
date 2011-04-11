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

public class Chart extends com.raisepartner.chartfusion.api.StyledChart {

	public final static String PARAMETER_NUMBERSCALEVALUE = "numberScaleValue";
	public final static String PARAMETER_BGANGLE = "bgAngle";
	public final static String PARAMETER_SHOWLABELS = "showLabels";
	public final static String PARAMETER_CHARTTOPMARGIN = "chartTopMargin";
	public final static String PARAMETER_DEFAULTANIMATION = "defaultAnimation";
	public final static String PARAMETER_ANIMATION = "animation";
	public final static String PARAMETER_DEFAULTNUMBERSCALE = "defaultNumberScale";
	public final static String PARAMETER_THOUSANDSEPARATOR = "thousandSeparator";
	public final static String PARAMETER_BORDERTHICKNESS = "borderThickness";
	public final static String PARAMETER_BGRATIO = "bgRatio";
	public final static String PARAMETER_SHOWBORDER = "showBorder";
	public final static String PARAMETER_BASEFONTSIZE = "baseFontSize";
	public final static String PARAMETER_DECIMALS = "decimals";
	public final static String PARAMETER_BGALPHA = "bgAlpha";
	public final static String PARAMETER_TOOLTIPBGCOLOR = "toolTipBgColor";
	public final static String PARAMETER_BASEFONT = "baseFont";
	public final static String PARAMETER_SUBCAPTION = "subCaption";
	public final static String PARAMETER_NUMBERSCALEUNIT = "numberScaleUnit";
	public final static String PARAMETER_BASEFONTCOLOR = "baseFontColor";
	public final static String PARAMETER_BGCOLOR = "bgColor";
	public final static String PARAMETER_FORCEDECIMALS = "forceDecimals";
	public final static String PARAMETER_BGSWF = "bgSWF";
	public final static String PARAMETER_BGSWFALPHA = "bgSWFAlpha";
	public final static String PARAMETER_NUMBERPREFIX = "numberPrefix";
	public final static String PARAMETER_NUMBERSUFFIX = "numberSuffix";
	public final static String PARAMETER_CHARTLEFTMARGIN = "chartLeftMargin";
	public final static String PARAMETER_CLICKURL = "clickURL";
	public final static String PARAMETER_DECIMALSEPARATOR = "decimalSeparator";
	public final static String PARAMETER_FORMATNUMBERSCALE = "formatNumberScale";
	public final static String PARAMETER_CAPTION = "caption";
	public final static String PARAMETER_SHOWTOOLTIP = "showToolTip";
	public final static String PARAMETER_INTHOUSANDSEPARATOR = "inThousandSeparator";
	public final static String PARAMETER_CHARTRIGHTMARGIN = "chartRightMargin";
	public final static String PARAMETER_PALETTE = "palette";
	public final static String PARAMETER_BORDERCOLOR = "borderColor";
	public final static String PARAMETER_SHOWVALUES = "showValues";
	public final static String PARAMETER_TOOLTIPSEPCHAR = "toolTipSepChar";
	public final static String PARAMETER_CHARTBOTTOMMARGIN = "chartBottomMargin";
	public final static String PARAMETER_TOOLTIPBORDERCOLOR = "toolTipBorderColor";
	public final static String PARAMETER_FORMATNUMBER = "formatNumber";
	public final static String PARAMETER_CAPTIONPADDING = "captionPadding";
	public final static String PARAMETER_BORDERALPHA = "borderAlpha";
	public final static String PARAMETER_INDECIMALSEPARATOR = "inDecimalSeparator";
	public final static String PARAMETER_SHOWFCMENUITEM = "showFCMenuItem";

	public Chart() {
		super("chart");
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
	 * Type: Boolean Range value: 0/1 Description : It sets the configuration
	 * whether the x-axis labels will be displayed or not.
	 */
	public void setShowLabels(String value) {
		setAttribute(PARAMETER_SHOWLABELS, value);
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
	 * Type: String Range value: Character Description : This option helps you
	 * specify the character to be used as the thousands separator in a number.
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
	 * Type: Boolean Range value: 0/1 Description : Whether to show a border
	 * around the chart or not?
	 */
	public void setShowBorder(String value) {
		setAttribute(PARAMETER_SHOWBORDER, value);
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
	 * Type: Boolean Range value: 0-1 Description : Whether to add 0 padding at
	 * the end of decimal numbers? For example, if you set decimals as 2 and a
	 * number is 23.4. If forceDecimals is set to 1, FusionCharts will convert
	 * the number to 23. 40 (note the extra 0 at the end)
	 */
	public void setForceDecimals(String value) {
		setAttribute(PARAMETER_FORCEDECIMALS, value);
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
	 * Type: Number Range value: 0-100 Description : Helps you specify alpha for
	 * the loaded background SWF.
	 */
	public void setBgSWFAlpha(String value) {
		setAttribute(PARAMETER_BGSWFALPHA, value);
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
	 * Type: String Range value: Description : The entire chart can now act as a
	 * hotspot. Use this URL to define the hotspot link for the chart. The link
	 * can be specified in FusionCharts Link Format.
	 */
	public void setClickURL(String value) {
		setAttribute(PARAMETER_CLICKURL, value);
	}

	/**
	 * Type: String Range value: Character Description : This option helps you
	 * specify the character to be used as the decimal separator in a number.
	 */
	public void setDecimalSeparator(String value) {
		setAttribute(PARAMETER_DECIMALSEPARATOR, value);
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
	 * Type: String Range value: Description : Caption of the chart.
	 */
	public void setCaption(String value) {
		setAttribute(PARAMETER_CAPTION, value);
	}

	/**
	 * Type: Boolean Range value: 0/1 Description : Whether to show tool tip on
	 * chart?
	 */
	public void setShowToolTip(String value) {
		setAttribute(PARAMETER_SHOWTOOLTIP, value);
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
	 * Type: Number Range value: In Pixels Description : Amount of empty space
	 * that you want to put on the right side of your chart. Nothing is rendered
	 * in this space.
	 */
	public void setChartRightMargin(String value) {
		setAttribute(PARAMETER_CHARTRIGHTMARGIN, value);
	}

	/**
	 * Type: Number Range value: 39203.0 Description : FusionCharts v3
	 * introduces the concept of Color Palettes. Each chart has 5 pre-defined
	 * color palettes which you can choose from. Each palette renders the chart
	 * in a different color theme. Valid values are 1-5.
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
	 * Type: Boolean Range value: 0/1 Description : Sets the configuration
	 * whether data values would be displayed along with the data plot on chart.
	 */
	public void setShowValues(String value) {
		setAttribute(PARAMETER_SHOWVALUES, value);
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
	 * Type: Boolean Range value: 0/1 Description : This configuration
	 * determines whether the numbers displayed on the chart will be formatted
	 * using commas, e.g., 40,000 if formatNumber='1 ' and 40000 if
	 * formatNumber='0 '
	 */
	public void setFormatNumber(String value) {
		setAttribute(PARAMETER_FORMATNUMBER, value);
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

	public void setShowFCMenuItem(String value) {
		setAttribute(PARAMETER_SHOWFCMENUITEM, value);
	}
}
