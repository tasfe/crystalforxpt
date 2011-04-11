<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>文档库目录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function checkedAll(MyCheckbox) {
				var flag = MyCheckbox.checked;
				var checkboxes = document.getElementsByName('checkbox');
				if (flag == false) {
					for (var i = 0; i < checkboxes.length; i++) {
						if (i % 5 == 0) {
							checkboxes[i].checked = true;
						} else {
							checkboxes[i].checked = false;
						}
					}
				} else {
					for (var i = 0; i < checkboxes.length; i++) {
						checkboxes[i].checked = true;
					}
				}
			}
			
			function initCheckbox() {
				var checkboxes = document.getElementsByName('checkbox');
				var allAuthority = '<s:property value="allAuthority" />';
				var authorities = allAuthority.split('@');
				for (var i = 0; i < checkboxes.length; i+=5) {
					var id = checkboxes[i].value.substr(1);
					for (var j = 1; j < authorities.length; j++) {
						var values = authorities[j].split('#');
						if (id == values[0]) {
							for (var n = 1; n < values.length; n++) {
								if (values[n] & 1) {
									checkboxes[i + n].checked = true;
								} else {
									checkboxes[i + n].checked = false;
								}
							}
						}
					}
				}
			}
			
			function resetForm() {
				var MyForm = document.getElementById('form');
				MyForm.reset();
				initCheckbox();
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<s:form id="form" name="form" action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="pid" name="pid"></s:hidden>
			<s:hidden id="flag" name="flag"></s:hidden>
			<s:if test="actionURI=='save'">
				<input id="documentCat.pid" name="documentCat.pid" type="hidden" value="${pid}"></input>
			</s:if>
			<s:else>
				<s:hidden id="documentCat.id" name="documentCat.id"></s:hidden>
				<s:hidden id="documentCat.pid" name="documentCat.pid"></s:hidden>
				<s:hidden id="documentCat.code" name="documentCat.code"></s:hidden>
			</s:else>
			<table width="80%" height="" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr height="28">
					<td width="12%" align="right" nowrap bgcolor="#deebf1">
						<b>中文名称：</b>					</td>
					<td width="37%" bgcolor="#FFFFFF">
						<s:textfield id="documentCat.documentSC" name="documentCat.documentSC" cssStyle="width: 100%"/>				  </td>
					<td width="12%" align="center" nowrap bgcolor="#deebf1">
						<b>英文名称：</b>					</td>
					<td width="39%" bgcolor="#FFFFFF">
						<s:textfield id="documentCat.document" name="documentCat.document" cssStyle="width: 100%"/>				  </td>
				</tr>
		  </table>
		  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
			  <tr height="28">
					<td height="30" colspan="4" nowrap="nowrap" bgcolor="#FFFFFF" class="list_btm" style="text-align: center; border: 1">
						&nbsp;&nbsp;
						<input type="submit" value=" 保存 " class="mmBtn"/>
						&nbsp;&nbsp;
						<input type="button" value=" 重置 " class="mmBtn" onClick="resetForm();"/>
						&nbsp;&nbsp;
				  <input type="button" value=" 返回 " onClick="window.location='list.action?pid=${pid}&flag=${flag}'" class="mmBtn" /></td>
		  </tr></table>

		</s:form>
	</body>
</html>