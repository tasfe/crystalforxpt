<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>查看事件</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<script type="text/javascript">
	function comptime(beginTime,endTime){   
		if(!beginTime || !endTime){
			return "";
		}
		var beginTimes=beginTime.split(' ');  
		var endTimes=endTime.split(' '); 
		var nowTimes=getNowDate().split(' ');
		var beginTimesSplit= beginTimes[0].split('-'); 
		var endTimesSplit=endTimes[0].split('-');
		var nowTimesSplit=nowTimes[0].split('-');
		
		beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
		endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
		nowTime=nowTimesSplit[1]+'-'+(nowTimesSplit[2])+'-'+nowTimesSplit[0]+' '+nowTimes[1]; 
		
		var timeout=(Date.parse(endTime)-Date.parse(nowTime))/3600/1000;
		if(timeout<0){
			return "<font style='color:red;'>超时</font>"+"("+timeout.toFixed().substring(1,timeout.toFixed().length)+")";
		}
		var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
		return timeGap<=0?"":timeGap.toFixed();   
	}
	function getNowDate(){//获取当前时间(服务器时间)
		return "<%=(request.getAttribute("nowDate"))%>";
	}
	function setVisible(){
		var visible='${visible}';
		if(visible){
			if(visible=='false'){
				document.getElementById('toBeAssigned').style.display='none';
				if(document.getElementById('toBeAssignedList')){
					//document.getElementById('toBeAssignedList').style.display='none';
				}
				if(document.getElementById('toBeAssignedMore')){
					//document.getElementById('toBeAssignedMore').style.display='none';
				}
			}
		}
	}
	</script>
	<body leftmargin="2" topmargin="0" marginwidth="0" marginheight="0"
		style="padding-right: 1px; padding-bottom: 2px; padding-top: 2px" onLoad="setVisible();">
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0">
			<tr>
				<td valign="top" id="mainright" height="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="1">
						<tr>
							<td width="1%" height="22" align="center"
								background="../images/main20100521_582.gif"
								style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
								<img src="../images/modpass.gif" width="16" height="16">
							</td>
							<td width="98%" background="../images/main20100521_582.gif"
								style="color: #FFFFFF; font-weight: bold;">
								我的工作
							</td>
						</tr>
						<tr>
							<td colspan="2" height="2"></td>
						</tr>
						<tr>
							<td valign="top" colspan="2">
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td nowrap>
											<table cellspacing=0 bgcolor="#b5d6e6" cellpadding=4 border=0
												width="100%" style="border: 1px outset white">
												<tr>
													<td class="td-right" id="TongJi" style="padding: 8px">
														<table width="100%" bgcolor="#b5d6e6" border="0"
															cellspacing="6" cellpadding="1">
															<tr>
																<td align="center" bgcolor="#b5d6e6" id="toBeAssigned">
																	待指派服务:
																	<a><br>
																		<br>
																		<font size="3" color="red"><b><s:property
																					value="statistic0"></s:property>
																		</b>
																	</font>
																	<br>
																	</a>
																</td>
																<td align="center" bgcolor="#b5d6e6">
																	待受理服务:
																	<a><br>
																		<br>
																		<font size="3" color="red"><b><s:property
																					value="statistic1"></s:property>
																		</b>
																	</font>
																	<br>
																	</a>
																</td>
																<td align="center" bgcolor="#b5d6e6">
																	处理中的服务:
																	<a><br>
																		<font size="3" color="red"><b><br>
																				<s:property value="statistic2"></s:property>
																		</b>
																	</font>
																	<br>
																	</a>
																</td>
																<!--  
																<td align="center" bgcolor="#b5d6e6">
																	待处理问题:
																	<a><br>
																		<br>
																		<font size="3" color="red"><b>0</b>
																	</font>
																	<br>
																	</a>
																</td>
																<td align="center" bgcolor="#b5d6e6">
																	待实施变更:
																	<a><br>
																		<br>
																		<font size="3" color="red"><b>0</b>
																	</font>
																	<br>
																	</a>
																</td>
																-->
																<td align="center" bgcolor="#b5d6e6"
																	style="border-right: 0px">
																	计划任务:
																	<a><br>
																		<br>
																		<font size="4" color="red"><b><s:property value="statisticTask"></s:property></b>
																	</font>
																	<br>
																	</a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="18">
											<iframe width=0 height=0 name="sum" id="sum"></iframe>
											


										</td>
									</tr>
								</table>
								<table align="center" cellspacing=0 cellpadding=4 border=0
									width="100%" class="datagrid" style="border: 1px outset white"
									bgcolor="#b5d6e6">
									<tr id="tab1">
										<th width="15%" nowrap class="mmBtn"
											style="text-align: center">
											服务单号
										</th>
										<th width="10%" nowrap class="mmBtn"
											style="text-align: center">
											服务类别
										</th>
										<th width="5%" nowrap class="mmBtn" style="text-align: center">
											优先级
										</th>
										<th width="12%" nowrap class="mmBtn"
											style="text-align: center">
											申请时间
										</th>
										<th width="12%" nowrap class="mmBtn"
											style="text-align: center">
											希望完成时间
										</th>
										<th width="15%" nowrap class="mmBtn"
											style="text-align: center">
											剩余时间(小时)
										</th>
										<th width="5%" nowrap class="mmBtn" style="text-align: center">
											申请人
										</th>
										<th width="5%" nowrap class="mmBtn" style="text-align: center">
											查看
										</th>
									</tr>

									<tr id="tab11" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>需要您反馈并关闭的服务请求:<b />
										</td>
									</tr>
									<s:iterator value="list5" var="serviceRequest" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'">
												<td height="29">
													<script>
