<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">批量导入配置:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="99%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
    </tr>
  </table>
<table width="99%" border=0 align="center" cellpadding=2 cellspacing=1 bgcolor="#b5d6e6">
                      <tr height=30>
                        <th height="22" colspan="6" align="left" background="../../images/main20100521_58.gif" class="alllisttitle">从本地文件导入</th>
                      </tr>
                      <tr height=30>
                        <td width="7%" rowspan="2" align="right" bgcolor="#EBF4F5" style="padding-top: 17px; padding-right: 10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
                          <td width="100%" colspan="5" nowrap bgcolor="#FFFFFF" style="padding: 15px">
                            <table width="10%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td>
                                  <table width="10%" border="0" cellspacing="1" cellpadding="2" style="background-color: #FFFFFF; border: 1px solid #2D5082">
                                    <tr style="background: url(../img/feedback.gif); background-position: right bottom">
                                      <td nowrap style="padding-left: 10px; padding-right: 18px"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle">&nbsp;<b>选择配置类别</b>:</td>
                                      <td nowrap>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" bgcolor="#FFFFFF">
                                          <tr>
                                            <td style="border: 1px inset">
                                              <input type="hidden" name="ZiCLB" id="ZiCLB">
                                              <input type="text" name="ZiCLB_2" style="width: 100px; cursor: text; border: 1px solid white; font-size: 11px; font-family: Tahoma" readonly onClick="document.getElementById('Layer').style.visibility='visible';this.value='';document.getElementById('ZiCLB').value=''">                                            </td>
                                          </tr>
                                        </table>
                                        <div id="Layer" style="position:absolute; width:270px; height:20px; z-index:1; visibility: hidden; border: 0px; padding-top: 5px">
                                          <table width="100%" border="0" cellspacing="1" cellpadding="0"  bgcolor="#FFFFFF">
                                            <tr>
                                              <td>
                                                <iframe frameborder="0" height="150" width="100%" scrolling="yes" src="../task/?NowAction=tree&ID=Confi_Type&Par=ZiCLB&Layer=Layer" style="border: 1px solid #E5E9EE"></iframe>                                              </td>
                                            </tr>
                                          </table>
                                        </div>                                      </td>
                                      <td nowrap style="padding-left: 10px; padding-right: 18px"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle">&nbsp;<b>选择文件</b>:</td>
                                      <td nowrap>
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" bgcolor="#FFFFFF">
                                          <tr>
                                            <td style="border: 1px inset; padding-right: 1px">
                                              <input type="File" name="filename" style="border: 1px solid white; font-size: 11px; font-family: Tahoma; width: 120px">                                            </td>
                                          </tr>
                                        </table>                                      </td>
                                    </tr>
                                  </table>                                </td>
                                <td><img width="2"></td>
                                <td height="100%"><input name="button" type="button" class="mmBtn" value="开始"></td>
                              </tr>
                          </table>                          </td>
                      </tr>
                      <tr height=30>
                        <td nowrap colspan="5" bgcolor="#F9F9F9" style="color: #3A4E69; line-height: 24px; padding: 10px; padding-left: 16px"> 1、请先按配置分类查看，并下载表格文件进行填写，再行导入。<br>2、配置类别请参照表格文件例行，配置代码可以不用填写，由系统自动生成。<br>3、使用部门请填写部门代码，管理员请填写管理员帐号。<br>4、所有内容请不要使用半角逗号以及半角双引号。<br>5、状态请按以下格式:&nbsp;[NEW]-新建可修改，[DEL]-己注销，[WXZ]-维修中，[SYZ]-正常使用中，[YHS]-已被回收。 </td>
                      </tr>
                      <tr height=30>
                        <th height="22" colspan="6" align="left" class="alllisttitle">批量添加配置信息</th>
                      </tr>
                      <tr height=30>
                        <td width="7%" align="right" bgcolor="#EBF4F5" style="padding-right: 10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
                        <td width="100%" colspan="5" nowrap bgcolor="#FFFFFF" style="padding: 15px">
                          <table width="10%" border="0" cellspacing="1" cellpadding="3" style="background-color: #FFFFFF; border: 1px solid #2D5082">
                            <tr style="background: url(../img/feedback.gif); background-position: right bottom; cursor: hand">
                              <td nowrap style="padding-left: 10px; padding-right: 18px" onClick="window.open('tree.jsp','','width=500,height=400,scrollbars=yes,menubar=no,resizable=yes,top=100,left=100,status=yes')"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle">&nbsp;<b>点击这里开始添加配置..</b></td>
                            </tr>
                        </table>                        </td>
                      </tr>
                      
                      <tr height=30>
                        <th height="22" colspan="6" align="left" class="alllisttitle">按批次号添加配置信息</th>
                      </tr>
                      <tr height=30>
                        <td width="7%" align="right" bgcolor="#EBF4F5" style="padding-right: 10px"><img src="../../img/ind.jpg" width="36" height="36"  border="0"></td>
                        <td width="100%" colspan="5" nowrap bgcolor="#FFFFFF" style="padding: 15px">
                          <table width="100%" border="0" cellspacing="2" cellpadding="3" style="border: 1px solid #2D5082">
                            <tr>
                              <td class="td-right" style="padding-left: 10px; padding-top: 5px"><span style="padding-left: 10px; padding-right: 18px"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle"></span><b>添加一个新的入库批次</b>&nbsp;... </td>
                              <td width="1%" class="td-left" style="cursor: hand; background: url(../img/feedback.gif); background-position: right bottom">
																<a onClick="window.open('tree.jsp','','width=500,height=400,scrollbars=yes,menubar=no,resizable=yes,top=100,left=100,status=yes')"> &nbsp;<img src="../../img/viewdetail.gif" width="16" height="14" border="0">&nbsp; </a>															</td>
                            </tr>
                            <tr>
                              <td class="td-right" style="padding-left: 10px; padding-top: 5px"><span style="padding-left: 10px; padding-right: 18px"><img src="../../img/kb.gif" width="14" height="14" align="absmiddle"></span><b>查看所有入库批次信息列表</b>&nbsp;... </td>
                              <td width="1%" class="td-left" style="cursor: hand; background: url(../img/feedback.gif); background-position: right bottom">
																<a onClick="window.location='equipmentlist.jsp'"> &nbsp;<img src="../../img/viewdetail.gif" width="16" height="14" border="0">&nbsp; </a>															</td>
                            </tr>
                        </table>                        </td>
                      </tr>
  </table>
</div>
<script language="javascript">
if (window.top.location.href.indexOf("itsm.htm")>0){
	var Url = window.location.href;
	Url = Url.replace(/\&/g,"|@|");
	Url = Url.replace(/\#/g,"|$|");
	Url = Url.replace(/\?/g,"|~|");
	window.top.themain.mainit.topit.currurl.location.replace("../home/?NowAction=CurrURL&CurrURL="+Url);
}
</script>
</body>
</html>
