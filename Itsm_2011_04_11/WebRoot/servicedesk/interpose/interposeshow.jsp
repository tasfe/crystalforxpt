<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@page import="com.combanc.*"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title><s:property value="serviceRequest.requestNo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/util.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/engine.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/LocationDAO.js'></script>
	<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/RoleteamDAO.js'></script>
	<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
	
	<script type="text/javascript">
function getDAO(){ //取出类别
   UserDAO.findAll(callbackusers);
   LocationDAO.findAll(callbacklocation);
   RoleteamDAO.findAll(callbackroleteam);
 
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
function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
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
	document.getElementById("ITer").src="${pageContext.request.contextPath}/itsm/role/query.action?role.id="+roleteam;
}

function getUser(id) {
		document.getElementById("userinfo").src="${pageContext.request.contextPath}/itsm/user/query.action?userId="+id;
	}
</script>

		<script type="text/javascript">
function queding(){
window.close();
alert("成功");}
</script>

	</head>


<script language="JavaScript">
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
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">问题详细情况:</td>
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
															<td width="19%" bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
															<td width="14%" bgcolor="#EBF4F5">状态:</td>
															<td width="19%" bgcolor="#FFFFFF">
																	<s:if test="#serviceRequest.state==0">待受理</s:if>
          															<s:elseif test="#serviceRequest.state==1">正在处理中</s:elseif>
          															<s:elseif test="#serviceRequest.state==2">流程进行中</s:elseif>
          															<s:elseif test="#serviceRequest.state==3">未受理</s:elseif>
          															<s:elseif test="#serviceRequest.state==4">已完成</s:elseif></td>
															<td width="14%" bgcolor="#EBF4F5">责任工程师:</td>
															<td width="20%" bgcolor="#FFFFFF"><s:property value="serviceRequest.usersByEngineerId.truename"></s:property></td>
														</tr>
														<tr>
															<td nowrap bgcolor="#EBF4F5">申报人帐号:</td>
															<td bgcolor="#FFFFFF"><s:property value="serviceRequest.usersByRequestUser.username"></s:property></td>
															<td bgcolor="#EBF4F5">申报时间:</td>
															<td bgcolor="#FFFFFF"><s:property value="serviceRequest.submintTime"></s:property></td>
															<td bgcolor="#EBF4F5">问题类别:</td>
					                               			<td bgcolor="#FFFFFF"><s:property value="serviceRequest.serviceCategory.itemZh"></s:property></td>
														</tr>
														<tr>
															<td bgcolor="#EBF4F5">优先级:</td>
															<td bgcolor="#FFFFFF">
																	<s:property value="serviceRequest.priorityLvl"></s:property>															</td>
															<td bgcolor="#EBF4F5">摘要:</td>
															<td bgcolor="#FFFFFF"><s:property value="serviceRequest.summary"></s:property></td>
														</tr>
														<tr>
															<td height="99%" rowspan="2" valign="top" bgcolor="#EBF4F5" style="line-height: 22px">问题描述:</td>
															<td height="50" colspan=5 valign="top" bgcolor="#FFFFFF" style="font-weight: normal; line-height: 22px">
																<div style="width: 90%"><s:property value="serviceRequest.description"></s:property></div>
														  </td>
														</tr>
														<tr>
															<td height="18" colspan="5" valign="top" nowrap style="font-weight: normal; background-color: #FFFFCC; padding: 0px; padding-left: 5px">
																<div style="position: absolute; overflow: hidden"><img src="${pageContext.request.contextPath }/img/incident.gif" align="absmiddle">该问题正在等待工程师“<s:property value="serviceRequest.usersByEngineerId.truename"/>”受理，相关责任人可以取消未受理的问题。</div>
														  </td>
														</tr>
														<tr>
															<td nowrap bgcolor="#EBF4F5">自动转交时间:</td>
															<td bgcolor="#FFFFFF"><s:property value="serviceTran.autoOrEndTime"></s:property></td>
															<td bgcolor="#EBF4F5">承诺完成时间:</td>
															<td bgcolor="#FFFFFF"><s:property value="serviceRequest.promiseTime"></s:property></td>
															<td nowrap bgcolor="#EBF4F5">剩余可用处理时间:</td>
														    <td bgcolor="#FFFFFF"><s:property value=""></s:property></td>
														</tr>
												  </table>
												</td>
											</tr>
											<tr>
												<td height="10" colspan="2"><img height="0" width="830"></td>
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
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_re"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="re">问题解决计划</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_re"></td>
																					</tr>
																				</table>
																			</td>
																			
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('gl')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_gl"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="gl">其他相关联工单</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_gl"></td>
																					</tr>
																				</table>
																			</td>
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('ci')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_ci"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ci">涉及配置</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_ci"></td>
																					</tr>
																				</table>
																			</td>
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('tr')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_tr"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="tr">转交记录</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_tr"></td>
																					</tr>
																				</table>
																			</td>
																			
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('so')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_ch_left.gif" name="left_so" id="left_so"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_ch.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 3px" nowrap id="so">问题处理结果</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_ch_right.gif" name="right_so" id="right_so"></td>
																					</tr>
																				</table>
																			</td>
																			
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('ps')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_ps"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ps">审议</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_ps"></td>
																					</tr>
																				</table>
																			</td>
																			
																			<td style="padding-right: 1px; cursor: hand" onClick="Table('hi')">
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_left.gif" id="left_hi"></td>
																						<td background="${pageContext.request.contextPath }/img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="hi">活动记录</td>
																						<td><img src="${pageContext.request.contextPath }/img/tab_un_right.gif" id="right_hi"></td>
																					</tr>
																				</table>
																			</td>
																			<td align="right" width="99%">
																				
																				<table border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td onclick="window.print()" style="cursor: hand" nowrap><img src="${pageContext.request.contextPath }/img/print.jpg" align="absmiddle">&nbsp;打印</td>
																						<td><img width="10" height="1"></td>
																						<td onclick="window.save()" style="cursor: hand" nowrap><img src="${pageContext.request.contextPath }/img/save.jpg" align="absmiddle">&nbsp;保存&nbsp;</td>
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
																<table width="100%" height="220" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr bgcolor="#b5d6e6">
																					<td class="alllisttitle" align="center" width="14%" nowrap>转交人
																					<td class="alllisttitle" align="center" width="50%" nowrap>转交原因
																					<td class="alllisttitle" align="center" width="16%">转交至
																					<td class="alllisttitle" align="center" width="12%" nowrap>时间
																					<td class="alllisttitle" align="center" width="8%" nowrap style="text-align: center">详细
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
																				<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF">
																					
																					<tr>
																						<td colspan="3" valign="top">
																						<iframe frameborder="0" height="100%" width="100%" scrolling="yes" style="border: 1px inset"
																						src="tranrecode.action?serviceTran.requNo=<s:property value='serviceRequest.requestNo'></s:property>"></iframe></td>
																					</tr>
																					
																			  </table>
																			</div>
																		</td>
																	</tr>
															  </table>
															</td>
														</tr>
														<tr id="gl_table" style="display: none">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr bgcolor="#EBF4F5">
																		<td valign="top" height="12" style="overflow-y:scroll">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr bgcolor="#b5d6e6">
																					<td width="14%" height="22" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">工单号
																					<td width="43%" height="12" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">类别
																					<td width="27%" height="12" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">工程师
																					<td width="8%" height="12" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">选择
																			        <td width="8%" height="12" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">查看
																			     </tr>
																		  </table>
																		</td>
																	</tr>
																			<tr>
																				<td>
																				
																					<iframe width="100%" height="100%" scrolling="yes"
																						frameborder="0" style="border: 0px" id="request"
																						name="request"
																						src="${pageContext.request.contextPath }/servicedesk/interpose/inci.jsp"></iframe>
																					
																							</td>
																						</tr>
																					</table>
																				</td>
																			</tr>
																			<tr id="re_table" style="display: none">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td height="12" valign="top" bgcolor="#b5d6e6">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr>
																					<td height="12" nowrap bgcolor="#b5d6e6" class="alllisttitle">
																						问题解决计划</tr>
																		  </table>
																	  </td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
																				<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
																					<tr bgcolor="#FFFFFF">
																						<td width="14%" height="12" bgcolor="#EBF4F5">&nbsp;操作时间:</td>
																					    <td width="86%"><s:property value="serviceRequest.beginTime"></s:property></td>
																				  </tr>
																					
																					<tr bgcolor="#FFFFFF">
																						<td valign="top" bgcolor="#EBF4F5">
																							解决计划内容:																					  </td>
																						<td colspan=5 valign="top" style="font-weight: normal; line-height: 22px; padding-right: 80px;">	
																						<s:property value="serviceRequest.plan"></s:property>																					</td>
																				  </tr>
																			  </table>
																			</div>
																		</td>
																	</tr>
															  </table>
															</td>
														</tr>
														<tr id="hi_table" style="display: none">
															<td class="td-bg">
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" style="overflow-y: scroll; background-image: url(${pageContext.request.contextPath }/img/trh.jpg); border-bottom: 1px solid #EBE9ED" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr>
																					<td height="12" nowrap bgcolor="#b5d6e6" class="alllisttitle">活动记录跟踪</tr>
																		  </table>
																		</td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<iframe width="100%" height="100%" src="${pageContext.request.contextPath }/servicedesk/interpose/desk.jsp" scrolling="yes" frameborder="0" style="border: 0px"></iframe>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="ps_table" style="display: none">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr bgcolor="#b5d6e6">
																			  <td height="12" nowrap>问题评审和复议																				</tr>
																		  </table>
																		</td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
																				<table width="100%" border="0" cellspacing="2" cellpadding="4">
																					
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="ch_table" style="display: none">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="#FFFFFF">
																				<tr bgcolor="#b5d6e6">
																					<td width="14%" height="12" align="center" nowrap class="alllisttitle">工单号
																					<td width="37%" align="center" class="alllisttitle">实施人
																					<td width="40%" align="center" class="alllisttitle">实施时间
																				  <td width="9%" align="center" class="alllisttitle" style="text-align: center">查看</tr>
																		  </table>
																		</td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
																				<table width="100%" border="0" cellspacing="2" cellpadding="4">
																					
																				</table>
																			</div>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr id="so_table">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr>
																					<td height="12" nowrap bgcolor="#b5d6e6" class="alllisttitle">问题处理结果																				</tr>
																		  </table>
																		</td>
																	</tr>
																	<tr>
																		<td valign="top" bgcolor="#FFFFFF">
																			<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
																				<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
																					<tr bgcolor="#F9F9F9">
																						<td width="14%" height="12" nowrap bgcolor="#EBF4F5">&nbsp;完成时间:</td>
																						<td width="86%" colspan="3" bgcolor="#FFFFFF" style="padding-left: 6px">2010年8月18日 09:52:42</td>
																					</tr>
																					<tr bgcolor="#F9F9F9">
																						<td width="14%" height="29" valign="top" nowrap bgcolor="#EBF4F5">&nbsp;等待时长:</td>
																						<td width="36%" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">434</td>
																						<td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;处理时长:</td>
																						<td width="36%" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">43</td>
																					</tr>
																					<tr bgcolor="#F9F9F9">
																						<td width="14%" height="29" valign="top" nowrap bgcolor="#EBF4F5">&nbsp;是否超时响应:</td>
																						<td width="36%" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">否</td>
																						<td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;是否超时完成:</td>
																						<td width="36%" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">是</td>
																					</tr>
																					<tr bgcolor="#F9F9F9">
																						<td width="14%" height="29" valign="top" nowrap bgcolor="#EBF4F5">&nbsp;总耗时长:</td>
																						<td width="36%" colspan="3" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">23232</td>
																					</tr>
																					<tr bgcolor="#F9F9F9">
																						<td valign="top" nowrap bgcolor="#EBF4F5">&nbsp;问题解决方案:</td>
																						<td colspan="3" valign="top" bgcolor="#FFFFFF" style="padding-left: 6px">
																							<div style="overflow-x: hidden; width: 680px"> <a id="DisContent">
																								北京康邦科技有限公司
																							</div>
																					  </td>
																					</tr>
																					
																			  </table>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="4">
																				<tr>
																					<td bgcolor="#FFFFFF" width="99%"></td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="查看解决方案" class=mmBtn_sm name="button" onClick="window.open()" >
																				  </td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="查看任务通知" class=mmBtn_sm name="button" onClick="window.open('')">
																				  </td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="查看知识库" class=mmBtn_sm name="button" onClick="window.open('')">
																				  </td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="工作日志" class=mmBtn_sm name="button" onClick="window.open('')">
																				  </td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="添加新日志" class=mmBtn_sm name="button" onClick="window.open('')" >
																				  </td>
																					<td bgcolor="#FFFFFF">
																						<input type="button" value="发起一个事件" class=mmBtn_sm onclick="window.open('')" >
																				  </td>
																				</tr>
																		  </table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<input type="hidden" name="ConfigCh" id="ConfigCh" value="">
														<input type="hidden" name="Requests" id="Requests">
														<tr id="ci_table" style="display: none">
															<td>
																<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
																	<tr>
																		<td valign="top" height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="2" bgColor="white">
																				<tr>
																					<td width="14%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">代码
																					<td width="43%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">名称
																					<td width="27%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">类别
																					<td width="8%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">选中
																				  <td width="8%" align="center" nowrap bgcolor="#b5d6e6" class="alllisttitle">查看																				</tr>
																		  </table>
																		</td>
																	</tr>
																	<tr>
																		<td height="99%" style="padding: 0px;">
																			<iframe width="100%" height="100%" scrolling="yes" frameborder="0" style="border: 0px" id="configtb" name="configtb" src="${pageContext.request.contextPath }/servicedesk/interpose/asst.jsp"></iframe>
																		</td>
																	</tr>
																	<tr>
																		<td height="12">
																			<table width="100%" border="0" cellspacing="1" cellpadding="4">
																				<tr>
																					<td width="1%" bgcolor="#F1F2F7">
																						<input type="button" value="已选中" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&type=1&chose='+document.getElementById('ConfigCh').value">
																					</td>
																					<td width="1%" bgcolor="#F1F2F7">
																						<input type="button" value="保存更改" class=mmBtn_sm name="button" onClick="window.configtb.location='../prob/?NowAction=dbdeal&type=setCI2&RequNo=P0800000011&Value='+document.getElementById('ConfigCh').value" disabled>
																					</td>
																					<td width="99%" bgcolor="#F1F2F7">
																						<input name="config" type="text" id="config" style="width: 100%">
																					</td>
																					<td width="1%" bgcolor="#F1F2F7">
																						<input type="button" value="搜索" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&type=1&key='+document.getElementById('config').value">
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
												<td height="10" colspan="2"><img height="0" width="760"></td>
											</tr>
											<tr>
												<td height="12" colspan="2">
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
																	<tr>
																		<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;&nbsp;<img src="../../img/jiedian.gif" width="10" height="9">所包含任务</td>
																	</tr>
																	<tr bgcolor="#FFFFFF">
																		<td height="22" valign="top" background="../../images/main20100521_58.gif" bgcolor="#EBF4F5"><table width="100%" border="0" cellspacing="1" cellpadding="0" bgColor="white">
                                                                          <tr bgcolor="#EBF4F5">
                                                                            <td width="14%" align="center" nowrap>执行人
                                                                            <td width="8%" align="center" nowrap>任务号
                                                                            <td width="35%" align="center" nowrap>摘要
                                                                            <td width="27%" align="center" nowrap>须完成时间
                                                                            <td width="8%" align="center" nowrap>取消
                                                                            <td width="8%" align="center" nowrap>查看 </tr>
                                                                        </table></td>
																  </tr>
																	<tr bgcolor="#FFFFFF">
																		<td height="130">
																		
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
						</td>
					</tr>
					<tr>
						<td>
							<table cellspacing=0 cellpadding=0 border=0 width="100%">
								<tr>
									<td height="12" style="padding-top: 8px" nowrap> <a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
									<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
										<input type="button" class="mmBtn" value="强制转交问题" onclick="ShowApprove('Tran')" >
										<input type="button" class="mmBtn" value="取消" onclick="ShowApprove('Del')" >
										<input type="button" class="mmBtn" value="通知" onclick="ShowApprove('Not')" >
										<input type="button" class="mmBtn" value="关闭窗口" onclick="window.close()">
								  </td>
								</tr>
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divAct").style.display = "none";
								document.getElementById("divLay").style.display = "none";
								document.getElementById("divTran").style.display = "none";
								document.getElementById("divDel").style.display = "none";
								document.getElementById("divApp").style.display = "none";
								document.getElementById("divFin").style.display = "none";
								document.getElementById("divNot").style.display = "none";
								document.getElementById("divCls").style.display = "none";
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>
							<div id="divNot" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;发送任务通知</td>
									</tr>
									<s:form action="" method="post" theme="simple">
										<input type=hidden name=ProNo value="P0800000011">
										<input type=hidden name=Type value="PrAnnounce">
										<input type=hidden name=ServiceTitle value="任务通知">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">通知发送方式:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF" style="padding-left: 1px">
															<table border="0" cellspacing="0" cellpadding="0" width="100">
																<tr>
																	<td style="padding-top: 2px" width="1%" bgcolor="#FFFFFF">
																		<input type="checkbox" style="border: 0px" checked disabled>
																  </td>
																	<td style="padding-right: 15px; padding-top: 1px" nowrap bgcolor="#FFFFFF">系统消息</td>
																	<td style="padding-top: 2px" width="1%" bgcolor="#FFFFFF">
																		<input name="Methods" type="checkbox" value="Mail" style="border: 0px">
																  </td>
																	<td style="padding-right: 15px; padding-top: 1px" nowrap bgcolor="#FFFFFF">邮件</td>
																	<td style="padding-top: 2px" width="1%" bgcolor="#FFFFFF">
																		<input name="Methods" type="checkbox" value="Smms" style="border: 0px">
																  </td>
																	<td style="padding-right: 15px; padding-top: 1px" nowrap bgcolor="#FFFFFF">手机短信</td>
																</tr>
														  </table>
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">通知内容:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">接收人:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF" style="padding-left: 1px">
															<table border="0" cellspacing="0" cellpadding="0" width="100">
																<tr>
																	
																	<td style="padding-top: 2px" width="1%">
																		<input name="ITer" type="checkbox" value="zhangxx" style="border: 0px">
																	</td>
																	<td style="padding-right: 25px; padding-top: 1px" nowrap> <font style="padding: 2px"> <s:property value="serviceRequest.usersByEngineerId.truename"/> </font> </td>
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
											<input type="button" class="mmBtn" value="发送" onclick="if(confirm('你确认要发送吗?')){document.getElementById('frmAct10').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divAct" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;制订问题解决计划</td>
									</tr>
									<s:form action="accept" method="post" theme="simple">
										<input type=hidden name="<s:property value="serviceRequest.requestNo"/>" value="<s:property value="serviceRequest.requestNo"/>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bordercolor="#FFFFFF" bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">事件解决计划:</td>
														<td width="86%" colspan=3 bordercolor="#FFFFFF" bgcolor="#FFFFFF">
															<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
													  </td>
													</tr>
													<tr>
														<td rowspan="2" bgcolor="#EBF4F5">附加文件:</td>
														<td height="20" colspan="3" bordercolor="#FFFFFF" bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<textarea name="UpFile2" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(${pageContext.request.contextPath }/img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile2&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
																	</td>
																	<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="${pageContext.request.contextPath }/img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile2').value=''" title="清除附件"></td>
																</tr>
															</table>
													  </td>
													</tr>
													<tr>
														<td bgcolor="#FFFFFF">
															<iframe name=upload src="${pageContext.request.contextPath }/servicedesk/interpose/uploadpro.jsp" frameborder=0 width="99%" scrolling=no height=25></iframe>
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
											<input type="button" class="mmBtn" value="提交" onclick="if(confirm('你确认要提交吗？')){document.getElementById('frmAct2').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divCls" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;关闭这个问题</td>
									</tr>
										<s:form action="clo" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">意见建议:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceRequest.solution" cssStyle="width: 100%"></s:textarea>
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
											<input type="button" class="mmBtn" value="关闭" onclick="if(confirm('你确认要关闭这个问题吗？')){document.getElementById('frmAct31').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divTran" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;转派给其它工程师</td>
									</tr>
									<s:form action="transmit" method="post" theme="simple" name="formTran">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value=""></s:hidden>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">转交原因:</td>
														<td colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceTran.note" cssStyle="width: 100%"></s:textarea>
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
																	<!-- <iframe frameborder="0" height="100%" width="500" scrolling="yes" src="../servicedesk/engineerrequest/prob.jsp"></iframe>
																    -->
														</td>
													</tr>
													<tr>
														<td bgcolor="#EBF4F5">选择工程师:</td>
														<td bgcolor="#FFFFFF" style="padding: 5px">
															<iframe frameborder="0" height="100" width="90%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
													  </td>
													</tr>
													<tr>
														<td width="14%" rowspan="2" bgcolor="#EBF4F5">附加文件:</td>
														<td width="86%" height="20" colspan=3 bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<textarea name="UpFile9" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile9&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
																	</td>
																	<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="../../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile9').value=''" title="清除附件"></td>
																</tr>
															</table>
													  </td>
													</tr>
													<tr>
														<td colspan="3" bgcolor="#FFFFFF">
															<iframe name=upload src="${pageContext.request.contextPath }/servicedesk/interpose/uploadpro.jsp" frameborder=0 width="97%" scrolling=no height=25></iframe>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center valign="middle" nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="转交" onclick="if(confirm('你确认要转派吗？')){document.getElementById('formTran').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divDel" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;取消这个问题</td>
									</tr>
									<s:form action="ret" method="post" theme="simple">
										<input type=hidden name=ProNo value="P0800000011">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">取消原因:</td>
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
											<input type="button" class="mmBtn" value="取消" onclick="if(confirm('你确认要取消吗？')){document.getElementById('frmAct4').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divApp" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;评审待受理的问题</td>
									</tr>
                                        <s:form name="formTran" action="transmit" method="post" theme="simple">                                        <input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value=""></s:hidden>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=2 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">选项:</td>
														<td width="86%" colspan=2 bgcolor="#FFFFFF">
															<select name="Approved" style="width: 50%" onChange="if(this.value=='10000'){document.getElementById('T1A').style.display='';document.getElementById('T2A').style.display='';document.getElementById('T3A').style.display=''}else{document.getElementById('T1A').style.display='none';document.getElementById('T2A').style.display='none';document.getElementById('T3A').style.display='none'}">
																<option value="" style="background-color: #FFFFCC">暂不通过评审</option>
																<option value="10000">确认并通过问题评审</option>
																<option value="10001">取消问题(非问题)</option>
																<option value="10002">取消问题(已知问题)</option>
															</select>
													  </td>
													</tr>
													
													<tr>
														<td width="14%" bgcolor="#EBF4F5">意见及建议:</td>
														<td width="86%" colspan=2 bgcolor="#FFFFFF">
															<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
													  </td>
													</tr>
													<tr>
														<td width="14%" rowspan="2" bgcolor="#EBF4F5">附加文件:</td>
														<td width="86%" height="20" colspan=2 bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<textarea name="UpFile7" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile7&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
																	</td>
																	<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="../../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile7').value=''" title="清除附件"></td>
																</tr>
															</table>
													  </td>
													</tr>
													<tr>
														<td colspan="2" bgcolor="#FFFFFF">
															<iframe name=upload src="/servicedesk/interpose/uploadpro.jsp" frameborder=0 width="97%" scrolling=no height=25></iframe>
													  </td>
													</tr>
												
													<tr bgcolor="#F9F9F9" style="display: none" id="astd_1">
																<td height="12">工作组:</td>
																<td colspan="3" style="padding: 0px; padding-left: 7px">
																	<select style="width: 120px" id="location" onChange="getInfo1();">
        																<option value="-1">--请选择--</option>
    																</select>
    																<select style="width: 180px" name="role.id" id="engineerteam"  onChange="getInfo2();">
       																	<option value="-1">--请选择--</option>
    																</select>
																	<!-- <iframe frameborder="0" height="100%" width="500" scrolling="yes" src="../servicedesk/engineerrequest/prob.jsp"></iframe>
																    -->
																</td>
															</tr>
															<tr bgcolor="#F9F9F9" style="display: none" id="astd_2">
																<td valign="top">选择工程师:</td>
																<td colspan="3" style="padding-left: 7px">																	  																
																   <iframe frameborder="0" height="100" width="90%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
																</td>
															</tr>
														<tr bgcolor="#F9F9F9">
																<td width="14%" height="12"></td>
																<td style="padding: 1px; padding-left: 3px" colspan="3">
																	<table border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td style="padding-top: 1px">
																				<input type="checkbox" class="noborder" onClick="if(this.checked){document.getElementById('astd_0').style.display='none';document.getElementById('astd_1').style.display='';document.getElementById('astd_2').style.display=''}else{document.getElementById('astd_0').style.display='';document.getElementById('astd_1').style.display='none';document.getElementById('astd_2').style.display='none';document.getElementById('ITprinc').value='';window.ITer.location='about:blank'}" id="ChoseITer">
																			</td>
																			<td nowrap style="padding-top: 1px; line-height: 13px">&nbsp;手工分配任务给指定工程师</td>
																		</tr>
																	</table>
																</td>
															</tr>
												
													<tr id="T3A" style="display: none">
														<td bgcolor="#EBF4F5">优先级:</td>
														<td width="86%" colspan="2" bgcolor="#FFFFFF">
															<table width="20" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<select name="Impact" style="height: 20px; width: 80px" onChange="window.sla.location='../desk/?NowAction=sla&Type=prob&RequObj=|32,|33,&RequPep=&Ess='+this.value+'&Eme='+document.getElementById('Urgency').value">
																			<option value="1">影响度</option>
																			
																			<option value="1" selected>低</option>
																			
																			<option value="2">中</option>
																			
																			<option value="3">高</option>
																			
																			<option value="4">极高</option>
																			
																		</select>
																	</td>
																	<td style="font-family: 'Times New Roman'">&nbsp;<i>&</i>&nbsp;</td>
																	<td>
																		<select name="Urgency" style="height: 20px; width: 80px" onChange="window.sla.location='../desk/?NowAction=sla&Type=prob&RequObj=|32,|33,&RequPep=&Eme='+this.value+'&Ess='+document.getElementById('Impact').value">
																			<option value="1">紧急度</option>
																			
																			<option value="1" selected>低</option>
																			
																			<option value="2">中</option>
																			
																			<option value="3">高</option>
																			
																			<option value="4">极高</option>
																			
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
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="发送" onclick="if(confirm('你确认要发送这个评审意见吗？')){document.getElementById('frmAct20').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;完成问题并汇总解决方案</td>
									</tr>
									<s:form action="" method="post" theme="simple">
										<input type=hidden name=ProNo value="P0800000011">
										<input type=hidden name=Attachs value="|Null|Null|Null|Null|Null">
										
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">问题知识库:</td>
														<td width="36%" bgcolor="#FFFFFF" style="padding-left: 1px">
															<table border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td style="padding-top: 3px">
																		<input type=checkbox class=noborder name=Knowlg id=Knowlg value="1">
																	</td>
																	<td nowrap>添加该解决方案到知识库</td>
																</tr>
															</table>
													  </td>
														<td width="14%" bgcolor="#EBF4F5">摘要:</td>
														<td width="36%" bgcolor="#FFFFFF">
															<input type=text name=KeyWord size=40 style="width: 100%" value="gasg">
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">解决方案:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea name="ITMind" rows=8 style="width: 100%"></textarea>
													  </td>
													</tr>
													<tr>
														<td rowspan="2" bgcolor="#EBF4F5">附加文件:</td>
														<td colspan="3" height="20" style="padding-top: 0px; padding-bottom: 0px">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td bgcolor="#FFFFFF">
																		<textarea name="UpFile4" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(${pageContext.request.contextPath }/img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile4&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
																  </td>
																	<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="${pageContext.request.contextPath }/img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile4').value=''" title="清除附件"></td>
																</tr>
														  </table>
														</td>
													</tr>
													<tr>
														<td colspan="3" bgcolor="#FFFFFF">
															<iframe name=upload src="${pageContext.request.contextPath }/servicedesk/interpose/uploadpro.jsp" frameborder=0 width="99%" scrolling=no height=25></iframe>
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
											<input type="button" class="mmBtn" value="发送" onclick="if(confirm('你确认要发送吗？')){document.getElementById('frmAct6').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divLay" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;指定相关工程师()</td>
									</tr>
									<s:form action="" method="post" theme="simple">
										<input type=hidden name=ProNo value="P0800000011">
										<input type=hidden name=TskST value="1">
										<input type=hidden name=MsgPRI value="1">
										<input type=hidden name=MsgType value="Prob">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"/></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">任务摘要:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<input Name="keyword" ID="keyword" style="width: 60%">
													  <br></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">执行人:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<input type="text" name="ShiYR" style="width: 30%" onKeyUp="document.getElementById('Layer').style.visibility='visible';window.User.location='../task/?NowAction=users&Cate=1&Key='+this.value" onClick="document.getElementById('Layer').style.visibility='visible';window.User.location='../task/?NowAction=users&Cate=1&Key='+this.value">
															<br>
															<div id="Layer" style="position:absolute; width:30%; height:20px; z-index:1; visibility: hidden; border: 0px">
																<table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
																	<tr>
																		<td>
																			<iframe id="User" name="User" frameborder="0" height="115" width="100%" scrolling="yes" src="../task/?NowAction=users&type=inci" style="border: 1px solid #E5E9EE"></iframe>
																		</td>
																	</tr>
																</table>
															</div>
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">任务内容描述:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea name="Detail" rows=4 style="width: 70%"></textarea>
													  </td>
													</tr>
													<tr>
														<td rowspan="2" bgcolor="#EBF4F5">附加文件:</td>
														<td height="20" colspan="3" bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<textarea name="UpFile6" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile6&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
																	</td>
																	<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="../../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile6').value=''" title="清除附件"></td>
																</tr>
															</table>
													  </td>
													</tr>
													<tr>
														<td bgcolor="#FFFFFF">
															<iframe name=upload src="${pageContext.request.contextPath }/servicedesk/interpose/uploadpro.jsp" frameborder=0 width="99%" scrolling=no height=25></iframe>
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">须完成时间:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<table width="20" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<input Name="StartTime" ID="StartTime" size="9" onclick="gfPop.fPopCalendar(this);return false;" readonly style="border-right: 0px; width: 70px" value="2010-5-24"><input style="border-left: 0px; border-right: 0px; width: 15px; padding-right: 0px" value="09" maxlength="2" name="shi1" id="shi1" onKeyUp="if(this.value<24){}else{this.value='23'}"><input readonly style="border-left: 0px; border-right: 0px; width: 8px" value=":"><input style="border-left: 0px; border-right: 0px; width: 15px; padding-right: 0px" value="59" maxlength="2" name="fen1" id="fen1" onKeyUp="if(this.value<60){}else{this.value='00'}"><input readonly style="border-left: 0px; border-right: 0px; width: 8px" value=":"><input style="border-left: 0px; width: 18px; padding-right: 0px" value="54" maxlength="2" name="miao1" id="miao1" onKeyUp="if(this.value<60){}else{this.value='00'}">
																	</td>
																	<td style="padding-left: 10px; padding-right: 10px">~</td>
																	<td>
																		<input Name="CompTime" ID="CompTime" size="9" onclick="gfPop.fPopCalendar(this);return false;" readonly style="border-right: 0px; width: 70px"><input style="border-left: 0px; border-right: 0px; width: 15px; padding-right: 0px" value="00" maxlength="2" name="shi2" id="shi2" onKeyUp="if(this.value<24){}else{this.value='23'}"><input readonly style="border-left: 0px; border-right: 0px; width: 8px" value=":"><input style="border-left: 0px; border-right: 0px; width: 15px; padding-right: 0px" value="00" maxlength="2" name="fen2" id="fen2" onKeyUp="if(this.value<60){}else{this.value='00'}"><input readonly style="border-left: 0px; border-right: 0px; width: 8px" value=":"><input style="border-left: 0px; width: 18px; padding-right: 0px" value="00" maxlength="2" name="miao2" id="miao2" onKeyUp="if(this.value<60){}else{this.value='00'}">
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
										<td align="center" nowrap style="padding-top: 8px; padding-bottopx"><br>
			                              <input type="button" class="mmBtn" value="发送" onClick="if(confirm('你确认要发送吗？')){ frmRef.submit();}" name="button">								
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
