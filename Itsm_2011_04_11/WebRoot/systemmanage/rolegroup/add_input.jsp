<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>角色分组设置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function init(){
	var message="<s:property value='message' />";
	if(message=="1"){
		alert("名称重复！");
	}
}		

function confir(){
	var value1=document.getElementById('roleGroup.name').value;
	if(value1==''){ alert('请输入名称！'); return false;}
	
	var value2=document.getElementById('roleGroup.id').value;
	var value3=document.getElementById('roleGroup.pid').value;
	if(value2 && value3){
		if(value2==value3){
			alert('父级组别不能为自身！'); 
			return false;
		}
	}
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}
function notshow(){
	document.getElementById('Layer1').style.visibility='hidden'
}
function reset(){
	document.getElementById('roleGroup.name').reset();
	document.getElementById('parentname').reset();
	document.getElementById('roleGroup.pid').value='-1';
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
	<body leftmargin="0" marginwidth="0" onMouseDown="notshow();" onload="init();">
		<s:form id="form1" action="save.action" method="post" theme="simple" namespace="/roleGroup">
			<s:hidden id="roleGroup.id" name="roleGroup.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>名称<span style = 'color : red; vertical-align   :middle'>*</span>：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:textfield id="roleGroup.name" name="roleGroup.name"
							cssStyle="width: 80%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>父级组别：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
<input type="hidden" name="roleGroup.pid" id="roleGroup.pid" value="<s:property value="roleGroup.pid" />">
&nbsp;<input type="text" name="parentname" id="parentname" onClick="document.getElementById('Layer1').style.visibility='visible'" readonly value="<s:property value="parentname" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"><br/>
<div id="Layer1"  style="position:absolute; width: 80%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../roleGroup/top.action" style="border: 1px solid #E5E9EE;"></iframe>
</div>
					</td>
				</tr>
				<tr style="height: 25">
					<td align="center"  height="25" colspan="2" align="left" nowrap="nowrap"
						bgcolor="#deebf1" style="padding-left: 120px;">
						<input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return confir()">
						&nbsp;&nbsp;
						<input type="button" value=" 重置 " class="mmBtn" onClick="javascript:reset()">
						&nbsp;&nbsp;
						<input type="button" value=" 返回列表 " onClick="window.location='list.action?pid=-1'" class="mmBtn">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>