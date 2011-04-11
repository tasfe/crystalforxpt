<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
.onOver {
	Border : 1px solid white;
}
A:hover {
	color: #000000;
	text-decoration: none;
	background-color: #EEF8ED;
	Border-Bottom: 1px solid #333333;
	Border-Right: 1px solid #333333;
	Border-Top: 1px solid #C6CFD8;
	Border-Left: 1px solid #C6CFD8;
}
</style>

<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">请先选择您所需服务的类别:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
<table width="99%" height="80%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="30" border="0" cellpadding="2" cellspacing="0">
      <tr>
        <td height="30" bgcolor="#FFFFFF"><img src="../../images/main20100521dot04.gif">&nbsp;位置:&nbsp;主页&nbsp;&gt; IT报事服务 &gt; 硬件</td>
        <td width="1%" height="30" nowrap bgcolor="#FFFFFF" style="cursor: hand; padding-right: 0px" onClick="document.getElementById('TreeP').style.visibility='visible';document.getElementById('Noti').style.visibility='hidden'"><a>&nbsp;<img src="../../img/addfastrequest.gif" width="17" height="17" align="absmiddle">&nbsp;快速选择服务&nbsp;&nbsp;</a></td>
      </tr>
      <tr>
        <td valign="top" width="100%" height="1" colspan="2"><div id="TreeP" name="TreeP" style="position: absolute; width: 100%; z-index: 1; visibility: hidden;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
              <tr>
                <td>&nbsp;</td>
                <td width="35%" style="padding: 8px; border: 1px outset" background="../../img/cldbg.jpg"><iframe frameborder="0" height="280" width="100%" scrolling="yes" src="../home/?NowAction=treeview&ID=Service_Cat&Type=SB" style="border: 1px inset"></iframe></td>
              </tr>
          </table></td>
      </tr>
    </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top" background="../../img/Separator.gif" style="line-height:5px;"><img src="../../img/Separator.gif" width="6" height="6"></td>
        </tr>
      </table>
	  <div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 80%; width: 100%; padding: 0px">
      <table width="100%" height="100" border="0" align="center" cellpadding="5" cellspacing="0">
        <tr>
          <td width="30%" valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onClick="location.href='addnewdetail.jsp'" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 主机无显示 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与主机无显示相关的服务请求。</td>
            </tr>
          </table></td>
          <td width="30%" valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 安装调试新硬件 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与安装调试新硬件相关的服务请求。</td>
            </tr>
          </table></td>
          <td width="30%" valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 客户端硬件故障 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与客户端硬件故障相关的服务请求。</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 检测硬件 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与检测硬件相关的服务请求。</td>
            </tr>
          </table></td>
          <td valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 办公内网网络申请 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与办公内网网络申请相关的服务请求。</td>
            </tr>
          </table></td>
          <td valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 其它故障 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与其它故障相关的服务请求。</td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td valign="top" bgcolor="#FFFFFF" style="border: 1px solid white; cursor: hand" onMouseOver="this.style.border='1px solid #527FB3';this.bgColor='#EAF1F8'" onMouseOut="this.style.border='1px solid white';this.bgColor='#FFFFFF'"><table width="100%" border=0 cellpadding=3 cellspacing=0>
            <tr>
              <td width="3%" rowspan="2" align="right" valign="top" nowrap style="padding-left: 0px; padding-right: 6px; padding-top: 0px"><img src="../../img/addicon.gif" width="64" height="64"></td>
              <td width="94%" nowrap style="line-height: 18px"><b> 桌面机硬件升级 </b></td>
            </tr>
            <tr>
              <td height="60" valign="top">提交与桌面机硬件升级相关的服务请求。</td>
            </tr>
          </table></td>
          <td valign="top" bgcolor="#FFFFFF">&nbsp;</td>
          <td valign="top" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
      </table>
	 </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right"><table width="20%" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="../../img/category.jpg" /></td>
        <td background="../../img/category_bg.jpg"><table border="0" cellspacing="0" cellpadding="3" width="100%">
            <tr>
              <td style="padding-left: 0px;"><img src="../../img/tongji.gif" align="absmiddle" height="21" /></td>
              <td style="padding-left: 2px"><select name="AssCate" id="AssCate" style="width: 320px">
                  <option value="|229," style="background-color: #E4E7EF">变更请求: 员工入职IT信息管理申请</option>
                  <option value="|228," style="background-color: #E4E7EF">变更请求: 员工离职IT信息管理变更</option>
                  <option value="|230," style="background-color: #E4E7EF">变更请求: 员工调动IT信息管理变更</option>
                  <option value="|283," style="background-color: #E4E7EF">变更请求: 程序变更及发布</option>
                </select>
              </td>
              <td><input type="button" value="提交" class="mmBtn_sm" name="button" onclick="window.open('../asst/?NowAction=changeapply&amp;RequObj='+document.getElementById('AssCate').value+'&amp;Title='+document.getElementById('AssCate').options[document.getElementById('AssCate').selectedIndex].text,'','width=900,height=514,scrollbars=yes,menubar=no,resizable=yes,top=60,left=60,status=yes')" />
              </td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</body>
</html>
