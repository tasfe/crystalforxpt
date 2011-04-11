<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<html>
	<head>
		<title>查看文档</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

		<script type='text/javascript' src='../dwr/util.js'>
</script>

		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript" src="../fckeditor/fckeditor.js">
</script>
		<script type="text/javascript">

function clo()
{
window.close();
}

	</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="padding: 7px;; cursor: default; overflow-y: scroll">
		<s:form action="save" namespace="/document" method="post"
			enctype="multipart/form-data" theme="simple">
			
			<table width="80%" height="100%" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="92%">
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<s:property value="dc.title"/>
								
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									摘要：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<s:property value="dc.summary"/>
								</td>
							</tr>
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									关键词：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:property value="dc.keyword"/>
								</td>
							</tr>
							
							<tr>
								<td width="13%" height="50%" align="right" valign="middle"
									bgcolor="#deebf1">
									内容：
								</td>
								<td width="87%" bgcolor="#FFFFFF" style="overflow: scroll;">
							             <s:property value="dc.context" escape="false"/>
									<%--<FCK:editor instanceName="dc.context" toolbarSet="Mybasic"
										basePath="/fckeditor" height="100%" value="${dc.context}">
										<FCK:config CustomConfigurationsPath="${pageContext.request.contextPath}/js/fckeditor/myconfig.js" />
									</FCK:editor>
								--%></td>
							</tr>
							<tr>
								<td rowspan="2" class="td-left-s" align="right" valign="middle" bgcolor="#deebf1">
									<img src="../img/jiedian.gif" width="10" height="9">
									附件：
								</td>
									<td height="45%" colspan=5 valign="top" bgcolor="#EBF4F5"
									class="td-detail"
									style="font-weight: normal; line-height: 22px">
									<table width="100%" height="100%" border="0" cellpadding="4"
										cellspacing="1" bgcolor="#FFFFFF">
										<s:iterator value="accessoryList" var="accesory">
											<tr>
												<a href="down.action?dlFileName=<s:property value='name'></s:property>">
													<s:property value="trueName"></s:property> </a>
											</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							height="8%">
							<tr align="center" bgcolor="white">
								<td align="center" height="12" style="padding-top: 5px">
									
									<input name="button" type="button" class="mmBtn"
										onClick="clo();" value="返回">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>