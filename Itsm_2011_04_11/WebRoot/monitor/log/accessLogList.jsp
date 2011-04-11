<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<title>list</title>
		 
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css"  >
		
		<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>	
		<script type="text/javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>

		<script type="text/javascript">
		
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}else {  
        	return false;   
   		}   
	}

	function init() {
		MonitorSubnetService.findAll(callbackSubnet);
		MonitorSubnetService.getSelectedDeviceBySubnet(-1,callbackInitDeviceList)
	}

	function callbackSubnet(data) {
		dwr.util.removeAllOptions("subnet");
		dwr.util.addOptions("subnet", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("subnet", data, "id", "name");
	}
	function change(){
		var subnetId = document.getElementById("subnet").value;
		MonitorSubnetService.getSelectedDeviceBySubnet(subnetId,callbackInitDeviceList)
	}
	function callbackInitDeviceList(data){
		dwr.util.removeAllOptions("upDevice");
		dwr.util.addOptions("upDevice", [ {
			ip : 'all',
			ip : '--全部--'
		} ], "ip", "ip");
		dwr.util.addOptions("upDevice", data, "ip", "ip");
	}
	function keyNameChange(){
		document.getElementById("keyValue").value="";
	}
	
 
 
	function doSearch(){
		
		
		var keyValue = document.getElementById("keyValue").value;
	
		if("" != keyValue){
			var keyName = document.getElementById("keyName").options[document.getElementById("keyName").selectedIndex].text;
			if("IP"==keyName){
				if(!isIPv4(keyValue)){
					alert("IP地址格式不正确！");
					return;
				}
			}
			if("MAC"==keyName){
				if(!CheckMac(keyValue)){
					alert("mac地址格式不正确！Mac地址以空格,-,:分开"); 
					return;
				}
			}
			
		}
		form1.submit();
	}
</script>
	</head>
	<body onLoad="init()" style="overflow:hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					接入日志
				</td>
			</tr>
		</table>
		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
		  <table width="100%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
				<tr>
				  <td valign="top" bgcolor="#FFFFFF">

					<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form name="form1" action="queryAccessLog" method="post" theme="simple"
								namespace="/monitorLog">
								<tr>
									<td align="right" width="10%" nowrap bgcolor="#deebf1">选择日期</td>
									<td width="15%" bgcolor="#ffffff" style="padding-right: 10px">
										<input id="start" name="start" class="Wdate" type="text" language="javascript" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									</td>
									<td align="right" width="8%" nowrap bgcolor="#deebf1">选择分区：</td>
									<td width="8%" bgcolor="#ffffff" style="padding-right: 10px">
										<select id="subnet" name="subnetId" style="width: 100%; height:18px;" onchange="change()"></select>
								 	</td>
								 	<td align="right" width="10%" nowrap bgcolor="#deebf1">选择上连设备</td>
									<td width="15%" bgcolor="#ffffff" style="padding-right: 10px">
										<select id="upDevice" name="upDeviceIp" style="width: 100%; height:18px;"></select>
									</td>
									<td align="right" width="10%" nowrap bgcolor="#deebf1">关键字</td>
									<td width="25%" bgcolor="#ffffff" style="padding-right: 10px">
										<select id="keyName" name="keyName" style="width: 20%; height:18px;" onchange="keyNameChange()">
										<option>IP</option>
										<option>MAC</option>
										</select>
										<input type="text" id="keyValue" name="keyValue" >
									</td>
									<tags:button code="search" menu="31">
									<td width="21%" align="center" bgcolor="#deebf1" style="padding-right: 10px">
									
									<input name="button" type="button" class="mmBtn" onClick="doSearch()" value="查找">
									
									</td>
									</tags:button>
								</tr>
							</s:form>
					  </table>
					
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
					   	<tr>
							<td height="10" style="font-size: 12px; color: #333333; font-weight: bold;"></td>
						  </tr>
						  <tr>
							  <td height="20" style="font-size: 12px; color: #333333; font-weight: bold;">
								  <table cellspacing="0" cellpadding="0" border="0" width="100%">

									  <tr>
										  <td width="100%" height="30" style="padding-top: 10px">
											  <img src="../images/main20100521dot04.gif"><b>接入日志:</b>&nbsp;
									    </td>
									    <td width="39%" align="right"></td>
											
									</table>                                            
 							  </td>
						  </tr>
						<tr>
						 	<td valign="top" background="../img/Separator.gif" colspan="2" height="5px"></td>
						</tr>
						<tr>
							<td valign="top" colspan="2" height="6"></td>
						</tr>
						<tr>
							<td valign="top" colspan="2">
								<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 序号 </th>
									<th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> IP</th>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> MAC </th>
									<th width="3%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 上连设备 </th>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 接口描述 </th>
								   <th width="5%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 日期 </th>
								    <th width="5%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 首次发现时间 </th>
									<th width="3%" nowrap background="../images/main20100521_58.gif"class="alllisttitle"> 最后发现时间</th>
									</tr>
									<%int i=1; %>
									<s:iterator value="pageBean.list" id="accessLog">
									<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
									 <td height="26" align="center" > <%=i++ %></td>
							          <td align="center" ><s:property value="ip"/>  </td>
							          <td align="center" ><s:property value="mac"/></td>
							          <td align="center" ><s:property value="upDeviceIp"/></td>
							          <td align="center" ><s:property value="interfaceDescription"/>   </td>
							          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd"/></td>
							          <td align="center" ><s:date name="firstTime" format="HH:mm:ss"/></td>
							          <td align="center" ><s:date name="lastTime" format="HH:mm:ss"/></td>
							         
							         </tr>
							         </s:iterator>
									</table>
								</td>
							</tr>
							
						</table>
						</td>
					</tr>
					</table>
					<s:form name="form" method="post" action="%{actionURI}.action" theme="simple">
		  			<s:hidden id="start" name="start"></s:hidden>
		  			<s:hidden id="subnetId" name="subnetId"></s:hidden>
		  			<s:hidden id="upDeviceIp" name="upDeviceIp"></s:hidden>
		  			<s:hidden id="keyName" name="keyName"></s:hidden>
		  			<s:hidden id="keyValue" name="keyValue"></s:hidden>
					<s:hidden id="page" name="page" value="1"></s:hidden>
					<s:hidden id="pageSize" name="pageBean.pageSize"></s:hidden>
					</s:form>
				<jsp:include page="/common/page.jsp" />
			</div>
	</body>
</html>
