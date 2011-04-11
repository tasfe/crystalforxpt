<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" /> 
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
	<tr>
		<td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
	    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><s:if test="type==1">事件及服务类别</s:if>
																		<s:elseif test="type==2">计划任务类别</s:elseif>
																		<s:elseif test="type==3">变更请求类别</s:elseif>
																		<s:elseif test="type==4">项目类别</s:elseif></td>
	</tr>
</table>
<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#CCCCCC">
	<tr>
		<td height="100%" colspan="2" valign="top" bgcolor="#FFFFFF" style="padding: 0px">
											<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 156px; width: 100%; padding-right: 3px; padding-bottom: 3px">
												<script type="text/javascript">
													d = new dTree('d');
													d.add('0','-1','树形结构','list.action?pid=0&type=${type}','','listFrame');
													<s:iterator value="allServiceCategory" var="serviceCategory">
														d.add('${serviceCategory.id}','${serviceCategory.pid}','${serviceCategory.itemZh}','list.action?pid=${serviceCategory.id}&type=${serviceCategory.type}','','listFrame');
													</s:iterator>
													document.write(d);
													d.openAll();
												</script>
											</div>
		</td>
	</tr>
</table>
</body>
</html>