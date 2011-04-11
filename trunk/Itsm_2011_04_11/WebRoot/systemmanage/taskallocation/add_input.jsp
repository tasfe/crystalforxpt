<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>任务分配机制</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'>
</script>
		<script type="text/javascript">
function getDAO() { //取出类别
	<%--UserDAO.findAll(callbackusers);
 LocationDAO.findAll(callbacklocation);--%>
   callbacklocation1();
   RoleteamDAO.findAllByType(callbackroleteam);
}
function confirm(){
	var displayname=document.getElementById('taskAllocaiton.dispalyname').value;
	if(!displayname){
		alert('请输入显示名称！');
		return false;
	}
	var serviceCategoryList=document.getElementById("cats").value;
	if(!serviceCategoryList||serviceCategoryList==''){
		alert('请选择服务或请求类别！');
		return false;
	}
	var departments=document.getElementById('departments').value;
	if(!departments ||departments==''){
		alert('请选择适用申请客户！');
		return false;
	}
	
	var engineerteam=document.getElementById('engineerteam').value;
	if(!engineerteam ||engineerteam=='' ||engineerteam==-1){
		alert('请选择工作组！');
		return false;
	}
	return true;
}
function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
}
function callbackorg(data){  //显示出类别
   dwr.util.removeAllOptions("Roleteam");
   dwr.util.addOptions("subject",["--请选择--"]);
   dwr.util.addOptions("Roleteam",data,"id","name");
}

function callbackroleteam(data){  //显示出工程师分组
   dwr.util.removeAllOptions("engineerteam");
   document.getElementById("ITer").src="about:blank";
   dwr.util.removeAllOptions("engineerteam");
   dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam",data,"id","name"); 
   //当修改时选中
   var type='${actionURI}';
   if(type=='update'){
       var team='${roleteam.id}';
       var useFor='${roleteam.useFor}';
       if(useFor){
            dwr.util.setValue("location",useFor);
       }
       if(team){
            dwr.util.setValue("engineerteam",team);
            document.getElementById("teams").value=team;
            document.getElementById("ITer").src="../role/queryByRoleteam.action?role.id="+team+"&role.type=1";
       }
       
   }
}
function callbacklocation1(){  //显示出地理位置
   dwr.util.removeAllOptions("location");
   dwr.util.addOptions("location",{'-1':'--请选择--'});
    dwr.util.addOptions("location",{'1':'全局：面向所有地域'});
     dwr.util.addOptions("location",{'2':'仅对：某区域'});
      dwr.util.addOptions("location",{'3':'隐藏：将这个工作组隐藏'});
}
function getInfo1(){

	var location = document.getElementById("location").value;
	if(location!=-1) {
		RoleteamDAO.findByUseForString(location,callbackroleteam);		
	}else{
		RoleteamDAO.findAllByType(callbackroleteam);		
	}
}

function getInfo2(){
	var roleteam = document.getElementById('engineerteam').value;

	document.getElementById("teams").value=roleteam;

	document.getElementById("ITer").src="../role/queryByRoleteam.action?role.id="+roleteam+"&role.type=1";
}


</script>

	</head>

	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="padding: 7px;; cursor: default;">
		<s:form action="%{actionURI}.action" namespace="/taskallocation"
			method="post" theme="simple">
			<s:hidden id="departments" name="departments"></s:hidden>
			<s:hidden id="teams" name="teams"></s:hidden>
			<s:hidden id="cats" name="cats"></s:hidden>
			<s:hidden id="taskAllocaiton.roleteamByRoleteam.id"
				name="taskAllocaiton.roleteamByRoleteam.id"></s:hidden>
				<s:hidden id="taskAllocationId" name="taskAllocationId"></s:hidden>
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="100%">
							<tr>
								<td width="13%" height="1%" align="right" valign="middle"
									bgcolor="#deebf1">
									显示名称<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textfield id="taskAllocaiton.dispalyname" style="width:100%"
										name="taskAllocation.displayname" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									说&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textfield id="taskAllocaiton.content" style="width:100%"
										name="taskAllocation.content" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="30%" align="right" valign="middle"
									bgcolor="#deebf1">
									应用范围:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<table width="100%" height="100%" border="0" cellpadding="0"
										cellspacing="1" bgcolor="#b5d6e6">
										<tr>
											<td width="50%" height="24" bgcolor="#deebf1"
												style="padding-top: 0px; padding-bottom: 0px">
												事件或服务请求类别<span style = 'color : red; vertical-align   :middle'>*</span>
											</td>

										</tr>
										<tr>
											<td colspan=3 bgcolor="#EBF4F5" style="padding: 5px">
												<iframe frameborder="0" height="150" width="100%"
													scrolling="yes"
													src="../taskallocation/top3.action?type=1&taskAllocationId=<s:property value="taskAllocation.id"/>"
													style="border: 1px inset"></iframe>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="28%" align="right" valign="middle" nowrap
									bgcolor="#deebf1">
									适用申请客户:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<table width="100%" height="100%" border="0" cellpadding="0"
										cellspacing="1" bgcolor="#b5d6e6">
										<tr>
											<td width="50%" height="24" bgcolor="#deebf1"
												style="padding-top: 0px; padding-bottom: 0px">
												部门<span style = 'color : red; vertical-align   :middle'>*</span>
											</td>
										</tr>
										<tr>
											<td colspan=3 bgcolor="#EBF4F5" style="padding: 5px">
												<iframe frameborder="0" height="150" width="100%"
													scrolling="yes" src="../taskallocation/top2.action?taskAllocationId=<s:property value="taskAllocation.id"/>"
													style="border: 1px inset"></iframe>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="12" bgcolor="#EBF4F5">
									工作组<span style = 'color : red; vertical-align   :middle'>*</span>:
								</td>
								<td bgcolor="#FFFFFF" colspan="3"
									style="padding: 0px; padding-left: 7px">
									<select style="width: 120px" id="location"
										onChange="getInfo1();">
										<option value="-1" selected>
											--请选择--
										</option>
									</select>
									<select style="width: 180px"
										id="engineerteam"
										name="taskAllocaiton.roleteamByRoleteam.id"
										onChange="getInfo2();">
										<option value="-1">
											--请选择--
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EBF4F5">
									服务工程师:
								</td>
								<td bgcolor="#FFFFFF" style="padding: 5px">
									<iframe frameborder="0" height="100" width="90%"
										scrolling="yes" style="border: 1px inset" name="ITer"
										id="ITer"></iframe>
								</td>
							</tr>
							
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" height="12" style="padding-top: 5px">
						<input class="mmBtn" name="submit" type="submit"
							style="width: auto" value="保存" onClick="javascript:return confirm();">
						&nbsp;
						<input name="button" type="button" class="mmBtn"
							style="width: auto" onClick="window.location='list.action'"
							value="返回">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>