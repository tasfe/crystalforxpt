<!--TestRecords:500//-->
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<script language=JavaScript>
function CLear(){
	
}
function Grade(TheValue){
	if (TheValue.indexOf("$$")>0)
	{
		window.parent.document.getElementById('Grade').value=TheValue;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="../cn_css/custo.css">
</head>
<body bgcolor="#F3F4F8" leftmargin="0" topmargin="4" oncontextmenu="return false" onselectstart="return false">

	<select style="width: 120px" onChange="window.location='../prob/?NowAction=team&Clears=&Mult=&RequPower=clwt&Color=F3F4F8&Grade=&ParentUse='+this.value">
	<option value="">请选择</option>
	
		<option value="" selected>全局</option>
		
		<option value="|3,">重庆区域</option>
		
		<option value="|4,">北京区域</option>
		
		<option value="|5,">上海区域</option>
		
		<option value="|6,">成都区域</option>
		
		<option value="|7,">西安区域</option>
		
	</select>
	<select style="width: 180px" onChange="window.parent.ITer2.location='../sla/?NowAction=iterlist&FormName=Principal&Mult=&RequPower=clwt&Value='+this.value;Clear();Grade(this.value)">
	<option value="">请选择</option>
	
		<option value=",|zhanwei,|liukai,yangcen,">二线支持_信息系统管理</option>
		
		<option value=",|huojiang,">二线支持_数据挖掘管理</option>
		
		<option value=",|zhangxx,">测试使用</option>
		
	</select>

</body>
</html>