<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>资产历史状况报表</title>
    <link href="../images/report/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="../js/FusionCharts.js"></script>
    <script>
    function page(page){
    document.form1.action="historystatistic.action?page="+page+"&startDate=<s:property value="startDate"/>&endDate=<s:property value="endDate"/>&startDate2=<s:property value="startDate2"/>&assets.department.id=<s:property value="assets.department.id"/>&assets.assetsType.id=<s:property value="assets.assetsType.id"/>&endDate2=<s:property value="endDate2"/>&assets.assetsState.id=<s:property value="assets.assetsState.id"/>";
    document.form1.submit();
    }
    </script>
</head>
<body style="overflow:hidden;">
<form action="" method="post" name="form1">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="100%" align="center"><h3>资产历史状况统计图</h3></td>
</tr>
<tr>
<td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">  
  <tr>
    <td width="50%" align="center">
    	<div id="chartdiv" align="center">故障类型统计图.如果无法正常显示，需要安装FLASH插件!</div>
		<script type="text/javascript">
		   var chart = new FusionCharts("../Charts/Column3D.swf", "ChartId", "380", "200", "0", "0");
		   chart.setDataXML("<s:property value="xmlString" escape="false" />");	   
		   chart.render("chartdiv");
		</script>
	</td>
	<td width="50%" align="center">
    	<div id="chartdivs" align="center">小故障类型统计图.如果无法正常显示，需要安装FLASH插件!</div>
		<script type="text/javascript">
		   var smalltype_chart = new FusionCharts("../Charts/Pie3D.swf", "ChartId", "380", "200", "0", "0");
		   smalltype_chart.setDataXML("<s:property value="xmlString" escape="false" />");	   
		   smalltype_chart.render("chartdivs");
		</script>
	</td>
  </tr>
  </table>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>
 <table style="font-size:12px;" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
        <tr bgcolor="#b5d6e6">
          <td height="22" align="center" nowrap style="text-align: center" background="../images/main20100521_58.gif">序号</td>
          <td align="center" nowrap background="../images/main20100521_58.gif">时间段</td>
          <td align="center" nowrap" background="../images/main20100521_58.gif">资产数量</td>
        </tr>          
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'"> 
            <td height="19" align="center">1</td>
            <td align="center"><s:property value="date1"/></td>
            <td align="center"><s:property value="number1"/></td>
          </tr>
          <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'"> 
            <td height="19" align="center">2</td>
            <td align="center"><s:property value="date2"/></td>
            <td align="center"><s:property value="number2"/></td>
          </tr>
      </table>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>
<table style="font-size:12px;" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
      <tr>
            <td height="22" align="center" nowrap style="text-align: center" background="../images/main20100521_58.gif">序号</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产编号</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产名称</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产类别</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">所属部门</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产状态</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">入库时间</td>
          </tr>
          <s:iterator value="pageBean.list" var="assetsBase"  status='st'>
           <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">          
            <td height="19" align="center"><s:property value="#st.index+1"/></td>
            <td align="center"><s:property value="codeId"/></td>
            <td align="center"><s:property value="name"/></td>
            <td align="center"><s:property value="assetsType.name"/></td>
            <td align="center"><s:property value="department.name"/></td>
            <td align="center"><s:property value="assetsState.name"/></td>
            <td align="center"><s:date name="inDate" format="yyyy-MM-dd"/></td>
          </tr>
          </s:iterator>
        </table>
<table style="font-size:12px;" border="0" align="center" cellpadding="0" class="list" cellspacing="0">
          <tr>
            <td height="30" align="right" class="zczb_qua">
            <s:if test="%{pageBean.allRow!=0}">
				共<s:property value="pageBean.allRow"/>条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页 
        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
        		<s:else>
            		<a href="#" onClick="page(1);">第一页</a>
            		<a href="#" onClick="page(<s:property value="%{pageBean.currentPage-1}"/>);" >上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            		<a href="#" onClick="page(<s:property value="%{pageBean.currentPage+1}"/>);">下一页</a>
            		<a href="#" onClick="page(<s:property value="pageBean.totalPage"/>);" >最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页</s:else>
        	</s:if>	
            </td>
          </tr>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>
