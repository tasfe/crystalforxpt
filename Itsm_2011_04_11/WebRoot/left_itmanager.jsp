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
                     <td height="22" align="left" class="lefttreesecond"><a href="serviceprocess/query2.action?serviceRequest.state=-1" target="mainFrame">IT经理服务进度管理</a></td>
                  </tr>                  
				  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="${pageContext.request.contextPath}/engineerrequesthistory/requestlist.action" target="mainFrame">服务请求历史</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemdailyaudit/list.action" target="mainFrame">系统日志审计</a></td>
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
                    <td height="22" align="left" class="lefttreesecond"><a href="#" target="mainFrame">疑似问题</td>
                  </tr>
                     <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="#" target="mainFrame">申报新问题</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="#" target="mainFrame">跟踪问题处理进度</a></td>
                  </tr>
                                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="#" target="mainFrame">历史记录查询</a></td>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr34)">IT资产管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
           <tr id="mytr34" style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
              
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assets/list.action" target="mainFrame">IT资产管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assetsconfig/main.action" target="mainFrame">资产属性配置</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assetstype/main.action" target="mainFrame">资产类别管理</td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assetsstate/list.action" target="mainFrame">资产状态管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="producer/list.action" target="mainFrame">供应厂商管理</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assets/statistic.action" target="mainFrame">资产信息统计</a></td>
                  </tr>
                                    <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="showAssets/state.action" target="mainFrame">资产信息查看</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="assets/quality.action" target="mainFrame">资产质保查询</a></td>
                  </tr>
                   <%--  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemmanage/importconfig/classificationlist.jsp" target="mainFrame">分类别管理</a></td>
                  </tr> --%>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemmanage/assets/importassets.jsp" target="mainFrame">资产批量导入</a></td>
                  </tr>
                 <%--  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemmanage/importconfig/infosearch.jsp" target="mainFrame">配置库信息检索</a></td>
                  </tr>
                  <tr>
                    <td height="22" align="left" class="lefttreesecond"><a href="systemmanage/importconfig/filemanage.jsp" target="mainFrame">文档库管理</a></td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">提交新请求</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">跟踪变更处理进度</td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">历史记录查询</a></td>
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
                <td width="71"  align="left" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr4)">配置管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          <tr id="mytr4" style="display:none">
            <td align="center"><table width="147" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="images/space.gif" width="1" height="1" /></td>
              </tr>
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">分类别管理</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">批量导入配置</td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">配置库信息检索</a></td>
               </tr> 
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">文档库管理</a></td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">自助服务管理</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">知识库管理</td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">公告与通知管理</a></td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">创建新项目</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">跟踪项目工作进度</td>
               </tr>
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">历史记录查询</a></td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">添加新任务</td>
               </tr>
               
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">工作日志</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">按周查看任务</td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">事件或服务统计</td>
               </tr>
               
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">计划任务统计</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">问题统计</td>
               </tr>  
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">变更请求统计</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">资产配置统计统计</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">项目统计</td>
               </tr>
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">自定义统计报表</td>
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
                 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">服务水平协议管理</td>
               </tr>
               
               <tr>
                 <td height="22" align="left" class="lefttreesecond"><a href="#">连带合同管理</a></td>
               </tr>  
               <tr>
               	 <td height="22" align="left" class="lefttreesecond"><a href="#"  target="mainFrame">可用性协议管理</td>
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
                <td width="71" background="images/main20100521_30.gif" class="con-white" style="cursor:hand;" onclick="javascript:trshowandoff(mytr10)">日志管理</td>
                <td width="51" align="right" background="images/main20100521_30.gif"><img src="images/main20100521_32.gif" width="9" height="23" alt="" /></td>
              </tr>
            </table></td>
          </tr>
          
									<tr id="mytr10" style="display:none">
										<td>
										<table width="147" border="0" cellspacing="0" cellpadding="0">
						
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
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

</body>
</html>
