<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产新增</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<script type="text/javascript" src="../js/util.js"></script>
		<script type="text/javascript">
function init(){
var now=new Date();
var year=now.getFullYear();
var month=now.getMonth()+1;
var day=now.getDate();
if(month<10){month=0+""+month;}
if(day<10){day=0+""+day;}
var date=year+"-"+month+"-"+day;
document.getElementById("buyDate0").value=date;
document.getElementById("exitfacotryDate0").value=date;
document.getElementById("inDate0").value=date;
}
	function dateJudge(){   
	if(document.getElementById("codeId0")){
	    var code=document.getElementById("codeId0").value;
		if(code==""){
		  alert("请输入资产编号！");
		  return false;
		}
	}
	
	if(document.getElementById("name0")){	
		var name=document.getElementById("name0").value;
		if(name==""){
			alert("请输入资产名称！");
			return false;
		}
	}
	
	if(document.getElementById("assetsStateid")){	
		var assetsState=document.getElementById("assetsStateid").value; 
		if(assetsState<=0){
			alert("请选择资产状态！");
		    return false;
		}
	}
	
	if(document.getElementById("ip0")){
	    var ip=document.getElementById("ip0").value;
	    var patrn=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;   
	    if(ip.length!=0){
           if (!patrn.exec(ip)) {
           alert("请填写正确的IP！");
           return false;}
        }  
	}
	
	if(document.getElementById("qualityTime0")){
	    var qualityTime=document.getElementById("qualityTime0").value;
	    var patrn=/^[0-9]{1,20}$/; 
	    if(qualityTime.length!=0){
	       if (!patrn.exec(qualityTime)) {
	          alert("保修年限请输入数字！");
	          return false;
	       }
	    }
	}
	
	
	if(document.getElementById("price0")){
	   var price=document.getElementById("price0").value;
	   var patrn=/^\d+(?:\.\d{0,2})?$/; 
	   if(price.length!=0){
	     if(!patrn.exec(price)){
	     alert("请填写资产单价！");
	     return false;
	   }
	}
	}
	
	if(document.getElementById("usersByChargeId0.name")){
		var charge=document.getElementById("usersByChargeId0.name").value; 
		if(charge==""){
			alert("请选择负责人！");
			return false;
		}
	}
} 
function back()
{
window.parent.location.href="list.action";
}	
	
	
var fileInputNumber = 0;
  function addFile(){	
			var strFile = "file"+fileInputNumber;
			var filePath = document.getElementById(strFile);
			document.getElementById(strFile).style.display = "none";
			var paths = filePath.value.split("\\");
	        var name = paths[paths.length-1];
　　   		var str1 =
				'<div style="background-color:#E7EBF7">'+
				'<font size="2" style="width:300px">'+name+'</font>'+
				'<a href="#"><img onclick="removeFile(this,'+strFile+')" src="../img/del2.gif" border="0" alt="删除附件"/></a>'+
				'</div>';
				var _file = document.getElementById("_file");
　　   		    _file.insertAdjacentHTML("beforeend",str1);

			addInput();
　　    	}
	function addInput(){
			fileInputNumber++;
			var strFile = "file"+fileInputNumber;
		　　 var str2 = '<input name="file" id="'+strFile+'" type="file" value="添加附件" onchange="addFile()" />';
			var _input = document.getElementById("input");
　　   		_input.insertAdjacentHTML("afterbegin",str2);
	}
	function removeFile(id,strFile) {
　　      var new_tr = id.parentNode.parentNode;
　　      try {
　　          var tmp = new_tr.parentNode;
　　          tmp.removeChild(new_tr);
	    removeInput(strFile);
　　       } catch(e) {}
　　}

	function removeInput(strFile) {
　　      var _input = document.getElementById("input");
　　      try {
　　          input.removeChild(strFile);
　　       } catch(e) {}
　　}

