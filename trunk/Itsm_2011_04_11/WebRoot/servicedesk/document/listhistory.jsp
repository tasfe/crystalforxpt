<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>文档列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../js/zcms/zDialog.js"></script>
		<script type="text/javascript" src="../js/zcms/zDrag.js"></script>
		<script type="text/javascript">
function loadTop() {
	clo();
	var topframe = parent.frames.topFrame;
	topframe.location = "top.action";
}
function newD() {
	window
			.open("add.action?pid=${pid}", "backup",
					"height=600, width=800, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no");
}

function del() {
	var msgs = "确认删除记录吗？";
	if (confirm(msgs) == true) {
		return true;
	} else {
		return false;
	}
}
   function clo()
{    
     var ag ='<s:property value="msg" escape="false" />';
	 if(ag)
	 {	
		alert("提示："+ag);
	 }
 }
		</script>

	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="loadTop()">
		<s:form name="form" theme="simple">
			<s:hidden id="page" name="page"></s:hidden>
			<s:hidden id="state" name="state"></s:hidden>
			<s:hidden id="pageSize" name="pageSize"></s:hidden>
		</s:form>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30" align="right"/>
					
			</tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					序号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					文档标题
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					作者
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					版本号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					查看
				</td>
				
			
				
			</tr>
			<s:iterator value="pageBean.list" var="dc" status="stat">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
					onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center" class="zczb_qua">
						${stat.index+1}
					</td>
					<td align="center" class="zczb_qua">
						${title}
					</td>
					<td align="center" class="zczb_qua">
						${userName}
					</td>
					<td align="center" class="zczb_qua">
						${version}
					</td>
					<td align="center">
						<img src="../images/edt.gif">
						<a href="#"
							onclick="window.open('showhistory.action?dc.num=${num}&dc.version=${version}&page=${page}','backup','height=600,width=800,location=no,status=no,toolbar=no,resizable=no,scrollbars=yes');">查看</a>
					</td>
					<%-- <td align="center"><img src="../images/edt.gif"><a href="index_update.action?document_id=${dc.id}&pid=${pid}&page=${page}">修改</a></td>--%>
					
					
				
				</tr>
			</s:iterator>
		</table>
		<jsp:include page="/common/page.jsp" />
	</body>
</html>
