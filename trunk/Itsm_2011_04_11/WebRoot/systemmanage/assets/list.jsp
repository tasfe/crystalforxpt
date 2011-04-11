<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">    
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">    
        <META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<script type="text/javascript" src="../js/util.js"></script>
		<script type="text/javascript">
function res()
{
   document.getElementById('assets.name').value="";
   document.getElementById('assets.codeId').value="";
   document.getElementById('assets.model').value="";
   document.getElementById('assetsTypeid').value="";
   document.getElementById('assetsTypename').value="";
   document.getElementById('supportid').value="";
   document.getElementById('supportname').value="";
   document.getElementById('producerid').value="";
   document.getElementById('producername').value="";
   document.getElementById('assetsStateid').value="";
   document.getElementById('assetsStatename').value="";
   document.getElementById('buildlocationid').value="";
   document.getElementById('buildlocationname').value="";
   document.getElementById('departmentid').value="";
   document.getElementById('departmentname').value="";
   document.getElementById('departmentid1').value="";
   document.getElementById('departmentname1').value="";
   document.getElementById('assets.usersByUsersId.id').value="";
   document.getElementById('assets.usersByUsersId.truename').value="";
   document.getElementById('assets.usersByChargeId.id').value="";
   document.getElementById('assets.usersByChargeId.truename').value="";
}
function del(){   
   var msg="确认删除记录吗？";
   if (confirm(msg) == true){
   return true;
   }else{
   return false;
   }   
}
function toExcelchoose(){
   document.form1.action="toExcelchoose.action";
   document.form1.method="post";
   document.form1.submit();
}
function checkAll(argu){
      var obj = document.getElementsByName("code");
     
         if(argu.checked){
         for(var i= 0;i<obj.length;i++){
              obj[i].checked = true;
            }
         }else{
         for(var i= 0;i<obj.length;i++){
              obj[i].checked = false;
            }
         }
}

        function change(id){
         var obj = new Object(id);
         var result=window.showModalDialog("../systemmanage/assets/change.jsp",obj,"dialogWidth=400px;dialogHeight=380px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
         if(result==1){
           document.form1.action="list.action";
           document.form1.method="post";
           document.form1.submit();
         }
        }

function changes(){
      var obj = document.getElementsByName("code");
      var j=0;
      var str="";
      for(var i= 0;i<obj.length;i++){
         if(obj[i].checked==true){
         str+=obj[i].value+",";
         j++;
         }
      }
      var obj1=new Object(str);
      if(j>0){
         var result=window.showModalDialog("../systemmanage/assets/change.jsp",obj1,"dialogWidth=400px;dialogHeight=380px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
         if(result==1){
            document.form1.action="list.action";
            document.form1.method="post";
            document.form1.submit();
         }
      }else{
        alert("请选择资产！！！");
      }
}


function changequery(){
   document.form1.action="changequery.action";
   document.form1.method="post";
   document.form1.submit();
}

function query(){
   document.form1.action="list.action";
   document.form1.method="post";
   document.form1.submit();
}
function changepage(page){
document.form1.action="list.action?page="+page;
document.form1.method="post";
document.form1.submit();
}

function print(id){
window.open ('print.action?flag=0&assetsId='+id, '','height=130,width=200,top=1,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
function notshow(){
document.getElementById('buildlocation').style.visibility='hidden';
document.getElementById('assetsType').style.visibility='hidden';
document.getElementById('assetsState').style.visibility='hidden';
document.getElementById('department').style.visibility='hidden';
document.getElementById('department1').style.visibility='hidden';
document.getElementById('support').style.visibility='hidden';
document.getElementById('producer').style.visibility='hidden';

}

function Device(){
   document.form1.action="device.action";
   document.form1.method="post";
   document.form1.submit();
}
function Computer(){
   document.form1.action="computer.action";
   document.form1.method="post";
   document.form1.submit();
}

function selectUser(){
var win = new UserSelectWindow({
accepted:function(user){
document.getElementById('assets.usersByChargeId.truename').value=user.name;
document.getElementById('assets.usersByChargeId.id').value=user.id;
}
});
win.show();
}

function selectUser1(){
var win = new UserSelectWindow({
accepted:function(user){
document.getElementById('assets.usersByUsersId.truename').value=user.name;
document.getElementById('assets.usersByUsersId.id').value=user.id;
}
});
win.show();
}
</script>
	</head>

	<body style="overflow:auto;" onMouseDown="notshow();" >
	<s:form action="" method='post' theme="simple" name="form1">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产管理</td>
     </tr>
    </table>
   
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产名称： </td>
          <td bgcolor="#FFFFFF" align="left" width="17%" style="padding-left:5px;padding-right:5px;"><s:textfield id="assets.name" name="assets.name" style="width:98%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产编号： </td>
          <td bgcolor="#FFFFFF" align="left" width="17%" style="padding-left:5px;padding-right:5px;"><s:textfield id="assets.codeId" name="assets.codeId" style="width:98%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">资产型号：</td>
          <td bgcolor="#FFFFFF" align="left" width="17%" style="padding-left:5px;padding-right:5px;"><s:textfield id="assets.model" name="assets.model" style="width:98%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产类别： </td>
          <td width="18%" align="left" bgcolor="#FFFFFF" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsType.id" id="assetsTypeid" value="<s:property value="assets.assetsType.id"/>">
<input type="text" name="assets.assetsType.name" id="assetsTypename" value="<s:property value="assets.assetsType.name"/>" onClick="document.getElementById('assetsType').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsType').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsType"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>


          </td>
         </tr>
       <tr>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 供应商： </td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsProducerBySupportId.id" id="supportid" value="<s:property value="assets.assetsProducerBySupportId.id"/>">
<input type="text" name="assets.assetsProducerBySupportId.name" id="supportname" value="<s:property value="assets.assetsProducerBySupportId.name"/>" onClick="document.getElementById('support').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('support').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="support"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/support.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

         </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 制造商： </td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsProducerByProduceId.id" id="producerid" value="<s:property value="assets.assetsProducerByProduceId.id"/>">
<input type="text" name="assets.assetsProducerByProduceId.name" id="producername" value="<s:property value="assets.assetsProducerByProduceId.name"/>" onClick="document.getElementById('producer').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('producer').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="producer"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/producer.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
        
         </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 资产状态： </td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">
<input type="hidden" name="assets.assetsState.id" id="assetsStateid" value="<s:property value="assets.assetsState.id"/>">
<input type="text" name="assets.assetsState.name" id="assetsStatename" value="<s:property value="assets.assetsState.name"/>" onClick="document.getElementById('assetsState').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('assetsState').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="assetsState"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetsState.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
         
         </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">存放位置：</td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">
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
         
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 责任人： </td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;">
		  <s:hidden id="assets.usersByChargeId.id" name="assets.usersByChargeId.id"></s:hidden>
		  <input id="users" type="text" name="assets.usersByChargeId.truename" style="width: 83%;" readonly  onClick="javascript:selectUser();" value="<s:property value="assets.usersByChargeId.truename"/>">
          <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser();"><br>
          </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%">责任部门：</td>
         <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">

<input type="hidden" name="assets.usersByChargeId.department.id" id="departmentid" value="<s:property value="assets.usersByChargeId.department.id"/>">
<input type="text" name="assets.usersByChargeId.department.name" id="departmentname" value="<s:property value="assets.usersByChargeId.department.name"/>" onClick="document.getElementById('department').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="department"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

         </td>
          <td align="right" nowrap bgcolor="#deebf1" width="8%"> 使用人： </td>
          <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">
          <s:hidden id="assets.usersByUsersId.id" name="assets.usersByUsersId.id"/>
          <input id="users1" type="text" name="assets.usersByUsersId.truename" style="width: 85%;" readonly  onClick="javascript:selectUser1();" value="<s:property value="assets.usersByUsersId.truename"/>">
          <img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="javascript:selectUser1();"><br>
          </td>
          
          <td align="right" nowrap bgcolor="#deebf1" width="8%">使用部门：</td>
         <td bgcolor="#FFFFFF" align="left" style="padding-left:5px;padding-right:5px;">

<input type="hidden" name="assets.usersByUsersId.department.id" id="departmentid1" value="<s:property value="assets.usersByUsersId.department.id"/>">
<input type="text" name="assets.usersByUsersId.department.name" id="departmentname1" value="<s:property value="assets.usersByUsersId.department.name"/>" onClick="document.getElementById('department1').style.visibility='visible'" readonly style="width:85%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('department1').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;">
<div id="department1"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department1.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

         </td>
          </tr>
                  
          <tr> 
          <td bgcolor="#FFFFFF" align="center" colspan="8"><input type="button" style="height: 20px" class="mmBtn"  value="搜索" onClick="query();"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/></td>
         </tr>
         
        </table>
	   </s:form>
	   
	   
	   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
				<td height="30" align="left">
				<tags:button code="changes" menu="5">
				  <input type="button" class="mmBtn" value="资产变更" onClick="changes();">
				</tags:button>
				<tags:button code="changeselect" menu="5">
				  <input type="button" class="mmBtn" value="资产变更查询" onClick="changequery();">
				</tags:button>  
				</td>
				<td height="30" align="center">
				
				  <input type="button" class="mmBtn" value="网络设备同步" onClick="Device();">
				
				  <input type="button" class="mmBtn" value="入网计算机同步" onClick="Computer();">
				
				</td>
				<td align="right">
				<tags:button code="export" menu="5">
				  <input type="button" class="mmBtn" value="导出EXCEL" onClick="toExcelchoose();">
				</tags:button>
				<tags:button code="add" menu="5">
				  <input type="button" class="mmBtn" value="新建资产" onClick="window.location='main.action'">
				</tags:button>  				
				</td>
			</tr>
	   </table>
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			 <tags:button code="changes" menu="5">
			  <td height="22" width="6%" align="center" background="../images/main20100521_58.gif" class="alllisttitle"><input type="checkbox" name="codes" id="codes" value="<s:property value="code"/>" onClick="checkAll(this);"/>选择</td>
			 </tags:button> 
			  <td  height="22" align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">流水号</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产编号</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">资产名称</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">资产类别</td>
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">状态</td>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">责任部门</td>
			  <s:if test="%{#session.record==2}">
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">his系统</td>
			  </s:if>
			  <td align="center" width="8%" background="../images/main20100521_58.gif" class="alllisttitle">存放位置</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">供应商</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">制造商</td>
			  <td align="center" width="20%" background="../images/main20100521_58.gif" class="alllisttitle">操作</td>
		   </tr>
			<s:iterator value="pageBean.list" var="assetsBase">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			 <tags:button code="changes" menu="5">
			  <td height="19" align="center" class="zczb_qua">
			  <input type="checkbox" name="code" id="code" value="<s:property value="code"/>"/></td>
			 </tags:button> 
			  <td height="19" align="center" class="zczb_qua"><s:property value="valueUnit" /></td>
			  <td align="center" class="zczb_qua"><s:property value="codeId" /></td>
			  <td align="center" class="zczb_qua"><s:property value="name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsType.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsState.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="usersByChargeId.department.name" /></td>
			  <s:if test="%{#session.record==2}">
			  <td align="center" class="zczb_qua">
			  <s:if test="%{ishis<1}">
			     否
			  </s:if>
			  <s:if test="%{ishis==1}">
			      是
			  </s:if>
			  </td>
			  </s:if>
			  <td align="center" class="zczb_qua"><s:property value="buildlocation.allname" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsProducerBySupportId.name" /></td>
			  <td align="center" class="zczb_qua"><s:property value="assetsProducerByProduceId.name" /></td>
			  <td align="center" class="zczb_qua">
			  <tags:button code="change" menu="5">
				<a href="#" onClick="change(<s:property value="code"/>);">变更</a><!--<b>/</b><a href="updateInput.action?assetsId=<s:property value="code"/>" >信息变更</a>-->
			  </tags:button>
			  
			  <tags:button code="update" menu="5">
				<a href="updateInput.action?assetsId=<s:property value="code"/>">修改</a>
			  </tags:button>
			  
			  <tags:button code="delete" menu="5">
				<a href="delete.action?assetsId=<s:property value="code"/>" onClick="javascript:return del()">删除</a>
			  </tags:button>
			  
			  <tags:button code="query" menu="5">
				<a href="show.action?assetsId=<s:property value="code"/>" >查看</a>
			  </tags:button>
				<a href="#" onClick="print(<s:property value="code"/>)">标签打印</a>
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