function barcode(){
var type="<s:property value="assetsType.id"/>";
var now=new Date();
var year=now.getFullYear();
var month=now.getMonth()+1;
var day=now.getDate();
if(month<10){month=0+""+month;}
if(day<10){day=0+""+day;}
if(type<10){type=0+""+type;}
var math=Math.floor(Math.random()*10)+""+Math.floor(Math.random()*10)+""+Math.floor(Math.random()*10)+""+Math.floor(Math.random()*10);
document.getElementById("codeId0").value=type+""+year+""+month+""+day+""+math; 
}
function notshow(){
document.getElementById('buildlocation').style.visibility='hidden';
document.getElementById('itsmType').style.visibility='hidden';
document.getElementById('assetsState').style.visibility='hidden';
document.getElementById('location').style.visibility='hidden';
document.getElementById('support').style.visibility='hidden';
document.getElementById('producer').style.visibility='hidden';
}
function user(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByUsersId0").value=result.id;
document.getElementById("usersByUsersId0.name").value=result.name;
document.getElementById("usersByUsersId0.department").value=result.department;
}
}
function Charge(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByChargeId0").value=result.id;
document.getElementById("usersByChargeId0.name").value=result.name;
document.getElementById("usersByChargeId0.department").value=result.department;
}
}


function selectUser(){
var win = new UserSelectWindow({
accepted:function(user){
document.getElementById('usersByChargeId0.name').value=user.name;
document.getElementById('usersByChargeId0').value=user.id;
document.getElementById('usersByChargeId0.department').value=user.departmentname;
}
});
win.show();
}

function selectUser1(){
var win = new UserSelectWindow({
accepted:function(user){
document.getElementById('usersByUsersId0.name').value=user.name;
document.getElementById('usersByUsersId0').value=user.id;
document.getElementById('usersByUsersId0.department').value=user.departmentname;
}
});
win.show();
}

</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();" onMouseDown="notshow();">
<s:form action="/assets/save.action?type=%{assetsType.id}" method="post" theme="simple" name="form1" enctype="multipart/form-data">
<table width="98%" cellspacing="1" align="center" border=1 bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle">
 <table width="100%" align="center" >
  <tr>
    <td width="70%" align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="assetsType.name"/>新增资产</td>
   <!-- <td width="10%" align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle">数量:<input type="text" id="num" name="num" value="1" style="width:30%;height=18;"></td> --> 
    <td width="20%" align="right" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><font class="zc_add">如需其他信息请配置资产属性</font><img src="../images/tanhao.jpg" width="5" height="12">
    &nbsp;&nbsp;&nbsp;</td>
  </tr>
 </table>
 </td>
 </tr>
 <tr>
   <td height="22" bgcolor="#FFFFFF">
   <table width="98%" align="center" cellpadding="0" cellspacing="0">
     <tr>
       <td valign="top"><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·资产信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
           </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td  height="30" align="right"><font class="zc_add">*</font>资产编号：</td> 
                   <td  height="30" align="left"><input type="text" id="codeId0" name="codeId0" style="width:150;height=18;"><input type="button" onClick="barcode();" value="条形码" style="font-size:10px;width:50px;height:18px;"></td>

                   <td width="4%"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right"><font class="zc_add">*</font>资产名称：</td>
                   <td  height="30" align="left"><input type="text" id="name0" name="name0" style="width:85%;height=18;"></td>
                   <td ><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">资产型号：</td>
                   <td  height="30" align="left" width="20%"><input type="text" id="model0" name="model0" style="width:85%;height=18;"></td>
                 </tr>
                  <tr>
                   <td height="30" align="right">资产类别：</td>
                   <input type="hidden" id="assetsType0" name="assetsType0" value="<s:property value="assetsType.id"/>">
                   <td height="30"  align="left"><input type="text" value="<s:property value="assetsType.name"/>" disabled style="width:85%;height=18;"/></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right">运维类别：</td>
                   
                   <td height="30"  align="left" width="20%">
<input type="hidden" id="itsmTypeid" name="itsmType0">
<input type="text" name="itsmType.name" id="itsmTypename" onClick="document.getElementById('itsmType').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('itsmType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="itsmType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/itsmtype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
</td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right"><font class="zc_add">*</font>资产状态：</td>
                   <td height="30" align="left">
                   <input type="hidden" name="assetsState0" id="assetsStateid">
