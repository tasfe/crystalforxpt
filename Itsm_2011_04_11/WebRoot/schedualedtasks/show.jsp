<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>计划任务详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/util.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/interface/RoleteamDAO.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath }/dwr/engine.js'></script>
</head>
<script language="JavaScript">
	function getDAO(){ //取出类别
   		RoleteamDAO.findAllByType(callbackroleteam);
	}
	function callbackroleteam(data){  //显示出工程师分组
   		dwr.util.removeAllOptions("engineerteam");
   		document.getElementById("ITer").src="about:blank";
   		dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   		dwr.util.addOptions("engineerteam",data,"id","name"); 
	}
	function getInfo2(){
		var roleteam = document.getElementById("engineerteam").value;
		document.getElementById("ITer").src="../role/query.action?role.id="+roleteam;
	}
	function fin() {
		var sol=document.getElementById('solu').value;
		if(sol=='') {
			alert("请输入解决方案！");
		} else {
			if(confirm("您确定要提交吗？")) 
			document.getElementById('formFin').submit();}
	}
	
	function trans(){
	var val1=document.getElementById('ITprinc').value;
	var val2=document.getElementById('trancause').value;
	if(val1!=''){
		if(val2!='') {
			if(val2.length>200) alert("您输入的字数太多，请控制在200字以内！");
			else if(confirm('你确认要提交吗？')) document.getElementById('formTrans').submit();
		}else alert('请填写转交原因！');
	}else alert('请选择工程师！');
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
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">计划任务详情</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: scroll; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1">
		<tr>
			<td valign="top" id="mainright">
				<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1">
					<script LANGUAGE="JavaScript">
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
					</script>
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
										<input type="button" id="button2" class="mmBtn" value="完成这个任务" onclick="ShowApprove('Fin')">
									<input type="button" id="button5" class="mmBtn" value="转交" onclick="ShowApprove('Tra')">						
										<input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)">
										
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divFin").style.display = "none";
								document.getElementById("divTra").style.display = "none";
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>
							<div id="divFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;完成这个任务</td>
									</tr>
									<s:form name="formFin" action="solve" method="post" theme="simple" enctype="multipart/form-data">
										<input type=hidden name="schedualedTaskDetail.id" value="<s:property value='schedualedTaskDetail.id'></s:property>">
											<tr>
												<td style="padding: 10px" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
														<tr>
															<td width="14%" bgcolor="#EBF4F5">任务号:</td>
															<td width="86%" colspan=2 bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property value="schedualedTaskDetail.taskCode"></s:property></td>
														</tr>
														<tr>
															<td width="14%" valign="top" bgcolor="#EBF4F5">解决方案:</td>
															<td width="86%" colspan=2 bgcolor="#FFFFFF"><textarea id="solu" name="schedualedTaskDetail.solution" rows=10 style="width: 100%"></textarea></td>
														</tr>
														<tr>
															<td rowspan="2" valign="top"  bgcolor="#EBF4F5">附件:</td>
															<td colspan="2" height="20"  bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
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
														<tr>
															<td width="14%"  bgcolor="#EBF4F5">
																	帮助操作说明:
															</td>
															<td width="86%" colspan=2  bgcolor="#EBF4F5">
																	在任务执行情况列表中将列出所有处理结果。请您确认所有任务执行人均提交处理结果之后再关闭这个任务。
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
							<div id="divTra" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;转交给其他工程师</td>
									</tr>
									<s:form name="formTrans" action="transmit" method="post" theme="simple" enctype ="multipart/form-data">
										<input type="hidden" id="stId" name="schedualedTasks.id" value="${schedualedTaskDetail.schedualedTasks.id}">
										<input type="hidden" id="ITprinc" name="schedualedTasks.user.id">
										<s:hidden name="page"/>
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">工单号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF"><s:property value="schedualedTaskDetail.schedualedTasks.proNo"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">转交原因:</td>
														<td colspan=3 bgcolor="#FFFFFF">
															<textarea id="trancause" name="solution" rows=5 style="width: 100%"></textarea>
													  </td>
													</tr>
													<tr>
														<td height="12" bgcolor="#EBF4F5">工程师分组:</td>
														<td bgcolor="#FFFFFF" style="padding: 0px; padding-left: 7px">
    														<select style="width: 180px" id="engineerteam"  onChange="getInfo2();">
       															<option value="-1">--请选择--</option>
    														</select>
														</td>
													</tr>
													<tr>
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
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="trans()">
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
