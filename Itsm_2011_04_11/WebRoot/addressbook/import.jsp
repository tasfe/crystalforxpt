<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>

<html>
	<head>
		<title>通讯录导入</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		function check() {
			var tag = 1;
			var str = "";
			var filename = document.getElementById("file").value;
			if (!filename) {
				str = str + "请选择要导入的文件!";
				tag = 0;
			} else {
				var arys = filename.split('.');
				var suffix = arys[arys.length - 1];
				var temp = "xls";
				if (suffix != temp) {
					str = str + "只能导入Excel文件!";
					tag = 0;
				}
			}
			if (tag == 1) {
				document.form1.submit();
			} else if (tag == 0) {
				alert(str);
			}
		}

	function change() {
		
		var filename = document.getElementById("file").value;
		
		document.getElementById("fileName").value = filename;
	}

	function download1() {
		
		document.location.href = "AddressBookManage/download.action?id=1";
	}
	
	function download2(){
		
		document.location.href = "AddressBookManage/download.action?id=2";
	}
	
	
</script>
	</head>

	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
		style="overflow: hidden;">
		<table cellspacing=0 cellpadding=0 border=0 width="100%">
			<tr>
				<td width="3%" height="22" align="center"
					background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					<img src="../images/modpass.gif" width="16" height="16">
				</td>
				<td width="97%" background="../images/main20100521_582.gif"
					style="color: #FFFFFF; font-weight: bold;">
					通讯录导入配置
				</td>
			</tr>
		</table>
		<div
			style="position: absolute; overflow-x: hidden; overflow-y: scroll; height: 95%; width: 100%; padding-top: 5px;">
			<table width="99%" border=0 align="center" cellpadding=2
				cellspacing=1 bgcolor="#b5d6e6">
				<tr height=30>
					<th height="22" colspan="6" align="left"
						background="../images/main20100521_58.gif" class="alllisttitle">
						从本地文件导入
					</th>
				</tr>
				<tr height=30>
					<td width="7%" rowspan="2" align="right" bgcolor="#EBF4F5"
						style="padding-top: 17px; padding-right: 10px">
						<img src="../img/ind.jpg" width="36" height="36" border="0">
					</td>
					<td width="100%" colspan="5" nowrap bgcolor="#FFFFFF"
						style="padding: 10px">
						<form name="form1" action="addressBookImport.action" method="post"
							enctype="multipart/form-data" namespace="/AddressBookManage">
							<table width="10%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<table width="10%" border="0" cellspacing="1" cellpadding="2"
											style="background-color: #FFFFFF; border: 1px solid #7B9BB4;">
											<tr
												style="background: url(../img/feedback.gif); background-position: right bottom;">
												<td nowrap>
													<td nowrap style="padding-left: 10px; padding-right: 8px">
														<img src="../img/kb.gif" width="14" height="14"
															align="absmiddle">
														&nbsp;
														<b>选择文件：</b>
													</td>
													<td nowrap>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" height="100%" bgcolor="#FFFFFF">
															<tr>
																<td style="border: 1px inset; padding-right: 1px">
																	<input type="hidden" name="fileName" id="fileName">
																	<input type="file" name="file" onChange="change();" style="border: 1px solid white; font-size: 12px; width:360px; text-align:right;">
																</td>
															</tr>
														</table>
													</td>
											</tr>
										</table>
									</td>
									<td>
										<img width="2">
									</td>
									<td>&nbsp;&nbsp;
										
									</td>
									<td height="100%">
										<tags:button code="import" menu="33">
											<input name="button" type="button" class="mmBtn" value="导 入"
												onClick="check();">
										</tags:button>
									</td>
									<td>&nbsp;&nbsp;
										
									</td>
									<td>
										<tags:button code="downloads" menu="33">
											<input id="1" name="downloads" type="button" class="mmBtn"
												value="公共通讯录Excel模板下载" onClick="download1();">
										</tags:button>
									</td>
									<td>
									&nbsp;&nbsp;
									</td>
									<td>
										<tags:button code="downloads" menu="33">
											<input id="2" name="downloads" type="button" class="mmBtn"
												value="个人通讯录Excel模板下载" onClick="download2();">
										</tags:button>
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<tr height=30>
					<td nowrap colspan="5" bgcolor="#F9F9F9"
						style="color: #3A4E69; line-height: 24px; padding: 10px; padding-left: 16px">
						
						1、请按照需要 ,选择"Excel模板下载"并按要求填写"Excel模板"，再行导入。
						<br>
						2、公共通讯录Excel模板中所属部门栏只能用数字代替:1--人民大学;2--网络中心;3--教务处;4--校办;5--建筑工程学院;6--计算机学院;
						<br>
						3、个人通讯录Excel模板中关系栏也用数字代替:1--好友;2--朋友;3--家人;4--新同事;5--陌生人;6--黑名单;
					</td>
				</tr>

			</table>
		</div>
		<script language="javascript">
	if (window.top.location.href.indexOf("itsm.htm") > 0) {
		var Url = window.location.href;
		Url = Url.replace(/\&/g, "|@|");
		Url = Url.replace(/\#/g, "|$|");
		Url = Url.replace(/\?/g, "|~|");
		window.top.themain.mainit.topit.currurl.location
				.replace("../home/?NowAction=CurrURL&CurrURL=" + Url);
	}
</script>
	</body>
</html>

