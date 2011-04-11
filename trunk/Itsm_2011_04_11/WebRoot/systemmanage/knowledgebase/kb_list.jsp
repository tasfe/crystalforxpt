<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function del() {
	var msg = "确认删除吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
</script>
	</head>
	<body leftmargin="0" topmargin="4" oncontextmenu="return false"
		onselectstart="return false">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="94%">
			<tr>
				<td valign="top" id="mainright" height="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="136" valign="top" bgcolor="#FFFFFF">
								<table cellspacing=2 cellpadding=4 border=0 width="100%"
									style="font-size: 12px">
									<tr bgcolor="#FFFFFF">
										<td height="22" align="center"
											background="../images/main20100521_58.gif">
											编号
										</td>
										<td align="center" background="../images/main20100521_58.gif">
											标题
										</td>
										<td align="center" background="../images/main20100521_58.gif">
											所属类别
										</td>
										<td align="center" background="../images/main20100521_58.gif">
											查看
										</td>
										<td align="center" background="../images/main20100521_58.gif">
											修改
										</td>
										<td align="center" background="../images/main20100521_58.gif">
											删除
										</td>
									</tr>
									<s:iterator value="pageBean.list" var="knowledgeBase"
										status="st">
										<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
											onMouseOut="this.bgColor='#FFFFFF'">
											<td height="20" align="center">
												<s:property value="indexcode" />
											</td>
											<td align="center">
												<s:property value="title" />
											</td>
											<td align="center">
												<s:property value="categoryId.itemZh" />
											</td>
											<td align="center">
												<tags:button code="query" menu="24">
													<a href="#"
														onClick="window.parent.location='show.action?knowledgeBase.id=<s:property value="id"/>'">查看</a>
												</tags:button>
											</td>
											<td align="center">
												<tags:button code="update" menu="24">
													<a href="#"
														onClick="window.parent.location='updateInput.action?knowledgeBaseId=<s:property value="id"/>'">修改</a>
												</tags:button>
											</td>
											<td align="center">
												<tags:button code="delete" menu="24">
													<a
														href="delete.action?knowledgeBaseId=<s:property value="id"/>"
														onclick="del()">删除</a>
												</tags:button>
											</td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="6%">
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
						<s:if test="%{actionURI == 'search'}">
							<a
								href="search.action?page=1&knowledgeBase.categoryId.id=${knowledgeBase.categoryId.id}">第一页</a>
							<a
								href="search.action?page=<s:property value='%{pageBean.currentPage-1}'/>&knowledgeBase.categoryId.id=${knowledgeBase.categoryId.id}">上一页</a>
						</s:if>
						<s:else>
							<a
								href="query.action?page=1&knowledgeBase.title=encodeURI(${knowledgeBase.title})&type=${type}&flag=${flag}">第一页</a>
							<a
								href="query.action?page=<s:property value='%{pageBean.currentPage-1}'/>&knowledgeBase.title=encodeURI(${knowledgeBase.title})&type=${type}&flag=${flag}">上一页</a>
						</s:else>
					</s:else>
					<s:if test="%{pageBean.currentPage !=pageBean.totalPage}">
						<s:if test="%{actionURI == 'search'}">
							<a
								href="search.action?page=<s:property value="%{pageBean.currentPage+1}"/>&knowledgeBase.categoryId.id=${knowledgeBase.categoryId.id}">下一页</a>
							<a
								href="search.action?page=<s:property value='%{pageBean.totalPage}'/>&knowledgeBase.categoryId.id=${knowledgeBase.categoryId.id}">最后一页</a>
						</s:if>
						<s:else>
							<a
								href="query.action?page=<s:property value='%{pageBean.currentPage+1}'/>&knowledgeBase.title=encodeURI(${knowledgeBase.title})&type=${type}&flag=${flag}">下一页</a>
							<a
								href="query.action?page=<s:property value='%{pageBean.totalPage}'/>&knowledgeBase.title=encodeURI(${knowledgeBase.title})&type=${type}&flag=${flag}">最后一页</a>
						</s:else>
					</s:if>
					<s:else> 下一页 最后一页</s:else>
				</td>
			</tr>
		</table>
	</body>
</html>