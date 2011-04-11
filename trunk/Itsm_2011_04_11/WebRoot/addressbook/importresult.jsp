<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>    
    <title>导入结果</title>
   	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="2%" height="22" align="left" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">
    	<img src="../images/modpass.gif" width="16" height="16">
    </td>
    <td width="98%"  background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;font-size:12px;">导入结果:</td>
  </tr>
</table>
  <table width="99%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"></td>
    </tr>
  </table>
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">      
        <tr>
      	<td height="22" colspan="6" align="left" background="../images/main20100521_58.gif" class="alllisttitle" style="font-size:12px;">导入结果</td>
    	</tr>
        <tr bgcolor="#FFFFFF">
		<td  height="19" align="left" style="font-size:12px;"><br>
		
		<s:iterator value="errorList">
   			<s:property/>
   		</s:iterator>
  		<br></td>
        </tr>
 		<tr>
		<td colspan="2" height="30">
		<table align="right" width="100" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
		<tr style="cursor: hand;">
		<td><img src="../images/addnew001.gif"></td>
		<td onClick="javascript:window.location.href='../AddressBookManage/myContact.action'">返回</td><td align="right"><img src="../images/addnew003.gif"></td>
		</tr></table>
		</td>
		</tr>
      
</table>
  </body>
</html>
