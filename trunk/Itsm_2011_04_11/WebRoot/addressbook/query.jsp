<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>list</title>
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">

	function init() {
		DepartmentDAO.findAll(callbackdepartment);
	}

	function callbackdepartment(data) {
		dwr.util.removeAllOptions("department");
		dwr.util.addOptions("department", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("department", data, "id", "name");
	} 
	
	function res(){
			
		document.getElementById('username').value="";
		document.getElementById('department').value="";
		
	}

	</script>
	</head>
	<body onLoad="init()" style="overflow:hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">

			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					我的通讯录
				</td>
			</tr>

		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">

		  <table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
					  <s:form action="queryDepOrName" method="post" theme="simple"
								namespace="/AddressBookManage">
					    <tr>
					      <td align="center" width="15%" nowrap bgcolor="#deebf1"> 姓&nbsp;&nbsp;&nbsp;&nbsp;名： </td>
					      <td width="21%" bgcolor="#FFFFFF" style="padding-right: 10px"><input type="text" name="username" style="width: 120px;" /></td>
					      <td align="center" width="14%" nowrap bgcolor="#deebf1">选择部门：</td>
					      <td width="27%" bgcolor="#FFFFFF" style="padding-right: 10px"><select id="department" name="departmentId" style="width: 50%">
					        </select></td>
					      <td width="12%" align="center" bgcolor="#deebf1" style="padding-right: 10px"><input type="submit" value="查询" class=mmBtn name="submit" style="height: 20px" /></td>
					      <td width="12%" align="center" bgcolor="#deebf1" style="padding-right: 10px"><input type="button" value="重置" class=mmBtn name="button" style="height: 20px" onClick="res();"/></td>
				        </tr>
				      </s:form>
				  </table>
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
					    <tr>
					      <td height="10" style="font-size: 12px; color: #333333; font-weight: bold;"></td>
				        </tr>
					    <tr>
					      <td height="20"
									style="font-size: 12px; color: #333333; font-weight: bold;"><table cellspacing="0" cellpadding="0" border="0" width="100%">
					        <tr>
					          <td width="100%" height="30" style="padding-top: 10px"><img src="../images/main20100521dot04.gif"><b>所有的通讯录:</b>&nbsp;</td>
					          <td width="39%" align="right"><span style="padding-top: 4px"><tags:button code="add" menu="31">
<table align="right" width="100" border="0" cellpadding="0"
cellspacing="0" background="../images/addnew002.gif">
<tr style="cursor: hand;">
<td>
<img src="../images/addnew001.gif"></td>
<td onClick="javascript:window.location.href='../AddressBookManage/newAddressBook.action'">新添通讯录</td><td align="right">
<img src="../images/addnew003.gif"></td></tr></table>
</tags:button>
					 
					          </span>
		                    </table></td>
				        </tr>
					    <tr>
					      <td valign="top" background="../img/Separator.gif" colspan="2" height="5px"></td>
				        </tr>
					    <tr>
					      <td valign="top" colspan="2" height="6"></td>
				        </tr>
					    <tr>
					      <td valign="top" colspan="2"><table width="100%" border="0" align="center"
													cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
					        <tr>
					          <th width="3%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 姓&nbsp;&nbsp;&nbsp;&nbsp;名 </th>
					          <th width="4%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 部&nbsp;&nbsp;&nbsp;&nbsp;门 </th>
					          <th width="7%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 电子邮件 </th>
					          <th width="3%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 手&nbsp;&nbsp;&nbsp;&nbsp;机 </th>
					          <th width="3%" height="22" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 办公电话 </th>
					          <th width="3%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 传&nbsp;&nbsp;&nbsp;&nbsp;真 </th>
					          <th width="5%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> Email </th>
				            </tr>
					        <s:iterator value="pageBean.list" id="u">
<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"onMouseOut="this.bgColor='#FFFFFF'">
 <td align="center"  class="zczb_qua"><s:property value="#u.username" /></td>
        <td align="center" class="zczb_qua"><s:property value="department.name" /></td>
        <td align="center"class="zczb_qua"><s:property value="email" /></td>
        <td align="center" class="zczb_qua"><s:property value="phone" /></td>
        <td align="center" class="zczb_qua"><s:property value="officePhone" /></td>
        <td align="center" class="zczb_qua"><s:property value="fax" /></td>
<td align="center" class="zczb_qua"><s:property value="email" /></td>
				              </tr>
				            </s:iterator>
				          </table></td>
				        </tr>
					    <tr>
					   <td height="30" colspan="2" align="right">
<input name="button" type="button" value="后退" onClick="javascript:window.location.href='../AddressBookManage/myContact.action'" class="mmBtn" />
					      </td>
				        </tr>
			      </table></td>
				</tr>
			</table>
		
			<s:form name="form" method="post"
				action="/AddressBookManage/queryDepOrName.action" theme="simple">
				<s:hidden id="page" name="page"></s:hidden>
			</s:form>
			<jsp:include page="/common/page.jsp" />
	
	</div>
	</body>
</html>
