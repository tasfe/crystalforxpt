<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国人民大学校园网IP地址申请表</title>
<style type="text/css"> 
<!--
body {
	margin-left: 0px;
	margin-right: 0px;
	margin-top:0px;
	margin-bottom:0px;
}
.tab1{
	font-size:14px;
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
.table table,.table td,.table tr{border:1px solid #000; font-size:14px;}
-->
</style>
<!--media=print 这个属性可以在打印时有效--> 

<style media="print"> 
.noprint{display:none;} 
</style> 
</head>
 
<body>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0"  class="tab1">
  <tr>
    <td colspan="3" class="line1">中国人民大学校园网IP地址申请表</td>
    <td class="line1">编号（<s:property value="ipAddress.serialNumber"/>）</td>
    <td colspan="2" class="line1">网络与教育技术中心 制表</td>
     <td width="4"  align="right">&nbsp;</td>
  </tr>
  <tr><td colspan="6" align="right"><input name="Submit4" type="button" class="go-wenbenkuang" onClick="javascript:window.print()" value="打印" Class="noprint"/></td></tr>
  <tr>
    <td colspan="6" align="center"><br><br>
      <h2><strong>中国人民大学校园IP地址申请表</strong><br><br>
    </h2></td>
  </tr>
  <tr>
    <td height="42" colspan="6">本申请表仅用于校内单位申请中国人民大学校园网静态IP地址。</td>
  </tr>
  <tr>
    <td colspan="6"><table width="100%" border="1" cellspacing="0" cellpadding="0" class="table">
      <tr>
        <td width="33%" rowspan="2" align="center" bgcolor="#FFFFFF">申请IP地址数量</td>
        <td width="27%" align="center" bgcolor="#FFFFFF">其中IPv4地址</td>
        <td width="40%" align="center" bgcolor="#FFFFFF"><s:property value="ipAddress.applyIpv4Count"/></td>
      </tr>
      <tr>
        <td align="center" bgcolor="#FFFFFF">IPv6地址</td>
        <td align="center" bgcolor="#FFFFFF"><s:property value="ipAddress.applyIpv6Count"/></td>
      </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">申请IP地址用途</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.applyIpPurpose"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">IP地址使用位置（房间）</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.ipUseRoom"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">申请单位全称</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.unitsFullName"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">单位所在校内地址</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.unitsAddress"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">单位现有IP地址数量</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.unitsExtentIpCount"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">现有IP地址用途</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.unitsExtentIpPurpose"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">技术联系人</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.technicalContact"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">联系电话</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.phone"/></td>
        </tr>
      <tr>
        <td height="22" align="center" bgcolor="#FFFFFF">电子邮箱</td>
        <td colspan="2" bgcolor="#FFFFFF"><s:property value="ipAddress.email"/></td>
        </tr>
    </table></td>
  </tr>
  
    <tr>
    <td height="48" colspan="6">&nbsp;</td>
  </tr>
  
  <tr>
    <td height="87" colspan="6">
    <br>
    附注：（此处请说明申请IP地址的原因）<br><br>
    <s:property value="ipAddress.applyIpCause"/></td><br/><br/>
  </tr>
  <br>
  <tr>
    <td width="210" height="39">单位负责人：</td>
    <td width="145"><s:property value="ipAddress.unitsLeader"/> </td>
    <td width="188">（单位公章）</td>
    <td width="167">申请人签名：</td>
    <td width="146"><s:property value="ipAddress.application"/> </td>
    <td width="154"><s:date name="ipAddress.applicationDate"  format="yyyy-MM-dd"/></td>
  </tr>
     <tr>
    <td height="48" colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td height="86" colspan="6" class="line2">说明：①本申请表仅供校内单位申请校园网固定IP地址使用；②申请表填写完毕加盖单位公章后交至网络与教育技术中心用户服务台（理工配楼3层，电话62515292），同时将电子版申请表发送给noc@ruc.edu.cn；③网络中心收到申请表盖章件后2个工作日内完成批复和路由设置。</td>
  </tr>
  <tr>
    <td height="60" colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" class="table"><table width="100%" border="1" cellpadding="0" cellspacing="0" class="table">
      <tr>
        <td height="26" colspan="4" align="center" bgcolor="#FFFFFF"><strong>以下由网络中心填写</strong></td>
        </tr>
      <tr>
        <td width="17%" height="24" align="center" bgcolor="#FFFFFF">接收人</td>
        <td width="34%" bgcolor="#FFFFFF"><s:property value="ipAddress.receiver"/></td>
        <td width="20%" align="center" bgcolor="#FFFFFF">接收日期</td>
        <td width="29%" bgcolor="#FFFFFF"><s:date name="ipAddress.receiveDate"  format="yyyy-MM-dd"/></td>
      </tr>
      <tr>
        <td height="24" align="center" bgcolor="#FFFFFF">完成人</td>
        <td bgcolor="#FFFFFF"><s:property value="ipAddress.completer"/></td>
        <td align="center" bgcolor="#FFFFFF">完成日期</td>
        <td bgcolor="#FFFFFF"><s:date name="ipAddress.completeDate"  format="yyyy-MM-dd"/></td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
<td colspan="6" align="center"><br><br>
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

