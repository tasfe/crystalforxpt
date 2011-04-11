<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>供应商厂商资料</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function init(){
var i="<s:property value="message"/>";
if(i=="1"){
alert("该供应厂商暂时还不能删除！");
}else if(i=="2"){
alert("删除成功！");
}else if(i=="3"){
alert("保存成功！");
}
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
	
	function res(){
	    document.getElementById("producer.name").value="";
	    document.getElementById("producer.type0").selected=true;
	    document.getElementById("producer.persion").value="";
	    document.getElementById("producer.level0").selected=true;
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

<body style="overflow:hidden;" onLoad="init();">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">供应商厂商搜索</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
	 <s:form action="list.action?howquery=1" method='post' theme="simple" name="form1">
	<tr>
		<td width=2% align="center" nowrap bgcolor="#deebf1">名称:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input type="text" name="producer.name" id="producer.name" style="width: 100%" value="<s:property value="producer.name"/>">
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">类型:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<select size="1" id="producer.type" name="producer.type" style="width:100%;">
				<option value="0" id="producer.type0" <s:if test="producer.type==0">selected</s:if>>--请选择--</option>
                <option value="1" id="producer.type1" <s:if test="producer.type==1">selected</s:if>>供应商</option>
                <option value="2" id="producer.type2" <s:if test="producer.type==2">selected</s:if>>制造商</option>
                <option value="3" id="producer.type3" <s:if test="producer.type==3">selected</s:if>>二者都是</option>
            </select>
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">级别:&nbsp;</td>
		<td width="22%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<select size="1" id="producer.level" name="producer.level" style="width:100%;">
				<option value="0" id="producer.level0" <s:if test="producer.level==0">selected</s:if>>--请选择--</option>
                <option value="1" id="producer.level1" <s:if test="producer.level==1">selected</s:if>>*</option>
                <option value="2" id="producer.level2" <s:if test="producer.level==2">selected</s:if>>**</option>
                <option value="3" id="producer.level3" <s:if test="producer.level==3">selected</s:if>>***</option>
                <option value="4" id="producer.level4" <s:if test="producer.level==4">selected</s:if>>****</option>
                <option value="5" id="producer.level5" <s:if test="producer.level==5">selected</s:if>>*****</option>
            </select>
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">联系人:&nbsp;</td>
		<td width="23%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input type="text" name="producer.persion" id="producer.persion" style="width: 100%" value="<s:property value="producer.persion"/>">
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
      		<tr onClick="window.location.href='../systemmanage/producer/add.jsp'" style="cursor:hand;">
      		<tags:button code="add" menu="9">
        		<td><img src="../images/addnew001.gif"></td>
        		<td nowrap>新建供应制造商</td>
        		<td align="right"><img src="../images/addnew003.gif"></td>
        	</tags:button>
      		</tr>
    	</table>
    </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#b5d6e6">
        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">名称</td>                
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">类型</td>        
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">星级级别</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">联系人</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">联系电话</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">售后服务电话</td>        
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">地址</td>
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">描述</td>
        <tags:button code="update" menu="9">
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
        </tags:button>
        <tags:button code="delete" menu="9">
        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
        </tags:button>
      </tr>
      <s:iterator value="pageBean.list" var="producer">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
		<td height="19" align="center" class="zczb_qua"><s:property value="name"/></td>
          <td align="center" class="zczb_qua"><s:if test="#producer.type==1">供应商</s:if>
          					<s:elseif test="#producer.type==2">制造商</s:elseif>
          					<s:elseif test="#producer.type==3">二者都是</s:elseif>
          </td>
          <td align="center" class="zczb_qua"><s:if test="#producer.level==1">*</s:if>
          					<s:elseif test="#producer.level==2">**</s:elseif>
          					<s:elseif test="#producer.level==3">***</s:elseif>
          					<s:elseif test="#producer.level==4">****</s:elseif>
          					<s:elseif test="#producer.level==5">*****</s:elseif>
          </td>          
          <td align="center" class="zczb_qua"><s:property value="persion"/></td>
          <td align="center" class="zczb_qua"><s:property value="persionTel"/></td>
          <td align="center" class="zczb_qua"><s:property value="tel"/></td>
          <td align="center" class="zczb_qua"><s:property value="address"/></td>
          <td align="center" class="zczb_qua"><s:property value="description"/></td>
          <tags:button code="update" menu="9">
          <td align="center" class="zczb_qua"><img src="../images/edt.gif"><a href="updatefind.action?producer.id=<s:property value="id" />">修改</a></td>
          </tags:button>
          <tags:button code="delete" menu="9">
          <td align="center" class="zczb_qua"><img src="../images/del.gif"><a href="delete.action?producer.id=<s:property value="id"/>"  onclick="javascript:return del()">删除</a></td>
          </tags:button>
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
</body>
</html>
