<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{
}
	function RowsPerPage(value) {
		window.location.href="requestlist.action?pageSize="+value;
	}
	function goPage(num) {
		window.location.href="requestlist.action?page="+num;
	}
</script>


</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<s:form name="form" theme="simple">
<s:hidden id="page" name="page"></s:hidden>
<s:hidden id="state" name="state"></s:hidden>
<s:hidden id="pageSize" name="pageSize"></s:hidden>
</s:form>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">用户跟踪请求处理情况:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px; font-style: italic;">
<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table cellspacing=0 cellpadding=0 border=0 width="100%">
      <tr>
        <td nowrap><img src="../img/help2.jpg" width="22" height="23" align="absmiddle">&nbsp;</td>
        <td width="99%" onClick="document.getElementById('thelayer').style.display = 'none'">&nbsp;<b>
         <s:if test="state==0">
          所有待派单请求
          </s:if>
          <s:elseif test="state==1">
         所有等待受理请求
          </s:elseif>
          <s:elseif test="state==2">
          所有处理中请求
          </s:elseif>
           <s:elseif test="state==3">
         所有流程进行中请求
          </s:elseif>
           <s:elseif test="state==4">
          所有已拒绝请求 
          </s:elseif>
           <s:elseif test="state==5">
          所有未反馈请求   
          </s:elseif>
          <s:else>
           所有已关闭请求 
           </s:else>
           </b></td>
        <td align=right colspan=2 width="1%" height="28" valign="top" style="padding-top: 4px"><table border="0" cellspacing="0" cellpadding="0" width="100px">
            <tr>
              <td width="1"><img width="3"></td>
              <td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"  onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()" nowrap><input type="text" name="thetypes" value="根据状态查看" style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px" readonly>
              </td>
              <td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; PADDING: 1px; PADDING-TOP: 1px"><input type="button" value="6"  style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; padding-top: 0px; height: 17px; line-height: 9px; width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}" name="click">
              </td>
            </tr>
            <tr id="thelayer" onClick="this.style.display='none'" style="display: none">
              <td width="1"></td>
              <td height="2" colspan="2"><div style="position:absolute; width:100%; height:15px; z-index:9; cursor: default; overflow-x: hidden; border: 1px outset">
                  <table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
<%--                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../img/ServiceInput.gif" align=absmiddle></td>
                      <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.location='tracelist.action?type=1&state=-1'">&nbsp;所有未结束请求</td>
                    </tr>--%>
                   <%-- <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../img/task.gif" align=absmiddle></td>
                      <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.location='tracelist.action?state=0'">&nbsp;新创建</td>
                    </tr>--%>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../img/page.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.location='tracelist.action?type=0&state=0'">&nbsp;待派单</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../img/ServiceInput.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.location='tracelist.action?type=0&state=1'">&nbsp;工程师未受理</td>
                    </tr>
                    <tr>
                      <td bgcolor="white" width="1%"><img width=18 height=18 src="../img/wait.gif" align=absmiddle border="0"></td>
                      <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="window.location='tracelist.action?type=0&state=2'">&nbsp;工程师正在处理中</td>
                    </tr>
                  </table>
              </div></td>
            </tr>
        </table></td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
        <tr bgcolor="#FFFFFF">
          <td height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">工单号</td>
          <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">服务类别</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">申请时间</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">开始处理时间</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">承诺完成时间</td>
          <td align="center" background="../images/main20100521_58.gif" bgcolor="#FFFFFF" class="alllisttitle">服务工程师</td>
          <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">状态</td>
          <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">查看</td>
<%--          <td align="center" background="../images/main20100521_58.gif" class="alllisttitle">进度</td>--%>
                 </tr>
                       
     <s:iterator value="pageBean.list" var="serviceRequest">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
          <td align="center"><s:property value="requestNo"/></td>
          <td align="center"><s:property value="serviceCategory.itemZh"/></td>
          <td align="center">  <s:date name="submintTime" format="yyyy/MM/dd HH:mm:ss" /></td>
          
          <td align="center"> <s:date name="buginTime" format="yyyy/MM/dd HH:mm:ss" /></td>
        
         
          <td align="center"> <s:date name="expectTime" format="yyyy/MM/dd HH:mm:ss" /></td> 
           
          <td align="center"><s:property value="usersByEngineerId.truename"/></td>
         <s:if test="state==0">
           <td align="center">待派单</td> 
          </s:if>
          <s:elseif test="state==1">
          <td align="center">等待受理</td> 
          </s:elseif>
          <s:elseif test="state==2">
          <td align="center">处理中</td> 
          </s:elseif>
           <s:elseif test="state==3">
          <td align="center">流程进行中</td> 
          </s:elseif>
           <s:elseif test="state==4">
          <td align="center">已拒绝</td> 
          </s:elseif>
           <s:elseif test="state==5">
          <td align="center"><a href="propressInfo.action?requestNo=${serviceRequest.requestNo}"><img src="../img/processing.gif" align="absmiddle" alt="提交反馈"></a></td> 
          </s:elseif>
          <s:else>
          <td align="center">已关闭</td> 
           </s:else>

      
          <td align="center"><a href="showReqInfo.action?requestNo=${serviceRequest.requestNo}" target="_self"><img src="../img/viewdetail.gif" align="absmiddle"></a></td>
         <%-- <td align="center"><a href="propressInfo.action?requestNo=${serviceRequest.requestNo}"><img src="../img/processing.gif" align="absmiddle" alt="查看进度"></a></td>   --%>      
        </tr>
     
     </s:iterator>
       
      </table>
    </td>
  </tr>
</table>
<jsp:include page="/common/page.jsp"/> 


</div>
</body>
</html>
