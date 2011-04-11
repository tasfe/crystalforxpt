<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" type="text/javascript" src="<%=path%>/js/DatePicker/WdatePicker.js"></script>
    <script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/MonitorAlertTypeService.js'></script>
	<script type='text/javascript' src='dwr/interface/MonitorAlertSmalltypeService.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
    <title></title>
    <script src="js/Main.js" type="text/javascript"></script>	
	<link href="theme/css.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		<!--
		.STYLE4 {color: #03515d;
			font-size: 12px;
		}
		.STYLE1 {color:#022e44;
			font-size: 12px;
			font-weight:bold;
		}
		
		/* Settings */
		body.settings #primary, body.settings #secondary{
        	padding:5px;
        	
		}
		/* Settings main page choice */
		dl.settings_options{
		  list-style:none;
		  width:100%;
		  margin:0;
		  padding:10px 0 0 0;
		  float:left;
		  clear:left;
		}
		
		dl.settings_options dt{
		  border-bottom:1px solid #ccc;
		  font-weight:bold;
		  margin:0 0 10px;
		}
		
		dl.settings_options dd{
		  margin:0;
		  padding:0;
		  display:inline;
		}
		dl.settings_options dd a{
		  text-decoration:none;
		  display:block;
		  float:left;
		  min-width:260px;
		  min-height:40px;
		  /* see hacks.ie6.css */
		  margin:0 10px 20px 10px;
		  padding:5px 5px 5px 50px;
		  /* the background-image property is applied in-line */
		  background-color:transparent;
		  background-repeat:no-repeat;
		  background-position:top left;
		  outline:none;
		}
		dl.settings_options dd a strong{
		  display:block;
		  font-size:1.0em;
		  font-weight:bold;
		  color:#333;
		}
		dl.settings_options dd a span{
		  display:block;
		  font-size:12px;
		  color:#888;
		}
		dl.settings_options dd a:hover strong{ text-decoration:underline; }
		dl.settings_options dd a:hover span{ text-decoration:none; }
		dl.settings_options dd a:active{ outline:none; /* for firefox */ }
		body.abstract_settings h3{
		  clear:left;
		  padding:200px 0 0;
		  margin:0;
		  text-align:center;
		  font-size:1.1em;
		  font-weight:normal;
		  color:#888;
		}
		
		-->
	</style>
<script>
//初始化
function init(){
	 // 初始化下拉菜单选项
	MonitorAlertTypeService.getAll(callbackAlertTypeType);
	MonitorAlertSmalltypeService.findByParentTypeId("all", fillAlertSmallType );
}
function callbackAlertTypeType(data){  //显示出报警类型
	dwr.util.removeAllOptions("monitorAlertType");
	dwr.util.addOptions("monitorAlertType",{'':'--全部--'});
	dwr.util.addOptions("monitorAlertType",data,"code","name");   
		
}

function onChange(obj){    //通过调用JS文件来调用java类
	if(document.form.monitorAlertType.value=="")  
		MonitorAlertSmalltypeService.findByParentTypeId("all", fillAlertSmallType );
   	else  
   		MonitorAlertSmalltypeService.findByParentTypeId(document.form.monitorAlertType.value, fillAlertSmallType );
}
function fillAlertSmallType(resultlist) //JS的回调函数 更新界面
{
	document.form.smallType.options.length=0;
	for(var i=0; i<resultlist.length; i++){
		var parabean=resultlist[i];
		var oOption=new Option(parabean.name,parabean.code);
		document.form.smallType.options.add(oOption);
	}
}

 
//提交
function doSearch(){
	if(document.form.start.value==""){
     	alert("请选择开始时间！");
     	return;    
 	}
 	if(document.form.end.value==""){
     	alert("请选择结束日期！");  
     	return;      
 	}
 	if(document.form.selectSmallType.options.length==0){
 		alert("请选择报警小类型！"); 
 		return;       
 	}
 	 var selectSmallType = document.form.selectSmallType;
	 var selectedItem = "";
	 for(var i = 0; i < selectSmallType.options.length; i++){
		selectedItem += "@" + selectSmallType.options[i].value;
	 }
 	document.form.selectedItem.value = selectedItem.substring(1);
	document.form.action="monitorAlert/searchResult.action"
	document.form.submit();
}

	
</script>
  </head>
  
  <body onload="init()">
  
  <s:form name="form" method="post" theme="simple" enctype="multipart/form-data"> 
  <br/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">报警>>查找 </td>
    <td width="85%" align="right" background="images/listconframe_07.gif"></td>
    <td width="1%" align="right" background="images/listconframe_07.gif"><img src="images/main20100521_39.gif" width="10" height="28" alt=""></td>
  </tr>
  <input type="hidden" name="selectedItem" id="selectedItem" value="" /> 
