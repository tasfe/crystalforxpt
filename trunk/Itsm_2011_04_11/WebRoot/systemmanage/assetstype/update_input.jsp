<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产类别管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src='../dwr/interface/AssetsTypeDAO.js'></script>
  <script type='text/javascript' src='../dwr/engine.js'></script>
  <script type='text/javascript' src='../dwr/util.js'></script>

		
<script type="text/javascript">
function init(){
AssetsTypeDAO.findById("<s:property value="assetsType.pid"/>",callpidname);
}

function callpidname(data){
var a="<s:property value="assetsType.pid"/>";
if(a==0){
document.getElementById("assetsTypename").value="根目录";
}else{
document.getElementById("assetsTypename").value=data.name;
}
}


function isExist(){
var ids=document.getElementById("assetsType.id").value;
AssetsTypeDAO.findById(ids,isequals);
}

function isequals(data){
var name=document.getElementById("assetsType.name").value;
if(name==""){
alert("类别名称不能为空！");
}else{
if(data.name!=name)
{
AssetsTypeDAO.findByNames(name,ishave);
}else{
var id=document.getElementById("assetsTypeid").value;
var patrn=/^[0-9]{1,20}$/;
if (!patrn.exec(id)||id=="") 
{
alert("父级代码不能空！");
}else if(id==0){
document.form1.submit();
}else{
AssetsTypeDAO.findById(id,ishaveid);
}

}
}
}


function ishave(data){
if(data.length!=0)
{
alert('类别名称重复，请重新命名！');
}else{
var id=document.getElementById("assetsTypeid").value;
var patrn=/^[0-9]{1,20}$/;
if (!patrn.exec(id)||id=="") 
{
alert("父级代码不能空！");
}else if(id==0){
document.form1.submit();
}else{
AssetsTypeDAO.findById(id,ishaveid);
}
}
}

function ishaveid(data){
if(data==null){
alert("父级代码不存在！");
}else{
document.form1.submit();
}
}

function notshow(){
document.getElementById('assetsType').style.visibility='hidden'
}

</script>
<!--
body {
	margin-top: 5px;
}
-->

	</head>
	<body leftmargin="0" marginwidth="0" onload="init();" onMouseDown="notshow();">
		<s:form action="%{actionURI}.action" method="post" theme="simple" name="form1">
			<s:hidden id="assetsType.id" name="assetsType.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>类别名称：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:textfield id="assetsType.name" name="assetsType.name" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" nowrap bgcolor="#deebf1">
						<b>详细描述：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="assetsType.description" name="assetsType.description"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>父级类别：</b>
					</td>
					<td bgcolor="#FFFFFF">
<input type="hidden" name="assetsType.pid" id="assetsTypeid" value="<s:property value="assetsType.pid"/>" Style="width: 100%">
<input type="text" name="assetsTypename" id="assetsTypename" onClick="document.getElementById('assetsType').style.visibility='visible'" readonly Style="width: 100%">
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
				    </td>
				</tr>
				
				<tr align="center" style="height: 25">
					<td height="25" colspan="2" align="center" bgcolor="#FFFFFF">
						<input type="button" class="mmBtn" value=" 保存 " onClick="isExist();">
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