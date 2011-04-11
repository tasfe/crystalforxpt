<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>供应商厂商资料</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
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

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>

<body style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">供应商厂商搜索</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
	 <s:form action="query" namespace="/producer" method='post' theme="simple">
	<tr>
		<td width=2% align="center" nowrap bgcolor="#deebf1">名称:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<s:textfield id="producer.name" name="producer.name" cssStyle="width: 100%"></s:textfield>
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">类型:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<s:select list="%{#{0:'',1:'供应商', 2:'厂商',3:'二者都是'}}"  name="producer.type" />
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">级别:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<s:select list="%{#{0:'',1:'*', 2:'**',3:'***',4:'****',5:'*****'}}" name="producer.level"/>
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">联系人:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<s:textfield id="producer.persion" name="producer.persion" style="width: 100%"/>
		</td>
		<td width="2%" bgcolor="#deebf1" >
			<input type="submit" style="height:20px" class="mmBtn" value="搜索" />
		</td>
	</tr>
	</s:form>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
      		<tr onClick=window.location.href("../systemmanage/producer/add.jsp") style="cursor:hand;">
        		<td><img src="../images/addnew001.gif"></td>
        		<td nowrap>新建供应商厂商</td>
        		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">供应商厂商资料</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#b5d6e6">
        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">名称</td>                
        <td align="center" nowrap bgcolor="#deebf1">类型</td>        
        <td align="center" nowrap bgcolor="#deebf1">星级级别</td>
        <td align="center" nowrap bgcolor="#deebf1">联系人</td>
        <td align="center" nowrap bgcolor="#deebf1">联系电话</td>
        <td align="center" nowrap bgcolor="#deebf1">售后服务电话</td>        
        <td align="center" nowrap bgcolor="#deebf1">地址</td>
        <td align="center" nowrap bgcolor="#deebf1">描述</td>
        <td align="center" nowrap bgcolor="#deebf1">修改</td>
        <td align="center" nowrap bgcolor="#deebf1">删除</td>
      </tr>
      <s:iterator value="pageBean.list" var="producer">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
		<td height="19" align="center"><s:property value="name"/></td>
          <td align="center"><s:if test="#producer.type==1">供应商</s:if>
          					<s:elseif test="#producer.type==2">厂商</s:elseif>
          					<s:elseif test="#producer.type==3">二者都是</s:elseif>
          </td>
          <td align="center"><s:if test="#producer.level==1">*</s:if>
          					<s:elseif test="#producer.level==2">**</s:elseif>
          					<s:elseif test="#producer.level==3">***</s:elseif>
          					<s:elseif test="#producer.level==4">****</s:elseif>
          					<s:elseif test="#producer.level==5">*****</s:elseif>
          </td>          
          <td align="center"><s:property value="persion"/></td>
          <td align="center"><s:property value="persionTel"/></td>
          <td align="center"><s:property value="tel"/></td>
          <td align="center"><s:property value="address"/></td>
          <td align="center"><s:property value="description"/></td>
          <td align="center"><img src="../images/edt.gif"><a href="updatefind.action?producer.id=<s:property value="id" />">修改</a></td>
          <td align="center"><img src="../images/del.gif"><a href="delete.action?producer.id=<s:property value="id"/>"  onclick="javascript:return del()">删除</a></td>
        </tr>
      </s:iterator>
</table>
<table border="0" align="center" cellpadding="0" class="list" cellspacing="0">
          <tr>
            <td height="30" align="right">
				共<s:property value="pageBean.allRow"/>条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页 
        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
        		<s:else>
            		<a href="query.action?page=1&producer.level=<s:property value="producer.level"/>&producer.name=<s:property value="producer.name"/>&producer.type=<s:property value="producer.type"/>&producer.persion=<s:property value="producer.persion"/>">第一页</a>
            		<a href="query.action?page=<s:property value="%{pageBean.currentPage-1}"/>&producer.level=<s:property value="producer.level"/>&producer.name=<s:property value="producer.name"/>&producer.type=<s:property value="producer.type"/>&producer.persion=<s:property value="producer.persion"/>">上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            		<a href="query.action?page=<s:property value="%{pageBean.currentPage+1}"/>&producer.level=<s:property value="producer.level"/>&producer.name=<s:property value="producer.name"/>&producer.type=<s:property value="producer.type"/>&producer.persion=<s:property value="producer.persion"/>">下一页</a>
            		<a href="query.action?page=<s:property value="pageBean.totalPage"/>&producer.level=<s:property value="producer.level"/>&producer.name=<s:property value="producer.name"/>&producer.type=<s:property value="producer.type"/>&producer.persion=<s:property value="producer.persion"/>">最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页</s:else>
            </td>
          </tr>
</table>
</div>
</body>
</html>
