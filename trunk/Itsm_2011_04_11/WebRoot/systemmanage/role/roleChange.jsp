<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
--%><title>切换用户类型</title>
</head>
<body>
  <s:iterator value="#session.rolesize" >
   <a href='roleChange.action?roleType=<s:property value="value.roleType"/>'>
  
	
	</a>
	</s:iterator>
</body>
</html>