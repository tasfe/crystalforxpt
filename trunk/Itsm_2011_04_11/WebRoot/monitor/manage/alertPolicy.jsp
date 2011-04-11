<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<%=path%>/theme/css.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/MonitorAlertPolicyService.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
    <script src="<%=path%>/js/Main.js" type="text/javascript"></script>	
    <title></title>
    
	
	<style type="text/css">
		<!--
		.STYLE4 {color: #03515d;
			font-size: 12px;
		}
		.STYLE1 {color:#022e44;
			font-size: 12px;
			font-weight:bold;
		}
		
		/* Settings */
		body.settings #primary, body.settings #secondary{
        	padding:5px;
        	
		}
		/* Settings main page choice */
		dl.settings_options{
		  list-style:none;
		  width:100%;
		  margin:0;
		  padding:10px 0 0 0;
		  float:left;
		  clear:left;
		}
		
		dl.settings_options dt{
		  border-bottom:1px solid #ccc;
		  font-weight:bold;
		  margin:0 0 10px;
		}
		
		dl.settings_options dd{
		  margin:0;
		  padding:0;
		  display:inline;
		}
		dl.settings_options dd a{
		  text-decoration:none;
		  display:block;
		  float:left;
		  min-width:260px;
		  min-height:40px;
		  /* see hacks.ie6.css */
		  margin:0 10px 20px 10px;
		  padding:5px 5px 5px 50px;
		  /* the background-image property is applied in-line */
		  background-color:transparent;
		  background-repeat:no-repeat;
		  background-position:top left;
		  outline:none;
		}
		dl.settings_options dd a strong{
		  display:block;
		  font-size:1.0em;
		  font-weight:bold;
		  color:#333;
		}
		dl.settings_options dd a span{
		  display:block;
		  font-size:12px;
		  color:#888;
		}
		dl.settings_options dd a:hover strong{ text-decoration:underline; }
		dl.settings_options dd a:hover span{ text-decoration:none; }
		dl.settings_options dd a:active{ outline:none; /* for firefox */ }
		body.abstract_settings h3{
		  clear:left;
		  padding:200px 0 0;
		  margin:0;
		  text-align:center;
		  font-size:1.1em;
		  font-weight:normal;
		  color:#888;
		}
		body {font-family:Arial, Helvetica, sans-serif; font-size:11px;}
		/*Example for a Menu Style*/
		.menu {background-color:#008bd3;border-bottom:1px solid #d7d7d7; height:23px;width:100%;}
		.menu ul {margin:0px; padding:0px; list-style:none; text-align:left;}
		.menu li {display:inline; line-height:23px;}
		.menu li a {color:#ffffff; text-decoration:none; padding:5px 5px 6px 5px; }
		.menu li a.tabactive {border-left:1px solid #d7d7d7; border-right:1px solid #d7d7d7; color:#000000; background-color:#ffffff; font-weight:bold;  position:relative;}
		#tabcontent1,#tabcontent2,#tabcontent3,#tabcontent4 {border:1px solid #ececec; width:100%; text-align:center;padding:6px 0px; font-size:12px; margin-bottom:5px;}
		-->
	</style>
<script>
//EASY TABS 1.2 - MENU SETTINGS
var tablink_idname = new Array("tablink")
var tabcontent_idname = new Array("tabcontent") 
var tabcount = new Array("4")
var loadtabs = new Array("1")  
var autochangemenu = 2;
var changespeed = 2;
var stoponhover = 1;
/*Swich EasyTabs Functions - no need to edit something here*/
function easytabs(menunr, active) {if (menunr == autochangemenu){currenttab=active;}if ((menunr == autochangemenu)&&(stoponhover==1)) {stop_autochange()} else if ((menunr == autochangemenu)&&(stoponhover==0))  {counter=0;} menunr = menunr-1;for (i=1; i <= tabcount[menunr]; i++){document.getElementById(tablink_idname[menunr]+i).className='tab'+i;document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';}document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' tabactive';document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';}var timer; counter=0; var totaltabs=tabcount[autochangemenu-1];var currenttab=loadtabs[autochangemenu-1];function start_autochange(){counter=counter+1;timer=setTimeout("start_autochange()",1000);if (counter == changespeed+1) {currenttab++;if (currenttab>totaltabs) {currenttab=1}easytabs(autochangemenu,currenttab);restart_autochange();}}function restart_autochange(){clearTimeout(timer);counter=0;start_autochange();}function stop_autochange(){clearTimeout(timer);counter=0;}

window.onload=function(){
var menucount=loadtabs.length; var a = 0; var b = 1; do {easytabs(b, loadtabs[a]);  a++; b++;}while (b<=menucount);
if (autochangemenu!=0){start_autochange();}
}
//初始化
function init(){
	//初始化报警时间选项
	 if(document.getElementById("isDefinehours").checked==false){
	  	document.getElementById("dailyStart").disabled=true;
	  	document.getElementById("dailyEnd").disabled=true;
	 }else{
	 	document.getElementById("dailyStart").disabled=false;
	 	document.getElementById("dailyEnd").disabled=false;
	 }
	 //初始化报警方式选项
	 if(document.getElementById("alertPolicy.mobileSwitch").value=="true"){
	 	document.getElementById("cellphone").disabled=false;
	 	var myinput = document.getElementsByName("cellphone");
		for(var i=0;i<myinput.length;i++){
			if(myinput[i].type=="checkbox"){
				
				myinput[i].disabled=false;
			}
		}
	 }else{
	 	document.getElementById("cellphone").disabled=true;
	 	var myinput = document.getElementsByName("cellphone");
		for(var i=0;i<myinput.length;i++){
			if(myinput[i].type=="checkbox"){
				
				myinput[i].disabled=true;
			}
		}
	 
	 }
	 
	 if(document.getElementById("alertPolicy.emailSwitch").value=="true"){
	 	document.getElementById("email").disabled=false;
	 	var myinput = document.getElementsByName("email");
		for(var i=0;i<myinput.length;i++){
			if(myinput[i].type=="checkbox"){
				
				myinput[i].disabled=false;
			}
		}
	 }else{
	 	document.getElementById("email").disabled=true;
	 	var myinput = document.getElementsByName("email");
		for(var i=0;i<myinput.length;i++){
			if(myinput[i].type=="checkbox"){
				
				myinput[i].disabled=true;
			}
		}
	 
	 }
	 //初始化用户的报警方式选项
	 var user = document.getElementById("alertReceivers").value;
	 if(user.length>0){
	 	var users = user.split(",");
	 	for(var i=0;i<users.length;i++){
	 		var selectedUserOptions = users[i].split("@");
	 		if(selectedUserOptions.length>0){
	 			if(selectedUserOptions[1]=="1"){
	 				var id = "cellphone_"+selectedUserOptions[0];
	 				document.getElementById(id).checked=true;
	 			}
	 			if(selectedUserOptions[2]=="1"){
	 				var id = "email_"+selectedUserOptions[0];
	 				document.getElementById(id).checked=true;
	 			}
	 		}
	 		 
	 	}
	 }
	
}
//全选/全不选
	function selectAll1(tempControl){
     	var theBox=tempControl;
      	xState=theBox.checked;
      	var el = document.getElementsByName("cellphone");
      	var len = el.length;
        for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox") 
        	 el[i].checked = xState;
        }
    }
    function selectAll2(tempControl){
     	var theBox=tempControl;
      	xState=theBox.checked;
      	var el = document.getElementsByName("email");
      	var len = el.length;
        for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox") 
        	 el[i].checked = xState;
        }
    }
    
//设置默认值
function setDefaultValue(){
	 document.getElementById("limenRetry").value=1;
	 document.getElementById("errorRetry").value=1;
	 document.getElementById("tempRetry").value=1;
	 var el = document.getElementsByName("alertPolicy.days");
      	var len = el.length;
        for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox") 
        	 el[i].checked = true;
        }
	 document.getElementById("isDefinehours").checked=false;
	 document.getElementById("dailyStart").disabled=true;
	 document.getElementById("dailyEnd").disabled=true;
	 document.getElementById("dailyStart").value=0;
	 document.getElementById("dailyEnd").value=0;
	 
	 document.getElementById("alertPolicy.monitorAlertPolicy.mobileSwitch").checked=true;
	 document.getElementById("alertPolicy.monitorAlertPolicy.emailSwitch").checked=true;
	 document.getElementById("alertPolicy.monitorAlertPolicy.soundSwitch").checked=true;
	 
	 var ele = document.getElementsByName("alertPolicy.alertSmallTypes1");
     len = ele.length;
        for(var j=0; j<len; j++) {
        	if(ele[j].type=="checkbox") 
        	 if(ele[j].value=="1" || ele[j].value=="2" || ele[j].value=="3"){
        	 	ele[j].checked = true;
        	 }else{
        	 	ele[j].checked = false;
        	 }	
     }
     
     ele = document.getElementsByName("alertPolicy.alertSmallTypes2");
     len = ele.length;
        for( j=0; j<len; j++) {
        	if(ele[j].type=="checkbox") 
        	 ele[j].checked = false;
     }
     ele = document.getElementsByName("alertPolicy.alertSmallTypes3");
     len = ele.length;
        for( j=0; j<len; j++) {
        	if(ele[j].type=="checkbox") 
        	 ele[j].checked = false;
     }
}

function checkNum(obj,oldvalue){
	if(!isNumber(obj.value)){
		alert("请填入数字");
		if(null!=oldvalue&&""!=oldvalue)
				obj.value=oldvalue;
			else
				obj.value=0;
		return;
	}
}
function checkName(){
	var name = document.getElementById("policyName").value;

	if(name==null||name==""){
		alert("请填写报警策略名称！");
		return false;
	}
	var id = document.getElementById("policyId").value;
	MonitorAlertPolicyService.checkName(name,id,afterCheckName);
}
function afterCheckName(data){
	if(data){
		alert("已有此名称的报警策略，请重新填写！");
		return ;
	}else{
		doSubmit();
	}
		 
}
//提交
function doSubmit(){
	

	if(document.getElementById("errorRetry").value==null||document.getElementById("errorRetry").value==""){
		alert("请选择故障检查 连续几次异常后发出报警！");
		return ;
	}
	if(document.getElementById("limenRetry").value==null||document.getElementById("limenRetry").value==""){
		alert("请选择CPU、流量阈值检查连续几次异常后发出报警！");
		return ;
	}
	if(document.getElementById("tempRetry").value==null||document.getElementById("tempRetry").value==""){
		alert("请选择温度阈值检查连续几次异常后发出报警！");
		return ;
	}
	if(!checkDays()){
		if(!window.confirm('一周中都没有配置监测的日期，所以系统不会对此策略关联的设备进行监测，是否继续？'))
               return; 
	}
	if(!checkTypes()){
		if(!window.confirm('未选择任何报警类型，是否继续？'))
               return; 
	}
	document.getElementById("alertReceivers").value=checkSelectedUsers();
	document.form.target="mainFrame";
	document.form.action="monitorSystemSettings/saveAlertPolicy.action";
	document.form.submit();
}
function checkSelectedUsers(){
	var selectedUsers = "";
	var el = document.getElementsByName("cellphone");
    var len = el.length;
    for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox") {
        	var id = el[i].id;
        	if(id.indexOf("_")>-1){
	        	id = id.substring(id.indexOf("_")+1,id.length);
	        	
	        	if(el[i].checked || document.getElementById("email_"+id).checked ){
	        		var iscellphone = "0";
	        		var isemail = "0";
	        		if(el[i].checked)
	        			iscellphone = "1";
	        		if(document.getElementById("email_"+id).checked)
	        			isemail = "1";	
	        		selectedUsers  = selectedUsers + id+"@"+iscellphone+"@"+isemail+",";  		
	        	}
        	}
        }
        	 
    }
    
    if(selectedUsers.length>0)
    	selectedUsers = selectedUsers.substring(0,selectedUsers.length-1);
    return selectedUsers;
}
//是否选择星期
function checkDays(){
	var result = false;
	var el = document.getElementsByName("alertPolicy.days");
    var len = el.length;
    for(var i=0; i<len; i++) {
    	if(el[i].type=="checkbox"&& el[i].checked ) {
    		result = true;
    		break;
        }
    }
    return result;
}
//是否选择任何报警类型
function checkTypes(){
	var result = false;
	var el = document.getElementsByName("alertPolicy.alertSmallTypes1");
    var len = el.length;
    for(var i=0; i<len; i++) {
    	if(el[i].type=="checkbox"&& el[i].checked ) {
    		result = true;
    		break;
        }
    }
    
    var el = document.getElementsByName("alertPolicy.alertSmallTypes2");
    var len = el.length;
    for(var i=0; i<len; i++) {
    	if(el[i].type=="checkbox"&& el[i].checked ) {
    		result = true;
    		break;
        }
    }
    
    var el = document.getElementsByName("alertPolicy.alertSmallTypes3");
    var len = el.length;
    for(var i=0; i<len; i++) {
    	if(el[i].type=="checkbox"&& el[i].checked ) {
    		result = true;
    		break;
        }
    }
    
    return result;
}
function definehours(obj){

	if(document.getElementById("isDefinehours").checked){
  
	  	document.getElementById("dailyStart").disabled=false;
	  	document.getElementById("dailyEnd").disabled=false;
	 }else{
	 
	 	document.getElementById("dailyStart").disabled=true;
	 	document.getElementById("dailyEnd").disabled=true;
	 }
}
	
