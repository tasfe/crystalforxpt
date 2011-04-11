<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" /> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function search(id,type){
				window.parent.document.getElementById('solution').src="search.action?knowledgeBase.categoryId.id="+id+"&knowledgeBase.categoryid.type="+type;
			}
		</script>
</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">	
		<table width="100%" height="100%" border="0" cellpadding="2"
			cellspacing="1" bgcolor="#CCCCCC">
			<tr>
				<td height="100%" colspan="2" valign="top" bgcolor="#FFFFFF"
					style="padding: 0px">
					<div style="position: absolute; overflow-x: scroll; overflow-y: scroll;  height: 100%; width: 100%; padding-right: 3px; padding-bottom: 3px">

						<script type="text/javascript">
													d = new dTree('d');
													d.add('0','-1','树形结构','','','listFrame');
													<s:iterator value="allServiceCategory" var="category">
														d.add('${category.id}','${category.pid}','${category.itemZh}','javascript:search(${category.id},${category.type})');
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