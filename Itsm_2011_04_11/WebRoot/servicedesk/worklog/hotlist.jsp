<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="init();" style="overflow:auto">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工作日志发帖排行</td>
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
					class="alllisttitle">用户</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">日志数</td>
			</tr>
			<s:iterator value="hotUsersList" var="worklog" status="st">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td align="center" width="5%" class="zczb_qua" height="22">
						<s:property value="#st.index+1" />
					</td>
					<td align="center" width="25%" class="zczb_qua" height="22">
						<s:property value="truename" />
				   </td>
                   <td align="center" class="zczb_qua" height="22">
                   <s:property value="add1"/>
                   </td> 
				</tr>
			</s:iterator>
		</table>
        <table width="99%" border="0">
  <tr>
    <td height="8"></td>
  </tr>
</table>
		<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工作日志点击排行</td>
     </tr>
    </table>
        <table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
          <tr bgcolor="#FFFFFF">
            <td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle"> 序号 </td>
            <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 标题 </td>
            <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">类型</td>
            <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 日期 </td>
            <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 点击数</td>
          </tr>
          <s:iterator value="hotLogsList" var="worklog" status="st">
            <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
              <td align="center" width="5%" class="zczb_qua" height="22"><s:property value="#st.index+1" /></td>
              <td align="center" width="25%" class="zczb_qua" height="22"><s:property value="title" /></td>
              <s:if test="type==1">
                <td align="center" width="5%" class="zczb_qua" height="22">个人日志</td>
              </s:if>
              <s:elseif test="type==2">
                <td align="center" class="zczb_qua" height="22">部门日志</td>
              </s:elseif>
              <s:else>
                <td align="center" class="zczb_qua" height="22">公开日志</td>
              </s:else>
              <td align="center" width="5%" class="zczb_qua" height="22">
						<s:date name="time" format="yyyy/MM/dd" />
					</td>
                    <td align="center" width="5%" class="zczb_qua" height="22">
							<s:property value="wordpress" />
					</td>
            </tr>
           
          </s:iterator>
        </table>
              

	</body>
</html>
