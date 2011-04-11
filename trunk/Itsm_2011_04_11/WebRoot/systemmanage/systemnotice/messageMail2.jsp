<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    <title>IT Service Desk</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<link href="css/style.css" rel="stylesheet" type="text/css">
 </head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; border: 1px inset; overflow: hidden" onLoad="setTimeout('window.top.close()',12000)">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
	<tr>
		<td width="1%" height="22" align="center" background="images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
		<img src="images/modpass.gif" width="16" height="16"></td>
		<td width="99%" background="images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
		&nbsp;&nbsp;操作提示			
		</td>			
	</tr>			
</table>	
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:80%; width: 90%; padding-top: 5px;">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
<tr class="td-right-s">
<td width="30%" valign="top" bgcolor="#deebf1" style="padding:20px">工程师：
<p style="padding-left:20px">您的操作已经被系统成功执行！<br>您的支持将有助于我们改进工作,谢谢您的工作！</p>

</td>
</tr>
</table>
</div> 
</body>
</html>
