<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>用户资料</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'>
</script>
<script type="text/javascript" src='<%=path %>/js/pub.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
<script type='text/javascript' src='../dwr/util.js'>
</script>
		<style type="text/css">

</style>
		<script type="text/javascript">
function init() { //取出类别
	var did = '<s:property value="user.department.id"/>';
	var lid='<s:property value="user.location.id"/>';
	if(!did || did==''){
		document.getElementById('user.department.id').value='-1';
	}
	if(!lid || lid==''){
		document.getElementById('user.location.id').value='-1';
	}
}

function callbackorg(data) { //显示出类别
	dwr.util.removeAllOptions("Location");
	dwr.util.addOptions("Location", {
		'-1' : '--请选择--'
	});
	dwr.util.addOptions("Location", data, "id", "name");
	var a = "<s:property value="
user.location.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("Location",a);  
}
 }

function callbackdepartment(data){  //显示出类别
 dwr.util.removeAllOptions("parentDepart");
   dwr.util.addOptions("parentDepart",{'-1':'--请选择--'});
   dwr.util.addOptions("parentDepart",data,"id","name");  
     var a = "<s:property value="user.department.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("parentDepart",a);  
   } 
}
function del() {
	var msg = "确认删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
function refresh(){
	document.getElementById('keyword').value='';
	document.getElementById('departmentname').value='';
	document.getElementById('user.department.id').value='-1';
	document.getElementById('locationname').value='';
	document.getElementById('user.location.id').value='-1';
}
function submit(type,userid){
	document.getElementById('form').action="<%= path%>/user/list.action?type="+type+"&userId="+userid+"";
	document.getElementById('form').submit();
}
</script>
	</head>
	<body  onLoad="init()"  onmousedown="document.getElementById('department').style.visibility='hidden';document.getElementById('locationDiv').style.visibility='hidden';">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					用户资料:
				</td>
			</tr>

		</table>
		<table width="99%" border="0" align="center" cellpadding="2"
			cellspacing="1" bgcolor="#b5d6e6">
			<s:form action="list"  method='post'
				theme="simple" name="form" id="form">
				<s:hidden id="page" name="page" value="1"></s:hidden>
				<s:hidden id="pageSize" name="pageSize"></s:hidden>
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">
						关键字:&nbsp;
					</td>
					<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="keyword"
							id="keyword" cssStyle="width:120px" />
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">
						部门:&nbsp;
					</td>
					<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
					<!-- 
					<input id="department.name" type="text" name="user.department.name" style="width: 79%; background-color: #FFFFFF; cursor: text" readonly value="<s:property value="user.department.name"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="department.id" name="user.department.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../department/departmentList.action" style="border: 1px solid #E5E9EE"></iframe>

																</div>
					
					
					
					
					</td>
					 -->
					 	<input type="hidden" name="user.department.id" id="departmentid" value="<s:property value="user.department.id"/>">
						<input type="text" name="user.department.name" id="departmentname" value="<s:property value="user.department.name"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:85%;">
						<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
						<div id="department"  style="position:absolute; width: 100%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">
							<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
						</div>
					</td>	
					<td width="2%" nowrap bgcolor="#deebf1">
						所属区域:&nbsp;
					</td>
					<td width="25%" bgcolor="#FFFFFF" style="padding-right: 10px">
					<!--  
						<select id="Location" name="user.location.id"
							style="width: 100%">
						</select>
					-->
						<input type="hidden" name="user.location.id" id="locationid" value="<s:property value="user.location.id"/>">
						<input type="text" name="user.location.name" id="locationname" value="<s:property value="user.location.name"/>" onClick="document.getElementById('locationDiv').style.visibility='visible'" readonly style="width:85%;">
						<img id='img' src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('locationDiv').style.visibility='visible'"><br/>
						<div id="locationDiv"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">	
							<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
						</div>
					</td>
					<td nowrap bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<tags:button code="search" menu="85">
						<input type="submit" style="height: 20px" class="mmBtn"
							value="搜 索" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" style="height: 20px" class="mmBtn"
							value="重 置" onclick="refresh();"/>
						</tags:button>
					</td>
				</tr>
			</s:form>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll:false; height: 95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30" align="right">
						<table width="60" border="0" cellpadding="0" cellspacing="0"
							background="../images/addnew002.gif">
								<tags:button code="add" menu="85">
							<tr onClick="window.location='addInput.action'"
								style="cursor: hand;">
								<td>
									<img src="../images/addnew001.gif">
								</td>
								<td nowrap>
									新建用户
								</td>
								<td align="right">
									<img src="../images/addnew003.gif">
								</td>
							</tr>
							</tags:button>
						</table>
				</tr>
		  </table>
			<table width="99%" border="0" align="center" cellpadding="0"
				cellspacing="1" bgcolor="#b5d6e6">
				<tr bgcolor="#FFFFFF">
					<!-- 
				    <td height="20" align="center"
						background="../images/main20100521_58.gif" class="alllisttitle" style="display:none">
						员工工号
					</td>
					 -->
					 <td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						登录名
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						用户全名
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						部门
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						所属区域
					</td>
					<!--  
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle" >
						状态
					</td>
					-->
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						修改
					</td>
					<td align="center" background="../images/main20100521_58.gif"
						class="alllisttitle">
						删除
					</td>
				</tr>
				<s:iterator value="pageBean.list" var="users">
					<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
						onMouseOut="this.bgColor='#FFFFFF'">
						<!-- 
						<td height="26" align="center" style="display:none">
							<s:property value="username" />
						</td>
						 -->
						<td height="26" align="center">
							<s:property value="username" />
						</td>
						<td align="center">
							<s:property value="truename" />
						</td>
						<td align="center">
							<s:property value="department.name" />
						</td>
						<td align="center">
							<s:property value="location.name" />
						</td>
						<!-- 
						<td align="center">
							<script type="text/javascript">
								var enabled='<s:property value='enabled'/>';
								if(enabled=='1'){
									document.write('已启用&nbsp;&nbsp;&nbsp'+'<a href="javascript:submit(1,<s:property value='id'/>)">禁用</a>');
								}else{
									document.write('禁用中&nbsp;&nbsp;&nbsp'+'<a href="javascript:submit(0,<s:property value='id'/>)">启用</a>');
								}
							</script>
						</td>
						 -->
						<td align="center">
							<tags:button code="update" menu="85">
							<img src="../images/edt.gif">
							<a href="updateInput.action?userId=<s:property value='id'/>&page=<s:property value="page"/>">修改</a>
							</tags:button>
						</td>
						<td align="center">
							<tags:button code="delete" menu="85">
							<img src="../images/del.gif">
							<a
								href="delete.action?userId=<s:property value="id"/>&page=<s:property value="page"/>" onclick="javascript:return del()">删除</a>
						  </tags:button>
						</td>
					</tr>
				</s:iterator>
			</table>

			<jsp:include page="/common/page.jsp" />
	</div>
	</body>
</html>
