<!--TestRecords:500//-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
<script language=JavaScript>
function Chose(Num){
	var val=window.document.getElementById(Num).value
	window.parent.document.getElementById('catenameh').value=Num;
	window.parent.document.getElementById('catename').value=val;
}
function choose(value1,value2){
	window.parent.document.getElementById('catenameh').value=value1;
	window.parent.document.getElementById('catename').value=value2;
}
</script>
</head>
<body bgcolor="#F3F4F8" leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr> 
    		<td valign="top" id="mainright" height="100%"> 
      			<table width="100%" border="0" cellspacing="0" cellpadding="0">
        			<tr> 
          				<td height="136" valign="top" bgcolor="#FFFFFF">             
            				<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
            					<tr>
      							<td>
      								<script type="text/javascript">
										d = new dTree('d');
										d.add('0','-1','树形结构','','','listFrame');
										<s:iterator value="allServiceCategory" var="serviceCategory">
											d.add('${serviceCategory.id}','${serviceCategory.pid}','${serviceCategory.itemZh}','javascript:choose(${serviceCategory.id},\'${serviceCategory.itemZh}\');');
										</s:iterator>
										document.write(d);
										d.openAll();
									</script>
								</td>
      							
      							</tr>		
<!--              					 <s:iterator value="allServiceCategory" var="serviceCategory" status='st'>-->
<!--        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">-->
<!--										<td width="13%" nowrap class="subtitle">-->
<!--											<input name="chose" type="radio" value="<s:property value='id'></s:property>" style="border: 0px" onClick="if(this.checked){Chose(this.value)}">-->
<!--											<input type="hidden"  id="<s:property value='id'></s:property>" value="<s:property value='itemZh'></s:property>" >-->
<!--											<s:property  value="itemZh"/>-->
<!--										</td>-->
<!--        							</tr>-->
<!--      							</s:iterator>       									-->
            				</table>
          				</td>
        			</tr>
      			</table>
    		</td>
  		</tr>
	</table>
</body>
</html>