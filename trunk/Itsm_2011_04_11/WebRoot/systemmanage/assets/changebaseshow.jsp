<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产信息查看</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<base target="_self">
<script type="text/javascript">
function back()
{
document.location.href="list.action";
}
function download(id){
document.location.href="downloads.action?ids="+id;
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0">
<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="assets.name"/></td>
 </tr>
 <tr>
   <td height="22" bgcolor="#FFFFFF">
   <table width="98%" align="center" cellpadding="0" cellspacing="0">
     <tr>
       <td valign="top"><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·资产信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td  height="30" align="right">资产编号：</td> 
                   <td  height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.codeId"/></td></tr>
                   </table>
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">资产名称：</td>
                   <td  height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.name"/></td></tr>
                   </table>
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">资产型号：</td>
                   <td  height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.model"/></td></tr>
                   </table>
                   </td>
                 </tr>
                 <tr>
                   <td height="30" align="right">资产类别：</td>
                   <td height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.assetsType.name"/></td></tr>
                   </table>
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">运维类别：</td>
                   <td height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.itsmType.name"/></td></tr>
                   </table>
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">资产状态：</td>
                   <td height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.assetsState.name"/></td></tr>
                   </table>
                   </td>
                   
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">保修年限(月)：</td>
                   <td width="20%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.qualityTime"/></td></tr>
                   </table>
                   </td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="14%" height="30" align="right">资产单价：</td>
                   <td width="16%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.price"/></td></tr>
                   </table>
                   </td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="14%" height="30" align="right">采购日期：</td>
                   <td width="16%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:date name='assets.buyDate' format='yyyy-MM-dd'/></td></tr>
                   </table>
                   </td>
                  
                 </tr>
                 <tr>
                    <td width="14%" height="30" align="right">出厂日期：</td>
                   <td width="16%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:date name='assets.exitfacotryDate' format='yyyy-MM-dd'/></td></tr>
                   </table>
                   </td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                    <td width="14%" height="30" align="right">入库日期：</td>
                   <td width="20%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:date name='assets.inDate' format='yyyy-MM-dd'/></td></tr>
                   </table>
                   </td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                    <td width="14%" height="30" align="right">地理区域：</td>
                   <td width="20%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.location.name"/></td></tr>
                   </table>
                   </td>
                </tr>
                <tr>
                   <td width="14%" height="30" align="right">存放位置：</td>
                   <td width="16%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.buildlocation.allname"/></td></tr>
                   </table>
                   </td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
<%List list1=(List)ActionContext.getContext().getSession().get("assetsConfigList");
  for(int i=0;i<list1.size();i++){
  AssetsConfig assetsConfig=(AssetsConfig)list1.get(i);
  if(assetsConfig.getConfigColumnName().indexOf("remark")>=0||assetsConfig.getConfigColumnName().equals("mac")||assetsConfig.getConfigColumnName().equals("devicename")||assetsConfig.getConfigColumnName().equals("ip")||assetsConfig.getConfigColumnName().equals("system")){
  if(i%3==0){%>
         <tr>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
            <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>0"></div></td></tr>
            </table>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>	
  <%}else if(i%3==1){%>
            <td width="14%" height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
		    <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>0"></div></td></tr>
            </table>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>
  <%}else if(i%3==2){%>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
		    <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>0"></div></td></tr>
            </table>
            </td>
		   </tr>
  <%}}}%>

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
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·资产附件·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
       </tr>
       <tr>
         <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
           <tr>
             <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
               <tr>
                 <td width="10%" height="30" align="right" >上传文件：</td>
                 <td width="90%" height="30" align="left" >
                 
                 <table width="97%" border="1" cellspacing="0" cellpadding="0"  style="border:#C3BFB3 1px solid; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px; background-color:#FFF" class="zcxx_tab3">
                   
                   <tr>
                     <td height="20" bgcolor="#FFFFFF">
                     <table width="100%" border="0" cellspacing="1" cellpadding="4">
                       <tr>
                          <td valign="top"  colspan="2">
                           <s:iterator value="accessoryList" var="accessory">
                           <div style="background-color:#E7EBF7">
                           <font size="2" style="width:300px"><a href="#" onClick="download(<s:property value="id"/>);"><s:property value="trueName" /></a></font>
                           </div>
                           </s:iterator>
                          </td>
                       </tr>
                     </table></td>
                   </tr>
                 </table>
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
       <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="49%"><table width="99%" align="center" cellpadding="0" cellspacing="0">
             <tr>
               <td width="5%" class="zcxx_tab1">&nbsp;</td>
               <td width="20%" valign="bottom" class="alllisttitle" align="center">·供应商·</td>
               <td width="75%" class="zcxx_tab1">&nbsp;</td>
             </tr>
             <tr>
               <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
                 <tr>
                   <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                     <tr>
                       <td width="11%" height="30" align="right" >供应商名称：</td>
                       <td width="24%" height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerBySupportId.name"/></td>
                           </tr>
                       </table></td>
                       </tr>
                     <tr>
                       <td height="30" align="right" >类型：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td>
                            <s:if test="assets.assetsProducerBySupportId.type==1">供应商</s:if>
          					<s:elseif test="assets.assetsProducerBySupportId.type==2">厂商</s:elseif>
          					<s:elseif test="assets.assetsProducerBySupportId.type==3">二者都是</s:elseif>
                           </td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >星级级别：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td>
                            <s:if test="assets.assetsProducerBySupportId.level==1">*</s:if>
          					<s:elseif test="assets.assetsProducerBySupportId.level==2">**</s:elseif>
          					<s:elseif test="assets.assetsProducerBySupportId.level==3">***</s:elseif>
          					<s:elseif test="assets.assetsProducerBySupportId.level==4">****</s:elseif>
          					<s:elseif test="assets.assetsProducerBySupportId.level==5">*****</s:elseif>
                          </td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >联系人：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerBySupportId.persion"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >联系人电话：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerBySupportId.persionTel"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >售后服务电话：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerBySupportId.tel"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >地址：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerBySupportId.address"/></td>
                         </tr>
                       </table></td>
                     </tr>
                   </table></td>
                 </tr>
               </table></td>
             </tr>
             <tr>
               <td colspan="3">&nbsp;</td>
             </tr>
           </table></td>
           <td>&nbsp;</td>
           <td width="49%"><table width="99%" align="center" cellpadding="0" cellspacing="0">
             <tr>
               <td width="5%" class="zcxx_tab1">&nbsp;</td>
               <td width="20%" valign="bottom" class="alllisttitle" align="center">·制造商·</td>
               <td width="75%" class="zcxx_tab1">&nbsp;</td>
             </tr>
             <tr>
               <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
                 <tr>
                   <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                     <tr>
                       <td width="11%" height="30" align="right" >制造商名称：</td>
                       <td width="24%" height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerByProduceId.name"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >类型：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td>
                            <s:if test="assets.assetsProducerByProduceId.type==1">供应商</s:if>
          					<s:elseif test="assets.assetsProducerByProduceId.type==2">厂商</s:elseif>
          					<s:elseif test="assets.assetsProducerByProduceId.type==3">二者都是</s:elseif>
                           </td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >星级级别：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td>
                            <s:if test="assets.assetsProducerByProduceId.level==1">*</s:if>
          					<s:elseif test="assets.assetsProducerByProduceId.level==2">**</s:elseif>
          					<s:elseif test="assets.assetsProducerByProduceId.level==3">***</s:elseif>
          					<s:elseif test="assets.assetsProducerByProduceId.level==4">****</s:elseif>
          					<s:elseif test="assets.assetsProducerByProduceId.level==5">*****</s:elseif>
                           </td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >联系人：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerByProduceId.persion"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >售后服务电话：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerByProduceId.persionTel"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >联系人电话：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerByProduceId.tel"/></td>
                         </tr>
                       </table></td>
                     </tr>
                     <tr>
                       <td height="30" align="right" >地址：</td>
                       <td height="30" align="left" ><table width="96%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                         <tr>
                           <td><s:property value="assets.assetsProducerByProduceId.address"/></td>
                         </tr>
                       </table></td>
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
         </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·责任信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="14%" height="30" align="right">责任人姓名：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByChargeId.truename"/></td></tr>
                   </table>                   </td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right">使用人姓名：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByUsersId.truename"/></td></tr>
                   </table>                   </td>
                   
                   <td width="3%" align="left">&nbsp;</td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">责任人电话：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByChargeId.phone"/></td></tr>
                   </table>                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right">使用人电话：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByUsersId.phone"/></td></tr>
                   </table>                   </td>
                   
                   
                   <td width="3%" align="left">&nbsp;</td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">责任人手机：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByChargeId.cellphone"/></td></tr>
                   </table>                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right">使用人手机：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByUsersId.cellphone"/></td></tr>
                   </table>                   </td>
                   
                   
                   <td width="3%" align="left">&nbsp;</td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">责任人邮箱：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByChargeId.email"/></td></tr>
                   </table>                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right">使用人邮箱：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByUsersId.email"/></td></tr>
                   </table>                   </td>
                   
                   <td width="3%" align="left">&nbsp;</td>
                 </tr>
                 
                 <tr>
                   <td width="14%" height="30" align="right">责任部门：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByChargeId.department.name"/></td></tr>
                   </table>                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right">使用部门：</td>
                   <td width="26%" height="30" align="left">
                   <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
                     <tr><td><s:property value="assets.usersByUsersId.department.name"/></td></tr>
                   </table>                   </td>
                   
                   <td width="3%" align="left">&nbsp;</td>
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
     <%List lists=(List)ActionContext.getContext().getSession().get("assetsConfigInfoList");
     if(lists.size()!=0){ %>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·专有信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
<%for(int i=0;i<lists.size();i++){
  AssetsConfig assetsConfig=(AssetsConfig)lists.get(i);
  if(i%3==0){%>
         <tr>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
             <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>1"></div></td></tr>
             </table>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>	
  <%}else if(i%3==1){%>
            <td width="14%" height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
		     <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>1"></div></td></tr>
             </table>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>
  <%}else if(i%3==2){%>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
		     <table width="85%"  cellspacing="0" cellpadding="0" class="zcgl_cktab">
              <tr><td><div id="<%=assetsConfig.getConfigColumnName() %>1"></div></td></tr>
             </table>
            </td>
		   </tr>
  <%}}%>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <%} %>
     
     
     
     
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·变更信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=1 bgcolor="#b5d6e6" class="zcxx_tab3">
                 <tr>
                   <td width="5%" align="center" >序号</td>
                   <td width="8%" align="center" >变更类型</td>
                   <td width="7%" align="center" >责任人</td>
                   <td width="7%" align="center" >责任部门</td>
                   <td width="7%" align="center" >经办人</td>
                   <td width="7%" align="center" >使用人</td>
                   <td width="7%" align="center" >使用部门</td>
                   <td width="8%" align="center" >变更日期</td>
                   <td align="center" >变更描述</td>
                 </tr>
                 <s:iterator value="changelist" var="assetsChange" status="index">
                 <tr>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="#index.index+1"/></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="assetsState.name" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="usersByChargeid.truename" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="usersByChargeid.department.name" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="usersByManagersid.truename" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="usersByUserid.truename" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="usersByUserid.department.name" /></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:date name='changeTime' format='yyyy-MM-dd'/></td>
                   <td align="center" bgcolor="#FFFFFF" style="font-weight:bold;"><s:property value="changeDescription" /></td>
                 </tr>
                 </s:iterator>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     
     
     
     
     
   </table></td></tr>
 </table>
 <div>
 <table width="100%">
  <tr>
   <td width="100%" height="30" align="center" bgcolor="#FFFFFF" >
	<input type="button" value=" 返回列表 "  class="mmBtn" onClick="back();">
   </td>
  </tr>
 </table>

<script language='javascript'>
if(document.getElementById("system0")){
var system0=document.getElementById("system0");
system0.insertAdjacentHTML("beforeend","<s:property value="assets.system"/>");
}
if(document.getElementById("ip0")){
var ip0=document.getElementById("ip0");
ip0.insertAdjacentHTML("beforeend","<s:property value="assets.ip"/>");
}
if(document.getElementById("mac0")){
var pn0=document.getElementById("mac0");
pn0.insertAdjacentHTML("beforeend","<s:property value="assets.mac"/>");
}
if(document.getElementById("devicename0")){
var sn0=document.getElementById("devicename0");
sn0.insertAdjacentHTML("beforeend","<s:property value="assets.devicename"/>");
}
if(document.getElementById("remark10")){
  var remark10=document.getElementById("remark10");
　remark10.insertAdjacentHTML("beforeend","<s:property value="assets.remark1"/>");
}
if(document.getElementById("remark20")){
  var remark20=document.getElementById("remark20");
　remark20.insertAdjacentHTML("beforeend","<s:property value="assets.remark2"/>");
}
if(document.getElementById("remark30")){
 var remark30=document.getElementById("remark30");
 remark30.insertAdjacentHTML("beforeend","<s:property value="assets.remark3"/>");
}
if(document.getElementById("remark40")){
  var remark40=document.getElementById("remark40");
　remark40.insertAdjacentHTML("beforeend","<s:property value="assets.remark4"/>");
}
if(document.getElementById("remark50")){
 var remark50=document.getElementById("remark50");
 remark50.insertAdjacentHTML("beforeend","<s:property value="assets.remark5"/>");
}
if(document.getElementById("remark60")){
 var remark60=document.getElementById("remark60");
 remark60.insertAdjacentHTML("beforeend","<s:property value="assets.remark6"/>");
}
if(document.getElementById("remark70")){
 var remark70=document.getElementById("remark70");
　remark70.insertAdjacentHTML("beforeend","<s:property value="assets.remark7"/>");
}
if(document.getElementById("remark80")){
 var remark80=document.getElementById("remark80");
 remark80.insertAdjacentHTML("beforeend","<s:property value="assets.remark8"/>");
}
if(document.getElementById("remark90")){
 var remark90=document.getElementById("remark90");
　remark90.insertAdjacentHTML("beforeend","<s:property value="assets.remark9"/>");
}
if(document.getElementById("remark100")){
 var remark100=document.getElementById("remark100");
 remark100.insertAdjacentHTML("beforeend","<s:property value="assets.remark10"/>");
}
if(document.getElementById("remark110")){
  var remark10=document.getElementById("remark110");
　remark10.insertAdjacentHTML("beforeend","<s:property value="assets.remark11"/>");
}
if(document.getElementById("remark120")){
  var remark20=document.getElementById("remark120");
　remark20.insertAdjacentHTML("beforeend","<s:property value="assets.remark12"/>");
}
if(document.getElementById("remark130")){
 var remark30=document.getElementById("remark130");
 remark30.insertAdjacentHTML("beforeend","<s:property value="assets.remark13"/>");
}
if(document.getElementById("remark140")){
  var remark40=document.getElementById("remark140");
　remark40.insertAdjacentHTML("beforeend","<s:property value="assets.remark14"/>");
}
if(document.getElementById("remark150")){
 var remark50=document.getElementById("remark150");
 remark50.insertAdjacentHTML("beforeend","<s:property value="assets.remark15"/>");
}
if(document.getElementById("remark160")){
 var remark60=document.getElementById("remark160");
 remark60.insertAdjacentHTML("beforeend","<s:property value="assets.remark16"/>");
}
if(document.getElementById("remark170")){
 var remark70=document.getElementById("remark170");
　remark70.insertAdjacentHTML("beforeend","<s:property value="assets.remark17"/>");
}
if(document.getElementById("remark180")){
 var remark80=document.getElementById("remark180");
 remark80.insertAdjacentHTML("beforeend","<s:property value="assets.remark18"/>");
}
if(document.getElementById("remark190")){
 var remark90=document.getElementById("remark190");
　remark90.insertAdjacentHTML("beforeend","<s:property value="assets.remark19"/>");
}
if(document.getElementById("remark200")){
 var remark100=document.getElementById("remark200");
 remark100.insertAdjacentHTML("beforeend","<s:property value="assets.remark20"/>");
}
if(document.getElementById("remark210")){
  var remark10=document.getElementById("remark210");
　remark10.insertAdjacentHTML("beforeend","<s:property value="assets.remark21"/>");
}
if(document.getElementById("remark220")){
  var remark20=document.getElementById("remark220");
　remark20.insertAdjacentHTML("beforeend","<s:property value="assets.remark22"/>");
}
if(document.getElementById("remark230")){
 var remark30=document.getElementById("remark230");
 remark30.insertAdjacentHTML("beforeend","<s:property value="assets.remark23"/>");
}
if(document.getElementById("remark240")){
  var remark40=document.getElementById("remark240");
　remark40.insertAdjacentHTML("beforeend","<s:property value="assets.remark24"/>");
}
if(document.getElementById("remark250")){
 var remark50=document.getElementById("remark250");
 remark50.insertAdjacentHTML("beforeend","<s:property value="assets.remark25"/>");
}
if(document.getElementById("remark260")){
 var remark60=document.getElementById("remark260");
 remark60.insertAdjacentHTML("beforeend","<s:property value="assets.remark26"/>");
}
if(document.getElementById("remark270")){
 var remark70=document.getElementById("remark270");
　remark70.insertAdjacentHTML("beforeend","<s:property value="assets.remark27"/>");
}
if(document.getElementById("remark280")){
 var remark80=document.getElementById("remark280");
 remark80.insertAdjacentHTML("beforeend","<s:property value="assets.remark28"/>");
}
if(document.getElementById("remark290")){
 var remark90=document.getElementById("remark290");
　remark90.insertAdjacentHTML("beforeend","<s:property value="assets.remark29"/>");
}
if(document.getElementById("remark300")){
 var remark100=document.getElementById("remark300");
 remark100.insertAdjacentHTML("beforeend","<s:property value="assets.remark30"/>");
}




if(document.getElementById("remark11")){
 var remark11=document.getElementById("remark11");
 remark11.insertAdjacentHTML("beforeend","<s:property value="info.remark1"/>");
}
if(document.getElementById("remark21")){
 var remark21=document.getElementById("remark21");
 remark21.insertAdjacentHTML("beforeend","<s:property value="info.remark2"/>");
}
if(document.getElementById("remark31")){
 var remark31=document.getElementById("remark31");
 remark31.insertAdjacentHTML("beforeend","<s:property value="info.remark3"/>");
}
if(document.getElementById("remark41")){
 var remark41=document.getElementById("remark41");
 remark41.insertAdjacentHTML("beforeend","<s:property value="info.remark4"/>");
}
if(document.getElementById("remark51")){
 var remark51=document.getElementById("remark51");
 remark51.insertAdjacentHTML("beforeend","<s:property value="info.remark5"/>");
}
if(document.getElementById("remark61")){
 var remark61=document.getElementById("remark61");
 remark61.insertAdjacentHTML("beforeend","<s:property value="info.remark6"/>");
}
if(document.getElementById("remark71")){
 var remark71=document.getElementById("remark71");
 remark71.insertAdjacentHTML("beforeend","<s:property value="info.remark7"/>");
}
if(document.getElementById("remark81")){
 var remark81=document.getElementById("remark81");
 remark81.insertAdjacentHTML("beforeend","<s:property value="info.remark8"/>");
}
if(document.getElementById("remark91")){
 var remark91=document.getElementById("remark91");
 remark91.insertAdjacentHTML("beforeend","<s:property value="info.remark9"/>");
}
if(document.getElementById("remark101")){
 var remark101=document.getElementById("remark101");
 remark101.insertAdjacentHTML("beforeend","<s:property value="info.remark10"/>");
}
if(document.getElementById("remark111")){
 var remark111=document.getElementById("remark111");
 remark111.insertAdjacentHTML("beforeend","<s:property value="info.remark11"/>");
}
if(document.getElementById("remark121")){
 var remark121=document.getElementById("remark121");
 remark121.insertAdjacentHTML("beforeend","<s:property value="info.remark12"/>");
}
if(document.getElementById("remark131")){
 var remark131=document.getElementById("remark131");
 remark131.insertAdjacentHTML("beforeend","<s:property value="info.remark13"/>");
}
if(document.getElementById("remark141")){
 var remark141=document.getElementById("remark141");
 remark141.insertAdjacentHTML("beforeend","<s:property value="info.remark14"/>");
}
if(document.getElementById("remark151")){
 var remark151=document.getElementById("remark151");
 remark151.insertAdjacentHTML("beforeend","<s:property value="info.remark15"/>");
}
if(document.getElementById("remark161")){
 var remark161=document.getElementById("remark161");
 remark161.insertAdjacentHTML("beforeend","<s:property value="info.remark16"/>");
}
if(document.getElementById("remark171")){
 var remark171=document.getElementById("remark171");
 remark171.insertAdjacentHTML("beforeend","<s:property value="info.remark17"/>");
}
if(document.getElementById("remark181")){
 var remark181=document.getElementById("remark181");
 remark181.insertAdjacentHTML("beforeend","<s:property value="info.remark18"/>");
}
if(document.getElementById("remark191")){
 var remark191=document.getElementById("remark191");
 remark191.insertAdjacentHTML("beforeend","<s:property value="info.remark19"/>");
}
if(document.getElementById("remark201")){
 var remark201=document.getElementById("remark201");
 remark201.insertAdjacentHTML("beforeend","<s:property value="info.remark20"/>");
}

</script>	
	</body>
</html>