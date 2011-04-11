<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产新增提示页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	<script>
	function patch(id){
	window.location.href="add.action?pid="+id;
	}
	function nopatch(){
	window.parent.location.href="list.action";
	}
	
	setTimeout("window.parent.location.href='list.action'",20000);
	
	</script>	
	</head>
	<body>
	<table align="center" border="0">
     <tr>
      <td width="100%" align="center" height="30" colspan="2"  bgcolor="#FFFFFF" style="color:#F00; font-weight:bold; font-size:15px;">
                是否继续添加</td>
     </tr>
     <tr>
      <td align="right">
       <input type="button" value="是" onclick="patch('<s:property value="pid"/>');">
      </td>
      <td align="left">
       <input type="button" value="否" onclick="nopatch();">
      </td>
     </tr>
     <tr>
      <td colspan="2">
	    20秒后自动跳转资产列表页！
      </td>
     </tr>
    </table>
	</body>
</html>