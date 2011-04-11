<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Test  Page</title>
    <script type="text/javascript" src="../../js/DatePicker/WdatePicker.js">
    </script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
	<link href="../../css/style.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src='../../dwr/util.js'></script>
	<script type='text/javascript' src='../../dwr/interface/UserDAO.js'></script>
	<script type='text/javascript' src='../../dwr/engine.js'></script>
	
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
	function findUser()
	{
		UserDAO.findAll(callbackusers);
	}
	
	 function callbackusers(data)
	 {  //显示出分类
		
	  dwr.util.removeAllOptions("users");
	  dwr.util.addOptions("users", [{id:'-1',name:'--请选择--'}],"id","name");
	  dwr.util.addOptions("users",data,"id","truename");   
	}
	</script>
  </head>
  
  <body onload="findUser();">
	<table>
	<tr>
	<td>&nbsp;    
		用户:   
		<select id="users" style="width: 50%; background-color: #FFFFCC">
    	<option value="-1">--请选择--</option>
   	</select>
   	</td>
   	</tr>
   	<tr>
   	<td>
		提交请求时间：
		<input type="text" id="id1" onclick="WdatePicker()" style="width: 50%; background-color: #FFFFCC"/>
		<img onclick="WdatePicker({el:$dp.$('id1')})" src="../../js/DatePicker/skin/datePicker.gif" width="16" height="22" />
  </td>
  </tr>
  </table>
  
  
<!--<div style="position:absolute;overflow-x:scroll;overflow-y:scroll;width:100%;padding-top:5px;" >
<table width="99%" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
	<tr>
  	<td bgcolor="100%" height="12" colspan="2">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb-list">
		<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Gray">
			<tr>
			<td style="padding-right:1px;cursor:hand" onClick="Table('at')">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td><img src="../../img/tab_un_left.gif" id="left_at"></td>
				<td background="../../img/tab_un.gif" style="font-weight:bold;padding-left:10px;padding-right:10px;padding-top:5px" nowrap id="at">添加附件</td>
				<td><img src="../../img/tab_un_right.gif" id="right_at"></td>
				</tr>
			</table>
			</td>
		
			<td style="padding-right:1px;cursor:hand" onClick="Table('au')">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td><img src="../../img/tab_un_left.gif" id="left_au"></td>
				<td background="../../img/tab_un.gif" style="font-weight:bold;padding-left:10px;padding-right:10px;padding-top:5px" nowrap id="au">用户信息</td>
				<td><img src="../../img/tab_un_right.gif" id="right_au"></td>
				</tr>
			</table>
			</td>
			
			<td style="padding-right:1px;cursor:hand" onClick="Table('ar')">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td><img src="../../img/tab_un_left.gif" id="left_ar"></td>
				<td background="../../img/tab_un.gif" style="font-weight:bold;padding-left:10px;padding-right:10px;padding-top:5px" nowrap id="ar">任务分配与协议</td>
				<td><img src="../../img/tab_un_right.gif" id="right_ar"></td>
				</tr>
			</table>
			</td>
			
			<td style="padding-right:1px;cursor:hand" onClick="Table('af')">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td><img src="../../img/tab_un_left.gif" id="left_af"></td>
				<td background="../../img/tab_un.gif" style="font-weight:bold;padding-left:10px;padding-right:10px;padding-top:5px" nowrap id="af">快速解决</td>
				<td><img src="../../img/tab_un_right.gif" id="right_af"></td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
			</td>
		</tr>
		
		<tr id="at_table" style="display:">
		<td>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color:#C3BFB3;border-collapse:collapse;border-left-width:1px;border-right-width:1px;border-top-width:1px;">
			<tr>
			<td valign="top" style="overflow-y:scroll;background-image:url(../../img/trh.jpg)" height="12">
			<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
				<tr>
				<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">上传附件</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td valign="top" height="160"><div id="_file"></div><br/></td>
					</tr>
			<tr>
			<td height="20" bgcolor="#FFFFFF">
			<table width="100%" border="0" cellspacing="1" cellpadding="4">
			<tr>
			<td width="1%" bgcolor="#EBF4F5" nowrap>&nbsp;选择文件:</td>
			<td width="99%" bgcolor="#EBF4F5" style="padding-top:1px;padding-bottom:2px">
			<div id="input">
			<input name="file" id="file0" type="file" value="添加文件" onChange="addFile()"/>
			<font size="1.8" color="red">如果添加多个附件,请继续点击"浏览"</font>
			</div>
			</td>
			</tr>
			</table>
			</td>
			</tr>
		</table>
		</td>
		</tr>
		<tr id="au_table" style="display:none">
		<td>
		<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color:#C3BFB3;border-collapse:collapse;border-right-width:1px;border-top-width:1px;">
		<tr>
		<td valign="top" style="overflow-y:scroll;background-imane:url(../../img/trh.jpg)" height="12">
		<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
		<tr>
		<td nowrap class="subtitle" bgcolor="#b5d6e6">查看用户信息</td></tr>		
		</table>
		</td>
		</tr>
		<tr>
		<td valign="top" nowrap><iframe frameborder="0" height="179" width="100%" scrolling="yes" style="border:1px inset" name="userinfo" id="userinfo"></iframe></td>
		</tr>
		<tr>
		<td valign="top" style="overflow-y:scroll" height="12"></td>
		</tr>
		</table>
		</td>
		</tr>
		
		<tr id="ar_table" style="display:none">
		<td>
		
		<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-collapse;border-left-width:1px;border-right-width:1px;border-top-width:1px;">
		<tr>
		<td valign="top" style="overflow-y:scroll;background-image:url(../../img/trh.jpg)" height="12">
		<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
		<tr>
		<td valign="top" style="overflow-y:scroll;background-image:url(../../img/trh.jpg)" height="12">
			<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
			<tr>
			<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">任务指派与服务协议</td>
			</tr>
			</table>
		</td>
		</tr>
		<tr>
		<td valign="top" height="191">
		<div style="position:absolute;overflow-x:hidden;overflow-y:scroll;height:100%;width:100%">
		<table width="100%" border="0" cellspacing="2" cellpadding="4">
		<tr bgcolor="#EBF4F5" id="astd_0">
		<td width="14%" valign="top">系统自动分配:</td>
		<td colspan="3"><input type="text" name=ITers style="width:100%;border:0px;background-color:#EBF4F5;Color:#3A4E69;padding-top:2px" readonly></td>
		</tr>
		
		<tr bgcolor="#EBF4F5" style="display:none" id="astd_1">
		<td height="12">工作组:</td>
		<td colspan="3" style="padding:0px;padding-left:7px">
		<select style="width:120px" id="location" onChange="getInfo1();">
		<option value="-1">--请选择--</option>
		</select>
		<select style="width:180px" name="role.id" id="engineerteam" onChange="getInfo2();">
		<option value="-1">---请选择-</option>
		</select>
		</td>
		</tr>
		
		<tr>
		<td bgcolor="#EBF4F5" style="display:none" id="astd_2"></td>
		<td valign="top">选择工程师:</td>
		<td colspan="3" style="padding-left:7px">
		<iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border:1px inset" name="ITer" id="ITer"></iframe>
		</td>
		</tr>		
		
		<tr bgcolor="#EBF4F5">
		<td width="14%" height="12"></td>
		<td style="padding:1px;padding-left:3px" colspan="3">
		<table border="0" cellspacing="0" cellpadding="0">
		
		<tr bgcolor="#EBF4F5">
		</tr>
		
		</table>
		
		
		</td>
		</tr>
		
				
		</table>
		</div>
		</td>
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
--></body>
</html>
			 
		