<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>我的便签</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"><br>
		<s:form action="shownote.action" method="post" theme="simple">
			<table width="80%" height="75" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 30">
					<td width="12%" align="right" bgcolor="#deebf1"><b>便签标题：</b></td>
					<td width="88%" bgcolor="#FFFFFF">
					<s:property value="schedule.title"/>
					<!--  	<s:textfield id="title" name="title" cssStyle="width: 100%"></s:textfield>-->
				  </td>
				</tr>
				<tr style="height: 225">
					<td align="right" nowrap bgcolor="#deebf1"><b>便签内容：</b></td>
					<td bgcolor="#FFFFFF">
	             	<s:property value="schedule.detail"/>
				  </td>
				</tr>
					<tr style="height: 30">
					<td align="right" nowrap bgcolor="#deebf1"><b>发布时间：</b></td>
					<td bgcolor="#FFFFFF">
		     		<s:property value="schedule.atime"/>
				  </td>
				</tr>
		  </table>
		  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr align="center" style="height: 25">
					<td height="30" colspan="2" nowrap="nowrap" bgcolor="#FFFFFF" class="list_btm" style="text-align: center">
						&nbsp;&nbsp;
						<input type="submit" value=" 保存 " class="mmBtn">
						&nbsp;&nbsp;
						<input type="reset" value=" 重置 " class="mmBtn">
						&nbsp;&nbsp;
						<input type="button" value=" 返回 " onClick="window.location='mynote.action'" class="mmBtn">
	  </td>
		  </tr></table>

		</s:form>
	</body>
</html>