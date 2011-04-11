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
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看配置文档:</td>
  </tr>
</table>
<table width="100%" height="95%" border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
  <tr>
    <td bgcolor="#FFFFFF">
	<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF">
              <tr>
                <td height="12" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px">标题:</td>
                <td height="12" colspan="3" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">冯驰: 测试-开发项目 - 可行性分析 - xyz软件开发项目_业务可行性分析_V0001</div>
                </td>
              </tr>
              <tr>
                <td width="16%" height="12" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px">作者:</td>
                <td width="30%" height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">冯驰</div>
                </td>
                <td width="16%" height="12" valign="top" nowrap bgcolor="#B5D6E6" class="td-left" style="padding-right: 35px">产生日期:</td>
                <td width="34%" height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">2008-7-29</div>
                </td>
              </tr>
              <tr>
                <td height="12" rowspan="2" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px">关键词:</td>
                <td height="99%" colspan="3" bgcolor="#EBF4F5" style="padding-left: 7px">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                    <tr>
                      <td valign="top">
                        <div style="position: absolute; overflow-y: Auto; height: 100%; width: 100%; overflow-x: hidden; font-weight: normal; line-height: 22px"> XYZ_业务可行性分析_V0001.doc </div>
                      </td>
                    </tr>
                    <tr>
                      <td height="1%" align="right" style="padding-top: 4px; padding-right: 2px">
                        <input type="button" value="下载日志" class=mmBtn_sm name="button" onClick="window.open('../prob/?NowAction=docuhis&ID=DOC080000001','','width=600,height=500,scrollbars=no,menubar=no,resizable=no,top=100,left=176,status=yes')">
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
              <tr>
                <td height="27" colspan="3" nowrap bgcolor="#EBF4F5" style="padding-left: 6px; font-weight: normal"><IMG ALIGN='ABSMIDDLE' src="../../img/word.gif"><a OnClick="window.open('../home/?NowAction=docview&DocNo=DOC080000001&DocTitle=冯驰: 测试-开发项目 - 可行性分析 - xyz软件开发项目_业务可行性分析_V0001&FileName=F_080729_182544_945_FENGCHI.doc','DocWin','top=50,left=50,width=600,height=500,resizable=1')" style='cursor: hand'> DEMO演示_V03.DOC</td>
              </tr>
              <tr>
                <td width="16%" height="12" rowspan="2" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px">相关项目:</td>
                <td height="12" rowspan="2" valign="top" bgcolor="#EBF4F5" style="padding-top: 2px; padding-bottom: 2px">
                  <table border="0" cellspacing="0" cellpadding="3" width="100%">
                    
                    <tr onClick="window.open('../chan/?NowAction=projectdetail&ProNo=PRJ080000001','','width=900,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes')" style="cursor: hand" title="PRJ080000001">
                      <td nowrap><b>PRJ080000001</b></td>
                      <td width="1%"><img src="../../img/check.jpg" width="18" height="18" border="0" align="absmiddle" style="border: 1px outset white"></td>
                    </tr>
                    
                    <tr onClick="window.open('../prob/?NowAction=detailtask&TskID=TS080000003','','width=680,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes')" style="cursor: hand" title="TS080000003">
                      <td style='border-top: 1px solid white' nowrap><b>TS080000003</b></td>
                      <td width="1%" style='border-top: 1px solid white'><img src="../../img/check.jpg" width="18" height="18" border="0" align="absmiddle" style="border: 1px outset white"></td>
                    </tr>
                    
                  </table>
                </td>
                <td height="12" nowrap bordercolor="#B5D6E6" bgcolor="#B5D6E6" class="td-left" style="padding-right: 35px">内部编号:</td>
                <td height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">P_xyz_v0001</div>
                </td>
              </tr>
              <tr>
                <td width="16%" height="12" nowrap bordercolor="#B5D6E6" bgcolor="#B5D6E6" class="td-left" style="padding-right: 35px">地址:</td>
                <td height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)"></div>
                </td>
              </tr>
              
              <s:form name=myForm2 id=myForm2 action='../prob/default.asp?NowAction=db_sla&type=SaveSoluNEW&ID=1' method='post' target="sum">
                <tr>
                  <td height="12" rowspan="2" bgcolor="#b5d6e6" class="td-left" style="padding-top: 5px">新加版本:</td>
                  <td height="20" colspan="3" bgcolor="#EBF4F5" style="padding-top: 0px; padding-bottom: 0px">
                    <table width="100%" border="0" cellspacing="3" cellpadding="0">
                      <tr>
                        <td>
                          <textarea name="UpFile" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td height="20" colspan="2" bgcolor="#EBF4F5">
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td nowrap>
							<input type="file" name="filename" style="width: 50%">
							<input type="submit" name="sub1" value="upload.." style="width: 65px">
						</td>
					  </tr>
					</table>
                  </td>
                  <td height="20" bgcolor="#EBF4F5" style="padding-top: 0px; padding-bottom: 0px">
                    <table width="20" border="0" cellspacing="0" cellpadding="4">
                      <tr>
                        <td width="1%" valign="bottom"><img src="../../img/savefile.gif" width="14" height="14" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" title="保存文件" onClick="if(document.getElementById('UpFile').value!=''){myForm2.submit()}else{alert('请先上传文件！')}"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </s:form>
              
              <tr>
                <td width="16%" height="12" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px"><img src="../../img/headw.gif" width="16" height="16" align="absmiddle">冯驰:</td>
                <td height="27" colspan="3" valign="top" nowrap bgcolor="#EBF4F5" style="padding-left: 6px; font-weight: normal">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="99%"><IMG ALIGN='ABSMIDDLE' src="../../img/word.gif"><a OnClick="window.open('../home/?NowAction=docview&DocNo=DOC080000001&DocTitle=冯驰: 测试-开发项目 - 可行性分析 - xyz软件开发项目_业务可行性分析_V0001&FileName=F_080729_182407_963_FENGCHI.doc','DocWin','top=50,left=50,width=600,height=500,resizable=1')" style='cursor: hand'> DEMO演示_V02.DOC</td>
                      <td nowrap valign="bottom" style="padding: 1px; padding-right: 4px"><a style="padding: 2px; background-image:url(../img/tr.gif); border: 1px solid white">&nbsp;08-07-29 18:24:10&nbsp;</a></td>
                      
                      <td style="padding: 1px; padding-left: 4px" valign="bottom"><a href="../prob/default.asp?NowAction=db_sla&type=UpSolu&RequNo=1&ID=2" onClick="return confirm('你确认要更新此版本为标准版本吗？')" target="sum" title="更新为标准版"><img src="../../img/pageok.gif" width="18" height="18" border="0"></a></td>
                      <td style="padding: 1px; padding-left: 2px" valign="bottom"><a href="../prob/default.asp?NowAction=db_sla&type=DelSolu&RequNo=1&ID=2" onClick="return confirm('你确认要删除这个版本吗？')" target="sum" title="删除版本"><img src="../../img/del.gif" width="18" height="18" border="0"></a></td>
                      
                    </tr>
                  </table>
                </td>
              </tr>
              
              <tr>
                <td width="16%" height="12" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px"><img src="../../img/headw.gif" width="16" height="16" align="absmiddle">张晓曦:</td>
                <td height="27" colspan="3" valign="top" nowrap bgcolor="#EBF4F5" style="padding-left: 6px; font-weight: normal">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="99%"><IMG ALIGN='ABSMIDDLE' src="../../img/word.gif"><a OnClick="window.open('../home/?NowAction=docview&DocNo=DOC080000001&DocTitle=冯驰: 测试-开发项目 - 可行性分析 - xyz软件开发项目_业务可行性分析_V0001&FileName=F_080814_103426_349_ZHANGXX.doc','DocWin','top=50,left=50,width=600,height=500,resizable=1')" style='cursor: hand'> DEMO演示_V01.DOC</td>
                      <td nowrap valign="bottom" style="padding: 1px; padding-right: 4px"><a style="padding: 2px; background-image:url(../img/tr.gif); border: 1px solid white">&nbsp;08-08-14 10:34:27&nbsp;</a></td>
                      
                      <td style="padding: 1px; padding-left: 4px" valign="bottom"><a href="../prob/default.asp?NowAction=db_sla&type=UpSolu&RequNo=1&ID=6" onClick="return confirm('你确认要更新此版本为标准版本吗？')" target="sum" title="更新为标准版"><img src="../../img/pageok.gif" width="18" height="18" border="0"></a></td>
                      <td style="padding: 1px; padding-left: 2px" valign="bottom"><a href="../prob/default.asp?NowAction=db_sla&type=DelSolu&RequNo=1&ID=6" onClick="return confirm('你确认要删除这个版本吗？')" target="sum" title="删除版本"><img src="../../img/del.gif" width="18" height="18" border="0"></a></td>
                      
                    </tr>
                  </table>
                </td>
              </tr>
              
              <tr>
                <td width="16%" height="12" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 35px">创建人:</td>
                <td height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">冯驰</div>
                </td>
                <td width="16%" height="12" valign="top" nowrap bgcolor="#B5D6E6" class="td-left" style="padding-right: 35px">时间:</td>
                <td height="12" valign="top" bgcolor="#EBF4F5" style="padding-top: 3px; padding-left: 3px">
                  <div class='DivOut' style="behavior: url(../cn_css/datagrid_div.htc)">2008-7-29 18:22:03</div>
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
