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
  <table width="99%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
    </tr>
  </table>
  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="99%" onClick="document.getElementById('thelayer').style.display='none'"><img src="../../images/main20100521dot04.gif">&nbsp;<b>按配置分类查看:</b>&nbsp;</td>
    </tr>
  </table>
  <table width="99%" border=0 align="center" cellpadding=0 cellspacing=1 bgcolor="#b5d6e6">
    <tr>
      <td height=22 colspan="6" background="../../images/main20100521_58.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><b class="alllisttitle"><strong>&nbsp;&nbsp;</strong>硬件</b></td>
          <td align="right"><a href="hardware.jsp"><img src="../../img/ico_listdown.gif" width="20" height="20" border="0"></a></td>
        </tr>
      </table></td>    
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table width="100%"  border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td width="99%" bgcolor="#FFFFFF">&nbsp;<img src="../../img/open.gif" align="absmiddle" width="16" >&nbsp;<a href="hardware.jsp">&nbsp;点击展开: 硬件&nbsp;...&nbsp;</a></td>
            <td rowspan="4" align="center" nowrap bgcolor="#FFFFFF" style="padding-left:15px; padding-right:15px;">2</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td bgcolor="#FFFFFF">&nbsp;<img src="../../img/search.gif" align="absmiddle" width="16" >&nbsp;<a href="hardwaresearch.jsp">&nbsp;在“硬件”下检索配置、文档或资源&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/yhs.jpg" width="18" height="18" align="absmiddle" >&nbsp;<a href="../asst/?NowAction=assets&type=dept&ZiCLB=|230,&ZiCLB_2=硬件">&nbsp;按部门查看硬件&nbsp;...&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/Main_Set.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assets&type=stat&ZiCLB=|230,&ZiCLB_2=硬件">&nbsp;按使用状态查看硬件&nbsp;...&nbsp;</a></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td height=22 colspan="6" align="left" background="../../images/main20100521_58.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><b>软件</b></td>
            <td align="right"><a href="software.jsp"><img src="../../img/ico_listdown.gif" width="20" height="20" border="0"></a></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table width="100%"  border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td width="99%" bgcolor="#FFFFFF">&nbsp;<img src="../../img/open.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assets&type=cate&Par=231">&nbsp;点击展开: 软件&nbsp;...&nbsp;</a></td>
            <td rowspan="4" align="center" nowrap bgcolor="#FFFFFF" style="padding-left:15px; padding-right:15px;"> 0 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/search.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assetssearchcate&RecState=|231,">&nbsp;在“软件”下检索配置、文档或资源&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/yhs.jpg" width="18" height="18" align="absmiddle" >&nbsp;<a href="../asst/?NowAction=assets&type=dept&ZiCLB=|231,&ZiCLB_2=软件">&nbsp;按部门查看软件&nbsp;...&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/Main_Set.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assets&type=stat&ZiCLB=|231,&ZiCLB_2=软件">&nbsp;按使用状态查看软件&nbsp;...&nbsp;</a></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td height=22 colspan="6" background="../../images/main20100521_58.gif"><b class="alllisttitle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><b class="alllisttitle">人力资源</b></td>
            <td align="right"><a href="human.jsp"><img src="../../img/ico_listdown.gif" width="20" height="20" border="0"></a></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table width="100%"  border="0" cellpadding="3" cellspacing="1" bgcolor="#CCCCCC">
          <tr>
            <td width="99%" bgcolor="#FFFFFF">&nbsp;<img src="../../img/open.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assets&type=cate&Par=256">&nbsp;点击展开: 人力资源&nbsp;...&nbsp;</a></td>
            <td rowspan="4" align="center" nowrap bgcolor="#FFFFFF" style="padding-left:15px; padding-right:15px;">0 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/search.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assetssearchcate&RecState=|256,">&nbsp;在“人力资源”下检索配置、文档或资源&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/yhs.jpg" width="18" height="18" align="absmiddle" >&nbsp;<a href="../asst/?NowAction=assets&type=dept&ZiCLB=|256,&ZiCLB_2=人力资源">&nbsp;按部门查看人力资源&nbsp;...&nbsp;</a></td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;<img src="../../img/Main_Set.gif" align="absmiddle" width="16" >&nbsp;<a href="../asst/?NowAction=assets&type=stat&ZiCLB=|256,&ZiCLB_2=人力资源">&nbsp;按使用状态查看人力资源&nbsp;...&nbsp;</a></td>
          </tr>
      </table></td>
    </tr>
    <tr style="cursor: hand" onClick="window.open('../task/?NowAction=synch','','width=600,height=500,scrollbars=no,menubar=no,resizable=yes,top=80,left=126,status=yes')">
      <td colspan="6" height=22><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td background="../../images/main20100521_58.gif"><strong>&nbsp;&nbsp;数据同步映射关系</strong></td>
          <td width="1%" background="../../images/main20100521_58.gif" style="padding-right: 7px; cursor: hand"><img src="../../img/up.gif" width="22" height="22"></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>
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
