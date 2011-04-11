<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<html>
<head>
<title>系统日志审计</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath }/js/DatePicker/WdatePicker.js"></script>

<script> 
var online= new Array(); 
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
</style>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold; padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="99%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">系统日志审计</td>
  </tr>
</table>
<div style="position: absolute; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
      <table width="99%" border="0" align="center" cellpadding="1" cellspacing="0">
		<tr><td width="99" height="2"></td></tr>
        <tr onClick="document.getElementById('thelayer').style.display='none'"> 
          <td height="12">
                    <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                    <s:form  method="post" action="" theme="simple"><tr> 
                      <td width="8%" align="center" nowrap bgcolor="#deebf1" class="td-left">操作人:&nbsp;</td>
                      <td width="17%" bgcolor="#FFFFFF" class="td-detail"> <input Name="ITprinc" ID="ITprinc" value="" style="width: 90%"></td>
                      <td width="8%" align="center" nowrap bgcolor="#deebf1" class="td-left">开始时间:&nbsp;</td>
                    <td width="20%" bgcolor="#FFFFFF"> 
                     <input type="text" name="sDate" id="d11" onClick="WdatePicker()"/><img onClick="WdatePicker({el:$dp.$('d11')})" src="${pageContext.request.contextPath }/js/DatePicker/skin/datePicker.gif" width="16" height="22" />                     </td>
                      <td width="8%" align="center" nowrap bgcolor="#deebf1" class="td-left">结束时间:&nbsp;</td>
                      <td width="35%" bgcolor="#FFFFFF" class="td-detail">       
                      <input type="text" name="sDate" id="d11" onClick="WdatePicker()"/><img onClick="WdatePicker({el:$dp.$('d11')})" src="${pageContext.request.contextPath }/js/DatePicker/skin/datePicker.gif" width="16" height="22" />                      </td>
                      <td align="right" bgcolor="#F9F9F9" width="4%"> 
                      <input name="button" type="button" class="mmBtn" onClick="SeekOnClick('ID','Desc'); this.disabled=true;" value="搜索" style="height: 20px">                      </td>
                    </tr></s:form>
            </table>
          </td>
        </tr> 
		<tr> 
		  <td height="12">
		  <table width="100%" border=0 cellpadding=0 cellspacing=1 bgcolor="#b5d6e6">
            <tr>
              <td width="99%" background="../images/main20100521_58.gif" onClick="document.getElementById('thelayer').style.display = 'none'"><table width="50%" border=0 cellpadding=0 cellspacing=0>
                  <tr>
                    <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px; padding-left:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
                    <td width="98%" style="color:#333333; font-weight:bold;">所有变更与活动记录:  </td>
                  </tr>
                </table></td>
              <td width="1%" colspan=2 align=right valign="top" background="../images/main20100521_58.gif" >
              <table border="0" cellspacing="0" cellpadding="0" width="100px">
                  <tr>
                    <td width="1"><img width="3"></td>
                    <td width="99%" style="BORDER: #cccccc 1px groove; BORDER-RIGHT: 0px; HEIGHT: 18px; font-size: 8pt; font-family: Tahoma; cursor: default"  onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'};document.getElementById('click').focus()" nowrap><input type="text" name="thetypes" value="根据类别查看" style="width: 150px; border: 0px; font-size: 8pt; font-family: Tahoma; padding-left: 2px; cursor: default; height: 15px" readonly></td>
                    <td width="1%" style="BORDER: #cccccc 1px groove; BORDER-LEFT: 0px; "><input type="button" value="6"  style="border: 1px outset white; BACKGROUND-IMAGE: url(../img/tr.gif); font-size: 12px; font-family: Webdings; height: 17px; line-height: 9px; width: 21px" onClick="if (document.getElementById('thelayer').style.display=='none') {document.getElementById('thelayer').style.display = ''} else {document.getElementById('thelayer').style.display = 'none'}" name="click"></td>
                  </tr>
                  <tr id="thelayer" onClick="this.style.display='none'" style="display: none">
                    <td width="1"></td>
                    <td height="2" colspan="2"><div style="position:absolute; width:100%; height:15px; z-index:9; cursor: default; overflow-x: hidden; border: 1px outset">
                        <table width="100%" border="0" cellpadding="1" cellspacing="0" bgcolor="white">
                          <tr>
                            <td bgcolor="white" width="1%" style="padding: 3px"><img width=16 height=16 src="${pageContext.request.contextPath }/img/check.jpg" align=absmiddle></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../asst/?NowAction=changehis&ITprinc=&RequTime1=&RequTime2=&Sort=ID&Desc=Desc&RecState='">&nbsp;所有变更活动记录</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%" style="padding: 3px"><img width=16 height=16 src="${pageContext.request.contextPath }/img/check.jpg" align=absmiddle></td>
                            <td nowrap onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../asst/?NowAction=changehis&ITprinc=&RequTime1=&RequTime2=&Sort=ID&Desc=Desc&RecState=REFUSE'">&nbsp;拒绝任务记录</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%" style="padding: 3px"><img width=16 height=16 src="${pageContext.request.contextPath }/img/check.jpg" align=absmiddle></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../asst/?NowAction=changehis&ITprinc=&RequTime1=&RequTime2=&Sort=ID&Desc=Desc&RecState=TRANSFER'">&nbsp;业务或服务转交记录</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%" style="padding: 3px"><img width=16 height=16 src="${pageContext.request.contextPath }/img/check.jpg" align=absmiddle></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../asst/?NowAction=changehis&ITprinc=&RequTime1=&RequTime2=&Sort=ID&Desc=Desc&RecState=RETURN'">&nbsp;用户退单记录</td>
                          </tr>
                          <tr>
                            <td bgcolor="white" width="1%" style="padding: 3px"><img width=16 height=16 src="${pageContext.request.contextPath }/img/check.jpg" align=absmiddle></td>
                            <td onMouseOver="this.bgColor='#648AC4';this.style.color='white'" onMouseOut="this.bgColor='white';this.style.color='#333333'" bgcolor="white" onClick="location.href='../asst/?NowAction=changehis&ITprinc=&RequTime1=&RequTime2=&Sort=ID&Desc=Desc&RecState=DEAL'">&nbsp;资产配置变更记录</td>
                          </tr>
                      </table>
                      </td>
                  </tr>
              </table>
              </td>
            </tr>
          </table>
          </td>
	    </tr>
	    
          <tr> 
			<td valign="top"><table width="100%" border=0 cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
              <tr> 
                <td width="13%" height="22" align="center" nowrap bgcolor="#deebf1"><font style='cursor:hand' onclick="SeekOnClick('RequNo','DESC');">工单或配置号</font></td>
                <td width="9%" align="center" nowrap bgcolor="#deebf1" title="Service Category"><font style='cursor:hand' onclick="SeekOnClick('Type','DESC');">类别</font></td>
                <td width="11%" align="center" nowrap bgcolor="#deebf1"><font style='cursor:hand' onclick="SeekOnClick('Succor','DESC');">操作人</font></td>
                <td width="10%" align="center" nowrap bgcolor="#deebf1"><font style='cursor:hand' onclick="SeekOnClick('AddTime','DESC');">发生时间</font></td>
                <td width="50%" align="center" nowrap bgcolor="#deebf1">详细内容</td>
                <td width=7% align="center" nowrap bgcolor="#deebf1" style="text-align: center">查看</td>
              </tr>
             <s:iterator value="serviceTrans" var="serviceTran">
              <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'"> 
                <td height="30" valign="middle" nowrap><img src="../img/jiedian.gif">
                <s:property value="requNo"/></td>
                <td align="center" valign="middle"><s:property value="type"/></td>
                <td align="center" valign="middle"><s:property value="serviceFrom"/></td>
                <td align="center" valign="middle" nowrap><s:property value="operatorTime"/></td>
                <td valign="center" valign="middle" ><s:property value="note"/></td>
                <td align=center valign="middle" nowrap><a onClick="window.open('../asst/?NowAction=annouce&ID=82&Type=AsstChan','','width=700,height=350,scrollbars=yes,menubar=no,resizable=yes,top=40,left=80,status=yes')" href="javascript:"><img src="${pageContext.request.contextPath }/img/check.gif" width="22" height="22" border="0"></a></td>
              </tr>
              </s:iterator>
        
     </table>
     
   </body>
</html>
