<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>域名申请新增</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
	// 创建XMLHTTPRequest对象   
 function getXMLHTTPRequest(){   
     var xmlHttp;   
    if(window.XMLHttpRequest){   
         xmlHttp=new XMLHttpRequest();  //非IE浏览器，用xmlhttprequest对象创建   
      }else if(window.ActiveXObject){   
         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");  //IE浏览器用activexobject对象创建   
      }   
    return xmlHttp;   
 }   
    
 var http;   
 function checkDomain(){   
    var username=document.forms[0]['domainregister.domain'].value;   
    http=getXMLHTTPRequest();   
    var url="systemmanage/domainregister/checkDomain.action?domainRegister.domain="+domain;   
    if(http){  //成功创建XMLHTTPRequest对象   
      http.open("GET",url,true); 　　//与服务端建立连接(请求方式post或get，地址,true表示异步)   
      http.onreadystatechange=callback;  //指定回调函数　　    
      http.send(null);  //发送请求   
  }   
}   
 function callback(){   
   if(http.readyState==4){   
     if(http.status==200){   
        processResponse();   
    }   
   }   
 }   
 //处理服务器端响应   
 function processResponse(){   
    var text=http.responseText;   
    if(text=="true"){   
        document.getElementById("error").innerHTML="域名已存在";   
     }else{   
        document.getElementById("error").innerHTML="";   
     }   
 }   
		</script>
	</head>
	
<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();">
<s:form action="user_save" method="post" theme="simple" namespace="/domainregister">
<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="ipAddress.id>"/>新增域名申请</td>
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
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·域名申请表相关信息·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td  height="30" align="right">申请域名：</td> 
                   <td  height="30" align="left"><input type="text" id="domainRegister.domain" name="domainRegister.domain" style="width:85%;height=18;" onBlur="checkDomain()">
                   <span id="error" style="color:red"></span></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">对应IP地址：</td>
                   <td  height="30" align="left"><input type="text" id="domainRegister.ipAddress" name="domainRegister.ipAddress" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">服务器存放位置：</td>
                   <td  height="30" align="left"><input type="text" id="domainRegister.serverLocation" name="domainRegister.serverLocation" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td height="30" align="right">申请单位全称：</td>
                   <td height="30" align="left"><input type="text" id="domainRegister.unitsFullName" name="domainRegister.unitsFullName" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">单位所在校内地址：</td>
                   <td height="30" align="left"><input type="text" id="domainRegister.unitsAddress" name="domainRegister.unitsAddress" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">联系电话：</td>
                   <td height="30" align="left"><input type="text" id="domainRegister.phone" name="domainRegister.phone" style="width:85%;height=18;" ></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">电子邮箱：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="domainRegister.email" name="domainRegister.email" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">技术联系人：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="domainRegister.technicalContact" name="domainRegister.technicalContact" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">单位负责人：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="domainRegister.unitsLeader" name="domainRegister.unitsLeader" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">申请人签名：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="domainRegister.application" name="domainRegister.application" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">申请日期：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="domainRegister.applicationDate" name="domainRegister.applicationDate" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">&nbsp;</td>
                   <td width="20%" height="30" align="left">&nbsp;</td>
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
           <td width="6%" class="zcxx_tab1">&nbsp;</td>
           <td width="24%" valign="bottom" class="alllisttitle" align="center">·此处请说明申请原因以及未能在上表中填写的需要说明的事项例如撤销原域名等·</td>
           <td width="70%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="8%" height="30" align="right" ></td>
                   <td width="84%" height="30" align="left" ><input type="text" id="domainRegister.remark" name="domainRegister.remark" style="width:100%;height=80;"></td>
                   <td width="8%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
              
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
	<input type="button" value=" 返回 "  class="mmBtn" onClick="window.history.go(-1)">
   </td>
  </tr>
 
</s:form>	
	</body>
</html>