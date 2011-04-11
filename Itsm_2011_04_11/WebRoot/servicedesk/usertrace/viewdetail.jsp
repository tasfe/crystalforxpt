<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)"
	for="window">
return true;</script>

<html>
	<head>
		<title>IT Service Desk</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" language="javascript">
function Notice() {

}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					用户跟踪请求处理情况:
				</td>
			</tr>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" height="100%" border="0" cellpadding="4"
							cellspacing="1" bgcolor="#FFFFFF">
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									工&nbsp;&nbsp;单&nbsp;&nbsp;号:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail">
									<s:property value="serviceRequest.requestNo" />
								</td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail">
									<s:date name="serviceRequest.submintTime" />
								</td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									申请人全名:
								</td>
								<td width="33%" bgcolor="#EBF4F5" class="td-detail">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										onClick="window.open('showUserInfo.action?userId=${serviceRequest.usersByRequestUser.id}','','width=600,height=500,scrollbars=no,menubar=no,resizable=no,top=100,left=150,status=yes')"
										style="cursor: hand">
										<tr>
											<td>
												<b><s:property
														value="serviceRequest.usersByRequestUser.truename" /> </b>
											</td>
											<td width="1%">
												<img src="../img/viewdetail.gif" alt="查看详细">
											</td>
										</tr>
									</table>
								</td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									所属类别:
								</td>
								<td valign="top" bgcolor="#EBF4F5">
									<div class='DivOut'
										style="behavior: url(../cn_css/datagrid_div.htc)">
										<s:property value="serviceRequest.serviceCategory.itemZh" />
								</td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									提&nbsp;&nbsp;交&nbsp;&nbsp;者:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail">
									<s:property value="serviceRequest.usersByRequestUser.name" />
								</td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									工&nbsp;&nbsp;程&nbsp;&nbsp;师:
								</td>
								<td valign="top" bgcolor="#EBF4F5">
									<s:property value="usersByEngineerId.name" />
								</td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left"
									style="line-height: 22px; padding-bottom: 60px">
									详细描述:
								</td>
								<td height="45%" colspan=5 valign="top" bgcolor="#EBF4F5"
									class="td-detail"
									style="font-weight: normal; line-height: 22px">
									<s:property value="serviceRequest.description" />
								</td>
							</tr>
							<tr>
								<td nowrap bgcolor="#b5d6e6" class="td-left"
									style="line-height: 22px; padding-bottom: 60px">
									相关附件:
								</td>
								<td height="45%" colspan=5 valign="top" bgcolor="#EBF4F5"
									class="td-detail"
									style="font-weight: normal; line-height: 22px">
									<table width="100%" height="100%" border="0" cellpadding="4"
										cellspacing="1" bgcolor="#FFFFFF">
										<s:iterator value="accessoryList" var="accesory">
											<tr>
												<a href="down.action?dlFileName=<s:property value='name'></s:property>">
													<s:property value="trueName"></s:property> </a>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

<%--
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center"
						background="../../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold;">
						处理结果:
					</td>
				</tr>
			</table>
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" height="100%" border="0" cellpadding="4"
							cellspacing="1" bgcolor="#FFFFFF">
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									工程师:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail"></td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									事故原因:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail"></td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									开始时间:
								</td>
								<td width="33%" bgcolor="#EBF4F5" class="td-detail">&nbsp;
									

								</td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									结束时间:
								</td>
								<td valign="top" bgcolor="#EBF4F5">
									<div class='DivOut'
										style="behavior: url(../cn_css/datagrid_div.htc)">
								</td>
							</tr>
							<tr>
								<td nowrap bgcolor="#b5d6e6" class="td-left">
									处理时长:
								</td>
								<td nowrap bgcolor="#EBF4F5" class="td-detail">&nbsp;
									

								</td>
								<td nowrap bgcolor="#b5d6e6" class="td-left">
									延误时长:
								</td>
								<td valign="top" bgcolor="#EBF4F5">&nbsp;
									

								</td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									处理总时长:
								</td>
								<td width="33%" nowrap bgcolor="#EBF4F5" class="td-detail"></td>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									状态:
								</td>
								<td valign="top" bgcolor="#EBF4F5"></td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left"
									style="line-height: 22px; padding-bottom: 60px">
									解决方案:
								</td>
								<td height="90%" colspan=5 valign="top" bgcolor="#EBF4F5"
									class="td-detail"
									style="font-weight: normal; line-height: 22px"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>--%>

<%--			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center"
						background="../../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold;">
						用户反馈:
					</td>
				</tr>
			</table>
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" height="100%" border="0" cellpadding="4"
							cellspacing="1" bgcolor="#FFFFFF">
							<tr>
								<td nowrap bgcolor="#b5d6e6" class="td-left">
									满意度:
								</td>
								<td nowrap bgcolor="#EBF4F5" class="td-detail">&nbsp;
									

								</td>
							</tr>
							<tr>
								<td width="17%" nowrap bgcolor="#b5d6e6" class="td-left">
									用户建议:
								</td>
								<td nowrap bgcolor="#EBF4F5" class="td-detail"></td>
							</tr>
						</table>--%>
			
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center"
						background="../../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold;">
						相关操作记录:
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="5" cellspacing="1"
				bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#FFFFFF">
								<td height="22" align="center"
									background="../../images/main20100521_58.gif"
									class="alllisttitle">
									时间
								</td>
								<td align="center" background="../../images/main20100521_58.gif"
									class="alllisttitle">
									内容
								</td>
								<td align="center" background="../../images/main20100521_58.gif"
									bgcolor="#FFFFFF" class="alllisttitle">
									操作人
								</td>
								<td align="center" background="../../images/main20100521_58.gif"
									class="alllisttitle">
									查看
								</td>
							</tr>
							<s:iterator value="serviceTranList">
								<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
									onMouseOut="this.bgColor='#FFFFFF'">

									<td height="20" align="center">
										<s:date name="operatorTime" format="yyyy/MM/dd HH:mm:ss" />
									</td>
									<td align="center">
										<s:property value="type" />
									</td>
									<td align="center">
										<s:property value="users" />
									</td>
									<td align="center">
										<a
											onClick="'','','width=560,height=440,scrollbars=yes,menubar=no,resizable=yes,top=80,left=176,status=yes')"
											style="cursor: hand"
											href="../userrequesthistory/propressInfo.action?requestNo=${serviceRequest.requestNo}"><img
												src="../img/viewdetail.gif" alt="查看详细"> </a>
									</td>

								</tr>
							</s:iterator>
						</table>
						<table cellspacing=0 cellpadding=0 border=0 width="100%">
							<tr>
								<td align=center nowrap
									style="padding-top: 8px; padding-bottom: 0px">
									<input name="button" type="button" class="mmBtn"
										onClick="window.location='propressInfo.action?requestNo=${serviceRequest.requestNo}'"
										value="查看进度">
									<%--<input type="button" class="mmBtn" value="流程"
										onClick="window.open('viewProcess.jsp','new','width=900,height=440,scrollbars=no,menubar=no,resizable=yes,top=80,left=176,status=yes')">
								--%></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
		</div>
	</body>
 </html>
