<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String ip = (String)request.getAttribute("ip");
if(ip==null)ip="";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>温湿度测控</title>
    <script type="text/javascript" src="<%=path%>/Charts/FusionCharts.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/fusionCharts.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" >

  </head>
  
   
	<body bgcolor="#ffffff" leftmargin="0" marginwidth="0">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="<%=path%>/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					温湿度测控
				</td>
			</tr>
	</table>
	<table width='600' align='center' cellpadding='2' cellspacing='0'>
	  <tr>
	    <td align="center" class="textBold"></td>
	
	  </tr>
	  <tr>
	  <td align="center">
	    <div id="chart1div" align="center" class="text">
		 <p>&nbsp;</p>
		 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
	    </div>
	
	    <script type="text/javascript">
	    	
	    	var ip ="<%=ip%>";
	   		
	   		var chart1 = new FusionCharts("<%=path%>/Charts/RealTimeLineDY.swf", "ChId1", "800", "400", "0", "1");  
		   //开始加载是没有数据的。 
		   chart1.setDataXML("<chart "
		   					+"palette='3'"
		   					+"caption='7*24小时温湿度监测' "
		   					+"subCaption='轮询间隔30秒' "
		   					+"dataStreamURL='<%=path%>/monitorDevice/getRealTimeAkcpData.action?ip="+ip+"'	"
		   					+"refreshInterval='3'  "
		   					+"numberSuffix='℃' "
		   					+"SNumberSuffix='%' "
		   					+"setAdaptiveYMin='1'"
		   					+"setAdaptiveSYMin='1'"
		   					+"xAxisName='Time' "
		   					+"showRealTimeValue='1' "
		   					+"realTimeValuePadding='50'"
		   					+"labelDisplay='Rotate' "
		   					+"slantLabels='1' "
	 						+"numDisplaySets='30'"
	 						+"PYAxisMinValue='10'"
	 						+"PYAxisMaxValue='50'"
	 						+"SYAxisMinValue='0'"
	 						+"SYAxisMaxValue='100'"
	 						+"labelStep='2'  "
	 						
	 						+"bgColor='000000' "
	 						+"bgAlpha='100' "
	 						+"canvasBorderThickness='1'"
	 						+"canvasBorderColor='008040' "
	 						+"canvasBgColor='000000'"
	 						+"yAxisMaxValue='100'"
	 						+"decimals='0'"
	 						+"numdivlines='9'"
	 						+"numVDivLines='28'"
	 						+"divLineColor='008040'"
	 						+"vDivLineColor='008040' "
	 						+"divLineAlpha='100' "
	 						+"chartLeftMargin='10'"
	 						+"baseFontColor='00dd00'"
	 						+"toolTipBgColor='000000'"
	 						+"toolTipBorderColor='008040' "
	 						+"baseFontSize='11' "
	 						+"showAlternateHGridColor='0' "
	 						+"legendBgColor='000000'"
	 						+"legendBorderColor='008040'"
	 						+"showLabels='1'"
	 						+"plotGradientColor=''"
	 						+"showLegend='1'"
	 						+"showShadow='1'"
	 						+"showPlotBorder='0'"
	 						+"plotBordercolor='FFFFFF'"
		   					+">"
		   					+"<categories></categories>"
		   					+"<dataset color='00dd00'  seriesName='温度' parentYAxis='P' showValues='0' ></dataset>"
		   					+"<dataset color='ff5904'  seriesName='湿度' parentYAxis='S' showValues='0' ></dataset>"
		   					+"<styles>"
		   					+"<definition>"
		   					+"<style type='font' name='captionFont' size='14'/>"
		   					+"<style type='font' name='subCaptionFont' size='12'/>"
		   					+"</definition>"
		   					+"<application>"
		   					+"<apply toObject='Caption' styles='captionFont'/>"
		   					+"<apply toObject='Realtimevalue' styles='captionFont'/>"
		   					+"<apply toObject='subCaption' styles='subCaptionFont'/>"
		   					+"</application>"
		   					+"</styles>"
		   					+"<trendlines>"
		   					+"<line parentYAxis='P' startValue='25' displayValue='温度' thickness='1' color='0372AB' dashed='1'/>"
		   					+"<line parentYAxis='S' startValue='50' displayValue='湿度' thickness='1' color='DF8600' dashed='1'/>"
		   					+""
		   					+"</trendlines>"
		   					
		   					+"</chart>"); 
		   chart1.render("chart1div"); 
		</script>
	  </td>
	   </tr>
	   <tr height='10'>
	   <td></td>
	   </tr>
	   
	   <tr>
	     <td align="center">&nbsp;</td>
	   </tr>
	    
	</table>
	</body>
  
</html>
