<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产类别管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
  <script type='text/javascript' src='/itsm/dwr/interface/AssetsBaseDAO.js'></script>
  <script type='text/javascript' src='/itsm/dwr/engine.js'></script>
  <script type='text/javascript' src='/itsm/dwr/util.js'></script>


<script type="text/javascript">
function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg)==true)  { 
		
		return true;
   		}else{   
        	return false;   
   		}   
	}  
function newassets(){
  window.location='addInput.action';
}
function init(){
var a="<s:property value="message"/>";
if(a=="1"){
alert("此位置暂时不能删除！");
}
if(a=="2"){
parent.document.getElementById('topFrame').src="top.action";
location.href="list.action?pid=0";
}
}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
		<span id="da"></span>
				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
			     <tr><s:hidden name="assetsType.pid" value="%{assetsType.pid}"/> 
			     <tags:button code="add" menu="7">
				  <td height="30" align="right">
				   <input type="button" class="mmBtn" value="新建资产类型" onClick="newassets();">
				   <%-- <input type="button" class="mmBtn" value="备份资产" onclick="window.open('backupAssets.action','backup','height=400,width=500,location=no,status=no,toolbar=no');">	--%>			
				  </td>
				  </tags:button>
			    </tr>
	           </table>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
				<td height="22" align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					类别编号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					类别名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					详细描述
				</td>
				<tags:button code="update" menu="7">
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					修改
				</td>
				</tags:button>
				<tags:button code="delete" menu="7">
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					删除
				</td>
                </tags:button>
			</tr>
			<s:iterator value="assetsTypeList" var="assetsType">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center" class="zczb_qua">
						<s:property value="id" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="description" />
					</td>
					<tags:button code="update" menu="7">
					<td align="center" class="zczb_qua">
						<img src="../images/edt.gif">
						<a href="updateInput.action?assetsTypeId=${assetsType.id}">修改</a>
					</td>
					</tags:button>
					<tags:button code="delete" menu="7">
					<td align="center" class="zczb_qua"><img src="../images/del.gif"><a href="delete.action?assetsTypeId=${assetsType.id}" onClick="javascript:return del()">删除</a>
					</td>
					</tags:button>
                 
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
