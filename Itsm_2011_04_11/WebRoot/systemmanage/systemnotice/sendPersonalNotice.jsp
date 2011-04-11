<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.combanc.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>即时通知发送</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="css/style.css" rel="stylesheet" type="text/css">
		
	<script type="text/javascript" src="js/zcms/zDrag.js"></script>
	<script type="text/javascript" src="js/zcms/zDialog.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/UserDAO.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">
	
	function newZCMS(){
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 400;
		diag.Title = "用户列表";
		diag.URL = "2.jsp";
		diag.show();
		diag.okButton.value = "提交";
		diag.cancelButton.value = "取消";	
	}
	
	function getTo(){
	
		var diag = new Dialog();
		diag.Width = 750;
		diag.Height = 400;
		diag.MessageTitle = "请选择用户";
		diag.Drag = true;
		diag.URL = "3.jsp";
		diag.show();
		diag.okButton.value = "提交";
		diag.cancelButton.value = "取消";
	
	}
	
	 function findUser()
	 {
		UserDAO.findAll(callbackusers);
	 }
	 
	 function callbackusers(data)
	 {  //显示出分类
		
	  dwr.util.removeAllOptions("users");
	  dwr.util.addOptions("users", [{id:'-1',name:'--请选择--'}],"id","name");
	  dwr.util.addOptions("users",data,"id","username");   
  		
  	  var temp = "";
  		
	  for(var i=0;i<data.length;i++)
	 {
  	  	temp +="<input type='checkbox'/>"+data[i].truename+"<br/>";
  	 }
  	  document.getElementById("div").innerHTML=temp;
	}
	</script>
  </head>
  
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style=" overflow:auto;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
	  <tr>
	    <td width="1%" height="22" align="center"
				background="images/main20100521_582.gif"
				style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;"><img src="images/modpass.gif" width="16" height="16"></td>
	    <td width="99%" background="images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">发送个人通知</td>
      </tr>
</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<s:form name="submitInfo" id="submitInfo" action="/PersonalNotice/sendPersonalNotice.action" method="post" theme="simple">
	<tr>
	<td bgcolor="white" valign="top">
	<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
	<tr>
		<td width="10%" align="center" bgcolor="#deebf1" colspan="2"><center><b>请点击接收人</b></center></td>
	</tr>
	<tr>
		<!--<td width="4%" align="center" bgcolor="#deebf1"> <a href="javascript:newZCMS()"><b>接&nbsp;&nbsp;收&nbsp;&nbsp;人:</b></a></td>
		-->
		<td width="5%" align="right" bgcolor="#deebf1"> <a href="javascript:void(0)" onClick="getTo();">接收人：</a></td>
		<td width="20%" bgcolor="#FFFFFF"> <input type="text" id="to" name="to" readonly style="width: 20%;"/>
		</td>
	</tr>
	<tr>
		<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">主 题：</td>
		<td width="50%" bgcolor="#FFFFFF"><input name="title" type="text" size="60" style="width: 80%;"/></td>
	</tr>
	<tr>
		<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">内 容：</b></td>
		<td width="50%" bgcolor="#FFFFFF"><textarea name="content" rows="8" cols="86" style="width: 90%; height: 120;"></textarea></td>
	</tr>
	<tr align="center" style="height:25">
	<td height="30" colspan="2" align="center" nowrap="nowrap" bgcolor="#FFFFFF">
	<input name="submit" type="submit" value="发送通知" class="mmBtn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input name="button" type="button" value="后退" onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/listSystemNotice.action'" class="mmBtn">
</td>
</tr>
	</table>
	</td>
	</tr>
</s:form>
</table>
</body>
  