<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link href="<%=path%>/theme/css.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
    <script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='dwr/interface/MonitorAlertPolicyService.js'></script>
	<script type='text/javascript' src='dwr/engine.js'></script>
    <title>输入新建策略名</title>
    <style type="text/css">
		<!--
		.STYLE4 {color: #03515d;
			font-size: 12px;
		}
		.STYLE1 {color:#022e44;
			font-size: 12px;
			font-weight:bold;
		}
		
		-->
	</style>
	<script>
	function doSubmit(){
		
		var name = document.getElementById("name").value;
		MonitorAlertPolicyService.checkName(name,"",afterCheck);
		
	}
	function afterCheck(data){
		if(data){
			alert("已有此名称的报警策略，请重新填写！");
			return;
		}
		window.returnValue=document.getElementById("name").value;
		window.close();
	}
	</script>
  </head>
  
  <body>
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">输入新建策略名</td>
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
    <td  bgcolor="#FFFFFF" class="STYLE1">
    	<table class="STYLE4" align="center">
		   <tr>
		   	<td align="center">
				 <input type="text" id="name"/>
				  <br/>
	      	</td>
	      </tr>
      	</table> 
    	<table class="STYLE4" align="center">
		   <tr>
		   	<td align="center">
				<input name="button" type="button" class="mmBtn" onClick="doSubmit()" value="保存"> 
			    <input name="button" type="button" class="mmBtn" onClick="window.close()" value="取消">   
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
  </body>
</html>
