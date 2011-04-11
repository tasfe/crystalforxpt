<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<title>IT运维系统</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

img {
	border: 0px;
}
-->
</style>		
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
		<SCRIPT type="text/javascript">
	 if (window != top){
	 	top.location.href = location.href;
	 } 
	function fullscr() {
		var a = screen.width + 8;
		var b = screen.height - 22;
		window.moveTo(-4, -4);
		window.resizeTo(a, b);
		document.all('uidtxt').focus();
	}
	function Auto_keydown() {
		if (event.keyCode == 13) {
			if (event.srcElement.name == "yzmtxt") {
				login_confirm();
			} else {
				event.keyCode = 9;
			}
		}
	}
	function passwordPress(e){
		var val;  
		
	    if (!e)   
	    {   
	        e = window.event;   
	    }   
	  	if(!e){
	  	
	  	}
	    if (e.keyCode)   
	    {   
	        val = e.keyCode;   
	    }   
	    else if(e.which)    
	    {   
	        val = e.which;   
	    }   
		
	   	if(val==13){   
			login_confirm();
		}   
	}
	function login_confirm() {
		if (document.getElementById("uidtxt").value.replace(/^\s*/, "") == "") {
			document.getElementById("tsmsg").innerHTML = "用户名不能为空！";
			document.getElementById("uidtxt").focus();
			return false;
		}
		if (document.getElementById("pwdtxt").value.replace(/^\s*/, "") == "") {
			document.getElementById("tsmsg").innerHTML = "密码不能为空！";
			document.getElementById("pwdtxt").focus();
			return false;
		}
		document.getElementById("tsmsg").innerHTM = "";
		document.Form1.submit();
	}
	function init()
	{
		
	
	}
</SCRIPT>
	</HEAD>
	<BODY onload="init()">
		<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="58" valign="top">
					<table width="100%" height="58" border="0" cellpadding="0" cellspacing="0" class="login_top_bg"  background="../images/login-top-bg.jpg">
						<tr>
							<td width="100">&nbsp;</td>
							<td height="58"><img src="<%=path%>/images/logo.jpg" width="281" height="58"></td>
							<td align="center">&nbsp;</td>
							<td width="17%">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" height="473" border="0" cellpadding="0" cellspacing="0" class="login_bg" background="../images/login_bg1.jpg">
						<tr>
							<td width="59%" align="center">
								<table width="90%" height="473" border="0" cellpadding="0" cellspacing="0" class="login_bg2" background="../images/login-content-bg.jpg">
									<tr>
										<td valign="top">
											<table width="100%" height="427" border="0" align="right" cellpadding="0" cellspacing="0">
												<tr>
													<td height="80">&nbsp;</td>
												</tr>
												<tr>
													<td height="100" align="right" valign="top">
														<table width="90%" border="0" align="right" cellpadding="0" cellspacing="0">
															<tr>
																<td width="35%" height="32" valign="top">
																	<table width="90%" height="28" align="left" cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="6%" class="notice_tab" align="center">
																				<img src="<%=path%>/images/notice.gif" width="21" height="19">
																			</td>
																			<td width="56%" align="left" class="notice_tab">
																				<span class="login_txt_bt">公告列表</span>
																			</td>
																			<td width="34%" align="right" class="notice_tab">
																				<span class="left_txt"><s:a href="#" onclick="window.open('listUI.action')">更多&gt;&gt;</s:a></span>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<s:iterator value="pageBean.list" var="notice" status="st">
															<tr>
																<td width="35%" height="25" colspan="2" class="left_txt" align="left"><table width="90%" align="left" cellpadding="0" cellspacing="0">
																  <tr>
																    <td width="75%">· <s:a href="#" onclick="window.open('showUI.action?id=%{#notice.id}')"><s:property value="#notice.title" /></s:a></td>
																	<td width="25%" align="right">[<s:date name="#notice.date" format="yyyy-MM-dd HH:mm" />]</td>
															      </tr>
																  </table>
																</td>
															</tr>													
															</s:iterator>
														</table>
													</td>
												</tr>
												<tr>
													<td align="right" valign="top">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>

								</table>
							</td>
							<td width="1%">&nbsp;</td>
							<td width="40%" valign="bottom">
								<table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td>&nbsp;</td>
										<td align="center">
											<img src="<%=path%>/images/login.jpg" width="82" height="81">
										</td>
									</tr>
									<tr>
										<td width="4%">&nbsp;</td>
										<td width="96%" height="38">
											<span class="login_txt_bt">登陆IT运维系统</span>
											
										</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td height="21">
											<table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
												<tr>
													<td colspan="2" align="middle">
													<s:property value="msg"  />
													<s:form action="login" namespace="/" method="post" theme="simple" id="Form1" name="Form1">
														<table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table212">
<tr>
<td width="14%" height="38" colspan="2" align="left">
  <span class="login_txt">用户名：</span>
  <span><input name="username" id="uidtxt" size="30"  onkeypress="passwordPress(event)" style="width:200px;"></span>
