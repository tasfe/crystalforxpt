<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function comptime(beginTime,endTime){   
	if(!beginTime || !endTime){
		return "";
	}
	var beginTimes=beginTime.split(' ');  
	var endTimes=endTime.split(' '); 
	
	var beginTimesSplit= beginTimes[0].split('-'); 
	var endTimesSplit=endTimes[0].split('-');
	
	beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
	endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
	var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
	return timeGap<0?"":timeGap.toFixed();   
}
</script>


<table width="100%" border="1" cellpadding="1" cellspacing="0" height="100%">
	<input id="tmp" value="so" type="hidden">
	<tr>
		<td style="padding: 10px; line-height: 10px" bgcolor="#FFFFFF">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
				<tr>
					<td height="99%" colspan="2">
						<table width="100%" height="99%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
							<tr>
								<td width="14%" bgcolor="#EBF4F5">工单号:</td>
								<td width="19%" bgcolor="#FFFFFF">
									<s:property value="serviceRequest.requestNo"></s:property>
								</td>
								<td width="14%" bgcolor="#EBF4F5">状态:</td>
								<td width="19%" bgcolor="#FFFFFF">
									<s:if test="serviceRequest.state==0">待派单</s:if>
									<s:elseif test="serviceRequest.state==1">待受理</s:elseif>
									<s:elseif test="serviceRequest.state==2">处理中</s:elseif>
									<s:elseif test="serviceRequest.state==3">流程进行中</s:elseif>
									<s:elseif test="serviceRequest.state==4">已拒绝</s:elseif>
									<s:elseif test="serviceRequest.state==5">已完成，等待用户反馈</s:elseif>
									<s:elseif test="serviceRequest.state==6">已关闭</s:elseif>
									<s:elseif test="serviceRequest.state==7">挂起</s:elseif>
								</td>
								<td width="14%" bgcolor="#EBF4F5">责任工程师:</td>
								<td width="20%" bgcolor="#FFFFFF">
									<s:property value="serviceRequest.usersByEngineerId.truename"></s:property>
								</td>
							</tr>
							<tr>
								<td nowrap bgcolor="#EBF4F5">申请人帐号:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.usersByRequestUser.username"></s:property>
								</td>
								<td bgcolor="#EBF4F5">申请人姓名:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.usersByRequestUser.truename"></s:property>
								</td>
								<td bgcolor="#EBF4F5">提交者:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.usersByOriginatorId.truename"></s:property>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EBF4F5">摘要:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.summary"></s:property>
								</td>
								<td bgcolor="#EBF4F5">严重程度:</td>
								<td bgcolor="#FFFFFF">
									<script type="text/javascript">
										var serverity='<s:property value="serviceRequest.severityTypBySeverity"></s:property>';
										var ty="中";
										if(serverity){
											if(serverity==1){
												ty='低';
											}else{
												if(serverity==3){
													ty="高";
												}else{
													if(serverity==4){
														ty="极高";
													}else{
														ty="中";
													}
												}
											}
										}
										document.write(ty);
									</script>
								</td>
								<td bgcolor="#EBF4F5">
									事件类别:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.serviceCategory.itemZh"></s:property>
								</td>
							</tr>
							<tr>
								<td height="99%" rowspan="2" valign="top" bgcolor="#EBF4F5"
									style="line-height: 22px">
									事件描述:
								</td>
								<td height="50" colspan=5 valign="top" bgcolor="#FFFFFF"
									style="font-weight: normal; line-height: 22px">
									<div style="width: 100%">
										<s:property value="serviceRequest.description"></s:property>
									</div>
								</td>
							</tr>
							<tr>
								<td height="18" colspan="5" valign="top" nowrap
									style="font-weight: normal; color:#F00;background-color: #FFFFCC; padding: 0px; padding-left: 5px">
									<div style="position: absolute; overflow: hidden">
										<img
											src="${pageContext.request.contextPath }/img/incident.gif"
											align="absmiddle">
										<s:property value="info"></s:property>
									</div>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EBF4F5">
									优先级:
								</td>
								<td bgcolor="#FFFFFF">&nbsp;<i>f</i>&nbsp;(&nbsp;<s:property value="serviceRequest.severityTypByEmergency"/>,<s:property value="serviceRequest.severityTypByEssential"/>&nbsp;)&nbsp;=<s:property value="serviceRequest.priorityLvl"/>
								</td>
								<td bgcolor="#EBF4F5">
									提交时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:date name="serviceRequest.submintTime"/>
								</td>
								<td nowrap bgcolor="#EBF4F5">
									希望完成时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:date name="serviceRequest.expectTime"/>
								</td>
							</tr>
							<tr style="display:none">
								<td nowrap bgcolor="#EBF4F5">
									自动转交时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:date name="serviceRequest.transmitTime"/>
								</td>
								<td bgcolor="#EBF4F5">
									承诺完成时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:date name="serviceRequest.promiseTime"/>
								</td>
								<td nowrap bgcolor="#EBF4F5">
									剩余可用处理时间:
								</td>
								<td bgcolor="#FFFFFF">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td height="10" colspan="2">
						<img height="0" width="830">
					</td>
				</tr>
				<tr>
					<td height="12" colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="29">
									<div style="position: absolute; overflow: hidden; height: 100%; width: 100%; border-right: 2px solid white">
										<table border="0" cellspacing="0" cellpadding="0" width="100%" class="Gray">
											<tr>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('re')" >
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_re">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="re">事件解决计划
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_re">
															</td>
														</tr>
													</table>
												</td>

												<td style="padding-right: 1px; cursor: hand" onClick="Table('gl')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_gl">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="gl">其他相关联工单
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_gl">
															</td>
														</tr>
													</table>
												</td>												
												<td style="padding-right: 1px; cursor: hand" onClick="Table('ca')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_ca">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ca"> 相关附件
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_ca">
															</td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('config')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_config"></td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="config"> 涉及配置</td>
															<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_config"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('tr')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_tr">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="tr"> 转交记录
															</td>
															<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_tr">
															</td>
														</tr>
													</table>
												</td>

												<td style="padding-right: 1px; cursor: hand" onClick="Table('so')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="${pageContext.request.contextPath }/img/tab_ch_left.gif" name="left_so" id="left_so">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_ch.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 3px" nowrap id="so"> 事件处理结果
															</td>
															<td><img src="${pageContext.request.contextPath }/img/tab_ch_right.gif" name="right_so" id="right_so">
															</td>
														</tr>
													</table>
												</td>

									<!--  			<td style="padding-right: 1px; cursor: hand" onClick="Table('ps')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_ps">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ps"> 审议
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_ps">
															</td>
														</tr>
													</table>
												</td>             -->

												<td style="padding-right: 1px; cursor: hand" onClick="Table('hi')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_hi">
															</td>
															<td
																background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="hi"> 活动记录
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_hi">
															</td>
														</tr>
													</table>
												</td>
												<td align="right" width="99%">

													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="40%" align="center"></td>
															<td onclick="window.print()" style="cursor: hand" nowrap>
