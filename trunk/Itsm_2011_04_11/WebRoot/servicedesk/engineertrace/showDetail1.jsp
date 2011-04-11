<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>查看事件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
 <script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/SeverityTypDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
</head>
<script language="JavaScript">
function queryKb(){
	var keyword="";
	if(document.getElementById('summ').value){
		keyword=document.getElementById('summ').value;
	}
	window.open('${pageContext.request.contextPath}/servicedesk/engineerrequest/index_kb.jsp?keyword='+keyword, 'newwindow');
}
function getDAO(){ //取出类别
   RoleteamDAO.findAllByType(callbackroleteam);
   SeverityTypDAO.findByIntType(1,callbackessential);
   SeverityTypDAO.findByIntType(2,callbackemergency);
}
function callbackroleteam(data){  //显示出工程师分组
	dwr.util.removeAllOptions("engineerteam1");
   dwr.util.removeAllOptions("engineerteam2");
   document.getElementById("ITer").src="about:blank";
   dwr.util.addOptions("engineerteam1",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam1",data,"id","name");
   dwr.util.addOptions("engineerteam2",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam2",data,"id","name");    
}
function callbackessential(data){  //显示出影响度
   dwr.util.removeAllOptions("essential");
   dwr.util.addOptions("essential",{'-1':'影响度'});
   dwr.util.addOptions("essential",data,"severityValue","severityType");   
}
function callbackemergency(data){  //显示出紧急度
   dwr.util.removeAllOptions("emergency");
   dwr.util.addOptions("emergency",{'-1':'紧急度'});
   dwr.util.addOptions("emergency",data,"severityValue","severityType");   
}
function act(){
	value=document.getElementById('adviseplan').value
	if(value!='') {
		if(value.length>200) alert("您输入的字数太多，请控制在200字以内！");
		else  if(confirm('你确认要提交吗？')) document.getElementById('formAct').submit();}
	else alert('请填写分析意见及解决计划！');
}
function trans(){
	var flag;
	var all=document.getElementsByName('flag');
	if(all[0].checked) flag=0;
	else flag=1;
	var val1=document.getElementById('ITprinc').value;
	var val2=document.getElementById('trancause').value;
	var val3=document.getElementById('engineerteam1').value;
	if(flag==1){
		if(val1!=''){
			if(val2!='') {
				if(val2.length>200) alert("您输入的字数太多，请控制在200字以内！");
				else if(confirm('你确认要提交吗？')) document.getElementById('formTrans').submit();
			}else alert('请填写转交原因！');
		}else alert('请选择工程师！');
	}else if(val3!=-1) {
		if(val2!='') {
			if(val2.length>200) alert("您输入的字数太多，请控制在200字以内！");
			else if(confirm('你确认要提交吗？')) document.getElementById('formTrans').submit();
		}else alert('请填写转交原因！');
	} else alert('请选择工程师分组！'); 
}
function ref(){
	var value=document.getElementById('refusecause').value;
	if(value!='') {
		if(value.length>200) alert("您输入的字数太多，请控制在200字以内！");
		else if(confirm('你确认要提交吗？')) document.getElementById('formRef').submit();}
	else alert('请填写拒绝原因！');
}
function retu(){
	var value=document.getElementById('returncause').value;
	if(value!='') {
		if(value.length>200) alert("您输入的字数太多，请控制在200字以内！");
		else if(confirm('你确认要提交吗？')) document.getElementById('formRet').submit();}
	else alert('请填写退回原因！');
}
function fin(){
	var val1=document.getElementById('summ').value;
	var val2=document.getElementById('serviceRequestSolution').value;
	if(val1!=''){
		if(val2!='') {
			if(confirm('你确认要提交吗？')) document.getElementById('formFin').submit();
		}else  alert('请填写结果或解决方案！');
	}
	 else alert('请填写摘要！'); 
}

function getInfo2(){
	var roleteam = document.getElementById("engineerteam2").value;
	document.getElementById("ITer").src="../role/query.action?role.id="+roleteam;
}

var fileInputNumber = 0;
  function addFile()
　　  	{	var strFile = "file"+fileInputNumber;
			var filePath = document.getElementById(strFile);
			document.getElementById(strFile).style.display = "none";
			var paths = filePath.value.split("\\");
	        var name = paths[paths.length-1];
　　   		var str1 =
				'<div style="background-color:#E7EBF7">'+
				'<font size="2" style="width:300px">'+name+'</font>'+
				'<a href="#"><img onclick="removeFile(this,'+strFile+')" src="../img/del2.gif" border="0" alt="删除附件"/></a>'+
				'</div>';
				var _file = document.getElementById("_file");
　　   		    _file.insertAdjacentHTML("beforeend",str1);

			addInput();
　　    	}
	
	function addInput(){
			fileInputNumber++;
			var strFile = "file"+fileInputNumber;
		　　 var str2 = '<input name="file" id="'+strFile+'" type="file" value="添加附件" onchange="addFile()" />';
			var _input = document.getElementById("input");
　　   		_input.insertAdjacentHTML("afterbegin",str2);
	
	}
	
	function removeFile(id,strFile) {
　　      var new_tr = id.parentNode.parentNode;
　　      try {
　　          var tmp = new_tr.parentNode;
　　          tmp.removeChild(new_tr);
			removeInput(strFile);
　　       } catch(e) {}
　　}

	function removeInput(strFile) {
　　      var _input = document.getElementById("input");
　　      try {
　　          input.removeChild(strFile);
　　       } catch(e) {}
　　}
</script>


<body onLoad="getDAO()"leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件详细情况</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: scroll; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1">
		<tr>
			<td valign="top" id="mainright">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
					<Script LANGUAGE="JavaScript">
					function Table(id){
						var oldid = document.getElementById("tmp").value;
						if (id!=oldid) {
							document.getElementById("left_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_left.gif";
							document.getElementById("right_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_right.gif";
							document.getElementById(oldid).background="${pageContext.request.contextPath }/img/tab_un.gif";
							document.getElementById(oldid).style.paddingTop="5px";
							document.getElementById(oldid+"_table").style.display="none";
							document.getElementById("left_"+id).src="${pageContext.request.contextPath }/img/tab_ch_left.gif";
							document.getElementById("right_"+id).src="${pageContext.request.contextPath }/img/tab_ch_right.gif";
							document.getElementById(id).background="${pageContext.request.contextPath }/img/tab_ch.gif";
							document.getElementById(id).style.paddingTop="3px";
							document.getElementById(id+"_table").style.display="";
							document.getElementById("tmp").value=id;
						}
					}
					</Script>
					<tr>
						<td>
							
							<jsp:include page="common.jsp"/> 
							
						</td>
					</tr>					
					<tr>
						<td>
							<table cellspacing=0 cellpadding=0 border=0 width="100%">
								<tr>
									<td height="12" style="padding-top: 8px" nowrap> <a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
									
									<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
										<input type="button" id="button1" class="mmBtn" value="接受并开始受理" onclick="ShowApprove('Act')">
										<input type="button" id="button2" class="mmBtn" value="快速解决" onclick="ShowApprove('Fin')">
										<input type="button" id="button3" class="mmBtn" value="转交事件" onclick="ShowApprove('Tran')">
										<input type="button" id="button4" class="mmBtn" value="退回任务" onclick="ShowApprove('Del')">
										<input type="button" id="button5" class="mmBtn" value="拒绝" onclick="ShowApprove('Ref')">									
										<s:if test="flag==0"><input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)"></s:if>
										<s:else><input type="button" id="button7" class="mmBtn" value="关闭" onclick="window.close()"></s:else>
										
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divAct").style.display = "none";
								document.getElementById("divTran").style.display = "none";
								document.getElementById("divDel").style.display = "none";
								document.getElementById("divRef").style.display = "none";
								document.getElementById("divFin").style.display = "none";
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>							
							<div id="divAct" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;制订事件解决计划</td>
									</tr>
									<s:form name="formAct" action="accept" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bordercolor="#FFFFFF" bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" rowspan="2" valign="top" bgcolor="#EBF4F5">更改事件所属类别:</td>
														<td colspan=3 bgcolor="#EBF4F5">
															<input type="hidden" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>" name="serviceRequest.serviceCategory.id" id="catenameh">
															<input type="text" value="<s:property value='serviceRequest.serviceCategory.itemZh'></s:property>" readonly id="catename" style="width: 60%; cursor: hand">
														</td>
													</tr>
													<tr>
														<td colspan=3 bgcolor="#EBF4F5" style="padding: 5px">
															<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../serviceCategory/top2.action?type=1" style="border: 1px inset"></iframe>
														</td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">优先级:</td>
														<td width="86%" colspan=3 bgcolor="#EBF4F5">
															<table width="20" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td>
																		<select id="essential" name="serviceRequest.severityTypByEssential" ></select>
																	</td>
																	<td style="font-family: 'Times New Roman'">&nbsp;<i>&</i>&nbsp;</td>
																	<td>
																		<select id="emergency" name="serviceRequest.severityTypByEmergency" ></select>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">初步分析意见及解决计划:</td>
														<td width="86%" colspan=3 bgcolor="#EBF4F5">
															<textarea id="adviseplan" name="serviceRequest.plan" rows=10 style="width: 100%"></textarea>
														</td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="act()">
									  </td>
									</tr>
								</table>
							</div>
							
							<div id="divTran" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;转交给其它工程师</td>
									</tr>
									<s:form name="formTrans" action="transmit.action" method="post" theme="simple" enctype ="multipart/form-data">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value=""></s:hidden>
										<s:hidden id="ITnotice" name="isNotice" value="0"></s:hidden>
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">转交原因:</td>
														<td colspan=3 bgcolor="#FFFFFF">
															<textarea id="trancause" name="serviceRequest.transmitCause" rows=5 style="width: 100%"></textarea>
													  </td>
													</tr>
													<tr>
													<td width="14%" bgcolor="#EBF4F5">转交对象:</td>
													<td bgcolor="#FFFFFF">
														<input type="radio" id="radio0" name="flag" value="0" style="border: 0px" checked onclick="document.getElementById('ass_tr0').style.display='';document.getElementById('ass_tr1').style.display='none';document.getElementById('ass_tr2').style.display='none';">工程师分组&nbsp;&nbsp;
														<input type="radio" id="radio1" name="flag" value="1" style="border: 0px" onclick="document.getElementById('ass_tr0').style.display='none';document.getElementById('ass_tr1').style.display='';document.getElementById('ass_tr2').style.display='';">工程师&nbsp;&nbsp;
													</td>
												</tr>
												<tr id="ass_tr0" style="display:">
													<td height="12" bgcolor="#EBF4F5">工程师分组:</td>
													<td bgcolor="#FFFFFF" style="padding: 0px; padding-left: 7px">
    													<select style="width: 180px" id="engineerteam1" name="serviceRequest.acceptEngineers">
       														<option value="-1">--请选择--</option>
    													</select>
													</td>
												</tr>
												<tr id="ass_tr1" style="display:none">
													<td height="12" bgcolor="#EBF4F5">工程师分组:</td>
													<td bgcolor="#FFFFFF" style="padding: 0px; padding-left: 7px">
    													<select style="width: 180px" id="engineerteam2"  onChange="getInfo2();">
       														<option value="-1">--请选择--</option>
    													</select>
													</td>
												</tr>
												<tr id="ass_tr2" style="display:none">
													<td bgcolor="#EBF4F5">选择工程师:</td>
													<td bgcolor="#FFFFFF" style="padding: 5px">
														<iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
													 </td>
												</tr>
													<tr>
														<td bgcolor="#EBF4F5">附加文件:</td>
														<td height="20" bgcolor="#FFFFFF" colspan="3" style="padding: 0px; padding-left: 7px">
															<table width="100%" border="0" cellspacing="1" cellpadding="4">
																<tr><td><div id="_file"></div></td></tr>
																<tr>
																	<td width="99%" bgcolor="#F1F2F7" style="padding-top: 1px; padding-bottom: 2px">
																		<div id="input">
																			<input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
																			<font size="2" color="red">如果添加多个附件，请继续点击“浏览”</font>
																		</div>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center valign="middle" nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="转交" onclick="trans()">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divRef" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;拒绝这个事件</td>
									</tr>
									<s:form name="formRef" action="refuse" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">拒绝原因:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea id="refusecause" name="serviceRequest.cause" rows=5 style="width: 100%"></textarea>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="拒绝" onclick="ref()">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divDel" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;退回这个事件</td>
									</tr>
									<s:form name="formRet" action="ret" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">退回原因:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea id="returncause" name="serviceRequest.returnCause"  rows=5 style="width: 100%"></textarea>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="退回" onclick="retu()">
									  </td>
									</tr>
								</table>
							</div>
							
							<div id="divFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;完成事件并汇总解决方案</td>
									</tr>
									<s:form name="formFin" action="solve" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">										
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="50%" colspan=2 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
														<td width="36%" bgcolor="#FFFFFF" align="center"><a href="#" onclick="javascript:queryKb();"><font color="blue">查询知识库</font></a>&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">事件知识库:</td>
														<td width="36%" bgcolor="#FFFFFF" style="padding-left: 1px">
															<table border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td style="padding-top: 3px">
																		<input type=checkbox class=noborder name=Knowlg id=Knowlg value="1">
																	</td>
																	<td nowrap>添加该解决方案到知识库</td>
																</tr>
															</table>
													  </td>
														<td width="14%" bgcolor="#EBF4F5">摘要:</td>
														<td width="36%" bgcolor="#FFFFFF">
															<s:textfield id="summ" name="serviceRequest.summary" cssStyle="width:80%"></s:textfield>
													  </td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">解决方案:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea id="serviceRequestSolution" name="serviceRequest.solution" rows=10 style="width: 100%"></textarea>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="fin()">
									  </td>
									</tr>
								</table>
							</div>
							
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
<Script Language="JavaScript" src="${pageContext.request.contextPath}/js/mmBtn.js"></Script>
