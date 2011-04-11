<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
//monitor\amchart\amline\swfobject.js
%>
<HTML>
<HEAD>
<TITLE> 拓扑监控 </TITLE>
<SCRIPT language="JScript.Encode" src="<%=path%>/systemmanage/ActiveX/SwfObject.js" type="text/javascript"></SCRIPT>
<script type="text/javascript">
	function gotoWebUrl(web) {
		window.open(web);
	}
</script>
</HEAD>
<BODY leftmargin="0" topmargin="0" bgcolor="#FFFFFF" text="#000000">
<div id="flashcontent"></div>
<script type="text/javascript">
	var typeXmls = "<s:property value="typeXml"/>";
	var yearXmls = "<s:property value="yearXml"/>";
	var stateXmls = "<s:property value="stateXml"/>";
	var so = new SWFObject("<%=path%>/systemmanage/ActiveX/AssetStatistic.swf", "AssetStatistic", "100%", "100%",
			FlashVersion, "#ffffff", "high");
		so.addVariable("TypeXml",typeXmls);
		so.addVariable("YearXml",yearXmls);
		so.addVariable("StateXml",stateXmls);
		so.write("flashcontent");
		
</script>

</BODY>
</HTML>
