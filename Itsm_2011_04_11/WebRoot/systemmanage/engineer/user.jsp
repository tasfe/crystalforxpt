<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!--TestRecords:500//-->
<script language="javascript" event="onerror(msg, url, line)"
	for="window">return true;</script>

<html>
	<head>
		<title>IT Service Desk</title>
		<script language="javascript">
function OnUp()
{
	
}
</script>

<script type="text/javascript">
function showMembers() {
	var memberIds = document.getElementsByName('memberIds');
	var memberUsernames = document.getElementsByName('memberUsernames');
	var usernames = '';
	var userIds = '';
	for (var i = 0; i < memberIds.length; i++) {
	    if (memberIds[i].checked) {
		userIds = userIds + memberIds[i].value + ',';
        memberUsernames[i].checked = true;   
        usernames = usernames + memberUsernames[i].value + ',';   
		}else{
        memberUsernames[i].checked = false;}
	}
	parent.document.getElementById('showUsernames').innerHTML = usernames;	
	parent.document.getElementById('memberIds').innerHTML = userIds;
}

function showLeader() {
	
	var memberIds = document.getElementsByName('teamlead');
	var memberUsernames = document.getElementsByName('leadUsernames');
	var usernames = '';
	var userIds = '';
	for (var i = 0; i < memberIds.length; i++) {
	    if (memberIds[i].checked) {
		userIds = userIds + memberIds[i].value + ',';
        memberUsernames[i].checked = true;   
        usernames = usernames + memberUsernames[i].value + ',';   
		}else{
        memberUsernames[i].checked = false;}
	}
	parent.document.getElementById('leadUsernames').value = usernames;	
	parent.document.getElementById('teamlead').value = userIds;
}
function initCheckbox() {
				var memberIds = document.getElementsByName('memberIds');
				var idsList = parent.document.getElementById('idsList').innerHTML;
				//var idsList = '<s:property value="idsList"/>';
				var ids = idsList.split(',');
				for (var i = 0; i < memberIds.length; i++) {
					var id = memberIds[i].value +'';	
					
					for (var j = 0; j < ids.length; j++) {	
						
						
								if (id==ids[j]) {
								
									memberIds[i].checked = true;
								} 
							}
						}	
				
				showMembers();
				var teamleaderIds = document.getElementsByName('teamlead');
				
				var teamleaderId = parent.document.getElementById('teamleaderId').innerHTML;
			
				//var idsList = '<s:property value="idsList"/>';
				var idss = teamleaderId.split(',');
			
				for (var m = 0; m < teamleaderIds.length; m++) {
					
					var ida = teamleaderIds[m].value +'';	
					
					for (var j = 0; j < idss.length; j++) {	
						
						
								if (ida==idss[j]) {
								
									teamleaderIds[m].checked = true;
								} 
							}
						}
					
					showLeader();
				}
		



</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-top: 5px;
}
-->
</style></head>
	
	
	<body onLoad="initCheckbox()"  leftmargin="0" marginwidth="0"
		style="border: 0px"
		onMouseDown="document.getElementById('Layer2').style.visibility='hidden'">
<table width="100%" border=0 align="left" cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
								<tr bgcolor="#deebf1">
                                  <td height="24" colspan="2" align="center" nowrap >登录名</td>
								  <td height="24" align="center" nowrap>用户全名</td>
								
								  <td height="24" align="center" nowrap>部门</td>
								  <td height="24" align="center" nowrap>成员</td>
								   <td height="24" align="center" nowrap>负责人</td>
  </tr>
								<s:iterator value="userList" var="users">
									<tr bgcolor="#FFFFFF">

										<td width=2% align="center">
											<img src='../img/icon_user.gif' border='0' height=16>										</td>
										<td width="13%" style="color: #333333" nowrap align="center">
											&nbsp;
											<s:property value="username" />
&nbsp;										</td>

										<td width="18%" nowrap style="color: #333333" align="center">
											&nbsp;
											<s:property value="truename" />
&nbsp;										</td>
										
										<td width="20%" nowrap style="color: #333333" align="center">
											&nbsp;
											<s:property value="department.name" />
&nbsp;										</td>
										
										<td width="10%" align="center" valign="middle">
											<input type="checkbox" name="memberIds"
												value="<s:property value="id"/>"
												onclick="showMembers();" onchange="showMembers();" />
											  <span style="display: none;">	
											<input type="checkbox" name="memberUsernames"
												value="<s:property value="username"/>" />		
												
												<td width="10%" align="center" valign="middle">
											<input type="radio" name="teamlead"
												value="<s:property value="id"/>"
												onclick="showLeader();" onchange="showLeader();" />
											  <span style="display: none;">	
											<input type="checkbox" name="leadUsernames"
												value="<s:property value="username"/>" />										  </span>										</td>
									</tr>
								</s:iterator>
	</table>

	</body>
</html>
