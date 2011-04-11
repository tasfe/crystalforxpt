<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>查看事件</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type='text/javascript' src='../dwr/util.js'></script>
 <script type='text/javascript' src='../dwr/interface/LocationDAO.js'></script>
<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
</head>
<script language="JavaScript">
function getDAO(){ //取出类别
   LocationDAO.findAll(callbacklocation);
}
function callbacklocation(data){  //显示出地理位置
   dwr.util.removeAllOptions("location");
   dwr.util.addOptions("location",{'-1':'--请选择--'});
   dwr.util.addOptions("location",data,"id","name");   
}
function callbackroleteam(data){  //显示出工程师分组
   dwr.util.removeAllOptions("engineerteam");
   document.getElementById("ITer").src="about:blank";
   dwr.util.removeAllOptions("engineerteam");
   dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
   dwr.util.addOptions("engineerteam",data,"id","name");   
}
function getInfo1(){
	var location = document.getElementById("location").value;
	if(location!=-1) {
		RoleteamDAO.findEByLocationID(location,callbackroleteam);		
	}else{
		RoleteamDAO.findAll(callbackroleteam);		
	}
}
function getInfo2(){
	var roleteam = document.getElementById("engineerteam").value;
	document.getElementById("ITer").src="../role/query.action?role.id="+roleteam;
}
</script>


<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">事件详细情况</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: scroll; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
	<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1">
		<tr>
			<td valign="top" id="mainright">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
					<Script LANGUAGE="JavaScript">
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
					</Script>
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
										<s:if test="actionURI.equals('intervene')"><input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)"></s:if>	
										<s:elseif test="flag==1"><input type="button" id="button7" class="mmBtn" value="关闭" onclick="window.close()"></s:elseif>				
										<s:else><input type="button" id="button7" class="mmBtn" value="返回" onclick="window.history.go(-1)"></s:else>
								  </td>
								</tr>
								
							</table>
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
