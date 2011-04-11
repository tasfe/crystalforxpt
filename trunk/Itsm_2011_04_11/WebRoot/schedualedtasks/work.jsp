<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
	<tr>
		<th width="15%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 工单号 </th>
		<th width="20%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 任务类别 </th>
		<th width="40%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 摘要 </th>
		<th width="15%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 申请人 </th>
		<th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 查看 </th>
	</tr>
	<s:iterator value="schedualedTaskDetails" var="schedualedTaskDetail">
		<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = '#e3f0f7'" onMouseOut="this.bgColor = '#FFFFFF'">
			<td height="30" align="center">
				<s:property value="schedualedTasks.proNo" />
			</td>
			<td align="center">
				<s:property value="schedualedTasks.serviceCategory.itemZh" />
			</td>
			<td align="center">
				<s:property value="schedualedTasks.keyWord" />
			</td>
			<td align="center">
				<s:property value="schedualedTasks.application" />
			</td>
			<td align="center" nowrap>
				<a href="#" onClick="window.parent.location.href='show.action?schedualedTaskDetail.id=<s:property value="id" />'"><img src='../img/viewdetail.gif' border=0 width=18
						height=18>
				</a>
			</td>
		</tr>
	</s:iterator>
</table>
</body>