<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="../css/Default.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/dtree.js">
</script>
		<script src="../js/Main.js">
</script>
		<script src="../js/controls/Tabpage.js">
</script>
		<script>

</script>
	</head>
	<body oncontextmenu="return false;">
		<table width="100%" border="0" cellspacing="6" cellpadding="0"
			style="border-collapse: separate; border-spacing: 6px;">
			<tr valign="top">
				<td width="180">
					<table width="150" border="0" cellspacing="0" cellpadding="6">
						<tr>
							<td style="padding: 6px;">
								<div
									style="position: absolute; overflow-x: scroll; overflow-y: scroll; height: 500px; padding-right: 3px; padding-bottom: 3px; left: 21px; top: 29px;">
									<table width="135" style="width: 180px">
										<tr>
											<td width="167" height="100%" colspan="2" valign="top" bgcolor="#FFFFFF"
												style="padding: 0px">

												<s:property value="treeString" escape="false" />

											</td>
										</tr>
									</table>
								</div>

						  </td>
						</tr>
					</table>
				</td>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="6"
						class="blockTable">
						<tr>
							<td style="padding: 2px 6px; height: 80%;">
								<iframe src="list.action" width="100%" height="0"
									id="listFrame" frameborder="0" scrolling="auto"
									allowtransparency="true"></iframe>
							</td>
						</tr>
					</table>
					<script>
Page.onLoad(function() {
	Tab.initFrameHeight("listFrame");
}, 5);</script>
				</td>
			</tr>
		</table>
	</body>
</html>

