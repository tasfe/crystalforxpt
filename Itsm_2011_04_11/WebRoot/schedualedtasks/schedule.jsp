<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>制定计划</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" language="javascript">
			function showTr(val,id1,id2,id3) {
				if(val=='Time') {
					document.getElementById("tr0").style.display="";
					document.getElementById("tr1").style.display="";
					document.getElementById("tr4").style.display="";
					document.getElementById("tr2").style.display="none";
					document.getElementById("tr3").style.display="none";
					document.getElementById("trmonth").style.display="";
					document.getElementById("trday").style.display="";
				}else if(val=='Cron') {					
					document.getElementById("tr2").style.display="";
					document.getElementById("tr0").style.display="none";
					document.getElementById("tr1").style.display="none";
					document.getElementById("tr3").style.display="none";
					document.getElementById("tr4").style.display="none";
				}else {
					document.getElementById("tr0").style.display="";
					document.getElementById("tr3").style.display="";
					document.getElementById("tr4").style.display="";
					document.getElementById("tr1").style.display="none";
					document.getElementById("tr2").style.display="none";
					document.getElementById("trmonth").style.display="";
					document.getElementById("trday").style.display="none";
				}
				
			}
			function show(value){
				if(value=='D') {
					document.getElementById("trday").style.display="none";
					document.getElementById("trmonth").style.display="";
				}
				else if(value=='M') {
					document.getElementById("trmonth").style.display="none";
					document.getElementById("trday").style.display="";
				}else  {
					document.getElementById("trmonth").style.display="";
					document.getElementById("trday").style.display="";
				}
			}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
					制定计划
				</td>
			</tr>
		</table>
		<div style="position: absolute; overflow-x:auto; overflow-y:scroll; width: 100%; padding-top: 5px;">
				<s:form action="cron.action" name="myForm" id="myForm" method="post" theme="simple">
				<s:hidden type="hidden" name="id"/>
				<table width="99%" height="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6" class="tb-list">
					<tr>
						<td height="99%" bgcolor="#FFFFFF" valign="top">
							<table width="100%" border="0" cellspacing="1" cellpadding="2">
								<tr>
									<td height="31" bgcolor="#deebf1"  width="15%" >
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;执行周期:
									</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">
										<table border="0" cellspacing="0" cellpadding="0">
								  			<tr> 
												<td style="padding-top: 2px"><input type="radio" name="schedualed.type" value="Time" style="border: 0px" checked onClick="showTr('Time','tr1','tr2','tr3')"></td>
												<td nowrap>时间间隔&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualed.type" value="Cron" style="border: 0px" onClick="showTr('Cron','tr2','tr1','tr3')"></td>
												<td nowrap>Cron表达式&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td style="padding-top: 2px"><input type="radio" name="schedualed.type" value="Week" style="border: 0px" onClick="showTr('Week','tr3','tr2','tr1')"></td>
												<td nowrap>星期&nbsp;&nbsp;&nbsp;&nbsp;</td>													
								  			</tr>
										</table>
									</td>									
								</tr>
								<tr id="tr1" style="display:">
									<td height="31" width="15%" bgcolor="#deebf1"><img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;时间间隔:</td>
									<td colspan=3 bgcolor="#EBF4F5">
										<input id="timer" type="text" name="schedualed.distance" size=40 style="width:5%" onKeyUp="if(this.value<1000000){}else{this.value='0'}">
										<select name="schedualed.distanceType" onChange="show(this.value);">
											<option value="F">分</option>
											<option value="H">时</option>
											<option value="D">日</option>
											<option value="M">月</option>
										</select>
									</td>														
								</tr>
								<tr id="tr3"  style="display:none">
									<td height="31" bgcolor="#deebf1" width="15%" >
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;选择星期：
									</td>
									<td bgcolor="#EBF4F5">
										<select name="schedualed.week" >											
											<option value="2">星期一 </option>
											<option value="3">星期二</option>
											<option value="4">星期三</option>
											<option value="5">星期四 </option>
											<option value="6">星期五</option>
											<option value="7">星期六</option>
											<option value="1">星期日</option>
										</select>
									</td>																
								</tr>
								<tr  id="tr0" style="display:">
									<td height="31" bgcolor="#deebf1"  width="15%" ><img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;起始时间：</td>
									<td bgcolor="#EBF4F5" style="padding-top: 5px">
										<table width="100%" border="0" cellspacing="1" cellpadding="2"><tr><td>
										年&nbsp;&nbsp;<input type="text" id="yearStart" name="schedualed.yearStart" onclick="WdatePicker({dateFmt:'yyyy'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('yearStart'),dateFmt:'yyyy'})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;<b>~</b>&nbsp;
										<input type="text" id="yearEnd" name="schedualed.yearEnd" onclick="WdatePicker({dateFmt:'yyyy'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('yearEnd'),dateFmt:'yyyy'})" src="../js/DatePicker/skin/datePicker.gif" />
										<div id="divmonth" style="display:">
										</td>
										</tr>
										<tr id="trmonth"  style="display:"><td>
										月&nbsp;&nbsp;<input type="text" id="monthStart" name="schedualed.monthStart" onclick="WdatePicker({dateFmt:'MM'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('monthStart'),dateFmt:'MM'})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;<b>~</b>&nbsp;
										<input type="text" id="monthEnd" name="schedualed.monthEnd" onclick="WdatePicker({dateFmt:'MM'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('monthEnd'),dateFmt:'MM'})" src="../js/DatePicker/skin/datePicker.gif" />
										</td>
										</tr>
										<tr id="trday"  style="display:"><td>
										日&nbsp;&nbsp;<input type="text" id="dayStart" name="schedualed.dayStart" onclick="WdatePicker({dateFmt:'dd'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('dayStart'),dateFmt:'dd'})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;<b>~</b>&nbsp;
										<input type="text" id="dayEnd" name="schedualed.dayEnd" onclick="WdatePicker({dateFmt:'dd'})" style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('dayEnd'),dateFmt:'dd'})" src="../js/DatePicker/skin/datePicker.gif" />
										</td>
										</tr>
										</table>
									</td>
								</tr>								
								<tr id="tr2"  style="display:none">
									<td height="31" bgcolor="#deebf1" width="15%" >
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;Cron表达式：
									</td>
									<td bgcolor="#EBF4F5">
										<input name="schedualed.cronExpress" type="text" />
									</td>																
								</tr>								
								<tr  id="tr4"  style="display:">
									<td height="31" bgcolor="#deebf1" width="15%" >
										<img src="${pageContext.request.contextPath }/img/jiedian.gif" width="10" height="9">&nbsp;时分：
									</td>
									<td  bgcolor="#EBF4F5">
										<input type="text" id="time" name="schedualed.time" onclick="WdatePicker({dateFmt:'HH:mm'})"  style="width:5%">
										<img onClick="WdatePicker({el:$dp.$('time'),dateFmt:'HH:mm'})" src="../js/DatePicker/skin/datePicker.gif" />&nbsp;
									</td>																
								</tr>														
							</table>
						</td>
					</tr>						
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="12" style="padding-top: 8px" nowrap>
						</td>
						<td align="center" style="padding-top: 8px; padding-bottom: 0px">
							<input type="button" value="提交" class=mmBtn name="submit" onClick="">&nbsp;
							<input type="button" onClick="history.go(-1)" value="返回" class=mmBtn name="button">
						</td>
					</tr>
				</table>
				</s:form>
			</div>
	</body>
</html>
