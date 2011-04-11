<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../css/style.css" rel="stylesheet" type="text/css">

<html>
<head>
<title>资产历史对比统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/AssetsTypeDAO.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/interface/AssetsStateDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/AssetsProducerDAO.js'></script>
		
		<script>
		function init(){ //取出类别
		    AssetsStateDAO.findAll(callstate);
		    AssetsStateDAO.findAll(callstate1);
		    AssetsStateDAO.findAll(callstate2);
			AssetsProducerDAO.findByIntType(1,callbackproducer);
			AssetsProducerDAO.findByIntType(2,callbacksupporter);
			AssetsProducerDAO.findByIntType(1,callbackproducer2);
			AssetsProducerDAO.findByIntType(2,callbacksupporter2);
		}
		
		function callstate(data){
        dwr.util.removeAllOptions("state");
        dwr.util.addOptions("state",{'-1':'--请选择--'});
        dwr.util.addOptions("state",data,"id","name");
        }
        
        function callstate1(data){
        dwr.util.removeAllOptions("state1");
        dwr.util.addOptions("state1",{'-1':'--请选择--'});
        dwr.util.addOptions("state1",data,"id","name");
        }
         function callstate2(data){
        dwr.util.removeAllOptions("state2");
        dwr.util.addOptions("state2",{'-1':'--请选择--'});
        dwr.util.addOptions("state2",data,"id","name");
        }

		
		function tbshowandoff(a,b,c) {//显示和隐藏表格；
			document.getElementById(a).style.display= "block"; 
          	document.getElementById(b).style.display= "none"; 
          	document.getElementById(c).style.display= "none";
  		}
  		function dateJudge(){   
			var startDate=document.getElementById("startDate").value;
			var endDate=document.getElementById("endDate").value;   
			var startDate2=document.getElementById("startDate2").value;
			var endDate2=document.getElementById("endDate2").value; 
			if(startDate<=endDate&&startDate2<=endDate2&&endDate<startDate2){
				return true;
			}else{
				alert("请输入正确的日期！");
				return false;
			}
		} 
		function tongji(){
		   var startDate1=document.getElementById("startDate1").value;
		   var endDate1=document.getElementById("endDate1").value;
		   if(startDate1!=""&&endDate1!=""&&startDate1<=endDate1){
		     document.form2.submit();
		   }else{
		     alert("请输入正确的日期！");
		   }
		}


  		function callbacksupporter(data){  //显示出类别
  		 dwr.util.removeAllOptions("supporter");
  		   dwr.util.addOptions("supporter",{'-1':'--请选择--'});
  		   dwr.util.addOptions("supporter",data,"id","name");  
  		}

  		function callbackproducer(data){  //显示出类别
  		 dwr.util.removeAllOptions("producer");
  		   dwr.util.addOptions("producer",{'-1':'--请选择--'});
  		   dwr.util.addOptions("producer",data,"id","name");  
  		}
  		function callbacksupporter2(data){  //显示出类别
  		 dwr.util.removeAllOptions("supporter2");
  		   dwr.util.addOptions("supporter2",{'-1':'--请选择--'});
  		   dwr.util.addOptions("supporter2",data,"id","name");  
  		}

  		function callbackproducer2(data){  //显示出类别
  		 dwr.util.removeAllOptions("producer2");
  		   dwr.util.addOptions("producer2",{'-1':'--请选择--'});
  		   dwr.util.addOptions("producer2",data,"id","name");  
  		}
function notshow(){
document.getElementById('Layer1').style.visibility='hidden'
document.getElementById('Layer2').style.visibility='hidden'
document.getElementById('Layer3').style.visibility='hidden'
document.getElementById('Layer4').style.visibility='hidden'
document.getElementById('Layer5').style.visibility='hidden'
document.getElementById('Layer6').style.visibility='hidden'
}
		</script>
