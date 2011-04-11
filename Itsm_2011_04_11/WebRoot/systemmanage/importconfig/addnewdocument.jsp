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
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">创建新的文档:</td>
  </tr>
</table>
<table width="100%" height="85%" border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
  <tr>
    <td bgcolor="#FFFFFF"><table width="100%" height="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#FFFFFF">
	<s:form name=myForm id=myForm action='../prob/default.asp?NowAction=db_pro&type=SaveDoc&Refresh=1' method='post' target="sum">
	<input type="hidden" name="RequObj" id="RequObj" value="|1,">
	<input type="hidden" name="Projects" id="Projects" value="">
	<input type="hidden" name="AddTitle" id="AddTitle" value="">
	<input type="hidden" name="DocNo" id="DocNo" value="">
                <tr> 
                  <td width="15%" height="30" nowrap bgcolor="#b5d6e6" style="padding-right: 25px">文档名:</td>
                  <td width="85%" height="12" colspan="3" bgcolor="#EBF4F5"><input name="Title" type="text" id="Title" style="width: 100%" value="" maxlength="50"></td>
                </tr>
                <tr> 
                  <td width="15%" height="30" bgcolor="#b5d6e6">使用范围:</td>
                  <td width="85%" height="12" colspan="3" bgcolor="#EBF4F5">
				  <select size="1" style="width: 90%" name="RequDept" id="RequDept">
					
					<option value="">全局</option>
					
							<option value="|6,">成都区域</option>
							
				  </select>
				  </td>
                </tr>
                <tr> 
                  <td width="15%" height="30" nowrap bgcolor="#b5d6e6" style="padding-right: 25px">作者:</td>
                  <td width="35%" height="12" bgcolor="#EBF4F5"><input name="Writer" type="text" id="Writer" style="width: 80%" value="杨岑" maxlength="50"></td>
                  <td width="15%" height="12" nowrap bgcolor="#b5d6e6" style="padding-right: 25px">地址:</td>
                  <td width="35%" height="12" bgcolor="#EBF4F5"><input name="Location" type="text" id="Location" style="width: 100%" maxlength="50" value=""></td>
                </tr>
                <tr> 
                  <td height="30" nowrap bgcolor="#b5d6e6" style="padding-right: 25px">产生日期:</td>
                  <td height="12" bgcolor="#EBF4F5"><input name="Insert_Date" type="text" id="Insert_Date" style="width: 80%" onClick="gfPop.fPopCalendar(this);return false;" readonly value=""></td>
                  <td height="12" nowrap bgcolor="#b5d6e6" style="padding-right: 25px">内部编号:</td>
                  <td height="12" bgcolor="#EBF4F5"><input name="Source_No" type="text" id="Source_No" style="width: 100%" maxlength="50" value=""></td>
                </tr>
                <tr> 
                  <td width="15%" height="30" nowrap bgcolor="#b5d6e6" style="padding-right: 40px">目录:</td>
                  <td width="85%" height="12" colspan="3" bgcolor="#EBF4F5"><input type="text" name="Catename" style="width: 70%; cursor: text" readonly value="项目文档"><br>
                  <div id="Layer2" style="position:absolute; width: 70%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white"><iframe frameborder="0" height="130" width="100%" scrolling="yes" src="../home/?NowAction=aptree&ID=Document_Cat&Sear=1" style="border: 1px solid #E5E9EE"></iframe></div></td>
                </tr>
                <tr> 
                  <td height="30" nowrap bgcolor="#b5d6e6" style="padding-top: 5px">关键字:</td>
                  <td colspan="3" bgcolor="#EBF4F5"><textarea name="KeyWord" style="line-height: 19px; width: 100%; height: 100%; padding-right: 5px; padding-left: 5px"></textarea> 
                  </td>
                </tr>
                <tr> 
                  <td rowspan="2" bgcolor="#b5d6e6" style="padding-top: 5px">附件:</td>
                  <td height="20" colspan="3" bgcolor="#EBF4F5" style="padding-top: 0px; padding-bottom: 0px"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr> 
						<td><textarea name="UpFile" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../../img/pp.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly onDblClick="window.open('../home/?NowAction=attach&Item=UpFile&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea></td>
						<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" valign="bottom"><img src="../../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile').value=''" title="清除附件"></td>
                      </tr>
                  </table></td>
                </tr>
                <tr> 
                  <td height="20" colspan="3"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td nowrap>
			<input type="file" name="filename" style="width: 50%">
			<input type="submit" name="sub1" value="Upload.." style="width: 65px">
		</td>
		<td width="1%" title="Link to.."><img src="../../img/check.jpg" width="18" height="18" style="cursor: hand" onClick="window.open('addlink.jsp','','width=500,height=400,scrollbars=yes,menubar=no,resizable=yes,top=50,left=50,status=yes')"></td>
	  </tr>
</table></td>
           </tr></s:form>
</table></td>
  </tr>
</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
		  <tr>
			<td width="4%" height="12" nowrap style="padding-top: 8px"><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
			<td width="96%" align=center nowrap style="padding-top: 8px; padding-bottom: 0px"><input type="button" value="保存文档" onClick="if(confirm('您确认要保存吗？')){myForm.submit()}" class=mmBtn name="button">&nbsp;<input type="button" onClick="if(confirm('您确认要放弃保存吗？')){window.close()}" value="放弃" class=mmBtn name="button"></td>
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
