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
<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
</head>
<script language="JavaScript">
function getDAO(){ //取出类别
   RoleteamDAO.findAllByType(callbackroleteam);
   ServiceCategoryDAO.findAll(callbackcategory);
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
function callbackcategory(data){  //显示出类别
   dwr.util.removeAllOptions("category");
   dwr.util.addOptions("category",{'-1':'--请选择--'});
   dwr.util.addOptions("category",data,"id","itemZh");   
}
function getInfo1(){
	var location = document.getElementById("location").value;
	if(location!=-1) {
		RoleteamDAO.findEByLocationID(location,callbackroleteam);		
	}else{
		RoleteamDAO.findAll(callbackroleteam);		
	}
}
function getInfo2(){
	var roleteam = document.getElementById("engineerteam2").value;
	document.getElementById("ITer").src="../role/query.action?role.id="+roleteam;
}
function fin(){
	var val1=document.getElementById('summ').value;
	var val2=document.getElementById('solu').value;
	if(val1!=''){
		if(val2!='') {
			if(confirm('你确认要提交吗？')) document.getElementById('formFin').submit();
		}else  alert('请填写解决方案！');
	}
	 else alert('请填写摘要！'); 
}
function tran(){
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
				else if(confirm('你确认要提交吗？')) document.getElementById('formTran').submit();
			}else alert('请填写转交原因！');
		}else alert('请选择工程师！');
	}else if(val3!=-1) {
		if(val2!='') {
			if(val2.length>200) alert("您输入的字数太多，请控制在200字以内！");
			else if(confirm('你确认要提交吗？')) document.getElementById('formTran').submit();
		}else alert('请填写转交原因！');
	} else alert('请选择工程师分组！'); 
}

	var fileInputNumber = 0;
  function addFile(divId,inputId,file){
  		var strFile = file+fileInputNumber;
			var filePath = document.getElementById(strFile);
			document.getElementById(strFile).style.display = "none";
			var paths = filePath.value.split("\\");
	        var name = paths[paths.length-1];
　　   		var str1 =
				'<div style="background-color:#E7EBF7">'+
				'<font size="2" style="width:300px">'+name+'</font>'+
				'<a href="#"><img onclick="removeFile(this,'+strFile+','+inputId+')" src="../img/del2.gif" border="0" alt="删除附件"/></a>'+
				'</div>';
				var _file = document.getElementById(divId);
　　   		    _file.insertAdjacentHTML("beforeend",str1);

			addInput(divId,inputId,file);
　　    	}
	
	function addInput(divId,inputId,file){
			fileInputNumber++;
			var strFile =file+fileInputNumber;
		　　 var str2 = '<input name="file" id="'+strFile+'" type="file" value="添加附件" onchange="addFile(\''+divId+'\',\''+inputId+'\',\''+file+'\')" />';
			var _input = document.getElementById(inputId);
　　   		_input.insertAdjacentHTML("afterbegin",str2);
	
	}
	
	function removeFile(id,strFile,inputId) {
　　      var new_tr = id.parentNode.parentNode;
　　      try {
　　          var tmp = new_tr.parentNode;
　　          tmp.removeChild(new_tr);
			removeInput(strFile,inputId);
　　       } catch(e) {}
　　}

	function removeInput(strFile,inputId) {
　　      var _input = document.getElementById(inputId);
　　      try {
　　          input.removeChild(strFile);
　　       } catch(e) {}
　　}
</script>


<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
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
										<input type="button" id="button2" class="mmBtn" value="关闭服务请求" onclick="ShowApprove('Fin')">
										<input type="button" id="button3" class="mmBtn" value="转交" onclick="ShowApprove('Tran')">
								<!--  		<input type="button" id="button4" class="mmBtn" value="升级为问题" onclick="ShowApprove('Pro')">
										<input type="button" id="button5" class="mmBtn" value="发起变更" onclick="ShowApprove('Alt')">
										<input type="button" id="button6" class="mmBtn" value="进度通报" onclick="ShowApprove('Not')">	 -->								
										<s:if test="flag==0"><input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)"></s:if>
										<s:else><input type="button" id="button7" class="mmBtn" value="关闭" onclick="window.close()"></s:else>
										
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divFin").style.display = "none";
								document.getElementById("divTran").style.display = "none";
