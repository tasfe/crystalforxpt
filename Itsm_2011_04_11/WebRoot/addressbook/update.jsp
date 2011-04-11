<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>new</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
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
		DepartmentDAO.findAll(callbackdepartment);
	}

	function callbackdepartment(data) {
		dwr.util.removeAllOptions("department");
		dwr.util.addOptions("department", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("department", data, "id", "name");

	}
</script>
	</head>

	<body onLoad="init()" style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					修改通讯录
				</td>
			</tr>
		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: hidden; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">

			<s:form action="update" method="post" theme="simple"
				namespace="/AddressBookManage">

				<s:hidden name="id" value="%{id}"></s:hidden>

				<table width="99%" border="0" align="center" cellpadding="2"
					cellspacing="1" bgcolor="#b5d6e6">
					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							姓名：
						</td>
						<td colspan="3" bgcolor="#FFFFFF">
							<s:textfield name="username" value="%{addressBook.username}" style="width:150px;" />
						</td>
					</tr>
					<tr>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							手机：
						</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="phone" value="%{addressBook.phone}" style="width:150px;" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							办公电话：
						</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="officePhone"
								value="%{addressBook.officePhone}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							家庭电话：</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="homephone" value="%{addressBook.homephone}" style="width:150px;" />
						</td>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							传真：
						</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="fax" value="%{addressBook.fax}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							邮箱：
						</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="email" value="%{addressBook.email}" style="width:150px;" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							主页：</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="homepage" value="%{addressBook.homepage}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							MSN：
						</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="msn" value="%{addressBook.msn}" style="width:150px;" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							QQ：</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="qq" value="%{addressBook.qq}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							地址：</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="address" value="%{addressBook.address}" style="width:150px;" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							邮编：</td>
						<td bgcolor="#FFFFFF">
							<s:textfield name="zipCode" value="%{addressBook.zipCode}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							备注：</td>
						<td colspan="3" bgcolor="#FFFFFF">
							<s:textfield name="remarks" value="%{addressBook.remarks}" style="width:150px;" />
						</td>
					</tr>

					<tr>
						<td colspan="4" align="center" bgcolor="#FFFFFF">
							<input type="submit" value="保存" class=mmBtn name="submit" />
							&nbsp;
							<input type="reset" value="重置" class=mmBtn name="reset" />
							&nbsp;
							<input type="button" value="返回" class=mmBtn name="button" onclick="window.history.go(-1)" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
