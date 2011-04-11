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
	
	if(document.getElementById("assetsTypeid")){	
		var assetstypeDepart=document.getElementById("assetsTypeid").value; 
		if(assetstypeDepart<=0){
			alert("请选择资产类型！");
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
document.location.href="list.action";
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
document.getElementById('assetsType').style.visibility='hidden';
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
</script>

	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" border="10" marginheight="0" onLoad="init();" onMouseDown="notshow();">
<s:form action="/assets/update.action" method="post" theme="simple" name="form1" enctype="multipart/form-data">
<input type="hidden" name="code0" id="code0" value="<s:property value="assets.code"/>">
<table width="98%" cellspacing="1" align="center" bgcolor="#b5d6e6">
 <tr>
 <td align="center" bgcolor="#deebf1" height="22" background="../../images/main20100521_58.gif" class="alllisttitle"><s:property value="assets.name"/></td>
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
                   <td  height="30" align="left"><input type="text" id="codeId0" name="codeId0" value="<s:property value="assets.codeId"/>" style="width:85%;height=18;"></td>
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
                   <td height="30" align="right"><font class="zc_add">*</font>资产类别：</td>
                   <td height="30" align="left">
<input type="hidden" name="assetsType0" id="assetsTypeid" value="<s:property value="assets.assetsType.id" />">
<input type="text" name="assetsType.name" id="assetsTypename" onClick="document.getElementById('assetsType').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td height="30" align="right">运维类别：</td>
                   <td height="30"  align="left" width="20%">
<input type="hidden" id="itsmTypeid" name="itsmType0" value="<s:property value="assets.itsmType.id"/>">
<input type="text" name="itsmType.name" id="itsmTypename" onClick="document.getElementById('itsmType').style.visibility='visible'" readonly value="<s:property value="assets.itsmType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('itsmType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="itsmType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/itsmtype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
</td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td height="30" align="right"><font class="zc_add">*</font>资产状态：</td>
                   <td height="30" align="left">
<input type="hidden" id="assetsStateid" name="assetsState0" value="<s:property value="assets.assetsState.id"/>">
<input type="text" name="assetsState.name" id="assetsStatename" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly value="<s:property value="assets.assetsState.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                  </td>
                 </tr>
                 <tr>
                   <td width="14%" height="30" align="right">保修年限(月)：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="qualityTime0" name="qualityTime0"  value="<s:property value="assets.qualityTime"/>" style="width:85%;height=18;"></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">资产单价：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="price0" name="price0" value="<s:property value="assets.price"/>" style="width:85%;height=18;"></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">采购日期：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="buyDate0" name="buyDate0" value="<s:date name='assets.buyDate' format='yyyy-MM-dd'/>" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   </tr>
                 <tr>
                   
                   <td width="14%" height="30" align="right">出厂日期：</td>
                   <td width="16%" height="30" align="left"><input type="text" id="exitfacotryDate0" name="exitfacotryDate0" value="<s:date name='assets.exitfacotryDate' format='yyyy-MM-dd'/>" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                 
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">入库日期：</td>
                   <td width="20%" height="30" align="left"><input type="text" id="inDate0" name="inDate0" value="<s:date name='assets.inDate' format='yyyy-MM-dd'/>" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="14%" height="30" align="right">地理区域：</td>
                   <td width="20%" height="30" align="left">
<input type="hidden" id="locationid" name="location0" value="<s:property value="assets.location.id"/>">
<input type="text" name="location.name" id="locationname" onClick="document.getElementById('location').style.visibility='visible'" readonly value="<s:property value="assets.location.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('location').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="location"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/location.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                 </td>
                </tr>
                <tr>  
                  <td height="30" align="right">存放位置：</td>
                   <td height="30" align="left">

<input type="hidden" name="buildlocation0" id="buildlocationid" value="<s:property value='assets.buildlocation.id'/>">
<input type="text" name="buildlocation.name" id="buildlocationname" onClick="document.getElementById('buildlocation').style.visibility='visible'" readonly value="<s:property value="assets.buildlocation.allname" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('buildlocation').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="buildlocation"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/buildlocation.action" style="border: 1px solid #E5E9EE;"></iframe>
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
  if(i%3==0){%>
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
  <%}else if(i%3==1){%>
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
  <%}else if(i%3==2){%>
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
                   
                   <td width="254" height="30" align="right" >供应商：</td>
                   <td width="302" height="30" align="left" >
<input type="hidden" name="assetsProducerBySupportId0" id="supportid" value="<s:property value='assets.assetsProducerBySupportId.id'/>">
<input type="text" name="support.name" id="supportname" onClick="document.getElementById('support').style.visibility='visible'" readonly value="<s:property value="assets.assetsProducerBySupportId.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('support').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="support"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/support.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div> 
                   
                   </td>
                   <td width="110"><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="130" height="30" align="right" >制造商：</td>
                   <td width="299" height="30" align="left" >
<input type="hidden" name="assetsProducerByProduceId0" id="producerid" value="<s:property value='assets.assetsProducerByProduceId.id'/>">
<input type="text" name="producer.name" id="producername" onClick="document.getElementById('producer').style.visibility='visible'" readonly value="<s:property value="assets.assetsProducerByProduceId.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('producer').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="producer"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/producer.action" style="border: 1px solid #E5E9EE;"></iframe>
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
                   <td width="15%" height="30" align="left">
                   <input type="hidden" id="usersByChargeId0" name="usersByChargeId0" value="<s:property value="assets.usersByChargeId.id"/>">
                   <s:textfield id="usersByChargeId0.name" name="assets.usersByChargeId.truename" style="width:80%;" onclick="Charge();"/>
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="Charge();"></td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="10%" height="30" align="right" >责任部门：</td>
                   <td width="15%" height="30" align="left">
                   <input type="text" id="usersByUsersId0.department" name="usersByUsersId0.department" readonly style="width:80%;" value="<s:property value="assets.usersByChargeId.department.name"/>">                   </td>
                   
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   <td width="10%" height="30" align="right" >使用人：</td>
                   <td width="15%" height="30" align="left" >
                   <input type="hidden" id="usersByUsersId0" name="usersByUsersId0" value="<s:property value="assets.usersByUsersId.id"/>">
                   <s:textfield id="usersByUsersId0.name" name="assets.usersByUsersId.truename" style="width:80%;" onclick="user();"/>
                   <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="user();">                   </td>
                   <td><table width="1" border="0" cellspacing="0" cellpadding="0" bgcolor="#C0D6EE" height="99%" align="center">
                     <tr>
                       <td></td>
                     </tr>
                   </table></td>
                   
                   <td width="10%" height="30" align="right" >使用部门：</td>
                   <td width="15%" height="30" align="left">
                   <input type="text" id="usersByUsersId0.department" name="usersByUsersId0.department" readonly style="width:80%;" value="<s:property value="assets.usersByUsersId.department.name"/>">                   </td>
                   
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
            <td width="10%" height="30" align="right" ><%=assetsConfig.getConfigName().trim()%>：</td>
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
  <%}else if(i%3==2){%>
            <td width="10%" height="30" align="right"><%=assetsConfig.getConfigName().trim()%>：</td>
            <td width="24%" height="30" align="left">
          <%if(assetsConfig.getConfigStats()=="1"||assetsConfig.getConfigStats().equals("1")){%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;"> 
		  <%}else if(assetsConfig.getConfigStats()=="2"||assetsConfig.getConfigStats().equals("2")) {%>
		     <input type="text" id="<%=assetsConfig.getConfigColumnName() %>1" name="<%=assetsConfig.getConfigColumnName() %>1" style="width:85%;height=18;" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
		  <%} %>            </td>
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
 <table width="100%">
  <tr>
   <td width="100%" height="30" align="center" bgcolor="#FFFFFF" >
    <input type="submit" value=" 保存 " class="mmBtn" onClick="javascript:return dateJudge();"> 
	<input type="button" value=" 返回列表 "  class="mmBtn" onClick="back();">
   </td>
  </tr>
 </table>

</s:form>


<input type="hidden" name="system2" id="system2" value="<s:property value="assets.system"/>"/>
<input type="hidden" name="ip2" id="ip2" value="<s:property value="assets.ip"/>"/>
<input type="hidden" name="mac2" id="mac2" value="<s:property value="assets.mac"/>"/>
<input type="hidden" name="devicename2" id="devicename2" value="<s:property value="assets.devicename"/>"/>
<input type="hidden" name="remark12" id="remark12" value="<s:property value="assets.remark1"/>"/>
<input type="hidden" name="remark22" id="remark22" value="<s:property value="assets.remark2"/>"/>
<input type="hidden" name="remark32" id="remark32" value="<s:property value="assets.remark3"/>"/>
<input type="hidden" name="remark42" id="remark42" value="<s:property value="assets.remark4"/>"/>
<input type="hidden" name="remark52" id="remark52" value="<s:property value="assets.remark5"/>"/>
<input type="hidden" name="remark62" id="remark62" value="<s:property value="assets.remark6"/>"/>
<input type="hidden" name="remark72" id="remark72" value="<s:property value="assets.remark7"/>"/>
<input type="hidden" name="remark82" id="remark82" value="<s:property value="assets.remark8"/>"/>
<input type="hidden" name="remark92" id="remark92" value="<s:property value="assets.remark9"/>"/>
<input type="hidden" name="remark102" id="remark102" value="<s:property value="assets.remark10"/>"/>
<input type="hidden" name="remark112" id="remark112" value="<s:property value="assets.remark11"/>"/>
<input type="hidden" name="remark122" id="remark122" value="<s:property value="assets.remark12"/>"/>
<input type="hidden" name="remark132" id="remark132" value="<s:property value="assets.remark13"/>"/>
<input type="hidden" name="remark142" id="remark142" value="<s:property value="assets.remark14"/>"/>
<input type="hidden" name="remark152" id="remark152" value="<s:property value="assets.remark15"/>"/>
<input type="hidden" name="remark162" id="remark162" value="<s:property value="assets.remark16"/>"/>
<input type="hidden" name="remark172" id="remark172" value="<s:property value="assets.remark17"/>"/>
<input type="hidden" name="remark182" id="remark182" value="<s:property value="assets.remark18"/>"/>
<input type="hidden" name="remark192" id="remark192" value="<s:property value="assets.remark19"/>"/>
<input type="hidden" name="remark202" id="remark202" value="<s:property value="assets.remark20"/>"/>
<input type="hidden" name="remark212" id="remark212" value="<s:property value="assets.remark21"/>"/>
<input type="hidden" name="remark222" id="remark222" value="<s:property value="assets.remark22"/>"/>
<input type="hidden" name="remark232" id="remark232" value="<s:property value="assets.remark23"/>"/>
<input type="hidden" name="remark242" id="remark242" value="<s:property value="assets.remark24"/>"/>
<input type="hidden" name="remark252" id="remark252" value="<s:property value="assets.remark25"/>"/>
<input type="hidden" name="remark262" id="remark262" value="<s:property value="assets.remark26"/>"/>
<input type="hidden" name="remark272" id="remark272" value="<s:property value="assets.remark27"/>"/>
<input type="hidden" name="remark282" id="remark282" value="<s:property value="assets.remark28"/>"/>
<input type="hidden" name="remark292" id="remark292" value="<s:property value="assets.remark29"/>"/>
<input type="hidden" name="remark302" id="remark302" value="<s:property value="assets.remark30"/>"/>


<input type="hidden" name="remark13" id="remark13" value="<s:property value="info.remark1"/>"/>
<input type="hidden" name="remark23" id="remark23" value="<s:property value="info.remark2"/>"/>
<input type="hidden" name="remark33" id="remark33" value="<s:property value="info.remark3"/>"/>
<input type="hidden" name="remark43" id="remark43" value="<s:property value="info.remark4"/>"/>
<input type="hidden" name="remark53" id="remark53" value="<s:property value="info.remark5"/>"/>
<input type="hidden" name="remark63" id="remark63" value="<s:property value="info.remark6"/>"/>
<input type="hidden" name="remark73" id="remark73" value="<s:property value="info.remark7"/>"/>
<input type="hidden" name="remark83" id="remark83" value="<s:property value="info.remark8"/>"/>
<input type="hidden" name="remark93" id="remark93" value="<s:property value="info.remark9"/>"/>
<input type="hidden" name="remark103" id="remark103" value="<s:property value="info.remark10"/>"/>
<input type="hidden" name="remark113" id="remark113" value="<s:property value="info.remark11"/>"/>
<input type="hidden" name="remark123" id="remark123" value="<s:property value="info.remark12"/>"/>
<input type="hidden" name="remark133" id="remark133" value="<s:property value="info.remark13"/>"/>
<input type="hidden" name="remark143" id="remark143" value="<s:property value="info.remark14"/>"/>
<input type="hidden" name="remark153" id="remark153" value="<s:property value="info.remark15"/>"/>
<input type="hidden" name="remark163" id="remark163" value="<s:property value="info.remark16"/>"/>
<input type="hidden" name="remark173" id="remark173" value="<s:property value="info.remark17"/>"/>
<input type="hidden" name="remark183" id="remark183" value="<s:property value="info.remark18"/>"/>
<input type="hidden" name="remark193" id="remark193" value="<s:property value="info.remark19"/>"/>
<input type="hidden" name="remark203" id="remark203" value="<s:property value="info.remark20"/>"/>
<script language='javascript'>
if(document.getElementById("system0")){
  document.getElementById("system0").value=document.getElementById("system2").value;
}
if(document.getElementById("ip0")){
  document.getElementById("ip0").value=document.getElementById("ip2").value;
}
if(document.getElementById("mac0")){
  document.getElementById("mac0").value=document.getElementById("mac2").value;
}
if(document.getElementById("devicename0")){
  document.getElementById("devicename0").value=document.getElementById("devicename2").value;
}

if(document.getElementById("remark10")){
 document.getElementById("remark10").value=document.getElementById("remark12").value;
}
if(document.getElementById("remark20")){
 document.getElementById("remark20").value=document.getElementById("remark22").value;
}
if(document.getElementById("remark30")){
 document.getElementById("remark30").value=document.getElementById("remark32").value;
}
if(document.getElementById("remark40")){
 document.getElementById("remark40").value=document.getElementById("remark42").value;
}
if(document.getElementById("remark50")){
 document.getElementById("remark50").value=document.getElementById("remark52").value;
}
if(document.getElementById("remark60")){
 document.getElementById("remark60").value=document.getElementById("remark62").value;
}
if(document.getElementById("remark70")){
 document.getElementById("remark70").value=document.getElementById("remark72").value;
}
if(document.getElementById("remark80")){
 document.getElementById("remark80").value=document.getElementById("remark82").value;
}
if(document.getElementById("remark90")){
 document.getElementById("remark90").value=document.getElementById("remark92").value;
}
if(document.getElementById("remark100")){
 document.getElementById("remark100").value=document.getElementById("remark102").value;
}
if(document.getElementById("remark110")){
 document.getElementById("remark110").value=document.getElementById("remark112").value;
}
if(document.getElementById("remark120")){
 document.getElementById("remark120").value=document.getElementById("remark122").value;
}
if(document.getElementById("remark130")){
 document.getElementById("remark130").value=document.getElementById("remark132").value;
}
if(document.getElementById("remark140")){
 document.getElementById("remark140").value=document.getElementById("remark142").value;
}
if(document.getElementById("remark150")){
 document.getElementById("remark150").value=document.getElementById("remark152").value;
}
if(document.getElementById("remark160")){
 document.getElementById("remark160").value=document.getElementById("remark162").value;
}
if(document.getElementById("remark170")){
 document.getElementById("remark170").value=document.getElementById("remark172").value;
}
if(document.getElementById("remark180")){
 document.getElementById("remark180").value=document.getElementById("remark182").value;
}
if(document.getElementById("remark190")){
 document.getElementById("remark190").value=document.getElementById("remark192").value;
}
if(document.getElementById("remark200")){
 document.getElementById("remark200").value=document.getElementById("remark202").value;
}
if(document.getElementById("remark210")){
 document.getElementById("remark210").value=document.getElementById("remark212").value;
}
if(document.getElementById("remark220")){
 document.getElementById("remark220").value=document.getElementById("remark222").value;
}
if(document.getElementById("remark230")){
 document.getElementById("remark230").value=document.getElementById("remark232").value;
}
if(document.getElementById("remark240")){
 document.getElementById("remark240").value=document.getElementById("remark242").value;
}
if(document.getElementById("remark250")){
 document.getElementById("remark250").value=document.getElementById("remark252").value;
}
if(document.getElementById("remark260")){
 document.getElementById("remark260").value=document.getElementById("remark262").value;
}
if(document.getElementById("remark270")){
 document.getElementById("remark270").value=document.getElementById("remark272").value;
}
if(document.getElementById("remark280")){
 document.getElementById("remark280").value=document.getElementById("remark282").value;
}
if(document.getElementById("remark290")){
 document.getElementById("remark290").value=document.getElementById("remark292").value;
}
if(document.getElementById("remark300")){
 document.getElementById("remark300").value=document.getElementById("remark302").value;
}


if(document.getElementById("remark11")){
 document.getElementById("remark11").value=document.getElementById("remark13").value;
}
if(document.getElementById("remark21")){
 document.getElementById("remark21").value=document.getElementById("remark23").value;
}
if(document.getElementById("remark31")){
 document.getElementById("remark31").value=document.getElementById("remark33").value;
}
if(document.getElementById("remark41")){
 document.getElementById("remark41").value=document.getElementById("remark43").value;
}
if(document.getElementById("remark51")){
 document.getElementById("remark51").value=document.getElementById("remark53").value;
}
if(document.getElementById("remark61")){
 document.getElementById("remark61").value=document.getElementById("remark63").value;
}
if(document.getElementById("remark71")){
 document.getElementById("remark71").value=document.getElementById("remark73").value;
}
if(document.getElementById("remark81")){
 document.getElementById("remark81").value=document.getElementById("remark83").value;
}
if(document.getElementById("remark91")){
 document.getElementById("remark91").value=document.getElementById("remark93").value;
}
if(document.getElementById("remark101")){
 document.getElementById("remark101").value=document.getElementById("remark103").value;
}
if(document.getElementById("remark111")){
 document.getElementById("remark111").value=document.getElementById("remark113").value;
}
if(document.getElementById("remark121")){
 document.getElementById("remark121").value=document.getElementById("remark123").value;
}
if(document.getElementById("remark131")){
 document.getElementById("remark131").value=document.getElementById("remark133").value;
}
if(document.getElementById("remark141")){
 document.getElementById("remark141").value=document.getElementById("remark143").value;
}
if(document.getElementById("remark151")){
 document.getElementById("remark151").value=document.getElementById("remark153").value;
}
if(document.getElementById("remark161")){
 document.getElementById("remark161").value=document.getElementById("remark163").value;
}
if(document.getElementById("remark171")){
 document.getElementById("remark171").value=document.getElementById("remark173").value;
}
if(document.getElementById("remark181")){
 document.getElementById("remark181").value=document.getElementById("remark183").value;
}
if(document.getElementById("remark191")){
 document.getElementById("remark191").value=document.getElementById("remark193").value;
}
if(document.getElementById("remark201")){
 document.getElementById("remark201").value=document.getElementById("remark203").value;
}
</script>	
	</body>
</html>