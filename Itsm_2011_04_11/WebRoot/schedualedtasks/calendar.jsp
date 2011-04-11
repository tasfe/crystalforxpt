<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>查看计划任务</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type="text/JavaScript" src="../js/Main.js"></script>
		<script type="text/javascript"> 
			function sub(){
				if (document.getElementById("keyword").value=="") {
					return false;
				}
				return true;
			}
		</script>
	</head>
	<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					查看计划任务
				</td>
			</tr>
		</table>
		<div>
			<iframe width=168 height=190 name="gfPop" id="gfPop" src="../function/calendar/ipopeng.asp" scrolling="no" frameborder="0"
				style="border: 1px outset; visibility: visible; z-index: 999; position: absolute; left: -600px; top: 0px;"></iframe>
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" id="mainright" height="100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="1" height="100%">
							<tr>
								<td colspan="2" height="12"></td>
							</tr>
							<tr>
								<td height="12" colspan="2" valign="top">
									<table border="0" cellspacing=0 cellpadding=0 width="100%">
										<tr>
											<td width="49%" height="30" style="padding-top: 7px" nowrap>
												<b><img src="../img/index.jpg" width="20" height="19" align="absmiddle">&nbsp;按日期查看计划任务:</b>
											</td>
											<td align=right height="30" nowrap>
												<span class="clsButtonFace">
													<input name="button1" type="button" onClick="window.location='add.action'" value="添加新任务" class="mmBtn">&nbsp;</span>&nbsp;
												<span class="clsButtonFace">
													<input name="button2" type="button" onClick="window.location='week.action'" value="按周查看任务" class="mmBtn">&nbsp;</span>&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td valign="top" height="99%" colspan="2">
									<table cellspacing=0 cellpadding=1 border=1 width="100%" class="tb-list" style="border: 1px outset white" height="100%">
										<tr>
											<td width="1%" height="22" align="left" nowrap background="../images/main20100521_58.gif"> &nbsp;&nbsp;
												<B>工作日历</B>
											</td>
										</tr>
										<tr>
											<td width="10%" valign="top">
												<table width="100%" height="100%" border="0" cellpadding="10" cellspacing="0">
													<tr>
														<td width="60%" height="100%" rowspan="2" valign="top" id="solutionsm" style="padding: 2px">
															<table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
																<tr>
																	<td width="100%" height="99%" valign="top" bgColor="#FFFFFF">
																		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
																			<tr>
																				<td height="100%" valign="top" width="100%" style="border: 1px inset; border-bottom: 0px">
																					<iframe frameborder="0" height="100%" width="100%" id="work" name="work" scrolling="yes" src="work.action"></iframe>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>

														<td width="40%" height="100%" valign="top" id="solutionsm" style="padding: 2px">
															<iframe frameborder="0" height="100%" width="100%" id="month" name="month" scrolling="yes" src="month.action"></iframe>
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
