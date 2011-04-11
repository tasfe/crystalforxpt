<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>区域管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function loadTop() {
	var ag = '<s:property value="msg" escape="false" />';
	if (ag) {
		alert("提示：" + ag);
	}
	var topframe = parent.frames.topFrame;
	topframe.location = "top.action";
}
function del() {
	var msg = "确认删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="loadTop();">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30" align="right">
					<table width="60" border="0" cellpadding="0" cellspacing="0"
						background="../images/addnew002.gif">
						<tags:button code="add" menu="72">
							<tr onClick="window.location='addInput.action'"
								style="cursor: hand;">
								<td>
									<img src="../images/addnew001.gif">
								</td>
								<td nowrap>
									新建区域
								</td>
								<td align="right">
									<img src="../images/addnew003.gif">
								</td>
							</tr>
						</tags:button>
					</table>
			</tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					序号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					区域名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					区域详细介绍
				</td>
				<%--
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">父级代码</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">位置代号</td>
        --%>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					英文名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					修改
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					删除
				</td>
			</tr>
			<s:iterator value="locations" var="location">
				<tr bgcolor="#FFFFFF" onmouseover="this.bgColor='#e3f0f7'"
					onmouseout="this.bgColor='#FFFFFF'">
					<td height="19" align="center">
						<s:property value="id" />
					</td>
					<td align="center">
						<s:property value="name" />
					</td>
					<td align="center">
						<s:property value="content" />
					</td>
					<%--<td align="center"><s:property value="pid"/></td>
          <td align="center"><s:property value="code"/></td>
					--%>
					<td align="center">
						<s:property value="locationSc" />
					</td>
					<td align="center">
						<tags:button code="update" menu="72">
							<img src="../images/edt.gif">
						&nbsp;
							<a href="updateInput.action?locationId=<s:property value="id"/>">修改</a>
						</tags:button>
					</td>
					<td align="center">
						<tags:button code="delete" menu="72">
							<img src="../images/del.gif">
						&nbsp;
							<a href="delete.action?locationId=<s:property value="id"/>"
								onclick="javascript:return del()">删除</a>
						</tags:button>
					</td>
				</tr>
			</s:iterator>
		</table>
		<iframe height="0" width="0" frameborder="0" name="sub" id="sub"></iframe>
	</body>
</html>
