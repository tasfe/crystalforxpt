<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html xmlns="http://www.w3.org/1999/xhtml">

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
	       ServiceCategoryDAO.findAll(callbackserviceCategory);
	       UserDAO.findAll(callbackusers);
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
		</script>
	</head>
	<%
		FCKeditor fckEditor = new FCKeditor(request, "knowledgeBase.solution");
	%>
	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0"marginheight="0" style="padding: 7px;; cursor: default;">
		<s:form action="update" namespace="/knowledgebase" method="post" theme="simple">
			<table width="60%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="100%">
							<tr>
								<td width="13%" height="1%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题:
									<s:hidden name="knowledgeBase.id" id="knowledgeBase.id"/>
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textfield id="knowledgeBase.title" style="width:100%"
										name="knowledgeBase.title" />
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									内容:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
									<s:textarea id="knowledgeBase.symptom" name="knowledgeBase.symptom"
										style="height: 100%; width: 100%"></s:textarea>
								</td>
							</tr>
								<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									所属类别:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
		                          <select id="service_category" name="knowledgeBase.categoryId.id" style="width: 100%"></select>
								</td>
							</tr>
							
									<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									工程师:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
					             <select id="users" name="knowledgeBase.engineerId.id" style="width: 100%"></select>
								</td>
							</tr>
							
							<tr>
								<td width="13%" height="30%" align="right" valign="middle"
									bgcolor="#deebf1">
									解决方案:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<!--<FCK:editor instanceName="knowledgeBase.solution" basePath="/fckeditor" value="${knowledgeBase.solution}"></FCK:editor>
							 	-->
							<FCK:editor instanceName="knowledgeBase.solution" toolbarSet="Basic" basePath="/fckeditor" value="${knowledgeBase.solution}"></FCK:editor>
								</td>
							</tr>
									</table>
								</td>
							</tr>
							
		
				<tr align="center">
					<td align="center" height="12" style="padding-top: 5px">
						<input class="mmBtn" name="submit" type="submit"
							style="width: auto" value="保存">
						&nbsp;
						<input name="button" type="button" class="mmBtn"
							style="width: auto" onClick="window.location='list.action'"
							value="返回">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>