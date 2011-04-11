<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>添加计划任务</title>
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">		
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../js/file-upload-check.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<style type="text/css">
			.onOver {
				Border : 1px solid white;
			}
			A:hover {
				color: #000000;
				text-decoration: none;
				background-color: #EEF8ED;
				Border-Bottom: 1px solid #333333;
				Border-Right: 1px solid #333333;
				Border-Top: 1px solid #C6CFD8;
				Border-Left: 1px solid #C6CFD8;
			}
		</style>
		<script type="text/javascript">
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
		function showTr(v1) {
			if(v1=='only') document.getElementById('enddate').style.display="none";
			else  document.getElementById('enddate').style.display="";
		}
		function getDAO(){ //取出类别
			var message='${message}';
			if(message ){
				alert(message);
			}
   			RoleteamDAO.findAllByType(callbackroleteam);  			
   			ServiceCategoryDAO.findByIntType(2,callbackcategory);
		}
		function callbacklocation(data){  //显示出地理位置
   			dwr.util.removeAllOptions("location");
   			dwr.util.addOptions("location",{'-1':'--请选择--'});
   			dwr.util.addOptions("location",data,"id","name");   
		}
		function callbackcategory(data){  //显示出类别
   			dwr.util.removeAllOptions("category");
   			dwr.util.addOptions("category",{'-1':'--请选择--'});
   			dwr.util.addOptions("category",data,"id","itemZh");   
		}	
		function callbackroleteam(data){  //显示出工程师分组
   			dwr.util.removeAllOptions("engineerteam");
   			document.getElementById("engineer").src="about:blank";
   			dwr.util.removeAllOptions("engineerteam");
   			dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   			dwr.util.addOptions("engineerteam",data,"id","name");   
		}
		function getInfo2(){
			var roleteam = document.getElementById("engineerteam").value;
			document.getElementById("engineer").src="../role/queryforTask.action?role.id="+roleteam;
		}
		var fileInputNumber = 0;
  function addFile()
　　  	{	
			var strFile = "file"+fileInputNumber;
			var filePath = document.getElementById(strFile);
			if(!getFileSize(filePath.value)){
				return ;
			}
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
		if(confirm('确定删除该附件吗？')){
			var new_tr = id.parentNode.parentNode;
	　　      try {
	　　          var tmp = new_tr.parentNode;
	　　          tmp.removeChild(new_tr);
				removeInput(strFile);
	　　       } catch(e) {}
		}
　　}

	function removeInput(strFile) {
　　      var _input = document.getElementById("input");
　　      try {
　　          input.removeChild(strFile);
　　       } catch(e) {}
　　}
	function submitform() {
		 var a = document.getElementsByName("schedualedTasks.rate");
    	 var rate;
    	 for(var i=0;i<a.length;i++) {
      	     if(a[i].checked) {rate= a[i].value;break;}
    	}		
		var category=document.getElementById("category").value; if(category==-1) {alert("请选择任务类别！"); return false;}	
		var severity=document.getElementById("severity").value; if(severity==-1) {alert("请选择重要程度！"); return false;}
		var submitAt1=document.getElementById("submitAt1").value; if(submitAt1=='') {alert("请输入执行时间！"); return false;}
		var approveAt1=document.getElementById("approveAt1").value; if(rate!='Only'&&approveAt1=='') {alert("请输入截止时间！"); return false;}
		if(submitAt1!=''&&approveAt1!=''&&submitAt1>approveAt1)  {alert("起始时间不能在截止时间之前！"); return false;}
		var Application1=document.getElementById("Application1").value; if(Application1=='') {alert("请输入创建人！"); return false;}
		var keyWord=document.getElementById("keyWord").value; if(keyWord=="") {alert("请输入摘要！"); return false;}
		var detail=document.getElementById("detail").value; if(detail=="") {alert("请输入描述信息！"); return false;}
		var userid=document.getElementById("userid").value; if(userid=="") {alert("请指派负责人！"); return false;}
		var members=document.getElementById("members").value; if(members=="") {alert("请指派工程师成员！"); return false;}	
	return true;
}

	</script>
	</head>

	<body  onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow-y:scroll;overflow-x:scroll">
		<s:form action="save" name="myForm" id="myForm" method="post" theme="simple" enctype="multipart/form-data">
			<input id="tmp" value="au" type="hidden">
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
						添加计划任务
					</td>
				</tr>
			</table>
			<div style="position: absolute; overflow-x:hidden; overflow-y:hidden; width: 100%; padding-top: 5px;">
				<table width="99%" height="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
					<tr>
						<td height="99%" bgcolor="#FFFFFF" valign="top">
							<table width="100%" border="0" cellspacing="1" cellpadding="2">
								<tr>
									<td width="15%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;任务类别<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="35%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<select id="category" name="schedualedTasks.serviceCategory.id" style="width: 100%"></select>
									</td>
									<td width="15%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif"width="10" height="9">&nbsp;重要程度<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="35%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<select id="severity" name="schedualedTasks.serverity" style="width: 100%">
											<option value="-1" >--请选择--</option>
											<option value="低" >低</option>								  
								  			<option value="中" >中</option>								  
								  			<option value="高" >高</option>											
										</select>
									</td>									
								</tr>
								<tr>
									<td height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;执行频率<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td bgcolor="#EBF4F5" colspan="3" style="padding-top: 5px">
										<table border="0" cellspacing="0" cellpadding="0">
								  			<tr> 
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Only" style="border: 0px" checked onClick="showTr('only')"></td>
												<td nowrap>只执行一次&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Half" style="border: 0px" onClick="showTr('none')"></td>
												<td nowrap>每半年一次&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Quar" style="border: 0px" onClick="showTr('none')"></td>
												<td nowrap>每季度一次&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Month" style="border: 0px" onClick="showTr('none')"></td>
												<td nowrap>每月一次&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Week" style="border: 0px" onClick="showTr('none')"></td>
												<td nowrap>每周一次&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualedTasks.rate" value="Day" style="border: 0px" onClick="showTr('none')"></td>
												<td nowrap>每日一次</td>
								  			</tr>
										</table>
									</td>									
								</tr>
								<tr  id="t1" style="display:">
									<td bgcolor="#deebf1"  height="31">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;执行时间<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td bgcolor="#EBF4F5">
										<table width="100%">
											<tr>
												<td  width="50%">
													<s:textfield id="submitAt1" name="schedualedTasks.submitAt" onclick="WdatePicker()" cssStyle="width:65%"></s:textfield>
													<img onClick="WdatePicker({el:$dp.$('submitAt1')})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;
												</td>
												<td> <div id="enddate" style="display:none">
														<b>~</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<s:textfield id="approveAt1" name="schedualedTasks.approveAt" onclick="WdatePicker()" cssStyle="width:65%"></s:textfield>
														<img onClick="WdatePicker({el:$dp.$('approveAt1')})" src="../js/DatePicker/skin/datePicker.gif" />
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td height="31" nowrap bgcolor="#deebf1" style="padding-right: 30px">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;创建人:
									</td>	
									<td bgcolor="#EBF4F5">
										<input name="schedualedTasks.Application" id="Application1" style="width: 100%" size="20" value="<s:property value='#session.user.truename'/>" readOnly>
									</td>								
								</tr>
								<tr>
									<td bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;摘要<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td colspan="3" bgcolor="#EBF4F5">
										<input type="text" id="keyWord" name="schedualedTasks.keyWord" style="width:100%"/>
									</td>
								</tr>	
								<tr>
									<td bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;详细描述<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td colspan="3" bgcolor="#EBF4F5">
										<textarea id="detail" name="schedualedTasks.detail" rows="8" cols="86" style="width: 100%; height: 120"></textarea>
									</td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr>
						<td bgcolor="#FFFFFF" height="12" colspan="2">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tb-list">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Gray">
											<tr>												
												<td style="padding-right: 1px; cursor: hand" onClick="Table('au')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_au"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="au"> 指派任务</td>
															<td><img src="../img/tab_un_right.gif" id="right_au"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('at')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_at"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="at"> 添加附件</td>
															<td><img src="../img/tab_un_right.gif" id="right_at"></td>
														</tr>
													</table>
												</td>
												<!--  
												<td style="padding-right: 1px; cursor: hand" onClick="Table('ar')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_ar"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ar"> 涉及配置</td>
															<td><img src="../img/tab_un_right.gif" id="right_ar"></td>
														</tr>
													</table>
												</td>
												-->
												<td width="99%" align="right">
											  		<table border="0" cellspacing="0" cellpadding="0" width="95%">
														<tr>
															<!--  <td width="40%" align="center"> <font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
															<td nowrap align="right">
