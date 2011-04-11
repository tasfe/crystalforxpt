<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"><style type="text/css">
<!--
body {
	margin-top: 10px;
	margin-bottom: 0px;
}
-->
</style></head>
  <body>
  <table width="350" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="11%" align="left" background="images/listconframe_04.gif"><img src="images/listconframe_03.gif" width="38" height="31" alt="" /></td>
      <td width="19%" align="left" background="images/listconframe_04.gif" style="font-size:14px; color:#666666; font-weight:bold; padding-top:6px;">密码修改</td>
      <td width="6%" align="left" background="images/listconframe_07.gif"><img src="images/listconframe_06.gif" width="19" height="31" alt="" /></td>
      <td width="50%" align="left" background="images/listconframe_07.gif" style="padding-top:6px; font-size:14px;">&nbsp;</td>
      <td width="14%" align="right" background="images/listconframe_07.gif"><img src="images/listconframe_09.gif" width="17" height="31" alt="" /></td>
    </tr>
  </table>
  <table width="350" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#f4fbff">
    <tr>
      <td width="10" height="10"><img src="images/listconframe_11.gif" width="10" height="10" alt="" /></td>
      <td height="10"><img src="images/space.gif" width="1" height="1" /></td>
      <td width="10" height="10"><img src="images/listconframe_15.gif" width="10" height="10" alt="" /></td>
    </tr>
    <tr>
      <td width="10" background="images/listconframe_11.gif">&nbsp;</td>
      <td height="6">
      <form action="changePwd.action" method="post">
  		<font color="red">
  			<s:fielderror/>
  			<s:actionmessage/>
  		</font>
	    <table width="100%" border=0 cellpadding="2" cellspacing="1" bgcolor="#CCCCCC">
	    	<tr><td align="center" bgcolor="#FFFFFF">原始密码：<input type="password" name="user.password"/></td></tr>
	    	<tr><td align="center" bgcolor="#FFFFFF">新&nbsp;&nbsp;密&nbsp;&nbsp;码：<input type="password" name="newPwd"/></td></tr>
	    	<tr><td align="center" bgcolor="#FFFFFF">密码确认：<input type="password" name="confirm"/></td></tr>
	    	<tr>
	    		<td align="center" bgcolor="#FFFFFF"><input type="submit" value="修改密码"/></td>
	    	</tr>
	    </table>
    </form></td>
      <td width="10" background="images/listconframe_17.gif">&nbsp;</td>
    </tr>
    <tr>
      <td width="10" height="10"><img src="images/listconframe_20.gif" width="10" height="10" alt="" /></td>
      <td height="10" background="images/listconframe_21.gif"><img src="images/space.gif" width="1" height="1" /></td>
      <td width="10" height="10"><img src="images/listconframe_22.gif" width="10" height="10" alt="" /></td>
    </tr>
  </table>
  </body>
</html>
