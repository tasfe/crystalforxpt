<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
		<script type='text/javascript'
			src='/itsm/dwr/interface/AssetsTypeDAO.js'></script>
 <script type='text/javascript' src='/itsm/dwr/interface/AssetsProducerDAO.js'></script>


		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
function init(){ //取出类别

loadTop();
DepartmentDAO.findAll(callbackdepartment);
AssetsTypeDAO.findAll(callbackassetstype);
AssetsProducerDAO.findByIntType(1,callbackproducer);
AssetsProducerDAO.findByIntType(2,callbacksupporter);


}



function callbackdepartment(data){  //显示出类别
 dwr.util.removeAllOptions("parentDepart");
   dwr.util.addOptions("parentDepart",{'-1':'--请选择--'});
   dwr.util.addOptions("parentDepart",data,"id","name");  
     var a = "<s:property value="assets.departmentCode" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("parentDepart",a);  
   } 
}
function callbacksupporter(data){  //显示出类别
 dwr.util.removeAllOptions("supporter");
   dwr.util.addOptions("supporter",{'-1':'--请选择--'});
   dwr.util.addOptions("supporter",data,"id","name");  
     var a = "<s:property value="assets.supportId" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("supporter",a);  
   } 
}
function callbackassetstype(data){  //显示出类别
 dwr.util.removeAllOptions("assetstypeDepart");
   dwr.util.addOptions("assetstypeDepart",{'-1':'--请选择--'});
   dwr.util.addOptions("assetstypeDepart",data,"id","name");  
     var a = "<s:property value="assets.typeCode" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("assetstypeDepart",a);  
   } 
}
function callbackproducer(data){  //显示出类别
 dwr.util.removeAllOptions("producer");
   dwr.util.addOptions("producer",{'-1':'--请选择--'});
   dwr.util.addOptions("producer",data,"id","name");  
     var a = "<s:property value="assets.produceId" />";
     if (typeof(a) != "undefined") {   
     dwr.util.setValue("producer",a);  
   } 
}

	function checkAssetsType()
	{
	var type = document.getElementById('assetsQurey.name');
	priceinfo.innerHTML=type.value;
	out.print(type.value);
	}

</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		onLoad="init();">
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30" align="right">
			</tr>
			<tr>
    <td height="20" colspan="5" background="../images/main20100521_58.gif"><table width="50%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td width="2%" align="center" background="../images/main20100521_58.gif" style="color:#FFFFFF; font-weight:bold; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
        <td width="98%" style="color:#333333; font-weight:bold;">资产质保信息(10天内即将过保资产)</td>
      </tr>
    </table></td>
  </tr>
		</table>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="1" bgcolor="#b5d6e6">

			<tr bgcolor="#FFFFFF">
				<td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					设备编号
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					设备名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					操作系统
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					生命周期状态
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					部门
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					IP地址
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					出厂日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					购买日期
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					保修日期
				</td>
			</tr>
			<s:iterator value="htmlAssetsList" var="htmlAssets">
				<tr bgcolor="#FFFFFF" onMouseOver=this.bgColor
					= '#e3f0f7';onMouseOut=this.bgColor='#FFFFFF';>
					<td height="19" align="center">
						<s:property value="code" />
					</td>
					<td align="center">
						<s:property value="name" />
					</td>
					<td align="center">
						<s:property value="system" />
					</td>
					<td align="center">
						<s:property value="stateString" />
					</td>
					<td align="center">
						<s:property value="departmentString" />
					</td>
					<td align="center">
						<s:property value="ip" />
					</td>
					<td align="center">
						<s:property value="inDate" />
					</td>
					<td align="center">
						<s:property value="inDate" />
					</td>
					<td align="center">
						<s:property value="qualityTime" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
