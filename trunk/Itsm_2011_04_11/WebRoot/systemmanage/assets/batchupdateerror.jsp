<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function del(){   
   var msg="确认删除记录吗？";
   if (confirm(msg) == true){
   return true;
   }else{
   return false;
   }   
}

function back()
{
document.location.href="batch.action";
}

</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" >
<s:if test="%{batchlist.size!=0}">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="2%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					批量入库结果</td>
			</tr>
		</table>				
        	
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td  height="22" align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产编号</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产描述</td>
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">入库日期</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
			   <td align="center" width="20%" background="../images/main20100521_58.gif" class="alllisttitle">错误信息</td>
			 </tr>
			<s:iterator value="batchlist" var="assetsBase">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			  <td height="19" align="center" class="zczb_qua"><s:property value="codeId" /></td>
			  <td align="center" class="zczb_qua"><s:property value="m_des" /></td>
			  <td align="center" class="zczb_qua"><s:date name='inDate' format='yyyy-MM-dd'/></td>
			  
			 

			  <td align="center" class="zczb_qua">
				<img src="../images/del.gif">
				<a href="delete.action?assetsId=<s:property value="code"/>&message=1" onClick="javascript:return del()">删除</a>
			  </td>
			  <td align="center" class="zczb_qua">
				<font class="zc_add">此资产编码在系统中已经存在，请删除此信息。</font>
			  </td>

			 </tr>
			</s:iterator>
		   </table>
		</s:if>   
		<s:if test="%{batchlist.size==0}">
		  <div align="center"><br>批量入库完成。</div>
		</s:if>
        <div align="center"><br/>
          
          <input type="button" value=" 返回列表 "  class="mmBtn" onClick="back();">
       </div>
</body>
</html>
