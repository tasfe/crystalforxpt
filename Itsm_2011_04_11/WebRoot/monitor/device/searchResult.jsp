<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
	<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<link rel="stylesheet" media="all" type="text/css" href="<%=path%>/js/impromptu/examples.css">	
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-1.js"></script>
	<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-impromptu.js"></script>
    <title>新发现SNMP对象列表</title>
</head>
<style type="text/css">
 
</style>
<script type="text/javascript">
	//全选/全不选
	function selectAll(tempControl){
     	var theBox=tempControl;
      	xState=theBox.checked;
      	var el = document.getElementsByTagName('input');
      	var len = el.length;
        for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox") 
        	 el[i].checked = xState;
        }
    } 
    function addAll(){
		doSubmit("all");    	
    } 
    function addSelect(){
    	var ips="";
	    var el = document.getElementsByTagName('input');
	    var len = el.length;
	    for(var i=0; i<len; i++) {
		  	if(el[i].type=="checkbox"&&el[i].checked==true&&el[i].id!=""){
		  		ips=ips+el[i].id+","
		  	}
    	}
        	 
		if(ips.length==0){
			alert("请选择需要添加的设备！");
			return false;
		}
		ips=ips.substring(0,ips.length-1);
		doSubmit(ips);
    } 
    function doSubmit(ips){
    	 
    	document.form.action="searchSave.action?newDeviceIp="+ips;
		document.form.submit();
    } 
    var deviceScanTreadFlag = false;//判断是正在发现设备还是终止发现设备
    var num =0;
    function init(){
    	deviceScanTreadFlag = false;
    	var startIp = document.getElementById("startIp").value;
    	var endIp = document.getElementById("endIp").value;
    	var readCommunity = document.getElementById("readCommunity").value;
		MonitorDeviceService.deviceScan(trim(startIp),trim(endIp),readCommunity,callbackDeviceScan);
    	openprompt();
    }
    function callbackDeviceScan(data){
    		 
			if(deviceScanTreadFlag) return;
			$.prompt.close();
			if(data.length>0){
				num =data.length;
				var strTable = "";
				for(var i =0;i<data.length;i++){
					var ip = data[i].ip;
					var name = data[i].name;
					var mac = data[i].mac;
					var readCommunity = data[i].readCommunity;
					var description = data[i].description;
					if(description.length>250){
					description = description.substring(0,250);
					}
					var monitorDeviceType = data[i].monitorDeviceType.code;
					var monitorDeviceTypeName = data[i].monitorDeviceType.name;
					strTable += "<TR>"
							+"<TD  height='26' align='center' width='50' bgcolor='#FFFFFF'>"
							//+"<INPUT type='hidden' name = 'newList' value='"+ip+","+name+","+description+","+monitorDeviceType+"' >"
							+"<input type='checkbox' id='"+ip+"'/>"
							+"</TD>"
							+"<TD  height='26' align='center' bgcolor='#FFFFFF'>"+ip
							+"<input type='hidden' value='"+ip+"' name='newMonitorDeviceList["+i+" ].ip'/>"
							+"<input type='hidden' value='"+readCommunity+"' name='newMonitorDeviceList["+i+" ].readCommunity'  />"
							+"</TD>"
							+"<TD  height='26' align='center' bgcolor='#FFFFFF'>"+mac
							+"<input type='hidden' value='"+mac+"' name='newMonitorDeviceList["+i+" ].mac'/>"
							+"</TD>"
							+"<TD  height='26' align='center' bgcolor='#FFFFFF'>"
							+"<input type='text'   style='width:90%;border-left: 0;border-right: 0;border-top: 0;border-bottom: 1px solid #000;' value='"+name+"' name='newMonitorDeviceList["+i+" ].name'/>"
							+"</TD>"
							+"<TD   height='26' align='center' bgcolor='#FFFFFF'>"
							+"<input  type='text'  style='width:90%;border-left: 0;border-right: 0;border-top: 0;border-bottom: 1px solid #000;' value='"+description+"' name='newMonitorDeviceList["+i+"].description'/>"
							+"</TD>"
							+"<TD  height='26' align='center' bgcolor='#FFFFFF'>"
							
							
							
							+"<input type='hidden' id='type"+i+"'  value='"+monitorDeviceType+"' />"
							
							+"<select id='deviceType"+i+"' name='newMonitorDeviceList["+i+" ].monitorDeviceType.code' />"
							+"</TD>"
							+"</TR>";


				}
				strTable = 
						//"<table>"
						"<table width='99%' border='0' align='center' cellpadding='0' cellspacing='1' bgcolor='#b5d6e6'>"
						+ "<tr bgcolor='#b5d6e6'>"
						+ "<td height='26' width='50' align='center' style='text-align: center' bgcolor='#deebf1'><input type='checkbox' onclick='javascript:selectAll(this);' />全选</td>"
						+ "<td height='26' align='center' bgcolor='#deebf1'>IP</td>"
						+ "<td align='center' bgcolor='#deebf1'>MAC</td>"
						+ "<td align='center' bgcolor='#deebf1'>名称</td>"
						+ "<td align='center' bgcolor='#deebf1'>描述</td>"
						+ "<td align='center' bgcolor='#deebf1'>类型</td>"
						+ " </tr>"
						+ strTable
						+ "</table>"
				 
				document.getElementById("newList").innerHTML = strTable;
				MonitorDeviceTypeDAO.findAll(callbackDeviceType);
				 
			}else{
				$.prompt("没有发现新设备，请修改IP地址或者读Community,然后重新搜索！",{ callback: mycallbackfunc });
				//history.go("-1");
			}
			
		}
     function callbackDeviceType(data){  //显示出设备类型
    	for(var i =0;i<num;i++){
    		var value = document.getElementById("type"+i).value;
 
    		dwr.util.removeAllOptions("deviceType"+i);
			dwr.util.addOptions("deviceType"+i,data,"code","name"); 
			dwr.util.setValue("deviceType"+i,value);  
			
    	}
		
	}
   
    function openprompt(){
			var temp = {
						state0: {
						html:'正在发现<br/>'+'<img src="../images/progressBar.gif"/>',
						buttons: {取消: false },
						focus: 1,
						submit:function(v,m,f){ 
							$.prompt.goToState('state1');//go forward
							return false; 
							}
						},
						state1: {
						html:'警告!<br/>'+'确定要取消吗?',
						buttons: {是: true ,否: false },
						submit:function(v,m,f){ 
							if(v) {
								$.prompt.close();
								stopDeviceScan();
							}
							else 
								$.prompt.goToState('state0');
							 return false; 
							}
						}
				}
			$.prompt(temp);
	}
    
    function stopDeviceScan(){
		deviceScanTreadFlag = true;	
		MonitorDeviceService.stopDeviceScan(callbackStopDeviceScan);
	}
    function callbackStopDeviceScan(data){
		$.prompt(data,{ callback: mycallbackfunc });
	}
    function mycallbackfunc(v,m,f){
	 	history.go("-1");
	}
    
