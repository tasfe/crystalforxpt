<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet"
			href="${pageContext.request.contextPath}/css/dtree.css"
			type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/dtree.js">
</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					工作日志
				</td>
			</tr>
		</table>
		<table width="100%" height="100%" border="0" cellpadding="2"
			cellspacing="1" bgcolor="#CCCCCC">
			<tr>


				<td height="100%" colspan="2" valign="top" bgcolor="#FFFFFF"
					style="padding: 0px">
					<div
						style="position: absolute; overflow-x: scroll; overflow-y: scroll; height: 480px; width: 100%; padding-right: 3px; padding-bottom: 3px">
						<s:property value="treeString" escape="false" />
					</div>
				</td>

			</tr>
		</table>
	</body>
</html>