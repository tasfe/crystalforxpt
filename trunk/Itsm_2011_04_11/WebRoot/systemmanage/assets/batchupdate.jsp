<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ page import="com.combanc.itsm.pojo.AssetsConfig"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产新增修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<base target="_self">
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
document.location.href="batch.action";
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

    function removedataFile(id,ids,assetsId){
      var msg="确认删除附件吗？";
      if (confirm(msg) == true){
        var new_tr = id.parentNode.parentNode;
　　           try {
　　               var tmp = new_tr.parentNode;
　　              tmp.removeChild(new_tr);
　　           }catch(e) {}
        document.location.href="deleteFile.action?ids="+ids+"&assetsId="+assetsId;
      }
    }
    function download(id){
      document.location.href="downloads.action?ids="+id;
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
document.getElementById("assets.usersByUsersId.department.name").value=result.department;
}
}
function Charge(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("usersByChargeId0").value=result.id;
document.getElementById("usersByChargeId0.name").value=result.name;
document.getElementById("assets.usersByChargeId.department.name").value=result.department;
}
}
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();" onMouseDown="notshow();">
<s:form action="/assets/batchupdate.action" method="post" theme="simple" name="form1" enctype="multipart/form-data">
<input type="hidden" name="id" id="id" value="<s:property value="id"/>">

<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="assetsType.name"/>新增资产</td>
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
               <td valign="top" bgcolor="#EBF4FD">
               <table cellspacing=0 cellpadding=0 border=0 width="100%" class="zcxx_tab3">
                 <tr>
                   <td rowspan="2" align="right"><font class="zc_add">*</font>资产编号：</td>
                   <td rowspan="2" align="left"><textarea id="codeId0" name="codeId0" disabled readonly style="width:205px;height:40px; border:#C9C7BA 1px solid;" ><s:property value="codeIds"/></textarea></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right"><font class="zc_add">*</font>资产名称：</td>
                   <td  height="30" align="left"><input type="text" id="name0" name="name0" value="<s:property value="assets.name"/>" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right">资产型号：</td>
                   <td  height="30" align="left"><input type="text" id="model0" name="model0" value="<s:property value="assets.model"/>" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right">*资产类别：</td>
                   <td height="30"  align="left" width="20%"><input type="hidden" name="assetsType0" id="assets.assetsType.id" value="<s:property value="assetsType.id" />">
                   <input type="text" disabled readonly value="<s:property value="assetsType.name" />" style="width:85%;height=18;" ></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td height="30" align="right">运维类别：</td>
                   <td height="30" align="left">
<input type="hidden" id="itsmTypeid" name="itsmType0">
<input type="text" name="itsmType.name" id="itsmTypename" onClick="document.getElementById('itsmType').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('itsmType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="itsmType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/itsmtype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                  </td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right"><font class="zc_add">*</font>资产状态：</td>
                   <td width="20%" height="30" align="left">
   <input type="hidden" name="assetsState0" id="assetsStateid">
<input type="text" name="assetsState.name" id="assetsStatename" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">保修年限(月)：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="qualityTime0" name="qualityTime0"  value="<s:property value="assets.qualityTime"/>" style="width:85%;height=18;"></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">资产单价：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="price0" name="price0" value="<s:property value="assets.price"/>" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">采购日期：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="buyDate0" name="buyDate0" value="<s:date name='assets.buyDate' format='yyyy-MM-dd'/>" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">出厂日期：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="exitfacotryDate0" name="exitfacotryDate0" value="<s:date name='assets.exitfacotryDate' format='yyyy-MM-dd'/>" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">*入库日期：</td>
                   <td width="20%" height="30" align="left"><input type="text" disabled id="inDate0" name="inDate0" value="入库时间为扫描时间" style="width:85%;height=18;"></td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">地理区域：</td>
                   <td width="20%" height="30" align="left">
                   <input type="hidden" name="location0" id="locationid">
