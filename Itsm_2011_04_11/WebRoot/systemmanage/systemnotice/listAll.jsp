<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
	<title>系统公告列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
</head>
	
<body style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">系统公告搜索</td>
  </tr>
</table>

<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
<s:form method="post" action="/SystemNotice/querySystemNotice2.action" theme="simple">
	<tr>
		<td width="8%" align="center" nowrap bgcolor="#deebf1">发布者：&nbsp;</td>
											
		<td width="14%" bgcolor="#FFFFFF" style="padding-right: 10px">
		<input name="authorName" type="text" size="40" style="width: 80%"></td>
		
		<td width="8%" align="center" nowrap bgcolor="#deebf1">标题：&nbsp;</td>			
		<td width="17%" bgcolor="#FFFFFF" style="padding-right: 10px">														
		<input name="title" type="text" size="40" style="width: 80%">
		</td>	
										
		<td width="8%" align="center" nowrap bgcolor="#deebf1">详细内容：&nbsp;</td>																																
		<td width="18%" bgcolor="#FFFFFF" style="padding-right: 10px">
		<input name="content" type="text" size="40" style="width: 80%"></td>
		
	    <td width="6%" bgcolor="#deebf1" align="center">
			<input type="submit" style="height:20px" class="mmBtn" value="搜索" id="2" />
		</td>
		</tr>									
</s:form>
</table>
<br>
<div style="position: absolute; overflow-x: hidden; overflow-y: hidden; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6"> <tr>
       	<th width="60%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">公告标题</th> 
        <th width="15%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布时间</th>
        <th width="15%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布者</th>
        <th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">查看</th>
      </tr>
      <s:iterator value="pageBean.list" id="u">
      	<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      		<td align="left"><s:a href="showUI.action?id=%{#u.id}"><s:property value="#u.title"/></s:a></td>
      		<td align="center"><s:property value="#u.date.toString().substring(0,16)"/></td>
			<td height="30" align="center"><s:property value="#u.authorName"/></td>
        	<td align="center">
        		<img src="../images/edt.gif">
        		<s:a href="showUI.action?id=%{#u.id}">查看</s:a>	
	        </td>
        </tr>
      </s:iterator>
      </table>
      <jsp:include page="/common/page.jsp"/>
	<s:form name="form" method="post" action="listUI.action" theme="simple" >
	<s:hidden id="page" name="page"></s:hidden>		
	</s:form>
</div>
		
</body>
</html>
