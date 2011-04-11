<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产状态添加页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">    
		
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/interface/AssetsStateDAO.js'></script>
        <script type='text/javascript' src='../dwr/engine.js'></script>
        <script type='text/javascript' src='../dwr/util.js'></script>
        <base target="_self">
		<script type="text/javascript">
		

		
function init(){ //取出类别
var a="<s:property value="message"/>";
if(a=="1"){
window.returnValue=1;
window.close();
}
} 

function clo(){
window.close();
}
function check(){
var id=document.getElementById("assetsState.id").value;
AssetsStateDAO.findById(id,isequals);
}
function isequals(data){
var name=document.getElementById("assetsState.name").value;
if(name==""){
alert("请填写状态名称！");
}else{
if(name!=data.name){
AssetsStateDAO.findByName(name,ishave);
}else{
var sequence=document.getElementById("assetsState.sequence").value; 
var patrn=/^[0-9]{1,20}$/; 
if(sequence==""){
alert("请填写状态顺序，并且必须是数字！");
}else if(!patrn.exec(sequence)) {
alert("状态顺序请输入数字！");
}else{
document.form1.submit();
}
}
}
}
function ishave(data){
if(data.length!=0){
alert("状态名称重复！");
}else{
var sequence=document.getElementById("assetsState.sequence").value; 
var patrn=/^[0-9]{1,20}$/; 
if(sequence==""){
alert("请填写状态顺序，并且必须是数字！");
}else if(!patrn.exec(sequence)) {
alert("状态顺序请输入数字！");
}else{
document.form1.submit();
}
}
}


</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="init();">



<table cellspacing=0 cellpadding=0 border=0 width="100%">   
<tr>
<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">
<img src="../images/modpass.gif" width="16" height="16"></td>
<td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产变更修改</td>
</tr>
</table>  

<s:form action="/assetsstate/update.action" method='post' theme="simple" name="form1">
<s:hidden id="assetsState.id" name="assetsState.id"></s:hidden>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">状态名称：</td>
<td align="left" width="70%">&nbsp;<s:textfield id="assetsState.name" name="assetsState.name"></s:textfield></td>
</tr>  
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">状态顺序：</td>
<td align="left" width="70%">&nbsp;<s:textfield id="assetsState.sequence" name="assetsState.sequence"></s:textfield></td>
</tr> 
<tr height="26" bgcolor="#FFFFFF">
<td colspan="2" align="center" width="100%" class="alllisttitle">
<input type="button" value="提交" class="mmBtn" onClick="check();">
<input type="button" value="取消" class="mmBtn" onClick="clo();">
</td>
</tr> 
</table>

</s:form>

</body>
</html>
