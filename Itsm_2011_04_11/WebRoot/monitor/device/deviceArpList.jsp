<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<head>
	<title>IP转发表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>	
<script type="text/javascript">


//弹出提示信息
var message='<%=(String)request.getAttribute("message") %>';
if(null!=message && message !="null")
	alert(message);

function init() {
	
}

</script>
</head>

<body onLoad="init()">

<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">IP转发表</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">IP转发表：<s:property value="monitorDevice.ip"/></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#b5d6e6">
      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center">序号</td>
        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
        <td align="center" nowrap bgcolor="#deebf1">MAC</td>
        <td align="center" nowrap bgcolor="#deebf1">接口</td>          
        <td align="center" nowrap bgcolor="#deebf1">接口描述</td>
        <td align="center" nowrap bgcolor="#deebf1">类型</td>
      </tr>
      <%int i=1; %>
      <s:iterator value="#request.arpEntryList" var="arpEntry">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
        	<td height="26" align="center" width="50"><%=i++%></td>
			<td height="19" align="center"><s:property value="ip"/></td>
	        <td align="center"><s:property value="mac"/></td>
	        <td align="center"><s:property value="interface_"/></td>
	        <td align="center"><s:property value="description"/></td>
	        <td align="center"><s:property value="type"/></td> 
	    </tr>
      </s:iterator>
</table>

</body>

</html>