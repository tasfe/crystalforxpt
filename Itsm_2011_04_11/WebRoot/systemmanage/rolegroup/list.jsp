<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>角色分组设置</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function loadTop() {
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
function init(){
var msg="<s:property value="message"/>";
if(msg=="1"){
	alert("此组别暂时不能删除！");
}
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="loadTop();init();" style="overflow:hidden;">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
	  <tr>
				<td height="30" align="right">
					<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
							<tr onClick="window.location='addInput.action'" style="cursor: hand;">
								<td><img src="../images/addnew001.gif"></td>
								<td nowrap>新建角色组别</td>
								<td align="right"><img src="../images/addnew003.gif"></td>
							</tr>
					</table>
			</tr>
		</table>
        <div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%; padding-right: 3px; padding-bottom: 3px;">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					序号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					编码
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
			<s:iterator value="roleGroupList" var="roleGroup">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center">
						<s:property value="id" />
					</td>
					<td align="center">
						<s:property value="name" />
					</td>
					<td align="center">
						<s:property value="code" />
					</td>
					<td align="center">
							<img src="../images/edt.gif">&nbsp;
							<a href="addInput.action?id=<s:property value="id"/>">修改</a>
					</td>
					<td align="center">
							<img src="../images/del.gif">&nbsp;
							<a href="delete.action?id=<s:property value="id"/>" onclick="javascript:return del()">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table></div>
	<iframe height="0" width="0" frameborder="0" name="sub" id="sub"></iframe>
	</body>
</html>
