<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>我的便签</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
				<SCRIPT LANGUAGE="JavaScript" type="text/javascript"
					src="../js/DatePicker/WdatePicker.js">
</SCRIPT>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"><br>
		<s:form action="addnote.action" method="post" theme="simple">
			<table width="80%" height="75" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 30">
					<td width="12%" align="right" bgcolor="#deebf1"><b>便签标题：</b></td>
					<td width="88%" bgcolor="#FFFFFF">
					<s:textfield id="schedule.title" name="schedule.title" cssStyle="width: 100%"></s:textfield>
				  </td>
				</tr>
				<tr style="height: 225">
					<td align="right" nowrap bgcolor="#deebf1"><b>便签内容：</b></td>
					<td bgcolor="#FFFFFF">
					<s:textarea id="schedule.detail" name="schedule.detail" cssStyle="width: 100%;height:225"></s:textarea>
				  </td>
				</tr>
					<tr style="height: 30">
					<td align="right" nowrap bgcolor="#deebf1"><b>发布时间：</b></td>
					<td bgcolor="#FFFFFF">
				<s:textfield name="schedule.atime" id="id2" size="100"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})"></s:textfield>
									<img
										onClick="WdatePicker({el:$dp.$('id2'),dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})"
										src="../js/DatePicker/skin/datePicker.gif" width="20"
										height="22" />
										<!--  
									<SCRIPT LANGUAGE="JavaScript">

var myDate = new Date();
   document.getElementById("id2").value= myDate.toLocaleDateString();     //获取当前日期
</SCRIPT>-->
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
						<input type="button" value=" 返回 " onClick="window.location='list.action'" class="mmBtn">
	  </td>
		  </tr></table>

		</s:form>
	</body>
</html>