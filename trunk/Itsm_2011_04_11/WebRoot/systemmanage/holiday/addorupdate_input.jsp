<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>节假日管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"><br>
		<s:form action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="holiday.id" name="holiday.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" nowrap bgcolor="#deebf1"><b>节假日名称：</b></td>
					<td width="90%" bgcolor="#FFFFFF">
				  <s:textfield id="holiday.name" name="holiday.name" cssStyle="width: 100%"></s:textfield></td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1"><b>节假日细节：</b></td>
					<td bgcolor="#FFFFFF">
					<s:textfield id="holiday.holiday" name="holiday.holiday" cssStyle="width: 100%; height:200px;" cols="60"></s:textfield></td>
				</tr>
		
				<tr align="center" style="height: 25">
					<td height="25" colspan="2" align="left" nowrap="nowrap" bgcolor="#deebf1" style="padding-left:120px;">&nbsp;&nbsp;
                        <input name="submit" type="submit" class="mmBtn" value=" 保存 ">
&nbsp;&nbsp;
<input name="reset" type="reset" class="mmBtn" value=" 重置 ">
&nbsp;&nbsp;
<input name="button" type="button" class="mmBtn" onClick="window.location='list.action'" value=" 返回 ">				    </td>
			    </tr>
		  </table>
		</s:form>
	</body>
</html>