<!--TestRecords:500//-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" event="onerror(msg, url, line)"
	for="window">
return true;</script>

<html>
	<head>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript">

function Chose(Num) {

	var box = document.getElementsByTagName('input');
	var a = "";
	for ( var i = 0; i < box.length; i++) {
		if (box[i].checked) {
			a = a + ":" + box[i].value;
		}

	}
	window.parent.document.getElementById('departments').value = a;

}
function tt() {

	var sled = document.getElementById("departmentList").value;
	var all=document.getElementsByTagName('input');
	var ss=sled.split(":");
	for(var i=0;i<all.length;i++)
		{	
		for( var j=0;j<ss.length;j++)
			{			
			if(all[i].value==ss[j])
				{
				all[i].checked=true;
				}
			}
		}

}
</script>
	</head>
	<body bgcolor="#F3F4F8" leftmargin="0" topmargin="4"
		oncontextmenu="return false" onselectstart="return false" onload="tt()">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<s:hidden id="departmentList" name="departmentList"></s:hidden>
			<tr>
				<td valign="top" id="mainright" height="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="136" valign="top" bgcolor="#FFFFFF">
								<table cellspacing=2 cellpadding=4 border=0 width="100%"
									style="font-size: 12px">
									<s:iterator value="allDepartmentList" var="serviceCategory" status='st'>
										<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
											onMouseOut="this.bgColor='#FFFFFF'">
											<td width="13%" nowrap class="subtitle">
											
											<input name="chose" type="checkbox"
													value="<s:property value='id'></s:property>"
													style="border: 0px"
													onClick="Chose(this.value)">
                                               <s:property value="name" />
										
												
											</td>
										</tr>
									</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>