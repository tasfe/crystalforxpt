<!--TestRecords:500//-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function Chose(Num){
	window.parent.document.getElementById('ITprinc').value=Num;
}
function Chose1(Num){
	window.parent.document.getElementById('ITnotice').value=Num;
}
</script>
</head>
<body bgcolor="#F3F4F8" leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr> 
    		<td valign="top" id="mainright" height="100%"> 
      			<table width="100%" border="0" cellspacing="0" cellpadding="0">
        			<tr> 
          				<td height="136" valign="top" bgcolor="#FFFFFF">             
            				<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
              					<tr> 
                					<td width="13%" nowrap class="subtitle">登陆名</td>
                					<td width="30%" nowrap class="subtitle" colspan="2">用户全名</td>								
                					<td width="25%" nowrap class="subtitle">待处理任务</td>
                					<td width="25%" nowrap class="subtitle">处理中的任务</td>
                					<td height="18" nowrap class="subtitle" width="1%">选择
                					</td>
								
              					</tr> 
              					<s:iterator value="role.userses" var="user" status='st'>
        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										<td width="13%" nowrap class="subtitle"><s:property value="username"/></td>
          								<td width="35%" nowrap class="subtitle" colspan="2"><s:property value="truename"/></td>
          								<td width="25%" nowrap class="subtitle"><s:property value="pending"/></td>
          								<td width="25%" nowrap class="subtitle"><s:property value="processing"/></td>
          								<td height="13" nowrap class="subtitle" width="1%">          									
          									<input name="chose" type="radio" value="<s:property value='id'></s:property>" style="border: 0px" onClick="if(this.checked){Chose(this.value)}">
          								</td>
        							</tr>
      							</s:iterator>             
<!--								<tr bgcolor="#F9F9F9"> -->
<!--                					<td nowrap height="26"><img src="../img/head.jpg" align="absmiddle">Team</td>-->
<!--                					<td nowrap colspan="4">选择全部成员</td>-->
<!--									<td align="center" style="padding-top: 4px" id=""><input name="chose" type="radio" value="1" style="border: 0px" onClick="if(this.checked){Chose('')}"></td>-->
<!--								</tr>								-->
            				</table>
          				</td>
        			</tr>
      			</table>
    		</td>
  		</tr>
	</table>
</body>
</html>