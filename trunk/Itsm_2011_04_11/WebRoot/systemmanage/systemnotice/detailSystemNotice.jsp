<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>



<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
	<link href="css/style.css" rel="stylesheet" type="text/css">
  </head>
  
  <body background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
  <table cellspacing=0 cellpadding=0 border=0 width="100%">
 	 <tr>
   <td width="2%" height="22" align="center" background="images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">公告详细情况:</td>
  </tr>
</table>

<div>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td valign="top" id="mainright">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<tr>
	<td>
		<table width="100%" cellpadding="1" cellspacing="0" height="100%">
		<tr>
		<td style="padding: 10px; line-height: 10px" bgcolor="#FFFFFF">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
				<tr>
				<td height="99%" colspan="2">
					<table width="100%" height="95%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
					<s:iterator value="#request.syemNotice" id="u">
					<tr>
					<td width="2%" height="10%" align="right" bgcolor="#EBF4F5">发布者：</td>
					<td width="19%" height="10%" bgcolor="#FFFFFF"><s:property value="#u.authorName" /></td>
					</tr>
					
					<tr>
					<td height="10%" align="right" bgcolor="#EBF4F5">公告标题：</td>
					<td height="10%" width="20%" bgcolor="#FFFFFF"><s:property value="#u.title" /></td>
					</tr>
					
					<tr>
					<td height="70%" align="right" bgcolor="#EBF4F5">公告内容：</td>
					<td height="70%" valign="top" width="20%" bgcolor="#FFFFFF"><s:property value="#u.content" /></td>
					</tr>
					
					<tr>
					<td height="20%" align="right" bgcolor="#EBF4F5">日期：</td>
					<td width="20%" bgcolor="#FFFFFF"><s:property value="#u.date.toString().substring(0,16)"/></td>
					</tr>
					
						<tr>
					<td height="10%" align="right" bgcolor="#EBF4F5">上传文件： </td>
					<td height="10%" width="20%" bgcolor="#FFFFFF">
						<!--<a href="<%=path%>/SystemNotice/dlpsUI.action?dlFileName=<s:property value='accessoryList.get(0).name'/>">
						<s:property value="accessoryList.get(0).trueName" />
						</a>
						-->
						<a href="<%=path%>/SystemNotice/dlpsUI.action?dlFileName=<s:property value='accessoryList.get(0).name'/>">
  						<s:property value="fileName" /></a>
						</td>
					</tr>
					
					
					</s:iterator>
					
					<tr>
  					<td colspan="2" height="30" align="right" bgcolor="#FFFFFF">
					<table width="80" border="0" cellpadding="0" cellspacing="0" background="images/addnew002.gif">    		
					<tr style="cursor:hand;">
		     		<td><img src="images/addnew001.gif"></td>
		      		<td onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/findNowNotices.action'">后退</td>
		      		<td align="right"><img src="images/addnew003.gif"></td>
		      		</tr>
		   			</table>
					</td>
					</tr>
					</table>
				</table>
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
