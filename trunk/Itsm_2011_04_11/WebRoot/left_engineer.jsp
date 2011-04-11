<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
<%@page import="com.combanc.itsm.action.ModuleAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%ModuleAction moduleAction = new ModuleAction();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="StyleSheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="./js/dtree.js"></script>
<style>
html{
overflow: scroll;
overflow-x: hidden;
overflow-x: auto !important;
}
</style>
<script language="javascript">
function trshowandoff(aa) {//显示和隐藏表格的行；
      if (aa.style.display == "") {
          aa.style.display = "none";
      } else {
          aa.style.display = "";
      }
  }
  
function trsecshowandoff(bb,cc) {//显示和隐藏表格的行；
      if (bb.style.display == "") {
          bb.style.display = "none";
		  cc.src="images/main20100521dot03.gif"
      } else {
          bb.style.display = "";
		  cc.src="images/main20100521dot02.gif"
      }
  }
</script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/main20100521_26.gif);
}
</style>
</head>

<body style="overflow-x:hidden">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="177" valign="top" background="images/main20100521_26.gif"><table width="177" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="23" background="images/main20100521_23.jpg">&nbsp;</td>
      </tr>
      <tr>
        <td><img src="images/main20100521_26.gif" width="177" height="3" alt="" /></td>
      </tr>
      <tr>
        <td align="center"><table width="151" border="0" cellpadding="0" cellspacing="0" background="images/main20100521_43.gif">
          
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="71" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr1)">事件管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr1" style="display:''">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
                  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/engineerrequest/init.action" target="mainFrame">提交请求</a></td>
                  </tr>
				  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/engineertrace/tracelist.action" target="mainFrame">跟踪请求处理情况</a></td>
                  </tr>                  
				  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/engineerrequesthistory/requestlist.action" target="mainFrame">服务请求历史</a></td>
                  </tr>
                </table></td>
                <td>
                </td>
          </tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
         <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="71" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr2)">问题管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
           <tr id="mytr2" style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              
                  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">疑似问题</a></td>
                  </tr>
                     <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">申报新问题</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">跟踪问题处理进度</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">历史记录查询</a></td>
                  </tr>                  
                </table></td>
			</tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80"  align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr3)">变更请求管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr3" style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">提交新请求</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">跟踪变更处理进度</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">历史记录查询</a></td>
               </tr>               
             </table>
             </td>
          </tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="71"  align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr4)">资产管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr4" style="display:none">
            <td align="center">
             <table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assets/list.action" target="mainFrame">IT资产管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assetsconfig/main.action" target="mainFrame">资产属性配置</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assetstype/main.action" target="mainFrame">资产类别管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assetsstate/list.action" target="mainFrame">资产状态管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="producer/list.action" target="mainFrame">供应厂商管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assets/statistic.action" target="mainFrame">资产信息统计</a></td>
                  </tr>
                                    <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="showAssets/state.action" target="mainFrame">资产信息查看</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assets/quality.action" target="mainFrame">资产质保查询</a></td>
                  </tr>
                   <%--  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="systemmanage/importconfig/classificationlist.jsp" target="mainFrame">分类别管理</a></td>
                  </tr> --%>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="systemmanage/assets/importassets.jsp" target="mainFrame">资产批量导入</a></td>
                  </tr>
                 <%--  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="systemmanage/importconfig/infosearch.jsp" target="mainFrame">配置库信息检索</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="systemmanage/importconfig/filemanage.jsp" target="mainFrame">文档库管理</a></td>
                  </tr>--%>
                </table>
             </td>
          </tr>
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr11)">监控管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr11"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorDevice/deviceList.action"  target="mainFrame">设备管理</a></td>
               </tr>
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorSubnet/subnetList.action"  target="mainFrame">分区管理</a></td>
               </tr>
               <tr>
               <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">入网计算机</a></td>
               </tr> 
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">TopN</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报警</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报警策略</a></td>
               </tr>
                <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报表</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">管理</a></td>
               </tr>                            
             </table>
             </td>
          </tr>
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr5)">资源发布管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr5"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">自助服务管理</a></td>
               </tr>
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="knowledgebase/list.action" target="mainFrame">知识库管理</a></td>
               </tr>
               <tr>
               <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="SystemNotice/listSystemNotice.action" target="mainFrame">公告与通知管理</a></td>
               </tr>                             
             </table>
             </td>
          </tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="90" align="left" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr6)">项目管理</td>
                <td width="32" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr6"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>                                                                                                                                                                                                                                                                                                                      
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/ProjectManage/newProject.action" target="mainFrame">创建新项目</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/ProjectManage/traceProject.action" target="mainFrame">跟踪项目工作进度</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/ProjectManage/queryProject.action" target="mainFrame">历史记录查询</a></td>
               </tr>                             
             </table>
             </td>
          </tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr7)">计划任务管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr7"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="schedualedtask/add.action"  target="mainFrame">添加新任务</a></td>
               </tr>
                <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="schedualedtask/list.action"  target="mainFrame">查看全部任务</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="schedualedtask/calendar.action"  target="mainFrame">工作日历</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="schedualedtask/week.action"  target="mainFrame">按周查看任务</a></td>
               </tr>                           
             </table>
             </td>
          </tr>
          
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="90" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr8)">报表与统计管理</td>
                <td width="40" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr8"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">事件或服务统计</a></td>
               </tr>
               
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">计划任务统计</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">问题统计</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">变更请求统计</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">资产配置统计统计</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">项目统计</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">自定义统计报表</a></td>
               </tr>                         
             </table>
             </td>
          </tr>
          
           <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr9)">服务水平管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr9"  style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">服务水平协议管理</a></td>
               </tr>
               
               <tr>
                 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">连带合同管理</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">可用性协议管理</a></td>
               </tr>                                        
             </table>
             </td>
          </tr>
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
          
         <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr10)">日志管理</td>
                		<td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              		</tr>
            	</table>
            </td>
          </tr>
          <tr id="mytr10"  style="display:none">
          	<td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
          	 <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/myquery.action?workLogQurey.workLog.type=2" target="mainFrame">&nbsp;&nbsp;工作日志管理</a></td>
         	 </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/main.action" target="mainFrame">&nbsp;&nbsp;工作日志查询</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/hot.action" target="mainFrame">&nbsp;&nbsp;工作日志排行</a></td>
              </tr>
             </table>
         	 </td>
         </tr>
             <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="80" align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr12)">文档管理</td>
                		<td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              		</tr>
            	</table>
            </td>
          </tr>
          <tr id="mytr12"  style="display:none">
          	<td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
          	 <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="documentCat/main.action?type=4" target="mainFrame">文档库目录</a></td>
                
                  </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="document/main.action?pid=0" target="mainFrame">&nbsp;&nbsp;文档管理</a></td>
              </tr>
         	 </table>
         	 </td>
         </tr>   
          <tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
        </table>
       </td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
