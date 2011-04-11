<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
  <head>
    <title>网络设备查找</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<link rel="stylesheet" media="all" type="text/css" href="<%=path%>/js/impromptu/examples.css">	
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-1.js"></script>
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-impromptu.js"></script>

	
	<script language="JavaScript" type="text/javascript">
		function initPage(){
			// 初始化下拉菜单选项
			MonitorDeviceTypeDAO.findAll(callbackDeviceType);
			MonitorSubnetService.findAll(callbackSubnet);
		}
		
		function callbackDeviceType(data){  //显示出设备类型
			   	dwr.util.removeAllOptions("deviceType");
			   	dwr.util.addOptions("deviceType",{'0':'--全部--'});
			   	dwr.util.addOptions("deviceType",data,"code","name");   
			   
		}
		function callbackSubnet(data){  //显示出分区类型
		   	dwr.util.removeAllOptions("monitorSubnet");
		 	dwr.util.addOptions("monitorSubnet",{'0':'--全部--'});
		   	dwr.util.addOptions("monitorSubnet",data,"id","name");   
		}
		function doSubmit(){
			var ip=document.getElementById("ip").value;
			if(ip.toString().length>0){
				if(!isValidIP(ip)) {
					alert("IP地址格式不正确！");
					return;
				}
			}
			
			var mac=document.getElementById("mac").value;
			if(mac.toString().length>0){
				if(!CheckMac(mac)) {
					alert("MAC地址格式不正确！");
					return;
				}
			}
			document.deviceQuery.submit();
			
		}

		
	</script>
  </head>

	<body onload="initPage()"  leftmargin="0" marginwidth="0">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="<%=path%>/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					网络设备查找
				</td>
			</tr>
		</table>
		<br/>
		<div style="position: absolute; overflow-x: hidden; overflow-y: hidden; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
		<s:form name="deviceQuery" action="deviceQuery.action" method="post" theme="simple">
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>IP地址：</b></td>
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="ip" name="monitorDevice.ip" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>MAC地址：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="mac" name="monitorDevice.mac" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>设备名称：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<s:textfield id="name" name="monitorDevice.name" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>类型：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<select id="deviceType" name="monitorDevice.monitorDeviceType.code" style="width:200px"></select>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="20%" bgcolor="#deebf1">
							<img src="<%=path%>/img/jiedian.gif"
								width="10" height="9" />
							<b>所属分区：</b></td>
					 
					<td width="80%" bgcolor="#FFFFFF">
						<select id="monitorSubnet" name="monitorSubnet.id" style="width:200px"></select>
					</td>
				</tr>
				<tr align="center" style="height: 25"  >
					<td height="25" colspan="2" align="center" nowrap="nowrap"
						bgcolor="#FFFFFF" style="padding-left: 120px;">
						<input  name=search type="button" class="mmBtn" onClick="doSubmit()" value=" 查找 ">
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