</table>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#f4fbff">
	<tr>
    	<td width="10" height="10"><img src="images/main20100521_48.gif" width="10" height="10" alt=""></td>
    	<td height="10" bgcolor="#FFFFFF"><img src="images/space.gif" width="1" height="1" /></td>
    	<td width="10" height="10" background="images/main20100521_50.gif"><img src="images/main20100521_50.gif" width="10" height="5" alt=""></td>
  	</tr>
  	<tr>
    <td width="10" background="images/main20100521_48.gif">&nbsp;</td>
    <div style=”color:red”>
  <s:fielderror />
</div> 
    <td height="6" bgcolor="#FFFFFF" class="STYLE1">
		 <dl class="settings_options">
      		<dt></dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr >
	      			 	<td width=200>
	      			 		报警类型
	      			 	</td>
	      			 	<td width=300>     
					      	<select id="monitorAlertType" name="monitorAlertType.code" onChange="onChange(this)"></select>
	      			 	</td>
	      			 </tr>  
	      			 <tr >
	      			 	<td width=200>
	      			 		开始时间
	      			 	</td>
	      			 	<td width=300>     
					      	<input id="start" name="start" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')||\'2020-10-01\'}'})"/>
	      			 	</td>
	      			 	<td width=200>
	      			 		结束时间
	      			 	</td>
	      			 	<td width=300>     
					      	<input id="end" name="end" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'start\')}',maxDate:'2020-10-01'})"/>
	      			 	</td>
	      			 	
	      			 </tr> 
	      			 <tr >
	      			 	<td width=200>
	      			 		选择小类型
	      			 	</td>
	      			 	<td >  
	      			 	<table>
	      			 	<tr>
	      			 	<td style="border:none;">
							<select name="smallType" size="5" multiple="multiple" id="smallType" style="width:140px;" ondblclick="changePosition('1')" ></select>
				            </td>
				            <td align="center" style="border:none;">
				            <input type="button" value=">" id="b1" onClick="changePosition('1')"/><br/>
							<input type="button" value='<' id="b2" onClick="changePosition('2')"/><br/>
				 
							<input type="button" value=">>" id="b3" onClick="changeAll('1')"/><br/>
							<input type="button" value="<<" id="b4" onClick="changeAll('2')"/><br/>
							</td>
				            <td style="border:none;">
				            <select name="selectSmallType" size="5" multiple="multiple" style="width:140px" id="selectSmallType" ondblclick="changePosition('2')" ></select>
				         </td>
	      			 	</tr>
	      			 	</table>
	      			 	</td>
	      			 	
	      			 </tr>  
	      		</table>
      			</dd>	
		</dl>
		  
		 
		<table class="STYLE4">
		<br/>
		   <tr>
		   	<td >
				<input name="button" type="button" class="mmBtn" onClick="doSearch()" value="查找">
			    <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="重置">   
			    <input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value="返回"> 
	      	</td>
	      </tr>
      	</table> 
      </td>
    <td width="10" background="images/main20100521_50.gif">&nbsp;</td>
  </tr>
  	<tr>
    <td width="10" height="10"><img src="images/main20100521_66.gif" width="10" height="28" alt=""></td>
    <td height="10" background="images/main20100521_67.gif">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    </table>
    </td>
    <td width="10" height="10"><img src="images/main20100521_69.gif" width="10" height="28" alt=""></td>
  </tr>
