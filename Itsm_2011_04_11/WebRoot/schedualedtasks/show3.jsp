<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>计划任务详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/theme/custo.css">
		<link href="../../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<script language="JavaScript">
</script>


	<body leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					计划任务详情
				</td>
			</tr>
		</table>
		<div
			style="position: absolute; overflow-x: scroll; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border="0" align="center" cellpadding="5"
				cellspacing="1">
				<tr>
					<td valign="top" id="mainright">
						<table width="99%" border="0" align="center" cellpadding="2"
							cellspacing="1">
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
											<td height="12" style="padding-top: 8px" nowrap>
											</td>

											<td align=center nowrap style="padding-top: 8px; padding-bottom: 0px">
												<s:if test="actionURI.equals('show1')">
												<input type="button" class="mmBtn" value="关闭" onClick="window.close();">
												</s:if>
												<s:else><input type="button" onClick="history.go(-1)" value="后退" class=mmBtn name="button"></s:else>
											</td>
										</tr>

									</table>
									<a name="SetAction"></a>									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>