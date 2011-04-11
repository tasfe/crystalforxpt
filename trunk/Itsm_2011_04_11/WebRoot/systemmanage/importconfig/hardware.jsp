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
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a>&nbsp; </span><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a>&nbsp; </span><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a>&nbsp; </span><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
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
          <td align="right"></td>
        </tr>
      </table></td>    
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><a href="server.jsp">服务器</a></td>
    </tr>
    <tr height=33>
      <td align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px">&nbsp;</td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table cellspacing=0 width='100%' cellpadding=2 border=0 class='datagrid'>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all" bgcolor="#ffffff">&nbsp;<a href="brandserver.jsp">&nbsp;品牌服务器&nbsp;</a>&nbsp;<a style="color: red">(2)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格" onClick="window.open('../manage/?NowAction=assetstb&RecState=|230,|239,|243,&CateCode=PPSRV','','width=500,height=400,scrollbars=yes,menubar=yes,resizable=yes,top=50,left=50,status=yes')"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表" onClick="window.open('../manage/?NowAction=assetsls&RecState=|230,|239,|243,&CateCode=PPSRV','','width=500,height=400,scrollbars=yes,menubar=yes,resizable=yes,top=50,left=50,status=yes')"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置" onClick="window.open('../asst/?NowAction=assetsimport&Code=|230,|239,|243,&CateCode=PPSRV','','width=800,height=560,scrollbars=yes,menubar=no,resizable=yes,top=50,left=70,status=yes')"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;普PC服务器&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">桌面机</td>
    </tr>
    <tr height=33>
      <td align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px">&nbsp;</td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table cellspacing=0 width='100%' cellpadding=2 border=0 class='datagrid'>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all" bgcolor="#ffffff">&nbsp;&nbsp;笔记本&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;台式机&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">网络设备</td>
    </tr>
    <tr height=33>
      <td align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px">&nbsp;</td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table cellspacing=0 width='100%' cellpadding=2 border=0 class='datagrid'>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all" bgcolor="#ffffff">&nbsp;&nbsp;路由器&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;交换机&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;防火墙&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;其它类&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><img width="21" height="21" src="../../img/blank.gif"> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><img width="21" height="21" src="../../img/blank.gif"> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><img width="21" height="21" src="../../img/blank.gif"> </td>
              </tr>
          </table></td>
        </tr>
      </table></td>
    </tr>
    <tr height=33>
      <td width="1%" align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">外围设备</td>
    </tr>
    <tr height=33>
      <td align="center" bgcolor="#EBF4F5" style="padding-left:10px; padding-right:10px">&nbsp;</td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><table cellspacing=0 width='100%' cellpadding=2 border=0 class='datagrid'>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all" bgcolor="#ffffff">&nbsp;&nbsp;扫描仪&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;复印机&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;显示器&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;一体机&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
        </tr>
        <tr height=33>
          <td width="1%" align="right" style="padding-left: 30px; border-right: 1px solid #EBE9ED; border-top: 1px solid #EBE9ED" bgcolor="#ffffff"><img src="../../img/newdot001.gif" width="14" height="19" border="0"></td>
          <td nowrap style="word-break: keep-all; border-top: 1px solid #EBE9ED" bgcolor="#ffffff">&nbsp;&nbsp;打印机&nbsp;&nbsp;<a style="color: red">(0)</a></td>
          <td bgcolor="#ffffff" style="padding: 0px; border-top: 1px solid #EBE9ED" align="right"><table border="0" cellspacing="0" cellpadding="0" height="25">
              <tr>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="下载配置导入表格"><img src="../../img/csv.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="Javascript:" title="导出配置列表"><img src="../../img/xls.jpg" border="0"></a> </td>
                <td nowrap align="center" style="padding-right: 10px; padding-left: 10px; border-left: 1px solid #EBE9ED" bgcolor="#ffffff"><a href="javascript:" title="添加新配置"><img src="../../img/add.jpg" border="0"></a> </td>
              </tr>
          </table></td>
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