</script>
<body onLoad="init()">
<s:form name="form" theme="simple">
<s:hidden id="page" name="page"></s:hidden>
<s:hidden id="startIp" name="startIp"></s:hidden>
<s:hidden id="endIp" name="endIp"></s:hidden>
<s:hidden id="readCommunity" name="readCommunity"></s:hidden>
<s:hidden id="subnetId" name="subnetId"></s:hidden>
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">新发现SNMP对象列表</td>
  </tr>
  <tr>
  	<td>
  	<br/>
  	</td>
  </tr>
</table>
<div id="newList">
<table width='99%' border='0' align='center' cellpadding='0' cellspacing='1' bgcolor='#b5d6e6'>
<tr bgcolor='#b5d6e6'>
<td height='26' width='50' align='center' style='text-align: center' bgcolor='#deebf1'><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
<td height='26' align='center' bgcolor='#deebf1'>IP</td>
<td align='center' bgcolor='#deebf1'>MAC</td>
<td align='center' bgcolor='#deebf1'>名称</td>
<td align='center' bgcolor='#deebf1'>描述</td>
<td align='center' bgcolor='#deebf1'>类型</td>
</tr>
</table>
</div>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" >
 <tr>
 		<td colspan="5" height="30" align="center">
		 	<table   border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
		    	<tr style="cursor:hand;">
		       		<td height="25"    nowrap="nowrap" bgcolor="#FFFFFF"  >
		       		<input  type="button" class="mmBtn" value=" 添加所有设备" onClick="addAll()">
		       		<input  type="button" class="mmBtn" value=" 添加选中设备" onClick="addSelect()">
		       		<input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value=" 返回 ">
		        	</td>	
		     	</tr>
		   	</table>
		</td>
</tr>
</table>
</s:form>
</body>
</html>
