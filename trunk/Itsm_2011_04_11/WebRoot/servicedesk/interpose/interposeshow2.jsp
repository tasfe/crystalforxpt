<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

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
<script language="JavaScript">
function html_clock(Num){
	var Num = Num-1;
	if (Num<1) {
		window.document.getElementById("theclock").innerHTML = "- - : - - : - -";
	} else {
		var Hours1 = (Num/60)/60;
		var Hours = Math.round((Num/60)/60);
		if (Hours > Hours1) Hours = Hours-1;
		var Minutes1 = (Num-Hours*60*60)/60;
		var Minutes = Math.round((Num-Hours*60*60)/60);
		if (Minutes > Minutes1) Minutes = Minutes-1;
		var Seconds = Num-Hours*60*60-Minutes*60;
		if (Hours < 12) window.document.getElementById('theclock').style.color = 'Orange';
		if (Hours < 8) window.document.getElementById('theclock').style.color = 'red';
		if (Hours < 4) window.document.getElementById('theclock').style.color = 'black';
		if (Hours < 10) Hours = "0" + Hours;
		if (Minutes < 10) Minutes = "0" + Minutes;
		if (Seconds < 10) Seconds = "0" + Seconds;
		window.document.getElementById("theclock").innerHTML = Hours+":"+Minutes+":"+Seconds;
		setTimeout ("html_clock('"+Num+"')", 1000);
	}
}
</script>
</head>
<body onLoad="getDAO()" background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件请求详情:</td>
  </tr>
