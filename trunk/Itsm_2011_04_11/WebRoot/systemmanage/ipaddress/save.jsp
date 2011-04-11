<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>IP地址申请新增</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
	</head>
	
<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();">
<s:form action="save" method="post" theme="simple" namespace="/ipaddress">
<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="ipAddress.id>"/>新增IP地址申请</td>
 </tr>
 <tr>
   <td height="22" bgcolor="#FFFFFF">
 
   <table width="98%" align="center" cellpadding="0" cellspacing="0">
     <tr>
       <td valign="top"><table width="99%" align="center" cellpadding="0" cellspacing="0">
        <tr>
         <td colspan="3" valign="top"><br></td>
       </tr>
       <tr>
         <td colspan="3">&nbsp;</td>
       </tr>
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·IP申请表申请人信息·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td  height="30" align="right">IPv4地址申请数量：</td> 
                   <td  height="30" align="left"><input type="text" id="ipAddress.applyIpv4Count" name="ipAddress.applyIpv4Count" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">IPv6地址申请数量：</td>
                   <td  height="30" align="left"><input type="text" id="ipAddress.applyIpv6Count" name="ipAddress.applyIpv6Count" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">IP地址申请用途：</td>
                   <td  height="30" align="left"><input type="text" id="ipAddress.applyIpPurpose" name="ipAddress.applyIpPurpose" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td height="30" align="right">IP地址使用位置：</td>
                   <td height="30" align="left"><input type="text" id="ipAddress.ipUseRoom" name="ipAddress.ipUseRoom" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">申请单位全称：</td>
                   <td height="30" align="left"><input type="text" id="ipAddress.unitsFullName" name="ipAddress.unitsFullName" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">单位所在校内地址：</td>
                   <td height="30" align="left"><input type="text" id="ipAddress.unitsAddress" name="ipAddress.unitsAddress" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="8%" height="30" align="right">单位现有IP地址数量：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.unitsExtentIpCount" name="ipAddress.unitsExtentIpCount" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">单位现有IP地址用途：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.unitsExtentIpPurpose" name="ipAddress.unitsExtentIpPurpose" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">技术联系人：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="ipAddress.technicalContact" name="ipAddress.technicalContact" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">联系电话：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.phone" name="ipAddress.phone" style="width:85%;height=18;" ></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">电子邮箱：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.email" name="ipAddress.email" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">申请IP地址原因：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="ipAddress.applyIpCause" name="ipAddress.applyIpCause" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">单位负责人：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.unitsLeader" name="ipAddress.unitsLeader" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right" >申请人签名：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.application" name="ipAddress.application" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">申请日期：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="ipAddress.applicationDate" name="ipAddress.applicationDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 </tr>
                 
        

               </table></td>
             </tr>
           </table></td>
           </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
     <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
       <tr>
         <td width="5%" class="zcxx_tab1">&nbsp;</td>
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·以下由网络中心填写·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
       </tr>
       <tr>
         <td colspan="3" valign="top"><br></td>
       </tr>
       <tr>
         <td colspan="3">&nbsp;</td>
       </tr>
     </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·接收人相关信息·</td>
           <td width="75%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   
                   <td width="11%" height="30" align="right" >接收人：</td>
                   <td width="24%" height="30" align="left" ><input type="text" id="ipAddress.receiver" name="ipAddress.receiver" style="width:85%;height=18;" ></td>
                   <td width="32%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="11%" height="30" align="right" >接收日期：</td>
                   <td width="30%" height="30" align="left"><input type="text" id="ipAddress.receiveDate" name="ipAddress.receiveDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·完成人相关信息·</td>
           <td width="70%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="11%" height="30" align="right" >完成人：</td>
                   <td width="24%" height="30" align="left" ><input type="text" id="ipAddress.completer" name="ipAddress.completer" style="width:85%;height=18;"></td>
                   <td width="32%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="11%" height="30" align="right" >完成日期：</td>
                   <td width="30%" height="30" align="left"><input type="text" id="ipAddress.completeDate" name="ipAddress.completeDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 </tr>
               </table></td>
        
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
</tr></table></td></tr></table>

  <tr>
   <td width="14%" height="30" align="center" bgcolor="#FFFFFF" >
    <input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return dateJudge();"> 
	<input type="reset" value=" 重置 " class="mmBtn"> 
	<input type="button" value=" 返回列表 "  class="mmBtn" onClick="window.history.go(-1)">
   </td>
  </tr>
 

</s:form>	
	</body>
</html>