<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript"> 
			function detail(){
			var id=document.getElementById("idss").value;
				window.location="detail.action?scheduleId="+id;
			}
			function change(num,uid) {
			
				var date=document.getElementById("nowDate").value;
				window.location = "month.action?flag="+num+"&dateStart="+date+"&uid="+uid;
			}
		</script>
	</head>
	<body>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="../images/m_mpbg.gif">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					预约月历:
				</td>
			</tr>
			
						  	<tr>
    <td height="30" colspan="5" background="../images/main20100521_58.gif">
    	<table width="60%" border=0 cellpadding=0 cellspacing=0>
      	<tr>
        <td width="3%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="99%" style="color:#333333; font-weight:bold;">月历详情</td>
      	</tr>
    	</table>
    </td>
  	</tr>
			
		</table>
	<input name="nowDate" id="nowDate" value="<s:property value="curDate" escape="false"/>" type="hidden" />
		<s:hidden name="schedule.id" id="idss" theme="simple" />
				<s:hidden name="schedule.atime" theme="simple" />
			<s:hidden name="schedule.userByAssigner.id" theme="simple" />
		<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
			<tr>
				<td width="100%"  height="22" bgColor="#FFFFFF" align="center" background="../images/main20100521_58.gif">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center" width="40%">
								<a href="#" onClick="change(-1,<s:property value="uid"/>)">上一个月</a>
							<td align="center" width="20%"><s:property value="curDate"/></td>
							<td align="center" width="40%"><a href="#" onClick="change(1,<s:property value="uid"/>)">下一个月</a></td>
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
	</body>