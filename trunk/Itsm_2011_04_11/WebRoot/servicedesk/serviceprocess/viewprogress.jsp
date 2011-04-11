<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>IT Service Desk</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../../css/style.css" rel="stylesheet" type="text/css">
		
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">
		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" height="80%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table border="0" cellspacing="4" cellpadding="0">
							<tr>
								<s:iterator value="serviceTranList" var="serviceTran" status="stuts">
									<td style="padding: 5px">
										<img src="../img/arrowright.jpg" width="64" height="64">
									</td>
									<td>
										<table border="0" cellspacing="1" cellpadding="0" style="border: 1px outset" style="font-size: 12px">
											<tr class="td-right">
												<td align="center" nowrap width="99%" style="padding-left: 2px" height="19">
													<b><s:property value="type" /> </b>
												</td>
											</tr>
											<tr style="padding-left: 4px; padding-right: 5px">
												<td align="center" nowrap bgcolor="#F9F9F9">
													<s:property value="usersByServiceFrom.truename" />
												</td>
											</tr>
											<tr style="padding-left: 4px; padding-right: 5px">
												<td nowrap align="center" bgcolor="#F9F9F9">
													<s:date name="operatorTime" format="yyyy-MM-dd HH:mm:ss" />
												</td>
											</tr>
										</table>
									</td>
									<s:if test="#stuts.modulus(5)==0">
							       </tr>
							       <tr>
								</s:if>
								</s:iterator>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
