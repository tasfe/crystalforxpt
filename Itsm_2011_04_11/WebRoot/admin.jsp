<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
		<base target="_self">
<title>开发人员特殊使用页面（内部特供）</title>
<link href="theme/css.css" rel="stylesheet" type="text/css" />
<%
String path = request.getContextPath();
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires", 0);
%>
<script>
function login(){
window.parent.location="<%=path%>/index.jsp";
}
</script>
</head>
<body topmargin="0" leftmargin="0">
<form action="adminsave.action">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
<s:iterator value="record" var="configRecord">
<tr>
<td><input type="radio" id="id<s:property value="id"/>" name="ids" value="<s:property value="id"/>"></td>
<td><s:property value="recordName"/></td>
</tr>
</s:iterator>
<tr>
<td><input type="submit" value="保存"></td><td><input type="button" value="登陆页面" onclick="login();">
</td>
</tr>
<script>
document.getElementById("id<s:property value='configre.id'/>").checked="true";
</script>
</table>
</form>
</body>

</html>