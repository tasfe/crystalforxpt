﻿<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>知识库管理首页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js">
</script>
		<script type="text/javascript">
function sub() {
	if (document.getElementById("keyword").value == "") {
		alert("请输入关键词！");
		return false;
	}
	return true;
}
</script>
	</head>
	<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					知识库检索
				</td>
			</tr>
		</table>
		<div>
			<table cellspacing=0 cellpadding=1 border=1 width="100%"
				style="border: 1px outset white" height="100%">
				<tr>
					<td width="10%" valign="top">
						<table width="100%" height="100%" border="0" cellpadding="10"
							cellspacing="0">
							<tr>
								<td width="60%" height="100%" rowspan="2" valign="top"
									id="solutionsm" style="padding: 2px">
									<table width="100%" height="100%" border="0" cellpadding="1"
										cellspacing="0">
										<tr>
											<td width="100%" height="99%" valign="top" bgColor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" height="100%">
													<tr>
														<td height="100%" valign="top" width="100%"
															style="border: 1px inset; border-bottom: 0px">
															<s:form id="form1" name="from1" method="post"
																action="query2.action" target="_blank" theme="simple">
																<table width="100%" height="100%" border="0"
																	cellpadding="5" cellspacing="0">
																	<tr>
																		<td height="12" style="padding-left: 3px"></td>
																	</tr>
																	<tr>
																		<td height="22" style="padding-left: 3px">
																			请输入关键词:
																		</td>
																	</tr>
																	<tr>
																		<td height="12">
																			<table width="90%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td width="99%">
																						&nbsp;&nbsp;
																						<input id="keyword" name="knowledgeBase.title"
																							type="text" style="width: 99%" maxlength="20">
																					</td>
																					<td valign="top"></td>
																				</tr>
																				<tr>
																					<td colspan="2">
																						<div id="Layer"
																							style="position: absolute; width: 100%; height: 20px; z-index: 99; visibility: default; text-align: right">
																							<table width="1%" border="0" cellspacing="1"
																								cellpadding="0" style="border: 1px outset white"
																								background="../img/cldbg.jpg">
																								<!--  
																								<tr>
																									<td style="padding-top: 2px">
																										<input name="flag" type="radio"
																											style="border: 0px" value="Inci" checked>
																									</td>
																									<td nowrap>
																										事件&nbsp;&nbsp;
																									</td>
																								</tr>
																								<tr>
																									<td style="padding-top: 2px">
																										<input name="flag" type="radio"
																											style="border: 0px" value="Prob">
																									</td>
																									<td nowrap>
																										问题&nbsp;&nbsp;
																									</td>
																								</tr>
																								-->
																							</table>
																						</div>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td height="12" colspan="2">
																			<b>&nbsp;&nbsp;选项：</b>
																		</td>
																	</tr>
																	<tr>
																		<td height="12" colspan="2" style="padding-left: 0px">
																			<table width="80%" border="0" cellspacing="0"
																				cellpadding="3">
																				<tr>
																					<td width="5%" height="25"
																						style="padding-top: 6px; padding-left: 0px">
																						&nbsp;&nbsp;
																						<input name="type" type="radio" value="Title"
																							style="border: 0px">
																					</td>
																					<td width="95%" style="font-family: Tahoma" nowrap>
																						只在文档标题中检索...
																					</td>
																				</tr>
																				<tr>
																					<td height="25"
																						style="padding-top: 6px; padding-left: 0px">
																						&nbsp;&nbsp;
																						<input name="type" type="radio" value="Content"
																							checked style="border: 0px">
																					</td>
																					<td style="font-family: Tahoma" nowrap>
																						同时检索文档标题和文档内容...
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td height="30" align="right" valign="bottom"
																						style="padding: 0px; padding-top: 18px">
																						<tags:button code="select" menu="53">
																							<input type="submit" value="开始搜索…" class=mmBtn
																								onClick="javascript: return sub();">
																						</tags:button>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" valign="bottom"
																			style="padding: 0px; padding-bottom: 8px">
																			<img src="../img/addicon.jpg" width="140"
																				height="138">
																		</td>
																	</tr>
																</table>
															</s:form>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
								<td width="40%" height="100%" valign="top" id="solutionsm"
									style="padding: 2px">
									<table width="100%" height="100%" border="0" cellpadding="1"
										cellspacing="0">
										<tr>
											<td width="100%" height="99%" valign="top" bgColor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" height="100%">
													<tr>
														<td height="100%" valign="top" width="100%"
															style="border: 1px inset; border-bottom: 0px">
															<iframe frameborder="0" height="100%" width="100%"
																id="solution" name="solution" scrolling="yes"
																src="top2.action"></iframe>
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
			</table>

		</div>
	</body>
</html>