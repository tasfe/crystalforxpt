<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产信息查看</title>
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
<frameset rows="25,*" framespacing="0" bordercolor="#f8f9f4" frameborder="yes">
  <frame id="titleFrame" name="titleFrame" title="titleFrame" src="title.action" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#ffffff 1px solid"/>
  <frameset cols="180,*" framespacing="0" bordercolor="#f8f9f4" frameborder="yes">
    <frame id="topFrame" name="topFrame" title="topFrame" src="top.action" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#ffffff 1px solid" />
    <frame id="listFrame" name="listFrame" title="listFrame" src="qurey.action" scrolling="auto" frameborder="0" style="BORDER:#91A9BD 0px solid"/>
  <frame src="UntitledFrame-3"></frameset>
  <frame src="UntitledFrame-3">
</frameset><noframes></noframes>
</html>