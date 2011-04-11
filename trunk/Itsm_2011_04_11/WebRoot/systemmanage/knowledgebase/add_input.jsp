<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<html>
	<head>
		<title>添加知识库管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
		<!-- ext core -->
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<script type="text/javascript" src="../js/util.js"></script>
		
		<script type="text/javascript">
      function getDAO() { //取出类别
	     //ServiceCategoryDAO.findAll(callbackserviceCategory);
	     //UserDAO.findEngineer(callbackusers);
       }
	 function selectUser(){
		var win = new UserSelectWindow({
			accepted:function(user){
				document.getElementById('knowledgeBase.engineerId.name').value=user.name;
				document.getElementById('knowledgeBase.engineerId.id').value=user.id;
			}
		});
		win.show();
	}
      function callbackserviceCategory(data){  //显示出分类
 			dwr.util.removeAllOptions("service_category");
   			dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("service_category",data,"id","itemZh");  
    		var a = "<s:property value="serviceRequest.serviceCategory.itemZh" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("service_category",a);  
   			} 
		}	
		
		function callbackusers(data){  //显示出分类
 			dwr.util.removeAllOptions("users");
   			dwr.util.addOptions("users", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("users",data,"id","truename");  
    		var a = "<s:property value="knowledgeBase.engineerId.truename" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("users",a);  
   			} 
		}	
		
var fileInputNumber = 0;
 function addFile() {	
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
	function sub(){
		var value1=document.getElementById("knowledgeBase.title").value;
			if(value1=='') { alert("请输入标题！"); return false; }
		var value2=document.getElementById("knowledgeBase.symptom").value;
			if(value2=='') { alert("请输入症状描述！"); return false; }
		//var value3=document.getElementById("service_category").value;
			//if(value3==-1) { alert("请选择所属类别！"); return false; }
		if(!(document.getElementById("cat.name").value)){//modify jyxiao 
			alert("请选择所属类别！");
			return false;
		}
		var value4=document.getElementById("users").value;
			if(value4==-1) { alert("请选择工程师！"); return false; } 
		return true;
	}

	</script>
	</head>
	<%
		FCKeditor fckEditor = new FCKeditor(request, "knowledgeBase.solution");
	%>
	<body onLoad="getDAO();" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;; cursor: default;overflow-y:scroll;overflow-x:hidden" onmousedown="document.getElementById('Layer2').style.visibility='hidden'">
		<s:form action="save" namespace="/knowledgebase" method="post" enctype="multipart/form-data" theme="simple" >			
			<table width="80%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#b5d6e6" height="92%">
							<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									标题:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input type="text" id="knowledgeBase.title" style="width:100%;height:100%" name="knowledgeBase.title" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="20%" align="right" valign="middle" bgcolor="#deebf1">
									症状描述:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<textarea id="knowledgeBase.symptom" name="knowledgeBase.symptom" style="height: 95%; width: 100%"></textarea>
								</td>
							</tr>							
							<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									所属类别:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input id="cat.name" type="text" name="knowledgeBase.categoryId.itemZh" style="width: 60%; background-color: #FFFFFF; cursor: text" readonly value="<s:property value="knowledgeBase.categoryId.itemZh"/>" onClick="document.getElementById('Layer2').style.visibility='visible'">
                    <s:hidden id="cat.id" name="knowledgeBase.categoryId.id"></s:hidden>
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br>

																<div id="Layer2" style="position:absolute; width: 100%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

																	<iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../serviceCategory/cat.action" style="border: 1px solid #E5E9EE"></iframe>

																</div>
				
					
								<%--
		                          <select id="service_category" name="knowledgeBase.categoryId.id" style="width: 100%"></select>
								--%></td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle" bgcolor="#deebf1">
									工程师:
								</td>
								<!--  
								<td width="87%" bgcolor="#FFFFFF">
					             	<select id="users" name="knowledgeBase.engineerId.id" style="width: 100%"></select>
								</td>
								-->
								<td width="14%" bgcolor="#FFFFFF" class="td-right-s" style="padding-top: 5px">
										<s:hidden id="knowledgeBase.engineerId.id" name="knowledgeBase.engineerId.id"></s:hidden>
										<input id="users" type="text" name="knowledgeBase.engineerId.name" style="width: 40%; background-color: #FFFFFF; cursor: text" readonly  onClick="javascript:selectUser();">
&nbsp;<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser();">
								</td>
							</tr>							
							<tr>
								<td width="13%" height="30%" align="right" valign="middle" bgcolor="#deebf1">
									解决方案:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<FCK:editor  instanceName="knowledgeBase.solution" toolbarSet="Basic" basePath="/fckeditor"></FCK:editor>
								</td>
							</tr>
							<tr>
								<td height="20%"  bgcolor="#deebf1" align="right"  valign="middle"> 上传附件:
								</td>
								<td bgcolor="#FFFFFF">
									<div style="width:100%;height:100%;overflow-y:auto"> 
									<table width="100%" border="0" cellspacing="1" cellpadding="4">
										<tr>
											<td width="99%" bgcolor="#F1F2F7" style="padding-top: 1px; padding-bottom: 2px">
												<div id="input">
													<input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
													<font size="2" color="red">如果添加多个附件，请继续点击“浏览”</font>
												</div>
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
								<td align="center" height="12" style="padding-top: 5px">
									<input class="mmBtn" name="submit" type="submit" value="保存" onClick="return sub()"> &nbsp;
									<input name="button" type="button" class="mmBtn" onClick="window.history.go(-1)" value="返回">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
		<center><font color="red"><s:property value="message"/></font> </center>
	</body>
</html>