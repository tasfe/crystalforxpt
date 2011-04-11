<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="6%">&nbsp;</td>
    <td width="86%" height="30" align="right" style="padding-right:5px;"><table width="60" border="0" cellpadding="0" cellspacing="0" background="../../images/addnew002.gif">
      <tr onClick="window.open('addnewfile.jsp','','width=600,height=400,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')" style="cursor:hand;">
        <td><img src="../../images/addnew00001.gif" width="24" height="22"></td>
        <td nowrap>新建目录</td>
        <td align="right"><img src="../../images/addnew003.gif" width="7" height="22"></td>
      </tr>
    </table></td>
    <td width="8%" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" background="../../images/addnew002.gif">
      <tr onClick="window.open('addnewdocument.jsp','','width=600,height=400,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')" style="cursor:hand;">
        <td><img src="../../images/addnew001.gif" width="24" height="22"></td>
        <td nowrap>新建一个文档</td>
        <td align="right"><img src="../../images/addnew003.gif" width="7" height="22"></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="98%" border=0 align="center" cellpadding=3 cellspacing=1 bgcolor="#b5d6e6" class="datagrid">
  <tr>
    <td width=14% align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">文档号</td>
    <td width="67%" align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">文档标题</td>
    <td width="5%" align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">作者</td>
    <td width=5% align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">查看</td>
    <td width=5% align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">修改</td>
    <td width=4% align="center" nowrap background="../../images/main20100521_58.gif" class="subtitle">删除</td>
  </tr>
  <tr bgcolor='#F9F9F9'>
    <td align="center" nowrap bgcolor="#FFFFFF" style='Padding: 5px'>DOC080000001  </td>
    <td align="left" bgcolor="#FFFFFF" style='Padding: 5px'>冯驰: 测试-开发项目 - 可行性分析 - xyz软件开发项目_业务可行性分析_V0001</td>
    <td align="center" nowrap bgcolor="#FFFFFF" style='Padding: 5px'>冯驰</td>
    <td align="center" nowrap bgcolor="#FFFFFF" style='Padding: 5px'><img onClick="window.open('viewnewdocument.jsp','','width=600,height=600,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')" style="cursor:hand;" src="../../img/check.jpg" width="18" height="18" align="absmiddle" /></td>
    <td align="center" nowrap bgcolor="#FFFFFF" style='Padding: 5px'><img onClick="window.open('editnewdocument.jsp','','width=600,height=400,scrollbars=yes,menubar=no,resizable=yes,top=20,left=20,status=yes')" style="cursor:hand;" src="../../images/edt.gif" width="14" height="14" align="absmiddle" /></td>
    <td align="center" nowrap bgcolor="#FFFFFF" style='Padding: 5px'><img src="../../img/del.gif" width="18" height="18" align="absmiddle" /></td>
  </tr>
</table>
</body>
</html>
