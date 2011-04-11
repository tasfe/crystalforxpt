<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产新增</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <base target="_self">
        <script>
function init(){
var a="<s:property value="flag"/>";
if(a=="1"){
alert("更新成功！");
location.href="listinfo.action?id="+<s:property value="assetsType.id"/>;
}
}
function sub()
{
if(confirm("您确定要提交吗?"))
{
form1.action="updateinfo.action";
form1.submit();
document.getElementById("submit1").disabled=true;
}
}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onload="init();">
	<s:form action="" method="post" theme="simple" name="form1">

<input type="hidden" value='<s:property value="assetsType.id"/>' name="assetsTypeId">
 <table width="100%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../images/main20100521_58.gif" class="alllisttitle"><s:property value="assetsType.name"/>专有属性配置 </td>
 </tr>
 <tr>
   <td height="22" bgcolor="#FFFFFF"><table width="98%" align="center" cellpadding="0" cellspacing="0">
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
               <td valign="top" bgcolor="#EBF4FD"><table width="98%" border=0 align="center" cellpadding=0 cellspacing=0 class="zcxx_tab3">
                 <tr>
                   <input type="hidden" name="remark101" id="remark101">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark111"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark121" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark131" value="remark1">
                   <td width="10%" height="30" align="center" ><select name="remark141" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option> 
                   </select></td>
                   <td width="5%" rowspan="7" align="center"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="200" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="remark201" id="remark201">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark211"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark221" style="width:100%;height=18;" ></td>
                   <input type="hidden" name="remark231" value="remark2">
                   <td width="10%" height="30" align="center" ><select name="remark241" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <td width="5%" rowspan="7" align="center"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="200" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <input type="hidden" name="remark301" id="remark301">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark311"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark321" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark331" value="remark3">
                   <td width="10%" height="30" align="center" ><select name="remark341" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark401" id="remark401">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark411"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark421" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark431" value="remark4">
                   <td width="10%" height="30" align="center" ><select name="remark441" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark501" id="remark501">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark511"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark521" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark531" value="remark5">
                   <td width="10%" height="30" align="center" ><select name="remark541" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                   <input type="hidden" name="remark601" id="remark601">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark611"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark621" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark631" value="remark6">
                   <td width="10%" height="30" align="center" ><select name="remark641" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>   
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark701" id="remark701">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark711"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark721" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark731" value="remark7">
                   <td width="10%" height="30" align="center"  ><select name="remark741" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark801" id="remark801">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark811"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark821" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark831" value="remark8">
                   <td width="10%" height="30" align="center" ><select name="remark841" style="width:80%;height=18;">
                    <option value="1">输入框</option>
                    <option value="2">日期输入框</option>  
                   </select></td>
                   <input type="hidden" name="remark901" id="remark901">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark911"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark921" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark931" value="remark9">
                   <td width="10%" height="30" align="center" ><select name="remark941" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>  
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1001" id="remark1001">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1011"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1021" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1031" value="remark10">
                   <td width="10%" height="30" align="center" ><select name="remark1041" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1101" id="remark1101">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1111"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1121" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1131" value="remark11">
                   <td width="10%" height="30" align="center" ><select name="remark1141" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1201" id="remark1201">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1211"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1221" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1231" value="remark12">
                   <td width="10%" height="30" align="center" ><select name="remark1241" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1301" id="remark1301">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1311"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1321" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1331" value="remark13">
                   <td width="10%" height="30" align="center" ><select name="remark1341" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1401" id="remark1401">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1411"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1421" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1431" value="remark14">
                   <td width="10%" height="30" align="center" ><select name="remark1441" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1501" id="remark1501">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1511"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1521" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1531" value="remark15">
                   <td width="10%" height="30" align="center" ><select name="remark1541" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1601" id="remark1601">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1611"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1621" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1631" value="remark16">
                   <td width="10%" height="30" align="center" ><select name="remark1641" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1701" id="remark1701">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1711"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1721" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1731" value="remark17">
                   <td width="10%" height="30" align="center" ><select name="remark1741" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark1801" id="remark1801">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1811"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1821" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1831" value="remark18">
                   <td width="10%" height="30" align="center" ><select name="remark1841" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                 </tr>
                 <tr>
                 <input type="hidden" name="remark1901" id="remark1901">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark1911"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark1921" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark1931" value="remark19">
                   <td width="10%" height="30" align="center" ><select name="remark1941" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <input type="hidden" name="remark2001" id="remark2001">
                   <td width="1%" height="30" align="right"  ><input type="checkbox" name="remark2011"></td>
                   <td width="10%" height="30" align="center"  ><input type="text" name="remark2021" style="width:100%;height=18;"></td>
                   <input type="hidden" name="remark2031" value="remark20">
                   <td width="10%" height="30" align="center" ><select name="remark2041" style="width:80%;height=18;">
                     <option value="1">输入框</option>
                     <option value="2">日期输入框</option>
                   </select></td>
                   <td width="1%" height="30" align="right"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"  >&nbsp;</td>
                   <td width="10%" height="30" align="center"  >&nbsp;</td>
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
   </table></td></tr>
 </table>
 <div>
 <table width="100%">
  <tr>
   <td width="100%" height="30" align="center">
    <input type="button" value=" 保存 " id="submit1" name="submit1" class="mmBtn" onClick="sub();"> 
   </td>
  </tr>
 </table>
 </s:form>
</body>
<script>
<%List list=(List)ActionContext.getContext().getSession().get("assetsConfigList");
for(int i=0;i<list.size();i++){
AssetsConfig assetsConfig=(AssetsConfig)list.get(i);%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>0<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getId() %>";
<%if(assetsConfig.getIschoose()==1){%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>1<%=assetsConfig.getFlag() %>").checked="true";
<%}%>
document.getElementById("<%=assetsConfig.getConfigColumnName()%>2<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigName() %>";
document.getElementById("<%=assetsConfig.getConfigColumnName()%>3<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigColumnName()%>";
document.getElementById("<%=assetsConfig.getConfigColumnName()%>4<%=assetsConfig.getFlag() %>").value="<%=assetsConfig.getConfigStats() %>";
<%} %>
</script>
</html>