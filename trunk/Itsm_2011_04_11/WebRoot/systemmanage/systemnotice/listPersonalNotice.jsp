<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>个人通知列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">

	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
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
	<body style="overflow:hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">个人通知查看</td>
			</tr>
		</table>
		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
				<tr>
					<th width="10%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发信人</th>
					<th width="60%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">主题</th>
					<th width="14%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">日期</th>
					<th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">查看</th>
					<th width="8%" nowrap background="../images/main20100521_58.gif"class="alllisttitle">删除</th>
				</tr>

				<s:iterator value="pageBean.list" id="u">
					<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						<td height="30" align="center" class="zczb_qua"><s:property value="#u.from" /></td>
						<td align="center" class="zczb_qua"><s:property value="#u.title" /></td>
						<td align="center" class="zczb_qua"><s:property value="#u.date.toString().substring(0,19)" /></td>
						<td align="center" nowrap>
							<s:if test="#u.read==0">
								<s:a href="detailPersonalNotice.action?id=%{#u.id}"><img src='../images/icons/icon037a1.gif' border=0 width=18 height=18>查看</s:a>
							</s:if>
							<s:else>								
							<s:a href="detailPersonalNotice.action?id=%{#u.id}"><img src='../images/icons/icon037a19.gif' border=0 width=18 height=18>查看</s:a>
							</s:else>
						</td>
						<td align="center">
							<img src="../images/del.gif"><s:a href="deletePersonalNotice.action?id=%{#u.id}" onclick="javascript:return del()">删除</s:a>
						</td>
                         </s:iterator>
				    <tr>
				      <td height="30" colspan="5" align="right" bgcolor="#FFFFFF" class="zczb_qua"><table width="90" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
				        <tr style="cursor: hand;">
				          <td><img src="../images/addnew001.gif"></td>
				          <td onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/listSystemNotice.action'"> 后退</td>
				          <td align="right"><img src="../images/addnew003.gif"></td>
			            </tr>
			          </table></td>
		      </table>
			<s:form name="form" method="post" action="/PersonalNotice/listPersonalNotice.action" theme="simple">
			  <s:hidden id="page" name="page"></s:hidden>
		  </s:form>
			<jsp:include page="/common/page.jsp" />
			
	</div>
	</body>
</html>