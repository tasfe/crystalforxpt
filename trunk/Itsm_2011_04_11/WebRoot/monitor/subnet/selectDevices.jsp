<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
	<s:head />
		<title>选择设备</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <base target="_self"><!-- 不弹出新窗口 -->
		 
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
		<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		<script language="JavaScript">
			function init(){
				MonitorDeviceTypeDAO.findAll(callbackDeviceType);
				var subnetId = document.getElementById("subnetId").value;
				MonitorSubnetService.findExcept(subnetId,callbackSubnet);
			}
			function callbackDeviceType(data){  //显示出设备类型
			   	dwr.util.removeAllOptions("deviceType");
			   	dwr.util.addOptions("deviceType",{'':'--请选择--'});
			   	dwr.util.addOptions("deviceType",data,"code","name");   
			}
			function callbackSubnet(data){  //显示出设备类型
			   	dwr.util.removeAllOptions("monitorSubnet");
			   	dwr.util.addOptions("monitorSubnet",{'':'--请选择--'});
			   	dwr.util.addOptions("monitorSubnet",data,"id","name");
			}
			
			function update(){
				document.form.action="selectDevices.action?";
		        document.form.submit();
				 
			}
			
			function getDevices(){
				var type="";
				var fromIpAddress ="" ,endIpAddress="";
				type=document.getElementById("deviceType").value;
				
						//检查ip
				var str =document.getElementById("fromIpAddress1").value+
							document.getElementById("fromIpAddress2").value+
							document.getElementById("fromIpAddress3").value+
						    document.getElementById("fromIpAddress4").value+
						    document.getElementById("endIpAddress1").value+
						    document.getElementById("endIpAddress2").value+
						    document.getElementById("endIpAddress3").value+
						    document.getElementById("endIpAddress4").value;	
				
					if(trimAll(str).length >0 &&checkIP()) {
						if(!checkNumber(str)){
						 	alert("请输入合适的数值。");
						 	return;
						}  	
					 	fromIpAddress = document.getElementById("fromIpAddress1").value+"."+
										document.getElementById("fromIpAddress2").value+"."+
										document.getElementById("fromIpAddress3").value+"."+
										document.getElementById("fromIpAddress4").value;
						endIpAddress = 	document.getElementById("endIpAddress1").value+"."+
								    	document.getElementById("endIpAddress2").value+"."+
						    			document.getElementById("endIpAddress3").value+"."+
						    			document.getElementById("endIpAddress4").value;	
					 }
				var param="";
				if(null!=type&&type.toString().length>0)
					param="&type="+type;
				if(null!=fromIpAddress&&fromIpAddress.length>0)	{
						param+="&fromIpAddress="+fromIpAddress;
				}
				if(null!=endIpAddress&&endIpAddress.length>0)	{
						param+="&endIpAddress="+endIpAddress;
				}
				
				document.form.action="selectDevices.action?1=1"+param;
		        document.form.submit();
				 
			}
			 
			//验证输入的是否是数值
			function checkNumber(str){
				for(i=0;i<str.length;i++){
						ch = str.charAt(i);
						if(isNaN(ch))
						 return false;	
				}
				return true;		
			}
			
			//检查IP地址是否合法
			function  checkIP(){
				var result=true;
				if(trimAll(document.getElementById("fromIpAddress1").value).length == 0   || document.getElementById("fromIpAddress1").value > 255 ||
				trimAll(document.getElementById("fromIpAddress2").value).length == 0 || document.getElementById("fromIpAddress2").value > 255 ||	
				trimAll(document.getElementById("fromIpAddress3").value).length == 0 || document.getElementById("fromIpAddress3").value > 255 ||	
				trimAll(document.getElementById("fromIpAddress4").value).length == 0 || document.getElementById("fromIpAddress4").value > 255 ||	
				trimAll(document.getElementById("endIpAddress1").value).length == 0 || document.getElementById("endIpAddress1").value > 255 ||	
				trimAll(document.getElementById("endIpAddress2").value).length == 0 || document.getElementById("endIpAddress2").value > 255 ||	
				trimAll(document.getElementById("endIpAddress3").value).length == 0 || document.getElementById("endIpAddress3").value > 255 ||	
				trimAll(document.getElementById("endIpAddress4").value).length == 0 || document.getElementById("endIpAddress4").value > 255 ){
					alert("请输入有效的IP地址");	
					result=false;
				}
				if(parseInt(document.getElementById("endIpAddress1").value) < parseInt(document.getElementById("fromIpAddress1").value)){
					alert("指定的范围无效。请重新指定。");
					result=false;
				}
				else if(parseInt(document.getElementById("endIpAddress1").value)  == parseInt(document.getElementById("fromIpAddress1").value)){
					if(parseInt(document.getElementById("endIpAddress2").value) < parseInt(document.getElementById("fromIpAddress2").value)){
						alert("指定的范围无效。请重新指定。");
						result=false;
					}
					else if(parseInt(document.getElementById("endIpAddress2").value) == parseInt(document.getElementById("fromIpAddress2").value)){
						if(parseInt(document.getElementById("endIpAddress3").value) < parseInt(document.getElementById("fromIpAddress3").value)){
							alert("指定的范围无效。请重新指定。");
							result=false;
						}
						else if(parseInt(document.getElementById("endIpAddress3").value) == parseInt(document.getElementById("fromIpAddress3").value)){
							if(parseInt(document.getElementById("endIpAddress4").value) < parseInt(document.getElementById("fromIpAddress4").value)){
								alert("指定的范围无效。请重新指定。");
								result=false;
							}
						}
					}
				}
				
				return result;
			}
			
			//ip地址 
			function doAlert(x,event,nextField){	
				if(x.id == 'fromIpAddress4'){
					document.getElementById("endIpAddress1").value=document.getElementById("fromIpAddress1").value;
					document.getElementById("endIpAddress2").value=document.getElementById("fromIpAddress2").value;
					document.getElementById("endIpAddress3").value=document.getElementById("fromIpAddress3").value;
				}
				
				if(x.value.length == x.maxLength || event.keyCode == 13 || event.keyCode == 190 || event.keyCode == 27 ){
					document.getElementById(nextField).focus();		
				}
			}
			//关闭
			function cancel(){
				window.close();
			}
			function confirm(){
				var ids="";
		    	var el = document.getElementsByName('deviceCheckbox');
		        for(var i=0; i<el.length; i++) {
		        	if(el[i].checked==true&&el[i].id!=""){
		  				ids=ids+el[i].id+","
		  			}
	        	}
        	 
		        if(ids.length==0){
					alert("请选择设备！");
					return false;
				}
				ids=ids.substring(0,ids.length-1);
				
				window.close();
				returnValue= ids;
			}
			
			//全选/全不选
			function selectAll(tempControl){
		     	var theBox=tempControl;
		      	xState=theBox.checked;
		      	var el = document.getElementsByName("deviceCheckbox");
		      	var len = el.length;
		        for(var i=0; i<len; i++) {
		        	
		        	 el[i].checked = xState;
		        }
		    }
		</script>

  </head>
  
  <body  onLoad="init();">
  <table cellspacing=0 cellpadding=0 border=0 width="100%">   
	<tr>
	<td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">
	<img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
	<td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">选择设备</td>
	</tr>
	</table>  

  <s:form name="form" method="post" theme="simple"> 
  <table>


    		<tr>
		      	 <td>
		      	 <div style="display:none" id="divSelectDevices">
		      	  
		      		<tr>
			      	<td align="center">
			      		其他分区<select id="monitorSubnet" name="monitorSubnet.id"   onchange="update()"></select>
				      	设备类型 <select id="deviceType" name="monitorDevice.monitorDeviceType.code"></select>
				      	起始IP 
				      	<input type="text" id="fromIpAddress1" name="fromIpAddress1" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'fromIpAddress2')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="fromIpAddress2" name="fromIpAddress2" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'fromIpAddress3')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="fromIpAddress3" name="fromIpAddress3" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'fromIpAddress4')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="fromIpAddress4" name="fromIpAddress3" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'fromIpAddress4')" style="width:41px" class="dwipInfo"> 
				      	结束IP 
				      	<input type="text" id="endIpAddress1" name="endIpAddress1" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'endIpAddress2')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="endIpAddress2" name="endIpAddress2" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'endIpAddress3')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="endIpAddress3" name="endIpAddress3" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'endIpAddress4')" style="width:41px" class="dwipInfo"> .
						<input type="text" id="endIpAddress4" name="endIpAddress4" maxlength="3" size="4" value="" onkeyup="doAlert(this,event,'endIpAddress4')" style="width:41px" class="dwipInfo">
				      	<input type="button" class="mmBtn" value="筛选设备" onclick="getDevices()">
			      	</td>
		      		</tr>
		      		<tr>
		      			<td>
			      		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
					      <tr bgcolor="#b5d6e6">
					      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
					        <td align="center" nowrap bgcolor="#deebf1">名称</td>
					        <td align="center" nowrap bgcolor="#deebf1">类型</td>          
					        <td align="center" nowrap bgcolor="#deebf1">描述</td>
					      </tr>
					      <%int i=1; %>
					        <s:iterator id="device" value="availableDeviceList" var="device">
						       <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						       	<td height="26" width="50"><input type="checkbox" name="deviceCheckbox" id="<s:property value='id'/>"/><%=i++%></td>
						       	<td height="22" align="center"><a href="<%=path%>/monitorDevice/detail.action?deviceId=<s:property value='id'/>" target= "_BLANK"><s:property value="ip"/></a></td>
						       	<td align="center"><s:property value="name"/></td>
						       	<td align="center"><s:property value="monitorDeviceType.name"/></td>
						       	<td align="center"><s:property value="description"/></td>
						       </tr>
						      </s:iterator>
				     	</table>
		      		</td>
		      		</tr>
	      	 <tr height="26" bgcolor="#FFFFFF">
				<td colspan="2" align="center" width="100%" class="alllisttitle">
				<input type="submit" value="提交" class="mmBtn" onClick="confirm()">
				<input type="button" value="取消" class="mmBtn" onClick="cancel()">
				</td>
			 </tr>
  </table>
  <s:hidden id="subnetId" name="subnetId"/>
  </s:form>
  </body>
</html>
