<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>User</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<base target="_self">
		<script type="text/javascript">
function changepage(page){
document.form1.action="user.action?page="+page;
document.form1.submit();
}
function query(){
document.form1.action="user.action";
document.form1.submit();
}
function res(){
document.getElementById("user.username").value="";
document.getElementById("user.truename").value="";
document.getElementById("departmentid").value="";
document.getElementById("departmentname").value="";
document.getElementById("locationid").value="";
document.getElementById("locationname").value="";
}
function clo(){
window.close();
}
function cle(){
var obj = new Object();
obj.id ="";
obj.name ="";
obj.department="";
window.returnValue=obj;
window.close();
}
function choose(ids,names,departments){
if(ids!=""){
var obj = new Object();
obj.id =ids;
obj.name =names;
obj.department=departments;
window.returnValue=obj;
}
window.close();
}
function notshow(){
document.getElementById('department').style.visibility='hidden';
document.getElementById('location').style.visibility='hidden';
}
</script>
	</head>

	<body onMouseDown="notshow();">
	
	<s:form action="" method='post' theme="simple" name="form1">
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right"  bgcolor="#deebf1" width="8%"> 登陆账号： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%"><s:textfield id="user.username" name="user.username" Style="width:100%;"></s:textfield></td>
          <td align="right"  bgcolor="#deebf1" width="8%"> 用户全名： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%"><s:textfield id="user.truename" name="user.truename" style="width:100%;"></s:textfield></td>
          </tr>
          <tr>
          <td align="right"  bgcolor="#deebf1" width="8%">所属部门：</td>
          <td bgcolor="#FFFFFF" align="center" width="8%">
<input type="hidden" name="user.department.id" id="departmentid" value="<s:property value="user.department.id" />">
<input type="text" name="user.department.name" id="departmentname" onClick="document.getElementById('department').style.visibility='visible'" readonly value="<s:property value="user.department.name" />" style="width:100%;">
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="department"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
          </td>
          <td align="right"  bgcolor="#deebf1" width="8%">地理区域：</td>
          <td bgcolor="#FFFFFF" align="center" width="8%">
<input type="hidden" name="user.location.id" id="locationid" value="<s:property value="user.location.id" />">
<input type="text" name="user.location.name" id="locationname" onClick="document.getElementById('location').style.visibility='visible'" readonly value="<s:property value="user.location.name" />" style="width:100%;">
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="location"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
          </td>
          </tr>
          <tr>
          <td bgcolor="#deebf1" align="center" colspan="4">
          <input type="button" style="height: 20px" class="mmBtn"  value="搜索" onClick="query();"/>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="关闭" onClick="clo();"/>&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="清空" onClick="cle();"/></td>
         </tr>
        </table>
	</s:form>
           <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
             <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			  <td height="22" width="6%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">登陆账号</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">用户全名</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">所属部门</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">地理位置</td>
			 </tr>
	        <s:iterator value="pageBean.list" var="user">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'" onclick="choose('<s:property value="id" />','<s:property value="truename" />','<s:property value="department.name" />');">
			  <td height="19" align="center" class="zczb_qua"><s:property value="username" /></td>
			  <td align="center" class="zczb_qua"><s:property value="truename" /></td>
			  <td align="center" class="zczb_qua"><s:property value="department.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="location.name" /></td>
			 </tr>
	        </s:iterator>
           </table>
		   
		<table border="0" align="center" cellpadding="0" class="list" cellspacing="0">
          <tr>
            <td height="30" align="right" class="zczb_qua">
             <s:if test="%{pageBean.allRow!=0}">
				共<s:property value="pageBean.allRow"/> 条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页
        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
        		<s:else>
            		<a href="#" onClick="changepage(1);">第一页</a>
            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage-1}"/>);">上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage+1}"/>);">下一页</a>
            		<a href="#" onClick="changepage(<s:property value="pageBean.totalPage"/>);">最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页 </s:else>
        	</s:if>
            </td>
          </tr>
        </table>
	</body>
</html>