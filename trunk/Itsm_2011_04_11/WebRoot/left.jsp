 ﻿<%@ page contentType="text/html; charset=utf-8" language="java" isELIgnored="false" %>
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
           <%-- <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr3)">事件管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>--%>
          </tr>



<%--          <tr id="mytr3">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
             
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="eventorservicestatistics/list.action" target="mainFrame">事件或服务统计</a></td>
                  </tr>
                  <!-- 
                  <tr>
                    <td height="22" align="left" class="lefttreesecond">复审及关闭事件工单</td>
                  </tr>
                   -->
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="serviceprocess/query2.action?serviceRequest.state=-1" target="mainFrame">IT经理服务进度管理</a></td>
                  </tr>
                  <!-- 
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="servicedesk/interpose/interposeshow.jsp" target="mainFrame">干预服务流程及进度</a></td>
                  </tr>
                   -->
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemdailyaudit/list.action" target="mainFrame">系统日志审计</a></td>
                  </tr>

                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="servicedesk/statistics/list.jsp" target="mainFrame">用户服务请求统计</a></td>
                  </tr>

                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/userrequest/init.action" target="mainFrame">用户提交请求</td>
                  </tr>
                  
                  <tr>
                    <td height="22" align="left" class="lefttreesecond">服务请求管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/engineerrequest/init.action" target="mainFrame">工程师提交请求</td>
                  </tr>

 <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/usertrace/tracelist.action" target="mainFrame">用户跟踪请求处理情况</a></td>
                  </tr>

				  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/engineertrace/tracelist.action" target="mainFrame">工程师跟踪请求处理情况</a></td>
                  </tr>
                  
                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/userrequesthistory/tracelist.action?state=4" target="mainFrame">用户查看历史请求</a></td>
                  </tr>
				  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/engineerrequesthistory/requestlist.action" target="mainFrame">工程师服务请求历史</a></td>
                  </tr>

                </table></td>
                <td>
                </td>
          </tr>--%>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr34)">IT资产管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
           <tr id="mytr34">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assets/list.action" target="mainFrame">IT资产管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="assets/batch.action" target="mainFrame">资产批量入库</td>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr4)">监控管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr4">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorDevice/deviceList.action"  target="mainFrame">设备管理</a></td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorSubnet/subnetList.action"  target="mainFrame">分区管理</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorComputer/computerList.action" target="mainFrame">入网计算机</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitorTopN/listAll.action" target="mainFrame">TopN</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报警</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报警策略</a></td>
               </tr>
                <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="#">报表</a></td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreefirst"  background="images/main20100521_45.gif" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="monitor/manage/settings.jsp" target="mainFrame">管理</a></td>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr1)">系统管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr1">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;基本设置</td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;<a href="location/main.action" target="mainFrame">&nbsp;地理位置管理</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="holiday/list.action" target="mainFrame">节假日管理</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="department/main.action" target="mainFrame">部门管理</a></td>
              </tr>
              
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" style="cursor:hand;" onclick="javascript:trsecshowandoff(mytr21,closeorpadding)"><img id="closeorpadding" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;日程管理</td>
              </tr>
              <tr id="mytr21" style="display:none">
                <td height="22" align="left" background="images/main20100521_61.gif"><table width="147" border="0" cellspacing="0" cellpadding="0">
                
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="schedule/add.action" target="mainFrame">预约登记</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="schedule/list.action" target="mainFrame">预约查询</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="schedule/my.action" target="mainFrame">我的日程</a></td>
                  </tr> 
                           
                    </table></td>
              </tr>
              
              
                    <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" style="cursor:hand;" onclick="javascript:trsecshowandoff(mytr24,closeorpadding)"><img id="closeorpadding" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;IP申请管理</td>
              </tr>
              <tr id="mytr24" style="display:none">
                <td height="22" align="left" background="images/main20100521_61.gif"><table width="147" border="0" cellspacing="0" cellpadding="0">
                
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="ipaddress/list.action" target="mainFrame">IP申请查询</a></td>
                  </tr>
                  <!--  
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="ipaddress/saveInput.action" target="mainFrame">新增IP申请</a></td>
                  </tr>
                  -->
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="ipaddress/user_saveInput.action" target="mainFrame">用户提交IP地址申请</a></td>
                  </tr> 
                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="ipaddress/user_list.action" target="mainFrame">用户跟踪查询</a></td>
                  </tr> 
                  <!--  
                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="ipnetcenter/list.action" target="mainFrame">网络中心人员</a></td>
                  </tr> 
                        -->   
                    </table></td>
              </tr>
              
              
              
                    <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" style="cursor:hand;" onclick="javascript:trsecshowandoff(mytr25,closeorpadding)"><img id="closeorpadding" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;域名申请管理</td>
              </tr>
              <tr id="mytr25" style="display:none">
                <td height="22" align="left" background="images/main20100521_61.gif"><table width="147" border="0" cellspacing="0" cellpadding="0">
                
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="domainregister/list.action" target="mainFrame">域名申请查询</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="domainregister/user_saveInput.action" target="mainFrame">用户提交域名申请</a></td>
                  </tr> 
                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="domainregister/user_list.action" target="mainFrame">用户跟踪查询</a></td>
                  </tr> 
                    </table></td>
              </tr>
              
              
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" style="cursor:hand;" onclick="javascript:trsecshowandoff(mytr11,closeorpadding)"><img id="closeorpadding" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;用户管理</td>
              </tr>
              <tr id="mytr11" style="display:none">
                <td height="22" align="left" background="images/main20100521_61.gif"><table width="147" border="0" cellspacing="0" cellpadding="0">
                
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="user/personaldata.action" target="mainFrame">用户个人资料配置</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="user/list.action" target="mainFrame">用户管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="role/main.action" target="mainFrame">角色权限</a></td>
                  </tr>                  
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="menu/list.action" target="mainFrame">菜单管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="role/list.action" target="mainFrame">用户角色</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="engineer/list.action" target="mainFrame">工程师分组</a></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="severityTyp/list.action" target="mainFrame">严重度与紧急度</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" style="cursor:hand;" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'" onclick="javascript:trsecshowandoff(mytr12,closeorpadding2)"><img id="closeorpadding2" src="images/main20100521dot03.gif" width="9" height="9" />&nbsp;&nbsp;类别管理</td>
              </tr>
              
              
              
              <tr id="mytr12"  style="display:none">
                <td height="22" align="left" background="images/main20100521_61.gif"><table width="147" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="serviceCategory/main.action?type=1" target="mainFrame">事件及服务类别</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="serviceCategory/main.action?type=2" target="mainFrame">计划任务类别</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="serviceCategory/main.action?type=3" target="mainFrame">变更请求类别</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="problemType/main.action" target="mainFrame">问题类别</a></td>
                  </tr>
                  <!-- 
                  <tr>
                    <td height="22" align="left" class="lefttreesecond">资产配置附加表单</td>
                  </tr>
                   -->
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="documentCat/main.action?type=4" target="mainFrame">文档库目录</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond">&nbsp;<a href="serviceCategory/main.action?type=4" target="mainFrame">项目类别</a></td>
                  </tr>

                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="serviceCategory/list.action?type=5" target="mainFrame">项目任务类别</a></td>
                  </tr>

				  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="SystemNotice/listSystemNotice.action" target="mainFrame">系统公告</a></td>
                  </tr>
                
                </table></td>
                
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr2)">其他设置</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr2">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;工作流程管理</td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="taskallocation/list.action" target="mainFrame">任务分配机制管理</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;<a href="errorType/list.action" target="mainFrame">错误原因管理</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;统计条件管理</td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="9" height="9" />&nbsp;&nbsp;触发器管理</td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" />&nbsp;&nbsp;输出模板管理</td>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr5)">资源发布</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr  id="mytr5">
         	<td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/myquery.action" target="mainFrame">&nbsp;&nbsp;工作日志管理</a></td>
              </tr>
                <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/main.action" target="mainFrame">&nbsp;&nbsp;工作日志查询</a></td>
              </tr>
                             <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="worklog/hot.action" target="mainFrame">&nbsp;&nbsp;工作日志排行</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="knowledgebase/list.action" target="mainFrame">&nbsp;&nbsp;知识库管理</a></td>
              </tr>
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="document/main.action?pid=0" target="mainFrame">&nbsp;&nbsp;文档管理</a></td>
              </tr>
        	</table></td>
      	</tr>
      	
      	    <tr>
            <td><table width="151" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29"><img src="images/main20100521_28.gif" width="29" height="23" alt="" /></td>
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr5)">通讯录管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          
            <tr  id="mytr6">
         	<td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="AddressBookManage/main.action?pid=0" target="mainFrame">&nbsp;&nbsp;公共通讯录</a></td>
              </tr><%--
               <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="AddressBookManage/myContact.action" target="mainFrame">&nbsp;&nbsp;单位通讯录</a></td>
              </tr>
               --%><tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="AddressBookManage/listPersonalContact.action" target="mainFrame">&nbsp;&nbsp;个人通讯录</a></td>
              </tr>
              <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="AddressBookManage/addressImport.action" target="mainFrame">&nbsp;&nbsp;通讯录导入</a></td>
              </tr>
            <%--  <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="ProjectManage/traceProject.action" target="mainFrame">&nbsp;&nbsp;上传头像</a></td>
              </tr>
                <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="AddressBookManage/directorySubmit.action" target="mainFrame">&nbsp;&nbsp;提交办公通讯录</a></td>
              </tr>
        		 <tr>
                <td height="22" align="left" background="images/main20100521_45.gif" class="lefttreefirst" onmouseover="this.background='images/main20100521_452.gif'" onmouseout="this.background='images/main20100521_45.gif'"><img src="images/main20100521dot01.gif" width="10" height="10" /><a href="department/main.action" target="mainFrame">&nbsp;&nbsp;组别管理</a></td>
              </tr>
        	--%></table></td>
      	</tr>
      	
      	
      	
      	
      	<tr>
            <td bgcolor="#228BBB"><img src="images/space.gif" width="1" height="1" /></td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF"><img src="images/space.gif" width="100" height="3" /></td>
          </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