</script>
  </head>
  
  <body onload="init()">
 
  <s:form name="form" method="post" theme="simple"> 
  <br/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">报警策略修改</td>
    <td width="85%" align="right" background="images/listconframe_07.gif"></td>
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
    <td height="6" bgcolor="#FFFFFF" class="STYLE1">
	    <!--Start of the Tabmenu 1 -->
		<div class="menu">
		<ul>
		<li><a class="tab1" href="#" onmouseover="easytabs('1', '1');" onfocus="easytabs('1', '1');" onclick="return false;" title="" id="tablink1">基本信息</a></li>
		<li><a class="tab2" href="#" onmouseover="easytabs('1', '2');" onfocus="easytabs('1', '2');" onclick="return false;" title="" id="tablink2">报警时间 </a></li>
		<li><a class="tab3" href="#" onmouseover="easytabs('1', '3');" onfocus="easytabs('1', '3');" onclick="return false;" title="" id="tablink3">报警方式 </a></li>
		<li><a class="tab4 tabactive" href="#" onmouseover="easytabs('1', '4');" onfocus="easytabs('1', '4');" onclick="return false;" title="" id="tablink4">报警类型 </a>
		</li>
		</ul>
		</div>
		<!--End of the Tabmenu 1 -->
		
		
		<!--Start Tabcontent 1 -->
		<div style="display: none;" id="tabcontent1">
		<table width="90%" align="center">
		   <tr>
		   	<td width="10%" align="left">
				名称： 
	      	</td>
	      	<td width="80%" align="left">
				 <input type="text" id="policyName" name="alertPolicy.monitorAlertPolicy.name" value="<s:property value='alertPolicy.monitorAlertPolicy.name'/>">
	      	</td>
	      	</tr>
	      	<tr>
	      	<td width="10%" align="left">
				修改时间:
	      	</td>
	      	<td width="80%" align="left">
	      		<s:date name="alertPolicy.monitorAlertPolicy.modifyTime" format="yyyy/MM/dd HH:mm:ss" />
	      	</td>
	      	</tr>
	      	<tr>
	      	<td width="10%" align="left">
				备注：
	      	</td>
	      	<td width="80%" align="left">
				<textarea id="note" name="alertPolicy.monitorAlertPolicy.note" rows="8" cols="86"
								style="width: 100%; height: 120;"></textarea>
	      	</td>
	      </tr>
      	</table> 
		</div>
		<!--End Tabcontent 1-->
		
		<!--Start Tabcontent 2-->
		<div style="display: none;" id="tabcontent2">
		<table width="90%" align="center">
		   <tr>
		   	<td>
			<dl class="settings_options">
      		<dt>监测时间间隔（时间参数请在全局报警设置中修改）</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
	      			 	<td width=200>
	      			 	 故障/阈值检查时间间隔
	      			 	</td>
	      			 	<td width=300>
	      			 	 每 <input type="text" style="width:20px;" disabled name="alertPolicy.checkGap" value="<s:property value='alertPolicy.checkGap'/>">分钟监测一次
	      			 	</td>
      			   </tr>
      			 	<tr>
	      			 	<td width=200>
	      			 	 异常后的检查时间间隔
	      			 	</td>
	      			 	<td width=300>
	      			 	 每 <input type="text" style="width:20px;" disabled name="alertPolicy.errorGap" value="<s:property value='alertPolicy.errorGap'/>">分钟监测一次
	      			 	</td>
      			   </tr>
      			   <tr>
	      			 	<td width=200>
	      			 	故障检查 
	      			 	</td>
	      			 	<td width=300>
	      			 	连续
	      			 	<s:select 
	      			 			id="errorRetry"
	      			 			list="#{1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}" 
	      			 			name="alertPolicy.monitorAlertPolicy.errorRetry" 
	      			 			value="alertPolicy.monitorAlertPolicy.errorRetry" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			style="width:50px;"
	      			 			headerValue="请选择"></s:select>
	      			 			
	      			 	 次异常后发出报警
	      			 	</td>
      			   </tr>
      			   <tr>
	      			 	<td width=200>
	      			 	CPU、流量阈值检查
	      			 	</td>
	      			 	<td width=300>
	      			 	连续
	      			 	<s:select 
	      			 			id="limenRetry"
	      			 			list="#{1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}" 
	      			 			name="alertPolicy.monitorAlertPolicy.limenRetry" 
	      			 			value="alertPolicy.monitorAlertPolicy.limenRetry" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			style="width:50px;"
	      			 			headerValue="请选择"></s:select>
	      			 			
	      			 	 次异常后发出报警
	      			 	</td>
      			   </tr>
      			    <tr>
	      			 	<td width=200>
	      			 	温度阈值检查 
	      			 	</td>
	      			 	<td width=300>
	      			 	连续
	      			 	<s:select 
	      			 			id="tempRetry"
	      			 			list="#{1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10'}" 
	      			 			name="alertPolicy.monitorAlertPolicy.tempRetry" 
	      			 			value="alertPolicy.monitorAlertPolicy.tempRetry" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			style="width:50px;"
	      			 			headerValue="请选择"></s:select>
	      			 			
	      			 	 次异常后发出报警
	      			 	</td>
      			   </tr>
      			 </table>
      			</dd>
		</dl> 
		<dl class="settings_options">
      		<dt>监测时间过滤</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
	      			 	<td width=200>
	      			 	<s:if test="alertPolicy.isDefinehours==0">
							<input type="checkbox" id="isDefinehours" onclick="definehours(this)" name="alertPolicy.isDefinehours"   value="1"  >
						</s:if>
						<s:else>
							<input type="checkbox" id="isDefinehours" onclick="definehours(this)"  name="alertPolicy.isDefinehours" checked value="1"   >
						</s:else>
								     	在下列时间段才监测
	      			 	</td>
	      			 	<td width=300>
	      			 				<s:select 
				      			 			id="dailyStart"
				      			 			list="#{0:'00:00:00',1:'01:00:00',2:'02:00:00',3:'03:00:00',4:'04:00:00',5:'05:00:00',6:'06:00:00',7:'07:00:00',8:'08:00:00',9:'09:00:00',10:'10:00:00',11:'11:00:00',12:'12:00:00',13:'13:00:00',14:'14:00:00',15:'15:00:00',16:'16:00:00',17:'17:00:00',18:'18:00:00',19:'19:00:00',20:'20:00:00',21:'21:00:00',22:'22:00:00',23:'23:00:00'}" 
				      			 			name="alertPolicy.monitorAlertPolicy.dailyStart" 
				      			 			value="alertPolicy.monitorAlertPolicy.dailyStart" 
				      			 			theme="simple" 
				      			 			headerKey="" 
				      			 			headerValue="请选择"></s:select>
				      			 		<s:select 
				      			 			id="dailyEnd"
				      			 			list="#{0:'00:00:00',1:'01:00:00',2:'02:00:00',3:'03:00:00',4:'04:00:00',5:'05:00:00',6:'06:00:00',7:'07:00:00',8:'08:00:00',9:'09:00:00',10:'10:00:00',11:'11:00:00',12:'12:00:00',13:'13:00:00',14:'14:00:00',15:'15:00:00',16:'16:00:00',17:'17:00:00',18:'18:00:00',19:'19:00:00',20:'20:00:00',21:'21:00:00',22:'22:00:00',23:'23:00:00'}" 
				      			 			name="alertPolicy.monitorAlertPolicy.dailyEnd" 
				      			 			value="alertPolicy.monitorAlertPolicy.dailyEnd" 
				      			 			theme="simple" 
				      			 			headerKey="" 
				      			 			headerValue="请选择"></s:select>
	      			 	</td>
      			   </tr>
      			   <tr>
	      			 	<td colspan=2>
	      			 	 <s:checkboxlist name ="alertPolicy.days" 
				      			  		list='#{"2":"星期一","3":"星期二","4":"星期三","5":"星期四","6":"星期五","7":"星期六","1":"星期日"}'  labelposition="top"
			         					listKey="key"
			         					listValue="value" ></s:checkboxlist>
	      			 	</td>
	      			 	 
      			   </tr>

      			 </table>
	      	</dd>
			</dl> 
	      	</td>
	      </tr>
      	</table> 
		</div>
		<!--End Tabcontent 2 -->
		
		<!--Start Tabcontent 3-->
		<div style="display: none;" id="tabcontent3">
		<table width="90%" align="center">
		   <tr>
		   	<td>
		   		<s:hidden id="alertPolicy.mobileSwitch" name ="alertPolicy.mobileSwitch" ></s:hidden>
		   		<s:hidden id="alertPolicy.emailSwitch"  name ="alertPolicy.emailSwitch" ></s:hidden>
		   		<s:hidden id="alertPolicy.soundSwitch"  name ="alertPolicy.soundSwitch" ></s:hidden>
		   		<dl class="settings_options">
	      		<dt>全局报警状态为：
	      		短信报警--<s:if test="alertPolicy.mobileSwitch">开</s:if><s:else>关</s:else>; 
	      		 邮件报警--<s:if test="alertPolicy.emailSwitch">开</s:if><s:else>关</s:else>;  
	      		 声音报警--<s:if test="alertPolicy.soundSwitch">开</s:if><s:else>关</s:else></dt>
	      			<dd id="">
	      			 <table class="STYLE4" width="99%">
	      			 	<tr>
		      			 	<td width=33%>
		      			 	<s:if test="alertPolicy.mobileSwitch">
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.mobileSwitch" name="alertPolicy.monitorAlertPolicy.mobileSwitch" > </s:checkbox>
		      			 	</s:if>
		      			 	<s:else>
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.mobileSwitch" name="alertPolicy.monitorAlertPolicy.mobileSwitch" onclick="return false"> </s:checkbox>
		      			 	</s:else>
							发送短信报警	
		      			 	</td> 
		      			 	<td width=33%>
		      			 	<s:if test="alertPolicy.emailSwitch">
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.emailSwitch" name="alertPolicy.monitorAlertPolicy.emailSwitch" > </s:checkbox>
		      			 	</s:if>
		      			 	<s:else>
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.emailSwitch"  name="alertPolicy.monitorAlertPolicy.emailSwitch" onclick="return false"> </s:checkbox>
		      			 	</s:else>
							发送邮件报警	
		      			 	</td>
		      			 	<td width=33%>
		      			 	<s:if test="alertPolicy.soundSwitch">
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.soundSwitch"  name="alertPolicy.monitorAlertPolicy.soundSwitch" > </s:checkbox>
		      			 	</s:if>
		      			 	<s:else>
		      			 		<s:checkbox id="alertPolicy.monitorAlertPolicy.soundSwitch" name="alertPolicy.monitorAlertPolicy.soundSwitch" onclick="return false"> </s:checkbox>
		      			 	</s:else>
							声音报警	
		      			 	</td>
	      			   </tr>
	      			   <tr><td colspan=3>
		      			   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			      			   <tr bgcolor="#b5d6e6">
						        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
						        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">用户名</td>        
						        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">用户类型</td>        
						        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle"><input type="checkbox" id="cellphone" onclick="javascript:selectAll1(this);"/>手机号码</td>
						        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle"><input type="checkbox" id="email" onclick="javascript:selectAll2(this);"/>邮编</td>
						      </tr>
	        					<%int i=1; %> 
							      <s:iterator value="alertPolicy.userList" var="user">
							        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
									 <td height="19" align="center" ><%=i++ %></td>
							          <td align="left" ><s:property value="username"/></td>
							          <td align="left" ><s:property value="usertype"/></td>
							          <td align="left" ><input type="checkbox" name="cellphone" id="cellphone_<s:property value='id'/>"/> <s:property value="cellphone"/></td>
							          <td align="left" ><input type="checkbox" name="email" id="email_<s:property value='id'/>"/> <s:property value="email"/></td>
							        </tr>
							      </s:iterator>
	      			   </table>
	      			   
	      			   </td></tr>
	      			 </table>
		      	</dd>
				</dl> 
			</td>
		   	</tr>
		 </table>
		</div>
		<!--End Tabcontent 3-->
		
		<!--Start Tabcontent 4-->
		<div style="display: block;" id="tabcontent4">
		<table width="90%" align="center">
		   <tr>
		   	<td>
		   		<dl class="settings_options">
	      		<dt>重要报警</dt>
	      			<dd id="">
	      			 <table class="STYLE4">
	      			 	<tr>
		      			 	<td width=100%>
		      			 	 <s:checkboxlist name ="alertPolicy.alertSmallTypes1" 
				      			  		list="alertPolicy.alertSmallTypeList1"  
				      			  		labelposition="top"
			         					listKey="code"
			         					listValue="name" ></s:checkboxlist>
								 
		      			 	</td> 
	      			   </tr>
	      			 </table>
		      	</dd>
				</dl> 
				<dl class="settings_options">
	      		<dt>普通报警</dt>
	      			<dd id="">
	      			 <table class="STYLE4">
	      			 	<tr>
		      			 	<td width=100%>
		      			 	 <s:checkboxlist name ="alertPolicy.alertSmallTypes2" 
				      			  		list="alertPolicy.alertSmallTypeList2"  
				      			  		labelposition="top"
			         					listKey="code"
			         					listValue="name" ></s:checkboxlist>
								 
		      			 	</td> 
	      			   </tr>
	      			 </table>
		      	</dd>
				</dl> 
				<dl class="settings_options">
	      		<dt>次要报警</dt>
	      			<dd id="">
	      			 <table class="STYLE4">
	      			 	<tr>
		      			 	<td width=100%>
		      			 	 <s:checkboxlist name ="alertPolicy.alertSmallTypes3" 
				      			  		list="alertPolicy.alertSmallTypeList3"  
				      			  		labelposition="top"
			         					listKey="code"
			         					listValue="name" ></s:checkboxlist>
								 
		      			 	</td> 
	      			   </tr>
	      			 </table>
		      	</dd>
				</dl> 
		   	</td>
		   	</tr>
		 </table>
			
		</div>
		 
	
		<table class="STYLE4">
		<br/>
		   <tr>
		   	<td >
				<input name="button" type="button" class="mmBtn" onClick="checkName()" value="保存">
			    <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="重置">  
			    <input name="button" type="button" class="mmBtn" onClick="setDefaultValue()" value="默认">   
	      	</td>
	      </tr>
      	</table> 
      </td>
    <td width="10" background="images/main20100521_50.gif">&nbsp;</td>
  </tr>
  	<tr>
    <td width="10" height="10"><img src="images/main20100521_66.gif" width="10" height="28" alt=""></td>
    <td height="10" background="images/main20100521_67.gif">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    </table>
    </td>
    <td width="10" height="10"><img src="images/main20100521_69.gif" width="10" height="28" alt=""></td>
  </tr>
</table>

<br/>
<s:hidden id="alertReceivers" name ="alertPolicy.monitorAlertPolicy.alertReceivers" ></s:hidden>
<s:hidden id="policyId" name ="alertPolicy.monitorAlertPolicy.id" ></s:hidden>
</s:form>

</body>
</html>
