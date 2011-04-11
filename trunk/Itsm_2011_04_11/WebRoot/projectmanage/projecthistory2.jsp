<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.combanc.*"%>

<html>
<head>
		<title>项目历史记录</title>
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/SeverityTypDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>

		<style type="text/css">
.onOver {
	Border: 1px solid white;
}

A:hover {
	color: #000000;
	text-decoration: none;
	background-color: #EEF8ED;
	Border-Bottom: 1px solid #333333;
	Border-Right: 1px solid #333333;
	Border-Top: 1px solid #C6CFD8;
	Border-Left: 1px solid #C6CFD8;
}
</style>

<script type="text/javascript">

</script>
</head>

<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">

<div OnMouseDown="document.getElementById('Layer2').style.visibility='hidden'">

<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  	<tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">&nbsp;<img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">项目历史记录</td>
 	</tr>
</table>

<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
<s:form method="post" action="" theme="simple">
<tr>
<td width="2%" align="center" nowrap bgcolor="#deebf1">内容描述:&nbsp;</td>
<td width="23%" bgcolor="#FFFFFF" style="padding-right:10px"><input name="content" type="text" size="40" style="width:100%"></td>

<td width="2%" align="center" nowrap bgcolor="#deebf1">项目类别:&nbsp;</td>
<td width="23%" bgcolor="#FFFFFF" style="padding-right:10px"><input name="projecttype" type="text" size="40" style="width:100%"></td>

<td width="2%" align="center" nowrap bgcolor="#deebf1">项目号:&nbsp;</td>
<td width="23%" bgcolor="#FFFFFF" style="padding-right:10px"><input name="projectid" type="text" size="40" style="width:100%"></td>

<td width="2%" bgcolor="#deebf1"><input type="submit" style="height: 20px" class="mmBtn"value="搜索" /></td>
</tr>
</s:form>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td>
<table align="left" width="100" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
<tr>
<td height="20" colspan="5" background="../images/main20100521_58.gif">
<table width="60%" border=0 cellpadding=0 cellspacing=0>
<tr>
<td valign="top" height="12" colspan="2">
   <table cellspacing=0 cellpadding=0 border=0 width="100%">
      <tr> 
         <td width="99%" height="30" valign="bottom"><b><img src="../img/index.jpg" width="20" height="19" align="center">&nbsp;项目列表:</b></td>
      </tr>
     </table>
	</td>
	</tr>

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
