<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

	<head>
		<title>历史记录查询</title>

		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">

		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					历史记录查询
				</td>
			</tr>
		</table>

		<div
			style="position: absolute; overflow-x: scroll; overflow-y: scroll; width: 100%; padding-top: 5px;">

			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td valign="top" id="mainright" height="100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="1"
							height="100%">
							<tr>
								<td colspan="2" height="2"></td>
							</tr>

							<tr>
								<td height="12" colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="2"
										class="tb-list" style="border: 1px outset white">
										<tr>
											<td class="td-right" width="100%" style="padding-left: 3px">

												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgcolor="#b5d6e6">

													<tr>
														<td align="center" width="2%" nowrap bgcolor="#deebf1">
															内容描述:&nbsp;
														</td>
														<td bgcolor="#FFFFFF" width="23%"
															style="padding-right: 10px">
															<input name="content" type="text" size="40"
																style="width: 100%">
														</td>

														<td width="2%" align="center" nowrap bgcolor="#deebf1">
															项目类别:&nbsp;
														</td>
														<td width="23%" bgcolor="#FFFFFFF"
															style="padding-right: 10px">
															<input name="projecttype" type="text" size="40"
																style="width: 100%">
														</td>
														<td nowrap class="td-left" width=2%>
															项目号:&nbsp;
														</td>
														<td class="td-detail" width="23%"
															style="padding-right: 20px">
															<input Name="ProNo" ID="ProNo" value=""
																style="width: 100%">
														</td>
														<td class="td-detail" width="2%">
															<input type="button" style="height: 20px" class="mmBtn"
																value="搜索" onclick="">
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>

							<tr>
								<td width="60%" height="30" style="padding-top: 10px"
									onClick=document.getElementById(
									'thelayer').style.display='none';>
									<img src="../images/main20100521dot04.gif" />
									&nbsp;
									<b>项目列表:</b>&nbsp;
								</td>
								<td width="39%" align="right">
									<font color="red"><b><s:property value="message"></s:property>
									</b> </font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								
							</tr>

							<tr>
								<td valign="top" colspan="2">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										bgcolor="#b5d6e6" class="datagrid">
										<tr>
											<th width="9%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												项目号
											</th>
											<th width="10%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												项目类别
											</th>
											<th width="18%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												项目名称
											</th>
											<th width="10%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												项目主管
											</th>
											<th width="5%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												所属地区
											</th>
											<th width=1% height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												查看
											</th>
										</tr>
										<s:iterator value="pageBean.list" id="u">
											<tr bgcolor="#FFFFFF" onMouseOver=this.bgColor
												= '#e3f0f7';
onMouseOut=this.bgColor= '#FFFFFF';
>
												<td align="left">
													<s:property value="#u.id" />
												</td>
												<td align="center">
													<s:property value="#u.projectType" />
												</td>
												<td height="30" align="center">
													<s:property value="#u.projectName" />
												</td>
												<td height="30" align="center">
													<s:property value="#u.projectManager" />
												</td>
										</s:iterator>

									</table>

									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td nowrap width="50%"
												style="padding-top: 6px; padding-bottom: 1px">
												&nbsp;
											</td>
											<td align=right nowrap width="50%"
												style="padding-top: 6px; padding-bottom: 1px">
												<input type="button" class="mmBtn" value="查看报表">
												<input type="button" class="mmBtn" value="后退" onclick="window.history(-1);"/>
											</td>
										</tr>
									</table>

								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<s:form name="form" method="post"
				action="/ProjectManage/queryProject.action" theme="simple">
				<s:hidden id="page" name="page"></s:hidden>
			</s:form>
			<jsp:include page="/common/page.jsp" />
		</div>

	</body>
</html>

