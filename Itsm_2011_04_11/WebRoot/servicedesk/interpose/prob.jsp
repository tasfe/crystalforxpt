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
	<option value="">��ѡ��</option>
	
		<option value="" selected>ȫ��</option>
		
		<option value="|3,">��������</option>
		
		<option value="|4,">��������</option>
		
		<option value="|5,">�Ϻ�����</option>
		
		<option value="|6,">�ɶ�����</option>
		
		<option value="|7,">��������</option>
		
	</select>
	<select style="width: 180px" onChange="window.parent.ITer2.location='../sla/?NowAction=iterlist&FormName=Principal&Mult=&RequPower=clwt&Value='+this.value;Clear();Grade(this.value)">
	<option value="">��ѡ��</option>
	
		<option value=",|zhanwei,|liukai,yangcen,">����֧��_��Ϣϵͳ����</option>
		
		<option value=",|huojiang,">����֧��_�����ھ����</option>
		
		<option value=",|zhangxx,">����ʹ��</option>
		
	</select>

</body>
</html>