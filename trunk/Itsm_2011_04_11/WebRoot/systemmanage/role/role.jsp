<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/Default.css" rel="stylesheet" type="text/css" />
<script src="../js/Main.js"></script>
<script src="../js/controls/Tabpage.js"></script>
<link rel="StyleSheet" href="../css/dtree.css" type="text/css"/>
<script type="text/javascript" src="../js/dtree.js"></script>
<script> 
Page.onLoad(function(){
	if(!Cookie.get("Role.LastRoleCode")||Cookie.get("Role.LastRoleCode")==""){
		//var tree = $("tree1");
		//var arr = tree.getElementsByTagName("p");
		//for(var i=0;i<arr.length;i++){
			//var p = arr[i];
			//if(i==1){
				//p.className = "cur";
				//Tree.CurrentItem = p;
				//p.onclick.apply(p);
				//break;
			//}
		//}
		
	}else{
		//Tree.select("tree1","cid",Cookie.get("Role.LastRoleCode"));//此没有执行
		//alert(123);
		var param=Cookie.get("Role.LastRoleCode").split('$');
		var cid = param[0];
		var ctype=param[1];
		onTreeClick(cid,ctype);
		//var tree = $("tree1");
		//var arr = tree.getElementsByTagName("p");
		//for(var i=0;i<arr.length;i++){
			//var p = arr[i];
			//if(p.cid==Cookie.get("Role.LastRoleCode")){
				//p.className = "cur";
				//Tree.CurrentItem = p;
				//p.onclick.apply(p);
				//break;
			//}
		//}
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
	var cid;
	var ctype;
	if(!Cookie.get("Role.LastRoleCode")||Cookie.get("Role.LastRoleCode")==""){
		return ;
	}else{
		var param=Cookie.get("Role.LastRoleCode").split('$');
		cid = param[0];
		ctype=param[1];
	}
	if(!cid || !ctype){
		return ;
	}
	var currentTab = Tab.getCurrentTab().contentWindow;
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
		Tab.getCurrentTab().src = "./basic.action?roleId="+cid;
	}else if(currentTab.init){
		<!--		currentTab.init();-->
		Tab.getCurrentTab().src = "./menu.action?roleId="+cid+"&roleType="+ctype;
	}
}
 
function onTreeClick(ele){
	var cid = Tree.CurrentItem.getAttribute("cid");
	var ctype=Tree.CurrentItem.getAttribute("ctype");
	var currentTab = Tab.getCurrentTab().contentWindow;
	Cookie.set("Role.LastRoleCode",cid);
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
			Tab.getCurrentTab().src = "./basic.action?roleId="+cid;
	}else if(Tab.getCurrentTab()==Tab.getChildTab("Menu")){
<!--		currentTab.init();-->
		Tab.getCurrentTab().src = "./menu.action?roleId="+cid+"&roleType="+ctype;
	}
}
function onTreeClick(id,type){
	if(!id || !type){
		return ;
	}
	var cid = id;
	var ctype=type;
	var currentTab = Tab.getCurrentTab().contentWindow;
	Cookie.set("Role.LastRoleCode",cid+"$"+ctype);
	if(Tab.getCurrentTab()==Tab.getChildTab("Basic")){
			Tab.getCurrentTab().src = "./basic.action?roleId="+cid;
	}else if(Tab.getCurrentTab()==Tab.getChildTab("Menu")){
<!--		currentTab.init();-->
		Tab.getCurrentTab().src = "./menu.action?roleId="+cid+"&roleType="+ctype;
	}
}
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="6" cellpadding="0"
	style="border-collapse: separate; border-spacing: 6px;">
	<tr valign="top">
		<td width="180">
		<table width="180" border="0" cellspacing="0" cellpadding="6"
			class="blockTable">
			<tr>
				<td style="padding:6px;" class="blockTd">
					<div style='-moz-user-select:none;height:430px' >
						<table>
							<tr>
								<td><p level="0" id="tree1__TreeRoot" parentid="" lazy="0"><img src='../images/icons/treeicon10.gif'>角色列表</p>
									<div >
										<script type="text/javascript">
										d = new dTree('d');
										<s:iterator value="roleGroups" var="roleGroup" status="sta">
											d.add('${roleGroup.id}','${roleGroup.pid}','${roleGroup.name}','','','');
										</s:iterator>
										<s:iterator value="roles" var="role" status="sta">
											if('${role.roleGroup}'=='0'){
												d.add('${role.id}+x','-1','${role.name}',"javascript:onTreeClick(${role.id},'${role.roleType}');",'','');
											}else{
												d.add('${role.id}+x','${role.roleGroup}','${role.name}',"javascript:onTreeClick(${role.id},'${role.roleType}');",'','');
											}
										</s:iterator>
										document.write(d);
						 				d.openAll();
										</script>
									</div>
								</td>
							</tr>
						</table>
					</div> 
				</td>
			</tr>
		</table>
		</td>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="6" class="blockTable">  <tr>    <td height="26" valign="middle" class="blockTd">    <table width="100%" border='0' cellpadding='0' cellspacing='0' style="background:url(/zoa/Framework/Images/divchildtabBarBg.gif) repeat-x left bottom; margin-bottom:1px;">    <tr>      <td valign="bottom" height='30' style="padding:0 0 0 6px;_padding:0 0 0 2px;">
			<div style='-moz-user-select:none;' onselectstart='return false' id='Basic'  class='divchildtabCurrent' src='RoleTabBasic.jsp' onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src='../images/icons/icon018a1.gif' />
				<b>基本信息</b>
			</div>
			<div style='-moz-user-select:none;' onselectstart='return false' id='Menu'  class='divchildtab' src='menu.action' onclick="Tab.onChildTabClick(this);onRoleTabClick()" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>
				<img src="../images/icons/icon018a1.gif" />
				<b>菜单权限</b>
			</div>
					</td>   </tr>   </table>   </td>  </tr>  <tr>     <td style="padding:2px 6px;"><iframe src="basic.action" width="100%" height="0" id="_ChildTabFrame_Basic" frameborder="0" scrolling="auto" allowtransparency="true"></iframe><iframe src="menu.action" width='100%' height='0' id="_ChildTabFrame_Menu" frameborder="0" scrolling="auto" allowtransparency="true"></iframe>     </td>  </tr></table><script>Page.onLoad(function(){Tab.initFrameHeight("_ChildTabFrame_Basic");},5);</script>
</td>
	</tr>
</table>
</body>
</html>

