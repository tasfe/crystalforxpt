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
	document.getElementById("ITer").src="/itsm/role/query2.action?role.id="+roleteam;
}

function getUser(id) {
		document.getElementById("userinfo").src="${pageContext.request.contextPath}/itsm/user/query2.action?userId="+id;
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
<body onLoad="getDAO()"  background="../img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
<iframe width=0 height=0 name="sum" id="sum"></iframe>
<iframe width=0 height=0 name="sla" id="sla"></iframe>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td valign="top" id="mainright">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
				
		<script language="JavaScript">
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
			  <table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list">
				<tr> 
					<th style="padding: 0px">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;事件请求详情</th>
				</tr>
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
										<img src="${pageContext.request.contextPath }/img/incident.gif" align="absmiddle">
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
										  <td width="14%" height="29" valign="top">&nbsp;时间:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top">&nbsp;操作人:</td>
										  <td width="86%" valign="top" style="padding-left: 6px"></td>
										</tr>
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
						<tr id="tr_table" style="display: none">
						<td> 
						  <table width="100%" border="1" cellspacing="0" cellpadding="0" height="260" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr> 
							  <td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								  <table width="100%" border="0" cellspacing="2" cellpadding="4" bgcolor="white">
									<tr> 
									  <td class="subtitle" width="14%" nowrap>转交人</td>
									  <td class="subtitle" width="50%" nowrap>转交原因</td>
									  <td class="subtitle" width="16%">转交至</td>
									  <td class="subtitle" width="12%" nowrap>时间</td>
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
										  <td width="14%" height="29" valign="top">&nbsp;开始时间:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
										  <td width="14%" height="29" valign="top">&nbsp;完成时间:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top">&nbsp;等待时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
										  <td width="14%" height="29" valign="top">&nbsp;处理时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top">&nbsp;是否超时响应:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
										  <td width="14%" height="29" valign="top">&nbsp;是否超时完成:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"> </td>
									   </tr>
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" height="29" valign="top">&nbsp;总耗时长:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
										  <td width="14%" height="29" valign="top">&nbsp;事故原因:</td>
										  <td width="36%" valign="top" style="padding-left: 6px"></td>
									   </tr>
									   
									   <tr bgcolor="#F9F9F9"> 
										  <td width="14%" valign="top" height="99%">&nbsp;处理解决方案:</td>
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
									
									<td bgcolor="#F1F2F7"><input type="button" value="查看详细解决方案" class=mmBtn_sm name="button" onClick="window.open('../desk/?NowAction=solution&ID=R10082400001','','width=560,height=440,scrollbars=no,menubar=no,resizable=yes,top=80,left=176,status=yes')" disabled></td>
									
									<td bgcolor="#F1F2F7"><input type="button" value="查看任务通知" class=mmBtn_sm name="button" onClick="window.open('../chan/?NowAction=pnotice&Type=R10082400001','','width=600,height=450,scrollbars=no,menubar=no,resizable=no,top=80,left=126,status=yes')"></td>
									<td bgcolor="#F1F2F7"><input type="button" value="工作日志" class=mmBtn_sm name="button" onClick="window.open('../sla/?NowAction=logbook&ID=R10082400001','','width=550,height=500,scrollbars=no,menubar=no,resizable=yes,top=100,left=126,status=yes')"></td>
									
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
						  </table></td>
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
						  </table></td>
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
									<td valign="top" bgcolor="#FFFFFF">
												<iframe width="100%" height="100%" src="viewprogress.action?requestNo=<s:property value='serviceRequest.requestNo'></s:property>"
													scrolling="yes" frameborder="0" style="border: 0px"></iframe>
											</td>
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
									<td width="1%" bgcolor="#F1F2F7"><input type="button" value="当前用户" class=mmBtn_sm name="button" onClick="window.configtb.location='../asst/?NowAction=assetschose&user=|Administrator|'"></td>
								</tr></table></td>
							 </tr>
						  </table></td>
						 
						 <td width="52%" style="padding-left: 10px">
						  <table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list" height="100%">
							<tr> 
							  <th style="padding: 0px" class="InTB">&nbsp;<img src="../img/cate.gif" align="absMiddle">&nbsp;相关解决方案</th>
							</tr>
							<tr>
					         	<td valign="top" bgcolor="#FFFFFF">
								<iframe width="100%" height="100%" src=""scrolling="yes" frameborder="0" style="border: 0px"></iframe>
								</td>
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
					<td height="10" colspan="2">
						<img height="0" width="760">
					</td>
				</tr>

			</table>
		</td>
	</tr>
</table>