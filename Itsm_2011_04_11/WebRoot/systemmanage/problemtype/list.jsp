<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" >
			function loadTop() {
				var topframe = parent.frames.topFrame;
				topframe.location = "top.action";
			}
		</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="loadTop();">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
             <tags:button code="add" menu="93">
            <tr onClick="window.location='addInput.action?pid=${pid}'" style="cursor:hand;">
              <td><img src="../images/addnew001.gif"></td>
              <td nowrap>新建问题</td>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
            </tags:button>
          </table>
      </tr>
    </table>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
          <tr bgcolor="#FFFFFF">
            <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">标题</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">代码</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
          </tr>
          <s:iterator value="problemTypeList" var="pt">
            <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='#e3f0f7'" onmouseout="this.bgColor='#FFFFFF'">
              <td height="19" align="center">${pt.id}</td>
              <td align="center">${pt.problemCate}</td>
              <td align="center">${pt.code}</td>
              <td align="center">
              <tags:button code="update" menu="93">
              <img src="../images/edt.gif">
              <a href="updateInput.action?problemTypeId=${pt.id}&flag=${flag}">修改</a>
              </tags:button>
              </td>
              <td align="center">
              <tags:button code="delete" menu="93">
              <img src="../images/del.gif">
              <a href="javascript:;" onClick="parent.location='delete.action?problemTypeId=${pt.id}&pid=${pid}&flag=${flag}'">删除</a>
              </tags:button>
              </td>
            </tr>
          </s:iterator>
        </table>
	</body>
</html>
