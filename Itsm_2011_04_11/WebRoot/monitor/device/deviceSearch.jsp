<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
  <head>
    <title>SNMP设备搜索</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<link rel="stylesheet" media="all" type="text/css" href="<%=path%>/js/impromptu/examples.css">	
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-1.js"></script>
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-impromptu.js"></script>

	
	<script language="JavaScript" type="text/javascript">
		var deviceScanTreadFlag = false;//判断是正在发现设备还是终止发现设备
		function doSubmit(){
			var startIp=document.getElementById("startIp").value;
			if(startIp==""){
					alert("起始IP地址不能为空！");
					return false;
			}
			if(!isValidIP(startIp)) {
					alert("起始IP地址格式不正确！");
					return;
			}
			var endIp=document.getElementById("endIp").value;
			if(endIp==""){
					alert("结束IP地址不能为空！");
					return false;
			}
			if(!isValidIP(endIp)) {
					alert("结束IP地址格式不正确！");
					return;
			}
			var readCommunity=document.getElementById("readCommunity").value;
			document.deviceSearch.submit();
			
		}

		
	</script>
  </head>

	<body leftmargin="0" marginwidth="0">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="<%=path%>/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					SNMP设备搜索
				</td>
			</tr>
		</table>
		<br/>
		<div style="position: absolute; overflow-x: hidden; overflow-y: hidden; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
		<s:form name="deviceSearch" action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="subnetId" name="subnetId"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>起始IP地址：</b></td>
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="startIp" name="startIp" value="192.168.1.1"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>结束IP地址：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="endIp" name="endIp" value="192.168.1.254"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>读Community：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="readCommunity"
							name="readCommunity" value="public" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr align="center" style="height: 25"  >
					<td height="25" colspan="2" align="center" nowrap="nowrap"
						bgcolor="#FFFFFF" style="padding-left: 120px;">
						<input  name=search type="button" class="mmBtn" onClick="doSubmit()" value=" 搜索 ">
						&nbsp;&nbsp;
						<input name="reset" type="reset" class="mmBtn" value=" 重置 ">
						&nbsp;&nbsp;
						<input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value=" 返回 ">
					</td>
				</tr>
			</table>
			<div id="newList">
			</div>
		</s:form>
		</div>
		
	</body>
</html>
