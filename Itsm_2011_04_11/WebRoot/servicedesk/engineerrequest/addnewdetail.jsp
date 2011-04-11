<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*"%>
<html>
	<head>
		<title>工程师提交请求</title>
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/SeverityTypDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript" src='../js/servicedesk/AssociateAssets.js'></script>
		<!-- ext core -->
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<script type="text/javascript" src="../js/util.js"></script>
		
		<style type="text/css">
.onOver {
	Border: 1px solid white;
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
.subtitle {
	background-color: #EAEFF3;
	font-family: Tahoma;
	font-size: 11px;
	word-break: break-all;
	padding-right: 4px;
	padding-left: 4px;
}
</style>
<script type="text/javascript">
function Notice()
{

}
function Chose1(obj){
	if(obj.checked){
		document.getElementById('ITnotice').value=obj.value;
	}else{
		document.getElementById('ITnotice').value="0";
	}
}
function queryKb(){
	var keyword="";
	if(document.getElementById('summary').value){
		keyword=document.getElementById('summary').value;
	}
	window.open('${pageContext.request.contextPath}/servicedesk/engineerrequest/index_kb.jsp?keyword='+keyword, 'newwindow');
}
function selectUser(){
	var win = new UserSelectWindow({
		accepted:function(user){
			document.getElementById('serviceRequest.usersByRequestUser.truename').value=user.name;
			document.getElementById('serviceRequest.usersByRequestUser.id').value=user.id;
			getUser(user.id);
		}
	});
	win.show();
}
function getNowDate(){//获取当前时间(服务器时间)
	return "<%=(request.getAttribute("nowDate"))%>";
}
function getDAO(){ //取出类别
   RoleteamDAO.findAllByType(callbackroleteam);
   SeverityTypDAO.findAll(callbackseverity);
   SeverityTypDAO.findByIntType(1,callbackessential);
   SeverityTypDAO.findByIntType(2,callbackemergency);
}
function callbackseverity(data){  //显示出严重程度
   dwr.util.removeAllOptions("severity");
   dwr.util.addOptions("severity",{'-1':'--请选择--'});
   dwr.util.addOptions("severity",data,"severityValue","severityType");  
   dwr.util.setValue("severity",2); 
}
function callbackessential(data){  //显示出影响度
   dwr.util.removeAllOptions("essential");
   dwr.util.addOptions("essential",{'-1':'--请选择--'});
   dwr.util.addOptions("essential",data,"severityValue","severityType");  
   dwr.util.setValue("essential",2); 
}
function callbackemergency(data){  //显示出紧急度
   dwr.util.removeAllOptions("emergency");
   dwr.util.addOptions("emergency",{'-1':'--请选择--'});
   dwr.util.addOptions("emergency",data,"severityValue","severityType");  
   dwr.util.setValue("emergency",2); 
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
function assetChoose(){
	var obj1=null;
	var result=window.showModalDialog("${pageContext.request.contextPath }/assets/listForInc.action",obj1,"dialogWidth=750px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
	addRow('configTab',result);
}
function delAssetChoose(){
	alert('delete');
}
function submitform() {
	var use=document.getElementById("users").value; if(use==-1||!use) {alert("请选择请求用户！"); return false;}	
	/*var cat=document.getElementById("category").value; if(cat==-1) {alert("请选择服务类别！"); return false;}*/
	
	var sev=document.getElementById("severity").value; if(sev==-1) {alert("请选择严重程度！"); return false;}
	if(!(document.getElementById("cat.name").value)){//modify jyxiao 
		alert("请选择类别！");
		return false;
	}
	var ess=document.getElementById("essential").value; if(ess==-1) {alert("请选择影响度！"); return false;}
	var eme=document.getElementById("emergency").value; if(eme==-1) {alert("请选择紧急度！"); return false;}
	var sum=document.getElementById("summary").value; if(sum=="") {alert("请输入摘要！"); return false;}
	var des=document.getElementById("description").value; if(des=="") {alert("请输入描述信息！"); return false;}	
	return true;
}

var fileInputNumber = 0;
  function addFile()
　　  	{	
			var strFile = "file"+fileInputNumber;
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
　　          //new_tr.removeNode(true);
　　          // just ie , not w3c;
　　          // other idea
　　          var tmp = new_tr.parentNode;
　　          // 为了在ie和firefox下都能正常使用,就要用另一个方法代替,最取上一层的父结点,然后remove.
　　          tmp.removeChild(new_tr);
			
			removeInput(strFile);
　　       } catch(e) {}
　　}

	function removeInput(strFile) {
　　      var _input = document.getElementById("input");
　　      try {
　　          //new_tr.removeNode(true);
　　          // just ie , not w3c;
　　          // other idea
　　          // 为了在ie和firefox下都能正常使用,就要用另一个方法代替,最取上一层的父结点,然后remove.
　　          input.removeChild(strFile);
　　       } catch(e) {}
　　}

	function Table(tb1,tb2,tb3){
		document.getElementById(tb1+"_table").style.display="";
		document.getElementById(tb2+"_table").style.display="none";
		document.getElementById(tb3+"_table").style.display="none";
	}
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
	function getUser(id) {
		if(id!=-11&&id!=-1) document.getElementById("userinfo").src="../user/query.action?userId="+id;
		else window.location.href("../user/addInput2.action");
	}
	
	function assign(val) {
		if(val) { 
			document.getElementById('astd_0').style.display='none';
			document.getElementById('astd_1').style.display='none';
			document.getElementById('astd_2').style.display='none';
			document.getElementById('astd_3').style.display='';
			document.getElementById('astd_4').style.display='none';
			document.getElementById('notice').style.display='';
		}else{
			var all=document.getElementsByName('radio1');
			if(all.length>0){
				for(var i=0;i<all.length;i++){
					all[i].checked=false;
				}
			}
			document.getElementById('radio2').chedked='false';
			document.getElementById('astd_0').style.display='';
			document.getElementById('astd_1').style.display='none';
			document.getElementById('astd_2').style.display='none';
			document.getElementById('astd_3').style.display='none';
			document.getElementById('astd_4').style.display='none';
			document.getElementById('notice').style.display='none';
		}
	}
</script>
	</head>

	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:auto;" onmousedown="document.getElementById('Layer2').style.visibility='hidden'" >
		<s:form action="../engineerrequest/save.action" name="myForm" id="myForm" method="post" theme="simple" enctype="multipart/form-data">
				<s:hidden id="ITprinc" name="serviceRequest.usersByEngineerId.id" value="-1"></s:hidden>
				<s:hidden id="ITnotice" name="isNotice" value="0"></s:hidden>
				<input id="tmp" value="at" type="hidden">
			<!-- <s:hidden id="serviceRequest.id" name="serviceRequest.id"></s:hidden> -->
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
						工程师提交请求
					</td>
				</tr>
			</table>
			<div style="position: absolute; overflow-x:scroll; overflow-y:scroll; width: 100%; padding-top: 5px;">
				<table width="99%" height="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
					<tr>
						<td height="99%" bgcolor="#FFFFFF" valign="top">
							<table width="100%" border="0" cellspacing="1" cellpadding="2">
								<tr>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;请求来源<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="19%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<select id="source" name="serviceRequest.source" style="width: 100%; background-color: #FFFFCC">
											<option value="C">电话</option>
											<option value="F">传真</option>
											<option value="E">电子邮件</option>
											<option value="O">其它</option>
										</select>
									</td>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif"width="10" height="9">&nbsp;请求用户<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<!-- <td width="19%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<select id="users" name="serviceRequest.usersByRequestUser.id" onChange="getUser(this.value)" style="width: 100%; background-color: #FFFFCC"></select>
									</td> -->
									<td width="18%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<s:hidden id="serviceRequest.usersByRequestUser.id" name="serviceRequest.usersByRequestUser.id"></s:hidden>
										<input id="users" type="text" name="serviceRequest.usersByRequestUser.truename" style="width: 79%; background-color: #FFFFCC; cursor: text" readonly  onClick="javascript:selectUser();">
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser();"><br>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;严重程度<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="19%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<select id="severity" name="serviceRequest.severityTypBySeverity" style="width: 100%; background-color: #FFFFCC"></select>
									</td>
									</td>
								</tr>
								<tr>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;类别<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="18%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px"><%--
									
									
									
										<select id="category" name="serviceRequest.serviceCategory.id" style="width: 100%; background-color: #FFFFCC"></select>
									--%>
									
									
										<input id="cat.name" type="text" name="serviceRequest.serviceCategory.itemZh" style="width: 79%; background-color: #FFFFCC; cursor: text" readonly value="<s:property value="serviceRequest.serviceCategory.itemZh"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="cat.id" name="serviceRequest.serviceCategory.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 180%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../serviceCategory/cat.action" style="border: 1px solid #E5E9EE"></iframe>

																</div>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;影响度<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
					 				<td width="19%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<select id="essential" name="serviceRequest.severityTypByEssential" style="width: 100%; background-color: #FFFFCC"></select>
									</td>
									<td width="14%" height="31" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;紧急度<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="18%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px">
										<select id="emergency" name="serviceRequest.severityTypByEmergency" style="width: 100%; background-color: #FFFFCC"></select>
									</td>
								</tr>

								<tr>
									<td bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;摘要<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td bgcolor="#EBF4F5">
										<input name="serviceRequest.summary" id="summary" style="width: 100%; background-color: #FFFFCC" size="20">
									</td>
									<td width="14%" height="31" nowrap bgcolor="#deebf1" style="padding-right: 30px">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;提交请求时间：
									</td>
									<td width="19%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<s:textfield name="serviceRequest.submintTime" id="id1" value='%{getNowDate()}' readOnly='true'></s:textfield>
										<!-- <img onclick="WdatePicker({el:$dp.$('id1'),dateFmt:'yyyy-MM-dd HH:mm:ss'})" src="../js/DatePicker/skin/datePicker.gif" width="16" height="22" /> -->
									</td>
									<td width="14%" height="31" nowrap bgcolor="#deebf1" style="padding-right: 30px">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;希望完成时间:
									</td>
									<td width="19%" bgcolor="#EBF4F5" style="padding-top: 5px">
										<s:textfield name="serviceRequest.expectTime" id="id2" onclick="var date=getNowDate(); WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:date})"></s:textfield>
										<img onClick="var date=getNowDate(); WdatePicker({el:$dp.$('id2'),dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:date})" src="../js/DatePicker/skin/datePicker.gif" width="16" height="22" />
									</td>
								</tr>
								<tr>
									<td width="14%" bgcolor="#deebf1">
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9"> &nbsp;详细描述<span style = 'color : red; vertical-align   :middle'>*</span>:
									</td>
									<td width="85%" colspan="5" bgcolor="#EBF4F5">
										<textarea id="description" name="serviceRequest.description" rows="8" cols="86" style="width: 100%; height: 120;background-color: #FFFFCC"></textarea>
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
											<!-- 	<td style="padding-right: 1px; cursor: hand" onClick="Table('as')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_ch_left.gif" id="left_as"></td>
															<td background="../img/tab_ch.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 3px"	nowrap id="as">填写详细表单</td>
															<td><img src="../img/tab_ch_right.gif" id="right_as"></td>
														</tr>
													</table>
												</td>    -->
												<td style="padding-right: 1px; cursor: hand" onClick="Table('at')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_at"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="at"> 添加附件</td>
															<td><img src="../img/tab_un_right.gif" id="right_at"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('config')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_config"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="config"> 涉及配置</td>
															<td><img src="../img/tab_un_right.gif" id="right_config"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('au')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_au"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="au"> 用户信息</td>
															<td><img src="../img/tab_un_right.gif" id="right_au"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('ar')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_ar"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="ar">任务分配与协议</td>
															<td><img src="../img/tab_un_right.gif" id="right_ar"></td>
														</tr>
													</table>
												</td>
												<td style="padding-right: 1px; cursor: hand" onClick="Table('af')">
													<table border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td><img src="../img/tab_un_left.gif" id="left_af"></td>
															<td background="../img/tab_un.gif" style="font-weight: bold; padding-left: 10px; padding-right: 10px; padding-top: 5px" nowrap id="af">快速解决</td>
															<td><img src="../img/tab_un_right.gif" id="right_af"></td>
														</tr>
													</table>
												</td>
												<td width="99%" align="right">
											  		<table border="0" cellspacing="0" cellpadding="0" width="95%">
														<tr>
															<td width="40%" align="center"> <font color="red"><b><s:property value="message"></s:property></b></font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
															<td nowrap align="right">
<!--																<a onclick="document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand">&nbsp;其它工具&nbsp;</a>-->
															</td>
															<td width="1%" onClick="document.getElementById('Layer5').style.visibility='visible'" style="cursor: hand">
<!--																<img src="../img/do.gif" align="absmiddle">-->
															</td>
														</tr>
													</table>   
											</tr>
										</table>
									</td>
								</tr>
						<!-- 		<tr id="as_table" style="display: ">
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
															<td height="12" nowrap class="subtitle">
																填写服务请求或事件详细表单
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="191" valign="top">
													<div
														style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%"
														id="AttachTB" name="AttachTB">
														<table width="100%" border="0" cellpadding="3"
															cellspacing="3" bgcolor="#F9F9F9">
															<tr>
																<td style="color: gray">
																	<img src="../img/i.jpg" align="absmiddle">
																	&nbsp;请先选择一个服务或事件类别。
																</td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>						 -->		
								<tr id="at_table" style="display: ">
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
								<tr id="config_table" style="display:none">
									<td >
										<table width="100%" cellspacing="0" cellpadding="0" height="220">
											<tr>
												<td width="55%" style="padding-right: 10px">
											  <table width="100%" cellpadding="1" cellspacing="0" class="tb-list" height="100%">
														<tr nowrap class="subtitle" >
															<td valign="top" height="12">
																<table width="100%" border="0" cellpadding="2" cellspacing="1" id='configTab' bgcolor="#FFFFFF">
																	<s:hidden id="associate_assets" name="associate_assets"></s:hidden>
																	<tr>
																		<td width="14%" height="12" nowrap class="subtitle" >资产编号</td>
																		<td width="20%" height="12" nowrap class="subtitle">资产名称</td>
																		<td width="20%" height="12" nowrap class="subtitle">资产类别</td>
																		<td width="15%" height="12" nowrap class="subtitle">供应商</td>
																		<td width="15%" height="12" nowrap class="subtitle">制造商</td>
																		<td width="8%" height="12" nowrap class="subtitle" align="center">查看</td>
																		<td width="8%" height="12" nowrap class="subtitle" align="center">删除</td>
																		<td width="0%" height="12" nowrap class="subtitle" align="center" style='visibility:hidden;'></td>
																	</tr>
																</table>
														  </td>
														</tr>
														<tr>
															<td valign="bottom"><table width="100%" border="0" cellspacing="1" cellpadding="4">
															  <tr>
															    <td width="1%" valign="bottom" bgcolor="#F1F2F7"><input type="button" value="添加" class=mmBtn_sm name="button2" onClick="assetChoose();"></td>
															    <!--  <td width="1%" valign="bottom" bgcolor="#F1F2F7" ><input type="button" value="删除" class=mmBtn_sm name="button2" onClick="delAssetChoose();"></td>-->
															    <td width="98%" bgcolor="#F1F2F7" ></td>
														      </tr>
														    </table></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="au_table" style="display:none">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
													<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
														<tr>
															<td nowrap class="subtitle" bgcolor="#b5d6e6">查看用户信息</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top" nowrap>
													<iframe frameborder="0" height="179" width="100%" scrolling="yes" style="border: 1px inset" name="userinfo" id="userinfo"></iframe>
												</td>
											</tr>
											<tr>
												<td valign="top" style="overflow-y: scroll" height="12">
												<!-- 	<table width="100%" border="0" cellspacing="0" cellpadding="2" bgcolor="white">
														<tr>
															<td height="12"  bgcolor="#b5d6e6"><img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;所在部门</td>
															<td height="12"> <input type="text" name="dep"> </td>
															<td height="12"  bgcolor="#b5d6e6"><img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;账号或全名</td>
															<td height="12"> <input type="text" name="uname"> </td>
															<td height="12"> <input type="button" value="搜索"> </td>
														</tr>
													</table>  -->
												</td>
											</tr>											
										</table>
									</td>
								</tr>	
								<tr id="ar_table" style="display: none">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
													<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
														<tr>
															<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6" width="8%">任务指派与服务协议
															</td>
															<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6" width="92%"  id="notice" style="display:none">
																&nbsp;&nbsp;<input name="notice" type="checkbox" value="1" style="border: 0px" onClick="Chose1(this)">短信通知
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top" height="191">
													<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
														<table width="100%" border="0" cellspacing="2" cellpadding="4">
															<tr bgcolor="#EBF4F5" id="astd_0">
																<td width="14%" >系统自动分配:</td>
																<td colspan="3">
																	<input type=text name=ITers style="width: 100%; border: 0px; background-color: #EBF4F5; Color: #3A4E69; padding-top: 2px" readonly>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display:none" id="astd_3">
																<td width="14%">选择指派对象:</td>
																<td colspan="3" style="padding: 0px; padding-left: 7px">
    																<input type="radio" id="radio1" name="flag" value="0" onClick="document.getElementById('astd_4').style.display='';document.getElementById('astd_1').style.display='none';document.getElementById('astd_2').style.display='none';">工程师分组&nbsp;&nbsp;
    																<input type="radio" id="radio2" name="flag" value="1" onClick="document.getElementById('astd_1').style.display='';document.getElementById('astd_2').style.display='';document.getElementById('astd_4').style.display='none';">工程师&nbsp;&nbsp;
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display: none" id="astd_4">
																<td height="12">选择工程师分组:</td>
																<td colspan="3" style="padding: 0px; padding-left: 7px">
    																<select style="width: 180px" name="serviceRequest.acceptEngineers" id="engineerteam1">
       																	<option value="-1">--请选择--</option>
    																</select>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display: none" id="astd_1">
																<td height="12">选择工程师分组:</td>
																<td colspan="3" style="padding: 0px; padding-left: 7px">
    																<select style="width: 180px" id="engineerteam2"  onChange="getInfo2();">
       																	<option value="-1">--请选择--</option>
    																</select>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display: none" id="astd_2">
																<td valign="top">选择工程师:</td>
																<td colspan="3" style="padding-left: 7px">																	  																
																   <iframe frameborder="0" height="100" width="100%" scrolling="yes" style="border: 1px inset" name="ITer" id="ITer"></iframe>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5">
																<td width="14%" height="12"></td>
																<td style="padding: 1px; padding-left: 3px" colspan="3">
																	<table border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td style="padding-top: 1px">
																				<input type="checkbox" class="noborder" onClick="assign(this.checked)" id="ChoseITer">
																			</td>
																			<td nowrap style="padding-top: 1px; line-height: 13px">&nbsp;手工分配任务给指定工程师</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display:none">
																<td width="14%" valign="top">响应时间:</td>
																<td>
																	<input type="text" name=ReTime style="width: 100%; background-color:#EBF4F5; border: 0px;padding-top: 2px" readonly>
																</td>
																<td width="14%" valign="top">承诺完成时间:</td>
																<td>
																	<input type="text" name=FiTime style="width: 100%;  background-color:#EBF4F5;  border: 0px; padding-top: 2px" readonly>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display:none">
																<td width="14%" valign="top">自动转交时间:</td>
																<td colspan="3">
																	<input type="text" name=UpTime style="width: 100%;  background-color:#EBF4F5;  border: 0px; padding-top: 2px" readonly>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5" style="display:none">
																<td width="14%" valign="top">概要及说明:</td>
																<td id="About" colspan="3" style="padding-left: 7px; line-height: 22px">&nbsp;</td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="af_table" style="display: none">
									<td>
										<table width="100%" border="1" cellspacing="0" cellpadding="0" height="220" style="border-color: #C3BFB3; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px;">
											<tr>
												<td valign="top" style="overflow-y: scroll; background-image: url(../img/trh.jpg)" height="12">
													<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="white">
														<tr>
															<td height="12" nowrap class="subtitle" bgcolor="#b5d6e6">快速解决</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td valign="top" height="191">
													<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%">
														<table width="100%" border="0" cellspacing="2" cellpadding="4">
															<tr bgcolor="#EBF4F5">
																<td width="14%" height="12">选项:</td>
																<td style="padding: 1px" width="86%">
																	<table border="0" cellspacing="0" cellpadding="0"  width="100%">
																		<tr>
																			<td style="padding-top: 2px">
																				<input type="checkbox" name="serviceRequest.state" value="5" class="noborder">
																			&nbsp;快速解决这个服务请求，并且将之关闭。</td>
																			<td align="right" nowrap style="padding-top: 1px; line-height: 13px">&nbsp;<a href="#" onClick="javascript:queryKb();">查询知识库</a>&nbsp;&nbsp;</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr bgcolor="#EBF4F5">
																<td width="14%" valign="top">解决方案:</td>
																<td><s:textarea id="serviceRequestSolution" name="serviceRequest.solution" cssStyle="width: 100%; height: 80px"></s:textarea></td>
															</tr>
															<tr bgcolor="#EBF4F5">
																<td width="14%">事件产生原因:</td>
																<td valign="top">
																	<select name="serviceRequest.errorCause" id="ErroCause" style="width: 20%">
																		<option value="">--请选择--</option>
																		<option value="用户操作">用户操作</option>
																		<option value="系统设置">系统设置</option>
																		<option value="设备稳定性">设备稳定性</option>
																	</select>
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
						</td>
					</tr>	
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="12" style="padding-top: 8px" nowrap>
						</td>
						<td align="center" style="padding-top: 8px; padding-bottom: 0px">
							<input type="submit" value="提交" class=mmBtn name="submit" onClick="javascript:return submitform()">&nbsp;
							<input type="button" onClick="history.go(-1)" value="后退" class=mmBtn name="button">
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
