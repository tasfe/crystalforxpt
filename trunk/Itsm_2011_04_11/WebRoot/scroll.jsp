<%@ page contentType="text/html; charset=utf-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />		
		<link href="${pageContext.request.contextPath }/theme/css.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
		<title>无标题文档</title>
		<script type="text/javascript">
			function listNotice(){
				document.getElementById("div1").innerHTML="";
				window.parent.parent.mainFrame.location.href="../PersonalNotice/listPersonalNotice.action";
			}
		</script>
	</head>
	<body background="../images/main20100521_21.gif">	
		<s:if test="number>0">	
		<marquee direction="left"  behavior="scroll" scrollamount=3>
			<div id="div1">
 				<a style="cursor:hand" onclick="listNotice()" target="mainFrame">您有${number}条通知需要查阅，请点击此查看。</a>&nbsp;&nbsp;&nbsp;&nbsp;
 			</div>
 		</marquee> 
 		</s:if>
	</body>
</html>
