<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
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
	 
}
//设置默认值
function setDefaultValue(){
	 document.getElementById("checkGap").value=5;
	 document.getElementById("errorGap").value=1; 
}
 
//提交
function doSubmit(){
	if(document.getElementById("checkGap").value==null||document.getElementById("checkGap").value==""){
		alert("请选择故障检查时间间隔！");
		return ;
	}
	if(document.getElementById("errorGap").value==null||document.getElementById("errorGap").value==""){
		alert("请选择异常后的监测时间间隔！");
		return ;
	}
	document.form.action="monitorSystemSettings/saveFaultScanSetting.action"
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
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">管理>>故障检查 </td>
    <td width="85%" align="right" background="images/listconframe_07.gif"></td>
    <td width="1%" align="right" background="images/listconframe_07.gif"><img src="images/main20100521_39.gif" width="10" height="28" alt=""></td>
  </tr>
  
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
      		<dt>选项</dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr height=30>
	      			 	 
	      			 	<td width=200>
	      			 	 
	      			 		故障/阈值检查时间间隔<s:select 
	      			 			id="checkGap"
	      			 			list="#{1:'1分钟',2:'2分钟',3:'3分钟',4:'4分钟',5:'5分钟',6:'6分钟',7:'7分钟',8:'8分钟',9:'9分钟',10:'10分钟'}" 
	      			 			name="faultScan.checkGap" 
	      			 			value="faultScan.checkGap" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
	      			 	</td>
	      			 	<td width=300>     
					      	异常后的监测时间间隔<s:select 
	      			 			id="errorGap"
	      			 			list="#{1:'1分钟',2:'2分钟',3:'3分钟',4:'4分钟',5:'5分钟',6:'6分钟',7:'7分钟',8:'8分钟',9:'9分钟',10:'10分钟'}" 
	      			 			name="faultScan.errorGap" 
	      			 			value="faultScan.errorGap" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
					      
	      			 	</td>
	      			 	 
	      			 </tr>   
	      		</table>
      			</dd>	
		</dl>
		  
		 
		<table class="STYLE4">
		<br/>
		   <tr>
		   	<td >
				<input name="button" type="button" class="mmBtn" onClick="doSubmit()" value="保存">
			    <input name="button" type="button" class="mmBtn" onClick="window.location.reload()" value="重置">  
			    <input name="button" type="button" class="mmBtn" onClick="setDefaultValue()" value="默认">   
			    <input name="button" type="button" class="mmBtn" onClick="history.go(-1)" value="取消"> 
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

<br/>

</s:form>

</body>
</html>
