<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	
%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
    <title>关键接口列表</title>
</head>
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
    
    //删除设备
    function del(){   
		var msg="确认删除记录吗？";   
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
				
				document.form.action="keyInterfaceDelete.action?id="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
	}
	function enableItems(){
			var ids="";
	    	var el = document.getElementsByTagName('input');
	      	var len = el.length;
	        for(var i=0; i<len; i++) {
	        	if(el[i].type=="checkbox"&&el[i].checked==true&&el[i].id!=""){
	  				ids=ids+el[i].id+","
	  			}
        	}
	        if(ids.length==0){
				alert("请选择需要启动监测的接口！");
				return false;
			}
			if (window.confirm("你确认启动监测吗？")){
				ids=ids.substring(0,ids.length-1);
				
				document.form.action="enableMonitoring.action?id="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	}
	function desableItems(){
			var ids="";
	    	var el = document.getElementsByTagName('input');
	      	var len = el.length;
	        for(var i=0; i<len; i++) {
	        	if(el[i].type=="checkbox"&&el[i].checked==true&&el[i].id!=""){
	  				ids=ids+el[i].id+","
	  			}
        	}
	        if(ids.length==0){
				alert("请选择需要停止监测的接口！");
				return false;
			}
			if (window.confirm("你确认停止监测吗？")){
				ids=ids.substring(0,ids.length-1);
				
				document.form.action="desableMonitoring.action?id="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	}
	
</script>
<body>
<s:form name="form" theme="simple" method="post" action="/monitorDevice/keyInterfaceList.action">
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
		     		<td  onClick="enableItems()" nowrap>启动监测选中接口</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		       		<td> &nbsp;&nbsp; </td>
		       		<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="desableItems()" nowrap>停止监测选中接口</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		        	<td> &nbsp;&nbsp; </td>
		        	<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="delItems()" nowrap>删除选中接口</td>
		      		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">关键接口监测表</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">

  <tr bgcolor="#b5d6e6">
  	<td height="26" width="50" align="center" style="text-align: center" bgcolor="#deebf1"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
    <td height="26" align="center" bgcolor="#deebf1">设备IP</td>
    <td align="center" bgcolor="#deebf1">接口号</td>
    <td align="center" bgcolor="#deebf1">接口描述</td>
    <td align="center" bgcolor="#deebf1">备注</td>
    <td align="center" bgcolor="#deebf1">启动状态</td>
    <td align="center" bgcolor="#deebf1">操作</td>
  </tr>
  <s:iterator value="#request.list" status="index">
  <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
  	<td height="26" width="50"><input type="checkbox" id="<s:property value='id'/>"/></td>
	<td height="26" align="center"><a href="detail.action?deviceId=<s:property value='monitorDevice.id'/>"><s:property value="monitorDevice.ip"/></a></td>
	<td align="center"><s:property value="interfaceNum"/></td>
	<td align="center"><s:property value="interfaceDesc"/></td>
	<td align="center"><s:property value="note"/></td>
	<td align="center">
	<s:if test="isMonitor==1">
		<img src="<%=path%>/img/monitor/status5.gif" alt="监测中">
	</s:if>
	<s:else>
		<img src="<%=path%>/img/monitor/status1.gif" alt="已停止监测">
	</s:else>
	</td>
	<td align="center">
			<img src="<%=path%>/images/del.gif"><a href="keyInterfaceDelete.action?id=<s:property value="id"/>"  onclick="javascript:return del()">删除</a>
		<s:if test="isMonitor==1">
			<img src="<%=path%>/images/edt.gif"><a href="desableMonitoring.action?id=<s:property value="id"/>"  >停止监测</a>
		</s:if>
		<s:else>
			<img src="<%=path%>/images/edt.gif"><a href="enableMonitoring.action?id=<s:property value="id"/>"  >启动监测</a>
		</s:else>
		
	</td>
  </tr>
  </s:iterator>
</table>
</s:form>
</body>
</html>
