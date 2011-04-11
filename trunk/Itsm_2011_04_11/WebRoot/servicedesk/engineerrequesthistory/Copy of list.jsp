<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>工程师服务请求历史</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工程师服务请求历史:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;"><table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF">
												<tr>
													<td width="1%" nowrap bgcolor="#b5d6e6">工&nbsp;&nbsp;单&nbsp;&nbsp;号:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input Name="RequNo" ID="RequNo" value="" style="width: 100%">
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">申&nbsp;&nbsp;请&nbsp;&nbsp;人:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input Name="LoginUser" ID="LoginUser" value="" style="width: 100%">
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input Name="Department_2" ID="Department_2" value="" style="width: 100%; cursor: text" onClick="this.value='';document.getElementById('Department').value='';document.getElementById('Layer1').style.visibility='visible'" readonly>
														<input Name="Department" ID="Department" type="hidden"><br>
														<div id="Layer1" style="position:absolute; width: 180%; height:20px; z-index:99; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
															<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../user/?NowAction=tree&ID=Dept_Mana&Par=Department&Layer=Layer1" style="border: 1px solid #E5E9EE"></iframe>
														</div>
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">完成结果:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5">
														<select Name="RequPeop" ID="RequPeop" style="height: 20px; width: 100%">
															<option value="">请选择</option>
															<option value="OK" >已成功解决</option>
															<option value="CLS" >无法解决的事件</option>
														</select>
												  </td>
												</tr>
												<tr>
													<td width="1%" nowrap bgcolor="#b5d6e6">服务类别:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input type="text" name="Catename" style="width: 100%; cursor: text" readonly value="" onClick="this.value='';document.getElementById('RequObj').value='';document.getElementById('Layer2').style.visibility='visible'">
														<input Name="RequObj" ID="RequObj" value="" type="hidden"><br>
														<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:99; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
															<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../home/?NowAction=aptree&ID=Service_cat&Sear=1&Lct=Inci" style="border: 1px solid #E5E9EE"></iframe>
														</div>
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">工&nbsp;&nbsp;程&nbsp;&nbsp;师:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input Name="ITprinc" ID="ITprinc" value="" style="width: 100%">
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">服务协议:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<select Name="OtherNote" ID="OtherNote" style="height: 20px; width: 100%">
															<option value="">请选择</option>
															<option value="1" >满足服务水平协议</option>
															<option value="0" >不满足服务水平协议</option>
														</select>
												  </td>
													
													<td width="1%" nowrap bgcolor="#b5d6e6">自定义条件:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5">
														<select Name="Remark" ID="Remark" style="width: 100%">
															<option value=""></option>
															<option value=" AND ITprinc = 'yangcen'">我完成的服务请求</option>
															<option value=" AND RequNo in(SELECT RequNo FROM Service><Tran WHERE Succor = '杨岑' or Succor = '杨岑' or Succor = 'yangcen')">我经手过的服务请求</option>
															
															<option value="And_FieldName_$_XXXXX">打印</option>
															
															<option value="And_FieldName_$_XXXXX"></option>
															
														</select>
												  </td>
												</tr>
												<tr>
													<td width="1%" nowrap bgcolor="#b5d6e6">申请时间:&nbsp;</td>
													<td nowrap bgcolor="#EBF4F5" style="padding-right: 10px">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="49%">
																	<input Name="RequTime1" ID="RequTime1" value="" onClick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-right: 0px; text-align: left; padding-right: 0px">
																</td>
																<td width="2%">
																	<input value="~" readonly style="width: 100%; border-right: 0px; border-left: 0px; text-align: center; padding-left: 0px; padding-right: 0px">
																</td>
																<td width="49%">
																	<input Name="RequTime2" ID="RequTime2" value="" onClick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-left: 0px; text-align: right; padding-left: 0px">
																</td>
															</tr>
														</table>
												  </td>
													<td width=1% nowrap bgcolor="#b5d6e6">结束时间:&nbsp;</td>
													<td nowrap bgcolor="#EBF4F5" style="padding-right: 10px">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="49%">
																	<input Name="EndTime1" ID="EndTime1" value="" onClick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-right: 0px; text-align: left; padding-right: 0px">
																</td>
																<td width="2%">
																	<input value="~" readonly style="width: 100%; border-right: 0px; border-left: 0px; text-align: center; padding-left: 0px; padding-right: 0px">
																</td>
																<td width="49%">
																	<input Name="EndTime2" ID="EndTime2" value="" onClick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-left: 0px; text-align: right; padding-left: 0px">
																</td>
															</tr>
														</table>
												  </td>
													<td width="1%" nowrap bgcolor="#b5d6e6">摘要或描述:&nbsp;</td>
													<td width="22%" bgcolor="#EBF4F5" style="padding-right: 10px">
														<input Name="Detail" ID="Detail" value="" style="width: 100%">
												  </td>
													<td align="right" bgcolor="#EBF4F5" colspan=2>
														<input name="button" type="button" class="mmBtn" onClick="SeekOnClick('ID','Desc'); this.disabled=true;" value="搜索" style="height: 20px">
												  </td>
												</tr>
											</table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" style="font-size:12px; color:#333333; font-weight:bold;">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" style="font-size:12px; color:#333333; font-weight:bold;"><img src="../../images/main20100521dot04.gif">服务请求列表</td>
        </tr>
        <tr>
          <td valign="top" background="../../img/Separator.gif" style="line-height:5px;"><img src="../../img/Separator.gif" width="6" height="6"></td>
        </tr>
      </table>
      <table width="100%" border=0 cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
        <tr>
          <td width="5%" height="22" align="center" nowrap background="../../images/main20100521_58.gif"><font class="alllisttitle" style='cursor:hand' onclick="SeekOnClick('RequNo','DESC');">工单号</font></td>
          <td width="26%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('RequObj','DESC');">服务类别</font> </td>
          <td width="9%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('RequPeop','DESC');">申请人</font> </td>
          <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('RequTrueTime','DESC');">申请时间</font> </td>
          <td width="13%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('WorkTimes','DESC');">结束时间</font> </td>
          <td width="12%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('Approver','DESC');">派单人</font> </td>
          <td width="14%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('ITPrinc','DESC');">工程师</font> </td>
          <td width="7%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> 处理总时长 </td>
          <td width=2% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"><font style='cursor:hand' onclick="SeekOnClick('State','DESC');">状态</font></td>
          <td width=2% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">查看</td>
        </tr>
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
          <td height="30">&nbsp;R08000004450&nbsp;</td>
          <td valign="top"> IT报事服务/硬件/桌面机硬件升级/ </td>
          <td align="center" valign="middle"> 金紫霓 </td>
          <td align="center" valign="middle"> 2008-6-27 13:23:44 </td>
          <td align="center" valign="middle"></td>
          <td align="center" valign="middle"> jianglin2 </td>
          <td align="center" valign="middle"></td>
          <td align="center" valign="middle"></td>
          <td align="center" valign="middle" nowrap>&nbsp;拒绝</td>
          <td align=center valign="middle" nowrap>&nbsp;<a onClick="window.open('ViewReportDetail.jsp','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes')" style="cursor: hand" href="javascript:"><img src="../../img/viewdetail.gif" width="16" height="14" border="0"></a>&nbsp;</td>
        </tr>
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
          <td height="30">&nbsp;C08000004444&nbsp;</td>
          <td valign="top"> IT报事服务/计算机软件/MCAFEE防毒软件8.5+升级病毒库/ </td>
          <td align="center" valign="middle"> 陈涛 </td>
          <td align="center" valign="middle"> 2008-6-17 14:49:22 </td>
          <td align="center" valign="middle"> 2008-6-19 11:10:25 </td>
          <td align="center" valign="middle"> 张晓曦 </td>
          <td align="center" valign="middle"> 张晓曦 </td>
          <td align="center" valign="middle"> 1d 20h 21' </td>
          <td align="center" valign="middle" nowrap>&nbsp;关闭</td>
          <td align=center valign="middle" nowrap>&nbsp;<a onClick="window.open('ViewReportDetail.jsp','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes')" style="cursor: hand" href="javascript:"><img src="../../img/viewdetail.gif" width="16" height="14" border="0"></a><a onClick="window.open('../inci/?NowAction=detail&RequNo=C08000004444&RequID=4444','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes')" style="cursor: hand" href="javascript:"></a>&nbsp;</td>
        </tr>
      </table>
      <table cellspacing=0 cellpadding=0 border=0 width="100%">
        <tr>
          <td nowrap style="padding-top: 6px; padding-bottom: 1px"><table border=0 cellspacing=0 cellpadding=0 width='1%' style='border: outset 1px white'>
            <tr>
              <td style='background-image: url(../img/page.jpg)' nowrap>&nbsp;|&nbsp;&nbsp;<font color=red><b>1</b></font>&nbsp;&nbsp;| &nbsp;[<font color=red><b>2</b></font>/<font color=red><b>1</b></font>]&nbsp;</td>
            </tr>
          </table></td>
          <td align=right nowrap style="padding-top: 6px; padding-bottom: 1px"><input name="button2" type="button" disabled class="mmBtn" onClick="window.open('../kpi/?NowAction=export&type=Inci&RequObj=&TheSql=select%20*%20from%20Service_Rec%20where%20%28State%20%3D%20%27PED%27%20or%20State%20%3D%20%27FIN%27%20or%20State%20%3D%20%27REF%27%29%20and%20not%20RequNo%20like%20%27%25RFC%25%27%20and%20State%20%3C%3E%20%27%27%20%20order%20by%20ID%20Desc','','width=780,height=500,scrollbars=yes,menubar=yes,resizable=yes,top=10,left=100,status=yes')" value="导出">
              <input name="button2" type="button" class="mmBtn" onClick="window.location='ViewReport.jsp'" value="查看报表">
              <input name="button2" type="button" class="mmBtn" onClick="window.open('customize.jsp','','width=400,height=350,scrollbars=yes,menubar=no,resizable=no,top=60,left=100,status=yes')" value="自定义">
              <input name="button2" type="button" class="mmBtn" onClick="history.go(-1)" value="返回">
          </td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
</body>
</html>