</table>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td valign="top" id="mainright">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
				
		<script language="JScript">
		function Table(id){
			var oldid = document.getElementById("tmp").value;
			if (id!=oldid) {
				document.getElementById("left_"+oldid).src="../img/tab_un_left.gif";
				document.getElementById("right_"+oldid).src="../img/tab_un_right.gif";
				document.getElementById(oldid).background="../img/tab_un.gif";
				document.getElementById(oldid).style.paddingTop="5px";
				document.getElementById(oldid+"_table").style.display="none";
				document.getElementById("left_"+id).src="../img/tab_ch_left.gif";
				document.getElementById("right_"+id).src="../img/tab_ch_right.gif";
				document.getElementById(id).background="../img/tab_ch.gif";
				document.getElementById(id).style.paddingTop="3px";
				document.getElementById(id+"_table").style.display="";
				document.getElementById("tmp").value=id;
			}
		}
		</script>
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
					<td height="10" colspan="2"><img height="0" width="850"></td>
				  </tr>
				  <tr>
					<td height="12" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb-list">
					  <tr> 
						<td height="29" valign="top">
						   <div style="position: absolute; overflow: hidden; height: 100%; width: 100%; border-right: 2px solid white">
						   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="Gray">
							<tr>
							  
							  <td style="padding-right: 1px; cursor: hand" onClick="Table('us')">
								<table border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td><img src="../img/tab_un_left.gif" id="left_us"></td>
									<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="us">申请人详情</td>
									<td><img src="../img/tab_un_right.gif" id="right_us"></td>
								  </tr>
								</table>
							  </td>
							  
							  <td style="padding-right: 1px; cursor: hand" onClick="Table('so')">
								<table border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td><img src="../img/tab_ch_left.gif" id="left_so"></td>
									<td background="../img/tab_ch.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="so">处理结果</td>
									<td><img src="../img/tab_ch_right.gif" id="right_so"></td>
								  </tr>
								</table>
							  </td>
							  
							  <td style="padding-right: 1px; cursor: hand" onClick="Table('gl')">
								<table border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td><img src="../img/tab_un_left.gif" id="left_gl"></td>
									<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="gl">其他相关联工单</td>
									<td><img src="../img/tab_un_right.gif" id="right_gl"></td>
								  </tr>
								</table>
							  </td>
							  
							  <td style="padding-right: 1px; cursor: hand" onClick="Table('hd')">
								<table border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td><img src="../img/tab_un_left.gif" id="left_hd"></td>
									<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="hd">活动记录</td>
									<td><img src="../img/tab_un_right.gif" id="right_hd"></td>
								  </tr>
								</table>
							  </td>
							  <td align="right" width="99%" style="padding-left: 50px">
								
							  </td>
							</tr>
						  </table>
						  </div>
						 </td>
					  </tr>
					  <tr id="ps_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td class="subtitle">变更评审意见</td> 
									</tr>
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
						  </table></td>
					  </tr>
					  <tr id="re_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">服务请求任务指派</td></tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" height="100%">
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;时间:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;操作人:</td>
										  <td width="86%" valign="top" style="padding-left: 6px">工程师</td>
										</tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" valign="top" height="99%" bgcolor="#EBF4F5">&nbsp;意见建议:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
								  </table>
								 </div>
							   </td>
							</tr>
						  </table></td>
						</tr>
						<tr id="tr_table" style="display: none">
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td class="subtitle" width="14%" nowrap bgcolor="#EBF4F5">转交人</td>
									  <td class="subtitle" width="50%" nowrap bgcolor="#EBF4F5">转交原因</td>
									  <td class="subtitle" width="16%" bgcolor="#EBF4F5">转交至</td>
									  <td class="subtitle" width="12%" nowrap bgcolor="#EBF4F5">时间</td>
									  <td class="subtitle" width="8%" nowrap style="text-align: center" bgcolor="#EBF4F5">详细</td>
									</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
								  
										<tr bgcolor="#F9F9F9"> 
										  <td valign="top" colspan="3">&nbsp;没有记录</td>
										</tr>
									
								  </table>
								 </div>
							   </td>
							</tr>
						  </table></td>
						</tr>
						<tr id="yq_table" style="display: none">
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td class="subtitle" width="14%" nowrap>操作人</td>
									  <td class="subtitle" width="45%" nowrap>延期原因</td>
									  <td class="subtitle" width="16%">延期时长</td>
									  <td class="subtitle" width="25%" nowrap>时间</td>
									</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
									
										<tr bgcolor="#F9F9F9"> 
										  <td valign="top" colspan="3">&nbsp;没有记录</td>
										</tr>
									
								  </table>
								 </div>
							   </td>
							</tr>
						  </table></td>
						</tr>
						<tr id="so_table">
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">项目</td> 
									  <td class="subtitle" width="36%">内容</td> 
									  <td width="14%" height="12" nowrap class="subtitle">项目</td> 
									  <td class="subtitle" width="36%">内容</td> 
									</tr>
								  </table>
							   </td>
							</tr>
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" height="100%">
									   
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;开始时间:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;完成时间:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;等待时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;处理时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;是否超时响应:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;是否超时完成:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;总耗时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
										  <td width="14%" height="29" valign="top" bgcolor="#EBF4F5">&nbsp;事故原因:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" valign="top" height="99%" bgcolor="#EBF4F5">&nbsp;处理解决方案:</td>
										  <td width="86%" valign="top" style="padding-left: 6px" colspan="3">
											<div style="overflow-x: hidden; width: 90%">
											<a id="DisContent">
											
											</a>
											
											</div>
										  </td>
										</tr>
										
								  </table>
								 </div>
							   </td>
							</tr>
							<tr> 
							  <td height="12"><table width="100%" border="0" cellspacing="1" cellpadding="4" class="datagrid">
								  <tr>
									<td bgcolor="#F1F2F7" width="99%"></td>
									
									<td bgcolor="#F1F2F7"><input type="button" value="查看详细解决方案" class=mmBtn_sm name="button" onClick="window.open('../desk/?NowAction=solution&ID=C10082000001','','width=560,height=440,scrollbars=no,menubar=no,resizable=yes,top=80,left=176,status=yes')" disabled></td>
									
									<td bgcolor="#F1F2F7"><input type="button" value="查看任务通知" class=mmBtn_sm name="button" onClick="window.open('../chan/?NowAction=pnotice&Type=C10082000001','','width=600,height=450,scrollbars=no,menubar=no,resizable=no,top=80,left=126,status=yes')"></td>
									<td bgcolor="#F1F2F7"><input type="button" value="工作日志" class=mmBtn_sm name="button" onClick="window.open('../sla/?NowAction=logbook&ID=C10082000001','','width=550,height=500,scrollbars=no,menubar=no,resizable=yes,top=100,left=126,status=yes')"></td>
									
								</tr></table></td>
							</tr>
						  </table></td>
						</tr>
						<tr id="tu_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">时间</td> 
									  <td class="subtitle" width="50%">退单原因</td> 
									  <td class="subtitle" width="28%">工程师</td>
									  <td class="subtitle" width="8%" nowrap style="text-align: center">详细</td>
									</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
								  
									   <tr bgcolor="#F9F9F9">
										  <td valign="top" colspan="3">&nbsp;没有记录</td>
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
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">工单号</td> 
									  <td width="43%" height="12" nowrap class="subtitle">类别</td> 
									  <td width="27%" height="12" nowrap class="subtitle">工程师</td> 
									  <td width="8%" height="12" nowrap class="subtitle" align="center">选择</td> 
									  <td width="8%" height="12" nowrap class="subtitle" align="center">查看</td> 
									</tr>
							  	<s:iterator value="relatedRequestList" var="serviceRequest" status='st'>
        							<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
        							<td align="center" width="14%" nowrap><s:property value="requestNo"></s:property></td>
									<td align="center" width="52%" nowrap><s:property value="serviceCategory.itemZh"></s:property></td>
									<td align="center" width="16%"><s:property value="usersByEngineerId.truename"/></td>
									<td align="center" width="12%" nowrap>选择</td>
									<td align="center" width="6%" nowrap style="text-align: center">详细</td>
        							</tr>
      							</s:iterator>
								  </table>
							   </td>
							</tr> 
						  </table>
						  </td>
						</tr>
						<tr id="fe_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">用户反馈信息</td> 								</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" height="100%">
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top">&nbsp;满意度:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
									  
										<tr bgcolor="#F9F9F9"> 
										  <td width="14%" valign="top" height="99%">&nbsp;反馈意见:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
								  </table>
								 </div>
							   </td>
							</tr>
						  </table>
						  </td>
						</tr>
						<tr id="fs_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">复审及关闭</td> 								</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" height="100%">
										<tr bgcolor="#F9F9F9"> 
										  <td width="14%" valign="top" height="99%">&nbsp;意见建议:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
								  </table>
								 </div>
							   </td>
							</tr>
						  </table></td>
						</tr>
						<tr id="hd_table" style="display: none"> 
						<td class="td-bg">
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg); border-bottom: 1px solid #EBE9ED" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td height="12" nowrap class="subtitle">活动记录跟踪</td> 
									</tr>
								  </table>
							   </td>
							  </tr>
							  <tr> 
								<td valign="top" bgcolor="#FFFFFF"><iframe width="100%" height="100%" src="desk.action?requestNo=<s:property value='requestNo'/>" scrolling="yes" frameborder="0" style="border: 0px"></iframe></td>
							  </tr>
							</table>
						  </td>
						</tr>
						<tr id="us_table" style="display: none"> 
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg); border-bottom: 1px solid #EBE9ED" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">项目</td> 
									  <td class="subtitle" width="36%">内容</td> 
									  <td width="14%" height="12" nowrap class="subtitle">项目</td> 
									  <td class="subtitle" width="36%">内容</td> 
									</tr>
								  </table>
							   </td>
							</tr> 
							<tr> 
							  <td valign="top" bgcolor="#FFFFFF">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
								  
							  <tr bgcolor="#F9F9F9"> 
										<td width="14%" height="29">&nbsp;登录名:</td>
										<td valign="top" style="padding-left: 8px"><s:property value="serviceRequest.usersByRequestUser.username" /></td>
										<td width="14%">&nbsp;显示全名:</td>
										<td width="36%" valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.truename" /></div></td>
									  </tr>
									  <tr bgcolor="#F9F9F9" style="display: none"> 
										<td width="14%" height="29">&nbsp;所在公司:</td>
										<td valign="top" colspan="3"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.company" /></div></td>
									  </tr>
									  <tr bgcolor="#F9F9F9"> 
										<td width="14%" height="29">&nbsp;所在部门:</td>
										<td nowrap valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.department.name" /></div></td>
										<td width="14%">&nbsp;所在位置:</td>
										<td width="36%" valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.location.name" /></div></td>
									  </tr>
									  <tr bgcolor="#F9F9F9"> 
										<td width="14%" height="29">&nbsp;电话:</td>
										<td width="36%" valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.phone" /></div></td>
										<td width="14%">&nbsp;手机:</td>
										<td width="36%" valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.cellphone" /></div></td>
									  </tr>
									  <tr bgcolor="#F9F9F9"> 
										<td width="14%" height="29">&nbsp;最后登录:</td>
										<td width="36%" valign="top"><div class='DivOut'><s:property value="serviceRequest.usersByRequestUser.lastAccessTime" /></div></td>
										<td width="14%" height="29">&nbsp;电子邮箱:</td>
										<td width="36%" valign="top"><div class='DivOut'><a href="MailTo:"><s:property value="serviceRequest.usersByRequestUser.email" /></a></div></td>
									  </tr>
								  </table>
								 </div>
							   </td>
							</tr>
						  </table></td>
						</tr>
					  </table>
					</td>
				  </tr>
				  
				  <tr>
					<td height="10" colspan="2"><img height="0" width="830"></td>
				  </tr>
				  <tr>
					<td height="12" colspan="2">
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
					   <tr>
						<td width="48%">
						  <table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list" height="100%">
							<tr> 
							  <th style="padding: 0px" class="InTB">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;所涉及配置及变更</th>
							</tr>
							<tr> 
							  <td valign="top" style="padding: 0px; overflow-y: scroll; background-image: url(../img/try.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="white">
								  
									<tr> 
									  <td width="14%" height="12" nowrap class="subtitle">代码</td> 
									  <td width="43%" height="12" nowrap class="subtitle">名称</td> 
									  <td width="27%" height="12" nowrap class="subtitle">类别</td> 
									  <td width="8%" height="12" nowrap class="subtitle" align="center">选中</td> 
									  <td width="8%" height="12" nowrap class="subtitle" align="center">查看</td> 
									</tr>
								  </table>
							   </td>
							</tr>
							<input type="hidden" name="ConfigCh" id="ConfigCh" value=""> 
							<input type="hidden" name="Requests" id="Requests"> 
							<tr>
							  <td height="148" style="padding: 0px"><iframe width="100%" height="100%" scrolling="yes" frameborder="0" style="border: 0px" id="configtb" name="configtb" src="asst.action"></iframe></td>
							</tr>
							<tr> 
							  <td><table width="100%" border="0" cellspacing="1" cellpadding="4">
								  <tr>
									<td width="1%" bgcolor="#F1F2F7"><input type="button" value="已选中" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&chose='+document.getElementById('ConfigCh').value"></td>
									<td width="99%" bgcolor="#F1F2F7"><input name="config" type="text" id="config" style="width: 100%"></td>
									<td width="1%" bgcolor="#F1F2F7"><input type="button" value="搜索" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&key='+document.getElementById('config').value"></td>
									<td width="1%" bgcolor="#F1F2F7"><input type="button" value="当前用户" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&user=|user|'"></td>
								</tr></table></td>
							 </tr>
						  </table></td>
						 
						 <td width="52%" style="padding-left: 10px">
						  <table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list" height="100%">
							<tr> 
							  <th style="padding: 0px" class="InTB">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;相关解决方案</th>
							</tr>
							<tr>
							  <td height="209" style="padding: 0px"><iframe width="100%" height="100%" scrolling="yes" frameborder="0" style="border: 0px" id="request" name="request" src="desk.action"></iframe></td>
							</tr>
						  </table></td>
						
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
		<input id="tmp" value="so" type="hidden">
						<tr>
							<td>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td height="12" style="padding-top: 8px"><a id="theclock" style="font-size: 20px; font-family: 'Arial Black'; color: green" name="theclock"></a></td>
										<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="强制指派任务" onclick="ShowApprove('Act')">
											<input type="button" class="mmBtn" value="延期" onclick="ShowApprove('Yan')">
											<input type="button" class="mmBtn" value="通知" onclick="ShowApprove('Not')">
											<input type="button" class="mmBtn" value="关联请求" onclick="ShowApprove('Gl')">
											<input type="button" class="mmBtn" value="升级为问题" onclick="ShowApprove('Upd')">
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
										<s:form name="frmAct10" action="../prob/default.asp?NowAction=db_pro&type=ProjectAno" method='post' target="sum">
											<input type=hidden name=ProNo value="C10082000001">
											<input type=hidden name=Type value="SvAnnounce">
											<input type=hidden name=ServiceTitle value="任务通知">
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4" class="datagrid">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
															<td class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
															<td class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
															<td class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
															<td width="86%" colspan=3 class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
										<s:form action="assign" method="post" theme="smiple">
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
															<td colspan=3 class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
														</tr>
														<tr>
															<td width="14%" class="td-left" valign="top">意见或建议:</td>
															<td colspan=3 class="td-detail">
																<textarea name="ITMind" rows=4 style="width: 70%"></textarea>
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
										<s:form  action="refuse" method="post" theme="simple">
												<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
									        	<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										        <input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
											
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="2" cellpadding="4">
														<tr>
															<td width="14%" class="td-left">工单号:</td>
															<td width="86%" colspan=3 class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
														</tr>
														<tr>
															<td width="14%" valign="top" class="td-left">拒绝原因:</td>
															<td width="86%" colspan=3 class="td-detail">
																<s:textarea name="serviceRequest.cause"  cssStyle="width: 100%"></s:textarea>
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
															<td width="86%" colspan=3 class="td-detail"><s:property value="serviceRequest.requestNo"/></td>
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
