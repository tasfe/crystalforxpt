<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ip = (String)request.getAttribute("ip");
if(ip==null)ip="";
String ifIndex = (String)request.getAttribute("ifIndex");
if(ifIndex==null)ifIndex="";
%>
<html>
	<head>
		<title>接口历史流量</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/common.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/commonstyles.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/tabMenu.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/amchart.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/amchart.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
		<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>	
		<script type="text/javascript" src="<%=path%>/js/monitor/tools.js" ></script>	
		<script language="JavaScript" type="text/javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
	

		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorInterfaceCacheService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorVendorMacDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		
		<script type="text/javascript" src="<%=path%>/Charts/FusionCharts.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/fusionCharts.css">

<script type="text/javascript">
var ip ="<%=ip%>";
var ifIndex = "<%=ifIndex%>";
var unit = 0;//流量单位 0：kbps 1:Mbps
function initPage(){
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	var day = 1;//默认开始显示24小时的历史数据，时间间隔是5分钟
	if("" != start && "" != end){
		day = 0;
		MonitorDeviceService.deviceInterfaceDataHistory(ip,ifIndex,unit,day,start,end,callbackBuildXmlString); 
	}else{
		day = 1;
		MonitorDeviceService.deviceInterfaceDataHistory(ip,ifIndex,unit,day,start,end,callbackBuildXmlString);
	}
	dwr.engine.setAsync(false);//DWR设为同步执行	
	MonitorInterfaceCacheService.findByDeviceIp(ip,callbackInterfaceCaheList);	
	dwr.engine.setAsync(true);//DWR恢复异步执行
	dwr.util.setValue("ifIndex",ifIndex);  
	
		
}

//快捷显示接口流量历史数据 24小时/7天/30天
function setDeviceInterfaceHistory(day){
	MonitorDeviceService.deviceInterfaceDataHistory(ip,ifIndex,unit,day,"","",callbackBuildXmlString); 
}
 
//返回数据依次为 标题、时间（横坐标）、流量（竖坐标 ）
function callbackBuildXmlString(data){

	var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
	
	chart1.setDataXML(buildXMLString(data[4],data[5],data[0],data[1],data[2],data[3]));
	
	
	chart1.render("chartdiv");
	if(data[6]=="false"){
		alert("不存在此时间段的历史流量数据！");
	}
}


function buildXMLString(strCaption,strSubCaption,strLable,strValue1,strValue2,strValue3) {
	var strXML;
	var sunit = "Kbps";
	if(unit == 0)
		sunit = "Kbps";
	else
		sunit = "Mbps";
	strXML = "<chart caption='"+strCaption+"' compactDataMode='1' dataSeparator='|' paletteThemeColor='5D57A5' divLineColor='5D57A5' divLineAlpha='40' vDivLineAlpha='40'"
				+" subcaption='"+strSubCaption+"'"
				+"xAxisName = '日期(月-日)' "
				+"yAxisName = '流量("+sunit+")'"
				+"numberSuffix='"+sunit+"'"
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
	strXML += "<dataset seriesName='双向' >\n";
	strXML += strValue1;		  
	strXML += "</dataset>";
	strXML += "<dataset seriesName='接收' >\n";
	strXML += strValue2;		  
	strXML += "</dataset>";
	strXML += "<dataset seriesName='发送' >\n";
	strXML += strValue3;		  
	strXML += "</dataset>";
	strXML += "</chart>";
	return strXML;
}
								 


function doSearch(){
		var sip = document.getElementById("sip").value;
		ip = sip.toString().trim();
		
		if("" == ip){
			alert("请填写IP地址！");	
	 		return;
		}
		if(!isIPv4(ip)){
			alert("IP地址格式不正确，请重新填写！");
			document.getElementById("sip").focus();
			return;
		}
		var ifIndex =  document.getElementById("ifIndex").value;
		 
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
		
		MonitorDeviceService.deviceInterfaceDataHistory(ip,ifIndex,unit,0,start,end,callbackBuildXmlString); 
		
}
function changeIp(value){
		
		if("" == value){
			alert("请填写IP地址！");	
	 		return;
		}
		if(!isIPv4(value)){
			
			alert("IP地址格式不正确，请重新填写！");
			document.getElementById("sip").focus();
			return;
		}
		MonitorInterfaceCacheService.findByDeviceIp(value,callbackInterfaceCaheList);
}

function callbackInterfaceCaheList(data){
	if(null == data){
		alert("在接口Cache表里没有找到该设备的接口列表！");
		return;
	}
	dwr.util.removeAllOptions("ifIndex");
	dwr.util.addOptions("ifIndex",data,"interface_","description");   
	
}
function setUnit(obj){
	if(obj.value!=""){
		unit = obj.value;
	}
	
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
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看设备接口历史流量</td>
  </tr>
</table>

	<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
	 <tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader">
		 	设备接口历史流量
	 </td>
	 </tr>
			<tr>
			<td valign="top">
			<div id="snapBox">
				<a href="javascript:setDeviceInterfaceHistory(30)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-30days.gif" title="30 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceInterfaceHistory(7)">
				<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-7days.gif" title="7 天" class="alighnRight" border="0">
				</a>
				<a href="javascript:setDeviceInterfaceHistory(1)"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-live.gif" title="24小时" class="alighnRight" border="0"></a>
			</div>
			<div class="chart-disp-block"  align="center" >
				<div id="chartdiv" >
				This text is replaced by the Flash movie.
				</div>
			</div>
			
			
			<div align="center">
			 
			<table>
			<tr>
			<td class="tdLabel">IP地址</td>
			<td><input id="sip" type="text" name="ip" onchange="changeIp(this.value)" value="<%=ip %>"/></td>
			<td class="tdLabel">接口</td>
			<td> 
				<select id="ifIndex" name="ifIndex"  >
			</td>
			<td class="tdLabel">流量单位</td>
			<td>  
				<s:select 
	      			id="unit"
	      			list="#{0:'Kbps',1:'Mbps'}" 
	      			name="unit" 
	      			value="unit" 
	      			onchange="setUnit(this)" 
	      			theme="simple" 
	      			headerKey="" 
	      			headerValue="请选择"></s:select>
	      	</td>
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

