<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>工作日志</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js">
</script>
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'>
</script>
		<script type='text/javascript'
			src='/itsm/dwr/interface/AssetsTypeDAO.js'>
</script>
		<script type='text/javascript'
			src='/itsm/dwr/interface/AssetsProducerDAO.js'>
</script>

		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript">
function init() { //取出类别

	DepartmentDAO.findAll(callbackdepartment);
	LocationDAO.findAll(callbackorg);
	UserDAO.findAll(callbackusers);
}

function callbackorg(data) { //显示出类别
	dwr.util.removeAllOptions("Location");
	dwr.util.addOptions("Location", {
		'-1' : '--请选择--'
	});
	dwr.util.addOptions("Location", data, "id", "name");
	var a = "<s:property value="
workLogQurey.workLog.location.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("Location",a);  
}
 }
function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
   	var a = "<s:property value="workLogQurey.workLog.users.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("users",a);  
     }
}
function callbackdepartment(data){  //显示出类别
 dwr.util.removeAllOptions("parentDepart");
   dwr.util.addOptions("parentDepart",{'-1':'--请选择--'});
   dwr.util.addOptions("parentDepart",data,"id","name");  
     var a = "<s:property value="workLogQurey.workLog.department.id" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("parentDepart",a);  
   } 
}





	function checkAssetsType()
	{
	var type = document.getElementById('assetsQurey.name');
	priceinfo.innerHTML=type.value;
	out.print(type.value);
	}

	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  

</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="init();"
		onmousedown="document.getElementById('Layer2').style.visibility='hidden';document.getElementById('Layer3').style.visibility='hidden'">

		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					工作日志
				</td>
			</tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" colspan="2" align="center" class="alllisttitle">
					<table width="99%" border="0" cellspacing="1" align="center"
						bgcolor="#b5d6e6">
						<s:form action="list" id="form" method='post' theme="simple"
							name="form">
							<s:hidden id="page" name="page"></s:hidden>
							<s:hidden id="pageSize" name="pageSize"></s:hidden>
							<tr>
								<td height="22" colspan="7" align="left"
									background="../images/main20100521_58.gif" class="alllisttitle">
									日志搜索
								</td>
							</tr>
							<tr>
								<td width="10%" height="22" align="right" bgcolor="#deebf1">
									日志标题：
								</td>
								<td width="10%" bgcolor="#FFFFFF">
									<s:textfield id="workLogQurey.workLog.title"
										name="workLogQurey.workLog.title" cssStyle="width: 99%"></s:textfield>
									<td width="10%" align="right" bgcolor="#deebf1">
										日志时间段：
									</td>
									<td colspan="4" width="10%" bgcolor="#FFFFFF">
										<s:textfield id="startDate" name="workLogQurey.startDate"
											onclick="WdatePicker()" cssClass="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											style="width:40%"></s:textfield>
										<!--<img onClick="WdatePicker({el:$dp.$('workLogQurey.startDate')})" src="../js/DatePicker/skin/datePicker.gif" />-->
										<strong>—</strong>
										<s:textfield id="endDate" name="workLogQurey.endDate"
											onclick="WdatePicker()" cssClass="Wdate"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
											style="width:40%"></s:textfield>
										<!--<img onClick="WdatePicker({el:$dp.$('workLogQurey.endDate')})" src="../js/DatePicker/skin/datePicker.gif" />-->
									</td>
							</tr>
							<tr>
								<td align="right" bgcolor="#deebf1">
									所属部门：
								</td>
								<td bgcolor="#FFFFFF" width="16%">


									<input id="department.name" type="text"
										name="workLogQurey.workLog.department.name"
										style="width: 79%; background-color: #FFFFFF; cursor: text"
										readonly
										value="<s:property value="workLogQurey.workLog.department.name"/>"
										onClick="document.getElementById('Layer2').style.visibility='visible'">
									<s:hidden id="department.id"
										name="workLogQurey.workLog.department.id"></s:hidden>
									&nbsp;
									<img src="../images/main20100521lsearch.gif" align="absmiddle"
										style="cursor: hand"
										onClick="document.getElementById('Layer2').style.visibility='visible'">
									<br>

									<div id="Layer2"
										style="position: absolute; width: 180%; height: 20px; z-index: 1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

										<iframe frameborder="0" height="150" width="100%"
											scrolling="yes" src="../department/departmentList.action"
											style="border: 1px solid #E5E9EE"></iframe>

									</div>


									<%--<select id="parentDepart"
										name="workLogQurey.workLog.department.id" style="width: 99%">
									</select>
								--%>
								</td>
								<td align="right" bgcolor="#deebf1">
									位置：
								</td>
								<td width="16%" align="left" bgcolor="#FFFFFF">
									<select id="Location" name="workLogQurey.workLog.location.id"
										style="width: 99%">
									</select>
								</td>
								<td width="10%" bgcolor="#deebf1" align="right">
									用户名：
								</td>
								<td width="16%" align="left" bgcolor="#FFFFFF">
									<input id="users.name" type="text"
										name="workLogQurey.workLog.users.truename"
										style="width: 79%; background-color: #FFFFFF; cursor: text"
										
										value="<s:property value="workLogQurey.workLog.users.truename"/>"
										onClick="document.getElementById('Layer3').style.visibility='visible'">
									<s:hidden id="users.id"
										name="workLogQurey.workLog.users.id"></s:hidden>
									&nbsp;
									<img src="../images/main20100521lsearch.gif" align="absmiddle"
										style="cursor: hand"
										onClick="document.getElementById('Layer3').style.visibility='visible'">
									<br>

									<div id="Layer3"
										style="position: absolute; width: 180%; height: 20px; z-index: 1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

										<iframe frameborder="0" height="150" width="100%"
											scrolling="yes" src="../common/userchoose.jsp"
											style="border: 1px solid #E5E9EE"></iframe>

									</div>
									<%--<select id="users" name="workLogQurey.workLog.users.id"
										style="width: 99%">
									</select>
								--%>
								</td>
								<td align="center" bgcolor="#FFFFFF">
									<tags:button code="select" menu="22">
										<input type="submit" style="height: 20px" class="mmBtn"
											value="搜索" />
									</tags:button>
								</td>
							</tr>

						</s:form>
					</table>

				</td>
			</tr>
			<div style="margin: 10px">
				<tr>
					<s:iterator value="pageBean.list" var="worklog" status="st">

						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="1" bgcolor="#deebf1">
							<tr background="../images/main20100521_58.gif"
								class="alllisttitle">
								<th width="40%" align="left"
									style="padding-left: 5px; height: 22px;">
									<s:property value="title" />
								</th>
								<td align="right">
									<tags:button code="query" menu="22">
										<a href="show.action?workLog.id=${worklog.id}">查看全文</a>
									</tags:button>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF">
								<td colspan="2" align="left" scope="9" class="zczb_qua"
									style="padding-left: 5px; height: 22px;">
									<s:property value="content" escape="false" />
								</td>
							</tr>

							<td colspan="2" align="left"
								style="padding-left: 5px; height: 20px; color: #A6A6A6;">
								<s:if test="type==1">
							             个人日志
					            </s:if>
								<s:elseif test="type==2">
								部门日志
								</s:elseif>
								<s:else>
								公开日志
								</s:else>
								<%--
								| 日期：
								<s:date name="time" format="yyyy/MM/dd" />--%>
								| 最 后 修 改：
								<s:date name="timesumbit" format="yyyy/MM/dd hh:mm:ss" />
							</td>

						</table>

					</s:iterator>

				</tr>
		</table>
		<jsp:include page="/common/page.jsp" />

	</body>
</html>
