<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*" %>
<link href="../css/style.css" rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type="text/javascript">
function getUserDAO(){ //取出类别
UserDAO.findAll(callbackorg)
}
function callbackorg(data){  //显示出类别
   dwr.util.removeAllOptions("Manager");
   dwr.util.addOptions("Manager",["--请选择--"]);
   dwr.util.addOptions("Manager",data,"id","truename");
   var a = "<s:property value="users.managerId" />";
   if (typeof(a) != "undefined") {   
   dwr.util.setValue("Manager",a);  
   }  
}


</script>
</head>
<body onLoad="getUserDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;">
  <s:form action="%{actionURI}.action" method="post" theme="simple">
  <s:hidden id="user.id" name="user.id"></s:hidden>
   <s:hidden id="user.department.id" name="user.department.id"></s:hidden>
   <s:hidden id="user.location.id" name="user.location.id"></s:hidden>
  <s:hidden id="user.username" name="user.username"></s:hidden>
  <s:hidden id="user.lastAccessTime" name="user.lastAccessTime"></s:hidden>
  <s:hidden id="user.lastAccessIp" name="user.lastAccessIp"></s:hidden>
  <s:hidden id="user.sex" name="user.sex"></s:hidden>
  <s:hidden id="user.birthday" name="user.birthday"></s:hidden>
  <s:hidden id="user.address" name="user.address"></s:hidden>
   <s:hidden id="user.status" name="user.status"></s:hidden>
  

			  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
		
				<tr> 
				  <td bgcolor="white" valign="top">
                    <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">用户全名:</td>
                        <td width="35%" bgcolor="#FFFFFF"><s:textfield id="user.truename" name="user.truename" ></s:textfield></td>
                        <td width="15%" align="center" bgcolor="#deebf1" title="The password will not be changeed if keep empty..">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF" title="The password will not be changeed if keep empty.."><input type="password" name="user.password" value="${user.password}" style="width:100%"></td>
                      </tr>
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮:</td>
                        <td width="35%" bgcolor="#FFFFFF"><s:textfield id="user.email" name="user.email" cssStyle="width: 100%"></s:textfield></td>
                        <td width="15%" align="center" bgcolor="#deebf1">用户类别:</td>
                        <td width="35%" bgcolor="#FFFFFF"> 
                          	<select id="user.usertype" name="user.usertype">
																	<option id="category1" value="system">
																		系统管理员</option>
																	<option id="category2" value="user">
																		用户</option>
																	<option id="category3" value="manager">
																		主管</option>	
																	<option id="category4" value="engineer">
																		工程师</option>				
							</select>
							<script type="text/javascript">
							                                       var select = document.getElementById('user.usertype');
							                                       for (var i = 0; i < 4; i++) {
							                                        	if (<s:property value="user.usertype" /> == select.options[i].value) {
								                                        select.options[i].selected = 'selected';
							                                         	}
						                                         	}
						     </script>
						</td>

                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><s:textfield id="user.cellphone" name="user.cellphone" cssStyle="width: 100%"></s:textfield></td>
                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">部门经理:</td>
                        <td bgcolor="#FFFFFF"><select id="Manager" name="user.managerId"></select>	</td>
                        <td width="15%" align="center" bgcolor="#deebf1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</td>
                        <td width="35%" bgcolor="#FFFFFF"><s:textfield id="user.phone" name="user.phone" cssStyle="width: 100%"></s:textfield></td>
                      </tr>
                      <tr style="display: none">
                        <td align="center" bgcolor="#deebf1">所在公司:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><input type="text" name="Subcompany" value="|242," style="width:100%"></td>
                      </tr>
                      <tr> 
                      <!--  
                        <td align="center" bgcolor="#deebf1">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务:</td>
                        <td bgcolor="#FFFFFF">
						<select name="Team1" style="height: 20px; width: 48%">
                          <option>角色</option>
                        </select>
						<select name="Team2" style="height: 20px; width: 48%">
                          <option>工作组</option>
                        </select></td>
                        -->
                        
                        <!-- 
                        <td align="center" bgcolor="#deebf1">优先级:</td>
                        <td bgcolor="#FFFFFF">
						   <select id="Severity" name="user.XXXX.id"></select>					
						</td>
						-->	
						
                      </tr>
                      <tr>
                        <td height="50%" align="center" valign="top" bgcolor="#deebf1">所属部门:</td>
                        <td style="padding: 0px; border-bottom: 1px solid buttonface" colspan="3"><iframe name="iframe" id="11" height="100%" width="100%" frameborder="0" scrolling="yes" src=""></iframe></td>
                      </tr>
                      <!--  
                      <tr> 
                        <td align="center" bgcolor="#deebf1">冻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结:</td>
                      	<td bgcolor="#FFFFFF">
					   <input type="checkbox" name="Remark" value="1" style="border: 0px; width: 14px">冻结用户</td>
                        <!--  
                        <td align="center" bgcolor="#deebf1">语言:</td>
                        <td bgcolor="#FFFFFF">
                          <select name="Langua" style="width: 80%">
                            <option value="EN" >英文</option>
                            <option value="CN" selected>简体中文</option>
                        </select></td>
                        -->
                      
                    
                      <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">所在地理位置:</td>
                        <td style="padding: 0px; border-bottom: 1px solid buttonface" colspan="3"><iframe name="testwin" id="22" height="100%" width="100%" frameborder="0" scrolling="yes" src=""></iframe></td>
                      </tr>
					    <!-- 
						<tr>
						  <td align="center" valign="top" bgcolor="#deebf1">员工工号:</td>	  
						  <td colspan="3" bgcolor="#FFFFFF">
						  <input type="text" name="Add1" value="${user.id }" style="width: 100%">						  </td>
						</tr>
						 -->
                    </table>
				  </td>
				</tr>
				<tr align="center" style="height: 25">
					<td height="30" colspan="2" align="center" nowrap="nowrap" bgcolor="#FFFFFF">
						<input name="submit" type="submit"  value=" 保存 " class="mmBtn">
						&nbsp;&nbsp;
						<input name="reset" type="reset"  value=" 重置"  class="mmBtn">
						&nbsp;&nbsp;
				  <input name="button" type="button" onClick="window.location='list.action'" value=" 返回 " class="mmBtn">				  </td>
				</tr>

        </table>
    </s:form>
  </body>
</html>