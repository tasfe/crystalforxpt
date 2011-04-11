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
<script> 
function init(){
	DataGrid.setParam("dg1","RoleCode",$V("RoleCode"));
	DataGrid.loadData("dg1");
}
function treeCheckBoxClick(ele){
	var id=ele.id;
	var parentId=ele.getAttribute('parent');//父亲节点
	var code=ele.getAttribute('code');//编码 .._parentId_id_.....
	var checked=ele.checked;
	
	var arr = $N(ele.name); //所有菜单checkBox
	
	if(arr.length<1){
		return ;
	}
	for(var i=0;i<arr.length;i++){
		if(arr[i].getAttribute('code').indexOf(code+"_")>=0){
			arr[i].checked=checked;
		}
	}
	setParentCheck(checked,parentId);
}
function setParentCheck(check,parentId){//父节点设置
	if(parentId==0){
		return ;
	}
	if(check){//如果选中设置其所有父节点选中
		while(parentId!=0){
			document.getElementById('MenuID_'+parentId).checked=true;
			parentId=document.getElementById('MenuID_'+parentId).getAttribute('parent');
		}
	}else{
		isRotate(parentId);		
	}
}
function isRotate(parentId){//传入父节点,当取消选中时进行父节点设置
	var arr = $N('MenuID'); 
	if(arr.length<1 || parentId==0){
		return 0;
	}
	var check=false;
	for(var i=0;i<arr.length;i++){
		if(arr[i].getAttribute('parent')==parentId){
			check=arr[i].checked;
		}
		if(check){
			break;
		}
	}
	if(!check){//兄弟节点没有选中时，把父节点设为非选中
		document.getElementById('MenuID_'+parentId).checked=false;
	}else{
		return ;
	}
	parentId=document.getElementById('MenuID_'+parentId).getAttribute('parent');
	isRotate(parentId);
}
/*
function treeCheckBoxClick(ele){
	var id = ele.id;
	var index = parseInt(id.substring(id.lastIndexOf("_")+1));
	var checked = ele.checked;
	var level = ele.getAttribute("level");
	var arr = $N(ele.name);
	
	var length = arr.length;
	// 选中
	if(checked){
		for(var i=index-1;i>=0;i--){
			if(length>0){
							if(arr[i].getAttribute("level")<level){
				arr[i].checked = true;
				level = arr[i].getAttribute("level");
				if(level==0){
					break;
				}
			}
			}

		}
		level = ele.getAttribute("level");
		for(var i=index+1;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = true;
			}else {
				break;
			}
		}
	}else{
	// 取消选中
		if(level>0){
			var rootIndex = 0;
			var check_flag = false;
			for(var i=index-1;i>=0;i--){
				if(length>0)
					{
					if(arr[i].getAttribute("level")<level){
						rootIndex = i;
						break;
				}
				if(rootIndex!=i&&arr[i].checked){
					check_flag = true;
				}				
					}
								
			} 
			if(!check_flag){
				for(var i=index+1;i<length;i++){
					if(arr[i].getAttribute("level")<level){
						break;
					}
					if(arr[i].checked){
						check_flag = true;
						break;
					}
				}
				if(!check_flag){
					arr[rootIndex].checked = false;
				}
			}
			
		}		
		level = ele.getAttribute("level");
		for(var i=index+1;i<length;i++){
			if(arr[i].getAttribute("level")>level){
				arr[i].checked = false;
			}else{
				break;
			}
		}
	}
}
*/ 
function save(){
	if(!$V("RoleCode")){
		Dialog.alert("请先选择一个角色！");
		return;
	}
	var dc = new DataCollection();
	var arr = $N("MenuID");
	if(!arr||arr.length==0){
		Dialog.alert("您还没有选择菜单项！");
		return;
	};
	
	var dt = new DataTable();
	var cols=[];
	cols.push(["ID","1"]);
	cols.push(["Browse","1"]);
	cols.push(["PrivField","1"]);
	var values =[];
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked){
			values.push([arr[i].value,"1",$NV(arr[i].value+"")]);
		}else{
			values.push([arr[i].value,"0",""]);
		}
	}
	dt.init(cols,values);
	dc.add("RoleCode",$V("RoleCode"));
	dc.add("dt",dt,"DataTable");
	Server.sendRequest("com.zving.platform.RoleTabMenu.save",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				init();					
			}
		});
	});
}
 
function clickAllSelect(){
	var f = $("AllSelect").checked;
	var menuTree = $N("MenuID");
	var menuTreeLength = menuTree.length;
	for(var i=0;i<menuTreeLength;i++){
		menuTree[i].checked = f;
	}
}

