<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>个人信息配置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
		<style type="text/css">
<!--
body {
	margin-top: 0px;
	margin-left: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<script language="javascript" type="text/javascript">
/*
 * 验证手机号，13,15,18.....
 */
function checkMobile( s ){ 
	var regu =/^0{0,1}(13[0-9]?|15[0-9]|18[0-9])[0-9]{8}$/; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	}else{ 
		return false; 
	} 
}
/*
 * 验证电话，三位或四位区号 - 七位或八位数字
 */
function checkPhone( strPhone ) { 
	var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	var re=new RegExp(pattern);
	if(re.test(strPhone)){
		return true;
	}else{
		return false;
	}

}
/*
 * 验证邮箱
 */
function checkEmail(strEmail) { 
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; 
	var re=new RegExp(emailReg);
	if( re.test(strEmail)){ 
		return true; 
	}
	else{ 
		return false; 
	} 
}
function validate() {
	var phone=document.getElementById("user.phone").value;
	if(phone){
		if(!checkPhone(phone)){
			alert("请输入正确的电话号码!");
			document.getElementById("user.phone").focus();
			return false;
		}
	}
	var cellphone=document.getElementById("user.cellphone").value;
	if(cellphone){
		if(!checkMobile(cellphone)){
			alert("请输入正确的手机号码!");
			document.getElementById("user.cellphone").focus();
			return false;
		}
	}
	var email=document.getElementById("user.email").value;
	if(email){
		if(!checkEmail(email)){
			alert("请输入正确的电子邮箱地址!");
			document.getElementById("user.email").focus()
			return false;
		}
	}
	var oldPwd=document.getElementById("oldPwd").value;
	var newp = document.getElementById("newPwd").value;
	var newpx = document.getElementById("newPwdx").value;
	if(oldPwd|| newp || newpx){
		if(!oldPwd){
			alert("请输入旧密码!");
			document.getElementById("oldPwd").focus()
			return false;
		}
		if(!newp){
			alert("请输入新密码!");
			document.getElementById("newPwd").focus()
			return false;
		}
		if(!newpx){
			alert("请再次输入新密码!");
			document.getElementById("newPwdx").focus()
			return false;
		}
	}
	if(newp && newpx){
		if(newpx!=newp)
	    { 
	        alert("两次密码输入不匹配!"); 
	        document.getElementById("newPwd").focus(); 
	        return false; 
	    }
	}
    document.getElementById("theFrom").sumbit;
}  

