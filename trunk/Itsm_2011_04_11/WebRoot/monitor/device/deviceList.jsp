<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String subnetId = (String)request.getAttribute("subnetId");
	if(subnetId==null)subnetId="";
	String ip = (String)request.getAttribute("ip");
	if(ip==null)ip="";
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/monitorDevice/deviceList.action";
	
%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
    <title>网络设备</title>
</head>
<script type="text/javascript">
	function show(subnet,ip){
	url="<%=path%>/history/cpuList.action?ip="+ip+"&subnet="+subnet;
	document.location = url;
	}
	function search(){
		var ip = document.getElementById("ip").value;
		document.location = "<%=path%>/monitorDevice/list.action?subnetId=<%=subnetId%>&ip="+ip;
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
    
    //删除设备
    function del(){   
		var msg="确认删除该设备吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
    
     //批量删除
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
				
				document.form.action="deviceDelete.action?deviceId="+ids;
		        document.form.submit();
			}
			else{
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
	//查询设备
	function searchDevice(){
		document.form.action="queryInput.action";
		document.form.submit();
	}
</script>
<body>
<s:form name="form" theme="simple" method="post" action="/monitorDevice/deviceList.action">
<s:hidden id="page" name="page" value="1"></s:hidden>
<s:hidden id="pageSize" name="pageBean.pageSize"></s:hidden>

<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">设备管理</td>
  </tr>
</table>
<!--<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">-->
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
      		<tr style="cursor:hand;">
		       		<td><img src="../images/addnew001.gif"></td>
		     		<td  onClick=window.location.href("addInput.action?page=<s:property value='page' />&pageBean.pageSize=<s:property value='pageBean.pageSize' />") nowrap>添加设备</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		       		<td> &nbsp;&nbsp; </td>
		       		<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick=window.location.href("searchInput.action") nowrap>搜索新设备</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		        	<td> &nbsp;&nbsp; </td>
		        	<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="delItems()" nowrap>删除选中设备</td>
		      		<td align="right"><img src="../images/addnew003.gif"></td>
		      		
		      		<td> &nbsp;&nbsp; </td>
		        	<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="keyInterface()" nowrap>查看全部关键接口</td>
		      		<td align="right"><img src="../images/addnew003.gif"></td>
		      		
		      		<td> &nbsp;&nbsp; </td>
		        	<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="searchDevice()" nowrap>查询设备</td>
		      		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">设备列表</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">

  <tr bgcolor="#b5d6e6">
  	<td height="26" width="50" align="center" style="text-align: center" bgcolor="#deebf1"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
    <td height="26" align="center" bgcolor="#deebf1">IP</td>
    <td align="center" bgcolor="#deebf1">描述</td>
    <td align="center" bgcolor="#deebf1">名称</td>
    <td align="center" bgcolor="#deebf1">类型</td>
    <td align="center" bgcolor="#deebf1">入网计算机（其中快照数）个</td>
    <td align="center" bgcolor="#deebf1">操作</td>
  </tr>
  <s:iterator value="pageBean.list" status="index">
  <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
  	<td height="26" width="50"><input type="checkbox" id="<s:property value='id'/>"/></td>
	<td height="26" align="center"><a href="detail.action?deviceId=<s:property value='id'/>"><s:property value="ip"/></a></td>
	<td align="center">
		<span title="<s:property value="description"/>">
	    <s:if test="description.length()>70">          
		    <s:property value="description.substring(0,70)"/>… 
		</s:if>                     
		<s:else>       
		    <s:property value="description"/>   
		</s:else>
		</span>
	</td>
	<td align="center"><s:property value="name"/></td>
	<td align="center"><s:property value="monitorDeviceType.name"/></td>
	<td align="center">
	 
		<a href="/monitorComputer/computerQuery.action?upDeviceIp=<s:property value="ip"/>">
		<font color="red"><s:property value="archiveUserNum"/></font>
		/<s:property value="snapUserNum"/>
		</a>
		
	</td>
	<td align="center">
		<img src="<%=path%>/images/edt.gif"><a href="detail.action?deviceId=<s:property value='id'/>">查看详情</a>
		<img src="<%=path%>/images/edt.gif"><a href="updateInput.action?deviceId=<s:property value="id" />&page=<s:property value="page" />&pageBean.pageSize=<s:property value="pageBean.pageSize" />">修改</a>
		<img src="<%=path%>/images/del.gif"><a href="deviceDelete.action?deviceId=<s:property value="id"/>&page=<s:property value="page" />&pageBean.pageSize=<s:property value="pageBean.pageSize" />"  onclick="javascript:return del()">删除</a>

		<s:if test="monitorDeviceType.code==20"></s:if>
		<s:else>
		<img src="<%=path%>/images/edt.gif"><a href="#" onclick="openDeviceInterface(<s:property value="id"/>,'<s:property value="ip"/>','<s:property value="name"/>')">设备端口</a>
	  	<!-- <a href="interfaceMonitor.action?ip=<s:property value='ip'/>">接口测控</a> -->
	  		<s:if test="monitorDeviceType.code==6">
	  			<a href="akcpMonitor.action?ip=<s:property value='ip'/>">温湿度测控</a>
	  			<a href="cpuHistory.action?deviceId=<s:property value='id'/>">温湿度历史</a>
	  		</s:if>
	  		<s:else>
	  		<a href="cpuMonitor.action?ip=<s:property value='ip'/>">CPU测控</a>
	  		<a href="cpuHistory.action?deviceId=<s:property value='id'/>">CPU历史</a>
	  		</s:else>
	  		<a href="deviceArpList.action?deviceId=<s:property value='id'/>">ARP表</a>
	  		<a href="deviceMacList.action?deviceId=<s:property value='id'/>">MAC转发表</a>
	  	</s:else>
	  
	</td>
  </tr>
  </s:iterator>
</table>
</s:form>
<jsp:include page="/common/page.jsp"/>
</body>
</html>
