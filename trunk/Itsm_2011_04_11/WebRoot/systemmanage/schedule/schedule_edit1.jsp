<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.Calendar" isELIgnored="false" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>预约登记</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript"
			src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/UserDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">

     function getDAO() { //取出类别
	       UserDAO.findAll(callbackusers);
          }
          
          function callbackusers(data){  //显示出用户
   dwr.util.removeAllOptions("users");
   dwr.util.addOptions("users",{'-1':'--请选择--'});
   dwr.util.addOptions("users",data,"id","truename");   
   var a = "<s:property value="schedule.userByExecutor.id" />";
    		if (typeof(a) != "undefined") {   
     			dwr.util.setValue("users",a);  
   			} 
}

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

	<body onLoad="getDAO()" leftmargin="0" topmargin="0" marginwidth="0"
		marginheight="0" style="padding: 7px;; cursor: default;">

		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			background="../images/m_mpbg.gif">
			<tr>
				<td width="1%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold; padding-left: 5px; padding-right: 5px;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="98%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					预约登记:
				</td>
			</tr>

			<tr>
				<td height="30" colspan="5"
					background="../images/main20100521_58.gif">
					<table width="60%" border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td width="3%" align="center"
								background="../images/main20100521_58.gif"
								style="color: #FFFFFF; font-weight: bold; padding-right: 5px;">
								<img src="../images/modpass.gif" width="16" height="16">
							</td>
							<td width="98%" style="color: #333333; font-weight: bold;">
								更新预约登记
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30" colspan="6" width="100%"
					background="../images/main20100521_58.gif">
					<table width="100%" border=0 cellpadding=0 cellspacing=0>
						<tr>
							<td width="98%" style="color: #333333; font-weight: bold;"></td>
						</tr>

					</table>


					<form action="update.action" name="myForm" method="post">
						<s:hidden name="schedule.id" theme="simple" />
						<s:hidden name="schedule.atime" theme="simple" />
						<s:hidden name="schedule.userByAssigner.id" theme="simple" />
						<table width="100%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td bgColor="white" valign="top">
									<table width="100%" border="0" cellspacing="1" cellpadding="2"
										bgcolor="#b5d6e6" height="100%">

										<tr>
											<td width="13%" height="5%" align="right" valign="middle"
												bgcolor="#deebf1">
												执行人:
											</td>
											<td width="87%" bgcolor="#FFFFFF">
												<select id="users" name="schedule.userByExecutor.id"
													style="width: 100%"></select>
											</td>
										</tr>

										<tr>
											<td width="13%" height="30%" align="right" valign="middle"
												bgcolor="#deebf1">
												日程内容:
											</td>
											<td width="87%" bgcolor="#FFFFFF">
												<table width="100%" height="100%" border="0" cellpadding="0"
													cellspacing="1" bgcolor="#b5d6e6">

													<tr>
														<td colspan=3 bgcolor="#EBF4F5" style="padding: 5px">
															<s:textarea name="schedule.content" theme="simple"
																cols="3" rows="1" style="width:100%; height:100px;"></s:textarea>
														</td>
													</tr>
												</table>
											</td>
										</tr>


										<tr>
											<td width="13%" height="5%" align="right" valign="middle"
												bgcolor="#deebf1">
												执行日期:
											</td>
											<td width="87%" bgcolor="#FFFFFF">
												&nbsp;&nbsp;
												<s:textfield name="schedule.adate" theme="simple" id="d12"
													onclick="WdatePicker()" />
												<img onclick="WdatePicker({el:$dp.$('d12')})"
													src="../js/DatePicker/skin/datePicker.gif" width="16"
													height="22" />
											</td>
										</tr>

										<tr>
											<td width="13%" align="right" valign="middle"
												bgcolor="#deebf1" height="22">
												具体时间:
											</td>
											<td width="87%" bgcolor="#FFFFFF" height="22">
												&nbsp;
												<select name="schedule.hour">
													<option value="00">
														00
													</option>
													<option value="01">
														01
													</option>
													<option value="02">
														02
													</option>
													<option value="03">
														03
													</option>
													<option value="04">
														04
													</option>
													<option value="05">
														05
													</option>
													<option value="06">
														06
													</option>
													<option value="07">
														07
													</option>
													<option value="08">
														08
													</option>
													<option value="09">
														09
													</option>
													<option value="10">
														10
													</option>
													<option value="11">
														11
													</option>
													<option value="12">
														12
													</option>
													<option value="13">
														13
													</option>
													<option value="14">
														14
													</option>
													<option value="15">
														15
													</option>
													<option value="16">
														16
													</option>
													<option value="17">
														17
													</option>
													<option value="18">
														18
													</option>
													<option value="19">
														19
													</option>
													<option value="20">
														20
													</option>
													<option value="21">
														21
													</option>
													<option value="22">
														22
													</option>
													<option value="23">
														23
													</option>
												</select>
												时
												<select name="schedule.minute">
													<option value="00">
														00
													</option>
													<option value="05">
														05
													</option>
													<option value="10">
														10
													</option>
													<option value="15">
														15
													</option>
													<option value="20">
														20
													</option>
													<option value="25">
														25
													</option>
													<option value="30">
														30
													</option>
													<option value="35">
														35
													</option>
													<option value="40">
														40
													</option>
													<option value="45">
														45
													</option>
													<option value="50">
														50
													</option>
													<option value="55">
														55
													</option>
												</select>
												分
											</td>
										</tr>


										<tr>
											<td width="13%" height="30%" align="right" valign="middle"
												bgcolor="#deebf1">
												重要级别:
											</td>
											<td width="87%" bgcolor="#FFFFFF">
												<table width="100%" height="100%" border="0" cellpadding="0"
													cellspacing="1" bgcolor="#b5d6e6">

													<tr>
														<td colspan="3" bgcolor="#FFFFFF">
															<br />
															<input name="schedule.level" type="radio" value="4"
																checked="checked" />
															重要且紧急
															<br />
															<br />
															<input type="radio" name="schedule.level" value="3" />
															重要不紧急
															<br />
															<br />
															<input type="radio" name="schedule.level" value="2" />
															紧急不重要
															<br />
															<br />
															<input type="radio" name="schedule.level" value="1" />
															不紧急不重要
															<br>
															<br>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" height="12" style="padding-top: 5px">
									<input class="mmBtn" name="submit" type="submit"
										style="width: auto" value="保存">
									&nbsp;
									<input name="button" type="button" class="mmBtn"
										style="width: auto" onClick="window.history.go(-1)"
										value="返回">
								</td>
							</tr>

							<tr>
								<td height="30" colspan="6" width="100%"
									background="../images/main20100521_58.gif">
									<table width="100%" border=0 cellpadding=0 cellspacing=0>
										<tr>
											<td width="98%" style="color: #333333; font-weight: bold;"></td>
										</tr>
									</table>
									<form>
	</body>
</html>