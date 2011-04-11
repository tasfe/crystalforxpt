<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>知识库管理首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
function init() { //取出类别
	ServiceCategoryDAO.findAll(callbackserviceCategory);
}

      function callbackserviceCategory(data){  //显示出分类
 			dwr.util.removeAllOptions("service_category");
   			dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   			dwr.util.addOptions("service_category",data,"id","itemZh");  
    		var a = "<s:property value="serviceRequest.serviceCategory.itemZh" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("service_category",a);  
   			} 
		}	
		
		
		function tbshowandoff(a,b,c) {//显示和隐藏表格；
			document.getElementById(a).style.display= "block"; 
          	document.getElementById(b).style.display= "none"; 
          	document.getElementById(c).style.display= "none";
  		}
		function tbshowandoff(a,b) {//显示和隐藏表格；
			document.getElementById(a).style.display= "block"; 
          	document.getElementById(b).style.display= "none"; 

  		}

	function loadTop() {
		var topframe = parent.frames.topFrame;
		topframe.location = "top.action";
	}
	
	function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	}  
	</script>
</head>
<body onLoad="init()" style="overflow:hidden;">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  		<tr>
    		<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    		<td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">知识库管理（Incident Solution）</td>
  		</tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding=3 cellspacing="1">
    	<tr>
      		<td align=right style="padding-right: 1px" height="32"><span class="mmBtn" style="cursor:hand;">
      			<a onClick="tbshowandoff('table1','table2');">&nbsp;&nbsp;按工程师模式查看</a></span>&nbsp; <span class="mmBtn" style="cursor:hand;">
      			<a onClick="tbshowandoff('table2','table1');">&nbsp;按用户模式查看&nbsp;</a></span>&nbsp;
    	</tr>
  	</table>
  	
    <table id="table1" width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
	<tr>
  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass.gif" width="16" height="16">&nbsp;按工程师模式查看&nbsp;</td>
    </tr>

		
        <tr bgcolor="#FFFFFF">
          <td height="22" colspan="8" align="center" class="alllisttitle"><table width="99%" border="0" cellspacing="1">
		      <s:form action="query" method='post' theme="simple" name="form">
		      <s:hidden id="page" name="page"></s:hidden>
              <s:hidden id="pageSize" name="pageSize"></s:hidden>
              <tr>
                <td width="13%" height="22"> 知识库搜索 </td>
              </tr>
               <tr>
		          <td height="8"> 知识库编号： </td>
		          <td width="20%"><s:textfield id="knowledgeBase.indexcode" name="knowledgeBase.indexcode"cssStyle="width: 60%"></s:textfield></td>
	              <td width="8%"> 知识库标题： </td>
		          <td width="20%"><s:textfield id="knowledgeBase.title" name="knowledgeBase.title"cssStyle="width: 60%"></s:textfield></td>
				</tr>
				<tr>
	              <td width="8%"> 所包含内容： </td>
		           <td width="40%"><s:textfield id="knowledgeBase.symptom" name="knowledgeBase.symptom"cssStyle="width: 60%"></s:textfield></td>
		          <td width="8%"> 所属类别： </td>
		          <td width="40%">
		            <select id="service_category" name="knowledgeBase.categoryId.id" style="width: 60%"></select></td>
	            </tr>
              <tr>
                <td colspan="8" align="right"><input type="submit"  class="mmBtn"
									value=" 搜索 "/>	<input type="button" value=" 返回 "
							onClick="window.location='list.action'" class="mmBtn"></td>
              </tr>
            </s:form>
          </table>
          <table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr><td height="30" align="right">
								<table width="60" border="0" cellpadding="0" cellspacing="0"
									background="../images/addnew002.gif">
									<tr onClick="window.location='addInput.action'"
										style="cursor: hand;">
										<td><img src="../images/addnew001.gif"></td>
										<td nowrap>添加知识库</td>
										<td align="right"><img src="../images/addnew003.gif"></td>
									</tr>
								</table></tr></table>
          </td>
        </tr>
        <tr bgcolor="#FFFFFF">
          <td height="22" align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 编号 </td>
          <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 标题 </td>
          <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">内容</td>
		  <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">所属类别</td>
          <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 查看 </td>
          <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 修改 </td>
		  <td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle"> 删除 </td>
        </tr>
      		<s:iterator value="pageBean.list" var="knowledgeBase" status="st">
            <tr bgcolor="#FFFFFF" onMouseOver=this.bgColor='#e3f0f7';onMouseOut=this.bgColor='#FFFFFF';>
            <td height="20" align="center" background="../images/main20100521_58.gif" class="alllisttitle"><s:property value ="indexcode" /></td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"><s:property value ="title" /></td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"><s:property value ="symptom" /></td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"><s:property value ="categoryId.itemZh" /></td>
            <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"><a href="show.action?knowledgeBase.id=<s:property value="id"/>">查看</a></td>
		    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"><a href="updateInput.action?knowledgeBaseId=<s:property value="id"/>">修改</a></td>
		    <td align="center" background="../images/main20100521_58.gif" class="alllisttitle"onclick="del()"><a href="delete.action?knowledgeBaseId=<s:property value="id"/>">删除</a></td>
          </tr>
          </s:iterator>
	</table>
	
  <table id="table2" style="display :none"; width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
	<tr>
  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass.gif" width="16" height="16">&nbsp;按用户模式查看&nbsp;</td>
    </tr>
    
        <s:form action="query2" method='post' theme="simple" name="form">
		      <s:hidden id="page" name="page"></s:hidden>
              <s:hidden id="pageSize" name="pageSize"></s:hidden>
              <tr>
                <td width="13%" height="22"> 知识库搜索 </td>
              </tr>
               <tr>
		          <td height="8"> 知识库编号： </td>
		          <td width="20%"><s:textfield id="knowledgeBase.indexcode" name="knowledgeBase.indexcode"cssStyle="width: 60%"></s:textfield></td>
	              <td width="8%"> 知识库标题： </td>
		          <td width="20%"><s:textfield id="knowledgeBase.title" name="knowledgeBase.title"cssStyle="width: 60%"></s:textfield></td>
				</tr>
				<tr>
	              <td width="8%"> 所包含内容： </td>
		           <td width="40%"><s:textfield id="knowledgeBase.symptom" name="knowledgeBase.symptom"cssStyle="width: 60%"></s:textfield></td>
		          <td width="8%"> 所属类别： </td>
		          <td width="40%">
		            <select id="service_category" name="knowledgeBase.categoryId.id" style="width: 60%"></select></td>
	            </tr>
              <tr>
                <td colspan="8" align="right"><input type="submit"  class="mmBtn"
									value=" 搜索 "/>	<input type="button" value=" 返回 "
							onClick="window.location='list.action'" class="mmBtn"></td>
              </tr>
            </s:form>
          </table>
          
         <jsp:include page="/common/page.jsp" />
     </body>
</html>