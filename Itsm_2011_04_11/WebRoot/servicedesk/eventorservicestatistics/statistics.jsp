<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>请求统计</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/javascript" src="<%=path %>/js/DatePicker/WdatePicker.js"></script>
	<!-- ext core -->
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/ext/resources/css/ext-all.css">
	<script type="text/javascript" src="<%=path %>/js/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=path %>/js/ext/ext-all.js"></script>
	<script type="text/javascript" src="<%=path %>/js/util.js"></script>
    <script type="text/javascript">
    	function init(){
    		
    	}
    	function selectUser(){
			var win = new UserSelectWindow({
				accepted:function(user){
					document.getElementById('engineerName').value=user.name;
					document.getElementById('engineerId').value=user.id;
					
				}
			});
			win.show();
		}
		function clearChoose(){
			document.getElementById('users').value='';
			document.getElementById('engineerId').value='0';
			document.getElementById('startTime').value='';
			document.getElementById('endTime').value='';
		}
		function checkForm(){
			return true;
		}
		function toDetail(condition){
			window.open("<%=path %>/srstatistic/getListByRequnos.action?requNos="+condition);
		}
    </script>
  </head>
  
 <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="<%=path %>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path %>/images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="<%=path %>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">服务请求处理统计</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
    	<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			<s:form action="getEngineerWorkStatics.action" method='post' theme="simple" namespace="/srstatistic">
				<tr>
					<td width="5%" nowrap bgcolor="#deebf1">工程师:&nbsp;</td>
					<td width="25%" bgcolor="#EBF4F5"  style="padding-right: 10px">
						<s:hidden id="engineerId" name="engineerId"></s:hidden>
					  <s:textfield id="users" name="engineerName" style="width: 79%; background-color: #FFFFCC; cursor: text"  onClick="javascript:selectUser();"></s:textfield>
&nbsp;<img src="<%=path %>/images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser();"><br>
					</td>
					<td width="5%" nowrap bgcolor="#deebf1">开始时间:&nbsp;</td>
					<td width="20%" bgcolor="#EBF4F5"  style="padding-right: 10px">
						<s:textfield name="startTime" id="startTime" onclick=" WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield>
						<img onClick=" WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd'})" src="<%=path %>/js/DatePicker/skin/datePicker.gif"  />
					</td>
					<td width="5%" nowrap bgcolor="#deebf1">结束时间:&nbsp;</td>
					<td width="20%" bgcolor="#EBF4F5"  style="padding-right: 10px">
						<s:textfield name="endTime" id="endTime" onclick=" WdatePicker({dateFmt:'yyyy-MM-dd'})"></s:textfield>
						<img onClick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd'})" src="<%=path %>/js/DatePicker/skin/datePicker.gif"  />
					</td>
					<td width="20%" align="center" bgcolor="#deebf1">
						 <tags:button code="search" menu="115">
							<input type="submit" style="height: 20px" class="mmBtn" value="搜索" onClick="javascript:return checkForm();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" style="height: 20px" class="mmBtn" value="清空" onClick="javascript:clearChoose();"/>
						</tags:button>
					</td>					
				</tr>				
				</s:form>
	  </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="20" style="font-size:12px; color:#333333; font-weight:bold;">
          <table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td width="60%" height="30" style="padding-top: 10px"><img src="<%=path %>/images/main20100521dot04.gif">&nbsp;<b>服务请求处理统计列表:</b>&nbsp;</td>
              <td width="39%" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	
              </td>
              <td align="right" colspan="2" width="1%" height="28" valign="top" style="padding-top: 4px">
          
              </td>
            </tr>
          </table>
          </td>
        </tr>
        <tr>
          <td valign="top" background="<%=path %>/img/Separator.gif" style="line-height:5px;"><img src="<%=path %>/img/Separator.gif" width="6" height="6"></td>
        </tr>
      </table>     
      <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
        <tr>
          <td width="25%" height="20" nowrap background="<%=path %>/images/main20100521_58.gif" class="alllisttitle" align="center">工程师</td>
          <td width="25%" nowrap background="<%=path %>/images/main20100521_58.gif" class="alllisttitle" align="center">处理请求数 </td>          
          <td width="25%" nowrap background="<%=path %>/images/main20100521_58.gif" class="alllisttitle" align="center">处理总时长(小时)</td>
          <td width="25%" nowrap background="<%=path %>/images/main20100521_58.gif" class="alllisttitle" align="center">平均处理时长</td>
        </tr> 
      	<s:iterator value="datas">
      		<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
	      		<td align="center" height="20"><s:property value="engineerName"/></td>
	      		<td align="center" height="20">
	      			<a href="#" style="text-decoration:underline;color:blue" onClick="toDetail('<s:property value="condition"/>');"><s:property value="num"/></a:>
	      		</td>
	      		<td align="center" height="20"><s:property value="dealTime"/></td>
	      		<td align="center" height="20">
	      			<script type="text/javascript">
	      				var num="<s:property value="num"/>";
	      				var dealTime="<s:property value="dealTime"/>";
	      				document.write((dealTime/num).toFixed(1));
	      			</script>
	      		</td>
      		</tr>
      	</s:iterator>
      </table>
  

</div>
</body>
</html>
