<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
function res()
{
   document.getElementById('assetsStateid').value="";
   document.getElementById('assetsStatename').value="";
   document.getElementById('assetsChange.usersByChargeid.id').value="";
   document.getElementById('assetsChange.usersByChargeid.truename').value="";
   document.getElementById('assetsChange.usersByManagersid.id').value="";
   document.getElementById('assetsChange.usersByManagersid.truename').value="";
   document.getElementById('assetsChange.usersByUserid.id').value="";
   document.getElementById('assetsChange.usersByUserid.truename').value="";
   document.getElementById('assetsChange.changeTime').value="";
   document.getElementById('assetsChange.remark').value="";
}

function changeshow(id){
	var obj="";
   var result=window.showModalDialog("changeshow.action?id="+id,obj,"dialogWidth=400px;dialogHeight=380px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:yes;status:no;");
   if(result==1){
    document.form1.action="changequery.action";
    document.form1.method="post";
    document.form1.submit();
   }      
}

function back(){
   document.form1.action="list.action";
   document.form1.method="post";
   document.form1.submit();
}

function changepage(page){
document.form1.action="changequery.action?page="+page;
document.form1.method="post";
document.form1.submit();
}

function print(id){
window.open ('print.action?flag=1&assetsId='+id, '','height=120,width=220,top=1,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}


function Charge(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByChargeid.id").value=result.id;
document.getElementById("assetsChange.usersByChargeid.truename").value=result.name;
}
}
function Managers(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByManagersid.id").value=result.id;
document.getElementById("assetsChange.usersByManagersid.truename").value=result.name;
}
}
function Users(){
var obj1=null;
var result=window.showModalDialog("../utiltree/user.action",obj1,"dialogWidth=400px;dialogHeight=500px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
if(result!=null){
document.getElementById("assetsChange.usersByUserid.id").value=result.id;
document.getElementById("assetsChange.usersByUserid.truename").value=result.name;
}
}

function notshow(){
document.getElementById('assetsState').style.visibility='hidden';
}


function al(){
alert("此资产已删除！");
}
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onMouseDown="notshow();" >
 	<s:form action="/assets/changequery.action" method='post' theme="simple" name="form1">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产变更管理</td>
     </tr>
    </table>
   
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1" width="10%"> 变更类型： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
          <input type="hidden" name="assetsChange.assetsState.id" id="assetsStateid" value="<s:property value="assetsChange.assetsState.id"/>">
<input type="text" name="assetsChange.assetsState.name" id="assetsStatename" value="<s:property value="assetsChange.assetsState.name"/>" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="10%"> 责任人： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
<s:hidden id="assetsChange.usersByChargeid.id" name="assetsChange.usersByChargeid.id"/>
<s:textfield id="assetsChange.usersByChargeid.truename" name="assetsChange.usersByChargeid.truename" style="width:80%;" onclick="Charge();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Charge();">
          
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="10%"> 经办人： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
<s:hidden id="assetsChange.usersByManagersid.id" name="assetsChange.usersByManagersid.id"/>
<s:textfield id="assetsChange.usersByManagersid.truename" name="assetsChange.usersByManagersid.truename" style="width:80%;" onclick="Managers();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Managers();">
          
          
          
          </td>
          
       </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1" width="10%"> 使用人： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
<s:hidden id="assetsChange.usersByUserid.id" name="assetsChange.usersByUserid.id"/>
<s:textfield id="assetsChange.usersByUserid.truename" name="assetsChange.usersByUserid.truename" style="width:80%;" onclick="Users();"/>
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onclick="Users();">
          
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="10%"> 变更日期： </td>
          <td bgcolor="#FFFFFF" align="center" width="10%">
          <input type="text" id="assetsChange.changeTime" name="assetsChange.changeTime" class="Wdate" style="width:93%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='assetsChange.changeTime' format='yyyy-MM-dd'/>">
          </td>
          <td align="center" nowrap bgcolor="#deebf1" width="10%" colspan="2"> 
           <input type="submit" style="height: 20px" class="mmBtn"  value="搜索"/>
           <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/> 
           <input type="button" style="height: 20px" class="mmBtn"  value="返回" onClick="back();"/> </td>
          
         </tr>
        </table>
	   </s:form>
	   
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td height="22" align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产编号</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">变更类型</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">责任人</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">责任部门</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">经办人</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">使用人</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">使用部门</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">变更日期</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif"	class="alllisttitle">资产查看</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif"	class="alllisttitle">标签打印</td>
			 </tr>
			<s:iterator value="pageBean.list" var="assetsChange">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			  <td height="19" align="center" class="zczb_qua"><s:property value="assetsBase.codeId" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsState.name" /></td>
          	  <td align="center" class="zczb_qua"><s:property value="usersByChargeid.truename" /></td>
          	  <td align="center" class="zczb_qua"><s:property value="usersByChargeid.department.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="usersByManagersid.truename" /></td>
			  <td align="center" class="zczb_qua"><s:property value="usersByUserid.truename" /></td>
			  <td align="center" class="zczb_qua"><s:property value="usersByUserid.department.name" /></td>
			  <td align="center" class="zczb_qua"><s:date name='changeTime' format='yyyy-MM-dd'/></td>
			  <td align="center" class="zczb_qua">
			  <s:if test="assetsState.id!=0">
			   <img src="../images/edt.gif">
				<a href="#" onClick="changeshow(<s:property value="id"/>);">修改</a>
			  </s:if>
			  <s:if test="assetsState.id==0">
			   <img src="../images/edt.gif">
				<a href="#" onClick="al();">修改</a>
			  </s:if>
			  </td>
			  <td align="center" class="zczb_qua">
				<a href="changebaseshow.action?assetsId=<s:property value="assetsBase.code" />" >查看</a>
			  </td>
			  <td align="center" class="zczb_qua">
				<a href="#" onClick="print(<s:property value="id" />);">标签打印</a>
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
