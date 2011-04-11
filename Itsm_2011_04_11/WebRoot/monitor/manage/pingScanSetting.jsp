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
    <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
    <title></title>
    <script src="js/Main.js" type="text/javascript"></script>	
	<link href="theme/css.css" rel="stylesheet" type="text/css" />
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
		
		-->
	</style>
<script>
//初始化
function init(){
	 document.getElementById("divAddTimePoint").style.display="none";//隐藏
	 if( document.getElementsByName("pingScan.pingExcuteMode")[0].checked==true){
	  	document.getElementById("divAuto").style.display="none";//隐藏
	 } else{
	 	document.getElementById("divAuto").style.display="";
	 }
	 if(document.getElementById("isDefinehours").checked==false){
	  	document.getElementById("pingStartTimeOfDay").disabled=true;
	 }else{
	 	document.getElementById("pingEndTimeOfDay").disabled=false;
	 }
	
	if(document.getElementsByName("pingScan.pingAutoExcuteMode")[0].checked==true){
		 	document.getElementById("divAutoExcute").style.display="";
		 	document.getElementById("divTimingExcute").style.display="none";
		 } else {
		 	document.getElementById("divAutoExcute").style.display="none";//隐藏
		 	document.getElementById("divTimingExcute").style.display="";
	}
}
//设置默认值
function setDefaultValue(){
	 document.getElementById("pingNum").value=4;
	 document.getElementById("pingSize").value=32; 
	 document.getElementById("pingTimeout").value=4000;
	 document.getElementById("pingDetailLife").value=2; 
	 document.getElementById("pingReportLife").value=180; 
	 document.getElementsByName("pingScan.pingExcuteMode")[0].checked=true;
	 document.getElementById("divAuto").style.display="none";//隐藏
	 
    
}
 
