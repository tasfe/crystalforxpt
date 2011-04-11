<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>部门管理</title>
	</head>
	<frameset rows="180,*" cols="*" framespacing="5" bordercolor="#f8f9f4" frameborder="yes">
		<frame id="topFrame" name="topFrame" title="topFrame" src="top.action?id=<s:property value="id"/>" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#91A9BD 1px solid"/>
		<frame id="listFrame" name="listFrame" title="listFrame" src="list.action?pid=<s:property value="pid"/>" scrolling="auto" frameborder="0" style="BORDER:#91A9BD 0px solid"/>
	<frame src="UntitledFrame-2"></frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>