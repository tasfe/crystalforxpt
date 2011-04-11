<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="theme/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.STYLE4 {color: #03515d;
	font-size: 12px;
}
-->
</style>
<script>
//表格移上之后变换颜色
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
</head>
<body topmargin="0" leftmargin="0">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="24"><img src="images/main20100521dot04.gif" width="10" height="7">首页 &gt; 日志管理 </td>
  </tr>
</table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" style="font-size:12px; color:#022e44; font-weight:bold;">告警查看</td>
    <td width="2%" align="left" background="images/listconframe_07.gif">&nbsp;</td>
    <td width="85%" align="right" background="images/listconframe_07.gif"><img src="images/main20100521lrule.gif" width="97" height="22"></td>
    <td width="1%" align="right" background="images/listconframe_07.gif"><img src="images/main20100521_39.gif" width="10" height="28" alt=""></td>
  </tr>
</table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#f4fbff">
  <tr>
    <td width="10" height="10"><img src="images/main20100521_48.gif" width="10" height="10" alt=""></td>
    <td height="10" bgcolor="#FFFFFF"><img src="images/space.gif" width="1" height="1" /></td>
    <td width="10" height="10" background="images/main20100521_50.gif"><img src="images/main20100521_50.gif" width="10" height="5" alt=""></td>
  </tr>
  <tr>
    <td width="10" background="images/main20100521_48.gif">&nbsp;</td>
    <td height="6" bgcolor="#FFFFFF"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="25"><img src="images/main20100521lsearch.gif" width="16" height="16"></td>
        <td width="68">起始时间：</td>
        <td width="130"><input style="width:120px; height:20px;" type="text" name="textfield"></td>
        <td width="68">结束时间：</td>
        <td width="130"><input style="width:120px; height:20px;" type="text" name="textfield2"></td>
        <td width="89"><select name="select" style="height:22px;">
          <option>全部告警</option>
        </select>
        </td>
        <td width="90" height="30"><img src="images/main20100521_52.gif" width="43" height="22" alt=""></td>
      </tr>
    </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF">
            <td height="22" align="center" background="images/main20100521_58.gif">ID </td>
            <td align="center" background="images/main20100521_58.gif">时 间</td>
            <td align="center" background="images/main20100521_58.gif">类 型 </td>
            <td align="center" background="images/main20100521_58.gif">内 容</td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td height="20" align="center">00-01-01-02 </td>
            <td align="center">2007-03-24 18:11:24</td>
            <td align="center">连接告警 </td>
            <td align="center">AS服务器发出一个连接建立告警 </td>
          </tr>
      </table></td>
    <td width="10" background="images/main20100521_50.gif">&nbsp;</td>
  </tr>
  <tr>
    <td width="10" height="10"><img src="images/main20100521_66.gif" width="10" height="28" alt=""></td>
    <td height="10" background="images/main20100521_67.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="STYLE4">&nbsp;&nbsp;共有 120 条记录，当前第 1/10 页</td>
        <td><table border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="40"><img src="images/first.gif" width="37" height="15" /></td>
            <td width="45"><img src="images/back.gif" width="43" height="15" /></td>
            <td width="45"><img src="images/next.gif" width="43" height="15" /></td>
            <td width="40"><img src="images/last.gif" width="37" height="15" /></td>
            <td width="100"><div align="center"><span class="font_12">转到第
              <input name="textfield3" type="text" size="4" style="height:16px; width:20px; border:1px solid #999999;" />
              页 </span></div></td>
            <td width="40"><img src="images/go.gif" width="37" height="15" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td width="10" height="10"><img src="images/main20100521_69.gif" width="10" height="28" alt=""></td>
  </tr>
</table>
</body>
</html>