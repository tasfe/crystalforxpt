<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<title></title>
	<script src="../js/Main.js"></script>
	<script>
function selectIcon(){
	var diag = new Dialog("Diag2");
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "ѡ��ͼ��";
	diag.URL = "menu/Icon.jsp";
	diag.OKEvent = afterSelectIcon;
	diag.show();
}

function afterSelectIcon(){
	$("Icon").src = $DW.Icon;
	$D.close();
}

function onParentChange(){
	if($V("ParentID")==0){
		$("URL").value = "";
		$E.disable("URL");
	}else{
		$E.enable("URL");
	}
}
</script>
	<link href="../Include/Default.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=gbk">
	</head>
	<body class="dialogBody">
	<form id="form2">
	<table width="100%" height="100%" border="0">
		<tr>
			<td valign="middle">
			<table width="400" height="197" align="center" cellpadding="2"
				cellspacing="0">
				<tr>
					<td width="104" height="10"></td>
					<td width="6"></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">���ƣ�</td>
					<td>&nbsp;</td>
					<td width="260"><input name="Name" verify="����|NotNull"
						type="text" value="" style="width:100px" class="input1" id="Name"
						size=15 /> <input name="ID" type="hidden" id="ID" /></td>
				</tr>
				<tr>
					<td align="right">�ϼ��˵���</td>
					<td>&nbsp;</td>
					<td>
					<z:select name="ParentID" id="ParentID"
						style="width:100px;" onChange="onParentChange()"><span
						value="0"></span> ${ParentMenu}</z:select>
					</td>
				</tr>
				<tr>
					<td align="right">ͼ�꣺</td>
					<td>&nbsp;</td>
					<td><label></label> <label><img
						src="../Icons/icon018a4.gif" style="border:1px"
						onClick="selectIcon()" name="Icon" width="24" height="24"
						align="absmiddle" id="Icon">������ѡ��ͼ�꣩</label></td>
				</tr>
				<tr>
					<td align="right">�Ƿ���ʾ��</td>
					<td>&nbsp;</td>
					<td><label> <input name="Visiable" type="radio"
						value="Y" checked /> �� </label> <label> <input type="radio"
						name="Visiable" value="N" /> ��</label></td>
				</tr>
				<tr>
					<td align="right">URL��</td>
					<td>&nbsp;</td>
					<td><input name="URL" disabled type="text" class="input1"
						id="URL" value="" size="30" verify="URL|NotNull"
						condition="$V('ParentID')!='0'" /></td>
				</tr>
				<tr>
					<td align="right">��ע��</td>
					<td>&nbsp;</td>
					<td><input name="Memo" type="text" class="input1" id="Memo"
						value="" size="30" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center" height="10"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</form>
	</body>
	</html>
