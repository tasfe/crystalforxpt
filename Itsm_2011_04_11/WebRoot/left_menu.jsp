<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
<%@page import="com.combanc.itsm.action.ModuleAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="StyleSheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="./js/dtree.js"></script>
<style>
html{
overflow: scroll;
overflow-x: hidden;
overflow-x: auto !important;
}
</style>
<script language="javascript">
function trshowandoff(aa) {//显示和隐藏表格的行；
      if (document.getElementById(aa).style.display == "") {
          document.getElementById(aa).style.display = "none";
      } else {
          document.getElementById(aa).style.display = "";
      }
  }
  
function trsecshowandoff(bb,cc) {//显示和隐藏表格的行；
      if (document.getElementById(bb).style.display == "") {
          document.getElementById(bb).style.display = "none";
		 document.getElementById(cc).src="images/main20100521dot03.gif"
      } else {
          document.getElementById(bb).style.display = "";
		 document.getElementById(cc).src="images/main20100521dot02.gif"
      }
  }
</script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/main20100521_26.gif);
}
</style>
</head>

<body style="overflow-x:hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="177" valign="top" background="images/main20100521_26.gif">
    	<table width="177" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="23" background="images/main20100521_23.jpg">&nbsp;</td>
	      </tr>
	      <tr>
	        <td><img src="images/main20100521_26.gif" width="177" height="3" alt="" /></td>
	      </tr>
      		<tr>
        		<td align="center">
        			<table width="151" border="0" cellpadding="0" cellspacing="0" background="images/main20100521_43.gif">
        				<tr>
            				<td>
            					<table width="151" border="0" cellspacing="0" cellpadding="0">
              						<s:iterator value="menuMap" var="map">
        				 			<tr style="display:">
                						<td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                						<td width="81" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(${map.key.id})">${map.key.name}</td>
                						<td width="41" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              			 			</tr>
              			 			 <tr id="${map.key.id}" style="display:">
            							<td align="center" colspan="3">
            								<table width="147" border="0" cellspacing="0" cellpadding="0">
              									<tr><td><img src="images/space.gif" width="1" height="1" /></td></tr>
              									<s:iterator value="#map.value" var="submap">
              									<s:if test="#submap.value.size>0">
        				 						<tr>
                									<td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" style="cursor:hand;" onclick="javascript:trsecshowandoff('mytr_${submap.key.id}','closeorpadding_${submap.key.id}')"><img id="closeorpadding_${submap.key.id}" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;${submap.key.name}</td>
              									</tr>			             						
			             						<tr id="mytr_${submap.key.id}" style="display:none">
                									<td height="22" align="left" background="images/main20100521_61.gif">
                										<table width="147" border="0" cellspacing="0" cellpadding="0">
                											<s:iterator value="#submap.value" var="submenu">
			             									<tr>
			              										<td  colspan="3" height="22" align="left" bgcolor="images/main20100521_45.gif" class="lefttreesecond">&nbsp;<a href="<%=path%>/${url}" target="mainFrame">${name}</a></td>
        				 									</tr>
        				 									</s:iterator>
                  										</table>
                  									</td>
                  								</tr>
                  								</s:if>
                  								<s:else>
                  								<tr>
			                						<td colspan="3" width="147" height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="<%=path%>/${submap.key.url}" target="mainFrame">${submap.key.name}</a></td>
			             						</tr>
                  								</s:else>
			             						</s:iterator>
              								</table>
              							</td>
              						</tr>
              						  <tr>
							            <td bgcolor="#228BBB" colspan="3"><img src="images/space.gif" width="1" height="1" /></td>
							          </tr>
							          <tr>
							            <td bgcolor="#FFFFFF" colspan="3"><img src="images/space.gif" width="100" height="3" /></td>
							          </tr>
              			 			</s:iterator>
            					</table>
            				</td>
          				</tr>
        			
        			
        			</table>
        		</td>
        	</tr>
		</table>
	  </td>
	</tr>
</table>
</body>
</html>
