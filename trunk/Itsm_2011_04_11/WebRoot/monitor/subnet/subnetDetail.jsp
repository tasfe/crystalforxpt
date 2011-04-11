<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
	<s:head />
	<title>查看分区</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/common.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/commonstyles.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/monitor/tabMenu.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
	<script type="text/javascript" src="<%=path%>/js/monitor/ceruleanBlue.js"></script>
	<script type="text/javascript" src="<%=path%>/js/monitor/fxloader.js"></script>
	<script type="text/javascript" src="<%=path%>/monitor/flex/swfobject.js"></script>
	<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetTypeDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetDAO.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/interface/DwrService.js'></script>
	<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
	<style type="text/css">
		<!--
		.STYLE4 {color: #03515d;
			font-size: 12px;
		}
		.STYLE1 {color:#022e44;
			font-size: 12px;
			font-weight:bold;
		}
		.redBdr{ border: 1px solid #C8443F; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		.greenBdr{ border: 1px solid #8DC73F; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		.yellowBdr{border: 1px solid #F6F905; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		
		-->
	</style>
	<script type="text/javascript">
	var boolName=true;
	var oldSubnetName="";

	function initPage(){
		oldSubnetName=document.getElementById("subnetName").value;
	}
	function callbackSubnetType(data){  //显示出分区类型
	   	dwr.util.removeAllOptions("subnetType");
	   	dwr.util.addOptions("subnetType",data,"code","name");  
	   	var a = "<s:property value="monitorSubnet.monitorSubnetType.code" />";
		if (typeof(a) != "undefined") { 
			  		dwr.util.setValue("subnetType",a);  
		}
	}
	function beginUpdateSubnet(){
		var d = "<a href='javascript:cancelUpdateSubnet()' title='取消' ><img src='<%=path%>/img/monitor/ceruleanBlue/cancelIcon.gif' alt='取消' class='alighnRight' border='0'></a>";
		document.getElementById('snapBox').innerHTML =d;
		showallEdits();
	}
	function cancelUpdateSubnet(){
		var d = "<a href='javascript:beginUpdateSubnet()' title='取消' ><img src='<%=path%>/img/monitor/ceruleanBlue/editIcon.gif' alt='修改' class='alighnRight' border='0'></a>";
		document.getElementById('snapBox').innerHTML =d;
		cancelallEdits();
	}
	/* FUNCTIONS FOR INLINE EDITING STARTS HERE */
	function showallEdits(){
	
		writeVal("divSubnetNameShow","divSubnetNameEdit");
		writeVal("divParentSubnetShow","divParentSubnetEdit");
		writeVal("divSubnetTypeShow","divSubnetTypeEdit");
		writeVal("divSubnetScanShow","divSubnetScanEdit");
		writeVal("divSubnetCenterShow","divSubnetCenterEdit");
		writeVal("divSubnetEdgeShow","divSubnetEdgeEdit");
		writeVal("divSubnetNodeShow","divSubnetNodeEdit");
		writeVal("divSubnetNoteShow","divSubnetNoteEdit");
		MonitorSubnetTypeDAO.findAll(callbackSubnetType);
		document.getElementById('saveChanges').style.display ='block';
		
	}
	function writeVal(idd,idd2){
		var obj = document.getElementById(idd).innerHTML;
		document.getElementById(idd).innerHTML = document.getElementById(idd2).innerHTML;
		document.getElementById(idd2).innerHTML = obj;
	}
	function cancelallEdits(){
		writeVal("divSubnetNameShow","divSubnetNameEdit");
		writeVal("divParentSubnetShow","divParentSubnetEdit");
		writeVal("divSubnetTypeShow","divSubnetTypeEdit");
		writeVal("divSubnetScanShow","divSubnetScanEdit");
		writeVal("divSubnetCenterShow","divSubnetCenterEdit");
		writeVal("divSubnetEdgeShow","divSubnetEdgeEdit");
		writeVal("divSubnetNodeShow","divSubnetNodeEdit");
		writeVal("divSubnetNoteShow","divSubnetNoteEdit");
		document.getElementById('saveChanges').style.display ='none';
		
	}
	function saveSubnet(){
		var subnetName=document.getElementById("subnetName");
		if(subnetName.value==""){
			alert("分区名称必须填写 ！");
			subnetName.focus();
			return;
		}
		document.form.target="mainFrame";
		document.form.action="subnetUpdate.action"
		document.form.submit();
	}
	
	//显示数据的百分比，有颜色区别
	function showBdrData(data,s,opposite){
		var div,divClass,divColor;
		if(opposite){
			if(data>=s[0]){
				divClass="redBdr";
				divColor="rgb(200, 68, 63)";
			} else if(data>=s[1]){
				divClass="yellowBdr";
				divColor="rgb(246, 249, 5)";
			} else {
				divClass="greenBdr";
				divColor="rgb(141, 199, 63)";
			}
		} else {
			if(data<=s[0]){
				divClass="redBdr";
				divColor="rgb(200, 68, 63)";
			} else if(data<=s[1]){
				divClass="yellowBdr";
				divColor="rgb(246, 249, 5)";
			} else {
				divClass="greenBdr";
				divColor="rgb(141, 199, 63)";
			}
			
		}
		
		div="<div class='"+divClass+"'> <div style='width:"+data+"%; background-color: "+divColor+"; height: 8px; font-size: 7px;'></div>";
	
		return 	div;		 	
	}
	function bytesConvert(bytes) {
		bytes = bytes*8;
        var ext = new Array('B', 'k', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y');
        var unitCount = 0;
        for(; bytes > 1000; unitCount++) 
        	bytes /= 1000;
        
        return roundNumber(bytes,1) + "" + ext[unitCount];
	}
	function roundNumber(num, dec) {
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}
	
	//打开拓扑图
	function openTopology(subnetId){
		MonitorSubnetService.openTopology(subnetId,callbackGetSubnet);
	}
	
	function callbackGetSubnet(data){
	if(data==undefined || data == null) return;
	var url='../monitor/flex/Topology.html?subnetId='+data.id;
	if(data.centerIp!=null&&data.centerIp.toString().length>0)
			url=url+"&center="+data.centerIp;
		else
			url=url+"&center=root";
		if(data.name!=null&&data.name.toString().length>0)
			url=url+"&name="+data.name;	
		window.open (url+"&mode=show");  
	}
	function getFlexUrl(){
		var subnetId = document.getElementById("subnetId");
		var subnetName=document.getElementById("subnetName");
		var subnetCenterIp = document.getElementById("subnetCenterIp");
		var url='/monitor/flex/Topology.html?subnetId='+subnetId;
		if(subnetCenterIp!=null&&subnetCenterIp.toString().length>0){
			url=url+"&center="+subnetCenterIp;
		}else{
			url=url+"&center=root";
		}
		if(subnetName!=null&&subnetName.toString().length>0){
			url=url+"&name="+subnetName;
		}
		url=url+"&mode=show";
		alert(url);
		return url();
	}
	//查看互联端口
	function linkportList(subnetId){
		document.form.action="/monitorSubnet/linkportList.action?subnetId="+subnetId;
		document.form.submit();
		
	}
	</script>
</head>
<body>
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="<%=path%>/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					查看分区
				</td>
			</tr>
	</table>
	<s:form action="%{actionURI}.action" method="post" theme="simple" name="form">
	<div id="dashboard2" style="width:100%"> 
		<table width="100%" align="center"  border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="tabBtmLine">	
					<span class="themeFontColor" style="float: left;"> </span>
					<div id="snapShotHeaderDiv" class="themeFontColor" style="float: left;"><s:property value="monitorDevice.name"/>
					</div>
					<div style="clear: both;"></div>
				</td>
				<td class="tabBtmLine" width="105" align="right"> 
					<s:if test="monitorSubnet.monitorSubnetType.name=='区域'"></s:if>
					<s:else>
					<table class="dropdownnTxt" style="width: 112px;" onclick="showthemall('MenuActions_1')" onmouseover="clearhideDropDiv()" onmouseout="delayhideDropDiv(500,'MenuActions_1');" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
							<td width="85%" align="center" nowrap="nowrap">
							<a href="javascript:openTopology(<s:property value='monitorSubnet.id'/>)" > 
						                       	 打开拓扑图
						     </a>
							</td>
							</tr>
        					</tbody>
        				</table>
					</s:else>
				</td>
				<td class="tabBtmLine" width="105" align="right"> 
					<s:if test="monitorSubnet.monitorSubnetType.name=='区域'"></s:if>
					<s:else>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody><tr>
								<td>&nbsp;</td>
    							<td align="center">
									<table class="dropdownnTxt" style="width: 112px;" onclick="showthemall('MenuActions_1')" onmouseover="clearhideDropDiv()" onmouseout="delayhideDropDiv(500,'MenuActions_1');" border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
											<td width="85%" align="center" nowrap="nowrap">
											<a href="javascript:linkportList(<s:property value='monitorSubnet.id'/>)" > 
										      	查看互连端口
										    </a>
											</td>
											</tr>
				        					</tbody>
				        			</table>
				        			</td>
						 		</tr>
						 		</tbody>
						 	</table>
					</s:else>
				</td>
				<!--  
				<td class="tabBtmLine" width="105" align="right"> 
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody><tr>
								<td>&nbsp;</td>
    							<td align="center">
    								
        							<s:if test="monitorSubnet.monitorSubnetType.name=='区域'"></s:if>
									<s:else>
									<table class="dropdownnTxt" style="width: 112px;" onclick="showthemall('MenuActions_1')" onmouseover="clearhideDropDiv()" onmouseout="delayhideDropDiv(500,'MenuActions_1');" border="0" cellpadding="0" cellspacing="0">
								         <tbody>
								          <tr>
								            <td width="85%" align="center" nowrap="nowrap">&nbsp;常用链接</td>
								            <td class="pullDownnArrow" width="20" align="right">&nbsp;</td>
								          </tr>
        								</tbody>
        							</table>
        							<div style="display: none;" id="MenuActions_1" class="ddMenuActions" onmouseover="clearhideDropDiv();" onmouseout="delayhideDropDiv(500,'MenuActions_1');">
          								<table style="width: 112px;" border="0" cellpadding="0" cellspacing="0">
            							<tbody>
            							<tr>
											<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="javascript:openTopology(<s:property value='monitorSubnet.id'/>)" > 
						                       	 打开拓扑图
						                        </a>
						                    </td>
                        				</tr>
                        				<tr>
											<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="javascript:linkportList(<s:property value='monitorSubnet.id'/>)" > 
						                       	查看互连端口
						                        </a>
						                    </td>
                        				</tr>
										</tbody>
										</table>
	
	
    								</div>
    								</s:else>
						 		</td>
						 		</tr>
						 		</tbody>
						 	</table>
				</td>
				-->
			</tr>
	</div>
	<div id="bubbles"></div>
	<div id="dashboard2">
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
			<tr>
			<td>
			<br/>
			</td>
			</tr>
			<s:if test="monitorSubnet.monitorSubnetType.name=='区域'">
				<tr>
				<td align="left" valign="top"  width="90%" >
				<!-- SUBNET PROPERTIES TABLE STARTS HERE -->
				<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 分区属性 </td>
					</tr>
					<tr><td valign="top" colspan=2>
						<div id="snapBox">
						<a href="javascript:beginUpdateSubnet()" title="修改" ><img src="<%=path%>/img/monitor/ceruleanBlue/editIcon.gif" alt="修改" class="alighnRight" border="0"></a>
						</div>
						<div id="properties" align="center">
						<table  width="100%" border="0" >
							<tr valign="top">
								<td width="30%" height="25" align="left" valign="top"  class="tab_row_fill">
									名称
								</td>
								<td width="70%" align="left"   valign="top"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetNameShow">
								 	<s:property  value="monitorSubnet.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetNameEdit">
								 	<s:hidden name="monitorSubnet.id"></s:hidden>
								 	<input type="text" id="subnetName" name="monitorSubnet.name" onChange="checkName()" value="<s:property value="monitorSubnet.name"/>" style="width: 80%">
								 </div>
									
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									上层分区
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								  <div style="float: left; display: block;" id="divParentSubnetShow">
								 	<s:property  value="monitorSubnet.parentSubnet.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divParentSubnetEdit">
								 	  <s:select id="parentSubnet" name="monitorSubnet.parentSubnet.id" list="parentSubnetList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--"></s:select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									类型
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetTypeShow">
								 	<s:property  value="monitorSubnet.monitorSubnetType.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetTypeEdit">
								  	<select id="subnetType" id="subnetType" name="monitorSubnet.monitorSubnetType.code"></select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									扫描
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetScanShow">
								 	<s:if test="monitorSubnet.scan==0">
								     	 否
								     </s:if>
								     <s:else>
								      	是
								     </s:else>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetScanEdit">
								  	<s:if test="monitorSubnet.scan==0">
								     	 <input type="checkbox" id="subnetScan" name="monitorSubnet.scan"  value="1" >
								     </s:if>
								     <s:else>
								      	<input type="checkbox" id="subnetScan" name="monitorSubnet.scan" checked value="1" >
								     </s:else>
								 </div>
								</td>
							</tr>
							
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									中心设备
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetCenterShow">
								 	<s:property   value="monitorSubnet.centerIp"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetCenterEdit">
								 	<input type="text" id="subnetCenterIp" name="monitorSubnet.centerIp"  value="<s:property value="monitorSubnet.centerIp"/>" style="width: 80%">
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%"  height="25" align="left" class="tab_row_fill">
									 拓扑连线方式
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetEdgeShow">
								 	<s:if test="monitorSubnet.edgeRenderer=='BaseEdgeRenderer'">
								     	 直线
								    </s:if>
								    <s:elseif test="monitorSubnet.edgeRenderer=='OrthogonalDirectionEdgeRenderer'">
								    	折线
								    </s:elseif>
								    <s:elseif test="monitorSubnet.edgeRenderer=='CircularEdgeRenderer'">
								    	弧线
								    </s:elseif>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetEdgeEdit">
								 	<s:select 
			      			 			id="edgeRenderer"
			      			 			list="#{'BaseEdgeRenderer':'直线','OrthogonalDirectionEdgeRenderer':'折线','CircularEdgeRenderer':'弧线'}" 
			      			 			name="monitorSubnet.edgeRenderer" 
			      			 			value="monitorSubnet.edgeRenderer" 
			      			 			theme="simple" 
			      			 			headerKey="" 
			      			 			headerValue="请选择"></s:select>
								 </div>
								 
									 
								</td>
							</tr>
							<tr>
								<td width="30%"  height="25" align="left" class="tab_row_fill">
									拓扑设备显示
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetNodeShow">
								 	<s:if test="monitorSubnet.nodeTextType==1">
								     	 IP地址
								    </s:if>
								    <s:elseif test="monitorSubnet.nodeTextType==2">
								    	设备名称
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==3">
								    	描述
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==4">
								    	响应时间
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==5">
								    	运行时间
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==6">
								    	CPU
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==7">
								    	CPU均值
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==8">
								    	CPU峰值
								    </s:elseif>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetNodeEdit">
								 	<s:select 
			      			 			id="nodeTextType"
			      			 			list="#{1:'IP地址',2:'设备名称',3:'描述',4:'响应时间',5:'运行时间',6:'CPU',7:'CPU均值',8:'CPU峰值'}" 
			      			 			name="monitorSubnet.nodeTextType" 
			      			 			value="monitorSubnet.nodeTextType" 
			      			 			theme="simple" 
			      			 			headerKey="" 
			      			 			headerValue="请选择"></s:select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25"  align="left" class="tab_row_fill">
									备注
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetNoteShow">
									<s:property  value="monitorSubnet.note"/>
								</div>
								<div style="float: left; display: none;" id="divSubnetNoteEdit">
									<input type="text" id="subnetNote" name="monitorSubnet.note"  value="<s:property value="monitorSubnet.note"/>" style="width: 80%">
								</div>
								
									
								</td>
							</tr>
							<tr><td colspan=2>
							<div id="saveChanges" style="float: right; display: none;"> 
							    <input type="button" class="mmBtn" value="保存" onclick="saveSubnet()">
							    <input type="button" class="mmBtn" value="取消" onclick="cancelUpdateSubnet()">
							</div>
							</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					
					
					
				</table>
				</td>
				</tr>
			</s:if>
			<s:else>
			<tr>
				<td align="left" valign="top"  width="39%" >
				<!-- SUBNET PROPERTIES TABLE STARTS HERE -->
				<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 分区属性 </td>
					</tr>
					<tr><td valign="top" colspan=2>
						<div id="snapBox">
						<a href="javascript:beginUpdateSubnet()" title="修改" ><img src="<%=path%>/img/monitor/ceruleanBlue/editIcon.gif" alt="修改" class="alighnRight" border="0"></a>
						</div>
						<div id="properties" align="center">
						<table  width="100%" border="0" >
							<tr valign="top">
								<td width="30%" height="25" align="left" valign="top"  class="tab_row_fill">
									名称
								</td>
								<td width="70%" align="left"   valign="top"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetNameShow">
								 	<s:property  value="monitorSubnet.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetNameEdit">
								 	<s:hidden name="monitorSubnet.id"></s:hidden>
								 	<input type="text" id="subnetName" name="monitorSubnet.name" onChange="checkName()" value="<s:property value="monitorSubnet.name"/>" style="width: 80%">
								 </div>
									
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									上层分区
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								  <div style="float: left; display: block;" id="divParentSubnetShow">
								 	<s:property  value="monitorSubnet.parentSubnet.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divParentSubnetEdit">
								 	  <s:select id="parentSubnet" name="monitorSubnet.parentSubnet.id" list="parentSubnetList" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--"></s:select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									类型
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetTypeShow">
								 	<s:property  value="monitorSubnet.monitorSubnetType.name"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetTypeEdit">
								  	<select id="subnetType" id="subnetType" name="monitorSubnet.monitorSubnetType.code"></select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									扫描
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								 <div style="float: left; display: block;" id="divSubnetScanShow">
								 	<s:if test="monitorSubnet.scan==0">
								     	 否
								     </s:if>
								     <s:else>
								      	是
								     </s:else>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetScanEdit">
								  	<s:if test="monitorSubnet.scan==0">
								     	 <input type="checkbox" id="subnetScan" name="monitorSubnet.scan"  value="1" >
								     </s:if>
								     <s:else>
								      	<input type="checkbox" id="subnetScan" name="monitorSubnet.scan" checked value="1" >
								     </s:else>
								 </div>
								</td>
							</tr>
							
							<tr>
								<td width="30%" height="25" align="left" class="tab_row_fill">
									中心设备
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetCenterShow">
								 	<s:property   value="monitorSubnet.centerIp"/>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetCenterEdit">
								 	<input type="text" id="subnetCenterIp" name="monitorSubnet.centerIp"  value="<s:property value="monitorSubnet.centerIp"/>" style="width: 80%">
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%"  height="25" align="left" class="tab_row_fill">
									 拓扑连线方式
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetEdgeShow">
								 	<s:if test="monitorSubnet.edgeRenderer=='BaseEdgeRenderer'">
								     	 直线
								    </s:if>
								    <s:elseif test="monitorSubnet.edgeRenderer=='OrthogonalDirectionEdgeRenderer'">
								    	折线
								    </s:elseif>
								    <s:elseif test="monitorSubnet.edgeRenderer=='CircularEdgeRenderer'">
								    	弧线
								    </s:elseif>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetEdgeEdit">
								 	<s:select 
			      			 			id="edgeRenderer"
			      			 			list="#{'BaseEdgeRenderer':'直线','OrthogonalDirectionEdgeRenderer':'折线','CircularEdgeRenderer':'弧线'}" 
			      			 			name="monitorSubnet.edgeRenderer" 
			      			 			value="monitorSubnet.edgeRenderer" 
			      			 			theme="simple" 
			      			 			headerKey="" 
			      			 			headerValue="请选择"></s:select>
								 </div>
								 
									 
								</td>
							</tr>
							<tr>
								<td width="30%"  height="25" align="left" class="tab_row_fill">
									拓扑设备显示
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetNodeShow">
								 	<s:if test="monitorSubnet.nodeTextType==1">
								     	 IP地址
								    </s:if>
								    <s:elseif test="monitorSubnet.nodeTextType==2">
								    	设备名称
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==3">
								    	描述
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==4">
								    	响应时间
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==5">
								    	运行时间
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==6">
								    	CPU
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==7">
								    	CPU均值
								    </s:elseif>
								    <s:elseif test="monitorSubnet.nodeTextType==8">
								    	CPU峰值
								    </s:elseif>
								 </div>
								 <div style="float: left; display: none;" id="divSubnetNodeEdit">
								 	<s:select 
			      			 			id="nodeTextType"
			      			 			list="#{1:'IP地址',2:'设备名称',3:'描述',4:'响应时间',5:'运行时间',6:'CPU',7:'CPU均值',8:'CPU峰值'}" 
			      			 			name="monitorSubnet.nodeTextType" 
			      			 			value="monitorSubnet.nodeTextType" 
			      			 			theme="simple" 
			      			 			headerKey="" 
			      			 			headerValue="请选择"></s:select>
								 </div>
								</td>
							</tr>
							<tr>
								<td width="30%" height="25"  align="left" class="tab_row_fill">
									备注
								</td>
								<td width="70%" align="left"  class="tab_row_fill">
								<div style="float: left; display: block;" id="divSubnetNoteShow">
									<s:property  value="monitorSubnet.note"/>
								</div>
								<div style="float: left; display: none;" id="divSubnetNoteEdit">
									<input type="text" id="subnetNote" name="monitorSubnet.note"  value="<s:property value="monitorSubnet.note"/>" style="width: 80%">
								</div>
								
									
								</td>
							</tr>
							<tr><td colspan=2>
							<div id="saveChanges" style="float: right; display: none;"> 
							    <input type="button" class="mmBtn" value="保存" onclick="saveSubnet()">
							    <input type="button" class="mmBtn" value="取消" onclick="cancelUpdateSubnet()">
							</div>
							</td>
							</tr>
						</table>
						</div>
						</td>
					</tr>
					
					
					
				</table>
				<td  valign="top"  width="1%">
				</td>
				<td align="right" valign="top"  width="60%">
				<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 拓扑图快照 </td>
					</tr>
					<tr><td valign="top" colspan=2>
					<iframe border=0 marginwidth=0 framespacing=0 marginheight=0 
							src="/monitor/flex/Topology.html?subnetId=<s:property value='monitorSubnet.id'/>&center=<s:property value='monitorSubnet.centerIp'/>&name=<s:property value='monitorSubnet.name'/>" 
							allowtransparency="true" 
							frameborder=0 
							scrolling=no 
							width="100%" 
							height="100%"> </iframe>
					</td>
					</tr>
					
					
					
				</table>
				</td>
				</td>
			</tr>
			<tr>
			<td colspan = 3>
			<br/>
			</td>
			</tr>
			<tr>
			<td colspan = 3>
			 <table width="100%"  border="0">
			 <tr>
			 <td width="49%">
			 	<table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 最新报警 </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<!-- 
						<a href="/monitorComputer/computerList.action" target="_blank">
							  查看更多
						</a>
						 -->
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
					        <td align="center" nowrap bgcolor="#deebf1">报警类型</td>
					        <td align="center" nowrap bgcolor="#deebf1">最新时间</td>
					        <td align="center" nowrap bgcolor="#deebf1">报警次数</td> 
					      	</tr>
					      	<s:iterator value="#request.alertList" var="alert">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="22" align="left">
								<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif" alt="重要报警"/></s:if>
	          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif" alt="普通报警"/></s:elseif>
	          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif" alt="次要报警" /></s:elseif>
	          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif" alt="重要恢复" /></s:elseif>
	          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif" alt="普通恢复"/></s:elseif>
								&nbsp;
								<s:property value="ip"/>
								</td>
						        <td align="center"><s:property value="monitorAlertSmalltype.name"/></td>
						        <td align="center"><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /></td> 
						        <td align="center"><s:property value="count"/></td>     
						        
					      	 </tr>
					      	</s:iterator>
						</table>
						
						</div>
						
						
					</td>
					</tr>
					
				</table>
			 </td>
			 <td width="1%">
			 </td>
			 <td width="50%">
			 	<table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left"  height="18" bgcolor="#bbe0ff" class="tableHeader">最近入网计算机 </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<a href="/monitorComputer/computerList.action?subnetId=<s:property value='monitorSubnet.id'/>" target="_blank">
							  查看更多
						</a>
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP</td> 
					        <td align="center" nowrap bgcolor="#deebf1">MAC</td>
					        <td align="center" nowrap bgcolor="#deebf1">计算机名</td>
					        <td align="center" nowrap bgcolor="#deebf1">上连设备</td> 
					        <td align="center" nowrap bgcolor="#deebf1">最后发现时间</td>
					      	</tr>
					      	<s:iterator value="#request.computerList" var="computer">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="22" align="center"><s:property value="ip"/></td>
						        <td align="center"><s:property value="mac"/></td>
						        <td align="center"><s:property value="computerName"/></td> 
						        <td align="center">
						        <a href="<%=path%>/monitorDevice/detail.action?deviceId=<s:property value='monitorDevice.id'/>"><s:property value="monitorDevice.ip"/></a>
						        </td>     
						        <td align="center"><s:date name="discoveryTime"/></td>
					      	 </tr>
					      	</s:iterator>
						</table>
						</div>
					</td>
					</tr>
				</table>
			 </td>
			 </tr>
			 <tr>
			 <td colspan = 3>
			 <table width="100%"  border="0">
			 <tr>
			 <td width="49%">
			 	<table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 可用 TOP N  </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<!-- 
						<a href="/monitorComputer/computerList.action" target="_blank">
							  查看更多
						</a>
						 -->
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP地址</td> 
					        <td align="center" nowrap bgcolor="#deebf1">设备名称</td>
					        <td align="center" nowrap bgcolor="#deebf1">可用率</td>
					      	</tr>
					      	<s:iterator value="#request.realtimeUseList" var="use">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="19" align="center">
								<s:property value="ip"/>
								</td>
						        <td align="center"><s:property value="name"/></td>
						        <td align="center"> <s:property value="rate"/> %
							    	<script type="text/javascript">document.write(showBdrData("<s:property value='rate'/>",new Array(50,70,100),false))</script>
							    </td> 
					      	 </tr>
					      	</s:iterator>
						</table>
						
						</div>
						
						
					</td>
					</tr>
					
				</table>
			 </td>
			 <td width="1%">
			 </td>
			 <td width="50%">
			 <table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> CPU占用率 TOP N  </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<!--  
						<a href="/monitorComputer/computerList.action" target="_blank">
							  查看更多
						</a>
						-->
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP地址</td> 
					        <td align="center" nowrap bgcolor="#deebf1">设备名称</td>
					        <td align="center" nowrap bgcolor="#deebf1">占用率</td>
					      	</tr>
					      	<s:iterator value="#request.realtimeCpuList" var="use">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="19" align="center">
								<s:property value="ip"/>
								</td>
						        <td align="center"><s:property value="name"/></td>
						        <td align="center"> <s:if test="data==-1.0" >无数据 </s:if>
								    <s:else>
									    <s:property value="data"/> %
									    <script type="text/javascript">document.write(showBdrData("<s:property value='data'/>",new Array(90,30,0),true))</script>
								    </s:else>
		     					</td> 
					      	 </tr>
					      	</s:iterator>
						</table>
						
						</div>
						
						
					</td>
					</tr>
					
				</table>
			 </td>
			 </tr>
			 <tr>
			 <td colspan = 3>
			 <table width="100%"  border="0">
			 <tr>
			 <td width="49%">
			 <table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 互连接口流量TOP N  </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<!-- 
						<a href="/monitorComputer/computerList.action" target="_blank">
							  查看更多
						</a>
						 -->
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP地址</td> 
					        <td align="center" nowrap bgcolor="#deebf1">接口描述</td>
					        <td align="center" nowrap bgcolor="#deebf1">发送</td>
					        <td align="center" nowrap bgcolor="#deebf1">接收</td>
					        <td align="center" nowrap bgcolor="#deebf1">时间</td>
					      	</tr>
					      	<s:iterator value="#request.realtimeLinkPortFlowList" var="linkport">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="22" align="center">
								<s:property value="deviceIp"/>
								</td>
						        <td align="center"><s:property value="interfaceDescription"/> </td>
						        <td align="left">
						        <script type="text/javascript">
							    document.write(bytesConvert("<s:property value='deliveryTraffic'/>"))
							    </script>/s 
							    </td>
						        <td align="left"> 
						        <script type="text/javascript">
							    document.write(bytesConvert("<s:property value='receiveTraffic'/>"))
							    </script>/s 
						        </td>
						        <td align="center"> <s:date name="gatherTime" format="HH:mm:ss"/></td>
					      	 </tr>
					      	</s:iterator>
						</table>
						
						</div>
						
						
					</td>
					</tr>
					
				</table>
			 </td>
			 <td width="1%">
			 </td>
			 <td width="50%">
			 <table width="100%" height="300"  border="0"  cellpadding="0" cellspacing="0" class="tableOne">
					<tr>
					<td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 用户接口流量TOP N  </td>
					</tr>
					<tr><td valign="top" colspan=2 align="right">
						<div id="snapBox" >
						<!--  
						<a href="/monitorComputer/computerList.action" target="_blank">
							  查看更多
						</a>
						-->
						</div>
						<div id="snapBox">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
							<tr bgcolor="#b5d6e6">
					        <td height="22" align="center" nowrap bgcolor="#deebf1" style="text-align: center">IP地址</td> 
					        <td align="center" nowrap bgcolor="#deebf1">接口描述</td>
					        <td align="center" nowrap bgcolor="#deebf1">发送</td>
					        <td align="center" nowrap bgcolor="#deebf1">接收</td>
					        <td align="center" nowrap bgcolor="#deebf1">时间</td>
					      	</tr>
					      	<s:iterator value="#request.realtimeUsePortFlowList" var="linkport">
					      	 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					      	 	
								<td height="22" align="center">
								<s:property value="deviceIp"/>
								</td>
						        <td align="center"><s:property value="interfaceDescription"/> </td>
						        <td align="left">
						        <script type="text/javascript">
							    document.write(bytesConvert("<s:property value='deliveryTraffic'/>"))
							    </script>/s 
							    </td>
						        <td align="left"> 
						        <script type="text/javascript">
							    document.write(bytesConvert("<s:property value='receiveTraffic'/>"))
							    </script>/s 
						        </td>
						        <td align="center"> <s:date name="gatherTime" format="HH:mm:ss"/></td>
					      	 </tr>
					      	</s:iterator>
						</table>
						
						</div>
						
						
					</td>
					</tr>
					
				</table>
			 </td>
			 <tr>
			 </table>
			 
			</td>
			</tr>
			</s:else>
		</table>
	</div>
	</s:form>
</body>
</html>