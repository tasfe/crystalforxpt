<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body onmousedown="document.getElementById('TreeP').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">用户基本资料:</td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFFF" height="100%">
      <tr>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">登&nbsp;&nbsp;录&nbsp;&nbsp;名:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"> <s:property value="users.username" /></td>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.truename"/></td>
      </tr>
      <tr style="display: none">
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">所在公司名:</td>
        <td colspan="3" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.username"/></td>
      </tr>
      <tr>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">所在部门:</td>
        <td valign="top" nowrap bgcolor="#EBF4F5" class="td-right-s" style="padding-left: 0px; padding-top: 5px"><s:property value="users.department.name"/></td>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">所在位置:</td>
        <td valign="top" nowrap bgcolor="#EBF4F5" class="td-right-s" style="padding-left: 0px; padding-top: 5px"><s:property value="users.location.name"/></td>
      </tr>
      <tr>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.phone"/></td>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.cellphone"/></td>
      </tr>
      <tr>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">电子邮箱:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"><a href="MailTo:wanglin@longhu.com"><s:property value="users.email"/></a></td>
        <td width="16%" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 6px">用户组别:</td>
        <td width="34%" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.usertype"/></td>
      </tr>
      <tr>
        <td width="16%" valign="top" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 8px">员工工号:</td>
        <td colspan="3" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.id"/></td>
      </tr>
      <tr>
        <td width="16%" valign="top" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 8px">其它备注信息:</td>
        <td height="99%" colspan="3" nowrap bgcolor="#EBF4F5" class="td-right-s"><s:property value="users.description"/></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
