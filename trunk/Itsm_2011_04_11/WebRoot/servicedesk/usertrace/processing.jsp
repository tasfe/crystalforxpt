<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body onmousedown="document.getElementById('TreeP').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">用户跟踪请求处理情况:</td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table cellspacing=0 cellpadding=0 border=0 width="100%">
      <tr>
        <td nowrap><img src="../../img/help2.jpg" width="23" height="24" align="absmiddle">&nbsp;</td>
        <td width="99%" onClick="document.getElementById('thelayer').style.display = 'none'">&nbsp;<b>所有未结束请求:</b></td>
        <td align=right colspan=2 width="1%" height="28" valign="top" style="padding-top: 4px"><table border="0" cellspacing="0" cellpadding="0" width="100px">
            <tr>
              <td width="1"><img width="3"></td>
              <td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"  onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()" nowrap><input type="text" name="thetypes" value="根据状态查看" style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px" readonly>
              </td>
              <td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px; PADDING-TOP: 1px"><input type="button" value="6"  style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px; width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}" name="click">
              </td>
            </tr>
            <tr id="thelayer" onClick="this.style.display='none'" style="display: none">
              <td width="1"></td>
              <td height="2" colspan="2"><div style="position:absolute; width:100%; height:15px; z-index:9; cursor: default; overflow-x: hidden; border: 1px outset">
                  <table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../../img/@ServiceInput.gif" align=absmiddle></td>
                      <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState='">&nbsp;所有未结束请求</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../../img/task.gif" align=absmiddle></td>
                      <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=DEPT'">&nbsp;新创建</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../../img/page.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=WAIT'">&nbsp;等待派单</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../../img/ServiceInput.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=ACT'">&nbsp;工程师未受理</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../../img/wait.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../desk/?NowAction=servicesearchmy&Rq=1&Sort=RequTrueTime&Desc=Desc&RecState=IDO'">&nbsp;工程师正在处理中</td>
                    </tr>
                  </table>
              </div></td>
            </tr>
        </table></td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
        <tr bgcolor="#FFFFFF">
          <td height="22" align="center" background="../../images/main20100521_58.gif" class="alllisttitle">工单号</td>
          <td align="center" background="../../images/main20100521_58.gif" class="alllisttitle">服务类别</td>
          <td align="center" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">申请时间</td>
          <td align="center" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">开始处理时间</td>
          <td align="center" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">承诺完成时间</td>
          <td align="center" background="../../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">服务工程师</td>
          <td align="center" background="../../images/main20100521_58.gif" class="alllisttitle">状态</td>
          <td align="center" background="../../images/main20100521_58.gif" class="alllisttitle">查看</td>
          <td align="center" background="../../images/main20100521_58.gif" class="alllisttitle">进度</td>
        </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">1</td>
            <td align="center">2</td> <td align="center">2010-5-25</td> <td align="center">2010-5-25</td> <td align="center">2010-5-30</td> <td align="center">杨凯利</td>
            <td align="center">新创建</td>
            <td align="center"><img src="../../img/viewdetail.gif" align="absmiddle"><a href="viewdetail.jsp" target="_self">查看</a></td>
            <td align="center"><a href="processing.jsp"><img src="../../img/processing.gif" align="absmiddle" alt="查看进度"></a></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td>
            <td align="center"></td>
            <td align="center">&nbsp;</td>
            <td align="center"></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td>
            <td align="center"></td>
            <td align="center">&nbsp;</td>
            <td align="center"></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td>
            <td align="center"></td>
            <td align="center">&nbsp;</td>
            <td align="center"></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td>
            <td align="center"></td>
            <td align="center">&nbsp;</td>
            <td align="center"></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="20" align="center">&nbsp;</td>
            <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td> <td align="center">&nbsp;</td>
            <td align="center"></td>
            <td align="center">&nbsp;</td>
            <td align="center"></td>
          </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
