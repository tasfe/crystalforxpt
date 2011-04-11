<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数量与日期统计</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
	<script type='text/javascript' src='../dwr/util.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/interface/AssetsStateDAO.js'></script>
		<script type='text/javascript' src='../dwr/interface/AssetsProducerDAO.js'></script>
    <script>
    function page(page){
    document.form2.action="DataStatistic.action?page="+page;
    document.form2.submit();
    }
    function tongji(){
		   var startDate1=document.getElementById("startDate1").value;
		   var endDate1=document.getElementById("endDate1").value;
		   if(startDate1!=""&&endDate1!=""&&startDate1<=endDate1){
		     document.form2.action="DataStatistic.action";
		     document.form2.submit();
		   }else{
		     alert("请输入正确的日期！");
		   }
		}
function notshow(){
document.getElementById('Layer5').style.visibility='hidden'
document.getElementById('Layer6').style.visibility='hidden'
}



function init(){ //取出类别
		    AssetsStateDAO.findAll(callstate2);
			AssetsProducerDAO.findByIntType(1,callbackproducer2);
			AssetsProducerDAO.findByIntType(2,callbacksupporter2);

		}
		
         function callstate2(data){
        dwr.util.removeAllOptions("state2");
        dwr.util.addOptions("state2",{'-1':'--请选择--'});
        dwr.util.addOptions("state2",data,"id","name");
        var a = "<s:property value='assets.assetsState.id'/>";
     if (typeof(a) != "undefined") {   
       dwr.util.setValue("state2",a);
     }
        }

  		function callbacksupporter2(data){  //显示出类别
  		 dwr.util.removeAllOptions("supporter2");
  		   dwr.util.addOptions("supporter2",{'-1':'--请选择--'});
  		   dwr.util.addOptions("supporter2",data,"id","name");  
  		   var a = "<s:property value='assets.assetsProducerBySupportId.id'/>";
     if (typeof(a) != "undefined") {   
       dwr.util.setValue("supporter2",a);
     }
  		}

  		function callbackproducer2(data){  //显示出类别
  		 dwr.util.removeAllOptions("producer2");
  		   dwr.util.addOptions("producer2",{'-1':'--请选择--'});
  		   dwr.util.addOptions("producer2",data,"id","name");  
  		   var a = "<s:property value='assets.assetsProducerByProduceId.id'/>";
     if (typeof(a) != "undefined") {   
       dwr.util.setValue("producer2",a);
     }
  		}
  		
  		
  		function excel(){
   document.form2.action="excelDown.action";
   document.form2.method="post";
   document.form2.submit();
}
    </script>
</head>
<body style="overflow:hidden;" onMouseDown="notshow();" onload="init();">
<table id="table1" style="display :block"; width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
  		<td background="../images/main20100521_58.gif" style="font-weight:bold; padding-right:5px;">
  			<img src="../images/modpass1.jpg" width="16" height="16">&nbsp;资产数量与日期类统计&nbsp;
  		</td>
    </tr>
    <s:form id="form2" action="" namespace="/assets" method='post' theme="simple">
      <tr> 
			 <td bgcolor="white" valign="top">
			   <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#b5d6e6">
			    <tr>
                <td width="9%" align="right" bgcolor="#deebf1">采购起始日期：</td>
                <td width="11%" align="center" bgcolor="#FFFFFF"><s:textfield id="startDate1" name="startDate1" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:86%"></s:textfield></td>
                <td width="9%" align="right" bgcolor="#deebf1">采购结束日期：</td>
                <td width="11%" align="center" bgcolor="#FFFFFF"><s:textfield id="endDate1" name="endDate1" cssClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:86%"></s:textfield></td>
                
                
              
                <td height="10" align="right" bgcolor="#deebf1">资产类别：</td>
                
                <td width="10%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.assetsType.id" id="assetstype0" value="<s:property value="assets.assetsType.id" />">
&nbsp;<input type="text" name="assets.assetsType.name" id="name0" onClick="document.getElementById('Layer5').style.visibility='visible'" readonly value="<s:property value="assets.assetsType.name" />
" style="width:70%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer5').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer5"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:hidden;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/assetstype.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>
                </td>
                
                <td width="9%"  align="right" bgcolor="#deebf1">所属部门：</td>
                
                <td width="12%" align="center" bgcolor="#FFFFFF">