<!--																<img src="${pageContext.request.contextPath }/img/print.jpg" align="absmiddle"> &nbsp;打印-->
															</td>
															<td>
<!--																<img width="10" height="1">-->
															</td>
															<td onclick="window.save()" style="cursor: hand" nowrap>
<!--																<img src="${pageContext.request.contextPath }/img/save.jpg" align="absmiddle"> &nbsp;保存&nbsp;-->
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr id="tr_table" style="display: none">
								<td>
									<table width="100%" height="220" border="1" cellpadding="0" cellspacing="0"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td class="alllisttitle" align="center" width="25%" nowrap>
															转交人
														</td>														
														<td class="alllisttitle" align="center" width="25%">
															转交至
														</td>
														<td class="alllisttitle" align="center" width="30%" nowrap>
															 转交时间
														</td>
														<td class="alllisttitle" align="center" width="19%" nowrap
															style="text-align: center">
															详细
														</td>
														<td class="alllisttitle" align="center" width="1%" nowrap
															style="text-align: center">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b5d6e6">
													<s:iterator value="serviceTranList" var="serviceTran" status='st'>
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td  height="22" align="center" width="25%" nowrap>
																<s:property value="usersByServiceFrom.truename" />
															</td>															
															<td align="center" width="25%">
																<s:property value="usersByServiceTo.truename" />
															</td>
															<td align="center" width="31%" nowrap>
																<s:date name="operatorTime" />
															</td>
															<td align="center" width="18%" nowrap style="text-align: center">
																<a style="cursor:hand" onclick="window.open('showtran.action?serviceTran.id=<s:property value="id" />');">详细</a>
															</td>
														</tr>
													</s:iterator>													
												</table>
											</div>
											</td>
										</tr>										
									</table>
								</td>
							</tr>
							<tr id="gl_table" style="display: none">
								<td>
									<table width="100%" height="220" border="1" cellpadding="0" cellspacing="0"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td width="15%" height="22" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															工单号
														</td>
														<td width="20%" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															类别
														</td>
														<td width="30%"  align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															摘要
														</td>
														<td width="20%" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															工程师
														</td>														
														<td width="14%" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															详细
														</td>
														<td width="1%"  align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
														</td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td valign="top" bgcolor="#FFFFFF">
													<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b5d6e6">
														<s:iterator value="relatedRequestList" var="serviceRequest" status='st'>
															<s:if test="#st.index<7">
															<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
																<td  height="22" align="center" width="15%" nowrap>
																	<s:property value="requestNo"></s:property>
																</td>
																<td align="center" width="20%" nowrap>
																	<s:property value="serviceCategory.itemZh"></s:property>
																</td>
																<td align="center" width="30%" nowrap>
																	<s:property value="summary"></s:property>
																</td>
																<td align="center" width="21%">
																	<s:property value="usersByEngineerId.truename" />
																</td>
																<td align="center" width="13%" nowrap style="text-align: center">
																	<a style="cursor:hand" onclick="window.open('show.action?flag=1&serviceRequest.id=<s:property value="id" />');">详细</a>
																</td>
															</tr>
															</s:if>															
														</s:iterator>
														<s:if test="relatedRequestList.size>8">
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td  height="22" align="center" width="15%" nowrap>更多...</td>
															<td colspan="4" height="22" align="left"nowrap></td>
														</tr>
														</s:if>
												</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="re_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td height="12" valign="top" bgcolor="#b5d6e6">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr>
														<td height="12" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															事件解决计划
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div
													style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" height="100%" border="0"
														cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
														<tr bgcolor="#FFFFFF">
															<td width="14%" height="12" bgcolor="#EBF4F5">
																&nbsp;操作时间:
															</td>
															<td width="86%">
																<s:date name="serviceRequest.beginTime"/>
															</td>
														</tr>

														<tr bgcolor="#FFFFFF">
															<td valign="top" bgcolor="#EBF4F5">
																初步分析意见及解决计划:
															</td>
															<td colspan=5 valign="top"
																style="font-weight: normal; line-height: 22px; padding-right: 80px;">
																<s:property value="serviceRequest.plan"></s:property>
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="ca_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td height="12" valign="top">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr>
														<td height="12" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															相关附件
														</td>
													</tr>
												</table>
											</td>
											</tr>
											
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div
													style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" height="100%" border="0"
														cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
													<s:iterator value="accessoryList" var="accesory">
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td height="12" width="100%">&nbsp;
																<a href="download.action?dlFileName=<s:property value='name'/>&flag=0&page=${page}&serviceRequest.id=<s:property value="tableId" />">
																<img src="${pageContext.request.contextPath }/img/view.jpg"  border=0 width=18 height=18>&nbsp;&nbsp;&nbsp;<s:property value="trueName"></s:property>
																</a>
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="config_table" style="display:none">
								<td >
									<table width="100%" border="0" cellspacing="0" cellpadding="0" height="220">
										<tr>
											<td valign="top"><table width="100%" border="0" cellspacing="1" cellpadding="2"  id='configTab'>
											  <s:hidden id="associate_assets" name="associate_assets"></s:hidden>
											  <tr>
											    <td width="22%" height="12" nowrap="nowrap" class="subtitle" align="center">资产编号</td>
											    <td width="20%" height="12" nowrap="nowrap" class="subtitle" align="center">资产名称</td>
											    <td width="20%" height="12" nowrap="nowrap" class="subtitle" align="center">资产类别</td>
											    <td width="15%" height="12" nowrap="nowrap" class="subtitle" align="center">供应商</td>
											    <td width="13%" height="12" nowrap="nowrap" class="subtitle" align="center">制造商</td>
											    <td width="10%" height="12" align="center" nowrap="nowrap" class="subtitle">查看</td>
											    <!--  <td width="8%" height="12" nowrap class="subtitle" align="center">删除</td>-->
											    <td width="0%" height="12" align="center" nowrap="nowrap" class="subtitle" style='visibility:hidden;'></td>
										      </tr>
											  <s:iterator value="serviceRequest.assets" var="assets">
											    <tr>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><s:property value="codeId" /></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><s:property value="name" /></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><s:property value="assetsType.name" /></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><s:property value="assetsProducerBySupportId.name" /></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><s:property value="assetsProducerByProduceId.name" /></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua"><img src="../images/ck.gif" /><a href="../assets/show.action?assetsId=<s:property value="code"/> " target='_blank' >查看</a></td>
											      <td align="center" bgcolor="#FFFFFF" class="zczb_qua" style='visibility:hidden;'><s:property value="code" /></td>
										        </tr>
										      </s:iterator>
										    </table></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="hi_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr>
														<td height="12" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															活动记录跟踪
													</tr>
												</table>
											</td>
										</tr>
										
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
														<tr>
															<td valign="top" bgcolor="#FFFFFF">
																<iframe width="100%" height="100%" src="propress.action?requestNo=<s:property value='serviceRequest.requestNo'></s:property>"
																	scrolling="yes" frameborder="0" style="border: 0px"></iframe>
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="ps_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td height="12" nowrap>
															事件评审和复议
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div
													style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" border="0" cellspacing="2"
														cellpadding="4">

													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="ch_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="#FFFFFF">
													<tr bgcolor="#b5d6e6">
														<td width="14%" height="12" align="center" nowrap
															class="alllisttitle">
															工单号
														</td>
														<td width="37%" align="center" class="alllisttitle">
															实施人
														</td>
														<td width="40%" align="center" class="alllisttitle">
															实施时间
														</td>
														<td width="9%" align="center" class="alllisttitle"
															style="text-align: center">
															查看
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div
													style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" border="0" cellspacing="2"
														cellpadding="4">

													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="so_table">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr>
														<td height="12" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															事件处理结果
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												<div
													style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
													<table width="100%" height="100%" border="0"
														cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
														<tr bgcolor="#F9F9F9">
															<td width="14%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;申请时间:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:date name="serviceRequest.submintTime"/>
															</td>
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;完成时间:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:date name="serviceRequest.finishTime"/>
															</td>
														</tr>
														<tr bgcolor="#F9F9F9">
															
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;处理时长(小时):
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px" >
																<script type="text/javascript">
												           			var beginTime="<s:date name="serviceRequest.submintTime"/>";
												           			var endTime="<s:date name="serviceRequest.finishTime"/>";
												           			document.write(comptime(beginTime,endTime));
												           		</script>
															</td>
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;确认人:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<script type="text/javascript">
																	var close='<s:property value="serviceRequest.state"></s:property>';
																	if(close==6){
																		var temp2='<s:property value="serviceRequest.temp2"></s:property>';
																		if(temp2){
																			document.write(temp2);
																		}else{
																			document.write('<s:property value="serviceRequest.usersByRequestUser.truename"></s:property>');
																		}
																	}
																</script>
																
															</td>
														</tr>
														<tr bgcolor="#F9F9F9" style="display:none">
															<td width="14%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;是否超时响应:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
															</td>
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;是否超时完成:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
															</td>
														</tr>
														<tr bgcolor="#F9F9F9" style="display:none">
															<td width="14%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;总耗时长:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
															</td>
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;事故原因:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:property value="serviceRequest.errorCause"></s:property>
															</td>
														</tr>														
														<tr bgcolor="#F9F9F9">
															<td valign="top" nowrap bgcolor="#EBF4F5">
																&nbsp;事件解决方案:
															</td>
															<td colspan="3" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<div style="overflow-x: hidden; width: 680px">
																	<s:property value="serviceRequest.solution"></s:property>
																</div>
															</td>
														</tr>

													</table>
												</div>
											</td>
										</tr>
								<!--		<tr>
											<td height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="4">
													<tr>
														<td bgcolor="#FFFFFF" width="99%"></td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="查看解决方案" class=mmBtn_sm
																name="button"
																onClick="window.open('../desk/?NowAction=solution&ID=P0800000011&Cat=Prob','','width=560,height=440,scrollbars=no,menubar=no,resizable=yes,top=80,left=176,status=yes')"
																disabled>
														</td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="查看任务通知" class=mmBtn_sm
																name="button"
																onClick="window.open('../chan/?NowAction=pnotice&Type=P0800000011','','width=600,height=450,scrollbars=no,menubar=no,resizable=no,top=80,left=126,status=yes')">
														</td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="查看知识库" class=mmBtn_sm
																name="button"
																onClick="window.open('../prob/?NowAction=guide_big&RequObj=|32,|33,&Cat=Prob','','width=700,height=450,scrollbars=no,menubar=no,resizable=yes,top=80,left=126,status=yes')">
														</td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="工作日志" class=mmBtn_sm
																name="button"
																onClick="window.open('../sla/?NowAction=logbook&ID=P0800000011','','width=550,height=500,scrollbars=no,menubar=no,resizable=yes,top=100,left=126,status=yes')">
														</td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="添加新日志" class=mmBtn_sm
																name="button"
																onClick="window.open('../asst/?NowAction=addlog&ID=P0800000011','','width=560,height=440,scrollbars=no,menubar=no,resizable=yes,top=120,left=156,status=yes')"
																disabled>
														</td>
														<td bgcolor="#FFFFFF">
															<input type="button" value="发起一个事件" class=mmBtn_sm
																onclick="window.open('../desk/?NowAction=serviceremedy&RequNo=P0800000011','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=50,left=50,status=yes')"
																disabled>
														</td>
													</tr>
												</table>
											</td>
										</tr>   -->
									</table>
								</td>
							</tr>
							<tr id="ci_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr>
														<td width="14%" align="center" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															代码
														</td>
														<td width="43%" align="center" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															名称
														</td>
														<td width="27%" align="center" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															类别
														</td>
														<td width="8%" align="center" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															选中
														</td>
														<td width="8%" align="center" nowrap bgcolor="#b5d6e6"
															class="alllisttitle">
															查看
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td height="99%" style="padding: 0px;">
												<iframe width="100%" height="100%" scrolling="yes"
													frameborder="0" style="border: 0px" id="configtb"
													name="configtb" src="asst.jsp"></iframe>
											</td>
										</tr>
										<tr>
											<td height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="4">
													<tr>
														<td width="1%" bgcolor="#F1F2F7">
															<input type="button" value="已选中" class=mmBtn_sm
																name="button"
																onClick="window.configtb.location='../asst/?NowAction=assetschose&type=1&chose='+document.getElementById('ConfigCh').value">
														</td>
														<td width="1%" bgcolor="#F1F2F7">
															<input type="button" value="保存更改" class=mmBtn_sm
																name="button"
																onClick="window.configtb.location='../prob/?NowAction=dbdeal&type=setCI2&RequNo=P0800000011&Value='+document.getElementById('ConfigCh').value"
																disabled>
														</td>
														<td width="99%" bgcolor="#F1F2F7">
															<input name="config" type="text" id="config"
																style="width: 100%">
														</td>
														<td width="1%" bgcolor="#F1F2F7">
															<input type="button" value="搜索" class=mmBtn_sm
																name="button"
																onClick="window.configtb.location='../asst/?NowAction=assetschose&type=1&key='+document.getElementById('config').value">
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
					<td height="10" colspan="2">
						<img height="0" width="760">
					</td>
				</tr>

			</table>
		</td>
	</tr>
</table>