<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>地理位置管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function confir(){
	var value1=document.getElementById('buildlocationname').value;
	if(value1==''){ alert('请输入位置名称！'); return false;}
	var value2=document.getElementById('buildlocationid').value;
	if(value2==-1){ alert('请选择父级位置！'); return false;}
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}
function notshow(){
document.getElementById('buildlocation').style.visibility='hidden'
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onMouseDown="notshow();"><br>
		<s:form id="form1" action="%{actionURI}.action" method="post" theme="simple">
			<input type="hidden" name="buildid" id="buildid" value="<s:property value="buildid" />">
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>位置名称：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
						<s:textfield id="buildlocation.name" name="buildlocation.name" cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>父级位置：</b>
					</td>
					<td width="88%" bgcolor="#FFFFFF">
<input type="hidden" name="buildlocation.pid" id="buildlocationid" value="<s:property value="buildlocation.pid" />">
&nbsp;<input type="text" name="buildlocationname" id="buildlocationname" onClick="document.getElementById('buildlocation').style.visibility='visible'" readonly value="<s:property value="pidname" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('buildlocation').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="buildlocation"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/buildlocation.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
					</td>
				</tr>
				<tr style="height: 25">
					<td align="center"  height="25" colspan="2" align="left" nowrap="nowrap"
						bgcolor="#deebf1" style="padding-left: 120px;">
						<input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return confir()">
						&nbsp;&nbsp;
						<input type="reset" value=" 重置 " class="mmBtn">
						&nbsp;&nbsp;
						<input type="button" value=" 返回 " onClick="window.history.go(-1)" class="mmBtn">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>