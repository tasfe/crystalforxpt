<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>位置管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function del() {
	var msg = "确认删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
function init(){
var a="<s:property value="message"/>";
if(a=="1"){
alert("此位置暂时不能删除！");
}
if(a=="2"){
parent.document.getElementById('topFrame').src="top.action";
location.href="list.action?pid=1";
}
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"	onLoad="init();" style="overflow:auto;">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
	  <tr>
				<td height="30" align="right">
					<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
							<tr onClick="window.location='addInput.action'" style="cursor: hand;">
								<td><img src="../images/addnew001.gif"></td>
								<td nowrap>新建位置</td>
								<td align="right"><img src="../images/addnew003.gif"></td>
							</tr>
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
					位置名称
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
			<s:iterator value="buildLocationlist" var="buildLocation">
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
							<a href="updateInput.action?buildid=<s:property value="id"/>">修改</a>
					</td>
					<td align="center">
							<img src="../images/del.gif">&nbsp;
							<a href="delete.action?buildid=<s:property value="id"/>" onclick="javascript:return del()">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	
	</body>
</html>
