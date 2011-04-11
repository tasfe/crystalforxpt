<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>用户资料</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
<script type='text/javascript' src='../dwr/util.js'>
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
		<script type="text/javascript">
function init() { //取出类别
	var ag = '<s:property value="message" escape="false" />';
	if (ag) {
		alert("提示：" + ag);
	}
	DepartmentDAO.findAll(callbackdepartment);
	LocationDAO.findAll(callbackorg);

}




</script>
	</head>
	<body style="overflow: hidden;" onLoad="init()" onmousedown="document.getElementById('Layer2').style.visibility='hidden'" >
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					用户查询:
				</td>
			</tr>

		</table>
		<table width="99%" border="0" align="center" cellpadding="2"
			cellspacing="1" bgcolor="#deebf1">
			<s:form action="list"  method='post'
				theme="simple">
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">
						姓名:&nbsp;
					</td>
					<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="user.truename"
							id="user.truename" cssStyle="width:120px" />
					</td>
					
					<td bgcolor="#FFFFFF" style="padding-right: 10px">
					
					<input id="department.name" type="text" name="user.department.name" style="width: 79%; background-color: #FFFFFF; cursor: text" readonly value="<s:property value="user.department.name"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="department.id" name="user.department.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	
																</div>
					
					
					
					
					</td>
	
				</tr>
				<tr>
					<td colspan="5" nowrap bgcolor="#deebf1">&nbsp;
						
					</td>
					<td width="21%" align="center" nowrap bgcolor="#deebf1"
						style="padding-right: 10px">
						<tags:button code="search" menu="85">
						<input type="submit" style="height: 20px" class="mmBtn"
							value="搜 索" />
							</tags:button>
					</td>
				</tr>
			</s:form>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30" align="right"></tr>
			</table>
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="1" bgcolor="#b5d6e6">
				<tr bgcolor="#FFFFFF">
				    <td height="20" align="center"
						background="../images/main20100521_58.gif" class="alllisttitle">
						员工工号
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						登陆名
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						用户全名
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						用户类型
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						地理位置
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">&nbsp;</td>
				</tr>
				<s:iterator value="pageBean.list" var="users">
					<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
						onMouseOut="this.bgColor='#FFFFFF'">
						<td height="26" align="center">
							<s:property value="id" />
						</td>
						<td height="26" align="center">
							<s:property value="username" />
						</td>
						<td align="center">
							<s:property value="truename" />
						</td>
						<td align="center">
							<s:property value="usertype" />
						</td>
						<td align="center">
							<s:property value="location.name" />
						</td>
						<td align="center">
                         <input type="radio" name="code" id="code" value=""/>
                         </td>
					</tr>
				</s:iterator>
			</table>
			<s:form name="form" method="post" action="/user/list.action"
				theme="simple">
	      <s:hidden id="page" name="page"></s:hidden>
				<s:hidden id="pageSize" name="pageSize"></s:hidden>
	    </s:form>

			<jsp:include page="/common/page.jsp" />
		</div>
	</body>
</html>
