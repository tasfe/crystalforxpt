<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" import="net.fckeditor.*"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>新建供应商厂商</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js">
</SCRIPT>
		<SCRIPT type="text/javascript" src="../fckeditor/fckeditor.js">
</SCRIPT>
		<SCRIPT type="text/javascript" src="../js/jquery.js">
</SCRIPT>
		<SCRIPT type="text/javascript">

				
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

function checkPhone(strPhone) {
	var phoneRegWithArea = /^[0][1-9]{1,2}[0-9]{1}-[0-9]{5,8}$/;
	var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/;
	if (strPhone.length > 9) {
		if (phoneRegWithArea.test(strPhone)) {
			return true;
		} else {
			return false;
		}
	} else {
		if (phoneRegNoArea.test(strPhone)) {
			return true;
		} else {
			return false;
		}
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
		var len = 0;       
         for (var i=0; i<name.length; i++){           
   if (name.charCodeAt(i)>127 || name.charCodeAt(i)==94)
   {               
      len += 2;            //中文加2
     }else{               
      len ++;           
     }       
      }       
       if(len>100)
		{
			
			alert("标题最大50个汉字或者100个字符！");
			return false;
			}
		var note=document.getElementById("workLog.note").value;
		var len = 0;       
        for (var i=0; i<note.length; i++){           
      if (note.charCodeAt(i)>127 || note.charCodeAt(i)==94)
      {               
      len += 2;            //中文加2
      }else{               
       len ++;           
      }       
}  
		if(note.length>100){alert("摘要最大50个汉字或者100个字符！");return false;}
        var oEditor = FCKeditorAPI.GetInstance("InstanceName");  

         if(oEditor.EditorDocument.body.innerText.length<0)
        	 {
        	 alert("日志内容不允许为空！");
        	 return false;
        	 }
        
        var time=document.getElementById("id2").value.trim();
		if(time=="")
		{
		alert("请输入时间！");
		return false;
		}
		return true;
	}
</SCRIPT>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="padding: 7px;">
		<table align="center" width="786px">
			<s:form action="%{actionURL}.action" namespace="/worklog" method='post'
				theme="simple">
				<s:hidden name="page" id="page"></s:hidden>
				<s:hidden name="pageSize" id="pageSize"></s:hidden>
				<s:hidden name="workLog.id"></s:hidden>
				<tr>
					<td height="265" style="width: 786px">
						<table cellpadding="2" cellspacing="1" bgcolor="#b5d6e6"
							style="width: 790px; height: 250px">
							<tr>
								<td width="10%" align="center" bgcolor="#deebf1">
									标 题：
								</td>
								<td align="center" width="90%" bgcolor="#FFFFFF">
									<s:textfield id="workLog.title" name="workLog.title" size="120" />
								</td>
							</tr>
							<tr>
								<td width="10%" align="center" bgcolor="#deebf1">
									摘 要：
								</td>
								<td width="90%" align="center" bgcolor="#FFFFFF">
									<s:textfield name="workLog.note" id="workLog.note" size="120" />
							</tr>
							<!--    <tr> 
                        <td width="16%" height="20" align="center" bgcolor="#deebf1">作 者:</td>
                        <td width="84%" bgcolor="#FFFFFF"><input type="text" name="workLog.users.truename" style="width:100%;"/></td>
                      </tr>    -->
							<tr>
								<td width="10%" align="center" nowrap bgcolor="#deebf1">
									类 型：
								</td>
								<td align="center" bgcolor="#FFFFFF">
									<p>
										<label>
											<input name="workLog.type" type="radio" id="worklog.type_0"
												value="1" />
											个人日志
										</label>

										<label>
											<input name="workLog.type" type="radio" id="worklog.type_1"
												value="2" checked="checked" />
											部门日志
										</label>

										<label>
											<input type="radio" name="workLog.type" value="3"
												id="worklog.type_2" />
											公开日志
										</label>

									</p>
								</td>
							</tr>
							<tr>
								<td width="10%" align="center" nowrap bgcolor="#deebf1">
									内 容：
								</td>
								<td width="90%" align="center" bgcolor="#FFFFFF">

									<FCK:editor instanceName="workLog.content" toolbarSet="Basic"
										basePath="/fckeditor" value="${workLog.content}"></FCK:editor>
								</td>
								<!--     <s:textarea name="workLog.content" id="workLog.content"/></td>-->
							</tr>
							<tr>
								<td align="center" nowrap bgcolor="#deebf1">
									时 间：
								</td>
								<td align="left"
									tyle="padding: 0px; border-bottom: 1px solid buttonface"
									bgcolor="#FFFFFF">



									<s:textfield name="workLog.time" id="id2" size="100"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})"></s:textfield>
									<img
										onClick="WdatePicker({el:$dp.$('id2'),dateFmt:'yyyy-MM-dd',minDate:'%y-%M-{%d}'})"
										src="../js/DatePicker/skin/datePicker.gif" width="20"
										height="22" />
                                                                        
									<SCRIPT LANGUAGE="JavaScript">

var myDate = new Date();
   document.getElementById("id2").value= myDate.toLocaleDateString();     //获取当前日期
  

//-->
</SCRIPT>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td height="25" colspan="2" align="center" nowrap="nowrap">
						<input name="submit" type="submit" value="保存 " class="mmBtn"
							onclick="javascript:return formValidate()" />
						&nbsp;&nbsp;
						<input name="reset" type="reset" value="重置 " class="mmBtn" />
						&nbsp;&nbsp;
						<input name="button" type="button" onclick="window.history.go(-1)"
							value="返回 " class="mmBtn" />
					</td>
				</tr>
			</s:form>
		</table>
	</body>
</html>