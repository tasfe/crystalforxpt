<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*"%>
<html>
	<head>
		<title>工程师分组</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript">
function getLocationDAO() { //取出类别
	//LocationDAO.findAll(callbackorg);
}
function callbackorg(data) { //显示出类别
	dwr.util.removeAllOptions("Location");
	dwr.util.addOptions("Location", [ "--请选择--" ]);
	dwr.util.addOptions("Location", data, "id", "name");
	var a = "<s:property value="
engineer.location.id" />";
   if (typeof(a) != "undefined") {   
   dwr.util.setValue("Location",a);  
   }  
}
function check()
{
var name1=document.getElementById("engineer.name");
if(name1=="")
{
alert("工作组名称不允许为空！");
return false;
}
document.form.submit();
}
</script>

	</head>

	<body onLoad="getLocationDAO() " leftmargin="0" topmargin="0"
		marginwidth="0" marginheight="0"
		style="padding: 7px; border: 0px; cursor: default;">
		<s:form action="%{actionURI}" method="post" name="form" theme="simple"
			namespace="/engineer">
			<s:hidden id="engineer.id" name="engineer.id"></s:hidden>
			<s:hidden id="teamlead" name="teamlead"></s:hidden>
			<s:if test="actionURI=='save'">
				<input id="engineer.type" name="engineer.type" type="hidden"
					value="1"></input>
			</s:if>
			<s:else>
				<s:hidden id="engineer.type" name="engineer.type"></s:hidden>
			</s:else>

			<table width="80%" height="50%" border="0" align="center"
				cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
				<tr>
					<td height="30" width="10%" align="center" nowrap bgcolor="#deebf1"
						class="td-left" style="padding-right: 20px">
						工作组名称<span style = 'color : red; vertical-align   :middle'>*</span>:
					</td>
					<td width="25%" bgcolor="#FFFFFF" class="td-right">
						<s:textfield id="engineer.name" name="engineer.name"></s:textfield>
					</td>
					<td width="10%" align="center" nowrap bgcolor="#deebf1"
						class="td-left" style="padding-right: 20px">
						应用范围:
					</td>
					<td width="25%" bgcolor="#FFFFFF" class="td-left">
						<select id="engineer.useFor" name="engineer.useFor">

							<option value="1" selected id="category1">
								全局：面向所有地域
							</option>
							<option value="2" id="category2">
								仅对：某区域
							</option>
							<option value="3" id="category3">
								隐藏：将这个工作组隐藏
							</option>

						</select>
						<%--	
                       <script type="text/javascript">
                              if(<s:property value="engineer.useFor" />!=null)
							  document.getElementById('engineer.useFor').value="<s:property value="engineer.useFor" />";                                   
						     </script>
																					  --%>
					</td>
					<td width="10%" bgcolor="#FFFFFF" class="td-left">负责人</td>
					<td width="20%" bgcolor="#FFFFFF" class="td-left"><input type="text" name="leadUsernames" id="leadUsernames" value="" readonly></td>
				</tr>
				<tr>
					<td width="11%" height="80" rowspan="2" align="center" valign="top"
						nowrap bgcolor="#deebf1" class="td-left"
						style="padding-right: 20px">
						成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员:
					</td>
					<td style="padding: 0px" bgcolor="white" colspan="5">
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
	
							<tr>
							
								<td bgcolor="white" class="td-right"
									style="padding-left: 2px; padding-top: 5px;" width="99%">
									<textarea name="showUsernames" cols="80" rows="3"
										style="height: 50px; font-family: Courier New ;wrap : hard" readonly></textarea>
								</td>
								<td valign="bottom" width="1%">
									&nbsp;&nbsp;
									<img src="../img/char.gif" style="cursor: hand"
										onMouseDown="document.getElementById('showUsernames').value='';
										document.getElementById('memberIds').value='';
										document.getElementById('idsList').value='';
										window.frames('11').location.reload();"
										title="清空">
									&nbsp;
								</td>
							</tr>

							<tr>
								<td>
									<span style="display: none;"> <textarea name="memberIds" id="memberIds"></textarea>
									</span>
								</td>


								<td>
									<span style="display: none;"> <textarea name="idsList" id="idsList">${idsList}</textarea> </span>
								</td>
								<td>
									<span style="display: none;"> <textarea name="teamleaderId">${teamleaderId}</textarea> </span>
								</td>
							</tr>
						</table>
						<iframe frameborder="0" height="300" width="90%" scrolling="auto" id="11"
							name="l1" id="l1" src="user.action" style="padding-left: 2px;"></iframe>
					</td>
				</tr>
				<%--	<tr>
												<td height="40%" bgcolor="white" style="padding: 0px; border-bottom: 1px solid #7D7D7F" colspan="3">												</td>
											</tr>
										<tr>
												<td width="11%" rowspan="2" align="center" valign="top" nowrap bgcolor="#deebf1" class="td-left" style="padding-right: 20px">权限列表:</td>
												<td style="padding: 0px" bgcolor="white" colspan="3"><span style="display: none;">
																			<span style="display: none;"><textarea name="authorityIds" ></textarea></span>
																		   <textarea name="authorityIdsList"><s:property value="authorityIdsList"/></textarea></span></td>
											</tr>
											<tr>
												<td bgcolor="white" style="padding-left: 2px;" colspan="3"><iframe frameborder="0" height="160" width="100%" scrolling="no" name="iframe" id="l1" src="authority.action"></iframe></td>
											</tr>
											<tr>
												<td width="11%" align="center" nowrap bgcolor="#deebf1" class="td-left" style="padding-right: 20px">工作组级别:</td>
												<td colspan="3" bgcolor="#FFFFFF" class="td-left" style="padding: 1px">
													<table width="60" border="0" cellspacing="3" cellpadding="0">
														<tr>
															<td>
																<input type="checkbox" value="未定的" style="border: 0px; width: 14px" name="field4" id="field4">															</td>
															<td style="padding-right: 20px; padding-top: 1px" nowrap>未定的</td>
															<td>
																<input type="checkbox" value="一级(线" style="border: 0px; width: 14px"  name="field4" id="field4">															</td>
															<td style="padding-right: 20px; padding-top: 1px" nowrap>一级(线)</td>
															<td>
																<input type="checkbox" value="二级(线)" style="border: 0px; width: 14px"  name="field4" id="field4">															</td>
															<td style="padding-right: 20px; padding-top: 1px" nowrap>二级(线)</td>
															<td>
																<input type="checkbox" value="三级(线)" style="border: 0px; width: 14px"  name="field4" id="field4">															</td>
															<td style="padding-top: 1px" nowrap>三级(线)</td>
														</tr>
													</table>											  </td>
											</tr>
											--%>

			</table>
			<table width="80%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td height="30" colspan="4" align="center" bgcolor="#FFFFFF"
						style="padding-top: 5px">
						<input type="button" class="mmBtn" value="保存" style="width: auto"
							onclick="javascript:check();">
						&nbsp;
						<input type="button" class="mmBtn" value="返回"
							onClick="window.location='list.action'" style="width: auto">
					</td>
			  </tr>
		  </table>

		</s:form>

		<script type="text/javascript">
function a() {
<%--document.frames.iframe.saveAuthorityIds();--%>
document.form.submit();

}
</script>
	</body>
</html>

