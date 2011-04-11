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
<script type='text/javascript' src='../dwr/interface/SeverityTypDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
</head>
<script language="JavaScript">
function getDAO(){ //取出类别
  RoleteamDAO.findAllByType(callbackroleteam);
   SeverityTypDAO.findByIntType(1,callbackessential);
   SeverityTypDAO.findByIntType(2,callbackemergency);
}
function callbackroleteam(data){  //显示出工程师分组
   dwr.util.removeAllOptions("engineerteam1");
   dwr.util.removeAllOptions("engineerteam2");
   document.getElementById("ITer").src="about:blank";
   dwr.util.addOptions("engineerteam1",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam1",data,"id","name");
   dwr.util.addOptions("engineerteam2",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam2",data,"id","name");    
}
function callbackessential(data){  //显示出影响度
   dwr.util.removeAllOptions("essential");
   dwr.util.addOptions("essential",{'-1':'影响度'});
   dwr.util.addOptions("essential",data,"severityValue","severityType");   
}
function callbackemergency(data){  //显示出紧急度
   dwr.util.removeAllOptions("emergency");
   dwr.util.addOptions("emergency",{'-1':'紧急度'});
   dwr.util.addOptions("emergency",data,"severityValue","severityType");   
}
function ass(){
	var flag;
	var all=document.getElementsByName('flag');
	if(all[0].checked) flag=0;
	else flag=1;
	var val1=document.getElementById('ITprinc').value;
	var val3=document.getElementById('engineerteam1').value;
	if(flag==1){
		if(val1!=''){
			if(confirm('你确认要提交吗？')) document.getElementById('formAss').submit();
		}else alert('请选择工程师！');
	}else if(val3!=-1) {
		if(confirm('你确认要提交吗？')) document.getElementById('formAss').submit();
	} else alert('请选择工程师分组！'); 
}
function del(){
	var val1=document.getElementById('timer').value;
	var val2=document.getElementById('delaycause').value;
	if(val1!=''){
		if(val2!='') {
			if(confirm('你确认要提交吗？')) document.getElementById('formDel').submit();
		}else  alert('请填写延期原因！');
	}
	 else alert('请填写延期时间！'); 
}
function getInfo2(){
	var roleteam = document.getElementById("engineerteam2").value;
	document.getElementById("ITer").src="../role/query.action?role.id="+roleteam;
}


</script>


<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件详细情况</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: scroll; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1">
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
									<td height="12" style="padding-top: 8px" nowrap> <a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
									
									<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
										<input type="button" id="button1" class="mmBtn" value="强制指派任务" onclick="ShowApprove('Ass')">
										<input type="button" id="button1" class="mmBtn" value="延期" onclick="ShowApprove('Del')">
								<!-- 		<input type="button" id="button1" class="mmBtn" value="通知" onclick="ShowApprove('Not')">
										<input type="button" id="button4" class="mmBtn" value="关联请求" onclick="ShowApprove('Rel')">
										<input type="button" id="button4" class="mmBtn" value="升级为问题" onclick="ShowApprove('Pro')">     -->					
										<input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)">
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divAss").style.display = "none";
								document.getElementById("divDel").style.display = "none";
<!--								document.getElementById("divNot").style.display = "none";-->
<!--								document.getElementById("divRel").style.display = "none";-->
<!--								document.getElementById("divPro").style.display = "none";    -->
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>							
							<div id="divAss" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;指派任务给工程师</td>
									</tr>
									<s:form name="formAss" action="assign" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value=""></s:hidden>
										<s:hidden id="ITnotice" name="isNotice" value="0"></s:hidden>
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
													<td width="14%" bgcolor="#EBF4F5">工单号:</td>
													<td colspan=2 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
												</tr>
												<tr>
													<td width="14%" bgcolor="#EBF4F5" valign="top">意见或建议:</td>
													<td colspan=2 bgcolor="#FFFFFF">
														<textarea name="serviceRequest.cause" rows=5 style="width: 100%"></textarea>
													</td>
												</tr>
												<tr>
													<td width="14%" bgcolor="#EBF4F5">指派对象:</td>
													<td bgcolor="#FFFFFF">
														<input type="radio" id="radio1" name="flag" value="0" style="border: 0px" checked onclick="document.getElementById('ass_tr0').style.display='';document.getElementById('ass_tr1').style.display='none';document.getElementById('ass_tr2').style.display='none';">工程师分组&nbsp;&nbsp;
														<input type="radio" id="radio1" name="flag" value="1" style="border: 0px" onclick="document.getElementById('ass_tr0').style.display='none';document.getElementById('ass_tr1').style.display='';document.getElementById('ass_tr2').style.display='';">工程师&nbsp;&nbsp;
													</td>
												</tr>
												<tr id="ass_tr0" style="display:">
													<td height="12" bgcolor="#EBF4F5">工程师分组:</td>
													<td bgcolor="#FFFFFF" style="padding: 0px; padding-left: 7px">
    													<select style="width: 180px" id="engineerteam1" name="serviceRequest.acceptEngineers">
       														<option value="-1">--请选择--</option>
    													</select>
													</td>
												</tr>
												<tr id="ass_tr1" style="display:none">
													<td height="12" bgcolor="#EBF4F5">工程师分组:</td>
													<td bgcolor="#FFFFFF" style="padding: 0px; padding-left: 7px">
    													<select style="width: 180px" id="engineerteam2"  onChange="getInfo2();">
       														<option value="-1">--请选择--</option>
    													</select>
													</td>
												</tr>
												<tr id="ass_tr2" style="display:none">
													<td bgcolor="#EBF4F5">选择工程师:</td>
													<td bgcolor="#FFFFFF" style="padding: 5px">
														<iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
													 </td>
												</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="ass()">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divDel" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;承诺时间延期</td>
									</tr>
									<s:form name="formDel" action="delay" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
													<td width="14%" bgcolor="#EBF4F5">工单号:</td>
													<td bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
												</tr>
												<tr>
													<td width="14%" bgcolor="#EBF4F5">延后时间:</td>
													<td colspan=3 bgcolor="#FFFFFF">
														<input id="timer" type="text" name="timer" size=40 style="width: 200px" onKeyUp="if(this.value<1000000){}else{this.value='0'}">
														<select name="danwei" style="font-size: 9px">
															<option value="m">Minute</option>
															<option value="h">Hour</option>
															<option value="d">Day</option>
														</select>
													</td>
												</tr>
												<tr>
													<td width="14%" valign="top" bgcolor="#EBF4F5">原因:</td>
													<td width="86%" colspan=3 bgcolor="#FFFFFF">
														<textarea id="delaycause" name="serviceTran.note" rows=5 style="width: 70%"></textarea>
													</td>
												</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="del()">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divNot" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;发送任务通知</td>
									</tr>
									<s:form name="formNot" action="" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
													<td width="14%" bgcolor="#EBF4F5">工单号:</td>
													<td bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
												</tr>
												<tr>
													<td width="14%" valign="top" bgcolor="#EBF4F5">通知发送方式:</td>
													<td width="86%" colspan=3 bgcolor="#FFFFFF" style="padding-left: 1px">
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
													<td width="14%" valign="top" bgcolor="#EBF4F5">通知内容:</td>
													<td width="86%" colspan=3 bgcolor="#FFFFFF">
														<textarea name="ITMind" rows=5 style="width: 70%"></textarea>
													</td>
												</tr>
												<tr>
													<td width="14%" bgcolor="#EBF4F5" valign="top">接收人:</td>
													<td width="86%" colspan=3 bgcolor="#FFFFFF"style="padding-left: 1px">
														<table border="0" cellspacing="0" cellpadding="0" width="100">
															<tr>
																		
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
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="if(confirm('你确认要提交吗？')){document.getElementById('formNot').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divRel" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;关联请求</td>
									</tr>
									<s:form name="formRel" action="ret" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
													<td width="14%" class="td-left">工单号:</td>
													<td class="td-detail"><s:property value="serviceRequest.requestNo"></s:property></td>
												</tr>
												<tr>
													<td rowspan="2" valign="top" class="td-left">相关请求单:</td>
													<td valign="bottom" class="td-right" style="padding-left: 5px">
														<input name="Cause" type="text" style="width: 500; cursor: hand" value="" readonly>
													</td>
												</tr>
												<tr>
													<td style="padding: 5px" class="td-right">
														<iframe frameborder="0" height="130" width="90%" scrolling="yes" style="border: 1px inset" name="l1" id="l1" src="../inci/?NowAction=requestlist&value="></iframe>
													</td>
												</tr>

											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="if(confirm('你确认要提交吗？')){document.getElementById('formRel').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divPro" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;升级为问题</td>
									</tr>
									<s:form name="formPro" action="ret" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">问题初步分析:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceTran.note"  cssStyle="width: 100%"></s:textarea>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="退回" onclick="if(confirm('你确认要退回吗？')){document.getElementById('formPro').submit();}">
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
</div>
</body>
</html>
<Script Language="JavaScript" src="${pageContext.request.contextPath}/js/mmBtn.js"></Script>
