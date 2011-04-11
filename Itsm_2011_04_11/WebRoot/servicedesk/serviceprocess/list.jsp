<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>IT经理服务进度管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${pageContext.request.contextPath }/css/style.css"
			rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript'
			src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script>
			function init(){ //取出类别
			ServiceCategoryDAO.findAll(callbackserviceCategory);
		}
			function callbackserviceCategory(data){  //显示出分类
 			dwr.util.removeAllOptions("service_category");
   			dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("service_category",data,"id","itemZh");  
    		var a = "<s:property value="serviceRequest.serviceCategory.itemZh" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("service_category",a);  
   			} 
		}	
		function tbshowandoff(a,b,c) {//显示和隐藏表格；
			document.getElementById(a).style.display= "block"; 
          	document.getElementById(b).style.display= "none"; 
          	document.getElementById(c).style.display= "none";
  		}
		function tbshowandoff(a,b) {//显示和隐藏表格；
			document.getElementById(a).style.display= "block"; 
          	document.getElementById(b).style.display= "none"; 

  		}
			function dateJudge(){   
			var startDate=document.getElementById("startDate").value;
			var endDate=document.getElementById("endDate").value;   
			if(startDate<=endDate){
				return true;
			}else{
				alert("请输入正确的日期！");
				return false;
			}
		}  	
		
		</script>

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
	</head>
	<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					IT经理服务进度管理
				</td>
			</tr>
		</table>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/wpCalendar.js"></script>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="99" height="2"></td>
				</tr>
				<tr
					onClick="document.getElementById('thelayer').style.display='none'">
					<td height="12">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="query" method='post' theme="simple" name="form">
							 <s:hidden id="page" name="page"></s:hidden>
                             <s:hidden id="pageSize" name="pageSize"></s:hidden>
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										工单号:&nbsp;
									</td>
									<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<s:textfield name="serviceRequest.requestNo"
											id="serviceRequest.requestNo" style="width: 100%" />
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										申请人:&nbsp;
									</td>
									<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<s:textfield name="serviceRequest.usersByRequestUser.truename"
											id="serviceRequest.usersByRequestUser.truename"
											style="width: 100%" />
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										摘要或描述:&nbsp;
									</td>
									<td width="28%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<s:textfield name="serviceRequest.summary"
											id="serviceRequest.summary" style="width: 100%; cursor: text" />
									</td>
									<!--  
									<td width="1%" nowrap bgcolor="#deebf1">
										自定义条件:&nbsp;
									</td>
									<td width="21%" bgcolor="#FFFFFF">
										<select name="RequPeop" id="RequPeop" style="width: 100%">
											<option value=""></option>
											<option value="">
												紧急度
											</option>
											<option value="">
												优先级
											</option>
											<option value="">
												地域
											</option>
											<option value="">
												错误原因
											</option>
											<option value="">
												打印
											</option>
										</select>
									</td>
									-->
								</tr>
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										服务类别:&nbsp;
									</td>
									<td bgcolor="#FFFFFF" style="padding-right: 10px">
										<select id="service_category"
											name="serviceRequest.serviceCategory.id"
											cssStyle="width: 100%">
										</select>
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										工程师:&nbsp;
									</td>
									<td bgcolor="#FFFFFF" style="padding-right: 10px">
										<s:textfield name="serviceRequest.usersByEngineerId.username"
											id="serviceRequest.usersByEngineerId.username"
											style="width: 100%" />
									</td>
									<td width="2%" nowrap bgcolor="#deebf1">
										申请时间:&nbsp;
									</td>
									<td nowrap bgcolor="#FFFFFF" style="padding-right: 10px">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="49%" nowrap>
													<s:textfield id="startDate" name="serviceRequest.startDate"
														onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:40%"></s:textfield>
													<img
														onClick="WdatePicker({el:$dp.$('serviceRequest.startDate'),dateFmt:'yyyy-MM-dd HH:mm:ss'})"
														src="../js/DatePicker/skin/datePicker.gif" />
													&nbsp;
													<b>~</b>&nbsp;
													<s:textfield id="endDate" name="serviceRequest.endDate"
														onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:40%"></s:textfield>
													<img
														onClick="WdatePicker({el:$dp.$('serviceRequest.endDate'),dateFmt:'yyyy-MM-dd HH:mm:ss'})"
														src="../js/DatePicker/skin/datePicker.gif" />
												</td>
											</tr>
										</table>
									</td>
									<td width="2%" bgcolor="#deebf1">
										<input type="submit" style="height: 20px" class="mmBtn"
											value="搜索" />
									</td>
								</tr>
							</s:form>
						</table>
					</td>
				</tr>

				<tr>
					<td height="12">
						&nbsp;
					</td>
				</tr>

				<tr>
					<td height="12">
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="1" bgcolor="#b5d6e6">
							<tr>
								<td width="86%" height="3" valign="middle"
									background="../images/main20100521_58.gif"
									onClick="document.getElementById('thelayer').style.display='none'">
									<table width="50%" border=0 cellpadding=0 cellspacing=0>
										<tr>
											<td width="2%" align="center"
												background="../images/main20100521_58.gif"
												style="color: #FFFFFF; font-weight: bold; padding-right: 5px; padding-left: 5px;">
												<img src="../images/modpass.gif" width="16" height="16">
											</td>
											<td width="98%" style="color: #333333; font-weight: bold;">
												所有未关闭的服务请求: 
											</td>
										</tr>
									</table>
								</td>
								<td width="14%" height="22" colspan="2" align="right"
									valign="top" background="../images/main20100521_58.gif">
									<table border="0" cellspacing="0" cellpadding="0" width="100px">
										<tr>
											<td width="1"></td>
											<td width="99%"
												style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"
												onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()"
												nowrap>
												<input type="text" name="thetypes" value="根据状态查看"
													style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px"
													readonly>
											</td>
											<td width="1%"
												style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px;">
												<input type="button" value="6"
													style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; height: 17px; line-height: 9px; width: 21px"
													onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}"
													name="click">
											</td>
										</tr>
										<tr id="thelayer" onClick="this.style.display='none'"
											style="display: none">
											<td width="1"></td>
											<td height="2" colspan="2">
												<div
													style="position: absolute; width: 100%; height: 115px; z-index: 9; cursor: default; overflow-x: hidden; border: 1px outset">
													<table width="100%" border="0" cellpadding="1"
														cellspacing="0" bgcolor="white">
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="../img/ServiceInput.gif" align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action">
																&nbsp;所有未关闭请求
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/task.gif"
																	align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=0'">
																&nbsp;新创建
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/task.gif"
																	align="absmiddle">
															</td>
															<td nowrap
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=0'">
																&nbsp;部门领导审批完成
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/page.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=0'">
																&nbsp;等待派单
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/ico_t1.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=0'">
																&nbsp;暂时挂起中
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18"
																	src="../img/ServiceInput.gif" align="absmiddle"
																	border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=1'">
																&nbsp;等待工程师受理
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/wait.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=2'">
																&nbsp;工程师正在处理中
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/delete.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=4'">
																&nbsp;已经被拒绝
															</td>
														</tr>
														<tr>
															<td bgcolor="white" width="1%">
																<img width="18" height="18" src="../img/pageok.gif"
																	align="absmiddle" border="0">
															</td>
															<td
																onMouseOver="this.bgColor='#648AC4';this.style.color='white'"
																onMouseOut="this.bgColor='white';this.style.color='#333333'"
																bgcolor="white"
																onClick="location.href='/itsm/serviceprocess/query2.action?serviceRequest.state=5'">
																&nbsp;等待用户反馈
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
					</td>
				</tr>

				<tr
					onClick="document.getElementById('thelayer').style.display='none'">
					<td valign="top">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<tr>
								<td width="17%" height="22" align="center" nowrap
									bgcolor="#deebf1">
									工单号
								</td>
								<td width="6%" align="center" nowrap bgcolor="#deebf1">
									服务类别
								</td>
								<td width="5%" align="center" nowrap bgcolor="#deebf1">
									申请人
								</td>
								<td width="12%" align="center" nowrap bgcolor="#deebf1">
									申请时间
								</td>
								<td width="15%" align="center" nowrap bgcolor="#deebf1">
									摘要
								</td>
								<td width="12%" align="center" nowrap bgcolor="#deebf1">
									承诺完成时间
								</td>
								<td width="8%" align="center" nowrap bgcolor="#deebf1">
									工程师
								</td>
								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									处理进度
								</td>
								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									查看
								</td>
								<td width="1%" align="center" nowrap bgcolor="#deebf1"
									style="text-align: center">
									干预
								</td>
							</tr>
							<s:iterator value="pageBean.list" var="serviceRequest">
								<tr onMouseOver="this.bgColor='#e3f0f7'"
									onMouseOut="this.bgColor='#FFFFFF'">
									<td height="30" align="center" valign="middle"
										bgcolor="#FFFFFF">
										<s:property value="requestNo" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="serviceCategory.itemZh" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="usersByRequestUser.truename" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="submintTime" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="summary" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="promiseTime" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:property value="usersByEngineerId.username" />
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<s:if test="#serviceRequest.state==0">待派单</s:if>
										<s:elseif test="#serviceRequest.state==1">待受理</s:elseif>
										<s:elseif test="#serviceRequest.state==2">处理中</s:elseif>
										<s:elseif test="#serviceRequest.state==3">流程进行中</s:elseif>
										<s:elseif test="#serviceRequest.state==4">已拒绝</s:elseif>
										<s:elseif test="#serviceRequest.state==5">已完成，等待用户反馈</s:elseif>
										<s:elseif test="#serviceRequest.state==6">已关闭</s:elseif>
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<a
											href="show.action?serviceRequest.id=<s:property value="id" />">
											<img src='../img/zjz.gif' border=0 width=18 height=18>
										</a>
									</td>
									<td align="center" valign="middle" bgcolor="#FFFFFF">
										<a
											href="interposeShow.action?serviceRequest.id=<s:property value="id" />">
											<img src='../img/zjz.gif' border=0 width=18 height=18>
										</a>
									</td>
								</tr>
							</s:iterator>
						</table>
						<table cellspacing="0" cellpadding="0" border="0" width="100%">
							<tr>
								<td height="30" align="center" nowrap>
								<!-- 
									<input type="button" class="mmBtn" value="查看报表" onClick="">
									<input type="button" class="mmBtn" value="自定义" onClick="">
									
									<input type="button" class="mmBtn" value="后退"
										onClick="history.go(-1)">
										
										 -->
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<jsp:include page="/common/page.jsp"/> 
		</div>
	</body>
</html>

