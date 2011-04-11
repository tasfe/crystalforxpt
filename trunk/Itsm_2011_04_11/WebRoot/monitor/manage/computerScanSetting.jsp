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
	if(0 == document.getElementById("scanGapTime").value){
		document.getElementById("divScanTimers").style.display="";//显示
	} else {
		document.getElementById("divScanTimers").style.display="none";//隐藏
	}
}
//设置定时扫描时间
function setScanTimers(obj) {

	if(obj.value!=null&&obj.value!=""&&0==obj.value){
		document.getElementById("divScanTimers").style.display="";//显示
	} else {
		document.getElementById("divScanTimers").style.display="none";//隐藏
	}
}
//设置默认值
function setDefaultValue(){
	 document.getElementById("scanGapTime").value=0;
	 document.getElementById("divScanTimers").style.display="";//显示
	 document.getElementById("scanHour0").value=10;
	 document.getElementById("scanHour1").value=16;
	 document.getElementById("scanHour2").value=20;
	 document.getElementById("arpNbt").checked=false; 
	 document.getElementById("pingScan").checked=false; 
	 document.getElementById("ignoreError").checked=true; 
	 document.getElementsByName("computerScan.l23Scan")[0].checked=true;
	 document.getElementsByName("computerScan.changeRefresh")[1].checked=true;
	 document.getElementById("newHost").checked=false; 
	 document.getElementById("ipChangedAlert").checked=true; 
	 document.getElementById("linkChangedAlert").checked=true; 
	 document.getElementById("computerNameChangedAlert").checked=false; 
	 document.getElementById("domainChangedAlert").checked=false; 
	 document.getElementById("userChangedAlert").checked=false; 
	 document.getElementById("changeAlert").checked=false; 
}
//提交
function doSubmit(){
	if(document.getElementById("scanGapTime").value==null||document.getElementById("scanGapTime").value==""){
		alert("请选择扫描间隔！");
		return ;
	}
	document.form.action="monitorSystemSettings/saveComputerScanSetting.action"
	document.form.submit();
}
//全选/全不选
function selectAll(tempControl){
     	var theBox=tempControl;
      	xState=theBox.checked;
      	var el = document.getElementsByTagName('input');
      	var len = el.length;
        for(var i=0; i<len; i++) {
        	if(el[i].type=="checkbox"&& el[i].name.toString().indexOf("subnetList",0)>-1) 
        	 el[i].checked = xState;
        }
}
	
