<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<html>
	<head>
		<title>查看知识库管理</title>
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
		function setHtml(){
			var solution = "<s:property value='knowledgeBase.solution'/>";
			document.getElementById("solution").innerHTML=solution;
		}
		</script>
	</head>
	<%
		FCKeditor fckEditor = new FCKeditor(request, "knowledgeBase.solution");
	%>
	<body onLoad="getDAO();setHtml();" leftmargin="0" topmargin="0" marginwidth="0"marginheight="0" style="padding: 7px;; cursor: default;">
		<s:form action="user_show" namespace="/knowledgebase" method="post" theme="simple">
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgColor="white" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="2"
							bgcolor="#b5d6e6" height="100%">
								<tr>
								<td width="13%" height="1%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<s:property value="knowledgeBase.indexcode"/>
								</td>
							</tr>
							<tr>
								<td width="13%" height="1%" align="right" valign="middle"
									bgcolor="#deebf1">
									标题:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<s:property value="knowledgeBase.title"/>
								</td>
							</tr>
							<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									内容:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
										<s:property value="knowledgeBase.symptom"/>
								</td>
							</tr>
								<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									所属类别:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
								<s:property value="knowledgeBase.categoryId.itemZh"/>
								</td>
							</tr>
							
									<tr>
								<td width="13%" height="10%" align="right" valign="middle"
									bgcolor="#deebf1">
									工程师:
								</td>
								<td width="87%" bgcolor="#FFFFFF">
					             <s:property value="knowledgeBase.engineerId.truename"/>
								</td>
							</tr>
							
							<tr>
								<td width="13%" height="300px" align="right" valign="middle"
									bgcolor="#deebf1">
									解决方案:
								</td>
								<td width="87%" height="300px"  bgcolor="#FFFFFF" valign="top">
	                           		<!-- <div id="solution" style="padding:6px 10px;text-align:center"></div> -->
	                           		<s:property value='knowledgeBase.solution'  escape="false"/>
								</td>
							</tr>
							
								<tr>
								<td width="13%" height="300px" align="right" valign="middle"
									bgcolor="#deebf1">
									相关附件:
								</td>
								<td height="45%"  valign="top" bgcolor="#EBF4F5"
									class="td-detail"
									style="font-weight: normal; line-height: 22px">
								<table width="100%" height="100%" border="0" cellpadding="4"
							cellspacing="1" bgcolor="#FFFFFF">
									<s:iterator value="accessoryList" var="accesory">
										<!--<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'"
											onMouseOut="this.bgColor='#ffffff'">
											<td height="12" width="100%">-->
												
												<tr><a href="download.action?dlFileName=<s:property value='name'></s:property>">
												<s:property value="trueName"></s:property>
												</a>
												</tr>
									</s:iterator>
								</table>
								</td>
								<tr>
							
									</table>
								</td>
							</tr>
								
				<tr align="center">
					<td align="center" height="12" style="padding-top: 5px">
						
						<input name="button" type="button" class="mmBtn"
							style="width: auto" onClick="window.location='query2.action'"
							value="返回">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>