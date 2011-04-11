<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>资产配置管理</title>
	</head>
<frameset rows="25,*" framespacing="0" bordercolor="#f8f9f4" frameborder="yes">
  <frame id="titleFrame" name="titleFrame" title="titleFrame" src="title.action" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#ffffff 1px solid"/>
  <frameset cols="180,*" framespacing="0" bordercolor="#f8f9f4" frameborder="yes">
    <frame id="topFrame" name="topFrame" title="topFrame" src="top.action" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#b5d6e6 1px solid"/>
		<frame id="listFrame" name="listFrame" title="listFrame" src="listinfo.action?id=0" scrolling="auto" frameborder="0" style="BORDER:#91A9BD 0px solid"/>
  <frame src="UntitledFrame-3"></frameset>
  <frame src="UntitledFrame-3">
</frameset><noframes></noframes>

</html>