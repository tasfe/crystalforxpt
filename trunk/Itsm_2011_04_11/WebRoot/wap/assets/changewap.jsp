<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a img{border:0px;}
-->
</style>

<base target="_self">
<script type="text/javascript">
function init(){
var a=<s:property value="message"/>;
 if(a=="1"){
   alert("变更成功！");
   document.form1.action="../wapassets/main.action";
   document.form1.submit();
 }
}

function check(){
var type=document.getElementById("type").value; 
var Charge=document.getElementById("Charge").value; 
var Managers=document.getElementById("Managers").value; 
var User=document.getElementById("User").value; 
var changeTime=document.getElementById("changeTime").value; 
var remark=document.getElementById("remark").value; 
var changeDescription=document.getElementById("changeDescription").value; 

if(type==-1){
alert("请选择变更类型！");
}else if(Charge==-1){
alert("请选择负责人！");
}else if(Managers==-1){
alert("请选择经办人！");
}else if(User==-1){
alert("请选择使用人！");
}else if(changeTime==""){
alert("请选择变更日期！");
}else if(CheckDate(changeTime)){
alert("变更日期格式为YYYY-MM-DD");
}else if(remark!=""&&CheckDate(remark)){
alert("归还日期格式为YYYY-MM-DD");
}else{
document.form1.action="../wapassets/changesave.action?code=<s:property value="base.code"/>";
document.form1.submit();
}
}

//判断输入的日期是否正确
function CheckDate(INDate)
{ 
 if(INDate==""){return true;}
 subYY=INDate.substr(0,4)
 if(isNaN(subYY) || subYY<=0){
  return true;
 }
 //转换月份
 if(INDate.indexOf('-',0)!=-1){ separate="-"}
 else{
  if(INDate.indexOf('/',0)!=-1){separate="/"}
  else {return true;}
  }
  area=INDate.indexOf(separate,0)
  subMM=INDate.substr(area+1,INDate.indexOf(separate,area+1)-(area+1))
  if(isNaN(subMM) || subMM<=0){return true;}
  if(subMM.length<2){subMM="0"+subMM}
 //转换日
 area=INDate.lastIndexOf(separate)
 subDD=INDate.substr(area+1,INDate.length-area-1)
 if(isNaN(subDD) || subDD<=0){
  return true;
 }
 if(eval(subDD)<10){subDD="0"+eval(subDD)}
 NewDate=subYY+"-"+subMM+"-"+subDD
 if(NewDate.length!=10){return true;}
    if(NewDate.substr(4,1)!="-"){return true;}
    if(NewDate.substr(7,1)!="-"){return true;}
 var MM=NewDate.substr(5,2);
 var DD=NewDate.substr(8,2);
 if((subYY%4==0 && subYY%100!=0)||subYY%400==0){ //判断是否为闰年
  if(parseInt(MM)==2){
   if(DD>29){return true;}
  }
 }else{
  if(parseInt(MM)==2){
   if(DD>28){return true;}
  } 
 }
 var mm=new Array(1,3,5,7,8,10,12); //判断每月中的最大天数
 var flag = false;
 for(i=0;i<mm.length;i++){
    if(parseInt(MM,10) == mm[i]){flag = true;}
 }
 if (flag == true){
    if(parseInt(DD)>31){return true;}
 }else{
    if(parseInt(DD)>30){return true;}
 }

 if(parseInt(MM)>12){return true;}
   return false;
}
</script>
<title>IT运维-变更</title>
</head>
<body onload="init();" bgcolor="#E4E5E7">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="../images/wap/top_bg.jpg"><img src="../images/wap/logo.jpg" width="152" height="45"></td>
  </tr>
  <tr>
    <td height="266" valign="top" bgcolor="#E4E5E7">
    <div>
<form id="form1" name="form1" action="" method="post">
变更类型<br/>
<select id="type" name="type" style='width:100;'>
<option value="-1">-请选择-</option>
<s:iterator value="statelist" var="assetsState">
<option value="<s:property value="id" />"><s:property value="name"/></option>
</s:iterator>
</select><br/>
负责人<br/>
<select id="Charge" name="Charge" style='width:100;'>
<option value="-1">-请选择-</option>
<s:iterator value="userlist" var="users">
<option value="<s:property value="id" />"><s:property value="truename"/></option>
</s:iterator>
</select><br/>
经办人<br/>
<select id="Managers" name="Managers" style='width:100;'>
<option value="-1">-请选择-</option>
<s:iterator value="userlist" var="users">
<option value="<s:property value="id" />"><s:property value="truename"/></option>
</s:iterator>
</select><br/>
使用人<br/>
<select id="User" name="User" style='width:100;'>
<option value="-1">-请选择-</option>
<s:iterator value="userlist" var="users">
<option value="<s:property value="id" />"><s:property value="truename"/></option>
</s:iterator>
</select><br/>
变更日期<br/>
<input type="text" id="changeTime" name="changeTime" style='width:100px;' value="<s:property value="changeTime"/>">格式YYYY-MM-DD<br/>
归还日期<br/>
<input type="text" id="remark" name="remark" style='width:100px;' value="<s:property value="remark"/>">格式YYYY-MM-DD<br/>
变更描述<br/>
<textarea rows="5" cols="20" id="changeDescription" name="changeDescription" ></textarea><br/>
<div>
<a href="#" onclick="check();"><img src="../images/wap/bt_bc.jpg" width="48" height="21"/></a>
<a href="../wapassets/search.action?codeId=<s:property value="base.codeId"/>"><img src="../images/wap/bt_fh.jpg" width="48" height="21"/></a>
</div>
</form>
</div>

    </td>
  </tr>
</table>
</body>

</html>