<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>

<head>
<title>用户资料</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">

<s:form name="form" action="findNowNotices.action" theme="simple">
<s:hidden id="page" name="page"></s:hidden>
<s:hidden id="pageSize" name="pageSize"></s:hidden>
</s:form>

<!--<table cellspacing=0 cellpadding=0 border=0 width="100%">

  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">系统公告:</td>
  </tr>
</table>-->

<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">系统公告:</td>
  </tr>
</table>



<!--<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px; font-style: italic;">
	
-->
<div style="position:absolute;">
<table width="99%" border="0" cellpadding="3" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
		<tr>
		<th width="26%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">公告标题</th> 
        <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">公告发布者</th>
        <th width="6%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布时间</th>
		<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 查看 </th>
	    <!--<th align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="5">查看</th>
	--></tr>
<s:iterator value="pageBean.list" var="notice" id="u">
      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'" >
         <td height="20" align="left"><s:property value="#u.title"/></td>
	  	 <td align="center"><s:property value="authorName"/></td>
	  	 
         <td align="center"><s:date name="date" format="yyyy/MM/dd HH:mm:ss"/></td>
         <td align="center" nowrap><s:a href="detailSystemNotice.action?id=%{#u.id}"><img src='../img/viewdetail.gif' border=0 width=18 height=18></s:a></td><!--
         <td align="center" onClick="window.open('noticeInfo.action?id=${id}','','width=1024,height=768,scrollbars=no,menubar=no,resizable=no,top=50,left=70,status=yes')" style="cursor: hand"><img src="../img/viewdetail.gif" align="middle"></td>
       --></tr>
</s:iterator>

</table>

<jsp:include page="/common/page.jsp"/>
</div>
</body>
</html>