<input type="hidden" name="assets.department.id" id="department0" value="<s:property value="assets.department.id" />">
&nbsp;<input type="text" name="assets.department.name" id="departmentname0" onClick="document.getElementById('Layer6').style.visibility='visible'" readonly value="<s:property value="assets.department.name" />
" style="width:70%;">
<img src="../images/main20100521lsearch.gif" align="absmiddle" style="cursor: hand" onClick="document.getElementById('Layer6').style.visibility='visible'"><br/>
<div style="position:relative; width:60px; height:1px; line-height:1px; left:-98px; top:0px;"><div id="Layer6"  style="position:absolute; width: 180px; height:220px; z-index:1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white; top:0px;left:0px; overflow:hidden;">
<iframe frameborder="0" width="100%" height="100%" scrolling="auto" src="../utiltree/department.action" style="border: 1px solid #E5E9EE;"></iframe>
</div></div>

               </td>
              </tr>
              <tr>
                <td width="9%" align="right" bgcolor="#deebf1">资产状态：</td>
                <td width="11%" align="center" bgcolor="#FFFFFF"><select id="state2" name="assets.assetsState.id" style="width:90%"></select></td>
                <td width="9%" align="right" bgcolor="#deebf1">供应商： </td>
                <td width="11%" align="center" bgcolor="#FFFFFF"><select id="producer2" name="assets.assetsProducerByProduceId.id" style="width:90%"></select></td>
                <td width="8%" align="right" bgcolor="#deebf1">制造商：</td>
                <td width="12%" align="center" bgcolor="#FFFFFF"><select id="supporter2" name="assets.assetsProducerBySupportId.id" style="width:90%"></select></td>
                <td align="center" colspan="2" bgcolor="#FFFFFF"><input type="button"  class="mmBtn" value=" 统计 " onclick="tongji();">&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="button" value=" 返回 " onClick="window.location='statistic.action'" class="mmBtn">
                 </td>
                </tr>
              </table>
            </td>
		</tr>
    </s:form>
   </table>
   
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
  <td height="35"> <input type="button"  class="mmBtn" value=" Excel导出 " onclick="excel();"/></td></tr>
<tr><td>
         <table style="font-size:12px;" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#b5d6e6">
          <tr>
            <td height="22" align="center" nowrap style="text-align: center" background="../images/main20100521_58.gif">序号</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产编号</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产名称</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产类别</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">所属部门</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">资产状态</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">供应商</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">制造商</td>
            <td align="center" nowrap background="../images/main20100521_58.gif">采购日期</td>
          </tr>
          <s:iterator value="pageBean.list" var="assetsBase"  status='st'>
           <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'" onMouseOut="this.bgColor='#FFFFFF'">          
            <td height="19" align="center"><s:property value="#st.index+1"/></td>
            <td align="center"><s:property value="codeId"/></td>
            <td align="center"><s:property value="name"/></td>
            <td align="center"><s:property value="assetsType.name"/></td>
            <td align="center"><s:property value="department.name"/></td>
            <td align="center"><s:property value="assetsState.name"/></td>
            <td align="center"><s:property value="assetsProducerByProduceId.name"/></td>
            <td align="center"><s:property value="assetsProducerBySupportId.name"/></td>
            <td align="center"><s:date name="buyDate" format="yyyy-MM-dd"/></td>
          </tr>
          </s:iterator>
        </table>
        <table style="font-size:12px;" border="0" align="center" cellpadding="0" class="list" cellspacing="0">
          <tr>
            <td height="30" align="right" class="zczb_qua">
            <s:if test="%{pageBean.allRow!=0}">
				共<s:property value="pageBean.allRow"/>条记录
				共<s:property value="pageBean.totalPage"/> 页
				当前第<s:property value="pageBean.currentPage"/>页 
        		<s:if test="%{pageBean.currentPage == 1}">&nbsp;第一页 上一页 </s:if>
        		<s:else>
            		<a href="#" onClick="page(1);">第一页</a>
            		<a href="#" onClick="page(<s:property value="%{pageBean.currentPage-1}"/>);" >上一页</a>
        		</s:else>
        		<s:if test="%{pageBean.currentPage != pageBean.totalPage}">
            		<a href="#" onClick="page(<s:property value="%{pageBean.currentPage+1}"/>);">下一页</a>
            		<a href="#" onClick="page(<s:property value="pageBean.totalPage"/>);" >最后一页</a>
        		</s:if>
        		<s:else> 下一页 最后一页</s:else>
        	</s:if>	
            </td>
          </tr>
         </table>
</td></tr>
</table>

</body>
</html>
