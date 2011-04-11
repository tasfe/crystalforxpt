<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>多用户选择</title>
  <link href="../css/style.css" rel="stylesheet" type="text/css">
  </head>
  <script type="text/javascript">
function notshow(){
		document.getElementById('Layer2').style.visibility='hidden'
}
function showMembers() {
	var memberIds = document.getElementsByName('memberIds');
	var memberUsernames = document.getElementsByName('memberUsernames');
	var parentUsernames=parent.document.getElementById('showUsernames').innerHTML;
	var parentUserIds=parent.document.getElementById('memberIds').innerHTML;
	for (var i = 0; i < memberIds.length; i++) {
	    if (memberIds[i].checked) {
		    if(parentUserIds.indexOf(memberIds[i].value + ',')<0){
		    	parentUserIds = parentUserIds + memberIds[i].value + ',';
		    	parentUsernames=parentUsernames+memberUsernames[i].value+',';
		    }
	        memberUsernames[i].checked = true;  
		}else{
			if(parentUserIds.indexOf(memberIds[i].value + ',')>=0){
		    	parentUserIds = parentUserIds.replace(memberIds[i].value + ',',"");
		    	parentUsernames=parentUsernames.replace(memberUsernames[i].value+',',"");
		    }
        	memberUsernames[i].checked = false;
        }
	}
	parent.document.getElementById('showUsernames').innerHTML = parentUsernames;	
	parent.document.getElementById('memberIds').innerHTML = parentUserIds;
}
function changepage(page){
	document.selectForm.action="../user/nuserselect.action?page="+page;
	document.selectForm.method="post";
	document.selectForm.submit();
}

function initCheckbox() {
	var memberIds = document.getElementsByName('memberIds');
	var idsList = parent.document.getElementById('memberIds').innerHTML;
	if(!idsList){
		return ;
	}
	var ids = idsList.split(',');
	for (var i = 0; i < memberIds.length; i++) {
		var id = memberIds[i].value +'';			
		for (var j = 0; j < ids.length; j++) {		
					if (id==ids[j]) {
						memberIds[i].checked = true;
					} 
				}
			}	
	showMembers();
}
  </script>
  <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="border: 0px" scroll='no' onMouseDown="notshow();" onLoad="initCheckbox();">
	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
	<tr>
		<td valign="top" id="mainright">
			<table width="100%"  cellpadding="0" cellspacing="0" height="100%">
				<tr>
				  <td valign="top">
	<div style="position: absolute; overflow-x: hidden;  height: 100%; width: 100%; padding: 2px; padding-bottom: 20px">
							<table cellspacing=1 cellpadding=1  width="100%"  bgcolor="#deebf1">
								<tr >
									<td width="2%"></td>
									<td width="28%" align="center" nowrap >登录名</td>
									<td width='30%' align="center" nowrap>用户全名</td>
									
									<td width='30%' align="center" nowrap>部门</td>
									<td width='10%' align="center" nowrap>选择</td>
								</tr>
								<s:iterator value="pageBean.list" var="users">
									<tr bgcolor="#FFFFFF">

										<td width=2% align="center"><img src='../img/icon_user.gif' border='0' height=16></td>
									    <td width="28%" align="center" nowrap style="color: #333333"><s:property value="username" /></td>
										<td width='30%' align="center" nowrap style="color: #333333"><s:property value="truename" /></td>
									
										<td width='30%' align="center" nowrap style="color: #333333"><s:property value="department.name" /></td>
										<td width="10%" align="center" valign="middle">
											<input type="checkbox" name="memberIds"
												value="<s:property value="id"/>"
												onclick="showMembers();" onchange="showMembers();" />
											  <span style="display: none;">	
											<input type="checkbox" name="memberUsernames"
												value="<s:property value="username"/>" />
											  </span>
										</td>
									</tr>
							  </s:iterator>
							</table>
							<table border="0" align="center" cellpadding="0" class="list" cellspacing="0">
					          <tr>
					            <td height="30" align="right" class="zczb_qua">
					             <s:if test="%{pageBean.allRow!=0}">
									共<s:property value="pageBean.allRow"/> 条记录
									共<s:property value="pageBean.totalPage"/> 页
									当前第<s:property value="pageBean.currentPage"/>页
					        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
					        		<s:else>
					            		<a href="#" onClick="changepage(1);">第一页</a>
					            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage-1}"/>);">上一页</a>
					        		</s:else>
					        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
					            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage+1}"/>);">下一页</a>
					            		<a href="#" onClick="changepage(<s:property value="pageBean.totalPage"/>);">最后一页</a>
					        		</s:if>
					        		<s:else> 下一页 最后一页 </s:else>
					        	</s:if>
					            </td>
					          </tr>
        					</table>
	</div>

						
					</td>
				</tr>
				<s:form action="../user/nuserselect.action" method='post' theme="simple" id="selectForm" name="selectForm">
				<tr>
					<td align="center" height="12">
						<table width="100%"  cellspacing="0" cellpadding="0">
							<tr>
								<td width="10%">关键字：</td>
								<td width="25%"><input type="text" name="keyword" value="<s:property value="keyword"/>" /></td>
								<td width="10%">部门：</td>
								<td width="30%">
<input type="hidden" name="departmentId" id="departmentId" value="<s:property value="departmentId"/>"/>
<input type="text" name="departmentName" id="departmentName" onClick="document.getElementById('Layer2').style.visibility='visible'" readonly value="<s:property value="departmentName"/>" />
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:0px; top:-200px;">
<div id="Layer2"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div>
</div>
								</td>
								<td width="25%" height="25"><input value="搜索" type="submit" class="mmBtn" /></td>
							</tr>
							
						</table>
					</td>
				</tr>
				</s:form>
			</table>
		</td>
	</tr>
	</table>
  </body>
</html>
