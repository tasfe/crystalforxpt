<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>添加设备</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>	
		<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorVendorMacDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		<script language="JavaScript" type="text/javascript">
			function initPage(){
				// 初始化下拉菜单选项
			   	MonitorDeviceTypeDAO.findAll(callbackDeviceType);
			   	MonitorVendorMacDAO.findAll(callbackVendorMac);
			   	// 如果deviceId不空，则是更新设备，否则添加设备
			   	var a = "<s:property value="deviceId" />";
			   	if (typeof(a) != "undefined" && a.length > 0) { 
			   		document.getElementById("save").value="更新";
			   		document.getElementById("monitorDevice.ip").readonly="true";
			  	}else{
			  		document.getElementById("monitorDevice.readCommunity").value="public";
			  	}
			  	
			}
			function callbackDeviceType(data){  //显示出设备类型
			   	dwr.util.removeAllOptions("deviceType");
			   	// dwr.util.addOptions("deviceType",{'':'--请选择--'});
			   	dwr.util.addOptions("deviceType",data,"code","name");   
			   	var a = "<s:property value="monitorDevice.monitorDeviceType.code" />";
			   	if (typeof(a) != "undefined") { 
			  		dwr.util.setValue("deviceType",a);  
			  	}
			  	changeType();
			}
			function callbackVendorMac(data){  //显示出厂商MAC读法
			   	dwr.util.removeAllOptions("vendorMac");
			   	dwr.util.addOptions("vendorMac",{'':''});
			   	dwr.util.addOptions("vendorMac",data,"id","name");   
			   	var a = "<s:property value="monitorDevice.monitorVendorMac.id" />";
			   	if (typeof(a) != "undefined") { 
			  		dwr.util.setValue("vendorMac",a);  
			  	}
			}
			// true说明不含有此IP，添加入数据库
			var bCheckIp = true;
			function dataCheck(){  
				var ip=document.getElementById("monitorDevice.ip").value;
				if(ip==""){
					alert("IP地址不能为空！");
					return false;
				}
				if(document.getElementById("deviceType").value !="20"){//虚拟设备 不用验证IP地址 ,读Community
					if(!isValidIP(ip)) {
						alert("IP地址格式不正确！")
						return false;
					}
					var readCommunity=document.getElementById("monitorDevice.readCommunity").value;
					if(readCommunity==""){
						alert("读Community不能为空！");
						return false;
					}
				}
				
				
				return true;
			}
			function clearDescr() {
				document.getElementById("monitorDevice.description").value="";
				document.getElementById("monitorDevice.name").value="";
			}
			function readDevice() {
				if(!dataCheck())
					return;
				document.form.read.disabled=true;
				MonitorDeviceService.readDeviceInfo(document.getElementById("monitorDevice.ip").value,
					document.getElementById("monitorDevice.readCommunity").value,
					document.getElementById("vendorMac").value,
					callbackRead);
			}
			function callbackRead(data){
				document.form.read.disabled=false;
				if(data == null) {
					alert("设备描述及名称信息读取超时，请检查设备SNMP配置");
					return;
				}
				var description = data["description"];
				if(description.length>250){
					description = description.substring(0,250);
					alert("设备描述超过设定长度，将截取前250个字符！");
				}
					
     			dwr.util.setValue("monitorDevice.description",description);
     			dwr.util.setValue("monitorDevice.name",data["name"]); 
     			dwr.util.setValue("monitorDevice.mac",data["mac"]); 
			}
			
			var checkSubmitFlg = false;//防止页面的重复提交，刷新
		    function checkSubmit() {
		      if (checkSubmitFlg == true) {
		         return false;
		      }
		      checkSubmitFlg = true;
		      return true;
		    } 
			// 保存设备
			function saveDevice() {
				//if (checkSubmit()){
					if(!dataCheck())
						return false;
					
					dwr.engine.setAsync(false);//DWR设为同步执行
					MonitorDeviceService.findByIp(document.getElementById("monitorDevice.ip").value,callbackSave);
					dwr.engine.setAsync(true);//DWR恢复异步执行
					// 不含有此IP的设备的时候才保存
					if(document.getElementById("save").value=="更新") {
						document.form.action="deviceUpdate.action";
						document.form.submit();
					}
					else if(bCheckIp) {
						document.form.action="deviceAdd.action";
			       		document.form.submit();
					}else {
						alert("已包含此IP地址的设备，请勿重复添加");
					}
				//}
				
			}
			function callbackSave(data) {
				var list = eval(data);  
				if(list.length > 0) {
					bCheckIp=false;
				}else {
					bCheckIp=true;
				}
			}
			function changeType(){
				if(document.getElementById("deviceType").value =="20"){//虚拟设备就不能读取描述信息了
				 	document.form.read.disabled=true;
				}else{
					document.form.read.disabled=false;
				}
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
					添加/修改设备信息
				</td>
			</tr>
		</table>
		<s:form action="%{actionURI}.action" method="post" theme="simple" name="form">
		<s:hidden name="subnetId"></s:hidden>
		<s:hidden id="page" name="page"></s:hidden>
		<s:hidden id="pageSize" name="pageBean.pageSize"></s:hidden>
		<s:hidden name="monitorDevice.id"/>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>IP地址：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:if test="monitorDevice.id != null">
							<s:textfield readonly="true" id="monitorDevice.ip" name="monitorDevice.ip"
								cssStyle="width: 100%" onchange="clearDescr()"></s:textfield>
						</s:if>
						<s:else>
							<s:textfield id="monitorDevice.ip" name="monitorDevice.ip"
								cssStyle="width: 100%" onchange="clearDescr()"></s:textfield>
						</s:else>

					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>设备类型：</b>
					</td>
					 
					<td width="30%" bgcolor="#FFFFFF">
						<select id="deviceType" name="monitorDevice.monitorDeviceType.code" onchange="changeType()" ><s:hidden name="monitorSubnet.monitorSubnetType.code"/></select>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>MAC地址：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.mac"
							name="monitorDevice.mac" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>CPU OID：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.cpuOid"
							name="monitorDevice.cpuOid" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>读Community：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.readCommunity"
							name="monitorDevice.readCommunity" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>写Community：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.writeCommunity" name="monitorDevice.writeCommunity" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>设备描述：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.description" maxlength="250"
							name="monitorDevice.description" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>设备名称：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.name" name="monitorDevice.name"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>私有MAC地址读取方法：</b>
					</td>
					 
					<td width="30%" bgcolor="#FFFFFF">
						<select id="vendorMac" name="monitorDevice.monitorVendorMac.id"><s:hidden name="monitorSubnet.monitorVendorMac.id"/></select>
					</td>
				</tr>
				
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>备注1：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.note1" name="monitorDevice.note1" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>备注2：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorDevice.note2" name="monitorDevice.note2" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr align="center" style="height: 25">
					<td  colspan="2"   nowrap="nowrap" bgcolor="#FFFFFF"  >
						
						<input name="read" type="button" class="mmBtn" value=" 读取设备信息" onClick="readDevice()">
						&nbsp;&nbsp;
						<input name="save" id="save" type="button" class="mmBtn" value=" 保存 " onClick="saveDevice()">
						&nbsp;&nbsp;
						<input name="reset" type="reset" class="mmBtn" value=" 重置 ">
						&nbsp;&nbsp;
						<input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value=" 返回 ">
					</td>
				</tr>
			</table>
		</s:form>
<%--		<s:debug></s:debug>--%>
	</body>
</html>