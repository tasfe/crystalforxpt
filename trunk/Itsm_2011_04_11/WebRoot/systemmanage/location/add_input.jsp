<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>区域管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
function getLocation(){ //取出类别
LocationDAO.findAll(callbackorg)
}
function callbackorg(data){  //显示出类别
   dwr.util.removeAllOptions("Location");   
   dwr.util.addOptions("Location",{'-1':'--请选择--'});
   dwr.util.addOptions("Location",data,"id","name");
}
function confir(){
	var value1=document.getElementById('locaiton.name').value;
	if(value1==''){ alert('请输入区域名称！'); return false;}
	var value2=document.getElementById('locationid').value;
	if(value2==-1||!value2||value2==0){ alert('请选择父级区域！'); return false;}
	
	
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}

</script>
		<style type="text/css">
<!--
body {
	margin-top: 5px;
}
-->
</style>
	</head>
	<body onLoad="getLocation()" leftmargin="0" marginwidth="0" onMouseDown="document.getElementById('locationDiv').style.visibility='hidden';">
		<s:form id="form1" action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="location.id" name="location.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>区域名称<span style = 'color : red; vertical-align   :middle'>*</span>：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:textfield id="locaiton.name" name="location.name"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" nowrap bgcolor="#deebf1">
						<b>区域详细介绍：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="location.content" name="location.content"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>父级区域<span style = 'color : red; vertical-align   :middle'>*</span>：</b>
					</td>
					<!--  
					<td width="88%" bgcolor="#FFFFFF">
						<select id="Location" name="location.pid"></select>
					</td>
					-->
					<td bgcolor="#FFFFFF"  width="15%">

						<input type="hidden" name="location.pid" id="locationid" value="<s:property value="location.pid"/>">
						<input type="text" name="parentName" id="locationname" value="<s:property value="parentName"/>" onClick="document.getElementById('locationDiv').style.visibility='visible'" readonly style="width:25%;">
						<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('locationDiv').style.visibility='visible'"><br/>
						<div id="locationDiv"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">	
							<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
						</div>
						<script type="text/javascript">
							var type='${actionURI}';
							if(type=='update'){
								document.getElementById('locationname').disabled=true;
								document.getElementById('img').style.display='none';
							}
						</script>
         			</td>
				</tr>


				<%--<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>位置代码：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="location.code" name="location.code"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>

				--%><tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>英文名称：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="location.locationSc" name="location.locationSc"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>

				<tr style="height: 25">
					<td align="center"  height="25" colspan="2" align="left" nowrap="nowrap"
						bgcolor="#deebf1" style="padding-left: 120px;">
						<input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return confir()">
						&nbsp;&nbsp;
						<input type="reset" value=" 重置 " class="mmBtn">
						&nbsp;&nbsp;
						<input type="button" value=" 返回 " onClick="window.history.go(-1)" class="mmBtn">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>