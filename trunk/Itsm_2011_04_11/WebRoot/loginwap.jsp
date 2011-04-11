<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="must-revalidate,no-cache" />
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
.login_ft1{
	font-size:16px;
	color:#0A5599;
	font-weight:bold;
	}
.login_ft2{
	font-size:14px;
	color:#373737;
	font-weight:bold;
	}
-->
</style>
<script type="text/javascript">
function init(){
document.getElementById('uidtxt').focus(); 
}
function sub() {

 document.form1.submit();	
}
</script>
<title>IT运维-登陆</title>
</head>
<body bgcolor="#E4E5E7" onload="init();">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="images/wap/top_bg.jpg"><img src="images/wap/logo.jpg" width="152" height="45"></td>
  </tr>
  <tr>
    <td valign="top" bgcolor="#E4E5E7" ><br/>
    <form id="form1" name="form1" action="login.action" method="post" >
<table width="240" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="28" colspan="2" align="center" valign="bottom" class="login_ft1">用户登录</td>
        </tr>
      <tr>
        <td align="center"><div> 用户名：<input id="uidtxt" name="username" style="width:120px" type="text" value="" /><br /> 密　码：<input id="pwdtxt" name="password" style="width:120px" type="password" value="" /></td>
        </tr>
      <tr>
        <td align="center"><br/>

<a href="#" onclick="sub();"><img src="images/wap/denglu.jpg" width="84" height="23"></a>
</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>
