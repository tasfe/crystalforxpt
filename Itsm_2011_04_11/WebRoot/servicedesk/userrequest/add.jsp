<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*"%>

<script language="javascript" event="onerror(msg, url, line)"
	for="window">return true;</script>

<html>
	<head>
		<title>用户提交请求</title>
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript'
			src='../dwr/interface/SeverityTypDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>

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
td {font-size:12px}
</style>

		<script type="text/javascript">
function Notice()
{

}

function getSeverityTypDAO(){ //取出类别
   SeverityTypDAO.findAll(callbackorg);
    SeverityTypDAO.findByIntType(1,callbackessential);
   SeverityTypDAO.findByIntType(2,callbackemergency);
   UserDAO.findByUsertypeString("engineer",callbackusers);
}
function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
  
}
function callbackorg(data){  //显示出类别
     dwr.util.removeAllOptions("Severity");
     dwr.util.addOptions("Severity",{'-1':'--请选择--'});
     dwr.util.addOptions("Severity",data,"severityValue","severityType");   
}

function callbackessential(data){  //显示出影响度
   dwr.util.removeAllOptions("essential");
   dwr.util.addOptions("essential",{'-1':'--请选择--'});
   dwr.util.addOptions("essential",data,"severityValue","severityType");  
   var ess= '${serviceCategory.defEss}';
   if(ess){
   		dwr.util.setValue("essential",ess);
   }
}
function callbackemergency(data){  //显示出紧急度
   dwr.util.removeAllOptions("emergency");
   dwr.util.addOptions("emergency",{'-1':'--请选择--'});
   dwr.util.addOptions("emergency",data,"severityValue","severityType"); 
   var eme= '${serviceCategory.defEme}';
   if(eme){
   		dwr.util.setValue("emergency",eme);
   }  
}

