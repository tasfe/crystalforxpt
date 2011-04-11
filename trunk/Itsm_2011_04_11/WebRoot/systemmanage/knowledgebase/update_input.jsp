<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html>
	<head>
		<title>更新知识库管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
         function getDAO() { //取出类别
	       //ServiceCategoryDAO.findAll(callbackserviceCategory);
	       //UserDAO.findAll(callbackusers);
          }

      function callbackserviceCategory(data){  //显示出分类
 			dwr.util.removeAllOptions("service_category");
   			dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("service_category",data,"id","itemZh");  
    		var a = "<s:property value="knowledgeBase.categoryId.id" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("service_category",a);  
   			} 
		}	
		
		function callbackusers(data){  //显示出分类
 			dwr.util.removeAllOptions("users");
   			dwr.util.addOptions("users", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("users",data,"id","truename");  
    		var a = "<s:property value="knowledgeBase.engineerId.id" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("users",a);  
   			} 
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

	function sub(){
		var value1=document.getElementById("knowledgeBase.title").value;
			if(value1=='') { alert("请输入标题！"); return false; }
		var value2=document.getElementById("knowledgeBase.symptom").value;
			if(value2=='') { alert("请输入症状描述！"); return false; }
		//var value3=document.getElementById("service_category").value;
			//if(value3==-1) { alert("请选择所属类别！"); return false; }
		//var value4=document.getElementById("users").value;
			//if(value4==-1) { alert("请选择工程师！"); return false; }
		return true;
	}

</script>
	</head>
	<%
		FCKeditor fckEditor = new FCKeditor(request, "knowledgeBase.solution");
	%>
	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;overflow-y:scroll" >
		<s:form action="update" namespace="/knowledgebase" method="post" theme="simple" enctype ="multipart/form-data">
			<s:hidden name="knowledgeBase.id" id="knowledgeBase.id"/>
			<s:hidden id="knowledgeBase.indexcode" name="knowledgeBase.indexcode"></s:hidden>
			<s:hidden id="knowledgeBase.mode" name="knowledgeBase.mode"></s:hidden>
			<table width="80%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="92%">
							<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									标题:									
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textfield id="knowledgeBase.title" cssStyle="width:100%;height:100%" name="knowledgeBase.title" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="20%" align="right" valign="middle" bgcolor="#deebf1">
									症状描述:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textarea id="knowledgeBase.symptom" name="knowledgeBase.symptom" cssStyle="height:100%; width: 100%"></s:textarea>
								</td>
							</tr>
								<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									所属类别:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<!--  
		                          <select id="service_category" name="knowledgeBase.categoryId.id" style="width: 100%" ></select>
		                         -->
		                         <s:textfield name=" knowledgeBase.categoryId.itemZh" readonly="true" style="width: 50%;background-color:#D4D4D4"></s:textfield>
					             <s:hidden name=" knowledgeBase.categoryId.id"></s:hidden>
								</td>
							</tr>
							
							<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									工程师:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<!--  
					             <select id="users" name="knowledgeBase.engineerId.id" style="width: 100%"></select>
					             -->
					             <s:textfield name="knowledgeBase.engineerId.truename" readonly="true" style="width: 40%;background-color:#D4D4D4 "></s:textfield>
					             <s:hidden name="knowledgeBase.engineerId.id"></s:hidden>
								</td>
							</tr>
							
							<tr>
								<td width="13%" height="30%" align="right" valign="middle" bgcolor="#deebf1">
									解决方案:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<!--<FCK:editor instanceName="knowledgeBase.solution" basePath="/fckeditor" value="${knowledgeBase.solution}"></FCK:editor>
							 	-->
							<FCK:editor instanceName="knowledgeBase.solution" toolbarSet="Basic" basePath="/fckeditor" value="${knowledgeBase.solution}"></FCK:editor>
								</td>
							</tr>
							<tr>
								<td align="right" height="20%" bgcolor="#deebf1"  valign="middle">上传附件:
								</td>
								<td bgcolor="#FFFFFF">
									<div style="height:100px;overflow-y:auto"> 									
									<table width="100%" border="0" cellspacing="1" cellpadding="4">
											<tr>
												<td width="99%" bgcolor="#F1F2F7" style="padding-top: 1px; padding-bottom: 2px">
													<div id="input">
														<input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
														<font size="2" color="red">如果添加多个附件，请继续点击“浏览”</font>
													</div>
												</td>
											</tr>
											<tr>
												<td valign="top" align="left" style="padding: 10px; "  >
													<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px">
														<s:iterator value="accessoryList" var="accessory" status="st">
															<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
																<td align="left">
																	<a href="download.action?dlFileName=<s:property value="name"/>"><s:property value="trueName" />
																	</a>
																	<a href="#" onClick="if(confirm('确认删除吗？')) window.location.href='deleteaccessary.action?knowledgeBaseId=${knowledgeBase.id}&accessoryId=<s:property value='id'/>'"><img src="../img/del2.gif" border="0" alt="删除附件"></a><br>													
																</td>
															</tr>
														</s:iterator>
													</table>
												</td>
											</tr>
											<tr><td><div id="_file"></div></td></tr>
									</table>
								</div>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" height="8%">
							<tr align="center" bgcolor="white">
								<td colspan=2 align="center" height="12" style="padding: 5px">
									<input class="mmBtn"  type="submit" style="width: auto" value="保存" onClick="return sub()"> &nbsp;
									<input name="button" type="button" class="mmBtn" style="width: auto" onClick="window.history.go(-1)" value="返回">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>