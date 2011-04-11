<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="../css/print.css" rel="stylesheet" type="text/css">
		<base target="_self">
</head>
<body>
<table align="center" cellpadding="0" cellspacing="0" border="0" height="60px">
<tr>
<td height="8px" colspan=2></td>
</tr>
<tr>
<td height="10px" colspan=2><b>资产编号：<s:property value="assetsChange.assetsBase.codeId"/></b></td>
</tr>
<tr>
<td height="10px"><b>资产名称：<s:property value="assetsChange.assetsBase.name"/></b></td>
</tr>
<tr>
<td height="10px"><b>资产型号：<s:property value="assetsChange.assetsBase.model"/></b></td>
</tr>
<tr>
<td height="10px"><b>变更日期：<s:date name="assetsChange.changeTime" format='yyyy-MM-dd'/></b></td>
</tr>
<tr>
<td height="10px"><b>责任部门：<s:property value="assetsChange.usersByChargeid.department.name"/></b></td>
</tr>
<tr>
<td height="10px"><b>使用人：<s:property value="assetsChange.usersByUserid.truename"/></b></td>
</tr>
</table>                   
</body>
</html>