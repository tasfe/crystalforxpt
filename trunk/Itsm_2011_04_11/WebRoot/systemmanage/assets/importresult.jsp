<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>    
    <title>导入结果</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function excel(){
   document.form1.action="excelDown.action";
   document.form1.method="post";
   document.form1.submit();
}

function back(){
location.href="./../systemmanage/assets/importassets.jsp"; 
}
</script>
</head>
<style>
th,td{white-space:nowrap; padding:0px;}
</style>
<body style="overflow-x:auto; overflow-y:auto;" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >


<table width="100%" cellpadding=0 cellspacing=0>
<tr><td colspan="2"><table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td width="2%" height="22" align="left" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%"  background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;font-size:12px;">导入结果:</td>
  </tr>

</table></td></tr>
<s:if test="#request.table==''">
 <tr>
    <td height="auto" colspan="2" align=right><table cellpadding="0" cellspacing="1" style="width:100%;">
        <tr>
          <td style="background:url(../images/main20100521_58.gif); height:22px; font-size:12px; color:#003366; border:1px solid #b5d6e6"><table width="100%" cellpadding="0" cellspacing="0">
              <tr>
                <td style="line-height:22px; text-align:center; font-size:12px; color:#ff0000"><strong>导入完成</strong></td>
              </tr>
            </table>          </td>
        </tr>
  </tr>
<tr>
<td height="26" align="center"><input class="mmBtn" type="button" onClick="back();" value="返回" style="background:url(../images/main20100521_58.gif);border:1px solid #b5d6e6; font-size:12px;"/></td>
</tr>
</s:if>
<s:if test="#request.table!=''">
<s:form action="" method='post' theme="simple" name="form1">
<tr>
<td height="26"><input class="mmBtn" type="button" onClick="excel();" value="导出错误信息" style="background:url(../images/main20100521_58.gif);border:1px solid #b5d6e6; font-size:12px;"/>  <input class="mmBtn" type="button" onClick="back();" value="返回" style="background:url(../images/main20100521_58.gif);border:1px solid #b5d6e6; font-size:12px;"/></td>
</tr>

  <tr>
    <td height="auto" colspan="2" align=right><table cellpadding="0" cellspacing="1" style="width:100%;">
        <tr>
          <td style="background:url(../images/main20100521_58.gif); height:22px; font-size:12px; color:#003366; border:1px solid #b5d6e6"><table width="100%" cellpadding="0" cellspacing="0">
              <tr>
                <td style="line-height:22px; text-align:center; font-size:12px; color:#ff0000"><strong>导入失败信息</strong></td>
              </tr>
            </table>          </td>
        </tr>
        <tr>
          <td style="font-size:12px; height:30px; text-align:left; "><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" >
            <s:property value="#request.title" escape="false"/> 
            <s:property value="#request.table" escape="false"/> 
          </table></td>
        </tr>
      </table></td>
  </tr>
  </s:form>
</s:if>
</table>


  </body>
</html>