</td>
</tr>
<tr>
<td width="14%" height="50" colspan="2" align="left">
  <span class="login_txt"> 密&nbsp;&nbsp;&nbsp; 码：</span>
  <span><input name="password" type="password" id="pwdtxt" onkeypress="passwordPress(event)" size="30" style="width:200px;"></span>																</td>
														  </tr>
															<tr>
																<td width="24" height="50"></td>
																<td align="left"><img src="<%=path%>/images/login_dl1.jpg" width="105" height="27" style="cursor: hand;" onClick="login_confirm();" /></td>
															</tr>
															<tr>
																<td height="20" colspan="2" align="center" style="font-size: 12px; color: #bfeaff">
																	<DIV style="COLOR: red;" id="tsmsg"></DIV>
																</td>
															</tr>
														</table>
													  </s:form>
													</td>
												</tr>
												<tr>
													<td width="433" height="164" align="right" valign="bottom">
														<img src="<%=path%>/images/login-wel.gif">
											    </td>
													<td width="57" align="right" valign="bottom">&nbsp;
														
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="65" valign="top" bgcolor="#254A7E"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg"  background="../images/login-buttom-bg.jpg">
				  <tr>
				    <td height="50"  align="center"><span class="login-buttom-txt">北京康邦科技有限公司 版权所有</span></td>
			      </tr>
			    </table></td>
			</tr>
		</table>




<!--


		<table width="787" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="50">
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<img src="<%=path%>/images/itsmlogin_03.jpg" width="387"
						height="138" alt="" />
				</td>
				<td>
					<img src="<%=path%>/images/itsmlogin_04.jpg" width="286"
						height="138" alt="" />
				</td>
				<td>
					<img src="<%=path%>/images/itsmlogin_05.jpg" width="114"
						height="138" alt="" />
				</td>
			</tr>
		</table>
		<table width="787" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<s:form action="login" namespace="/" method="post" theme="simple" id="Form1" name="Form1">
				<tr>
					<td width="467" align="center">
						<table width="467" border="0" cellspacing="0" cellpadding="0">
							<s:iterator value="pageBean.list" var="notice" status="st">
								<s:if test="#st.index<8">
									<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
										onMouseOut="this.bgColor='#FFFFFF'">
										<td align="left">
											<s:a href="#" onclick="window.open('showUI.action?id=%{#notice.id}')"><s:property value="#notice.title" /></s:a>
										</td>
										<td align="center">
											<s:date name="#notice.date" format="yyyy-MM-dd HH:mm" />
										</td>
									</tr>
								</s:if>
							</s:iterator>
							<tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
								onMouseOut="this.bgColor='#FFFFFF'">
								<td align="left" colspan="2">
									<s:a href="#" onclick="window.open('listUI.action')">更多...</s:a>
								</td>
							</tr>
						</table>
					</td>
					<td width="168" valign="top"
						background="<%=path%>/images/itsmlogin_08.jpg"
						style="padding-top: 23px;">
						<table width="168" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="32" style="font-size: 12px; color: #bfeaff">
									用户名：
								</td>
								<td colspan="2">
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 100px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=uidtxt tabIndex=1 maxLength=16
										size=18 type=text name=username>
								</td>
							</tr>
							<tr>
								<td height="32" style="font-size: 12px; color: #bfeaff">
									<font color="#bfeaff">密&nbsp;&nbsp;&nbsp;&nbsp;码：</font>
								</td>
								<td colspan="2">
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 100px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=pwdtxt tabIndex=2 maxLength=16
										size=18 type=password name=password>
								</td>
							</tr>
							<%--
							<tr>
								<td height="32" style="font-size: 12px; color: #bfeaff"><font color="#bfeaff">验证码：</font></td>
								<td>
									<INPUT
										style="BORDER-BOTTOM: #004bbb 1px solid; BORDER-LEFT: #004bbb 1px solid; BACKGROUND-COLOR: #caf1f6; WIDTH: 45px; COLOR: #0b62ae; FONT-SIZE: 9pt; BORDER-TOP: #004bbb 1px solid; BORDER-RIGHT: #004bbb 1px solid"
										onkeydown=Auto_keydown();; id=yzmtxt tabIndex=3 maxLength=4
										size=8 type=text name=yzmtxt>
								</td>
								<td><img style="VERTICAL-ALIGN: bottom; CURSOR: pointer"id="imgSrc" src="images/yzm.jpg" width="50" height="19" /></td>
							</tr>
							--%>
							<tr>
								<td height="20" colspan="3" align="right"
									style="font-size: 12px; color: #bfeaff">
									<DIV style="COLOR: red;" id=tsmsg></DIV>
								</td>
							</tr>
						</table>
					</td>
					<td width="79">
						<img id="div1" src="<%=path%>/images/itsmlogin_09.jpg" width="79"
							height="142" alt="" style="cursor: hand;"
							onClick="login_confirm();" />
					</td>
					<td width="73">
						<img src="<%=path%>/images/itsmlogin_10.jpg" width="73"
							height="142" alt="" />
					</td>
				</tr>
			</s:form>
		</table>
		<table width="168" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<img src="<%=path%>/images/itsmlogin_11.jpg" width="787"
						height="191" alt="" />
				</td>
			</tr>
		</table>    -->
	</BODY>
</HTML>
