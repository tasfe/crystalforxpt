<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.combanc.itsm.pojo.Users"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<base target="_self">
<title>管理信息化系统 IT运维-选择</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a img{
	border:0px;}
-->
</style>
</head>
<body bgcolor="#E4E5E7">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td background="../images/wap/top_bg.jpg"><img src="../images/wap/logo.jpg" width="152" height="45"></td></tr>
<tr>
<td valign="top" bgcolor="#E4E5E7" align="center"><br/>
<table border="0" cellspacing="0" cellpadding="0" width="80%">
<tr>
<td width="50%" height="120" align="center"><a href="main.action"><h1>资产变更</h1></a></td>
</tr>
<tr>
<td>&nbsp;&nbsp;</td>
</tr>
<tr>
<td width="50%" height="120" align="center"><a href="inStorechoose.action"><h1>资产入库</h1></a></td>
</tr>
<tr>
<td>&nbsp;&nbsp;</td>
</tr>
<tr><%Users u=(Users)request.getSession().getAttribute("user"); %>
<td width="50%" height="120" align="center"><a href="../login.action?username=<%=u.getUsername() %>&password=<%=u.getPassword() %>&wap=wap"><h1>返回主菜单</h1></a></td>
</tr>
</table>
</td>
</tr>
</table>
</body>
</html>
