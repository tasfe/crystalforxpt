<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"  import="net.fckeditor.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<link href="../../css/style.css" rel="stylesheet" type="text/css">

<html>
<head>
<title>新建供应商厂商</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../fckeditor/fckeditor.js"></script>
<script type="text/javascript">

	String.prototype.trim = function()
	{
    	return this.replace(/(^\s*)|(\s*$)/g, ""); 
	}
	
	function checkPhone( strPhone ) { 
		var phoneRegWithArea = /^[0][1-9]{1,2}[0-9]{1}-[0-9]{5,8}$/; 
		var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/; 
		if( strPhone.length > 9 ) {
    		if( phoneRegWithArea.test(strPhone) ){return true; }else{return false;}
			}
		else{
    		if(phoneRegNoArea.test(strPhone)){return true; }else{return false;}
		}
	}
	
	function checkMobile( s ){ 
		var regu =/^[1][3,5][0-9]{9}$/; 
		var re = new RegExp(regu); 
		if (re.test(s)) { 
			return true; 
		}else{ 
			return false; 
		} 
	} 
	
	function formValidate() {
		var name=document.getElementById("workLog.title").value.trim();
		if(name=="") {alert("请输入标题！"); return false;}
		var content=document.getElementById("workLog.content").value.trim();
		if(content=="")
		{
		alert("请输入日志内容！");
		return false;
		}
		var time=document.getElementById("id2").value.trim();
		if(tine=="")
		{
		alert("请输入时间！");
		return false;
		}
		return true;
	}
</script>
</head>
<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding: 7px;">
  <table align="center">
    <s:form action="save" namespace="/worklog" method='post' theme="simple">
		 <tr> 
			 <td height="265">
						<table cellpadding="2" cellspacing="1" bgcolor="#b5d6e6"  style="width: 600px; height: 250px">
							<tr>
								<td width="12%" align="center" bgcolor="#deebf1">
									标 题：
								</td>
								<td width="88%" align="left" bgcolor="#FFFFFF">
								
										<s:property value="workLog.title"/>
								</td>
							</tr>
							<tr>
								<td width="12%" align="center" bgcolor="#deebf1">
									摘 要：
								</td>
								<td width="88%" align="left" bgcolor="#FFFFFF">
								<s:property value="workLog.note" escape="false"/>
								
						  </tr>
							<!--    <tr> 
                        <td width="16%" height="20" align="center" bgcolor="#deebf1">作 者:</td>
                        <td width="84%" bgcolor="#FFFFFF"><input type="text" name="workLog.users.truename" style="width:100%;"/></td>
                      </tr>    -->
							<tr align="right">
								<td width="12%" align="center"  nowrap
									bgcolor="#deebf1">
									内 容：
								</td>
								<td width="88%" align="left" bgcolor="#FFFFFF">
								<s:property value="workLog.content" escape="false"/> 
						
								</td>
							</tr>
							<tr>
								<td align="center" nowrap bgcolor="#deebf1">
									时 间：
								</td>
								<td align="left" bgcolor="#FFFFFF"
									tyle="padding: 0px; border-bottom: 1px solid buttonface" >
									<s:date name="workLog.time" format="yyyy/MM/dd"/>
								</td>
							</tr>
							<tr>
								<td align="center"  nowrap bgcolor="#deebf1">提交时间：</td>
								<td align="left" bgcolor="#FFFFFF"><s:date name="workLog.timesumbit" format="yyyy/MM/dd hh:mm:ss" /></td>
							</tr>
						</table>
					</td>
		</tr>
		<tr align="center">
			<td height="25" colspan="2" align="center" nowrap="nowrap">
				<input name="button" type="button" onClick="window.history.go(-1)" value="返回 " class="mmBtn"></td>
		</tr>
	</s:form>
  </table>
</body>
</html>