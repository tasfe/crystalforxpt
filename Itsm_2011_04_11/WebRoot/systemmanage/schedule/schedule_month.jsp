<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/schedule/list.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工程师日程列表</title>
<link href="../css/Default.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script> 
function ChangeDate(i){
	window.location = "month.action?uid="+${uid}+"&cDate="+$V('hnowDate')+"&Flag="+i;
}
</script> 
</head>
	<body>
	<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
		<tr valign="top">
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
				<tr>
					<td valign="middle" class="blockTd" style=" padding-bottom:0;"><img
						src="../Icons/icon016a1.gif" width="20" height="20" />日程列表</td>
				</tr>
				<tr>
					<td align="right" style="padding:0;">
					<table width="260" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="95"><div class='divbtn' style='-moz-user-select:none;' onselectstart='return false' id='' onClick="ChangeDate(-1);" onMouseOver="Effect.onBtnMouseover(this)" onMouseOut="Effect.onBtnMouseout(this)"><img src="../Icons/icon403a17.gif" /><b>上一个月&nbsp;</b></div></td>
							<td align="center"><s:property value="%{#request.nowDate}" escape="false"/></td>
							<td width="95"><div class='divbtn' style='-moz-user-select:none;' onselectstart='return false' id='' onClick="ChangeDate(1);" onMouseOver="Effect.onBtnMouseover(this)" onMouseOut="Effect.onBtnMouseout(this)"><img src="../Icons/icon403a18.gif" /><b>下一个月&nbsp;</b></div></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><s:property value="%{#request.NoteContent}" escape="false"/></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<input name="hnowDate" id="hnowDate" value="<s:property value="%{#request.hnowDate}" escape="false"/>" type="hidden" />
	<input name="uid" id="uid" value="<s:property value="%{#request.uid}" escape="false"/>" type="hidden" />
	</body>
</html>