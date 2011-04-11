<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>成功添加日志</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
		
	</head>

	<body background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
		<div>
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" id="mainright">
						<script type="text/javascript">
							alert("您的日志已成功添加！"); 
							window.close();
						</script>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
