<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
function html_clock(Num){
	var Num = Num-1+2;
	var Hours1 = (Num/60)/60;
	var Hours = Math.round((Num/60)/60);
	if (Hours > Hours1) Hours = Hours-1;
	var Minutes1 = (Num-Hours*60*60)/60;
	var Minutes = Math.round((Num-Hours*60*60)/60);
	if (Minutes > Minutes1) Minutes = Minutes-1;
	var Seconds = Num-Hours*60*60-Minutes*60;
	if (Minutes > 4) window.document.getElementById('htmlclock').style.color = 'Orange';
	if (Minutes > 9) window.document.getElementById('htmlclock').style.color = 'red';
	if (Hours > 0) window.document.getElementById('htmlclock').style.color = 'black';
	if (Hours < 10) Hours = "0" + Hours;
	if (Minutes < 10) Minutes = "0" + Minutes;
	if (Seconds < 10) Seconds = "0" + Seconds;
	window.document.getElementById('htmlclock').innerText = Hours+":"+Minutes+":"+Seconds;
	setTimeout ("html_clock('"+Num+"')", 1000);
}
function Disabled(Va,Form){
	if (Va=="ID")
	{
		Form.elements[8].disabled=true;
		Form.elements[10].disabled=true;
		Form.elements[11].disabled=true;
		Form.elements[12].disabled=true;
		Form.elements[13].disabled=true;
	}
	else
	{
		Form.elements[8].disabled=false;
		Form.elements[10].disabled=false;
		Form.elements[11].disabled=false;
		Form.elements[12].disabled=false;
		Form.elements[13].disabled=false;
	}
}
</script>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">分类总汇:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px; left: 1px;">
  <table width="99%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
			<tr> 
			  <td width="15%" height="12" nowrap bgcolor="#EBF4F5" class="td-left-s" style="padding-right: 20px">分类字段:</td>
			  <td width="85%" height="12" bgcolor="#FFFFFF" class="td-right-s">
			  <select size=1 style="width: 80%" name="Filed" id="Filed">
				<option value="">整体</option>
				
							<option value="TiaoXM">条形码</option>
							
							<option value="PinP">品牌</option>
							
							<option value="GuiG_1">规格一</option>
							
							<option value="GuiG_2">规格二</option>
							
							<option value="GuiG_3">规格三</option>
							
							<option value="GuiG_4">规格四</option>
							
							<option value="XingH">型号</option>
							
							<option value="ChuCXH">出厂批号</option>
							
							<option value="DanJ">单价</option>
							
							<option value="SheBMC">名称</option>
							
							<option value="Location">地理位置</option>
							
							<option value="EndTime">废止时间</option>
							
							<option value="ShuX">属性</option>
							
							<option value="Editor">入库人</option>
							
							<option value="ZhuangT">状态</option>
							
							<option value="SouSBM">所属部门</option>
							
							<option value="ShiYR">管理员</option>
							
			  </select>
			  </td>
			</tr>
			<tr> 
			  <td width="15%" rowspan="2" nowrap bgcolor="#EBF4F5" class="td-left-s" style="padding-right: 20px">选定汇总项:</td>
			  <td width="85%" height="12" colspan="2" bgcolor="#FFFFFF" class="td-right-s">
			  <select size=1 style="width: 60%" name="Collect" id="Collect" onChange="Disabled(this.value,this.form)">
				
							<option value="TiaoXM">条形码</option>
							
							<option value="PinP">品牌</option>
							
							<option value="GuiG_1">规格一</option>
							
							<option value="GuiG_2">规格二</option>
							
							<option value="GuiG_3">规格三</option>
							
							<option value="GuiG_4">规格四</option>
							
							<option value="XingH">型号</option>
							
							<option value="ChuCXH">出厂批号</option>
							
							<option value="DanJ">单价</option>
							
							<option value="SheBMC">名称</option>
							
							<option value="Location">地理位置</option>
							
							<option value="EndTime">废止时间</option>
							
							<option value="ShuX">属性</option>
							
							<option value="Editor">入库人</option>
							
							<option value="ZhuangT">状态</option>
							
							<option value="SouSBM">所属部门</option>
							
							<option value="ShiYR">管理员</option>
							
				<option value="ID">计数..</option>
			  </select>
			  </td>
			</tr>
			<tr> 
			  <td width="85%" height="12" colspan="2" bgcolor="#FFFFFF" class="td-right-s">必须选择一个用于填写数字型数据的字段</td>
			</tr>
			<tr> 
			  <td nowrap bgcolor="#EBF4F5" class="td-left-s" style="padding-top: 5px; padding-right: 20px">汇总方式:</td>
			  <td colspan="2" bgcolor="#FFFFFF" class="td-right-s">
			  <table width="100%" border="0" cellspacing="3" cellpadding="0" style="border: 1px inset white; line-height: 12px; border-bottom: 1px solid #ECECEC; border-right: 1px solid #ECECEC" bgcolor="#FFFFFF" height="100%">
                <tr>
                  <td width="1%" valign="top" style="padding-top: 1px"><input name="Methods" type="checkbox" value="Sum" style="border: 0px" checked></td>
                  <td valign="top">求和</td>
                </tr>
                <tr>
                  <td width="1%" valign="top" style="padding-top: 1px"><input name="Methods" type="checkbox" value="Cou" style="border: 0px" checked></td>
                  <td valign="top">计数</td>
                </tr>
                <tr>
                  <td width="1%" valign="top" style="padding-top: 1px"><input name="Methods" type="checkbox" value="Ave" style="border: 0px"></td>
                  <td valign="top">平均值</td>
                </tr>
                <tr>
                  <td width="1%" valign="top" style="padding-top: 1px"><input name="Methods" type="checkbox" value="Max" style="border: 0px"></td>
                  <td valign="top">最大值</td>
                </tr>
                <tr>
                  <td width="1%" valign="top" style="padding-top: 1px"><input name="Methods" type="checkbox" value="Min" style="border: 0px"></td>
                  <td valign="top">最小值</td>
                </tr>
                <tr>
                  <td colspan="2" height="99%"></td>
                </tr>
              </table>
			  </td>
			</tr>
			<tr> 
			  <td width="15%" height="12" nowrap bgcolor="#EBF4F5" class="td-left-s" style="padding-right: 20px">报表项:</td>
			  <td width="85%" height="12" colspan="2" bgcolor="#FFFFFF" class="td-right-s">
			  <select size=1 style="width: 60%" name="Report" id="Report">
				<option value="Cou">计数</option>
				<option value="Sum">求和</option>
				<option value="Ave">平均值</option>
			  </select>
			  </td>
			</tr>
  </table>
  <table cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
      <td height="12" style="padding-top: 8px" nowrap><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
      <td align=center nowrap style="padding-top: 8px; padding-bottom: 0px"><input type="button" value="生成报表" onClick="document.getElementById('FieldName').value=document.getElementById('Filed').options[document.getElementById('Filed').selectedIndex].text;document.getElementById('FieldValue').value=document.getElementById('Collect').options[document.getElementById('Collect').selectedIndex].text;window.open('about:blank','Stotal','width=671,height=500,scrollbars=yes,menubar=yes,resizable=yes,top=10,left=100,status=yes');myForm.submit()" class=mmBtn name="button"></td>
    </tr>
  </table>
