<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>项目计划类别</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	<style type="text/css">
<!--
body {
	margin-top: 5px;
}
-->
</style></head>
	<body leftmargin="0" marginwidth="0">
		<s:form id="form" name="form" action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="type" name="type"></s:hidden>
			<s:if test="actionURI=='save'">
				<input id="serviceCategory.pid" name="serviceCategory.pid" type="hidden" value="${pid}"></input>
				<input id="serviceCategory.type" name="serviceCategory.type" type="hidden" value="${type}"></input>
			</s:if>
			<s:else>
				<s:hidden id="serviceCategory.id" name="serviceCategory.id"></s:hidden>
				<s:hidden id="serviceCategory.pid" name="serviceCategory.pid"></s:hidden>
				<s:hidden id="serviceCategory.type" name="serviceCategory.type"></s:hidden>
				<s:hidden id="serviceCategory.code" name="serviceCategory.code"></s:hidden>
			</s:else>
			<table width="80%" height="" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr height="28">
					<td width="12%" align="right" nowrap bgcolor="#deebf1">
						<b>中文名称：</b>					</td>
					<td width="37%" bgcolor="#FFFFFF">
						<s:textfield id="serviceCategory.itemZh" name="serviceCategory.itemZh" cssStyle="width: 100%"/>				  </td>
					<td width="12%" align="center" nowrap bgcolor="#deebf1">
						<b>英文名称：</b>					</td>
					<td width="39%" bgcolor="#FFFFFF">
						<s:textfield id="serviceCategory.item" name="serviceCategory.item" cssStyle="width: 100%"/>				  </td>
				</tr>
		  </table>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr height="28">
					<td height="30" colspan="4" nowrap="nowrap" bgcolor="#FFFFFF" class="list_btm" style="text-align: center; border: 1">
						&nbsp;&nbsp;
						<input type="submit" value=" 保存 " class="mmBtn"/>
						&nbsp;&nbsp;
						<input type="reset" value=" 重置 " class="mmBtn" />
						&nbsp;&nbsp;
						<input type="button" value=" 返回 " onClick="window.location='list.action?type=${type}&pid=${pid}'" class="mmBtn" />
	  </td>
	</tr>
</table>

		</s:form>
</body>
</html>