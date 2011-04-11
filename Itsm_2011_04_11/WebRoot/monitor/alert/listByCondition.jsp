<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<title>报警记录列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="theme/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
        var GB_ROOT_DIR = "<%=path%>/js/greybox/";
</script>
<script type="text/javascript" src="<%=path%>/js/greybox/AJS.js"></script>
<script type="text/javascript" src="<%=path%>/js/greybox/AJS_fx.js"></script>
<script type="text/javascript" src="<%=path%>/js/greybox/gb_scripts.js"></script>
<link href="<%=path%>/js/greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
    
<script type="text/javascript">
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
	
	function changepage(page){
		var type = document.getElementById("monitorAlertType").value;
		if(type!="")
			document.form.action="monitorAlert/listByCondition.action?type="+type+"&page="+page;
		else
			document.form.action="monitorAlert/listByCondition.action?page="+page;
	   
	   document.form.submit();
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
				
				document.form.action="monitorAlert/delete.action?id="+ids;
				document.form.target="mainFrame";
		        document.form.submit();
			}
			else{
				return false;
			} 
	    
	}
	function search(){
			document.form.action="monitorAlert/searchInput.action";
			document.form.target="mainFrame";
		    document.form.submit();
	}
	   
	function showDescription(title,href) {
	      return GB_show("\''"+title+"\''报警说明", href,300,400);
	      
	         
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

<body style="overflow:hidden;">
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
</table>
<s:form action="monitorAlert/listByCondition.action" method='post' theme="simple" name="form">

<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0"> 	
  <tr>
    <td height="30" align="right">
    	<input name="button" type="button" class="mmBtn" onClick="search()" value="查找"/>
      	<input name="button" type="button" class="mmBtn"   value="统计"/>
      	<input name="button" type="button" class="mmBtn" onClick="delItems()"   value="批量删除"/>
      	
    </td>
  </tr>
</table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <s:if test="monitorAlertType==null||monitorAlertType.code==null">
	      <tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">名称</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	        <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" > <input type="checkbox" id="<s:property value='id'/>"/> <%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" >
	          <a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame" onclick="javascript:return del()">删除</a>
	        </td>
	        </tr>
	      </s:iterator>
      </s:if>
      <s:elseif test="monitorAlertType.code==1">
       	<tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">进程/接口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	       <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" ><input type="checkbox" id="<s:property value='id'/>"/> <%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" ><s:property value="interface_"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" ><a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame"  onclick="javascript:return del()">删除</a></td>
	        </tr>
	      </s:iterator>
      </s:elseif>
      <s:elseif test="monitorAlertType.code==2">
      	<tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">MAC</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上联设备</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	       <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" > <input type="checkbox" id="<s:property value='id'/>"/><%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" ><s:property value="mac"/></td>
	          <td align="center" ><s:property value="uplink"/></td>
	          <td align="center" ><s:property value="description"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" ><a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame"  onclick="javascript:return del()">删除</a></td>
	        </tr>
	      </s:iterator>
      </s:elseif>
      <s:elseif test="monitorAlertType.code==3">
	      <tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	       <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" > <input type="checkbox" id="<s:property value='id'/>"/> <%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" ><s:property value="interface_"/></td>
	          <td align="left" ><s:property value="description"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" ><a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame"  onclick="javascript:return del()">删除</a></td>
	        </tr>
	      </s:iterator>
      </s:elseif>
      <s:elseif test="monitorAlertType.code==4">
      	<tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">MAC</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上联设备</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">端口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	       <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" ><input type="checkbox" id="<s:property value='id'/>"/> <%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" ><s:property value="mac"/></td>
	          <td align="center" ><s:property value="uplink"/></td>
	          <td align="center" ><s:property value="port"/></td>
	          <td align="center" ><s:property value="interface_"/></td>
	          <td align="center" ><s:property value="description"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" ><a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame"  onclick="javascript:return del()">删除</a></td>
	        </tr>
	      </s:iterator>
      </s:elseif>
      <s:elseif test="monitorAlertType.code==5">
      <tr bgcolor="#b5d6e6">
	        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif"><input type="checkbox" onclick="javascript:selectAll(this);"/>序号</td>                
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">MAC</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上联设备</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警次数</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">首次时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">最近时间</td>
	        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">操作</td>
	      </tr>
	       <%int i=1; %> 
	      <s:iterator value="pageBean.list" var="alert">
	        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <td height="19" align="center" > <input type="checkbox" id="<s:property value='id'/>"/><%=i++ %></td>
	          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
	          <td align="left" ><s:property value="old"/></td>
	          <td align="left" ><s:property value="ip"/></td>
	          <td align="center" ><s:property value="mac"/></td>
	          <td align="center" ><s:property value="uplink"/></td>
	          <td align="center" ><s:property value="description"/></td>
	          <td align="center" >
	          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
          					<s:elseif test="monitorAlertSmalltype.level==2">
          						<s:if test="%{count>limitToAddLevel}">
          							<img src="<%=path%>/img/monitor/status1.gif"/>重要报警
          						</s:if>
          						<s:else>
          							<img src="<%=path%>/img/monitor/status2.gif"/>普通报警
          						</s:else>
          					</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
	          </td>
	          <td align="center" ><s:property value="count"/></td>
	          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
	          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
	          <td align="center" ><a href="monitorAlert/delete.action?id=<s:property value="id"/>"  target="mainFrame"  onclick="javascript:return del()">删除</a></td>
	        </tr>
	      </s:iterator>
      </s:elseif>
</table>
<s:hidden id="monitorAlertType" name="monitorAlertType.code"></s:hidden>
<s:hidden id="page" name="page"></s:hidden>
<s:hidden id="pageSize" name="pageBean.pageSize"></s:hidden>
</s:form>
<jsp:include page="/common/page.jsp"/>
</div>
</body>
</html>
