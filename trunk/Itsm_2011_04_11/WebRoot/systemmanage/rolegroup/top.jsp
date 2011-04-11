<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title></title>
		<link rel="StyleSheet"
			href="${pageContext.request.contextPath}/css/dtree.css"
			type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" href="../css/style.css" type="text/css" />
		<style type="text/css">
.list {
	border-left: 1px solid white;
	border-right: 1px solid #A09B8D;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

.list_btm {
	border-top: 2px outset white;
	background-color: #D4D0C7;
	text-align: right;
	padding: 3px;
	padding-top: 4px;
}

.list_par {
	border-top: 1px solid white;
	border-bottom: 2px ridge #BDB9B0;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

td {
	font-size: 11px;
	padding: 2px;
	font-family: Tahoma;
}

.mmBtn {
	padding: 0px;
	background-color: #D4D0C7;
	mborder: 1px outset white;
	font-family: Tahoma;
	font-size: 11px;
	line-height: 15px;
}

.divtitle {
	position: absolute;
	z-index: 1;
	overflow: hidden;
	width: 100%;
	height: 22px;
	padding: 0px;
}
</style>
<script type="text/javascript">
	function back(pid,pname){
		if(window.parent.document.getElementById('Layer1')){
			window.parent.document.getElementById('Layer1').style.visibility='hidden';
			if(window.parent.document.getElementById('roleGroup.pid')){
				window.parent.document.getElementById('roleGroup.pid').value=pid;
			}
			if(window.parent.document.getElementById('parentname')){
				window.parent.document.getElementById('parentname').value=pname;
			}
			if(window.parent.document.getElementById('role.roleGroup')){
				window.parent.document.getElementById('role.roleGroup').value=pid;
			}
			if(window.parent.document.getElementById('groupname')){
				window.parent.document.getElementById('groupname').value=pname;
			}
			return ;
		}
		parent.document.getElementById('listFrame').src="list.action?pid="+pid;
	}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
		<table width="100%" height="100%" border="0" cellpadding="1"
			cellspacing="1" bgcolor="#b5d6e6">
			<tr>
				<td height="100%" colspan="2" valign="top" bgcolor="#FFFFFF"
					style="padding: 0px">
					<div
						style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 100%; width: 100%; padding-right: 3px; padding-bottom: 3px;">
						<script type="text/javascript">
							d = new dTree('d');
							<s:iterator value="roleGroupList" var="roleGroup">
								d.add('${roleGroup.id}','${roleGroup.pid}','${roleGroup.name}',"javascript:back(${roleGroup.id},'${roleGroup.name}');",'','');
							</s:iterator>
							document.write(d);
							d.openAll();
						</script>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>