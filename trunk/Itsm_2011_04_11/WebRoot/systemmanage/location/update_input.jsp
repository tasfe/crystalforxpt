<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>区域管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='${pageContext.request.contextPath}/dwr/util.js'></script>
 <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/interface/LocationDAO.js'></script>
  <script type='text/javascript' src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type="text/javascript">
function getLocation(){ //取出类别
LocationDAO.findAll(callbackorg)
}
function callbackorg(data){  //显示出类别
   dwr.util.removeAllOptions("Location");
   dwr.util.addOptions("Location",data,"id","name");
   dwr.util.setValue("Location",<s:property value="Location.pid"/>);      
}
function confir(){
	var value1=document.getElementById('locaiton.name').value;
	if(value1==''){ alert('请输入区域名称！'); return false;}
	var value2=document.getElementById('Location').value;
	if(value2==-1){ alert('请选择父级区域！'); return false;}
	var value3=document.getElementById('location.code').value;
	
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}
</script>

	</head>
	<body onLoad="getLocation()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"><br>
		<s:form action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="location.id" name="location.id"></s:hidden>
			<s:hidden id="location.pid" name="location.pid"></s:hidden>
			<table width="80%" height="75" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1"><b>区域名称<span style = 'color : red; vertical-align   :middle'>*</span>：</b></td>
					<td width="88%" bgcolor="#FFFFFF">
				  <s:textfield id="locaiton.name" name="location.name" cssStyle="width: 100%"></s:textfield>				  </td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" nowrap bgcolor="#deebf1"><b>区域详细介绍：</b></td>
					<td bgcolor="#FFFFFF">
					<s:textfield id="location.content" name="location.content" cssStyle="width: 100%"></s:textfield>					</td>
				</tr>
					<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1"><b>父级区域<span style = 'color : red; vertical-align   :middle'>*</span>：</b></td>
					<td width="35%" bgcolor="#FFFFFF">
					<s:textfield id="parentName" name="parentName" cssStyle="width: 35%" readOnly="true"></s:textfield></td>
				</tr>
				
				
				<%--<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1"><b>位置代码：</b></td>
					<td bgcolor="#FFFFFF">
					<s:textfield id="location.code" name="location.code" cssStyle="width: 100%"></s:textfield>					</td>
				</tr>
				
				--%><tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1"><b>英文名称：</b></td>
					<td bgcolor="#FFFFFF">
					<s:textfield id="location.locationSc" name="location.locationSc" cssStyle="width: 100%"></s:textfield>					</td>
				</tr>
				
				<tr align="center" style="height: 25">
					<td  align="center" height="25" colspan="2" align="left" nowrap="nowrap" bgcolor="#deebf1" class="list_btm" style="padding-left:120px;">
					<input name="submit" type="submit" class="mmBtn" value=" 保存 "  onClick="javascript:return confir()">
&nbsp;&nbsp;
<input name="reset" type="reset" class="mmBtn" value=" 重置 ">
&nbsp;&nbsp;
<input name="button" type="button" class="mmBtn" onClick="window.location='list.action'" value=" 返回 ">
				   </td>
			    </tr>
		  </table>
		</s:form>
	</body>
</html>