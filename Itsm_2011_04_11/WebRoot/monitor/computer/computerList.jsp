<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<title>计算机表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
<script type="text/javascript" src="<%=path%>/js/Main.js" ></script>	
<script type="text/javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<script type="text/javascript">
function init() {
	MonitorSubnetService.findAll(callbackSubnet);
	MonitorSubnetService.getSelectedDeviceBySubnet(-1,callbackInitDeviceList)
}
function callbackSubnet(data) {
		dwr.util.removeAllOptions("subnet");
		dwr.util.addOptions("subnet", [ {
			id : '-1',
			name : '--请选择--'
		} ], "id", "name");
		dwr.util.addOptions("subnet", data, "id", "name");
		var subnetId ="<s:property value="subnetId" />";
		dwr.util.setValue("subnet",subnetId);  
		 
}

function change(){
	var subnetId = document.getElementById("subnet").value;
	MonitorSubnetService.getSelectedDeviceBySubnet(subnetId,callbackInitDeviceList)
}
function callbackInitDeviceList(data){
	dwr.util.removeAllOptions("upDevice");
	dwr.util.addOptions("upDevice", [ {
			ip : 'all',
			ip : '--全部--'
	} ], "ip", "ip");
	dwr.util.addOptions("upDevice", data, "ip", "ip");
}


function doSearch(){
		var ip = document.getElementById("monitorComputer.ip").value;
		if(ip!=""){
			if(!isIPv4(ip)){
				alert("IP地址格式不正确！");
				return;
			}
		}
		var mac = document.getElementById("monitorComputer.mac").value;
		if(mac!=""){
			if(!CheckMac(mac)){
				alert("mac地址格式不正确！Mac地址以空格,-,:分开"); 
			}
		}
		form1.submit();
}
 //删除入网计算机 
function del(){   
		var msg="确认删除该计算机吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
}  

 //批量删除
function doDeleteItems(){
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
				
				document.form.action="computerDelete.action?computerId="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
}
	 


</script>
</head>

<body onLoad="init()">

<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">计算机表搜索</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
	<s:form name="form1" action="computerQuery" method="post" theme="simple" namespace="/monitorComputer">
	<tr>
		<td width=2% align="center" nowrap bgcolor="#deebf1">IP:&nbsp;</td>
		<td width="13%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input type="text" id="monitorComputer.ip" name="monitorComputer.ip" style="width: 100%">
		</td>
		<td width=2% align="center" nowrap bgcolor="#deebf1">MAC:&nbsp;</td>
		<td width="13%" bgcolor="#FFFFFF" style="padding-right: 10px">
			<input  type="text" id="monitorComputer.mac" name="monitorComputer.mac" style="width:100%;">
		</td>
		
		<td align="right" width="8%" nowrap bgcolor="#deebf1">选择分区：</td>
		<td width="12%" bgcolor="#ffffff" style="padding-right: 10px">
			<select id="subnet" name="subnetId" style="width: 100%; height:18px;" onchange="change()"></select>
		</td>
		<td align="right" width="10%" nowrap bgcolor="#deebf1">选择上连设备</td>
		<td width="15%" bgcolor="#ffffff" style="padding-right: 10px">
			<select id="upDevice" name="upDeviceIp" style="width: 100%; height:18px;"></select>
		</td>
		<td align="right" width="10%" nowrap bgcolor="#deebf1">状态</td>
		<td width="5%" bgcolor="#ffffff" style="padding-right: 10px">
			<s:select id="status" list="#{-1:'全部',0:'在线',1:'下线'}" name="status" value="status" theme="simple" ></s:select>
			
		</td>
		<td align="right" width="10%" nowrap bgcolor="#deebf1">最后发现时间</td>
			<td width="15%" bgcolor="#ffffff" style="padding-right: 10px">
			<input id="discoveryTime" name="monitorComputer.discoveryTime" class="Wdate" type="text" language="javascript" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
		</td>
		<td width="2%" bgcolor="#deebf1" >
			<input type="button" onClick="doSearch()" style="height:20px" class="mmBtn" value="搜索" />
		</td>
	</tr>
	</s:form>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
      		<tr style="cursor:hand;">
        		<td><img src="../images/addnew001.gif"></td>
        		<td  onClick="doDeleteItems()" nowrap>删除选中记录</td>
        		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="80%" style="color:#333333; font-weight:bold;">计算机列表 
       		 <img src="<%=path%>/img/monitor/dest_all_conn.png" title="总数"><s:property value="pageBean.allRow"/> 
        	<img src="<%=path%>/img/monitor/dest_pc_conn.png" title="在线"><s:property value="sanpshotCount"/>
        	<img src="<%=path%>/img/monitor/dest_pc_forbid.png" title="下线"><s:property value="sanpshotOfflineCount"/>
        	</td>
        
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr bgcolor="#b5d6e6">
      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
        <td align="center" nowrap bgcolor="#deebf1">MAC</td>
        <td align="center" nowrap bgcolor="#deebf1">上连设备</td>          
        <td align="center" nowrap bgcolor="#deebf1">接口描述</td>
        <td align="center" nowrap bgcolor="#deebf1">计算机名</td>
        <td align="center" nowrap bgcolor="#deebf1">状态</td>
        <td align="center" nowrap bgcolor="#deebf1">最后发现时间</td>
        <td align="center" nowrap bgcolor="#deebf1">操作</td>
      </tr>
      <%int i=1; %>
      <s:iterator value="pageBean.list" var="computer">
        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
        	<td height="26" width="50"><input type="checkbox" id="<s:property value='id'/>"/>&nbsp;<%=i++%></td>
			<td height="19" align="center"><s:property value="ip"/></td>
	        <td align="center"><s:property value="mac"/></td>
	        <td align="center">
	       
	        <a href="<%=path%>/monitorDevice/detail.action?deviceId=<s:property value='monitorDevice.id'/>"><s:property value="monitorDevice.ip"/></a>
	        </td>
	        <td align="center"><s:property value="interfaceDescription"/></td>
	        <td align="center"><s:property value="computerName"/></td> 
	        <td align="center">
	        	<s:if test="snapshot==true">
	        		<img src="<%=path%>/img/monitor/dest_pc_conn.png" title="在线">
	        	</s:if>
	        	<s:else>
	        		<img src="<%=path%>/img/monitor/dest_pc_forbid.png" title="下线">
	        	</s:else>
	        
	        </td>            
	        <td align="center"><s:date name="discoveryTime"/></td>
	        <td align="center">
	          	<img src="<%=path%>/images/edt.gif">
	          	<a href="updateInput.action?computerId=<s:property value="id" />">修改</a>
				<img src="<%=path%>/images/del.gif">
				<a href="computerDelete.action?computerId=<s:property value="id"/>"  onclick="javascript:return del()">删除</a>
			</td>
	    </tr>
      </s:iterator>
</table>


<s:form name="form" method="post" action="%{actionURI}.action" theme="simple">
	<s:hidden id="subnetId" name="subnetId"></s:hidden>
	<s:hidden id="upDeviceIp" name="upDeviceIp"></s:hidden>
	<s:hidden id="page" name="page" value="1"></s:hidden>
	<s:hidden id="pageSize" name="pageBean.pageSize"></s:hidden>
	<s:hidden name="monitorComputer.discoveryTime"></s:hidden>
	<s:hidden name="monitorComputer.ip"></s:hidden>
	<s:hidden name="monitorComputer.mac"></s:hidden>
</s:form>
<jsp:include page="/common/page.jsp"/> 
</body>

</html>