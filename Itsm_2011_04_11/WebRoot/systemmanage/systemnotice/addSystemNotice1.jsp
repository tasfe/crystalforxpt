<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>发送公告</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="../js/zcms/zDrag.js"></script>
	<script type="text/javascript" src="../js/zcms/zDialog.js"></script>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	
		function checkForm(){
			var title= document.getElementById("title").value;
			var content = document.getElementById("content").value;
			if(!title){
				alert('标题不能为空!');
				return false;
			}
			if(!content){
				alert('公告内容不能为空!');
				return false;
			}
			return true;
		}	
	</script>

  </head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:auto;">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
	  <tr>
	    <td width="1%" height="22" align="center"
				background="../images/main20100521_582.gif"
				style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
	    <td width="99%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">发送公告</td>
      </tr>
</table>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <s:form name="frm" method="post" action="/SystemNotice/addSystemNotice.action" enctype="multipart/form-data" theme="simple">
		<tr>
		<td bgcolor="white" valign="top">
		<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
		<tr>
			<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">标 题：</td>
			<td width="35%" bgcolor="#FFFFFF"><input id="title" name="title" type="text" size="50" style="width: 80%;"/></td>
		</tr>
		
		<tr>
			<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">发布人：</td>
		<td width="20%" bgcolor="#FFFFFF">
   			<input type="text" id="authorName" name="authorName" value="<s:property value='authorName'/>" size="70" style="width: 80%;" readonly/>
   		</td>
		</tr>

		<tr>
		<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">公告内容：</td>
		<td width="35%" bgcolor="#FFFFFF"><textarea name="content" id="content" rows="10" cols="90" style="width: 90%; height:120;"></textarea></td>
		</tr>
	
		<tr>
		<td width="5%" align="right" bgcolor="#deebf1" class="zczb_qua">附 件：</td>
		<td width="10%" bgcolor="#FFFFFF"><s:file name="file" size="80" id="file"></s:file></td>
		</tr>

		<tr align="center" style="height:25">
		<td height="30" colspan="2" align="center" nowrap="nowrap" bgcolor="#FFFFFF">
		<input name="submit" type="submit" value="发送通知" class="mmBtn" onClick="javascript:return checkForm();">&nbsp;&nbsp;
		<input name="reset" type="reset" value="全部重设" class="mmBtn">&nbsp;&nbsp;
		<input name="button" type="button" value="后退" onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/listSystemNotice.action'" class="mmBtn">
		</td>
		</tr>
	</table>
</s:form>
</table>
</body>
</html>