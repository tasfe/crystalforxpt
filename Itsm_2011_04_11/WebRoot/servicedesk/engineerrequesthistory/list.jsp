<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>工程师服务请求历史</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
<script type="text/javascript" language="javascript">
	function init(){ //取出类别
		ServiceCategoryDAO.findByIntType(1,callbackserviceCategory);
		DepartmentDAO.findAll(callbackserviceDepartment);
	}
	function callbackserviceCategory(data){  //显示出服务类别
 		dwr.util.removeAllOptions("service_category");
   		dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   		dwr.util.addOptions("service_category",data,"id","itemZh");
	}
	function callbackserviceDepartment(data){  //显示出部门类别
 		dwr.util.removeAllOptions("department");
   		dwr.util.addOptions("department", [{id:'-1',name:'--请选择--'}],"id","name");
   		dwr.util.addOptions("department",data,"id","name");
	}
	function dateJudge(){   
		var startDate=document.getElementById("startDate").value;
		var endDate=document.getElementById("endDate").value;   
		if(startDate<=endDate){
			return true;
		}else{
			alert("请输入正确的日期！");
			return false;
		}
	}
	function RowsPerPage(value) {
		var uri=document.getElementById("actionURI").value;
		if(uri=="requestlist") {window.location.href="requestlist.action?pageSize="+value;}
		else { 
				document.getElementById("pagepagesize").value=value;
				document.formpage.submit();
		}
	}
	function goPage(num) {
		var uri=document.getElementById("actionURI").value;
		if(uri=="requestlist") {window.location.href="requestlist.action?page="+num;}
		else if(uri=="query4") {
				document.getElementById("pagepage").value=num;
				document.formpage.submit();
			}
	}
	function nextPage(num) {
		document.getElementById("pagepage").value=num;
		document.formpage.submit();
	}
	function comptime(beginTime,endTime){   
		if(!beginTime || !endTime){
			return "";
		}
		var beginTimes=beginTime.split(' ');  
		var endTimes=endTime.split(' '); 
		
		var beginTimesSplit= beginTimes[0].split('-'); 
		var endTimesSplit=endTimes[0].split('-');
		
		beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
		endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
		var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
		return timeGap<0?"":timeGap.toFixed();   
	}
	//清空
	function res(){
		document.getElementById("serviceRequest.requestNo").value="";
		document.getElementById("serviceRequest.usersByRequestUser.truename").value="";
		document.getElementById("cat.name").value="";
		document.getElementById("cat.id").value="";
		
		document.getElementById("serviceRequest.summary").value="";
		document.getElementById("serviceRequest.usersByEngineerId.truename").value="";
		document.getElementById("department.name").value="";
		document.getElementById("department.id").value="";
		
		document.getElementById("startDate").value="";
		document.getElementById("endDate").value="";
		document.getElementById("beginTime").value="";
		document.getElementById("finishTime").value="";
		document.getElementById("Close").options[0].selected='true';
	}