var fileInputNumber = 0;
	var fileName="";
  function addFile()
　　  	{	
			var strFile = "file"+fileInputNumber;
			var filePath = document.getElementById(strFile);
			
			document.getElementById(strFile).style.display = "none";
			var paths = filePath.value.split("\\");
	        var name = paths[paths.length-1];
<%--			var fileName = new Array();
			fileName.unshift(name);
				alert(fileName);
				--%>
		
			fileName=fileName+"%"+name;
		
			document.getElementById("fileName").value=fileName;
	       alert(name);
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
function dateJudge()
{
	
   
	
	if(document.getElementById("Severity").value==-1)
	{
	alert("请选择严重程度！");	
	return false;
	}

	if(document.getElementById("essential").value==-1)
	{
	alert("请选择影响度！");	
	return false;
	}
	
	if(document.getElementById("emergency").value==-1)
	{
	alert("请选择紧急程度！");	
	return false;
	}
	
	  
	if(document.getElementById("id2").value== "")
	{
		alert("请选择期望完成时间！");
		return false;
	}
	if(document.getElementById("des").value.length==0)
	{
	alert("请填写详情！");	
	return false;
	}
	var f=document.getElementById("auto").checked;
	if(f==true)
	{
		if(documnet.getElementById("users").value==-1)
		{ 
			alert("请指定工程师");
			return false;
	}
	}
}

function output()
{

var auto=document.getElementById("auto").checked;
if(auto==true)
{
	document.getElementById("users").disabled=false;
}
else{
	document.getElementById("users").disabled=true;
}
}
</script>
	</head>

	<body onLoad="getSeverityTypDAO()" leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0" >
		<s:form action="%{actionURI}" name="myForm" id="myForm"
			method="post" theme="simple" enctype="multipart/form-data">
			<s:hidden id="serviceRequest.id" name="serviceRequest.id"></s:hidden>
			<s:hidden id="fileName" name="fileName"></s:hidden>
			<s:hidden id="serviceCategory.id" name="serviceCategory.id"></s:hidden>
			<table cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td width="1%" height="22" align="center"
						background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
						<img src="../images/modpass.gif" width="16" height="16">
					</td>
					<td width="98%" background="../images/main20100521_582.gif"
						style="color: #FFFFFF; font-weight: bold;">
						您要提交的服务请求: <s:property value="serviceCategory.itemZh" />
					</td>
				</tr>
			</table>
			<div
				style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
				<table width="99%" border="0" align="center" cellpadding="5"
					cellspacing="1" bgcolor="#6d9db4">
					<tr>
						<td valign="top" bgcolor="#FFFFFF">
							<table width="100%" border="0" cellpadding="4" cellspacing="1"
								bgcolor="#FFFFFF">

								<tr>
									<td width="15%" height="31" valign="middle" bgcolor="#cbe3ef"
										class="td-left-s">
										<div align="left">
											<img src="../img/jiedian.gif" width="10" height="9">
											&nbsp;严重程度:
										</div>
									</td>
									<td width="25%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<div align="left">
											<select id="Severity"
												name="serviceRequest.severityTypBySeverity">
											</select>
										</div>
									</td>
									<td width="11%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<div align="left">
											<img
												src="${pageContext.request.contextPath }/img/jiedian.gif"
												alt="" width="10" height="9">
											&nbsp;影响度:
										</div>
									</td>
									<td width="21%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<div align="left">
											<span class="td-right-s" style="padding-top: 5px"> <select
													id="essential" name="serviceRequest.severityTypByEssential">
												</select> </span>
										</div>
									</td>
									<td width="14%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<div align="left">
											<img
												src="${pageContext.request.contextPath }/img/jiedian.gif"
												alt="" width="10" height="9">
											&nbsp;紧急度:
										</div>
									</td>
									<td width="22%" bgcolor="#EBF4F5" class="td-right-s"
										style="padding-top: 5px">
										<div align="left">
											<span class="td-right-s" style="padding-top: 5px"> <select
													id="emergency" name="serviceRequest.severityTypByEmergency">
												</select> </span>
										</div>
									</td>
								</tr>

								<tr>
									<td width="15%" bgcolor="#cbe3ef" class="td-left-s" height="31"
										nowrap style="padding-right: 30px">
										<img src="../img/jiedian.gif" width="10" height="9">
										希望完成时间:
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">
										<s:textfield name="serviceRequest.expectTime" id="id2"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}'})"></s:textfield>
										<img onClick="WdatePicker({el:$dp.$('id2'),dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}'})"
											src="../js/DatePicker/skin/datePicker.gif" width="16"
											height="22" />
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">
										<%--<input id="auto" type=checkbox onClick="output()">


										是否指定工程师--%>
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">
										<span class="td-right-s" style="padding-top: 5px"> <%--<select
												id="users" name="serviceRequest.usersByEngineerId.id"
												disabled="true"></select>--%> </span>
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">&nbsp;
										
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">&nbsp;
										
									</td>
								</tr>

								<!--  									
      <tr style='display: none'>
        <td height="31" valign="middle" bgcolor="#cbe3ef" class="td-left-s"><img src="../../img/jiedian.gif" width="10" height="9">&nbsp;服务申请人:</td>
        <td width="84%" bgcolor="#EBF4F5" class="td-right-s" style="padding-top: 5px"><select name="select" onChange="Chose(this.value)">
            <option value="wanglin">自已</option>
            <option value="">代替其它人</option>
          </select>
        </td>
        <td height="31" nowrap bgcolor="#EBF4F5" class="td-right-s" style="padding: 4px; padding-top: 1px; padding-bottom: 0px"><img width="200" height="0"><br>
            <img src="../../img/confi.jpg" align="absmiddle"><img width="5" height="0"><a id="NowUser">
            <input name="text" type="text" disabled id="NowUser" style="width: 177px; height: 20px" value="wanglin">
          </a> </td>
      </tr>
      -->

								<tr>
									<td valign="middle" bgcolor="#cbe3ef" class="td-left-s">
										<img src="../img/jiedian.gif" width="10" height="9">
										&nbsp;摘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要:
									</td>
									<td colspan="5" bgcolor="#EBF4F5" class="td-right-s">

										<s:textfield name="serviceRequest.summary"
											style="width: 100%; background-color: #FFFFCC"
											onKeyUp="if(this.value!=''){this.style.backgroundColor='#FFFFFF'}else{this.style.backgroundColor='#FFFFCC'}"
											title="summary"></s:textfield>

									</td>
								</tr>


								<tr>
									<td valign="middle" bgcolor="#cbe3ef" class="td-left-s">
										<img src="../img/jiedian.gif" width="10" height="9">
										&nbsp;详细描述:
									</td>
									<td colspan="5" bgcolor="#EBF4F5" class="td-right-s">
										<textarea id="des" name="serviceRequest.description" rows="8"
											cols="86" style="width: 100%; height: 120"></textarea>
									</td>
								</tr>

								<%--	<s:form action="fileUpload" id="addForm" method="POST"
									enctype="multipart/form-data" theme="simple">--%>
								<tr>
									<td rowspan="2" valign="top" bgcolor="#cbe3ef"
										class="td-left-s">
										<img src="../img/jiedian.gif" width="10" height="9">
										&nbsp;上传附件:
									</td>
									<td class="td-right-s" bgcolor="#EBF4F5" colspan="5">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td colspan="2">
													<div id="input">
														<input name="file" id="file0" type="file" value="添加附件"
															onChange="addFile()" />
														<font size="1.8" color="red">如果添加多个附件，请继续点击“浏览”</font>
													</div>
													<br />
													<div id="_file"></div>
													<br />
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<!--  
												<tr>
													<td>
						                            <input type="button" value="上传文件" onClick="addForm.submit()" class=mmBtn name="button">
					                                </td>
												</tr>
												-->
								<%--	</s:form>--%>





							</table>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="30" align="center">
										<input type="submit" value="提交申请"
											onClick="javascript:return dateJudge()" class=mmBtn
											name="button2">
										<input type="button" onClick="history.go(-1)" value="后退"
											class=mmBtn name="button">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</body>
</html>
