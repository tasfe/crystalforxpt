<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>查看互连端口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
<script type="text/javascript">
	//弹出导入其他分区记录后的提示信息
 	var message='<%=(String)request.getAttribute("message") %>';
 	if(null!=message && message !="null")
 	 	alert(message);
	//删除互连端口
    function del(id){   
		var msg="确认删除该记录吗？";   
		if (confirm(msg) == true)  {   
        	document.form.action="deleteLinkport.action?id="+id;
			document.form.submit();
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
				
				document.form.action="deleteLinkport.action?id="+ids;
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
	}
		//导入其他分区记录
		function addLinkports(){
		var subnetId=document.getElementById("subnetId").value;
		var result=window.showModalDialog("selectLinkport.action?subnetId="+subnetId,"","dialogWidth=800px;dialogHeight=600px;dialogLeft=300px;dialogTop=300px;scroll:yes;center:Yes;help:no;resizable:no;status:no;");
	 	if(result==undefined){
               return false;
        }  
		document.form.action="addLinkports.action?linkportIds="+result;
		document.form.submit();
		
	}

	
</script>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>

<body >
<s:form name="form" method="post" theme="simple">
<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  <tr>
    <td width="1%" height="22" align="center" background="${pageContext.request.contextPath }/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
    <td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看互联端口</td>
  </tr>
</table>

<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
<tr><td><br/></td></tr>
  <tr>
	
        <td align="right">
        	<tags:button code="changeselect" menu="5">
				  <input type="button" class="mmBtn" value="批量删除" onClick="delItems()">
			</tags:button>
			<tags:button code="changeselect" menu="5">
				  <input type="button" class="mmBtn" value="导入其他分区记录" onClick="addLinkports()">
			</tags:button> 
			<!-- 
			<tags:button code="add" menu="5">
				<input type="button" class="mmBtn" value="导出到Excel" onClick="">
			</tags:button> 
			 -->  				
		</td>
  </tr>
  <tr>
    <td height="20" colspan="5" background="<%=path%>/images/main20100521_58.gif">
    <table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="<%=path%>/images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;"></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
 	<tr bgcolor="#b5d6e6">
	        <td height="26" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="<%=path%>/images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);" />序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连IP</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连接口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连接口描述</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连接口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连接口描述</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	</tr>
	<%int i=1; %> 
 	<s:iterator value="linkportList" var="linkport">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="26" align="center" > <input type="checkbox" id="<s:property value='id'/>"/> <%=i++ %></td>
	          <td align="left" ><a href="/monitorDevice/detail.action?deviceIp=<s:property value='ip'/>" target="_blank"><s:property value="ip"/></a></td>
	          <td align="center"><s:property value="interface_"/></td>
	          <td align="left" ><s:property value="description"/></td>
	          <td align="left" ><a href="/monitorDevice/detail.action?deviceIp=<s:property value='downlinkIp'/>" target="_blank"><s:property value="downlinkIp"/></a>  </td>
	          <td align="center" ><s:property value="downlinkInterface"/></td>
	          <td align="left" ><s:property value="downlinkDesc"/></td>
	          <td align="center" >
	          <img src="<%=path%>/images/del.gif"><a href="#"  onclick="del(<s:property value="id"/>)">删除</a>
	           
	          </td>
	         </tr>
	      </s:iterator>
</table>
<s:hidden id="subnetId" name="subnetId"/>
<s:hidden id="message" name="message"/>
</s:form>
</body>
</html>

