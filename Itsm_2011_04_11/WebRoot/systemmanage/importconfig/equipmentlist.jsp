<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">批量导入配置:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="99%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
    <tr>
      <input type="hidden" name="RecState" value="">
      <td width=9% nowrap bgcolor="#EBF4F5" class="td-left">配置代码:</td>
      <td width="23%" bgcolor="#FFFFFF" class="td-detail" style="padding-right: 10px"><input name="ZiCDM" id="ZiCDM" value="" style="width: 100%">      </td>
      <td width=9% nowrap bgcolor="#EBF4F5" class="td-left">状态:</td>
      <td width="23%" bgcolor="#FFFFFF" class="td-detail" style="padding-right: 10px"><select name="Status" id="Status" style="width: 100%">
          <option value=""></option>
          <option value="NEW">资料修改中</option>
          <option value="YHS">已回收</option>
        </select>      </td>
      <td width=9% nowrap bgcolor="#EBF4F5" class="td-left">配置名称:</td>
      <td width="23%" bgcolor="#FFFFFF" class="td-detail" style="padding-right: 20px"><input name="SheBMC" id="SheBMC" value="" style="width: 100%">      </td>
      <td width="2%" bgcolor="#FFFFFF" class="td-detail"><input name="button" type="button" class="mmBtn" style="height: 20px" onClick="SeekOnClick('AddTime','Desc'); this.disabled=true;" value="搜索">      </td>
    </tr>
  </table>
  
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="99%" height="30" style="padding-top: 10px" onClick="document.getElementById('thelayer').style.display='none'"><img src="../../images/main20100521dot04.gif">&nbsp;<b>入库批次信息列表:</b>&nbsp;</td>
    </tr>
  </table>
  <table width="99%" border=0 align="center" cellpadding=4 cellspacing=1 bgcolor="#b5d6e6" class="datagrid">
    <tr>
      <td width=1% height="22" align="center" nowrap background="../../images/main20100521_58.gif"><font class="alllisttitle" style='cursor:hand' onclick="SeekOnClick('ZiCDM','DESC');">代码</font></td>
      <td width="20%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"><font style='cursor:hand' onclick="SeekOnClick('SheBMC','DESC');">配置名称</font></td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"><font style='cursor:hand' onclick="SeekOnClick('ZiCLB','DESC');">类别</font></td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"><font style='cursor:hand' onclick="SeekOnClick('SouSBM','DESC');">所属部门</font></td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"><font style='cursor:hand' onclick="SeekOnClick('ShiYR','DESC');">管理员</font></td>
      <td width=1% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">关系</td>
      <td width=1% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">详细</td>
    </tr>
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      <td height="22" nowrap>&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
      <td height="22" nowrap class="alllisttitle">&nbsp;</td>
    </tr>
  </table>
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="30" align="center"><span style="padding-top: 6px; padding-bottom: 1px">
        <input name="button2" type="button" class="mmBtn" onClick="history.go(-1)" value="返回">
      </span></td>
    </tr>
  </table>
</div>
<script language="JavaScript">
function SeekOnClick(Var1, Var2)
{	
	var Location = "../asst/?NowAction=assetssearch";
	Location += "&Status=" + document.getElementById("Status").value;
	Location += "&ZiCDM=" + document.getElementById("ZiCDM").value;
	Location += "&SheBMC=" + document.getElementById("SheBMC").value;
	Location += "&RecState=&RecName=&Title=&BC=1&Sort=";
	Location += Var1 + "&Desc=" + Var2;
	document.location.href = Location;
}
if (window.top.location.href.indexOf("itsm.htm")>0){
	var Url = window.location.href;
	Url = Url.replace(/\&/g,"|@|");
	Url = Url.replace(/\#/g,"|$|");
	Url = Url.replace(/\?/g,"|~|");
	window.top.themain.mainit.topit.currurl.location.replace("../home/?NowAction=CurrURL&CurrURL="+Url);
}
</script>
</body>
</html>
