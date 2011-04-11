<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>供应商厂商修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='../dwr/interface/AssetsProducerDAO.js'></script>
  <script type='text/javascript' src='../dwr/engine.js'></script>
  <script type='text/javascript' src='../dwr/util.js'></script>
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
	var id=document.getElementById("producer.id").value;
	AssetsProducerDAO.findById(id,ishave);
	}
	
	function ishave(data){
	var name=document.getElementById("producer.name").value.trim();
	if(name==""){
	alert("请输入名称！");
	}else{
	if(data.name!=name){
	AssetsProducerDAO.findByNames(name,formValidate);
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
    <s:form action="update" namespace="/producer" method='post' theme="simple" name="form1">
    	<s:hidden name="producer.id" id="producer.id"></s:hidden>
		 <tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                    <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</td>
                        <td width="35%" bgcolor="#FFFFFF"><s:textfield id="producer.name" name="producer.name" cssStyle="width: 100%"></s:textfield></td>
                        <td width="15%" align="center" bgcolor="#deebf1" >类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF" >
                        	<s:select list="%{#{0:'--请选择--',1:'供应商', 2:'制造商',3:'二者都是'}}"  id="producer.type" name="producer.type" style="width:100%;"/>
                        </td>
                      </tr>
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">星&nbsp;&nbsp;级&nbsp;&nbsp;级&nbsp;&nbsp;别:</td>
                        <td width="35%" bgcolor="#FFFFFF">
                        	<s:select list="%{#{0:'--请选择--',1:'*', 2:'**',3:'***',4:'****',5:'*****'}}" id="producer.level" name="producer.level" style="width:100%;"/></td>
                        <td width="15%" align="center" bgcolor="#deebf1">售后服务电话:</td>
                        <td width="35%" bgcolor="#FFFFFF"><s:textfield id="producer.tel" name="producer.tel" style="width:100%;"></s:textfield></td>                                            
                      </tr>                     
                      <tr> 
                        <td width="15%" align="center" bgcolor="#deebf1">联&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;&nbsp;&nbsp; 人:</td>
                        <td width="35%" nowrap  bgcolor="#FFFFFF"><s:textfield id="producer.persion" name="producer.persion" style="width: 100%"/></td>
                        <td width="15%" align="center" bgcolor="#deebf1">联&nbsp;系&nbsp;人&nbsp;电&nbsp;话:</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF"><s:textfield id="producer.persionTel" name="producer.persionTel" style="width: 100%"/></td>
                      </tr>                   
                      <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
                        <td  colspan="3" bgcolor="#FFFFFF"><s:textfield id="producer.address"  name="producer.address" style="width: 100%"/></td>
                      </tr>	
                       <tr>
                        <td height="50%" align="center" valign="top" nowrap bgcolor="#deebf1">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</td>
                        <td  colspan="3" bgcolor="#FFFFFF"><s:textfield id=""  name="producer.description" style="width: 100%"/></td>
                      </tr>				
              </table>
            </td>
		</tr>
		<tr align="center" style="height: 25">
			<td height="30" colspan="2" align="center" nowrap="nowrap">
				<input name="button" type="button"  value="修改 " class="mmBtn" onClick="isExist();">&nbsp;&nbsp;
				<input name="reset" type="reset"  value=" 重置 " class="mmBtn">&nbsp;&nbsp;
				<input name="button" type="button" onClick="window.history.go(-1)" value=" 返回 " class="mmBtn"></td>
		</tr>
	</s:form>
	
  </table>
</body>		
</html>