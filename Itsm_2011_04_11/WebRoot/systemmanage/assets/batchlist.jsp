<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<script type="text/javascript" src="../js/util.js"></script>
		<script type="text/javascript">
function res()
{
   document.getElementById('assets.codeId').value="";
   document.getElementById('assets.m_des').value="";
   document.getElementById('assets.inDate').value="";
}
function del(){   
   var msg="确认删除记录吗？";
   if (confirm(msg) == true){
   return true;
   }else{
   return false;
   }   
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

function update(ids){
var win = new selecttypewindow({
		accepted:function(id,name){
				       document.form1.action="updateInput.action?message=1&id="+ids+","+"&type="+id;
                       document.form1.method="post";
                       document.form1.submit();
   		}
	});
	win.show();
}


function query(){
document.form1.action="batch.action";
document.form1.method="post";
document.form1.submit();
}
function changepage(page){
document.form1.action="batch.action?page="+page;
document.form1.method="post";
document.form1.submit();
}


function selecttype(){
	var win = new selecttypewindow({
		accepted:function(id,name){
			var obj = document.getElementsByName("code");
				    var j=0;
				    var str="";
				    for(var i= 0;i<obj.length;i++){
				      if(obj[i].checked==true){
				        str+=obj[i].value+",";
				        j++;
				      }
				    }
				    if(j>0){
				       document.form1.action="updateInput.action?message=1&id="+str+"&type="+id;
                       document.form1.method="post";
                       document.form1.submit();
                    }else{
                       alert("请选择资产！！！");
                    }
		}
	});
	win.show();
}
selecttypewindow = Ext.extend(Ext.Window, {
	title: "选择资产类别",
	width: 300,
	modal:true,
	height: 460,
	minWidth: 600,
	minHeight: 420,
	layout: "fit",
	closeAction: "close",
	plain: true,
	accepted: null,
	canceled: null,
	deptId : null,
	condition:null,
	initComponent: function() {
		selecttypewindow.superclass.initComponent.call(this);
		var parent = this;
		var limit = 10;
		
		// 创建一个div，供window显示
		var winContainer = Ext.DomHelper.append(Ext.getBody(), {tag: "div"});
		this.applyTo = winContainer;
		var typeTree = new Ext.tree.TreePanel({
			anchor:'100% 100%',
            animate:true, 
            autoScroll:true,
            enableDD : false,
            containerScroll: true,
			split: true,
			collapsible: true,
			collapsed: false,
			border:false,
			root: new Ext.tree.AsyncTreeNode({
				id: "0",
				text: "资产类别",
				expanded: true
			}),
			rootVisible: true,
			loader: new Ext.tree.TreeLoader({
				dataUrl: "../utiltree/getChildren.action",
				
				createNode : function(attr) 
					{  
				        if (attr.nt == 1) 
				        {  
				            attr.leaf = true;
				        } 
				        
				      return(attr.leaf ?  
				           new Ext.tree.TreeNode(attr) :  
				           new Ext.tree.AsyncTreeNode(attr));  
					} ,
				listeners: {
					beforeload: function(loader, node) {
						this.baseParams.requestData = Ext.encode({
							parentID: node.id || "0"
						});
					}
				}
			}),
			listeners:{
				click:function (node, e){
				    Ext.MessageBox.buttonText.yes = "确认";
				    Ext.MessageBox.buttonText.no = "取消";
				    Ext.Msg.confirmYN("资产类别","是否选择该类别?",
				    function(){
				    parent.accepted(node.id,node.text);
					parent.close();
					});
				}
			}
		});
	
    	this.add(typeTree);		
	}
});


</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;" onMouseDown="notshow();">

	<s:form action="" method='post' theme="simple" name="form1">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
     <tr>
      <td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
      <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产管理</td>
     </tr>
    </table>
   
	    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">   
         <tr bgcolor="#b5d6e6">
          <td align="right" nowrap bgcolor="#deebf1" > 资产编号： </td>
          <td bgcolor="#FFFFFF" align="left" width="210" style="padding:0 5px 0 5px;"><s:textfield id="assets.codeId" name="assets.codeId" style="width:100%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" > 资产描述： </td>
          <td bgcolor="#FFFFFF" align="left" width="210" style="padding:0 5px 0 5px;"><s:textfield id="assets.m_des" name="assets.m_des" style="width:100%;"></s:textfield></td>
          <td align="right" nowrap bgcolor="#deebf1" >入库日期：</td>
          <td bgcolor="#FFFFFF" align="left" width="210" style="padding:0 5px 0 5px;">
          <input type="text" id="assets.inDate" name="assets.inDate" class="Wdate" style="width:100%;" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='assets.inDate' format='yyyy-MM-dd'/>">
          </td>
          <td bgcolor="#deebf1" colspan="3" align="center"><input type="button" style="height: 20px" class="mmBtn"  value="搜索" onClick="query();"/>&nbsp;&nbsp;
          <input type="button" style="height: 20px" class="mmBtn"  value="重置" onClick="res();"/>&nbsp;&nbsp;</td>
         </tr>
        </table>
	   </s:form>
	   
	   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	        <tr>
				<td width="15%" height="30" align="left">
				  <input type="button" class="mmBtn" id="btn" name="btn" value="批量修改" onClick="selecttype();">
				</td>
				
			</tr>
	   </table>
	   
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td height="22" width="7%" align="center" background="../images/main20100521_58.gif" class="alllisttitle"><input type="checkbox" name="codes" id="codes" value="<s:property value="code"/>" onClick="checkAll(this);"/>选择</td>
			  <td  height="22" align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产编号</td>
			  <td align="center" width="10%" background="../images/main20100521_58.gif" class="alllisttitle">资产描述</td>
			  <td align="center" width="5%" background="../images/main20100521_58.gif" class="alllisttitle">入库日期</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">修改</td>
			  <td align="center" width="6%" background="../images/main20100521_58.gif" class="alllisttitle">删除</td>
			 </tr>
			<s:iterator value="pageBean.list" var="assetsBase">
			 <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
			  <td height="19" align="center" class="zczb_qua">
			  <input type="checkbox" name="code" id="code" value="<s:property value="code"/>"/></td>
			  <td height="19" align="center" class="zczb_qua"><s:property value="codeId" /></td>
			  <td align="center" class="zczb_qua"><s:property value="m_des" /></td>
			  <td align="center" class="zczb_qua"><s:date name='inDate' format='yyyy-MM-dd'/></td>
			  
			  <td align="center" class="zczb_qua">
			   <img src="../images/edt.gif">
				<a href="#" onClick="update(<s:property value="code"/>);">修改</a>
			  </td>

			  <td align="center" class="zczb_qua">
				<img src="../images/del.gif">
				<a href="delete.action?assetsId=<s:property value="code"/>&message=1" onClick="javascript:return del()">删除</a>
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
