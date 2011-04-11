<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/domainregister/user_list.action";
%>
<html>
	<head>
		<title>域名申请管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
			<script type='text/javascript' src='../js/zcms/zDialog.js'></script>
		<script type='text/javascript' src='../js/zcms/zDrag.js'></script>
		<script type="text/javascript">
function query(){
   document.form1.action="qurey.action";
   document.form1.method="post";
   document.form1.submit();
}

function res()
{
 document.getElementById('domainRegister.serialNumber').value="";
   document.getElementById('domainRegister.domain').value="";
      document.getElementById('domainRegister.unitsFullName').value="";
        document.getElementById('domainRegister.unitsAddress').value="";
      document.getElementById('domainRegister.unitsLeader').value="";
   document.getElementById('domainRegister.application').value="";
  
}

function del(){   
   var msg="确认删除记录吗？";
   if (confirm(msg) == true){
   return true;
   }else{
   return false;
   }   
}

function open1(id)
{
	//alert("id:"+id);
   var diag = new Dialog();
   diag.Width = 800;
	diag.Height = 1000;
   
	diag.URL = "user_show.action?domainRegisterId="+id;
	diag.show();

}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
	<s:form action="query2.action" method='post' theme="simple">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">域名申请</td>
     </tr>
    </table>
   
	  
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1"> 申请编号： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.serialNumber" name="domainRegister.serialNumber" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 申请域名： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.domain" name="domainRegister.domain" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 申请单位： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.unitsFullName" name="domainRegister.unitsFullName" style="width:100;"></s:textfield></td>
          <td bgcolor="#deebf1" align="center">
          <tags:button code="search" menu="104">
          <input type="submit" style="height: 20px" class="mmBtn" value="搜索" />
          </tags:button>
          </td>
       </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1"> 单位地址： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.unitsAddress" name="domainRegister.unitsAddress" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 单位负责人： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.unitsLeader" name="domainRegister.unitsLeader" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" >申请人：</td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="domainRegister.application" name="domainRegister.application" style="width:100;"></s:textfield></td>
          <td bgcolor="#deebf1" align="center">
          <tags:button code="reset" menu="104">
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/>
          </tags:button>
          </td>
         </tr>
        </table>
	   </s:form>
	   
	   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
				<td align="right">
				  <tags:button code="add" menu="104">
				  <input type="button" class="mmBtn" value="新建域名申请" onClick="window.location='user_saveInput.action'">				
				</tags:button>
				</td>
			</tr>
	   </table>
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td align="center" width="3%" height="20" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请编号</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请域名</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">对应IP地址</td>
			  <td align="center" width="19%" background="../images/main20100521_58.gif" class="alllisttitle">服务器存放位置</td>
			  <td align="center" width="12%" background="../images/main20100521_58.gif" class="alllisttitle">申请单位</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">单位地址</td>
			
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">处理进度</td>
			    <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">处理结果</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif"	class="alllisttitle">预览</td>
			 </tr>
			<s:iterator value="pageBean.list" var="domainRegister">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			   <td align="center" class="zczb_qua" height="20"><s:property value="id" /></td>
			  <td align="center" class="zczb_qua"><s:property value="serialNumber" /></td>
			  <td align="center" class="zczb_qua"><s:property value="domain" />.ruc.edu.cn</td>
			  <td align="center" class="zczb_qua"><s:property value="ipAddress" /></td>
			  <td align="center" class="zczb_qua"><s:property value="serverLocation" /></td>
			  <td align="center" class="zczb_qua"><s:property value="unitsFullName" /></td>
			  <td align="center" class="zczb_qua"><s:property value="unitsAddress" /></td>
			  
			  <td align="center" class="zczb_qua"><s:if test="#domainRegister.state==0">待审批</s:if>
          					<s:elseif test="#domainRegister.state==1">已审批</s:elseif></td>
			  
			  	  <td align="center" class="zczb_qua"><s:if test="#domainRegister.approvalResult==0">未通过</s:if>
          					<s:elseif test="#domainRegister.approvalResult==1">已通过</s:elseif>
			            					<s:elseif test="#domainRegister.approvalResult==2">未处理</s:elseif></td>
			  
			  
			  
			  <td align="center" class="zczb_qua">
			  <tags:button code="update" menu="104">
			  <s:if test="#domainRegister.state==0"> <img src="../images/edt.gif">
				<a href="user_updateInput.action?domainRegisterId=<s:property value="id"/>">修改</a></s:if>
				 <s:if test="#domainRegister.state==1"> <img src="../images/edt1.gif" >
				修改</s:if>
				</tags:button>
			  </td>
			  <td align="center" class="zczb_qua">
			  <tags:button code="delete" menu="104">
				  <s:if test="#domainRegister.state==0"> <img src="../images/del.gif">
		        <a href="user_delete.action?domainRegisterId=<s:property value="id"/>" onClick="javascript:return del()">删除</a>		</s:if>	
		        	 <s:if test="#domainRegister.state==1"> <img src="../images/del1.gif" >
				删除</s:if>
				</tags:button>
			  </td>
			  <td align="center" class="zczb_qua">
			  <tags:button code="detail" menu="104">
			  <img src="../images/search.png" height="15" width="15">
		        <a href="user_show.action?domainRegisterId=<s:property value="id"/>"  target="_blank">预览</a>	
			 </tags:button>
			  </td>
			 </tr>
			</s:iterator>
			   </table>
		   <table>
		 <tr> 
		  <tr> 
              <td width="100%"><div align="right">
				共<s:property value="pageBean.allRow"/> 条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页 
				<s:if test="%{pageBean.currentPage == 1}">第一页 上一页</s:if>
				<s:else>
				<a href="<%=requestURI %>?page=1">第一页</a>
				<a href="<%=requestURI %>?page=<s:property value="%{pageBean.currentPage-1}"/>">上一页</a>
				</s:else>
				<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
				<a href="<%=requestURI %>?page=<s:property value="%{pageBean.currentPage+1}"/>">下一页</a>
				<a href="<%=requestURI %>?page=<s:property value="pageBean.totalPage"/>">最后一页</a>
				</s:if>
				<s:else>下一页 最后一页</s:else>
              </div></td></tr></table>
	</body>
</html>
