<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>获取许可证</title>
<link href="<%=request.getContextPath() %>/css/Default.css" rel="stylesheet" type="text/css">
<style>
body {
	background-color:#F6FAFF
}
h4 {
	color:#226699
}
</style>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0" style="border-collapse: separate; border-spacing: 6px;">
  <tr valign="top">
    <td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">
      <tr>
        <td valign="center" class="blockTd">系统信息</td>
      </tr>
      <tr>
        <td valign="middle" align="center"><font color="red"><s:actionmessage/></font></td>
      </tr>
      <tr>
        <td style="padding:0"><table width="100%" border="0" cellspacing="6" style="border-collapse: separate; border-spacing: 6px;">
          <tr>
            <td width="55%" valign="top">
            <table width="100%" cellpadding="0" cellspacing="0" class="dataTable">
              <tr class="dataTableHead">
                <td width="36%" height="30" align="right" type="Tree"><b>系统信息项&nbsp;</b></td>
                <td width="64%" type="Data" field="count"><b>值</b></td>
              </tr>
              <s:iterator value="systemInfo" id="stuts">  
              <tr style1="backgroundcolor:#FEFEFE" style2="backgroundcolor:#F9FBFC">
              	<s:iterator id="stuts" id="s" status="current">
                  <td <s:if test="#current.index==0">align="right"</s:if>><s:property/></td>
                  </s:iterator>
                </tr>
                </s:iterator>
            </table>
              <table width="100%" cellpadding="0" cellspacing="0" class="dataTable">
                <tr class="dataTableHead">
                  <td width="36%" height="30" align="right" type="Tree"><b>授权信息项&nbsp;</b></td>
                  <td width="64%" type="Data" field="count"><b>值</b></td>
                </tr>
                <s:iterator value="licenseInfo" id="stuts">  
                  <tr style1="backgroundcolor:#FEFEFE" style2="backgroundcolor:#F9FBFC">
                    <s:iterator id="stuts" id="s" status="current">
                    <td <s:if test="#current.index==0">align="right"</s:if>><s:property/></td>
                    </s:iterator>
                  </tr>
                </s:iterator>
              </table>

          </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
