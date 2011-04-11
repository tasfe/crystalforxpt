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
								<td height="22" width="14%" bgcolor="#EBF4F5">任务编号:</td>
								<td width="19%" bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.taskCode"></s:property>
								</td>
								<td width="14%" bgcolor="#EBF4F5">类别:</td>
								<td width="19%" bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.schedualedTasks.serviceCategory.itemZh"></s:property>
								</td>
								<td width="14%" bgcolor="#EBF4F5">重要程度:</td>
								<td width="20%" bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.schedualedTasks.serverity" />
								</td>
							</tr>
							<tr>
								<td nowrap bgcolor="#EBF4F5">摘要:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.schedualedTasks.keyWord" />
								</td>
								<td bgcolor="#EBF4F5">创建人:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.schedualedTasks.application" />
								</td>
								<td bgcolor="#EBF4F5">负责人:</td>
								<td bgcolor="#FFFFFF">
									<s:property value="schedualedTaskDetail.schedualedTasks.user.truename" />
								</td>
							</tr>
							<tr>
								<td height="99%" rowspan="2" valign="top" bgcolor="#EBF4F5"
									style="line-height: 22px">
									任务描述:
								</td>
								<td height="60" colspan=5 valign="top" bgcolor="#FFFFFF"
									style="font-weight: normal; line-height: 22px">
									<div style="width: 100%">
										<s:property value="schedualedTaskDetail.schedualedTasks.detail"></s:property>
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
									制定时间:
								</td>
								<td bgcolor="#FFFFFF"><s:date name="schedualedTaskDetail.schedualedTasks.submitAt" format="yyyy-MM-dd"/>
								</td>
								<td bgcolor="#EBF4F5">
									计划时间:
								</td>
								<td bgcolor="#FFFFFF">
									<s:date name="schedualedTaskDetail.schedualedTime"  format="yyyy-MM-dd"/>
								</td>
								<td nowrap bgcolor="#EBF4F5">
									状态:
								</td>
								<td bgcolor="#FFFFFF">
									<s:if test="schedualedTaskDetail.state.equals('wait')">未执行</s:if>
          							<s:elseif test="schedualedTaskDetail.state.equals('work')">已执行 </s:elseif>
          							<s:elseif test="schedualedTaskDetail.state.equals('fini')">已复审 </s:elseif>
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
										<table border="0" cellspacing="0" cellpadding="0" width="100%" class="Gray">
											<tr>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('re')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_re">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="re">计划任务执行详情
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
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ca">附加文件
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
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_tr">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="tr">转交记录
															</td>
															<td>
																<img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_tr">
															</td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('so')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="${pageContext.request.contextPath }/img/tab_ch_left.gif" name="left_so" id="left_so">
															</td>
															<td background="${pageContext.request.contextPath }/img/tab_ch.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 3px" nowrap id="so"> 执行结果
															</td>
															<td><img src="${pageContext.request.contextPath }/img/tab_ch_right.gif" name="right_so" id="right_so">
															</td>
														</tr>
													</table>
												</td>
												<td align="right" width="99%">

													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="40%" align="center"> <font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
									
								</td>
							</tr>
							<tr id="re_table"  style="display:none"> 
								<td> 
									<table width="100%" height="220" border="1" cellpadding="0" cellspacing="0"
										style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" height="12">
												<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
													<tr bgcolor="#b5d6e6">
														<td width="20%" height="22" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">超期完成天数</td>
														<td width="30%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">完成时间</td>
														<td width="30%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">执行人</td>														
														<td width="19%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">查看</td>
														<td width="1%"  align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle"></td>
													</tr>
												</table>
											</td>
										</tr> 
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
												 <table width="100%" border="0" cellspacing="2" cellpadding="4">
												 	<s:iterator value="schedualedTaskDetail.schedualedTaskUsers" var="stu">
													<tr  onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
														<td width="20%"  align="center">
															<s:if test="outOfDay==null">0 days</s:if>
															<s:else><s:property value="outOfDay"/> days</s:else>
														</td>
														<td width="31%"  align="center">
															<s:if test="finishTime==null">?</s:if>
															<s:else><s:date name="finishTime" format="yyyy-MM-dd HH:mm:ss"/></s:else>															
														</td>
														<td width="30%"  align="center"><s:property value="users.truename"/></td>													
														<td valign="top" width="19%" align="center">
															<s:if test="flag==1">
																<a href="#" onClick="window.open('showstu.action?schedualedTaskUser.id=<s:property value="id" />')"><img src='../img/viewdetail.gif' border=0 width=18 height=18></a>
															</s:if>
															<s:else><img src='../img/viewdetail.gif' border=0 width=18 height=18></s:else>
														</td>
													</tr>
													</s:iterator>
												</table>
											 </td>
										</tr>
											<tr> 
												<td height="12">
													<table width="100%" border="0" cellspacing="1" cellpadding="4" class="datagrid">
													  <tr>
														<td bgcolor="#EBF4F5" width="99%"></td>	
														<td bgcolor="#EBF4F5"><input type="button" value="查看工作日志" class="mmBtn" onClick="window.open('listdiary.action?std.taskDetailId=${schedualedTaskDetail.id}')"></td>
														<td bgcolor="#EBF4F5"><input type="button" value="添加新日志" class="mmBtn" onClick="window.open('inputdiary.action?std.taskDetailId=${schedualedTaskDetail.id}')"></td>
													 </tr>
													</table>
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
															任务号
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
													<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b5d6e6">
														<s:iterator value="relatedRequestList" var="request" status='st'>
															<s:if test="#st.index<8">
															<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
																<td  height="22" align="center" width="15%" nowrap>
																	<s:property value="taskCode"></s:property>
																</td>
																<td align="center" width="20%" nowrap>
																	<s:property value="schedualedTasks.serviceCategory.itemZh"></s:property>
																</td>
																<td align="center" width="30%" nowrap>
																	<s:property value="schedualedTasks.keyWord"></s:property>
																</td>
																<td align="center" width="21%">
																	<s:property value="schedualedTasks.user.truename" />
																</td>
																<td align="center" width="13%" nowrap style="text-align: center">
																	<a style="cursor:hand" onclick="window.open('show1.action?page=${page}&schedualedTaskDetail.id=<s:property value="id" />');">详细</a>
																</td>
															</tr>
															</s:if>															
														</s:iterator>
														<s:if test="relatedRequestList.size()>8">
															<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
																<td colspan="5" height="22" align="left" width="15%" nowrap>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更多...</td></tr>
															</s:if>
												</table>
											</td>
										</tr>
									</table>
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
													<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b5d6e6">
													<s:iterator value="serviceTranList" var="stTran" status='st'>
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
													<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
													<s:iterator value="accessoryList" var="accesory">
														<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
															<td height="12" width="100%">&nbsp;
																<a href="../engineertrace/download.action?dlFileName=<s:property value='name'></s:property>">
																<img src="${pageContext.request.contextPath }/img/view.jpg"  border=0 width=18 height=18>&nbsp;&nbsp;&nbsp;<s:property value="trueName"></s:property>
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
							<tr id="so_table"  style="display:">
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
															任务执行结果
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top" bgcolor="#FFFFFF">
													<table width="100%" height="220px" border="0"
														cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
														<tr bgcolor="#F9F9F9">
															<td width="11%" height="29" valign="top" nowrap
																bgcolor="#EBF4F5">
																&nbsp;执行时间:
															</td>
															<td width="22%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:date name="schedualedTaskDetail.schedualedTime" format="yyyy-MM-dd"/>
															</td>
															<td width="11%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;完成时间:
															</td>
															<td width="22%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:date name="schedualedTaskDetail.finishedTime"  format="yyyy-MM-dd"/>
															</td>
															<td width="12%" height="29" valign="top"
																bgcolor="#EBF4F5">
																&nbsp;完成任务工程师:
															</td>
															<td width="22%" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:property value="schedualedTaskDetail.finiEngineer" />
															</td>
														</tr>									
														<tr bgcolor="#F9F9F9">
															<td valign="top" nowrap bgcolor="#EBF4F5">
																&nbsp;事件解决方案:
															</td>
															<td colspan="5" valign="top" bgcolor="#FFFFFF"
																style="padding-left: 6px">
																<s:property value="schedualedTaskDetail.solution"></s:property>
																<!--  
																<div style="overflow-x: hidden; width: 680px">
																	<s:property value="schedualedTaskDetail.solution"></s:property>
																</div>
																-->
															</td>
														</tr>

													</table>
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
						</table>
					</td>
				</tr>
	<!-- 			<tr>
					<td height="10" colspan="2">
						<img height="0" width="830">
					</td>
				</tr>
				<tr>
					<td height="12" colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							 <tr>
								<td>
									 <table width="100%" border="1" cellpadding="0" cellspacing="0" class="tb-list" height="100%">
										<tr> 
										  <td style="padding: 0px" colspan="2" bgcolor="#b5d6e6" class="alllisttitle">&nbsp;&nbsp;相关计划任务</td>
										</tr>
										<tr> 
										  <td valign="top" height="12" colspan="2">
											  <table width="100%" border="0" cellspacing="2" cellpadding="2" bgColor="white">
												<tr> 
												  <td width="50%" nowrap bgcolor="#EBF4F5">已执行</td> 
												  <td width="50%" nowrap bgcolor="#EBF4F5">未执行</td> 
												</tr>
											  </table>
										   </td>
										</tr> 
										<tr>
										  <td height="130" style="padding-right: 1px; border-right: 1px solid white" width="50%">
										  	<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%" id="AttachTB" name="AttachTB" >
										  		<table width="100%" border="0" cellpadding="6" cellspacing="0" bgcolor="#FFFFFF">
										  			<tr>
										  			</tr>
										  		</table>
										  	</div>
										  </td>
										  <td height="130" width="50%">
										  	<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%" id="AttachTB" name="AttachTB" >
										  		<table width="100%" border="0" cellpadding="6" cellspacing="0" bgcolor="#FFFFFF">
										  			<tr>
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
				</tr>       -->
				
				<tr>
					<td height="10" colspan="2">
						<img height="0" width="760">
					</td>
				</tr>

			</table>
		</td>
	</tr>
</table>