<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>详情页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
</script>

	</head>

	<body  leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="padding: 7px;; cursor: default;">
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="100%">
							<tr>
								<td width="18%" height="30"  valign="middle" align="center"
									bgcolor="#deebf1">
									显示名称:
								</td>
								<td width="82%" bgcolor="#FFFFFF">
										<s:property value="taskAllocation.displayname"/>
								</td>
							</tr>
							<tr>
								<td width="18%" height="30"  valign="middle"
									bgcolor="#deebf1" align="center">
									说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:
								</td>
								<td width="82%" bgcolor="#FFFFFF">
										<s:property value="taskAllocation.content"/>
								</td>
							</tr>
							<tr>
								<td width="18%" height="30"  valign="middle"
									bgcolor="#deebf1" align="center">
									事件或服务请求类别:
								</td>
								<td width="82%" bgcolor="#FFFFFF">
									<s:property value="catsName"/>
								</td>
							</tr>
							<tr>
								<td height="30"  width="18%" valign="middle" nowrap
									bgcolor="#deebf1" align="center">
									部门:
								</td>
								<td width="82%" bgcolor="#FFFFFF">
									<s:property value="departmentsName"/>
								</td>
							</tr>
							<tr>
								<td height="30"  valign="middle" nowrap width="18%"
									bgcolor="#deebf1" align="center">
									工作组:
								</td>
								<td width="82%" bgcolor="#FFFFFF">
									<s:property value="teamName"/>
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" height="12" style="padding-top: 5px">
						<input name="button" type="button" class="mmBtn"
							style="width: auto" onClick="window.location='list.action'"
							value="返回列表">
					</td>
				</tr>
			</table>
	</body>
</html>