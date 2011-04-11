<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>严重度与紧急度管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" >			
	function del(){   
		var msg="确认删除吗？";   
		if (confirm(msg) == true)  {   
        	return true;   
   		}   
    	else {   
        	return false;   
   		}   
	} 
		</script>
	</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
      <tr>
        <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">严重度与紧急度管理:</td>
      </tr>
    </table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-right: 3px; padding-bottom: 3px">
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="30" align="right"><table width="60" border="0" cellpadding="0" cellspacing="0" background="../images/addnew002.gif">
          <tags:button code="add" menu="79">
            <tr onClick="window.location='addInput.action'" style="cursor:hand;">
              <td><img src="../images/addnew001.gif"></td>
              <td nowrap>新建工程</td>
              <td align="right"><img src="../images/addnew003.gif"></td>
            </tr>
            </tags:button>
          </table>
      </tr>
    </table>
		<table width="99%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
            <td width="15%" height="22" align="center" background="../images/main20100521_58.gif" class="alllisttitle">序号</td>
            <td width="23%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">程度</td>
            <td width="24%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">值</td>
			<td width="21%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">大类</td>
            <td width="8%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
            <td width="9%" align="center" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
          </tr>
          <s:iterator value="severityTypList" var="severityTypes" status="sta">
		  <tr><td height="22" colspan="6" bgcolor="#EBF4F5"><s:if test="#sta.count==1">
		  
								  										<b>事件/变更/问题：影响度</b>
			                                                             </s:if>
								  									<s:elseif test="#sta.count==2">
								  										<b>事件/变更/问题：紧急度</b>
								  									</s:elseif>
								  									<s:elseif test="#sta.count==3">
								  										<b>服务请求/事件：严重程度</b>
								  									</s:elseif>
								  									<s:elseif test="#sta.count==4">
								  										<b>项目/任务：严重程度</b>
								  									</s:elseif>
								  									<s:elseif test="#sta.count==5">
								  										<b>用户/客户：优先等级</b>
								  									</s:elseif>
								  									<s:elseif test="#sta.count==6">
								  										<b>配置：优先等级</b>
								  									</s:elseif>
		    </td>
		  </tr>
            <s:iterator value="severityTypes" var="severityTyp">
              <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
                <td height="20" align="center">${severityTyp.id}</td>
                <td height="20" align="center">${severityTyp.severityType}</td>
                <td align="center">${severityTyp.severityValue}</td>
				<td align="center">${severityTyp.category}</td>
                <td align="center">
                 <tags:button code="update" menu="79">
                <img src="../images/edt.gif">
                <a href="updateInput.action?severityTypId=${severityTyp.id}">修改</a>
                </tags:button>
                </td>
                <td align="center">
                 <tags:button code="delete" menu="79">
                <img src="../images/del.gif">
                <a href="delete.action?severityTypId=${severityTyp.id}"  onclick="javascript:return del()">删除</a>
               </tags:button>
                </td>
              </tr>
            </s:iterator>
          </s:iterator>
    </table>
</div>
</body>
</html>
