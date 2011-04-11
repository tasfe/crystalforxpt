<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产新增</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <base target="_self">
        <script>
function init(){
var a="<s:property value="flag"/>";
if(a=="1"){
alert("更新成功！");
location.href="list.action";
}
}
function sub(){
if(confirm("您确定要提交吗?"))
{
form1.action="update.action";
document.getElementById("submit1").disabled=true;
form1.submit();
}
}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();">
<s:form action="" method="post" theme="simple" name="form1">
 <table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../images/main20100521_58.gif" class="alllisttitle">资产公共信息属性配置</td>
 </tr>
 <tr>
   <td height="22" bgcolor="#FFFFFF"><table width="98%" align="center" cellpadding="0" cellspacing="0">
     <tr>
       <td valign="top"><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·资产信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=0 class="zcxx_tab3">
                 <tr>
                   <input type="hidden" name="codeId00" id="codeId00"> 
                   <input type="hidden" name="codeId10" id="codeId10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="codeId20" name="codeId20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="codeId30" id="codeId30">
                   <td width="10%" height="30" align="center"><select id="codeId40" name="codeId40" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   <td width="5%" rowspan="16" align="center"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="500" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="name00" id="name00">
                   <input type="hidden" name="name10" id="name10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="name20" name="name20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="name30" id="name30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="name40" id="name40" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   <td width="5%" rowspan="16" align="center"  ><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="500" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="model00" id="model00">
                   <input type="hidden" name="model10" id="model10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="model20" id="model20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="model30" id="model30">
                   <td width="10%" height="30" align="center"><select name="model40" id="model40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   </tr>
                 <tr>
                   <input type="hidden" name="assetsType00" id="assetsType00">
                   <input type="hidden" name="assetsType10" id="assetsType10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="assetsType20" name="assetsType20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="assetsType30" id="assetsType30">
                   <td width="10%" height="30" align="center"><select name="assetsType40" id="assetsType40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>   
                   </select></td>
                   <input type="hidden" name="itsmType00" id="itsmType00">
                   <input type="hidden" name="itsmType10" id="itsmType10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="itsmType20" name="itsmType20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="itsmType30" id="itsmType30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="itsmType40" id="itsmType40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>   
                   </select></td>
                   
                   <input type="hidden" name="assetsState00" id="assetsState00">
                   <input type="hidden" name="assetsState10" id="assetsState10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="assetsState20" name="assetsState20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="assetsState30" id="assetsState30">
                   
                   <td width="10%" height="30" align="center"><select name="assetsState40" id="assetsState40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   
                   </tr>
                 <tr>
                   <input type="hidden" name="qualityTime00" id="qualityTime00">
                   <input type="hidden" name="qualityTime10" id="qualityTime10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="qualityTime20" name="qualityTime20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="qualityTime30" id="qualityTime30">
                   <td width="10%" height="30" align="center"><select id="qualityTime40" name="qualityTime40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="price00" id="price00">
                   <input type="hidden" name="price10" id="price10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="price20" name="price20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="price30" id="price30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="price40" id="price40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="buyDate00" id="buyDate00">
                   <input type="hidden" name="buyDate10" id="buyDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="buyDate20" name="buyDate20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="buyDate30" id="buyDate30">
                   <td width="10%" height="30" align="center"><select name="buyDate40" id="buyDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                   
                   
                   </tr>
                 <tr>
                   <input type="hidden" name="exitfacotryDate00" id="exitfacotryDate00">
                   <input type="hidden" name="exitfacotryDate10" id="exitfacotryDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="exitfacotryDate20" name="exitfacotryDate20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="exitfacotryDate30" id="exitfacotryDate30">
                   <td width="10%" height="30" align="center"><select name="exitfacotryDate40" id="exitfacotryDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                   <input type="hidden" name="inDate00" id="inDate00">
                   <input type="hidden" name="inDate10" id="inDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="inDate20" name="inDate20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="inDate30" id="inDate30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="inDate40" id="inDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                   <input type="hidden" name="location00" id="location00">
                   <input type="hidden" name="location10" id="location10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="location20" name="location20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="location30" id="location30">
                   <td width="10%" height="30" align="center"><select name="location40" id="location40" style="width:80%;height=18;">
                    <option value="4">下拉列选</option>
                   </select></td>
                  
                 </tr>
                 <tr>
                    <input type="hidden" name="buildlocation00" id="buildlocation00">
                    <input type="hidden" name="buildlocation10" id="buildlocation10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="buildlocation20" name="buildlocation20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="buildlocation30" id="buildlocation30">
                   <td width="10%" height="30" align="center"><select name="buildlocation40" id="buildlocation40" style="width:80%;height=18;">
                    <option value="4">下拉列选</option>
                   </select></td>
                   <input type="hidden" name="system00" id="system00">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="system10" id="system10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="system20" name="system20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="system30" id="system30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="system40" id="system40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" id="ip00" name="ip00">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="ip10" name="ip10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="ip20" id="ip20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="ip30" id="ip30">
                   <td width="10%" height="30" align="center"><select name="ip40" id="ip40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   
                   </tr>
                 <tr>
                   <input type="hidden" name="mac00" id="mac00">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="mac10" id="mac10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="mac20" name="mac20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="mac30" id="mac30">
                   <td width="10%" height="30" align="center"><select name="mac40" id="mac40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="devicename00" id="devicename00">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="devicename10" id="sn10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="devicename20" name="devicename20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="devicename30" id="devicename30">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="devicename40" id="devicename40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="remark100" id="remark100">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark110" name="remark110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="remark120" name="remark120" value="" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark130" id="remark130">
                   <td width="10%" height="30" align="center"><select name="remark140" id="remark140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                 </tr>
                 <tr>
                   <input type="hidden" name="remark200" id="remark200">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark210" name="remark210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark220" id="remark220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark230" id="remark230">
                   <td width="10%" height="30" align="center"><select name="remark240" id="remark240" style="width:80%;height=18;"> 
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark300" id="remark300">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark310" name="remark310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark320" id="remark320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark330" id="remark330">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark340" id="remark340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark400" id="remark400">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark410" name="remark410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="remark420" name="remark420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark430" id="remark430">
                   <td width="10%" height="30" align="center"><select name="remark440" id="remark440" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option> 
                   </select></td>
                   
                 </tr>
                 <tr>
                   <input type="hidden" name="remark500" id="remark500">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark510" name="remark510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark520" id="remark520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark530" id="remark530">
                   <td width="10%" height="30" align="center"><select name="remark540" id="remark540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark600" id="remark600">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark610" name="remark610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark620" id="remark620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark630" id="remark630">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark640" id="remark640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark700" id="remark700">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark710" name="remark710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" id="remark720" name="remark720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark730" id="remark730">
                   <td width="10%" height="30" align="center"><select name="remark740" id="remark740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                 </tr>
                 <tr>
                   <input type="hidden" name="remark800" id="remark800">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark810" name="remark810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark820" id="remark820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark830" id="remark830">
                   <td width="10%" height="30" align="center"><select name="remark840" id="remark840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark900" id="remark900">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark910" id="remark910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark920" id="remark920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark930" id="remark930">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark940" id="remark940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark1000" id="remark1000">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" id="remark1010" name="remark1010"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1020" id="remark1020" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1030" id="remark1030">
                   <td width="10%" height="30" align="center"><select name="remark1040" id="remark1040" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 
                 
                  <tr>
                   <input type="hidden" name="remark1100" id="remark1100">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1120" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1130" value="remark11">
                   <td width="10%" height="30" align="center"><select name="remark1140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark1200" id="remark1200">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1230" value="remark12">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1240" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark1300" id="remark1300">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1330" value="remark13">
                   <td width="10%" height="30" align="center"><select name="remark1340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1400" id="remark1400">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1430" value="remark14">
                   <td width="10%" height="30" align="center"><select name="remark1440" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark1500" id="remark1500">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1530" value="remark15">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark1600" id="remark1600">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1630" value="remark16">
                   <td width="10%" height="30" align="center"><select name="remark1640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1700" id="remark1700">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1730" value="remark17">
                   <td width="10%" height="30" align="center"><select name="remark1740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark1800" id="remark1800">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1830" value="remark18">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark1900" id="remark1900">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1930" value="remark19">
                   <td width="10%" height="30" align="center"><select name="remark1940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark2000" id="remark2000">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2010"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2020" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2030" value="remark20">
                   <td width="10%" height="30" align="center"><select name="remark2040" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark2100" id="remark2100">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2120" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2130" value="remark21">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark2200" id="remark2200">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2230" value="remark22">
                   <td width="10%" height="30" align="center"><select name="remark2240" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark2300" id="remark2300">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2330" value="remark23">
                   <td width="10%" height="30" align="center"><select name="remark2340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark2400" id="remark2400">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2430" value="remark24">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2440" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark2500" id="remark2500">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2530" value="remark25">
                   <td width="10%" height="30" align="center"><select name="remark2540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark2600" id="remark2600">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2630" value="remark26">
                   <td width="10%" height="30" align="center"><select name="remark2640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark2700" id="remark2700">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2730" value="remark27">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <input type="hidden" name="remark2800" id="remark2800">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2830" value="remark28">
                   <td width="10%" height="30" align="center"><select name="remark2840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark2900" id="remark2900">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2930" value="remark29">
                   <td width="10%" height="30" align="center"><select name="remark2940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark3000" id="remark3000">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark3010"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark3020" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark3030" value="remark30">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark3040" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" style="width:100%;height=18;" value="资产附件" readonly></td>
                   <td width="10%" height="30" align="center"><select name="select" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                   </select></td>
                 </tr>
                 
                 
               </table></td>
             </tr>
           </table></td>
           </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·供应商制造商信息·</td>
           <td width="75%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=0 class="zcxx_tab3">
                 <tr>
                   <input type="hidden" name="assetsProducerBySupportId00" id="assetsProducerBySupportId00">
                   <input type="hidden" name="assetsProducerBySupportId10" id="assetsProducerBySupportId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="12%" height="30" align="left" ><input type="text" name="assetsProducerBySupportId20" id="assetsProducerBySupportId20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="assetsProducerBySupportId30" id="assetsProducerBySupportId30">
                   <td width="10%" height="30" align="center"><select name="assetsProducerBySupportId40" id="assetsProducerBySupportId40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   <td width="24%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="assetsProducerByProduceId00" id="assetsProducerByProduceId00">
                   <input type="hidden" name="assetsProducerByProduceId10" id="assetsProducerByProduceId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="12%" height="30" align="left" ><input type="text" name="assetsProducerByProduceId20" id="assetsProducerByProduceId20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="assetsProducerByProduceId30" id="assetsProducerByProduceId30">
                   <td width="10%" height="30" align="center"><select name="assetsProducerByProduceId40" id="assetsProducerByProduceId40" style="width:80%;height=18;">
                     <option value="4" selected="true">下拉列选</option>   
                   </select></td>
                   <td width="3%" align="center">&nbsp;</td>
                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·责任信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=0 class="zcxx_tab3">
                 <tr>
                   <input type="hidden" name="usersByChargeId00" id="usersByChargeId00">
                   <input type="hidden" name="usersByChargeId10" id="usersByChargeId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByChargeId20" id="usersByChargeId20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="usersByChargeId30" id="usersByChargeId30">
                   <td  width="12%" height="30" align="center"><select name="usersByChargeId40" id="usersByChargeId40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   
                   <input type="hidden" name="usersByChargeIddepartment00" id="usersByChargeIddepartment00">
                   <input type="hidden" name="usersByChargeIddepartment10" id="usersByChargeIddepartment10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByChargeIddepartment20" id="usersByChargeIddepartment20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="usersByChargeIddepartment30" id="usersByChargeIddepartment30">
                   <td  width="12%" height="30" align="center"><select name="usersByChargeIddepartment40" id="usersByChargeIddepartment40" style="width:80%;height=18;">
                   <option value="1">输入框</option>
                   </select></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <input type="hidden" name="usersByUsersId00" id="usersByUsersId00">
                   <input type="hidden" name="usersByUsersId10" id="usersByUsersId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByUsersId20" id="usersByUsersId20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="usersByUsersId30" id="usersByUsersId30">
                   <td  width="12%" height="30" align="center"><select name="usersByUsersId40" id="usersByUsersId40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   
                   <input type="hidden" name="usersByUsersIddepartment00" id="usersByUsersIddepartment00">
                   <input type="hidden" name="usersByUsersIddepartment10" id="usersByUsersIddepartment10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByUsersIddepartment20" id="usersByUsersIddepartment20" style="width:100%;height=18;" value="" readonly></td>
                   <input type="hidden" name="usersByUsersIddepartment30" id="usersByUsersIddepartment30">
                   <td  width="12%" height="30" align="center"><select name="usersByUsersIddepartment40" id="usersByUsersIddepartment40" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   
                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
   </table></td></tr>
 </table>
 <div>
 <table width="98%">
  <tr>
   <td width="100%" height="30" align="center">
    <input type="button" value=" 保存 " id="submit1" name="submit1" class="mmBtn" onClick="sub();">
   </td>
  </tr>
 </table>
 </s:form>
</body>
<script>
<%List list=(List)ActionContext.getContext().getSession().get("assetsConfigList");
for(int i=0;i<list.size();i++){
AssetsConfig assetsConfig=(AssetsConfig)list.get(i);%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>0<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getId() %>";
<%if(assetsConfig.getIschoose()==1){%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>1<%=assetsConfig.getFlag() %>").checked="true";
<%}%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>2<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigName() %>";
document.getElementById("<%=assetsConfig.getConfigColumnName()%>3<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigColumnName()%>";
document.getElementById("<%=assetsConfig.getConfigColumnName()%>4<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigStats() %>";
<%} %>
</script>
</html>

