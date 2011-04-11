<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
	<title>系统公告列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript">
	
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
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
</style>
</head>
	
<body style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">系统公告搜索</td>
  </tr>
</table>
			
<%--<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
--%>
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
<s:form method="post" action="/SystemNotice/querySystemNotice.action" theme="simple">
	<tr>
		<td width="8%" align="center" nowrap bgcolor="#deebf1">发布者：&nbsp;</td>
											
		<td width="14%" bgcolor="#FFFFFF" style="padding-right: 10px">
		<input name="authorName" type="text" size="40" style="width: 80%"></td>
		
		<td width="8%" align="center" nowrap bgcolor="#deebf1">标题：&nbsp;</td>			
		<td width="17%" bgcolor="#FFFFFF" style="padding-right: 10px">														
		<input name="title" type="text" size="40" style="width: 80%">
		</td>	
										
		<td width="8%" align="center" nowrap bgcolor="#deebf1">详细内容：&nbsp;</td>																																
		<td width="18%" bgcolor="#FFFFFF" style="padding-right: 10px">
		<input name="content" type="text" size="40" style="width: 80%"></td>
		
	    <td width="6%" bgcolor="#deebf1" align="center">
	     <tags:button code="search" menu="97">
		<input type="submit" style="height:20px" class="mmBtn" value="搜索" id="1"/>
		</tags:button>
		</td>
		</tr>									
</s:form>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<td height="32">
<table align="right" width="100" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
	 <tags:button code="noticecheck" menu="97">  
	<tr style="cursor:hand;">
	<td><img src="../images/addnew001.gif"></td>	
	<td onClick="javascript:window.location.href='<%=request.getContextPath()%>/PersonalNotice/listPersonalNotice.action'">通知查看</td>
	<td align="right"><img src="../images/addnew003.gif"></td>
	</tr>
	</tags:button>
</table>
</td>
</tr>

</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">公告列表</td>
  </tr>
</table>

<%--<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
--%>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 70%; width: 100%; padding-top: 5px;">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6"> 	
<%--<table width="99%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
      --%><tr>
       	<th width="18%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">公告标题</th> 
        <th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布时间</th>
        <th width="14%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">发布者</th>
        <th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">修改</th>
        <th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">删除</th>
      </tr>
      <s:iterator value="pageBean.list" id="u">
      	<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      	<td align="center" class="zczb_qua"><s:a href="detailSystemNotice.action?id=%{#u.id}"><s:property value="#u.title"/></s:a></td>
      	<td align="center" class="zczb_qua"><s:property value="#u.date.toString().substring(0,16)"/></td>
		<td height="30" align="center" class="zczb_qua"><s:property value="#u.authorName"/></td>
        <td align="center">
            <tags:button code="update" menu="97">  
        <img src="../images/edt.gif">
        <s:a href="updatePSystemNotice.action?id=%{#u.id}">修改</s:a>	
        </tags:button>
        </td>
        <td align="center">
          <tags:button code="delete" menu="97">  
        <img src="../images/del.gif">
        <s:a href="deleteSystemNotice.action?id=%{#u.id}" onclick="javascript:return del()">删除</s:a>
            </tags:button>
        </td>
        </tr>
      </s:iterator>
      <tr style="cursor:hand;">
      <td height="30" colspan="6" align="right" bgcolor="#FFFFFF"><table width="270" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="100" cellspacing="0" cellpadding="0" background="../images/addnew002.gif">
            <tr>
              <tags:button code="add" menu="97">
                <td><img src="../images/addnew001.gif"></td>
                <td onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/saveSystemNotice.action'"><center>
                  发布新公告
                </center></td>
              </tags:button>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
          </table></td>
          <td>&nbsp;</td>
          <td><table width="110" cellspacing="0" cellpadding="0" background="../images/addnew002.gif">
            <tr>
              <tags:button code="addpersonal" menu="97">
                <td><img src="../images/addnew001.gif"></td>
                <td onClick="javascript:window.location.href='<%=request.getContextPath()%>/systemmanage/systemnotice/sendPersonalNotice.jsp'"><center>
                  个人通知发布
                </center></td>
              </tags:button>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
          </table></td>
        </tr>
      </table></td>
	</tr>


<%--<tr>
<td height="30" align="right">
<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
<tr >
<td><img src="../images/addnew001.gif"></td>
<td onClick=""></td>

</tr>
</table>
</td>
</tr>
--%></table><%--
	<table width="100%" border="0" align="center" cellpadding=3 cellspacing=0>
    	<tr>
      	<td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;">
      	
      	<a onClick="javascript:window.location.href='<%=request.getContextPath()%>/SystemNotice/saveSystemNotice.action'">&nbsp;发布新公告&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;">
      	<a onClick="javascript:window.location.href='<%=request.getContextPath()%>/systemmanage/systemnotice/sendPersonalNotice2.jsp'">&nbsp;个人通知发布&nbsp;</a></span>
      	</td>
    	</tr>
  	</table>
--%>

<br>
<s:form name="form" method="post" action="/SystemNotice/listSystemNotice.action" theme="simple" >
<s:hidden id="page" name="page"></s:hidden>		
</s:form>
<jsp:include page="/common/page.jsp"/>
<%--</div>
--%>
</div>
		
</body>
</html>
