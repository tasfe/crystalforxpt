<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<title>ARP报警白名单</title>
		<link rel="stylesheet" media="all" type="text/css" href="<%=path%>/js/impromptu/examples.css">	
		<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-1.js"></script>
		<script type="text/javascript" src="<%=path%>/js/impromptu/jquery-impromptu.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css"  >
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorArpAlertWhiteMacService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>

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
		 	
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}else {  
        	return false;   
   		}   
	}
	
	function CheckMac(strMAC) { 
		//mac地址正则表达式 
		var reg_name=/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(\s[0-9A-Fa-f]{2}){5}/ ; 
		if(!reg_name.test(strMAC)){ 
		
		return false; 
		} 
		return true; 
	}
	var mac = "";
	function checkMacExsist(strMAC) { 
		mac = strMAC;
		MonitorArpAlertWhiteMacService.findByMac(strMAC,callbackMac);
		
	}
	
	function callbackMac(data){
		if(null == data){
			mac = mac.replace(" ","%20"); 
			document.form.action="/monitorSystemSettings/addArpAlertWhileMac.action?mac="+mac;
			document.form.submit();
		}else{
			$.prompt('MAC地址重复！');
		}
		
			 
	}	

	function mycallbackform(v,m,f){
		
		 if(!v) return ;
		 if(f.mac.length=0){
			 $.prompt('请填写MAC地址！');
		 }else{
		 	if(!CheckMac(f.mac)){
		 			$.prompt('mac地址格式不正确！Mac地址以空格,-,:分开');
					addItem();
				}
		 }
		 	
		 	
		 
		 
	}
	
 	//添加mac地址
    function addItem(){
    	//var txt = '请输入mac地址:<br /><input type="text" id="mac" 	name="mac" value="" />';
	   //	$.prompt(txt,{
		//	callback: mycallbackform,
		//	buttons: { 确定: true, 取消: false }
		//});
		
		
		var statesdemo = {
		state0: {
			html:'请输入mac地址:<br /><input type="text" id="mac" 	name="mac" value="" />',
			buttons: { 确定: true, 取消: false  },
			focus: 0,
			submit:function(v,m,f){ 
				if(!v) $.prompt.close();
				else{
					
				  	if(f.mac==''){
						$.prompt.goToState('state1');
					 }else{
					 	if(!CheckMac(f.mac)){
							$.prompt.goToState('state2');
						}else{
							$.prompt.close();

							checkMacExsist(f.mac);
							
							
						}
					 }
				}
					
				return false; 
			}
		},
		state1: {
			html:'请填写MAC地址',
			buttons: { 确定: true, 取消: false  },
			focus: 0,
			submit:function(v,m,f){ 
				if(!v) $.prompt.close()
				else  
					$.prompt.goToState('state0');
				return false;
			}
		},
		state2: {
			html:'mac地址格式不正确！Mac地址以空格,-,:分开',
			buttons: { 确定: true},
			focus: 0,
			submit:function(v,m,f){ 
				   
					$.prompt.goToState('state0');
				return false;
			}
		}
		};
		
    	$.prompt(statesdemo);
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
				
				document.form.action="deleteArpAlertWhileMac.action?id="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
	}
	
	function init() {
		 
	}
	
</script>
	</head>
	<body onLoad="init()" style="overflow:hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="99%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					ARP报警白名单
				</td>
			</tr>
		</table>
		<s:form name="form" theme="simple" method="post" >
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
		  <tr>
		    <td height="30" align="right">
		    	<table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
		      		<tr style="cursor:hand;">
				       		<td><img src="../images/addnew001.gif"></td>
				     		<td  onClick=addItem() nowrap>添加</td>
				        	<td align="right"><img src="../images/addnew003.gif"></td>
	
				        	<td><img src="../images/addnew001.gif"></td>
				        	<td  onClick="delItems()" nowrap>删除选中MAC</td>
				      		<td align="right"><img src="../images/addnew003.gif"></td>
		      		</tr>
		    	</table>
		    </td>
		  </tr>
		</table>
		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
		
		<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
		
		  <tr bgcolor="#b5d6e6">
		  	<td height="26" width="50" align="center" style="text-align: center" bgcolor="#deebf1"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
		    <td align="center" bgcolor="#deebf1">MAC</td>
		    <td align="center" bgcolor="#deebf1">操作</td>
		  </tr>
		  <%int i=1; %>
		  <s:iterator value="arpAlertWhiteMacList" status="index">
		  <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
		  	<td height="26" width="50"><input type="checkbox" id="<s:property value='id'/>"/><%=i++ %></td>
			<td align="center"><s:property value="mac"/></td>
			
			<td align="center">

				<img src="<%=path%>/images/del.gif"><a href="deleteArpAlertWhileMac.action?id=<s:property value="id"/>"  onclick="javascript:return del()">删除</a>
			</td>
		  </tr>
		  </s:iterator>
		</table>
		</div>
		</s:form>
	</body>
</html>
