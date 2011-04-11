<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.Calendar" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String pid = request.getParameter("pid");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息添加</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../Framework/Main.js"></script>
<script type='text/javascript' src='../dwr/util.js'></script>
<script type='text/javascript' src='../dwr/interface/SFinanceTypeDAO.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<script type="text/javascript">
function getFinanceType(){ //取出类别
SFinanceTypeDAO.findAll(callbackorg)
}
function callbackorg(data){  //显示出类别
   dwr.util.removeAllOptions("FinanceType");
   dwr.util.addOptions("FinanceType",data,"code","name");
             
}
</script>
</head>

<body onload="getFinanceType()">
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
<tr> 
    <td width="100%" valign="top"> 
	<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/m_mpbg.gif"> 
          <tr> 
            <td class="place">用户信息添加</td> 
            <td>&nbsp;</td> 
            <td align="right">
<div style="color:red">
    <s:fielderror />
</div>
			</td> 
            <td width="3"><img src="../images/m_mpr.gif" width="3" height="32" /></td> 
          </tr> 
    </table> 
    <form action="save.action" method="post">
    <input type="hidden" name="finance.PBaseset.id" value="<%=pid %>"/>
    <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#C0CCDD" id="content">
      <tr class="top">
        <td height="35" width="80" align="right"><div align="right">登录名：</div></td>
        <td><input type="text" name="finance.FTitle" /></td>
        <td align="right"><div align="right"></div></td>
        <td></td>
      </tr>
      <tr>
        <td height="35" width="80" align="right"><div align="right">用户全名：</div></td>
        <td><input type="text" name="finance.FMoney" verify="Number" /></td>
      </tr>
         
      <tr>
        <td height="35" width="80" align="right"><div align="right">用户类型：</div></td>
        <td><select id="FinanceType" name="finance.SFinanceType.code"></select></td>       
      </tr>
      <tr>
        <td height="60" align="right">地理位置：</td>
       <td><select id="FinanceType" name="finance.SFinanceType.code"></select></td>
        </tr>
      <tr> 
        <td height="37" colspan="4" class="gray"> 
           <div align="center"> 
               <input type="submit" name="Submit" value="保存" class="page"/>
               <input type="reset" name="Reset" value="重置"/>
           </div>
        </td> 
      </tr> 
      </table>
      </form>
	</td> 
  </tr> 
</table> 
</body>
</html>