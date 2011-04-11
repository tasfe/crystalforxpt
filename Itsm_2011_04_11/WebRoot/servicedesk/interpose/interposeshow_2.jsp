<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>查看事件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
<script type='text/javascript' src='/itsm/dwr/interface/SeverityTypDAO.js'></script>
<script type='text/javascript' src='/itsm/dwr/interface/RoleteamDAO.js'></script>
</head>
<script language="JavaScript">
function getDAO(){ //取出类别
   LocationDAO.findAll(callbacklocation);
   SeverityTypDAO.findByIntType(1,callbackessential);
   SeverityTypDAO.findByIntType(2,callbackemergency);
}
function callbacklocation(data){  //显示出地理位置
   dwr.util.removeAllOptions("location");
   dwr.util.addOptions("location",{'-1':'--请选择--'});
   dwr.util.addOptions("location",data,"id","name");   
}
function callbackroleteam(data){  //显示出工程师分组
   dwr.util.removeAllOptions("engineerteam");
   document.getElementById("ITer").src="about:blank";
   dwr.util.removeAllOptions("engineerteam");
   dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam",data,"id","name");   
}
function callbackessential(data){  //显示出影响度
   dwr.util.removeAllOptions("essential");
   dwr.util.addOptions("essential",{'-1':'影响度'});
   dwr.util.addOptions("essential",data,"id","severityType");   
}
function callbackemergency(data){  //显示出紧急度
   dwr.util.removeAllOptions("emergency");
   dwr.util.addOptions("emergency",{'-1':'紧急度'});
   dwr.util.addOptions("emergency",data,"id","severityType");   
}

function getInfo1(){
	var location = document.getElementById("location").value;
	if(location!=-1) {
		RoleteamDAO.findByLocationID(location,callbackroleteam);		
	}else{
		RoleteamDAO.findAll(callbackroleteam);		
	}
}
function getInfo2(){
	var roleteam = document.getElementById("engineerteam").value;
	document.getElementById("ITer").src="/itsm/role/query.action?role.id="+roleteam;
}

function html_clock(Num){
	var Num = Num-1;
	if (Num<1) {
		window.document.getElementById("htmlclock").innerHTML = "- - : - - : - -";
	} else {
		var Hours1 = (Num/60)/60;
		var Hours = Math.round((Num/60)/60);
		if (Hours > Hours1) Hours = Hours-1;
		var Minutes1 = (Num-Hours*60*60)/60;
		var Minutes = Math.round((Num-Hours*60*60)/60);
		if (Minutes > Minutes1) Minutes = Minutes-1;
		var Seconds = Num-Hours*60*60-Minutes*60;
		if (Hours < 12) window.document.getElementById('htmlclock').style.color = 'Orange';
		if (Hours < 8) window.document.getElementById('htmlclock').style.color = 'red';
		if (Hours < 4) window.document.getElementById('htmlclock').style.color = 'black';
		if (Hours < 10) Hours = "0" + Hours;
		if (Minutes < 10) Minutes = "0" + Minutes;
		if (Seconds < 10) Seconds = "0" + Seconds;
		window.document.getElementById("htmlclock").innerHTML = Hours+":"+Minutes+":"+Seconds;
		setTimeout ("html_clock('"+Num+"')", 1000);
	}
}
</script>


<body onLoad="getDAO()" background="../../img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件详细情况:</td>
  </tr>
