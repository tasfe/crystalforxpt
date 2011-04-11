<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>提交反馈信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        
        <style>
        td {font-size:12px}
        </style>
         <link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body leftmargin="0" marginwidth="0">

		<table width="502" height="60" border="5" align="center"
			cellpadding="0" cellspacing="0" bgcolor="#b5d6e6">
			<s:form method="post" action="doSatisfaction.action">
				<s:hidden id="requestNo" name="requestNo"></s:hidden>
				<tr style="height: 25">

					<td width="100" bgcolor="#EBF4F5"
						align="center" >
						用户满意度
					</td>
					<td width="70%" bgcolor="#EBF4F5" align="center"
						style="width: 295px">
						<table height="80%">
							<tr height="80%">
								<td height="80%">
									<s:radio name="serviceLvl" list="serviceLvList" listKey="value"
										listValue="name" value="1" title="用户满意度">
									</s:radio>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr style="height: 25">

					<td style="text-align: center" height="100" align="center"
						bgcolor="#EBF4F5">
						用户建议
					</td>
					<td align="center" bgcolor="#EBF4F5"
					style="width: 295px">
						<table>
							<tr>
								<td>
									<s:textarea id="feedback" name="feedback"
										 javascriptTooltip="用户建议" title="用户建议"
										rows="5" cols="30">
									</s:textarea>
								</td>
							</tr>
						</table>

					</td>
				</tr>
				<tr style="height: 25">

					<td colspan="2" align="center">
						<input name="button" type="submit" class="mmBtn" value="提交反馈">
						<input type="button" class="mmBtn" value="关闭"
							onClick="javascript:self.close()">

					</td>
				</tr>
			</s:form>
		</table>

	</body>
</html>