</script>
</head>
<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onmousedown="document.getElementById('Layer2').style.visibility='hidden';document.getElementById('Layer1').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工程师服务请求历史</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;"><table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			<s:form action="query4.action" method='post' theme="simple">
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">工单号:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.requestNo" id="serviceRequest.requestNo" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">申请人:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.usersByRequestUser.truename" id="serviceRequest.usersByRequestUser.truename" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">服务类别:&nbsp;</td>
					<td width="25%" bgcolor="#FFFFFF" style="padding-right: 10px">
					
						<input id="cat.name" type="text" name="serviceRequest.serviceCategory.itemZh" style="width: 79%; background-color: #FFFFFF; cursor: text" readonly value="<s:property value="serviceRequest.serviceCategory.itemZh"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="cat.id" name="serviceRequest.serviceCategory.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../serviceCategory/cat.action" style="border: 1px solid #E5E9EE"></iframe>

																</div><%--
						<select id="service_category" name="serviceRequest.serviceCategory.id" style="width:99%"></select>
					--%></td>
					<td width="18%" nowrap bgcolor="#deebf1"></td>
						
					</td>
				</tr>
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">摘要或描述:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.summary"id="serviceRequest.summary" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">工程师:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.usersByEngineerId.truename" id="serviceRequest.usersByEngineerId.truename" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">部门:&nbsp;</td>
					<td width="25%" bgcolor="#FFFFFF" style="padding-right: 10px">
					
					
						<input id="department.name" type="text" name="serviceRequest.departmentByRequestDept.name" style="width: 79%; background-color: #FFFFFF; cursor: text" readonly value="<s:property value="serviceRequest.departmentByRequestDept.name"/>" onClick="document.getElementById('Layer1').style.visibility='visible'">
                    <s:hidden id="department.id" name="user.department.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"><br>

																<div id="Layer1" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../department/departmentList.action" style="border: 1px solid #E5E9EE"></iframe>

																</div>
					
					
					
					<%--
					
					
					
						<select id="department" name="serviceRequest.departmentByRequestDept.id" style="width:99%"></select>
					--%></td>					
					<td width="18%" align="right" bgcolor="#deebf1">
						
					</td>
				</tr>
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">申请时间:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%" nowrap>
									<s:textfield id="startDate" name="startSubmitTime" onclick="WdatePicker()" style="width:45%"></s:textfield>
									&nbsp;<b>~</b>&nbsp;
									<s:textfield id="endDate" name="endSubmitTime" onclick="WdatePicker()" style="width:46%"></s:textfield>
								</td>
							</tr>
						</table>
					</td>		
					<td width="1%" nowrap bgcolor="#deebf1">结束时间:&nbsp;</td>
					<td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%" nowrap>
									<s:textfield id="beginTime" name="startFinishTime" onclick="WdatePicker()" style="width:45%"></s:textfield>
									&nbsp;<b>~</b>&nbsp;
									<s:textfield id="finishTime" name="endFinishTime" onclick="WdatePicker()" style="width:46%"></s:textfield>
								</td>
							</tr>
						</table>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">完成结果:&nbsp;</td>
					<td width="25%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<select name="serviceRequest.isFinished" id="Close" style="width:99%">
							<option value="-1">--请选择--</option>
							<option value="1">已经成功解决</option>
							<option value="0">无法解决的事件</option>
						</select>
					</td>
					<td width="18%" nowrap bgcolor="#deebf1">
						&nbsp;&nbsp;
						<input type="submit" style="height: 20px" class="mmBtn" value="搜索" />
						&nbsp;&nbsp;
						<input type="button"  style="height: 20px" class="mmBtn" value="清空"  onClick="res()"/>
					</td>
				</tr>
				</s:form>
	  </table>
	  
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" style="font-size:12px; color:#333333; font-weight:bold;">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" style="font-size:12px; color:#333333; font-weight:bold;"><table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td width="99%" height="30" style="padding-top: 10px" ><img src="../images/main20100521dot04.gif">&nbsp;<b>服务请求列表:</b>&nbsp;</td>
              <td nowrap>&nbsp;</td>
              <td></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td valign="top"  style="line-height:5px;"></td>
        </tr>
      </table>  
         
      <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
        <tr>
          <th width="8%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工单号</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">服务类别</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请人</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">派单人</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工程师</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请时间</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">结束时间</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">处理总时长(小时)</th>
          <th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">状态 </th>
          <th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 查看 </th>
        </tr>
        
             
        <s:iterator value="pageBean.list" var="serviceRequest">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
           <td height="30"><s:property value="requestNo"/></td>
           <td align="center"><s:property value="serviceCategory.itemZh"/></td>
           <td align="center"><s:property value="usersByRequestUser.truename"/></td>
           <td align="center"><s:property value="usersByOperatorId.truename"/></td>           
           <td align="center"><s:property value="usersByEngineerId.truename"/></td> 
           <td align="center"><s:date name="submintTime"/></td>
           <td align="center"><s:date name="finishTime"/></td>
           <td align="center">
           		<script type="text/javascript">
           			var beginTime="<s:date name="submintTime"/>";
           			var endTime="<s:date name="finishTime"/>";
           			document.write(comptime(beginTime,endTime));
           		</script>
           </td>
           <td align="center"><s:if test="#serviceRequest.state==0">待派单</s:if>
          					<s:elseif test="#serviceRequest.state==1">待受理</s:elseif>
          					<s:elseif test="#serviceRequest.state==2">处理中</s:elseif>
          					<s:elseif test="#serviceRequest.state==3">流程进行中</s:elseif>
          					<s:elseif test="#serviceRequest.state==4">已拒绝</s:elseif>
          					<s:elseif test="#serviceRequest.state==5">等待用户反馈</s:elseif>
          					<s:elseif test="#serviceRequest.state==6">已关闭</s:elseif>
          					<s:elseif test="#serviceRequest.state==7">已挂起</s:elseif>
          </td>
          <td align="center" nowrap><a href="../engineertrace/show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img src='../img/viewdetail.gif' border=0 width=18 height=18></a></td>          
        </tr>
     </s:iterator>  
        
     
        
        
      </table>
      <table cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
          <td nowrap style="padding-top: 6px; padding-bottom: 1px"></td>
          <td align="right" nowrap style="padding-top: 6px; padding-bottom: 1px">
       <!--    	  <input name="button2" type="button" class="mmBtn" onClick="window.location='ViewReport.jsp'" value="查看报表">
              <input name="button2" type="button" class="mmBtn" onClick="window.open('customize.jsp','','width=400,height=350,scrollbars=yes,menubar=no,resizable=no,top=60,left=100,status=yes')" value="自定义">
           -->    
              <input name="button2" type="button" class="mmBtn" onClick="history.go(-1)" value="后退" >
          </td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">	
	<tr height="24" vAlign="middle"  align="center">
		<td height="30" align="left">
           <s:if test="%{pageBean.currentPage == 1}">第一页 上一页</s:if>
           <s:else>
           		<s:if test="%{actionURI == 'requestlist'}">
					<a href="requestlist.action?page=1">第一页</a>
                    <a href="requestlist.action?page=<s:property value='%{pageBean.currentPage-1}'/>">上一页</a>
				</s:if>
				<s:else>
					<a href="#" onClick="nextPage(1)">第一页</a>
					<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage-1}'/>)">上一页</a>
				</s:else>
           </s:else>
           <s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
           		<s:if test="%{actionURI == 'requestlist'}">
					<a href="requestlist.action?page=<s:property value='%{pageBean.currentPage+1}'/>">下一页</a>
		            <a href="requestlist.action?page=<s:property value='%{pageBean.totalPage}'/>">最后一页</a>
				</s:if>
				<s:else>
					<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage+1}'/>)">下一页</a>
					<a href="#" onClick="nextPage(<s:property value='pageBean.totalPage'/>)" >最后一页</a>
				</s:else>            
			</s:if>
		<s:else> 下一页 最后一页</s:else>
		</td>	
		
		<td valign="middle" align="right">
			共<s:property value="pageBean.allRow"/> 条记录&nbsp;&nbsp;
			共<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;
			当前第<s:property value="pageBean.currentPage"/>页&nbsp;&nbsp;
			每页<s:property value="pageBean.pageSize"/>行&nbsp;&nbsp;
			每页
			<SELECT	style="COLOR: #191970; HEIGHT: 22px; BACKGROUND-COLOR: #E8FFBB"	name="pageSize" onchange="RowsPerPage(this.value)">
				<s:iterator var="counter" begin="10" end="pageBean.allRow+10" step="10" >
					<option value="<s:property value='#counter'/>"<s:if test="pageBean.pageSize == #counter"> selected</s:if>><s:property value='#counter'/></option>
				</s:iterator>
			</SELECT>
			行&nbsp; 
			转到第
			<SELECT style="COLOR: #191970; HEIGHT: 22px; BACKGROUND-COLOR: #E8FFBB" name="page" onchange="goPage(this.value)">
				<s:iterator var="counter" begin="1" end="pageBean.totalPage" step="1" >
					<option value="<s:property value='#counter'/>" <s:if test="pageBean.currentPage == #counter"> selected</s:if>><s:property value='#counter'/></option>
				</s:iterator>
		    </SELECT>页
		</td>
		<td>
			<s:hidden id="actionURI" name="actionURI"></s:hidden>			
  			<s:hidden id="state" name="serviceRequest.state"></s:hidden>
			<s:form name="formpage" action="%{actionURI}.action" method="post" theme="simple">
				<s:hidden id="pagepage" name="page" value="1"></s:hidden>
				<s:hidden id="pagepagesize" name="pageSize"></s:hidden>
   				<s:hidden id="requestNo" name="serviceRequest.requestNo"></s:hidden>
   				<s:hidden id="serviceCategoryid" name="serviceRequest.serviceCategory.id"></s:hidden>
  				<s:hidden id="usersByRequestUsertruename" name="serviceRequest.usersByRequestUser.truename"></s:hidden>
				<s:hidden id="startDate" name="serviceRequest.startDate"></s:hidden>
  				<s:hidden id="endDate" name="serviceRequest.endDate"></s:hidden>
  				<s:hidden id="summary" name="serviceRequest.summary"></s:hidden>
  				<s:hidden id="userByServiceFromusername" name="serviceRequest.usersByEngineerId.truename"></s:hidden>  				
  				<s:hidden id="serviceRequestisFinished" name="serviceRequest.isFinished"></s:hidden>
  				<s:hidden id="serviceRequestbeginTime" name="serviceRequest.beginTime"></s:hidden>
  				<s:hidden id="serviceRequestfinishTime" name="serviceRequest.finishTime"></s:hidden>
  				<s:hidden id="serviceRequestdepartmentBySourceDept.id" name="serviceRequest.departmentBySourceDept.id"></s:hidden>
  			</s:form>
		</td>
	  </tr>
</table> 
</div>
</body>
</html>
