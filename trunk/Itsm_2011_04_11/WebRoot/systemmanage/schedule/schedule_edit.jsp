<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.Calendar" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>预约登记</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

function showDialog(url, args, features) {
	if (!features) features = {};
	if (!args) args = {};
	args.opener = window;
	var dialog;
	if (features.dependent && 'showModelessDialog' in window) {
		if (features.resizable == null) features.resizable = 1;
		if (features.status == null) features.status = 0;
		var f = [];
		if (features.width) f.push('dialogWidth:' + features.width);
		if (features.height) f.push('dialogHeight:' + features.height);
		if (features.top) f.push('dialogTop:' + features.top);
		if (features.left) f.push('dialogLeft:' + features.left);
		for (var key in features) {
			if (features.hasOwnProperty(key))
				f.push(key + ':' + features[key]);
		}
		dialog = window.showModelessDialog(url, args, f.join(';'));
		dialog.moveBy = function (x, y) {
			if (x) this.dialogLeft = parseInt(this.dialogLeft) + x + 'px';
			if (y) this.dialogTop = parseInt(this.dialogTop) + y + 'px';
		}
		dialog.resizeBy = function (x, y) {
			if (x) this.dialogWidth = parseInt(this.dialogWidth) + x + 'px';
			if (y) this.dialogHeight = parseInt(this.dialogHeight) + y + 'px';
		}
		dialog.opener = window;
	} else {
		var f = [];
		for (var key in features) {
			if (features.hasOwnProperty(key))
				f.push(key + '=' + features[key]);
		}
		dialog = window.open(url, 'dialog', f.join(','));
		dialog.dialogArguments = args;
	}
	return dialog;
}
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
<tr> 
    <td width="100%" valign="top"> 
	<table width="100%" border="0" cellpadding="0" cellspacing="0" background="../images/m_mpbg.gif"> 
          <tr> 
            <td class="place">预约登记</td> 
            <td>&nbsp;</td> 
            <td align="right">&nbsp;</td> 
            <td width="3"><img src="../images/m_mpr.gif" width="3" height="32" /></td> 
          </tr> 
    </table> 
    <form action="update.action" name="myForm" method="post">
     <s:hidden name="schedule.id" theme="simple"/>
     <s:hidden name="schedule.atime" theme="simple"/>
     <s:hidden name="schedule.userByAssigner.id" theme="simple"/>
    <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#C0CCDD" id="content">
      <tr class="top">
        <td height="35" width="80" align="right"><div align="right">执行人：</div></td>
        <td> <s:hidden name="schedule.userByExecutor.id" id="user_id" /><s:textfield name="schedule.userByExecutor.fullname" theme="simple" id="user_fullname" readonly="readonly" /><a href="#" onclick="showDialog('../admin/system/selectPerson2.jsp', {a:1, b:2}, {width:'300px', height:'200px', dependent:true});
        " ><img src="../images/user.gif" border="0" /></a></td>
        <td align="right"><div align="right"></div></td>
        <td></td>
      </tr>
      <tr>
        <td height="60" align="right">日程内容：</td>
        <td colspan="3" valign="middle"><s:textarea name="schedule.content" theme="simple" cols="3" rows="1" style="width:60%; height:45px;"></s:textarea></td>
        </tr>
      <tr>
        <td height="30" align="right">执行日期：</td>
        <td><s:textfield name="schedule.adate" theme="simple" id="d12" onclick="WdatePicker()" /><img onclick="WdatePicker({el:$dp.$('d12')})" src="../js/DatePicker/skin/datePicker.gif" width="16" height="22" />
        </td>
        <td height="30" align="right">具体时间：</td>
        <td>
		<select name="schedule.hour"> 
            <option value="00">00</option> 
            <option value="01">01</option> 
            <option value="02">02</option> 
            <option value="03">03</option> 
            <option value="04">04</option> 
            <option value="05">05</option> 
            <option value="06">06</option> 
            <option value="07">07</option> 
            <option value="08">08</option> 
            <option value="09">09</option> 
            <option value="10">10</option> 
            <option value="11">11</option> 
            <option value="12">12</option> 
            <option value="13">13</option> 
            <option value="14">14</option> 
            <option value="15">15</option> 
            <option value="16">16</option> 
            <option value="17">17</option> 
            <option value="18">18</option> 
            <option value="19">19</option> 
            <option value="20">20</option> 
            <option value="21">21</option> 
            <option value="22">22</option> 
            <option value="23">23</option> 
          </select> 
		时
		<select name="schedule.minute"> 
		  <option value="00">00</option> 
		  <option value="05">05</option> 
		  <option value="10">10</option> 
		  <option value="15">15</option> 
		  <option value="20">20</option> 
		  <option value="25">25</option> 
		  <option value="30">30</option> 
		  <option value="35">35</option> 
		  <option value="40">40</option> 
		  <option value="45">45</option> 
		  <option value="50">50</option> 
		  <option value="55">55</option> 
		</select> 
		分
        </td>
        </tr>
      <tr>
        <td height="30" align="right">重要级别：</td>
        <td colspan="3">
        <input name="schedule.level" type="radio" value="4" checked="checked" /> 
          重要且紧急　
            <input type="radio" name="schedule.level" value="3" /> 
            重要不紧急　
            <input type="radio" name="schedule.level" value="2" /> 
            紧急不重要　
            <input type="radio" name="schedule.level" value="1" /> 
            不紧急不重要
        </td>
        </tr>
      <tr> 
        <td height="37" colspan="4" class="gray"> 
           <div align="center"> 
               <input type="submit" name="Submit" value="保存" class="page"/>
           </div>
        </td> 
      </tr> 
      </table>
      </form>
	</td> 
  </tr> 
</table> 
</body>
</html>