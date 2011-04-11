<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>历史记录查询</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					历史记录查询
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="2"
			cellspacing="1" bgcolor="#b5d6e6">
			<s:form method="post" action="/SystemNotice/querySystemNotice.action"
				theme="simple">
				<tr>
					<td width="2%" align="center" nowrap bgcolor="#deebf1">
						内容描述:&nbsp;
					</td>

					<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<input name="projectContent" type="text" size="40" style="width: 100%"/>
					</td>

					<td width="2%" align="center" nowrap bgcolor="#deebf1">
						项目类别:&nbsp;
					</td>
					<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<input name="projectType" type="text" size="40" style="width: 100%"/>
					</td>

					<td width="2%" align="center" nowrap bgcolor="#deebf1">
						项目号:&nbsp;
					</td>
					<td width="28%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<input name="projectNo" type="text" size="40" style="width: 100%" />
					</td>
					<td width="2%" bgcolor="#deebf1">
						<input type="submit" style="height: 20px" class="mmBtn" value="搜索" />
					</td>
				</tr>
			</s:form>
		</table>
		
<!--<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif">
    	<table width="60%" border=0 cellpadding=0 cellspacing=0>
      	<tr>
        <td width="3%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">项目列表</td>
      	</tr>
    	</table>
    </td>
  	</tr>
</table>-->
	<br>
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					项目列表
				</td>
			</tr>
		</table>	
		
<div style="position: absolute; overflow-x: hidden; overflow-y: hidden; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6"> 
			
			<tr>
       		<th width="1%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">项目号</th> 
        	<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">创建人</th>
       		<th width="10%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">启动时间</th>
        	<th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">项目名称</th>
        	<th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">项目主管</th>
        	<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">查看</th>	
      </tr>
      
      	<s:iterator value="pageBean.list" id="u">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
				<td align="left"><s:property value="#u.id"/></td>
				<td align="center"><s:property value="#u.projectCreater" /></td>
				<td align="center"><s:property value="#u.beginTime.toString().substring(0,12)"/></td>
				<td height="30" align="center"><s:property value="#u.projectName"/></td>
				<td height="30" align="center"><s:property value="#u.projectManager" /></td>
				<td align="center" nowrap><s:a href="detailProject.action?id=%{#u.id}"><img src='../img/viewdetail.gif' border=0 width=18 height=18></s:a></td>
				</s:iterator>
												
		</table>
		
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="">
 	<tr>
  	<td colspan="6" height="30" align="right">
	<table width="90" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
		<tr style="cursor:hand;">
      	<td><img src="../images/addnew001.gif"></td>
      	<td onClick="javascript:window.location.href='<%=request.getContextPath()%>/ProjectManage/newProject.action'">后退</td>
      	<td align="right"><img src="../images/addnew003.gif"></td>
		</tr>
	</table>
	</td>
   </tr>
</table>

<s:form name="form" method="post" action="/ProjectManage/queryProject.action" theme="simple" >
<s:hidden id="page" name="page"></s:hidden>		
</s:form>
<jsp:include page="/common/page.jsp"/>
		</div>
	</body>
</html>
