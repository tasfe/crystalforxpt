<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../../css/style.css" rel="stylesheet" type="text/css">

<html>
<head>
<title>新建供应商厂商</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
  <script type='text/javascript' src='../../dwr/interface/AssetsProducerDAO.js'></script>
  <script type='text/javascript' src='../../dwr/engine.js'></script>
  <script type='text/javascript' src='../../dwr/util.js'></script>




<script type="text/javascript">
	String.prototype.trim = function()
	{
    	return this.replace(/(^\s*)|(\s*$)/g, ""); 
	}
	
	function checkPhone( strPhone ) { 
		var phoneRegWithArea = /^[0][1-9]{1,2}[0-9]{1}-[0-9]{5,8}$/; 
		var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/; 
		if( strPhone.length > 9 ) {
    		if( phoneRegWithArea.test(strPhone) ){return true; }else{return false;}
			}
		else{
    		if(phoneRegNoArea.test(strPhone)){return true; }else{return false;}
		}
	}
	
	function checkMobile( s ){ 
		var regu =/^[1][3,5,8][0-9]{9}$/; 
		var re = new RegExp(regu); 
		if (re.test(s)) { 
			return true; 
		}else{ 
			return false; 
		} 
	} 
	function isExist(){
	var name=document.getElementById("producer.name").value.trim();
	if(name=="")
	{alert("请输入名称！");
	}else{
	AssetsProducerDAO.findByNames(name,formValidate);
	}
	}
	function formValidate(data) {
		if(data.length!=0){
		alert("名称重复请重新输入！");
		}else{
		var tel=document.getElementById("producer.tel").value.trim();
		var mobile=document.getElementById("producer.persionTel").value.trim();
		var type=document.getElementById("producer.type").value;
		var level=document.getElementById("producer.level").value;
		if(tel!=""&&!checkPhone(tel)){
		alert("请输入正确的售后服务电话!");
		}else if(mobile!=""&&!checkPhone(mobile)&&!checkMobile(mobile)){
		alert("请输入正确的联系人电话!");
		}else if(type==0){
		alert("请选择类型！");
		}else if(level==0){
		alert("请选择星级级别！");
		}else{
		document.form1.submit();
		}
		}
	}
</script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;">
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
    <s:form action="save" namespace="/producer" method='post' theme="simple" name="form1">
		 <tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</td>
                        <td width="35%" bgcolor="#FFFFFF"><input type="text" id="producer.name" name="producer.name" style="width:100%;"/></td>
                        <td width="15%" align="center" bgcolor="#deebf1" >类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF" >
                        	<select size="1" id="level" id="producer.type" name="producer.type" style="width:100%;">
                        		<option value="0">--请选择--</option>
                        		<option value="1">供应商</option>
                        		<option value="2">制造商</option>
                        		<option value="3">二者都是</option>
               				</select>
                        </td>
                      </tr>
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">星&nbsp;&nbsp;级&nbsp;&nbsp;级&nbsp;&nbsp;别:</td>
                        <td width="35%" bgcolor="#FFFFFF">
                        	<select size="1" id="level" id=="producer.level" name="producer.level" style="width:100%;">
                        		<option value="0">--请选择--</option>
                        		<option value="1">*</option>
                        		<option value="2">**</option>
                        		<option value="3">***</option>
                        		<option value="4">****</option>
                        		<option value="5">*****</option>
               				</select>
                        <td width="15%" align="center" bgcolor="#deebf1">售后服务电话:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF"><input type="text" name="producer.tel" id="producer.tel" style="width:100%;"/></td>                                            
                      </tr>                     
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;&nbsp;&nbsp; 人:</td>
                        <td width="35%" bgcolor="#FFFFFF"><input type="text" name="producer.persion" style="width:100%;"/></td>
                        <td width="15%" align="center" bgcolor="#deebf1">联&nbsp;系&nbsp;人&nbsp;电&nbsp;话:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF"><input type="text" name="producer.persionTel" id="producer.persionTel" style="width:100%;"/></td>
                      </tr>                   
                      <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><input type="text" name="producer.address" style="width: 100%"></td>
                      </tr>					
              		  <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</td>
                        <td colspan="3" bgcolor="#FFFFFF"><input type="text" name="producer.description" style="width: 100%"></td>
                      </tr>
              </table>
            </td>
		</tr>
		<tr align="center" style="height: 25">
			<td height="30" colspan="2" align="center" nowrap="nowrap">
			    <input name="button" type="button"  value="保存" class="mmBtn" onClick="isExist();">&nbsp;&nbsp;
				<input name="reset" type="reset"  value="重置 " class="mmBtn">&nbsp;&nbsp;
				<input name="button" type="button" onClick="window.history.go(-1)" value="返回 " class="mmBtn"></td>
		</tr>
	</s:form>
  </table>
</body>
</html>