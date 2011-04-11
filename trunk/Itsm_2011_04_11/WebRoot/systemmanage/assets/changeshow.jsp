<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产变更页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">    
		
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<base target="_self">
		<script type="text/javascript">
		

		
function init(){
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
var type=document.getElementById("assetsChange.assetsState.id").value;
var Charge=document.getElementById("assetsChange.usersByChargeid.truename").value;
var Managers=document.getElementById("assetsChange.usersByManagersid.truename").value;
var User=document.getElementById("assetsChange.usersByUserid.truename").value;
var changeTime=document.getElementById("assetsChange.changeTime").value;
if(type==""){
alert("请选择变更类型！");
}else if(Charge==""){
alert("请选择负责人！");
}else if(Managers==""){
alert("请选择经办人！");
}else if(User==""){
alert("请选择使用人！");
}else if(changeTime==""){
alert("请选择变更日期！");
}else{
form1.submit();
document.getElementById("submit1").disabled=true;
}
}
function notshow(){
document.getElementById('assetsState').style.visibility='hidden';
}

function Charge(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByChargeid.id").value=result.id;
document.getElementById("assetsChange.usersByChargeid.truename").value=result.name;
document.getElementById("assetsChange.usersByChargeid.department.name").value=result.department;
}
}
function Managers(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByManagersid.id").value=result.id;
document.getElementById("assetsChange.usersByManagersid.truename").value=result.name;
}
}
function Users(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByUserid.id").value=result.id;
document.getElementById("assetsChange.usersByUserid.truename").value=result.name;
document.getElementById("assetsChange.usersByUserid.department.name").value=result.department;
}
}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="init();" onMouseDown="notshow();" >



<table cellspacing=0 cellpadding=0 border=0 width="100%">   
<tr>
<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">
<img src="../images/modpass.gif" width="16" height="16"></td>
<td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产变更修改</td>
</tr>
</table>  

<s:form action="/assets/changeupdate.action" method='post' theme="simple" name="form1">
<input type="hidden" name="assetsChange.id" id="assetsChange.id" value="<s:property value="assetsChange.id"/>"> 
<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%"  class="alllisttitle" bgcolor="#deebf1">变更类型：</td>
<td align="left" width="70%">
<input type="hidden" name="assetsChange.assetsState.id" id="assetsStateid" value="<s:property value="assetsChange.assetsState.id"/>">
<input type="text" name="assetsChange.assetsState.name" id="assetsStatename" value="<s:property value="assetsChange.assetsState.name"/>" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:50%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
</td>
</tr>

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">责任人：</td>
<td align="left" width="70%">
<s:hidden id="assetsChange.usersByChargeid.id" name="assetsChange.usersByChargeid.id"/>
<s:textfield id="assetsChange.usersByChargeid.truename" name="assetsChange.usersByChargeid.truename" style="width:50%;" onclick="Charge();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Charge();">

</td>
</tr> 

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">责任部门：</td>
<td align="left" width="70%">
<input type="text" id="assetsChange.usersByChargeid.department.name" name="assetsChange.usersByChargeid.department.name" readonly style="width:50%;" value="<s:property value="assetsChange.usersByChargeid.department.name"/>">
</td>
</tr> 

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">经办人：</td>
<td align="left" width="70%">
<s:hidden id="assetsChange.usersByManagersid.id" name="assetsChange.usersByManagersid.id"/>
<s:textfield id="assetsChange.usersByManagersid.truename" name="assetsChange.usersByManagersid.truename" style="width:50%;" onclick="Managers();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Managers();">

</td>
</tr> 
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%"  class="alllisttitle" bgcolor="#deebf1">使用人：</td>
<td align="left" width="70%">
<s:hidden id="assetsChange.usersByUserid.id" name="assetsChange.usersByUserid.id"/>
<s:textfield id="assetsChange.usersByUserid.truename" name="assetsChange.usersByUserid.truename" style="width:50%;" onclick="Users();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Users();">

</td>
</tr> 

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">使用部门：</td>
<td align="left" width="70%">
<input type="text" id="assetsChange.usersByUserid.department.name" name="assetsChange.usersByUserid.department.name" readonly style="width:50%;" value="<s:property value="assetsChange.usersByUserid.department.name"/>">
</td>
</tr> 

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">变更日期：</td>
<td align="left" width="70%">&nbsp;<input type="text" id="assetsChange.changeTime" name="assetsChange.changeTime" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="<s:date name="assetsChange.changeTime" format="yyyy-MM-dd"/>"></td>
</tr>
<tr height="26" bgcolor="#FFFFFF">
<td align="center" width="30%" class="alllisttitle" bgcolor="#deebf1">变更描述：</td>
<td align="left" width="70%">&nbsp;<textarea rows="5" cols="20" id="assetsChange.changeDescription" name="assetsChange.changeDescription"><s:property value="assetsChange.changeDescription"/></textarea></td>
</tr> 
<tr height="26" bgcolor="#FFFFFF">
<td colspan="2" align="center" width="100%" class="alllisttitle">
<input type="Button" value="提交" class="mmBtn" id="submit1" name="submit1" onClick="check();">
<input type="button" value="取消" class="mmBtn" onClick="clo();">
</td>
</tr> 
</table>

</s:form>

</body>
</html>
