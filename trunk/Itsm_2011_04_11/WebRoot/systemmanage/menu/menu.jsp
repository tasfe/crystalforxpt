<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单</title>
<link href="../css/Default.css" rel="stylesheet" type="text/css" />
<script src="../js/Main.js"></script>
<script>
function add(){
	var diag = new Dialog("Diag1");
	diag.Width = 450;
	diag.Height = 250;
	diag.Title = "新建菜单";
	diag.URL = "menu/MenuDialog.jsp";
	diag.onLoad = function(){
		$DW.$("Name").focus();
	};
	diag.OKEvent = addSave;
	diag.show();
}

function addSave(){
	var dc = Form.getData($DW.$F("form2"));
	dc.add("Visiable",$DW.$NV("Visiable"));
	dc.add("Icon",$DW.$("Icon").src);
	if($DW.Verify.hasError()){
		return;
	}
	Server.sendRequest("com.zving.platform.Menu.add",dc,function(response){
		Dialog.alert(response.Message,function(){
			if(response.Status==1){
				$D.close();
				DataGrid.loadData("dg1",initGrid);
			}
		});
	});
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
  
function trsecshowandoff(bb,cc) {//显示和隐藏表格的行；
      if (document.getElementById(bb).style.display == "") {
          document.getElementById(bb).style.display = "none";
		  document.getElementById(cc).src="../images/framework/butCollapse.gif";
      } else {
          document.getElementById(bb).style.display = "";
		  document.getElementById(cc).src="../images/framework/butExpand.gif";
      }
  }
</script>
</head>
<body style="overflow:auto">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
    <tr valign="top">
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="blockTable">
 		<tr>
            <td valign="middle" class="blockTd"><img src="../images/icons/icon022a1.gif" width="20" height="20" />菜单列表</td>
          </tr>
		  <tr>
			<td style="padding:0 8px 4px;">
				<a href='javascript:void(0);' ztype='zPushBtn' class='zPushBtn' hidefocus='true' tabindex='-1' onselectstart='return false' id='' onClick="add();return false;" priv="add" ><img src="../Icons/icon022a2.gif" width="20" height="20" /><b>新建&nbsp;</b></a> 
            </td>
          </tr>
          <tr>
			<td>
              <table width="100%" rules="rows" cellpadding="0" cellspacing="0" afterdrag="sortMenu" border="1" bordercolor="#b5d6e6">
                <tr ztype="head" background="../images/main20100521_58.gif" height="22px">
                  <td height="22px"  background="../images/main20100521_58.gif" width="3%" ztype="RowNo" drag="true"><img src="../images/framework/icon_drag.gif" width="16" height="16"></td>
                  <td  background="../images/main20100521_58.gif" width="3%" ztype="selector" field="id"><input type='checkbox' value='*' id='dg1_AllCheck' onclick="DataGrid.onAllCheckClick('dg1')"/></td>
                  <td  background="../images/main20100521_58.gif" width="18%" ztype="tree" level="treelevel" ><b>菜单名称</b></td>
                  <td  background="../images/main20100521_58.gif" width="7%" ztype="checkbox" checkedvalue="Y" field="visiable"><b>是否显示</b></td>
                  <td background="../images/main20100521_58.gif" width="7%" ztype="checkbox" checkedvalue="Y" field="expand"><b>是否展开</b></td>
                  <td background="../images/main20100521_58.gif" width="28%"><strong>URL</strong></td>
                  <td background="../images/main20100521_58.gif" width="17%"><strong>添加时间</strong></td>
                  <td background="../images/main20100521_58.gif" width="17%"><strong>备注</strong></td>
                </tr>
                <s:iterator value="menuMap" var="map">
        		<tr style="display:">
                	<td>${map.key.id}</td>
                  	<td><input type='checkbox' name='dg1_RowCheck' id='dg1_RowCheck2' value='${id}'></td>
                  	<td><img id="img_${map.key.id}" src='../images/framework/butExpand.gif' onclick="javascript:trsecshowandoff(${map.key.id},'img_${map.key.id}')"/>&nbsp;
                  	  <img src="../${map.key.icon}" align="absmiddle"/>&nbsp;${map.key.name}</td>
                  	<td><input type='checkbox' disabled name='dg1_visiable_Checkbox' id='dg1_visiable_Checkbox1' value='${visiable}' checked></td>
                  	<td></td>
                  	<td>${map.key.url} </td>
                  	<td>${map.key.addTime}</td>
                  	<td>${map.key.memo}</td>
              	</tr>
              	<s:if test="#map.value.size>0">
              	<tr id="${map.key.id}" >
            		<td colspan="8"  width="100%" ><table width="100%" cellpadding="0" cellspacing="0">
            		  <s:iterator value="#map.value" var="submap">
            		    <s:if test="#submap.value.size>0">
            		      <tr >
            		        <td width="3%" >${submap.key.id}</td>
            		        <td width="3%" ><input type='checkbox' name='dg1_RowCheck2' id='dg1_RowCheck' value='${submap.key.id}'></td>
            		        <td width="18%" ><q style='padding:0 10px'></q><img id='subimg_${submap.key.id}' src='../img/jian.jpg' onclick="javascript:trsecshowandoff2(${submap.key.id},'subimg_${submap.key.id}')"/>&nbsp; <img src="../${submap.key.icon}" align="absmiddle"/>&nbsp;${submap.key.name}</td>
            		        <td width="7%" ><input type='checkbox' disabled name='dg1_visiable_Checkbox2' id='dg1_visiable_Checkbox2' value='${submap.key.visiable}' checked></td>
            		        <td width="7%" ></td>
            		        <td width="28%" >${submap.key.url} </td>
            		        <td width="17%" >${submap.key.addTime}</td>
            		        <td width="17%" >${submap.key.memo}</td>
          		        </tr>
            		      <tr id="${submap.key.id}" style="display:">
            		        <td height="22" background="images/main20100521_61.gif" colspan="8"><table width="100%" border="0" cellspacing="0" cellpadding="0" colspan="8" >
            		          <s:iterator value="#submap.value" var="submenu">
            		            <tr>
            		              <td width="3%">${id}</td>
            		              <td width="3%"><input type='checkbox' name='dg1_RowCheck2' id='dg1_RowCheck' value='${id}'></td>
            		              <td width="18%"><q style='padding:0 20px'></q><img src='../images/framework/icon_sort.gif'/>&nbsp; <img src="../${Icon}" align="absmiddle"/>&nbsp;${name}</td>
            		              <td width="7%"><input type='checkbox' disabled name='dg1_visiable_Checkbox2' id='dg1_visiable_Checkbox2' value='${visiable}' checked></td>
            		              <td width="7%"></td>
            		              <td width="28%">${url} </td>
            		              <td width="17%">${addTime}</td>
            		              <td width="17%">${memo}</td>
          		              </tr>
          		            </s:iterator>
          		          </table></td>
          		        </tr>
          		      </s:if>
            		    <s:else>
            		      <tr>
            		        <td  width="3%" >${submap.key.id}</td>
            		        <td width="3%" ><input type='checkbox' name='dg1_RowCheck2' id='dg1_RowCheck' value='${submap.key.id}'></td>
            		        <td width="18%" ><q style='padding:0 10px'></q><img id='subimg_${submap.key.id}' src='../images/framework/butNoChild.gif' onclick="javascript:trsecshowandoff(${submap.key.id},'subimg_${submap.key.id}')"/>&nbsp; <img src="../${submap.key.icon}" align="absmiddle"/>&nbsp;${submap.key.name}</td>
            		        <td width="7%" ><input type='checkbox' disabled name='dg1_visiable_Checkbox2' id='dg1_visiable_Checkbox2' value='${submap.key.visiable}' checked></td>
            		        <td width="7%" ></td>
            		        <td width="28%" >${submap.key.url} </td>
            		        <td width="17%" >${submap.key.addTime}</td>
            		        <td width="17%" >${submap.key.memo}</td>
          		        </tr>
          		      </s:else>
          		    </s:iterator>
          		  </table></td>
              	</tr>
              	</s:if>
              	</s:iterator>
              </table>
            </td>
          </tr>
      </table></td>
  </table>
</body>
</html>
