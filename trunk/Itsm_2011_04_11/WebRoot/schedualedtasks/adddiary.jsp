<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>添加日志</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function sub() {
				var title=document.getElementById("title").value;
				if(title=='') {alert("请输入日志标题！"); return false;}
				var content=document.getElementById("contentt").value;
				if(title=='') {alert("请输入日志内容！"); return false;}
				return true;
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
	</head>

	<body background="${pageContext.request.contextPath }/img/main.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					<img src="${pageContext.request.contextPath }/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					添加日志
				</td>
			</tr>
		</table>

		<div>
			<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" id="mainright">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" height="95%">
							<tr>
								<td>
									<table width="100%" border="1" cellpadding="1" cellspacing="0" height="100%">
										<tr>
											<td style="padding: 10px; line-height: 10px" bgcolor="#FFFFFF">
											<s:form name="myForm" action="savediary" method="post" theme="simple" enctype="multipart/form-data">
											<s:hidden name="std.taskDetailId"/>
												<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
													<tr>
														<td height="99%" colspan="2">
															<table width="100%" height="95%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
																<tr>
																	<td width="2%" height="5%" bgcolor="#EBF4F5">
																		<center>日志标题:</center>
																	</td>
																	<td bgcolor="#FFFFFF">
																		<input type="text" name="std.title" id="title" style="width:40%;height:100%">
																	</td>
																</tr>
																<tr>
																	<td height="70%" bgcolor="#EBF4F5">
																		<center>日志内容:</center>
																	</td>
																	<td valign="top" width="20%" bgcolor="#FFFFFF">
																		<textarea id="content" name="std.content" style="width: 100%;height:100%"></textarea>
																	</td>
																</tr>
																<tr>
																	<td height="5%" bgcolor="#EBF4F5">
																		<center>任务进度:</center>
																	</td>
																	<td bgcolor="#FFFFFF">
																		<select id="progress" name="std.progress" style="width:40%;height:100%">
																			<option value="1">1%</option>
																			<s:iterator var="counter" begin="5" end="100" step="5" >
																				<option value="<s:property value='#counter'/>"><s:property value='#counter'/>%</option>
																			</s:iterator>																		
																		</select>
																	</td>
																</tr>
																<tr>
																	<td valign="top"  height="20%" bgcolor="#EBF4F5"><center>附件:</center></td>
																	<td valign="bottom" height="20"  bgcolor="#FFFFFF" style="padding-top: 0px; padding-bottom: 0px">
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
															<table width="100%" border="0" cellpadding="4" cellspacing="0" background="images/addnew002.gif">
																<tr>
																	<td align="center">
																		<input type="submit" value="提交" onclick="return sub()" class="mmBtn">
																		<input type="button" value="关闭" onclick="window.close()" class="mmBtn">
																	</td>
																</tr>
															</table>
												</table>
												</s:form>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		</div>

	</body>
</html>