function sub(){
	var roleid=document.getElementById("RoleCode").value;
	var array=document.getElementsByName('MenuID');
	if(roleid=='') alert("请先选择一个角色！");
	else{
		var flag=0;
		for(var i=0;i<array.length;i++) {
			if(array[i].checked) {
				flag=1;break;
			}
		}
		if(flag==0){
			alert("您还没有选择菜单项！");
		}else {		
			var array=document.getElementsByName('MenuID');	
			for(var i=0;i<array.length;i++) {
				if(array[i].checked){
					var value=array[i].value;
					var subArray=document.getElementsByName(value);
					var a=":";
					for(var j=0;j<subArray.length;j++) {
						if (subArray[j].checked) {
							a=a+subArray[j].value+",";
						}
					}
				}
				array[i].value=value+a;				
			}
			menuform.submit();
		}
	}
}
function selectButton() {
	var all=document.getElementsByName('selectbutton');	
	for(var i=0;i<all.length;i++) {		
		var val=all[i].value;
		var array=val.split(':');
		document.getElementById('MenuID_'+array[0]).checked=true;
		var all2=document.getElementsByName(array[0]);
		var subArray=array[1].split(',');
		for(var j=0;j<all2.length;j++){
			for(var k=0;k<subArray.length;k++) {
				if(all2[j].value==subArray[k]) all2[j].checked=true;
			}
		}		
	}
}
function trsecshowandoff(bb,cc) {//显示和隐藏表格的行；
      if (document.getElementById(bb).style.display == "") {
          document.getElementById(bb).style.display = "none";
		  document.getElementById(cc).src="../images/framework/butCollapse.gif";
      } else {
          document.getElementById(bb).style.display = "";
		  document.getElementById(cc).src="../images/framework/butExpand.gif";
      }
  }
  function trsecshowandoff2(bb,cc) {//显示和隐藏表格的行；
      if (document.getElementById(bb).style.display == "") {
          document.getElementById(bb).style.display = "none";
		  document.getElementById(cc).src="../img/jia.jpg";
      } else {
          document.getElementById(bb).style.display = "";
		  document.getElementById(cc).src="../img/jian.jpg";
      }
  }
</script>
</head>
<body onload="selectButton()">
<s:form name="menuform" action="saveprivilege" method="post" theme="simple" target="mainFrame">
	<s:hidden name="roleId" id="RoleCode"/> 
	<s:iterator value="privilegeMap" var="var">
		<input type="hidden" name="selectbutton" value="${var.value}"/> 
	</s:iterator>
