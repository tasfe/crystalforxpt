<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
 	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>康邦管理信息化系统</title>
</head>
<frameset rows="98,*,41" cols="*" frameborder="yes" framespacing="0" bordercolor="#f8f9f4">
  <frame src="top_engineer.jsp" name="topFrame" scrolling="no" noresize frameborder="0">
  <frameset cols="194,*" framespacing="no" frameborder="no" bordercolor="#f8f9f4">
   <frame src="menu.action?roleType=<s:property value="roleType"/>" name="leftFrame" scrolling="auto" noresize id="leftFrame" title="leftFrame" frameborder="0" style="BORDER:#91A9BD 0px solid;">
   <frame src="serviceprocess/itmanager.action" name="mainFrame" id="mainFrame" title="mainFrame" style="BORDER:#91A9BD 0px solid"/>
  </frameset>
  <frame src="bottom.jsp" name="bottomFrame" id="bottomFrame" scrolling="no" noresize frameborder="0" style="BORDER-TOP:#91A9BD 0px solid"/>
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>