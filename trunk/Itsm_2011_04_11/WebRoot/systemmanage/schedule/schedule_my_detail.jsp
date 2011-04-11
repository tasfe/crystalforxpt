<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>日程细节</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="../images/m_mpbg.gif">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="100%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					预约详情:
				</td>
			</tr>

			<tr>
				<td height="30" colspan="5"
					background="../images/main20100521_58.gif">
					<table width="60%" border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td width="3%" align="center"
								background="../images/main20100521_58.gif"
								style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
								<img src="../images/modpass.gif" width="16" height="16">
							</td>
							<td width="99%" style="color: #333333; font-weight: bold;">
								明细查询
							</td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
		<s:hidden name="schedule.id" theme="simple" />
		<s:hidden name="schedule.atime" theme="simple" />
		<s:hidden name="schedule.userByAssigner.id" theme="simple" />
		<table width="100%" height="75" border="0" cellspacing="1"
			cellpadding="2" align="center" bgcolor="#b5d6e6">
			<tr style="height: 40">
				<td align="right" nowrap bgcolor="#deebf1">
					<b>执行人：</b>
				</td>
				<td bgcolor="#FFFFFF">
					<s:property value="schedule.userByExecutor.truename" />
				</td>
			</tr>
			<tr style="height: 40">
				<td width="12%" align="right" bgcolor="#deebf1">
					<b>执行日期：</b>
				</td>
				<td width="88%" bgcolor="#FFFFFF">
					<s:date name="schedule.adate" format="yyyy-MM-dd(EE)" />
				</td>
			</tr>
			<tr style="height: 40">
				<td width="12%" align="right" bgcolor="#deebf1">
					<b>具体时间：</b>
				</td>
				<td width="88%" bgcolor="#FFFFFF">
					<s:property value="schedule.hour" />
					:
					<s:property value="schedule.minute" />
				</td>
			</tr>
			<tr style="height: 310">
				<td align="right" nowrap bgcolor="#deebf1">
					<b>日程安排：</b>
				</td>
				<td bgcolor="#FFFFFF">
					<s:property value="schedule.content" />
				</td>
			</tr>
			<tr style="height: 40">
				<td align="right" nowrap bgcolor="#deebf1">
					<b>预约人：</b>
				</td>
				<td bgcolor="#FFFFFF">
					<s:property value="schedule.userByAssigner.truename" />
				</td>
			</tr>

			<tr>
				<td height="30" colspan="2" width="100%"
					background="../images/main20100521_58.gif">
					<table width="100%" border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td colspan="3" width="98%" style="color: #333333; font-weight: bold;">
								<table width="98%" style="color: #333333; font-weight: bold;">
									<tr style="color: #333333; font-weight: bold;">
									<td align="right">
							            <input  align="right" type="button" name="Submit1" value="返回" onclick="window.location.href='my.action'"/>
									</td>
									</tr>
								</table>
							</td>
							</tr>
							</table>
							</td>
							</tr>
							
					</table>
	</body>
</html>