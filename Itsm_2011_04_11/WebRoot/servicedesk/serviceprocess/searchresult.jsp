<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>IT经理服务进度管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${pageContext.request.contextPath }/css/style.css"
			rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					IT经理服务进度管理
				</td>
			</tr>
		</table>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/wpCalendar.js"></script>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="99" height="2"></td>
				</tr>
				<tr
					onClick="document.getElementById('thelayer').style.display='none'">
					<td height="12">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="query" namespace="/serviceprocess"
								method='post' theme="simple">
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										工单号:&nbsp;
									</td>
									<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
									<s:textfield id="serviceRequest.requestNo" name="serviceRequest.requestNo" style="width:100%"/>
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										申请人:&nbsp;
									</td>
									<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="serviceRequest.usersByRequestUser.truename"
											id="serviceRequest.usersByRequestUser.truename" style="width: 100%">
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										摘要或描述:&nbsp;
									</td>
									<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="serviceRequest.summary" id="serviceRequest.summary" style="width: 100%; cursor: text">
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										自定义条件:&nbsp;
									</td>
									<td width="22%" bgcolor="#FFFFFF">
										<select name="RequPeop" id="RequPeop" style="width: 100%">
											<option value=""></option>
											<option value="And_FieldName_$_XXXXX">
												打印
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										服务类别:&nbsp;
									</td>
									<td bgcolor="#FFFFFF" style="padding-right: 10px">
										<input type="text" name="Catename"
											style="width: 100%; cursor: text" readonly value=""
											onClick="this.value='';document.getElementById('RequObj').value='';document.getElementById('Layer2').style.visibility='visible'">
										<input name="RequObj" id="RequObj" value="" type="hidden">
										<div id="Layer2"
											style="position: absolute; width: 180%; height: 20px; z-index: 99; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
											<iframe frameborder="0" height="150" width="100%"
												scrolling="yes"
												src="../home/?NowAction=aptree&ID=Service_cat&Sear=1&Lct=Inci"
												style="border: 1px solid #E5E9EE"></iframe>
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										工程师:&nbsp;
									</td>
									<td bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="serviceRequest.dealEngineers" id="serviceRequest.dealEngineers" value=""
											style="width: 100%">
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										申请时间:&nbsp;
									</td>
									<td nowrap bgcolor="#FFFFFF" style="padding-right: 10px">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="49%" nowrap>
													<input type="text" name="sDate" id="d11"
														onClick="WdatePicker()" />
													<img onClick="WdatePicker({el:$dp.$('d11')})"
														src="${pageContext.request.contextPath }/js/DatePicker/skin/datePicker.gif"
														width="16" height="22" />
												</td>
												<td width="2%">
													~
												</td>
												<td width="49%" nowrap>
													<input type="text" name="sDate" id="d11"
														onClick="WdatePicker()" />
													<img onClick="WdatePicker({el:$dp.$('d11')})"
														src="${pageContext.request.contextPath }/js/DatePicker/skin/datePicker.gif"
														width="16" height="22" />
												</td>
											</tr>
										</table>
									</td>
									<td width="2%" bgcolor="#deebf1">
										<input type="submit" style="height: 20px" class="mmBtn"
											value="搜索" />
									</td>
								</tr>
							</s:form>
						</table>
					</td>
				</tr>

				<tr>
					<td height="12">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="12">
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="1" bgcolor="#b5d6e6">
							<tr>
								<td width="86%" height="3" valign="middle"
									background="../images/main20100521_58.gif"
									onClick="document.getElementById('thelayer').style.display='none'">
									<table width="50%" border=0 cellpadding=0 cellspacing=0>
										<tr>
											<td width="2%" align="center"
												background="../images/main20100521_58.gif"
												style="color: #FFFFFF; font-weight: bold; padding-right: 5px; padding-left: 5px;">
												<img src="../images/modpass.gif" width="16" height="16">
											</td>
											<td width="98%" style="color: #333333; font-weight: bold;">
												所有未关闭的服务请求: 
											</td>
										</tr>
									</table>
								</td>
								<td width="14%" height="22" colspan="2" align="right"
									valign="top" background="../images/main20100521_58.gif">
									<table border="0" cellspacing="0" cellpadding="0" width="100px">
										<tr>
											<td width="1"></td>
											<td width="99%"
												style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"
												onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()"
												nowrap>
												<input type="text" name="thetypes" value="根据状态查看"
													style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px"
													readonly>
											</td>
											<td width="1%"
												style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px;">
												<input type="button" value="6"
													style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; height: 17px; line-height: 9px; width: 21px"
													onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}"
													name="click">
											</td>
										</tr>
										<tr id="thelayer" onClick="this.style.display='none'"
											style="display: none">
											<td width="1"></td>
											<td height="2" colspan="2">
												<div
													style="position: absolute; width: 100%; height: 115px; z-index: 9; cursor: default; overflow-x: hidden; border: 1px outset">
													<table width="100%" border="0" cellpadding="1"
														cellspacing="0" bgcolor="white">
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/ServiceInput.gif"
																	align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState='">
																&nbsp;所有未关闭请求
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/task.gif"
																	align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=DEPT&RequItem=0'">
																&nbsp;新创建
															</td>
														</tr>

														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/task.gif"
																	align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=DEPT&RequItem=165'">
																&nbsp;部门领导审批完成
															</td>
														</tr>

														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/page1.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=WAIT'">
																&nbsp;等待派单
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/ico_t1.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=PED'">
																&nbsp;暂时挂起中
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/ServiceInput.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=ACT'">
																&nbsp;等待工程师受理
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/wait.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=IDO'">
																&nbsp;工程师正在处理中
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/delete.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=REF'">
																&nbsp;已经被拒绝
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="${pageContext.request.contextPath }/img/pageok.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='../desk/?NowAction=servicesearch&RequDept=&RequPeop=&RequNo=&LoginUser=&RequObj=&CateName=&DeptName=&ITprinc=&RequTime1=&RequTime2=&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&Width=14,10,12,18,12,8,@10,&Sort=ID&Desc=Desc&RecState=FIN'">
																&nbsp;等待用户反馈
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
				<tr
					onClick="document.getElementById('thelayer').style.display='none'">
					<td valign="top">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<tr>
								<td width="17%" height="22" align="center" nowrap
									bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('RequNo','DESC');">工单号</font>
								</td>
								<td width="6%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('RequObj','DESC');">服务类别</font>
								</td>

								<td width="5%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('RequPeop','DESC');">申请人</font>
								</td>

								<td width="12%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('RequTrueTime','DESC');">申请时间</font>
								</td>

								<td width="15%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('ServiceTitle','DESC');">摘要</font>
								</td>

								<td width="12%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('NeedTime','DESC');">承诺完成时间</font>
								</td>

								<td width="8%" align="center" nowrap bgcolor="#deebf1">
									<font style='cursor: hand'
										onclick="SeekOnClick('ITPrinc','DESC');">工程师</font>
								</td>


								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									<font style='cursor: hand'
										onclick="SeekOnClick('State','DESC');">处理进度</font>
								</td>
								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									查看
								</td>

								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									干预
								</td>
							</tr>
							<s:iterator value="serviceRequestList" var="serviceRequest">
								<tr onMouseOver="this.bgColor='#e3f0f7'"
									onMouseOut="this.bgColor='#FFFFFF'">
									<td height="30" align="center" valign="middle"
										bgcolor="#FFFFFF">
										<s:property value="requestNo" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="serviceCategory.itemZh" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="usersByRequestUser.truename" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="submintTime" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="summary" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="promiseTime" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="userByServiceFrom.username" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:if test="#serviceRequest.state==0">待受理</s:if>
										<s:elseif test="#serviceRequest.state==1">正在处理中</s:elseif>
										<s:elseif test="#serviceRequest.state==2">流程进行中</s:elseif>
										<s:elseif test="#serviceRequest.state==3">未受理</s:elseif>
										<s:elseif test="#serviceRequest.state==4">已完成</s:elseif>
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<a href="show.action?serviceRequest.id=<s:property value="id" />">
										<img src='../img/zjz.gif' border=0 width=18 height=18>
										</a>
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<a href="interposeShow.action?serviceRequest.id=<s:property value="id" />">
										<img src='../img/zjz.gif' border=0 width=18 height=18>
										</a>
									</td>
								</tr>
							</s:iterator>
						</table>
						<table cellspacing="0" cellpadding="0" border="0" width="100%">
							<tr>
								<td height="30" align="center" nowrap>
									<input type="button" class="mmBtn" value="查看报表"
										onClick="window.location='../kpi/?NowAction=report_list&type=Inci&Title=事件或服务统计'">
									<input type="button" class="mmBtn" value="自定义"
										onClick="window.open('../kpi/?NowAction=favlist&Type=Inci&Url=../desk/%3FNowAction%3Dservicesearch%26RequObj%3D&Chosed=RequObj,RequPeop,RequTrueTime,ServiceTitle,NeedTime,ITPrinc,NeedTime,&Width=14,10,12,18,12,8,@10,&ChosedName=服务类别%2C申请人%2C申请时间%2C摘要%2C承诺完成时间%2C工程师%2C剩余时间%2C&List=Inci','','width=400,height=350,scrollbars=yes,menubar=no,resizable=no,top=60,left=100,status=yes')">
									<input type="button" class="mmBtn" value="后退"
										onClick="history.go(-1)">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>

