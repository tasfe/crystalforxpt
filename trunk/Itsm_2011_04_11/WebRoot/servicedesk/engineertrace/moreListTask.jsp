<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
	<link href="../css/style.css" rel="stylesheet" type="text/css">
  </head>
  	<script type="text/javascript">
	</script>
  <body leftmargin="2" topmargin="0" marginwidth="0" marginheight="0"
		style="padding-right: 1px; padding-bottom: 2px; padding-top: 2px">
		
	<table align="center" cellspacing=0 cellpadding=0 border=0
			width="100%" class="datagrid" >
			<tr id="tab1">
				<th width="15%" nowrap class="mmBtn"
					style="text-align: center">
					任务单号
				</th>
				<th width="10%" nowrap class="mmBtn"
					style="text-align: center">
					任务类别
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					重要程度
				</th>
				<th width="12%" nowrap class="mmBtn"
					style="text-align: center">
					摘要
				</th>
				<th width="12%" nowrap class="mmBtn"
					style="text-align: center">
					执行时间
				</th>
				<th width="15%" nowrap class="mmBtn"
					style="text-align: center">
					剩余时间
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					负责人
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					查看
				</th>
			</tr>
			
			<tr id="tab11" >
				<td colspan="8" class="menu">
					<img src="../img/index.jpg" align="absMiddle">
					<b>近期需要您执行的计划任务列表:</b>
				</td>
			</tr>
				<s:iterator value="listTask" var="task">
						<tr >
							<td height="29">
								<table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td width="1%">
											<img src="../img/incident.gif"
												style="background-color: ">
										</td>
										<td width="87%" nowrap>
											&nbsp;&nbsp;
											<s:property value="taskCode" />
											&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td align="center">
								<s:property value="schedualedTasks.serviceCategory.itemZh" />
							</td>
							<td align="center">
								<s:property value="schedualedTasks.serverity" />
							</td>
							<td align="center">
								<s:date name="schedualedTasks.keyWord" />
							</td>
							<td align="center">
								<s:date name="schedualedTime" format="yyyy-MM-dd" />
							</td>
							 <td align="center">
							 
							</td>
							<td align="center">
								<s:property value="schedualedTasks.user.truename" />
							</td>
							<td align=center nowrap>
								<a
									href="${pageContext.request.contextPath }/schedualedtask/show.action?schedualedTaskDetail.id=<s:property value="id" />"><img
												src='../img/viewdetail.gif' border=0 width=18 height=18>
								</a>
							</td>
						</tr>
		</s:iterator>
	</table>
  </body>
</html>