</div>
<script language="JavaScript">
function SeekOnClick(Var1, Var2)
{
	var strTemp;
	strTemp = "&TiaoXM=" + document.getElementById("TiaoXM").value;
	strTemp = strTemp + "&ZiCDM=" + document.getElementById("ZiCDM").value;
	strTemp = strTemp + "&TbName=" + document.getElementById("TbName").value;
	strTemp = strTemp + "&TbID=" + document.getElementById("TbID").value;
	strTemp = strTemp + "&User=" + document.getElementById("User").value;
	strTemp = strTemp + "&SheBMC=" + document.getElementById("SheBMC").value;
	strTemp = strTemp + "&ZiCLB_S=";
	strTemp = strTemp + "&RecName=ZiCLB";
	strTemp = strTemp + "&Title=";
	strTemp = strTemp + "&TitleCN=";
	strTemp = strTemp + "&TitleEN=";
	strTemp = strTemp + "&Width=20,10,10,10,"
	strTemp = strTemp + "&Chosed=SheBMC,ZiCLB,SouSBM,ShiYR,"
	strTemp = strTemp + "&ChosedName=名称%2C类别%2C所属部门%2C管理员%2C"
	strTemp = strTemp + "&Sort=" + Var1;
	strTemp = strTemp + "&Desc=" + Var2;
	document.location.href = "../asst/?NowAction=assetslist&RecState=|230,|239,"+ strTemp;	
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
