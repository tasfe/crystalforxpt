<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../css/style.css" rel="stylesheet" type="text/css">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;">
			  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
				<s:form action="" method='post' theme="simple">
				<tr> 
				  <td bgcolor="white" valign="top"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">用户全名:</td>
                        <td width="35%" bgcolor="#FFFFFF"><input type="text" name="truename" value="<s:property value="username"/>"  style="width:100%;"></td>
                        <td width="15%" align="center" bgcolor="#deebf1" title="The password will not be changeed if keep empty..">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF" title="The password will not be changeed if keep empty.."><input type="Password" name="Password" value="<s:property value="password"/>" style="width:100%" maxlength="16"></td>
                      </tr>
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮:</td>
                        <td width="35%" bgcolor="#FFFFFF"><input type="text" name="Email" value="<s:property value="email"/>" style="width: 100%"></td>
                        <td width="15%" align="center" bgcolor="#deebf1">用户类别:</td>
                        <td width="35%" bgcolor="#FFFFFF"> 
                          <select name="Category" style="width: 80%">
                            <option value="User" selected>普通用户</option>
                            <option value="System Admin" >管理员</option>
                            <option value="Approver" >主管经理</option>
                            <option value="IT Engineer" >技术工程师</option>
                          </select>                        </td>
                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><input type="text" name="cellphone" value="<s:property value="cellphone"/>" style="width:100%"></td>
                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">部门经理:</td>
                        <td bgcolor="#FFFFFF"><input type="text" name="ShiYR" onKeyUp="document.getElementById('Layer').style.visibility='visible';window.User.location='../user/?NowAction=users&Key='+this.value" value="wanglin" onClick="document.getElementById('Layer').style.visibility='visible';window.User.location='../user/?NowAction=users&Key='+this.value"><br>
                        <div id="Layer" style="position:absolute; width:200; height:20px; z-index:1; visibility: hidden; padding-top: 3px"><iframe id="User" name="User" frameborder="0" height="100" width="100%" scrolling="yes" src="../user/?NowAction=users" ></iframe></div></td>
                        <td width="15%" align="center" bgcolor="#deebf1">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</td>
                        <td width="35%" bgcolor="#FFFFFF"><input type="text" name="Tel" value="<s:property value="phone"/>" style="width: 100%"></td>
                      </tr>
                      <tr style="display: none">
                        <td align="center" bgcolor="#deebf1">所在公司:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><input type="text" name="Subcompany" value="|242," style="width:100%"></td>
                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务:</td>
                        <td bgcolor="#FFFFFF">
						<select name="Team1" style="height: 20px; width: 48%">
                          <option>角色</option>
                        </select>
						<select name="Team2" style="height: 20px; width: 48%">
                          <option>工作组</option>
                        </select></td>
                        <td align="center" bgcolor="#deebf1">优先级:</td>
                        <td bgcolor="#FFFFFF">
						  <select name="PRI" style="height: 20px; width: 80%">
						   
							<option value="1" selected>低</option>
							  
							<option value="3">高</option>
					    </select>						</td>
                      </tr>
                      <tr>
                        <td height="50%" align="center" valign="top" bgcolor="#deebf1">所属部门:</td>
                        <td style="padding: 0px; border-bottom: 1px solid buttonface" colspan="3"><iframe name="testwin" id="testwin" height="100%" width="100%" frameborder="0" scrolling="yes" src=""></iframe></td>
                      </tr>
                      <tr> 
                        <td align="center" bgcolor="#deebf1">冻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结:</td>
                      	<td bgcolor="#FFFFFF">
					   <input type="checkbox" name="Remark" value="1" style="border: 0px; width: 14px">冻结用户</td>
                        <td align="center" bgcolor="#deebf1">语言:</td>
                        <td bgcolor="#FFFFFF">
                          <select name="Langua" style="width: 80%">
                            <option value="EN" >英文</option>
                            <option value="CN" selected>简体中文</option>
                        </select></td>
                      </tr>
                      <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">所在地理位置:</td>
                        <td style="padding: 0px; border-bottom: 1px solid buttonface" colspan="3"><iframe name="testwin" id="testwin" height="100%" width="100%" frameborder="0" scrolling="yes" src=""></iframe></td>
                      </tr>
					
						<tr>
						  <td align="center" valign="top" bgcolor="#deebf1">员工工号:</td>
						  
						  <td colspan="3" bgcolor="#FFFFFF">
						  <input type="text" name="Add1" value="<s:property value="id"/>" style="width: 100%">						  </td>
						</tr>
                    </table></td>
				</tr>
				<tr align="center" style="height: 25">
					<td height="30" colspan="2" align="center" nowrap="nowrap">
						<input name="submit" type="submit"  value=" 保存 " class="mmBtn">
						&nbsp;&nbsp;
						<input name="reset" type="reset"  value=" 重置 " class="mmBtn">
						&nbsp;&nbsp;
				  <input name="button" type="button" 
							onClick="window.location='list.action'" value=" 返回 " class="mmBtn"></td>
				</tr></s:form>
      </table>
  </body>
</html>