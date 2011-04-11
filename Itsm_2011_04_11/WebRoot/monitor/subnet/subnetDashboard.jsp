<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	<s:head />
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../css/tab/basic.css" rel="stylesheet" type="text/css">
	<link href="../css/tab/tabs.css" rel="stylesheet" type="text/css">
</head><body>
	<div id="header">
	<ul id="primary">
		<li><a href="detail.action?subnetId=<s:property value="monitorSubnet.id" />">拓扑</a></li>
		<li><a href="devices.action?subnetId=<s:property value="monitorSubnet.id" />">设备一览</a></li>
		<li><a href="dashboard.action?subnetId=<s:property value="monitorSubnet.id" />">数据</a></li>
	</ul>
	</div>
	<div id="main">
		<div id="contents">
			<h2>Welcome to Example Tabs</h2>			
			<p class="note">Use the tabs up above to navigate the site. The secondary navigation in the <a href="http://labs.silverorange.com/images/tabsupdate/about.html">About Us</a> section is functional.</p>
			 
		</div>
	</div>
</body></html>