<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
 <head>
	<s:head />
		<title>导入其他分区记录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <base target="_self"><!-- 不弹出新窗口 -->
		 
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorSubnetService.js'></script>
		<script type='text/javascript' src='<%=path%>/js/pub.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		<script language="JavaScript">
			function init(){
				var subnetId = document.getElementById("subnetId").value;
				
				MonitorSubnetService.findExcept(subnetId,callbackLinkport);
			}
			function callbackLinkport(data){  //显示出设备类型
			   	dwr.util.removeAllOptions("monitorSubnet");
			   	dwr.util.addOptions("monitorSubnet",{'':'--请选择--'});
			   	dwr.util.addOptions("monitorSubnet",data,"id","name");
			}
			
			
			
			function update(){
				document.form.action="selectLinkport.action";
		        document.form.submit();
				 
			}
			 
	
			
		
		
			//关闭
			function cancel(){
				window.close();
			}
			function confirm(){
				var ids="";
		    	var el = document.getElementsByName('linkportCheckbox');
		        for(var i=0; i<el.length; i++) {
		        	if(el[i].checked==true&&el[i].id!=""){
		  				ids=ids+el[i].id+","
		  			}
	        	}
        	 
		        if(ids.length==0){
					alert("请选择互连端口！");
					return false;
				}else{
					ids=ids.substring(0,ids.length-1);
					
					window.close();
					returnValue= ids;
				}
				
			}
			
			//全选/全不选
			function selectAll(tempControl){
		     	var theBox=tempControl;
		      	xState=theBox.checked;
		      	var el = document.getElementsByName("linkportCheckbox");
		      	var len = el.length;
		        for(var i=0; i<len; i++) {
		        	
		        	 el[i].checked = xState;
		        }
		    }
		</script>

  </head>
  
  <body  onLoad="init();">
  <table cellspacing=0 cellpadding=0 border=0 width="100%">   
	<tr>
	<td width="1%" height="22" align="center" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;">
	<img src="<%=path%>/images/modpass.gif" width="16" height="16"></td>
	<td width="98%" background="<%=path%>/images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">导入其他分区记录</td>
	</tr>
	</table>  

  <s:form name="form" method="post" theme="simple"> 
  <s:hidden id="subnetId" name="subnetId"/>
  <table>


    		<tr>
		      	 <td>
		      	  
		      	  
		      		<tr>
			      	<td colspan=7>
				      	分区名称<select id="monitorSubnet" name="monitorSubnet.id"   onchange="update()"></select>
			      			
			      	</td>
		      		</tr>
		      		<tr>
		      			<td>
			      		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
					      <tr bgcolor="#b5d6e6">
					      	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
					       	<td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连IP</td>        
					        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连接口</td>
					        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上连接口描述</td>        
					        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连IP</td>
					        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连接口</td>
					        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">下连接口描述</td>
					      </tr>
					      <%int i=1; %> 
					        <s:iterator id="linkport" value="linkportList" var="linkport">
						       <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						       	  <td height="26" align="center" > <input type="checkbox" name="linkportCheckbox" id="<s:property value='id'/>"/> <%=i++ %></td>
						       	  <td align="left" ><s:property value="ip"/>  </td>
						          <td align="center" ><s:property value="interface_"/></td>
						          <td align="left" ><s:property value="description"/></td>
						          <td align="left" ><s:property value="downlinkIp"/>   </td>
						          <td align="center" ><s:property value="downlinkInterface"/></td>
						          <td align="left" ><s:property value="downlinkDesc"/></td>
						       </tr>
						      </s:iterator>
				     	</table>
		      		</td>
		      		</tr>
	      	 <tr height="26" bgcolor="#FFFFFF">
				<td colspan="2" align="center" width="100%" class="alllisttitle">
				<input type="submit" value="提交" class="mmBtn" onClick="confirm()">
				<input type="button" value="取消" class="mmBtn" onClick="cancel()">
				</td>
			 </tr>
  </table>
  </s:form>
  </body>
</html>
