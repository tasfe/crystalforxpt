<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员选择</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" /> 
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/UsersDAO.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type="text/javascript">
	
function getDepart(){ //取出类别
DepartmentDAO.findAll(callbackDepart);
}
function callbackDepart(data){  //显示出类别
   dwr.util.removeAllOptions("department");
   dwr.util.addOptions("department",["--请选择--"]);
   dwr.util.addOptions("department",data,"id","name");
}

function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
}

function button_onclick() {
var obj=document.getElementById("Users");
window.opener.myForm.user_fullname.value = obj.options[obj.selectedIndex].text;
window.opener.myForm.user_id.value = document.getElementById("Users").value; 
window.close(); 
}

</script>
</head>

<body onload="getDepart()">

<!--
function getPerson(depart){ //取出类别
UsersDAO.findByDepartment(department,callbackPerson)
}
function callbackPerson(data){  //显示出类别
   dwr.util.removeAllOptions("Users");
   dwr.util.addOptions("Users",data,"id","truename");
}
-->


<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" bordercolor="#E2E2E1" bgcolor="#FFFFFF">
<tr>
    <td width="100%" valign="top"> 
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="27">
        <tr>
             <td width="5%" height="27"  background="../images/contentbg.gif"><div align="center"><img src="<%=request.getContextPath()%>/images/contenttop.gif"height="27" /> </div></td> 
             <td width="95%"  background="../images/contentbg.gif"><strong><font color="#1445A6">人员选择</font></strong> </td>
        </tr>
      </table> 
     <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#C0CCDD" id="content">
 		<tr>
 		<td>
			<div align="center">
			<select id="department" onchange="getPerson(this.value)"></select><br /><br />
			<select id="Users" style="width:100"></select><br /><br />
			<input type="submit" name="Submit" value="确认" class="page" onclick="return button_onclick()" />
			</div>
 		</td>
 		</tr>
    </table>
	 </td>
  </tr>
  <tr>
  	<td  class="page">
    <table width="100%" border="0" align="left" >
	  	<tr>
	    <td height="30" ><div align="center"></div></td>
	    </tr>
	</table>
	</td>
  </tr>
</table>
</body>
</html>
