<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>IT Service Desk</title>
<script language="JavaScript">
function onDown()
{
	if(window.parent.document.getElementById('Layer')!=undefined)
	{
		window.parent.document.getElementById('Layer').style.visibility='hidden';
	}
	if(window.parent.document.getElementById('Layer1')!=undefined)
	{
		window.parent.document.getElementById('Layer1').style.visibility='hidden';
	}
	if(window.parent.document.getElementById('Layer2')!=undefined)
	{
		window.parent.document.getElementById('Layer2').style.visibility='hidden';
	}
	if(window.parent.document.getElementById('Layer3')!=undefined)
	{
		window.parent.document.getElementById('Layer3').style.visibility='hidden';
	}
	if(window.parent.document.getElementById('Layer_Add')!=undefined)
	{
		window.parent.document.getElementById('Layer_Add').style.visibility='hidden';
	}
	if(window.parent.gfPop!=undefined)
	{
		window.parent.gfPop.hiddencld();
	}
}
function Chose(IsCK, Value)
{
	var OldValue = window.parent.document.getElementById('ConfigCh').value;
	
	if (IsCK==true)
	{
		window.parent.document.getElementById('ConfigCh').value=OldValue.replace(','+Value,'')+','+Value;
	}
	else
	{
		window.parent.document.getElementById('ConfigCh').value=OldValue.replace(','+Value,'');
	}
	
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/simple.css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onmousedown="onDown()">
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
	<tr>
		<td valign="top" id="mainright">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="#FFFFFF">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="2" cellpadding="2" class="datagrid">
									
							<tr bgcolor="#FFFFFF">
								<td width="14%" height="25" valign="top">
									<div class='DivOut'>R08000004430</div>
							  </td>
								<td width="43%" valign="top">
									<div class='DivOut'>
										事件: IT报事服务/员工变动IT信息管理/员工离职IT资产审批/
									</div>
							  </td>
								<td width="27%" valign="top">
									<div class='DivOut'>张勇</div>
							  </td>
								<td width="8%" align=center style="padding-left: 6px; padding-right: 6px">
									<input name="chose" type="checkbox" value="1" style="border: 0px" onClick="if (this.checked){ window.parent.document.getElementById('Requests').value=window.parent.document.getElementById('Requests').value.replace(',R08000004430','')+',R08000004430' } else { window.parent.document.getElementById('Requests').value=window.parent.document.getElementById('Requests').value.replace(',R08000004430','') }" checked>
							  </td>
								<td width="8%" align=center style="padding-left: 7px; padding-right: 7px" title="´¦ÀíÖÐ"><a onClick="window.open('../inci/?NowAction=Detail&RequNo=R08000004430','','width=900,height=550,scrollbars=yes,menubar=no,resizable=yes,top=30,left=30,status=yes')" style="cursor: hand" href="javascript:"><img src="../../img/wait.gif" width="14" height="18" border="0"></a></td>
						  </tr>
							
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
