<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<style type="text/css">
a img {
	border-width: 0px;
}

input:focus {
	border: 2px #C4A059 groove;
}
</style>
	</head>
	<body style="margin: 0px; padding: 0px;" oncontextmenu="return false">
		<form action="login.do" method="POST">
			<table id="loginTab" border="0" align="center" cellpadding="0"
				cellspacing="0" vspace="0" hspace="0"
				style="font-size: 12px; padding: 0px; margin: 0 auto; width: 270px; height: auto;">
				<tr height="40" style="padding-top: 0px;">
					<td
						style="text-align: left; font-size: 14px; font-weight: bold; width: 70px;">
						&nbsp;登录系统
					</td>
					<td>
						<span id="msg"
							style="color: #f00; text-align: center; font-size: 12px; text-align: left;">
						</span>
					</td>
				</tr>
				<tr height="30">
					<td align="right" width="70" nowrap>
						用户名：
					</td>
					<td nowrap width="200">
						<input type="text" name="username" id="username" value="" size="30"
							style="width: 120px"">
					</td>
				</tr>
				<tr height="30">
					<td align="right" nowrap>
						密码：
					</td>
					<td nowrap>
						<input type="password" name="password" id="password" value=""
							size="30" style="width: 120px"">
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td colspan="">
						<span style="padding: 0px 0px 0px 100px;"> <a
							id="forgetPassword" href="javascript:" style="color: #3366FF"
							onClick="retakePassword();">忘记密码</a> </span>
					</td>
				</tr>
				<tr height="30">
					<td>
						&nbsp;
					</td>
					<td nowrap>
						<input type="checkbox" name="autoLogin" size="30">
						自动登录
					</td>
				</tr>
				<tr height="30">
					<td nowrap valign="top">
						&nbsp;
					</td>
					<td width="100%" align="center">
						<div align="left">
							<input type="Submit" name="login" class="Button" value=" 登录 ">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>