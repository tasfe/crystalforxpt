<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>编辑入网计算机</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript">
			function initPage(){
			}
			
			function saveComputer() {
				document.form.action="computerUpdate.action";
				document.form.submit();
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
					修改入网计算机信息
				</td>
			</tr>
		</table>
		<s:form action="%{actionURI}.action" method="post" theme="simple" name="form">
		<s:hidden name="monitorComputer.id"/>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>IP地址：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						 <s:property value="monitorComputer.ip"/>
					</td>
				</tr>
				
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>MAC地址：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:property value="monitorComputer.mac"/>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>上连设备：</b>
					</td>
					 
					<td width="30%" bgcolor="#FFFFFF">
						<a href="/monitorDevice/detail.action?deviceId=<s:property value='monitorComputer.monitorDevice.id'/>"><s:property value="monitorComputer.monitorDevice.ip"/></a>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>上连设备接口描述：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
					 	<s:property value="monitorComputer.interfaceDescription"/>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>计算机名：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:property value="monitorComputer.computerName"/>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>最后发现时间：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:date name="monitorComputer.discoveryTime"/>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>使用人：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorComputer.user" name="monitorComputer.user"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>地点：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorComputer.place" name="monitorComputer.place"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>部门：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorComputer.department" name="monitorComputer.department"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>备注1：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorComputer.note1" name="monitorComputer.note1" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" bgcolor="#deebf1">
						<img src="<%=path%>/img/jiedian.gif" width="10" height="9" />
						<b>备注2：</b>
					</td>
					 
					<td bgcolor="#FFFFFF">
						<s:textfield id="monitorComputer.note2" name="monitorComputer.note2" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr align="center" style="height: 25">
					<td  colspan="2"   nowrap="nowrap" bgcolor="#FFFFFF"  >
						
						 
						<input name="save" id="save" type="button" class="mmBtn" value=" 保存 " onClick="saveComputer()">
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