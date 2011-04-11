<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function check(){	
		var tag = 1;
		var str = "";		
		var filename = document.getElementById("file").value;	
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
		   
			document.form1.submit();
			outs();
		}else if(tag==0){
			alert(str);
		}
	}	
	
	
	function change(){
	  var filename = document.getElementById("file").value;	
	  document.getElementById("fileName").value=filename;
	}
	
	
	function download(){
	  document.location.href="../../assets/templateDown.action";
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
   <th height="22" colspan="6" align="left" background="../../images/main20100521_58.gif" class="alllisttitle">资产信息导入</th>
  </tr>
  <tr height=30>
   <td width="7%" rowspan="2" align="right" bgcolor="#EBF4F5" style="padding-top: 17px; padding-right: 10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
    <td width="100%" colspan="5" nowrap bgcolor="#FFFFFF" style="padding: 10px">
      <table width="10%" border="0" cellspacing="0" cellpadding="0">
      <form name="form1" action="../../assets/import.action"  method="post" enctype="multipart/form-data" >
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
              <input type="hidden" name="fileName" id="fileName">
              <input type="file" name="file" onChange="change();" style="border: 1px solid white; font-size: 12px; width: 360px">
             </td>
            </tr>
           </table>
           </td>
           </tr>
          </table>
          </td>
          <td><img width="2"></td>
          <td height="100%">&nbsp;
          <tags:button code="import" menu="13">
           <input name="button" type="button" class="mmBtn" value="导 入" onClick="check();">&nbsp;&nbsp;
          </tags:button>
          <tags:button code="download" menu="13">	                               
           <input name="downloads" type="button" class="mmBtn" value="资产信息Excel模板下载" onClick="download();">
          </tags:button>	
          </td>
         </tr>
         </form>
       </table>    
    </td>
    </tr>
    <tr height=30>
     <td nowrap colspan="6" bgcolor="#F9F9F9" style="color: #3A4E69; line-height: 24px; padding: 10px; padding-left: 16px"> 
      1、请先下载"Excel模板"并按要求填写"Excel模板"，再行导入。<br>
      2、"Excel模板"中的"资产编码"为唯一编码，如与原有编码重复此行信息将不被导入。<br>
      3、"Excel模板"中的"采购日期、出厂日期、入库日期"日期类型字段请按"YYYY-MM-DD"格式填写，货币类型字段请填写数字形式。<br>
      4、其他字段如不填写将导入为"空"，已填写字段将于系统中的数据进行比对，如系统中没有配置该信息，此行信息将不被导入。<br>
      5、"Excel模板"中的"使用人、责任人"请填写系统中的员工登陆名。<br>
      6、资产的使用部门、责任部门与使用人、责任人相关。
     </td>
    </tr>
</table>
</body>
</html>

