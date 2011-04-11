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
    
    <title></title>
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
		.redBdr{ border: 1px solid #C8443F; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		.greenBdr{ border: 1px solid #8DC73F; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		.yellowBdr{border: 1px solid #F6F905; background-color: #fff; width: 80%; text-align:left; font-size:7px}
		
		-->
	</style>
<script>
	//表格移上之后变换颜色
	var  highlightcolor='#c1ebff';
	//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
	var  clickcolor='#51b2f6';
	function  changeto(){
		source=event.srcElement;
		if  (source.tagName=="TR"||source.tagName=="TABLE")
			return;
		while(source.tagName!="TD")
			source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		//alert(cs.length);
		if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}

	function  changeback(){
		if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
		return
		if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
		//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}

	function bytesConvert(bytes) {
		bytes = bytes*8;
        var ext = new Array('B', 'k', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y');
        var unitCount = 0;
        for(; bytes > 1000; unitCount++) 
        	bytes /= 1000;
        
        return roundNumber(bytes,1) + "" + ext[unitCount];
	}
	function roundNumber(num, dec) {
		var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}
	//显示数据的百分比，有颜色区别
	function showBdrData(data,s,opposite){
		var div,divClass,divColor;
		if(opposite){
			if(data>=s[0]){
				divClass="redBdr";
				divColor="rgb(200, 68, 63)";
			} else if(data>=s[1]){
				divClass="yellowBdr";
				divColor="rgb(246, 249, 5)";
			} else {
				divClass="greenBdr";
				divColor="rgb(141, 199, 63)";
			}
		} else {
			if(data<=s[0]){
				divClass="redBdr";
				divColor="rgb(200, 68, 63)";
			} else if(data<=s[1]){
				divClass="yellowBdr";
				divColor="rgb(246, 249, 5)";
			} else {
				divClass="greenBdr";
				divColor="rgb(141, 199, 63)";
			}
			
		}
		
		div="<div class='"+divClass+"'> <div style='width:"+data+"%; background-color: "+divColor+"; height: 8px; font-size: 7px;'></div>";
	
		return 	div;		 	
	}
</script>
  </head>
  
  <body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">故障 TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">数据</td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
          </tr>
          <%int i=1; %> 
		<s:iterator value="realtimeFaultList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF"> <%=i++ %> <img src="img/monitor/status1.gif" width="19" height="19" alt=""></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="data"/> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="ip"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="name"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="type"/></td>
		  </tr>
		  </s:iterator>
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
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">响应 TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">数据</td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
          
          </tr>
          <%int h=1; %> 
		<s:iterator value="realtimeDelayList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF"> <%=h++ %> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="data"/> ms</td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="ip"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="name"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="type"/></td>
		   
		  </tr>
		  </s:iterator>
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
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">可用 TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22"  align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">数据</td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
            
          </tr>
          <%int j=1; %> 
		<s:iterator value="realtimeUseList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF"> <%=j++ %> </td>
		     <td align="center" bgcolor="#FFFFFF"><s:property value="rate"/> %
		    <script type="text/javascript">document.write(showBdrData("<s:property value='rate'/>",new Array(50,70,100),false))</script>
		     </td>
		     <td align="center" bgcolor="#FFFFFF"><s:property value="ip"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="name"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="type"/></td>
		   
		  </tr>
		  </s:iterator>
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
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">CPU TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">数据</td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
          
            <td align="center" background="images/main20100521_58.gif">采集时间</td>
          </tr>
          <%int k=1; %> 
		<s:iterator value="realtimeCpuList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF"> <%=k++ %> </td>
		    <td align="center" bgcolor="#FFFFFF"> 
		    <s:if test="data==-1.0" >无数据 </s:if>
		    <s:else>
			    <s:property value="data"/> %
			    <script type="text/javascript">document.write(showBdrData("<s:property value='data'/>",new Array(90,30,0),true))</script>
		    </s:else>
		    	
		    </td>
		     <td align="center" bgcolor="#FFFFFF"><s:property value="ip"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="name"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="type"/></td>
		    
		    <td align="center" bgcolor="#FFFFFF"><s:date name="gatherTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		  </tr>
		  </s:iterator>
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
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">互联接口 TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
           	<td align="center" background="images/main20100521_58.gif">接口号</td>
            <td align="center" background="images/main20100521_58.gif">接口描述</td>
           	<td align="center" background="images/main20100521_58.gif">双向流量</td>
           	<td align="center" background="images/main20100521_58.gif">发送流量</td>
           	<td align="center" background="images/main20100521_58.gif">接收流量</td>
            <td align="center" background="images/main20100521_58.gif">采集时间</td>
          </tr>
          <%int l=1; %> 
		<s:iterator value="realtimeLinkPortFlowList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF">  <%=l++ %> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceIp"/> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceName"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceType"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="interface_"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="interfaceDescription"/></td>
		    <td align="center" bgcolor="#FFFFFF">
			    <script type="text/javascript">
			    document.write(bytesConvert("<s:property value='bilateralTraffic'/>"))
			    </script>/s
		    </td>
		    <td align="center" bgcolor="#FFFFFF">
		    	<script type="text/javascript">
			    document.write(bytesConvert("<s:property value='deliveryTraffic'/>"))
			    </script>/s
		     
		    </td>
		    <td align="center" bgcolor="#FFFFFF">
		    	<script type="text/javascript">
			    document.write(bytesConvert("<s:property value='receiveTraffic'/>"))
			    </script>/s
		    </td>
		    <td align="center" bgcolor="#FFFFFF"><s:date name="gatherTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		    
		  </tr>
		  </s:iterator>
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
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" background="images/main20100521listbg.gif">
  <tr>
    <td width="3%" align="left" background="images/listconframe_04.gif"><img src="images/main20100521_35.gif" width="13" height="28" alt=""><img src="images/main20100521_36.gif" width="22" height="28" alt=""></td>
    <td width="9%" align="left" nowrap background="images/listconframe_04.gif" class="STYLE1">用户接口 TOP N </td>
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
    <td height="6" bgcolor="#FFFFFF">
  	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6" onMouseOver="changeto()"  onmouseout="changeback()">
          <tr bgcolor="#FFFFFF"  class="STYLE1">
            <td height="22" align="center" background="images/main20100521_58.gif">序号 </td>
            <td align="center" background="images/main20100521_58.gif">IP地址</td>
            <td align="center" background="images/main20100521_58.gif">名称</td>
            <td align="center" background="images/main20100521_58.gif">类型</td>
           	<td align="center" background="images/main20100521_58.gif">接口号</td>
            <td align="center" background="images/main20100521_58.gif">接口描述</td>
           	<td align="center" background="images/main20100521_58.gif">双向流量</td>
           	<td align="center" background="images/main20100521_58.gif">发送流量</td>
           	<td align="center" background="images/main20100521_58.gif">接收流量</td>
            <td align="center" background="images/main20100521_58.gif">采集时间</td>
          </tr>
          <%int m=1; %> 
		<s:iterator value="realtimeUsePortFlowList" status="index">
		  <tr class="STYLE4">
		    <td height="26" align="center" bgcolor="#FFFFFF">  <%=m++ %> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceIp"/> </td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceName"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="deviceType"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="interface_"/></td>
		    <td align="center" bgcolor="#FFFFFF"><s:property value="interfaceDescription"/></td>
		    <td align="center" bgcolor="#FFFFFF">
			    <script type="text/javascript">
			    document.write(bytesConvert("<s:property value='bilateralTraffic'/>"))
			    </script>/s
		    </td>
		    <td align="center" bgcolor="#FFFFFF">
		    	<script type="text/javascript">
			    document.write(bytesConvert("<s:property value='deliveryTraffic'/>"))
			    </script>/s
		     
		    </td>
		    <td align="center" bgcolor="#FFFFFF">
		    	<script type="text/javascript">
			    document.write(bytesConvert("<s:property value='receiveTraffic'/>"))
			    </script>/s
		    </td>
		    <td align="center" bgcolor="#FFFFFF"><s:date name="gatherTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		    
		  </tr>
		  </s:iterator>
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
