<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>查看计划任务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<script type="text/JavaScript" src="../js/Main.js"></script>
		<script type="text/javascript"> 
			function work(flag){
				var val1=document.getElementById("dateStart").value;
				var val2=document.getElementById("dateEnd").value;
				window.location.href="week.action?flag="+flag+"&dateStart="+val1+"&dateEnd="+val2;
				
			}
		</script>
	</head>
	<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					查看计划任务
				</td>
			</tr>
		</table>
		<div>
			<iframe width=168 height=190 name="gfPop" id="gfPop"
				src="../function/calendar/ipopeng.asp" scrolling="no"
				frameborder="0"
				style="border: 1px outset; visibility: visible; z-index: 999; position: absolute; left: -600px; top: 0px;"></iframe>
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td valign="top" id="mainright" height="100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="1"
							height="100%">
							<tr>
								<td colspan="2" height="12"></td>
							</tr>
							<tr>
								<td height="12" colspan="2" valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="20"
												style="font-size: 12px; color: #333333; font-weight: bold;">
												<table cellspacing="0" cellpadding="0" border="0"
													width="100%">
													<tr>
														<td width="49%" height="30" style="padding-top: 7px" nowrap>
															<b><img src="../img/index.jpg" width="20" height="19" align="absmiddle">&nbsp;按周查看计划任务:</b>
														</td>
														<td width="39%" align="right">
															<font color="red"><b><s:property
																		value="message"></s:property>
															</b>
															</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

														</td>
														<td nowrap>															
														</td>
														<td align="right" colspan="2" width="1%" height="28"
															valign="top" style="padding-top: 4px">
															<table border="0" cellspacing="0" cellpadding="0"
																width="100px">
																<tr>
																	<td width="1">
																		<img src="../img/help2.jpg" width="22" height="23" align="absmiddle">&nbsp;&nbsp;&nbsp;
																	</td>
																	<td width="99%"
																		style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"
																		onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()"
																		nowrap>
																		<input type="text" name="thetypes" value="转至..."
																			style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px"
																			readonly>
																	</td>
																	<td width="1%"
																		style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px; PADDING-TOP: 1px">
																		<input type="button" value="6"
																			style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px; width: 21px"
																			onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}"
																			name="click">
																	</td>
																</tr>
																<tr id="thelayer" onClick="this.style.display='none'"
																	style="display: none">
																	<td width="1"></td>
																	<td height="2" colspan="2">
																		<div
																			style="position: absolute; width: 100%; height: 115px; z-index: 9; cursor: default; overflow-x: hidden; border: 1px outset;">
																			<table width="100%" border="0" cellpadding="1"
																				cellspacing="0" bgcolor="white">
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/task.gif"
																							align="absmiddle">
																					</td>
																					<td nowrap
																						onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																						onMouseOut="this.bgColor='white';this.style.color='#333333'"
																						bgcolor="white">
																						&nbsp;<s:property value="dateStart"/>
																						&nbsp;~&nbsp;<s:property value="dateEnd"/>
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/task.gif"
																							align="absmiddle">
																					</td>
																					<td nowrap
																						onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																						onMouseOut="this.bgColor='white';this.style.color='#333333'"
																						bgcolor="white"
																						onClick="javascript:work(-1);">
																						&nbsp;上一周
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/task.gif"
																							align="absmiddle">
																					</td>
																					<td nowrap
																						onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																						onMouseOut="this.bgColor='white';this.style.color='#333333'"
																						bgcolor="white"
																						onClick="javascript:work(1);">
																						&nbsp;下一周
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/task.gif"
																							align="absmiddle">
																					</td>
																					<td nowrap
																						onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																						onMouseOut="this.bgColor='white';this.style.color='#333333'"
																						bgcolor="white"
																						onClick="javascript:work(0);">
																						&nbsp;当前周
																					</td>
																				</tr>
																			</table>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td valign="top" height="99%" colspan="2">
									<table width="100%" border="0" cellpadding="2" cellspacing="1"
										bgcolor="#b5d6e6" class="datagrid">
										<tr>
											<th width="10%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												执行时间
											</th>
											<th width="15%" height="22" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												任务号
											</th>
											<th width="15%" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												任务类别
											</th>
											<th width="30%" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												摘要
											</th>
											<th width="10%" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												申请人
											</th>
											<th width="10%" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												重要程度
											</th>
											<th width="10%" nowrap
												background="../images/main20100521_58.gif"
												class="alllisttitle">
												查看
											</th>
										</tr>
										<s:iterator value="schecualedTaskDetailList" var="stds">											
											<s:iterator var="schedualedTaskDetail">	
												<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor = '#e3f0f7'" onMouseOut="this.bgColor = '#FFFFFF'">
													<td height="30" align="center">
														<s:date name="schedualedTime" format="yyyy-MM-dd"/>
													</td>
													<td height="30" align="center">
														<s:property value="taskCode" />
													</td>
													<td align="center">
														<s:property value="schedualedTasks.serviceCategory.itemZh" />
													</td>
													<td align="center">
														<s:property value="schedualedTasks.keyWord" />
													</td>
													<td align="center">
														<s:property value="schedualedTasks.application" />
													</td>
													<td align="center">
														<s:property value="schedualedTasks.serverity" />
													</td>
													<td align="center" nowrap>
														<a href="#" onClick="window.location.href='show.action?schedualedTaskDetail.id=<s:property value="id" />'"><img src='../img/viewdetail.gif' border=0 width=18 height=18 ></a>
													</td>
												</tr>
											</s:iterator>
											<tr bgcolor="#FFFFFF">
													<td height="30" align="left" colspan="7"/>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<input name="dateStart" id="dateStart" value="<s:property value="dateStart"/>" type="hidden" />
		<input name="dateEnd" id="dateEnd" value="<s:property value="dateEnd"/>" type="hidden" />
	</body>
</html>
