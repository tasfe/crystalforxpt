<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
<head>
<title>工程师跟踪请求处理情况</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
<script type="text/javascript" language="javascript">
	function init(){ //取出类别
		ServiceCategoryDAO.findByIntType(1,callbackserviceCategory);
	}
	function callbackserviceCategory(data){  //显示出服务类别
 		dwr.util.removeAllOptions("service_category");
   		dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   		dwr.util.addOptions("service_category",data,"id","itemZh");
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
		if(uri=="tracelist") {window.location.href="tracelist.action?pageSize="+value;}
		else if(uri=="query2") {
				var state=document.getElementById("state").value;
				window.location.href="query2.action?pageSize="+value+"&state="+state;
			}else if(uri=="query3") {
				var state=document.getElementById("state").value;
				window.location.href="query3.action?pageSize="+value+"&state="+state;
			}else {
				document.getElementById("pagepagesize").value=value;
				document.formpage.submit();
			}
	}
	function goPage(num) {
		var uri=document.getElementById("actionURI").value;
		if(uri=="tracelist") {window.location.href="tracelist.action?page="+num;}
		else if(uri=="query2") {
				var state=document.getElementById("state").value;
				window.location.href="query2.action?page="+num+"&state="+state;
			}else if(uri=="query3") {
				var state=document.getElementById("state").value;
				window.location.href="query3.action?page="+num+"&state="+state;
			}else {
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
		var nowTimes=getNowDate().split(' ');
		
		var beginTimesSplit= beginTimes[0].split('-'); 
		var endTimesSplit=endTimes[0].split('-');
		var nowTimesSplit=nowTimes[0].split('-');
		
		beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
		endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
		nowTime=nowTimesSplit[1]+'-'+(nowTimesSplit[2])+'-'+nowTimesSplit[0]+' '+nowTimes[1]; 
		
		var timeout=(Date.parse(endTime)-Date.parse(nowTime))/3600/1000;
		if(timeout<0){
			return "<font style='color:red;'>超时</font>"+"("+timeout.toFixed().substring(1,timeout.toFixed().length)+")";
		}
		var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
		return timeGap<=0?"":timeGap.toFixed();   
	}
	function getNowDate(){//获取当前时间(服务器时间)
		return "<%=(request.getAttribute("nowDate"))%>";
	}
	//重置
	function res(){
		document.getElementById("serviceRequest.requestNo").value="";
		document.getElementById("serviceRequest.usersByRequestUser.truename").value="";
		document.getElementById("serviceRequest.summary").value="";
		document.getElementById("cat.name").value="";
		document.getElementById("cat.id").value="";
		
		document.getElementById("serviceRequest.usersByEngineerId.truename").value="";
		document.getElementById("startDate").value="";
		document.getElementById("endDate").value="";
	}
</script>
</head>
<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onmousedown="document.getElementById('Layer2').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工程师跟踪请求处理情况</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			<s:form action="/engineertrace/query.action" method='post' theme="simple">
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">工单号:&nbsp;</td>
					<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.requestNo" id="serviceRequest.requestNo" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">申请人:&nbsp;</td>
					<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.usersByRequestUser.truename" id="serviceRequest.usersByRequestUser.truename" cssStyle="width:99%"/>
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">摘要或描述:&nbsp;</td>
					<td width="40%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.summary"id="serviceRequest.summary" cssStyle="width:99%"/>
					</td>
					<td  nowrap bgcolor="#deebf1"><!-- 自定义条件:&nbsp;  --></td>
					<!--<td width="21%" bgcolor="#FFFFFF">
						<select name="RequPeop" id="RequPeop" style="width: 100%">
							<option value=""></option>
							<option value="">紧急度</option>
							<option value="">优先级</option>
							<option value="">地域</option>
							<option value="">错误原因</option>
							<option value="">打印</option>
						</select>   </td> -->
					
				</tr>
				<tr>
					<td width="1%" nowrap bgcolor="#deebf1">服务类别:&nbsp;</td>
					<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px"><%--
						<select id="service_category" name="serviceRequest.serviceCategory.id" style="width:99%"></select>
				--%>
				<input id="cat.name" type="text" readOnly='true' name="serviceRequest.serviceCategory.itemZh" style="width: 79%; background-color: #FFFFFF; cursor: text"  value="<s:property value="serviceRequest.serviceCategory.itemZh"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="cat.id" name="serviceRequest.serviceCategory.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../serviceCategory/cat.action" style="border: 1px solid #E5E9EE"></iframe>

																</div>
				
					</td>
					<td width="1%" nowrap bgcolor="#deebf1">工程师:&nbsp;</td>
					<td width="20%" bgcolor="#FFFFFF" style="padding-right: 10px">
						<s:textfield name="serviceRequest.usersByEngineerId.truename" id="serviceRequest.usersByEngineerId.truename" cssStyle="width:99%" />
					</td>
					<td width="2%" nowrap bgcolor="#deebf1">申请时间:&nbsp;</td>
					<td nowrap bgcolor="#FFFFFF" style="padding-right: 10px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="49%" nowrap>
									<s:textfield id="startDate" name="startSubmitTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:47%" cssClass="Wdate" ></s:textfield>
									~
									<s:textfield id="endDate" name="endSubmitTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:48%" cssClass="Wdate"></s:textfield>
								</td>
							</tr>
						</table>
					</td>					
					<td  align="center" bgcolor="#deebf1">
						<input type="submit" class="mmBtn" value="搜索" />
						&nbsp;&nbsp;
						<input type="button"  class="mmBtn" value="清空"  onClick="res()"/>
					</td>
					<!-- <td nowrap bgcolor="#FFFFFF" style="padding-right: 10px"></td> -->
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
              <td width="60%" height="30" style="padding-top: 10px" onClick="document.getElementById('thelayer').style.display='none'"><img src="../images/main20100521dot04.gif">&nbsp;<b>所有未关闭的服务请求:</b>&nbsp;</td>
              <td width="39%" align="right">
              	<!--  	<input type=hidden id="message" name="message" value="<s:property value='message'></s:property>"> 
              	<script type="text/javascript"> 		
					var msg=document.getElementById("message").value;
					if(msg!=""){
						document.getElementById("message").value="";
						alert(msg);
					}
				</script> 
				  -->
              </td>
              <td nowrap><img src="../img/help2.jpg" width="22" height="23" align="absmiddle">&nbsp;</td>
              <td align="right" colspan="2" width="1%" height="28" valign="top" style="padding-top: 4px"><table border="0" cellspacing="0" cellpadding="0" width="100px">
                  <tr>
                    <td width="1"><img width="3"></td>
                    <td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px;
													font-size: 8pt; font-family: Tahoma; cursor: default" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()"
													nowrap><input type="text" name="thetypes" value="根据状态查看" style="width: 150px;
														border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default;
														height: 15px" readonly>
                    </td>
                    <td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px;
													PADDING-TOP: 1px"><input type="button" value="6" style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif);
														font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px;
														width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}"
														name="click">
                    </td>
                  </tr>
                  <tr id="thelayer" onClick="this.style.display='none'" style="display: none">
                    <td width="1"></td>
                    <td height="2" colspan="2"><div style="position: absolute; width: 100%; height: 115px; z-index: 9; cursor: default;	overflow-x: hidden; border: 1px outset;">
                        <table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/ServiceInput.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query3.action?state=6'">&nbsp;所有未关闭请求</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=0'">&nbsp;新创建</td>
                          </tr>
                          <!-- 
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=5'">&nbsp;部门领导审批完成</td>
                          </tr>
                           -->
                          <!--  
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/page.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=0'">&nbsp;等待派单</td>
                          </tr>
                          -->
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/ico_t1.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=7'">&nbsp;暂时挂起中</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/ServiceInput.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=1'">&nbsp;等待工程师受理</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/wait.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=2'">&nbsp;工程师正在处理中</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/delete.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=4'">&nbsp;已经被拒绝</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/pageok.gif" align="absmiddle" border="0"></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='${pageContext.request.contextPath}/engineertrace/query2.action?state=5'">&nbsp;等待用户反馈</td>
                          </tr>
                        </table>
                    </div></td>
                  </tr>
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td valign="top" background="../img/Separator.gif" style="line-height:5px;"><img src="../img/Separator.gif" width="6" height="6"></td>
        </tr>
      </table>     
      <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
        <tr>
          <th width="9%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工单号</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">服务类别 </th>          
          <th width="15%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">摘要</th>
          <th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请人</th>
          <th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请时间 </th>
          <th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">希望完成时间</th>
          <th width="10%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">剩余时间(小时)</th>          
          <th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工程师</th>
          <th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">处理进度</th>
          <th width="1%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 查看 </th>
          <th width="1%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 干预 </th>
        </tr>
        
             
        <s:iterator value="pageBean.list" var="serviceRequest">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
           <td height="30"  align="center"><s:property value="requestNo"/></td>
           <td align="center"><s:property value="serviceCategory.itemZh"/></td>
           <td align="center">
           		<s:if test="summary.length()>20">
           			<s:property value="summary.substring(0,17)+'...'"/>
           		</s:if>
           		<s:else><s:property value="summary"/></s:else>
           </td>
           <td align="center"><s:property value="usersByRequestUser.truename"/></td>
           <td align="center"><s:date name="submintTime"/></td>
           <td align="center"><s:date name="expectTime"/></td> 
           <td align="center">
           		<script type = "text/javascript" >    
           			var submitTime="<s:date name="submintTime"/>";
           			var expectTime="<s:date name="expectTime"/>"; 
      				document.write(comptime(submitTime,expectTime));
     			</script>
			</td>
           <td align="center"><s:property value="usersByEngineerId.truename"/></td>
           <td align="center"><s:if test="#serviceRequest.state==0">待派单</s:if>
          					<s:elseif test="#serviceRequest.state==1">待受理</s:elseif>
          					<s:elseif test="#serviceRequest.state==2">处理中</s:elseif>
          					<s:elseif test="#serviceRequest.state==3">流程进行中</s:elseif>
          					<s:elseif test="#serviceRequest.state==4">已拒绝</s:elseif>
          					<s:elseif test="#serviceRequest.state==5">等待用户反馈</s:elseif>
          					<s:elseif test="#serviceRequest.state==6">已关闭</s:elseif>
          					<s:elseif test="#serviceRequest.state==7">已挂起</s:elseif>
          </td>
          <td align="center" nowrap><a href="../engineertrace/show.action?flag=0&page=${page}&serviceRequest.id=<s:property value="id" />"><img src='../img/viewdetail.gif' border=0 width=18 height=18></a></td>
          <td align="center" nowrap>
          <tags:button code="intervene" menu="17">
          	<a href="../engineertrace/intervene.action?page=${page}&serviceRequest.id=<s:property value="id" />"><img src='../img/zjz.gif' border=0 width=18 height=18></a>
         </tags:button>
          </td>
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
           		<s:if test="%{actionURI == 'tracelist'}">
					<a href="tracelist.action?page=1">第一页</a>
                    <a href="tracelist.action?page=<s:property value='%{pageBean.currentPage-1}'/>">上一页</a>
				</s:if>
				<s:else>
					<s:if test="%{actionURI == 'query2'}">
						<a href="query2.action?page=1&state=<s:property value='state'/>">第一页</a>
                    	<a href="query2.action?page=<s:property value='%{pageBean.currentPage-1}'/>&state=<s:property value='state'/>">上一页</a>
					</s:if>
					<s:else>
						<s:if test="%{actionURI == 'query3'}">
							<a href="query3.action?page=1&state=<s:property value='state'/>">第一页</a>
                    		<a href="query3.action?page=<s:property value='%{pageBean.currentPage-1}'/>&state=<s:property value='state'/>">上一页</a>
						</s:if>
						<s:else>
							<a href="#" onClick="nextPage(1)">第一页</a>
							<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage-1}'/>)">上一页</a>
						</s:else>
					</s:else>
				</s:else>
           </s:else>
           <s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
           		<s:if test="%{actionURI == 'tracelist'}">
					<s:if test="%{actionURI == 'tracelist'}">
						<a href="tracelist.action?page=<s:property value='%{pageBean.currentPage+1}'/>">下一页</a>
		            	<a href="tracelist.action?page=<s:property value='%{pageBean.totalPage}'/>">最后一页</a>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="%{actionURI == 'query2'}">
						<a href="query2.action?page=<s:property value="%{pageBean.currentPage+1}"/>&state=<s:property value='state'/>">下一页</a>
                    	<a href="query2.action?page=<s:property value='%{pageBean.totalPage}'/>&state=<s:property value='state'/>">最后一页</a>
					</s:if>
					<s:else>
						<s:if test="%{actionURI == 'query3'}">
							<a href="query3.action?page=<s:property value="%{pageBean.currentPage+1}"/>&state=<s:property value='state'/>">下一页</a>
                    		<a href="query3.action?page=<s:property value='%{pageBean.totalPage}'/>&state=<s:property value='state'/>">最后一页</a>
						</s:if>
						<s:else>
							<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage+1}'/>)">下一页</a>
							<a href="#" onClick="nextPage(<s:property value='%{pageBean.totalPage}'/>)" >最后一页</a>
						</s:else>
					</s:else>
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
  			<s:hidden id="state" name="state"></s:hidden>
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
  			</s:form>
		</td>
	  </tr>
</table> 
</div>
 
</body>
</html>