</head>
<body onLoad="init()" style="overflow:hidden;" onMouseDown="notshow();">

	<table cellspacing=0 cellpadding=0 border=0 width="100%">   
  		<tr>
    		<td width="1%" height="22" align="center" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../images/modpass.gif" width="16" height="16"></td>
    		<td width="98%" background="../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">资产信息统计</td>
  		</tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding=3 cellspacing=0>
    	<tr>
      		<td align=right style="padding-right: 1px" height="32">
      		  <span class="mmBtn" style="cursor:hand;"><a onClick="tbshowandoff('table1','table2','table3');">&nbsp;资产数量与日期统计&nbsp;</a></span>&nbsp;
      		  <span class="mmBtn" style="cursor:hand;"><a onClick="tbshowandoff('table2','table1','table3');">&nbsp;资产数量与价值百分率统计&nbsp;</a></span>&nbsp; 
      		  <span class="mmBtn" style="cursor:hand;"><a onClick="tbshowandoff('table3','table1','table2');">&nbsp;资产历史对比统计&nbsp;</a></span>
      		</td>
    	</tr>
  	</table>
  	
  	
  	
  	
  	
  	
  	
  	
  	<table id="table1" style="display :block"; width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
  		<td background="../images/main20100521_58.gif" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass1.jpg" width="16" height="16">&nbsp;资产数量与日期类统计&nbsp;
  		</td>
    </tr>
    <s:form id="form2" action="DataStatistic" namespace="/assets" method='post' theme="simple">
      <tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			    <tr>
                <td height="10" align="right" bgcolor="#deebf1">采购起始日期：</td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><s:textfield id="startDate1" name="startDate1" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100%"></s:textfield></td>
                <td width="10%" align="right" bgcolor="#deebf1">采购结束日期：</td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><s:textfield id="endDate1" name="endDate1" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:100%"></s:textfield></td>
                
                
              
                <td height="10" align="right" bgcolor="#deebf1">资产类别：</td>
                
                <td width="12%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assets.assetsType.id0" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="assets.assetsType.name0" onClick="document.getElementById('Layer5').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer5').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer5"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                </td>
                
                <td width="8%"  align="right" bgcolor="#deebf1">所属部门：</td>
                
                <td width="12%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.department.id" id="assets.department.id0" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="assets.department.name0" onClick="document.getElementById('Layer6').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer6').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer6"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

               </td>
              </tr>
              <tr>
                <td width="10%" align="right" bgcolor="#deebf1">资产状态：</td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><select id="state2" name="assets.assetsState.id" style="width:100%"></select></td>
                <td width="10%" align="right" bgcolor="#deebf1">供应商： </td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><select id="producer2" name="assets.assetsProducerByProduceId.id" style="width:100%"></select></td>
                <td width="8%" align="right" bgcolor="#deebf1">制造商：</td>
                <td width="12%" align="center" bgcolor="#FFFFFF"><select id="supporter2" name="assets.assetsProducerBySupportId.id" style="width:100%"></select></td>
                <td width="10%" align="center" colspan="2" bgcolor="#FFFFFF"><input type="button"  class="mmBtn" value=" 统计 " onClick="tongji();">&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="button" value=" 返回 " onClick="window.location='statistic.action'" class="mmBtn">
                 </td>
                </tr>
              </table>
            </td>
		</tr>
    </s:form>
   </table>
   
   
   
   
   
   
   
    <table id="table2" style="display :none"; width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
  		<td background="../images/main20100521_58.gif" colspan="8" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass1.jpg" width="16" height="16">&nbsp;资产数量与百分率类统计&nbsp;</td>
    </tr>

		<s:form action="/assets/cartogramqurey.action" method='post' theme="simple">
		<tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                <tr>
                <td height="10" align="right" bgcolor="#deebf1">资产类别：</td>
                
                <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assets.assetsType.id1" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="assets.assetsType.name1" onClick="document.getElementById('Layer1').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer1').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer1"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                </td>
                
                <td width="10%" align="right" bgcolor="#deebf1">资产状态：</td>
                
                <td width="10%" align="center" bgcolor="#FFFFFF">
                <select id="state" name="assets.assetsState.id" style="width:100%"></select></td>
									
                <td width="10%"  align="right" bgcolor="#deebf1">所属部门：</td>
                
                <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.department.id" id="assets.department.id1" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="assets.department.name1" onClick="document.getElementById('Layer2').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />" style="width:80%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer2').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer2"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

               </td>
              </tr>
              <tr>
                <td width="10%" align="right" bgcolor="#deebf1">供应商： </td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><select id="producer" name="assets.assetsProducerByProduceId.id" style="width:100%">
                </select></td>
                <td width="10%" align="right" bgcolor="#deebf1">制造商：</td>
                <td width="10%" align="center" bgcolor="#FFFFFF"><select id="supporter" name="assets.assetsProducerBySupportId.id" style="width:100%">
                </select></td>
                <td colspan="2" bgcolor="#FFFFFF"></td>
                </tr>
                <tr>
                 <td width="10%" align="center" colspan="6" bgcolor="#FFFFFF"><input type="submit"  class="mmBtn" value=" 统计 ">
                   <input type="button" value=" 返回 " onClick="window.location='statistic.action'" class="mmBtn">
                 </td>
                </tr>
              </table>
            </td>
		</tr>
       </s:form>
	</table>
    
    
    
    
    
    

  <table id="table3" style="display :none"; width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
  		<td background="../images/main20100521_58.gif" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass1.jpg" width="16" height="16">&nbsp;资产历史对比类统计&nbsp;</td>
    </tr>
    <s:form id="form1" action="historystatistic" namespace="/assets" method='post' theme="simple">
		<tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
                      <tr> 
                        <td width="15%" align="right" bgcolor="#deebf1">历史时间段：</td>
                        <td width="35%" bgcolor="#FFFFFF">
                        	<s:textfield id="startDate" name="startDate" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
							<strong>—</strong>
							<s:textfield id="endDate" name="endDate" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
                        <td width="15%" align="right" bgcolor="#deebf1" >当前时间段：</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF" >
                        	<s:textfield id="startDate2" name="startDate2" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
							<strong>—</strong>
							<s:textfield id="endDate2" name="endDate2" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:40%"></s:textfield>
                        </td>
                      </tr>
                      <tr> 
                        <td width="15%" align="right" bgcolor="#deebf1">所属部门：</td>
                        <td width="35%" bgcolor="#FFFFFF">
