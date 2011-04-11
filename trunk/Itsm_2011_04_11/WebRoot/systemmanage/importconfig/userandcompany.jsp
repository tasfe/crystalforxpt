
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="border: 0px" onmousedown="document.getElementById('Layer3').style.visibility='hidden'">
<input type="hidden" name="dept" id="dept" value="">
<a id="Layer3"></a>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" bgcolor="#F9F9F9">
  <tr> 
    <td valign="top" id="mainright" height="100%">
			<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
				<tr>
					<td width="1%" nowrap bgcolor="#EBF4F5" class="subtitle" style="font-weight: normal">部门:
					<td width="49%" bgcolor="#FFFFFF" class="subtitle">
						<input name="dept_2" type="text" id="dept_2" style="width: 100%" onClick="window.soluts.location='company.jsp';this.value='';document.getElementById('dept').value=''" readonly>
				  <td width="1%" nowrap bgcolor="#EBF4F5" class="subtitle" style="font-weight: normal">关键字:
					<td width="50%" bgcolor="#FFFFFF" class="subtitle"><input name="name" type="text" id="name" style="width: 100%">
				  <td width="1%" bgcolor="#FFFFFF" class="subtitle">
						<input type="button" value="Go" onClick="window.soluts.location='user.jsp'" class=mmBtn_sm name="button" style="font-weight: normal; width: 26px">
				</tr>
				<tr>
					<td height="99%" colspan="5" bgcolor="#FFFFFF" style="padding: 0px">
						<iframe frameborder="0" height="100%" width="100%" scrolling="yes" name="soluts" id="soluts" src="user.jsp" style="border: 1px solid #E5E9EE"></iframe>
				  </td>
				</tr>
	  </table>
	</td>
	</tr>
</table>
</body>
</html>