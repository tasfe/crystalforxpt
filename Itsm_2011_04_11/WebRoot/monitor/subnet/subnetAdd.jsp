<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	<s:head />
		<title>新增分区</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='${pageContext.request.contextPath }/js/pub.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/util.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/MonitorDeviceTypeDAO.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/MonitorDeviceDAO.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/MonitorSubnetTypeDAO.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/MonitorSubnetDAO.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/engine.js'></script>
<script language="JavaScript">
var boolName=true;
function init(){
   	MonitorSubnetTypeDAO.findAll(callbackSubnetType);
}

function callbackSubnetType(data){  //显示出分区类型
   	dwr.util.removeAllOptions("subnetType");
   	dwr.util.addOptions("subnetType",data,"code","name");   
}
function doSubmit(){
	var subnetName=document.getElementById("subnetName");
	if(subnetName.value==""){
		alert("分区名称必须填写 ！");
		subnetName.focus();
		return;
	}
	var subnetType=document.getElementById("subnetType");
	if(subnetType.value==""){
		alert("分区类型必须选择 ！");
		subnetType.focus();
		return;
	}
	if(checkSubnetName()==true){
		document.all.subnetSave.target="mainFrame";
		document.all.subnetSave.submit();
	}
	else{
		alert("分区名称已存在，请重新填写 ！");
	 	subnetName.focus();
	}
}

function doNext(){
	var subnetName=document.getElementById("subnetName");
	if(subnetName.value==""){
		alert("分区名称必须填写 ！");
		subnetName.focus();
		return;
	}
	var subnetType=document.getElementById("subnetType");
	if(subnetType.value==""){
		alert("分区类型必须选择 ！");
		subnetType.focus();
		return;
	}
	if(checkSubnetName()==true){
		document.subnet.action="/monitorSubnet/subnetSaveNext.action";
		document.subnet.submit();
	}
	else{
		alert("分区名称已存在，请重新填写 ！");
	 	subnetName.focus();
	}
}

function checkName(){
	 if(checkSubnetName()==true){
	 	alert("分区名称通过验证 ！");
	 }
	 else{
		alert("分区名称已存在，请重新填写 ！");
	 	subnetName.focus();
	}
	 
}
function checkSubnetName(){
	 var subnetName=document.getElementById("subnetName");
	 dwr.engine.setAsync(false);//DWR设为同步执行
	 MonitorSubnetDAO.findByName(subnetName.value,callbackSubnet); 
	 dwr.engine.setAsync(true);//恢复异步执行
	 return	boolName;
}
function callbackSubnet(data){  
    var list = eval(data); 
	if(list.length > 0) {
		boolName=false;
	}
	else
		boolName=true;
} 
</script>
</head>

<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">

<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="${pageContext.request.contextPath }/images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">新增分区:</td>
  </tr>
</table>
<s:form name="subnet" action="subnetSave" method="save" theme="simple"> 
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="98%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
    <tr>
      <td height="22" colspan="10" nowrap background="${pageContext.request.contextPath }/images/main20100521_58.gif" class="alllisttitle" style="padding-left:10px;">新增分区</td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">分区名称:</td>
      <td width="29%" bgcolor="#FFFFFF"><input type="text" id="subnetName" name="monitorSubnet.name" style="width: 50%">
      <input type="button" onClick="checkName()" class="mmBtn" value="验证是否存在"/>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">上层分区:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      <s:select id="parentSubnet" name="monitorSubnet.parentSubnet.id" list="parentSubnetList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--"></s:select>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">类型:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      	<select id="subnetType" name="monitorSubnet.monitorSubnetType.code"></select>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">扫描:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      	<input type="checkbox" id="subnetScan" name="monitorSubnet.scan" value="1" checked="checked" size="30"> 
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">拓扑启动:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      	<input type="checkbox" id="subnetStart" name="monitorSubnet.start" value="1" checked="checked" size="30"> 
      </td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">备注:</td>
      <td width="89%" colspan="9" bgcolor="#FFFFFF"><input type="text" name="monitorSubnet.note1" style="width: 90%"></td>
    </tr>
  </table>
  <table cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
      <td width="50%" height="30" align=center nowrap style="padding-top: 6px; padding-bottom: 1px">
	      <input name="button" type="button" class="mmBtn" onClick="doSubmit()" value="保存">
	      <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="重置">   
	      <input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value="取消">    
      </td>
    </tr>
  </table>
</div>
</s:form>
</body>
</html>