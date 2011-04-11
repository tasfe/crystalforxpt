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
<script language="JavaScript">
//弹出删除设备后的提示信息
var message='<%=(String)request.getAttribute("message") %>';
if(null!=message && message !="null")
 	alert(message);
 	 	
var boolName=true;
var oldSubnetName="";
function init(){
	oldSubnetName=document.getElementById("subnetName").value;
	MonitorSubnetTypeDAO.findAll(callbackSubnetType);
	
}

function callbackSubnetType(data){  //显示出分区类型
   	dwr.util.removeAllOptions("subnetType");
   	dwr.util.addOptions("subnetType",data,"code","name");   
}

function doSubmit(){
	var subnetName=document.getElementById("subnetName");
	if(subnetName.value==""){
		alert("分区名称必须填写 ！");
		subnetName.focus();
		return;
	}
	document.subnet.action="subnetUpdate.action"
	document.subnet.submit();
}

function checkName(){
	 if(checkSubnetName()==false){ 
		alert("分区名称已存在，请重新填写 ！");
		document.getElementById("subnetName").value=oldSubnetName;
	 	document.getElementById("subnetName").value.focus();
	} 
}
function checkSubnetName(){
	 var subnetName=document.getElementById("subnetName");
	 dwr.engine.setAsync(false);//DWR设为同步执行
	 MonitorSubnetDAO.findByName(subnetName.value,callbackSubnet); 
	 dwr.engine.setAsync(true);//恢复异步执行
	 return	boolName;
}
function callbackSubnet(data){  
    var list = eval(data); 
	if(list.length > 0) {
		boolName=false;
	}
	else
		boolName=true;
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
						 	var subnetName=document.getElementById("subnetName").value;
						 	var subnetScan=document.getElementById("subnetScan").value;
						 	var subnetStart=document.getElementById("subnetStart").value; 
						 	var s=new subnet(); 
						 	s.id=subnetId;
						 	s.name=subnetName;
						 	s.scan=subnetScan;
						 	s.start=subnetStart;
						 	
							MonitorSubnetService.updateSubnetFromDWR(s,callbackupdateSubnet);
						 	function callbackupdateSubnet(result){
								if(result){
									$.prompt.goToState('state1');//go forward
								}else{
									$.prompt('后台报错！');
								}
							}
								
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
					
					var name=document.getElementById("subnetName").value;
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
		document.subnet.action="/monitorDevice/searchInput.action?subnetId="+subnetId;
		document.subnet.submit();
		//var result=window.showModalDialog("/monitorDevice/searchInput.action?subnetId="+subnetId,"search","dialogWidth=1024px;dialogHeight=600px;dialogLeft=300px;dialogTop=300px;scroll:yes;center:Yes;help:no;resizable:yes;status:no;minimize:yes;maximize:yes;");
		
	}

</script>
</head>

<body onLoad="init()" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">

<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">编辑分区:<s:fielderror/></td>
  </tr>
</table>
<s:form name="subnet" method="post" theme="simple" action="/monitorSubnet/updateInput.action"> 
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;">
  <table width="98%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">
    <tr>
      <td height="22" colspan="10" nowrap background="<%=path%>/images/main20100521_58.gif" class="alllisttitle" style="padding-left:10px;">编辑分区</td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">分区名称: <s:hidden id="subnetId" name="monitorSubnet.id"/></td>
      <td width="29%" bgcolor="#FFFFFF"><input type="text" id="subnetName" name="monitorSubnet.name" onChange="checkName()" value="<s:property value="monitorSubnet.name"/>" style="width: 50%"></td>
      <td width="5%" nowrap bgcolor="#EBF4F5">上层分区:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      <s:select id="parentSubnet" name="monitorSubnet.parentSubnet.id" list="parentSubnetList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--"></s:select>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">类型:</td>
      <td width="15%" bgcolor="#FFFFFF" >
   
      
      <select id="subnetType" id="subnetType" name="monitorSubnet.monitorSubnetType.code"></select>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">扫描:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      <s:if test="monitorSubnet.scan==0">
     	 <input type="checkbox" id="subnetScan" name="monitorSubnet.scan"  value="1" >
      </s:if>
      <s:else>
      	<input type="checkbox" id="subnetScan" name="monitorSubnet.scan" checked value="1" >
      </s:else>
      </td>
      <td width="5%" nowrap bgcolor="#EBF4F5">拓扑启动:</td>
      <td width="15%" bgcolor="#FFFFFF" >
      <s:if test="monitorSubnet.start==0">
       <input type="checkbox" id="subnetStart" name="monitorSubnet.start"  value="1" >
      </s:if>
      <s:else>
       <input type="checkbox" id="subnetStart" name="monitorSubnet.start"  checked value="1" >
      </s:else>
     </td>
    </tr>
    
     
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">备注:</td>
      <td width="89%" colspan="9" bgcolor="#FFFFFF"><input type="text" name="monitorSubnet.note1" value="<s:property value="monitorSubnet.note1"/>" style="width: 90%"></td>
    </tr>
    <tr>
      <td width="11%" nowrap bgcolor="#EBF4F5">包含设备:</td>
      <td width="89%" colspan="9" bgcolor="#FFFFFF">
      	 <table width="99%">
	      	 <tr><td>
		      	 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			      <tr bgcolor="#b5d6e6">
			      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
			        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
			        <td align="center" nowrap bgcolor="#deebf1">名称</td>
			        <td align="center" nowrap bgcolor="#deebf1">类型</td>          
			        <td align="center" nowrap bgcolor="#deebf1">描述</td>
			      </tr>
			      <s:iterator value="selectedDeviceList" var="device">
			       <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			       	<td height="26" width="50"><input type="checkbox" name="deviceCheckbox" id="<s:property value='id'/>"/></td>
			       	<td height="22" align="center"><a href="/monitorDevice/detail.action?deviceId=<s:property value='id'/>" target="_blank"><s:property value="ip"/></a></td>
			       	<td align="center"><s:property value="name"/></td>
			       	<td align="center"><s:property value="monitorDeviceType.name"/></td>
			       	<td align="center"><s:property value="description"/></td>
			       </tr>
			      </s:iterator>
			     </table>
	      	 </td></tr>
	      	  <tr><td>
	      	 	<input type="button" class="mmBtn" value="删除" onclick="deleteDevice()">
	      	 	<input type="button" class="mmBtn" value="添加" onclick="addDevices()">
	      	 	<input type="button" class="mmBtn" value="搜索新设备" onclick="searchDevices()">
	      	 </td></tr>
	      	 
      	 </table>
      	
	     
      </td>
    </tr>
  </table>
  <table cellspacing=0 cellpadding=0 border=0 width="100%">
    <tr>
      <td width="50%" height="30" align=center nowrap style="padding-top: 6px; padding-bottom: 1px">
	      <input name="button" type="button" class="mmBtn" onClick="doSubmit()" value="保存">
	      <input name="button" type="button" class="mmBtn" onClick="selectCenterDeviceIP()" value="重新生成拓扑">
	      <input name="button" type="button" class="mmBtn" onClick="javascript:document.subnet.reset();return false;" value="重置">   
	      <input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value="返回">    
      </td>
    </tr>
  </table>
</div>
<s:hidden id="centerIp" name="monitorSubnet.centerIp"/>
</s:form>
</body>
</html>