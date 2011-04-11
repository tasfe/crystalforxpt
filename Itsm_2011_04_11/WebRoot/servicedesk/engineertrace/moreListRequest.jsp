<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/theme/custo.css">
	<link href="../css/style.css" rel="stylesheet" type="text/css">
  </head>
  	<script type="text/javascript">
	function comptime(beginTime,endTime){   
		if(!beginTime || !endTime){
			return "";
		}
		var beginTimes=beginTime.split(' ');  
		var endTimes=endTime.split(' '); 
		var nowTimes=getNowDate().split(' ');
		var beginTimesSplit= beginTimes[0].split('-'); 
		var endTimesSplit=endTimes[0].split('-');
		var nowTimesSplit=nowTimes[0].split('-');
		
		beginTime=beginTimesSplit[1]+'-'+(beginTimesSplit[2])+'-'+beginTimesSplit[0]+' '+beginTimes[1];   
		endTime=endTimesSplit[1]+'-'+(endTimesSplit[2])+'-'+endTimesSplit[0]+' '+endTimes[1]; 
		nowTime=nowTimesSplit[1]+'-'+(nowTimesSplit[2])+'-'+nowTimesSplit[0]+' '+nowTimes[1]; 
		
		var timeout=(Date.parse(endTime)-Date.parse(nowTime))/3600/1000;
		if(timeout<0){
			return "<font style='color:red;'>超时</font>"+"("+timeout.toFixed().substring(1,timeout.toFixed().length)+")";
		}
		var timeGap =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000; 
		return timeGap<=0?"":timeGap.toFixed();   
	}
	function getNowDate(){//获取当前时间(服务器时间)
		return "<%=(request.getAttribute("nowDate"))%>";
	}
	</script>
  <body leftmargin="2" topmargin="0" marginwidth="0" marginheight="0"
		style="padding-right: 1px; padding-bottom: 2px; padding-top: 2px">
		
	<table align="center" cellspacing=0 cellpadding=0 border=0
			width="100%" class="datagrid" >
			<tr id="tab1">
				<th width="15%" nowrap class="mmBtn"
					style="text-align: center">
					服务单号
				</th>
				<th width="10%" nowrap class="mmBtn"
					style="text-align: center">
					服务类别
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					优先级
				</th>
				<th width="12%" nowrap class="mmBtn"
					style="text-align: center">
					申请时间
				</th>
				<th width="12%" nowrap class="mmBtn"
					style="text-align: center">
					希望完成时间
				</th>
				<th width="15%" nowrap class="mmBtn"
					style="text-align: center">
					剩余时间(小时)
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					申请人
				</th>
				<th width="5%" nowrap class="mmBtn" style="text-align: center">
					查看
				</th>
			</tr>
			
			<tr id="tab11" >
				<td colspan="8" class="menu">
					<img src="../img/index.jpg" align="absMiddle">
					<b>完整列表</b>
				</td>
			</tr>
				<s:iterator value="moreList" var="serviceRequest">
						<tr >
							<td height="29">
								<table width="100%" border="0" cellspacing="0"
									cellpadding="0">
									<tr>
										<td width="1%">
											<img src="../img/incident.gif"
												style="background-color: ">
										</td>
										<td width="87%" nowrap>
											&nbsp;&nbsp;
											<s:property value="requestNo" />
											&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td align="center">
								<s:property value="serviceCategory.itemZh" />
							</td>
							<td align="center">
								<s:property value="priorityLvl" />
							</td>
							<td align="center">
								<s:date name="submintTime" />
							</td>
							<td align="center">
								<s:date name="expectTime" />
							</td>
							 <td align="center">
				           		<script type = "text/javascript" >    
				           			var submitTime="<s:date name="submintTime"/>";
				           			var expectTime="<s:date name="expectTime"/>"; 
				      				document.write(comptime(submitTime,expectTime));
				     			</script>
							</td>
							<td align="center">
								<s:property value="usersByRequestUser.truename" />
							</td>
							<td align=center nowrap>
								<a
									href="${pageContext.request.contextPath }/engineertrace/show.action?flag=0&serviceRequest.id=<s:property value="id" />"><img
										src='../img/viewdetail.gif' border=0 width=18 height=18>
								</a>
							</td>
						</tr>
		</s:iterator>
	</table>
  </body>
</html>
