<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>




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
									<s:property value="serviceRequest.usersByRequestUser.truename"></s:property>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EBF4F5">摘要:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.summary"></s:property>
								</td>
								<td bgcolor="#EBF4F5">严重程度:</td>
								<td bgcolor="#FFFFFF">
									<s:property
										value="serviceRequest.severityTypBySeverity.severityType"></s:property>
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
									<div style="width: 90%">
										<s:property value="serviceRequest.description"></s:property>
									</div>
								</td>
							</tr>
							<tr>
								<td height="18" colspan="5" valign="top" nowrap
									style="font-weight: normal; background-color: #FFFFCC; padding: 0px; padding-left: 5px">
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
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.priorityLvl"></s:property>
								</td>
								<td bgcolor="#EBF4F5">
									提交时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.submintTime"></s:property>
								</td>
								<td nowrap bgcolor="#EBF4F5">
									希望完成时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.expectTime"></s:property>
								</td>
							</tr>
							<tr>
								<td nowrap bgcolor="#EBF4F5">
									自动转交时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.transmitTime"></s:property>
								</td>
								<td bgcolor="#EBF4F5">
									承诺完成时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value="serviceRequest.promiseTime"></s:property>
								</td>
								<td nowrap bgcolor="#EBF4F5">
									剩余可用处理时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:property value=""></s:property>
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
												<td style="padding-right: 1px; cursor: hand" onClick="Table('re')">
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

												<td style="padding-right: 1px; cursor: hand" onClick="Table('ps')">
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
												</td>

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
															<td onclick="window.print()" style="cursor: hand" nowrap>
																<img src="${pageContext.request.contextPath }/img/print.jpg" align="absmiddle"> &nbsp;打印
															</td>
															<td>
																<img width="10" height="1">
															</td>
															<td onclick="window.save()" style="cursor: hand" nowrap>
																<img src="${pageContext.request.contextPath }/img/save.jpg" align="absmiddle"> &nbsp;保存&nbsp;
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
									<table width="100%" height="220" border="0" cellpadding="0"
										cellspacing="1"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td class="alllisttitle" align="center" width="14%" nowrap>
															转交人
														</td>
														<td class="alllisttitle" align="center" width="50%" nowrap>
															转交原因
														</td>
														<td class="alllisttitle" align="center" width="16%">
															转交至
														</td>
														<td class="alllisttitle" align="center" width="12%" nowrap>
															时间
														</td>
														<td class="alllisttitle" align="center" width="8%" nowrap
															style="text-align: center">
															详细
														</td>
													</tr>
													<s:iterator value="serviceTranList" var="serviceTran" status='st'>
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td align="center" width="14%" nowrap>
																<s:property value="usersByServiceFrom.truename" />
															</td>
															<td align="center" width="52%" nowrap>
																<s:property value="note" />
															</td>
															<td align="center" width="16%">
																<s:property value="usersByServiceTo.truename" />
															</td>
															<td align="center" width="12%" nowrap>
																<s:property value="operatorTime" />
															</td>
															<td align="center" width="6%" nowrap style="text-align: center">
																详细
															</td>
														</tr>
													</s:iterator>
												</table>
											</td>
										</tr>										
									</table>
								</td>
							</tr>
							<tr id="gl_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12" style="overflow-y: scroll">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td width="14%" height="22" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															工单号
														</td>
														<td width="43%" height="12" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															类别
														</td>
														<td width="27%" height="12" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															工程师
														</td>
														<td width="8%" height="12" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															选择
														</td>
														<td width="8%" height="12" align="center" nowrap
															bgcolor="#b5d6e6" class="alllisttitle">
															查看
														</td>
													</tr>
													<s:iterator value="relatedRequestList" var="serviceRequest"
														status='st'>
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'"
															onMouseOut="this.bgColor='#ffffff'">
															<td align="center" width="14%" nowrap>
																<s:property value="requestNo"></s:property>
															</td>
															<td align="center" width="52%" nowrap>
																<s:property value="serviceCategory.itemZh"></s:property>
															</td>
															<td align="center" width="16%">
																<s:property value="usersByEngineerId.truename" />
															</td>
															<td align="center" width="12%" nowrap>
																选择
															</td>
															<td align="center" width="6%" nowrap
																style="text-align: center">
																详细
															</td>
														</tr>
													</s:iterator>
												</table>
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
																<s:property value="serviceRequest.beginTime"></s:property>
															</td>
														</tr>

														<tr bgcolor="#FFFFFF">
															<td valign="top" bgcolor="#EBF4F5">
																解决计划内容:
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

													<s:iterator value="accessoryList" var="accesory">
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td height="12" width="100%">
																<a href="download.action?dlFileName=<s:property value='name'></s:property>"><s:property
																		value="trueName"></s:property>
																</a>
															</td>
														</tr>
													</s:iterator>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="hi_table" style="display: none">
								<td class="td-bg">
									<table width="100%" border="1" cellspacing="0" cellpadding="0"
										height="220"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top"
												style="overflow-y: scroll; background-image: url(${pageContext.request.contextPath }/img/trh.jpg); border-bottom: 1px solid #EBE9ED"
												height="12">
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
												<iframe width="100%" height="100%" src="viewprogress.action?requestNo=<s:property value='serviceRequest.requestNo'></s:property>"
													scrolling="yes" frameborder="0" style="border: 0px"></iframe>
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
															<td width="14%" height="12" nowrap bgcolor="#EBF4F5">
																&nbsp;完成时间:
															</td>
															<td width="86%" colspan="3" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:property value="serviceRequest.finishTime"></s:property>
															</td>
															<td width="86%" colspan="3" bgcolor="#FFFFFF"
																style="padding-left: 6px"></td>
														</tr>
														<tr bgcolor="#F9F9F9">
															<td width="14%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;等待时长:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																4523
															</td>
															<td width="14%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;处理时长:
															</td>
															<td width="36%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																434
															</td>
														</tr>
														<tr bgcolor="#F9F9F9">
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
														<tr bgcolor="#F9F9F9">
															<td width="14%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;总耗时长:
															</td>
															<td width="36%" colspan="3" valign="top"
																bgcolor="#FFFFFF" style="padding-left: 6px"></td>
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
										<tr>
											<td height="12">
											</td>
										</tr>
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