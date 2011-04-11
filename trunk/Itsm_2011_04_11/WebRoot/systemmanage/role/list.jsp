<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>角色管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function init(){
				var message='${message}';
				if(message && message!=''&&message=='1'){
					alert('系统预置角色，不允许删除！');
				}
			}
		</script>
	</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onLoad="init();">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">用户角色:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
 <tags:button code="add" menu="88">    
 <tr onClick="window.location='addInput.action'" style="cursor:hand;">
        <td><img src="../images/addnew001.gif"></td>
        <td nowrap>新建用户角色</td>
        <td align="right"><img src="../images/addnew003.gif"></td>
      </tr>
      </tags:button>
    </table>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
  <tr bgcolor="#FFFFFF">
    <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">名称</td>
    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">类属</td>
    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
  </tr>
  <s:iterator value="list" >
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
      <td height="19" align="center"><s:property value="id" /></td>
      <td align="center"><s:property value="name" /></td>
      <td align="center"><s:property value="roleTypeName" /></td>
      <td align="center">
     	<tags:button code="update" menu="88"> 
      		<img src="../images/edt.gif">&nbsp;
      		<a href="updateInput.action?roleId=<s:property value="id" />">修改</a>
      	</tags:button>
      </td>
      <td align="center">
       <tags:button code="delete" menu="88"> 
      <img src="../images/del.gif">&nbsp;
      <a href="delete.action?roleId=<s:property value="id" />">删除</a>
      </tags:button>
      </td>
    </tr>
  </s:iterator>
</table>
</div>
</body>
</html>
