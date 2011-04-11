<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>系统公告详细列表2</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body background="${pageContext.request.contextPath }/img/main.jpg"
		leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: hidden; background-repeat: repeat-x; padding: 4px; border: 1px inset">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					信息查看:
				</td>
			</tr>
		</table>

		<div>
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td valign="top" id="mainright" style="border: #b5d6e6 1px solid;">
						<table width="100%" height="95%" cellspacing="0" bgcolor="#b5d6e6">
							<s:iterator value="#request.personalContact" id="u">

								<tr>
									<td width="2%" height="10%" bgcolor="#FFFFFF">
										<center>
											姓&nbsp;&nbsp;&nbsp;&nbsp;名：
										</center>
									</td>
									<td width="19%" height="10%" bgcolor="#FFFFFF">
										<s:property value="#u.username" />
									</td>
								</tr>

								<tr>
									<td width="2%" height="10%" bgcolor="#FFFFFF">
										<center>
											关&nbsp;&nbsp;&nbsp;&nbsp;系:
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.customertype.name" />
									</td>
								</tr>
								<tr>
									<td width="2%" height="10%" bgcolor="#FFFFFF">
										<center>
											职&nbsp;&nbsp;&nbsp;&nbsp;务:
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.duties" />
									</td>
								</tr>


								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											公&nbsp;司&nbsp;名&nbsp;称:
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.companyName" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											个&nbsp;人&nbsp;网&nbsp;址:
										</center>
									</td>
									<td height="10%" valign="top" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.personalWebsite" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：
										</center>
									</td>
									<td height="10%" valign="top" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.phone" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											办&nbsp;公&nbsp;电&nbsp;话:
										</center>
									</td>
									<td width="10%" bgcolor="#FFFFFF">
										<s:property value="#u.officePhone" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											传&nbsp;&nbsp;&nbsp;&nbsp;真：
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.fax" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											邮&nbsp;&nbsp;&nbsp;&nbsp;箱：
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.email" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											地&nbsp;&nbsp;&nbsp;&nbsp;址：
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.address" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											邮&nbsp;&nbsp;&nbsp;&nbsp;编：
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.zipCode" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											公&nbsp;司&nbsp;网&nbsp;址:
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.website" />
									</td>
								</tr>

								<tr>
									<td height="10%" bgcolor="#FFFFFF">
										<center>
											备&nbsp;&nbsp;&nbsp;&nbsp;注：
										</center>
									</td>
									<td height="10%" width="20%" bgcolor="#FFFFFF">
										<s:property value="#u.remarks" />
									</td>
								</tr>


							</s:iterator>

							<tr>
								<td height="30" colspan="2" align="right" bgcolor="#FFFFFF">
									<table width="100" border="0" align="center" cellpadding="0"
										cellspacing="0" background="images/addnew002.gif">
										<tr style="cursor: hand;">
											<td>
												<img src="images/addnew001.gif">
											</td>
											<td
												onClick="javascript:window.location.href='<%=request.getContextPath()%>/AddressBookManage/listPersonalContact.action'">
												后&nbsp;&nbsp;退
											</td>
											<td align="right">
												<img src="images/addnew003.gif">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
