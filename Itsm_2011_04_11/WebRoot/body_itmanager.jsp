<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>经理首页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/js/FusionChartsFree/contents/Style.css" type="text/css" />
		<script language="JavaScript" src="<%=request.getContextPath()%>/js/FusionChartsFree/JSClass/FusionCharts.js"></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/ServiceRequestStatisticService.js'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type="text/javascript">
			function init(){
				ServiceRequestStatisticService.getDailyInc(dailyInc);
				ServiceRequestStatisticService.getIncDealTime(incDealTime);
				ServiceRequestStatisticService.getMonthIncState(incMonthState);
				ServiceRequestStatisticService.getEngineerWork(incMonthEngineer);
			}
			function setDivWhenNoData(divId){
				document.getElementById(divId).innerHtml="加载数据失败,请检查相关配置!!!";
			}
			function clearDivInnerHtml(divId){
				if(document.getElementById(divId).innerHtml){
					document.getElementById(divId).innerHtml="";
				}
			}
			function incMonthEngineer(data){
				var truename=new Array();
				var state1=new Array();
				var state2=new Array();
				if(data){
					for(var i=0;i<data.length;i++){
						var split=data[i].split('~');
						truename[i]=split[0];
						state1[i]=split[1];
						state2[i]=split[2];
					}
				}else{
					return ;
				}
				var chart=new FusionCharts("<%=request.getContextPath()%>/js/FusionChartsFree/Charts/ScrollColumn2D.swf", "ChartIdg", "630", "195");
				var xmlStr = "<chart palette='2' caption='' shownames='1' showvalues='0' numberPrefix='' useRoundEdges='1' legendBorderAlpha='0' baseFontSize='12' baseFont='宋体'>";
				xmlStr +="<categories>";
				for(var j=0;j<truename.length;j++){
					xmlStr += "<category name='"+ truename[j] +"' /> ";
				}
				xmlStr +="</categories>";
				xmlStr +="<dataset seriesName='待处理' fontSize='12' color='AFD8F8' showValues='0'>";
				for(var k=0;k<state1.length;k++){
					xmlStr += "<set value='"+ state1[k] +"' /> ";
				}
				xmlStr +="</dataset>";
				xmlStr +="<dataset seriesName='处理中' fontSize='12' color='F6BD0F' showValues='0'>";
				for(var k=0;k<state1.length;k++){
					xmlStr += "<set value='"+ state2[k] +"' /> ";
				}
				xmlStr +="</dataset>";
				
				xmlStr +="</chart>";
				chart.setDataXML(xmlStr);
				chart.render("monthTOP10");
			}
			function incMonthState(data){
				var finish;
				var unFinish;
				if(data){
					var split=data.split('~');
					finish=split[0];
					unFinish=split[1];
				}else{
					return ;
				}
				var chart = new FusionCharts("<%=request.getContextPath()%>/js/FusionChartsFree/Charts/StackedColumn3D.swf", "ChartIdz", "350", "195");
				var xml="<chart palette='1' caption='' shownames='1' showvalues='0' numberPrefix='' showSum='1' decimals='0' overlapColumns='0' baseFontSize='12' baseFont='宋体'>";
				xml += "<categories>"; 
				xml += "<category label='事件' /> "; 
				xml += "</categories>";
				xml += "<dataset seriesName='已完成' showValues='0'>";
				xml += "<set value='"+finish+"' /> ";
				xml += "</dataset>";
				xml += "<dataset seriesName='处理中' showValues='0'>";
				xml += "<set value='"+unFinish+"' /> ";
				xml += "</dataset>";
				xml += "</chart>";
				chart.setDataXML(xml);
				chart.render("monthSRcount");
			}
			function incDealTime(data){
				var x=new Array();
				var y=new Array();
				if(data){
					for(var i=0;i<data.length;i++){
						var split=data[i].split('~');
						x[i]=split[0];
						y[i]=split[1];
					}
				}else{
					return;
				}
				var chart = new FusionCharts("<%=request.getContextPath()%>/js/FusionChartsFree/Charts/MSLine.swf", "ChartIdx", "470", "200");
				var xml="<chart caption='' subcaption='"+x[0]+"至"+x[6]+"' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='' bgAngle='270' bgAlpha='10,10' baseFontSize='12' baseFont='宋体'>";
				xml += "<categories>";
				xml += "<category label='"+x[0] +"' /> ";
				xml += "<category label='"+x[1] +"' /> ";
				xml += "<category label='"+x[2] +"' /> ";
				xml += "<category label='"+x[3] +"' /> ";
				xml += "<category label='"+x[4] +"' /> ";
				xml += "<category label='"+x[5] +"' /> ";
				xml += "<category label='"+x[6] +"' /> ";
				xml += "</categories>";
				xml += "<dataset seriesName='事件' color='1D8BD' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
				xml += "<set value='"+ y[0] +"' /> ";
				xml += "<set value='"+ y[1] +"' /> ";
				xml += "<set value='"+ y[2] +"' /> ";
				xml += "<set value='"+ y[3] +"' /> ";
				xml += "<set value='"+ y[4] +"' /> ";
				xml += "<set value='"+ y[5] +"' /> ";
				xml += "<set value='"+ y[6] +"' /> ";
				xml += "</dataset> ";
				xml += "<styles> ";
				xml += "<definition> <style name='CaptionFont' type='font' size='12' /> </definition>";
				xml += "<application> <apply toObject='CAPTION' styles='CaptionFont' /> <apply toObject='SUBCAPTION' styles='CaptionFont' /> </application>";
				xml += "</styles>";
				xml += "</chart>"; 
				chart.setDataXML(xml);
				chart.render("weekSRdealavg");
			}
			function dailyInc(data){
				var x=new Array();
				var y=new Array();
				if(data){
					for(var i=0;i<data.length;i++){
						var split=data[i].split('~');
						x[i]=split[0];
						y[i]=split[1];
					}
				}else{
					return;
				}
				var chart = new FusionCharts("<%=request.getContextPath()%>/js/FusionChartsFree/Charts/MSLine.swf", "ChartIdy", "470", "200");
				var xml="<chart caption='' subcaption='"+x[0]+"至"+x[6]+"' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='' bgAngle='270' bgAlpha='10,10' baseFontSize='12' baseFont='宋体'>";
				xml += "<categories>";
				xml += "<category label='"+x[0] +"' /> ";
				xml += "<category label='"+x[1] +"' /> ";
				xml += "<category label='"+x[2] +"' /> ";
				xml += "<category label='"+x[3] +"' /> ";
				xml += "<category label='"+x[4] +"' /> ";
				xml += "<category label='"+x[5] +"' /> ";
				xml += "<category label='"+x[6] +"' /> ";
				xml += "</categories>";
				xml += "<dataset seriesName='事件' color='1D8BD' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>";
				xml += "<set value='"+ y[0] +"' /> ";
				xml += "<set value='"+ y[1] +"' /> ";
				xml += "<set value='"+ y[2] +"' /> ";
				xml += "<set value='"+ y[3] +"' /> ";
				xml += "<set value='"+ y[4] +"' /> ";
				xml += "<set value='"+ y[5] +"' /> ";
				xml += "<set value='"+ y[6] +"' /> ";
				xml += "</dataset> ";
				xml += "<styles> ";
				xml += "<definition> <style name='CaptionFont' type='font' size='12' /> </definition>";
				xml += "<application> <apply toObject='CAPTION' styles='CaptionFont' /> <apply toObject='SUBCAPTION' styles='CaptionFont' /> </application>";
				xml += "</styles>";
				xml += "</chart>"; 
				chart.setDataXML(xml);
				chart.render("weekSRcount");
			}
		</script>
	</head>

	<body style="overflow:auto;" onLoad="init()">
	<table width="100%" cellpadding="1" cellspacing="1" height="100%">
      <tr>
        <td valign="top" height="50%" id='north'><table width="99%" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="50%" valign="top" height="50%">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td  height="22"  width="5%" background="../images/main20100521_58.gif" ><table width="99%" align="right">
                <tr>
                  <td width="24"><img src="../images/today_requests.png" width="18" height="18"></td>
                  <td class="alllisttitle">本周服务请求数量趋势图</td>
                </tr>
              </table></td>
			  </tr>
			 <tr bgcolor="#FFFFFF" >	
			  <td align="center" height="50%" ><div id='weekSRcount' align='center'></div></td>
			  </tr>
		   </table></td>
            <td>	
            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			 <tr bgcolor="#FFFFFF">
			  <td  height="22" align="center" width="5%" background="../images/main20100521_58.gif" ><table width="99%" align="right">
                <tr>
                  <td width="24"><img src="../images/current_incidents.png" width="18" height="18"></td>
                  <td class="alllisttitle">本周服务处理平均时长趋势图(小时)</td>
                </tr>
              </table></td>
			  </tr>
			 <tr bgcolor="#FFFFFF">	
			  <td  align="center" ><div id='weekSRdealavg' align='center'></div></td>
			  </tr>
		   </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="50%" valign="top"><table width="99%" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="66%"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
                <tr bgcolor="#FFFFFF">
                  <td  height="22" align="center" width="5%" background="../images/main20100521_58.gif" ><table width="99%" align="right">
                      <tr>
                        <td width="24"><img src="../images/month_engineer_incidents.png" width="19" height="18"></td>
                        <td class="alllisttitle">本月工程师进行中服务统计</td>
                        <td class="alllisttitle"><div align="center"><img src="../images/configure_search.png" width="18" height="18" style="display:none"></div></td>
                    </tr>
                  </table></td>
                </tr>
                <tr bgcolor="#FFFFFF">
                  <td align="center" ><div id='monthTOP10' align='center'></div></td>
                </tr>
            </table></td>
            <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
                <tr bgcolor="#FFFFFF">
                  <td  height="22" align="center" width="5%" background="../images/main20100521_58.gif" ><table width="99%" align="right">
                      <tr>
                        <td width="24"><img src="../images/await_requests.png" width="19" height="18"></td>
                        <td class="alllisttitle">本月服务请求处理统计</td>
                      </tr>
                  </table></td>
                </tr>
                <tr bgcolor="#FFFFFF">
                  <td  align="center" ><div id='monthSRcount' align='center'></div></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table>
		   
</body>
</html>
