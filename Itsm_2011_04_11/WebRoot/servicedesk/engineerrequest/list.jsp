<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>知识库检索</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" language="javascript">
		function nextPage(num) {
			document.getElementById("pagepage").value=num;
			document.formpage.submit();
		}
		function detail(id) {
			//	alert(window.opener.document.getElementById('serviceRequestSolution').value);
				document.getElementById("solution").src="user_show.action?knowledgeBaseId="+id;
			}
		
	</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; overflow: hidden; padding: 2px; border: 1px inset" >
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" id="mainright" height="99%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
						<tr>
							<td height="32" bgcolor="#deebf1"> &nbsp;
								<img src="../images/modpass.gif" align="absMiddle"> &nbsp;
								<b>知识库检索</b>
							</td>
						</tr>
						<tr>
							<td valign="top">
								<table width="100%" height="100%" border="0" cellpadding="2" cellspacing="0" style="border-top: 1px solid #C9D1DD">
					              <tr> 
					                <td width="20%" height="100%" rowspan="2" valign="top" bgcolor="#F8F8FA" id="solutionsm" style="padding-right: 2px" > 
					                  <table width="300" height="100%" border="0" cellpadding="0" cellspacing="1" class="titleBg">
										<tr> 
										  <td width="100%" height="99%" valign="top" bgcolor="#FFFFFF"> 
					                        <table width="100%" border="0" cellspacing="2" cellpadding="0" height="100%">
					                          <tr>
					                            <td height="100%" valign="top" width="100%"> 
					                              <div style="position: absolute; overflow-x: auto; overflow-y: scroll; height: 100%; width: 100%; padding: 10px"> 
					                                <table width="100%" border=0 cellpadding=4 cellspacing=0 bgcolor="#FFFFFF">
					                                  	<s:iterator value="pageBean.list" var="knowledgeBase" status="st">
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
					                  <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
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
			<tr>
				<td valign="top" id="mainright" height="12">
					<table cellspacing=0 cellpadding=0 border=0 width="100%">
						<tr>
							<td align="right" colspan="3"><input type="button" value="后退" class="mmBtn" onclick="window.history.go(-1)">
															&nbsp;&nbsp;<input type="button" value="关闭" class="mmBtn" onclick="window.close()">
							</td>
						</tr>
						<tr>
							<td align="center">
								共
								<s:property value="pageBean.allRow" />
								条记录&nbsp;&nbsp; 共
								<s:property value="pageBean.totalPage" />
								页&nbsp;&nbsp; 当前第
								<s:property value="pageBean.currentPage" />
								页&nbsp;&nbsp; 每页
								<s:property value="pageBean.pageSize" />
								行&nbsp;&nbsp;
							</td>
							<td align="right">
								<s:if test="%{pageBean.currentPage==1}">第一页 上一页</s:if>
								<s:else>
									<a href="#" onClick="nextPage(1)">第一页</a>
									<a href="#" onClick="nextPage(${pageBean.currentPage-1})">上一页</a>

								</s:else>
								<s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
									<a href="#" onClick="nextPage(${pageBean.currentPage+1})">下一页</a>
									<a href="#" onClick="nextPage(${pageBean.totalPage})">最后一页</a>
								</s:if>
								<s:else>下一页 最后一页</s:else>
							</td>
							<td>
								<s:form id="formpage" name="formpage" action="%{actionURI}"
									namespace="/knowledgebase" method="post" theme="simple">
									<s:hidden id="pagepage" name="page" value="1"></s:hidden>
									<s:hidden id="knowledgeBase.title" name="knowledgeBase.title"></s:hidden>
									<s:hidden id="type" name="type"></s:hidden>
									<s:hidden id="flag" name="flag"></s:hidden>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

