<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
function tab()
{
    //alert(document.all.myselect.value);
	if(document.all.myselect.value=="all")
	{
	document.all.tab1.style.display="";
	document.all.tab2.style.display="";
	}
	if(document.all.myselect.value=="main")
	{
	document.all.tab1.style.display="";
	document.all.tab2.style.display="none";
	}
	if(document.all.myselect.value=="sub")
	{
	document.all.tab1.style.display="none";
	document.all.tab2.style.display="";
	}
}
</script>
<script language="JavaScript">
function Search()
{
	Url = "../asst/?NowAction=assetssearch";
	Url = Url + "&Editor=" + document.getElementById("Editor").value;	
	Url = Url + "&Status=" + document.getElementById("Status").value;	
	Url = Url + "&SheBMC=" + document.getElementById("SheBMC").value;	
	Url = Url + "&ZiCLB=" + document.getElementById("ZiCLB").value;	
	Url = Url + "&SouSBM=" + document.getElementById("SouSBM").value;	
	Url = Url + "&ShiYR=" + document.getElementById("ShiYR").value;	
	Url = Url + "&AddTime1=" + document.getElementById("AddTime1").value;	
	Url = Url + "&AddTime2=" + document.getElementById("AddTime2").value;	
	Url = Url + "&EndTime1=" + document.getElementById("EndTime1").value;	
	Url = Url + "&EndTime2=" + document.getElementById("EndTime2").value;	
	Url = Url + "&BeiZ=" + document.getElementById("BeiZ").value;	
	Url = Url + "&Other=" + document.getElementById("Other").value;	
	Url = Url + "&andor=" + document.getElementById("andor").value;	
	Url = Url + "&Location=" + document.getElementById("Location").value;	
	document.location.href = Url;
}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">批量导入配置:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="98%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
    </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="49%" height="25"><img src="../../images/main20100521dot04.gif">&nbsp;<b>设备配置检索:</b>&nbsp;</td>
      <td width="50%" align="right"><select id="myselect" name="select" style="width: 150px" onChange="tab()">
        <option value="all">全部表格</option>
        <option value="main">主表</option>
        <option value="sub">从表: 硬件配置项附加表单</option>
      </select></td>
    </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" id="tab1">
    <tr>
      <td width="11%" height="22" colspan="4" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">&nbsp;硬件:</td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">创建人:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="Editor" style="width: 100%"></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">状态:</td>
      <td width="39%" bgcolor="#FFFFFF"><select name="Status" style="width: 100%">
          <option value=""></option>
          <option value="NEW">新建</option>
          <option value="WXZ">维修中</option>
          <option value="SYZ">正常使用中</option>
          <option value="YHS">库存</option>
          <option value="DEL">已注销</option>
      </select></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">名称:</td>
      <td bgcolor="#FFFFFF"><input type="text" name="SheBMC" style="width: 100%"></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">备注说明:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="BeiZ" style="width: 100%"></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">所属部门:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="SouSBM_2" style="width: 60%; cursor: text" readonly value="" onClick="document.getElementById('Layer2').style.visibility='visible'">
        &nbsp;<img src="../../img/check.jpg" width="18" height="18" align="absmiddle" style="cursor: hand" title="选择部门" onClick="document.getElementById('Layer2').style.visibility='visible'"> <br>
        <div id="Layer2" style="position:absolute; width: 60%; height:20px; z-index:1; visibility: hidden; border: 0px">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
            <tr>
              <td><iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Dept_Mana&Par=SouSBM&Layer=Layer2" style="border: 1px solid #E5E9EE"></iframe></td>
            </tr>
          </table>
      </div></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">管理或责任人:</td>
      <td bgcolor="#FFFFFF" style="padding-left: 5px"><input type="text" name="ShiYR" style="width: 100%" onClick="document.getElementById('Layer').style.visibility='visible'" onKeyUp="document.getElementById('Layer').style.visibility='visible';window.User.location='../task/?NowAction=users&Key='+this.value+'&dept='+document.getElementById('SouSBM').value">
          <br>
          <div id="Layer" style="position:absolute; width: 50%; height:78px; z-index:1; visibility: hidden; background-color: #FFFFFF; layer-background-color: #FFFFFF; padding-left: 0px">
            <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
              <tr>
                <td><iframe id="User" name="User" frameborder="0" height="150" width="238" scrolling="yes" src="../task/?NowAction=users" style="border: 1px solid #E5E9EE"></iframe></td>
              </tr>
            </table>
      </div></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">地理位置:</td>
      <td width="39%" bgcolor="#FFFFFF"><input type="text" name="Location_2" style="width: 60%; cursor: text" readonly onClick="document.getElementById('Layer3').style.visibility='visible'">
        &nbsp;<img src="../../img/check.jpg" width="18" height="18" align="absmiddle" style="cursor: hand" title="选择部门" onClick="document.getElementById('Layer3').style.visibility='visible'"> <br>
        <div id="Layer3" style="position:absolute; width:60%; height:20px; z-index:1; visibility: hidden; border: 0px">
          <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
            <tr>
              <td><iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Location_Mana&Par=Location&Layer=Layer3" style="border: 1px solid #E5E9EE"></iframe></td>
            </tr>
          </table>
      </div></td>
      <td width="11%" nowrap bgcolor="#EBF4F5">废止于:</td>
      <td width="39%" bgcolor="#FFFFFF" ><input Name="EndTime1" ID="EndTime1" value="" size="13" onClick="gfPop.fPopCalendar(this);return false;" readonly>
        &nbsp;&nbsp;-&nbsp;&nbsp;
        <input Name="EndTime2" ID="EndTime2" value="" size=13 onClick="gfPop.fPopCalendar(this);return false;" readonly>
      </td>
    </tr>
    <tr>
  <td width="11%" nowrap bgcolor="#EBF4F5">配置项编码:</td>
      <td width="39%" valign="top" bgcolor="#FFFFFF"><select Name="Text_1" size=1 style="width: 100%">
                <option value=''>所有</option>
              <option value='aaa'>aaa</option>
              <option value='bbb'>bbb</option>
              <option value='ccc'>ccc</option>
      </select></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">使用人:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_2" value="" style="width: 100%; height: 19px"></td>
    </tr>
    <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">放置地点:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_3" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">设备型号:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_4" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">财务编号:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_5" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">出厂编号:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_6" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">应用范围:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_7" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">供货商:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_8" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">联系方式:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_9" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">保修期:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_10" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">购货日期:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_11" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">设备单价:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_12" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">设备来源:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_13" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">软件配置:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_14" value="" style="width: 100%; height: 19px"></td>
  </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" id="tab2" style="padding-top:10px;">
    <tr>
    <td width="11%" height="18" colspan="4" nowrap background="../../images/main20100521_58.gif"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="alllisttitle">&nbsp;硬件配置项附加表单:</td>
        <td width="1%"><input Name="Item_1" ID="Item_1" value="|233," type="checkbox" style="border: 0px" checked></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">操作系统:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_7" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">MAC地址1:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_8" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">MAC地址2:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_9" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">IP地址:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_10" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">处理器:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_11" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">处理器个数:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_12" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">硬盘类型:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_13" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">硬盘大小:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_14" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">硬盘个数:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_15" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">内存大小:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_16" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">内存条数:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_17" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">光驱:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_18" value="" style="width: 100%; height: 19px"></td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5">读写卡:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_19" value="" style="width: 100%; height: 19px"></td>
    <td width="11%" nowrap bgcolor="#EBF4F5">描述和说明:</td>
    <td width="39%" valign="top" bgcolor="#FFFFFF"><input type="text" name="Text_1_30" value="" style="width: 100%; height: 19px"></td>
  </tr>
  </table>
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
  <input Name="Total" ID="Total" value="1" type="hidden">
  <tr>
    <td width="11%" height="22" colspan="4" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="line-height: 16px">&nbsp;其它限定条件:</td>
  </tr>
  <tr>
    <td width="11%" nowrap bgcolor="#EBF4F5" class="td-left" style="padding-left: 10px">检索方式:</td>
    <td width="39%" bgcolor="#FFFFFF"><select name="AndOr" style="width: 60%">
      <option value="and">满足已填写的全部条件</option>
      <option value="or">满足其中一个条件</option>
    </select></td>
    <td width="11%" nowrap bgcolor="#EBF4F5" class="td-left" style="padding-left: 10px">创建于:</td>
    <td width="39%" bgcolor="#FFFFFF"><input Name="AddTime1" ID="AddTime1" value="" size="13" onClick="gfPop.fPopCalendar(this);return false;" readonly>
      &nbsp;&nbsp;-&nbsp;&nbsp;
      <input Name="AddTime2" ID="AddTime2" value="" size=13 onClick="gfPop.fPopCalendar(this);return false;" readonly>    </td>
  </tr>
  </table>
  <table width="98%" border=0 align="center" cellpadding=0 cellspacing=0>
    <tr>
      <td align=center nowrap width="50%" style="padding-top: 6px; padding-bottom: 1px"><input name="button" type="button" class="mmBtn" onClick="window.open('about:blank','listwin','width=700,height=450,scrollbars=no,menubar=no,resizable=yes,top=100,left=120,status=yes');SearchForm.submit()" value="开始检索">
          <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="全部重设">
          <input name="button" type="button" class="mmBtn" onClick="history.back()" value="退回">
      </td>
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
<Script Language=Javascript src="../..s/cn_css/mmBtn.js"></Script>
