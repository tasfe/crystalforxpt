<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
 	String path = request.getContextPath();
%>
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>康邦管理信息化系统</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/Main.js" type="text/javascript"></script>
</HEAD>
<BODY style="min-width:1003px">
<form id="mainForm">
</form>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="1" colspan="2" valign="top">
			<iframe width="100%" height="121" name="topit" id="topit" src="top_user.jsp" scrolling="no" frameborder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td valign="top" width="175" height="100%" bgcolor="#c6e5f9" style="border-right:#09C0FE solid 1px;">
			<table width="175" border="0" cellspacing="0" cellpadding="12" height="100%" style="background:url(./image/menu_bg.jpg);background-repeat:repeat-y;">
				<tr id="request" name="request">
					<td onSelectStart="return false" style="padding-top: 0">
						<ul class="menu_box" id="menu_box">
							<tags:button code="browse" menu="50">
					          <li class="menu01" onMouseOut="this.className='menu01'" onMouseOver="this.className='menu01_current'"><a href="userrequest/init.action" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="51">
					          <li class="menu02" onMouseOut="this.className='menu02'" onMouseOver="this.className='menu02_current'"><a href="usertrace/tracelist.action" target="main"></a></li>
					        </tags:button>
					        <tags:button code="browse" menu="52"> 
					          <li class="menu03" onMouseOut="this.className='menu03'" onMouseOver="this.className='menu03_current'"><a href="userrequesthistory/tracelist.action?state=5" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="53"> 
					          <li class="menu07" onMouseOut="this.className='menu07'" onMouseOver="this.className='menu07_current'"><a href="knowledgebase/user.action" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="54"> 
					          <li class="menu08" onMouseOut="this.className='menu08'" onMouseOver="this.className='menu08_current'"><a href="/bbs/index.jsp?name=<s:property value="#session.user.username"/>&pwd=<s:property value="#session.user.password"/>" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="55"> 
					          <li class="menu04" onMouseOut="this.className='menu04'" onMouseOver="this.className='menu04_current'"><a href="SystemNotice/findNowNotices.action" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="57"> 
					          <li class="menu05" onMouseOut="this.className='menu05'" onMouseOver="this.className='menu05_current'"><a href="user/personaldata.action" target="main"></a></li>
					        </tags:button> 
					        <tags:button code="browse" menu="56">
					          <li class="menu06" onMouseOut="this.className='menu06'" onMouseOver="this.className='menu06_current'"><a href="javascript:logout()"></a></li>
					   		</tags:button>
					    </ul>
					</td>
				</tr>

				<tr>
					<td height="99%" onSelectStart="return false" style="background:url(./image/menu_bg.jpg);background-repeat:repeat-y;">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="99%" valign="top" bgcolor="#FFFFFF" style="padding: 5px; background-repeat: repeat-x; padding-top: 0px; padding-bottom: 0px; padding-right: 2px" background="img/main.jpg">
			<iframe height="100%" width="100%" frameborder="0" scrolling="yes" src="userrequest/init.action" name="main" id="main"></iframe><%--
			<iframe height="100%" width="100%" frameborder="0" scrolling="yes" src="/bbs/index.jsp?name=<s:property value="#session.user.username"/>&pwd=<s:property value="#session.user.password"/>" name="main" id="main"></iframe>
		--%></td>
	</tr>
	<tr>
		<td height="1" colspan="2" valign="top">
			<iframe width="100%" height="28" src="bottom_user.jsp" scrolling="no" frameborder="0"></iframe>
		</td>
	</tr>
</table>
<script type="text/javascript">
	function logout(){
		var mainForm=document.getElementById("mainForm");
		mainForm.action="./logout.action";
		mainForm.submit();
	}
</script>
</BODY>
</HTML>