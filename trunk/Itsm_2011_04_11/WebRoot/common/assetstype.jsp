<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>资产类别</title>
		<link rel="StyleSheet" href="../css/dtree.css" type="text/css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" href="../css/style.css" type="text/css" />
		
		
		<!-- ext core <script type="text/javascript" src="../js/dtreelichuang.js"></script>-->
		<link rel="stylesheet" type="text/css" href="../js/ext/resources/css/ext-all.css">
		<script type="text/javascript" src="../js/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext/ext-all.js"></script>
		<base target="_self">
		<style type="text/css">
.list {
	border-left: 1px solid white;
	border-right: 1px solid #A09B8D;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

.list_btm {
	border-top: 2px outset white;
	background-color: #D4D0C7;
	text-align: right;
	padding: 3px;
	padding-top: 4px;
}

.list_par {
	border-top: 1px solid white;
	border-bottom: 2px ridge #BDB9B0;
	background-color: #D4D0C7;
	font-family: Tahoma;
}

td {
	font-size: 11px;
	padding: 2px;
	font-family: Tahoma;
}

.mmBtn {
	padding: 0px;
	background-color: #D4D0C7;
	mborder: 1px outset white;
	font-family: Tahoma;
	font-size: 11px;
	line-height: 15px;
}

.divtitle {
	position: absolute;
	z-index: 1;
	overflow: hidden;
	width: 100%;
	height: 22px;
	padding: 0px;
}
#ext-gen17{ overflow-x:auto!important;overflow-y:auto!important;}
</style>

<script type="text/javascript">
function back(ids,names){
if(window.parent.document.getElementById('assetsTypeid')){
window.parent.document.getElementById('assetsTypeid').value=ids;
}
if(window.parent.document.getElementById('assetsTypename')){
window.parent.document.getElementById('assetsTypename').value=names;
}
if(window.parent.document.getElementById('assetsType')){
window.parent.document.getElementById('assetsType').style.visibility='hidden';
}
}	
	Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/js/ext/resources/images/default/s.gif";
	Ext.onReady(function(){
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = "side";
		Ext.MessageBox.buttonText.yes = "是";
		Ext.MessageBox.buttonText.no = "否";
		Ext.MessageBox.buttonText.ok = "确定";
		
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
					back(node.id,node.text);
				}
			}
		});
		var vp = new Ext.Viewport({
			layout: "fit",
			items: [typeTree]
		});
	});				
</script>
	</head>
	<body>
	</body>
</html>