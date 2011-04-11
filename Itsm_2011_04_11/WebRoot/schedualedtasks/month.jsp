<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript"> 
			function work(date){
				window.parent.document.getElementById("work").src="work.action?dateStart="+date;
			}
			function change(num) {
				var date=document.getElementById("nowDate").value;
				window.parent.document.getElementById("month").src="month.action?flag="+num+"&dateStart="+date;
			}
		</script>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
			<tr>
				<td width="100%"  height="22" bgColor="#FFFFFF" align="center" background="../images/main20100521_58.gif">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" width="40%">
								<a href="#" onClick="change(-1)">上一个月</a>
							<td align="center" width="20%"><s:property value="curDate"/></td>
							<td align="center" width="40%"><a href="#" onClick="change(1)">下一个月</a></td>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%" bgColor="#FFFFFF">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
						<tr>
							<td height="100%" valign="top" width="100%" style="border: 1px inset; border-bottom: 0px">
								${content}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input name="nowDate" id="nowDate" value="<s:property value="curDate" escape="false"/>" type="hidden" />
	</body>