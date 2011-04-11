<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>域名申请更新</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();">
	
	<s:form action="update" method="post" theme="simple" >
				<s:hidden id="domainRegister.id" name="domainRegister.id"></s:hidden>
				<s:hidden id="domainRegister.serialNumber" name="domainRegister.serialNumber"></s:hidden>
					<s:hidden id="domainRegister.domain" name="domainRegister.domain"></s:hidden>
					<s:hidden id="domainRegister.ipAddress" name="domainRegister.ipAddress"></s:hidden>
					<s:hidden id="domainRegister.serverLocation" name="domainRegister.serverLocation"></s:hidden>
					
					<s:hidden id="domainRegister.unitsFullName" name="domainRegister.unitsFullName"></s:hidden>
					<s:hidden id="domainRegister.unitsAddress" name="domainRegister.unitsAddress"></s:hidden>
					<s:hidden id="domainRegister.technicalContact" name="domainRegister.technicalContact"></s:hidden>
					<s:hidden id="domainRegister.phone" name="domainRegister.phone"></s:hidden>
					<s:hidden id="domainRegister.email" name="domainRegister.email"></s:hidden>
					<s:hidden id="domainRegister.remark" name="domainRegister.remark"></s:hidden>
					<s:hidden id="domainRegister.unitsLeader" name="domainRegister.unitsLeader"></s:hidden>
					<s:hidden id="domainRegister.application" name="domainRegister.application"></s:hidden>
					<s:hidden id="domainRegister.applicationDate" name="domainRegister.applicationDate"></s:hidden>
				
					
	
<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="assetsType.name"/>审批IP地址申请</td>
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
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·域名注册申请信息·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td  height="30" align="right">申请域名：</td> 
                   <td  height="30" align="left" ><s:property value="domainRegister.domain"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">对应IP地址：</td>
                   <td  height="30" align="left">     <s:property value="domainRegister.ipAddress"/> </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">服务器存放位置：</td>
                   <td  height="30" align="left"><s:property value="domainRegister.serverLocation"/></td>
                 </tr>
                 <tr>
                   <td height="30" align="right">申请单位全称：</td>
                   <td height="30" align="left"><s:property value="domainRegister.unitsFullName"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">单位所在校内地址：</td>
                   <td height="30" align="left"><s:property value="domainRegister.unitsAddress"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">联系电话：</td>
                   <td height="30" align="left"><s:property value="domainRegister.phone"/></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">电子邮箱：</td>
                   <td width="16%" height="30" align="left"><s:property value="domainRegister.email"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">技术联系人：</td>
                   <td width="16%" height="30" align="left"><s:property value="domainRegister.technicalContact"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">单位负责人：</td>
                   <td width="20%" height="30" align="left"><s:property value="domainRegister.unitsLeader"/></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">申请人签名：</td>
                   <td width="16%" height="30" align="left"><s:property value="domainRegister.application"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">申请日期：</td>
                   <td width="16%" height="30" align="left"><s:date name="domainRegister.applicationDate"  format="yyyy-MM-dd"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
            
              

               </table></td>
             </tr>
           </table></td>
           </tr>
        
       </table></td>
     </tr></table></td></tr>
     
            
         <tr>
           <td colspan="3" width="7">&nbsp;</td>
         </tr>   
       
              <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="6%" class="zcxx_tab1">&nbsp;</td>
           <td width="24%" valign="bottom" class="alllisttitle" align="center">·申请原因以及未填写的需要说明的事项·</td>
           <td width="70%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="8%" height="30" align="right" ></td>
                   <td width="84%" height="30" align="left" ><s:property value="domainRegister.remark"/></td>
                   <td width="8%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                          </table></td></tr>
                 </table></td></tr>
                </table></td></tr></table>
     
      <tr>
           <td colspan="3" width="7">&nbsp;</td>
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
     </table></td>
     </tr>
     
     
          <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·审批意见·</td>
           <td width="75%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="18%" align="center"><input type="radio" name="domainRegister.approvalResult" value="1" checked="checked">同意
                 </td>
                   <td width="16%"><input type="radio" name="domainRegister.approvalResult" value="0">不同意</td>
                   <td width="25%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="69%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="15%" height="10" align="right" >
                      意见（不同意时填写）：
                       <td width="30%" height="10" align="left"><s:textfield id="domainRegister.failReason" name="domainRegister.failReason" style="width:90%;"/>
          					</td>
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
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·接收人相关信息·</td>
           <td width="75%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   
                   <td width="11%" height="15" align="right" >接收人：</td>
                   <td width="24%" height="15" align="left" ><s:textfield id="domainRegister.receiver" name="domainRegister.receiver" /></td>
                   <td width="32%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="69%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="11%" height="10" align="right" >接收日期：</td>
                   <td width="30%" height="10" align="left"><input type="text" id="domainRegister.receiveDate" name="domainRegister.receiveDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
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
                   <td width="11%" height="10" align="right" >完成人：</td>
                   <td width="24%" height="10" align="left" ><s:textfield id="domainRegister.completer" name="domainRegister.completer" /></td>
                   <td width="32%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="11%" height="10" align="right" >完成日期：</td>
                   <td width="30%" height="10" align="left"><input type="text" id="domainRegister.completeDate" name="domainRegister.completeDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
  <tr>
   <td width="14%" height="30" align="center" bgcolor="#FFFFFF" >
    <input type="submit" value=" 审批" class="mmBtn" onClick="javascript:return dateJudge();"> 
	<input type="reset" value=" 重置 " class="mmBtn"> 
	<input type="button" value=" 返回 "  class="mmBtn" onClick="window.history.go(-1)">
   </td>
  </tr>
 </table>
 </td>
 </tr></table>

</s:form>	
	</body>
</html>