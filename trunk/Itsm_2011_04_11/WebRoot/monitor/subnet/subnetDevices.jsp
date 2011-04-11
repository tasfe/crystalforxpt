<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
	<s:head />
		<title>编辑分区</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="all" type="text/css" href="<%=path%>/js/impromptu/examples.css">	
<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-1.js"></script>
<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-impromptu.js"></script>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceDAO.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetTypeDAO.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetDAO.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/DwrService.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<style>

</style>
<script language="JavaScript">
//弹出删除设备后的提示信息
var message='<%=(String)request.getAttribute("message") %>';
if(null!=message && message !="null")
 	alert(message);
 	 	

function init(){
}

 
//拓扑图生成向导第二步：选择中心三层交换机 下拉框选项的初始化，从已选设备中挑选设备类型为'路由'和'交换+路由'的设备
function selectCenterDeviceIP(){
	
	var el = document.getElementsByName('deviceCheckbox');
	if(el.length==0){
		$.prompt('已选设备为空列表,请选择设备！');
	}else{
		var arrayObj = new Array(el.length);
		for(i=0; i <el.length; i++){
		    arrayObj[i]= el[i].id;
		}
		MonitorDeviceDAO.getCenterDeviceIP(arrayObj,callbackCenterDevices);
	}
}
//回调函数
function callbackCenterDevices(data){   
	if(data.length==0){
		$.prompt('已选设备中不存在设备类型是"路由"或者"交换+路由"的设备！');
	}
	else{
	 	var ips="";
	 	for(i=0; i <data.length; i++){
	 		ips=ips+ '<option value="'+data[i]+'">'+data[i];
	 	}
	 	openprompt(ips);//调用弹出窗口	 
	}
}

var isGoingDiscovery=true;
//拓扑图生成向导
function openprompt(ips){
				isGoingDiscovery=true;
				var temp = {
					state0: {
						html:'单击下一步将会覆盖原有记录',
						buttons: { 取消: false, 下一步: true },
						focus: 1,
						submit:function(v,m,f){ 
							if(!v)
								return true;
							else {
							var subnetId=document.getElementById("subnetId").value; 
							$.prompt.goToState('state1');//go forward
							}
							return false; 
						}
					},
					state1: {
						html:'第一步：选择算法.<br /><select name="algorithm"><option value="1">通用算法<option value="2">思科CDP<option value="3">华为NDP</select>',
						buttons: { 取消: false, 下一步: true },
						focus: 1,
						submit:function(v,m,f){ 
							if(!v)
								return true;
							else $.prompt.goToState('state2');//go forward
							return false; 
						}
					},
					state2: {
						html:'第二步：选择中心三层交换机<br /><select name="centerIP">'+ips+'</select>',
						buttons: { 上一步: -1, 退出: 0, 下一步: 1 },
						focus: 2,
						submit:function(v,m,f){ 
							if(v==0)
								$.prompt.close()
							else if(v==1){
								var subnetId=document.getElementById("subnetId").value;
								linkportDiscovery(f.algorithm,f.centerIP,subnetId);
								$.prompt.goToState('state3');
							}
							else if(v=-1)
								$.prompt.goToState('state1');//go back
							return false; 
						}
					},
					state3: {
						html:'正在发现<br/>'+'<img src="../images/progressBar.gif"/>',
						buttons: {取消: false },
						focus: 1,
						submit:function(v,m,f){ 
							$.prompt.goToState('state4');//go forward
							return false; 
						}
					},
					state4: {
						html:'警告!<br/>'+'确定要取消吗?',
						buttons: {是: true ,否: false },
						submit:function(v,m,f){ 
							if(v) {
								$.prompt.close();
								isGoingDiscovery=false;//取消操作，不进行跳转了
								cancelDiscovery();
							}
							else 
								$.prompt.goToState('state3');
							 return false; 
						}
					}
				}
				
				$.prompt(temp);
}
//互联端口发现
function linkportDiscovery(algorithm,centerIP,subnetId){
	MonitorSubnetService.linkportDiscovery(algorithm,centerIP,subnetId,callbackLinkportDiscovery);
			function callbackLinkportDiscovery(data){
				if(isGoingDiscovery){
					$.prompt.close();
					//var subnetId=document.getElementById("subnetId").value;
					//window.location.href='initTopology.action?subnetId='+subnetId;
					var url='../monitor/flex/Topology.html?subnetId='+subnetId;
					if(centerIP!=null&&centerIP.toString().length>0)
						url=url+"&center="+centerIP;
					else
						url=url+"&center=root";
					
					var name="ff";
					if(name!=null&&name.toString().length>0)
						url=url+"&name="+name;
					 
					window.location.href=url+"&mode=edit";  
				}
				//alert(data);
			}
}