<!--								document.getElementById("divPro").style.display = "none";-->
<!--								document.getElementById("divAlt").style.display = "none";-->
<!--								document.getElementById("divNot").style.display = "none";-->
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>							
							
					<!-- 		<div id="divNot" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;关闭这个事件</td>
									</tr>
										<s:form name="formNot" action="clo" method="post" theme="simple">
											<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
											<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
											<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" valign="top" bgcolor="#EBF4F5">意见建议:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceRequest.solution" cssStyle="width: 100%"></s:textarea>
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
											<input type="button" class="mmBtn" value="关闭" onclick="if(confirm('你确认要关闭这个事件吗？')){document.getElementById('formNot').submit();}">
									  </td>
									</tr>
								</table>
							</div>  -->
							<div id="divFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;完成或关闭请求</td>
									</tr>
									<s:form name="formFin" action="clo" method="post" theme="simple" enctype ="multipart/form-data">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
															<td width="14%" bgcolor="#EBF4F5">工单号:</td>
															<td colspan=4 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
														</tr>
														<tr>
															<td width="14%" bgcolor="#EBF4F5">关闭类型:</td>
															<td width="36%" bgcolor="#FFFFFF">
																<select name="serviceRequest.isFinished" id="Close" onChange="if(this.value==0){document.getElementById('AddToKnowledge').disabled=true}else{document.getElementById('AddToKnowledge').disabled=false}">
																	<option value="1">该事件已经被成功解决</option>
																	<option value="0">无法解决该事件</option>
																</select>
															</td>
															<td width="14%" bgcolor="#EBF4F5">事件产生原因:</td>
															<td width="36%" colspan="2" bgcolor="#FFFFFF">
																<select name="serviceRequest.errorCause" id="ErroCause">
																	<option value="">--请选择--</option>
																	<option value="用户操作">用户操作</option>
																	<option value="系统设置">系统设置</option>
																	<option value="设备稳定性">设备稳定性</option>
																</select>
															</td>
														</tr>
														
														<tr>
															<td width="14%" bgcolor="#EBF4F5">知识库:</td>
															<td style="padding-left: 1px" bgcolor="#FFFFFF">
																<table border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td style="padding-top: 3px">
																			<input type="checkbox" class="noborder" name="serviceRequest.addToKnowledge" id="AddToKnowledge" value="1">
																		</td>
																		<td nowrap>添加该解决方案到知识库</td>
																	</tr>
																</table>
															</td>
															<td width="14%" bgcolor="#EBF4F5">更改类别:</td>
															<td colspan="2" bgcolor="#FFFFFF">
																<select id="category" name="serviceRequest.serviceCategory.id"></select>
															</td>
														</tr>
														<tr>
															<td width="14%" bgcolor="#EBF4F5">摘要:</td>
															<td colspan="4" bgcolor="#FFFFFF">
																<input type="text" id="summ" name="serviceRequest.summary" value="<s:property value='serviceRequest.summary'></s:property>" size=40 style="width: 100%">
															</td>
														</tr>
														<tr onMouseOver="document.getElementById('Layer_Add').style.visibility='hidden'">
															<td width="14%" valign="top" bgcolor="#EBF4F5">结果或解决方案:</td>
															<td colspan=4 bgcolor="#FFFFFF" onMouseOver="document.getElementById('Layer2').style.visibility='hidden'">
																<textarea rows=10 style="width:100%" id="solu" name="serviceRequest.solution"></textarea>
															</td>
														</tr>
														<tr>
															<td  valign="top"  bgcolor="#EBF4F5">附加文件:</td>
															<td colspan="4" height="20"  bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
																<table width="100%" border="0" cellspacing="1" cellpadding="4">
																	<tr><td><div id="_file"></div></td></tr>
																	<tr>
																		<td width="99%" bgcolor="#F1F2F7" style="padding-top: 1px; padding-bottom: 2px">
																			<div id="input">
																				<input name="file" id="file0" type="file" value="添加附件" onChange="addFile('_file','input','file')" />
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
											<input type="button" class="mmBtn" value="关闭请求" onclick="fin()">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divTran" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;转派给其它工程师</td>
									</tr>
									<s:form name="formTran" action="transmit" method="post" theme="simple" enctype ="multipart/form-data">
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
															<textarea id="trancause" rows=5 name="serviceRequest.transmitCause" style="width: 100%"></textarea>
													  </td>
													</tr>
													<tr>
													<td width="14%" bgcolor="#EBF4F5">指派对象:</td>
													<td bgcolor="#FFFFFF">
														<input type="radio" id="radio1" name="flag" value="0" style="border: 0px" checked onclick="document.getElementById('ass_tr0').style.display='';document.getElementById('ass_tr1').style.display='none';document.getElementById('ass_tr2').style.display='none';">工程师分组&nbsp;&nbsp;
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
															<td rowspan="2" valign="top"  bgcolor="#EBF4F5">附件:</td>
															<td colspan="2" height="20"  bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
																<table width="100%" border="0" cellspacing="1" cellpadding="4">
																	<tr><td><div id="_file1"></div></td></tr>
																	<tr>
																		<td width="99%" bgcolor="#F1F2F7" style="padding-top: 1px; padding-bottom: 2px">
																			<div id="input1">
																				<input name="file" id="file00" type="file" value="添加附件" onChange="addFile('_file1','input1','file0')" />
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
											<input type="button" class="mmBtn" value="转交" onclick="tran()">
									  </td>
									</tr>
								</table>
							</div>
					<!-- 		<div id="divAlt" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;填写变更请求详细表单</td>
									</tr>
									<s:form name="formAlt" action="refuse" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">变更原因:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceRequest.cause"  cssStyle="width: 100%"></s:textarea>
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
											<input type="button" class="mmBtn" value="拒绝" onclick="if(confirm('你确认要拒绝吗？')){document.getElementById('formAlt').submit();}">
									  </td>
									</tr>
								</table>
							</div>
							<div id="divPro" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="${pageContext.request.contextPath}/images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="${pageContext.request.contextPath}/img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;升级为问题</td>
									</tr>
									<s:form name="formPro" action="ret" method="post" theme="simple">
										<input type=hidden name="serviceTran.requNo" value="<s:property value='serviceRequest.requestNo'></s:property>">
										<input type=hidden name="serviceTran.serviceCategory.id" value="<s:property value='serviceRequest.serviceCategory.id'></s:property>">
										<input type=hidden name="serviceRequest.id" value="<s:property value='serviceRequest.id'></s:property>">
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="serviceRequest.requestNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">问题初步分析:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<s:textarea name="serviceTran.note"  cssStyle="width: 100%"></s:textarea>
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
											<input type="button" class="mmBtn" value="退回" onclick="if(confirm('你确认要退回吗？')){document.getElementById('formPro').submit();}">
									  </td>
									</tr>
								</table>
							</div>							
							  -->
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
