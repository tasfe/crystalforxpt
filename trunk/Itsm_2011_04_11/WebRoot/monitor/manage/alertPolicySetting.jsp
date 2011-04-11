<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=path%>/css/Default.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/dtree.css" rel="StyleSheet"  type="text/css"/>
<script type="text/javascript"  src="<%=path%>/js/Main.js"></script>
<script type="text/javascript"  src="<%=path%>/js/dtree_checkbox.js"></script>
<script type="text/javascript"  src="<%=path%>/js/controls/Tabpage.js"></script>
<script> 
Page.onLoad(function(){
	if(!Cookie.get("Role.LastRoleCode")||Cookie.get("Role.LastRoleCode")==""){
		var tree = $("tree1");
		var arr = tree.getElementsByTagName("p");
		for(var i=0;i<arr.length;i++){
			var p = arr[i];
			if(i==1){
				p.className = "cur";
				Tree.CurrentItem = p;
				p.onclick.apply(p);
				break;
			}
		}
	}else{
		//Tree.select("tree1","cid",Cookie.get("Role.LastRoleCode"));//此没有执行
		var tree = $("tree1");
		var arr = tree.getElementsByTagName("p");
		for(var i=0;i<arr.length;i++){
			var p = arr[i];
			if(p.cid==Cookie.get("Role.LastRoleCode")){
				p.className = "cur";
				Tree.CurrentItem = p;
				p.onclick.apply(p);
				break;
			}
		}
	}
});
 
Page.onClick(function(){
	var div = $("_DivContextMenu");
	if(div){
			$E.hide(div);
	}
});
 
function showMenu(event,ele){
	var cid = ele.getAttribute("cid");
	if(!cid){
		return ;
	}
	var menu = new Menu();
	menu.setEvent(event);
	menu.setParam(cid);
	menu.addItem("新建角色",add,"/Icons/icon018a2.gif");
	menu.addItem("修改角色",showEditDialog,"/Icons/icon018a2.gif");
	menu.addItem("删除角色",del,"/Icons/icon018a2.gif");
	menu.addItem("-");
	// Type 0 表示节点为站点，1表示节点为栏目
   	menu.addItem("排序",sortRole,"Icons/icon400a13.gif");
   	menu.addItem("属性",attribute,"Icons/icon024a6.gif");
	menu.show();
}
 
function sortRole(param){
	if(!param){
		return;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 300;
	diag.Height = 100;
	diag.Title = "角色排序";
	diag.URL = "Platform/RoleSort.jsp?RoleID=" + param;
	diag.onLoad = function(){
		$DW.$("Move").focus();
	};
	diag.OKEvent = sortRoleSave;
	diag.show();
}
 
function sortRoleSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  	return;
    }
	Server.sendRequest("com.zving.platform.Role.sortRole",dc,function(response){
		if(response.Status==1){
			Tree.loadData("tree1");
			$D.close();
		}
	});	
}
 
function add(param){
	var diag = new Dialog("Diag1");
	diag.Width = 340;
	diag.Height = 150;
	diag.Title = "新建角色";
	diag.URL = "Platform/RoleAddDialog.jsp";
	diag.onLoad = function(){
		$DW.$("RoleCode").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}
 
function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("com.zving.platform.RoleTabBasic.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				window.parent.parent.location.reload();
			}
		});
	});
}
 
function del(param,paramname){
	var RoleCode = param;
	var RoleName = paramname;
	if(!RoleCode){
		Dialog.alert("请先选择一个角色！");
		return ;
	}
	Dialog.confirm("确认删除<b style='color:#F00'> "+RoleName+"</b> 角色吗？",function(){
		var dc = new DataCollection();
		dc.add("RoleCode",param);
		Server.sendRequest("com.zving.platform.RoleTabBasic.del",dc,function(response){
			Dialog.alert(response.Message,function(){
				if(response.Status==1){
					window.parent.parent.location.reload();
				}
			});
		});
	});
}
 
function showEditDialog(param){
	var RoleCode = param;
	if(!RoleCode){
		Dialog.alert("请先选择一个角色！");
		return ;
	}
	var diag = new Dialog("Diag1");
	diag.Width = 350;
	diag.Height = 80;
	diag.Title = "修改角色";
	diag.URL = "Platform/RoleEditDialog.jsp?RoleCode="+param;
	diag.onLoad = function(){
		$DW.$("RoleName").focus();
	};
	diag.OKEvent = editSave;
	diag.show();
}
 