<!--																<a onclick="document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand">&nbsp;其它工具&nbsp;</a>-->
															</td>
															<td width="1%" onclick="document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand">
<!--																<img src="../img/do.gif" align="absmiddle">-->
															</td>
														</tr>
													</table>   
											</tr>
										</table>
									</td>
								</tr>	
								<tr id="au_table" style="display:">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
													<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
														<tr>
															<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">指派任务</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top">
													
														<table width="100%" border="0" cellspacing="2" cellpadding="4">															
															<tr bgcolor="#EBF4F5">
																<td width="15%" height="12">工作组:</td>
																<td style="padding: 0px; padding-left: 7px">																	
    																<select style="width: 180px" name="role.id" id="engineerteam"  onChange="getInfo2();">
       																	<option value="-1">--请选择--</option>
    																</select>   																
																</td>
																<td>
																	<s:hidden id="userid" name="schedualedTasks.user.id"/>
																	<s:hidden id="members" name="members"/>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" >
																<td valign="top">执行工程师<span style = 'color : red; vertical-align   :middle'>*</span>:</td>
																<td style="padding-left: 7px">																	  																
																   <iframe frameborder="0" height="100%" width="100%" scrolling="yes" style="border: 1px inset" name="engineer" id="engineer"></iframe>
																</td>
															</tr>															
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>													
								<tr id="at_table" style="display:none">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0"
											height="220"
											style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top"
													style="overflow-y: scroll; background-image: url(../img/trh.jpg)"
													height="12">
													<table width="100%" border="0" cellspacing="2"
														cellpadding="2" bgcolor="white">
														<tr>
															<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">
																上传附件
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top" height="160">
													<div id="_file"></div>
													<br />
												</td>
											</tr>
											<tr>
												<td height="20" bgcolor="#FFFFFF">
													<table width="100%" border="0" cellspacing="1" cellpadding="4">
														<tr>
															<td width="1%" bgcolor="#EBF4F5" nowrap>
																&nbsp;选择文件:
															</td>
															<td width="99%" bgcolor="#EBF4F5" style="padding-top: 1px; padding-bottom: 2px">
																<div id="input">
																	<input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
																	<font size="1.8" color="red">如果添加多个附件，请继续点击“浏览”</font>
																</div>
															</td>
															<td width="1%" bgcolor="#F1F2F7">
															<!-- 	<input type="button" class="mmBtn_sm" value="清空" onClick="document.getElementById('_file').value=''" name="button"> -->
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="ar_table" style="display:none">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
													<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
														<tr>
															<td width="15%" height="12" class="subtitle" bgcolor="#b5d6e6">代码</td>
															<td width="45%" class="subtitle" bgcolor="#b5d6e6">名称</td>
															<td width="30%" class="subtitle" bgcolor="#b5d6e6">类别</td>
															<td width="10%" class="subtitle" bgcolor="#b5d6e6">查看</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top">

												</td>
											</tr>
										</table>
									</td>								
								</tr>									
							</table>
						</td>
					</tr>	
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center" style="padding-top: 8px; padding-bottom: 0px">
							<input type="submit" value="提交" class=mmBtn name="submit" onClick="javascript:return submitform()">&nbsp;
							<!--  
							<input type="button" onClick="window.loaction='list.action'" value="后退" class=mmBtn name="button">
							-->
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>