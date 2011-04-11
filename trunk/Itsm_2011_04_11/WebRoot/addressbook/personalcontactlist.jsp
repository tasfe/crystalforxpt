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
		
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}else {  
        	return false;   
   		}   
	}

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
	 
	function change()
	{
		var all = document.getElementById("all");
		var objs = document.getElementsByName("checkbox");
		
		if(all.checked)
		{
			for(var i = 0 ; i < objs.length; ++i)
			{
				objs[i].checked = true;
			}
		}
		else
		{
			for(var i = 0 ; i < objs.length; ++i)
			{
				objs[i].checked = false;
			}
		}
	}

	function deleteAddressBook()
	{
		var objs = document.getElementsByName("checkbox");
		var flag = false;
		var result = "";
		
		for(var i = 0 ; i < objs.length; ++i)
		{
			if(objs[i].checked)
			{
				var v = objs[i].value;
				result += v + "@";
				flag = true;
			}
		}
		
		if(!flag)
		{
			alert("尚未选择任何条目！");
			return;
		}
		else if(confirm("确定要删除么？"))
		{
			window.location.href = "deletePersonalContact.action?addressBookIds=" + result
		}
	}
	 
</script>
	</head>
	<body onLoad="init()" style="overflow: hidden;">

		<table cellspacing=0 cellpadding=0 border=0 width="100%">

			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="100%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					个人通讯录
				</td>
			</tr>

		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="queryPersonalContact" method="post"
								theme="simple" namespace="/AddressBookManage">
								<tr>
									<td align="right" width="4%" nowrap bgcolor="#deebf1">
										姓&nbsp;&nbsp;&nbsp;&nbsp;名：
									</td>
									<td width="4%" bgcolor="#EBF4F5" style="padding-right: 10px">
										<input type="text" name="username" style="width: 100px;" />
									</td>
									<td align="right" width="4%" nowrap bgcolor="#deebf1">
										公&nbsp;司&nbsp;名&nbsp;称&nbsp;:
									</td>
									<td width="4%" bgcolor="#EBF4F5" style="padding-right: 10px">
										<input type="text" name="companyName" style="width: 100px;" />
									</td>
									<tags:button code="search" menu="32">
										<td align="center" width="5%" nowrap bgcolor="#deebf1">
											<input type="submit" value="查询" class=mmBtn name="submit"
												style="height: 18px" />
										</td>
									</tags:button>
								</tr>
							</s:form>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="10">
								</td>
							</tr>
							<tr>
								<td height="20"
									style="font-size: 12px; color: #333333; font-weight: bold;">
									<table cellspacing="0" cellpadding="0" border="0" width="100%">

										<tr>
											<td width="60%" height="30" style="padding-top: 10px">
												<img src="../images/main20100521dot04.gif">
												<b>个人通讯录列表:</b>&nbsp;
											</td>
											<td width="39%" align="right">
												<tags:button code="add" menu="32">
												  <table align="right" width="100" border="0" cellpadding="0"
cellspacing="0" background="../images/addnew002.gif">
												    <tr style="cursor: hand;">
												      <td><img src="../images/addnew001.gif"></td>
												      <td onClick="javascript:window.location.href='../AddressBookManage/personalAddressBook.action'">新添通讯录</td>
												      <td align="right"><img src="../images/addnew003.gif"></td>
											        </tr>
											      </table>
												</tags:button>
											</td>
										</tr>
										<tr>
											<td valign="top" background="../img/Separator.gif" height="5"
												colspan="2"></td>
										</tr>
										<tr>
											<td valign="right" colspan="2" height="5"></td>
										</tr>
										<tr>
											<td width="99%" height="30" colspan="2">
												<table width="100%" border="0" align="center"
													cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<th width="6%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															姓&nbsp;&nbsp;名
														</th>
														<th width="14%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															公司名称
														</th>
														<th width="6%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															电子邮件
														</th>
														<th width="5%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															手&nbsp;&nbsp;机
														</th>
														<th width="5%" height="22" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															办公电话
														</th>
														<th width="5%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															传&nbsp;&nbsp;真
														</th>
														<th width="6%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															公司网址
														</th>
														<%--
														<th width="9%" nowrap
															background="../images/main20100521_58.gif"
															class="alllisttitle">
															个人网址
														</th>

														--%>
														<tags:button code="update" menu="32">
															<th width="6%" nowrap
																background="../images/main20100521_58.gif"
																class="alllisttitle">
																修&nbsp;&nbsp;改
															</th>
														</tags:button>
														<tags:button code="delete" menu="32">
															<th width="3%" nowrap align="right"
																background="../images/main20100521_58.gif">
																<input type="checkbox" id="all" onClick="change()" />
																<%--删&nbsp;&nbsp;&nbsp;&nbsp;除--%>
																<input type="button" value="删除"
																	onclick="deleteAddressBook()" style="height: 18px;">
															</th>
														</tags:button>
													</tr>
													<s:iterator value="pageBean.list" id="u">
														<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
															onMouseOut="this.bgColor='#FFFFFF'">
															<td align="center" class="zczb_qua">
																<s:a href="detailPersonalContact.action?id=%{#u.id}">
																	<s:property value="#u.username" />
																</s:a>
															</td>
															<%--
															<td align="center">
																<s:property value="#u.departName" />
															</td>
															--%>
															<td align="center" class="zczb_qua">
																<s:property value="#u.companyName" />
															</td>
															<td align="center" class="zczb_qua">
																<s:property value="#u.email" />
															</td>
															<td align="center" class="zczb_qua">
																<s:property value="#u.phone" />
															</td>
															<td align="center" class="zczb_qua">
																<s:property value="#u.officePhone" />
															</td>
															<%--
															<td align="center">
																<s:property value="#u.homephone" />
															</td>
															--%>
															<td align="center" class="zczb_qua">
																<s:property value="#u.fax" />
															</td>
															<td align="center" class="zczb_qua">
																<s:property value="#u.website" />
															</td>
															<%--
															<td align="center">
																<s:property value="#u.personalWebsite" />
															</td>


															--%>
															<tags:button code="update" menu="32">
																<td align="center" class="zczb_qua">
																	<img src="../images/edt.gif">
																	<tags:button code="update" menu="32">
																		<s:a href="updatePPersonalContact.action?id=%{#u.id}">修改</s:a>
																	</tags:button>
																</td>
															</tags:button>
															<tags:button code="delete" menu="32">
																<td align="center">
																	<input type="checkbox" name="checkbox"
																		value="<s:property value="id"/>">
																	<tags:button code="delete" menu="32">
																		<s:a
																			href="deletePersonalContact.action?addressBookIds=%{#u.id}"
																			onclick="javascript:return del()">
																			<img src='../img/delete.gif' border=0 width=18
																				height=18>
																		</s:a>
																	</tags:button>
																</td>
															</tags:button>
														</tr>
													</s:iterator>
												</table>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>



			<s:form name="form" method="post"
				action="/AddressBookManage/listPersonalContact.action"
				theme="simple">
				<s:hidden id="page" name="page" value="1"></s:hidden>
				<s:hidden id="pageSize" name="pageSize"></s:hidden>
			</s:form>
			<jsp:include page="/common/page.jsp" />
		</div>
	</body>
</html>
