<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>分区管理</title>
	</head>
	<frameset cols="18%,82%" framespacing="5" bordercolor="#f8f9f4"
		frameborder="yes">
		<frame id="topFrame" name="topFrame" title="通讯录树目录"
			src="<%=path%>/monitorSubnet/subnetList.action" noresize="noresize" scrolling="auto" 
			frameborder="0" style="BORDER: #91A9BD 1px solid" />
		<frame id="detailFrame" name="detailFrame" title="通讯录树目录"
			src="" scrolling="auto" frameborder="0"
			style="BORDER: #91A9BD 0px solid" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
