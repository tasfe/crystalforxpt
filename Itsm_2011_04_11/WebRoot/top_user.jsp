<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script src="js/Main.js" type="text/javascript"></script>
<script language="javascript">
//取得服务器时间
var dateOfClient = new Date();
var dateOfServer = new Date();
dateOfServer.setTime(<%=System.currentTimeMillis()%>);
setInterval("getTodayTime(" + (dateOfClient.getTime() - dateOfServer.getTime()) + ")",1);
			
//根据服务器时间计算当前时间并实时更新显示
	function getTodayTime(millis){
			var todayTime = new Date();
			todayTime.setTime(new Date() - millis);
			
			var year = todayTime.getFullYear();
			var month = todayTime.getMonth() + 1;
			var dayOfMonth = todayTime.getDate();
			var days=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
			var weekday = days[todayTime.getDay()];
			var hours = todayTime.getHours();
			var mins = todayTime.getMinutes();
			var secs = todayTime.getSeconds();
			
			var str = year + "年" 
				+ (month < 10 ?	"0" + month : month) + "月" 
				+ (dayOfMonth < 10 ?	"0" + dayOfMonth : dayOfMonth) + "日 &nbsp;" 
					+ weekday + "&nbsp;" 
						+ (hours < 10 ?	"0" + hours : hours) 
						+ ":" + (mins < 10 ?	"0" + mins : mins) 
						+ ":" + (secs < 10 ?	"0" + secs : secs);			
			$("todayTime").innerHTML = str;
	}
Page.onLoad(function(){
	$("loginStatus").innerHTML = $("online").innerHTML;
	window.setTimeout(signNow,3000);	
});

function updown(url){
	var status = document.getElementById(url+'_tb').style.display;
	if (status=='none') {
		document.getElementById(url+'_tb').style.display='';
		document.getElementById(url+'_img').src='img/ups.gif';
	} else {
		document.getElementById(url+'_tb').style.display='none';
		document.getElementById(url+'_img').src='img/downs.gif';
	}
}
function redirectWin(url) {
	window.top.opener = null;
	window.top.close(); 
}
</script>
</head>
<body>
<table id="_TableHeader" width="100%" border="0" cellpadding="0" cellspacing="0" class="bluebg">
	<tr>
		<td height="121" colspan="2" valign="top">
		<div id="header">
		  <div class="logo_box"></div>
		  <div class="right_box"></div>
		</div>
		<div class="bar">
		  <div class="bar_l">尊敬的<span><s:property value="#session.user.truename"/></span>，欢迎您登陆管理信息化系统
		  	&nbsp;&nbsp;<%--<iframe frameborder="0" height="27" scrolling="no" src="engineershow/top.action" ></iframe>
          --%>当前为<s:property value="#session.currentrole"/>控制台（控制台切换:
            <s:iterator value="#session.rolesize" id="column">
   <a href="roleChange.action?roleType=<s:property value="value.roleType"/>" target="_top">
	<span><s:property value="value.roleTypeName" /></span>
	</a>

	</s:iterator>
	）
		  </div>
		  <div class="bar_r">
		    <div class="date">当前时间：<span id="todayTime"></span>&nbsp;<span id="hideTime" style="display:none"></span></div>
		  </div>
		</div>
		</td>
	</tr>
</table>
</body>
</html>