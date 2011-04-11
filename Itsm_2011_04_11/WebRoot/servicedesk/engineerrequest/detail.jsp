<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>查阅详情1</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function test(){
				var val=document.getElementById("idsolution").innerHTML;
				val=val.substr(3);
				var index=val.lastIndexOf('</P>');
				val=val.substr(0,index);
				window.parent.opener.document.getElementById('serviceRequestSolution').value=val;
				window.parent.opener=null;
    			window.parent.close();
			}
		</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; overflow:auto; padding: 2px; border: 1px inset">
		
		<table border=0 width="100%" style="border: 2px outset; border-bottom: 0px border-left:2px" cellspacing="0" cellpadding="2" height="100%">
			<tr> 
				<td colspan="2" height="5%"  width="100%" bgcolor="#deebf1">&nbsp;<img src="../images/modpass.gif" align="absMiddle">&nbsp;<b>解决方案</b></td>		
	  		</tr>	  	
			<tr>
				<td  nowrap style="color:#647A9;padding: 5px" background="../img/address.jpg" height="5%">
					<img src="../img/check.gif" align="absMiddle"> &nbsp;<FONT face=黑体>症状描述:</FONT>
				</td>
				<td width="1%" background="../img/address.jpg"></td>

			</tr>
			<tr>
				<td valign="top" align="left" style="color: #647A9C;padding:5px; line-height: 25px" height="20%">
					<s:property value="knowledgeBase.title"></s:property>
					<br> &nbsp;					
				</td>
			</tr>
			<tr>
				<td nowrap style="color: #647A9C;padding: 5px" background="../img/address.jpg" height="5%">
					<img src="../img/check.gif" align="absMiddle"> &nbsp;
					<b>详细解决方法:</b>
				</td>
				<td width="1%" background="../img/address.jpg"></td>
			</tr>
			<tr>
				<td id="idsolution" valign="top" align="left" style="color: #647A9C;padding: 10px; line-height: 25px" colspan="2"  height="35%">
					${knowledgeBase.solution}
				</td>
			</tr>
			<tr>
				<td nowrap style="color: #647A9C;padding: 5px" background="../img/address.jpg" height="5%">
					<img src="../img/check.gif" align="absMiddle"> &nbsp;
					<b>相关附件:</b>
				</td>
				<td width="1%" background="../img/address.jpg"></td>
			</tr>
			<tr>
				<td valign="top" align="left" style="padding: 5px; line-height: 25px" height="20%" colspan="2">
					<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
              			<s:iterator value="accessoryList" var="accessory" status="st">
            				<tr bgcolor="#FFFFFF"  onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">            					
		    					<td align="left"><a href="../knowledgebase/download.action?dlFileName=<s:property value="name"/>"><s:property value="trueName"/></a></td>
          					</tr>
          				</s:iterator>
 					</table>				
				</td>
			</tr>
			<tr>
				<td nowrap style="color: #647A9C;" align="right" height="5%" colspan="2">
					<input type="button" value="插入" class="mmBtn" onclick="javascript:test()">
				</td>
			</tr>
		</table>
	</body>
</html>
