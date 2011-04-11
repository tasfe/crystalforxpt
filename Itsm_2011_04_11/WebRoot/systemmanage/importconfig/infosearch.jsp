<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>配置库信息检索</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function Search()
{
	
	Url = "../asst/?NowAction=assetssearch";
	Url = Url + "&Editor=" + document.getElementById("Editor").value;	
	Url = Url + "&ZiCDM=" + document.getElementById("ZiCDM").value;	
	Url = Url + "&SheBMC=" + document.getElementById("SheBMC").value;	
	Url = Url + "&ZiCLB=" + document.getElementById("ZiCLB").value;	
	Url = Url + "&SouSBM=" + document.getElementById("SouSBM").value;	
	Url = Url + "&ShiYR=" + document.getElementById("ShiYR").value;	
	Url = Url + "&AddTime1=" + document.getElementById("AddTime1").value;	
	Url = Url + "&AddTime2=" + document.getElementById("AddTime2").value;	
	Url = Url + "&EndTime1=" + document.getElementById("EndTime1").value;	
	Url = Url + "&EndTime2=" + document.getElementById("EndTime2").value;	
	Url = Url + "&BeiZ=" + document.getElementById("BeiZ").value;	
	Url = Url + "&Status=" + document.getElementById("Status").value;	
	Url = Url + "&Other=" + document.getElementById("Other").value;	
	Url = Url + "&andor=" + document.getElementById("andor").value;	
	Url = Url + "&Location=" + document.getElementById("Location").value;	
	document.location.href = Url;
}
function Change(Value)
{
	if (Value=="0")
	{
		document.getElementById("EndTime1").value = "2000-01-01"
		document.getElementById("EndTime2").value = "2010-6-1"
	}
	if (Value=="1")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-6-8"
	}
	if (Value=="2")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-6-15"
	}
	if (Value=="3")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-7-1"
	}
	if (Value=="4")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-8-1"
	}
	if (Value=="5")
	{
		document.getElementById("Editor").value = "yangcen"
	}
	if (Value=="6")
	{
		document.getElementById("ShiYR").value = "yangcen"
	}
	if (Value=="7")
	{
		document.getElementById("AddTime1").value = "2010-01-01"
		document.getElementById("AddTime2").value = "2010-6-1"
	}
	if (Value=="8")
	{
		document.getElementById("AddTime1").value = "2010-6-01"
		document.getElementById("AddTime2").value = "2010-6-1"
	}
}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">配置库信息检索:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="98%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
    </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
    <tr>
      <td height="22" colspan="4" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="padding-left:10px;">资产配置检索</td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">创建人:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="Editor" style="width: 50%"></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">代码:</td>
      <td width="39%" bgcolor="#FFFFFF" ><input type="text" name="ZiCDM" style="width: 70%"></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">名称:</td>
      <td colspan="4" bgcolor="#FFFFFF"><input type="text" name="SheBMC" style="width: 40%"></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">所属部门:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="SouSBM_2" style="width: 70%; cursor: text" readonly value="" onClick="document.getElementById('Layer2').style.visibility='visible';this.value='';document.getElementById('SouSBM').value=''">
        &nbsp;<img src="../../img/check.jpg" width="18" height="18" align="absmiddle" style="cursor: hand" title="选择部门" onClick="document.getElementById('Layer2').style.visibility='visible'"> <br>
        <div id="Layer2" style="position:absolute; width:70%; height:20px; z-index:1; visibility: hidden; border: 0px">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
            <tr>
              <td><iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Dept_Mana&Par=SouSBM&Layer=Layer2" style="border: 1px solid #E5E9EE"></iframe></td>
            </tr>
          </table>
        </div></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">管理或责任人:</td>
      <td bgcolor="#FFFFFF" class="td-right" style="padding-left: 5px"><input type="text" name="ShiYR" style="width: 240px" onClick="document.getElementById('Layer').style.visibility='visible';window.User.location='../task/?NowAction=users&Key='+this.value" onKeyUp="document.getElementById('Layer').style.visibility='visible';window.User.location='../task/?NowAction=users&Key='+this.value">
          <br>
          <div id="Layer" style="position:absolute; width:240; height:78px; z-index:1; visibility: hidden; background-color: #FFFFFF; layer-background-color: #FFFFFF; padding-left: 0px">
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
              <tr>
                <td><iframe id="User" name="User" frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=users" style="border: 1px solid #E5E9EE"></iframe></td>
              </tr>
            </table>
          </div></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">类别:</td>
      <td width="39%" colspan="3" bgcolor="#FFFFFF"><input type="text" name="ZiCLB_2" style="width: 40%; cursor: text" readonly onClick="document.getElementById('Layer4').style.visibility='visible';this.value='';document.getElementById('ZiCLB').value=''">
        &nbsp;<img src="../../img/check.jpg" width="18" height="18" align="absmiddle" style="cursor: hand" title="选择部门" onClick="document.getElementById('Layer4').style.visibility='visible'"> <br>
        <div id="Layer4" style="position:absolute; width:40%; height:20px; z-index:1; visibility: hidden; border: 0px">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
            <tr>
              <td><iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Confi_Type&Par=ZiCLB&Layer=Layer4" style="border: 1px solid #E5E9EE"></iframe></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">创建于:</td>
      <td width="39%" bgcolor="#FFFFFF"><input Name="AddTime1" ID="AddTime1" value="" size="13" onclick="this.value='';gfPop.fPopCalendar(this);return false;" readonly>
        &nbsp;&nbsp;-&nbsp;&nbsp;
        <input Name="AddTime2" ID="AddTime2" value="" size=13 onclick="this.value='';gfPop.fPopCalendar(this);return false;" readonly>      </td>
      <td width="11%" nowrap bgcolor="#EBF4F5">废止于:</td>
      <td width="39%" bgcolor="#FFFFFF" ><input Name="EndTime1" ID="EndTime1" value="" size="13" onclick="this.value='';gfPop.fPopCalendar(this);return false;" readonly>
        &nbsp;&nbsp;-&nbsp;&nbsp;
        <input Name="EndTime2" ID="EndTime2" value="" size=13 onclick="this.value='';gfPop.fPopCalendar(this);return false;" readonly>      </td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">地理位置:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="Location_2" style="width: 70%; cursor: text" readonly onClick="document.getElementById('Layer3').style.visibility='visible';this.value='';document.getElementById('Location').value=''">
        &nbsp;<img src="../../img/check.jpg" width="18" height="18" align="absmiddle" style="cursor: hand" title="选择部门" onClick="document.getElementById('Layer3').style.visibility='visible'"> <br>
        <div id="Layer3" style="position:absolute; width:70%; height:20px; z-index:1; visibility: hidden; border: 0px">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
            <tr>
              <td><iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Location_Mana&Par=Location&Layer=Layer3" style="border: 1px solid #E5E9EE"></iframe></td>
            </tr>
          </table>
        </div></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">备注说明:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="BeiZ" style="width: 70%"></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">其它描述内容:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="Other" style="width: 50%"></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">状态:</td>
      <td width="39%" bgcolor="#FFFFFF"><select name="Status" style="width: 45%">
          <option value=""></option>
          <option value="NEW">新建</option>
          <option value="WXZ">维修中</option>
          <option value="SYZ">正常使用中</option>
          <option value="YHS">库存</option>
          <option value="DEL">已注销</option>
      </select></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">检索方式:</td>
      <td width="39%" bgcolor="#FFFFFF"><select name="andor" style="width: 70%">
          <option value="and">满足已填写的全部条件</option>
          <option value="or">满足其中一个条件</option>
      </select></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">快捷条件:</td>
      <td width="39%" bgcolor="#FFFFFF"><select name="select" style="width: 80%" onChange="Change(this.value)">
          <option value=""></option>
          <option value="0">已过期的资产配置</option>
          <option value="1">一周内到期的资产配置</option>
          <option value="2">两周内到期的资产配置</option>
          <option value="3">一个月内到期的资产配置</option>
          <option value="4">两个月内到期的资产配置</option>
          <option value="5">由我创建的资产配置</option>
          <option value="6">由我进行管理或使用的资产配置</option>
          <option value="7">本年度新加的资产配置</option>
          <option value="8">本月新加的资产配置</option>
      </select></td>
    </tr>
  </table>
  <table cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
      <td width="50%" height="30" align=center nowrap style="padding-top: 6px; padding-bottom: 1px"><input name="button" type="button" class="mmBtn" onClick="Search()" value="开始检索">
          <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="全部重设">      </td>
    </tr>
  </table>
</div>
<script language="javascript">
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
<Script Language=Javascript src="../../cn_css/mmBtn.js"></Script>