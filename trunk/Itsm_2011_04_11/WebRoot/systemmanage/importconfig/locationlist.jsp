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
  <table width="98%" border="0" align="center" cellpadding=3 cellspacing=0>
    <tr>
      <td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;"><a onClick="location.href='classificationlist.jsp'">&nbsp;按配置分类查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='locationlist.jsp'">&nbsp;按地理位置查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='departmentlist.jsp'">&nbsp;按部门查看&nbsp;</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;"><a onClick="location.href='statuslist.jsp'">&nbsp;按使用状态查看&nbsp;</a></span></td>
    </tr>
  </table>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="99%" onClick="document.getElementById('thelayer').style.display='none'"><img src="../../images/main20100521dot04.gif">&nbsp;<b>按地理位置查看:</b>&nbsp;</td>
    </tr>
  </table>
  <table width="98%" border=0 align="center" cellpadding=0 cellspacing=1 bgcolor="#b5d6e6">
    <tr>
      <td height=22 colspan="6" background="../../images/main20100521_58.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><b class="alllisttitle"><strong>&nbsp;&nbsp;</strong>地理位置</b></td>
          <td align="right"><a href="hardware.jsp"><img src="../../img/ico_listdown.gif" width="20" height="20" border="0"></a></td>
        </tr>
      </table></td>    
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">香港区域 (0) </td>
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">重庆区域 (0) </td>
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">北京区域 (0) </td>
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">上海区域 (0) </td>
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;"><a href="brandserver.jsp">成都区域 (2)</a> </td>
    </tr>
    <tr height=33>
      <td width="1%" align="right" bgcolor="#EBF4F5" style="padding-right: 7px; padding-left:7px;"><img src="../../img/arrow0001.gif" width="20" height="20"></td>
      <td width="99%" nowrap colspan="5" bgcolor="#FFFFFF" style="padding:4px;">西安区域 (0) </td>
    </tr>
    <tr>
      <td colspan="6" height=22><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td background="../../images/main20100521_58.gif"><strong>&nbsp;&nbsp;设定查看范围</strong></td>
          <td width="1%" background="../../images/main20100521_58.gif" style="padding-right: 7px; cursor: hand" onClick="document.all.TB.style.display=''"><img src="../../img/ico_listdown.gif" width="20" height="20" border="0"></td>
        </tr>
      </table></td>
    </tr>
  </table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="display:none;" id="TB">
    <tr>
      <td height=22 colspan="6" align="right" valign="top" style="padding-right:300px;"><div id="Cate" style="position:absolute; overflow-y: scroll; width: 300px; height:150px; z-index:1; border: 1px solid #cccccc;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="line-height: 19px; font-family: Tahoma" bgColor="white" nowrap>
	
			<div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.all.TB.style.display=='none'){document.getElementById('230230').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('230230').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230," onClick="window.parent.document.getElementById('ZiCLB_2').value='硬件';window.parent.document.getElementById('ZiCLB').value='|230,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="230o" name="|230,o">硬件</a></font></font><div id="230230" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.all.TB.style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('239239').style.display=''}else{document.getElementById('239239').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|239," onClick="window.parent.document.getElementById('ZiCLB_2').value='服务器';window.parent.document.getElementById('ZiCLB').value='|230,|239,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="239o" name="|230,|239,o">服务器</a></font></font><br><div id="239239" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.all.TB.style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('243243').style.display=''}else{document.getElementById('243243').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|239,|243," onClick="window.parent.document.getElementById('ZiCLB_2').value='品牌服务器';window.parent.document.getElementById('ZiCLB').value='|230,|239,|243,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="243o" name="|230,|239,|243,o">品牌服务器</a></font></font><br><div id="243243" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.all.TB.style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('244244').style.display=''}else{document.getElementById('244244').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|239,|244," onClick="window.parent.document.getElementById('ZiCLB_2').value='普PC服务器';window.parent.document.getElementById('ZiCLB').value='|230,|239,|244,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="244o" name="|230,|239,|244,o">普PC服务器</a></font></font><br><div id="244244" style="padding-left: 20px; display: none"></div>
			</div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('240240').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('240240').style.display=''}else{document.getElementById('240240').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|240," onClick="window.parent.document.getElementById('ZiCLB_2').value='桌面机';window.parent.document.getElementById('ZiCLB').value='|230,|240,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="240o" name="|230,|240,o">桌面机</a></font></font><br><div id="240240" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('245245').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('245245').style.display=''}else{document.getElementById('245245').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|240,|245," onClick="window.parent.document.getElementById('ZiCLB_2').value='台式机';window.parent.document.getElementById('ZiCLB').value='|230,|240,|245,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="245o" name="|230,|240,|245,o">台式机</a></font></font><br><div id="245245" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('246246').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('246246').style.display=''}else{document.getElementById('246246').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|240,|246," onClick="window.parent.document.getElementById('ZiCLB_2').value='笔记本';window.parent.document.getElementById('ZiCLB').value='|230,|240,|246,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="246o" name="|230,|240,|246,o">笔记本</a></font></font><br><div id="246246" style="padding-left: 20px; display: none"></div>
			</div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('241241').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('241241').style.display=''}else{document.getElementById('241241').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|241," onClick="window.parent.document.getElementById('ZiCLB_2').value='网络设备';window.parent.document.getElementById('ZiCLB').value='|230,|241,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="241o" name="|230,|241,o">网络设备</a></font></font><br><div id="241241" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('247247').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('247247').style.display=''}else{document.getElementById('247247').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|241,|247," onClick="window.parent.document.getElementById('ZiCLB_2').value='路由器';window.parent.document.getElementById('ZiCLB').value='|230,|241,|247,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="247o" name="|230,|241,|247,o">路由器</a></font></font><br><div id="247247" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('248248').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('248248').style.display=''}else{document.getElementById('248248').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|241,|248," onClick="window.parent.document.getElementById('ZiCLB_2').value='交换机';window.parent.document.getElementById('ZiCLB').value='|230,|241,|248,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="248o" name="|230,|241,|248,o">交换机</a></font></font><br><div id="248248" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('249249').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('249249').style.display=''}else{document.getElementById('249249').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|241,|249," onClick="window.parent.document.getElementById('ZiCLB_2').value='防火墙';window.parent.document.getElementById('ZiCLB').value='|230,|241,|249,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="249o" name="|230,|241,|249,o">防火墙</a></font></font><br><div id="249249" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('250250').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('250250').style.display=''}else{document.getElementById('250250').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|241,|250," onClick="window.parent.document.getElementById('ZiCLB_2').value='其它类';window.parent.document.getElementById('ZiCLB').value='|230,|241,|250,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="250o" name="|230,|241,|250,o">其它类</a></font></font><br><div id="250250" style="padding-left: 20px; display: none"></div>
			</div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('242242').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('242242').style.display=''}else{document.getElementById('242242').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242," onClick="window.parent.document.getElementById('ZiCLB_2').value='外围设备';window.parent.document.getElementById('ZiCLB').value='|230,|242,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="242o" name="|230,|242,o">外围设备</a></font></font><br><div id="242242" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('251251').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('251251').style.display=''}else{document.getElementById('251251').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242,|251," onClick="window.parent.document.getElementById('ZiCLB_2').value='打印机';window.parent.document.getElementById('ZiCLB').value='|230,|242,|251,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="251o" name="|230,|242,|251,o">打印机</a></font></font><br><div id="251251" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('252252').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('252252').style.display=''}else{document.getElementById('252252').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242,|252," onClick="window.parent.document.getElementById('ZiCLB_2').value='扫描仪';window.parent.document.getElementById('ZiCLB').value='|230,|242,|252,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="252o" name="|230,|242,|252,o">扫描仪</a></font></font><br><div id="252252" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('253253').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('253253').style.display=''}else{document.getElementById('253253').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242,|253," onClick="window.parent.document.getElementById('ZiCLB_2').value='复印机';window.parent.document.getElementById('ZiCLB').value='|230,|242,|253,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="253o" name="|230,|242,|253,o">复印机</a></font></font><br><div id="253253" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('254254').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('254254').style.display=''}else{document.getElementById('254254').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242,|254," onClick="window.parent.document.getElementById('ZiCLB_2').value='显示器';window.parent.document.getElementById('ZiCLB').value='|230,|242,|254,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="254o" name="|230,|242,|254,o">显示器</a></font></font><br><div id="254254" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('255255').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('255255').style.display=''}else{document.getElementById('255255').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|230,|242,|255," onClick="window.parent.document.getElementById('ZiCLB_2').value='一体机';window.parent.document.getElementById('ZiCLB').value='|230,|242,|255,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="255o" name="|230,|242,|255,o">一体机</a></font></font><br><div id="255255" style="padding-left: 20px; display: none"></div>
			</div>
			</div></div>
		
			<div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('231231').style.display=='none'){document.getElementById('231231').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('231231').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|231," onClick="window.parent.document.getElementById('ZiCLB_2').value='软件';window.parent.document.getElementById('ZiCLB').value='|231,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="231o" name="|231,o">软件</a></font></font><div id="231231" style="padding-left: 20px; display: none"></div></div>
		
			<div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('256256').style.display=='none'){document.getElementById('256256').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('256256').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|256," onClick="window.parent.document.getElementById('ZiCLB_2').value='人力资源';window.parent.document.getElementById('ZiCLB').value='|256,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="256o" name="|256,o">人力资源</a></font></font><div id="256256" style="padding-left: 20px; display: none">
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('257257').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('257257').style.display=''}else{document.getElementById('257257').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|256,|257," onClick="window.parent.document.getElementById('ZiCLB_2').value='外部顾问';window.parent.document.getElementById('ZiCLB').value='|256,|257,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="257o" name="|256,|257,o">外部顾问</a></font></font><br><div id="257257" style="padding-left: 20px; display: none"></div>
			
			<img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('258258').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('258258').style.display=''}else{document.getElementById('258258').style.display='none';this.src='../../img/jia.jpg'}"><input type="radio" value="|256,|258," onClick="window.parent.document.getElementById('ZiCLB_2').value='内部研发人员';window.parent.document.getElementById('ZiCLB').value='|256,|258,';window.parent.document.getElementById('Cate').style.visibility='hidden';window.parent.document.getElementById('Postit').submit()" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="258o" name="|256,|258,o">内部研发人员</a></font></font><br><div id="258258" style="padding-left: 20px; display: none"></div>
			</div></div>
		
	</td>
  </tr>
</table></div></td>
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
