<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>分区管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>

<script> 

</script> 

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold; padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="99%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">分区管理</td>
  </tr>
</table>
<div style="position: absolute; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table id="table2" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
	  		<td background="../images/main20100521_63.gif" colspan="8" style="background-repeat:repeat-x;background-position: left bottom; height:21px; ">
	  		<img src="../images/modpass.gif" width="16" height="16"/>&nbsp;交换机&nbsp;</td>
	  		<td background="../images/main20100521_63.gif" colspan="8" style="background-repeat:repeat-x;background-position: left bottom; height:21px; " align="right">
	  		<img src="../images/22.gif" width="16" height="16"/>&nbsp;添加交换机分区&nbsp;</td>
	  			
	    </tr>
	    <tr><td>
	    <table width="100%" border="0" cellspacing="0" cellpadding="2" height="50">
 			<tr> 
            <td><a href="/map/MapView.do?selectedTab=Maps&amp;selectedNode=Google_Map.netmap&amp;viewId=Google_Map.netmap">Google地图</a></td>
			<td><a href="/map/MapView.do?objectName=combanc_bv&amp;jgraphMap=true&amp;selectedTab=Maps&amp;selectedNode=combanc_bview.netmap&amp;viewId=combanc_bview.netmap&amp;displayName=combanc">combanc</a></td>
			</tr>
        </table>
        </td></tr>
	</table>
	<table id="table2" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
	  		<img src="../images/modpass.gif" width="16" height="16">&nbsp;路由&nbsp;</td>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;"  align="right">
	  		<img src="../images/22.gif" width="16" height="16">&nbsp;添加路由分区&nbsp;</td>
	    </tr>
	    <tr><td>
	    <table width="100%" border="0" cellspacing="0" cellpadding="2" height="50">
 			<tr> 
            <td><a href="/map/MapView.do?selectedTab=Maps&amp;selectedNode=Google_Map.netmap&amp;viewId=Google_Map.netmap">Google地图</a></td>
			<td><a href="/map/MapView.do?objectName=combanc_bv&amp;jgraphMap=true&amp;selectedTab=Maps&amp;selectedNode=combanc_bview.netmap&amp;viewId=combanc_bview.netmap&amp;displayName=combanc">combanc</a></td>
			</tr>
        </table>
        </td></tr>
	</table>
	<table id="table2" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
	  		<img src="../images/modpass.gif" width="16" height="16">&nbsp;服务器&nbsp;</td>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;"  align="right">
	  		<img src="../images/22.gif" width="16" height="16">&nbsp;添加服务器分区&nbsp;</td>
	    </tr>
	    <tr><td>
	    <table width="100%" border="0" cellspacing="0" cellpadding="2" height="50">
 			<tr> 
            <td><a href="/map/MapView.do?selectedTab=Maps&amp;selectedNode=Google_Map.netmap&amp;viewId=Google_Map.netmap">Google地图</a></td>
			<td><a href="/map/MapView.do?objectName=combanc_bv&amp;jgraphMap=true&amp;selectedTab=Maps&amp;selectedNode=combanc_bview.netmap&amp;viewId=combanc_bview.netmap&amp;displayName=combanc">combanc</a></td>
			</tr>
        </table>
        </td></tr>
	</table>
    <table id="table2" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
	  		<img src="../images/modpass.gif" width="16" height="16">&nbsp;业务&nbsp;</td>
	  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;"  align="right">
	  		<img src="../images/22.gif" width="16" height="16">&nbsp;添加业务分区&nbsp;</td>
	    </tr>
	    <tr><td>
	    <table width="100%" border="0" cellspacing="0" cellpadding="2" height="50">
 			<tr> 
			<s:property   value="table1" escape="false"/> 
			</tr>
        </table>
        </td></tr>
	</table>      
                 
                       
                         
</div>         
<script language="JavaScript">
 
</script>

</body>
</html>
<Script Language=Javascript src="${pageContext.request.contextPath }/theme/mmBtn.js"></Script>