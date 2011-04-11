<%@ page language="java" pageEncoding="UTF-8"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<%
String path = request.getContextPath();
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires", 0);
%>

<script>alert('您没有登陆或者登陆超时，请重新登陆!');window.parent.location="<%=path%>/index.jsp";</script>");
<script>window.parent.location="<%=path%>/index.jsp";</script>");