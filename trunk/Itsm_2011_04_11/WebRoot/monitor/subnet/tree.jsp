<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    <script type="text/javascript"  src="<%=path%>/js/dtree.js"></script>
	<link href="<%=path%>/css/dtree.css" rel="StyleSheet"  type="text/css"/>
	<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/common.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/commonstyles.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/tabMenu.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
	<script type="text/javascript" src="<%=path%>/js/monitor/ceruleanBlue.js"></script>
	<script type="text/javascript" src="<%=path%>/js/monitor/fxloader.js"></script>
	<script type="text/javascript">
	function init(){
	 
	}
	function addSubnet(){
		document.form1.action = "/monitorSubnet/addInput.action";
		document.form1.target = "detailFrame";
		document.form1.submit();
	}
	function isSubnet(id){
		if(id==null)
			return false;
		if(id.indexOf("_")>-1)
			return false;
		return true;
	}
	 //删除分区
	function deleteSubnet(){
		if(!isSubnet(mytree.aNodes[mytree.selectedNode].id)) {
			alert("请选择要删除的分区！");
		}else{
			var msg="确认删除该分区吗？";   
			if (confirm(msg) == true)  {  
				document.form1.action = "/monitorSubnet/subnetDelete.action?subnetId="+mytree.aNodes[mytree.selectedNode].id;
				document.form1.target = "mainFrame";
				document.form1.submit();
				 
	   		}   
	    	else {   
	        	   
	   		}   
			 
		} 
		
		
	}
	  
    
	</script>
  </head>
  
  <body onload="init()">
  <form name="form1" method="post" theme="simple">
  	<table width="100%" border="0" height="100%" align="left" cellpadding="0" cellspacing="0" class="tableOne">
		<tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader"> 分区</td>
		</tr>
		<tr><td valign="top" align="left" >
		<div id="snapBox" align="right">
		<a href="javascript:addSubnet()" title="新建分区" >新建分区</a>
		<a href="javascript:deleteSubnet()" title="删除分区" >删除分区</a>
		</div>
		 
				<script type="text/javascript">
				<!--
				projectURI = "<%=path%>";
				mytree = new dTree('mytree');
				mytree.add('0','-1','网络','','','detailFrame');
				
				<s:iterator value="monitorSubnetList" var="subnet">
				<s:if test="parentSubnet==null">
					mytree.add('${id}','0','${name}','/monitorSubnet/detail.action?subnetId=${id}','','detailFrame');
				</s:if>	
				<s:else>
					mytree.add('${id}','${parentSubnet.id}','${name}','/monitorSubnet/detail.action?subnetId=${id}','','detailFrame');
				</s:else>
				<s:if test="monitorSubnetType.name=='区域'">
				</s:if>	
				<s:else>
				mytree.add('${id}_0','${id}','网络设备','/monitorSubnet/devices.action?subnetId=${id}','','detailFrame');
				mytree.add('${id}_1','${id}','用户网段','','','detailFrame');
				mytree.add('${id}_2','${id}','入网计算机','/monitorComputer/computerList.action?subnetId=${id}','','detailFrame');
				
				</s:else>
				</s:iterator> 
				document.write(mytree);
		
				//-->
				</script>
					
				</td>
		</tr> 
	</table>
  </form>
   
		
			 
		
  </body>
</html>
