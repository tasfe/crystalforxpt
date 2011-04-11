<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产选择</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		window.name="win_self" 
var arr=new Array();//全局数组用于存放用户选择的资产
function res()
{
   document.getElementById('assets.name').value="";
   document.getElementById('assets.codeId').value="";
   document.getElementById('assets.model').value="";
   document.getElementById('assets.assetsProducerBySupportId.id').selectedIndex="请选择";
   document.getElementById('assets.assetsProducerByProduceId.id').selectedIndex="请选择";
   document.getElementById('assets.assetsType.id').value="";
   document.getElementById('assets.assetsType.name').value="";
   document.getElementById('assets.assetsState.id').selectedIndex="请选择";
   document.getElementById('assets.department.id').value="";
   document.getElementById('assets.department.name').value="";
   document.getElementById('assets.location.id').selectedIndex="请选择";
   document.getElementById('assets.usersByUsersId.id').value="";
   document.getElementById('assets.usersByUsersId.truename').value="";
   document.getElementById('assets.usersByChargeId.id').value="";
   document.getElementById('assets.usersByChargeId.truename').value="";
}
function clo(){
	window.close();
}
function choose(){
	window.returnValue=arr;
	window.close();
}
function check(check,id,code,name,type,supplier,producer){//将选择的资产放入数组
	if(check.checked){
		var obj = new Object();
		obj.id=id;
		obj.code=code;
		obj.name=name;
		obj.type=type;
		obj.supplier=supplier;
		obj.producer=producer;
		arr.push(obj);
	}else{
		for(var i=0;i<arr.length;i++){
			if(arr[i].id==id){
				arr.splice(i,1);
				break;
			}
		}
	}
}
function query(){
   document.formInc.action="queryForInc.action";
   //document.form1.method="post";
   document.formInc.submit();
}
function changepage(page){
document.formInc.action="queryForInc.action?page="+page;
//document.form1.method="post";
document.formInc.submit();
}

function notshow(){
document.getElementById('Layer1').style.visibility='hidden';
document.getElementById('Layer2').style.visibility='hidden';
}

function user(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assets.usersByUsersId.id").value=result.id;
document.getElementById("assets.usersByUsersId.truename").value=result.name;
}
}
function Charge(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assets.usersByChargeId.id").value=result.id;
document.getElementById("assets.usersByChargeId.truename").value=result.name;
}
}
</script>
<BASE target=_self>
</head>
	

	<body  onMouseDown="notshow();" >
	<s:form action=""  theme="simple" name="formInc" target="win_self">
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产名称： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%"><s:textfield id="assets.name" name="assets.name" style="width:80%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产编号： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%"><s:textfield id="assets.codeId" name="assets.codeId" style="width:80%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">资产型号：</td>
          <td bgcolor="#FFFFFF" align="center" width="8%"><s:textfield id="assets.model" name="assets.model" style="width:80%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产类型： </td>
          <td bgcolor="#FFFFFF" align="center" width="15%">
<input type="hidden" name="assets.assetsType.id" id="assets.assetsType.id" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="assets.assetsType.name" onClick="document.getElementById('Layer1').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer1"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
          </td>
         </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 供应商： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%">
          <s:select list="supportlist" listKey="id" listValue="name" id="assets.assetsProducerBySupportId.id" name="assets.assetsProducerBySupportId.id" label="供应商：" headerKey="-1" headerValue="请选择" style="width:80%;"/>  
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 制造商： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%">
          <s:select list="producelist" listKey="id" listValue="name" id="assets.assetsProducerByProduceId.id" name="assets.assetsProducerByProduceId.id" label="厂商：" headerKey="-1" headerValue="请选择" style="width:80%;"/>  
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产状态： </td>
          <td bgcolor="#FFFFFF" align="center" width="8%">
          <s:select list="statelist" listKey="id" listValue="name" id="assets.assetsState.id" name="assets.assetsState.id" label="资产状态" headerKey="-1" headerValue="请选择" style="width:80%;"/>
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">所属部门：</td>
         <td bgcolor="#FFFFFF" align="center" width="15%">

<input type="hidden" name="assets.department.id" id="assets.department.id" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="assets.department.name" onClick="document.getElementById('Layer2').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer2"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
         </td>
         </tr>
         <tr>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">存放位置：</td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
          <s:select list="locationlist" listKey="id" listValue="name" id="assets.location.id" name="assets.location.id" label="存放位置：" headerKey="-1" headerValue="请选择" style="width:80%;"/>
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 使用人： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
          <s:hidden id="assets.usersByUsersId.id" name="assets.usersByUsersId.id"/>
          <s:textfield id="assets.usersByUsersId.truename" name="assets.usersByUsersId.truename" style="width:70%;" onclick="user();"/>
          <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="user();">
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 责任人： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
          <s:hidden id="assets.usersByChargeId.id" name="assets.usersByChargeId.id"/>
          <s:textfield id="assets.usersByChargeId.truename" name="assets.usersByChargeId.truename" style="width:70%;" onclick="Charge();"/>
          <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Charge();">
          </td>
          <td bgcolor="#deebf1" align="center" colspan="2"><input type="button" style="height: 20px" class="mmBtn"  value="搜索" onClick="query();"/>
          &nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/> &nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="确定" onClick="choose();"/> &nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="取消" onClick="clo();"/>
          </td>
         </tr>
        </table>
	   </s:form>
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">选中</td>
			  <td  height="22" align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产编号</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">资产名称</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">资产类别</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">所属部门</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">供应商</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">制造商</td>
			 </tr>
			<s:iterator value="pageBean.list" var="assetsBase">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
	  		  <td align="center" class="zczb_qua"><input type="checkbox" name="obj" id="obj" value="" 
	  										onClick="check(this,'<s:property value="code" />','<s:property value="codeId" />','<s:property value="name" />','<s:property value="assetsType.name" />','<s:property value="assetsProducerBySupportId.name" />','<s:property value="assetsProducerByProduceId.name" />');"/></td>
			  <td height="19" align="center" class="zczb_qua"><s:property value="codeId" /></td>
			  <td align="center" class="zczb_qua"><s:property value="name" /></td>
			  <td align="center" class="zczb_qua">
			  	<span title=" <s:property value="assetsType.name" />">
				  	<s:if test="assetsType.name.length()>7">          
					    <s:property value="assetsType.name.substring(0,7)"/>… 
					</s:if>                     
					<s:else>       
					    <s:property value="assetsType.name" />   
					</s:else>
				</span>
			  </td>
			  <td align="center" class="zczb_qua"><s:property value="department.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsProducerBySupportId.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsProducerByProduceId.name" /></td>
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
