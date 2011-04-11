<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String subnetId = (String)request.getAttribute("subnetId");
	if(subnetId==null)subnetId="";
	String ip = (String)request.getAttribute("ip");
	if(ip==null)ip="";
	String path = request.getContextPath();
	String requestURI = request.getContextPath() + "/monitorDevice/list.action?subnetId="+subnetId+"&ip="+ip;
%>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css" />
    <title>设备接口一览表</title>
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
</script>
<body>
<s:form name="form">
<s:hidden id="page" name="page"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="2%" align="right"><img src="<%=path%>/images/navdot.gif" width="15" height="11" /></td>
    <td width="98%" height="30"><a href="#">首页</a> &gt; 网络 &gt; <a href='./list.action?subnetId=<%=subnetId%>'>网络设备列表</a></td>
  </tr>
  <tr>
    <td colspan="2" align="right"><img src="<%=path%>/images/space.gif" width="100" height="10" /></td>
  </tr>
</table>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="20%" class="detailtitle">设备接口一览表</td>
    <td width="40%" align="right" valign="bottom">
    &nbsp; IP：<input type="text" id="ip" style="width:70px;" />
    &nbsp;<img src="<%=path%>/images/search.png" onclick="search();"/>
    </td>
  </tr>
  <tr>
    <td background="<%=path%>/images/detail01.gif"><img src="<%=path%>/images/detail01.gif" width="92" height="9" /></td>
    <td colspan="3" background="<%=path%>/images/detail02.gif"><img src="<%=path%>/images/detail02.gif" width="10" height="9" /></td>
  </tr>
</table>

<table width="90%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC">
	<tr>
		 <td colspan="6" height="30" align="right">
		 	<table width="300" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">    		
		    	<tr style="cursor:hand;">
		      			 
		       		<td><img src="../images/addnew001.gif"></td>
		     		<td  onClick=window.location.href("addInput.action") nowrap>添加设备</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		       		<td> &nbsp;&nbsp; </td>
		       		<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick=window.location.href("searchInput.action") nowrap>搜索设备</td>
		        	<td align="right"><img src="../images/addnew003.gif"></td>
		        		
		        	<td> &nbsp;&nbsp; </td>
		        	<td><img src="../images/addnew001.gif"></td>
		        	<td  onClick="delItems()" nowrap>删除选中设备</td>
		      		<td align="right"><img src="../images/addnew003.gif"></td>
		     	</tr>
		   	</table>
		</td>
	</tr>

  <tr>
  	<td height="26" width="50" align="center" bgcolor="#deebf1" style="text-align: center"><input type="checkbox" onclick="javascript:selectAll(this);" />全选</td>
    <td height="26" align="center" background="<%=path%>/images/detail03.gif" bgcolor="#FFFFFF" class="detailtitle2">IP</td>
    <td align="center" background="<%=path%>/images/detail04.gif" bgcolor="#FFFFFF" class="detailtitle2">描述</td>
    <td align="center" background="<%=path%>/images/detail04.gif" bgcolor="#FFFFFF" class="detailtitle2">名称</td>
    <td align="center" background="<%=path%>/images/detail04.gif" bgcolor="#FFFFFF" class="detailtitle2">类型</td>
    <td align="center" background="<%=path%>/images/detail04.gif" bgcolor="#FFFFFF" class="detailtitle2">操作</td>
  </tr>
  <s:iterator value="pageBean.list" status="index">
  <tr>
  	<td height="26" width="50"><input type="checkbox" id="<s:property value='id'/>"/></td>
	<td height="26" align="center" bgcolor="#FFFFFF"><a href="detail.action?deviceId=<s:property value='id'/>"><s:property value="ip"/></a></td>
	<td align="center" bgcolor="#FFFFFF">
		<span title="<s:property value="description"/>">
	    <s:if test="description.length()>70">          
		    <s:property value="description.substring(0,70)"/>… 
		</s:if>                     
		<s:else>       
		    <s:property value="description"/>   
		</s:else>
		</span>
	</td>
	<td align="center" bgcolor="#FFFFFF"><s:property value="name"/></td>
	<td align="center" bgcolor="#FFFFFF"><s:property value="monitorDeviceType.name"/></td>
	<td align="center" bgcolor="#FFFFFF">
		<img src="<%=path%>/images/edt.gif"><a href="#" />查看详情</a>
		<img src="<%=path%>/images/edt.gif"><a href="updateInput.action?deviceId=<s:property value="id" />">修改</a>
		<img src="<%=path%>/images/del.gif"><a href="deviceDelete.action?deviceId=<s:property value="id"/>"  onclick="javascript:return del()">删除</a>
	  	<a href="javascript:show('<s:property value="subnet.subnetId"/>','<s:property value="ip"/>')">统计图</a>
	</td>
  </tr>
  </s:iterator>
</table>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="right">
	共<s:property value="pageBean.allRow"/> 条记录
	共<s:property value="pageBean.totalPage"/> 页
	当前第<s:property value="pageBean.currentPage"/>页 
	<s:if test="%{pageBean.currentPage == 1}">第一页 上一页</s:if>
	<s:else>
	<a href="<%=requestURI %>&page=1">第一页</a>
	<a href="<%=requestURI %>&page=<s:property value="%{pageBean.currentPage-1}"/>">上一页</a>
	</s:else>
	<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
	<a href="<%=requestURI %>&page=<s:property value="%{pageBean.currentPage+1}"/>">下一页</a>
	<a href="<%=requestURI %>&page=<s:property value="pageBean.totalPage"/>">最后一页</a>
	</s:if>
	<s:else>下一页 最后一页</s:else>
 	</td>
  </tr>
</table>

</s:form>
</body>
</html>
