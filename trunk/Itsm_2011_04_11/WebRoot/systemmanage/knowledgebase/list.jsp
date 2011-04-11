<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>知识库检索</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" language="javascript">
		function nextPage(num) {
			document.getElementById("pagepage").value=num;
			document.formpage.submit();
		}
	</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; overflow: hidden; padding: 2px; border: 1px inset">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" id="mainright" height="99%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
						<tr>
							<td height="32" bgcolor="#deebf1"> &nbsp;
								<img src="../images/modpass.gif" align="absMiddle"> &nbsp;
								<b>知识库检索</b>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<table width="100%" border="1" cellpadding="0" cellspacing="0" height="100%">
									<tr>
										<td bgcolor="#FFFFFF" valign="top">
											<table width="100%" border="0" cellspacing="2" cellpadding="0" height="100%" >
												<tr>
													<td valign="top">
														<div style="position: absolute; overflow-x: hidden; overflow-y: auto; height: 100%; width: 100%; padding-left: 10px;padding-right: 2px">
															<table cellspacing=0 cellpadding=5 border=0 width="100%">
																<s:iterator value="pageBean.list" var="knowledgeBase" status="st">
																	<tr>
																		<td nowrap style="color: #647A9C" background="../img/address.jpg" height="30">
																			<img src="../img/check.gif" width="19" height="19" align="absmiddle"> &nbsp;
																			<a style="cursor: hand; color: #647A9C" onClick="window.open('user_show.action?knowledgeBaseId=${id}')"><b><s:property value="title"></s:property>
																			</b>
																			</a>
																		</td>
																		<td style="cursor: hand" background="../img/address.jpg" onClick="" width="1%" align="right" nowrap> &nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td style="padding: 15px; line-height: 21px" colspan="2">
																			<P>
																				<A style="LINE-HEIGHT: 25px; FONT-SIZE: 15px"><FONT face=黑体>${solution}</FONT>
																				</A>
																			</P>
																		</td>
																	</tr>
																</s:iterator>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top" id="mainright" height="12">
					<table cellspacing=0 cellpadding=0 border=0 width="100%">
						<tr>
							<td align="center">
								共
								<s:property value="pageBean.allRow" />
								条记录&nbsp;&nbsp; 共
								<s:property value="pageBean.totalPage" />
								页&nbsp;&nbsp; 当前第
								<s:property value="pageBean.currentPage" />
								页&nbsp;&nbsp; 每页
								<s:property value="pageBean.pageSize" />
								行&nbsp;&nbsp;
							</td>
							<td align="right">
								<s:if test="%{pageBean.currentPage==1}">第一页 上一页</s:if>
								<s:else>
									<a href="#" onClick="nextPage(1)">第一页</a>
									<a href="#" onClick="nextPage(${pageBean.currentPage-1})">上一页</a>

								</s:else>
								<s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
									<a href="#" onClick="nextPage(${pageBean.currentPage+1})">下一页</a>
									<a href="#" onClick="nextPage(${pageBean.totalPage})">最后一页</a>
								</s:if>
								<s:else>下一页 最后一页</s:else>
							</td>
							<td>
								<s:form id="formpage" name="formpage" action="%{actionURI}"
									namespace="/knowledgebase" method="post" theme="simple">
									<s:hidden id="pagepage" name="page" value="1"></s:hidden>
									<s:hidden id="knowledgeBase.title" name="knowledgeBase.title"></s:hidden>
									<s:hidden id="type" name="type"></s:hidden>
									<s:hidden id="flag" name="flag"></s:hidden>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

