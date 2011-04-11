<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Add Configurations</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>
<body style="overflow: hidden">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">请选择配置类别:</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../../images/space.gif" width="5" height="5"></td>
  </tr>
</table>
<table width="98%" height="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td valign="top" id="mainright">
	<input type="hidden" name="ZiCLB" id="ZiCLB" value="">
    <table width="100%" height="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
	  <tr> 
		<td height="99%" valign="top" bgcolor="#FFFFFF">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
          <tr>
            <td style="border: 1px inset; padding-left: 5px"><iframe frameborder="0" height="100%" width="100%" scrolling="yes" id="Config" name="Config" src="treelist.jsp"></iframe></td>
          </tr>
        </table>
		</td>
	  </tr>
	</table>
	<div id="Layer3"></div>
	</td>
  </tr>
	<tr>
	  <td height="12"> 
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
		  <tr>
			<td height="12" style="padding-top: 8px; padding-right: 20px" nowrap><input type="text" name="ZiCLB_2" id="ZiCLB_2" style="width: 160px; height: 23px; padding: 3px; font-weight: bold; padding-left: 23px; background-image: url(../img/folbig.gif); background-repeat: no-repeat" readonly></td>
			<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px">
			<input type="button" onClick="if(document.getElementById('ZiCLB').value!=''){window.location='nexthardware.jsp'}else{alert('必须选择一个配置类别！')}" value="下一步…" class=mmBtn>
			</td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</body>
</html>
<script language=JavaScript>
if ((window.screen.width-document.body.clientWidth)>100) {
	window.moveTo(100,100);
	window.resizeTo(500,400);
}
</script>
<Script Language=Javascript src="../cn_css/mmBtn.js"></Script>