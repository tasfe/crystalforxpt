<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>list</title>
		<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>

		<table cellspacing=0 cellpadding=0 border=0 width="100%">

			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					个人通讯录
				</td>
			</tr>
		</table>

		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px; top: 21px;">

		  <table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">

						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#b5d6e6">
							<s:form action="queryPersonalContact" method="post" theme="simple"
								namespace="/AddressBookManage">
								<tr>
									<td align="center" width="4%" nowrap bgcolor="#deebf1">
										姓&nbsp;&nbsp;&nbsp;&nbsp;名：
									</td>
									<td width="4%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input type="text" name="username" style="width: 100px;" />
									</td>
									<td align="center" width="4%" nowrap bgcolor="#deebf1">
								    公&nbsp;司&nbsp;名&nbsp;称&nbsp;：</td>
									<td width="4%" bgcolor="#FFFFFF" style="padding-right: 10px">
										<input type="text" name="companyName" style="width: 100px;" />
									</td>
									<td align="center" width="5%" nowrap bgcolor="#deebf1">
										<input type="submit" value="查询" class=mmBtn name="submit" style="height: 20px" />
									</td>
								</tr>
							</s:form>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">

					    <tr>
								<td height="20"
									style="font-size: 12px; color: #333333; font-weight: bold;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
								    <td height="10" style="font-size: 12px; color: #333333; font-weight: bold;"></td>
							      </tr>
								  <tr>
								    <td height="20"
									style="font-size: 12px; color: #333333; font-weight: bold;"><table cellspacing="0" cellpadding="0" border="0" width="100%">
								      <tr>
								        <td width="100%" height="30" style="padding-top: 10px"><img src="../images/main20100521dot04.gif"><b>个人通讯录列表: </b>&nbsp;</td>
								        <td width="39%" align="right"><tags:button code="add" menu="31">
<table align="right" width="100" border="0" cellpadding="0"
cellspacing="0" background="../images/addnew002.gif">
<tr style="cursor: hand;">
<td>
<img src="../images/addnew001.gif"></td>
<td onClick="javascript:window.location.href='../AddressBookManage/newAddressBook.action'">新添通讯录</td><td align="right">
<img src="../images/addnew003.gif"></td></tr></table>
</tags:button>
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
								        <th width="5%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 姓&nbsp;&nbsp;&nbsp;&nbsp;名 </th>
								        <th width="14%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 公&nbsp;司&nbsp;名&nbsp;称 </th>
								        <th width="7%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 电子邮件 </th>
								        <th width="6%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 手&nbsp;&nbsp;&nbsp;&nbsp;机 </th>
								        <th width="6%" height="22" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 办公电话 </th>
								        <th width="6%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 传&nbsp;&nbsp;&nbsp;&nbsp;真 </th>
								        <th width="9%" nowrap="nowrap"
															background="../images/main20100521_58.gif"
															class="alllisttitle"> 公司网址 </th>
						
							          </tr>
								      <%--<s:iterator value="#request.listPersonalContact" id="u">
											--%>
										<s:iterator value="pageBean.list" id="u">
											<tr bgcolor="#ffffff" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'"> 
								          	<td align="center" class="zczb_qua"><s:a href="detailPersonalContact.action?id=%{#u.id}"><s:property value="#u.username"/> 
								            </s:a></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.companyName"/></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.email"/></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.phone"/></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.officePhone"/></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.fax"/></td> 
								          <td align="center" class="zczb_qua"><s:property value="#u.website"/></td> 
							            </tr>
							         </s:iterator>
							        </table></td>
							      </tr>
								  <tr>
								    <td height="30" colspan="2" align="right"><input name="button" type="button" value="后退"
							onclick="javascript:window.location.href='../AddressBookManage/listPersonalContact.action'"
							class="mmBtn" /></td>
							      </tr>
							    </table></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<s:form name="form" method="post"
				action="/AddressBookManage/queryPersonalContact.action" theme="simple">
				<s:hidden id="page" name="page"></s:hidden>
			</s:form>
			<jsp:include page="/common/page.jsp" />
	
		</div>

	</body>
</html>
