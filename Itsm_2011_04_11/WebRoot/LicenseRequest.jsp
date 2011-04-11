<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>获取许可证</title>
<link href="css/Default.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='dwr/interface/LicenseInfo.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<style>
body {
	background-color:#F6FAFF
}
h4 {
	color:#226699
}
</style>
<script>
alert('没有授权许可，请购买!');
function getRequestLicense(){
	var customer = document.getElementById("Customer").value;
	if(!customer){
		alert("请先填写被授权单位/个人");
	}else{
		LicenseInfo.getLicenseRequest(customer,callBack);
	}
}
function callBack(data){
	document.getElementById("Request").value = data;
}

function saveLicense(){
	var customer = document.getElementById("ResponseLicense").value;
	if(!customer){
		alert('请填写许可证');
	}else{
		document.getElementById("form1").action="<%=request.getContextPath()%>/license/saveLicense.action";
		document.getElementById("form1").submit();
	}

}
</script>
</head>
<body >
<form name="form1" action="saveLicense" method="post" namespace="/license">
  <table width="100%" height="100%">
    <tr>
      <td align="center" valign="middle">
      <div style="margin:0 auto; background-color:#fff; padding:1px; border:1px solid #d2dbe5; width:660px;">
          <table width="100%" border="0" cellspacing="0" cellpadding="3">
            <tr>
              <td colspan="2" height="30" align="center" style="background-color:#EAF0F6" ><h4>获取许可证</h4></td>
            </tr>
            <tr>
              <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
              <td width="18%" align="right" style="height:30">被授权单位/个人：</td>
              <td width="82%" align="left"><input name="Customer"
							type="text" id="Customer" size="50"
							value="">
                <input type="button"
							name="Submit" value="生成请求" onClick="getRequestLicense();"></td>
            </tr>
            <tr>
              <td align="right" style="height:100px">许可证请求：</td>
              <td align="left"><textarea name="RequestLicense" wrap="virtual"
							id="Request" style="width:500px;height:100px"></textarea></td>
            </tr>
            <tr>
              <td align="right">许可证：</td>
              <td align="left"><textarea name="ResponseLicense" id="Response"
							style="width:500px;height:100px"></textarea></td>
            </tr>
            <tr>
              <td colspan="2" align="center"><input
							type="button" name="Submit2" onClick="saveLicense();"
							value="保存许可证"></td>
            </tr>
            <tr>
              <td colspan="2">&nbsp;</td>
            </tr>
          </table>
        </div></td>
    </tr>
  </table>
</form>
</body>
</html>
