<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>修改任务工程师</title>
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">		
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
function getInfo2(){
	var roleteam = document.getElementById("engineerteam").value;
	document.getElementById("engineer").src="../role/queryforTask2.action?role.id="+roleteam;
}
function getDAO(){ //取出类别
   	RoleteamDAO.findAllByType(callbackroleteam);  
}
function callbackroleteam(data){  //显示出工程师分组
   dwr.util.removeAllOptions("engineerteam");
   dwr.util.removeAllOptions("engineerteam");
   dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam",data,"id","name");   
}	
function submit(){
	var id=document.getElementById("id").value;
	var members=document.getElementById("members").value;
	var page=document.getElementById("page").value;
	if(confirm('您确认要修改吗？')){ window.location.href="updatestd.action?id="+id+"&members="+members+"&page="+page;}
}
</script>
</head>

<body onLoad="javascript:getDAO();" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">
		<s:hidden id="members" name="members"/>
		<s:hidden id="id" name="id"/>
		<s:hidden id="page" name="page"/>
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					修改任务工程师
				</td>
			</tr>
		</table>
		<br>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#6d9db4">
			<tr bgcolor="#EBF4F5">
				<td height="12" align="center" width="6%">工作组:</td>
				<td style="padding: 0px; padding-left: 0px">
    				<select style="width: 180px" name="role.id" id="engineerteam"  onChange="getInfo2();">
       					<option value="-1">--请选择--</option>
    				</select>   																
				</td>
			</tr>
		</table>
	<!-- 	<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; width: 100%; padding-top: 5px;">-->
			<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<iframe frameborder="0" height="100%" width="100%" scrolling="yes" style="border: 1px inset" src="users2.action?members=${members}" name="engineer" id="engineer"></iframe>
					</td>
				</tr>
			</table>
<!--		</div> -->
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td height="22"  align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
					<input type="button" class="mmBtn" value="提交" onclick="javascript:submit()">
					<input type="button" class="mmBtn" value="返回" onclick="history.go(-1)">
				 </td>
			</tr>
		</table>
	</body>
</html>
