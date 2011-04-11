<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>知识库管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js">
</script>
		<script type='text/javascript' src='../dwr/util.js'>
</script>
		<script type='text/javascript'
			src='../dwr/interface/ServiceCategoryDAO.js'>
</script>
		<script type='text/javascript' src='../dwr/engine.js'>
</script>
		<script type="text/javascript">
function init() { //取出类别
	ServiceCategoryDAO.findAll(callbackserviceCategory);
}

function callbackserviceCategory(data) { //显示出分类
	dwr.util.removeAllOptions("service_category");
	dwr.util.addOptions("service_category", [ {
		id : '-1',
		name : '--请选择--'
	} ], "id", "name");
	dwr.util.addOptions("service_category", data, "id", "itemZh");
	var a = "<s:property value="
serviceRequest.serviceCategory.itemZh" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("service_category",a);  
   			} 
		}	

	function loadTop() {
		var topframe = parent.frames.topFrame;
		topframe.location = "top.action";
	}
	
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  

</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="init();">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="20" colspan="5"
					background="../images/main20100521_58.gif">
					<table width="50%" border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td width="2%" align="center"
								background="../images/main20100521_58.gif"
								style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
								<img src="../images/modpass.gif" width="16" height="16">
							</td>
							<td width="98%" style="color: #333333; font-weight: bold;">
								知识库管理（Incident Solution）
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" colspan="7" align="center" class="alllisttitle">
					<table width="112%" border="0" cellspacing="1">
						<s:form action="query" method='post' theme="simple" name="form">
							<s:hidden id="page" name="page"></s:hidden>
							<s:hidden id="pageSize" name="pageSize"></s:hidden>
							<tr>
								<td width="13%" height="22">
									知识库查看
								</td>
							</tr>
							<tr>
								<td height="8">
									知识库编号：
								</td>
								<td width="20%">
									<s:textfield id="knowledgeBase.id" name="knowledgeBase.id"
										cssStyle="width: 60%"></s:textfield>
								</td>
								<td width="8%">
									知识库标题：
								</td>
								<td width="20%">
									<s:textfield id="knowledgeBase.title"
										name="knowledgeBase.title" cssStyle="width: 60%"></s:textfield>
								</td>
							</tr>
							<tr>
								<td width="8%">
									所包含内容：
								</td>
								<td width="40%">
									<s:textfield id="knowledgeBase.symptom"
										name="knowledgeBase.symptom" cssStyle="width: 60%"></s:textfield>
								</td>
								<td width="8%">
									所属类别：
								</td>
								<td width="40%">
									<select id="service_category"
										name="knowledgeBase.categoryId.id" style="width: 60%"></select>
								</td>
							</tr>
							<tr>
								<td width="80%" colspan="7" align="middle">
									<tags:button code="select" menu="24">
										<input type="submit" style="height: 20px" class="mmBtn"
											value="搜索" />
									</tags:button>
								</td>
							</tr>
						</s:form>
					</table>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="30" align="right">
								<table width="60" border="0" cellpadding="0" cellspacing="0"
									background="../images/addnew002.gif">
									<tags:button code="add" menu="24">
										<tr onClick="window.location='addInput.action'"
											style="cursor: hand;">
											<td>
												<img src="../images/addnew001.gif">
											</td>
											<td nowrap>
												添加知识库
											</td>
											<td align="right">
												<img src="../images/addnew003.gif">
											</td>
										</tr>
									</tags:button>
								</table>
						</tr>
					</table>
				</td>
			</tr>

			<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					序号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					标题
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					内容
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					所属类别
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					工程师
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					查看
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					修改
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					删除
				</td>
			</tr>
			<s:iterator value="pageBean.list" var="knowledgeBase" status="st">
				<tr bgcolor="#FFFFFF" onMouseOver=this.bgColor=
					'#e3f0f7';onMouseOut=this.bgColor='#FFFFFF';>
					<td align="center" width="5%">
						<s:property value="id" />
					</td>
					<td align="center" width="5%">
						<s:property value="title" />
					</td>
					<td align="center" width="15%">
						<s:property value="symptom" />
					</td>
					<td align="center" width="5%">
						<s:property value="categoryId.itemZh" />
					</td>
					<td align="center" width="5%">
						<s:property value="engineerId.truename" />
					</td>
					<td align="center" width="5%">
		
						<tags:button code="query" menu="24">
							<a href="show.action?knowledgeBaseId=<s:property value="id"/>">查看</a>
						</tags:button>
					</td>
					<td align="center" width="5%">
						<tags:button code="update" menu="24">
							<a
								href="updateInput.action?knowledgeBaseId=<s:property value="id"/>">修改</a>
						</tags:button>
					</td>
					<td align="center" width="5%" onclick="del()">
						<tags:button code="delete" menu="24">
							<a href="delete.action?knowledgeBaseId=<s:property value="id"/>">删除</a>
						</tags:button>
					</td>
				</tr>
			</s:iterator>
		</table>
		<jsp:include page="/common/page.jsp" />
	</body>
</html>
