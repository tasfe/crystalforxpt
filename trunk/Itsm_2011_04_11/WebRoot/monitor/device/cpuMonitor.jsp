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
    
    <title>CPU测控</title>
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
					CPU测控
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
	   	 
	   		
	   		var chart1 = new FusionCharts("<%=path%>/Charts/RealTimeStackedArea.swf", "ChId1", "800", "400", "0", "1");  
		   //开始加载是没有数据的。 
		   chart1.setDataXML("<chart "
		   					+"caption='7*24小时CPU监测' "
		   					+"subCaption='轮询间隔30秒' "
		   					+"setAdaptiveYMin='0'"
		   					+"xAxisName='Time' "
	 						+"realTimeValuePadding='50'"
	 						+"bgColor='000000' "
	 						+"bgAlpha='100' "
	 						+"canvasBorderThickness='1'"
	 						+"canvasBorderColor='008040' "
	 						+"canvasBgColor='000000'"
	 						+"yAxisMaxValue='100'"
	 						+"decimals='0'"
	 						+"numdivlines='9'"
	 						+"numVDivLines='58'"
	 						+"numDisplaySets='60'"
	 						+"divLineColor='008040'"
	 						+"vDivLineColor='008040' "
	 						+"divLineAlpha='100' "
	 						+"chartLeftMargin='10'"
	 						+"baseFontColor='00dd00'"
	 						+"showRealTimeValue='1' "
	 						+"dataStreamURL='<%=path%>/monitorDevice/getRealTimeCpuData.action?ip="+ip+"'	"
	 						+"refreshInterval='30'  "
		   					+"numberSuffix='%' "
		   					+"labelStep='2'  "
		   					+"labelDisplay='Rotate' "
		   					+"slantLabels='1' "
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
		   					+"<dataset color='009900'  seriesName='"+ip+"' showValues='0' ></dataset>"
		   					+"<styles>"
		   					+"<definition>"
		   					+"<style type='font' name='captionFont' size='14'/>"
		   					+"</definition>"
		   					+"<application>"
		   					+"<apply toObject='Caption' styles='captionFont'/>"
		   					+"<apply toObject='Realtimevalue' styles='captionFont'/>"
		   					+"</application>"
		   					+"</styles>"
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