<input type="text" name="assetsState.name" id="assetsStatename" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                    </td>
                   
                   
                 </tr>
                 <tr>
                   <td height="30" align="right">保修年限(月)：</td>
                   <td height="30" align="left"><input type="text" id="qualityTime0" name="qualityTime0" style="width:85%;height=18;"></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right">资产单价：</td>
                   <td height="30"  align="left"><input type="text" id="price0" name="price0" style="width:85%;height=18;"></td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                       </tr>
                   </table></td>
                    <td height="30" align="right">采购日期：</td>
                   <td height="30"  align="left"><input type="text" id="buyDate0" name="buyDate0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   
                 </tr>
                 <tr>  
                   <td height="30" align="right">出厂日期：</td>
                   <td height="30" align="left"><input type="text" id="exitfacotryDate0" name="exitfacotryDate0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                  
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">入库日期：</td>
                   <td height="30" align="left"><input type="text" id="inDate0" name="inDate0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right">地理区域：</td>
                   <td height="30" align="left">
                   <input type="hidden" name="location0" id="locationid">
<input type="text" name="location.name" id="locationname" onClick="document.getElementById('location').style.visibility='visible'" readonly  style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('location').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="location"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                 </td>
                 </tr>
                 <tr>  
                  <td height="30" align="right">存放位置：</td>
                   <td height="30" align="left">
                   <input type="hidden" name="buildlocation0" id="buildlocationid">
<input type="text" name="buildlocation.name" id="buildlocationname" onClick="document.getElementById('buildlocation').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('buildlocation').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="buildlocation"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/buildlocation.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                  </td> 
                  <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                 
                 
 <%List list1=(List)ActionContext.getContext().getSession().get("assetsConfigList"); 
   if(list1.size()>0){
  for(int i=0;i<list1.size();i++){
  AssetsConfig assetsConfig=(AssetsConfig)list1.get(i);
  if(assetsConfig.getConfigColumnName().indexOf("remark")>=0||assetsConfig.getConfigColumnName().equals("mac")||assetsConfig.getConfigColumnName().equals("devicename")||assetsConfig.getConfigColumnName().equals("ip")||assetsConfig.getConfigColumnName().equals("system")){
  if(i%3==0){%>
         <tr>
            <td height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
              <tr>
                <td></td>
                </tr>
            </table></td>	
  <%}else if(i%3==1){%>
            <td height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td height="30" align="left">
            
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>
  <%}else if(i%3==2){%>
            <td height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            </td>
		   </tr>
  <%}}}
  }%>

               </table></td>
             </tr>
           </table></td>
           </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
     <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
       <tr>
         <td width="5%" class="zcxx_tab1">&nbsp;</td>
         <td width="15%" valign="bottom" class="alllisttitle" align="center">·资产附件·</td>
         <td width="80%" class="zcxx_tab1">&nbsp;</td>
       </tr>
       <tr>
         <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
           <tr>
             <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
               <tr>
                 <td width="10%" height="30" align="right" >上传文件：</td>
                 <td width="90%" height="30" align="left" >
                 
                 <table width="97%" border="1" cellspacing="0" cellpadding="0"  style="border:#C3BFB3 1px solid; border-collapse: collapse; border-left-width: 1px; border-right-width: 1px; border-top-width: 1px; background-color:#FFF" class="zcxx_tab3">
                   
                   <tr>
                     <td height="20" bgcolor="#FFFFFF">
                     <table width="100%" border="0" cellspacing="1" cellpadding="4">
                       <tr>
                          <td valign="top" height="100" colspan="2">
                           <div id="_file"></div><br/>
                          </td>
                       </tr>
                       <tr>
                         <td width="1%" bgcolor="#EBF4F5" nowrap>&nbsp;选择文件：</td>
                         <td width="99%" bgcolor="#EBF4F5" style="padding-top: 1px; padding-bottom: 2px"  class="zc_add">
                          <div id="input">
                           <input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
                           <font class="zc_add">如果添加多个附件，请继续点击“浏览”</font> 
                          </div>
                         </td>
                       </tr>
                     </table></td>
                   </tr>
                 </table>
                 </td>
                 </tr>
               </table></td>
           </tr>
         </table></td>
       </tr>
       <tr>
         <td colspan="3">&nbsp;</td>
       </tr>
     </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="20%" valign="bottom" class="alllisttitle" align="center">·供应商制造商信息·</td>
           <td width="75%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   
                   <td width="238" height="30" align="right" >供应商：</td>
                   <td width="285" height="30" align="left" >
                   <input type="hidden" name="assetsProducerBySupportId0" id="supportid">
