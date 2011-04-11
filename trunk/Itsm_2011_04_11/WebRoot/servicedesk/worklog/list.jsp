<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产管理</title>
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
     var a = "<s:property value="workLogQurey.workLog.location.id" />";
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
		onLoad="init();" style="overflow: auto">
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
		<table width="99%" border="0" cellspacing="1" align="center"
			bgcolor="#b5d6e6">
			<s:form action="myquery" method='post' theme="simple" name="form">
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
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
							<!--<img onClick="WdatePicker({el:$dp.$('workLogQurey.startDate')})" src="../js/DatePicker/skin/datePicker.gif" />-->
							<strong>—</strong>
							<s:textfield id="endDate" name="workLogQurey.endDate"
								onclick="WdatePicker()" cssClass="Wdate"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
							<!--<img onClick="WdatePicker({el:$dp.$('workLogQurey.endDate')})" src="../js/DatePicker/skin/datePicker.gif" />-->
						</td>
				</tr>
				<tr>
					<td align="right" bgcolor="#deebf1">
						日志类型：
					</td>
					<td bgcolor="#FFFFFF" width="16%">
						<select name="workLogQurey.workLog.type"
							id="workLogQurey.workLog.type" style="width: 99%">
							<option value="0">
								--请选择--
							</option>
							<option value="1">
								个人日志
							</option>
							<option value="2">
								部门日志
							</option>
							<option value="3">
								公开日志
							</option>
						</select>
					</td>
					<script type="text/javascript">
var types='<s:property value="workLogQurey.workLog.type"/>';
if(types!=null)
	{
	document.getElementById("workLogQurey.workLog.type").value=types;
	}
	

</script>
					<td align="right" bgcolor="#deebf1">
						&nbsp;
					</td>
					<td width="16%" align="left" bgcolor="#FFFFFF">
						&nbsp;
					</td>
					<td width="10%" bgcolor="#deebf1" align="right">
						&nbsp;
					</td>
					<td width="16%" align="left" bgcolor="#FFFFFF">
						&nbsp;
					</td>
					<td align="center" bgcolor="#FFFFFF">
						<tags:button code="select" menu="21">
							<input type="submit" style="height: 20px" class="mmBtn"
								value="搜索" />
						</tags:button>
					</td>
				</tr>

			</s:form>
		</table>
		<table width="99%" border="0" height="30" align="center">
			<tr>
				<td>
					<table width="60" border="0" cellpadding="0" cellspacing="0"
						align="right">
						<tags:button code="add" menu="21">
							<tr onClick="window.location= 'addInPut.action'"
								style="cursor: hand;">
								<td align="right">
									<img src="../images/addnew001.gif">
								</td>
								<td nowrap background="../images/addnew002.gif">
									新增日志
								</td>
								<td align="right">
									<img src="../images/addnew003.gif">
								</td>
							</tr>
						</tags:button>
					</table>
				</td>
			</tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					序号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					标题
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					类型
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					摘要
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					查看
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					修改
				</td>
				<td align="center" colspan="3"
					background="../images/main20100521_58.gif" class="alllisttitle">
					删除
				</td>
			</tr>
			<s:iterator value="pageBean.list" var="worklog" status="st">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
					onMouseOut="this.bgColor='#FFFFFF'">
					<td align="center" width="5%" class="zczb_qua" height="22">
						<s:property value="#st.index+1" />
					</td>
					<td align="center" width="25%" class="zczb_qua" height="22">
						<s:property value="title" />
					</td>
					<s:if test="type==1">
						<td align="center" width="5%" class="zczb_qua" height="22">
							个人日志
						</td>
					</s:if>
					<s:elseif test="type==2">
						<td align="center" class="zczb_qua" height="22">
							部门日志
						</td>
					</s:elseif>
					<s:else>
						<td align="center" class="zczb_qua" height="22">
							公开日志
						</td>
					</s:else>
					<td align="center" width="35%" class="zczb_qua" height="22">
						<s:property value="note" />
					</td>
					<td align="center" width="15%" class="zczb_qua" height="22">
						<s:date name="time" format="yyyy/MM/dd" />
					</td>
					<td align="center" width="5%" class="zczb_qua" height="22">
						<tags:button code="query" menu="21">
							<a href="show.action?workLog.id=${worklog.id}">查看</a>
						</tags:button>
					</td>
					<td align="center" width="5%" class="zczb_qua" height="22">
						<tags:button code="update" menu="21">
							<img src="../images/edt.gif">
							<a href="addInPut.action?workLog.id=${worklog.id}">修改</a>
						</tags:button>
					</td>
					<td align="center" width="5%" class="zczb_qua" height="22">
						<tags:button code="delete" menu="21">
							<img src="../images/del.gif">
							<a href="deletequery.action?workLog.id=${worklog.id}&page=${page}&pageSize=${pageSize}" onclick="javascript:return del()">删除</a>
						</tags:button>
					</td>
				</tr>
			</s:iterator>
		</table>
		<jsp:include page="/common/page.jsp" />
	</body>
</html>
