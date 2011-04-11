<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<%
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/schedule/list.action";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>预约查询</title>
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

<body bgcolor="#ffffff">
<form action="delete.action" method="post" theme="simple">  
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="../images/m_mpbg.gif">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					预约查询:
				</td>
			</tr>
		</table>

    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" 

background="../images/addnew002.gif">
 <tags:button code="add" menu="82">
            <tr onClick="window.location='add.action'" style="cursor:hand;">
              <td><img src="../images/addnew001.gif"></td>
              <td nowrap>新建预约登记</td>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
   </tags:button>
          </table>
      </tr>
      
  	<tr>
    <td height="30" colspan="5" background="../images/main20100521_58.gif">
    	<table width="60%" border=0 cellpadding=0 cellspacing=0>
      	<tr>
        <td width="3%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">预约列表</td>
      	</tr>
    	</table>
    </td>
  	</tr>
    </table>

        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#FFFFFF">
        <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">选择 </td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">执行人</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">执行日期</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">具体时间</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">日程安排</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">预约人</td>
        <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">操作</td>
      </tr>
        
        <!-- 
        <tr>
          <td width="30" align="center"><div align="center"><strong>选择</strong></div></td> 
          <td align="center" width="30"><div align="center"><strong>序号</strong></div></td>  
          <td width="60"><div align="center"><strong>执行人</strong></div></td>
          <td height="25" width="110"><div align="center"><strong>执行日期</strong></div></td> 
          <td width="60"><div align="center"><strong>具体时间</strong></div></td> 
          <td><div align="center"><strong>日程安排</strong></div></td> 
          <td width="60"><div align="center"><strong>预约人</strong></div></td>
          <td width="150"><div align="center"><strong>操作</strong></div></td> 
        </tr> 
         -->
         
        <s:iterator value="pageBean.list" status="s">
        <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='#e3f0f7'" onmouseout="this.bgColor='#FFFFFF'">
	  		<td align="center"><input name="ids" type="checkbox" id="ids" value="<s:property value="id"/>" /></td> 
            <td align="center"><s:property value="#s.index+1" /></td>  
            <td class="name"><div align="center"><a href="month.action?uid=<s:property value="userByExecutor.id"/>"><s:property value="userByExecutor.truename"/></a></div></td>
            <td height="33" class="name"><div align="center">&nbsp;<s:date name="adate" format="yyyy-MM-dd(EE)"/></div></td> 
            <td class="name"><div align="center"><s:property value="hour"/>:<s:property value="minute"/> </div></td> 
            <td class="name"><div align="center"><a href="detail.action?scheduleId=<s:property value="id"/>"><s:property value="content"/></a></div></td>
            <td class="name"><div align="center"><s:property value="userByAssigner.truename"/></div></td>
            <td class="func"><div align="center">
             <tags:button code="detail" menu="82">
            <input type="button" name="Submit1" value="查看" onclick="window.location.href='detail.action?scheduleId=<s:property value="id"/>'"/>
           </tags:button>
            </div></td> 
        </tr> 
        </s:iterator>
          <tr style="cursor:hand;">
      <td colspan="9" height="30" align="right">
      	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" bgcolor="">
  	<tr>
    <td height="30" colspan="5" background="../images/main20100521_58.gif">
    	<table width="100%" border=0 cellpadding=0 cellspacing=0>
      	<tr>
        <td width="98%" style="color:#333333; font-weight:bold;"></td>
      	</tr>
    	</table>
    </td>
  	</tr>
		</table>

      </table> 
 
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
</form>
</body>
</html>