<input type="text" name="support.name" id="supportname" onClick="document.getElementById('support').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('support').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="support"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/support.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                   
                   
                   
              </td>
                   <td width="103"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="181" height="30" align="right" >制造商：</td>
                   <td width="288" height="30" align="left" >
                   <input type="hidden" name="assetsProducerByProduceId0" id="producerid">
<input type="text" name="producer.name" id="producername" onClick="document.getElementById('producer').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('producer').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="producer"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/producer.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                   
                </td>

                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·责任信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td width="10%" height="30" align="right" ><font class="zc_add">*</font>责任人：</td>
                   <td width="15%" height="30" align="left" >
                   <s:hidden id="usersByChargeId0" name="usersByChargeId0"/>
                   <s:textfield id="usersByChargeId0.name" name="usersByChargeId0.name" style="width:80%;" onClick="javascript:selectUser();"/>
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser();"></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="10%" height="30" align="right" >责任部门：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="text" id="usersByChargeId0.department" name="usersByChargeId0.department" readonly style="width:80%;">                   </td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="10%" height="30" align="right" >使用人：</td>
                   <td width="15%" height="30" align="left" >
                   <s:hidden id="usersByUsersId0" name="usersByUsersId0"/>
                   <s:textfield id="usersByUsersId0.name" name="usersByUsersId0.name" style="width:80%;" onClick="javascript:selectUser1();"/>
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser1();">                   </td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="10%" height="30" align="right" >使用部门：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="text" id="usersByUsersId0.department" name="usersByUsersId0.department" readonly style="width:80%;">                   </td>
                 </tr>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <%List lists=(List)ActionContext.getContext().getSession().get("assetsConfigInfoList");
     if(lists.size()!=0){%>
     <tr>
       <td><table width="99%" align="center" cellpadding="0" cellspacing="0">
         <tr>
           <td width="5%" class="zcxx_tab1">&nbsp;</td>
           <td width="15%" valign="bottom" class="alllisttitle" align="center">·专有信息·</td>
           <td width="80%" class="zcxx_tab1">&nbsp;</td>
         </tr>
         <tr>
           <td colspan="3" valign="top"><table width="100%" cellspacing="0" cellpadding="0" class="zcxx_tab2">
             <tr>
               <td valign="top" bgcolor="#EBF4FD"><table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">

  <%for(int i=0;i<lists.size();i++){
  AssetsConfig assetsConfig=(AssetsConfig)lists.get(i);
  if(i%3==0){%>
         <tr>
            <td width="10%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="24%" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>	
  <%}else if(i%3==1){%>
            <td height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
            
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:198px;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:198px;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>
  <%}else if(i%3==2){%>
            <td width="10%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="248" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:198px;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:198px;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %></td>
		   </tr>
  <%}}%>
               </table></td>
             </tr>
           </table></td>
         </tr>
         <tr>
           <td colspan="3">&nbsp;</td>
         </tr>
       </table></td>
     </tr>
     <%} %>
   </table></td></tr>
 </table>
 <div>
 <table>
  <tr>
   <td width="14%" height="30" align="center" bgcolor="#FFFFFF" >
    <input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return dateJudge();"> 
	<input type="reset" value=" 重置 " class="mmBtn"> 
	<input type="button" value=" 返回列表 "  class="mmBtn" onClick="back();">
   </td>
  </tr>
 </table>
</s:form>	
	</body>
</html>