function editSave(){
	var dc = Form.getData($DW.$F("form2"));
	if($DW.Verify.hasError()){
	  return;
     }
	Server.sendRequest("com.zving.platform.RoleTabBasic.save",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				window.parent.location.reload();
			}
		});
	});
}
function attribute(param){
	var RoleCode = param;
	var diag = new Dialog("Diag1");
	diag.Width = 260;
	diag.Height = 130;
	diag.Title = "属性";
	diag.URL = "Platform/RoleAttribute.jsp?RoleCode="+param;
	diag.ShowButtonRow = false;
	diag.show();
}
 
function onRoleTabClick(){
	var cid = Tree.CurrentItem.getAttribute("cid");
	var currentTab = Tab.getCurrentTab().contentWindow;
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
		Tab.getCurrentTab().src = "./basic.action?roleId="+cid;
	}else if(currentTab.init){
		<!--		currentTab.init();-->
		Tab.getCurrentTab().src = "./menu.action?roleId="+cid;
	}
}
 
function onTreeClick(ele){
	var cid = Tree.CurrentItem.getAttribute("cid");
	Cookie.set("Role.LastRoleCode",cid,"2100-01-01");
	$("_ChildTabFrame_Basic").src = "alertPolicy.action?policyId="+cid;
	//if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
	//		Tab.getCurrentTab().src = "./basic.action?roleId="+cid;
	//}else if(currentTab.init){
	//<!--		currentTab.init();-->
	//	Tab.getCurrentTab().src = "./menu.action?roleId="+cid;
	//}
}
//解除策略
function relievePolicy(){

	var ids = getCheckedBoxIds();//分区的Id没有下划线，设备的=设备ID+"_"+分区ID
	var id = ids.split(",");
	var deviceIds = "";
	for(var i=0; i<id.length ; i++){
		if(id[i].indexOf("_",0)>-1){
			deviceIds +=id[i].split("_")[0]+",";
		}
	}
	
	if(deviceIds.length>0){
		deviceIds = deviceIds.substring(0,deviceIds.length-1);
		document.form.action="monitorSystemSettings/relievePolicy.action?deviceId="+deviceIds;
		document.form.submit();
	}else{
		alert("请在策略应用中选择要解除策略的设备！");
	}
		
}
//应用策略
function applyPolicy(){
	var ids = getCheckedBoxIds();//分区的Id没有下划线，设备的=设备ID+"_"+分区ID
	var id = ids.split(",");
	var deviceIds = "";
	for(var i=0; i<id.length ; i++){
		if(id[i].indexOf("_",0)>-1){
			deviceIds +=id[i].split("_")[0]+",";
		}
	}
	if(deviceIds.length>0){
		var cid = Tree.CurrentItem.getAttribute("cid");
		deviceIds = deviceIds.substring(0,deviceIds.length-1);
		document.form.action="monitorSystemSettings/applyPolicy.action?policyId="+cid+"&deviceId="+deviceIds;
		document.form.submit();
	}else{
		alert("请在策略应用中选择要应用策略的设备！");
	}
}
//删除报警策略
function deletePolicy(){
	var cid = Tree.CurrentItem.getAttribute("cid");
	if(cid=="1"){
		alert("通用策略禁止删除！");
		return;
	}
	if(window.confirm('确定要删除该报警策略吗？')){
		Cookie.set("Role.LastRoleCode","1","2100-01-01");
		document.form.action="monitorSystemSettings/deleteAlertPolicy.action?policyId="+cid;
		document.form.submit();
		
	}else{
		return; 
	}
               
	
}
//添加报警策略
function addPolicy(){
	var url ='<%=path%>/monitor/manage/addAlertPolicy.jsp';
	var name = window.showModalDialog(url,'','dialogWidth=300px;dialogHeight=130px,center=yes,toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
	if(name!=undefined){
		document.form.action="monitorSystemSettings/addAlertPolicy.action?name="+name;
		document.form.submit();
	}
		
}
//创建副本
function clonePolicy(){
	var cid = Tree.CurrentItem.getAttribute("cid");
	if(cid!=""){
		document.form.action="monitorSystemSettings/cloneAlertPolicy.action?policyId="+cid;
		document.form.submit();
	}
		
}
</script>
</head>
<body>
 <s:form name="form" method="post" theme="simple"> 
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd" colspan=3>
					<div ztype='_Tree' style='-moz-user-select:none;height:430px'  onselectstart='stopEvent(event);' id='tree1' method='com.zving.platform.Role.treeDataBind' class='treeItem'>
						<table>
							<tr>
								<td><p level="0" id="tree1__TreeRoot" parentid="" lazy="0"><img src='../images/icons/treeicon10.gif'>报警策略</p>
									<div>
										<s:iterator value="alertPolicyList" var="role" status="sta">
											<p cid="${role.id}" cname="${role.name}" level="1" id="tree1_${role.id}" parentid="" lazy="0" onclick="Tree.onItemClick(event,this);onTreeClick(this);" ondblclick="Tree.onItemDblClick(event,this);" oncontextmenu="showMenu(event,this);"><img src='../images/icons/treeicon06.gif'><img src='../images/icons/icon025a1.gif'>&nbsp;${role.name}</p>
										</s:iterator>
									</div>
									<script>$('tree1').TagBody = "&lt;p&nbsp;cid='$\{RoleCode}'&nbsp;cname='$\{RoleName}'&nbsp;onClick=&quot;onTreeClick(this);&quot;&nbsp;oncontextmenu=&quot;showMenu(event,this);&quot;&gt;&amp;nbsp;$\{RoleName}&lt;/p&gt;";Tree.setParam('tree1','_ZVING_TREE_STYLE',"height:430px");Tree.setParam('tree1','_ZVING_TREE_LEVEL',999);Tree.setParam('tree1','_ZVING_TREE_LAZY',"false");Tree.setParam('tree1','_ZVING_TREE_EXPAND',"false");Tree.init('tree1');</script>
								</td>
							</tr>
						</table>
					</div> 
				</td>
			</tr>
			<tr>
				<td >
				<input name="button" type="button" class="mmBtn" onClick="addPolicy()" value="新建策略">
				</td>
				<td >
				<input name="button" type="button" class="mmBtn" onClick="deletePolicy()" value="删除策略">
				</td>
				<td >
				<input name="button" type="button" class="mmBtn" onClick="clonePolicy()" value="创建副本">
				</td>
			</tr>
		</table>
		</td>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">  
			 <tr>    
			 <td style="padding:6px;" class="blockTd">
			 <iframe src="alertPolicy.action" width="100%" height="0" id="_ChildTabFrame_Basic" frameborder="0" scrolling="auto" allowtransparency="true"></iframe>   
			 <script>Page.onLoad(function(){Tab.initFrameHeight("_ChildTabFrame_Basic");},5);</script>
			 </td>  
			 </tr>
		</table>
			 
		</td>
		<td width="180">
		
		<table width="180" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
				<tr>
				<td style="padding:6px;" class="blockTd" colspan=2>
				
				<script type="text/javascript">
				<!--
				projectURI = "<%=path%>";
				mytree = new dTree('mytree');
				mytree.config.useCheckbox = true;  //设置有复选框
				mytree.add('0','-1','策略应用','','','listFrame');
				<s:iterator value="subnetList" var="subnet">
				mytree.add('${subnet.id}','0','${subnet.name}','','','listFrame');
					
				</s:iterator>
				<s:iterator value="deviceList" var="device">
					var pname='${device.monitorDevice.monitorAlertPolicy.name}';
					if(pname.length>0)
						pname='('+pname+')';
					mytree.add('${device.monitorDevice.id}'+'_'+'${device.monitorSubnet.id}','${device.monitorSubnet.id}','${device.monitorDevice.name}'+pname,'','','listFrame');
				</s:iterator>	
				document.write(mytree);
		
				//-->
			</script>
									
				</td>
				</tr>
				<tr>
				<td >
				<input name="button" type="button" class="mmBtn" onClick="relievePolicy()" value="解除策略">
				
				</td>
				<td >
				<input name="button" type="button" class="mmBtn" onClick="applyPolicy()" value="应用策略">
				</td>
				</tr>
				</table>
			</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</s:form>
</body>
</html>

