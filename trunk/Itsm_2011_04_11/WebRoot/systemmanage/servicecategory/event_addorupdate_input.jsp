<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>事件及服务类别</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" >
			function selectedOption(id, value) {
				var select = document.getElementById(id);
				for (var i = 0; i < 2; i++) {
					if (value == select.options[i].value) {
						select.options[i].selected = 'selected';
					}
				}
			}
		</script>
	<style type="text/css">
<!--
body {
	margin-top: 5px;
}
-->
</style></head>
	<body leftmargin="0" marginwidth="0">
		<s:form id="form" name="form" action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="type" name="type"></s:hidden>
			<s:hidden id="pid" name="pid"></s:hidden>
			<s:if test="actionURI=='save'">
				<input id="serviceCategory.pid" name="serviceCategory.pid" type="hidden" value="${pid}"></input>
				<input id="serviceCategory.type" name="serviceCategory.type" type="hidden" value="${type}"></input>
			</s:if>
			<s:else>
				<s:hidden id="serviceCategory.id" name="serviceCategory.id"></s:hidden>
				<s:hidden id="serviceCategory.pid" name="serviceCategory.pid"></s:hidden>
				<s:hidden id="serviceCategory.type" name="serviceCategory.type"></s:hidden>
				<s:hidden id="serviceCategory.code" name="serviceCategory.code"></s:hidden>
			</s:else>
			<table width="80%"  border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr >
					<td width="13%" align="right" bgcolor="#deebf1">
						<b>中文名称<span style = 'color : red; vertical-align   :middle'>*</span>：</b>					</td>
					<td width="41%" bgcolor="#FFFFFF">
						<s:textfield id="serviceCategory.itemZh" name="serviceCategory.itemZh" cssStyle="width: 100%"/>				  </td>
					<td width="12%" align="center" nowrap bgcolor="#deebf1">
						<b>英文名称：</b>					</td>
					<td width="34%" bgcolor="#FFFFFF">
				  <s:textfield id="serviceCategory.item" name="serviceCategory.item" cssStyle="width: 100%"/>				  </td>
				</tr>
				<tr  style="display:none">
					<td align="right" nowrap bgcolor="#deebf1">
						<b>是否自助服务：</b>					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select id="serviceCategory.selfService" name="serviceCategory.selfService" >
							<option value="0">不显示，直接提交服务请求</option>
							<option value="1">以向导方式帮助用户解决问题</option>
						</select>
						<script type="text/javascript">
							selectedOption('serviceCategory.selfService', '<s:property value="serviceCategory.selfService" />');
						</script>
				  </td>
				</tr>
				<tr  style="display:none">
					<td align="right" bgcolor="#deebf1">
						<b>图片：</b>					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<s:textfield id="serviceCategory.icoUrl" name="serviceCategory.icoUrl" cssStyle="width: 100%"></s:textfield>
				  </td>
				</tr>
				<tr  style="display:none">
					<td align="right" bgcolor="#deebf1">
						<b>是否填写描述：</b>					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select id="serviceCategory.description" name="serviceCategory.description">
							<option value="0">
								允许客户填写服务请求描述，上传附件
							</option>
							<option value="1">
								允许客户填写服务请求描述，上传附件，及选择关联配置
							</option>
							<option value="2">
								不允许填写服务请求描述，上传附件，选择相关配置
							</option>
						</select>
						<script type="text/javascript">
							selectedOption('serviceCategory.description', '<s:property value="serviceCategory.description" />');
						</script>
				  </td>
				</tr>
				<tr >
					<td align="right" bgcolor="#deebf1">
						<b>默认优先级：</b>					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<s:select list="defEssList" headerKey="0" headerValue="影响度" name="serviceCategory.defEss" listKey="severityValue" listValue="severityType"></s:select>
						&nbsp;<b>&</b>&nbsp;
						<s:select list="defEmeList" headerKey="0" headerValue="紧急度" name="serviceCategory.defEme" listKey="severityValue" listValue="severityType"></s:select>
						<!--  
						&nbsp;&nbsp;强制以默认优先级选取并执行合适的服务水平协议：
						<select id="serviceCategory.isDef" name="serviceCategory.isDef" >
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
						<script type="text/javascript">
							selectedOption('serviceCategory.isDef', '<s:property value="serviceCategory.isDef" />');
						</script>
						-->
				  </td>
				</tr>
				<tr >
					<td align="right" bgcolor="#deebf1">
						<b>说明：</b>					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<s:textarea cssStyle="width: 100%" name="serviceCategory.explainer" rows="3"></s:textarea>
				  </td>
				</tr>
		  </table>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr height="28">
		<td height="30" colspan="4" nowrap="nowrap" bgcolor="#FFFFFF" class="list_btm" style="text-align: center;">
			&nbsp;&nbsp;
			<input type="submit" value=" 保存 " class="mmBtn"/>
			&nbsp;&nbsp;
			<input type="reset" value=" 重置 " class="mmBtn" style="display:none"/>
			&nbsp;&nbsp;
			<input type="button" value=" 返回 " onClick="window.location='list.action?type=${type}&pid=${pid}'" class="mmBtn" />
	  </td>
	</tr>
</table>

		</s:form>
</body>
</html>