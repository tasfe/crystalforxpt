<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
	<head>
		<title>知识库管理首页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/ServiceCategoryDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type="text/javascript">
			function init() { //取出类别
				ServiceCategoryDAO.findAll(callbackserviceCategory);
			}
			
			
     		function callbackserviceCategory(data){  //显示出分类
 				dwr.util.removeAllOptions("service_category");
   				dwr.util.addOptions("service_category", [{id:'-1',name:'--请选择--'}],"id","name");
   				dwr.util.addOptions("service_category",data,"id","itemZh");  
    			var a = "<s:property value="serviceRequest.serviceCategory.itemZh" />";
    			if (typeof(a) != "undefined") {   
     					dwr.util.setValue("service_category",a);  
   				} 
			}
			
			function del(){   
				var msg="确认删除记录吗？";   
				if (confirm(msg) == true)  {   
        			return true;   
   				}   
    			else {   
        			return false;   
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
</head>
<body  onLoad="init()" leftmargin="2" topmargin="2" rightmargin="1" bottommargin="2" style="overflow:auto">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
	<tr>
		<td width="2%" height="22" align="center" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
			<img src="../images/modpass.gif" width="16" height="16">
		</td>
		<td width="98%" background="../images/main20100521_582.gif" style="color: #FFFFFF; font-weight: bold;">
			知识库管理
		</td>
	</tr>
</table>
<div>
<iframe width=168 height=190 name="gfPop" id="gfPop" src="../function/calendar/ipopeng.asp" scrolling="no" frameborder="0" style="border:1px outset; visibility:visible; z-index:999; position:absolute; left:-600px; top:0px;"></iframe>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td valign="top" id="mainright" height="100%">
	  <table width="100%" border="0" cellspacing="0" cellpadding="1" height="100%">
	  
	  	<tr><td colspan="2" height="12"></td></tr>	  	        
		<tr>
		  <td height="12" colspan="2" valign="top">
			<table border="0" cellspacing=0 cellpadding=0 width="100%">
			  <tr>
                <td width="49%" height="30" style="padding-top: 7px" nowrap><b><img src="../img/index.jpg" width="20" height="19" align="absmiddle">&nbsp;按工程师管理模式查看:</b></td>
				<td align=right height="30" nowrap>
				<span class=clsButtonFace><input name="button1" type="button" onClick="window.location='list.action'" value="按工程师模式查看" class="mmBtn">&nbsp;</span>&nbsp;
				<span class=clsButtonFace><input name="button2" type="button" onClick="window.location='list2.action'" value="按用户模式查看" class="mmBtn">&nbsp;</span>&nbsp;
				<tags:button code="add" menu="24">	<span class=clsButtonFace><input name="button3" type="button"  value="添加解决方案" class="mmBtn" onClick="window.location='addInput.action'">&nbsp;</span>
				</tags:button>
				</td>
              </tr>
			</table>
		  </td>
		</tr>
		<tr>
		  <td valign="top" height="99%" colspan="2">
            <table cellspacing=0 cellpadding=1 border=1 width="100%" class="tb-list" style="border: 1px outset white" height="100%">
              <tr> 
                <td width="1%" height="22" align="left" nowrap  background="../images/main20100521_58.gif">&nbsp;&nbsp;<B>索引</B></td>
              </tr>
              <tr> 
                <td width="10%" valign="top"><table width="100%" height="100%" border="0" cellpadding="10" cellspacing="0">
                    <tr> 
                      
					  <td width="40%" height="100%" rowspan="2" valign="top" id="solutionsm" style="padding: 2px"> 
                        <table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
                          <tr> 
                            <td width="100%" height="99%" valign="top" bgColor="#FFFFFF"> 
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                <tr> 
                                  <td height="100%" valign="top" width="100%" style="border: 1px inset; border-bottom: 0px">
                                  	<iframe frameborder="0" height="100%" width="100%" scrolling="no" src="top.action" id="Solut" name="Solut"></iframe>
                                  </td>
                                </tr>
								
								<tr>
								  <td height="20"><table width="100%" border="0" cellspacing="0" cellpadding="4">
									  <tr>
										<td width="1%" nowrap bgcolor="#deebf1" style="padding-bottom: 0px; padding-right: 0px"><img src="../img/i.gif" align="absmiddle">&nbsp;<b>关键字</b>:</td>
										<td bgcolor="#deebf1" nowrap colspan="2" style="border-right: 1px inset; padding: 0px; padding-right: 4px" valign="top"> 
                                          <!--  
                                          <table border="0" cellspacing="0" cellpadding="0" height="24" width="100%">
                                            <tr>
                                              <td background="../img/trleft.jpg" width="46"></td>
                                              <td width="99%"></td>
                                              <td style="padding-top: 2px"><input name="tCat" type="radio" style="border: 0px" onClick="document.getElementById('Cat').value='Inci'" checked></td>
                                              <td nowrap>事件知识库</td>
                                              <td style="padding-top: 2px; padding-left: 8px"><input name="tCat" type="radio" style="border: 0px" onClick="document.getElementById('Cat').value='Prob';"></td>
                                              <td nowrap>问题知识库</td>
                                            </tr>
                                          </table> 
                                          -->
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
											  <input name="Cat" type="hidden" id="Cat" value="Inci">
											  </td>
                                              <td>
											  <select name="Type">
												<option value="Title">仅标题</option>
												<option value="Content">标题及内容</option>
												<option value="ITer">按工程师</option>
												<!--<option value="NewAdd">未审批条目</option>  -->
											  </select>
											  </td>
                                            </tr>
                                          </table>
										</td>
										<td width="1%" style="padding-left: 0px; border-right: 1px inset" background="../img/tr.gif">
										<input type="button" value="搜索" onClick="window.document.getElementById('solution').src='query.action?knowledgeBase.title='+encodeURI(document.getElementById('Key').value)+'&type='+document.getElementById('Type').value+'&flag='+document.getElementById('Cat').value" class=mmBtn_sm name="button" style="height: 20px"></td>
									</tr>
								  </table></td>
								</tr>
								
                              </table></td>
                          </tr>
                        </table></td>
					  
                      <td width="60%" height="100%" valign="top" id="solutionsm" style="padding: 2px"> 
                        <table width="100%" height="100%" border="0" cellpadding="1" cellspacing="0">
                          <tr> 
                            <td width="100%" height="99%" valign="top" bgColor="#FFFFFF"> 
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                                <tr> 
                                  <td height="100%" valign="top" width="100%" style="border: 1px inset; border-bottom: 0px">
                                  	<iframe frameborder="0" height="100%" width="100%" id="solution" name="solution" scrolling="yes" src="query.action"></iframe>
                                  </td>
                                </tr>
								<!--  
								<tr>
								  <td height="20" disabled>
									  <table width="100%" border="0" cellspacing="1" cellpadding="3" bgcolor="#E5EAEF">
							  			<tr>
											<td width="50%">
								  				<select id="VALUES" name="VALUES" style="width: 100%">
													<option value="">事件知识库按得分率统计</option>
								 				</select>
											</td>
											<td style="padding-left: 0px">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  					<tr>
														<td width="98%">
															<input type="text" id="Start" name="" onclick="WdatePicker()" style="width:40%"/>
															<img onClick="WdatePicker({el:$dp.$('Start')})" src="../js/DatePicker/skin/datePicker.gif"/>
															&nbsp;&nbsp;~&nbsp;&nbsp;
															<input type="text" id="End" name="" onclick="WdatePicker()"  style="width:40%"/>
															<img onClick="WdatePicker({el:$dp.$('End')})" src="../js/DatePicker/skin/datePicker.gif"/>
														</td>
								  					</tr>
												</table>
											</td>
											<td width="1%" style="padding-left: 0px"><input type="button" value="查看报表" onclick="Report();" class=mmBtn_sm name="button" style="height: 20px"></td>
							  			</tr>
									</table>
								 </td>
								</tr>
								-->								
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
</body>
</html>
