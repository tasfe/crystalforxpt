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
		<script type='text/javascript'
			src='../dwr/interface/customerTypeDAO.js'></script>
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
		customerTypeDAO.findAll(callbackcustomertype);
		//checkForm();
	}

	function callbackcustomertype(data) {
		dwr.util.removeAllOptions("customertype");
		dwr.util.addOptions("customertype", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("customertype", data, "id", "name");
	}
	
	function checkForm(){
	    var customertype = document.getElementById("customertype").value;
	  	var username = document.getElementById("username").value;
	  	var companyName = document.getElementById("companyName").value;
	  	var phone = document.getElementById("phone").value;
	  	var email = document.getElementById("email").value;
	  	var duties = document.getElementById("duties").value;
	  	var fax = document.getElementById("fax").value;
	  	var website = document.getElementById("website").value;
	  	var officePhone =document.getElementById("officePhone").value;
	  	
	  	if(customertype==-1){
			alert("关系不能为空！！");
			return false;	  	
	  	}
	  	if(!username){
	  		alert("姓名不能为空！");
	  		return false;
	  	}
	  	if(!companyName){
	  		alert("公司名称不能为空！");
			return false;	  	
	  	}
	  	if(!(phone.length==11)){
	  		alert("手机号码必须为11位！！");
	  		return false;
	  	}
	  	if(!duties){
	  		alert("职务不能为空！");
	  		return false;
	  	}
	  	var CheckMail=/^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?)$/;
		if (!CheckMail.test(email)){
			alert("Email格式不正确！");
			return false;
		}
		if(!fax){
			alert("传真号码不能为空！");
			return false;
		}
		
		if(!officePhone){
			alert("办公电话不能为空！");
			return false;
		}
		if(!website){
			alert("公司网址不能为空！");
			return false;
		}
	  	return true;
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
					我的通讯录
				</td>
			</tr>
		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: hidden; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">

			<s:form action="addPersonalContacts" method="post" theme="simple"
				namespace="/AddressBookManage">
				<table width="99%" border="0" align="center" cellpadding="2"
					cellspacing="1" bgcolor="#b5d6e6">
					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							关系：
						</td>
						<td colspan="3" bgcolor="#FFFFFF">
							<select id="customertype" name="customertypeId"
								style="width: 20%"></select>
						</td>
					</tr>
					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							姓名：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="username" type="text" id="username"
								style="width: 50%;" size="22" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							公司名称：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="companyName" type="text" id="companyName"
								style="width: 50%;" size="100" />
						</td>
					</tr>


					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							手机：
						</td>
						<%--
						<td bgcolor="#FFFFFF">
							<select id="department1" name="departmentId" style="width: 35%"></select>
						</td>
						<>
						--%>
						<td bgcolor="#FFFFFF">
							<input name="phone" type="text" id="phone" style="width: 50%;"
								size="100" />
						</td>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							职务：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="duties" type="text" id="duties" style="width: 50%;"
								size="100" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							办公电话：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="officePhone" type="text" id="officePhone"
								style="width: 50%;" size="100" />
						</td>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							传真：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="fax" type="text" id="fax" style="width: 50%;"
								size="100" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							邮箱：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="email" type="text" id="email" style="width: 50%;"
								size="100" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							主页：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="homepage" type="text" id="homepage"
								style="width: 50%;" size="100" />
						</td>
					</tr>

					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							公司网址：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="website" type="text" id="website"
								style="width: 50%;" size="100" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							个人网址：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="personalWebsite" type="text" id="personalWebsite"
								style="width: 50%;" size="100" />
						</td>
					</tr>


					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							地址：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="address" type="text" id="address"
								style="width: 50%;" size="100" />
						</td>

						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							邮编：
						</td>
						<td bgcolor="#FFFFFF">
							<input name="zipCode" type="text" id="zipCode"
								style="width: 50%;" size="100" />
						</td>
					</tr>
					<tr>
						<td width="9%" bgcolor="#deebf1">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" />
							备注：
						</td>
						<td colspan="3" bgcolor="#FFFFFF">
							<input name="remarks" type="text" id="remarks"
								style="width: 50%;" size="100" />
						</td>
					</tr>

					<tr>
						<td colspan="4" align="center" bgcolor="#FFFFFF">
							<input type="submit" value="提交" class=mmBtn name="submit" onClick="javascript:return checkForm();"/>
							&nbsp;
							<input type="reset" value="重填" class=mmBtn name="reset" />
							&nbsp;
							<input type="button" value="后退" class="mmBtn" name="button"
								onClick="javascript:window.location.href='../AddressBookManage/listPersonalContact.action'" />
						</td>
					</tr>

				</table>
			</s:form>
		</div>
	</body>
</html>
