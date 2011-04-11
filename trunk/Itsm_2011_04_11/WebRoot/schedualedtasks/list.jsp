<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
<head>
<title>查看计划任务</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
<script type="text/javascript" language="javascript">
	function getDAO(){ //取出类别  			
   		ServiceCategoryDAO.findByIntType(2,callbackcategory);
	}
	function callbackcategory(data){  //显示出类别
   		dwr.util.removeAllOptions("category");
   		dwr.util.addOptions("category",{'-1':'--请选择--'});
   		dwr.util.addOptions("category",data,"id","itemZh");   
	}
	function showoroff(id,img) {
		if(document.getElementById(id).style.display==''){
			document.getElementById(id).style.display='none';
			document.getElementById(img).src='../img/jia.jpg';			
		}else {
			document.getElementById(id).style.display='';
			document.getElementById(img).src='../img/jian.jpg';
		}
	}
	function RowsPerPage(value) {
		var uri=document.getElementById("actionURI").value;
		if(uri=="list") {window.location.href="list.action?pageSize="+value;}
		else {
			document.getElementById("pagepagesize").value=value;
			document.formpage.submit();
		}
	}
	function goPage(num) {
		var uri=document.getElementById("actionURI").value;
		if(uri=="list") {window.location.href="tracelist.action?page="+num;}
		else{
			document.getElementById("pagepage").value=num;
			document.formpage.submit();
		}
	}
	function nextPage(num) {
		document.getElementById("pagepage").value=num;
		document.formpage.submit();
	}
	function cron(){
		var id=document.getElementById("taskid").value;
		window.location.href="croninput.action?id="+id;
	}
