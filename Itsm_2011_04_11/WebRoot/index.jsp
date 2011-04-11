<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>中国人民大学网络中心管理信息化平台--登录</TITLE>
		<META content="text/html; charset=utf-8" http-equiv=Content-Type>
		<SCRIPT type="text/javascript">
	function fullscr() {
		var a = screen.width + 8;
		var b = screen.height - 22;
		window.moveTo(-4, -4);
		window.resizeTo(a, b);
		document.all('uidtxt').focus();
	}
	function Auto_keydown() {
		if (event.keyCode == 13) {
			if (event.srcElement.name == "yzmtxt") {
				login_confirm();
			} else {
				event.keyCode = 9;
			}
		}
	}
	function login_confirm() {
		if (document.getElementById("uidtxt").value.replace(/^\s*/, "") == "") {
			document.getElementById("tsmsg").innerHTML = "用户名不能为空！";
			document.getElementById("uidtxt").focus();
			return false;
		}
		if (document.getElementById("pwdtxt").value.replace(/^\s*/, "") == "") {
			document.getElementById("tsmsg").innerHTML = "密码不能为空！";
			document.getElementById("pwdtxt").focus();
			return false;
		}

		document.getElementById("tsmsg").innerHTM = "";
		document.Form1.submit();
	}
</SCRIPT>

		<STYLE>
body {
	background-color: #e7e7e7;
	margin-left: 0px;
	margin-top: 100px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</STYLE>
	</HEAD>
	<BODY style="MARGIN: 0px" onLoad="fullscr();">
		<table width="787" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="50">
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td><img src="images/itsmlogin_03.jpg" width="387" height="138" alt="" /></td>
				<td><img src="images/itsmlogin_04.jpg" width="286" height="138" alt="" /></td>
				<td><img src="images/itsmlogin_05.jpg" width="114" height="138" alt="" /></td>
			</tr>
		</table>
		<table width="787" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<s:form action="login" namespace="/" method="post" theme="simple" id="Form1" name="Form1">
				<tr>
					<td width="467"><img src="images/itsmlogin_07.jpg" width="467" height="142" alt="" /></td>
					<td width="168" valign="top" background="images/itsmlogin_08.jpg" style="padding-top: 23px;">
						<table width="168" border="0" cellspacing="0" cellpadding="0">
							<tr><td height="32" style="font-size: 12px; color: #bfeaff">用户名：</td>
								<td colspan="2">
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 100px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=uidtxt tabIndex=1 maxLength=16
										size=18 type=text name=username>
								</td>
							</tr>
							<tr>
								<td height="32" style="font-size: 12px; color: #bfeaff"><font color="#bfeaff">密&nbsp;&nbsp;&nbsp;&nbsp;码：</font></td>
								<td colspan="2">
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 100px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=pwdtxt tabIndex=2 maxLength=16
										size=18 type=password name=password>
								</td>
							</tr><%--
							<tr>
								<td height="32" style="font-size: 12px; color: #bfeaff"><font color="#bfeaff">验证码：</font></td>
								<td>
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 45px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=yzmtxt tabIndex=3 maxLength=4
										size=8 type=text name=yzmtxt>
								</td>
								<td><img style="VERTICAL-ALIGN: bottom; CURSOR: pointer"id="imgSrc" src="images/yzm.jpg" width="50" height="19" /></td>
							</tr>
							--%><tr>
								<td height="20" colspan="3" align="right"
									style="font-size: 12px; color: #bfeaff">
									<DIV style="COLOR: red;" id=tsmsg></DIV>
								</td>
							</tr>
						</table>
					</td>
					<td width="79"><img id="div1" src="images/itsmlogin_09.jpg" width="79"height="142" alt="" style="cursor: hand;"onClick="login_confirm();" /></td>
					<td width="73"><img src="images/itsmlogin_10.jpg" width="73" height="142" alt="" /></td>
				</tr>
			</s:form>
		</table>
		<table width="168" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<img src="images/itsmlogin_11.jpg" width="787" height="191" alt="" />
				</td>
			</tr>
		</table>
	</BODY>
</HTML>
