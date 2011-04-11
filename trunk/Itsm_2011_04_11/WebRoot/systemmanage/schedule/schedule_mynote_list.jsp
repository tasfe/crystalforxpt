<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/schedule/list.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的便签</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" /> 
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
<form action="delete.action" method="post" theme="simple">  
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
  <tr>
    <td width="100%" valign="top"> 
			<table width="100%" border="0" cellpadding="0" cellspacing="1" background="../images/m_mpbg.gif"> 
          <tr>
        <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">我的便签:</td>
      </tr>
    </table> 

    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" 

background="../images/addnew002.gif">
            <tr onClick="window.location='addnoteInput.action'" style="cursor:hand;">
              <td><img src="../images/addnew001.gif"></td>
              <td nowrap>新建便签</td>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
          </table>
      </tr>
    </table>

        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#FFFFFF">
        <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="5%">选择 </td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="5%">序号</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="10%">标题</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="50%">内容</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="10%">具体时间</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle" width="25%">操作</td>
      </tr>
        
        <s:iterator value="pageBean.list" status="s">
        <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='#e3f0f7'" onmouseout="this.bgColor='#FFFFFF'">
	  		<td align="center"><input name="ids" type="checkbox" id="ids" value="<s:property value="id"/>" /></td> 
            <td align="center"><s:property value="#s.index+1" /></td>  
            <td class="name"><div align="center"><s:property value="title"/></div></td>
            <td height="25" class="name"><div align="center">&nbsp;<s:property value="detail"/></div></td> 
            <td class="name"><div align="center"><s:property value="atime"/></div></td>
            <td class="func"><div align="center">
            <input type="button" name="Submit1" value="查看" onclick="window.location.href='shownote.action?scheduleId=<s:property value="id"/>'"/>
            <input type="button" name="Submit1" value="编辑" onclick="window.location.href='edit.action?scheduleId=<s:property value="scheduleId"/>'"/>
            <input type="button" name="Submit" value="删除" onclick="if(confirm('确认删除？')){window.location.href='delete.action?scheduleId=<s:property value="scheduleId"/>'}"/>
            </div></td> 
        </tr> 
        </s:iterator>
      </table> 
    </td> 
  </tr> 
  <tr> 
	<td class="page"> 
	<table width="100%" border="0" align="left" > 
        <tr> 
            <td width="8%" height="30"><div align="center">全选<input type="checkbox" name="checkbox2" value="Check All" onClick='checkAll(this,"ids");' /></div></td> 
            <td width="34%" >
            <input type="submit" onClick="if(confirm('确认删除多个项目？')){document.myForm.submit();}" name="Submit2" value="批量删除"  class="anybutton2"/>
            </td> 
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