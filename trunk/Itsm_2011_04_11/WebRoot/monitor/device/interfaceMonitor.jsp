<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String ip = (String)request.getAttribute("ip");
if(ip==null)ip="";
String ifIndex = (String)request.getAttribute("ifIndex");
if(ifIndex==null)ifIndex="";
String ifDescrDisp = (String)request.getAttribute("ifDescrDisp");
if(ifDescrDisp==null)ifDescrDisp="";
String devDescr = (String)request.getAttribute("devDescr");
if(devDescr==null)devDescr="";
String status = (String)request.getAttribute("status");
if(status==null)status="";
String preSendData = (String)request.getAttribute("preSendData");
if(preSendData==null)preSendData="0";
String preReceiveData = (String)request.getAttribute("preReceiveData");
if(preReceiveData==null)preReceiveData="0";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>接口测控</title>
    <script type="text/javascript" src="<%=path%>/Charts/FusionCharts.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/fusionCharts.css">
	<script language="javascript">
       var ip ="<%=ip%>";
	   		var ifIndex = "<%=ifIndex%>";
	   		var preSendData = "<%=preSendData%>";
	   		var preReceiveData = "<%=preReceiveData%>";
	   		
   </script>   

  </head>
  
   
	<body bgcolor="#ffffff">
	<table width='600' align='center' cellpadding='2' cellspacing='0'>
	  <tr>
	    <td align="center" class="textBold">接口测控:<%=ip%>, 接口:<%=ifIndex%></td>
	
	  </tr>
	  <tr>
	  <td align="center">
	    <div id="chart1div" align="center" class="text">
		 <p>&nbsp;</p>
		 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
	    </div>
	    <s:form method="post">
		<s:hidden id="preSendData" name="preSendData" ></s:hidden>
		</s:form>
	    <script type="text/javascript">
	    
	   		
	    	
	    	
	   		var chart1 = new FusionCharts("/Charts/RealTimeLine.swf", "ChId1", "800", "400", "0", "1");  
		   //开始加载是没有数据的。 
		   chart1.setDataXML("<chart "
		   					+"palette='3'"
		   					+"caption='7*24小时接口带宽' "
		   					+"subCaption='轮询间隔30秒' "
		   					+"dataStreamURL='/monitorDevice/getRealTimeInterfaceData.action?ip="+ip+"&ifIndex="+ifIndex+"&fluxType=流量'	"
		   					+"refreshInterval='30'  "
		   					+"numberSuffix='bps' "
		   				
		   					+"setAdaptiveYMin='1'"
		   					+"setAdaptiveSYMin='1'"
		   					+"xAxisName='Time' "
		   					+"showRealTimeValue='1' "
		   					+"realTimeValuePadding='50'"
		   					+"labelDisplay='Rotate' "
		   					+"slantLabels='1' "
	 						+"numDisplaySets='30'"
	 					
	 						+"labelStep='2'  "
	 						
	 						+"bgColor='000000' "
	 						+"bgAlpha='100' "
	 						+"canvasBorderThickness='1'"
	 						+"canvasBorderColor='008040' "
	 						+"canvasBgColor='000000'"
	 						+"yAxisMaxValue='1000'"
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
		   					+"<dataset color='ff5904'  seriesName='双向'  showValues='0' ></dataset>"
		   					+"<dataset color='ffff00'  seriesName='发送'  showValues='0' ></dataset>"
		   					+"<dataset color='008040'  seriesName='接收'  showValues='0' ></dataset>"
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
		   					+"</chart>"); 
		   chart1.render("chart1div"); 
		</script>
	  </td>
	   </tr>
	   <tr height='10'>
	   <td></td>
	   </tr>
	   
	   <tr>
	     <td align="center" class="text"> 
	     <p><strong><%=devDescr%>&nbsp;&nbsp;&nbsp;&nbsp;<%=ifDescrDisp%>&nbsp;&nbsp;&nbsp;&nbsp;<%=status%></strong></p>
		</td>
	   </tr>
	    
	</table>
	</body>
  
</html>