document.getElementById('tab11').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="requestNo" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="priorityLvl" />
												</td>
												<td align="center">
													<s:date name="submintTime" />
												</td>
												<td align="center">
													<s:date name="expectTime" />
												</td>
												 <td align="center">
									           		<script type = "text/javascript" >    
									           			var submitTime="<s:date name="submintTime"/>";
									           			var expectTime="<s:date name="expectTime"/>"; 
									      				document.write(comptime(submitTime,expectTime));
									     			</script>
												</td>
												<td align="center">
													<s:property value="usersByRequestUser.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18>
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="list5.size>5">
										<tr onMouseOver="this.bgColor='#e3f0f7'"
											onMouseOut="this.bgColor='#FFFFFF'">
											<td height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=5" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>

									<tr id="tab111" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>需要您指派任务的服务请求:</b>
										</td>
									</tr>
									<s:iterator value="list0" var="serviceRequest" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'" id="toBeAssignedList">
												<td height="29">
													<script>
document.getElementById('tab111').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="requestNo" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="priorityLvl" />
												</td>
												<td align="center">
													<s:date name="submintTime" />
												</td>
												<td align="center">
													<s:date name="expectTime" />
												</td>
												 <td align="center">
									           		<script type = "text/javascript" >    
									           			var submitTime="<s:date name="submintTime"/>";
									           			var expectTime="<s:date name="expectTime"/>"; 
									      				document.write(comptime(submitTime,expectTime));
									     			</script>
												</td>
												<td align="center">
													<s:property value="usersByRequestUser.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18>
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="list0.size>5">
										<tr id="toBeAssignedMore">
											<td colspan="8" height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=0" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>

									<tr id="tab12" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>需要您重新激活的服务请求:</b>
										</td>
									</tr>
									<s:iterator value="list7" var="serviceRequest" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'">
												<td height="29">
													<script>
document.getElementById('tab12').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="requestNo" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="priorityLvl" />
												</td>
												<td align="center">
													<s:date name="submintTime" />
												</td>
												<td align="center">
													<s:date name="expectTime" />
												</td>
												 <td align="center">
									           		<script type = "text/javascript" >    
									           			var submitTime="<s:date name="submintTime"/>";
									           			var expectTime="<s:date name="expectTime"/>"; 
									      				document.write(comptime(submitTime,expectTime));
									     			</script>
												</td>
												<td align="center">
													<s:property value="usersByRequestUser.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18>
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="list7.size>5">
										<tr>
											<td colspan="8" height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=7" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>

									<tr id="tab13" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>您正在处理中的服务请求:</b>
										</td>
									</tr>
									<s:iterator value="list2" var="serviceRequest" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'">
												<td height="29">
													<script>
document.getElementById('tab13').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="requestNo" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="priorityLvl" />
												</td>
												<td align="center">
													<s:date name="submintTime" />
												</td>
												<td align="center">
													<s:date name="expectTime" />
												</td>
												 <td align="center">
									           		<script type = "text/javascript" >    
									           			var submitTime="<s:date name="submintTime"/>";
									           			var expectTime="<s:date name="expectTime"/>"; 
									      				document.write(comptime(submitTime,expectTime));
									     			</script>
												</td>
												<td align="center">
													<s:property value="usersByRequestUser.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18 />
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="list2.size>5">
										<tr>
											<td colspan="8" height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=2" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>

									<tr id="tab14" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>需要您受理的服务请求:</b>
										</td>
									</tr>
									<s:iterator value="list1" var="serviceRequest" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'">
												<td height="29">
													<script>
