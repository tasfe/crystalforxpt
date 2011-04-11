<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
        <script type='text/javascript' src='../dwr/interface/AssetsProducerDAO.js'></script>
        <script type='text/javascript' src='../dwr/interface/AssetsStateDAO.js'></script>
        <script language="JavaScript"  src="../js/FusionCharts.js"></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
function init(){ //取出类别
AssetsStateDAO.findAll(callstate);
AssetsProducerDAO.findByIntType(1,callbackproducer);
AssetsProducerDAO.findByIntType(2,callbacksupporter);
}

function callstate(data){
dwr.util.removeAllOptions("state");
dwr.util.addOptions("state",{'-1':'--请选择--'});
dwr.util.addOptions("state",data,"id","name");
var a = "<s:property value="assets.assetsState.id"/>";
if (typeof(a) != "undefined"){   
dwr.util.setValue("state",a);  
}
}
function callbacksupporter(data){  //显示出类别
 dwr.util.removeAllOptions("supporter");
   dwr.util.addOptions("supporter",{'-1':'--请选择--'});
   dwr.util.addOptions("supporter",data,"id","name");  
     var a = "<s:property value="assets.assetsProducerBySupportId.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("supporter",a);  
   } 
}
function callbackproducer(data){  //显示出类别
 dwr.util.removeAllOptions("producer");
   dwr.util.addOptions("producer",{'-1':'--请选择--'});
   dwr.util.addOptions("producer",data,"id","name");  
     var a = "<s:property value="assets.assetsProducerByProduceId.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("producer",a);  
   } 
}
function notshow(){
document.getElementById('Layer1').style.visibility='hidden'
document.getElementById('Layer2').style.visibility='hidden'
}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="init();" onMouseDown="notshow();">
<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<s:if test="cartogramInfo!=null">
 <td width="50%" align="center">
 <div id="chartdiv" align="center">故障类型统计图.如果无法正常显示，需要安装FLASH插件!</div>
 <script type="text/javascript">
 var chart = new FusionCharts("../Charts/Pie3D.swf", "ChartId", "380", "200", "0", "0");
 chart.setDataXML("<s:property value="cartogramInfo.xmlString" escape="false" />");	   
 chart.render("chartdiv");
 </script>
 </td>
</s:if>
<s:if test="cartogramInfoprice!=null">
 <td width="50%" align="center">
 <div id="chartdiv1" align="center">故障类型统计图.如果无法正常显示，需要安装FLASH插件!</div>
 <script type="text/javascript">
 var chart = new FusionCharts("../Charts/Pie3D.swf", "ChartId", "380", "200", "0", "0");
 chart.setDataXML("<s:property value="cartogramInfoprice.xmlString" escape="false" />");	   
 chart.render("chartdiv1");
 </script>
 </td>
</s:if>
</tr>
</table>

<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
   <td height="30" align="right"></td>
</tr>
</table>
<table align="center" width="99%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
  <s:form action="/assets/cartogramqurey.action" method='post' theme="simple">
    <tr>
     <td height="10" align="right" bgcolor="#deebf1">资产类别：</td>
     <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assets.assetsType.id" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="assets.assetsType.name" onClick="document.getElementById('Layer1').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer1"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
     </td>
     <td width="10%" align="right" bgcolor="#deebf1">资产状态：</td>
     <td width="10%" align="center" bgcolor="#FFFFFF">
     <select id="state" name="assets.assetsState.id" style="width:100%"></select></td>
     <td width="10%"  align="right" bgcolor="#deebf1">所属部门：</td>
     <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.department.id" id="assets.department.id" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="assets.department.name" onClick="document.getElementById('Layer2').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer2"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
     </td>
    </tr>
    <tr>
     <td width="10%" align="right" bgcolor="#deebf1">供应商： </td>
     <td width="10%" align="center" bgcolor="#FFFFFF"><select id="producer" name="assets.assetsProducerByProduceId.id" style="width:100%">
     </select></td>
     <td width="10%" align="right" bgcolor="#deebf1">制造商：</td>
     <td width="10%" align="center" bgcolor="#FFFFFF"><select id="supporter" name="assets.assetsProducerBySupportId.id" style="width:100%">
     </select></td>
     <td colspan="2" bgcolor="#FFFFFF"></td>
    </tr>
    <tr>
      <td colspan="6" align="center" bgcolor="#FFFFFF"><input type="submit"  class="mmBtn" value=" 统计 ">
        <input type="button" value=" 返回 " onClick="window.location='statistic.action'" class="mmBtn"></td>
    </tr>
  </s:form>
</table>
<table width="96%" border="0">
  <tr>
    <td height="6"></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
<tr bgcolor="#FFFFFF">
<td width="33%" height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle"> 资产数量 </td>
<td width="33%" align="center" background="../images/main20100521_58.gif" class="alllisttitle"> 资产总数 </td>
<td width="33%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">资产百分比</td>
</tr>
<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
<td height="22" align="center" class="zczb_qua"><s:property value ="cartogramInfo.totle" /></td>
<td align="center" class="zczb_qua"><s:property value ="cartogramInfo.all" /></td>
<td colspan="2" align="center" class="zczb_qua"><s:property value ="cartogramInfo.percent" /></td>
</tr>
</table>
<br/>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
<tr bgcolor="#FFFFFF">
<td width="33%" height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle"> 资产价值 </td>
<td width="33%" align="center" background="../images/main20100521_58.gif" class="alllisttitle"> 资产总价值 </td>
<td width="33%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">资产价值百分比</td>
</tr>
<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
<td height="22" align="center" class="zczb_qua"><s:property value ="cartogramInfoprice.totle" /></td>
<td align="center" class="zczb_qua"><s:property value ="cartogramInfoprice.all" /></td>
<td colspan="2" align="center" class="zczb_qua"><s:property value ="cartogramInfoprice.percent" /></td>
</tr>
</table>
</body>
</html>
