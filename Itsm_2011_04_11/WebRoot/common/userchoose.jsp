<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>用户资料</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

		<script type="text/javascript">

</script>
	</head>
	<body style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					用户查询:
				</td>
			</tr>

		</table>
		<table width="99%" border="0" align="center" cellpadding="2"
			cellspacing="1" bgcolor="#deebf1">
			<s:form action="userlist" method='post' theme="simple" namespace="/user">
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">
						姓名:&nbsp;
					</td>
					<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="user.truename" id="user.truename"
							cssStyle="width:120px" />
					</td>
					<td width="21%" align="center" nowrap bgcolor="#deebf1"
						style="padding-right: 10px">

						<input type="submit" style="height: 20px" class="mmBtn"
							value="搜 索" />

					</td>
				</tr>
				<tr>


				</tr>
			</s:form>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">

			</table>
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="1" bgcolor="#b5d6e6">
				<tr bgcolor="#FFFFFF">


					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						用户全名
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						部门
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						地理位置
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						&nbsp;
					</td>
				</tr>

				<s:iterator value="pageBean.list" var="users">
					<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
						onMouseOut="this.bgColor='#FFFFFF'">


						<td align="center">
							<s:property value="truename" />
						</td>
						<td align="center">
							<s:property value="department.name" />
						</td>
						<td align="center">
							<s:property value="location.name" />
						</td>
						<td align="center">
							<input type="radio" name="code" id="code" value=""
								onClick="window.parent.document.getElementById('users.name').value= '<s:property value='truename' />';window.parent.document.getElementById('users.id').value= '<s:property value='id' />'" />
						</td>
					</tr>
				</s:iterator>

			</table>
			
		</div>
	</body>
</html>
