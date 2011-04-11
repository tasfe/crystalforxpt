<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>部门管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript">
		var name;
		var description;
function getDepartment() { //取出类别

	DepartmentDAO.findAll(callbackdepartment)
	var ag = '<s:property value="message" escape="false" />';
	if (ag) {
		alert("提示：" + ag);
	}
	ag="";
}
function callbackdepartment(data) { //显示出类别
	dwr.util.removeAllOptions("parentDepart");
	dwr.util.addOptions("parentDepart", {
		'-1' : '--请选择--'
	});

	dwr.util.addOptions("parentDepart", data, "id", "name");
	var a = "<s:property value="
department.pid" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("parentDepart",a);  
   } 
}
function confir(){
	var value1=document.getElementById('department.name').value;
	if(value1=='') {alert('请输入部门名称！'); return false;}
	var value2=document.getElementById('department.pid').value;
	if(!value2){
		document.getElementById('department.pid').value=0;
	}
	//if(value2==-1||!value2||value2==0){ alert('请选择所属父级部门！'); return false;}
	if(confirm('您确认要保存吗？'))  return true;
	else return false;
}
function rest()
{
	document.getElementById('department.name').value='${department.name}';
	document.getElementById('department.description').value='${department.description}';
}

</script>

	</head>
	<body onLoad="getDepartment()" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0" onMouseDown="document.getElementById('department').style.visibility='hidden';">
		<br>
		<s:form id="form1" action="%{actionURI}.action" method="post"
			theme="simple" namespace="/department">
			<s:hidden id="department.id" name="department.id"></s:hidden>
			<table width="80%" height="75" border="0" cellspacing="1"
				cellpadding="2" align="center" bgcolor="#b5d6e6">
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>部门名称<span style = 'color : red; vertical-align   :middle'>*</span>：</b>
					</td>
					<td width="90%" bgcolor="#FFFFFF">
						<s:textfield id="department.name" name="department.name"
							cssStyle="width: 100%"></s:textfield>
					</td>
				</tr>
				<tr style="height: 25">
					<td width="120" align="right" nowrap bgcolor="#deebf1">
						<b>部门详细介绍：</b>
					</td>
					<td bgcolor="#FFFFFF">
						<s:textfield id="department.description"
							name="department.description" cssStyle="width: 100%"></s:textfield>
					</td>

				</tr>
				<tr style="height: 25">
					<td width="120" align="right" bgcolor="#deebf1">
						<b>父级部门<span style = 'color : red; vertical-align   :middle'>*</span>：</b>
					</td>
					<!--  
					<td width="890" bgcolor="#FFFFFF">
						<select id="parentDepart" name="department.pid"></select>
						<script>
							if ('<s:property value="actionURI"/>' == "update") {
								document.getElementById("parentDepart").disabled = "true";
							}
						</script>
					</td>
					-->
					 <td bgcolor="#FFFFFF"  width="15%">

						<input type="hidden" name="department.pid" id="departmentid" value="<s:property value="department.pid"/>">
						<input type="text" name="parentName" id="departmentname" value="<s:property value="parentName"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:25%;">
						<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
						<div id="department"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
							<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
						</div>
						<script type="text/javascript">
							//var type='${actionURI}';
							//if(type=='update'){
								//document.getElementById('departmentname').disabled=true;
								//document.getElementById('img').style.display='none';
							//}
						</script>
         			</td>
				</tr>


				<tr align="center" style="height: 25">
					<td align="center" height="25" colspan="2" align="left"
						nowrap="nowrap" bgcolor="#deebf1" style="padding-left: 120px;">
						<input type="submit" value=" 保存 " class="mmBtn"
							onClick="javascript:return confir()">
						&nbsp;&nbsp;
						<!--  
						<input type="button" value=" 重置 " class="mmBtn" onClick="rest();">
						&nbsp;&nbsp;-->
						<input type="button" value=" 返回 " onClick="window.history.go(-1)"
							class="mmBtn">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>