<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>相关解决方案</title>
		<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" /> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function detail(id) {
				document.getElementById("solution").src="user_show.action?knowledgeBaseId="+id;
			}
		</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset; overflow: hidden">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="100%" valign="top" id="mainright"> 
      <table width="100%" border="1" cellpadding="0" cellspacing="0" class="tb-list" height="100%">
        <tr> 
		<td height="32" bgcolor="#deebf1">&nbsp;<img src="../images/modpass.gif" align="absMiddle">&nbsp;<b>相关解决方案(事件)</b></td>		
	  </tr>
        <tr class="td-right-s"> 
          <td height="99%" colspan="2" align="center" valign="top" bgcolor="#FFFFFF"> 
            <table width="100%" height="100%" border="0" cellpadding="2" cellspacing="0" style="border-top: 1px solid #C9D1DD">
              <tr> 
                <td width="20%" height="100%" rowspan="2" valign="top" bgcolor="#F8F8FA" id="solutionsm" style="padding-right: 2px" > 
                  <table width="300" height="100%" border="0" cellpadding="0" cellspacing="1" class="titleBg">
                    <tr>
					  <td height="24" bgcolor="#deebf1" style="padding: 3px ">&nbsp;电脑故障:</td>
					</tr>
					<tr> 
					  <td width="100%" height="99%" valign="top" bgcolor="#FFFFFF"> 
                        <table width="100%" border="0" cellspacing="2" cellpadding="0" height="100%">
                          <tr>
                            <td height="100%" valign="top" width="100%"> 
                              <div style="position: absolute; overflow-x: auto; overflow-y: scroll; height: 100%; width: 100%; padding: 10px"> 
                                <table width="100%" border=0 cellpadding=4 cellspacing=0 bgcolor="#FFFFFF">
                                  	<s:iterator value="knowledgeBases" var="knowledgeBase" status="st">
            							<tr bgcolor="#FFFFFF"  style="cursor: hand"  onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'" onClick="detail(${id})">            								
            								 <td width="1%">&nbsp;<img src="../img/open.gif" align="absmiddle"></td>
            								<td width="99%" nowrap><s:property value ="title" /></td>
          								</tr>
          							</s:iterator>
                                </table>
                              </div>
							</td>
                          </tr>
                        </table>
					  </td>
					</tr>
				  </table>
                </td>
                <td width="80%" height="100%" valign="top" style="border-left: 2px solid #82A5CC; padding: 0px"> 
                  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td style="padding: 3px" height="100%"><iframe frameborder="0" height="100%" width="100%" id="solution" name="solution" scrolling="yes"></iframe></td>
                    </tr>
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
<input type="hidden" id="temp">
</body>
</html>
