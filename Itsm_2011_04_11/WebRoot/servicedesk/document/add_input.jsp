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
       var oEditor = FCKeditorAPI.GetInstance("dc.context");
       var oDOM = oEditor.EditorDocument;
       var iLength ;
        if(document.all){
           iLength = oDOM.body.innerText.length;
         }else{
        var r = oDOM.createRange();
        r.selectNodeFCKeditor1s(oDOM.body);
        iLength = r.toString().length;
         }
        if(iLength>20000)
	    {
	    alert("你输入的内容过长！");
	    return false;
	}

	return true;
	}
	function clo()
{
window.close();
}
	</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="padding: 7px;; cursor: default; overflow-y: scroll">
		<s:form action="save" namespace="/document" method="post"
			enctype="multipart/form-data" theme="simple">
			<s:hidden id="pid" name="pid"></s:hidden>
			<s:hidden id="fileName" name="fileName"></s:hidden>
			<table width="80%" height="100%" border="0" align="center"
				cellpadding="0" cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="92%">
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题：</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input type="text" id="title" style="width: 90%; height: 100%"
										name="dc.title" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									摘要：</td>
								<td width="87%" height="10%" bgcolor="#FFFFFF">
									<textarea id="summary" name="dc.summary" rows="3" style="width: 90%"></textarea>
								</td>
							</tr>
							<tr>
								<td width="13%" height="5%" align="right" valign="middle"
									bgcolor="#deebf1">
									关键词：</td>
								<td width="87%" bgcolor="#FFFFFF">
									<input id="dc.keyword" name="dc.keyword"
										style="height: 95%; width: 90%" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="50%" align="right" valign="middle"
									bgcolor="#deebf1">
									内容：
								</td>
								<td width="87%" bgcolor="#FFFFFF">

									<FCK:editor instanceName="dc.context" toolbarSet="Mybar"
										basePath="/fckeditor" height="100%">
										<FCK:config
											CustomConfigurationsPath="${pageContext.request.contextPath}/js/fckeditor/myconfig.js" />
									</FCK:editor>
								</td>
							</tr>
							<tr>
								<td rowspan="2" bgcolor="#deebf1" align="right" valign="middle">
									<img src="../img/jiedian.gif" width="10" height="9">
									&nbsp;上传附件：</td>
								<td class="td-right-s" bgcolor="#EBF4F5" colspan="5"><table width="97%" border="1" cellspacing="0" cellpadding="0"  style="border:#C3BFB3 1px solid; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px; background-color:#FFF" class="zcxx_tab3">
								  <tr>
								    <td height="20" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="4">
								      <tr>
								        <td valign="top" height="100" colspan="2"><div id="_file"></div>
								          <br/></td>
							          </tr>
								      <tr>
								
								        <td width="99%" bgcolor="#EBF4F5" style="padding-top: 1px; padding-bottom: 2px"  class="zc_add"><div id="input">
								          <input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
								          <font class="zc_add">如果添加多个附件，请继续点击“浏览”</font> </div></td>
							          </tr>
								      </table></td>
							      </tr>
							    </table></td>
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