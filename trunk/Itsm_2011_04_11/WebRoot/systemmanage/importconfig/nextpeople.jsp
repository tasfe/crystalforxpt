<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>查看配置文档</title>
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
</Script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">软件:</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr> 
    <td valign="top" id="mainright" height="100%" colspan="2">
	
	<form name=myForm id=myForm action='../prob/default.asp?NowAction=db_ass&type=ADDM' method='post' target="sum">
	
	<input id="tmp" value="so" type="hidden">
	<input type="hidden" name="ZiCLB" id="ZiCLB" value="|230,">
	<input type="hidden" name="ZiCDM" id="ZiCDM" value="HW">
	<input type="hidden" name="Location" id="Location" value="">			
	<input type="hidden" name="SouSBM" id="SouSBM" value="">			
	<input type="hidden" name="RelationShip" id="RelationShip" value="相关联的合同">			
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<tr>
	  <td height="100%"> 
		<table width="100%" border="1" cellpadding="1" cellspacing="0" class="tb-list" height="100%">
		  <tr> 
			<td style="padding: 9px; padding-top: 6px" bgcolor="#FFFFFF" valign="top">
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
				  <tr onMouseDown="document.getElementById('Layer5').style.visibility='hidden'">
					<td height="1%" valign="top">
					  <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
						<tr>
						  <td width="14%" height="12" nowrap bgcolor="#EBF4F5">显示名称:</td>
						  <td width="86%" bgcolor="#FFFFFF">
						  <input type="text" name="SheBMC" style="width: 90%;" onKeyUp="if(this.value!=''){this.style.backgroundColor='#FFFFFF'}else{this.style.backgroundColor='#FFFFCC'}">						  </td>
						</tr>
						
						<tr>
						  <td width="14%" height="12" nowrap bgcolor="#EBF4F5">所在地理位置:</td>
						  <td width="86%" bgcolor="#FFFFFF">
						  <input type="text" name="Location_2" style="width: 90%; cursor: text" readonly value="" onClick="document.getElementById('Layer3').style.visibility='visible'"><br><div id="Layer3" style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; border: 0px"><table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF"><tr><td><iframe frameborder="0" height="115" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Location_Mana&Par=Location&Layer=Layer3" style="border: 1px solid #E5E9EE"></iframe></td></tr></table></div>						  </td>
						</tr>
						
						<tr>
						  <td width="14%" height="12" nowrap bgcolor="#EBF4F5">所属部门:</td>
						  <td width="86%" bgcolor="#FFFFFF">
						  <input type="text" name="SouSBM_2" style="width: 90%; cursor: text" readonly value="" onClick="document.getElementById('Layer2').style.visibility='visible'"><br><div id="Layer2" style="position:absolute; width: 60%; height: 20px; z-index:1; visibility: hidden; border: 0px"><table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF"><tr><td><iframe frameborder="0" height="115" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Dept_Mana&Par=SouSBM&Layer=Layer2" style="border: 1px solid #E5E9EE"></iframe></td></tr></table></div>						  </td>
						</tr>
						
						<tr>
						  <td width="14%" height="12" nowrap bgcolor="#EBF4F5">描述:</td>
						  <td width="86%" bgcolor="#FFFFFF">
						  <textarea name="BeiZ" rows="12" cols="86" style="width: 90%;" height: 60px; onKeyUp="if(this.value!=''){this.style.backgroundColor='#FFFFFF'}else{this.style.backgroundColor='#FFFFCC'}"></textarea>						  </td>
						</tr>
						
						<tr> 
						  <td width="14%" height="12" rowspan="2" nowrap bgcolor="#EBF4F5">附加文件:</td>
						  <td width="86%" height="12" bgcolor="#FFFFFF" class="td-right-s" style="padding: 0px; padding-left: 3px">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr> 
								<td><textarea name="UpFile" style="width: 100%; height: 20px; border: 0px; background-image: url(../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; overflow-y: hidden; overflow-x: hidden; cursor: default" readonly class="td-right-s"></textarea></td>
								<td width="1%" style="border: 0px; padding: 3px; border-left: 2px solid white" class="td-right-s" valign="bottom"><img src="../../img/del.gif" width="18" height="18" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" title="Clear.." onClick="document.getElementById('UpFile').value=''"></td>
							  </tr>
							</table>						  </td>
						</tr>
						<tr> 
						  <td height="12" bgcolor="#FFFFFF" style="padding-right: 1px"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td nowrap>
							<input type="file" name="filename" style="width: 50%">
							<input type="submit" name="sub1" value="upload.." style="width: 65px">						</td>
					  </tr>
					</table></td>
						</tr>
					  </table>					</td>
				  </tr>
				  <tr>
				    <td valign="top"><table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tr>
                        <td height="22" nowrap="nowrap" background="../../images/main20100521_58.gif" onMouseDown="document.getElementById('Layer5').style.visibility='hidden'"><b>详细配置信息</b></td>
                        <td  background="../../images/main20100521_58.gif" height="22" width="99%" align="right"><table border="0" cellspacing="0" cellpadding="0" width="70%">
                            <tr onMouseDown="document.getElementById('Layer5').style.visibility='visiable'">
                              <td width="99%">&nbsp;</td>
                              <td onClick="if(window.Old.location.href=='about:blank'){window.Old.location='releatfile.jsp'};document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand"><img src="../../img/det.gif" align="absmiddle" /></td>
                              <td onClick="if(window.Old.location.href=='about:blank'){window.Old.location='releatfile.jsp'};document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand" nowrap="nowrap">&nbsp;<b>相关合同</b></td>
                            </tr>
                            <tr>
                              <td colspan="3"><div id="Layer5" style="position:absolute; width:100%; height:20px; z-index:1; visibility: hidden; border: 0px">
                                  <table width="100%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" bgcolor="#FFFFFF">
                                    <tr>
                                      <td colspan="3"><iframe id="Old" name="Old" frameborder="0" height="167" width="100%" scrolling="Yes" style="border: 1px solid #E5E9EE"></iframe></td>
                                    </tr>
                                    <tr>
                                      <td class="subtitle" nowrap="nowrap" style="font-weight: normal">合同码:</td>
                                      <td class="subtitle" width="99%" nowrap="nowrap" style="font-weight: normal"><input type="text" name="GetFrom" id="GetFrom" value="" style="width: 100%" readonly="readonly" />                                      </td>
                                      <td class="subtitle" nowrap="nowrap"><input type="button" value="读取" onClick="if(document.getElementById('GetFrom').value!=''){window.sum.location='../asst/?NowAction=attach_asst&amp;Type=AddTable1&amp;Obj=HW&amp;LB=|230,&amp;RequNo='+document.getElementById('GetFrom').value};document.getElementById('Layer5').style.visibility='hidden'" class="mmBtn_sm" name="button2" style="font-weight: normal" />
                                          <input type="button" value="取消" onClick="document.getElementById('GetFrom').value='';window.Old.location.href='about:blank';document.getElementById('Layer5').style.visibility='hidden'" class="mmBtn_sm" name="button2" style="font-weight: normal" />                                      </td>
                                    </tr>
                                  </table>
                              </div></td>
                            </tr>
                        </table></td>
                      </tr>
                      
                    </table></td>
				    </tr>
				  <tr>
				    <td valign="top"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tbody>
                        <tr title="人力成本(/天)" bgcolor="#f9f9f9">
                          <td width="14%" height="27" valign="top" nowrap="nowrap" bgcolor="#EBF4F5">人力成本(/天):</td>
                          <td width="86%" bgcolor="#FFFFFF"><input style="width:90%" id="Text_1" name="Text_1" /></td>
                        </tr>
                      </tbody>
                    </table></td>
				  </tr>
				  <tr onMouseDown="document.getElementById('Layer5').style.visibility='hidden'">
					<td height="99%" valign="top">
					  <table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
						<tr> 
						  <td background="../../images/main20100521_58.gif" height="22"><b>其它选项</b></td>
						</tr>
						<tr>
						  <td style="padding: 0px">
						  <table width="100%" height="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
							<tr>
							  <td width="14%" height="12" valign="top" nowrap bgcolor="#EBF4F5">配置数量:</td>
							  <td width="86%" bgcolor="#FFFFFF">
							  <input name="Total" id="Total" style="width: 90%" type="text" onKeyUp="if(this.value<1000){}else{this.value='1'}" value="1" title="Max: 999" maxlength="3">							  </td>
							</tr>
							<tr>
							  <td width="14%" height="12" valign="top" nowrap bgcolor="#EBF4F5">流水后缀(一):</td>
							  <td width="86%" bgcolor="#FFFFFF">
							  <table width="80%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td width="99%">
								  
								  <select style="width: 100%" name="ADD1" id="ADD1">
									<option value="0">后缀于..</option>
									
											<option value="1">|-配置项编码</option>
											
											<option value="2">|-使用人</option>
											
											<option value="3">|-放置地点</option>
											
											<option value="4">|-设备型号</option>
											
											<option value="5">|-财务编号</option>
											
											<option value="6">|-出厂编号</option>
											
											<option value="7">|-应用范围</option>
											
											<option value="8">|-供货商</option>
											
											<option value="9">|-联系方式</option>
											
											<option value="10">|-保修期</option>
											
											<option value="11">|-购货日期</option>
											
											<option value="12">|-设备单价</option>
											
											<option value="13">|-设备来源</option>
											
											<option value="14">|-软件配置</option>
											
									<option value="41">|-显示名称</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="PRE1" id="PRE1" style="width: 40px">
									<option value=""></option>
									<option value="-">-</option>
									<option value="(">(</option>
									<option value="（">（</option>
									<option value="[">[</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="NUM1" id="NUM1" style="width: 80px">
									<option value="A">数字</option>
									<option value="C">汉字</option>
									<option value="R">大写汉字</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="END1" id="END1" style="width: 40px">
									<option value=""></option>
									<option value=")">)</option>
									<option value="）">）</option>
									<option value="]">]</option>
								  </select>								  </td>
                                </tr>
                              </table>							  </td>
							</tr>
							<tr>
							  <td width="14%" height="12" valign="top" nowrap bgcolor="#EBF4F5">流水后缀(二):</td>
							  <td width="86%" bgcolor="#FFFFFF">
							  <table width="80%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td width="99%">
								  
								  <select style="width: 100%" name="ADD2" id="ADD2">
									<option value="0">后缀于..</option>
									
											<option value="1">|-配置项编码</option>
											
											<option value="2">|-使用人</option>
											
											<option value="3">|-放置地点</option>
											
											<option value="4">|-设备型号</option>
											
											<option value="5">|-财务编号</option>
											
											<option value="6">|-出厂编号</option>
											
											<option value="7">|-应用范围</option>
											
											<option value="8">|-供货商</option>
											
											<option value="9">|-联系方式</option>
											
											<option value="10">|-保修期</option>
											
											<option value="11">|-购货日期</option>
											
											<option value="12">|-设备单价</option>
											
											<option value="13">|-设备来源</option>
											
											<option value="14">|-软件配置</option>
											
									<option value="41">|-显示名称</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="PRE2" id="PRE2" style="width: 40px">
									<option value=""></option>
									<option value="-">-</option>
									<option value="(">(</option>
									<option value="（">（</option>
									<option value="[">[</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="NUM2" id="NUM2" style="width: 80px">
									<option value="A">数字</option>
									<option value="C">汉字</option>
									<option value="R">大写汉字</option>
								  </select>								  </td>
                                  <td style="padding-left: 5px">
								  
								  <select name="END2" id="END2" style="width: 40px">
									<option value=""></option>
									<option value=")">)</option>
									<option value="）">）</option>
									<option value="]">]</option>
								  </select>								  </td>
                                </tr>
                              </table>							  </td>
							</tr>
							
							<input name="StartID" id="StartID" type="hidden" value="1">
							<input name="Length" id="Length" type="hidden" value="3">
						  </table>						  </td>
						</tr>
					  </table>					</td>
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
  <tr onMouseDown="document.getElementById('Layer5').style.visibility='hidden'">
	<td height="12" style="padding-top: 8px" nowrap><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
    <td height="30" align="center"  nowrap>
	  <input type="button" value="生成配置项" onClick="if(confirm('保存后将无法撤消，\n\n你确定要保存所有记录吗？')){myForm.submit()}" class=mmBtn name="button" >
	<input type="button" onClick="window.location.reload()" value="重设" class=mmBtn>	</td>
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
