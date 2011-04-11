<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
function Chose(Num){
	window.parent.document.getElementById('userid').value=Num;
}
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

function users() {
	var id = document.getElementById("prinpalid").value;
	var all=document.getElementsByName('principal');	
	for(var i=0;i<all.length;i++) {		
		if(all[i].value==id){all[i].checked=true;}
	}
}
</script>
</head>
<body onload="users()" bgcolor="#F3F4F8" leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false">
	<s:hidden id="prinpalid" name="id"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr> 
    		<td valign="top" id="mainright" height="100%"> 
      			<table width="100%" border="0" cellspacing="0" cellpadding="0">
        			<tr> 
          				<td height="136" valign="top" bgcolor="#FFFFFF">             
            				<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
              					<tr> 
                					<td width="40%">用户名</td>
                					<td width="40%">真实姓名</td>
                					<td align="center" width="10%">负责人</td>
                					<td align="center" width="10%">成员</td>								
              					</tr> 
              					<s:if test="actionURI.equals('users')">
              						<s:iterator value="userList" var="user" status='st'>
        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										<td><s:property value="username"/></td>
          								<td><s:property value="truename"/></td>
          								<td  align="center" >       									
          									<input name="principal" type="radio" value="<s:property value='id'/>" style="border: 0px" onClick="if(this.checked){Chose(this.value)}">
          								</td>
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
          									<input name="principal" type="radio" value="<s:property value='id'></s:property>" style="border: 0px" onClick="if(this.checked){Chose(this.value)}">
          								</td>
          								<td  align="center" >
          									<input id="member" name="member" type="checkbox" value="<s:property value='id'></s:property>" style="border: 0px" onClick="member()">
          								</td>
        							</tr>
      							</s:iterator>
      							</s:else>
            				</table>
          				</td>
        			</tr>
      			</table>
    		</td>
  		</tr>
	</table>
</body>
</html>