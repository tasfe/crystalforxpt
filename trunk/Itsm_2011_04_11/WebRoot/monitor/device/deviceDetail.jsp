<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>查看设备</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/common.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/commonstyles.css" rel="stylesheet" type="text/css">
		<link href="<%=path%>/css/monitor/tabMenu.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/monitor/tools.js"></script>
		<script type="text/javascript" src="<%=path%>/js/monitor/ceruleanBlue.js"></script>
		<script type="text/javascript" src="<%=path%>/js/monitor/fxloader.js"></script>
		<script type='text/javascript' src='<%=path%>/dwr/util.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceService.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorDeviceTypeDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/interface/MonitorVendorMacDAO.js'></script>
		<script type='text/javascript' src='<%=path%>/dwr/engine.js'></script>
		
		<script type="text/javascript" src="<%=path%>/Charts/FusionCharts.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/fusionCharts.css">
		 <script type="text/javascript">
        var GB_ROOT_DIR = "<%=path%>/js/greybox/";
		</script>
		<script type="text/javascript" src="<%=path%>/js/greybox/AJS.js"></script>
		<script type="text/javascript" src="<%=path%>/js/greybox/AJS_fx.js"></script>
		<script type="text/javascript" src="<%=path%>/js/greybox/gb_scripts.js"></script>
		<link href="<%=path%>/js/greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />
		<script language="JavaScript" type="text/javascript">
			//EASY TABS 1.2 - MENU SETTINGS
			var tablink_idname = new Array("tablink")
			var tabcontent_idname = new Array("tabcontent") 
			var tabcount = new Array("6")
			var loadtabs = new Array("1")  
			var autochangemenu = 2;
			var changespeed = 2;
			var stoponhover = 1;
			/*Swich EasyTabs Functions - no need to edit something here*/
			function easytabs(menunr, active) {if (menunr == autochangemenu){currenttab=active;}if ((menunr == autochangemenu)&&(stoponhover==1)) {stop_autochange()} else if ((menunr == autochangemenu)&&(stoponhover==0))  {counter=0;} menunr = menunr-1;for (i=1; i <= tabcount[menunr]; i++){document.getElementById(tablink_idname[menunr]+i).className='tab'+i;document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';}document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' tabactive';document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';}var timer; counter=0; var totaltabs=tabcount[autochangemenu-1];var currenttab=loadtabs[autochangemenu-1];function start_autochange(){counter=counter+1;timer=setTimeout("start_autochange()",1000);if (counter == changespeed+1) {currenttab++;if (currenttab>totaltabs) {currenttab=1}easytabs(autochangemenu,currenttab);restart_autochange();}}function restart_autochange(){clearTimeout(timer);counter=0;start_autochange();}function stop_autochange(){clearTimeout(timer);counter=0;}
			
			window.onload=function(){
			var menucount=loadtabs.length; var a = 0; var b = 1; do {easytabs(b, loadtabs[a]);  a++; b++;}while (b<=menucount);
			if (autochangemenu!=0){start_autochange();}
			}
		
		
			var ip = "";
			var sec = 0;
			function initPage(){
				ip = document.getElementById("ip").value;
				var deviceType = document.getElementById("deviceType").value;
				if(deviceType==20) return;
				MonitorDeviceService.getDeviceUseData(ip,callbackInitPieChart)
				
				
				if(deviceType==6){
					initRealTimeAkcpChart(ip);
					MonitorDeviceService.deviceDataHistory(ip,1,2,callbackBuildXmlString1);
					MonitorDeviceService.deviceDataHistory(ip,1,3,callbackBuildXmlString2);
				}else{
					initRealTimeCpuAvailabilityChart(ip);
					MonitorDeviceService.deviceDataHistory(ip,1,1,callbackBuildXmlString);
				}
				
				//得到设备的响应时间，每5秒刷新一下
				timerID = setInterval("getDeviceDelayTime('"+ip+"')",30000);
				
			}
			//得到设备的响应事件
			function getDeviceDelayTime(ip) {
				MonitorDeviceService.getDeviceDelayTime(ip,callbackGetDeviceDelayTime);
			    
			}
			function callbackGetDeviceDelayTime(data){
				if(data>-1){
					document.getElementById("num").innerHTML = data;
					document.getElementById("ms").innerHTML = 'ms';
				}else{
					document.getElementById("num").innerHTML = '超时';
					document.getElementById("ms").innerHTML = '';
				}
					
			}
			//设置显示CPU历史
			function setDeviceHistory(day){
				MonitorDeviceService.deviceDataHistory(ip,day,1,callbackBuildXmlString);
			} 
			 
			//设置显示温湿度历史
			function setDeviceHistory1(day){
				MonitorDeviceService.deviceDataHistory(ip,day,2,callbackBuildXmlString1);
				MonitorDeviceService.deviceDataHistory(ip,day,3,callbackBuildXmlString2);
			} 
			//初始化饼图 ——设备可用性
			function callbackInitPieChart(data){
				var myChart = new FusionCharts("<%=path%>/Charts/Pie3D.swf", "myChartId", "300", "175", "0", "0");
				var url = "<chart showPercentValues='1' pieRadius='70' >"
						+"<set label='有反应' value='"+data[0]+"' color='00FF00' />"
						+"<set label='无反应' value='"+data[1]+"' color='FF0000' />"
						+"</chart>";
				myChart.setDataXML(url);
				myChart.render("chartdiv");
			}
			
			//初始化实时显示CPU利用率									 
			function initRealTimeCpuAvailabilityChart(ip){
				var myChart = new FusionCharts("<%=path%>/Charts/AngularGauge.swf", "myChartId", "300", "150", "0", "0");
				var url = "<chart  palette='1' lowerLimit='0' upperLimit='100' gaugeStartAngle='180' gaugeEndAngle='0'   tickValueDistance='20' showValue='1' "
						+"majorTMNumber='10' " //需要将仪表盘分成的等份值
						+"numberSuffix='%'"
						+"dataStreamURL='/monitorDevice/getRealTimeAvailabilityData.action?type=1&ip="+ip+"' refreshInterval='30'>"
						+"<colorRange>"
						+"<color minValue='0' maxValue='30' />"
						+"<color minValue='30' maxValue='50' />"
						+"<color minValue='50' maxValue='80' />"
						+"<color minValue='80' maxValue='100' />"
						+"</colorRange>"
						+"<dials>"
						+"<dial id='Dial1' value='0' rearExtension='10' />"
						+"</dials>"
						+"</chart>";
				myChart.setDataXML(url);
				myChart.render("chartdivUtilization");
			}
			//初始化实时显示温湿度		
			function initRealTimeAkcpChart(ip){
				
				var myChart1 = new FusionCharts("<%=path%>/Charts/Thermometer.swf", "myChartId", "120", "300", "0", "0");
				var url1 = "<chart showBorder='0' bgColor='FFFFFF' bgAlpha='0' lowerLimit='0' upperLimit='100' majorTMNumber='11' majorTMHeight='2' minorTMNumber='9' decimalPrecision='0' thmFillColor='FF5904' chartLeftMargin='33' chartRightMargin='17' chartTopMargin='40' chartBottomMargin='40' numberSuffix='°' borderThickness='2'" 
						+"dataStreamURL='/monitorDevice/getRealTimeAvailabilityData.action?type=2&ip="+ip+"' refreshInterval='30'> "
						+"<value>0</value>"	
						+"</chart>";
				myChart1.setDataXML(url1);
				myChart1.render("chartdivThermometer");
				
				var myChart2 = new FusionCharts("<%=path%>/Charts/Thermometer.swf", "myChartId", "120", "300", "0", "0");
				var url2 = "<chart palette='3' bgColor='FFFFFF' bgAlpha='0' showBorder='0' lowerLimit='0' upperLimit='100' lowerLimitDisplay='Low' upperLimitDisplay='High' numberSuffix='%25' majorTMNumber='11' majorTMHeight='2' minorTMNumber='9' useSameFillColor='0' showTickValues='1' decimalPrecision='0'  chartLeftMargin='33' chartRightMargin='17' chartTopMargin='40' chartBottomMargin='40' borderThickness='2'"
						+"dataStreamURL='/monitorDevice/getRealTimeAvailabilityData.action?type=3&ip="+ip+"' refreshInterval='30'> "
						+"<value>0</value>"	
						+"</chart>";
				myChart2.setDataXML(url2);
				myChart2.render("chartdivHumidity");
										
			}
			
			function showDescription(title,href) {
				href="<%=path%>"+href;
	         	return GB_show("\''"+title+"\''报警说明", href,300,400);
			}
			 
			 
			//打开设备端口
			function openDeviceInterface(deviceId,ip,name){
				var url='<%=path%>/monitor/flex/DeviceInterfacePanel.html?deviceId='+deviceId;
				if(ip!=null&&ip.toString().length>0)
					url=url+"&ip="+ip;
				if(name!=null&&name.toString().length>0)
					url=url+"&name="+name;
				window.open (url+"&mode=show");  
			}
			 
			 
			 
		</script>
	</head>
	<body onload="initPage()"  leftmargin="0" marginwidth="0">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="1%" height="22" align="center"
					background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="<%=path%>/images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="<%=path%>/images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					查看设备
				</td>
			</tr>
		</table>
		<s:form action="%{actionURI}.action" method="post" theme="simple" name="form">
			<s:hidden id="ip" name="monitorDevice.ip"></s:hidden>
			<s:hidden id="deviceType" name="monitorDevice.monitorDeviceType.code"></s:hidden>
			<div id="dashboard2" style="width:100%"> 
			
			
			<table width="90%" align="center"  border="0" cellspacing="0" cellpadding="0">
					  	<tr>
						 	<td class="tabBtmLine">	
						 	 <span class="themeFontColor" style="float: left;"> </span>
						 	<div id="snapShotHeaderDiv" class="themeFontColor" style="float: left;"><s:property value="monitorDevice.name"/>
							</div>
							<div style="clear: both;"></div>
						 	</td>
						 	<td class="tabBtmLine" width="105" align="right"> 
						 	
					
						 	<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tbody><tr>
								<td>&nbsp;</td>
    							<td align="center">
    								<table class="dropdownnTxt" style="width: 112px;" onclick="showthemall('MenuActions_1')" onmouseover="clearhideDropDiv()" onmouseout="delayhideDropDiv(500,'MenuActions_1');" border="0" cellpadding="0" cellspacing="0">
								         <tbody>
								          <tr>
								            <td width="85%" align="center" nowrap="nowrap">&nbsp;常用链接</td>
								            <td class="pullDownnArrow" width="20" align="right">&nbsp;</td>
								          </tr>
        								</tbody>
        							</table>
        							<s:if test="monitorDevice.monitorDeviceType.code==20"></s:if>
									<s:else>
        							<div style="display: none;" id="MenuActions_1" class="ddMenuActions" onmouseover="clearhideDropDiv();" onmouseout="delayhideDropDiv(500,'MenuActions_1');">
          								<table style="width: 112px;" border="0" cellpadding="0" cellspacing="0">
            							<tbody>
            							<tr>
											<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="deviceArpList.action?deviceId=<s:property value='monitorDevice.id'/>" target="_blank" > 
						                        ARP表
						                        </a>
						                    </td>
                        				</tr>
            							<tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="deviceMacList.action?deviceId=<s:property value='monitorDevice.id'/>" target="_blank" > 
						                        MAC转发表
						                       	</a>
						                    </td>

                        				</tr>
                        				<tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="#" onclick="openDeviceInterface(<s:property value="monitorDevice.id"/>,'<s:property value="monitorDevice.ip"/>','<s:property value="monitorDevice.name"/>')"> 
						                       		设备端口
						                       	</a>
						                    </td>

                        				</tr>
                        				<tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="/monitor/flex/UserTopology.html?deviceId=<s:property value="monitorDevice.id"/>&deviceIp=<s:property value="monitorDevice.ip"/>" target="_blank" > 
						                        	用户接入
						                       	</a>
						                    </td>

                        				</tr>
                        				 <s:if test="monitorDevice.monitorDeviceType.code==6">
                        				 <tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="akcpMonitor.action?ip=<s:property value='monitorDevice.ip'/>" target="_blank" > 
						                        	温湿度测控
						                       	</a>
						                    </td>

                        				</tr>
                        				<tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="cpuHistory.action?deviceId=<s:property value='monitorDevice.id'/>" target="_blank" > 
						                        	温湿度历史
						                       	</a>
						                    </td>

                        				</tr>
                        				 </s:if>
                        				 <s:else>
                        				 <tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="cpuMonitor.action?ip=<s:property value='monitorDevice.ip'/>" target="_blank" > 
						                        	CPU测控
						                       	</a>
						                    </td>

                        				</tr>
                        				<tr>          		
                         					<td class="transBG" colspan="2" valign="middle" align="left">
						                    	<a href="cpuHistory.action?deviceId=<s:property value='monitorDevice.id'/>" target="_blank" > 
						                        	CPU历史
						                       	</a>
						                    </td>

                        				</tr>
                        				 </s:else>
            							<tr>
            								<td class="transBG" colspan="2" valign="middle" align="left">
            								<!-- <td class="transBG" style="border-right: 0px none;" title="Web" valign="middle" align="left"> -->
		                         			
						                    	<a href="javascript:MM_openBrWindow('http://192.168.118.39','Web','')" onclick="delayhideDropDiv(0,'MenuActions_1');"> 
						                        Web
						                        </a>
						                    </td>
						                    <!--<td class="transBG" style="border-left: 0px none; width: 9px; padding-right: 3px;"><img src="/webclient/devices/images/del.gif" onclick="noFunction();"></td>  -->
											

                        				</tr>
        
										</tbody>
										</table>
	
	
    								</div>
    								</s:else>
						 		</td>
						 		</tr>
						 		</tbody>
						 	</table>
						 	
						 	</td>
						</tr>
						
			</table>
			
			 
			
			</div>
			<div id="bubbles"></div>
			 
			<div id="dashboard2">
				<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td>
					<br/>
					</td>
					</tr>
					<tr>
						<td align="left" valign="top" >
							<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableBotomLine" >
						    <tr>
						      <td>
						      	  
						      </td>
						      </tr>
						    </table>
						    
						     
						    
						    <!-- DEVICE PROPERTIES TABLE STARTS HERE -->
						    <table border="0" height="410"  cellpadding="0" cellspacing="0" width="100%" class="tableOne" style="border-top: 0px none;">
								<tr>

								<td align="center" valign="top">
									<table width="100%" border="0" cellpadding="0" height="195" cellspacing="0" id="todayavailability">
							          <tr><td align="left" colspan=2 height="18" bgcolor="#bbe0ff" class="tableHeader">详细信息 </td></tr>
							           <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									    IP地址：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property  value="monitorDevice.ip"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 设备名称：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property value="monitorDevice.name"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 MAC地址：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									   <s:property value="monitorDevice.mac"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 设备类型：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property value="monitorDevice.monitorDeviceType.name"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 设备描述：
										</td>
									    <td width="70%" align="left"  onmouseover='bubble("bubbles","<s:property value="monitorDevice.description"/>");' onmouseout="bubble('bubbles','');"valign="middle" class="tab_row_fill">
									    <s:if test="monitorDevice.description.length()>9">         
										    <s:property value="monitorDevice.description.substring(0,50)"/>…
										</s:if>                  
										<s:else>      
										    <s:property value="monitorDevice.description"/>  
										</s:else>
									    
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 私有MAC地址读取方法：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property value="monitorDevice.monitorVendorMac.name"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 备注1：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property value="monitorDevice.note1"/>
									    </td>
									   </tr>
									   <tr>
									    <td width="30%" height="25" align="left" valign="middle" nowrap class="tab_row_fill">
									   	 备注2：
										</td>
									    <td width="70%" align="left" valign="middle" class="tab_row_fill">
									    <s:property value="monitorDevice.note2"/>
									    </td>
									   </tr>
							           
							        </table> 
									 
								</td>
							    </tr>
							</table>
							 
							
							  
							
							
						</td>
						<s:if test="monitorDevice.monitorDeviceType.code==20"></s:if>
						<s:else>
						<td width="58%" height="410" align="left" valign="top" class="dialBox" style="padding-left: 9px;">
								<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
							    <tr>
							      <td align="center" valign="top" width="50%" class="boxRightBtmLine">
							      <table width="100%" border="0" cellpadding="0" height="195" cellspacing="0" id="todayavailability">
							          <tr><td align="center" height="18" bgcolor="#bbe0ff" class="tableHeader">可用性 </td></tr>
							          <tr>
							            <td valign="top">
							            	
											<div id="chartdiv" align="center" class="centerthumb">
											 <p>&nbsp;</p>
											 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
										    </div>
											
											 
									 	</td>
							          </tr>
							           
							        </table>  
								  </td>
								   
							      <td align="center" valign="top" width="50%" class="boxLineBtm">
							      <table width="100%" border="0" cellpadding="0" height="195" cellspacing="0" id="todayavailability">
							          <tr><td align="center" height="18" bgcolor="#bbe0ff" class="tableHeader">响应时间 </td></tr>
							          <tr>
							            <td valign="center">
							            <div  align="center" class="centerthumb">
							            
							            <FONT ID="num" style="font-size: 60px;">0</FONT>
										<font id= "ms" style="font-size: 30px;">ms</font>
							            </div>
										
									 	</td>
							          </tr>
							        </table>  
							      </td> 
							    </tr>
							   	 <!-- 2ND ROW: DEVICE CPU, DISK AND MEMORY UTILIZATION STARTS HERE -->
							    <tr>
							    
							    <s:if test="monitorDevice.monitorDeviceType.code==6"><!-- 温湿度监控设备 -->
							    <td align="center" colspan = 2 valign="top" width="50%" class="boxLineRight">
							    <table width="100%" border="0" cellpadding="0" height="195" cellspacing="0" id="todayavailability">
							          <tr><td align="center" colspan = 2 height="18" bgcolor="#bbe0ff" class="tableHeader"> 温湿度 </td></tr>
							          <tr>
							            <td valign="top" colspan = 2 >
											<div id="snapBox">
							            	<a href="akcpMonitor.action?ip=<s:property value='monitorDevice.ip'/>" title="实时监控" target="_blank"><img src="<%=path%>/img/monitor/ceruleanBlue/nf.gif" alt="7*24小时实时监控" class="alighnRight" border="0"></a>
							            	</div>
							            	
							            	
									 	</td>
							          </tr>
							          <tr><td >
							          		<div id="chartdivThermometer" align="center" class="text">
											 <p>&nbsp;</p>
											 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
										    </div>
										</td>
										<td>
											<div id="chartdivHumidity" align="center" class="text">
											 <p>&nbsp;</p>
											 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
										    </div>
										</td>
							           </tr>
										   
							           
							        </table>  
							    </td>
							    
							    </s:if> 
							    <s:else>
							    <td align="center" colspan = 2 valign="top" width="50%" class="boxLineRight" >
								<table width="100%" border="0" cellpadding="0" height="195" cellspacing="0" id="todayavailability">
							          <tr><td align="center" height="18" bgcolor="#bbe0ff" class="tableHeader">CPU利用率 </td></tr>
							          <tr>
							            <td valign="top">
								            <div id="snapBox">
								            	<a href="cpuMonitor.action?ip=<s:property value='monitorDevice.ip'/>" title="实时监控" target="_blank"><img src="<%=path%>/img/monitor/ceruleanBlue/nf.gif" alt="7*24小时实时监控" class="alighnRight" border="0"></a>
							         	</div>
							           		<br/>
											<div id="chartdivUtilization" align="center" class="text">
											 <p>&nbsp;</p>
											 <p>FusionWidgets needs Adobe Flash Player to run. If you're unable to see the chart here, it means that your browser does not seem to have the Flash Player Installed. You can downloaded it <a href="http://www.adobe.com/products/flashplayer/" target="_blank"><u>here</u></a> for free.</p>
										    </div>
										    
									 	</td>
							          </tr>
							        </table>  
							    </td>
							     
							    </s:else>
							 
							
							    </tr>
							  </table>
						</td>
						</s:else>
					</tr>
					<s:if test="monitorDevice.monitorDeviceType.code==20"></s:if>
					<s:else>
					
					
					 <tr><td colspan =2>
						<br/>
						
						<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
							 <s:if test="monitorDevice.monitorDeviceType.code==6"><!-- 温湿度监控设备 -->
							 	<tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader"> 温湿度历史 </td></tr>
							    <tr><td valign="top">
									<div id="snapBox">
							            	<a href="cpuHistory.action?deviceId=<s:property value='monitorDevice.id'/>" title="自定义查询统计" target="_blank"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-editgraph.gif" alt="自定义查询统计" class="alighnRight" border="0"></a>
							            	<a href="javascript:setDeviceHistory1(30)">
							            	<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-30days.gif" title="30 天" class="alighnRight" border="0">
							            	</a>
							            	<a href="javascript:setDeviceHistory1(7)">
							            	<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-7days.gif" title="7 天" class="alighnRight" border="0">
							            	</a>
							            	<a href="javascript:setDeviceHistory1(1)"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-live.gif" title="24小时" class="alighnRight" border="0"></a>
							         </div>
							         
									<div class="chart-disp-block"  align="center" >
											 <br/>
									        <div id="chart1divZoomLine1" >
											  This text is replaced by the Flash movie.
											</div>
											 <br/>
											<div id="chart1divZoomLine2" >
											  This text is replaced by the Flash movie.
											</div>
											<br/>
									</div>
									<script type="text/javascript">
										var lable = "0|1";
										var value = "0|0";
										
										
										function callbackBuildXmlString1(data){
											lable = data[0];
											value = data[1];
											var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
											chart1.setDataXML(buildXMLString1());
											chart1.render("chart1divZoomLine1");
										}
										function buildXMLString1() {
											var strXML;
											strXML = "<chart caption='温度历史趋势图' compactDataMode='1' dataSeparator='|' paletteThemeColor='5D57A5' divLineColor='5D57A5' divLineAlpha='40' vDivLineAlpha='40'"
														+"numberSuffix='℃'"
														+"btnResetChartTitle='重置'"
														+"btnZoomOutTitle='缩小'"
														+"btnSwitchtoZoomModeTitle='切换到缩放模式'"
														+"btnSwitchToPinModeTitle='切换到比较模式'"
														+"zoomOutMenuItemLabel='放大'"
														+"resetChartMenuItemLabel='重置'"
														+"zoomModeMenuItemLabel='缩放模式'"
														+"pinModeMenuItemLabel='比较模式'"
														+">\n<categories>"; 
											strXML += lable;			
											strXML += "</categories>\n";
											strXML += "<dataset seriesName='"+ip+"' >\n";
											strXML += value;		  
											strXML += "</dataset>";
											strXML += "</chart>";
											return strXML;
										}
										
										function callbackBuildXmlString2(data){
											lable = data[0];
											value = data[1];
											var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
											chart1.setDataXML(buildXMLString2());
											chart1.render("chart1divZoomLine2");
										}
										function buildXMLString2() {
											var strXML;
											strXML = "<chart caption='湿度历史趋势图' compactDataMode='1' dataSeparator='|' paletteThemeColor='5D57A5' divLineColor='5D57A5' divLineAlpha='40' vDivLineAlpha='40'"
														+"numberSuffix='%'"
														+"btnResetChartTitle='重置'"
														+"btnZoomOutTitle='缩小'"
														+"btnSwitchtoZoomModeTitle='切换到缩放模式'"
														+"btnSwitchToPinModeTitle='切换到比较模式'"
														+"zoomOutMenuItemLabel='放大'"
														+"resetChartMenuItemLabel='重置'"
														+"zoomModeMenuItemLabel='缩放模式'"
														+"pinModeMenuItemLabel='比较模式'"
														+">\n<categories>"; 
											strXML += lable;			
											strXML += "</categories>\n";
											strXML += "<dataset seriesName='"+ip+"' >\n";
											strXML += value;		  
											strXML += "</dataset>";
											strXML += "</chart>";
											return strXML;
										}
									</script>		 
									</td>
							    </tr>
							 </s:if>
							 <s:else>
							    <tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader"> CPU历史 </td></tr>
							    <tr><td valign="top">
							    	<div id="snapBox">
							            	<a href="cpuHistory.action?deviceId=<s:property value='monitorDevice.id'/>" title="自定义查询统计" target="_blank"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-editgraph.gif" alt="自定义查询统计" class="alighnRight" border="0"></a>
							            	
							            	<a href="javascript:setDeviceHistory(30)">
							            	<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-30days.gif" title="30 天" class="alighnRight" border="0">
							            	</a>
							            	<a href="javascript:setDeviceHistory(7)">
							            	<img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-7days.gif" title="7 天" class="alighnRight" border="0">
							            	</a>
							            	<a href="javascript:setDeviceHistory(1)"><img src="<%=path%>/img/monitor/ceruleanBlue/wrapper-live.gif" title="24小时" class="alighnRight" border="0"></a>
							         </div>
									<div class="chart-disp-block"  align="center" >
									        <div id="chart1divZoomLine" >
											  This text is replaced by the Flash movie.
											</div>
									</div>
									<script type="text/javascript">
										var lable = "0|1";
										var value = "0|0";
										
										
										
										function callbackBuildXmlString(data){
											lable = data[0];
											value = data[1];
											var chart1 = new FusionCharts("<%=path%>/Charts/ZoomLine.swf", "ChId1", "900", "400", "0", "0");
											chart1.setDataXML(buildXMLString());
											chart1.render("chart1divZoomLine");
										}
										function buildXMLString() {
											var strXML;
											strXML = "<chart  caption='CPU利用率历史趋势图' compactDataMode='1' dataSeparator='|' paletteThemeColor='5D57A5' divLineColor='5D57A5' divLineAlpha='40' vDivLineAlpha='40'"
														+"numberSuffix='%'"
														+"btnResetChartTitle='重置'"
														+"btnZoomOutTitle='缩小'"
														+"btnSwitchtoZoomModeTitle='切换到缩放模式'"
														+"btnSwitchToPinModeTitle='切换到比较模式'"
														+"zoomOutMenuItemLabel='放大'"
														+"resetChartMenuItemLabel='重置'"
														+"zoomModeMenuItemLabel='缩放模式'"
														+"pinModeMenuItemLabel='比较模式'"
														+">\n<categories>"; 
											strXML += lable;			
											strXML += "</categories>\n";
											strXML += "<dataset seriesName='"+ip+"' >\n";
											strXML += value;		  
											strXML += "</dataset>";
											strXML += "</chart>";
											return strXML;
										}
									</script>
											 
									</td>
							    </tr>
							 </s:else>
							          
						</table>
					</td></tr>
					<tr><td colspan =2>
						<br/>
						<table width="100%" height="410" border="0" align="center" cellpadding="0" cellspacing="0" class="tableOne">
							          <tr><td align="left" height="18" bgcolor="#bbe0ff" class="tableHeader">  报警 </td></tr>
							          <tr>
							            <td valign="top">
											 <!--Start of the Tabmenu 1 -->
											<div class="menu">
											<ul>
											<li><a class="tab1 tabactive" href="#" onmouseover="easytabs('1', '1');" onfocus="easytabs('1', '1');" onclick="return false;" title="" id="tablink1">全部报警</a></li>
											<li><a class="tab2" href="#" onmouseover="easytabs('1', '2');" onfocus="easytabs('1', '2');" onclick="return false;" title="" id="tablink2">故障报警 </a></li>
											<li><a class="tab3" href="#" onmouseover="easytabs('1', '3');" onfocus="easytabs('1', '3');" onclick="return false;" title="" id="tablink3">接入报警</a></li>
											<li><a class="tab4" href="#" onmouseover="easytabs('1', '4');" onfocus="easytabs('1', '4');" onclick="return false;" title="" id="tablink4">阈值报警 </a></li>
											<li><a class="tab5" href="#" onmouseover="easytabs('1', '5');" onfocus="easytabs('1', '5');" onclick="return false;" title="" id="tablink5">SNMP TRAP 报警 </a></li>
											<li><a class="tab6 " href="#" onmouseover="easytabs('1', '6');" onfocus="easytabs('1', '6');" onclick="return false;" title="" id="tablink6">其他报警 </a></li>
											</ul>
											</div>
											<!--End of the Tabmenu 1 -->
											<div style="display: none;" id="tabcontent1">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">名称</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">IP</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int i=1; %> 
											      <s:iterator value="#request.monitorAlertList0" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=i++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
											<div style="display: none;" id="tabcontent2">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">档案值</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">IP</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">进程/接口</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int j=1; %> 
											      <s:iterator value="#request.monitorAlertList1" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=j++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" ><s:property value="interface_"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
											<div style="display: none;" id="tabcontent3">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">档案值</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">IP</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">MAC</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">上连设备</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">接口描述</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int m=1; %> 
											      <s:iterator value="#request.monitorAlertList2" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=m++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" ><s:property value="mac"/></td>
											          <td align="center" ><s:property value="uplink"/></td>
											          <td align="center" ><s:property value="description"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
											<div style="display: none;" id="tabcontent4">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">档案值</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">IP</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">上连设备</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">接口描述</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int l=1; %> 
											      <s:iterator value="#request.monitorAlertList3" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=l++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" ><s:property value="interface_"/></td>
	          										  <td align="left" ><s:property value="description"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
											<div style="display: none;" id="tabcontent5">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">MAC</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上联设备</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">端口</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口</td>
									        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int n=1; %> 
											      <s:iterator value="#request.monitorAlertList4" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=n++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
											<div style="display: none;" id="tabcontent6">
											<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
												<tr bgcolor="#b5d6e6">
											        <td height="22" align="center" nowrap bgcolor="#deebf1" class="alllisttitle" background="../images/main20100521_58.gif">序号</td>                
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">报警类型</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">档案值</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">IP</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">MAC</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">上联设备</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="<%=path%>/images/main20100521_58.gif" class="alllisttitle">接口描述</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警级别</td>        
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">报警次数</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">首次时间</td>
											        <td align="center" nowrap bgcolor="#deebf1" background="../images/main20100521_58.gif" class="alllisttitle">最近时间</td>
											      </tr>
											      <%int g=1; %> 
											      <s:iterator value="#request.monitorAlertList5" var="alert">
											        <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
													 <td height="19" align="center" >  <%=g++ %></td>
											          <td align="left" ><a href="#" title="鼠标放上去弹出报警说明信息 " onmouseover="timer1=window.setInterval('showDescription(\'<s:property value="monitorAlertSmalltype.name"/>\',\'<%=path%>/monitorAlert/description.action?id=<s:property value="monitorAlertSmalltype.code"/>\')',3000)" onmouseout="window.clearInterval(timer1)";><s:property value="monitorAlertSmalltype.name"/></a></td>
											          <td align="left" ><s:property value="old"/></td>
											          <td align="left" ><s:property value="ip"/></td>
											          <td align="center" ><s:property value="mac"/></td>
											          <td align="center" ><s:property value="uplink"/></td>
											          <td align="center" ><s:property value="description"/></td>
											          <td align="center" >
											          				<s:if test="monitorAlertSmalltype.level==1"><img src="<%=path%>/img/monitor/status1.gif"/>重要报警</s:if>
										          					<s:elseif test="monitorAlertSmalltype.level==2"><img src="<%=path%>/img/monitor/status2.gif"/>普通报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==3"><img src="<%=path%>/img/monitor/status3.gif"/>次要报警</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==4"><img src="<%=path%>/img/monitor/status4.gif"/>重要恢复</s:elseif>
										          					<s:elseif test="monitorAlertSmalltype.level==5"><img src="<%=path%>/img/monitor/status5.gif"/>普通恢复</s:elseif>
											          </td>
											          <td align="center" ><s:property value="count"/></td>
											          <td align="center" ><s:date name="firstTime" format="yyyy/MM/dd HH:mm:ss" /> </td>
											          <td align="center" ><s:date name="lastTime" format="yyyy/MM/dd HH:mm:ss" /></td>
											          
											        </tr>
											      </s:iterator>
											</table>
											</div>
									 	</td>
							          </tr>
						</table>
					</td></tr>
					</s:else>
				</table>
				 
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			</table>
			
		</s:form>
<%--		<s:debug></s:debug>--%>
	</body>
</html>