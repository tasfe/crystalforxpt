<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<html>
		<head>
			<title>查看知识库管理</title>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
			<link href="../css/style.css" rel="stylesheet" type="text/css">
		</head>
		<%
		FCKeditor fckEditor = new FCKeditor(request, "knowledgeBase.solution");
	%>
		<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;; cursor: default;overflow:auto">
			<s:form action="query" namespace="/knowledgebase" method="post" theme="simple">
				<table height="100%" width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td bgColor="white" valign="top">
							<table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#b5d6e6" height="100%">
								<tr>
									<td width="13%" height="5%" align="right" valign="middle" bgcolor="#deebf1">
										编号:
									</td>
									<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.indexcode" />
									</td>
								</tr>
								<tr>
									<td width="13%" height="5%" align="right" valign="middle" bgcolor="#deebf1">
										标题:
									</td>
									<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.title" />
									</td>
								</tr>
								<tr>
									<td width="13%" height="25%" align="right" valign="middle" bgcolor="#deebf1">
										症状描述:
									</td>
									<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.symptom" />
									</td>
								</tr>
								<tr>
									<td width="13%" height="5%" align="right" valign="middle" bgcolor="#deebf1">
										所属类别:
									</td>
									<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.categoryId.itemZh" />
									</td>
								</tr>

								<tr>
									<td width="13%" height="5%" align="right" valign="middle" bgcolor="#deebf1">
										工程师:
									</td>
									<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.engineerId.truename" />
									</td>
								</tr>

								<tr>
									<td width="13%" height="40%" align="right" valign="middle" bgcolor="#deebf1">
										解决方案:
									</td>
									<td width="87%"  bgcolor="#FFFFFF" >
										<s:property value='knowledgeBase.solution' escape="false" />
									</td>
								</tr>
								<tr>
									<td align="right" height="15%" nowrap bgcolor="#deebf1" style="line-height: 22px; padding-bottom: 60px">
										相关附件:
									</td>
									<td valign="top" bgcolor="#FFFFFF">
										<table width="100%" border="0" cellpadding="4" cellspacing="1" >
											<s:iterator value="accessoryList" var="accessory" status="st">
            									<tr bgcolor="#FFFFFF"  onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">            					
		    										<td align="left"><a href="download.action?dlFileName=<s:property value="name"/>"><s:property value="trueName"/></a></td>
          										</tr>
          									</s:iterator>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr align="center">
						<td align="center" height="12" style="padding-top: 5px">
							<input name="button" type="button" class="mmBtn" style="width: auto" onClick="window.history.go(-1)" value="返回">
						</td>
					</tr>
				</table>
			</s:form>
		</body>
	</html>