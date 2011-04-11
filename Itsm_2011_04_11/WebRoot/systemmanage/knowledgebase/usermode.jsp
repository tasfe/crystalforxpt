<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!--TestRecords:500//-->
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>
 
<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="../cn_css/style.css">
</head>
<body leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2">
<script language="JavaScript"> 
function Sub(){
	if (document.getElementById("RequObj").value!="") {
		window.open('about:blank','search','width=600,height=400,scrollbars=no,menubar=no,resizable=yes,top=60,left=60,status=yes');
		document.getElementById('post').submit();
	}
}
function Report(){
	var Values = document.getElementById("VALUES").value;
	var Start = document.getElementById("Start").value;
	var End = document.getElementById("End").value;
	var Url = Values + "&Start=" + Start + "&End=" + End
	if (Values!="") {
		window.open(Url,"","width=668,height=600,scrollbars=yes,menubar=no,resizable=yes,top=30,left=70,status=yes");
	}
}
</script>
 
<div OnMouseDown="gfPop.hiddencld()">
<iframe width=168 height=190 name="gfPop" id="gfPop" src="../function/calendar/ipopeng.asp" scrolling="no" frameborder="0" style="border:1px outset; visibility:visible; z-index:999; position:absolute; left:-600px; top:0px;"></iframe>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td valign="top" id="mainright" height="100%">
	  <table width="100%" border="0" cellspacing="0" cellpadding="1" height="100%">
        <tr> 
          <td background="../img/title_bg.jpg" class="title" valign="top" style="padding-top: 8px" width="99%" height="12"><img src="../ico/knowlg.jpg" width="209" height="22"></td>
          <td align="right" background="../img/title_bg.jpg"><img src="../img/title.jpg"></td>
		</tr>
		<tr><td colspan="2" height="2"></td></tr>
		<tr>
		  <td height="12" colspan="2" valign="top">
			<table border="0" cellspacing=0 cellpadding=0 width="100%">
			  <tr>
                <td width="49%" height="30" style="padding-top: 7px" nowrap><b><img src="../img/index.jpg" width="20" height="19" align="absmiddle">&nbsp;按普通用户模式查看:</b></td>
				<td align=right height="30" nowrap>
				<span class="clsButtonFace"><a onClick="window.location='../sla/?NowAction=knowlgmana'">&nbsp;按工程师模式查看&nbsp;</a></span>&nbsp;
				<span class="clsButtonFace"><a onClick="window.location='../sla/?NowAction=knowlgmana&type=new'">&nbsp;按用户模式查看&nbsp;</a></span>&nbsp;
				<span class="clsButtonFace"><a onClick="window.open('../sla/?NowAction=modifysolution','','width=700,height=600,scrollbars=no,menubar=no,resizable=yes,top=30,left=70,status=yes')">&nbsp;添加解决方案&nbsp;</a></span>
				
				</td>
              </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td valign="top" height="99%" colspan="2">
            <table cellspacing=0 cellpadding=1 border=1 width="100%" class="tb-list" style="border: 1px outset white" height="100%">
              <tr> 
                <th width="1%" nowrap>索引</th>
              </tr>
              <tr> 
                <td width="10%" valign="top"><table width="100%" height="100%" border="0" cellpadding="10" cellspacing="0">
                    <tr> 
                      
					  <td width="46%" height="100%" valign="top" style="padding: 0px; padding-left: 12px; padding-top: 10px"> 
					  <form name="post" method="post" action="../desk/default.asp?NowAction=search&type=1" target="search">
                        <input type="hidden" value="Inci" Name="Cat" ID="Cat">
						<table width="100%" height="100%" border="0" cellpadding="5" cellspacing="0">
                          <tr> 
                            <td height="12" style="padding-left: 3px"><img src="../img/key.jpg" width="149" height="24"> 
                            </td>
                          </tr>
                          <tr> 
                            <td height="12"> 
                                <table width="80%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td width="99%"><input name="RequObj" type="text" id="RequObj" style="font-size: 12px; height: 25px; width: 100%; color: green; padding: 3px; border-right: 0px" maxlength="20" onClick="document.getElementById('Layer').style.visibility='visible'"></td>
                                    <td valign="top"><img src="../img/chose.jpg" style="border: 1px inset #CCCCCC; border-left: 0px; cursor: hand" height="23" onClick="document.getElementById('Layer').style.visibility='visible'"></td>
                                  </tr>
                                  <tr>
                                    <td colspan="2"><div id="Layer" style="position:absolute; width: 100%; height:20px; z-index:99; visibility: default; text-align: right"><table width="1%" border="0" cellspacing="1" cellpadding="0" style="border: 1px outset white" background="../img/cldbg.jpg"><tr><td style="padding-top: 2px"><input name="ForCat" type="radio" style="border: 0px" checked onClick="window.Solut.document.getElementById('ProblemList').style.display='None';window.Solut.document.getElementById('RequestList').style.display=''"></td><td nowrap>事件&nbsp;&nbsp;</td></tr><tr><td style="padding-top: 2px"><input name="ForCat" type="radio" style="border: 0px" value="Prob" onClick="window.Solut.document.getElementById('ProblemList').style.display='';window.Solut.document.getElementById('RequestList').style.display='None'"></td><td nowrap>问题&nbsp;&nbsp;</td></tr></table></div></td>
                                  </tr>
                                </table>
							</td>
                          </tr>
                          <tr> 
                            <td height="12" colspan="2"> 
                              <b>选项：</b></td>
                          </tr>
                          <tr>
                            <td height="12" colspan="2" style="padding-left: 0px">
							<table width="80%" border="0" cellspacing="0" cellpadding="3">
                                <tr> 
                                  <td width="5%" height="25" style="padding-top: 6px; padding-left: 0px"> 
                                    <input name="keys" type="radio" value="1" style="border: 0px"> 
                                  </td>
                                  <td width="95%" style="font-family: Tahoma" nowrap>只在文档标题中检索...</td>
                                </tr>
                                <tr> 
                                  <td height="25" style="padding-top: 6px; padding-left: 0px"> <input name="keys" type="radio" value="2" checked style="border: 0px"> 
                                  </td>
                                  <td style="font-family: Tahoma" nowrap>同时检索文档标题和帮助内容...</td>
                                </tr>
                                <tr> 
                                  <td>&nbsp;</td>
                                  <td height="30" align="right" valign="bottom" style="padding: 0px; padding-top: 18px"><input type="button" value="开始搜索…" class=mmBtn name="button" onClick="Sub()"></td>
                                </tr>
                              </table></td>
                          </tr>
                          <tr> 
                            <td align="right" valign="bottom" style="padding: 0px; padding-bottom: 8px"><img src="../img/bigask.jpg" width="140" height="138"></td>
                          </tr>
                        </table>
                      </form>
					  </td>
					  
					  <td width="40%" height="100%" rowspan="2" valign="top" id="solutionsm" style="padding: 15px"> 
                        <table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
                          <tr> 
                            <td width="100%" height="99%" valign="top" bgColor="#FFFFFF"> 
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                <tr> 
                                  <td height="100%" valign="top" width="100%" style="border: 1px inset"><iframe frameborder="0" height="100%" width="100%" scrolling="yes" src="../home/?NowAction=treeview_mana&ID=Service_Cat&type=new&Prob=1" id="Solut" name="Solut"></iframe></td>
                                </tr>
								
                              </table></td>
                          </tr>
                        </table></td>
					  
                    </tr>
                  </table></td>
              </tr>
			</table>
          </td>
		</tr>
	  </table>
	</td>
  </tr>
</table>
</div>
<script language="javascript"> 
if (window.top.location.href.indexOf("itsm.htm")>0){
	var Url = window.location.href;
	Url = Url.replace(/\&/g,"|@|");
	Url = Url.replace(/\#/g,"|$|");
	Url = Url.replace(/\?/g,"|~|");
	window.top.themain.mainit.topit.currurl.location.replace("../home/?NowAction=CurrURL&CurrURL="+Url);
}
</script>
</body>
</html>
<Script Language=Javascript src="../cn_css/mmBtn.js"></Script>