</script>
  </head>
  
  <body onload="init()">
  <s:form name="form" method="post" theme="simple"> 
  <br/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">管理>>计算机扫描 </td>
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
    <td height="6" bgcolor="#FFFFFF" class="STYLE1">
   
    
		 <dl class="settings_options">
      		<dt>选项</dt>
      			<dd id="" >
      			 <table class="STYLE4">
	      			 
	      			 <tr>
	      			 	<td>
	      			 	间隔   <s:select 
	      			 			id="scanGapTime"
	      			 			list="#{0:'定时',10:'10分钟',30:'30分钟',60:'60分钟'}" 
	      			 			name="computerScan.scanGapTime" 
	      			 			value="computerScan.scanGapTime" 
	      			 			onchange="setScanTimers(this)" 
	      			 			theme="simple" 
	      			 			headerKey="" 
	      			 			headerValue="请选择"></s:select>
	      			 	  <s:if test="computerScan.arpNbt==0">
					     	 <input type="checkbox" id="arpNbt" name="computerScan.arpNbt"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="arpNbt" name="computerScan.arpNbt"  checked value="1" >
					      </s:else>
					      Netbios端口
					      
					      <s:if test="computerScan.pingScan==0">
					     	 <input type="checkbox" id="pingScan" name="computerScan.pingScan"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="pingScan" name="computerScan.pingScan"  checked value="1" >
					      </s:else>
					      Ping预扫描
					      
					      <s:if test="computerScan.ignoreError==0">
					     	 <input type="checkbox" id="ignoreError" name="computerScan.ignoreError"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="ignoreError" name="computerScan.ignoreError"  checked value="1" >
					      </s:else>
					     	忽略转发表异常
					      
	      			 	<div id="divScanTimers">
	      			 		定时扫描时间1: <s:select id="scanHour0" list="#{-1:'不定时',0:'0',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20',21:'21',22:'22',23:'23'}" name="computerScan.scanHour0" value="computerScan.scanHour0" theme="simple" headerKey="" headerValue="请选择"></s:select>
	      			 		定时扫描时间2: <s:select id="scanHour1" list="#{-1:'不定时',0:'0',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20',21:'21',22:'22',23:'23'}" name="computerScan.scanHour1" value="computerScan.scanHour1" theme="simple" headerKey="" headerValue="请选择"></s:select>
	      			 		定时扫描时间3: <s:select id="scanHour2"  list="#{-1:'不定时',0:'0',1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',19:'19',20:'20',21:'21',22:'22',23:'23'}" name="computerScan.scanHour2" value="computerScan.scanHour2" theme="simple" headerKey="" headerValue="请选择"></s:select>
	      			 	</div>
	      			 	</td>
	      			 	<td width=150></td>
	      			 	 </tr>
	      		</table>

      			</dd>
			
      			
      		
		</dl>
		 <dl class="settings_options">
      		<dt>转发表</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
      			 		<td>
      			 		<s:radio   name="computerScan.l23Scan"  list="#{0:'ARP表+MAC表',1:'仅ARP表',2:'仅MAC表'}" ></s:radio>
      			 		</td>
      			   </tr>
      			 	
      			 </table>
      			</dd>
		</dl>
		<dl class="settings_options">
      		<dt>策略</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
      			 		<td>
      			 		<s:radio   name="computerScan.changeRefresh"  list="#{0:'不报警但归档',1:'报警并归档'}" ></s:radio>
      			 		</td>
      			   </tr>
      			 	
      			 </table>
      			</dd>
		</dl>
		<dl class="settings_options">
      		<dt>报警</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
      			 		<td>
      			 		  <s:if test="computerScan.newHost==0">
					     	 <input type="checkbox" id="newHost" name="computerScan.newHost"    value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="newHost" name="computerScan.newHost"  checked value="1" >
					      </s:else>
					     	新计算机
					      <s:if test="computerScan.ipChangedAlert==0">
					     	 <input type="checkbox" id="ipChangedAlert" name="computerScan.ipChangedAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="ipChangedAlert" name="computerScan.ipChangedAlert"  checked value="1" >
					      </s:else>
					     	IP地址改变
					     	 <s:if test="computerScan.linkChangedAlert==0">
					     	 <input type="checkbox" id="linkChangedAlert" name="computerScan.linkChangedAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="linkChangedAlert" name="computerScan.linkChangedAlert"  checked value="1" >
					      </s:else>
					     	交换机接口改变
					     	 <s:if test="computerScan.computerNameChangedAlert==0">
					     	 <input type="checkbox" id="computerNameChangedAlert" name="computerScan.computerNameChangedAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="computerNameChangedAlert" name="computerScan.computerNameChangedAlert"  checked value="1" >
					      </s:else>
					     	计算机名改变
					     	 <s:if test="computerScan.domainChangedAlert==0">
					     	 <input type="checkbox" id="domainChangedAlert" name="computerScan.domainChangedAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="domainChangedAlert" name="computerScan.domainChangedAlert"  checked value="1" >
					      </s:else>
					     	域或组改变
					     	 <s:if test="computerScan.userChangedAlert==0">
					     	 <input type="checkbox" id="userChangedAlert" name="computerScan.userChangedAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="userChangedAlert" name="computerScan.userChangedAlert"  checked value="1" >
					      </s:else>
					     	登录名改变
					     	 <s:if test="computerScan.changeAlert==0">
					     	 <input type="checkbox" id="changeAlert" name="computerScan.changeAlert"  value="1" >
					      </s:if>
					      <s:else>
					      	<input type="checkbox" id="changeAlert" name="computerScan.changeAlert"  checked value="1" >
					      </s:else>
					     	计算机信息改变
      			 		</td>
      			   </tr>
      			 	
      			 </table>
      			</dd>
		</dl>
		<dl class="settings_options">
      		<dt>扫描分区设置</dt>
      			<dd id="">
      			 <table class="STYLE4">
      			 	<tr>
      			 		<td>
      			 		<table width="500" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
      			 		  <tr bgcolor="#b5d6e6">
						  	<td height="26" width="50" align="center" style="text-align: center" bgcolor="#deebf1">序号</td>
						    <td height="26" align="center" bgcolor="#deebf1">分区</td>
						    <td align="center" bgcolor="#deebf1">类型</td>
						    <td align="center" bgcolor="#deebf1"><input type="checkbox" onclick="javascript:selectAll(this);" />扫描</td>
						  </tr>
						  <%int i=1; %>
						  <s:iterator value="subnetList" status="index">
						  <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
						  <td height="26" align="center"><%=i++ %>
						  	<input type="hidden" name="subnetList[<%=i%>].id" value="<s:property value="id"/>" >
						  </td>
						  <td><s:property value="name"/></td>
						  <td><s:property value="monitorSubnetType.name"/></td>
						  <td align="center">
						  	  
							  <s:if test="scan==0">
						     	 <input type="checkbox" id="subnetScan<%=i%>" name="subnetList[<%=i%>].scan"  value="1" >
						      </s:if>
						      <s:else>
						      	<input type="checkbox" id="subnetScan<%=i%>" name="subnetList[<%=i%>].scan" checked value="1" >
						      </s:else>
						   </td>
						  </tr>
						  </s:iterator>
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
