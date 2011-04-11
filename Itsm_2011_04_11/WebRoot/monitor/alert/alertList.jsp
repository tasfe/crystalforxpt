<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/Default.css" rel="stylesheet" type="text/css" />
<script src="../js/Main.js"></script>
<script src="../js/controls/Tabpage.js"></script>
<script> 
function onRoleTabClick(){
	var currentTab = Tab.getCurrentTab().contentWindow;
 
}
 

</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable"> 
		 <tr>    
		 <td height="26" valign="middle" class="blockTd">   
		  <table width="100%" border='0' cellpadding='0' cellspacing='0' style="background:url(/zoa/Framework/Images/divchildtabBarBg.gif) repeat-x left bottom; margin-bottom:1px;">    
		  <tr>      
		  <td valign="bottom" height='30' style="padding:0 0 0 6px;_padding:0 0 0 2px;">
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic0'  class='divchildtabCurrent' onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src='../images/icons/icon018a1.gif' />
				<b>全部报警</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic1'  class='divchildtab'  onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>故障报警</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic2'  class='divchildtab'  onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>接入报警</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic3'  class='divchildtab'  onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>阈值报警</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic4'  class='divchildtab' onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>SNMPTRAP报警</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic5'  class='divchildtab' onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>其他报警</b>
			</div>
			</td>  
			</tr>   
		</table>   
		</td> 
		 </tr> 
		  <tr>     
		  <td style="padding:2px 6px;">
		  <iframe src="monitorAlert/listByCondition.action?type=all" width="100%" height="0" id="_ChildTabFrame_Basic0" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe>
		  <iframe src="monitorAlert/listByCondition.action?type=1" width="100%" height="0" id="_ChildTabFrame_Basic1" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe>
		  <iframe src="monitorAlert/listByCondition.action?type=2" width="100%" height="0" id="_ChildTabFrame_Basic2" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe>
		  <iframe src="monitorAlert/listByCondition.action?type=3" width="100%" height="0" id="_ChildTabFrame_Basic3" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe>
		  <iframe src="monitorAlert/listByCondition.action?type=4" width="100%" height="0" id="_ChildTabFrame_Basic4" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe>
		  <iframe src="monitorAlert/listByCondition.action?type=5" width="100%" height="0" id="_ChildTabFrame_Basic5" frameborder="0" scrolling="auto" allowtransparency="true">
		  </iframe> 
		  </td>  
		  </tr>
		  </table>
		  <script>Page.onLoad(function(){Tab.initFrameHeight("_ChildTabFrame_Basic0");},5);</script>
</td>
	</tr>
</table>
</body>
</html>

