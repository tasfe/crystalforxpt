<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>工程师处理列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
	function comptime(beginTime,endTime){   
		if(!beginTime || !endTime){
			return "";
		}
		var beginTimes=beginTime.split(' ');  
		var endTimes=endTime.split(' '); 
		
		var beginTimesSplit= beginTimes[0].split('-'); 
		var endTimesSplit=endTimes[0].split('-');
		
		beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
		endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
		var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
		return timeGap<0?"":Math.floor(timeGap);   
	}
</script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" >
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">服务请求处理列表</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;"><table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="20" style="font-size:12px; color:#333333; font-weight:bold;"><table cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td width="99%" height="30" style="padding-top: 10px" ><img src="../images/main20100521dot04.gif">&nbsp;<b>服务请求列表:</b>&nbsp;</td>
              <td nowrap>&nbsp;</td>
              <td></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td valign="top"  style="line-height:5px;"></td>
        </tr>
      </table>  
         
      <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6" class="datagrid">
        <tr>
          <th width="8%" height="22" nowrap background="../images/main20100521_58.gif" class="alllisttitle">工单号</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">服务类别</th>
          <th width="8%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请人</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">申请时间</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">结束时间</th>
          <th width="12%" nowrap background="../images/main20100521_58.gif" class="alllisttitle">处理总时长(小时)</th>
          <th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">状态 </th>
          <th width="6%" nowrap background="../images/main20100521_58.gif" class="alllisttitle" style="text-align: center"> 查看 </th>
        </tr>
        
             
        <s:iterator value="requests" var="serviceRequest">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
           <td height="30"><s:property value="requestNo"/></td>
           <td align="center"><s:property value="serviceCategory.itemZh"/></td>
           <td align="center"><s:property value="usersByRequestUser.truename"/></td>
           <td align="center"><s:date name="submintTime"/></td>
           <td align="center"><s:date name="finishTime"/></td>
           <td align="center">
           		<script type="text/javascript">
           			var beginTime="<s:date name="submintTime"/>";
           			var endTime="<s:date name="finishTime"/>";
           			document.write(comptime(beginTime,endTime));
           		</script>
           </td>
           <td align="center"><s:if test="#serviceRequest.state==0">待派单</s:if>
          					<s:elseif test="#serviceRequest.state==1">待受理</s:elseif>
          					<s:elseif test="#serviceRequest.state==2">处理中</s:elseif>
          					<s:elseif test="#serviceRequest.state==3">流程进行中</s:elseif>
          					<s:elseif test="#serviceRequest.state==4">已拒绝</s:elseif>
          					<s:elseif test="#serviceRequest.state==5">等待用户反馈</s:elseif>
          					<s:elseif test="#serviceRequest.state==6">已关闭</s:elseif>
          					<s:elseif test="#serviceRequest.state==7">已挂起</s:elseif>
          </td>
          <td align="center" nowrap><a href="../engineertrace/show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img src='../img/viewdetail.gif' border=0 width=18 height=18></a></td>          
        </tr>
     </s:iterator>  
      </table>

</div>
</body>
</html>
