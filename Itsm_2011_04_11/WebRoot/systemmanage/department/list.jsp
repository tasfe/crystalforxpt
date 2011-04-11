<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>部门管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

		<script type="text/javascript">
function loadTop() {
	var ag = '<s:property value="message" escape="false" />';
	if (ag) {
		alert("提示：" + ag);
	}
	var topframe = parent.frames.topFrame;
	topframe.location = "top.action";
}
function del(id) {
	if(id=='1'){
		alert('该节点为预置数据，不允许删除!');
		return false;
	}
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
				<td width="60%"></td>
				<td width="20%">
					<%--<font color="red"><B><s:property value="message" />
					</B>
					</font>
				--%></td>
				<td height="30" align="right">
					<table width="60" border="0" cellpadding="0" cellspacing="0"
						background="../images/addnew002.gif">
						<tags:button code="add" menu="74">
							<tr onClick="window.location='addInput.action'"
								style="cursor: hand;">
								<td>
									<img src="../images/addnew001.gif">
								</td>
								<td nowrap>
									新建部门
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
					部门名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					部门详细介绍
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
			<s:iterator value="departmentList" var="department">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
					onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center">
						<s:property value="id" />
					</td>
					<td align="center">
						<s:property value="name" />
					</td>
					<td align="center">
						<s:property value="description" />
					</td>
					
					<td align="center">
						<tags:button code="update" menu="74">
							<img src="../images/edt.gif">
							<a
								href="updateInput.action?departmentId=<s:property value="id"/>">修改</a>
						</tags:button>
					</td>
					<td align="center">
						<tags:button code="delete" menu="74">
							<img src="../images/del.gif">
							<a href="delete.action?departmentId=<s:property value="id"/>"
								onclick="javascript:return del(<s:property value="id"/>)">删除</a>
						</tags:button>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
