<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/monthFee/list.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户资料</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" /> 
<script language="JavaScript">
function checkAll(e,itemName){    //全选函数 e为全选对象,itemName 为子name
 var aa=document.getElementsByName(itemName);
 for(var i=0;i<aa.length;i++){
  aa[i].checked=e.checked;
 }
}

function checkItem(e,allName){   //当选取或取消选取没一个复选框时调用的函数 
 var all=document.getElementById(allName); //全选框对象
 if(!e.checked){      //如果是取消选取那么全选框就取消选取
  all.checked=false;
 }
 else{                 //如果是选取，就要判断其他是否都已经全部选取了
  var aa=document.getElementsByName(e.name);
  for(var i=0;i<aa.length;i++){
   if(!aa[i].checked){//如果有一个没有选上，那么就返回
    return;
   }
  }
  all.checked=true;//如果其他都选上了，全选框就要选上
 }
}
</script>
</head>

<body bgcolor="#ebebeb">
<form name="myForm" action="delete.action" method="post">  
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td width="100%" valign="top"> 
		<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/m_mpbg.gif"> 
          <tr> 
            <td class="place">用户角色<br /><br />固定的<br/></td> 
            <td>&nbsp;</td> 
            <td align="right">
            	<a href="add.action">新增</a>
            </td>
              <td width="3"><img src="../images/m_mpr.gif" width="3" height="32" /></td> 
          </tr> 
        </table>  
	<table width="100%" border="0" cellpadding="6" cellspacing="1" bgcolor="#C0CCDD" id="content"> 
		<tr class="top"> 
          <td width="5%"><div align="center"><strong>选择</strong></div></td>  
          <td width="5%"><div align="center"><strong>序号</strong></div></td>
          <td width="45%"><div align="center"><strong>名称</strong></div></td> 
          <td width="45%"><div align="center"><strong>类属</strong></div></td> 
          <td width="5%"><div align="center"><strong>修改</strong></div></td> 
          <td width="5%"><div align="center"><strong>删除</strong></div></td> 
        </tr> 
        
        	
		<tr onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='ecf5ff'"> 
          <td width="5%"><div align="center">选择</div></td>  
          <td width="5%"><div align="center">10</div></td>
          <td width="45%"><div align="center">员工IT帐号信息管理员</div></td> 
          <td width="45%"><div align="center">FIX</div></td> 
          <td width="5%"><div align="center"><input type="button" name="Submit" value="编辑" onclick="window.location.href='edit.action?id=<s:property value="id"/>"/></div></td>
          <td width="5%"><div align="center">
          <input type="button" name="Submit" value="删除" onclick="if(confirm('确认删除？')){window.location.href='delete.action?id=<s:property value="id"/>"/></div></td> 
        </tr> 
       
 </table>
      
        <hr/>
        浮动的

        
  	<table width="100%" border="0" cellpadding="6" cellspacing="1" bgcolor="#C0CCDD" id="content"> 
	<tr onmouseout="this.style.backgroundColor=''" onmouseover="this.style.backgroundColor='ecf5ff'"> 
          <td width="5%"><div align="center">选择</div></td>  
          <td width="5%"><div align="center">16</div></td>
          <td width="45%"><div align="center">行政部经理</div></td> 
          <td width="45%"><div align="center">RLT</div></td> 
          <td width="5%"><div align="center"><input type="button" name="Submit" value="编辑" onclick="window.location.href='edit.action?id=<s:property value="id"/>"/></div></td>
          <td width="5%"><div align="center">
          <input type="button" name="Submit" value="删除" onclick="if(confirm('确认删除？')){window.location.href='delete.action?id=<s:property value="id"/>"/></div></td> 
        </tr> 
      </table> 
      
      

  <tr> 
	<td class="page"> 
	<table width="100%" border="0" align="left" > 
        <tr>  
              <td width="58%"><div align="right">
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
              </div></td>
        </tr>
        <tr> 
        	<td height="38" colspan="3"><div align="right"><p>&nbsp;</p></div></td> 
        </tr> 
	</table> 

    </td> 
  </tr> 
</table> 
</form>
</body>
</html>