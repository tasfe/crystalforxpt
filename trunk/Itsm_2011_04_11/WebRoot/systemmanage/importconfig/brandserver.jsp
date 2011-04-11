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
  <table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                    <tr> 
                      <input type="hidden" name="RecState" value="|230,|239,">
                      <td width=9% align="center" nowrap bgcolor="#EBF4F5" class="td-left" onMouseDown="document.getElementById('Layer').style.visibility='hidden'">关键字:</td>
                      <td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px" onMouseDown="document.getElementById('Layer').style.visibility='hidden'">
					  <input name="ZiCDM" id="ZiCDM" value="" style="width: 100%">                      </td>
					  
                      <td width=9% align="center" nowrap bgcolor="#EBF4F5" class="td-left" onMouseDown="document.getElementById('Layer').style.visibility='hidden'">自定义:</td>
                      <td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px"> 
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40%">
							<input type="text" name="TbName" style="width: 95%; cursor: default; border-right: 0px; background-color: #FCFCFC" readonly value="" onClick="document.getElementById('Layer').style.visibility='visible'"><input name="TbID" id="TbID" value="" type="hidden"><input name="User" id="User" value="" type="hidden"><br><div id="Layer" style="position:absolute; width: 100%; height:1px; z-index:99; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
							<table width="100%" border="0" cellspacing="1" cellpadding="1" style="border: 1px solid #E5E9EE; cursor: default" bgcolor="#FFFFFF">
							
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='条形码';window.document.getElementById('TbID').value='TiaoXM';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;条形码&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='品牌';window.document.getElementById('TbID').value='PinP';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;品牌&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='规格一';window.document.getElementById('TbID').value='GuiG_1';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;规格一&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='规格二';window.document.getElementById('TbID').value='GuiG_2';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;规格二&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='规格三';window.document.getElementById('TbID').value='GuiG_3';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;规格三&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='规格四';window.document.getElementById('TbID').value='GuiG_4';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;规格四&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='型号';window.document.getElementById('TbID').value='XingH';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;型号&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='出厂批号';window.document.getElementById('TbID').value='ChuCXH';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;出厂批号&nbsp;</td></tr>
								  
								  <tr><td bgcolor="white" width="1" style="padding-top: 2px; padding-right: 0px"><img src="../../img/info.gif" height="15"></td>
								  <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.document.getElementById('TbName').value='单价';window.document.getElementById('TbID').value='DanJ';document.getElementById('Layer').style.visibility='hidden'" nowrap>&nbsp;单价&nbsp;</td></tr>
								  
							</table>
							</div>
							</td>
                            <td width="1%"><img src="../../img/chose.jpg" width="16" height="16" style="cursor:hand;" onClick="document.getElementById('Layer').style.visibility='visible'"></td>
                            <td width="60%" align="center" onMouseDown="document.getElementById('Layer').style.visibility='hidden'"><input name="TiaoXM" id="TiaoXM" value="" style="width: 95%; border-left: 0px"></td>
                          </tr>
                        </table>
                      </td>
                      
					  <td width=9% align="center" nowrap bgcolor="#EBF4F5" class="td-left" onMouseDown="document.getElementById('Layer').style.visibility='hidden'">使用状态:</td>
                      <td width="23%" bgcolor="#FFFFFF" style="padding-right: 20px" onMouseDown="document.getElementById('Layer').style.visibility='hidden'">
						<select name="SheBMC" id="SheBMC" style="width: 100%">
						  
						  <option value=""></option>
						  <option value="NEW">新建</option>
						  <option value="SYZ">正常使用中</option>
						  
					  </select>                      </td>
                      <td width="2%" nowrap bgcolor="#FFFFFF" onMouseDown="document.getElementById('Layer').style.visibility='hidden'"> 
                        <input type="button" style="height: 20px" class="mmBtn" value="搜索" onClick="SeekOnClick('AddTime','Desc'); this.disabled=true;">
						
						<input type="button" style="height: 20px" class="mmBtn" value="综合查询" onClick="location.href='../asst/?NowAction=assetssearchcate&RecState=|230,|239,&Doit=1'">
						
                      </td>
                    </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td onClick="document.getElementById('thelayer').style.display='none'">&nbsp;</td>
    </tr>
    <tr>
      <td width="99%" height="25" onClick="document.getElementById('thelayer').style.display='none'"><img src="../../images/main20100521dot04.gif">&nbsp;<b>按配置分类查看:</b>&nbsp;</td>
    </tr>
  </table>
  <table width="98%" border=0 align="center" cellpadding=4 cellspacing=1 bgcolor="#b5d6e6">
    <tr>
      <td width=10% height="22" align="center" nowrap background="../../images/main20100521_58.gif"><font class="alllisttitle" style='cursor:hand' onclick="SeekOnClick('ZiCDM','DESC');">代码</font></td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('SheBMC','DESC');">名称</font> </td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('ZiCLB','DESC');">类别</font> </td>
      <td width="30%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('SouSBM','DESC');">所属部门</font> </td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle"> <font style='cursor:hand' onclick="SeekOnClick('ShiYR','DESC');">管理员</font> </td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">事件</td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">变更</td>
      <td width="10%" height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">问题</td>
      <td width=10% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">关系</td>
      <td width=10% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">详细</td>
    </tr>
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      <td height="22" align="center" nowrap>PPSRVD000000001</td>
      <td height="22" align="center" nowrap><span class="DivOut">DELL服务器</span></td>
      <td height="22" align="center" nowrap><span class="DivOut">品牌服务器</span></td>
      <td height="22" align="center"><span class="DivOut">成都XX地产发展有限公司/公共事务及行政部/ </span></td>
      <td height="22" align="center" nowrap><span class="DivOut">杨岑</span></td>
      <td align="center" class="datagrid">0</td>
      <td align="center" class="datagrid">1</td>
      <td align="center" class="datagrid">0</td>
      <td height="22" align="center" nowrap style="text-align: center"><a href="javascript:" title="树形目录" onClick="document.all.myrelation.style.display=''"><img src="../../img/relation.gif" width="19" height="19" border="0"></a></td>
      <td height="22" align="center" nowrap style="text-align: center"><a href="Javascript:" onClick="window.open('../asst/?NowAction=assetsdetail&AssNo=PPSRVD000000001','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')"><img src="../../img/viewdetail2.gif" width="20" height="19" border="0"></a></td>
    </tr>
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      <td height="22" align="center" nowrap>PPSRVD000000002</td>
      <td height="22" align="center" nowrap><span class="DivOut">HP服务器</span></td>
      <td height="22" align="center" nowrap><span class="DivOut">品牌服务器</span></td>
      <td height="22" align="center"><span class="DivOut">成都XX地产发展有限公司/公共事务及行政部/</span></td>
      <td height="22" align="center" nowrap><span class="DivOut">杨岑</span></td>
      <td align="center" class="datagrid">0</td>
      <td align="center" class="datagrid">1</td>
      <td align="center" class="datagrid">0</td>
      <td height="22" align="center" nowrap style="text-align: center"><a href="javascript:" title="树形目录" onClick="document.all.myrelation.style.display=''"><img src="../../img/relation.gif" width="19" height="19" border="0"></a><a href="javascript:" title="树形目录" onClick="javascript:window.parent.document.getElementById('bottom').style.display='';javascript:window.parent.bottomFrame.location='../task/?NowAction=treeview&ID=PPSRVD000000001'"></a></td>
      <td height="22" align="center" nowrap style="text-align: center"><a href="Javascript:" onClick="window.open('../asst/?NowAction=assetsdetail&AssNo=PPSRVD000000001','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')"><img src="../../img/viewdetail2.gif" width="20" height="19" border="0"></a></td>
    </tr>
  </table>
  <table width="98%" height="30" border=0 align="center" cellpadding=0 cellspacing=0>
    <tr>
      <td align=center nowrap width="10%" style="padding-top: 6px; padding-bottom: 1px"><input name="button" type="button" class="mmBtn" onClick="history.back()" value="后退">
        <input name="button" type="button" class="mmBtn" onClick="window.open('customize.jsp','','width=400,height=350,scrollbars=yes,menubar=no,resizable=no,top=60,left=100,status=yes')" value="自定义">      </td>
    </tr>
    <tr>
      <td align=center nowrap style="padding-top: 6px; padding-bottom: 1px">&nbsp;</td>
    </tr>
  </table>
  
  <table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" id="myrelation" style="display:none;">

    <tr>
      <td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding-left:5px;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><span class="alllisttitle" style="padding-left:5px;">关系</span></td>
          <td align="right"><a href="javascript:" title="树形目录" onClick="javascript:window.parent.document.getElementById('bottom').style.display='';javascript:window.parent.bottomFrame.location='../task/?NowAction=treeview&ID=PPSRVD000000001'"><img src="../../img/close.jpg" width="15" height="13" border="0" style="cursor:hand;" onClick="document.all.myrelation.style.display='none'"></a></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF">
      <td height="120" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr>
          <td style="line-height: 19px; font-family: Tahoma" valign="top" width="47%" nowrap><div style="position: absolute; overflow: scroll; height: 100%; width: 100%">
              <div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 6px solid white" onClick="if (document.getElementById('PPSRVD000000001233').style.display=='none') { document.getElementById('PPSRVD000000001233').style.display='';this.src='../../img/jian.jpg' } else { document.getElementById('PPSRVD000000001233').style.display='none';this.src='../../img/jia.jpg' }; if (document.getElementById('PPSRVD000000001233').innerHTML=='') { window.Treeview.location='../task/?NowAction=treerequest&id=PPSRVD000000001&ref=233&Now=PPSRVD000000001';document.getElementById('PPSRVD000000001233').style.display='';this.src='../../img/jian.jpg' }"><img src="../../img/icon.jpg" align="absmiddle" width="16">&nbsp;&nbsp;<a style="border: 1px solid white; cursor: hand; color: red" onMouseOver="this.style.border='1px solid #8596CA';this.style.backgroundColor='#EEF8ED'" onMouseOut="this.style.border='1px solid white';this.style.backgroundColor='white'" onClick="window.open('../asst/?NowAction=assetsdetail&AssNo=PPSRVD000000001','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')">&nbsp;PPSRVD000000001:&nbsp;[DELL服务器]&nbsp;- [品牌服务器] - []&nbsp;</a>
                  <div id="PPSRVD000000001233" style="padding-left: 20px; display: none"></div>
              </div>
          </div></td>
          <td style="border-right: 1px solid gray; line-height: 14px; padding: 1px" width="3" bgcolor="#DEDEDE" valign="top">&nbsp;</td>
          <td style="line-height: 19px; font-family: Tahoma; padding-left: 1px" valign="top" width="53%" bgcolor="#FFFFFF" nowrap><div style="position: absolute; overflow: scroll; height: 100%; width: 100%"> <img src="../../img/jian.jpg" width="11" height="11" align="absmiddle" style="border: 5px solid white"><img src="../../img/cata.jpg" align="absmiddle">&nbsp;&nbsp;<a style="border: 1px solid white;" onMouseOver="this.style.border='1px solid #8596CA';this.style.backgroundColor='#EEF8ED'" onMouseOut="this.style.border='1px solid white';this.style.backgroundColor='white'">&nbsp;<b>相关联配置</b>&nbsp;</a><br>
                  <div id="ass" style="padding-left: 19px"> </div>
          </div></td>
        </tr>
      </table></td>
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
