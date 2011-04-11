<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>系统日志审计</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<script language="JavaScript">
function html_clock(Num){
	var Num = Num-1+2;
	var Hours1 = (Num/60)/60;
	var Hours = Math.round((Num/60)/60);
	if (Hours > Hours1) Hours = Hours-1;
	var Minutes1 = (Num-Hours*60*60)/60;
	var Minutes = Math.round((Num-Hours*60*60)/60);
	if (Minutes > Minutes1) Minutes = Minutes-1;
	var Seconds = Num-Hours*60*60-Minutes*60;
	if (Minutes > 4) window.document.getElementById('htmlclock').style.color = 'Orange';
	if (Minutes > 9) window.document.getElementById('htmlclock').style.color = 'red';
	if (Hours > 0) window.document.getElementById('htmlclock').style.color = 'black';
	if (Hours < 10) Hours = "0" + Hours;
	if (Minutes < 10) Minutes = "0" + Minutes;
	if (Seconds < 10) Seconds = "0" + Seconds;
	window.document.getElementById('htmlclock').innerText = Hours+":"+Minutes+":"+Seconds;
	setTimeout ("html_clock('"+Num+"')", 1000);
}
</script>
</head>
<body background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">

<iframe width=0 height=0 name="sum" id="sum"></iframe>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="100%" valign="top" id="mainright">
			<table width="100%" border="1" cellpadding="10" cellspacing="0" class="tb-list" height="100%">
				<tr>
					<th style="padding: 0px; padding-top: 2px" colspan="2" valign="top">
						<div style="position: absolute; overflow: hidden; height: 18px; width: 100%">&nbsp;<img src="${pageContext.request.contextPath }/img/cate.gif" align="absMiddle">&nbsp;项目转交详情</div>
					</th>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td valign="top" height="99%" style="padding: 10px">
						
						<table width="100%" border="0" cellspacing="2" cellpadding="4" height="100%" class="datagrid">
							
							<tr>
								<td width="4%" height="12" valign="top" nowrap class="td-left" style="padding-right: 45px">转交人:</td>
								<td width="96%" height="12" valign="top" class="td-detail" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'>李斌</div>
								</td>
							</tr>
							
							<tr>
								<td width="4%" height="12" valign="top" nowrap class="td-left" style="padding-right: 45px">接手工程师:</td>
								
								<td width="96%" height="12" valign="top" class="td-detail" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'>张晓曦</div>
								</td>
								
							</tr>
							
							<tr>
								<td height="12" valign="top" nowrap class="td-left">转交原因:</td>
								<td height="99%" colspan="3" style="padding: 0px">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
									<tr style="">
										<td height="99%" style="padding-left: 7px; font-weight: normal; line-height: 22px; padding-right: 20px" valign="top" class="td-detail">
										OK
										</td>
									</tr>
									<tr>
										<td height="99%" class="td-detail" style="text-align: right; padding: 5px">
										
										</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td width="4%" height="12" valign="top" nowrap class="td-left" style="padding-right: 45px">时间:</td>
								<td width="96%" height="12" valign="top" class="td-detail" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'>2008-8-1 15:07:52</div>
								</td>
							</tr>
							
						</table>
						
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td nowrap style="padding-top: 8px; padding-bottom: 0px; padding-right: 60px"><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
					<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px" width="99%">
						
						<input type="button" value="查看这个项目" class=mmBtn name="button" onClick="window.open('../chan/?NowAction=projectdetail&ProNo=PRJ080000001','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=60,left=100,status=yes')">
						
						<input type="button" value="关闭" class=mmBtn name="button" onClick="window.top.close()">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<script>html_clock('0')</script>
	
</table>
<script>document.title='PRJ080000001'</script>
</body>
</html>
<script language="Javascript" src="${pageContext.request.contextPath}/theme/mmBtn.js"></script>
