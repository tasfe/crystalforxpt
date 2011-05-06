<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

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
					公共通讯录
				</td>
			</tr>
		</table>
		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
		  <table width="100%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
				<tr>
				  <td valign="top" bgcolor="#FFFFFF">

					<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="queryDepOrName" method="post" theme="simple"
								namespace="/AddressBookManage">
								<tr>
									<td align="right" width="14%" nowrap bgcolor="#deebf1">姓&nbsp;&nbsp;名：</td>
									<td width="22%" bgcolor="#ffffff" style="padding-right: 10px">
										<input type="text" name="username" id="username" style="width: 120px; height:18px;" />
									</td>
									<td align="right" width="17%" nowrap bgcolor="#deebf1">选择部门：</td>
									<td width="26%" bgcolor="#ffffff" style="padding-right: 10px">
										<select id="department" name="departmentId" style="width: 50%; height:18px;"></select>
								 	</td>
									<tags:button code="search" menu="31"><td width="12%" align="center" bgcolor="#deebf1" style="padding-right: 10px"><input type="submit" value="查询" class=mmBtn name="submit" style="height: 20px" /></td>
									<td width="12%" align="center" bgcolor="#deebf1" style="padding-right: 10px"><input type="button" value="重置" class=mmBtn name="button" style="height: 20px" onclick="res();" /></td>
									
									</tags:button>
								</tr>
							</s:form>
					  </table>
					  <table width="100%" border="0" cellspacing="0" cellpadding="0">
					   		 <tr>
							<td height="10" style="font-size: 12px; color: #333333; font-weight: bold;"></td>
						  </tr>
						  <tr>
							  <td height="20" style="font-size: 12px; color: #333333; font-weight: bold;">
								  <table cellspacing="0" cellpadding="0" border="0" width="100%">

									  <tr>
										  <td width="100%" height="30" style="padding-top: 10px">
											  <img src="../images/main20100521dot04.gif"><b>所有的通讯录:</b>&nbsp;
									    </td>
									    <td width="39%" align="right">
											<tags:button code="add" menu="31">
											<table align="right" width="100" border="0" cellpadding="0"
											cellspacing="0" background="../images/addnew002.gif">
											<tr style="cursor: hand;">
											<td>
											<img src="../images/addnew001.gif"></td>
											<td onClick="javascript:window.location.href='../AddressBookManage/newAddressBook.action'">新添通讯录</td><td align="right">
											<img src="../images/addnew003.gif"></td></tr></table>
											</tags:button>
											  </table>                                            
 											</td>
									  </tr>
									  <tr>
										  <td valign="top" background="../img/Separator.gif" colspan="2" height="5px"></td>
									  </tr>
									  <tr>
 										<td valign="top" colspan="2" height="6"></td>
					  					  </tr>
										<tr>
										  <td valign="top" colspan="2">
										  <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
										<tr>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 姓&nbsp;&nbsp;名 </th>
									<th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 电子邮件 </th>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 手&nbsp;&nbsp;&nbsp;&nbsp;机 </th>
									<th width="3%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 办公电话 </th>
									<th width="3%" nowrap background="../images/main20100521_58.gif" class="alllisttitle"> 传&nbsp;&nbsp;&nbsp;&nbsp;真 </th>
								    <th width="5%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">Email </th>
									<tags:button code="update" menu="31">
									<th width="3%" nowrap background="../images/main20100521_58.gif"class="alllisttitle"> 修&nbsp;&nbsp;改 </th>
									</tags:button>
									<tags:button code="delete" menu="31">
									<th width="2%" nowrap  align="right" background="../images/main20100521_58.gif"><input type="checkbox" id="all" onClick="change()" /><%--删&nbsp;&nbsp;&nbsp;&nbsp;除--%><input type="button" value="删除" onclick="deleteAddressBook()" style="height:18px;"></th>
									 </tags:button>
									  </tr>
									<s:iterator value="pageBean.list" id="u">
										      <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										        <td align="center" class="zczb_qua"><s:a href="detailAddressBook.action?id=%{#u.id}">
								        		<s:property value="#u.username" /></s:a></td>
										        <td align="center" class="zczb_qua"><s:property value="#u.email" /></td>
										        <td align="center" class="zczb_qua"><s:property value="#u.phone" /></td>
										        <td align="center" class="zczb_qua"><s:property value="#u.officePhone" /></td>
										        <td align="center" class="zczb_qua"><s:property value="#u.fax" /></td>
										        <td align="center" class="zczb_qua"><s:property value="#u.email" /></td>
										        <tags:button code="update" menu="31">
										          <td align="center" class="zczb_qua"><img src="../images/edt.gif">
										            <s:a href="updateP.action?id=%{#u.id}">修改</s:a></td>
									            </tags:button>
										        <tags:button code="delete" menu="31">
										          <td align="center" class="zczb_qua"><input type="checkbox" name="checkbox" value="<s:property value="id"/>">
										            <s:a href="delete.action?addressBookIds=%{#u.id}" onclick="javascript:return del()"> <img src='../img/delete.gif' border=0 width=18 height=18></s:a></td>
									            </tags:button>
									          </tr>
									        </s:iterator>
									      </table></td>
					 					   </tr>
									  <tr>
										<td colspan="2" height="30">
										<tags:button code="export" menu="31">
										<table align="right" width="100" border="0" cellpadding="0"
										cellspacing="0" background="../images/addnew002.gif">
										<tr style="cursor: hand;">
										<td><img src="../images/addnew001.gif"></td>
										<td onClick="javascript:window.location.href='../AddressBookManage/export.action'">通讯录导出</td><td align="right"><img src="../images/addnew003.gif"></td>
										</tr></table>
										</tags:button>
										</td>
										</tr>
									</table>
								</td>
							</tr>
							
						</table>
						</td>
					</tr>
				</table>
		  			<s:form name="form" method="post" action="/AddressBookManage/myContact.action" theme="simple">
						<s:hidden id="page" name="page" value="1"></s:hidden>
							<s:hidden id="pageSize" name="pageSize"></s:hidden>
					</s:form>
				<jsp:include page="/common/page.jsp" />
			</div>
	</body>
</html>
