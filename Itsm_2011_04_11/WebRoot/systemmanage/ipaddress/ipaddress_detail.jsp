<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>IP地址申请详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
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
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
	<s:form action="/ipaddress/query.action" method='post' theme="simple">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">IP地址申请详情</td>
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
          <td bgcolor="#deebf1" align="center"><input type="submit" style="height: 20px" class="mmBtn" value="搜索" /></td>
       </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1"> 联系电话： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.phone" name="ipAddress.phone" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 单位负责人： </td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.unitsLeader" name="ipAddress.unitsLeader" style="width:100;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" >申请人：</td>
          <td bgcolor="#FFFFFF" align="center"><s:textfield id="ipAddress.application" name="ipAddress.application" style="width:100;"></s:textfield></td>
          <td bgcolor="#deebf1" align="center"><input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/></td>
         </tr>
        </table>
	   </s:form>
	   
	   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
	        <!-- 
				<td height="30" align="left">
				  <input type="button" class="mmBtn" value="资产变更" onClick="change();">
				  <input type="button" class="mmBtn" value="资产变更查询" onClick="changequery();">
				</td>
				 -->
				<td align="right">
				  <input type="button" class="mmBtn" value="导出EXCEL" onClick="excel();">
				  <input type="button" class="mmBtn" value="新建IP申请" onClick="window.location='saveInput.action'">				
				</td>
			</tr>
	   </table>
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td align="center" width="3%" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请编号</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请IPv4数量</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">申请IPv6数量</td>
			  <td align="center" width="19%" background="../images/main20100521_58.gif" class="alllisttitle">IP地址使用位置</td>
			  <td align="center" width="12%" background="../images/main20100521_58.gif" class="alllisttitle">申请单位</td>
			  <td align="center" width="7%" background="../images/main20100521_58.gif" class="alllisttitle">技术联系人</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">联系电话</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">电子邮箱</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
			  <td align="center" width="4%" background="../images/main20100521_58.gif"	class="alllisttitle">查看</td>
			 </tr>
			<s:iterator value="pageBean.list" var="ipAddress">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			   <td align="center" class="zczb_qua"><s:property value="id" /></td>
			  <td align="center" class="zczb_qua"><s:property value="serialNumber" /></td>
			  <td align="center" class="zczb_qua"><a href="month.action?uid=<s:property value=""/>"><s:property value="applyIpv4Count" />个IP地址</a></td>
			  <td align="center" class="zczb_qua"><s:property value="applyIpv6Count" />个IP地址</td>
			  <td align="center" class="zczb_qua"><s:property value="ipUseRoom" /></td>
			  <td align="center" class="zczb_qua"><s:property value="unitsFullName" /></td>
			  <td align="center" class="zczb_qua"><s:property value="technicalContact" /></td>
			  		  <td align="center" class="zczb_qua"><s:property value="phone" /></td>
			  <td align="center" class="zczb_qua"><s:property value="email" /></td>
			  <td align="center" class="zczb_qua">
			   <img src="../images/edt.gif">
				<a href="updateInput.action?ipAddressId=<s:property value="id"/>">修改</a>
			  </td>
			  <td align="center" class="zczb_qua">
				<img src="../images/del.gif">
				<a href="delete.action?ipAddressId=<s:property value="id"/>" onClick="javascript:return del()">删除</a>
			  </td>
			  <td align="center" class="zczb_qua">
				<a href="show.action?ipAddressId=<s:property value="id"/>" >查看</a>
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
	</body>
</html>
