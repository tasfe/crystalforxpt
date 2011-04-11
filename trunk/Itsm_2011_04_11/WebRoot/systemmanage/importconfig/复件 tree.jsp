<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>批量导入配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<input type="hidden" name="ZiCLB" id="ZiCLB" value="">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">请选择配置类别:</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="../../images/space.gif" width="5" height="5"></td>
  </tr>
</table>

<table width="98%" height="70%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
  <tr>
    <td height="320" valign="top" bgcolor="#FFFFFF"><div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:98%; width: 100%; padding-top: 5px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td style="line-height: 19px; font-family: Tahoma" bgColor="white" nowrap><div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('230230').style.display=='none'){document.getElementById('230230').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('230230').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230," onClick="document.all.ZiCLB_2.value='硬件';document.all.ZiCLB.value='|230,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
            &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="230o" name="|230,o">硬件</a></font></font>
            <div id="230230" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('239239').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('239239').style.display=''}else{document.getElementById('239239').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|239," onClick="document.all.ZiCLB_2.value='服务器';document.all.ZiCLB.value='|230,|239,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
              &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="239o" name="|230,|239,o">服务器</a></font></font><br>
              <div id="239239" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('243243').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('243243').style.display=''}else{document.getElementById('243243').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|230,|239,|243," onClick="document.all.ZiCLB_2.value='品牌服务器';document.all.ZiCLB.value='|230,|239,|243,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="243o" name="|230,|239,|243,o">品牌服务器</a></font></font><br>
                <div id="243243" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('244244').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('244244').style.display=''}else{document.getElementById('244244').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|239,|244," onClick="document.all.ZiCLB_2.value='普PC服务器';document.all.ZiCLB.value='|230,|239,|244,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="244o" name="|230,|239,|244,o">普PC服务器</a></font></font><br>
                <div id="244244" style="padding-left: 20px; display: none"></div>
              </div>
              <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('240240').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('240240').style.display=''}else{document.getElementById('240240').style.display='none';this.src='../../img/jia.jpg'}">
              <input type="radio" value="|230,|240," onClick="document.all.ZiCLB_2.value='桌面机';document.all.ZiCLB.value='|230,|240,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
              &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="240o" name="|230,|240,o">桌面机</a></font></font><br>
              <div id="240240" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('245245').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('245245').style.display=''}else{document.getElementById('245245').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|230,|240,|245," onClick="document.all.ZiCLB_2.value='台式机';document.all.ZiCLB.value='|230,|240,|245,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="245o" name="|230,|240,|245,o">台式机</a></font></font><br>
                <div id="245245" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('246246').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('246246').style.display=''}else{document.getElementById('246246').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|240,|246," onClick="document.all.ZiCLB_2.value='笔记本';document.all.ZiCLB.value='|230,|240,|246,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="246o" name="|230,|240,|246,o">笔记本</a></font></font><br>
                <div id="246246" style="padding-left: 20px; display: none"></div>
              </div>
              <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('241241').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('241241').style.display=''}else{document.getElementById('241241').style.display='none';this.src='../../img/jia.jpg'}">
              <input type="radio" value="|230,|241," onClick="document.all.ZiCLB_2.value='网络设备';document.all.ZiCLB.value='|230,|241,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
              &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="241o" name="|230,|241,o">网络设备</a></font></font><br>
              <div id="241241" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('247247').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('247247').style.display=''}else{document.getElementById('247247').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|230,|241,|247," onClick="document.all.ZiCLB_2.value='路由器';document.all.ZiCLB.value='|230,|241,|247,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="247o" name="|230,|241,|247,o">路由器</a></font></font><br>
                <div id="247247" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('248248').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('248248').style.display=''}else{document.getElementById('248248').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|241,|248," onClick="document.all.ZiCLB_2.value='交换机';document.all.ZiCLB.value='|230,|241,|248,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="248o" name="|230,|241,|248,o">交换机</a></font></font><br>
                <div id="248248" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('249249').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('249249').style.display=''}else{document.getElementById('249249').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|241,|249," onClick="document.all.ZiCLB_2.value='防火墙';document.all.ZiCLB.value='|230,|241,|249,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="249o" name="|230,|241,|249,o">防火墙</a></font></font><br>
                <div id="249249" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('250250').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('250250').style.display=''}else{document.getElementById('250250').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|241,|250," onClick="document.all.ZiCLB_2.value='其它类';document.all.ZiCLB.value='|230,|241,|250,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="250o" name="|230,|241,|250,o">其它类</a></font></font><br>
                <div id="250250" style="padding-left: 20px; display: none"></div>
              </div>
              <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('242242').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('242242').style.display=''}else{document.getElementById('242242').style.display='none';this.src='../../img/jia.jpg'}">
              <input type="radio" value="|230,|242," onClick="document.all.ZiCLB_2.value='外围设备';document.all.ZiCLB.value='|230,|242,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
              &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="242o" name="|230,|242,o">外围设备</a></font></font><br>
              <div id="242242" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('251251').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('251251').style.display=''}else{document.getElementById('251251').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|230,|242,|251," onClick="document.all.ZiCLB_2.value='打印机';document.all.ZiCLB.value='|230,|242,|251,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="251o" name="|230,|242,|251,o">打印机</a></font></font><br>
                <div id="251251" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('252252').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('252252').style.display=''}else{document.getElementById('252252').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|242,|252," onClick="document.all.ZiCLB_2.value='扫描仪';document.all.ZiCLB.value='|230,|242,|252,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="252o" name="|230,|242,|252,o">扫描仪</a></font></font><br>
                <div id="252252" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('253253').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('253253').style.display=''}else{document.getElementById('253253').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|242,|253," onClick="document.all.ZiCLB_2.value='复印机';document.all.ZiCLB.value='|230,|242,|253,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="253o" name="|230,|242,|253,o">复印机</a></font></font><br>
                <div id="253253" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('254254').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('254254').style.display=''}else{document.getElementById('254254').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|242,|254," onClick="document.all.ZiCLB_2.value='显示器';document.all.ZiCLB.value='|230,|242,|254,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="254o" name="|230,|242,|254,o">显示器</a></font></font><br>
                <div id="254254" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('255255').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('255255').style.display=''}else{document.getElementById('255255').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|230,|242,|255," onClick="document.all.ZiCLB_2.value='一体机';document.all.ZiCLB.value='|230,|242,|255,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="255o" name="|230,|242,|255,o">一体机</a></font></font><br>
                <div id="255255" style="padding-left: 20px; display: none"></div>
              </div>
            </div>
          </div>
              <div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('231231').style.display=='none'){document.getElementById('231231').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('231231').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|231," onClick="document.all.ZiCLB_2.value='软件';document.all.ZiCLB.value='|231,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="231o" name="|231,o">软件</a></font></font>
                <div id="231231" style="padding-left: 20px; display: none"></div>
              </div>
            <div><img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('256256').style.display=='none'){document.getElementById('256256').style.display='';this.src='../../img/jian.jpg'}else{document.getElementById('256256').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|256," onClick="document.all.ZiCLB_2.value='人力资源';document.all.ZiCLB.value='|256,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
              &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="256o" name="|256,o">人力资源</a></font></font>
              <div id="256256" style="padding-left: 20px; display: none"> <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('257257').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('257257').style.display=''}else{document.getElementById('257257').style.display='none';this.src='../../img/jia.jpg'}">
                  <input type="radio" value="|256,|257," onClick="document.all.ZiCLB_2.value='外部顾问';document.all.ZiCLB.value='|256,|257,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="257o" name="|256,|257,o">外部顾问</a></font></font><br>
                <div id="257257" style="padding-left: 20px; display: none"></div>
                <img src="../../img/jia.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('258258').style.display=='none'){this.src='../../img/jian.jpg';document.getElementById('258258').style.display=''}else{document.getElementById('258258').style.display='none';this.src='../../img/jia.jpg'}">
                <input type="radio" value="|256,|258," onClick="document.all.ZiCLB_2.value='内部研发人员';document.all.ZiCLB.value='|256,|258,';" style="border: 0px; border-top: 6px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">
                &nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand; color: black"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="258o" name="|256,|258,o">内部研发人员</a></font></font><br>
                <div id="258258" style="padding-left: 20px; display: none"></div>
              </div>
            </div></td>
        </tr>
      </table>
    </div></td>
  </tr>
</table>
<table width="98%" border=0 align="center" cellpadding=0 cellspacing=0>
		  <tr>
			<td height="12" style="padding-top: 8px; padding-right: 20px" nowrap><input type="text" name="ZiCLB_2" id="ZiCLB_2" style="width: 160px; height: 23px; padding: 3px; font-weight: bold; padding-left: 23px; background-image: url(../img/folbig.gif); background-repeat: no-repeat" readonly>
		    </td>
			<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px">
			<input type="button" onClick="window.location='nexthardware.jsp'" value="下一步…" class=mmBtn>
			</td>
		  </tr>
</table>
<Script Language=Javascript src="../../cn_css/mmBtn.js"></Script>
</body>
</html>
