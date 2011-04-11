<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function checkuser(){	
		var tag = 1;
		var str = "";		
		var filename = document.getElementById("fileuser").value;	
		if(!filename){
			str = str + "请选择要导入的文件!";
			tag = 0;
		}else{
			var arys = filename.split('.');
			var suffix = arys[arys.length-1];
			var temp = "xls";
			if(suffix!=temp){
				str = str + "只能导入Excel文件!";
				tag = 0;
			}
		}
		if(tag==1){
			document.form2.submit();
		}else if(tag==0){
			alert(str);
		}
	}
	
	function changeuser(){
	  var filename = document.getElementById("fileuser").value;	
	  document.getElementById("fileNameuser").value=filename;
	}
	
	function downloaduser(){
	 document.location.href="../../assets/usertemplateDown.action";
	}
		
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">

<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="3%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="97%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">批量导入配置</td>
  </tr>
</table>
<table width="99%" border=0 align="center" cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
  <tr height=30>
   <th height="22" colspan="6" align="left" background="../../images/main20100521_58.gif" class="alllisttitle">用户信息导入</th>
  </tr>
  <tr height=30>
   <td width="7%" rowspan="2" align="right" bgcolor="#EBF4F5" style="padding-top: 17px; padding-right: 10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
    <td width="100%" colspan="5" nowrap bgcolor="#FFFFFF" style="padding: 10px">
      <table width="10%" border="0" cellspacing="0" cellpadding="0">
      <form name="form2" action="../../assets/importuser.action"  method="post" enctype="multipart/form-data" >
       <tr>
        <td>
         <table width="10%" border="0" cellspacing="1" cellpadding="2" style="background-color: #FFFFFF; border: 1px solid #7B9BB4;">
          <tr style="background: url(../img/feedback.gif); background-position: right bottom;">
           <td nowrap>
           <td nowrap style="padding-left: 10px; padding-right: 8px"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle">&nbsp;<b>选择文件：</b></td>
           <td nowrap>
           <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" bgcolor="#FFFFFF">
            <tr>
             <td style="border: 1px inset; padding-right: 1px">
              <input type="hidden" name="fileNameuser" id="fileNameuser">
              <input type="file" name="fileuser" id="fileuser" onChange="changeuser();" style="border: 1px solid white; font-size: 12px; width: 360px">
             </td>
            </tr>
           </table>
           </td>
           </tr>
          </table>
          </td>
          <td><img width="2"></td>
          <td height="100%">&nbsp;
           <input name="button" type="button" class="mmBtn" value="导 入" onClick="checkuser();">&nbsp;&nbsp;
           <input name="downloads" type="button" class="mmBtn" value="用户信息Excel模板下载" onClick="downloaduser();">
          </td>
         </tr>
         </form>
       </table>  
    </td>
    </tr>
    <tr height=30>
     <td nowrap colspan="6" bgcolor="#F9F9F9" style="color: #3A4E69; line-height: 24px; padding: 10px; padding-left: 16px"> 
      1、请先下载"Excel模板"并按要求填写"Excel模板"，再行导入。<br>
      2、"Excel模板"中的"登陆名称"为唯一名称，如与系统中名称重复此行信息将不被导入。<br>
      3、"Excel模板"中的"用户全名必须填写，如不填写此行信息将不能被导入。<br>
      4、其他字段如不填写将导入为默认值，已填写字段将于系统中的数据进行比对，如系统中没有配置该信息，此行信息将不被导入。<br>
      5、被导入用户的登陆密码默认为"111111"，管理员分配角色权限后此用户才能使用。<br>
     </td>
    </tr>
</table>
</body>
</html>

