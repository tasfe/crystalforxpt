<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
	<title>IT Service Desk</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset; overflow: hidden" onLoad="setTimeout('window.top.close()',6000)">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">&nbsp;操作提示</td>
  </tr>
</table>

<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr> 
  <td valign="top" id="mainright" height="100%"><table cellspacing=2 cellpadding=4 border=0 width="100%" height="100%" bgcolor="#b5d6e6">
    <tr class="td-right-s">
      <td width="61%" style="padding: 20px" valign="top" bgcolor="#deebf1">工程师:
        <p style="padding-left: 20px">您的操作已经被系统成功执行，<br>
          您的支持将有助于我们改进工作，谢谢您的合作。</p>
        IT Service Desk<br>
        <s:property value="#u.date.toString().substring(0,16)"/>
        <br>
        2010-10-2 9:04:54 </td>
    </tr>
  </table></td>
</tr>
</table>
</body>
</html>

