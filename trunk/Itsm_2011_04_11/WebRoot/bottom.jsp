<<<<<<< .mine
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html lang="true"/>
<html:base />
<html>
=======
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html lang="true"/>
<html:base />
<html>
>>>>>>> .r4464
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Bottom.jsp</title>
<<<<<<< .mine
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/ScanLoopTimer.js'></script>
<script type='text/javascript' src='dwr/interface/DwrService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<style>
.footbar_wrap{ position:fixed; bottom:0; left:0;width:100%;}
.footbar{ width:100%; height:24px;line-height:24px; font-size:14px; margin:0 auto; background:url(http://127.0.0.1:8080/img/footbar.jpg) repeat-x 0 0; border-left:1px solid #b4b4b4; border-right:1px solid #b4b4b4;}
/*a:link, a:visited {*/
.footbar a {
	color: #333;
	text-decoration: none;
}
/*a:hover, a:active {*/
.footbar a:hover {

}
* html .footbar_wrap{ position:absolute; left:0; width:101.4%;top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight);}
ul{ margin:0; padding:0; }

.lxr{
background: url(chat_icon_5.gif) no-repeat scroll 12px -125px transparent;
}
.shang{
background: url(chat_icon_5.gif) no-repeat scroll 20px -1180px transparent;
}
.xia{
background: url(chat_icon_5.gif) no-repeat scroll 20px -1215px transparent;
}
.chat{
background: url(chat_icon_5.gif) no-repeat scroll 12px -1136px transparent;
}
.footbar li{ 
width:140px; text-align:center; 
float:left;position:relative;
margin-left:1px;list-style: none;
}
.thespan{ position:absolute; top:-110px; left:-1px; font-size:12px;text-align:center;display:none; width:140px;height:110px; border:1px solid #ccc; border-bottom:none;}
.clear{ clear:both;}
.footbar li.shang:hover{ border:1px solid #ccc; border-top:none; 
background:url(chat_icon_5.gif) no-repeat scroll 20px -1215px transparent;
background-color:#FFF;
}
.footbar li.lxr:hover{ border:1px solid #ccc; border-top:none; 
background:url(chat_icon_5.gif) no-repeat scroll 12px -170px transparent;
background-color:#FFF;
}
.footbar li.chat:hover{ border:1px solid #ccc; border-top:none; 
background-colojavascript:mctmp(0);r:#FFF;
}
.footbar li:hover span{ display:block;}
.footbar li p{ margin:0 0 2px 0;}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script>
$(function(){
 
   $(".footbar li:eq(1)").hover(function(){
     $(this).css({ border: "1px solid #ccc", background: "#fff"});
    $(this).css("border-top", "none");
    $(this).css("border-bottom", "none");
    $(".thespan:eq(0)").css("display","block");
  },
  function(){
    $(this).css({ border: "none", background: "none"});
    $(this).css("border-top", "none");
    
    $(".thespan:eq(0)").css("display","");
  })
})
</script>
 
<![endif]--> 

<script type='text/javascript' >
function startScan(){
	
	if(document.getElementById("startScan").disabled){
		alert("扫描正在进行！");
	}else{
		ScanLoopTimer.startScan(afterScan);
	}
	
}
function afterScan(){
	 
}
function stopScan(){
	if(document.getElementById("stopScan").disabled){
		alert("扫描已停止！");
	}else{
		ScanLoopTimer.stopScan(afterStopScan);
	}
}
function afterStopScan(){
	 
}


function putInfo(data) {
	var d = decodeURI(data);
	var text = dwr.util.getValue("info");
	//dwr.util.setValue("info",text+"\n"+d);
	document.getElementById('message').innerHTML =d;
}
/**
* 页面初始化
*/
function init() {
	dwr.engine.setActiveReverseAjax(true); // 激活反转 重要
}
</script>
=======
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='dwr/interface/ScanLoopTimer.js'></script>
<script type='text/javascript' src='dwr/interface/DwrService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<style>
.footbar_wrap{ position:fixed; bottom:0; left:0;width:100%;}
.footbar{ width:100%; height:24px;line-height:24px; font-size:14px; margin:0 auto; background:url(http://127.0.0.1:8080/img/footbar.jpg) repeat-x 0 0; border-left:1px solid #b4b4b4; border-right:1px solid #b4b4b4;}
/*a:link, a:visited {*/
.footbar a {
	color: #333;
	text-decoration: none;
}
/*a:hover, a:active {*/
.footbar a:hover {

}
* html .footbar_wrap{ position:absolute; left:0; width:101.4%;top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight);}
ul{ margin:0; padding:0; }

.lxr{
background: url(chat_icon_5.gif) no-repeat scroll 12px -125px transparent;
}
.shang{
background: url(chat_icon_5.gif) no-repeat scroll 20px -1180px transparent;
}
.xia{
background: url(chat_icon_5.gif) no-repeat scroll 20px -1215px transparent;
}
.chat{
background: url(chat_icon_5.gif) no-repeat scroll 12px -1136px transparent;
}
.footbar li{ 
width:140px; text-align:center; 
float:left;position:relative;
margin-left:1px;list-style: none;
}
.thespan{ position:absolute; top:-110px; left:-1px; font-size:12px;text-align:center;display:none; width:140px;height:110px; border:1px solid #ccc; border-bottom:none;}
.clear{ clear:both;}
.footbar li.shang:hover{ border:1px solid #ccc; border-top:none; 
background:url(chat_icon_5.gif) no-repeat scroll 20px -1215px transparent;
background-color:#FFF;
}
.footbar li.lxr:hover{ border:1px solid #ccc; border-top:none; 
background:url(chat_icon_5.gif) no-repeat scroll 12px -170px transparent;
background-color:#FFF;
}
.footbar li.chat:hover{ border:1px solid #ccc; border-top:none; 
background-colojavascript:mctmp(0);r:#FFF;
}
.footbar li:hover span{ display:block;}
.footbar li p{ margin:0 0 2px 0;}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script>
$(function(){
 
   $(".footbar li:eq(1)").hover(function(){
     $(this).css({ border: "1px solid #ccc", background: "#fff"});
    $(this).css("border-top", "none");
    $(this).css("border-bottom", "none");
    $(".thespan:eq(0)").css("display","block");
  },
  function(){
    $(this).css({ border: "none", background: "none"});
    $(this).css("border-top", "none");
    
    $(".thespan:eq(0)").css("display","");
  })
})
</script>
 
<![endif]--> 

<script type='text/javascript' >
function startScan(){
	
	if(document.getElementById("startScan").disabled){
		alert("扫描正在进行！");
	}else{
		ScanLoopTimer.startScan(afterScan);
	}
	
}
function afterScan(){
	 
}
function stopScan(){
	if(document.getElementById("stopScan").disabled){
		alert("扫描已停止！");
	}else{
		ScanLoopTimer.stopScan(afterStopScan);
	}
}
function afterStopScan(){
	 
}


function putInfo(data) {
	var d = decodeURI(data);
	var text = dwr.util.getValue("info");
	//dwr.util.setValue("info",text+"\n"+d);
	document.getElementById('message').innerHTML =d;
}

/**
* 页面初始化
*/
function init() {
	dwr.engine.setActiveReverseAjax(true); // 激活反转 重要
}
</script>
>>>>>>> .r4464
</head>
 
<body onload="init()">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="left" valign="top"><img src="images/main20100521_89.gif" width="177" height="13" alt=""></td>
    <td align="center"><img src="images/space.gif" width="1" height="1"></td>
  </tr>
  <tr>
  	<td height="28" align="center" background="images/main20100521_90.gif">
  	<div class="footbar_wrap" > 
<div class="footbar"> 
<ul> 
<li style="width:200px;color:#666;">
	<div  >&nbsp;</div> 
</li>
<li style="width:30px;color:#666;" class="shang" >
	<a href="#" >
		<input type="image" id="startScan"  title="开始扫描" src="<%=path%>/img/monitor/startscan.png" onclick="startScan()"  />
	</a>
</li>
<li style="width:30px;color:#666;" class="shang" >
	<a href="#" >
		<input  type="image" id="stopScan" title="停止扫描" src="<%=path%>/img/monitor/stopscan.png" onclick="stopScan()" />
	</a>
</li>

<li style="width:400px;color:#666;">
<div id= "message" class="align:left;" >&nbsp;</div> 
</li>
</ul> 
<div class="clear"></div> 
</div> 
</div>
  	</td>
    <td height="28" align="left" background="images/main20100521_90.gif">北京康邦科技有限公司 版权所有</td>
  </tr>
</table>
<<<<<<< .mine
<div class="footbar_wrap"> 
<div class="footbar"> 
<ul> 
<li style="width:100px;color:#666;" class="shang" >
	<div  >&nbsp;</div> 
</li>
<li style="width:30px;color:#666;" class="shang" >
	<a href="#" >
		<input type="image" id="startScan"  src="<%=path%>/img/monitor/startscan.png" onclick="startScan()" alt="开始扫描" />
	</a>
</li>
<li style="width:30px;color:#666;" class="shang" >
	<a href="#" >
		<input  type="image" id="stopScan"  src="<%=path%>/img/monitor/stopscan.png" onclick="stopScan()" alt="停止扫描" />
	</a>
</li>

<li style="width:300px;color:#666;">
<div id= "message" >&nbsp;</div> 
</li>
</ul> 
<div class="clear"></div> 
</div> 
</div> 
=======
 
>>>>>>> .r4464
</body>
</html>