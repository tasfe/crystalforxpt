<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" style="padding-top: 5px;">
			<tr>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;登&nbsp;&nbsp;录&nbsp;&nbsp;名: </td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.username" />
				</td>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;显示全名:
				</td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.truename" />
				</td>
			</tr>
			<tr>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;所在部门:
				</td>
				<td valign="top" nowrap bgcolor="#FFFFFF" style="padding-left: 0px; padding-top: 5px">
					<div class='DivOut'>
						<s:property value="user.department.name" />
					</div>
				</td>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;所属区域:
				</td>
				<td valign="top" nowrap bgcolor="#FFFFFF" style="padding-left: 0px; padding-top: 5px">
					<div class='DivOut'>
						<s:property value="user.location.name" />
					</div>
				</td>
			</tr>
			<tr>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:
				</td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.phone" />
				</td>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:
				</td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.cellphone" />
				</td>
			</tr>
			<tr>
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;电子邮箱地址:
				</td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.email" />
				</td>
				<!-- 
				<td width="16%" height="31" nowrap bgcolor="#EBF4F5" style="padding-top: 6px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;用户组别:
				</td>
				<td width="34%" nowrap bgcolor="#FFFFFF">
					<s:property value="user.usertype" />
				</td>
				 
				<td width="16%" nowrap bgcolor="#FFFFFF" >
				</td>-->
				<td width="50%" nowrap bgcolor="#FFFFFF" colspan="2">
				</td>
			</tr>
			<!--  
			<tr>
				<td width="16%" height="31" valign="top" nowrap bgcolor="#EBF4F5" style="padding-top: 8px">
					<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;员工工号:
				</td>
				<td colspan="3" nowrap bgcolor="#FFFFFF">
					<s:property value="user.id" />
				</td>
			</tr>
			-->
		</table>
	</body>
</html>