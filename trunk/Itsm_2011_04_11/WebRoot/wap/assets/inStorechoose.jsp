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
document.getElementById('codeId').focus(); 
var a=<s:property value="message"/>;
 if(a=="1"){
   alert("保存成功！");
 }else if(a=="2"){
   alert("相同编码资产已经存在！");
 }
var c="<s:property value="assetstype"/>";
if(c!=""){
document.getElementById('assetstype').selectedIndex=c;
}

}

function check(){
var codeId=document.getElementById("codeId").value;
var type=document.getElementById("assetstype").value;
var inDate=document.getElementById("inDate").value;

if(codeId==""){
alert("请填写资产编号！");
}else if(type==-1){
alert("请选择资产类别！");
}else if(inDate==""){
alert("请填写入库日期！");
}else if(CheckDate(inDate)){
alert("入库日期格式为YYYY-MM-DD");
}else{
document.form1.action="../wapassets/instoresave.action";
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

资产编号<br/>
<input type="text" id="codeId" name="codeId" style='width:100px;'><br/>
资产类别<br/>
<select id="assetstype" name="assetstype" style='width:100px;'>
<option value="-1" selected>-请选择-</option>
<s:iterator value="typelist" var="assetsType">
<option value="<s:property value="id" />"><s:property value="name"/></option>
</s:iterator>
</select><br/>
入库日期<br/>
<input type="text" id="inDate" name="inDate" style='width:100px;' value="<s:property value="inDate"/>">格式YYYY-MM-DD<br/>
<a href="#" onclick="check();"><img src="../images/wap/bt_bc.jpg" width="48" height="21"/></a>
<a href="functionlist.action"><img src="../images/wap/bt_fh.jpg" width="48" height="21"/></a>
</div>
</form>
</div>

    </td>
  </tr>
</table>
</body>

</html>