<table width="100%" border='0' cellpadding='0' cellspacing='0'>
	<tr>
		<td style="padding:4px 5px;">
			<a href='#' ztype='zPushBtn' class='zPushBtn' hidefocus='true' tabindex='-1' onselectstart='return false' id='' onClick="javascript:sub()" priv="save" ><img src="../images/icons/icon018a4.gif" /><b>保存&nbsp;</b></a>
			 <span style="line-height:24px;">&nbsp;&nbsp;&nbsp;<label>全选&nbsp;<input type="checkbox" id="AllSelect" onClick="clickAllSelect();" /></label></span></td>
	</tr>
	<tr>
		<td style="padding:0px 5px;">
			<table width="100%" cellpadding="2" cellspacing="0" id="dg1" border="1" rules="rows" bordercolor="#b5d6e6">
				<tr ztype="head">
					<td background="../images/main20100521_58.gif" width="6%" ztype="RowNo">序号</td>
					<td background="../images/main20100521_58.gif" width="26%" ztype="tree" level="treelevel">菜单名称</td>
					<td background="../images/main20100521_58.gif" width="68%">权限</td>
				</tr>
				<s:iterator value="menuMap" var="map" status="st">
        		<tr style1="background-color:#FFFFFF" style2="background-color:#F9FBFC">
        			<td rowno="1">${map.key.id}</td>
					<td><img id="img_${map.key.id}" src='../images/framework/butExpand.gif' onclick="javascript:trsecshowandoff(${map.key.id},'img_${map.key.id}')"/>&nbsp;
						<input type='checkbox'  name='MenuID' id='MenuID_${map.key.id}' value='${map.key.id}' level='0'  onClick='treeCheckBoxClick(this);' parent='${map.key.parentId }' code='menu_${map.key.id }'>
						<img src="../${map.key.icon}" align="absmiddle" />&nbsp;${map.key.name}</td>
					<td width="68%" style="white-space:normal">
						<s:iterator value="#map.key.buttonMap" var="button" status="st">
							<input type='checkbox' name='${map.key.id}' id='${map.key.id}_${st.index+1}' value='${button.key}' ><label for='341_0'>${button.value}</label>&nbsp;
						</s:iterator>
					</td>
                </tr>
                <s:if test="#map.value.size>0">
                <tr id="${map.key.id}" >
            		<td colspan="3"  width="100%" >
            		<table width="100%" cellpadding="0" cellspacing="0" >
              			<s:iterator value="#map.value" var="submap">
              			<s:if test="#submap.value.size>0">
              			<tr>
              				<td width="6%" rowno="1">${submap.key.id}</td>
							<td width="26%"><q style='padding:0 15px'></q><img id='subimg_${submap.key.id}' src='../img/jian.jpg' onclick="javascript:trsecshowandoff2(${submap.key.id},'subimg_${submap.key.id}')"/>&nbsp;
								<input type='checkbox'  name='MenuID' id='MenuID_${submap.key.id}' value='${submap.key.id}' level='0'  onClick='treeCheckBoxClick(this);' parent='${submap.key.parentId }' code='menu_${map.key.id }_${submap.key.id}'>
								<img src="../${submap.key.icon}" align="absmiddle" />&nbsp;${submap.key.name}</td>
							<td width="68%" style="white-space:normal">
								<s:iterator value="#submap.key.buttonMap" var="button" status="st">
									<input type='checkbox' name='${submap.key.id}' id='${submap.key.id}_${st.index+1}' value='${button.key}' ><label for='341_0'>${button.value}</label>&nbsp;
								</s:iterator>
							</td>
                 		</tr>                 		
                  		<tr id="${submap.key.id}" style="display:">                  		
            		        <td height="22" background="images/main20100521_61.gif" colspan="3">
            		        	<table width="100%" border="0" cellspacing="0" cellpadding="0" colspan="8" >
                 					<s:iterator value="#submap.value" var="submenu">
			     					<tr>
			        					<td width="6%" rowno="1">${id}</td>
										<td width="26%"><q style='padding:0 30px'></q><img src='../images/framework/butNoChild.gif'/>&nbsp;
											<input type='checkbox'  name='MenuID' id='MenuID_${id}' value='${id}' level='0'  onClick='treeCheckBoxClick(this);' parent='${parentId }' code='menu_${map.key.id }_${submap.key.id}_${id }'>
											<img src="../${icon}" align="absmiddle" />&nbsp;${name}</td>
										<td width="68%" style="white-space:normal">
											<s:iterator value="#submenu.buttonMap" var="button" status="st">
												<input type='checkbox' name='${id}' id='${id}_${st.index+1}' value='${button.key}' ><label for='341_0'>${button.value}</label>&nbsp;
											</s:iterator>
										</td>
        		 					</tr>
        		 					</s:iterator>
        		 				</table>
        		 			</td>
        		 		</tr>
        		 		</s:if>
        		 		<s:else>
        		 		<tr>
              				<td width="6%" rowno="1">${submap.key.id}</td>
							<td width="26%"><q style='padding:0 15px'></q><img id='subimg_${submap.key.id}' src='../img/jian.jpg'/>&nbsp;
								<input type='checkbox'  name='MenuID' id='MenuID_${submap.key.id}' value='${submap.key.id}' level='0'  onClick='treeCheckBoxClick(this);' parent='${submap.key.parentId }' code='menu_${map.key.id }_${submap.key.id}'>
								<img src="../${submap.key.icon}" align="absmiddle" />&nbsp;${submap.key.name}</td>
							<td width="68%" style="white-space:normal">
								<s:iterator value="#submap.key.buttonMap" var="button" status="st">
									<input type='checkbox' name='${submap.key.id}' id='${submap.key.id}_${st.index+1}' value='${button.key}' ><label for='341_0'>${button.value}</label>&nbsp;
								</s:iterator>
							</td>
                 		</tr> 
        		 		</s:else>
			     		</s:iterator>
			     	</table>
			     </td>
			 </tr>
			 </s:if>
           </s:iterator>
				
				
				<!-- 
				
				
				
				<s:iterator value="menuList" var="menu">
				<tr onclick="DataGrid.onRowClick(this,event);" oncontextmenu="DataGrid._onContextMenu(this,event)" level="0">
					<td rowno="1">${id}</td>
					<td><s:if test="expand==Y&&type==1"><img src='../images/framework/butExpand.gif' onclick='DataGrid.treeClick(this)'/>&nbsp;</s:if>
						<s:if test="type==2"><q style='padding:0 10px'></q><img src='../images/framework/butNoChild.gif'/></s:if>
						<input type='checkbox'  name='MenuID' id='MenuID_${id}' value='${id}' level='0'  onClick='treeCheckBoxClick(this);'>
						<img src="../${icon}" align="absmiddle" />&nbsp;${name}</td>
					<td width="68%" style="white-space:normal">
						<s:iterator value="#menu.buttonMap" var="map" status="st">
							<input type='checkbox' name='${id}' id='${id}_${st.index+1}' value='${map.key}' ><label for='341_0'>${map.value}</label>&nbsp;
						</s:iterator>
					</td>
				</tr>
				</s:iterator>
				
				 -->
				
			</table>
		</td>
	</tr>
</table>
</s:form>
</body>
</html>

