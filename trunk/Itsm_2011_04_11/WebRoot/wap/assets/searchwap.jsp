<?xml version="1.0" encoding="utf-8"?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>    
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
<script type="text/javascript">
function init(){
document.getElementById('codeId').focus(); 
}
function res(){
document.getElementById("codeId").value="";
}
function sub(){
document.form1.submit();
}
function change(a,b){
if(a==""){
alert("请先查询确认，在变更资产！");
}else{
if(b==""){
alert("该资产没有正式入库！");
}else{
document.form1.action="../wapassets/change.action?code="+a;
document.form1.submit();
}
}
}

</script>
<title>IT运维-搜索</title>
</head>

<body bgcolor="#E4E5E7"  onload="init();">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="../images/wap/top_bg.jpg"><img src="../images/wap/logo.jpg" width="152" height="45" /></td>
  </tr>
  <tr>
    <td height="266" valign="top" bgcolor="#E4E5E7"><table width="240" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>    <div>
<form id="form1" name="form1" action="../wapassets/search.action" method="post"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td style="font-size:12px;">资产编号：</td>
    <td><input type="text" id="codeId" name="codeId" style="width:120px;height=18;" value="<s:property value="base.codeId"/>" ></td>
  </tr>
</table>

<div align="center"><br />
<a href="#" onclick="sub();"><img src="../images/wap/bt_cx.jpg" width="48" height="21" onclick="sub();"/></a>
<a href="#" onclick="res();"><img src="../images/wap/bt_cz.jpg" width="48" height="21"/></a>
<a href="#" onclick="change('<s:property value="base.code"/>','<s:property value="base.valueUnit"/>');"><img src="../images/wap/bt_bg.jpg" width="48" height="21"/></a>
<a href="functionlist.action"><img src="../images/wap/bt_fh.jpg" width="48" height="21"/></a>
</div>
</form>
</div>
<s:if test="message==1">
<div align="left" style="font-size:12px; margin-left:10px; color:#317CA8; margin-bottom:10px;">
资产编号：<s:property value="base.codeId"/><br/>
资产型号：<s:property value="base.model"/><br/>
资产类别：<s:property value="base.assetsType.name"/><br/>
资产状态：<s:property value="base.assetsState.name"/><br/>
保修年限(月)：<s:property value="base.qualityTime"/><br/>
采购日期：<s:date name='base.buyDate' format='yyyy-MM-dd'/><br/>
出厂日期：<s:date name='base.exitfacotryDate' format='yyyy-MM-dd'/><br/>
入库日期：<s:date name='base.inDate' format='yyyy-MM-dd'/><br/>
资产单价：<s:property value="base.price"/><br/>
存放位置：<s:property value="base.location.name"/><br/>
供应商名称：<s:property value="base.assetsProducerBySupportId.name"/><br/>
制造商名称：<s:property value="base.assetsProducerByProduceId.name"/><br/>
使用人姓名：<s:property value="base.usersByUsersId.truename"/><br/>
负责人姓名：<s:property value="base.usersByChargeId.truename"/><br/>
所属部门：<s:property value="base.department.name"/><br/>
</div>
<div>
</div>
</s:if>
</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>

</html>