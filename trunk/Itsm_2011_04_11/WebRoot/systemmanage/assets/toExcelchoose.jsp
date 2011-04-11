<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
 <head>
  <title>EXCEL导出字段选择页面</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link href="../css/style.css" rel="stylesheet" type="text/css">
<script>
  //选择添加还是删除
  function addanddelete(selectid1,selectid2){
    var selectstart=document.getElementById(selectid1);
	var selectend=document.getElementById(selectid2);
	if(selectstart.selectedIndex==-1){
	   alert("请选择字段");
	}else{
	  for(var i=0;i<selectstart.options.length;i++){
	     if(selectstart.options[i].selected){
		    selectend.add(new Option(selectstart.options[i].text,selectstart.options[i].value));
		    selectstart.remove(selectstart.selectedIndex);
			i=-1;
		 }
	   }
	}
  }
//向下移动一行
  function moveDown(selectid){
    var selectend=document.getElementById(selectid);
	var tempText;
	var tempValue;
	for(var i = selectend.length-2; i>=0; i--){
		if(selectend.options[i].selected&&!selectend.options[i+1].selected){
			tempText = selectend.options[i+1].text;
			tempValue = selectend.options[i+1].value;
			selectend.options[i+1].text = selectend.options[i].text;
			selectend.options[i+1].value = selectend.options[i].value;
			selectend.options[i].text = tempText;
			selectend.options[i].value = tempValue;
			selectend.options[i].selected = false;
			selectend.options[i+1].selected = true;
		}
	}
}
//向上移动一行
function moveUp(selectid){
    var selectend=document.getElementById(selectid);
	var tempText;
	var tempValue;
	for(i = 1; i < selectend.length; i++){
		if(selectend.options[i].selected&&!selectend.options[i-1].selected){
			tempText = selectend.options[i-1].text;
			tempValue = selectend.options[i-1].value;
			selectend.options[i-1].text = selectend.options[i].text;
			selectend.options[i-1].value = selectend.options[i].value;
			selectend.options[i].text = tempText;
			selectend.options[i].value = tempValue;
			selectend.options[i].selected = false;
			selectend.options[i-1].selected = true;
		}
	}
}
//移动至第一行
function moveUpTop(selectid){
    var selectend=document.getElementById(selectid);
	var topIndex = selectend.selectedIndex;
	var i = 0;
	
	if(topIndex!=-1 && topIndex!=0){
		for(i = 0; i < topIndex; i++){
			moveUp(selectid);
		}
	}
}
//移动至最后一行
function moveDownBottom(selectid){
    var selectend=document.getElementById(selectid);
	var bottomIndex = selectend.length - selectend.selectedIndex - selectOptionCount(selectid);
	var i = 0;
	
	if(selectend.selectedIndex != -1 && selectend.selectedIndex != selectend.length-1){
		for(i = 0; i < bottomIndex; i++){
			moveDown(selectid);
		}
	}
}

//被选中的记录数
function selectOptionCount(selectid){
    var selectend=document.getElementById(selectid);
	var i;
	var selectedCount = 0;
	for(i = 1; i <selectend.length; i++){
		if(selectend.options[i].selected){
			selectedCount += 1;
		}
	}
	return selectedCount;
}

function excel(){
   var select2=document.getElementById("select2");
   var select4=document.getElementById("select4");
   for(var i=0;i<select2.options.length;i++){
      select2.options[i].selected = true;
   }
   for(var j=0;j<select4.options.length;j++){
      select4.options[j].selected = true;
   }
   document.form1.action="excelDownString.action";
   document.form1.method="post";
   document.form1.submit();
}

function back(){
   document.form1.action="list.action";
   document.form1.method="post";
   document.form1.submit();
}
</script>
<style>

</style>
 </head>
<body>
<s:form action="" method='post' theme="simple" name="form1">
  <table cellspacing="1" bgcolor="#B5D6E6" style="font-size:12px; color:#446484; width:100%; margin:0 8px 8px 8px">
   <tr bgcolor="#E9F7FC" style="background:url(../images/anniu_bg.jpg);border:0px; line-height:18px;">
    <td colspan="3" align="center"><strong>导出字段选择</strong></td>
	<td colspan="3" align="center"><strong>分组统计字段选择</strong></td>
	</tr>
   <tr bgcolor="#E9F7FC" style="line-height:20px;">
    <td align="center">
	资产信息字段</td>
	<td align="center">&nbsp;</td>
	<td align="center">
	已选导出字段</td>
    <td align="center">
	资产信息字段</td>
	<td align="center">&nbsp;	</td>
	<td align="center">
	已选分组统计字段</td>
	</tr>
	<tr bgcolor="#ffffff">
    <td align="center" style="padding:10px 0 10px 0">
	 <select name="select1" id="select1" multiple size="15" style="width:150px;" ondblclick="addanddelete('select1','select2');">
	 <s:iterator value="assetsConfigList" var="assetsConfig">
	   <option value="<s:property value='configColumnName'/>"><s:property value='configName'/></option>
     </s:iterator>
    </select></td>
	<td align="center">
	<input type="button" value="添加字段>>" onClick="addanddelete('select1','select2');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484"><br>
	<input type="button" value="<<删除字段" onClick="addanddelete('select2','select1');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484"><br>
	<input type="button" value="将已选择字段移动至第一行" onClick="moveUpTop('select2');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484;width:180px;"><br>
	<input type="button" value="将已选择字段向上移动一行" onClick="moveUp('select2');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484;width:180px;"><br>
	<input type="button" value="将已选择字段移动至最后一行" onClick="moveDownBottom('select2');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484;width:180px;"><br>
	<input type="button" value="将已选择字段向下移动一行" onClick="moveDown('select2');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484;width:180px;">	</td>
	<td align="center"  style="padding:10px 0 10px 0">
	<select name="select2" id="select2" multiple size="15" style="width:150px;" ondblclick="addanddelete('select2','select1');"></select>	</td>
    <td align="center"  style="padding:10px 0 10px 0">
	 <select name="select3" id="select3" multiple size="15" style="width:150px;" ondblclick="addanddelete('select3','select4');">
       <s:iterator value="assetsConfigList" var="assetsConfig">
	      <option value="<s:property value='configColumnName'/>"><s:property value='configName'/></option>
       </s:iterator>
    </select>	</td>
	<td align="center">
	 <input type="button" value="添加字段>>" onClick="addanddelete('select3','select4');"  style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484"><br>
	 <input type="button" value="<<删除字段" onClick="addanddelete('select4','select3');" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484">	</td>
	<td align="center" style="padding:10px 0 10px 0">
	<select name="select4" id="select4" multiple size="15" style="width:150px;" ondblclick="addanddelete('select4','select3');"></select>	</td>
   </tr>
   <tr>
   <td colspan="6" align="center" bgcolor="#ffffff"><input type="button" value="导 出" onClick="excel();"  style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484; line-height:18px;">&nbsp;&nbsp;&nbsp;<input type="button" value="返 回" onClick="back();" style="background:url(../images/anniu_bg.jpg); border:1px solid #519ACC; margin:4px;color:#446484;line-height:18px;"></td>
   </tr>
  </table>
</s:form>
</body>
</html>