</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: hidden;">
		<form name="theForm" action="changePwd.action" method="post"
				theme="simple">
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16" alt="">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					用户个人资料配置:
				</td>
			</tr>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px; padding-top: 5px;">

			<table width="98%" border="0" align="center" cellpadding="2"
				cellspacing="1" bgcolor="#b5d6e6" style="padding-top: 5px;">

				<tr>
					<td height="22" colspan="4" nowrap
						background="../images/main20100521_58.gif">
						<table width="50%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="2%" align="center"
									background="../images/main20100521_58.gif"
									style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
									<img src="../images/modpass.gif" width="16" height="16" alt="">
								</td>
								<td width="98%" style="color: #333333; font-weight: bold;">
									您的基本资料：
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;登&nbsp;&nbsp;录&nbsp;&nbsp;名:
					</td>
					<td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.username" />
					</td>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;显示全名:
					</td>
					<td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.truename" />
					</td>
				</tr>
				<tr valign="">
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;所在部门:
					</td>
					<td valign="top" nowrap bgcolor="#FFFFFF"
						style="padding-left: 0px; padding-top: 5px">
						<div class='DivOut'>
							<s:property value="user.department.name" />
						</div>
					</td>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;所属区域:
					</td>
					<td valign="top" nowrap bgcolor="#FFFFFF"
						style="padding-left: 0px; padding-top: 5px">
						<div class='DivOut'>
							<s:property value="user.location.name" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:
					</td>
					<!--  
					<td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.phone" />
					</td>
					-->
					<td nowrap bgcolor="#FFFFFF" width="34%">
							<input id="user.phone" type="text" name="phone" 
								value="${phone}" style="width: 63%;height:75%" >

					</td>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:
					</td>
					<!-- <td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.cellphone" />
					</td> -->
					<td nowrap bgcolor="#FFFFFF" width="34%">
							<input id="user.cellphone" type="text" name="cellphone" 
								value="${cellphone}" style="width: 63%;height:75%" >

					</td>
				</tr>
				<tr>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;电子邮箱地址:
					</td>
					<!-- <td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.email" />
					</td> -->
					<td nowrap bgcolor="#FFFFFF" width="34%">
							<input id="user.email" type="text" name="email" 
								value="${email}" style="width: 63%;height:75%" >

					</td>
					<td width="16%" height="31" nowrap bgcolor="#EBF4F5"
						style="padding-top: 6px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;用户组别:
					</td>
					<td width="34%" nowrap bgcolor="#FFFFFF">
						<s:property value="user.usertype" />
					</td>
				</tr>
				<!--  
				<tr>
					<td width="16%" height="31" valign="top" nowrap bgcolor="#EBF4F5"
						style="padding-top: 8px">
						<img src="${pageContext.request.contextPath}/img/jiedian.gif"
							width="10" height="9" alt="">
						&nbsp;员工工号:
					</td>
					<td colspan="3" nowrap bgcolor="#FFFFFF">
						<s:property value="user.id" />
					</td>
				</tr>
				-->
			</table>
			
				<font color="red"> <s:fielderror /> <s:actionmessage /> </font>
			
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="1" bgcolor="#b5d6e6">
					<tr>
						<td colspan="2" nowrap background="../images/main20100521_58.gif"
							nowrapheight="31">
							<table width="50%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="2%" align="center"
										background="../images/main20100521_58.gif"
										style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
										<img src="../images/modpass.gif" width="16" height="16" alt="">
									</td>
									<td width="98%" style="color: #333333; font-weight: bold;">
										密码设置：
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td nowrap bgcolor="#EBF4F5" nowrapheight="31">
							<img src="../img/jiedian.gif" alt="" width="10" height="9">
							&nbsp;输入旧密码:
						</td>
						<td nowrap bgcolor="#FFFFFF">
							<input id="oldPwd" type="password" name="oldPwd"
								value="${oldPwd}" style="width: 35%" maxlength="16">

						</td>
					</tr>
					<tr>
						<td width="16%" nowrap bgcolor="#EBF4F5" nowrapheight="31"
							id="oldPwd">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" alt="">
							&nbsp;输入新密码:
						</td>
						<td width="84%" nowrap bgcolor="#FFFFFF">
							<input id="newPwd" type="Password" name="newPwd"
								value="${newPwd}" style="width: 35%" maxlength="16">
						</td>
					</tr>
					<tr>
						<td width="16%" height="31" rowspan="2" valign="middle"
							bgcolor="#EBF4F5" style="padding-top: 6px">
							<img src="${pageContext.request.contextPath}/img/jiedian.gif"
								width="10" height="9" alt="">
							&nbsp;再次输入新密码:
						</td>
						<td width="84%" nowrap bgcolor="#FFFFFF">
							<input id="newPwdx" type="Password" name="newPwdx"
								value="${newPwdx}" style="width: 35%" maxlength="16">
						</td>
					</tr>
					<tr>
						<td nowrap bgcolor="#FFFFFF">
							请确保两次输入的密码完全一致，并请不要超过十六个字符，不修改密码请置空。
						</td>
					</tr>
				</table>
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="1" bgcolor="#b5d6e6">
					<tr>
						<%--	<td colspan="4" valign="middle"
							background="../images/main20100521_58.gif" bgcolor="#deebf1">
							<table width="50%" border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td width="2%" align="center"
										background="../images/main20100521_58.gif"
										style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
										<img src="../images/modpass.gif" width="16" height="16">
									</td>
									<td width="98%" style="color: #333333; font-weight: bold;">
										当有新的提示时，您需要哪种报警方式？
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="16%" height="31" align="right" valign="middle"
							bgcolor="#EBF4F5" style="padding-top: 5px">
							<input name="checkbox" type="checkbox" disabled class="noborder"
								value="" checked>
						</td>
						<td width="34%" nowrap bgcolor="#FFFFFF">
							给我发送邮件:&nbsp;发送通知邮件到指定邮箱
						</td>
						<td width="16%" height="31" align="right" valign="middle"
							bgcolor="#EBF4F5" style="padding-top: 5px">
							<input type="checkbox" name="Alert2" value="Tigtag"
								class="noborder">
						</td>
						<td width="34%" nowrap bgcolor="#FFFFFF">
							播放声音警告:&nbsp;收到提示时将播放提示音乐
						</td>
					</tr>
					<tr>
						<td height="31" align="right" valign="middle" bgcolor="#EBF4F5"
							style="padding-top: 5px">
							<input type="checkbox" name="Alert2" value="Flash"
								class="noborder">
						</td>
						<td nowrap bgcolor="#FFFFFF">
							闪屏警告:&nbsp;浏览器窗口会持续振动三秒
						</td>
						<td height="31" align="right" valign="middle" bgcolor="#EBF4F5"
							style="padding-top: 5px">
							<input type="checkbox" name="Alert2" value="Alert"
								class="noborder">
						</td>
						<td nowrap bgcolor="#FFFFFF">
							弹出对话框:&nbsp;弹出对话框并显示提示信息
						</td>--%>
					</tr>
				</table>
			
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input name="button" type="submit" class="mmBtn"
							onClick="javascript:return validate()" value="保存修改">
					</td>
				</tr>

			</table>
			</form>
	</body>
</html>

