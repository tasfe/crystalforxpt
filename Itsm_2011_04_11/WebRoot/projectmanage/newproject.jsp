<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>create Project</title>
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript'
			src='../dwr/interface/SeverityTypDAO.js'></script>
		<script type='text/javascript'
			src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>

		<style type="text/css">
.onOver {
	Border: 1px solid white;
}

A:hover {
	color: #000000;
	text-decoration: none;
	background-color: #EEF8ED;
	Border-Bottom: 1px solid #333333;
	Border-Right: 1px solid #333333;
	Border-Top: 1px solid #C6CFD8;
	Border-Left: 1px solid #C6CFD8;
}
</style>
		<script type="text/javascript">
	function init() {
		UserDAO.findAll(callbackusers);
		ServiceCategoryDAO.findByIntType(1, callbackcategory);
		SeverityTypDAO.findByIntType(2, callbackemergency);
		LocationDAO.findAll(callbackorg);

	}

	function callbackusers(data) { //显示主管类别
		dwr.util.removeAllOptions("users");
		dwr.util.addOptions("users", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("users", data, "id", "truename");
	}
	function callbackcategory(data) { //显示出项目类别
		dwr.util.removeAllOptions("category");
		dwr.util.addOptions("category", {
			'-1' : '--请选择--'
		});
		dwr.util.addOptions("category", data, "id", "itemZh");
	}

	function callbackemergency(data) { //显示出紧急度
		dwr.util.removeAllOptions("emergency");
		dwr.util.addOptions("emergency", {
			'-1' : '--请选择--'
		});
		dwr.util.addOptions("emergency", data, "severityValue", "severityType");
	}

	function callbackorg(data) { //显示出类别
		dwr.util.removeAllOptions("Location");
		dwr.util.addOptions("Location", {
			'-1' : '--请选择--'
		});
		dwr.util.addOptions("Location", data, "id", "name");
	}
	function Table(id) {
		var oldid = document.getElementById("tmp").value;
		if (id != oldid) {
			document.getElementById("left_" + oldid).src = "${pageContext.request.contextPath }/img/tab_un_left.gif";
			document.getElementById("right_" + oldid).src = "${pageContext.request.contextPath }/img/tab_un_right.gif";
			document.getElementById(oldid).background = "${pageContext.request.contextPath }/img/tab_un.gif";
			document.getElementById(oldid).style.paddingTop = "5px";
			document.getElementById(oldid + "_table").style.display = "none";
			document.getElementById("left_" + id).src = "${pageContext.request.contextPath }/img/tab_ch_left.gif";
			document.getElementById("right_" + id).src = "${pageContext.request.contextPath }/img/tab_ch_right.gif";
			document.getElementById(id).background = "${pageContext.request.contextPath }/img/tab_ch.gif";
			document.getElementById(id).style.paddingTop = "3px";
			document.getElementById(id + "_table").style.display = "";
			document.getElementById("tmp").value = id;
		}
	}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: auto;" onload="init();">
		<s:form action="createProject" name="myForm" id="myForm" method="post" theme="simple" enctype="multipart/form-data" namespace="/ProjectManage">
			<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id"
				value="-1"></s:hidden>
			<input id="tmp" value="at" type="hidden">

			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center"
						background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold;">
						创建新的项目
					</td>
				</tr>
			</table>

			<div
				style="position: absolute; overflow-x: scroll; overflow-y: scroll; width: 100%; padding-top: 5px;">
				<table width="99%" height="100%" border="0" align="center"
					cellpadding="4" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
					<tr>
						<td height="99%" bgcolor="#FFFFFF" valign="top">
							<table width="100%" border="0" cellspacing="1" cellpadding="2">
								<tr>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;项目名称:
									</td>
									<td bgcolor="#EBF4F5" colspan="5">
										<input name="projectName" id="projectName"
											style="width: 100%; background-color: #FFFFCC" size="100" />
									</td>
								</tr>
								<tr>
									<td width="14%" height="31" nowrap bgcolor="#deebf1"
										style="padding-right: 30px">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;起始时间:
									</td>
									<td width="19%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<input type="text" id="beginTime" name="beginTime"
											onclick="WdatePicker()"
											style="width: 85%; background-color: #FFFFCC" />
										<img onclick="WdatePicker({el:$dp.$('id1')})"
											src="../js/DatePicker/skin/datePicker.gif" width="20"
											height="22" />
									</td>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;项目创建人:
									</td>

									<td width="19%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<s:textfield id="projectCreater" name="projectCreater"
											style="width: 100%; background-color: #FFFFCC" size="50" />
									</td>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;所属地区:
									</td>

									<td width="19%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<select id="Location" name="projectLocation"
											style="width: 100%; background-color: #FFFFCC">
											<option value="-1">
												--请选择--
											</option>
										</select>

									</td>
								</tr>
								<tr>
									<td width="14%" height="31" nowrap bgcolor="#deebf1"
										style="padding-right: 30px">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;结束时间:
									</td>
									<td width="19%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<input type="text" id="id1" name="endTime"
											onclick="WdatePicker()"
											style="width: 85%; background-color: #FFFFCC" />
										<img onclick="WdatePicker({el:$dp.$('id1')})"
											src="../js/DatePicker/skin/datePicker.gif" width="20"
											height="22" />
									</td>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;项目类别:
									</td>
									<td width="19%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<select id="category" name="projectType"
											style="width: 100%; background-color: #FFFFCC"></select>
									</td>
									<td width="14%" height="31" nowrap bgcolor="#deebf1"
										style="padding-right: 30px">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;项目主管:
									</td>
									<td width="19%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<select id="users" name="projectManager"
											style="width: 100%; background-color: #FFFFCC">
											<option value="-1">
												--请选择--
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td width="14%" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />
										&nbsp;项目内容描述:
									</td>
									<td width="85%" colspan="6" bgcolor="#EBF4F5">
										<textarea id="projectContent" name="projectContent" rows="8"
											cols="86"
											style="width: 100%; height: 120; background-color: #FFFFCC"></textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
					<td bgcolor="#FFFFFF" height="12" colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb-list">
						<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Gray">
							<tr>	
							<td style="padding-right: 1px; cursor: hand" onClick="Table('at')">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
									<td><img src="../img/tab_un_left.gif" id="left_at"></td>
									<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="at">附加文档</td>
									<td><img src="../img/tab_un_right.gif" id="right_at"></td>
									</tr>
								</table>
							</td>
							<td style="padding-right: 1px; cursor: hand" onClick="Table('au')">
								<table border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td><img src="../img/tab_un_left.gif" id="left_au"></td>
								<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="au">项目所包含任务</td>
								<td><img src="../img/tab_un_right.gif" id="right_au"></td>
								</tr>
								</table>
							</td>
							<td style="padding-right: 1px; cursor: hand" onClick="Table('ar')">
								<table border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td><img src="../img/tab_un_left.gif" id="left_ar"></td>
								<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ar">涉及配置</td>
								<td><img src="../img/tab_un_right.gif" id="right_ar"></td>
								</tr>
								</table>
							</td>
							
							<td style="padding-right: 1px; cursor: hand" onClick="Table('af')">
								<table border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td><img src="../img/tab_un_left.gif" id="left_af"></td>
								<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="af">其他相关联工单</td>
								<td><img src="../img/tab_un_right.gif" id="right_af"></td>
								</tr>
								</table>
							</td>
							
							<td width="99%" align="right">
								<table border="0" cellspacing="0" cellpadding="0" width="95%">
								<tr>
								<td width="40%" align="center"> <font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td nowrap align="right"></td>
								<td width="1%" onclick="document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand"></td>
								</tr>
								</table>
							</td>
							</tr>
							</table>
						</td>
						</tr>
						
						<tr id="at_table" style="display: ">
						<td>
							<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr>				
							<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">	
								<tr>
								<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">上传附件</td>
								</tr>											
								</table>									
							</td>
							</tr>
							
							<tr>
							<td valign="top" height="160">
							<div id="_file"></div>
							<br/>
							</td>
							</tr>
							
							<tr>
							<td height="20" bgcolor="#FFFFFF">
							<table width="100%" border="0" cellspacing="1" cellpadding="4">
								<tr>
								<td width="1%" bgcolor="#EBF4F5" nowrap>&nbsp;选择文件:</td>
								<td width="99%" bgcolor="#EBF4F5" style="padding-top: 1px; padding-bottom: 2px">								
									<div id="input">
										<input name="file" id="file" type="file" value="添加附件" onChange="addFile()" />
										<font size="1.8" color="red">如果添加多个附件，请继续点击“浏览”</font>
									</div>
								</td>
								
								<td width="1%" bgcolor="#F1F2F7"></td>
								</tr>
							</table>
							</td>
							</tr>
							</table>
						</td>
						</tr>
						<tr id="au_table" style="display:none">
						<td>
							<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
							<tr>
							<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
								<tr>
								<td nowrap class="subtitle" bgcolor="#b5d6e6">查看用户信息</td>	
								</tr>
								</table>
							</td>
							</tr>
							
							<tr>
							<td valign="top" nowrap>
							<iframe frameborder="0" height="179" width="100%" scrolling="yes" style="border: 1px inset" name="userinfo" id="userinfo"></iframe>
							</td>
							</tr>
							
							
							<tr>
							<td valign="top" style="overflow-y: scroll" height="12"></td>
							</tr>
							
							</table>
						</td>
						</tr>
						
						
						<tr id="ar_table" style="display: none">
						<td>
							<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;"><%--
							<tr>
							<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
								<tr>
								<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">任务指派与服务协议</td>
								</tr>
								</table>
							</td>
							</tr>
							
							--%>
							
							<%--<tr>
							<td valign="top" height="191">
								<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
									<table width="100%" border="0" cellspacing="2" cellpadding="4">
										<tr bgcolor="#EBF4F5" id="astd_0">
										<td width="14%" valign="top">系统自动分配:</td>
										<td colspan="3">
										<input type=text name=ITers style="width: 100%; border: 0px; background-color: #EBF4F5; Color: #3A4E69; padding-top: 2px" readonly>
										</td>
										</tr>
										
										
										<tr bgcolor="#EBF4F5" style="display: none" id="astd_1">
										<td height="12">工作组:</td>
										<td colspan="3" style="padding: 0px; padding-left: 7px">
											<select style="width: 120px" id="location" onChange="getInfo1();">
        									<option value="-1">--请选择--</option>
    										</select>
    										<select style="width: 180px" name="role.id" id="engineerteam"  onChange="getInfo2();">
       										<option value="-1">--请选择--</option>
    										</select>
										</td>
										</tr>
										
										<tr bgcolor="#EBF4F5" style="display: none" id="astd_2">
											<td valign="top">选择工程师:</td>
											<td colspan="3" style="padding-left: 7px">	
											<iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
											</td>
										</tr>
										
										
										<tr bgcolor="#EBF4F5">
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
										
										<tr bgcolor="#EBF4F5">
											<td width="14%" valign="top">响应时间:</td>
											<td>
											<input type="text" name=ReTime style="width: 100%; background-color:#EBF4F5; border: 0px;padding-top: 2px" readonly>
											</td>
											<td width="14%" valign="top">承诺完成时间:</td>
											<td>
											<input type="text" name=FiTime style="width: 100%;  background-color:#EBF4F5;  border: 0px; padding-top: 2px" readonly>
											</td>
										</tr>
										
										<tr bgcolor="#EBF4F5">
											<td width="14%" valign="top">自动转交时间:</td>
											<td colspan="3">
											<input type="text" name=UpTime style="width: 100%;  background-color:#EBF4F5;  border: 0px; padding-top: 2px" readonly>
											</td>
										</tr>
															
										<tr bgcolor="#EBF4F5">
											<td width="14%" valign="top">概要及说明:</td>
											<td id="About" colspan="3" style="padding-left: 7px; line-height: 22px">&nbsp;</td>
										</tr>
									</table>
								</div>
								
							</td>
							</tr>
							--%>
							<tr>
							<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
								<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
								<tr>
								<td width="15%" height="12" class="subtitle" bgcolor="#b5d6e6">代码</td>
								<td width="45%" class="subtitle" bgcolor="#b5d6e6">名称</td>
								<td width="30%" class="subtitle" bgcolor="#b5d6e6">类别</td>
								<td width="10%" class="subtitle" bgcolor="#b5d6e6">查看</td>
								</tr>
								</table>
							</td>
							</tr>
							
							<tr>
							<td valign="top">
							<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
							</div>
							</td>
							</tr>
							
							</table>
							
						</td>
						</tr>
						
							<tr id="af_table" style="display: none">
								<td>
									<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
										<tr>
											<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
												<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
													<tr>
														<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">快速解决</td>
													</tr>
								
												</table>
											</td>
										</tr>
										
										<tr>
											<td valign="top" height="191">
												<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
												<table width="100%" border="0" cellspacing="2" cellpadding="4">
													<tr bgcolor="#EBF4F5">
													<td width="14%" height="12">选项:</td>
													<td style="padding: 1px">
														<table border="0" cellspacing="0" cellpadding="0">
														<tr>
														<td style="padding-top: 2px">
														<input type="checkbox" name="serviceRequest.state" value="5" class="noborder">
														</td>
														<td nowrap style="padding-top: 1px; line-height: 13px">&nbsp;快速解决这个服务请求，并且将之关闭。</td>
														</tr>
														</table>
													</td>
												    </tr>
												    
												    <tr bgcolor="#EBF4F5">
													<td width="14%" valign="top">解决方案:</td>
													<td><s:textarea name="serviceRequest.solution" cssStyle="width: 100%; height: 80px"></s:textarea></td>
													</tr>
													
													
													<tr bgcolor="#EBF4F5">
													<td width="14%">事件产生原因:</td>
													<td valign="top">
													<select name="serviceRequest.errorCause" id="ErroCause" style="width: 20%">
														<option value="">--请选择--</option>
														<option value="用户操作">用户操作</option>
														<option value="系统设置">系统设置</option>
														<option value="设备稳定性">设备稳定性</option>
													</select>
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
					
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="12" style="padding-top: 8px" nowrap>
						</td>
						<td align="center" style="padding-top: 8px; padding-bottom: 0px">
							<input type="submit" value="提交项目" class=mmBtn name="submit">&nbsp;
							
							<input type="reset" value="全部重设" class=mmBtn name="reset" />
						</td>
					</tr>
				</table>
		</s:form>
	</body>
</html>