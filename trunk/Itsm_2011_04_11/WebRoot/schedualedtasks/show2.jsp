<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>计划任务详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript">
	function fin() {
		var sol=document.getElementById('solu').value;
		if(sol=='') { alert("请输入意见和建议！"); }
		else{
			if(confirm("您确定要提交吗？")) 
		  	document.getElementById('formFin').submit();
		}
	}
</script>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">计划任务详情</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: scroll; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1">
		<tr>
			<td valign="top" id="mainright">
				<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1">
					<script LANGUAGE="JavaScript">
					function Table(id){
						var oldid = document.getElementById("tmp").value;
						if (id!=oldid) {
							document.getElementById("left_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_left.gif";
							document.getElementById("right_"+oldid).src="${pageContext.request.contextPath }/img/tab_un_right.gif";
							document.getElementById(oldid).background="${pageContext.request.contextPath }/img/tab_un.gif";
							document.getElementById(oldid).style.paddingTop="5px";
							document.getElementById(oldid+"_table").style.display="none";
							document.getElementById("left_"+id).src="${pageContext.request.contextPath }/img/tab_ch_left.gif";
							document.getElementById("right_"+id).src="${pageContext.request.contextPath }/img/tab_ch_right.gif";
							document.getElementById(id).background="${pageContext.request.contextPath }/img/tab_ch.gif";
							document.getElementById(id).style.paddingTop="3px";
							document.getElementById(id+"_table").style.display="";
							document.getElementById("tmp").value=id;
						}
					}
					</script>
					<tr>
						<td>
							<jsp:include page="common.jsp"/> 
						</td>
					</tr>					
					<tr>
						<td>
							<table cellspacing=0 cellpadding=0 border=0 width="100%">
								<tr>
									<td height="12" style="padding-top: 8px" nowrap> <a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
									
									<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
										<input type="button" id="button2" class="mmBtn" value="复审" onclick="ShowApprove('Fin')">							
										<input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)">
										
								  </td>
								</tr>
								
							</table>
							<a name="SetAction"></a>
							<script language="JavaScript">
							function ShowApprove(type) {
								document.getElementById("divFin").style.display = "none";
								document.getElementById("div"+type).style.display = "";
								document.location.href = "#SetAction"
							}
							</script>
							<div id="divFin" style="display: none; padding-top: 8px">
								<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#b5d6e6">
									<tr>
										<td height="22" background="../../images/main20100521_58.gif" class="alllisttitle" style="padding: 0px">&nbsp;<img src="../../img/jiedian.gif" width="10" height="9" align="absMiddle">&nbsp;填写任务复审结果</td>
									</tr>
									<s:form name="formFin" action="review" method="post" theme="simple">
										<input type=hidden name="schedualedTaskDetail.id" value="<s:property value='schedualedTaskDetail.id'></s:property>">										
										<tr>
											<td style="padding: 10px" bgcolor="#FFFFFF">
												<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
													<tr>
														<td width="14%" bgcolor="#EBF4F5">任务号:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">&nbsp;&nbsp;<s:property value="schedualedTaskDetail.taskCode"></s:property></td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">满意程度:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<select name="schedualedTaskDetail.sarLvl" style="height: 20px; width: 30%">
								  								<option value="10">10%</option>
								  								<option value="20">20%</option>
								  								<option value="30">30%</option>
								  								<option value="40">40%</option>
								  								<option value="50">50%</option>
								  								<option value="60">60%</option>
								  								<option value="70">70%</option>
								  								<option value="80">80%</option>
								  								<option value="90">90%</option>
								  								<option value="100" selected>100%</option>
															</select>
														</td>
													</tr>
													<tr>
														<td width="14%" bgcolor="#EBF4F5">意见和建议:</td>
														<td width="86%" colspan=3 bgcolor="#FFFFFF">
															<textarea id="solu" name="schedualedTaskDetail.advice" rows=10 style="width: 100%"></textarea>
													  </td>
													</tr>
											  </table>
											</td>
										</tr>
									</s:form>
							  </table>
								<table cellspacing=0 cellpadding=0 border=0 width="100%">
									<tr>
										<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
											<input type="button" class="mmBtn" value="提交" onclick="fin()">
									  </td>
									</tr>
								</table>
							</div>
						</td>
					</tr>					
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
<Script Language="JavaScript" src="${pageContext.request.contextPath}/js/mmBtn.js"></Script>
