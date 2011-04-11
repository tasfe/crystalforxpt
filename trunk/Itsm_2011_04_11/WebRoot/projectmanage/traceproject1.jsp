<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>

		<title>跟踪项目处理进度</title>
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/SeverityTypDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		
		<script type="text/javascript">
	
</script>

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
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					跟踪项目处理进度
				</td>
			</tr>
		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="" method="" theme="simple">
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										工单号:&nbsp;
									</td>
									<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="projectContent" type="text" size="40"
											style="width: 100%" />
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										申请人:&nbsp;
									</td>
									<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="projectContent" type="text" size="40"
											style="width: 100%" />
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										摘要或描述:&nbsp;
									</td>
									<td width="28%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="projectContent" type="text" size="40"
											style="width: 100%" />
									</td>
								</tr>
								<tr>
									<td width="1%" nowrap bgcolor="#deebf1">
										项目类别:&nbsp;
									</td>
									<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="projectContent" type="text" size="40"
											style="width: 100%" />
									</td>
									<td width="1%" nowrap bgcolor="#deebf1">
										项目负责人:&nbsp;
									</td>
									<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input name="projectContent" type="text" size="40"
											style="width: 100%" />
									</td>
									
									<td width="1%" nowrap bgcolor="#deebf1">
										<%--<img src="${pageContext.request.contextPath}/img/jiedian.gif"
											width="10" height="9" />--%>
										&nbsp;创建时间:
									</td>
									<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input type="text" id="beginTime" name="beginTime"
											onclick="WdatePicker()"
											style="width: 85%; background-color: #FFFFCC" />
										<img onclick="WdatePicker({el:$dp.$('id1')})"
											src="../js/DatePicker/skin/datePicker.gif" width="20"
											height="22" />
									</td>


									<td width="2%" align="right" bgcolor="#deebf1">
										<input type="submit" style="height: 20px" class="mmBtn"
											value="搜索" />
									</td>
								</tr>
							</s:form>
						</table>
					
				</tr>
			</table>
			
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td height="10" style="font-size:12px; color:#333333; font-weight:bold;">&nbsp;</td>
			</tr>
			
			<tr>
			<td height="20" style="font-size:12px; color:#333333; font-weight:bold;">
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
			<td width="60%" height="30" style="padding-top: 10px" onClick="document.getElementById('thelayer').style.display='none'"><img src="../images/main20100521dot04.gif">&nbsp;<b>关闭所有未关闭的项目:</b>&nbsp;</td>
			<td width="39%" align="right"><font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td nowrap><img src="../img/help2.jpg" width="22" height="23" align="absmiddle">&nbsp;</td>
			<td align="right" colspan="2" width="1%" height="28" valign="top" style="padding-top: 4px">
			<table border="0" cellspacing="0" cellpadding="0" width="100px">
			<tr>
			<td width="1"><img width="3"></td>
			<td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px;font-size: 8pt; font-family: Tahoma; cursor: default" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()" nowrap>
			<input type="text" name="thetypes" value="根据状态查看" style="width: 150px;border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default;height: 15px" readonly />
            </td>
            <td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px;PADDING-TOP: 1px">
			<input type="button" value="6" style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif);font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px;width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}" name="click">
          	</td>
			</tr>
			<tr id="thelayer" onClick="this.style.display='none'" style="display: none">
			<td width="1"></td>
			<td height="2" colspan="2">
			<div style="position: absolute; width: 100%; height: 115px; z-index: 9; cursor: default; overflow-x: hidden; border: 1px outset;">
			<table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
			
			<tr>
			<td bgcolor="white" width="1%"><img width="18" height="18" src="../img/ServiceInput.gif" align="absmiddle"></td>
			<td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='query3.action?state=6'">&nbsp;所有未关闭项目</td>
			</tr>
			
			<tr>
			<td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
			<td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='query2.action?state=0'">&nbsp;修订中的项目</td>
			</tr>
			
			<tr>
			<td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='query2.action?state=5'">&nbsp;等待启动的项目</td>
            </tr>
				
			<tr>
			<td bgcolor="white" width="1%"><img width="18" height="18" src="../img/page.gif" align="absmiddle" border="0"></td>
            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='query2.action?state=0'">&nbsp;正在进行中的项目</td>
			</tr>
			
			<tr>
			<td bgcolor="white" width="1%"><img width="18" height="18" src="../img/ico_t1.gif" align="absmiddle" border="0"></td>
            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='query2.action?state=7'">&nbsp;已完成的项目</td>
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
			
			
			
			<tr>
         	<td valign="top" background="../img/Separator.gif" style="line-height:5px;"><img src="../img/Separator.gif" width="6" height="6"></td>
       		</tr>
       		
			</table>
			
			
			<table width="100%" border="0" width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
			<tr>
			    <th width="2%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">项目号</th>
          		<th width="20%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">项目名称</th>          
          		<th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">创建人</th>
          		<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">创建时间 </th>
          		<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">计划完成时间</th>
         		<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">责任人</th>          
         		<th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">剩余时间</th>
          		<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">进度</th>
          		<th width="15%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">摘要</th>
          		<th width="1%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">查看 </th>
			</tr>
			
			<s:iterator value="pageBean.list" id="u" >
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
				<td height="30"  align="center"><s:property value="#u.id"/></td>
				<td align="left"><s:property value="#u.projectName"/></td>
				<td align="center"><s:property value="#usersByRequestUser.truename"/></td>
				<td align="center"><s:property value="#u.beginTime.toString().substring(0,10)"/></td>
				<td align="center"><s:property value="#u.endTime.toString().substring(0,10)"/></td>
				<td align="center">N/A</td>
				<td align="center">N/A</td>
				<td align="center">N/A</td>
				<td align="center">N/A</td>
				<td align="center" nowrap><a href="../engineertrace/show.action?serviceRequest.id=<s:property value="id" />"><img src='../img/viewdetail.gif' border=0 width=18 height=18></a></td>    	
				</tr>
			</s:iterator>
			
			</table>
			
			<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
			<td nowrap style="padding-top: 6px; padding-bottom: 1px"></td>
          	<td align="right" nowrap style="padding-top: 6px; padding-bottom: 1px">
			<input name="button2" type="button" class="mmBtn" onClick="javascript:window.location.href='<%=request.getContextPath()%>/ProjectManage/newProject.action'" value="后退" >
			</td>
			
			
			
			</tr>
			</table>
			<s:form name="form" method="post" action="/ProjectManage/traceProject.action" theme="simple" >
			<s:hidden id="page" name="page"></s:hidden>		
			</s:form>
			<jsp:include page="/common/page.jsp"/>
			
			
		</div>
	</body>
</html>