document.getElementById('tab14').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="requestNo" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="priorityLvl" />
												</td>
												<td align="center">
													<s:date name="submintTime" />
												</td>
												<td align="center">
													<s:date name="expectTime" />
												</td>
												 <td align="center">
									           		<script type = "text/javascript" >    
									           			var submitTime="<s:date name="submintTime"/>";
									           			var expectTime="<s:date name="expectTime"/>"; 
									      				document.write(comptime(submitTime,expectTime));
									     			</script>
												</td>
												<td align="center">
													<s:property value="usersByRequestUser.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18>
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="list1.size>5">
										<tr>
											<td colspan="8" height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=1" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>

									<s:if test="listTask.size>0">
										<tr id="tab2" style="display: ">
											<th width="15%" nowrap class="mmBtn"
												style="text-align: center">
												任务单号
											</th>
											<th width="10%" nowrap class="mmBtn"
												style="text-align: center">
												任务类别
											</th>
											<th width="5%" nowrap class="mmBtn"
												style="text-align: center">
												重要程度
											</th>
											<th width="12%" nowrap class="mmBtn"
												style="text-align: center">
												摘要
											</th>
											<th width="12%" nowrap class="mmBtn"
												style="text-align: center">
												执行时间
											</th>
											<th width="15%" nowrap class="mmBtn"
												style="text-align: center">
												剩余时间
											</th>
											<th width="5%" nowrap class="mmBtn"
												style="text-align: center">
												负责人
											</th>
											<th width="5%" nowrap class="mmBtn"
												style="text-align: center">
												查看
											</th>
										</tr>
									</s:if>
									<tr id="tab21" style="display: none">
										<td colspan="8" class="menu">
											<img src="../img/index.jpg" align="absMiddle">
											<b>近期需要您执行的计划任务:<b />
										</td>
									</tr>
									<s:iterator value="listTask" var="task" status='st'>
										<s:if test="#st.index<5">
											<tr onMouseOver="this.bgColor='#e3f0f7'"
												onMouseOut="this.bgColor='#FFFFFF'">
												<td height="29">
													<script>
