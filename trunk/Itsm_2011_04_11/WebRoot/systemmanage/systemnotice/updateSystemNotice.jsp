<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>公告更新3</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
<div align="center" style="width: 90%">
<s:actionerror cssStyle="color: red"/>
</div>  
<body leftmargin="0" marginwidth="0">
 <s:form action="SystemNotice/updateSystemNotice.action" name="submitInfo" method="post" theme="simple" enctype="multipart/form-data">
  <s:hidden name="id" value="%{id}"></s:hidden>
  <table width="90%" height="75" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
  <tr style="height:25">
  <td width="120" align="right" nowrap="nowrap" bgcolor="#deebf1" class="zczb_qua">发布人：</td>
  <td width="88%" bgcolor="#FFFFFF"><s:textfield name="authorName" value="%{systemNotice.authorName}" style="width: 50%;"/></td>
  </tr>
  
  <tr style="height:25">
  <td width="120" align="right" bgcolor="#deebf1" class="zczb_qua">标 题：</td>
  <td width="88%" bgcolor="#FFFFFF"><s:textfield name="title" value="%{systemNotice.title}" size="80" style="width: 70%;" /></td>
  </tr>
  <tr style="height:25">
  <td width="120" align="right" bgcolor="#deebf1">公告内容：</td>
  <td bgcolor="#FFFFFF"><s:textarea name="content" cols="30" rows="15" value="%{systemNotice.content}" style="width: 80%;"></s:textarea></td>
  </tr>
  <tr style="height:25">
  <td width="120" align="right" nowrap="nowrap" bgcolor="#deebf1" class="zczb_qua">注 意：</td>
  <td bgcolor="#FFFFFF">
  <a href="<%=path%>/SystemNotice/dlpsUI.action?dlFileName=<s:property value='accessoryList.get(0).name'/>">
  <s:property value="fileName" /></a>
 &nbsp;&nbsp;<font color="#FFFFCC">&nbsp;&nbsp;该文件将会被替换;</font></td>
  </tr>
  <tr style="height:25">
  <td width="120" align="right" bgcolor="#deebf1" class="zczb_qua">上传附件：</td>
  <td bgcolor="#FFFFFF">
  <s:file name="file" size="40" id="file"></s:file>
  </td>
  </tr>
  <tr align="center" style="height:30px;">
  <td height="30" colspan="2" align="center" nowrap="nowrap" bgcolor="#FFFFFF">
  <input name="button1" type="submit" class="mmBtn" value="重新发布"> 
  &nbsp;&nbsp;
  <input name="button2" type="reset" class="mmBtn" value="重置" >
&nbsp;&nbsp; 
  <input name="button" type="button" value="后退" onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/listSystemNotice.action'" class="mmBtn"/>
  </td>
  </tr>
  </table>
  </s:form>
  </body>
</html>
