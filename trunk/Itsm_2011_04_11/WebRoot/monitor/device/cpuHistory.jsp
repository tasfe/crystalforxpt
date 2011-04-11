<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>CPU利用率</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/common.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/commonstyles.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/tabMenu.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/amchart.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/amchart.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
		<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>
		<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>	
		<script language="JavaScript" type="text/javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
	

		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorVendorMacDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		
		<script type="text/javascript" src="<%=path%>/Charts/FusionCharts.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/fusionCharts.css">

<script type="text/javascript">
var ip = "";
function initPage(){
	ip = document.getElementById("ip").value;
	var deviceType = document.getElementById("deviceType").value;
	
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	if("" != start && "" != end){
		if(deviceType==6){
			MonitorDeviceService.deviceDataHistory(ip,2,start,end,callbackBuildXmlString1);
			MonitorDeviceService.deviceDataHistory(ip,3,start,end,callbackBuildXmlString2);
		}else{
			MonitorDeviceService.deviceDataHistory(ip,1,start,end,callbackBuildXmlString);
		}
			 
	}else{
		if(deviceType==6){
			MonitorDeviceService.deviceDataHistory(ip,1,2,callbackBuildXmlString1);
			MonitorDeviceService.deviceDataHistory(ip,1,3,callbackBuildXmlString2);
		}else{
			MonitorDeviceService.deviceDataHistory(ip,1,1,callbackBuildXmlString);
		}
		
	}
		
		
	
	
		
}

//快捷显示CPU历史 24小时/7天/30天
function setDeviceHistory(day){
	MonitorDeviceService.deviceDataHistory(ip,day,1,callbackBuildXmlString);
}
//快捷显示温湿度历史 24小时/7天/30天
function setDeviceHistory1(day){
	MonitorDeviceService.deviceDataHistory(ip,day,2,callbackBuildXmlString1);
	MonitorDeviceService.deviceDataHistory(ip,day,3,callbackBuildXmlString2);
} 
//CPU
function callbackBuildXmlString(data){
	var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
	chart1.setDataXML(buildXMLString("CPU利用率历史趋势图",data[0],data[1]));
	chart1.render("chart1divZoomLine");
}
//温度
function callbackBuildXmlString1(data){
	var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
	chart1.setDataXML(buildXMLString("温度历史趋势图",data[0],data[1]));
	chart1.render("chart1divZoomLine1");
}
//湿度
function callbackBuildXmlString2(data){
	var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
	chart1.setDataXML(buildXMLString("湿度历史趋势图",data[0],data[1]));
	chart1.render("chart1divZoomLine2");
}

function buildXMLString(strCaption,strLable,strValue) {
	var strXML;
	strXML = "<chart caption='"+strCaption+"' compactDataMode='1' dataSeparator='|' paletteThemeColor='5D57A5' divLineColor='5D57A5' divLineAlpha='40' vDivLineAlpha='40'"
				+"numberSuffix='%'"
				+"btnResetChartTitle='重置'"
				+"btnZoomOutTitle='缩小'"
				+"btnSwitchtoZoomModeTitle='切换到缩放模式'"
				+"btnSwitchToPinModeTitle='切换到比较模式'"
				+"zoomOutMenuItemLabel='放大'"
				+"resetChartMenuItemLabel='重置'"
				+"zoomModeMenuItemLabel='缩放模式'"
				+"pinModeMenuItemLabel='比较模式'"
				+">\n<categories>"; 
	strXML += strLable;			
	strXML += "</categories>\n";
	strXML += "<dataset seriesName='"+ip+"' >\n";
	strXML += strValue;		  
	strXML += "</dataset>";
	strXML += "</chart>";
	return strXML;
}
								 
function doSearch(){
		var sip = document.getElementById("sip").value;
		ip = sip;
		if("" == ip){
			alert("请填写IP地址！");	
	 		return;
		}
		if(!isIPv4(ip)){
			alert("IP地址格式不正确，请重新填写！");
			document.getElementById("ip").focus();
			return;
		}
		var start = document.getElementById("start").value;
		if("" == start){
			alert("请确定开始时间！");	
	 		return;
		}
		var end = document.getElementById("end").value;
		if("" == end){
			alert("请确定结束时间！");	
	 		return;
		}
		
		form.action="cpuHistory.action";
		form.submit();
		
		//var deviceType = document.getElementById("deviceType").value;
		//if(deviceType==6){
		//	MonitorDeviceService.deviceDataHistory(ip,2,start,end,callbackBuildXmlString1);
		//	MonitorDeviceService.deviceDataHistory(ip,3,start,end,callbackBuildXmlString2);
		//}else{
		//	MonitorDeviceService.deviceDataHistory(ip,1,start,end,callbackBuildXmlString);
			 
		//}
		
}

</script>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>

<body onload="initPage()">

<s:form name="form" method="post" > 
<s:hidden id="ip" name="monitorDevice.ip" ></s:hidden>
<s:hidden id="deviceType" name="monitorDevice.monitorDeviceType.code" ></s:hidden>
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看CPU/温湿度历史</td>
  </tr>
</table>

	<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
	 <tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader">
		 <s:if test="monitorDevice.monitorDeviceType.code==6">
			 温湿度历史
		 </s:if> 
		 <s:else>
		 	CPU历史
		 </s:else>
	 </td>
	 </tr>
			<tr>
			<td valign="top">
			
			<s:if test="monitorDevice.monitorDeviceType.code==6"><!-- 温湿度监控设备 -->
			<div id="snapBox">
				<a href="javascript:setDeviceHistory1(30)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-30days.gif" title="30 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceHistory1(7)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-7days.gif" title="7 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceHistory1(1)"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-live.gif" title="24小时" class="alighnRight" border="0"></a>
			</div>
			<div class="chart-disp-block"  align="center" >
				 <br/>
				 <div id="chart1divZoomLine1" >
				  This text is replaced by the Flash movie.
				  </div>
				  <br/>
				  <div id="chart1divZoomLine2" >
				  This text is replaced by the Flash movie.
				  </div>
				 <br/>
			</div>	 
			</s:if>
			<s:else>
			<div id="snapBox">
				<a href="javascript:setDeviceHistory(30)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-30days.gif" title="30 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceHistory(7)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-7days.gif" title="7 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceHistory(1)"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-live.gif" title="24小时" class="alighnRight" border="0"></a>
			</div>
			<div class="chart-disp-block"  align="center" >
				<div id="chart1divZoomLine" >
				This text is replaced by the Flash movie.
				</div>
			</div>
			</s:else>
			
			<div align="center">
			 
			<table>
			<tr>
			<td class="tdLabel">IP地址</td>
			<td><input id="sip" type="text" name="ip" value="<s:property value="monitorDevice.ip" escape="false"/>"/></td>
			<td class="tdLabel">开始时间</td>
			<td><input id="start" name="start" value="<s:property value="%{#request.start}" escape="false"/>" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'end\')||\'2020-10-01\'}'})"/></td>
			<td class="tdLabel">结束时间</td>
			<td><input id="end" name="end" value="<s:property value="%{#request.end}" escape="false"/>"  class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})"/></td>
			<td>
			 
				<input type="button" class="mmBtn" value="查询" onClick="doSearch()">
			  	
			</td>
			
			</table>
			
			 
		</div>	 
			</td>
	</tr>
	</table>

</s:form>
</body>
</html>