</table>
<div>
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" id="mainright">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
					<Script LANGUAGE="JavaScript">
					function Table(id){
						var oldid = document.getElementById("tmp").value;
						if (id!=oldid) {
							document.getElementById("left_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_left.gif";
							document.getElementById("right_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_right.gif";
							document.getElementById(oldid).background="${pageContext.request.contextPath }/img/tab_un.gif";
							document.getElementById(oldid).style.paddingTop="5px";
							document.getElementById(oldid+"_table").style.display="none";
							document.getElementById("left_"+id).src="${pageContext.request.contextPath }/img/tab_ch_left.gif";
							document.getElementById("right_"+id).src="${pageContext.request.contextPath }/img/tab_ch_right.gif";
							document.getElementById(id).background="${pageContext.request.contextPath }/img/tab_ch.gif";
							document.getElementById(id).style.paddingTop="3px";
							document.getElementById(id+"_table").style.display="";
							document.getElementById("tmp").value=id;
						}
					}
					</Script>
					<tr>
						<td>
							<jsp:include page="common.jsp"/> 
						</td>
					</tr>					
				<tr>
							<td>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td height="12" style="padding-top: 8px"><a id="theclock" style="font-size: 20px; font-family: 'Arial Black'; color: green" name="theclock"></a></td>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											
											<input type="button" class="mmBtn" value="强制指派任务" onclick="ShowApprove('Act')">
											<input type="button" class="mmBtn" value="延期" onclick="ShowApprove('Yan')">
											<input type="button" class="mmBtn" value="通知" onclick="ShowApprove('Not')">
											<input type="button" class="mmBtn" value="关联请求" onclick="ShowApprove('Gl')">
											<input type="button" class="mmBtn" value="拒绝" onclick="ShowApprove('Del')">
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td style="padding-top: 8px"> <a name="SetApprove"></a>
								<script language="JavaScript">
								function ShowApprove(type) {
									document.getElementById("divRef").style.display = "none";
									document.getElementById("divYan").style.display = "none";
									document.getElementById("divGL").style.display = "none";
									document.getElementById("divAct").style.display = "none";
									document.getElementById("divDel").style.display = "none";
									document.getElementById("divCat").style.display = "none";
									document.getElementById("divUpd").style.display = "none";
									document.getElementById("divNot").style.display = "none";
									document.getElementById("div"+type).style.display = "";
									document.location.href = "#SetApprove"
								}
								</script>
								<div id="divNot" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<tr>
											<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;发送任务通知</th>
										</tr>
										<s:form name="frmAct10" action="../prob/default.asp?NowAction=db_pro&type=ProjectAno" method="post" target="sum">
											<input type=hidden name=ProNo value="C10082000001">
											<input type=hidden name=Type value="SvAnnounce">
											<input type=hidden name=ServiceTitle value="任务通知">
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">通知发送方式:</td>
															<td width="86%" colspan=3 class="td-left" style="padding-left: 1px">
																<table border="0" cellspacing="0" cellpadding="0" width="100">
																	<tr>
																		<td style="padding-top: 2px" width="1%" bgcolor="#E5E9EE">
																			<input type="checkbox" style="border: 0px" checked disabled>
																		</td>
																		<td style="padding-right: 15px; padding-top: 1px" nowrap bgcolor="#E5E9EE">系统消息</td>
																		<td style="padding-top: 2px" width="1%" bgcolor="#E5E9EE">
																			<input name="Methods" type="checkbox" value="Mail" style="border: 0px">
																		</td>
																		<td style="padding-right: 15px; padding-top: 1px" nowrap bgcolor="#E5E9EE">邮件</td>
																		<td style="padding-top: 2px" width="1%" bgcolor="#E5E9EE">
																			<input name="Methods" type="checkbox" value="Smms" style="border: 0px">
																		</td>
																		<td style="padding-top: 1px" nowrap bgcolor="#E5E9EE">手机短信</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">通知内容:</td>
															<td width="86%" colspan=3 class="td-detail">
																<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
														<tr>
															<td width="14%" class="td-left" valign="top">接收人:</td>
															<td width="86%" colspan=3 class="td-detail" style="padding-left: 1px">
																<table border="0" cellspacing="0" cellpadding="0" width="100">
																	<tr>
																		
																		<td class="td-right-s" style="padding-top: 2px" width="1%">
																			<input name="ITer" type="checkbox" value="|eng|" style="border: 0px">
																		</td>
																		<td class="td-right-s" style="padding-right: 25px; padding-top: 1px" nowrap> <font style="padding: 2px"> 工程师 </font> </td>
																		
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px">
												<input type="button" class="mmBtn" value="发送" onclick="if(confirm('你确认要发送吗?')){document.getElementById('frmAct10').submit();}">
											</td>
										</tr>
									</table>
								</div>
								<div id="divCat" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<s:form name="frmCat" action="../prob/default.asp?NowAction=dbdeal&type=FeedBack" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<tr>
												<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;反馈意见补单
												</th>
											</tr>
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td class="td-detail">C10082000001</td>
														</tr>
														
														<tr>
															<td width="14%" class="td-left">客户满意度:</td>
															<td class="td-detail" style="padding-left: 0px">
																<table border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td style="padding-top: 3px">
																			<input type="radio" id="ClientIde" name="ClientIde" class="noborder" value="A" checked>
																		</td>
																		<td style="padding-right: 10px">满意</td>
																		<td style="padding-top: 3px">
																			<input type="radio" id="ClientIde" name="ClientIde" class="noborder" value="B">
																		</td>
																		<td style="padding-right: 10px" nowrap>一般</td>
																		<td style="padding-top: 3px">
																			<input type="radio" id="ClientIde" name="ClientIde" class="noborder" value="C">
																		</td>
																		<td nowrap>不满意</td>
																	</tr>
																</table>
															</td>
														</tr>
														
														<tr>
															<td width="14%" valign="top" class="td-left">客户意见:</td>
															<td width="86%" colspan=3 class="td-detail">
																<textarea name="OtherNote" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
														
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="提交" onClick="if(confirm('您确认要发送吗？')){ frmCat.submit()}" name="button">
											</td>
										</tr>
									</table>
								</div>
								<div id="divGl" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<s:form name="frmGl" action="../prob/default.asp?NowAction=dbdeal&type=setGl" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<tr>
												<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;关联相关请求</th>
											</tr>
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td rowspan="2" valign="top" class="td-left">相关请求单:</td>
															<td valign="bottom" class="td-right" style="padding-left: 5px">
																<input name="Cause" type="text" style="width: 500; cursor: hand" value=",C10081900007" readonly>
															</td>
														</tr>
														<tr>
															<td style="padding: 5px" class="td-right">
																<iframe frameborder="0" height="130" width="90%" scrolling="yes" style="border: 1px inset" name="l1" id="l1" src="../inci/?NowAction=requestlist&value=,C10081900007&OLDno=C10082000001"></iframe>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="提交" onClick="if(confirm('您需要关联选中的请求？')){ frmGl.submit()}" name="button">
											</td>
										</tr>
									</table>
								</div>
								<div id="divUpd" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<s:form name="frmUpd" action="../prob/default.asp?NowAction=dbdeal&type=setPRB" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<tr>
												<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;升级为问题</th>
											</tr>
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">问题初步分析:</td>
															<td width="86%" colspan=3 class="td-detail">
																<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="提交" onClick="if(confirm('您确认将此请求升级为问题吗？')){ frmUpd.submit()}" name="button">
											</td>
										</tr>
									</table>
								</div>
								<div id="divYan" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<tr>
											<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;承诺时间延期</th>
										</tr>
										<s:form name="frmTra" action="../prob/default.asp?NowAction=dbdeal&type=SetYan" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" class="td-left">延后时间:</td>
															<td colspan=3 class="td-detail">
																<input type=text name=Timer size=40 style="width: 200px" onKeyUp="if(this.value<1000000){}else{this.value='0'}">
																<select name="Danwei" style="font-size: 9px">
																	<option value="minutes">Minute</option>
																	<option value="hours">Hour</option>
																	<option value="days">Day</option>
																</select>
															</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">原因:</td>
															<td width="86%" colspan=3 class="td-detail">
																<textarea name="Remark" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="提交" onClick="if(confirm('你确认要将承诺时间延期吗？')){ frmTra.submit()}" name="button2">
											</td>
										</tr>
									</table>
								</div>
								<div id="divAct" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<tr>
											<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;指派任务给工程师</th>
										</tr>
										<s:form name="frmAct" action="../prob/default.asp?NowAction=dbdeal&type=ApproveAct&FC=1" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<input name="ITprinc" type="hidden">
											<input name="Grade" value="1" type="hidden">
											<input type="hidden" name="ResponseTimes" id="ResponseTimes" value="">
											<input type="hidden" name="ResponseManagers" id="ResponseManagers" value="">
											<input type="hidden" name="FinishTimes" id="FinishTimes" value="">
											<input type="hidden" name="FinishManagers" id="FinishManagers" value="">
											<input type="hidden" name="ITers" id="ITers" value="">
											<input type="hidden" name="Rp_Manager" id="Rp_Manager" value="">
											<input type="hidden" name="Up_Engineer" id="Up_Engineer" value="">
											<input type="hidden" name="ReTime" id="ReTime" value="">
											<input type="hidden" name="FiTime" id="FiTime" value="">
											<input type="hidden" name="UpTime" id="UpTime" value="">
											<input type="hidden" value="|1," name="category" id="category">
											
											<a id="About" name="About" style="display: none"></a>
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td colspan=3 class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" class="td-left" valign="top">意见或建议:</td>
															<td colspan=3 class="td-detail">
																<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
														<tr>
															<td valign="top" class="td-left" height="12">工作组:</td>
															<td style="padding: 0px; padding-left: 5px" class="td-right">
																<iframe frameborder="0" height="100%" width="500" scrolling="no" src="../prob/?NowAction=team&RequPower=clsj&Color=F3F4F8"></iframe>
															</td>
														</tr>
														<tr>
															<td valign="top" class="td-left">选择工程师:</td>
															<td style="padding: 5px" class="td-right">
																<iframe frameborder="0" height="130" width="90%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
															</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">优先级:</td>
															<td width="86%" colspan=3 class="td-detail">
																<table width="20"  border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td>
																			<select name="Essential" style="height: 20px; width: 80px" onChange="window.sla.location='../desk/?NowAction=sla&Go=1&TheTime=2010-8-20 8:49:16&NoChan=1&Type=inci&RequObj=|1,&RequPep=|user|&Ess='+document.getElementById('Essential').value+'&Eme='+document.getElementById('Emergency').value">
																				<option value="2">影响度</option>
																				
																				<option value="3">↑_高</option>
																				
																			</select>
																		</td>
																		<td style="font-family: 'Times New Roman'">&nbsp;<i>&</i>&nbsp;</td>
																		<td>
																			<select name="Emergency" style="height: 20px; width: 80px" onChange="window.sla.location='../desk/?NowAction=sla&Go=1&TheTime=2010-8-20 8:49:16&NoChan=1&Type=inci&RequObj=|1,&RequPep=|user|&Ess='+document.getElementById('Essential').value+'&Eme='+document.getElementById('Emergency').value">
																				<option value="2">紧急度</option>
																				
																				<option value="3">↑_高</option>
																				
																			</select>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="指派任务" onclick="if(document.getElementById('ITprinc').value!=''){frmAct.submit()}else{alert('请选择工程师！')}">
											</td>
										</tr>
									</table>
								</div>
								<div id="divDel" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<tr>
											<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;拒绝此请求</th>
										</tr>
										<s:form name="frmRef" action="../prob/default.asp?NowAction=dbdeal&type=ApproveRef" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">拒绝原因:</td>
															<td width="86%" colspan=3 class="td-detail">
																<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="拒绝" onClick="if(confirm('你确认要拒绝吗？')){ frmRef.submit()}" name="button">
											</td>
										</tr>
									</table>
								</div>
								<div id="divRef" style="display: none">
									<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
										<tr>
											<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;干预工作流程</th>
										</tr>
										<s:form name="frmRef2" method="post" target="sum">
											<input type=hidden name=RequNo value="C10082000001">
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail">C10082000001</td>
														</tr>
														<tr>
															<td width="14%" class="td-left" valign="top">将请求转交至:</td>
															<td width="86%" colspan=3 class="td-detail">
																<select name="TurnTo" size="5" id="TurnTo" style="width: 400px">
																	
																</select>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</s:form>
									</table>
									<table cellspacing=0 cellpadding=0 border=0 width="100%">
										<tr>
											<td align=right nowrap style="padding-top: 8px">
												<input type="button" class="mmBtn" value="转交" onClick="if(confirm('你确认要转交吗？')){if(document.getElementById('TurnTo').value!=''){window.sum.location=document.getElementById('TurnTo').value}else{alert('请选择正确的转交环节！')}}" name="button">
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
</body>
</html>
<Script Language=Javascript src="../cn_css/mmBtn.js"></Script>
					