<input type="hidden" name="assets.department.id" id="assets.department.id2" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="assets.department.name2" onClick="document.getElementById('Layer4').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />" style="width:40%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer4').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer4"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                        </td>                       	
                        <td width="15%" align="right" bgcolor="#deebf1">资产类别：</td>
                        <td width="35%" nowrap bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assets.assetsType.id2" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="assets.assetsType.name2" onClick="document.getElementById('Layer3').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />" style="width:40%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer3').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer3"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:auto;"><iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                        </td>                                            
                      </tr>        
                      <tr>
                        <td height="15%" align="right" valign="top" nowrap bgcolor="#deebf1">资产状态：</td>
                        <td width="35%" bgcolor="#FFFFFF">
                          <select id="state1" name="assets.assetsState.id" style="width:40%"></select></td>
                        <td width="15%" align="center" bgcolor="#ffffff"></td>
                        <td width="35%" nowrap bgcolor="#ffffff">
                        	
                        </td>   
                      </tr>
                      		<tr bgcolor="#ffffff" align="center" style="height: 25">
			<td height="30" colspan="4" align="center" nowrap="nowrap">
				<input name="submit" type="submit"  value=" 统计 " class="mmBtn" onClick="javascript:return dateJudge()">&nbsp;&nbsp;
				<input name="reset" type="reset"  value=" 重置 " class="mmBtn">&nbsp;&nbsp;
				<input name="button" type="button" onClick="window.history.go(-1)" value=" 返回  " class="mmBtn"></td>
		</tr>	
              </table>
            </td>
		</tr>

	</s:form>
  </table>  
</body>
</html>