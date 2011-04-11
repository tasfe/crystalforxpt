<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>公告详细信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

<!--
body {
	margin-top: 5px;
}
-->

	</head>
	<body leftmargin="0" marginwidth="0">
	
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6" title="公告">
				<tr style="height: 25">
					<td width="150" align="center" bgcolor="#deebf1">
						<b>公告标题：</b>
				  </td>
					<td width="717" bgcolor="#deebf1" align="center">
					<s:label id="title"><s:property value="systemNotice.title"/></s:label>
						
					</td>
				</tr>
				<tr style="height: 25">
					<td width="150" align="center"  bgcolor="#deebf1">
						<b>公告内容：</b>
					</td>
					<td height="100" bgcolor="#deebf1" align="center">
					<s:property value="systemNotice.content"/>
					
					</td>
				</tr>
				<tr style="height: 25">
					<td width="150" align="center" bgcolor="#deebf1">
						<b>发布时间：</b>
					</td>
					<td align="center" bgcolor="#deebf1">
					<s:date name="systemNotice.date" format="yyyy/MM/dd HH:mm:ss"/>
					
					</td>
				</tr>
				
				<tr align="center" style="height: 25">
					<td height="25" colspan="2" align="left" nowrap="nowrap"
						bgcolor="#deebf1" style="padding-left: 120px;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>

	</body>
</html>