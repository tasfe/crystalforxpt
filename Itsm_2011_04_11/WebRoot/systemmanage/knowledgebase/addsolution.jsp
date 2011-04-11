<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Solution</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x; padding: 4px; border: 1px inset; overflow: hidden" >
 
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td valign="top" id="mainright">
    
    <form name=myForm id=myForm action='../prob/default.asp?NowAction=dbdeal&type=SaveSolution&ID=' method='post' target="sum">
    
    <table width="100%" border="1" cellpadding="10" cellspacing="0" class="tb-list" height="100%" bgcolor="#deebf1">
	  <tr> 
		<td height="42">&nbsp;<img src="../../images/modpass.gif" align="absMiddle">&nbsp;<b>编辑解决方案(事件)</b></td>
		
		<td nowrap style="padding: 0px" width="1%">
		<a style="cursor: hand" onClick="window.location='../sla/?NowAction=modifysolutionprob'">&nbsp;<img src="../../images/modpass.gif" align="absMiddle">&nbsp;<b>转至:&nbsp;添加问题知识..</b>&nbsp;</a>
		</td>
		
	  </tr>
	  <tr bgcolor="#FFFFFF"> 
		<td width="88%" height="99%" colspan="2" valign="top"><table width="100%" border="1" bordercolor="#FFFFFF" cellspacing="0" cellpadding="4" height="100%" bgcolor="#deebf1">
			<tr id="l1" style="display: "> 
			  <td width="15%" height="12" style="padding-right: 20px" nowrap>标题:</td>
			  <td width="85%" height="12"><input name="Title" type="text" id="Title" style="width: 100%" value="" maxlength="50"></td>
			  </tr>
			  <tr id="l2" style="display: "> 
			  <td width="15%" height="12" nowrap style="padding-right: 20px" nowrap>事件所属类别:</td>
			  <td width="85%" height="12" style="padding-right: 0px"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
						<input type="text" name="Catename" style="width: 70%; cursor: text" readonly value="?" onClick="document.getElementById('Layer2').style.visibility='visible'">
						<input Name="RequObj" ID="RequObj" value="" type="hidden">
						<br>
						<div id="Layer2" style="position:absolute; width: 70%; height:20px; z-index:99; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white"><iframe frameborder="0" height="140" width="100%" scrolling="yes" src="../home/?NowAction=aptree&ID=Service_cat&Sear=1&Lct=Inci" style="border: 1px solid #E5E9EE"></iframe></div></td>
						<td width="1" style="padding-top: 3px"><input type="checkbox" class="noborder" name="TempL" value="1" ></td>
                        <td width="1%" nowrap>设为模板</td>
                        <td width="1" style="padding-top: 3px; padding-left: 10px"><input type="checkbox" class="noborder" name="TempR" value="1" ></td>
                        <td width="1%" nowrap>公开</td>
                      </tr>
                    </table></td>
			  </tr>
			  <tr id="l3" style="display: "> 
			  <td valign="top" class="td-left-s" nowrap style="padding-top: 5px; padding-right: 20px" height="12">症状描述:</td>
				<td class="td-right-s"><textarea name="RequDetail" style="line-height: 19px; width: 100%; height: 100px; padding-right: 5px; padding-left: 5px"></textarea> 
				</td>
			  </tr>
			  <tr id="l4" style="display: "> 
			  <td rowspan="2" valign="top" class="td-left-s" nowrap style="padding-top: 5px; padding-right: 20px" nowrap>附件:</td>
			  <td colspan="3" class="td-right-s" height="20" style="padding-top: 0px; padding-bottom: 0px"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
					<td><textarea name="UpFile1" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly class="td-right-s" onDblClick="window.open('../home/?NowAction=attach&Item=UpFile1&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea></td>
					<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" class="td-right-s" valign="bottom"><img src="../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile1').value=''" title="清除附件"></td>
				  </tr>
				</table></td>
			  </tr>
			  <tr id="l6" style="display: "> 
				<td colspan="3" class="td-right-s" height="20"><iframe name=upload src="../skin/upload/uploadpro.asp?fm=UpFile1" frameborder=0 width="99%" scrolling=no height=25></iframe></td>
			  </tr>
			  <tr> 
			  <td valign="top" class="td-left-s" nowrap style="padding-top: 5px; padding-right: 20px" id="l8">解决方案:</td>
				<td class="td-right-s"><textarea name="Remark" style="display: none"></textarea><IFRAME ID="eWebEditor1" src="#" frameborder="0" scrolling="no" width="100%" height="100%"></IFRAME>
				</td>
			  </tr>
			  <tr id="l5"> 
			  <td rowspan="2" valign="top" class="td-left-s" nowrap style="padding-top: 5px; padding-right: 20px" nowrap>附件:</td>
			  <td colspan="3" class="td-right-s" height="20" style="padding-top: 0px; padding-bottom: 0px"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr> 
					<td><textarea name="UpFile2" style="width: 300%; height: 20px; border: 0px; padding-left: 1px; overflow-y: scroll; overflow-x: hidden; background-image: url(../img/upfilex.gif); background-repeat: no-repeat; padding-left: 16px; cursor: hand" readonly class="td-right-s" onDblClick="window.open('../home/?NowAction=attach&Item=UpFile2&Value='+this.value,'','width=450,height=350,scrollbars=no,menubar=no,resizable=no,top=120,left=120,status=yes')"></textarea></td>
					<td width="1%" style="padding: 3px; padding-right: 0px; border-left: 2px solid white" class="td-right-s" valign="bottom"><img src="../img/del.gif" style="cursor: hand; Border-Bottom: 1px solid #333333; Border-Right: 1px solid #333333; Border-Top: 1px solid #C6CFD8; Border-Left: 1px solid #C6CFD8" onClick="document.getElementById('UpFile2').value=''" title="清除附件"></td>
				  </tr>
				</table></td>
			  </tr>
			  <tr id="l7"> 
				<td colspan="3" class="td-right-s" height="20"><iframe name=upload src="../skin/upload/uploadpro.asp?fm=UpFile2" frameborder=0 width="99%" scrolling=no height=25></iframe></td>
			  </tr>
			</table>
		</td>
	  </tr>
	</table>
	</form>
	</td>
	</tr>
	<tr>
	  <td height="12"> 
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
		  <tr>
			<td height="12" style="padding-top: 8px" nowrap><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a></td>
			<td align=right nowrap style="padding-top: 8px; padding-bottom: 0px"><input type="button" value="确认并保存" onClick="if(confirm('您确认要保存吗？')){myForm.submit()}" class=mmBtn name="button">&nbsp;<input type="button" onClick="if(confirm('此操作将会清空您已输入的信息，您确认继续吗？')){window.location.reload()}" value="全部重设" class=mmBtn name="button"></td>
		  </tr>
		</table>
	</td>
  </tr>
</table>
</body>
</html>
<Script Language=Javascript src="../cn_css/mmBtn.js"></Script>
