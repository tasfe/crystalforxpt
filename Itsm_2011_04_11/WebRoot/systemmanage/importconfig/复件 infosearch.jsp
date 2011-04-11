<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>配置库信息检索</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
function Search()
{
	
	Url = "../asst/?NowAction=assetssearch";
	Url = Url + "&Editor=" + document.getElementById("Editor").value;	
	Url = Url + "&ZiCDM=" + document.getElementById("ZiCDM").value;	
	Url = Url + "&SheBMC=" + document.getElementById("SheBMC").value;	
	Url = Url + "&ZiCLB=" + document.getElementById("ZiCLB").value;	
	Url = Url + "&SouSBM=" + document.getElementById("SouSBM").value;	
	Url = Url + "&ShiYR=" + document.getElementById("ShiYR").value;	
	Url = Url + "&AddTime1=" + document.getElementById("AddTime1").value;	
	Url = Url + "&AddTime2=" + document.getElementById("AddTime2").value;	
	Url = Url + "&EndTime1=" + document.getElementById("EndTime1").value;	
	Url = Url + "&EndTime2=" + document.getElementById("EndTime2").value;	
	Url = Url + "&BeiZ=" + document.getElementById("BeiZ").value;	
	Url = Url + "&Status=" + document.getElementById("Status").value;	
	Url = Url + "&Other=" + document.getElementById("Other").value;	
	Url = Url + "&andor=" + document.getElementById("andor").value;	
	Url = Url + "&Location=" + document.getElementById("Location").value;	
	document.location.href = Url;
}
function Change(Value)
{
	if (Value=="0")
	{
		document.getElementById("EndTime1").value = "2000-01-01"
		document.getElementById("EndTime2").value = "2010-6-1"
	}
	if (Value=="1")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-6-8"
	}
	if (Value=="2")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-6-15"
	}
	if (Value=="3")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-7-1"
	}
	if (Value=="4")
	{
		document.getElementById("EndTime1").value = "2010-6-1"
		document.getElementById("EndTime2").value = "2010-8-1"
	}
	if (Value=="5")
	{
		document.getElementById("Editor").value = "yangcen"
	}
	if (Value=="6")
	{
		document.getElementById("ShiYR").value = "yangcen"
	}
	if (Value=="7")
	{
		document.getElementById("AddTime1").value = "2010-01-01"
		document.getElementById("AddTime2").value = "2010-6-1"
	}
	if (Value=="8")
	{
		document.getElementById("AddTime1").value = "2010-6-01"
		document.getElementById("AddTime2").value = "2010-6-1"
	}
}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="line-height: 22px; background-color: white" class="td-bg" nowrap>
	
	<div><img src="../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 6px solid white" onClick="if (document.getElementById('11').style.display=='none') { document.getElementById('11').style.display='';this.src='../img/jian.jpg' } else { document.getElementById('11').style.display='none';this.src='../img/jia.jpg' }; if (document.getElementById('11').innerHTML=='') { window.Treeview.location='../home/?NowAction=treerequest_mana&id=Document_Cat&ref=1&Name=11&Type=&Cate=';document.getElementById('11').style.display='';this.src='../img/jian.jpg' }"><img src="../img/view.jpg" align="absmiddle" width="16">&nbsp;<a style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #8596CA';this.style.backgroundColor='#EEF8ED'" onMouseOut="this.style.border='1px solid white';this.style.backgroundColor='white'" onClick="window.parent.solution.location='../prob/?NowAction=documentlist&cate=|1,&par=1&CateName=项目文档&Cat='">&nbsp;项目文档&nbsp;</a><div id="11" style="padding-left: 20px; display: none"></div></div>
	
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
<Script Language=Javascript src="../../cn_css/mmBtn.js"></Script>