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


<body onLoad="getDAO()" background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件详细情况:</td>
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
									<td height="12" style="padding-top: 8px" nowrap> <a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
									
									<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">									
										<input type="button" id="button2" class="mmBtn" value="确认关闭" onclick="ShowApprove('reFin')">
										<input type="button" id="button1" class="mmBtn" value="重新指派任务" onclick="ShowApprove('reAss')">						
										<input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)">
										
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divreFin").style.display = "none";
								document.getElementById("divreAss").style.display = "none";
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>
							<div id="divreFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp; 反馈意见补单</td>
									</tr>
										<s:form name="formfClo" action="feedback" method="post" theme="simple">
											<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
											<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
											<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">客户满意度:</td>
														<td class="td-detail" style="padding-left: 0px">
															<table border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td style="padding-top: 3px">
																		<input type="radio" id="ClientIde" name="serviceRequest.serviceLvl" class="noborder" value="1" checked>
																	</td>
																	<td style="padding-right: 10px">满意</td>
																	<td style="padding-top: 3px">
																		<input type="radio" id="ClientIde" name="serviceRequest.serviceLvl" class="noborder" value="2">
																	</td>
																	<td style="padding-right: 10px" nowrap>一般</td>
																	<td style="padding-top: 3px">
																		<input type="radio" id="ClientIde" name="serviceRequest.serviceLvl" class="noborder" value="3">
																	</td>
																	<td nowrap>不满意</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">客户意见:</td>
														<td width="86%" colspan=3 class="td-detail">
															<textarea name="serviceRequest.feedback" rows=10 style="width: 100%"></textarea>
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
											<input type="button" class="mmBtn" value="提交" onclick="if(confirm('你确认要提交吗？')){document.getElementById('formfClo').submit();}">
									  </td>
									</tr>
								</table>
							</div>							
							<div id="divreAss" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;重新指派任务</td>
									</tr>
									<s:form name="formreAss" action="reassign" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value=""></s:hidden>
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
														<textarea name="serviceRequest.cause" rows=4 style="width: 100%"></textarea>
													</td>
												</tr>
												<tr>
														<td height="12" bgcolor="#EBF4F5">工作组:</td>
														<td  bgcolor="#FFFFFF" colspan="3" style="padding: 0px; padding-left: 7px">
															<select style="width: 120px" id="location" onChange="getInfo1();">
        														<option value="-1" selected>--请选择--</option>
    														</select>
    														<select style="width: 180px" id="engineerteam"  onChange="getInfo2();">
       															<option value="-1">--请选择--</option>
    														</select>
														</td>
													</tr>
													<tr>
														<td bgcolor="#EBF4F5">选择工程师:</td>
														<td bgcolor="#FFFFFF" style="padding: 5px">
															<iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
													  </td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">优先级:</td>
														<td width="86%" colspan=3 bgcolor="#EBF4F5">
															<table width="20" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<select id="essential" name="serviceRequest.severityTypByEssential.id" ></select>
																	</td>
																	<td style="font-family: 'Times New Roman'">&nbsp;<i>&</i>&nbsp;</td>
																	<td>
																		<select id="emergency" name="serviceRequest.severityTypByEmergency.id" ></select>
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
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="if(confirm('你确认要提交吗？')){document.getElementById('formreAss').submit();}">
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
