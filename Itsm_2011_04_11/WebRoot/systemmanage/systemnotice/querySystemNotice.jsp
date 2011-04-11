<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>快速查询列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">
<s:form method="post" action="/SystemNotice/querySystemNotice.action" theme="simple">
    	<table cellspacing=0 cellpadding=0 border=0 width="100%">
		<tr>
			<td width="1%" height="22" align="center"
				background="../images/main20100521_582.gif"
				style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
				<img src="../images/modpass.gif" width="16" height="16">
			</td>
			<td width="99%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">系统公告</td>
		</tr>
	</table>

		  <table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
		    <tr>
		      <td width="8%" align="center" nowrap bgcolor="#deebf1">发布者：&nbsp; </td>
		      <td  width="14%" bgcolor="#FFFFFF" style="padding-right: 10px"><input name="authorName" type="text" size="40" style="width: 80%"></td>
		      <td width="8%" align="center" nowrap bgcolor="#deebf1">标题：&nbsp; </td>
		      <td width="17%" bgcolor="#FFFFFF" style="padding-right: 10px"><input name="title" type="text" size="40" style="width: 80%"></td>
		      <td width="8%" align="center" nowrap bgcolor="#deebf1">详细内容：&nbsp; </td>
		      <td width="18%" bgcolor="#FFFFFF" style="padding-right: 10px"><input name="content" type="text" size="40" style="width: 80%"></td>
		      <td width="6%" bgcolor="#deebf1" align="center"><input name="button" type="submit" class="mmBtn" value="搜索"
 style="height: 20px"></td>
	        </tr>
	      </table>
          <br>
          <table cellspacing=0 cellpadding=0 border=0 width="100%">
		<tr>
			<td width="1%" height="22" align="center"
				background="../images/main20100521_582.gif"
				style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
				<img src="../images/modpass.gif" width="16" height="16">
			</td>
			<td width="99%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">查询列表</td>
		</tr>
	</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 70%; width: 100%; padding-top: 5px;">
		  <table width="99%" border="0" cellpadding="2" cellspacing="1"
					bgcolor="#b5d6e6" class="datagrid" align="center">
					<tr>
						<th width="45%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">公告标题
						</th>
						<th width="25%" height="22" nowrap
background="../images/main20100521_58.gif" class="alllisttitle">发布者</th>
						<th width="20%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布时间
						</th>
					</tr>
					<s:iterator value="#request.list" id="u">
						<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
							<td align="center" class="zczb_qua">
								<s:property value="#u.title" />
							</td>
							<td height="30" align="center" class="zczb_qua"><s:property value="#u.authorName" /></td>
							<td align="center" class="zczb_qua">
<s:property value="#u.date.toString().substring(0,16)" />
							</td>
						</tr>
					</s:iterator>
				</table>
				<table width="99%" border="0" align="center" cellpadding="0"
					cellspacing="0" bgcolor="">
					<tr>
						<td colspan="6" height="30" align="right">
							<table width="80" border="0" cellpadding="0" cellspacing="0"
								background="../images/addnew002.gif">
								<tr style="cursor: hand;">
									<td><img src="../images/addnew001.gif"></td>
									<td onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/listSystemNotice.action'">后 退</td>
									<td align="right"><img src="../images/addnew003.gif"></td></tr>
							</table>
						</td>
					</tr>
		</table>

			</div>
		</s:form>
	</body>
</html>
