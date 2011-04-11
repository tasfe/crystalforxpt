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
function check1(obj1,number,oldvalue){
	if(!isNumber(obj1.value)){
		alert("请填入数字");
		if(null!=oldvalue&&""!=oldvalue)
				obj1.value=oldvalue;
			else
				obj1.value=0;
		return;
	} else{
		if(parseInt(obj1.value)>parseInt (number)||parseInt(obj1.value)==parseInt(number)){
			alert("一般阈值必须小于严重阈值！");
			if(null!=oldvalue&&""!=oldvalue)
				obj1.value=oldvalue;
			else
				obj1.value=0;
			
			return;
		}
		
	}
}
function check2(obj1,number,oldvalue){
	if(!isNumber(obj1.value)){
		alert("请填入数字");
		if(null!=oldvalue&&""!=oldvalue)
				obj1.value=oldvalue;
			else
				obj1.value=0;
		return;
	} else{
		if(parseInt(obj1.value)<parseInt(number)||parseInt(obj1.value)==parseInt(number)){
			alert("严重阈值必须大于一般阈值！");
			if(null!=oldvalue&&""!=oldvalue)
				obj1.value=oldvalue;
			else
				obj1.value=0;
			return;
		}
		
	}
}
//设置默认值
function setDefaultValue(){
	 document.getElementById("cpuLimen1").value=30;
	 document.getElementById("cpuLimen2").value=60; 
	 document.getElementById("bandLimen1").value=5;
	 document.getElementById("bandLimen2").value=10; 
	 document.getElementById("unicastLimen1").value=1000;
	 document.getElementById("unicastLimen1").value=2000; 
	 document.getElementById("broadcastLimen1").value=50;
	 document.getElementById("broadcastLimen2").value=100; 
	 document.getElementById("tempLimen1").value=25;
	 document.getElementById("tempLimen2").value=40; 
	 document.getElementById("arpLimen1").value=5;
	 document.getElementById("arpLimen2").value=10; 
	 
	 document.getElementById("tcpConn1").value=100;
	 document.getElementById("tcpConn2").value=150; 
	 document.getElementById("procNum1").value=50;
	 document.getElementById("procNum2").value=80; 
	 document.getElementById("memRate1").value=90;
	 document.getElementById("memRate2").value=95; 
	 document.getElementById("vmemRate1").value=90;
	 document.getElementById("vmemRate2").value=95; 
	 document.getElementById("diskRate1").value=90;
	 document.getElementById("diskRate2").value=95; 
 
}
function doSubmit(){
	 
	document.form.action="monitorSystemSettings/saveThresholdSetting.action"
	document.form.submit();
}

	
</script>
  </head>
  
  <body>
  <s:form name="form" method="post" theme="simple"> 
  <br/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">阈值>>阈值 </td>
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
   
    
		 <dl class="settings_options">
      		<dt>警示阈值</dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr><td></td><td width=150>一般</td><td width=150>严重</td></tr>
	      			 <tr>
	      			 	<td>CPU警示阈值（%）</td>
	      			 	<s:hidden name="threshold.cpuLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.cpuLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.cpuLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.cpuLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.cpuLimen.isuse"></s:hidden>
	      			 	<td width=150><input type="text" id="cpuLimen1" onChange="check1(this,cpuLimen2.value,<s:property value="threshold.cpuLimen.value"/>)" name="threshold.cpuLimen.value" value="<s:property value="threshold.cpuLimen.value"/>" style="width: 90%"></td>
	      			 	<td width=150><input type="text" id="cpuLimen2" onChange="check2(this,cpuLimen1.value,<s:property value="threshold.cpuLimen.highValue"/>)" name="threshold.cpuLimen.highValue" value="<s:property value="threshold.cpuLimen.highValue"/>" style="width: 90%"></td>
	      			 </tr>
	      			 <tr>
	      			 	<td>带宽警示阈值（%）</td>
	      			 	<s:hidden name="threshold.bandLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.bandLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.bandLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.bandLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.bandLimen.isuse"></s:hidden>
	      			 	<td ><input type="text" id="bandLimen1" name="threshold.bandLimen.value" onChange="check1(this,bandLimen2.value,<s:property value="threshold.bandLimen.value"/>)"  value="<s:property value="threshold.bandLimen.value"/>" style="width: 90%"></td>
	      			 	<td ><input type="text" id="bandLimen2" name="threshold.bandLimen.highValue" onChange="check2(this,bandLimen1.value,<s:property value="threshold.bandLimen.highValue"/>)"  value="<s:property value="threshold.bandLimen.highValue"/>" style="width: 90%"></td>
	      			 </tr>
      				 <tr>
      				 	<td>单播包警示阈值（个/ 秒）</td>
      				 	<s:hidden name="threshold.unicastLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.unicastLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.unicastLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.unicastLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.unicastLimen.isuse"></s:hidden>
      				 	<td ><input type="text" id="unicastLimen1" name="threshold.unicastLimen.value" onChange="check1(this,unicastLimen2,<s:property value="threshold.unicastLimen.value"/>)" value="<s:property value="threshold.unicastLimen.value"/>" style="width: 90%"></td>
      				 	<td ><input type="text" id="unicastLimen2" name="threshold.unicastLimen.highValue" onChange="check2(this,unicastLimen1,<s:property value="threshold.unicastLimen.highValue"/>)"  value="<s:property value="threshold.unicastLimen.highValue"/>" style="width: 90%"></td>
      				 </tr>
	      			 <tr>
	      			 	<td>广播包警示阈值（个/ 秒）</td>
	      			 	<s:hidden name="threshold.broadcastLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.broadcastLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.broadcastLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.broadcastLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.broadcastLimen.isuse"></s:hidden>
	      			 	<td ><input type="text" id="broadcastLimen1" name="threshold.broadcastLimen.value" onChange="check1(this,broadcastLimen2.value,<s:property value="threshold.broadcastLimen.value"/>)" value="<s:property value="threshold.broadcastLimen.value"/>" style="width: 90%"></td>
	      			 	<td ><input type="text" id="broadcastLimen2" name="threshold.broadcastLimen.highValue" onChange="check2(this,broadcastLimen1.value,<s:property value="threshold.broadcastLimen.highValue"/>)"  value="<s:property value="threshold.broadcastLimen.highValue"/>" style="width: 90%"></td>
	      			 	</tr>
	      			 <tr>
	      			 	<td>温度警示阈值（度）</td>
	      			 	<s:hidden name="threshold.tempLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.tempLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.tempLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.tempLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.tempLimen.isuse"></s:hidden>
	      			 	<td><input type="text" id="tempLimen1" name="threshold.tempLimen.value" onChange="check1(this,tempLimen2.value,<s:property value="threshold.tempLimen.value"/>)" value="<s:property value="threshold.tempLimen.value"/>" style="width: 90%"></td>
	      			 	<td><input type="text" id="tempLimen2" name="threshold.tempLimen.highValue" onChange="check2(this,tempLimen1.value,<s:property value="threshold.tempLimen.highValue"/>)"  value="<s:property value="threshold.tempLimen.highValue"/>" style="width: 90%"></td>
	      			 </tr>
	      			 <tr>
	      			 	<td>Arp警示阈值（个）</td>
	      			 	<s:hidden name="threshold.arpLimen.code"></s:hidden>
	      			 	<s:hidden name="threshold.arpLimen.param"></s:hidden>
	      			 	<s:hidden name="threshold.arpLimen.note"></s:hidden>
	      			 	<s:hidden name="threshold.arpLimen.type"></s:hidden>
	      			 	<s:hidden name="threshold.arpLimen.isuse"></s:hidden>
	      			 	<td><input type="text" id="arpLimen1" name="threshold.arpLimen.value" onChange="check1(this,arpLimen2.value,<s:property value="threshold.arpLimen.value"/>)" value="<s:property value="threshold.arpLimen.value"/>" style="width: 90%"></td>
	      			 	<td><input type="text" id="arpLimen2" name="threshold.arpLimen.highValue" onChange="check2(this,arpLimen1.value,<s:property value="threshold.arpLimen.highValue"/>)"  value="<s:property value="threshold.arpLimen.highValue"/>" style="width: 90%"></td>
	      			 </tr>
	      		</table>

      			</dd>
			
      			
      		
		</dl>
		 <dl class="settings_options">
      		<dt>服务器阈值</dt>
      		<dd id="">
      			 <table class="STYLE4">
      			  	<tr><td></td><td width=150>一般</td><td width=150>严重</td></tr>
      			 	<tr>
      			 		<td>TCP连接数</td>
      			 		<s:hidden name="threshold.tcpConn.code"></s:hidden>
	      			 	<s:hidden name="threshold.tcpConn.param"></s:hidden>
	      			 	<s:hidden name="threshold.tcpConn.note"></s:hidden>
	      			 	<s:hidden name="threshold.tcpConn.type"></s:hidden>
	      			 	<s:hidden name="threshold.tcpConn.isuse"></s:hidden>
      			 		<td width=150><input type="text" id="tcpConn1" name="threshold.tcpConn.value" onChange="check1(this,tcpConn2.value,<s:property value="threshold.tcpConn.value"/>)" value="<s:property value="threshold.tcpConn.value"/>" style="width: 90%"></td>
      			 		<td width=150><input type="text" id="tcpConn2" name="threshold.tcpConn.highValue" onChange="check2(this,tcpConn1.value,<s:property value="threshold.tcpConn.highValue"/>)"  value="<s:property value="threshold.tcpConn.highValue"/>" style="width: 90%"></td>
      			 	</tr>
      			 	<tr>
      			 		<td>进程数</td>
      			 		<s:hidden name="threshold.procNum.code"></s:hidden>
	      			 	<s:hidden name="threshold.procNum.param"></s:hidden>
	      			 	<s:hidden name="threshold.procNum.note"></s:hidden>
	      			 	<s:hidden name="threshold.procNum.type"></s:hidden>
	      			 	<s:hidden name="threshold.procNum.isuse"></s:hidden>
      			 		<td><input type="text" id="procNum1" name="threshold.procNum.value" onChange="check1(this,procNum2.value,<s:property value="threshold.procNum.value"/>)" value="<s:property value="threshold.procNum.value"/>" style="width: 90%"></td>
      			 		<td><input type="text" id="procNum2" name="threshold.procNum.highValue" onChange="check2(this,procNum1.value,<s:property value="threshold.procNum.highValue"/>)"  value="<s:property value="threshold.procNum.highValue"/>" style="width: 90%"></td>
      			 	</tr>
      			 	<tr>
      			 		<td>内存使用率（%）</td>
      			 		<s:hidden name="threshold.memRate.code"></s:hidden>
	      			 	<s:hidden name="threshold.memRate.param"></s:hidden>
	      			 	<s:hidden name="threshold.memRate.note"></s:hidden>
	      			 	<s:hidden name="threshold.memRate.type"></s:hidden>
	      			 	<s:hidden name="threshold.memRate.isuse"></s:hidden>
      			 		<td><input type="text" id="memRate1" name="threshold.memRate.value" onChange="check1(this,memRate2.value,<s:property value="threshold.memRate.value"/>)" value="<s:property value="threshold.memRate.value"/>" style="width: 90%"></td>
      			 		<td><input type="text" id="memRate2" name="threshold.memRate.highValue" onChange="check2(this,memRate1.value,<s:property value="threshold.memRate.highValue"/>)"  value="<s:property value="threshold.memRate.highValue"/>" style="width: 90%"></td>
      			 	</tr>
      			 	<tr>
      			 		<td>虚存使用率（%）</td>
      			 		<s:hidden name="threshold.vmemRate.code"></s:hidden>
	      			 	<s:hidden name="threshold.vmemRate.param"></s:hidden>
	      			 	<s:hidden name="threshold.vmemRate.note"></s:hidden>
	      			 	<s:hidden name="threshold.vmemRate.type"></s:hidden>
	      			 	<s:hidden name="threshold.vmemRate.isuse"></s:hidden>
      			 		<td><input type="text" id="vmemRate1" name="threshold.vmemRate.value" onChange="check1(this,vmemRate2.value,<s:property value="threshold.vmemRate.value"/>)" value="<s:property value="threshold.vmemRate.value"/>" style="width: 90%"></td>
      			 		<td><input type="text" id="vmemRate2" name="threshold.vmemRate.highValue" onChange="check2(this,vmemRate1.value,<s:property value="threshold.vmemRate.highValue"/>)"  value="<s:property value="threshold.vmemRate.highValue"/>" style="width: 90%"></td>
      			 	</tr>
      			 	<tr>
      			 		<td>磁盘使用率（%）</td>
      			 		<s:hidden name="threshold.diskRate.code"></s:hidden>
	      			 	<s:hidden name="threshold.diskRate.param"></s:hidden>
	      			 	<s:hidden name="threshold.diskRate.note"></s:hidden>
	      			 	<s:hidden name="threshold.diskRate.type"></s:hidden>
	      			 	<s:hidden name="threshold.diskRate.isuse"></s:hidden>
      			 		<td><input type="text" id="diskRate1" name="threshold.diskRate.value" onChange="check1(this,diskRate2.value,<s:property value="threshold.diskRate.value"/>)" value="<s:property value="threshold.diskRate.value"/>" style="width: 90%"></td>
      			 		<td><input type="text" id="diskRate2" name="threshold.diskRate.highValue" onChange="check2(this,diskRate1.value,<s:property value="threshold.diskRate.highValue"/>)"  value="<s:property value="threshold.diskRate.highValue"/>" style="width: 90%"></td>
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
