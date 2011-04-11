<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>资产状态管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
  <script type='text/javascript' src='/itsm/dwr/interface/AssetsBaseDAO.js'></script>
  <script type='text/javascript' src='/itsm/dwr/engine.js'></script>
  <script type='text/javascript' src='/itsm/dwr/util.js'></script>


<script type="text/javascript">
function del(){   
		var msg="确认删除记录吗？";   
		if (confirm(msg)==true)  { 
		return true;
   		}else{   
        	return false;   
   		}   
	}  
function newstate(){
	var obj="";
   var result=window.showModalDialog("../systemmanage/assetsstate/add.jsp",obj,"dialogWidth=400px;dialogHeight=380px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
   if(result==1){
    document.form1.action="list.action";
    document.form1.method="post";
    document.form1.submit();
   }      
}
function update(id){
var obj="";
   var result=window.showModalDialog("show.action?id="+id,obj,"dialogWidth=400px;dialogHeight=380px;dialogLeft=300px;dialogTop=300px;scroll:no;center:Yes;help:no;resizable:no;status:no;");
   if(result==1){
    document.form1.action="list.action";
    document.form1.method="post";
    document.form1.submit();
   }      
}
</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
	<form action="" method="post" name="form1">
	<table cellspacing=0 cellpadding=0 border=0 width="100%">
	<tr>
		<td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../images/modpass.gif" width="16" height="16"></td>
	    <td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产状态管理</td>
	</tr>
    </table>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
		<tags:button code="add" menu="8">
		<td height="30" align="right">
		<input type="button" class="mmBtn" value="状态添加" onClick="newstate();">
		</td>
		</tags:button>
		</tr>
	    </table>		
		   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
			<tr bgcolor="#FFFFFF">
			    <td height="22" align="center"
					background="../images/main20100521_58.gif" class="alllisttitle">
					状态名称
				</td>
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					状态顺序
				</td>
				<tags:button code="update" menu="8">
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					修改
				</td>
				</tags:button>
				<tags:button code="delete" menu="8">
				<td align="center" background="../images/main20100521_58.gif"
					class="alllisttitle">
					删除
				</td>
                </tags:button>
			</tr>
			<s:iterator value="assetsStatelist" var="assetsState">
				<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">
					<td height="19" align="center" class="zczb_qua">
						<s:property value="name" />
					</td>
					<td align="center" class="zczb_qua">
						<s:property value="sequence" />
					</td>
					<tags:button code="update" menu="8">
					<td align="center" class="zczb_qua">
						<img src="../images/edt.gif">
						<a href="#" onClick="update(${assetsState.id});">修改</a>
					</td>
					</tags:button>
					<tags:button code="delete" menu="8">
					<td align="center" class="zczb_qua"><img src="../images/del.gif"><a href="del.action?id=${assetsState.id}" onClick="javascript:return del()">删除</a>
					</td>
					</tags:button>
                 
				</tr>
			</s:iterator>
			<s:if test="message==1">
			<tr><td align="center" colspan="6" background="../images/main20100521_58.gif" class="alllisttitle">
			该状态暂时还不能删除！
			</td></tr>
			</s:if> 
			
		</table>
		</form>
	</body>
</html>
