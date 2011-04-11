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
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="49%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">请选择显示的列:</td>
   <td width="1%" align="right" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td align="right"><span style="padding: 1px; border-right: 0px; padding-top: 2px">
         <input type="checkbox" name="Default" value="1" style="border: 0px">
       </span></td>
       <td nowrap class="detailtitle2">设为默认页</td>
     </tr>
   </table></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="300" valign="top"><div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px; left: 3px;">
      <table width="99%" height="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#b5d6e6">
        <input type="hidden" value="SheBMC" name="Field_0">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_0" type="checkbox" value="1" style="border: 0px" checked>          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="名称" name="Name_0" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_0">
              <option value="20">20%</option>
              <option value="$10">10%</option>
              <option value="$15">15%</option>
              <option value="$20">20%</option>
              <option value="$25">25%</option>
              <option value="$30">30%</option>
              <option value="$35">35%</option>
              <option value="$40">40%</option>
              <option value="$45">45%</option>
              <option value="$50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="ZiCLB" name="Field_1">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_1" type="checkbox" value="1" style="border: 0px" checked>          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="类别" name="Name_1" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_1">
              <option value="10">10%</option>
              <option value="$10">10%</option>
              <option value="$15">15%</option>
              <option value="$20">20%</option>
              <option value="$25">25%</option>
              <option value="$30">30%</option>
              <option value="$35">35%</option>
              <option value="$40">40%</option>
              <option value="$45">45%</option>
              <option value="$50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="SouSBM" name="Field_2">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_2" type="checkbox" value="1" style="border: 0px" checked>          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="所属部门" name="Name_2" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_2">
              <option value="10">10%</option>
              <option value="$10">10%</option>
              <option value="$15">15%</option>
              <option value="$20">20%</option>
              <option value="$25">25%</option>
              <option value="$30">30%</option>
              <option value="$35">35%</option>
              <option value="$40">40%</option>
              <option value="$45">45%</option>
              <option value="$50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="ShiYR" name="Field_3">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_3" type="checkbox" value="1" style="border: 0px" checked>          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="管理员" name="Name_3" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_3">
              <option value="10">10%</option>
              <option value="$10">10%</option>
              <option value="$15">15%</option>
              <option value="$20">20%</option>
              <option value="$25">25%</option>
              <option value="$30">30%</option>
              <option value="$35">35%</option>
              <option value="$40">40%</option>
              <option value="$45">45%</option>
              <option value="$50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="TiaoXM" name="Field_7">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_7" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="条形码" name="Name_7" style="width: 100%"></td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_7">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="PinP" name="Field_8">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_8" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="品牌" name="Name_8" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_8">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="GuiG_1" name="Field_9">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_9" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="规格一" name="Name_9" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_9">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="GuiG_2" name="Field_10">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_10" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="规格二" name="Name_10" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_10">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="GuiG_3" name="Field_11">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_11" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="规格三" name="Name_11" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_11">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="GuiG_4" name="Field_12">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_12" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="规格四" name="Name_12" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_12">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="XingH" name="Field_13">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_13" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="型号" name="Name_13" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_13">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="ChuCXH" name="Field_14">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_14" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="出厂批号" name="Name_14" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_14">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="DanJ" name="Field_15">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_15" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="单价" name="Name_15" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_15">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="Location" name="Field_48">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_48" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="地域" name="Name_48" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_48">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="Parent" name="Field_49">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_49" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="父配置" name="Name_49" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_49">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="Brothers" name="Field_51">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_51" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="关联配置" name="Name_51" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_51">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="StartTime" name="Field_53">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_53" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="启用时间" name="Name_53" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_53">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="EndTime" name="Field_54">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_54" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="终止时间" name="Name_54" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_54">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="ShuX" name="Field_55">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_55" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="属性" name="Name_55" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_55">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="ZiCDM" name="Field_61">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_61" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="代码" name="Name_61" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_61">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="BeiZ" name="Field_62">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_62" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="备注" name="Name_62" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_62">
              <option value="$10">10%</option>
              <option value="$15">15%</option>
              <option value="$20">20%</option>
              <option value="$25">25%</option>
              <option value="$30">30%</option>
              <option value="$35">35%</option>
              <option value="$40">40%</option>
              <option value="$45">45%</option>
              <option value="$50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="AddTime" name="Field_64">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_64" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="加入时间" name="Name_64" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_64">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="PRI" name="Field_65">
        <tr>
          <td width="1%" bgcolor="#EBF4F5" class="td-left"><input name="Chose_65" type="checkbox" value="1" style="border: 0px">          </td>
          <td width="99%" bgcolor="#FFFFFF" class="td-right"><input type="text" value="优先级" name="Name_65" style="width: 100%">          </td>
          <td bgcolor="#FFFFFF" class="td-right"><select name="Width_65">
              <option value="10">10%</option>
              <option value="15">15%</option>
              <option value="20">20%</option>
              <option value="25">25%</option>
              <option value="30">30%</option>
              <option value="35">35%</option>
              <option value="40">40%</option>
              <option value="45">45%</option>
              <option value="50">50%</option>
            </select>          </td>
        </tr>
        <input type="hidden" value="66" name="Total">
      </table>
    </div></td>
  </tr>
</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td height="12" style="padding-top: 8px" nowrap><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
    <td align=center nowrap><input type="button" value="确定" onClick="myForm.submit()" class=mmBtn name="button">
    </td>
  </tr>
</table>
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
