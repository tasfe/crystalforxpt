<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产变更页面</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link href="../../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../../js/DatePicker/WdatePicker.js"></script>
        <base target="_self">
		<script type="text/javascript">
		

		
function init(){
var now=new Date();
var year=now.getFullYear();
var month=now.getMonth()+1;
var day=now.getDate();
if(month<10){month=0+""+month;}
if(day<10){day=0+""+day;}
var date=year+"-"+month+"-"+day;
document.getElementById("changeTime").value=date;
var a="<s:property value="message"/>";
if(a=="1"){
window.returnValue=1;
window.close();
}
var obj = window.dialogArguments;
document.getElementById('id').value=obj;
} 

function clo(){
window.close();
}

function check(){
var type=document.getElementById("changeType").value; 
var Charge=document.getElementById("usersByChargeid.truename").value; 
var Managers=document.getElementById("usersByManagersid.truename").value; 
var User=document.getElementById("usersByUserid.truename").value; 
var changeTime=document.getElementById("changeTime").value; 
if(type==-1){
alert("请选择变更类型！");
}else if(Charge==""){
alert("请选择负责人！");
}else if(Managers==""){
alert("请选择经办人！");
}else if(User==""){
alert("请选择使用人！");
}else if(changeTime==""){
alert("请选择变更时间！");
}else{
form1.submit();
document.getElementById("submit1").disabled=true;
}
}



function Charge(){
var obj1=null;
var result=window.showModalDialog("../../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByChargeid").value=result.id;
document.getElementById("usersByChargeid.truename").value=result.name;
document.getElementById("usersByChargeid.department").value=result.department;
}
}
function Managers(){
var obj1=null;
var result=window.showModalDialog("../../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByManagersid").value=result.id;
document.getElementById("usersByManagersid.truename").value=result.name;
}
}
function Users(){
var obj1=null;
var result=window.showModalDialog("../../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByUserid").value=result.id;
document.getElementById("usersByUserid.truename").value=result.name;
document.getElementById("usersByUserid.department").value=result.department;
}
}

function notshow(){
document.getElementById('assetsState').style.visibility='hidden';
}


</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="init();" onMouseDown="notshow();" >


<table cellspacing=0 cellpadding=0 border=0 width="100%">   
<tr>
<td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">
<img src="../../images/modpass.gif" width="16" height="16"></td>
<td width="100%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产管理</td>
</tr>
</table>  
<s:form action="/assets/changes.action" method="post" theme="simple" name="form1">
<input type="hidden" name="id" id="id"> 
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
<tr class="zcbg_tab" height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">变更类型：</td>
<td align="left" width="70%">
 <input type="hidden" name="changeType" id="assetsStateid" >
<input type="text" name="assetsChange.assetsState.name" id="assetsStatename" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:50%;">
<img src="../../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
</td>
</tr>

<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%"class="alllisttitle" bgcolor="#deebf1">责任人：</td>
<td align="left" width="70%">
<s:hidden id="usersByChargeid" name="usersByChargeid"/>
<s:textfield id="usersByChargeid.truename" name="usersByChargeid.truename" style="width:50%;" onclick="Charge();"/>
<img src="../../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Charge();">
</td>
</tr>
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%"class="alllisttitle" bgcolor="#deebf1">责任部门：</td>
<td align="left" width="70%">
<input type="text" id="usersByChargeid.department" name="usersByChargeid.department" readonly style="width:50%;">
</td>
</tr>  
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">经办人：</td>
<td align="left" width="70%">
<s:hidden id="usersByManagersid" name="usersByManagersid"/>
<s:textfield id="usersByManagersid.truename" name="usersByManagersid.truename" style="width:50%;" onclick="Managers();"/>
<img src="../../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Managers();">
</td>
</tr> 
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">使用人：</td>
<td align="left" width="70%">
<s:hidden id="usersByUserid" name="usersByUserid"/>
<s:textfield id="usersByUserid.truename" name="usersByUserid.truename" style="width:50%;" onclick="Users();"/>
<img src="../../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Users();">
</td>
</tr>
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">使用部门：</td>
<td align="left" width="70%">
<input type="text" id="usersByUserid.department" name="usersByUserid.department" readonly style="width:50%;">
</td>
</tr>
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">变更日期：</td>
<td align="left" width="70%">&nbsp;<input type="text" id="changeTime" name="changeTime" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
</tr>
<tr  bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">变更描述：</td>
<td align="left" width="70%">&nbsp;<textarea rows="5" cols="20" id="changeDescription" name="changeDescription" ></textarea></td>
</tr> 
<s:if test="%{#session.record==2}">
<tr height="26" bgcolor="#FFFFFF">
<td align="right" width="30%" class="alllisttitle" bgcolor="#deebf1">是否是HIS系统：</td>
<td align="left" width="70%">&nbsp;
<input type="checkbox" id="ishis" name="ishis" checked="true">默认为是
</td>
</tr>
</s:if>

<tr height="26" bgcolor="#FFFFFF">
<td colspan="2" align="center" width="100%" class="alllisttitle" bgcolor="#FFFFFF">
<input type="button" value="提交" id="submit1" name="submit1" class="mmBtn" onClick="check();">
<input type="button" value="取消" class="mmBtn" onClick="clo();">
</td>
</tr> 
</table>

</s:form>

</body>
</html>
