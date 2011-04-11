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
	
	var str = "";
	
		for(var i=0;i<data.length;i++)
		{
			if(i!=0&&i%5==0){
			str +='<br/>';
		}
 			str +="<span style='width:140px;'><input type='checkbox' name='ck' value='" + data[i].truename+ "'/>" + data[i].truename + "<br/></span>";

 	 	}
 	 	str='<div style="padding:2px;border:solid 1px #9FB6CD;width:100%;font-size:12px;color:#3A4E69;">'+str+'</div>';
 		document.getElementById("div").innerHTML=str;
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
   	
   	function exit(){
    		parentDialog.close();
   	}
	</script>
  </head>
  
  <body onLoad="findUser();">
    <%--<select id="users">
    	<option value="-1">--请选择--</option>
    </select>
    --%><!--
    <input type="button" value="asdasd" onclick="Dialog.alert('asdasdas');"/>
    --><div id="div"></div>
   <div align="center" style=" margin-top:10px;">
    <input type="button" onClick="setUser();"  value="提 交"/>
    <input type="button" onClick="exit();"  value="取 消"/></div>
  </body>
</html>
