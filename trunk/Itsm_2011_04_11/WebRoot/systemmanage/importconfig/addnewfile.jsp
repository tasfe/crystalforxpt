<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>创建新的文档目录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
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
function ChoseAll(Content,ObjName)
{
	var allLine=document.body.all.item(Content);
	if (allLine!=null)
	{
		var count=allLine.length;
		if (count)
		{
			for (i=count-1; i>=0; i--)
			{
				if (ObjName.checked==true)
				{
					allLine[i].checked=true;
				}
				else
				{
					allLine[i].checked=false;
				}
			}
		}
	}
}
</Script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">创建新的文档目录:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td valign="top" bgcolor="#FFFFFF" id="mainright">
			<form name=myForm id=myForm action='../prob/default.asp?NowAction=db_pro&type=SaveCat&Refresh=1' method='post' target="sum">
				<input type="hidden" name="RequObj" id="RequObj" value="|1,|2,|3,">
				<input type="hidden" name="ID" id="ID" value="">
				<input type="hidden" name="Parent" id="Parent" value="">
				<table width="100%" border="0" cellpadding="0" cellspacing="1" height="100%">
					<tr bgcolor="#FFFFFF">
						<td width="88%" height="99%" colspan="2" valign="top">
							<table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
								<tr>
									<td width="15%" height="12" bgcolor="#EBF4F5" style="padding-right: 40px">目录名:</td>
									<td width="85%" height="12" bgcolor="#FFFFFF" class="td-right-s">
										<table width="100%"  border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td style="padding-right: 5px" width="50%">
													<input name="Document" type="text" id="Document" style="width: 100%; background: url(../img/en.gif); background-repeat: no-repeat; background-color: white" maxlength="50" value="">
												</td>
												<td width="50%">
													<input name="Document_SC" type="text" id="Document_SC" style="width: 100%; background: url(../img/cn.gif); background-repeat: no-repeat; background-color: white" maxlength="50" value="">
												</td>
											</tr>
										</table>
								  </td>
								</tr>
								
								<div id="Layer2"></div>
								
								<tr>
									<td width="15%" height="12" rowspan="2" valign="top" nowrap bgcolor="#EBF4F5" style="padding-right: 40px">权限:</td>
									<td bgcolor="#FFFFFF" class="td-right-s">
										<table border="0" cellspacing="0" cellpadding="0" width="100%" style="border: 1px inset #CCCCCC; Background-Color: White" height="100%">
											<tr>
												<td>
													<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%; padding: 2px">
														<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
															<tr>
																<td width=99% height="22" colspan="3" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding: 2px; padding-left: 4px">工作组
																<td width="1%" nowrap background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding: 2px; text-align: center">查看
																<td width="1%" nowrap background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding: 2px; text-align: center">添加
																<td width="1%" nowrap background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding: 2px; text-align: center">删改
														  </tr>
															
															<tr>
																<td colspan="6" bgcolor="#FFFFFF" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">全局&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">二线支持_信息系统管理</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|8_R|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|8_C_W|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|8_R_D|" style="border: 0px; width: 14px" checked>
															  </td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">二线支持_数据挖掘管理</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|9_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|9_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|9_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">测试使用</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|20_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|20_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|20_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">外部顾问组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|22_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|22_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|22_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
															<tr>
																<td height="22" colspan="6" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">重庆区域&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">一线支持_重庆IT运维组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|5_R|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|5_C_W|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|5_R_D|" style="border: 0px; width: 14px" checked>
															  </td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">二线支持_网络系统管理</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|6_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|6_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|6_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
															<tr>
																<td height="22" colspan="6" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">北京区域&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">一线支持_北京IT运维组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|1_R|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|1_C_W|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|1_R_D|" style="border: 0px; width: 14px" checked>
															  </td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">二线支持_应用系统管理</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|7_R|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|7_C_W|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|7_R_D|" style="border: 0px; width: 14px" checked>
															  </td>
															</tr>
															
															<tr>
																<td colspan="6" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">上海区域&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">一线支持_上海IT运维组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|2_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|2_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|2_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
															<tr>
																<td colspan="6" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">成都区域&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">一线支持_成都IT运维组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|3_R|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|3_C_W|" style="border: 0px; width: 14px" checked>
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|3_R_D|" style="border: 0px; width: 14px" checked>
															  </td>
															</tr>
															
															<tr>
																<td colspan="6" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle" style="padding-left: 3px; padding-bottom: 0px"><img src="../../img/lefticon12.gif">西安区域&nbsp;</td>
															</tr>
															
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td width="1%" style="padding-left: 3px; padding-right: 3px; text-align: center; padding-top: 1px"><img src="../../img/orgtra.gif"></td>
																<td width="99%" colspan="2">一线支持_西安IT运维组</td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content1" value="|4_R|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content2" value="|4_C_W|" style="border: 0px; width: 14px">
															  </td>
																<td style="text-align: center">
																	<input type="checkbox" name="Content3" value="|4_R_D|" style="border: 0px; width: 14px">
															  </td>
															</tr>
															
													  </table>
													</div>
												</td>
											</tr>
										</table>
								  </td>
								</tr>
								<tr>
									<td width="85%" height="12" background="../img/trx.gif" bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
										<table width="20" border="0" cellspacing="0" cellpadding="2">
											<tr>
												<td style="padding-top: 3px; padding-left: 0px">
													<input type="checkbox" name="Table" value="1" style="border: 0px; width: 14px" onClick="ChoseAll('Content1',this)">
												</td>
												<td nowrap><b>全部查看权限</b></td>
												<td style="padding-top: 3px; padding-left: 10px">
													<input type="checkbox" name="Table" value="1" style="border: 0px; width: 14px" onClick="ChoseAll('Content2',this)">
												</td>
												<td nowrap><b>全部添加权限</b></td>
												<td style="padding-top: 3px; padding-left: 10px">
													<input type="checkbox" name="Table" value="1" style="border: 0px; width: 14px" onClick="ChoseAll('Content3',this)">
												</td>
												<td nowrap><b>全部删改权限</b></td>
											</tr>
										</table>
								  </td>
								</tr>
						  </table>
						</td>
					</tr>
			  </table>
			</form>
	  </td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td height="12">
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td height="12" style="padding-top: 8px" nowrap><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
					<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
						<input type="button" value="保存目录" onClick="if(confirm('您确认要保存吗？')){myForm.submit()}" class=mmBtn name="button">
						<input type="button" onClick="if(confirm('您确认要放弃保存吗？')){window.close()}" value="放弃" class=mmBtn name="button">
					</td>
				</tr>
			</table>
	  </td>
	</tr>
</table>
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
