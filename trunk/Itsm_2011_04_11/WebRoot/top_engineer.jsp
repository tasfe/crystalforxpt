<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="theme/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="css/date.js"></script>
<script src="js/Framework.js"></script>
<script>
var clickedElement = null;
function onEleClick(ele){
	if(clickedElement){
		clickedElement.className = "left_list001";
	}
	clickedElement = ele;
	ele.className = 'left_list002';
}
function onEleMouseOver(ele){
	if(clickedElement == ele){
		return;
	}
	ele.className = 'left_list002';
}
function onEleMouseOut(ele){
	if(clickedElement == ele){
		return;
	}
	ele.className = 'left_list001';
}
Page.onLoad(function(){
	onEleClick(document.getElementById("divCode"));
});
function logout(){
	Server.sendRequest("Framework.logout",null);
	top.location = Server.ContextPath+"login.jsp";
}
function work(){
	parent.document.getElementById("mainFrame").src="engineertrace/engineer.action";
}
function rolechange(roleType){
parent.document.getElementById("mainFrame").src="roleChange.action?roleType="+roleType;
}
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/main20100521_02.gif">
  <tr>
    <td height="71"><img src="images/main20100521_01.gif" width="329" height="71" alt="" /></td>
    <td align="right" valign="bottom" style="padding-bottom:18px;">
    <table width="100" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="53%" nowrap="nowrap" valign="middle"><img src="images/main20100521_12.gif" width="7" height="6" /><img src="images/space.gif" width="5" /></td>
        <td nowrap="nowrap"><script language="JavaScript" type="text/javascript">calendar();</script><!-- <font style="color:#FFF;">尊敬的</font><font style="color:#ff8000;"><s:property value="#session.user.truename"/></font><font style="color:#FFF;">用户，欢迎您登陆系统！</font> --></td>
        <!--  <td align="right"><img src="images/space.gif" width="8" height="6" /></td>
        <td align="right"><img src="images/main0001.gif"> </td>
        <td align="right" nowrap="nowrap" background="images/main0002.gif" style="cursor:hand; color:#d1ecf2"><a href="${pageContext.request.contextPath }/admin/changePwd.jsp" target="_new" onclick="javascript:window.open('about:blank','_new','width=400,height=200,scrollbars=yes'); void(0)" style="color:#d1ecf2;">修改登录密码</a></td>
        <td align="right"><img src="images/main0003.gif"></td>--><%--
        
                 <td align="right"><img src="images/space.gif" width="8" height="18" /></td>
        <td align="right"><img src="images/main0001.gif"></td>
          <td align="right" nowrap="nowrap" background="images/main0002.gif" style="cursor:hand; color:#d1ecf2" onClick="document.getElementById('Layer').style.visibility='visible'" onmouseover="document.getElementById('Layer').style.visibility='visible'">角色切换&nbsp;</td>
        
									<div id="Layer"
										style="position: absolute; width: 20%; height: 5px; z-index: 1; visibility: hidden; padding: 1px; border: 0px outset white; background-color: white">

										<iframe frameborder="0" height="50" width="30%"
											scrolling="yes" src="../systemmanage/role/roleChange.jsp"
											style="border: 0px solid #E5E9EE;fontSize:20"></iframe>

									</div>
        
        
        --%><td align="right"><img src="images/space.gif" width="8" height="18" /></td>
        <td align="right"><img src="images/main0001.gif"></td>
        <td align="right" nowrap="nowrap" background="images/main0002.gif" style="cursor:hand; color:#d1ecf2"><a onclick="work()"  target="_top" style="color:#d1ecf2;">我的工作&nbsp;</a></td>
        <td align="right"><img src="images/main0003.gif"></td>
		<td align="right"><img src="images/space.gif" width="8" height="6" /></td>
		<td align="right"><img src="images/main0001.gif"></td>
		<td align="right" nowrap="nowrap" background="images/main0002.gif" style="cursor:hand; color:#d1ecf2"><a href="${pageContext.request.contextPath }/user/personaldata.action" target="mainFrame" style="color:#d1ecf2;">个人资料配置</a></td>
		<td align="right"><img src="images/space.gif" width="8" height="6" /></td>
		<td align="right"><img src="images/main0004.gif"></td>
		<td align="right" nowrap="nowrap" background="images/main0002.gif" style="cursor:hand; color:#d1ecf2"><a href="logout.action"  target="_top" style="color:#d1ecf2;">退出</a></td>
		<td align="right"><img src="images/main0003.gif"></td>
		<td align="right"><img src="images/space.gif" width="8" height="6" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/main20100521_21.gif">
  <tr>
    <td width="171" height="27" align="center" background="images/main20100521_18.jpg" class="con-white">欢迎用户：<s:property value="#session.user.truename"/></td>
    <td width="23"><img src="images/main20100521_19.jpg" width="23" height="27" alt="" /></td>
    <td width="600" style="font-size:12px; font-weight:bold; color:#073755">当前为<s:property value="#session.currentrole"/>控制台（控制台切换:
            <s:iterator value="#session.rolesize" id="column">
   <a href="roleChange.action?roleType=<s:property value="value.roleType"/>" target="_top">
	<span><s:property value="value.roleTypeName" /></span>
	</a>

	</s:iterator>
	）
    </td>
 	<td style="color:#073755"  nowrap="nowrap" align="right">&nbsp;
 		<iframe frameborder="0" height="27" scrolling="no" src="engineershow/top.action" ></iframe> 		
 	</td>
</tr>
</table>
</body>
</html>