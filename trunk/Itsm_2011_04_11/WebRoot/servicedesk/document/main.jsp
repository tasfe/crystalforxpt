﻿<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>文档库目录管理</title>
	</head>
	<frameset  cols="18%,82%" framespacing="5" bordercolor="#f8f9f4" frameborder="yes">
		<frame id="topFrame" name="topFrame" title="文档目录" src="top.action" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#91A9BD 1px solid"/>
		<frame id="listFrame" name="listFrame" title="文档目录" src="list.action?pid=${pid}&page=${page}" scrolling="auto" frameborder="0" style="BORDER:#91A9BD 0px solid"/>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>