document.getElementById('tab21').style.display = '';</script>
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td width="1%">
																<img src="../img/incident.gif"
																	style="background-color: ">
															</td>
															<td width="87%" nowrap>
																&nbsp;&nbsp;
																<s:property value="taskCode" />
																&nbsp;&nbsp;
															</td>
														</tr>
													</table>
												</td>
												<td align="center">
													<s:property value="schedualedTasks.serviceCategory.itemZh" />
												</td>
												<td align="center">
													<s:property value="schedualedTasks.serverity" />
												</td>
												<td align="center">
													<s:property value="schedualedTasks.keyWord" />
												</td>
												<td align="center">
													<s:date name="schedualedTime" format="yyyy-MM-dd" />
												</td>
												<td align="center">
													N/A
												</td>
												<td align="center">
													<s:property value="schedualedTasks.user.truename" />
												</td>
												<td align=center nowrap>
													<a
														href="../schedualedtask/show.action?schedualedTaskDetail.id=<s:property value="id" />"><img
															src='../img/viewdetail.gif' border=0 width=18 height=18>
													</a>
												</td>
											</tr>
										</s:if>
									</s:iterator>
									<s:if test="listTask.size>5">
										<tr>
											<td colspan="8" height="29">
												<a href="${pageContext.request.contextPath }/engineertrace/morelist.action?state=-1" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</a>
											</td>
										</tr>
									</s:if>
									<!--	<tr id="tab2">
								<th width="1%" nowrap>任务单号</th>
								<th width="15%" nowrap>任务类别</th>
								<th width="10%" nowrap onClick="window.location='../sla/?NowAction=mylist&Sort=1'" style="cursor: hand">优先级</th>
								<th width="12%" nowrap>摘要</th>
								<th width="12%" nowrap onClick="window.location='../sla/?NowAction=mylist'" style="cursor: hand">执行时间<img src='../img/rank_down.gif' border=0></th>
								<th width="15%" nowrap>剩余时间</th>
								<th width="13%" nowrap>责任人</th>
								<th width=1% nowrap style="text-align: center">查看</th>
							</tr>
							
							<tr id="tab3">
								<th width="1%" nowrap>问题单号</th>
								<th width="15%" nowrap>问题类别</th>
								<th width="10%" nowrap onClick="window.location='../sla/?NowAction=mylist&Sort=1'" style="cursor: hand">优先级</th>
								<th width="12%" nowrap>申报时间</th>
								<th width="12%" nowrap onClick="window.location='../sla/?NowAction=mylist'" style="cursor: hand">须完成时间<img src='../img/rank_down.gif' border=0></th>
								<th width="15%" nowrap>剩余时间</th>
								<th width="13%" nowrap>责任人</th>
								<th width=1% nowrap style="text-align: center">查看</th>
							</tr>
							
							<tr id="tab4">
								<th width="1%" nowrap>变更单号</th>
								<th width="15%" nowrap>变更类别</th>
								<th width="10%" nowrap onClick="window.location='../sla/?NowAction=mylist&Sort=1'" style="cursor: hand">优先级</th>
								<th width="12%" nowrap>申请时间</th>
								<th width="12%" nowrap onClick="window.location='../sla/?NowAction=mylist'" style="cursor: hand">希望完成时间</th>
								<th width="15%" nowrap>剩余时间</th>
								<th width="13%" nowrap>实施人</th>
								<th width=1% nowrap style="text-align: center">查看</th>
							</tr>
							
						 	<tr>
								<td colspan="8" class="menu"><img src="../img/mywork.gif" align="absMiddle">需要您发布的变更:</td>
							</tr>
							
							<tr>
								<td height="29">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="1%"><img src="../img/incident.gif" style="background-color: "></td>
											<td width="87%" nowrap>&nbsp;&nbsp;RFC08000004451&nbsp;&nbsp;</td>
										</tr>
									</table>
								</td>
								<td>硬件/服务器/服务器硬盘扩容/</td>
								<td>4</td>
								<td>2008-7-11 15:06:51</td>
								<td></td>
								<td>N/A</td>
								<td>张晓曦</td>
								<td align=center nowrap><a onClick=" "></a></td>
							</tr>
							
							<tr id="tab5">
								<th width="1%" nowrap>项目单号</th>
								<th width="15%" nowrap>项目或任务名</th>
								<th width="10%" nowrap onClick="window.location='../sla/?NowAction=mylist&Sort=1'" style="cursor: hand">优先级</th>
								<th width="12%" nowrap>创建时间</th>
								<th width="12%" nowrap onClick="window.location='../sla/?NowAction=mylist'" style="cursor: hand">计划结束时间<img src='../img/rank_down.gif' border=0></th>
								<th width="15%" nowrap>剩余时间</th>
								<th width="13%" nowrap>主管人</th>
								<th width=1% nowrap style="text-align: center">查看</th>
							</tr>
							
							<tr id="tab6">
								<th width="1%" nowrap>工单号</th>
								<th width="35%" nowrap colspan="3">标题或摘要</th>
								<th width="15%" nowrap>类别</th>
								<th width="15%" nowrap>创建时间</th>
								<th width="13%" nowrap>创建人</th>
								<th width=1% nowrap style="text-align: center">编辑</th>
							</tr>
			 -->
								</table>
							</td>
						</tr>
					</table>
					<table cellspacing=0 cellpadding=0 border=0 width="100%">
						<tr>
							<td width="99%" valign="bottom"
								style="padding-bottom: 2px; padding-left: 1px">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td nowrap
											style="padding: 2px; padding-left: 0px; padding-top: 3px"
											valign="bottom">

										</td>
									</tr>
								</table>
							</td>
							<td align=right nowrap width="1%"
								style="padding-top: 5px; padding-bottom: 2px; padding-right: 1px">
								<!--	<input type="button" class="mmBtn" value="增加任务提示" onClick="window.open('../task/?NowAction=newnote','','width=500,height=300,scrollbars=no,menubar=no,resizable=no,top=180,left=186,status=yes')">
						<input type="button" class="mmBtn" value="查看我的个人通知" onClick="window.open('../chan/?NowAction=pnotice','','width=600,height=450,scrollbars=no,menubar=no,resizable=no,top=80,left=126,status=yes')">
						<input type="button" class="mmBtn" value="申请告假" onClick="window.open('../task/?NowAction=askfl','','width=500,height=300,scrollbars=no,menubar=no,resizable=no,top=180,left=186,status=yes')">
					  -->
								<input type="button" class="mmBtn" value="刷新"
									onClick="window.location.reload()">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--
<script>
	document.getElementById('tab1').style.display='';
	document.getElementById('tab2').style.display='none';
	document.getElementById('tab3').style.display='none';
	document.getElementById('tab5').style.display='none';
	document.getElementById('tab6').style.display='none';
	document.getElementById('TongJi').innerHTML=document.getElementById('TongJi_Value').innerHTML;
</script>    -->
		<script language="Javascript" src="../cn_css/mmBtn.js">
</script>
	</body>
</html>

