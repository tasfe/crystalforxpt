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
			src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" href="../css/style.css" type="text/css" />
		<style type="text/css">
.list {
	border-left: 1px solid white;
	border-right: 1px solid #A09B8D;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

.list_btm {
	border-top: 2px outset white;
	background-color: #D4D0C7;
	text-align: right;
	padding: 3px;
	padding-top: 4px;
}

.list_par {
	border-top: 1px solid white;
	border-bottom: 2px ridge #BDB9B0;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

td {
	font-size: 11px;
	padding: 2px;
	font-family: Tahoma;
}

.mmBtn {
	padding: 0px;
	background-color: #D4D0C7;
	mborder: 1px outset white;
	font-family: Tahoma;
	font-size: 11px;
	line-height: 15px;
}

.divtitle {
	position: absolute;
	z-index: 1;
	overflow: hidden;
	width: 100%;
	height: 22px;
	padding: 0px;
}
</style>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="border: 1px solid #dddddd;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<s:property value="title"/>
				</td>
			</tr>
		</table>
	</body>
</html>