<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>



		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
		<link rel="StyleSheet" href="../css/jquery.treeview.css"
			type="text/css" />
	-->
	<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/jquery.js">
</script>
		<script type="text/javascript" src="../js/jquery.cookie.js">
</script>
		<script type="text/javascript" src="../js/jquery.treeview.js">
</script>
		<script type="text/javascript">
/*
$(document).ready(function() {
	$("#browser").treeview();
});
*/
</script>
     </head>
	<body style="font :12">
	<s:property value="teamString" escape="false"/>
	</body>
</html>
