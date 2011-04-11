<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>任务分配机制管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">任务分配机制管理:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" 

background="../images/addnew002.gif">
            <tr onClick="window.location='addInput.action?pid=<s:property value="pid"/>&type=<s:property 

value="type"/>'" style="cursor:hand;">
              <td><img src="../images/addnew001.gif"></td>
              <td nowrap>新建任务分配机制</td>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
          </table>
      </tr>
    </table>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">名称显示</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
          </tr>
          <s:iterator value="taskAllocations" var="taskAllocation">
            <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
              <td height="19" align="center"><a href="info.action?taskAllocationId=<s:property value="id"/>"><s:property value="id"/></a></td>
              <td align="center"><s:property value="displayname"/></td>
              <td align="center"><img src="../images/edt.gif"><a href="updateInput.action?taskAllocationId=<s:property value="id"/>">修改</a></td>
              <td align="center"><img src="../images/del.gif"><a href="delete.action?taskAllocationId=<s:property value="id"/>">删除</a></td>
            </tr>
          </s:iterator>
</table>


</body>
</html>
