<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产管理信息查看</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
 function res(){
   document.getElementById("assets.name").value="";
   document.getElementById("assets.codeId").value="";
   document.getElementById("assets.assetsProducerBySupportId.id").value="";
   document.getElementById("assets.assetsProducerBySupportId.name").value="";
   document.getElementById("assets.assetsProducerByProduceId.id").value="";
   document.getElementById("assets.assetsProducerByProduceId.name").value="";
   document.getElementById("assets.assetsType.id").value="";
   document.getElementById("assets.assetsType.name").value="";
   document.getElementById("departmentid").value="";
   document.getElementById("departmentname").value="";
   document.getElementById("assets.assetsState.id").value="";
   document.getElementById("assets.assetsState.name").value="";
   document.getElementById("assets.buildlocation.id").value="";
   document.getElementById("assets.buildlocation.name").value="";
 }
 
function changepage(page){
document.form1.action="qurey.action?page="+page;
document.form1.submit();
}

function notshow(){
document.getElementById('buildlocation').style.visibility='hidden';
document.getElementById('assetsType').style.visibility='hidden';
document.getElementById('assetsState').style.visibility='hidden';
document.getElementById('department').style.visibility='hidden';
document.getElementById('support').style.visibility='hidden';
document.getElementById('producer').style.visibility='hidden';

}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onMouseDown="notshow();" >
		<table align="center" width="99%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
		  <s:form action="/showAssets/qurey.action" method='post' theme="simple" name="form1">
		    <tr>
		      <td height="22" width="10%" align="right" bgcolor="#deebf1"> 资产名称： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF"><s:textfield id="assets.name" name="assets.name" cssStyle="width:100%"></s:textfield>
	          <td width="10%" align="right" bgcolor="#deebf1"> 资产编号： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF"><s:textfield id="assets.codeId" name="assets.codeId" cssStyle="width:100%"></s:textfield>
	          <td width="10%" align="right" bgcolor="#deebf1"> 供应商： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsProducerBySupportId.id" id="supportid" value="<s:property value="assets.assetsProducerBySupportId.id"/>">
<input type="text" name="assets.assetsProducerBySupportId.name" id="supportname" value="<s:property value="assets.assetsProducerBySupportId.name"/>" onClick="document.getElementById('support').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('support').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="support"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/support.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>		      
		      
		      </td>
		      <td width="10%" align="right" bgcolor="#deebf1"> 制造商： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsProducerByProduceId.id" id="producerid" value="<s:property value="assets.assetsProducerByProduceId.id"/>">
<input type="text" name="assets.assetsProducerByProduceId.name" id="producername" value="<s:property value="assets.assetsProducerByProduceId.name"/>" onClick="document.getElementById('producer').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('producer').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="producer"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/producer.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

		       </td>
	        </tr>
		    <tr>
		      <td width="10%" align="right" bgcolor="#deebf1"> 资产类别： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assetsTypeid" value="<s:property value="assets.assetsType.id"/>">
<input type="text" name="assets.assetsType.name" id="assetsTypename" value="<s:property value="assets.assetsType.name"/>" onClick="document.getElementById('assetsType').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
              </td>
		      <td width="10%" align="right" bgcolor="#deebf1"> 资产状态： </td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsState.id" id="assetsStateid" value="<s:property value="assets.assetsState.id"/>">
<input type="text" name="assets.assetsState.name" id="assetsStatename" value="<s:property value="assets.assetsState.name"/>" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>		      
		      
		      </td>
		      <td width="10%" align="right" bgcolor="#deebf1">责任部门：</td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.usersByChargeId.department.id" id="departmentid" value="<s:property value="assets.usersByChargeId.department.id"/>">
<input type="text" name="assets.usersByChargeId.department.name" id="departmentname" value="<s:property value="assets.usersByChargeId.department.name"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="department"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
		      </td>
		      <td width="10%" align="right" bgcolor="#deebf1">存放位置：</td>
		      <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.buildlocation.id" id="buildlocationid" value="<s:property value="assets.buildlocation.id"/>">
<input type="text" name="assets.buildlocation.name" id="buildlocationname" value="<s:property value="assets.buildlocation.name"/>" onClick="document.getElementById('buildlocation').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('buildlocation').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="buildlocation"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/buildlocation.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 


		     </td>
	        </tr>
		    <tr>
		      <td colspan="8" align="center" bgcolor="#FFFFFF"><input type="submit" style="height: 20px" class="mmBtn"	value="搜索"/>
		         <input type="button" style="height: 20px" class="mmBtn" value="重置" onClick="res();"/></td>
	        </tr>
	      </s:form>
    </table>
    <table width="96%" border="0">
  <tr>
    <td height="6"></td>
  </tr>
</table>
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
					供应商
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					制造商
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
					存放位置
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					查看				</td>
			</tr>
			<s:iterator value="pageBean.list" var="assets">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center" class="zczb_qua">
						<s:property value="codeId" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="assetsProducerBySupportId.name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="assetsProducerByProduceId.name" />
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
						<s:property value="buildlocation.allname" />
					</td>
					<td align="center" class="zczb_qua">
					  <a href="show.action?assetsId=<s:property value="code" />" >查看</a>				    </td>
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
                    <a href="#" onClick="changepage(1);">第一页</a>
            		<a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage-1}"/>);" >上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
                    <a href="#" onClick="changepage(<s:property value="%{pageBean.currentPage+1}"/>);" >下一页</a>
            		<a href="#" onClick="changepage(<s:property value="pageBean.totalPage"/>);">最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页</s:else>
        	</s:if>
            </td>
          </tr>
</table>
	</body>
</html>
