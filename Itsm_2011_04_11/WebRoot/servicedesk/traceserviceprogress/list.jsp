<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>IT Service Desk</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/theme/custo.css">
	</head>

	<body background="../img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; overflow-y: scroll; overflow-x: hidden; padding-right: 7px">
		<div onMouseDown="gfPop.hiddencld()">
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" id="mainright" height="100%">
						<table width="100%" border="0" cellspacing="1" cellpadding="10" height="100%" class="tbBg">
							<tr>
								<th onClick="document.getElementById('thelayer').style.display = 'none'" style="padding: 0px">
									跟踪服务处理进度:
								</th>
							</tr>
							<tr>
								<td height="99%" valign="top" bgcolor="#FFFFFF" style="padding: 0px">
									<table width="100%" height="100%%" border="0" cellpadding="12" cellspacing="0">
										<tr>
											<td height="12" style="padding: 12px; padding-bottom: 0px; padding-top: 7px">
												<table cellspacing=0 cellpadding=0 border=0 width="100%">
													<tr>
														<td nowrap="nowrap">
														</td>
														<td width="99%" onClick="document.getElementById('thelayer').style.display = 'none'">
															&nbsp;
															<b>所有未结束请求:</b>
														</td>
														<td align=right colspan=2 width="1%" height="28" valign="top" style="padding-top: 4px">
															<table border="0" cellspacing="0" cellpadding="0" width="100px">
																<tr>
																	<td width="1">
																		<img width="3">
																	</td>
																	<td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()" nowrap="nowrap">
																		<input type="text" name="thetypes" value="根据状态查看" style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px" readonly="readonly">
																	</td>
																	<td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px; PADDING-TOP: 1px">
																		<input type="button" value="6" style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px; width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}" name="click">
																	</td>
																</tr>
																<tr id="thelayer" onClick="this.style.display='none'" style="display: none">
																	<td width="1"></td>
																	<td height="2" colspan="2">
																		<div style="position: absolute; width: 100%; height: 15px; z-index: 9; cursor: default; overflow-x: hidden; border: 1px outset">
																			<table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/@ServiceInput.gif" align="middle">
																					</td>
																					<td nowrap="nowrap" onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState='">
																						&nbsp;所有未结束请求
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width=18 height=18 src="../img/task.gif" align="middle">
																					</td>
																					<td nowrap="nowrap" onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=DEPT'">
																						&nbsp;新创建
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width=18 height=18 src="../img/page.gif" align=middle border="0">
																					</td>
																					<td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=WAIT'">
																						&nbsp;等待派单
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width="18" height="18" src="../img/ServiceInput.gif" align=middle border="0">
																					</td>
																					<td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=ACT'">
																						&nbsp;工程师未受理
																					</td>
																				</tr>
																				<tr>
																					<td bgcolor="white" width="1%">
																						<img width=18 height=18 src="../img/wait.gif" align=middle border="0">
																					</td>
																					<td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=IDO'">
																						&nbsp;工程师正在处理中
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
										<tr>
											<td valign="top" style="padding: 10px; padding-top: 0px; padding-right: 10px" onClick="document.getElementById('thelayer').style.display = 'none'">
												<table cellspacing=2 cellpadding=3 border=0 width="100%" class="datagrid">
													<tr>
														<th width=1% nowrap="nowrap" class="InTB">
															<font style="cursor: hand">工单号</font>
														</th>
														<th width="20%" nowrap="nowrap" class="InTB">
															服务类别
														</th>
														<th width="12%" nowrap="nowrap" class="InTB">
															<font style="cursor: hand">申请时间 </font>
														</th>
														<th width="12%" nowrap="nowrap" class="InTB">
															<font style="cursor: hand">开始处理时间</font>
														</th>
														<th width="12%" nowrap="nowrap" class="InTB">
															<font style="cursor: hand">承诺完成时间</font>
														</th>
														<th width="12%" title="IT Engineer" nowrap="nowrap"
															class="InTB">
															<font style="cursor: hand">服务工程师</font>
														</th>
														<th width=1% nowrap="nowrap" class="InTB">
															状态
														</th>
														<th width=1% nowrap="nowrap" class="InTB">
															查看
														</th>
														<th width=1% nowrap="nowrap" class="InTB">
															进度
														</th>
													</tr>
													<tr class="td-right-s">
														<td nowrap="nowrap" style="padding-left: 6px; padding-right: 6px" height="27">
															R08000004430
														</td>
														<td valign="top">
															<div class='DivOut'>
																IT报事服务/员工变动IT信息管理/员工离职IT资产审批/
															</div>
														</td>
														<td valign="top">
															<div class='DivOut'>
																2008/6/10 16:11:39
															</div>
														</td>
														<td valign="top">
															<div class='DivOut'>
																2008/7/16 15:06:10
															</div>
														</td>
														<td valign="top">
															<div class='DivOut'>a</div>
														</td>
														<td valign="top">
															<div class='DivOut'>
																张晓曦
															</div>
														</td>
														<td nowrap="nowrap">
															&nbsp;处理中&nbsp;&nbsp;
														</td>
														<td align="center">
															<a href='../prob/?NowAction=detail_cus&RequNo=R08000004430&RequID=4430'>查看<br>
															</a>
														</td>
														<td align=center>
															<a href="../desk/?NowAction=status_cus&RequNo=R08000004430">进度
															</a>
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
			</table>
		</div>
	</body>
</html>