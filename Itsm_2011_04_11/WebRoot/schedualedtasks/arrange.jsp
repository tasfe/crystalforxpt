<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>修改任务工程师</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${pageContext.request.contextPath }/css/style.css"rel="stylesheet" type="text/css">
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/interface/RoleteamDAO.js'></script>
		<script type="text/javascript" language="javascript">		
		function getInfo2(){
			var roleteam = document.getElementById("engineerteam").value;
			document.getElementById("engineer").src="../role/queryforTask2.action?role.id="+roleteam;
		}
		function getDAO(){ //取出类别
		   	RoleteamDAO.findAllByType(callbackroleteam);
		}
		function callbacklocation(data){  //显示出地理位置
		   	dwr.util.removeAllOptions("location");
		   	dwr.util.addOptions("location",{'-1':'--请选择--'});
		   	dwr.util.addOptions("location",data,"id","name");   
		}
		function callbackroleteam(data){  //显示出工程师分组
		   dwr.util.removeAllOptions("engineerteam");
		   document.getElementById("engineer").src="about:blank";
		   dwr.util.removeAllOptions("engineerteam");
		   dwr.util.addOptions("engineerteam",{'-1':'--请选择--'});
		   dwr.util.addOptions("engineerteam",data,"id","name");   
		}	
		function submit(){
			var id=document.getElementById("id").value;
			var members=document.getElementById("members").value;
			var dateStart=document.getElementById("dateStart").value;
			if(confirm('您确认要修改吗？')){ window.location.href="updatebywd.action?id="+id+"&members="+members+"&dateStart="+dateStart;}
		}
		function Table(id,val){
			document.getElementById('dateStart').value=val;
			var oldid = document.getElementById("tmp").value;
			if (id!=oldid) {
				document.getElementById(oldid).background="${pageContext.request.contextPath }/images/header_bg.jpg";
				document.getElementById(id).background="${pageContext.request.contextPath }/images/conlistframe_07.gif";
				document.getElementById("tmp").value=id;
			}
		}
		</script>
	</head>
	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0"marginheight="0" style="overflow: hidden;">
		<s:hidden id="tmp" name="tmp" value="id1"/>
		<s:hidden id="id" name="id"/>
		<s:hidden id="members" name="members"/>
		<s:hidden id="dateStart" name="dateStart"/>
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center" background="../images/main20100521_582.gif"style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">修改任务工程师
				</td>
			</tr>
		</table><br>
<!--		<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">-->
			<table width="99%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
						<table width="100%" border="0" cellspacing="3" cellpadding="0" tyle="border: 1px inset" bgcolor="#F9F9F9">
							<tr>
								<s:iterator value="weekMap" var="day">					
									<s:if test="#day.key==1"><td id="id1" style="cursor:hand" align="center" background="${pageContext.request.contextPath }/images/conlistframe_07.gif" height="40" width="14%" onClick="Table('id1','${value}')">星期一</td></s:if>
									<s:elseif test="#day.key==2"><td id="id2" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id2','${value}')">星期二</td></s:elseif>
									<s:elseif test="#day.key==3"><td id="id3" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id3','${value}')">星期三</td></s:elseif>
									<s:elseif test="#day.key==4"><td id="id4" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id4','${value}')">星期四</td></s:elseif>
									<s:elseif test="#day.key==5"><td id="id5" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id5','${value}')">星期五</td></s:elseif>
									<s:elseif test="#day.key==6"><td id="id6" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id6','${value}')"><a style='color:red'>星期六</a></td></s:elseif>
									<s:elseif test="#day.key==7"><td id="id7" style="cursor:hand" background="${pageContext.request.contextPath }/images/header_bg.jpg" align="center" width="14%" onClick="Table('id7','${value}')"><a style='color:red'>星期日</a></td></s:elseif>									
								</s:iterator>
							</tr>							
						</table>
						<table width="100%" border="0" cellspacing="3" cellpadding="0" tyle="border: 1px inset" bgcolor="#F9F9F9">							
							<tr bgcolor="#EBF4F5">
								<td height="12"  align="center" colspan=1>工作组:</td>
								<td colspan=6 style="padding: 0px; padding-left: 0px">
    								<select style="width: 180px" name="role.id" id="engineerteam"  onChange="getInfo2();">
       									<option value="-1">--请选择--</option>
    								</select>   																
								</td>
							</tr>
							<tr>
								<td valign="top" bgcolor="#FFFFFF"  colspan=7>
									<iframe frameborder="0" height="100%" width="100%" scrolling="yes" style="border: 1px inset" name="engineer" id="engineer"></iframe>
								</td>
							</tr>
							
						</table>							
					</td>
				</tr>
			</table>
<!--		</div>-->
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tr>
				<td align="center" nowrap style="padding-top: 6px; padding-bottom: 1px">
					<input name="button1" type="button" class="mmBtn" onClick="submit()" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="button2" type="button" class="mmBtn" onClick="history.go(-1)" value="后退">
				</td>
			</tr>
		</table>
	</body>
</html>
