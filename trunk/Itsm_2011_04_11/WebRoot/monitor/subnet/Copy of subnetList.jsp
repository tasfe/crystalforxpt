<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>分区管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
<link type="text/css" rel="stylesheet" href="<%=path%>/css/tabletree4j.css" />
<script type="text/javascript" src="<%=path%>/js/TableTree4J.js"></script>
<script type="text/javascript">
	function del(){   
		var msg="确认删除该分区吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
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
   
	//打开拓扑图
	function openTopology(subnetId,centerIp,name){
		MonitorSubnetService.openTopology(subnetId,callbackGetSubnet);
		
	}
	
	//查看互联端口
	function linkportList(subnetId){
		document.form.action="monitorSubnet/linkportList.action?subnetId="+subnetId;
		document.form.submit();
		
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
	var gridTree ;
	function showGridTree(){
		gridTree = new TableTree4J("gridTree","<%=path%>/");	
		gridTree.tableDesc="<table border=\"1\" class=\"GridView\" width=\"100%\" id=\"table1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse\"  bordercolordark=\"#C0C0C0\" bordercolorlight=\"#C0C0C0\" >";	
		var headerDataList=new Array("名称","类型","设备总数","互连接口总数","中心设备","扫描","启动","备注","操作");
		var widthList=new Array("20%","10%","5%","5%","10%","10%","5%","5%","30%");
		//参数: arrayHeader,id,headerWidthList,booleanOpen,classStyle,hrefTip,hrefStatusText,icon,iconOpen
		gridTree.setHeader(headerDataList,-1,widthList,true,"GridHead",""," ","","");				
		//设置列样式
		gridTree.gridHeaderColStyleArray=new Array("","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo");
		gridTree.gridDataCloStyleArray=new Array("","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo","centerClo");	
		gridTree.config.booleanInitOpenAll=true;
		gridTree.config.useIcon=false;
		
		//add data
		var dataList;
		 <%=(String)request.getAttribute("gridTreeList") %>
		gridTree.printTableTreeToElement("gridTreeDiv");	
		
	}
	//分区布局 主要是判断有没有分区内有没有孤立
	function  layout(subnetId){
		MonitorSubnetService.subnetLayout(subnetId,callbackLayout);
	}
	function callbackLayout(data){
		alert("布局完毕，请打开拓扑图查看");
	}
</script>
</head>

<body style="overflow:hidden;" onload="showGridTree()">
<s:form name="form" method="post" theme="simple">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">分区列表</td>
  </tr>
</table>


<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
      		<tr style="cursor:hand;">
      			 
        		<td><img src="../images/addnew001.gif"></td>
        		<td  onClick=window.location.href("addInput.action") nowrap>新建分区</td>
        		<td align="right"><img src="../images/addnew003.gif"></td>
      		</tr>
    	</table>
    </td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;"></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
 <tr bgcolor="#b5d6e6"><td>
  <div id="gridTreeDiv"></div>
 </td></tr>
</table>
</s:form>
</body>
</html>
