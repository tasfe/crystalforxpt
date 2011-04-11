<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>网络中心人员资料</title>
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
	
	function res(){
	    document.getElementById("ipNetcenter.name").value="";
	     document.getElementById("ipNetcenter.type").value="";
	      document.getElementById("ipNetcenter.phone").value="";
	       document.getElementById("ipNetcenter.email").value="";
	
	}
	
	function changepage(page){
	   document.form1.action="list.action?howquery=1&page="+page;
	   document.form1.submit();
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
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">网络中心人员搜索</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
	 <s:form action="list.action?howquery=1" method='post' theme="simple" name="form1">
	<tr>
		<td width=2% align="center" nowrap bgcolor="#deebf1">姓名:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input type="text" name="ipNetcenter.name" id="ipNetcenter.name" style="width: 100%" value="<s:property value="producer.name"/>">
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">类型:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<select size="1" id="ipNetcenter.type" name="ipNetcenter.type" style="width:100%;">
				<option value="0" id="ipNetcenter.type0" <s:if test="ipNetcenter.type==0">selected</s:if>>--请选择--</option>
                <option value="1" id="ipNetcenter.type1" <s:if test="ipNetcenter.type==1">selected</s:if>>接收人</option>
                <option value="2" id="ipNetcenter.type2" <s:if test="ipNetcenter.type==2">selected</s:if>>完成人</option>
                <option value="3" id="ipNetcenter.type3" <s:if test="ipNetcenter.type==3">selected</s:if>>二者都是</option>
            </select>
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">电话:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
	   <input type="text" name="ipNetcenter.phone" id="ipNetcenter.phone" style="width: 100%" value="<s:property value="ipNetcenter.phone"/>">
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">Email:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input type="text" name="ipNetcenter.email" id="ipNetcenter.email" style="width: 100%" value="<s:property value="ipNetcenter.email"/>">
		</td>
		<td width="2%" bgcolor="#deebf1" >
			<input type="submit" style="height:20px" class="mmBtn" value="搜索" />
		</td>
		<td width="2%" bgcolor="#deebf1" >
			<input type="button" style="height:20px" class="mmBtn" value="重置" onClick="res();"/>
		</td>
	</tr>
	</s:form>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
      		<tr onClick="window.location.href='saveInput.action'" style="cursor:hand;">
        		<td><img src="../images/addnew001.gif"></td>
        		<td nowrap>新建网络中心人员</td>
        		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#b5d6e6">
        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">姓名</td>        
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">类型</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">联系电话</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">Email</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">备注</td>        
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
      </tr>
      <s:iterator value="pageBean.list" var="producer">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
                  <td align="center" class="zczb_qua"><s:property value="id"/></td>
        
		<td height="19" align="center" class="zczb_qua"><s:property value="name"/></td>
          <td align="center" class="zczb_qua"><s:if test="#ipNetcenter.type==1">接收人</s:if>
          					<s:elseif test="#ipNetcenter.type==2">完成人</s:elseif>
          					<s:elseif test="#ipNetcenter.type==3">二者都是</s:elseif>
          </td>
        
          <td align="center" class="zczb_qua"><s:property value="phone"/></td>
          <td align="center" class="zczb_qua"><s:property value="email"/></td>
          <td align="center" class="zczb_qua"><s:property value="detail"/></td>
          <td align="center" class="zczb_qua"><img src="../images/edt.gif"><a href="updateInput.action?ipNetcenterId=<s:property value="id" />">修改</a></td>
          <td align="center" class="zczb_qua"><img src="../images/del.gif"><a href="delete.action?ipNetcenterId=<s:property value="id"/>"  onclick="javascript:return del()">删除</a></td>
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
<table border="0" align="center" cellpadding="0" class="list" cellspacing="0">

      <s:if test="message==1">
			<tr><td align="center" colspan="6" >
			该类别暂时还不能删除！
			</td></tr>
			</s:if> 
			</table>
</div>
</body>
</html>
