<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>严重度与紧急度</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function confir(){
	var value1=document.getElementById('severityTyp.severityType').value;
	if(value1==''){ alert('请输入程度！'); return false;}
	var value2=document.getElementById('severityTyp.severityValue').value;
	if(value2==''){ alert('请给程度赋值！'); return false;}
     var re = /^[1-9]\d*$/;
     if (!re.test(value2)) {
        alert("请给程度赋值为正整数!");
        return false;
     }
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"><br>
		<s:form action="%{actionURI}.action" method="post" theme="simple">
			<s:hidden id="severityTyp.id" name="severityTyp.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="12%" align="right" nowrap bgcolor="#deebf1"><b>程度：</b></td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:textfield id="severityTyp.severityType" name="severityTyp.severityType" cssStyle="width: 100%"></s:textfield>
				  </td>
				</tr>
				<tr style="height: 25">
					<td align="right" bgcolor="#deebf1"><b>赋值：</b></td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="severityTyp.severityValue" name="severityTyp.severityValue" cssStyle="width: 100%"></s:textfield>
				  </td>
				</tr>
				<tr style="height: 25">
					<td align="right" bgcolor="#deebf1"><b>大类：</b></td>
					<td bgcolor="#FFFFFF">
						<select id="severityTyp.category" name="severityTyp.category" style="width: 100%">
							<option id="category1" value="1">事件/变更/问题：影响度</option>
							<option id="category2" value="2">事件/变更/问题：紧急度</option>
							<option id="category3" value="3">服务请求/事件：严重程度</option>
							<option id="category4" value="4">项目/任务：严重程度</option>
							<option id="category5" value="5">用户/客户：优先等级</option>
							<option id="category6" value="6">配置：优先等级</option>
						</select>
						<script type="text/javascript">
							var select = document.getElementById('severityTyp.category');
							for (var i = 0; i < 6; i++) {
								if (<s:property value="severityTyp.category" /> == select.options[i].value) {
									select.options[i].selected = 'selected';
								}
							}
						</script>
				  </td>
				</tr>
		  </table>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr align="center" style="height: 25">
		<td height="30" colspan="2" nowrap="nowrap" bgcolor="#FFFFFF" class="list_btm" style="text-align: center">
			&nbsp;&nbsp;
			<input type="submit" value=" 保存 " class="mmBtn"  onClick="javascript:return confir()">
			&nbsp;&nbsp;
			<input type="reset" value=" 重置 " class="mmBtn">
			&nbsp;&nbsp;
	  <input type="button" value=" 返回 " onClick="window.location='list.action'" class="mmBtn">				  </td>
	</tr>
</table>

		</s:form>
	</body>
</html>