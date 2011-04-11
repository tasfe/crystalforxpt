<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>工程师跟踪请求处理情况</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工程师跟踪请求处理情况:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;"><table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF">
      <tr>
        <td width=2% nowrap bgcolor="#b5d6e6" class="td-left">报表名称:&nbsp;</td>
        <td width="23%" bgcolor="#EBF4F5" class="td-detail" style="padding-right: 10px"><input name="Report_Title" id="Report_Title" value="" style="width: 100%">        </td>
        <td width=2% nowrap bgcolor="#b5d6e6" class="td-left">创建人:&nbsp;</td>
        <td width="23%" bgcolor="#EBF4F5" class="td-detail" style="padding-right: 10px"><input name="Creator" id="Creator" value="" style="width: 100%">        </td>
        <td width=2% nowrap bgcolor="#b5d6e6" class="td-left">创建时间:&nbsp;</td>
        <td width="23%" bgcolor="#EBF4F5" class="td-detail" style="padding-right: 20px"><input name="CreateTime" id="CreateTime" value="" style="width: 100%" onClick="gfPop.fPopCalendar(this);return false;" readonly>        </td>
        <td width="2%" bgcolor="#EBF4F5" class="td-detail"><input name="button" type="button" class="mmBtn" style="height: 20px" onClick="SeekOnClick(); this.disabled=true;" value="搜索">        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10" style="font-size:12px; color:#333333; font-weight:bold;">&nbsp;</td>
        </tr>
        <tr>
          <td height="20" style="font-size:12px; color:#333333; font-weight:bold;"><img src="../../images/main20100521dot04.gif">事件或服务统计</td>
        </tr>
        <tr>
          <td valign="top" background="../../img/Separator.gif" style="line-height:5px;"><img src="../../img/Separator.gif" width="6" height="6"></td>
        </tr>
      </table>
      <table width="100%" border=0 cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
        <tr>
          <th width="1%" height="22" nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">选中</th>
          <th width="1%" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">报表号</th>
          <th width="40%" background="../../images/main20100521_58.gif" class="alllisttitle">报表标题</th>
          <th width="20%" background="../../images/main20100521_58.gif" class="alllisttitle">创建人</th>
          <th width="25%" background="../../images/main20100521_58.gif" class="alllisttitle">创建时间</th>
          <th width=1% nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">编辑</th>
          <th width=1% nowrap background="../../images/main20100521_58.gif" class="alllisttitle" style="text-align: center">删除</th>
        </tr>
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
          <td width="1%" align="center" nowrap style="padding-left: 10px; padding-right: 10px; padding-top: 5px; padding-bottom: 2px"><input type="radio" id="chose" name="chose" value="../kpi/?NowAction=Rect&Langua=CN&Tjdx=Inci&Tiaojian=,and_(State_<>_'FIN'_and_State_<>_'REF'),and_(State_$_'FIN'_or_State_$_'REF'),and_(RequNo_^_'R~'),and_(ClientIde_$_'A')&Type=Y&Jbs=Y&Back=CCCCCC&Tjxm=Eng&Values=,dongyi,fengchi,huojiang,libin2,liukai,wanggx,yangcen,zhanwei,zhanglk,zhangxx,zhangzy,zhaowb&Tiaojian_NM=,未完成,已关闭,自行提交,满意&SubCate=Y&Per=&TopID=Inci&Title_Report=所有工程师工作详细状态&Report_ID=2&Locas=" onClick="window.document.getElementById('VALUES').value=this.value" style="border: 0px">
          </td>
          <td height="30">RPT0800000002</td>
          <td height="30" align="center">所有工程师工作详细状态</td>
          <td align="center">张晓曦</td>
          <td align="center">2008-4-25 14:29:58</td>
          <td align=center nowrap>&nbsp;<a onClick="window.open('ReportEdit.jsp','','width=600,height=500,scrollbars=yes,menubar=no,resizable=no,top=130,left=170,status=yes')" style="cursor: hand" href="javascript:"><img src="../../images/edt.gif" width="11" height="11" border="0">&nbsp;编辑</a></td>
          <td align=center nowrap>&nbsp;<a href="../prob/?NowAction=dbdeal&type=AdminDel&RequNo=2&Table=Report_Mana&Name=ID&Url=../kpi/%3FNowAction%3Dreport_list%26Title%3D事件或服务统计%26Type%3DInci%26Cate%3D%26Report_Title%3D%26Creator%3D%26CreateTime%3D%26page%3D1" onClick="return confirm('你确认要删除吗？');"><img src="../../images/del.gif" width="10" height="10" border="0">&nbsp; 删除</a></td>
        </tr>
        <tr bgcolor="#FFFFFF" style="font-weight: bold; color: #3A4E69">
          <td colspan="2" align="left" nowrap style="padding-left: 5px"><img src="../../img/i.gif" width="10" height="13" align="absmiddle">&nbsp;统计起止时间:</td>
          <td height="32" colspan="2" style="padding-left: 8px"><input type="text" name="Start" size="20" class="queryInput" style="width: 85; height: 18" value="2010-5-21" onClick="gfPop.fPopCalendar(this);return false;" readonly>
            -
            <input type="text" name="End" size="20" class="queryInput" style="width: 85; height: 18" value="2010-5-28" onClick="gfPop.fPopCalendar(this);return false;" readonly>
          </td>
          <td height="32" colspan="3" align="right"><a style="border: 1px solid; background-color: #E5E9EE">&nbsp;Total:&nbsp;1&nbsp;</a></td>
        </tr>
      </table>
      <table cellspacing=0 cellpadding=0 border=0 width="100%">
        <tr>
          <td nowrap width="1%" style="padding-top: 6px; padding-bottom: 1px"><table border=0 cellspacing=0 cellpadding=0 width='1%' style='border: outset 1px white'>
            <tr>
              <td style='background-image: url(../img/page.jpg)' nowrap>&nbsp;|&nbsp;&nbsp;<font color=red><b>1</b></font>&nbsp;&nbsp;| &nbsp;[<font color=red><b>1</b></font>/<font color=red><b>1</b></font>]&nbsp;</td>
            </tr>
          </table></td>
          <td align=center nowrap width="90%" style="padding-top: 6px; padding-bottom: 1px"><input name="button2" type="button" class="mmBtn" onClick="Report()" value="查看统计报表">
              <input name="button2" type="button" class="mmBtn" onClick="history.go(-1)" value="后退">
          </td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
</body>
</html>
