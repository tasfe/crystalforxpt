<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/zcms/zDrag.js"></script>
	<script type="text/javascript" src="js/zcms/zDialog.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/UserDAO.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
	<script type="text/javascript">
	
	function findUser(){
		UserDAO.findAll(callbackusers);
	}
	
	function callbackusers(data){  //显示出分类
		dwr.util.removeAllOptions("users");
 		dwr.util.addOptions("users", [{id:'-1',name:'--请选择--'}],"id","name");
 		dwr.util.addOptions("users",data,"id","username");   
		var temp = "";
		for(var i=0;i<data.length;i++)
		{
 			temp +="<input type='checkbox' name='ck' value='" + data[i].truename+ "'/>" + data[i].truename + "<br/>";

 	 	}
 		document.getElementById("div").innerHTML=temp;
   	}
   	
	function setUser()
	{
   		var cks = document.getElementsByName("ck");
   		var temp = "";
   		for(var i=0;i<cks.length;i++){
   			if(cks[i].checked==true)
   			temp += cks[i].value+",";
   		}
   		window.parent.document.getElementById("to").value = temp;
   		Dialog.close();
   	}
	</script>
  </head>
  
  <body onload="findUser();">
    <%--<select id="users">
    	<option value="-1">--请选择--</option>
    </select>
    --%><!--
    <input type="button" value="asdasd" onclick="Dialog.alert('asdasdas');"/>
    --><div id="div"></div>
    <input type="button" onclick="setUser();" value="提 交"/>
  </body>
</html>