function cancelDiscovery(){
	MonitorSubnetService.cencelDiscovery();
}
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
	function deleteDevice(){
		
		var ids="";
	    	var el = document.getElementsByName('deviceCheckbox');
	        for(var i=0; i<el.length; i++) {
	        	if(el[i].checked==true&&el[i].id!=""){
	  				ids=ids+el[i].id+","
	  			}
        	}
        	 
	         if(ids.length==0){
				alert("请选择需要删除的项！");
				return false;
			}
			if (window.confirm("你确认删除吗？与设备相连的互连接口记录也将被删除。")){
		 	
				ids=ids.substring(0,ids.length-1);
				document.subnet.action="subnetDeleteDevice.action?deviceIds="+ids;
		        document.subnet.submit();
			}
			else{
				return false;
			} 
	}
	
	function addDevices(){
		var subnetId = document.getElementById("subnetId").value;
		var obj=document.getElementsByName("deviceCheckbox");
		var ids="";
		 for(var i=0; i<obj.length; i++) {
	        	if(obj[i].id!=""){
	  				ids=ids+obj[i].id+","
	  			}
        	}
		 if(ids.length>0)
			 	ids=ids.substring(0,ids.length-1);
		var result=window.showModalDialog("selectDevices.action?subnetId="+subnetId,obj,"dialogWidth=1024px;dialogHeight=600px;dialogLeft=300px;dialogTop=300px;scroll:yes;center:Yes;help:no;resizable:yes;status:yes;minimize:yes;maximize:yes;");
	 	if(result==undefined){
               return false;
        }  
	 	 
		document.subnet.action="subnetDeleteDevice.action?method=add&deviceIds="+result;
		document.subnet.submit();
		
	}
	
	function searchDevices(){
		var subnetId = document.getElementById("subnetId").value;
		document.form.action = "/monitorDevice/searchInput.action?subnetId="+subnetId;
		document.form.submit();
	}
	
	  //删除设备提示
    function del(){   
		var msg="确认删除该设备吗？删除后需要重新添加或搜索。同时，如果其他分区也含有该设备，执行删除操作后，其他分区将不含有此设备！";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
	
	
	//打开设备端口
	function openDeviceInterface(deviceId,ip,name){
		var url='../monitor/flex/DeviceInterfacePanel.html?deviceId='+deviceId;
		if(ip!=null&&ip.toString().length>0)
			url=url+"&ip="+ip;
		if(name!=null&&name.toString().length>0)
			url=url+"&name="+name;
		window.open (url+"&mode=show");  
	}
	
	function keyInterface(){
		document.form.action="keyInterfaceList.action";
		document.form.submit();
	}
	
	function newDevice(){
		var subnetId = document.getElementById("subnetId").value;
		document.form.action = "/monitorDevice/addInput.action?subnetId="+subnetId;
		document.form.submit();
	}
	
	 //批量彻底删除设备
	function delItems(){
	    	var ids="";
	    	var el = document.getElementsByTagName('input');
	      	var len = el.length;
	        for(var i=0; i<len; i++) {
	        	if(el[i].type=="checkbox"&&el[i].checked==true&&el[i].id!=""){
	  				ids=ids+el[i].id+","
	  			}
        	}
	        if(ids.length==0){
				alert("请选择需要删除的项！");
				return false;
			}
			if (window.confirm("你确认删除吗？")){
				ids=ids.substring(0,ids.length-1);
				var subnetId = document.getElementById("subnetId").value;
				document.form.action="/monitorDevice/deviceDelete.action?deviceId="+ids+"&subnetId="+subnetId;
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
	}

</script>
</head>

<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">

<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">网络设备<s:fielderror/></td>
  </tr>
</table>

<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">

      	 <table width="99%">
	      	 <tr><td>
	      	 	<s:form name="subnet" method="post" theme="simple" action="/monitorSubnet/updateInput.action"> 
		      	 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			      <tr bgcolor="#b5d6e6">
			      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
			        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
			        <td align="center" nowrap bgcolor="#deebf1">名称</td>
			        <td align="center" nowrap bgcolor="#deebf1">类型</td>          
			        <td align="center" nowrap bgcolor="#deebf1">描述</td>
			        <td align="center" bgcolor="#deebf1">入网计算机/快照数</td>
			        <td align="center" nowrap bgcolor="#deebf1">操作</td>
			        <td align="center" nowrap bgcolor="#deebf1">常用链接</td>
			      </tr>
			      <%int i=1; %>
			      <s:iterator value="selectedDeviceList" var="device">
			       <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			       	<td height="26" width="50"><input type="checkbox" name="deviceCheckbox" id="<s:property value='id'/>"/><%=i++ %></td>
			       	<td height="22" align="center"><a href="/monitorDevice/detail.action?deviceId=<s:property value='id'/>" target="_blank"><s:property value="ip"/></a></td>
			       	<td align="center"><s:property value="name"/></td>
			       	<td align="center"><s:property value="monitorDeviceType.name"/></td>
			       	<td align="center">
			       		<span title="<s:property value="description"/>">
					    <s:if test="description.length()>30">          
						    <s:property value="description.substring(0,30)"/>… 
						</s:if>                     
						<s:else>       
						    <s:property value="description"/>   
						</s:else>
						</span>
			       	</td>
			       	<td align="center">
				       	<a href="/monitorComputer/computerQuery.action?upDeviceIp=<s:property value="ip"/>">
						<font color="red"><s:property value="archiveUserNum"/></font>
						/<s:property value="snapUserNum"/>
						</a>
			       	</td>
			       	<td align="center">
			       	<a href="/monitorDevice/detail.action?deviceId=<s:property value='id'/>" ><img src="<%=path%>/img/monitor/detail.png" alt="查看详情"></a>
			       	<a href="/monitorDevice/updateInput.action?deviceId=<s:property value='id'/>&subnetId=<s:property value='subnetId'/>" ><img src="<%=path%>/images/edt.gif" alt="修改"></a>
			       	<a href="/monitorDevice/deviceDelete.action?deviceId=<s:property value='id'/>&subnetId=<s:property value='subnetId'/>"  onclick="javascript:return del()"><img src="<%=path%>/images/del.gif" alt="删除"></a>
			       </td>
			       <td align="center">
			       <s:if test="monitorDeviceType.code==20"></s:if>
					<s:else>
						<a href="#" onclick="openDeviceInterface(<s:property value="id"/>,'<s:property value="ip"/>','<s:property value="name"/>')">设备端口</a>
						<s:if test="monitorDeviceType.code==6">
				  			<a href="/monitorDevice/akcpMonitor.action?ip=<s:property value='ip'/>">温湿度测控</a>
				  			<a href="/monitorDevice/cpuHistory.action?deviceId=<s:property value='id'/>">温湿度历史</a>
				  		</s:if>
				  		<s:else>
				  		<a href="/monitorDevice/cpuMonitor.action?ip=<s:property value='ip'/>">CPU测控</a>
				  		<a href="/monitorDevice/cpuHistory.action?deviceId=<s:property value='id'/>">CPU历史</a>
				  		</s:else>
				  		<a href="/monitorDevice/deviceArpList.action?deviceId=<s:property value='id'/>">ARP表</a>
				  		<a href="/monitorDevice/deviceMacList.action?deviceId=<s:property value='id'/>">MAC转发表</a>
					</s:else>
			       </td>
			       </tr>
			      </s:iterator>
			     </table>
			     <s:hidden id="subnetId" name="subnetId"/>
			     <div style="color:red">
				    <s:fielderror />
				</div>
				</s:form>
	      	 </td></tr>
	      	  <tr><td colspan=7  align="center">
	      	  	<s:form name="form" method="post" theme="simple">
	      	 	<input type="button" class="mmBtn" value="从分区内移除" onclick="deleteDevice()">
	      	 	<input type="button" class="mmBtn" value="选择设备到分区" onclick="addDevices()">
	      	 	<input type="button" class="mmBtn" value="搜索新设备" onclick="searchDevices()">
	      	 	<input type="button" class="mmBtn" value="添加新设备" onclick="newDevice()">
	      	 	<input type="button" class="mmBtn" value="彻底删除设备" onclick="delItems()">
	      	 	<input type="button" class="mmBtn" value="重新生成拓扑" onClick="selectCenterDeviceIP()" >  
	      	 	</s:form>
	      	 </td></tr>
      	 </table> 
</div>


</body>
</html>