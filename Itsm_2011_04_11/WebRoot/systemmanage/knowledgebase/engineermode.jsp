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
                <td width="49%" height="30" style="padding-top: 7px" nowrap><b><img src="../img/index.jpg" width="20" height="19" align="absmiddle">&nbsp;按工程师管理模式查看:</b></td>
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
                      
					  <td width="40%" height="100%" rowspan="2" valign="top" id="solutionsm" style="padding: 15px"> 
                        <table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
                          <tr> 
                            <td width="100%" height="99%" valign="top" bgColor="#FFFFFF"> 
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                <tr> 
                                  <td height="100%" valign="top" width="100%" style="border: 1px inset; border-bottom: 0px"><iframe frameborder="0" height="100%" width="100%" scrolling="yes" src="../home/?NowAction=treeview_mana&ID=Service_Cat&type=&Prob=1" id="Solut" name="Solut"></iframe></td>
                                </tr>
								
								<tr>
								  <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="4">
									  <tr>
										<td width="1%" nowrap background="../img/tr.gif" style="padding-bottom: 0px; padding-right: 0px"><img src="../img/i.gif" align="absmiddle">&nbsp;<b>关键字</b>:</td>
										<td nowrap colspan="2" style="border-right: 1px inset; padding: 0px; padding-right: 4px" background="../img/trbg.jpg" valign="top"> 
                                          <table border="0" cellspacing="0" cellpadding="0" height="24" width="100%">
                                            <tr>
                                              <td background="../img/trleft.jpg" width="46"><img width="46" height="1"></td>
                                              <td width="99%"></td>
                                              <td style="padding-top: 2px"><input name="tCat" type="radio" style="border: 0px" onClick="document.getElementById('Cat').value='';window.Solut.document.getElementById('ProblemList').style.display='None';window.Solut.document.getElementById('RequestList').style.display=''" checked></td>
                                              <td nowrap>事件知识库</td>
                                              <td style="padding-top: 2px; padding-left: 8px"><input name="tCat" type="radio" style="border: 0px" onClick="document.getElementById('Cat').value='Prob';window.Solut.document.getElementById('ProblemList').style.display='';window.Solut.document.getElementById('RequestList').style.display='None'"></td>
                                              <td nowrap>问题知识库</td>
                                            </tr>
                                          </table> 
                                        </td>
									  </tr>
									  </table>
									  <table width="100%" border="0" cellspacing="0" cellpadding="4">
									  <tr>
										<td width="99%" background="../img/tr.gif" colspan="2">
                                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td width="99%" style="padding-right: 4px">
											  <input name="Key" type="text" id="Key" style="width: 100%; height: 20px">
											  <input name="Cat" type="hidden" id="Cat">
											  </td>
                                              <td>
											  <select name="Type">
												<option value="Title">仅标题</option>
												<option value="Content">标题及内容</option>
												<option value="ITer">按工程师</option>
												<option value="NewAdd">未审批条目</option>
											  </select>
											  </td>
                                            </tr>
                                          </table>
										</td>
										<td width="1%" style="padding-left: 0px; border-right: 1px inset" background="../img/tr.gif"><input type="button" value="搜索" onClick="window.solution.location='../desk/?NowAction=solutionmana&Type='+document.getElementById('Type').value+'&Key='+document.getElementById('Key').value+'&Cat='+document.getElementById('Cat').value" class=mmBtn_sm name="button" style="height: 20px"></td>
									</tr>
								  </table></td>
								</tr>
								
                              </table></td>
                          </tr>
                        </table></td>
					  
                      <td width="60%" height="100%" style="border-left: 2px solid #82A5CC; padding: 15px" valign="top" bgColor="#FFFFFF"> 
                        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                          <tr> 
                            <td height="100%"><iframe frameborder="0" height="100%" width="100%" id="solution" name="solution" scrolling="yes" src="../desk/?NowAction=solutionmana&Key=No"></iframe></td>
                          </tr>
                          <tr> 
                            <td height="12" style="padding: 1px">
							<table width="100%" border="0" cellspacing="1" cellpadding="3" bgcolor="#E5EAEF">
							  <tr>
								<td width="99%">
								  <select name="VALUES" style="width: 100%">
									<option value="../kpi/?NowAction=Rect&Langua=CN&Tjdx=Inci&Tiaojian=,and_(Score_>_'-1'_and_Score_<_'2'_and_(AddToKnowledge_$_'1')),and_(Score_>_'1'_and_Score_<_'4'_and_(AddToKnowledge_$_'1')),and_(Score_>_'3'_and_Score_<_'6'_and_(AddToKnowledge_$_'1')),and_(Score_>_'5'_and_Score_<_'8'_and_(AddToKnowledge_$_'1'))&Type=Y&Jbs=Y&Back=CCCCCC&Tjxm=Yrs&Values=&Tiaojian_NM=,0-2,2-4,4-6,6-8&SubCate=Y&Per=&TopID=Inci&Title_Report=事件知识库按得分率统计">事件知识库按得分率统计</option>
								  </select>
								</td>
								<td style="padding-left: 0px">
								<table width="130" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<td width="49%"><input Name="Start" ID="Start" value="2010-8-25" onclick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-right: 0px; text-align: left; padding-right: 0px"></td>
									<td width="2%"><input value="~" readonly style="width: 100%; border-right: 0px; border-left: 0px; text-align: center; padding-left: 0px; padding-right: 0px; background-color: #E5EAEF"></td>
									<td width="49%"><input Name="End" ID="End" value="2010-9-1" onclick="gfPop.fPopCalendar(this);return false" readonly style="width: 100%; border-left: 0px; text-align: right; padding-left: 0px"></td>
								  </tr>
								</table>
								</td>
								<td width="1%" style="padding-left: 0px"><input type="button" value="查看报表" onclick="Report()" class=mmBtn_sm name="button" style="height: 20px"></td>
							  </tr>
							</table>
							</td>
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
