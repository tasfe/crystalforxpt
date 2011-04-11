<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*"%>
<link href="../css/style.css" rel="stylesheet" type="text/css">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
<script type='text/javascript' src='../js/check.js'>
</script>
		
		<script type="text/javascript">

function getUserDAO() { //取出类别
	//UserDAO.findAll(callbackorg)

}
function callbackorg(data) { //显示出类别
	dwr.util.removeAllOptions("Manager");
	dwr.util.addOptions("Manager", {
		'-1' : '--请选择--'
	});
	dwr.util.addOptions("Manager", data, "id", "truename");
	var a = "<s:property value="
user.managerId" />";
   if (typeof(a) != "undefined") {   
   dwr.util.setValue("Manager",a);  
   }  
}

function check()
{
var name2=	document.getElementById("user.username").value;
if(name2=="")
	{
	alert("登录名不允许为空");	
	return false;
	}
var name1=	document.getElementById("user.truename").value;
if(name1=="")
	{
	alert("用户全名不允许为空");	
	return false;
	}

var name3=	document.getElementById("user.password").value;
if(name3=="")
	{
	alert("密码不允许为空");	
	return false;
	}
var name4=	document.getElementById("user.department.id").value;
if(name4=="" || name4=="0")
	{
	alert("所属部门不允许为空");	
	return false;
	}
var name5=	document.getElementById("user.location.id").value;
if(name5=="")
	{
	alert("所属区域不允许为空");	
	return false;
	}
//以下验证手机，邮箱及电话
var telphone=document.getElementById('user.phone').value;
if(telphone){
	if(!checkPhone(telphone)){
		alert('电话格式错误，格式为：0二或三位数字-七或八位数字。');
		return false;
	}
}
var mobilephone=document.getElementById('user.cellphone').value;
if(mobilephone){
	if(!checkMobile(mobilephone)){
		alert('手机格式错误，请重新输入！');
		return false;
	}
}
var email=document.getElementById('user.email').value;
if(email){
	if(!checkEmail(email)){
		alert('邮箱格式错误，请重新输入！');
		return false;
	}
}
}
//重置功能实现
function refresh(){
	var type='${actionURI}';
	if(type=='save'){//新建
		document.getElementById('user.truename').value='';
		document.getElementById('user.password').value='';
		document.getElementById('user.username').value='';
		document.getElementById('user.phone').value='';
		document.getElementById('user.cellphone').value='';
		document.getElementById('user.email').value='';
		document.getElementById('user.department.id').value='';
		document.getElementById('user.location.id').value='';
		document.getElementById('idsList').value='';
		
	}else{//修改
		document.getElementById('user.truename').value='${user.truename}';
		document.getElementById('user.password').value='${user.password}';
		document.getElementById('user.username').value='${user.username}';
		document.getElementById('user.phone').value='${user.phone}';
		document.getElementById('user.cellphone').value='${user.cellphone}';
		document.getElementById('user.email').value='${user.email}';
		document.getElementById('user.department.id').value='${user.department.id}';
		document.getElementById('user.location.id').value='${user.location.id}';
		document.getElementById('idsList').value='${idsList}';
	}
	iframeReload();
}
//IFRAME重新加载
function iframeReload(){
	//window.frames('iframeDepartment').location.reload();
	//window.frames('iframeLocation').location.reload();
	window.frames('iframeRole').location.reload();
}
</script>
	</head>
	<body onLoad="getUserDAO();refresh()" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0" style="padding: 7px;" onMouseDown="document.getElementById('locationDiv').style.visibility='hidden';document.getElementById('department').style.visibility='hidden';">

		<s:form action="%{actionURI}.action" namespace="/user" method="post"
			theme="simple">
			<s:hidden id="user.id" name="user.id"></s:hidden>


			<s:hidden id="user.lastAccessTime" name="user.lastAccessTime"></s:hidden>
			<s:hidden id="user.lastAccessIp" name="user.lastAccessIp"></s:hidden>
			<s:hidden id="user.sex" name="user.sex"></s:hidden>
			<s:hidden id="user.birthday" name="user.birthday"></s:hidden>
			<s:hidden id="user.address" name="user.address"></s:hidden>
			<s:hidden id="user.status" name="user.status"></s:hidden>

			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<tr>
					<td bgcolor="white" valign="top">
						<table width="100%" border="0" cellpadding="2" cellspacing="1"
							bgcolor="#b5d6e6">
							<tr>
								<td width="15%" align="center" bgcolor="#deebf1">
									登陆名<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td width="35%" bgcolor="#FFFFFF">
									<s:textfield id="user.username" name="user.username"
										cssStyle="width: 100%" />
								</td>
								<td width="15%" align="center" bgcolor="#deebf1">
									用户全名<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td width="35%" bgcolor="#FFFFFF">
									<s:textfield id="user.truename" name="user.truename"
										cssStyle="width: 100%"></s:textfield>
								</td>
							</tr>
							<tr>
								
								<td width="15%" align="center" bgcolor="#deebf1"
									title="The password will not be changeed if keep empty..">
									密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td width="35%" nowrap bgcolor="#FFFFFF"
									>
									<input type="password" name="user.password" id="user.password"
										style="width: 100%">
								</td>
								<td width="15%" align="center" bgcolor="#deebf1">
									电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:
								</td>
								<td width="35%" bgcolor="#FFFFFF">
									<s:textfield id="user.phone" name="user.phone"
										cssStyle="width: 100%"></s:textfield>
								</td>

							</tr>
							<tr>
								<td align="center" bgcolor="#deebf1">
									手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:
								</td>
								<td bgcolor="#FFFFFF">
									<s:textfield id="user.cellphone" name="user.cellphone"
										cssStyle="width: 100%"></s:textfield>
								</td>
								<td align="center" bgcolor="#deebf1">
									邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:
								</td>
								<td bgcolor="#FFFFFF">
									<s:textfield id="user.email" name="user.email"
										cssStyle="width: 100%" />
								</td>
							</tr>
							<tr style="display: none">
								<td align="center" bgcolor="#deebf1">
									所在公司:
								</td>
								<td colspan="3" bgcolor="#FFFFFF">
									<input type="text" name="Subcompany" value="|242,"
										style="width: 100%">
								</td>
							</tr>
							<tr>
								<td height="15%" align="center" valign="top" bgcolor="#deebf1">
									部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<!--  
								<td style="padding: 0px; border-bottom: 1px solid buttonface"
									colspan="3">
									<iframe frameborder="0" height="150" width="100%" id='iframeDepartment'
										scrolling="yes" src="../department/top3.action?"
										style="border: 1px inset"></iframe>
								</td>
								-->
								 <td bgcolor="#FFFFFF"  width="35%">
									<input type="hidden" name="user.department.id" id="user.department.id" value="<s:property value="user.department.id"/>">
									<input type="text" name="user.department.name" id="user.department.name" value="<s:property value="user.department.name"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:85%;">
									<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
									<div id="department"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
										<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
									</div>
			         			</td>
			         			<td height="15%" align="center" valign="top" nowrap
									bgcolor="#deebf1">
									所属区域<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td bgcolor="#FFFFFF"  width="35%">
									<input type="hidden" name="user.location.id" id="locationid" value="<s:property value="user.location.id"/>">
									<input type="text" name="user.location.name" id="user.location.name" value="<s:property value="user.location.name"/>" onClick="document.getElementById('locationDiv').style.visibility='visible'" readonly style="width:85%;">
									<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('locationDiv').style.visibility='visible'"><br/>
									<div id="locationDiv"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">	
										<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
									</div>
			         			</td>
							</tr>
							<!--  
                      <tr> 
                        <td align="center" bgcolor="#deebf1">冻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结:</td>
                      	<td bgcolor="#FFFFFF">
					   <input type="checkbox" name="Remark" value="1" style="border: 0px; width: 14px">冻结用户</td>
                        <!--  
                        <td align="center" bgcolor="#deebf1">语言:</td>
                        <td bgcolor="#FFFFFF">
                          <select name="Langua" style="width: 80%">
                            <option value="EN" >英文</option>
                            <option value="CN" selected>简体中文</option>
                        </select></td>
                        -->


							<tr style="display:none">
								<td height="50%" align="center" valign="top" nowrap
									bgcolor="#deebf1">
									所属区域<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td style="padding: 0px; border-bottom: 1px solid buttonface"
									colspan="3">
									<iframe frameborder="0" height="150" width="100%" id="iframeLocation"
										scrolling="yes"
										src="top2.action?userId=<s:property value="user.id"/>"></iframe>
								</td>
							</tr>
							<tr>
								<td height="50%" align="center" valign="top" nowrap
									bgcolor="#deebf1">
									所属角色:
								</td>
								<td style="padding: 0px; border-bottom: 1px solid buttonface"
									colspan="3">
									<iframe frameborder="0" height="150" width="100%" id="iframeRole"
										scrolling="yes"
										src="${pageContext.request.contextPath }/role/forUserSelect.action"></iframe>
								</td>
								<td><span style="display: none;"> <textarea name="idsList" id="idsList"><s:property value="idsList"/></textarea></span></td>
							</tr>
							<!-- 
						<tr>
						  <td align="center" valign="top" bgcolor="#deebf1">员工工号:</td>	  
						  <td colspan="3" bgcolor="#FFFFFF">
						  <input type="text" name="Add1" value="${user.id }" style="width: 100%">						  </td>
						</tr>
						 -->
						</table>
					</td>
				</tr>
				<tr align="center" style="height: 25">
					<td height="30" colspan="2" align="center" nowrap="nowrap"
						bgcolor="#FFFFFF">
						<input name="submit" type="submit" value=" 保存 " class="mmBtn"
							onClick="javascript:return check()">
						&nbsp;&nbsp;
						<!--  
						<input name="button" type="reset" value=" 重置" class="mmBtn" onclick="refresh();">
						-->
						&nbsp;&nbsp;
						<input name="button" type="button" onClick="window.location='list.action'"
							value=" 返回列表 " class="mmBtn">
					</td>
				</tr>

			</table>
		</s:form>
	</body>
</html>