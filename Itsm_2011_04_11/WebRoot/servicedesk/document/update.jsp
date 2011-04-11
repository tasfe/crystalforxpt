<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<html>
	<head>
		<title>添加文档</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">

		<script type='text/javascript' src='../dwr/util.js'>
</script>

		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript" src="../fckeditor/fckeditor.js">
</script>
		<script type='text/javascript' src='../js/jquery.js'>
</script>
		<script type="text/javascript">
var fileInputNumber = 0;
var fileName = "";
function addFile()　　  	{	


			var strFile = "file"+fileInputNumber;
			var filePath = document.getElementById(strFile);


			document.getElementById(strFile).style.display = "none";
			var paths = filePath.value.split("\\");
	        var name = paths[paths.length-1];
		
			fileName=fileName+"%"+name;
			
			document.getElementById("fileName").value=fileName;
	
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
	
		var title= $("#title").attr("value"); 
        if(title==null)
        	{
        	alert("文档标题不允许为空！")
        	return false;
		}
        var summary =$("#summary").attr("value");
        if(summary==null)
        	{
        	alert("摘要不允许为空！");
        		return false;
        	}

	return true;
	}
	function clo()
{
window.close();
}
	function onload()
	{
		cloo();
		$("#summary").val('<s:property value="dc.summary"/>');
		
	}

function del(acessoryId,documentId)
{

	var msgs = "确认删除记录吗？";
	if (confirm(msgs) == true) {
		window.location='del_acessory.action?document_id='+documentId+"&acessory_id="+acessoryId;
		return true;
	} else {
		return false;
	}
	
}
   function cloo()
{    
     var ag ='<s:property value="msg" escape="false" />';
	 if(ag)
	 {	
		alert("提示："+ag);
	 }
 }
   function Chose(){
	   var dellist=""
	   var memberIds = document.getElementsByName('chose');
	   var memberUsernames = document.getElementsByName('xxx');
	   	for (var i = 0; i < memberIds.length; i++) {
	    if (memberIds[i].checked) {
	    	
	    	memberUsernames[i].style.display="";
	    	dellist=memberIds[i].value+'%'+dellist;
	    	
	    }
	    else{
	    	memberUsernames[i].style.display="none";
	    }
	    }
	   
        document.getElementById('delstrings').value=dellist;
}
	</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="padding: 7px;; cursor: default; overflow-y: scroll"
		onload="onload()">
		<s:form action="update" namespace="/document" method="post"
			enctype="multipart/form-data" theme="simple">
			<s:hidden id="pid" name="pid"></s:hidden>
			<s:hidden id="fileName" name="fileName"></s:hidden>
			<s:hidden id="dc.id" name="dc.id"></s:hidden>
			<s:hidden id="delstrings" name="delstrings"></s:hidden>
			<table width="80%" height="100%" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="92%">
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input type="text" id="title" style="width: 100%; height: 100%"
										name="dc.title" value=${dc.title } />
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									摘要：
								</td>

								<td width="87%" bgcolor="#FFFFFF">
									<textarea id="summary" name="dc.summary" width="100%"
										style="width: 300px">
									</textarea>
								</td>
							</tr>
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									关键词：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input id="keyword" name="dc.keyword"
										value='${dc.keyword}'
										style="height: 95%; width: 100%" title="关键词" />
								</td>
							</tr>

							<tr>
								<td width="13%" height="50%" align="right" valign="middle"
									bgcolor="#deebf1">
									内容：
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<FCK:editor instanceName="dc.context" toolbarSet="Mybar"
										basePath="/fckeditor" height="100%" value="${dc.context}">
										<FCK:config
											CustomConfigurationsPath="${pageContext.request.contextPath}/js/fckeditor/myconfig.js" />
									</FCK:editor>
								</td>
							</tr>
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									附件：
								</td>
								<td class="td-right-s" bgcolor="#EBF4F5" colspan="5">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2">
												<div id="input1" align="right">
													<s:iterator value="accessoryList" var="accesory">
														<tr>
															<input name="chose" type="checkbox"
																value="<s:property value='id'></s:property>"
																style="border: 0px" onClick="{Chose(this.value)}">
															<a
																href="down.action?dlFileName=<s:property value='name'></s:property>">
																<s:property value="trueName" /> </a>

															<img src="../images/del.gif"
																<%-- onclick="javascript:return del(${id},${dc.id})" 单击删除附件  --%>
																style="display: none"
																name="xxx">
														</tr>
													</s:iterator>

												</div>
											</td>
										</tr>
									</table>

								</td>
							</tr>
							<tr>
								<td rowspan="2" valign="middle" align="right" bgcolor="#deebf1">
									上传附件：
								</td>
								<td bgcolor="#deebf1" class="td-right-s" colspan="5">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td colspan="2">
												<br />
												<div id="_file"></div>
												<br />
												<div id="input">
													<input name="file" id="file0" type="file" value="添加附件"
														onChange="addFile()" />
													<font class="zc_add">如果添加多个附件，请继续点击“浏览”</font>
												</div>

											</td>
										</tr>
									</table>
								</td>
							</tr>

						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							height="8%">
							<tr align="center" bgcolor="white">
								<td align="center" height="12" style="padding-top: 5px">
									<input class="mmBtn" name="submit" type="submit" value="保存"
										onClick="return sub()">
									&nbsp;
									<input name="button" type="button" class="mmBtn"
										onClick="clo();" value="返回">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>