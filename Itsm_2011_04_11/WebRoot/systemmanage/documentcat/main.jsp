<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>文档库目录管理</title>
	</head>
			<script type="text/javascript" >
function clo()
{    
     var ag ='<s:property value="msg" escape="false" />';
	 if(ag)
	 {	
		alert("提示："+ag);
	 }
 }
		</script>
	<frameset rows="180,*" cols="*" framespacing="5" bordercolor="#f8f9f4" frameborder="yes" onload="clo();">
		<frame id="topFrame" name="topFrame" title="topFrame" src="" noresize="noresize" scrolling="no" frameborder="0" style="BORDER:#91A9BD 1px solid"/>
		<frame id="listFrame" name="listFrame" title="listFrame" src="list.action?pid=${pid}&flag=${flag}" scrolling="auto" frameborder="0" style="BORDER:#91A9BD 0px solid"/>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>