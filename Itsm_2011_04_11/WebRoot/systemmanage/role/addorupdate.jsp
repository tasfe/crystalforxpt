<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.combanc.*"%>
<html>
<head>
<title>角色管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function clear(){
		document.getElementById('role.roleGroup').value='0';
		document.getElementById('groupname').value='';
	}
	function setprivilege(obj){
		var names="<s:property value="roleTableNames"/>";
		var pris="";
		if(names){
			var name=names.split('~');
			if(name && name.length>0){
				for(var i=0;i<name.length;i++){
					var compent=document.getElementsByName(name[i]);
					if(compent && compent.length>0){
						for(var j=0;j<compent.length;j++){
							if(compent[j].checked){
								pris+=compent[j].value;
								pris+='~';
							}
						}
					}
				}
				
			}
		}
		document.getElementById('roleTablePrivi').value=pris;
	}
	function init(){
		var pris="<s:property value="roleTablePrivi"/>";
		if(pris){
			var priSplit=pris.split('~');
			if(priSplit && priSplit.length>0){
				for(var i=0;i<priSplit.length;i++){
					if(document.getElementById(priSplit[i])){
						document.getElementById(priSplit[i]).checked=true;
					}
				}
			}
		}
		var actionURI='${actionURI}';
		if(actionURI=='update'){
			var id='${role.id}';
			if(id==2||id==3||id==4){
				document.getElementById('role.roleType').disabled=true;
			}
		}
	}
	function check(){
		if(!document.getElementById('role.name').value){
			alert('角色名称不能为空!');
			return false;
		}
	}
</script>
</head>
<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"	style="padding: 7px; cursor: default; overflow:auto" onContextMenu="return false" onmousedown="document.getElementById('Layer1').style.visibility='hidden'">
			<s:form action="%{actionURI}.action" method="post" theme="simple">
             <s:hidden id="role.id" name="role.id"></s:hidden> 
             <s:hidden id="role.userFor" name="role.useFor" value="1"></s:hidden> 
             <s:hidden id="roleTablePrivi" name="roleTablePrivi"></s:hidden>
			<s:if test="actionURI=='save'">
			    <input id="role.type" name="role.type" type="hidden" value="0"></input>
			</s:if>
			<s:else>	    	
				<s:hidden id="role.type" name="role.type"></s:hidden>
			</s:else>
													<table width="80%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
														<tr>
															<td width="15%"  align="center" nowrap bgcolor="#deebf1">角色名称<span style = 'color : red; vertical-align   :middle'>*</span>:</td>
															<td width="35%" bgcolor="#FFFFFF"><s:textfield id="role.name" name="role.name"></s:textfield></td>
															<td  width="15%" align="center" nowrap bgcolor="#deebf1">角色类别:</td>
														   <td width="35%" bgcolor="#FFFFFF"><s:select id="role.roleType" list="#{1:'工程师',2:'经理',3:'用户',4:'管理员'}"  label="role.roleType" listKey="key" listValue="value"   value="role.roleType" name="role.roleType"></s:select>
                                                           </td>
													  </tr>
														 <tr>
															<td width="15%" height="25" align="center" nowrap bgcolor="#deebf1">角色组别:</td>
															<!--  
															<td width="89%" bgcolor="#FFFFFF">
																 <s:select id="role.useFor" list="#{1:'固定角色：总是包含所有选中人员',2:'浮动角色：根据申请人所在的地理位置判断',3:'手动角色：由上一环节操作人临时选择	'}"  label="role.useFor" listKey="key" listValue="value"  headerKey="0" value="role.useFor" name="role.useFor">
																</s:select> 
						                                        </td>
						                                    -->
						                                        <td width="55%" bgcolor="#FFFFFF" colspan="3">
																	<input type="hidden" name="role.roleGroup" id="role.roleGroup" value="<s:property value="role.roleGroup" />">
																	&nbsp;<input type="text" name="groupname" id="groupname" onClick="document.getElementById('Layer1').style.visibility='visible'" readonly value="<s:property value="groupname" />" style="width:50%;">
																	<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"></br>
																	<div id="Layer1"  style="position:absolute; width: 50%; height:20px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../roleGroup/top.action" style="border: 1px solid #E5E9EE;"></iframe>
																	</div>
																</td>
														</tr>
														<tr>
															<td width="15%" align="center" valign="top" nowrap bgcolor="#deebf1">
																成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员:</td>
															<td width="85%" valign="top" bgcolor="#FFFFFF" style="padding: 0px; padding-bottom:10px;" colspan="3">
																<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
																	<tr>
																		<td height="80" align="left" bgcolor="white" style="padding-left:2px"> <textarea id="showUsernames" name="showUsernames" cols="88" rows="20"	style="height: 50px;  font-family: Courier New; border:1px solid #b5d6e6"><s:property value="namesList"/></textarea></td>
											                            <td><span style="display: none;"><textarea name="memberIds" id="memberIds"><s:property value="idsList"/></textarea></span></td>
																	    <td><span style="display: none;"> <textarea name="idsList"><s:property value="idsList"/></textarea></span></td>
													                    
																	</tr>
															  </table><iframe frameborder="0" height="220" width="100%" scrolling="auto" name="l1" id="l1" src="../user/nuserselect.action" style="padding-left:2px;"></iframe></td>
														</tr>
														<tr>
															<td width="15%" align="center" valign="top" nowrap bgcolor="#deebf1">
																相关列表查询方式:</td>
															<td width="85%" valign="top" bgcolor="#FFFFFF" style="padding: 5px;" colspan="3">
																<table width="100%" border=0 align="left" cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
																	<tr bgcolor="#deebf1">
																			<td width="25%" align="center" nowrap >列表名称</td>
																			<td width='25%' align="center" nowrap>列表描述</td>
																			<td width='50%' align="center" nowrap>查询方式</td>
																	</tr>
																	<s:iterator value="roleTables" var="roleTable">
																		<tr bgcolor="#FFFFFF">
																			<td align="center" nowrap style="color: #333333"><s:property value="chName"></s:property></td>
																			<td align="center" nowrap style="color: #333333"><s:property value="description"></s:property></td>
																			<td  align="center" nowrap style="color: #333333">
																				<input id="0_${id}" type="radio" name="<s:property value="id"/>" value="0_${id}" onclick="setprivilege(this);"  />&nbsp;仅个人
																				<input id="1_${id}" type="radio" name="<s:property value="id"/>" value="1_${id}" onclick="setprivilege(this);"  />&nbsp;面向所在部门
																				<input id="2_${id}" type="radio" name="<s:property value="id"/>" value="2_${id}" onclick="setprivilege(this);"  />&nbsp;面向所有
																			</td>
																		</tr>
																	</s:iterator>
																</table>
															</td>
														</tr>
			  </table>
			  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
														<tr>
														  <td height="30" colspan="2" align="center" nowrap bgcolor="#FFFFFF">
															  <input type="submit" class="mmBtn" value="保存"	style="width: auto" onClick="javascript:return check()">
															  &nbsp;
														  <input type="button" class="mmBtn" value="返回"	onClick="window.location='list.action'"	style="width: auto"></td>
														</tr>
</table>

			</s:form>
	</body>
</html>

