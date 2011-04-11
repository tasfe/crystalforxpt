<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
<head>
<title>查看日志</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:auto;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看日志</td>
  </tr>
</table>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" id="mainright" height="99%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">						
						<tr>
							<td valign="top">
								<table width="100%" border="1" cellpadding="0" cellspacing="0" height="100%">
									<tr>
										<td bgcolor="#FFFFFF" valign="top">
											<table width="100%" border="0" cellspacing="2" cellpadding="0" height="100%" >
												<tr>
													<td valign="top">
															<table cellspacing=0 cellpadding=5 border=0 width="100%">
																<s:iterator value="stdList" var="std" status="st">
																	<tr>
																		<td width="10%" nowrap background="../img/address.jpg" height="30">
																			<img src="../img/view.jpg">&nbsp;&nbsp;<s:property value="title"/>
																		</td>
																		<td width="75%" nowrap background="../img/address.jpg"  height="30">
																			<s:property value="users.truename"/>
																		</td>
																		<td background="../img/address.jpg"  width="15%" align="center" nowrap> &nbsp;
																			<s:date name="submitTime" format="yyyy-MM-dd HH:mm:ss"/>
																		</td>
																	</tr>																	
																	<tr>
																		<td colspan="3" width="100%" height="10px" style="padding:0px 15px 5px" >
																			<table width="100%" border="0" cellpadding="0" cellspacing="0" >
							 													<tr>
																					<td >${progress}%&nbsp;&nbsp;<img src="../images/locationlistbg.gif" width="${progress}%" height="3"></td>
							  													</tr>
																			</table>																			
																		</td>
																	</tr>
																	<tr>
																		<td width="960" style="padding:1px 15px 5px;line-height:21px;word-wrap:break-word;table-layout:fixed;" colspan="3">
																				<s:property value="content"/>
																		</td>
																	</tr>
																	<s:if test="#std.accessoryList.size()>0">
																	<tr>
																		<td style="padding:0px 15px 0px;line-height:21px" colspan="3">
																			<table width="15%" border="1" cellpadding="0" cellspacing="0" >
							 													<s:iterator value="#std.accessoryList" var="acc" status="st">
							 													<tr><td bgcolor="#EBF4F5" >
																				<a href="../engineertrace/download.action?dlFileName=<s:property value='name'></s:property>">
																					<font color="blue"><s:property value="trueName"/></font>
																				</a></td></tr>
																			</s:iterator>
																			</table>
																		</td>		
																	</tr>
																	</s:if>
																</s:iterator>
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
				</td>
			</tr>
			
		</table> 
</body>
</html>
