<%@ page language="java" contentType="text/html; charset=UTF-8"	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
function res(){
   document.getElementById('assets.codeId').value="";
   document.getElementById('assets.name').value="";
   document.getElementById('assets.assetsState.id').value="";
   document.getElementById('assets.assetsState.name').value="";
   document.getElementById('assets.department.id').value="";
   document.getElementById('assets.department.name').value="";
   document.getElementById('assets.assetsType.id').value="";
   document.getElementById('assets.assetsType.name').value="";
   document.getElementById('assets.inDate').value="";
   document.getElementById('assets.exitfacotryDate').value="";
   document.getElementById('assets.buyDate').value="";
}


function changepage(page){
document.form1.action="quality.action?page="+page;
document.form1.submit();
}
function notshow(){
document.getElementById('assetsType').style.visibility='hidden';
document.getElementById('assetsState').style.visibility='hidden';
document.getElementById('department').style.visibility='hidden';
}

</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onMouseDown="notshow();">
	<s:form action="/assets/quality.action" method='post' theme="simple" name="form1">
	  <table cellspacing=0 cellpadding=0 border=0 width="100%">
	    <tr>
	      <td width="3%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
	      <td width="97%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产质保信息</td>
        </tr>
      </table>
	  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
	<tr>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	资产编号：	</td>
	<td bgcolor="#FFFFFF"  width="14%" align="left" style="padding-left:5px;padding-right:5px;">
	<s:textfield id="assets.codeId" name="assets.codeId" style="width:98%;"></s:textfield></td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	资产名称：</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
	<s:textfield id="assets.name" name="assets.name" style="width:98%;"></s:textfield>	</td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	资产状态：</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsState.id" id="assetsStateid" value="<s:property value="assets.assetsState.id"/>">
<input type="text" name="assets.assetsState.name" id="assetsStatename" value="<s:property value="assets.assetsState.name"/>" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
	</td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	责任部门：</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.usersByChargeId.department.id" id="departmentid" value="<s:property value="assets.usersByChargeId.department.id"/>">
<input type="text" name="assets.department.name" id="departmentname" value="<s:property value="assets.usersByChargeId.department.name"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="department"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>	
	
	</td>
	<td align="center" nowrap bgcolor="#deebf1" >
	<input type="submit" class="mmBtn" value="搜索">
	</td>
	</tr>
	
	<tr>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	出厂日期：	</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
	<input type="text" id="assets.exitfacotryDate" name="assets.exitfacotryDate" style="width:98%;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='assets.exitfacotryDate' format='yyyy-MM-dd'/>">	</td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	采购日期：	</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
	<input type="text" id=assets.buyDate name="assets.buyDate" style="width:98%;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='assets.buyDate' format='yyyy-MM-dd'/>">	</td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	入库日期：</td>
	<td bgcolor="#FFFFFF" align="left" width="14%" style="padding-left:5px;padding-right:5px;">
	<input type="text" id="assets.inDate" name="assets.inDate" style="width:98%;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='assets.inDate' format='yyyy-MM-dd'/>">	</td>
	<td align="right" nowrap bgcolor="#deebf1" width="8%">
	资产类别：</td>
	<td bgcolor="#FFFFFF" align="left" width="4%" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsType.id" id="assetsTypeid" value="<s:property value="assets.assetsType.id"/>">
<input type="text" name="assets.assetsType.name" id="assetsTypename" value="<s:property value="assets.assetsType.name"/>" onClick="document.getElementById('assetsType').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
	</td>
	<td align="center" nowrap bgcolor="#deebf1" width="10%">
    <input type="button" class="mmBtn" value="重置" onClick="res();">
    </td>
	</tr>
	</table>
	</s:form>
	
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">

		  <tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					资产编号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					资产名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					资产类别
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					资产状态
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					责任部门
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					出厂日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					采购日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					入库日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					保修年限（月）
				</td>
			</tr>
			<s:iterator value="pageBean.list" var="assetsBase">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center" class="zczb_qua">
						<s:property value="codeId" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="assetsType.name" />
					</td>
					<td align="center" class="zczb_qua">
					    <s:property value="assetsState.name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="usersByChargeId.department.name" />
					</td>
					<td align="center" class="zczb_qua">
					    <s:date name='exitfacotryDate' format='yyyy-MM-dd'/>
					</td>
					<td align="center" class="zczb_qua">
					    <s:date name='buyDate' format='yyyy-MM-dd'/>
					</td>
					<td align="center" class="zczb_qua">
					    <s:date name='inDate' format='yyyy-MM-dd'/>
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="qualityTime" />
					</td>
				</tr>
			</s:iterator>
		</table>
		
		<table border="0" align="center" cellpadding="0" class="list" cellspacing="0">
          <tr>
            <td height="30" align="right" class="zczb_qua">
            <s:if test="%{pageBean.allRow!=0}">
				共<s:property value="pageBean.allRow"/> 条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页
        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
        		<s:else>
            		<a href="#" onClick="changepage(1);">第一页</a>
            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage-1}"/>);">上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage+1}"/>);">下一页</a>
            		<a href="#" onClick="changepage(<s:property value="pageBean.totalPage"/>);">最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页 </s:else>
        	 </s:if>
            </td>
          </tr>
        </table>
	</body>
</html>
