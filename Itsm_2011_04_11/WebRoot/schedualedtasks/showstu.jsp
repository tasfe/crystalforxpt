<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>任务执行结果</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="${pageContext.request.contextPath }/css/style.css"
			rel="stylesheet" type="text/css">
	</head>

	<body background="${pageContext.request.contextPath }/img/main.jpg"
		leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="${pageContext.request.contextPath }/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="${pageContext.request.contextPath }/images/modpass.gif"
						width="16" height="16">
				</td>
				<td width="98%"
					background="${pageContext.request.contextPath }/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					任务执行结果
				</td>
			</tr>
		</table>

		<div>
			<table width="100%" height="100%" border="0" cellspacing="0"
				cellpadding="0">
				<tr>
					<td valign="top" id="mainright">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							height="100%">
							<tr>
								<td>
									<table width="100%" border="1" cellpadding="1" cellspacing="0"
										height="100%">
										<tr>
											<td style="padding: 10px; line-height: 10px"
												bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" height="100%">
													<tr>
														<td height="99%" colspan="2">
															<table width="100%" height="95%" border="0"
																cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
																<tr>
																	<td width="2%" height="10%" bgcolor="#EBF4F5">
																		<center>
																			任务编号:
																		</center>
																	</td>
																	<td height="10%" bgcolor="#FFFFFF">
																		<s:property
																			value="schedualedTaskUser.schedualedTaskDetail.taskCode" />
																	</td>
																</tr>

																<tr>
																	<td height="10%" bgcolor="#EBF4F5">
																		<center>
																			处理人:
																		</center>
																	</td>
																	<td bgcolor="#FFFFFF">
																		<s:property value="schedualedTaskUser.users.truename" />
																	</td>
																</tr>

																<tr>
																	<td height="60%" bgcolor="#EBF4F5">
																		<center>
																			处理结果:
																		</center>
																	</td>
																	<td valign="top" width="20%"
																		bgcolor="#FFFFFF">
																		<s:property value="schedualedTaskUser.finishResult" />
																	</td>
																</tr>

																<tr>
																	<td height="10%" bgcolor="#EBF4F5">
																		<center>
																			处理时间:
																		</center>
																	</td>
																	<td width="20%" bgcolor="#FFFFFF">
																		<s:date name="schedualedTaskUser.finishTime" format="yyyy-MM-dd HH:mm:ss"/>
																	</td>
																</tr>
															</table>
															<table width="100%" border="0" cellpadding="4"
																cellspacing="0" background="images/addnew002.gif">
																<tr>
																	<td align="center">
																		<input type="button" value="关闭"
																			onclick="window.close()" class="mmBtn">
																	</td>
																</tr>
															</table>
												</table>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
