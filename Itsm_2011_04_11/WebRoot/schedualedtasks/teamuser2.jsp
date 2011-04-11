<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language=JavaScript>
		function member() {
			var box = document.getElementsByName('member');
			var a = ",";
			for ( var i = 0; i < box.length; i++) {
			if (box[i].checked) {
				a = a + box[i].value+ "," ;
			}
		}
		window.parent.document.getElementById('members').value = a;
		}
</script>
</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">		
		
						<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px">
							<tr>
								<td width="40%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 用户名 </td>
								<td width="40%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 真实姓名 </td>
								<td align="center" width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 成员 </td>
							</tr>
							<s:if test="actionURI.equals('users')">
              						<s:iterator value="userList" var="user" status='st'>
        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										<td><s:property value="username"/></td>
          								<td><s:property value="truename"/></td>
          								<td  align="center" >
          									<input id="member" name="member" type="checkbox" value="<s:property value='id'></s:property>" style="border: 0px" onClick="member()" checked>
          								</td>
        							</tr>
      							</s:iterator>
              					</s:if>
              					<s:else>
              					<s:iterator value="role.userses" var="user" status='st'>
        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										<td><s:property value="username"/></td>
          								<td><s:property value="truename"/></td>
          								<td  align="center" >
          									<input id="member" name="member" type="checkbox" value="<s:property value='id'></s:property>" style="border: 0px" onClick="member()">
          								</td>
        							</tr>
      							</s:iterator>
      							</s:else>
						</table>
	</body>
</html>
