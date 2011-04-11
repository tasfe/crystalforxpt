<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>工作流定义</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">流程管理:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px; left: 16px; top: 53px;">
<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table cellspacing=0 cellpadding=0 border=0 width="100%">
      <tr>
        <td nowrap><img src="../img/help2.jpg" width="23" height="24" align="absmiddle">&nbsp;</td>
        <td width="99%" onClick="document.getElementById('thelayer').style.display = 'none'">&nbsp;<b>流程刘表:</b></td>
        <td align=right colspan=2 width="1%" height="28" valign="top" style="padding-top: 4px">
		</td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
        <tr bgcolor="#FFFFFF">
          <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">分类名称</td>
          <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">流程名称</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">描述</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">创建时间</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">管理</td>
        </tr>             
     <s:iterator value="pageBean.list" var="serviceRequest">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
          <td align="center"><s:property value="requestNo"/></td>
          <td align="center"><s:property value="serviceCategory.itemZh"/></td>
          <td align="center">  <s:date name="submintTime" format="yyyy/MM/dd HH:mm:ss" /></td>
          <td align="center"> <s:date name="buginTime" format="yyyy/MM/dd HH:mm:ss" /></td>
          <td align="center"> <s:date name="expectTime" format="yyyy/MM/dd HH:mm:ss" /></td>          
        </tr>
     </s:iterator>
       
      </table>
    </td>
  </tr>
</table>
</div>
</body>
</html>