</table>
<script language="JavaScript">
  <!--
 
    var s1 = document.getElementById("smallType");//左面的select
    var s2 = document.getElementById("selectSmallType");//右面的select
 
	/**
	**  z=1时，从左到右；z=2时，从右到左
	**/
	function changePosition(z){
 
		var o1 = s1.options;
		if(z=="2"){
			o1 = s2.options;
		}
		 
		for(var i=0;i<o1.length;i++){
			 
			if(o1[i].selected){
				var newOption = document.createElement("option");
				 
				newOption.value = o1[i].value;
				newOption.innerHTML = o1[i].innerHTML;
				if(z=="2"){
					s1.appendChild(newOption);
				}else{
					s2.appendChild(newOption);
				}
				
				o1[i]=null;
				i--;
				
			}
		}
 
	}
 
	/**
	**  z=1时，从左到右；z=2时，从右到左
	**/
	function changeAll(z){
		
		var o1 = s1.options;
		if(z=="2"){
			o1 = s2.options;
		}
		var len = o1.length;
 
		for(var i=0;i<len;i++){
			var oldOption = o1[i];
			var newOption = document.createElement("option");
			newOption.value = oldOption.value;
			newOption.innerHTML = oldOption.innerHTML;
			if(z=="2"){
				s1.appendChild(newOption);
			}else{
				s2.appendChild(newOption);
			}
			
		}
		if(z=="2"){
			s2.innerHTML = "";
		}else{
			s1.innerHTML = "";
		}
 
	}
 
	/**
	**  当在输入框输入数据时，左面的select会根据输入值比对信息，显示含有输入值的数据
	**/
	function changeValue(z){
		var s1 = document.getElementById("smallType");//左面的select
		var s2 = document.getElementById("selectSmallType");//右面的select
		var o2 = s2.options;
 
		var data = document.getElementById("smallType").value;//存放数据的输入框(如果要用到项目中，这里一定要改)
		var ds = data.split(",");//数据用","隔开
		
		//如果输入值为空		
		if(z==""){
			s1.innerHTML = "";
			for(var i=0;i<ds.length;i++){
				var _va = 0;//用于比较左右select中的值是否相等
				var va = ds[i];
				//循环右面的select中的value，右面有的option左面就不应该有了
				for(var j=0;j<o2.length;j++){
					var _o2 = o2[j].innerHTML;
					if(va == _o2){
						_va = 1;
						break;
					}
				}
				//右面的option没有的，左面有
				if( _va == 0 ){
					var newOption = document.createElement("option");
					newOption.value = va;
					newOption.innerHTML = va;
				
					s1.appendChild(newOption);
				}
			}
		}else{//输入值不为空
			s1.innerHTML = "";
			for(var i=0;i<ds.length;i++){
				var _va = 0;//用于比较左右select中的值是否相等
				var va = ds[i];
				//左面的select必须包含输入值
				if(va.indexOf(z) != -1){
					//循环右面的select中的value，右面有的option左面就不应该有了
					for(var j=0;j<o2.length;j++){
						var _o2 = o2[j].innerHTML;
						if(va == _o2){
							_va = 1;
							break;
						}
					}
					//右面的option没有的，左面有
					if( _va == 0 ){
						var newOption = document.createElement("option");
						newOption.value = va;
						newOption.innerHTML = va;
					
						s1.appendChild(newOption);
					}
					
				}
			}
		}
		
	}
	
	//初始化数据
	function initData(){
		var s1 = document.getElementById("smallType");
		var data = document.getElementById("smallType").value;
		var ds = data.split(",");
	
		for(var i=0;i<ds.length;i++){
			var va = ds[i];
			var newOption = document.createElement("option");
			newOption.value = va;
			newOption.innerHTML = va;
		
			s1.appendChild(newOption);	
		}
	}
 
	initData();
  //-->
  </SCRIPT>
<br/>

</s:form>

</body>
</html>
