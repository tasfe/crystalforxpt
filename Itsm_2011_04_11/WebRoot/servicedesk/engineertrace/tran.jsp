<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
<body style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">任务转交详情:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: hidden; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">

<iframe width=0 height=0 name="sum" id="sum"></iframe>
<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td height="100%" valign="top" id="mainright">
			<table width="100%" border="0" cellpadding="10" cellspacing="1" height="100%">
				<tr bgcolor="#FFFFFF">
					<td valign="top" height="99%" style="padding: 10px">
						<table width="100%" border="0" bgcolor="#b5d6e6" cellspacing="1" cellpadding="4" height="100%">
							
							<tr>
								<td width="4%" height="12" valign="top" nowrap bgcolor="#EBF4F5" style="padding-right: 45px">转交人:</td>
								<td width="96%" height="12" valign="top" bgcolor="#FFFFFF" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'><s:property value="serviceTran.usersByServiceFrom.truename"></s:property></div>
								</td>
							</tr>
							
							<tr>
								<td width="4%" height="12" valign="top" nowrap bgcolor="#EBF4F5"  style="padding-right: 45px">接手工程师:</td>
								
								<td width="96%" height="12" valign="top" bgcolor="#FFFFFF" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'><s:property value="serviceTran.usersByServiceTo.truename"></s:property></div>
								</td>
								
							</tr>
							
							<tr>
								<td height="12" valign="top" nowrap bgcolor="#EBF4F5" >转交原因:</td>
								<td height="99%" colspan="3" style="padding: 0px"  bgcolor="#FFFFFF">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
									<tr style="">
										<td height="99%" style="padding-left: 7px; font-weight: normal; line-height: 22px; padding-right: 20px" valign="top" class="td-detail">
											<s:property value="serviceTran.note"></s:property>
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
								<td width="4%" height="12" valign="top" nowrap bgcolor="#EBF4F5"  style="padding-right: 45px">转交时间:</td>
								<td width="96%" height="12" valign="top" bgcolor="#FFFFFF" style="padding-top: 3px; padding-left: 3px" colspan="3">
									<div class='DivOut'><s:date name="serviceTran.operatorTime" format="yyyy-MM-dd HH:mm:ss"></s:date></div>
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
					<td align="center" nowrap style="padding-top: 8px; padding-bottom: 0px" width="99%">
						<input type="button" value="关闭" class=mmBtn name="button" onClick="window.top.close()">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</body>
</html>