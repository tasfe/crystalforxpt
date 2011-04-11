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
	 document.getElementById("topN").value=10;
	 document.getElementById("snmpRetry").value=1; 
	 document.getElementById("snmpTimeOut").value=5; 
}
 
//提交
function doSubmit(){
	if(document.getElementById("topN").value==null||document.getElementById("topN").value==""){
		alert("请选择Top N！");
		return ;
	}
	if(document.getElementById("snmpRetry").value==null||document.getElementById("snmpRetry").value==""){
		alert("SNMP重试次数！");
		return ;
	}
	if(document.getElementById("snmpTimeOut").value==null||document.getElementById("snmpTimeOut").value==""){
		alert("SNMP 网络超时时间！");
		return ;
	}
	document.form.action="monitorSystemSettings/saveOtherSetting.action"
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
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">管理>>其它 </td>
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
      		<dt></dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 <tr height=30>
	      			 	 
	      			 	<td width=200>
	      			 	 
	      			 		Top N<s:select 
	      			 			id="topN"
	      			 			list="#{10:'10',20:'20',30:'30'}" 
	      			 			name="other.topN" 
	      			 			value="other.topN" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
	      			 	</td>
	      			 	<td width=300>     
					      	SNMP重试次数<s:select 
	      			 			id="snmpRetry"
	      			 			list="#{0:'0',1:'1',2:'2'}" 
	      			 			name="other.snmpRetry" 
	      			 			value="other.snmpRetry" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
					      
	      			 	</td>
	      			 	<td width=300>     
					      	SNMP 网络超时时间<s:select 
	      			 			id="snmpTimeOut"
	      			 			list="#{1:'1',2:'2',3:'3',4:'4',5:'5'}" 
	      			 			name="other.snmpTimeOut" 
	      			 			value="other.snmpTimeOut" 
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
