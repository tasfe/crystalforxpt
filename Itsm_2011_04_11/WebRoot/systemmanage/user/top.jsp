<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" /> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">


.list {
	border-left: 1px solid white;
	border-right: 1px solid #A09B8D;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

.list_btm {
	border-top: 2px outset white;
	background-color: #D4D0C7;
	text-align: right;
	padding: 3px;
	padding-top: 4px;
}

.list_par {
	border-top: 1px solid white;
	border-bottom: 2px ridge #BDB9B0;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

td {
	font-size: 11px;
	padding: 2px;
	font-family: Tahoma;
}

.mmBtn {
	padding: 0px;
	background-color: #D4D0C7;
	mborder: 1px outset white;
	font-family: Tahoma;
	font-size: 11px;
	line-height: 15px;
}

.divtitle {
	position: absolute;
	z-index: 1;
	overflow: hidden;
	width: 100%;
	height: 22px;
	padding: 0px;
}
</style>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" style="padding: 0px" height="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
						<tr>
							<td style="padding: 0px">
								<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
									<tr>
										<td style="padding: 0px; overflow-y: scroll; padding-right: 2px" height="12" class="list_par" colspan="2">
											<table cellspacing=0 cellpadding=0 border=0 width="100%" style="border-right: 1px solid white">
												<tr>
													<th>
														<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
															<tr>
																<td nowrap valign="top">用户管理</td>
															</tr>
														</table>
													</th>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td style="padding: 0px" height="100%" valign="top" colspan="2">
											<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 156px; width: 100%; padding-right: 3px; padding-bottom: 3px">
												<script type="text/javascript">
													d = new dTree('d');
													d.add('0','-1','树形结构','','','listFrame');
													<s:iterator value="locations" var="location">
														d.add('${location.id}','${location.pid}','${location.name}','list.action?locationId=${locaction.id}&name=${location.name}','','listFrame');
													</s:iterator>
													document.write(d);
													d.openAll();
												</script>
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
	</body>
</html>