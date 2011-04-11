<!--TestRecords:500//-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/TableTree4J.js"></script>
<script language="JavaScript">
function Chose(Num){

	window.parent.document.getElementById('user.department.id').value=Num;

}
function init() {
	  var l = window.parent.document.getElementById('user.department.id').value;
      gridTree=new TableTree4J("gridTree","../"); 
      gridTree.tableDesc="<table border=\"0\" class=\"GridView\" width=\"100%\" id=\"table1\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse: collapse\" bordercolordark=\"#C0C0C0\" bordercolorlight=\"#C0C0C0\" bgcolor=\"#FFFFFF\">"; 
      var headerDataList=new Array("部门名称","部门描述");
      var widthList=new Array("50%","50%");
      gridTree.setHeader(headerDataList,"0",widthList,true,"GridHead","","","","");    
      gridTree.gridHeaderColStyleArray=new Array("","");
      gridTree.gridDataCloStyleArray=new Array("",""); 
      var datalist;
      <s:iterator value="departmentList" var="department" >
	      	datalist=new Array('<input type="radio" name="check" id="<s:property value="id"/>" value="<s:property value="id"/>" onClick="if(this.checked){Chose(this.value)}" />'+'<s:property value="name"/>','<s:property value="description"/>');
	      	gridTree.addGirdNode(datalist,'<s:property value="id"/>','<s:property value="pid"/>',true,"","","","","","","",""); 	
      </s:iterator> 
      gridTree.printTableTreeToElement("tableTreeDepart");
      var checked=false;
	  if(l){
	  	document.getElementById(l).checked=true;
	  }
}

</script>
</head>
<body bgcolor="#F3F4F8" leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false" onload="init()">
	<!--  
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<s:hidden id="location" name="location"></s:hidden>
  		<tr> 
    		<td valign="top" id="mainright" height="100%"> 
      			<table width="100%" border="0" cellspacing="0" cellpadding="0">
        			<tr> 
          				<td height="136" valign="top" bgcolor="#FFFFFF">             
            				<table cellspacing=2 cellpadding=4 border=0 width="100%" style="font-size: 12px"> 
              					 <s:iterator value="departmentList" var="department" status='st'>
        							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
										<td width="13%" nowrap class="subtitle">
										
											<input name="chose" type="radio" value="<s:property value='id'></s:property>" style="border: 0px" onClick="if(this.checked){Chose(this.value)}">
											<input type="hidden"  id="<s:property value='id'></s:property>" value="<s:property value='name'></s:property>" >
											<s:property  value="name"/>
	
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
	-->
	<div id="tableTreeDepart">
	</div>
</body>
</html>