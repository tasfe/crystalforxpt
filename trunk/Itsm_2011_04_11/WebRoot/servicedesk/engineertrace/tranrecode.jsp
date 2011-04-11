<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false">
	<table width="100%" height="220" border="0" cellpadding="0" cellspacing="1" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
		<tr>
			<td valign="top" height="12">
				<table width="100%" border="0" cellspacing="1" cellpadding="2"  class="datagrid">
              					<s:iterator value="serviceTranList" var="serviceTran" status='st'>
        							<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#ffffff'">
        								<td align="center" width="14%" nowrap><s:property value="usersByServiceFrom.truename"/></td>
										<td align="center" width="52%" nowrap><s:property value="note"/></td>
										<td align="center" width="16%"><s:property value="usersByServiceTo.truename"/></td>
										<td align="center" width="12%" nowrap><s:property value="operatorTime"/></td>
										<td align="center" width="6%" nowrap style="text-align: center">详细</td>
        							</tr>
      							</s:iterator>					
            				</table>
          				</td>
        			</tr>
      			</table>
</body>
</html>