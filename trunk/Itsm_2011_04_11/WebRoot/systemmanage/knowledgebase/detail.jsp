<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>查阅详情</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; overflow:auto; padding: 2px; border: 1px inset">
		
		<table border=0 width="100%" style="border: 2px outset; border-bottom: 0px border-left:2px" cellspacing="0" cellpadding="2" height="6%">
			<tr> 
				<td height="32"  width="100%" bgcolor="#deebf1">&nbsp;<img src="../images/modpass.gif" align="absMiddle">&nbsp;<b>解决方案</b></td>		
	  		</tr>	  	
			<tr>
				<td  nowrap style="color: #647A9;padding: 10px" background="../img/address.jpg" height="30" colspan="2">
					<img src="../img/check.gif" align="absMiddle"> &nbsp; <b>症状描述:</b>
				</td>
				<td width="1%" background="../img/address.jpg"></td>

			</tr>
			<tr>
				<td valign="top" align="left" style="padding: 10px; line-height: 25px" height="100" colspan="3">
					<s:property value="knowledgeBase.title"></s:property>
					<br> &nbsp;					
				</td>
			</tr>
			<tr>
				<td nowrap style="color: #647A9C;padding: 10px" background="../img/address.jpg" height="30" colspan="2">
					<img src="../img/check.gif" align="absMiddle"> &nbsp;
					<b>详细解决方法:</b>
				</td>
				<td width="1%" background="../img/address.jpg"></td>
			</tr>
			<tr>
				<td valign="top" align="left" style="padding: 10px; line-height: 25px" colspan="3">
					<P>
						<A style="LINE-HEIGHT: 25px; FONT-SIZE: 15px"><FONT face=黑体>${knowledgeBase.solution}</FONT> </A>
					</P>
				</td>
			</tr>
			<tr>
				<td nowrap style="color: #647A9C;padding: 10px" background="../img/address.jpg" height="30" colspan="2">
					<img src="../img/check.gif" align="absMiddle"> &nbsp;
					<b>相关附件:</b>
				</td>
				<td width="1%" background="../img/address.jpg"></td>
			</tr>
			<tr>
				<td valign="top" align="left" style="padding: 10px; line-height: 25px" height="150" colspan="3">
					<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
              			<s:iterator value="accessoryList" var="accessory" status="st">
            				<tr bgcolor="#FFFFFF"  onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">            					
		    					<td align="left"><a href="download.action?dlFileName=<s:property value="name"/>"><s:property value="trueName"/></a></td>
          					</tr>
          				</s:iterator>
 					</table>				
				</td>
			</tr>
		</table>
	</body>
</html>