//提交
function doSubmit(){
	if(document.getElementById("pingNum").value==null||document.getElementById("pingNum").value==""){
		alert("请选择Ping次数！");
		return ;
	}
	if(document.getElementById("pingSize").value==null||document.getElementById("pingSize").value==""){
		alert("请选择Ping数据包长度！");
		return ;
	}
	if(document.getElementById("pingTimeout").value==null||document.getElementById("pingTimeout").value==""){
		alert("请选择Ping超时时间！");
		return ;
	}
	if(document.getElementById("pingDetailLife").value==null||document.getElementById("pingDetailLife").value==""){
		alert("请选择Ping细节数据保存时间 ！");
		return ;
	}
	if(document.getElementById("pingReportLife").value==null||document.getElementById("pingReportLife").value==""){
		alert("请选择Ping统计数据保存时间！");
		return ;
	}
	if( document.getElementsByName("pingScan.pingExcuteMode")[1].checked==true){
		if( document.getElementsByName("pingScan.pingExcuteMode")[1].checked==true){
		   var allTest = document.getElementsByName("pingScan.days");
	     	if(allTest.length>0){
	     	var b=false;
	         for(var i=0;i<allTest.length;i++){
	         	if(allTest[i].checked==true){
	         		b=true;
	         		break;
	         	}
	         	
	         }
	         if(b==false){
		        alert("请选择星期！");
				return ;
	         }
	     
	   	 }
		}
		
		if(document.getElementById("isDefinehours").checked==true){
			var time1 = document.getElementById("pingStartTimeOfDay").value;
			var time2 = document.getElementById("pingEndTimeOfDay").value;
			if(time2<=time1){
				alert("每天的Ping监测时间段中的结束时间必须大于开始时间！");
				return ;
			}
	
		}
		if(document.getElementsByName("pingScan.pingAutoExcuteMode")[0].checked==true){
			if(document.getElementById("pingLoopGap").value==null||document.getElementById("pingLoopGap").value==""){
				alert("请选择间隔时间！");
				return ;
			}
		}
	}
	
	 
	
	
	
	
	document.form.action="monitorSystemSettings/savePingScanSetting.action"
	document.form.submit();
}
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
	
	//删除 时间点
    function del(){   
		var msg="确认删除该时间吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
	//添加时间点
	function addTimePoint(){
		document.getElementById("divAddTimePoint").style.display="";//显示
	}
	//取消时间点
	function cancelTimePoint(){
		document.getElementById("divAddTimePoint").style.display="none";//隐藏
	}
	//保存时间点
	function saveTimePoint(){
		if(document.getElementById("addTimePointHour").value==null||document.getElementById("addTimePointHour").value==""){
			alert("请选择小时！");
			return ;
		}
		if(document.getElementById("addTimePointMinute").value==null||document.getElementById("addTimePointMinute").value==""){
			alert("请选择分钟！");
			return ;
		}
		var hour = document.getElementById("addTimePointHour").value;
		var minute = document.getElementById("addTimePointMinute").value;
		document.form.action="monitorSystemSettings/saveTimePoint.action?hour="+hour+"&minute="+minute;
		document.form.submit();
	}
	function setPingExcuteMode(a){
		 if(a=="1")
		 	document.getElementById("divAuto").style.display="";
		 else 
		 	document.getElementById("divAuto").style.display="none";//隐藏
	}
	function definehours(obj){
		 if(document.getElementById("isDefinehours").checked==false){
		  	document.getElementById("pingStartTimeOfDay").disabled=true;
		  	document.getElementById("pingEndTimeOfDay").disabled=true;
		 }else{
		 	document.getElementById("pingStartTimeOfDay").disabled=false;
		 	document.getElementById("pingEndTimeOfDay").disabled=false;
		 }
	}
	function setPingAutoExcuteMode(a){
		 if(a=="1"){
		 	document.getElementById("divAutoExcute").style.display="none";
		 	document.getElementById("divTimingExcute").style.display="";
		 } else {
		 	document.getElementById("divAutoExcute").style.display="";//隐藏
		 	document.getElementById("divTimingExcute").style.display="none";
		 }
		 	
	}
</script>
  </head>
  
  <body onload="init()">
  <s:form name="form" method="post" theme="simple" enctype="multipart/form-data"> 
  <br/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">管理>>故障检查 </td>
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
    <div style=”color:red”>
  <s:fielderror />
</div> 
    <td height="6" bgcolor="#FFFFFF" class="STYLE1">
		 <dl class="settings_options">
      		<dt>Ping参数 </dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr height=30>
	      			 	 
	      			 	<td width=200>
	      			 	 
	      			 		Ping次数 <s:select 
	      			 			id="pingNum"
	      			 			list="#{1:'1次',2:'2次',3:'3次',4:'4次',5:'5次'}" 
	      			 			name="pingScan.pingNum" 
	      			 			value="pingScan.pingNum" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
	      			 	</td>
	      			 	<td width=300>     
					      	Ping数据包长度<s:select 
	      			 			id="pingSize"
	      			 			list="#{32:'32字节',64:'64字节',128:'128字节',256:'256字节',512:'512字节',1024:'1024字节'}" 
	      			 			name="pingScan.pingSize" 
	      			 			value="pingScan.pingSize" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
					      
	      			 	</td>
	      			 	<td width=300>     
					      	Ping超时时间<s:select 
	      			 			id="pingTimeout"
	      			 			list="#{500:'500毫秒',1000:'1000毫秒',2000:'2000毫秒',3000:'3000毫秒',4000:'4000毫秒',5000:'5000毫秒'}" 
	      			 			name="pingScan.pingTimeout" 
	      			 			value="pingScan.pingTimeout" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
					      
	      			 	</td>
	      			 	 
	      			 </tr>   
	      		</table>
      			</dd>	
		</dl>
		
		
		<dl class="settings_options">
      		<dt>数据保存设置</dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr height=30>
	      			 	 
	      			 	<td width=200>
	      			 	 
	      			 		Ping细节数据保存时间 <s:select 
	      			 			id="pingDetailLife"
	      			 			list="#{1:'1天',2:'2天',3:'3天'}" 
	      			 			name="pingScan.pingDetailLife" 
	      			 			value="pingScan.pingDetailLife" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
	      			 	</td>
	      			 	<td width=300>     
					      	Ping统计数据保存时间<s:select 
	      			 			id="pingReportLife"
	      			 			list="#{7:'一星期',30:'一个月',90:'三个月',180:'半年',360:'一年'}" 
	      			 			name="pingScan.pingReportLife" 
	      			 			value="pingScan.pingReportLife" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
					      
	      			 	</td>
	      			 </tr>   
	      		</table>
      			</dd>	
		</dl>
		 <dl class="settings_options">
      		<dt>Ping执行方式</dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr >
	      			 	<td>
	      			 	<s:radio id="pingExcuteMode" name="pingScan.pingExcuteMode" onclick="setPingExcuteMode(this.value)"  list="#{0:'手动执行',1:'自动执行'}" ></s:radio>
	      			 	</td>
	      			 </tr>  
	      			
	      			 <tr  id ="whichDay" >
	      			  <td>
		      			  <div id="divAuto">
		      			  	<table>
		      			 		<tr >
		      			 		<td>
			      			 		<s:checkboxlist name ="pingScan.days" 
				      			  		list="#{'02':'星期一','03':'星期二','04':'星期三','05':'星期四','06':'星期五','07':'星期六','01':'星期日'}"  labelposition="top"
			         					listKey="key"
			         					listValue="value" ></s:checkboxlist>
		      			 		</td>
		      			 		</tr>
		      			 		<tr>
				      			 	<td>
				      			 	 <s:if test="pingScan.isDefinehours=='0'">
								     	 <input type="checkbox" id="isDefinehours" onclick="definehours(this)" name="pingScan.isDefinehours"   value="1"  >
								      </s:if>
								      <s:else>
								      	<input type="checkbox" id="isDefinehours" onclick="definehours(this)"  name="pingScan.isDefinehours" checked value="1"   >
								      </s:else>
								     	在下列时间段才监测
								     	
								     	<s:select 
				      			 			id="pingStartTimeOfDay"
				      			 			list="#{0:'00:00:00',1:'01:00:00',2:'02:00:00',3:'03:00:00',4:'04:00:00',5:'05:00:00',6:'06:00:00',7:'07:00:00',8:'08:00:00',9:'09:00:00',10:'10:00:00',11:'11:00:00',12:'12:00:00',13:'13:00:00',14:'14:00:00',15:'15:00:00',16:'16:00:00',17:'17:00:00',18:'18:00:00',19:'19:00:00',20:'20:00:00',21:'21:00:00',22:'22:00:00',23:'23:00:00'}" 
				      			 			name="pingScan.pingStartTimeOfDay" 
				      			 			value="pingScan.pingStartTimeOfDay" 
				      			 			theme="simple" 
				      			 			headerKey="" 
				      			 			headerValue="请选择"></s:select>
				      			 		<s:select 
				      			 			id="pingEndTimeOfDay"
				      			 			list="#{0:'00:00:00',1:'01:00:00',2:'02:00:00',3:'03:00:00',4:'04:00:00',5:'05:00:00',6:'06:00:00',7:'07:00:00',8:'08:00:00',9:'09:00:00',10:'10:00:00',11:'11:00:00',12:'12:00:00',13:'13:00:00',14:'14:00:00',15:'15:00:00',16:'16:00:00',17:'17:00:00',18:'18:00:00',19:'19:00:00',20:'20:00:00',21:'21:00:00',22:'22:00:00',23:'23:00:00'}" 
				      			 			name="pingScan.pingEndTimeOfDay" 
				      			 			value="pingScan.pingEndTimeOfDay" 
				      			 			theme="simple" 
				      			 			headerKey="" 
				      			 			headerValue="请选择"></s:select>
				      			 	</td>
				      			 </tr> 
			      			 	<td>
			      			 		<s:radio name="pingScan.pingAutoExcuteMode"   onclick="setPingAutoExcuteMode(this.value)" list="#{0:'循环执行',1:'定时'}" ></s:radio>
			      			 	</td>
	      			 			</tr> 
	      			 			 
				      			 <tr >
	      			 				<td >     
	      			 				<div id="divAutoExcute">
							      	间隔时间<s:select 
			      			 			id="pingLoopGap"
			      			 			list="#{5:'5分钟',10:'10分钟',20:'20分钟',30:'30分钟'}" 
			      			 			name="pingScan.pingLoopGap" 
			      			 			value="pingScan.pingLoopGap" 
			      			 			theme="simple" 
			      			 			headerKey="" 
			      			 			headerValue="请选择"></s:select>
			      			 		</div>
					      
	      			 				</td>
	      			 			</tr>
	      			 			 <tr >
	      			 				<td >     
	      			 				<div id="divTimingExcute">
	      			 				<table>
	      			 					<tr>
						      			 <td align= "right">
						      			  <input  type="button" class="mmBtn" value="添加" onclick="addTimePoint()">
						      			 </td>
					      				</tr>
					      				<tr >
						      			 <td >
						      			  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
									          <tr bgcolor="#FFFFFF"  class="STYLE1">
									            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
									            <td align="center" background="images/main20100521_58.gif">时间点</td>
									            <td align="center" background="images/main20100521_58.gif">操作</td>
									          </tr>
									          <%int i=1; %> 
											<s:iterator value="pingScan.timePointList" status="index">
											  <tr class="STYLE4">
											    <td height="26" align="center" bgcolor="#FFFFFF"> <%=i++ %> </td>
											    <td align="center" bgcolor="#FFFFFF"><s:date name="timePoint" format="HH:mm"/> </td>
											    <td align="center" bgcolor="#FFFFFF"><a href="monitorSystemSettings/deleteTimePoint.action?id=<s:property value="id"/>"  onclick="javascript:return del()">删除</a> </td>
											   
											  </tr>
											  </s:iterator>
									      </table>
						      			 </td>
						      			 </tr>
						      			 <tr>
							      			 <td>
							      			 <br/>
							      			  <div id="divAddTimePoint">
							      			   	时间点
							      			   	<s:select 
						      			 			id="addTimePointHour"
						      			 			list="#{0:'0时',1:'1时',2:'2时',3:'3时',4:'4时',5:'5时',6:'6时',
						      			 					7:'7时',8:'8时',9:'9时',10:'10时',11:'11时',12:'12时',
						      			 					13:'13时',14:'14时',15:'15时',16:'16时',17:'17时',18:'18时',
						      			 					19:'19时',20:'20时',21:'21时',22:'22时',23:'23时'}" 
						      			 			name="pingScan.addTimePointHour" 
						      			 			value="pingScan.addTimePointHour" 
						      			 			theme="simple" 
						      			 			headerKey="" 
						      			 			headerValue="请选择"></s:select>
						      			 		<s:select 
						      			 			id="addTimePointMinute"
						      			 			list="#{0:'00分',1:'01分',2:'02分',03:'03分',4:'04分',5:'05分',6:'06分',
						      			 					7:'07分',8:'08分',9:'09分',10:'10分',11:'11分',12:'12分',
						      			 					13:'13分',14:'14分',15:'15分',16:'16分',17:'17分',18:'18分',
						      			 					19:'19分',20:'20分',21:'21分',22:'22分',23:'23分',24:'24分',
						      			 					25:'25分',26:'26分',27:'27分',28:'28分',29:'29分',30:'30分',
						      			 					31:'31分',32:'32分',33:'33分',34:'34分',35:'35分',36:'36分',
						      			 					37:'37分',38:'38分',39:'39分',40:'40分',41:'41分',42:'42分',
						      			 					43:'43分',44:'44分',45:'45分',46:'46分',47:'47分',48:'48分',
						      			 					49:'49分',50:'50分',51:'51分',52:'52分',53:'53分',54:'54分',
						      			 					55:'55分',56:'56分',57:'57分',58:'58分',59:'59分'
						      			 					}" 
						      			 			name="pingScan.addTimePointMinute" 
						      			 			value="pingScan.addTimePointMinute" 
						      			 			theme="simple" 
						      			 			headerKey="" 
						      			 			headerValue="请选择"></s:select>
						      			 			<input  type="button" class="mmBtn" value="提交" onclick="saveTimePoint()">
						      			 			<input  type="button" class="mmBtn" value="取消" onclick="cancelTimePoint()">
							      			  </div>
							      			 </td>
						      			 </tr>
	      			 				</table>
	      			 				</div>
	      			 				</td>
	      			 			</tr>
	      			 
	      			 
	      			
	      			 
	      			  
		      			 		
		      			 	</table>
	         			  </div>
	      			 </td>
	      			 
	      			</tr>
	      			 
	      			
	      			
	      			 
	      		</table>
      			</dd>	
		</dl>
		<table class="STYLE4">
		<br/>
		   <tr>
		   	<td >
				<input name="button" type="button" class="mmBtn" onClick="doSubmit()" value="保存">
			    <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="重置">  
			    <input name="button" type="button" class="mmBtn" onClick="setDefaultValue()" value="默认">   
			    <input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value="取消"> 
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

</s:form>

</body>
</html>
