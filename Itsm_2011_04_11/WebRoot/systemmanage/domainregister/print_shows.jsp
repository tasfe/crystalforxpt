<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国人民大学校内域名注册申请表</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-right: 0px;
}
.tab1{
	font-size:16px;
	}
.line1{
	border-bottom:#000 1px solid;
	font-size:12px;
	color:#000;
	font-family:"宋体";
}

.line2{
	border-top:#000 1px solid;
	}
-->
</style>

<!--media=print 这个属性可以在打印时有效--> 

<style type="text/css" media="print">
.noprint{display : none }
</style>
</head>

<body>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0"  class="tab1">
  <tr>
    <td colspan="3" class="line1">校内域名注册申请表</td>
    <td class="line1">编号（<s:property value="domainRegister.serialNumber"/>）</td>
    <td colspan="2" class="line1">网络与教育技术中心 制表</td>
  </tr>
      <tr>
    <td height="48" colspan="6">&nbsp;</td>
  </tr>
  
    <tr><td colspan="6" align="right">
    <tags:button code="print" menu="102">
  <p class="noprint"><input id="btnPrint" type="button" value="打印" onclick="javascript:window.print();" /></p>
    </tags:button>
    </td></tr>
  
  
  <tr>
    <td colspan="6" align="center"><br>
      <h2><strong>中国人民大学校内域名注册申请表</strong><br>
    </h2></td>
  </tr>
  
      <tr>
    <td height="28" colspan="6">&nbsp;</td>
  </tr>
  
  <tr>
    <td height="42" colspan="6">本申请表仅用于申请校内ruc.edu.cn域下的主机或网络域名。</td>
  </tr>
  <tr>
    <td colspan="6"><table width="100%" border="1" cellspacing="0" cellpadding="1" bgcolor="#000000">

      <tr align="center">
        <td height="22" width="32%" align="center" bgcolor="#FFFFFF">申请域名||Domain</td>
        <td colspan="1" bgcolor="#FFFFFF">（<s:property value="domainRegister.domain"/>）.ruc.edu.cn</td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">对应IP地址||IPAddress</td>
        <td colspan="1"  align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.ipAddress"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">服务器存放位置||Server Location</td>
        <td colspan="1"  align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.serverLocation"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">申请单位全称||OrgName</td>
        <td colspan="1" align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.unitsFullName"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">单位所在校内地址||OrgLocation</td>
        <td colspan="1" align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.unitsAddress"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">技术联系人||Tech-Person</td>
        <td colspan="1" align="center"  bgcolor="#FFFFFF"><s:property value="domainRegister.technicalContact"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">联系电话||Phone</td>
        <td colspan="1"  align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.phone"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">电子邮箱||Email</td>
        <td colspan="1"  align="center"  bgcolor="#FFFFFF"><s:property value="domainRegister.email"/></td>
        </tr>
    </table></td>
  </tr>



  <tr>
    <td height="87" colspan="6">
    <br>
    附注：（此处请说明申请原因以及未能在上表中填写的需要说明的事项例如撤销原域名等） </td>
  </tr>
    <tr>
    <td height="48" colspan="6"> <s:property value="domainRegister.remark"/> </td>
  </tr>
  <tr>
    <td width="202" height="39">单位负责人：</td>
    <td width="203"><s:property value="domainRegister.unitsLeader"/></td>
    <td width="209">（单位公章）</td>
    <td width="151">申请人签名：</td>
    <td width="123"><s:property value="domainRegister.application"/></td>
    <td width="122"><s:date name="domainRegister.applicationDate"  format="yyyy-MM-dd"/></td>
  </tr>
  
      <tr>
    <td height="48" colspan="6">&nbsp;</td>
  </tr>
  
  <tr>
    <td height="86" colspan="6" class="line2">说明：①本申请表可供校内单位申请主机域名或子域使用，申请子域时请在IP地址栏内填写子域的DNS服务器地址（有多台时用空格分隔）；②申请表填写完毕加盖单位公章后交至网络与教育技术中心用户服务台（理工配楼3层，电话62515292），同时将电子版申请表发送给noc@ruc.edu.cn；③网络中心收到申请表盖章件后2个工作日内完成设置。④需要一并申请IP地址的请同事填写IP地址申请表；⑤变更域名对应IP地址的在IP地址栏内直接填写新地址即可；⑥网络中心有权对申请完成后超过两个月未使用的域名予以收回。</td>
  </tr>
  <tr>
    <td height="48" colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6"><table width="100%" border="1" cellspacing="0" cellpadding="1" bgcolor="#000000">
      <tr>
        <td height="26" colspan="4" align="center" bgcolor="#FFFFFF"><strong>以下由网络中心填写</strong></td>
        </tr>
      <tr>
        <td width="17%" height="24" align="center" bgcolor="#FFFFFF">接收人</td>
        <td width="34%" align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.receiver"/></td>
        <td width="20%" align="center" bgcolor="#FFFFFF">接收日期</td>
        <td width="29%" align="center" bgcolor="#FFFFFF"><s:date name="domainRegister.receiveDate"  format="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td height="24" align="center" bgcolor="#FFFFFF">完成人</td>
        <td align="center" bgcolor="#FFFFFF"><s:property value="domainRegister.completer"/></td>
        <td align="center" bgcolor="#FFFFFF">完成日期</td>
        <td align="center" bgcolor="#FFFFFF"><s:date name="domainRegister.completeDate"  format="yyyy-MM-dd"/></td>
      </tr>
    </table></td>
  </tr>
  
    <tr>
<td colspan="6" align="center"><br><br><br>
打印日期：
<SCRIPT language=JavaScript>
var day="";
var month="";
var ampm="";
var ampmhour="";
var myweekday="";
var year="";
mydate=new Date();
myweekday=mydate.getDay();
mymonth=mydate.getMonth()+1;
myday= mydate.getDate();
myyear= mydate.getYear();
year=(myyear > 200) ? myyear : 1900 + myyear;
if(myweekday == 0)
weekday=" 星期日 ";
else if(myweekday == 1)
weekday=" 星期一 ";
else if(myweekday == 2)
weekday=" 星期二 ";
else if(myweekday == 3)
weekday=" 星期三 ";
else if(myweekday == 4)
weekday=" 星期四 ";
else if(myweekday == 5)
weekday=" 星期五 ";
else if(myweekday == 6)
weekday=" 星期六 ";
document.write(year+"年"+mymonth+"月"+myday+"日 "+weekday); 
</SCRIPT> 
</tr>

</table>
</body>
</html>
