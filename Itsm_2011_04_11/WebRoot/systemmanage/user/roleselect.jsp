<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>用户所属角色选择</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		function initCheckbox() {
			var idsList=parent.document.getElementById('idsList').innerHTML;
			if(idsList){	
				var memberIds = document.getElementsByName('memberIds');
				var ids=idsList.split(',');
				for (var i = 0; i < memberIds.length; i++) {
					var id = memberIds[i].value +'';			
					for (var j = 0; j < ids.length; j++) {		
						if (id==ids[j]) {
							memberIds[i].checked = true;
						} 
					}
				}
				
			}
		}
		function choose(){
			var memberIds = document.getElementsByName('memberIds');
			var idsList="";
			for (var i = 0; i < memberIds.length; i++) {
				if(memberIds[i].checked){
					idsList+=memberIds[i].value;
					idsList+=",";
				}
			}
			parent.document.getElementById('idsList').innerHTML=idsList;
		}
		</script>
	</head>
	<body onLoad="initCheckbox()"  leftmargin="0" marginwidth="0" style="border: 0px">
		<table width="99%" border=0 align="left" cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
			<tr bgcolor="#deebf1">
				<td width='30%' align="center" nowrap >角色名称</td>
				<td width='30%' align="center" nowrap>角色类属</td>
				<td width='30%' align="center" nowrap style="display:none">查看</td>
				<td width='10%' align="center" nowrap>选择</td>
			</tr>
			<s:iterator value="roles" var="role">
				<tr bgcolor="#FFFFFF">
				  	<td  align="center" nowrap style="color: #333333"><s:property value="name" /></td>
					<td align="center" nowrap style="color: #333333"><s:property value="roleTypeName" /></td>
				
					<td  align="center" nowrap style="color: #333333;display:none">查看</td>
					<td  align="center" valign="middle">
						<input type="checkbox" name="memberIds"
							value="<s:property value="id"/>"
							onclick="choose()"/>
					</td>
				</tr>
		  </s:iterator>
	</table>
</body>
</html>