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
<td height="23px" align="right"><b>流水号:</b></td>
<td height="23px" align="left"><b><s:property value="assets.valueUnit"/></b></td>
</tr>
<tr>
<td height="23px" align="right"><b>资产名称:</b></td>
<td height="23px" align="left"><b><s:property value="assets.name"/></b></td>
</tr>
<tr>
<td align="center"  colspan="2">
<img src="<%=request.getContextPath()%>/barcode?msg=<s:property value="assets.codeId"/>&type=code39" height="40px" width=170px/>
</td>
</tr>
<tr>
<td align="right" >
<b>采购日期:</b>
</td>
<td align="left">
<b><s:date name='assets.buyDate' format='yyyy-MM-dd'/></b>
</td>
</tr>
<tr>
<td align="right">
<b>保修年限:</b>
</td>
<td align="left">
<b><s:property value="assets.qualityTime"/>(月)</b>
</td>
</tr>
</table>                   
</body>
</html>