</script>
</head>
<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看计划任务</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			<s:form action="query.action" method='post' theme="simple">
				<tr>
					<td width="5%" nowrap bgcolor="#deebf1">任务类别:&nbsp;</td>
					<td width="25%" bgcolor="#EBF4F5"  style="padding-right: 10px">
						<select id="category" name="schedualedTasks.serviceCategory.id" style="width: 99%"></select>
					</td>
					<td width="5%" nowrap bgcolor="#deebf1">摘要:&nbsp;</td>
					<td width="25%"  bgcolor="#EBF4F5"  style="padding-right: 10px">
						<s:textfield name="schedualedTasks.keyWord" id="schedualedTasks.keyWord" cssStyle="width:99%"/>
					</td>
					<td width="5%" nowrap bgcolor="#deebf1">创建时间:&nbsp;</td>
					<td width="30%"  bgcolor="#EBF4F5"  style="padding-right: 10px">
						<input type="text" id="submitAt" name="schedualedTasks.submitAt" onclick="WdatePicker()" style="width:40%">
						<img onClick="WdatePicker({el:$dp.$('submitAt')})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;<b>~</b>&nbsp;
						<input type="text" id="approveAt" name="schedualedTasks.approveAt" onclick="WdatePicker()" style="width:40%">
							<img onClick="WdatePicker({el:$dp.$('approveAt')})" src="../js/DatePicker/skin/datePicker.gif" />
					</td>
					<td width="5%" align="center" bgcolor="#deebf1">
						<input type="submit" style="height: 20px" class="mmBtn" value="搜索" />
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
              <td width="60%" height="30" style="padding-top: 10px" onClick="document.getElementById('thelayer').style.display='none'"><img src="../images/main20100521dot04.gif">&nbsp;<b>所有计划任务:</b>&nbsp;</td>
              <td width="39%" align="right"><font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	
              </td>
              <td nowrap><img src="../img/help2.jpg" width="22" height="23" align="absmiddle">&nbsp;</td>
              <td align="right" colspan="2" width="1%" height="28" valign="top" style="padding-top: 4px"><table border="0" cellspacing="0" cellpadding="0" width="100px">
                  <tr>
                    <td width="1"><img width="3"></td>
                    <td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px;
													font-size: 8pt; font-family: Tahoma; cursor: default" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()"
													nowrap><input type="text" name="thetypes" value="转至..." style="width: 150px;
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
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='add.action'">&nbsp;添加新任务</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='calendar.action'">&nbsp;工作日历</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%"><img width="18" height="18" src="../img/task.gif" align="absmiddle"></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'"
																	bgcolor="white" onClick="location.href='week.action'">&nbsp;按周查看任务</td>
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
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
        <tr>
          <th width="10%" height="20" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工单号</th>
          <th width="13%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">任务类别 </th>          
          <th width="23%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">摘要</th>
          <th width="13%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">执行时间 </th>
          <th width="5%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请人</th>
          <th width="5%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">负责人</th>
          <th width="7%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">执行频率</th>
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">未执行</th>          
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">已执行</th>
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">已复审</th>
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">查看</th>
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 修改 </th>
          <th width="4%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 删除 </th>
        </tr> 
        <s:iterator value="pageBean.list" var="schedualedTasks" status='st'>
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
           <td height="30"  align="center"><s:property value="proNo"/></td>
           <td align="center"><s:property value="serviceCategory.itemZh"/></td>
           <td align="center">
           		<s:if test="keyWord.length()>20"><s:property value="keyWord.substring(0,18)+'...'"/></s:if>
           		<s:else><s:property value="keyWord"/></s:else>
           	</td>
           <td align="center">
           		<s:if test="#schedualedTasks.rate.equals('Only')"><s:date name="submitAt" format="yyyy-MM-dd"/></s:if>
           		<s:else><s:date name="submitAt" format="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;<s:date name="approveAt" format="yyyy-MM-dd"/></s:else>
           </td>
           <td align="center"><s:property value="application"/></td>
           <td align="center"><s:property value="user.truename"/></td>
           <td align="center"><s:if test="#schedualedTasks.rate.equals('Only')">只执行一次</s:if>
          					<s:elseif test="#schedualedTasks.rate.equals('Half')">每半年一次 </s:elseif>
          					<s:elseif test="#schedualedTasks.rate.equals('Quar')">每季度一次 </s:elseif>
          					<s:elseif test="#schedualedTasks.rate.equals('Month')">每月一次</s:elseif>
          					<s:elseif test="#schedualedTasks.rate.equals('Week')">每周一次</s:elseif>
          					<s:elseif test="#schedualedTasks.rate.equals('Day')">每日一次</s:elseif></td> 
           <td align="center"><s:property value="sum1"/></td>
           <td align="center"><s:property value="sum2"/></td>
           <td align="center"><s:property value="sum3"/></td>
           <td align="center"><a href="#" onClick="javascript:showoroff('<s:property value="id"/>','${st.index+1}')"><img id="${st.index+1}" src='../img/jia.jpg' border=0 width=18 height=18></a></td>
           <td align="center" nowrap>
           		 <tags:button code="update" menu="42">
           		<s:if test="#schedualedTasks.tmp1.equals('DEL')"></s:if>
           		<s:else>
           		<a href="updateinput.action?id=${id}&page=${page}"><img src='../images/edt.gif' border=0 width=18 height=18></a>
           		</s:else>
           		</tags:button>
           	</td>
           <td align="center" nowrap>
           		<tags:button code="delete" menu="42">
           		<s:if test="#schedualedTasks.tmp1.equals('DEL')"></s:if>
           		<s:else>
           		<a href="#" onClick="if(confirm('确认删除吗？')) {window.location.href='deletest.action?id=${id}&page=${page}';}"><img src='../img/delete.gif' border=0 width=18 height=18></a>
		   		</s:else>
		   		</tags:button>
		   </td>
        </tr >
        <tr id="<s:property value='id'/>" style="display:none">
        	<td width="100%" colspan="13" bgcolor="#FFFFFF" >
        		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
        			<s:iterator value="#schedualedTasks.schedualedTaskDetails" var="schedualedTaskDetail">
        			<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						<td  align="center" height="22" width="23%" align="left">
				 			<img src="../images/icons/icon047a1.gif">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="taskCode"/>
						</td>
            			<td  align="center" width="46%">
							<s:iterator value="#schedualedTaskDetail.schedualedTaskUsers" var="schedualedTaskUser">
								<s:property value="users.truename"/>
							</s:iterator>
						</td>
            			<td  align="center" width="11%"><s:date name="schedualedTime" format="yyyy-MM-dd"/></td>
            			<td  align="center" width="8%"><s:if test="state.equals('wait')">未执行</s:if>
          							<s:elseif test="state.equals('work')"><font style="color:blue">已执行</font> </s:elseif>
          							<s:elseif test="state.equals('fini')"><font style="color:red">已复审 </font></s:elseif></td>
            			<td align="center" width="4%"><a href="#" onClick="window.location.href='show.action?page=${page}&schedualedTaskDetail.id=<s:property value="id" />'"><img src='../img/viewdetail.gif' border=0 width=18 height=18 ></a></td>
            			<td align="center" width="4%"><a href="updatestdinput.action?members=${engineer}&id=${id}&page=${page}"><img src='../images/edt.gif' border=0 width=18 height=18></a></td>
            			<td align="center" width="4%"><a href="#" onClick="if(confirm('确认删除吗？')) {window.location.href='deletestd.action?id=${id}&page=${page}';}"><img src='../img/delete.gif' border=0 width=18 height=18></a></td>
         			</tr>
         			</s:iterator>          			
         			<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						<td  align="center" height="22">
				 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="../img/afc.gif" width=18 height=18>
						</td>
						<td  align="center" colspan="2"></td>
						<td  align="center" colspan="2"><s:if test="#schedualedTasks.rate.equals('Day')"><a href="arrange.action?id=<s:property value='#schedualedTasks.id'/>"><img src="../img/task.gif" width=18 height=18 title="排班"></a></s:if></td>
						<td  align="center" colspan="2"><s:if test="#schedualedTasks.rate.equals('Day')"><a href="croninput.action?id=<s:property value='#schedualedTasks.id'/>"><img src="../images/icons/icon1.gif" width=18 height=18 title="定制时间"></a></s:if></td>
					</tr>
        			
        		</table>  
        	</td>
        </tr>
     </s:iterator>
      </table>
      <table cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
          <td nowrap style="padding-: 6px; padding-bottom: 1px"></td>
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
           		<s:if test="%{actionURI == 'list'}">
					<a href="list.action?page=1">第一页</a>
                    <a href="list.action?page=<s:property value='%{pageBean.currentPage-1}'/>">上一页</a>
				</s:if>
				<s:else>
					<a href="#" onClick="nextPage(1)">第一页</a>
					<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage-1}'/>)">上一页</a>
				</s:else>
           </s:else>
           <s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
           		<s:if test="%{actionURI == 'list'}">
					<s:if test="%{actionURI == 'list'}">
						<a href="list.action?page=<s:property value='%{pageBean.currentPage+1}'/>">下一页</a>
		            	<a href="list.action?page=<s:property value='%{pageBean.totalPage}'/>">最后一页</a>
					</s:if>
				</s:if>
				<s:else>
					<a href="#" onClick="nextPage(<s:property value='%{pageBean.currentPage+1}'/>)">下一页</a>
					<a href="#" onClick="nextPage(<s:property value='%{pageBean.totalPage}'/>)" >最后一页</a>
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
			<s:form name="formpage" action="%{actionURI}.action" method="post" theme="simple">
				<s:hidden id="pagepage" name="page" value="1"></s:hidden>
				<s:hidden id="pagepagesize" name="pageSize"></s:hidden>				
   				<s:hidden id="serviceCategoryid" name="schedualedTasks.serviceCategory.id"></s:hidden>
   				<s:hidden id="sTkeyWord" name="schedualedTasks.keyWord"></s:hidden>
  				<s:hidden id="stsubmitAt" name="schedualedTasks.submitAt"></s:hidden>
				<s:hidden id="stapproveAt" name="schedualedTasks.approveAt"></s:hidden>
  			</s:form>
		</td>
	  </tr>
</table> 
</div>
 
</body>
</html>
