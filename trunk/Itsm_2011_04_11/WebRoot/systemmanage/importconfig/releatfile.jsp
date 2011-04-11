<!--TestRecords:500//-->
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script language=JavaScript>
function Chose(Num){
	var OldValue=window.parent.document.getElementById('ITprinc').value;
	document.getElementById(Num).bgColor='#E5E9EE';
	if (OldValue!='') document.getElementById(OldValue).bgColor='';
	window.parent.document.getElementById('ITprinc').value=Num;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../cn_css/simple.css">
</head>
<body leftmargin="2" topmargin="2" marginwidth="0" marginheight="0">
<table width="100%" border=0 cellpadding=2 cellspacing=1 bgcolor="#b5d6e6" class="datagrid">
              <tr> 
                <td width=1% height="22" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">代码</td>
                <td width="20%" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">配置名称</td>
                <td width="10%" align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">类别</td>
                <td width=1% align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">选择</td>
                <td width=1% align="center" nowrap background="../../images/main20100521_58.gif" class="alllisttitle">查看</td>
              </tr>
              <tr bgcolor="#F9F9F9"> 
				<s:form name="myForm" method="post" action="../asst/default.asp?NowAction=assetslistsmall&input=GetFrom&DM=&LB=|0,&PR=&CT=">
                <td height="24" colspan="6" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="2">
                      <tr>
                        <td width="11%"> 
                          <select name="Name" id="Name">
                          <option value="ZiCDM" >配置代码</option>
                          <option value="ShiYR" >管理员帐号</option>
                          <option value="SouSBM" >配置所在部门</option>
                          <option value="SheBMC" >配置名称</option>
                          <option value="TiaoXM" >自定义编号</option>
                        </select>
                        </td>
                        <td width="99%"> 
                          <input name="KeyWord" type="text" id="KeyWord" value="" style="width: 100%; height: 20px">
						</td>
                        <td width="1%"> 
                          <input type="button" value="Go.." onClick="myForm.submit()" class=mmBtn name="button" style="height: 20px; font-size: 9px">
                        </td>
                    </tr>
                  </table></td>
			    </s:form>
              </tr>
              
</table>
<script language="JavaScript">
function SeekOnClick(Var1, Var2){	
	document.location.href = "../asst/?NowAction=assetslistsmall&TiaoXM=" + document.getElementById("TiaoXM").value + "&ZiCDM=" + document.getElementById("ZiCDM").value + "&SheBMC=" + document.getElementById("SheBMC").value + "&Sort=" + Var1 + "&Desc=" + Var2 + "&DM=";
}
</script>
</body>
</html>