<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/ipaddress/user_list.action";
%>
<html>
	<head>
		<title>IP地址申请管理</title>
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
   document.getElementById('ipAddress.serialNumber').value="";
   document.getElementById('ipAddress.ipUseRoom').value="";
      document.getElementById('ipAddress.unitsFullName').value="";
   document.getElementById('ipAddress.phone').value="";
      document.getElementById('ipAddress.unitsLeader').value="";
   document.getElementById('ipAddress.application').value="";
  
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
	diag.show();

}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
	<s:form action="/ipaddress/user_list.action" method='post' theme="simple">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">IP地址申请</td>
     </tr>
    </table>
   
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1"> 申请表编号： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.serialNumber" name="ipAddress.serialNumber" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 使用位置（房间）： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.ipUseRoom" name="ipAddress.ipUseRoom" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 单位全名： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.unitsFullName" name="ipAddress.unitsFullName" style="width:100;"></s:textfield></td>
          <td bgcolor="#deebf1" align="center">
          <tags:button code="search" menu="101">
          <input type="submit" style="height: 20px" class="mmBtn" value="搜索" />
          </tags:button>
          </td>
       </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1"> 联系电话： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.phone" name="ipAddress.phone" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 单位负责人： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.unitsLeader" name="ipAddress.unitsLeader" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" >申请人：</td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.application" name="ipAddress.application" style="width:100;"></s:textfield></td>
          <td bgcolor="#deebf1" align="center">
          <tags:button code="reset" menu="101">
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/>
          </tags:button>
          </td>
         </tr>
        </table>
	   </s:form>
	   
	   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
				<td align="right">
				<tags:button code="add" menu="101">
				  <input type="button" class="mmBtn" value="新建IP申请" onClick="window.location='user_saveInput.action'">				
				</tags:button>
				</td>
			</tr>
	        <tr>
	          <td align="right" height="6"></td>
         </tr>
	   </table>
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td align="center" width="3%" height="20" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请编号</td>
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">IPv4数量</td>
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">IPv6数量</td>
			  <td align="center" width="15%" background="../images/main20100521_58.gif" class="alllisttitle">IP地址使用位置</td>
			  <td align="center" width="12%" background="../images/main20100521_58.gif" class="alllisttitle">申请单位</td>
			  <td align="center" width="15%" background="../images/main20100521_58.gif" class="alllisttitle">申请IP用途</td>
			
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">处理进度</td>
			    <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">处理结果</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif"	class="alllisttitle">预览</td>
			 </tr>
			<s:iterator value="pageBean.list" var="ipAddress">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			   <td align="center" class="zczb_qua" height="20"><s:property value="id" /></td>
			  <td align="center" class="zczb_qua"><s:property value="serialNumber" /></td>
			  <td align="center" class="zczb_qua"><s:property value="applyIpv4Count" /></td>
			  <td align="center" class="zczb_qua"><s:property value="applyIpv6Count" /></td>
			  <td align="center" class="zczb_qua"><s:property value="ipUseRoom" /></td>
			  <td align="center" class="zczb_qua"><s:property value="unitsFullName" /></td>
			  <td align="center" class="zczb_qua"><s:property value="applyIpPurpose" /></td>
			  <td align="center" class="zczb_qua"><s:if test="#ipAddress.state==0">待审批</s:if>
          					<s:elseif test="#ipAddress.state==1">已审批</s:elseif></td>
			  
			  	  <td align="center" class="zczb_qua"><s:if test="#ipAddress.approvalResult==0">未通过</s:if>
          					<s:elseif test="#ipAddress.approvalResult==1">已通过</s:elseif>
			            					<s:elseif test="#ipAddress.approvalResult==2">未处理</s:elseif></td>
			  
			  
			  
			  <td align="center" class="zczb_qua">
			    <tags:button code="update" menu="101">
			  <s:if test="#ipAddress.state==0"> <img src="../images/edt.gif">
				<a href="user_updateInput.action?ipAddressId=<s:property value="id"/>">修改</a></s:if>
				 <s:if test="#ipAddress.state==1"> <img src="../images/edt1.gif" >
				修改</s:if>
				</tags:button>
			  </td>
			  <td align="center" class="zczb_qua">
			    <tags:button code="delete" menu="101">
				  <s:if test="#ipAddress.state==0"> <img src="../images/del.gif">
		        <a href="user_delete.action?ipAddressId=<s:property value="id"/>" onClick="javascript:return del()">删除</a>		</s:if>	
		        	 <s:if test="#ipAddress.state==1"> <img src="../images/del1.gif" >
				删除</s:if>
				</tags:button>
			  </td>
			  
			  <!-- 
			  <td align="center" class="zczb_qua">
			    <tags:button code="detail" menu="101">
			  <img src="../images/search.png" height="15" width="15">
					<a href="javascript:void(0)" onClick="open1(<s:property value='id'/>);">查看</a>
					</tags:button>
			  </td>
			   -->
			   
			     <td align="center" class="zczb_qua">
			    <tags:button code="detail" menu="101">
				<img src="../images/search.png" height="15" width="15">
		        <a href="user_show.action?ipAddressId=<s:property value="id"/>"  target="_blank">预览</a>	
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
