<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>创建新的文档目录</title>
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
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">添加附件链接:</td>
  </tr>
</table>
<table width="100%" height="85%" border="0" cellpadding="4" cellspacing="1" bgcolor="#FFFFFF">
			<tr> 
			  <td width="15%" height="12" bgcolor="#b5d6e6" class="td-left">链接地址:</td>
			  <td width="85%" height="12" bgcolor="#EBF4F5" class="td-right-s"><input type="text" name="Link" style="width: 100%; cursor: text" value="http://"></td>
			</tr>
			<tr> 
			  <td width="15%" height="12" bgcolor="#b5d6e6" class="td-left">文件名:</td>
			  <td width="85%" height="12" bgcolor="#EBF4F5" class="td-right-s"><input type="text" name="FileName" style="width: 100%; cursor: text" value=""></td>
			</tr>
			<tr> 
			  <td width="15%" height="12" valign="top" nowrap bgcolor="#b5d6e6" class="td-left" style="padding-right: 20px">链接测试结果:</td>
			  <td width="85%" height="85%" bgcolor="#EBF4F5" class="td-right-s"><iframe frameborder="0" height="100%" width="100%" scrolling="yes" style="border: 1px inset #FFFFFF" id="test" name="test" src="about:blank"></iframe></td>
			</tr>
</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
		  <tr>
			<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
			  <input type="button" value="保存并添加" onClick="window.location='file.asp?InputName=UpFile&CateName=项目文档&FilePath=|1,&Save=1&LinkUrl='+document.getElementById('Link').value+'&FileName='+document.getElementById('FileName').value" class=mmBtn name="button">
			<input type="button" value="测试" onClick="window.location='file.asp?InputName=UpFile&CateName=项目文档&FilePath=|1,&Save=0&LinkUrl='+document.getElementById('Link').value+'&FileName='+document.getElementById('FileName').value" class=mmBtn name="button">
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
