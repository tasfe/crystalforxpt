<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
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
alert("配置成功！");
location.href="list.action";
}
}

function sub()
{
if(confirm("您确定要提交吗?"))
{
form1.action="savebase.action";
form1.submit();
document.getElementById("submit1").disabled=true;
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
                   <input type="hidden" name="codeId10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="codeId20" style="width:100%;height=18;" value="资产编号" readonly></td>
                   <input type="hidden" name="codeId30" value="codeId">
                   <td width="10%" height="30" align="center"><select name="codeId40" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   <td width="5%" rowspan="16" align="center"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="500" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="name10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="name20" style="width:100%;height=18;" value="资产名称" readonly></td>
                   <input type="hidden" name="name30" value="name">
                   <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="name40" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   <td width="5%" rowspan="16" align="center"  ><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="500" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="model10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="model20" style="width:100%;height=18;" value="资产型号" readonly></td>
                   <input type="hidden" name="model30" value="model">
                   <td width="10%" height="30" align="center"><select name="model40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   </tr>
                 <tr>
                   <input type="hidden" name="assetsType10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="assetsType20" style="width:100%;height=18;" value="资产类别" readonly></td>
                   <input type="hidden" name="assetsType30" value="assetsType">
                   <td width="10%" height="30" align="center"><select name="assetsType40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>   
                   </select></td>
                   <input type="hidden" name="itsmType10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="itsmType20" style="width:100%;height=18;" value="运维类别" readonly></td>
                   <input type="hidden" name="itsmType30" value="itsmType">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="itsmType40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>   
                   </select></td>
                   <input type="hidden" name="assetsState10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="assetsState20" style="width:100%;height=18;" value="资产状态" readonly></td>
                   <input type="hidden" name="assetsState30" value="assetsState">
                  
                   <td width="10%" height="30" align="center"><select name="assetsState40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   </tr>
                 <tr>
                   <input type="hidden" name="qualityTime10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="qualityTime20" style="width:100%;height=18;" value="保修年限(月)" readonly></td>
                   <input type="hidden" name="qualityTime30" value="qualityTime">
                   <td width="10%" height="30" align="center"><select name="qualityTime40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="price10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="price20" style="width:100%;height=18;" value="资产单价" readonly></td>
                   <input type="hidden" name="price30" value="price">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="price40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <input type="hidden" name="buyDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="buyDate20" style="width:100%;height=18;" value="采购日期" readonly></td>
                   <input type="hidden" name="buyDate30" value="buyDate">
                   <td width="10%" height="30" align="center"><select name="buyDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                 </tr>
                 <tr>
                   <input type="hidden" name="exitfacotryDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="exitfacotryDate20" style="width:100%;height=18;" value="出厂日期" readonly></td>
                   <input type="hidden" name="exitfacotryDate30" value="exitfacotryDate">
                   <td width="10%" height="30" align="center"><select name="exitfacotryDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                   <input type="hidden" name="inDate10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="inDate20" style="width:100%;height=18;" value="入库日期" readonly></td>
                   <input type="hidden" name="inDate30" value="inDate">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="inDate40" style="width:80%;height=18;">
                    <option value="2" selected="true">日期输入框</option>   
                   </select></td>
                   <input type="hidden" name="location10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="location20" style="width:100%;height=18;" value="地理区域" readonly></td>
                   <input type="hidden" name="location30" value="location">
                   <td width="10%" height="30" align="center"><select name="location40" style="width:80%;height=18;">
                    <option value="4">下拉列选</option>
                   </select></td>
                  
                 <tr>
                    <input type="hidden" name="buildlocation10" value="on">
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="buildlocation20" style="width:100%;height=18;" value="存放位置" readonly></td>
                   <input type="hidden" name="buildlocation30" value="buildlocation">
                   <td width="10%" height="30" align="center"><select name="buildlocation40" style="width:80%;height=18;">
                    <option value="4">下拉列选</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  name="system10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="system20" style="width:100%;height=18;" value="操作系统" readonly></td>
                   <input type="hidden" name="system30" value="system">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="system40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox"  name="ip10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="ip20" style="width:100%;height=18;" value="IP" readonly></td>
                   <input type="hidden" name="ip30" value="ip">
                   <td width="10%" height="30" align="center"><select name="ip40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="mac10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="mac20" style="width:100%;height=18;" value="MAC" readonly></td>
                   <input type="hidden" name="mac30" value="mac">
                   <td width="10%" height="30" align="center"><select name="mac40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="devicename10"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="devicename20" style="width:100%;height=18;" value="设备/计算机名称" readonly></td>
                   <input type="hidden" name="devicename30" value="devicename">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="devicename40" style="width:80%;height=18;">
                    <option value="1" selected="true">输入框</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark120" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark130" value="remark1">
                   <td width="10%" height="30" align="center"><select name="remark140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                   </tr>
                 <tr>
                    <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark230" value="remark2">
                   <td width="10%" height="30" align="center"><select name="remark240" style="width:80%;height=18;"> 
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark330" value="remark3">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark430" value="remark4">
                   <td width="10%" height="30" align="center"><select name="remark440" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option> 
                   </select></td>
                   </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark530" value="remark5">
                   <td width="10%" height="30" align="center"><select name="remark540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark630" value="remark6">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark730" value="remark7">
                   <td width="10%" height="30" align="center"><select name="remark740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark830" value="remark8">
                   <td width="10%" height="30" align="center"><select name="remark840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark930" value="remark9">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1010"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1020" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1030" value="remark10">
                   <td width="10%" height="30" align="center"><select name="remark1040" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
              <!--____________________________________________________________________________________________________________________________________--> 
                 
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1120" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1130" value="remark11">
                   <td width="10%" height="30" align="center"><select name="remark1140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1230" value="remark12">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1240" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1330" value="remark13">
                   <td width="10%" height="30" align="center"><select name="remark1340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1430" value="remark14">
                   <td width="10%" height="30" align="center"><select name="remark1440" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1530" value="remark15">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1630" value="remark16">
                   <td width="10%" height="30" align="center"><select name="remark1640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1730" value="remark17">
                   <td width="10%" height="30" align="center"><select name="remark1740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1830" value="remark18">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark1840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1930" value="remark19">
                   <td width="10%" height="30" align="center"><select name="remark1940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2010"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2020" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2030" value="remark20">
                   <td width="10%" height="30" align="center"><select name="remark2040" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2110"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2120" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2130" value="remark21">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2140" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2210"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2220" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2230" value="remark22">
                   <td width="10%" height="30" align="center"><select name="remark2240" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2310"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2320" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2330" value="remark23">
                   <td width="10%" height="30" align="center"><select name="remark2340" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2410"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2420" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2430" value="remark24">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2440" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2510"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2520" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2530" value="remark25">
                   <td width="10%" height="30" align="center"><select name="remark2540" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2610"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2620" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2630" value="remark26">
                   <td width="10%" height="30" align="center"><select name="remark2640" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2710"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2720" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2730" value="remark27">
                    <td width="1%"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"><select name="remark2740" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2810"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2820" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2830" value="remark28">
                   <td width="10%" height="30" align="center"><select name="remark2840" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2910"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2920" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2930" value="remark29">
                   <td width="10%" height="30" align="center"><select name="remark2940" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
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
                 
             <!--____________________________________________________________________________________________________________________________________--> 
                 
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
                   <input type="hidden" name="assetsProducerBySupportId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="12%" height="30" align="left" ><input type="text" name="assetsProducerBySupportId20" style="width:100%;height=18;" value="供应商" readonly></td>
                   <input type="hidden" name="assetsProducerBySupportId30" value="assetsProducerBySupportId">
                   <td width="10%" height="30" align="center"><select name="assetsProducerBySupportId40" style="width:80%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   <td width="24%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="assetsProducerByProduceId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="12%" height="30" align="left" ><input type="text" name="assetsProducerByProduceId20" style="width:100%;height=18;" value="制造商" readonly></td>
                   <input type="hidden" name="assetsProducerByProduceId30" value="assetsProducerByProduceId">
                   <td width="10%" height="30" align="center"><select name="assetsProducerByProduceId40" style="width:80%;height=18;">
                     <option value="4" selected="true">下拉列选</option>   
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
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·责任信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=0 class="zcxx_tab3">
                 <tr>
                   <input type="hidden" name="usersByChargeId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByChargeId20" style="width:100%;height=18;" value="责任人" readonly></td>
                   <input type="hidden" name="usersByChargeId30" value="usersByChargeId">
                   <td  width="12%" height="30" align="center"><select name="usersByChargeId40" style="width:85%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <input type="hidden" name="usersByChargeIddepartment10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByChargeIddepartment20" style="width:100%;height=18;" value="责任部门" readonly></td>
                   <input type="hidden" name="usersByChargeIddepartment30" value="usersByChargeIddepartment">
                   <td  width="12%" height="30" align="center"><select name="usersByChargeIddepartment40" style="width:85%;height=18;">
                    <option value="1">输入框</option>
                   </select></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                    <input type="hidden" name="usersByUsersId10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByUsersId20" style="width:100%;height=18;" value="使用人" readonly></td>
                   <input type="hidden" name="usersByUsersId30" value="usersByUsersId">
                   <td  width="12%" height="30" align="center"><select name="usersByUsersId40" style="width:85%;height=18;">
                    <option value="4" selected="true">下拉列选</option>
                   </select></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <input type="hidden" name="usersByUsersIddepartment10" value="on">
                   <td width="1%" height="30" align="right" ><input type="checkbox"  checked="true" disabled="true"></td>
                   <td width="10%" height="30" align="left" ><input type="text" name="usersByUsersIddepartment20" style="width:100%;height=18;" value="使用部门" readonly></td>
                   <input type="hidden" name="usersByUsersIddepartment30" value="usersByUsersIddepartment">
                   <td  width="12%" height="30" align="center"><select name="usersByUsersIddepartment40" style="width:85%;height=18;">
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
</html>