<input type="text" name="location.name" id="locationname" onClick="document.getElementById('location').style.visibility='visible'" readonly  style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('location').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="location"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                       <tr>
                         <td></td>
                       </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">存放位置：</td>
                   <td width="20%" height="30" align="left">
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
  for(int i=0;i<list1.size();i++){
  AssetsConfig assetsConfig=(AssetsConfig)list1.get(i);
  if(assetsConfig.getConfigColumnName().indexOf("remark")>=0||assetsConfig.getConfigColumnName().equals("mac")||assetsConfig.getConfigColumnName().equals("devicename")||assetsConfig.getConfigColumnName().equals("ip")||assetsConfig.getConfigColumnName().equals("system")){
  if((i+1)%3==0){%>
         <tr>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
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
  <%}else if((i+1)%3==1){%>
            <td width="14%" height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
            
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
  <%}else if((i+1)%3==2){%>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>0" name="<%=assetsConfig.getConfigColumnName() %>0" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            </td>
		   </tr>
  <%}}}%>
                 
                 
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
                           <s:iterator value="accessoryList" var="accessory">
                           <div style="background-color:#E7EBF7">
                           <font size="2" style="width:300px"><a href="#" onClick="download(<s:property value="id"/>);"><s:property value="trueName" /></a></font>
                           <a href="#"><img onClick="removedataFile(this,<s:property value="id"/>,<s:property value="tableId"/>)" src="../img/del2.gif" border="0" alt="删除附件"/></a>
                           </div>
                           </s:iterator>
                           <div id="_file"></div><br/>
                          </td>
                       </tr>
                       <tr>
                         <td width="1%" bgcolor="#EBF4F5" nowrap>&nbsp;选择文件：</td>
                         <td width="99%" bgcolor="#EBF4F5" style="padding-top: 1px; padding-bottom: 2px">
                          <div id="input">
                           <input name="file" id="file0" type="file" value="添加附件" onChange="addFile()" />
                           <font size="1.8" color="red">如果添加多个附件，请继续点击“浏览”</font> 
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
                   
                   <td width="116" height="30" align="right" >供应商：</td>
                   <td width="24%" height="30" align="left" >
                   <input type="hidden" name="assetsProducerBySupportId0" id="supportid">
<input type="text" name="support.name" id="supportname" onClick="document.getElementById('support').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('support').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="support"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/support.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right" >制造商：</td>
                   <td width="24%" height="30" align="left" >
                   <input type="hidden" name="assetsProducerByProduceId0" id="producerid">
<input type="text" name="producer.name" id="producername" onClick="document.getElementById('producer').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('producer').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="producer"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/producer.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>                   </td>
                   <td width="3%" align="left" >&nbsp;</td>
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
                
                  <s:if test="%{assets.code!=null}">
                   <td width="7%" height="30" align="right" ><font class="zc_add">*</font>责任人：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="hidden" id="usersByChargeId0" name="usersByChargeId0" value="<s:property value='assets.usersByChargeId.id'/>">
                   <input type="text" id="usersByChargeId0.name" name="assets.usersByChargeId.truename" style="width:80%;" value="<s:property value='assets.usersByChargeId.truename'/>" onClick="Charge();">
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="Charge();">                   </td>
                   </s:if>
                   <s:else>
                   <td width="7%" height="30" align="right" ><font class="zc_add">*</font>责任人：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="hidden" id="usersByChargeId0" name="usersByChargeId0" value="0">
                   <input type="text" id="usersByChargeId0.name" name="assets.usersByChargeId.truename" style="width:80%;" value="如不填写，负责人为PDA登陆人" onClick="Charge();">
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="Charge();">                   </td>
                   </s:else>
                   
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   
                   <s:if test="%{assets.code!=null}">
                   <td width="7%" height="30" align="right" >责任部门：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="text" readonly id="assets.usersByChargeId.department.name" name="assets.usersByChargeId.department.name" value="<s:property value='assets.usersByChargeId.department.name'/>" style="width:80%;"></td>
                   </s:if>
                   <s:else>
                   <td width="7%" height="30" align="right" >责任部门：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="text" readonly id="assets.usersByChargeId.department.name" name="assets.usersByChargeId.department.name" value="责任部门为登陆人所在部门" style="width:80%;"></td>
                   </s:else>



                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="7%" height="30" align="right" >使用人：</td>
                   <td width="15%" height="30" align="left" >
                   <s:hidden id="usersByUsersId0" name="usersByUsersId0" />
                   <s:textfield id="usersByUsersId0.name" name="assets.usersByUsersId.truename" style="width:80%;" onclick="user();"/>
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="user();">                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                  
                  <td width="7%" height="30" align="right" >使用部门：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="text" readonly  id="assets.usersByUsersId.department.name" name="assets.usersByUsersId.department.name" style="width:80%;"></td>
                  
                  
                   
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
     <input type="hidden"  name="id" id="id" value="<s:property value="info.id"/>">
     <%List lists=(List)ActionContext.getContext().getSession().get("assetsConfigInfoList");
     if(lists.size()!=0){ %>
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
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>	
  <%}else if(i%3==1){%>
            <td width="14%" height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
            
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            
            </td>
            <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
            <tr>
             <td></td>
            </tr>
            </table></td>
  <%}else if(i%3==2){%>
            <td width="14%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="20%" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>
            </td>
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
	<input type="button" value=" 返回列表 "  class="mmBtn" onClick="back();">
   </td>
  </tr>
 </table>

</s:form>
	</body>
</html>
