<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="../theme/admin.css">

<Script Language="JavaScript">
function CheckBox(obj,id)
{
	var value=obj.value;
	var parents=window.parent.document.getElementById('field3');
	if (parents.value.length<1700)
	{
		var allLine=document.body.all.item("dept");
		if (allLine!=null)
		{
			var count=allLine.length;
			if (count)
			{
				for (i=0; i<count; i++)
				{
					if (allLine[i].title.indexOf(value)>0)
					{
						if (id=="OK")
						{
							if (parents.value.length<1700)
							{
								allLine[i].checked=true;
								parents.value=parents.value.replace('<'+allLine[i].value+'>','')+'<'+allLine[i].value+'>';
							}
							else
							{
								alert('Sorry, Overflow!!');
								allLine[i].focus();
								break;
							}
						}
						else
						{
							allLine[i].checked=false;
							parents.value=parents.value.replace('<'+allLine[i].value+'>','');
						}
					}
				}
			}
		}
	}
	else
	{
		obj.checked=false;
		parents.value=parents.value.replace('<'+value+'>','');
		if (id=="OK")
		{
			alert("Sorry, overflow!");
		}
	}
}
function ClearAll()
{
	var allLine=document.body.all.item("dept");
	if (allLine!=null)
	{
		var count=allLine.length;
		if (count)
		{
			for (i=count-1; i>=0; i--)
			{
				allLine[i].checked=false;
			}
		}
	}
}
function OnUp()
{
	
}
</Script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="padding-bottom: 2px; padding-top: 2px; overflow-x: hidden; overflow-y: scroll" onselectstart="return false">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td style="line-height: 19px; font-family: Tahoma; padding-left: 2px; padding-bottom: 4px" bgColor="white" nowrap>
	
			<div><img src="../img/jian.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('11').style.display=='none'){document.getElementById('11').style.display='';this.src='../img/jian.jpg'}else{document.getElementById('11').style.display='none';this.src='../img/jia.jpg'}"><input type="checkbox" value="|1," onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,>','')+'<|1,>';CheckBox(this,'OK')}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,>','');CheckBox(this,'NO')};OnUp()" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept" title="Tree_|1,">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="1o" name="|1,o">重庆XX地产发展有限公司</a></font></font><div id="11" style="padding-left: 20px">
			<img src="../img/jian.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('1010').style.display=='none'){this.src='../img/jian.jpg';document.getElementById('1010').style.display=''}else{document.getElementById('1010').style.display='none';this.src='../img/jia.jpg'}"><input type="checkbox" value="|1,|10," onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,>','')+'<|1,|10,>';CheckBox(this,'OK')}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,>','');CheckBox(this,'NO')};OnUp()" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept" title="Tree_|1,|10,">&nbsp;<font style="padding: 1px"><font style="padding: 1px; cursor: hand; background-color: white"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="10o" name="|1,|10,o">公共事务与行政部</a></font></font><br><div id="1010" style="padding-left: 20px">
			<img src="../img/jian.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('2121').style.display=='none'){this.src='../img/jian.jpg';document.getElementById('2121').style.display=''}else{document.getElementById('2121').style.display='none';this.src='../img/jia.jpg'}"><input type="checkbox" value="|1,|10,|21," onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,|21,>','')+'<|1,|10,|21,>';CheckBox(this,'OK')}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,|21,>','');CheckBox(this,'NO')};OnUp()" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept" title="Tree_|1,|10,|21,">&nbsp;<font style="padding: 1px"><font style="padding: 1px; cursor: hand; background-color: white"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="21o" name="|1,|10,|21,o">驾驶�?/a></font></font><br><div id="2121" style="padding-left: 20px"></div>
			
			<img src="../img/jian.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white" onClick="if(document.getElementById('2222').style.display=='none'){this.src='../img/jian.jpg';document.getElementById('2222').style.display=''}else{document.getElementById('2222').style.display='none';this.src='../img/jia.jpg'}"><input type="checkbox" value="|1,|10,|22," onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,|22,>','')+'<|1,|10,|22,>';CheckBox(this,'OK')}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1,|10,|22,>','');CheckBox(this,'NO')};OnUp()" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept" title="Tree_|1,|10,|22,">&nbsp;<font style="padding: 1px"><font style="padding: 1px; cursor: hand; background-color: white"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="22o" name="|1,|10,|22,o">行政�?/a></font></font><br><div id="2222" style="padding-left: 20px"></div>
			</div>

		
	<div><img src="../img/point.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white"><input type="checkbox" value="All" onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<All>','')+'<All>'}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<All>','')}" style="border: 0px; border-top: 5px solid white; border-left: 4px solid white; width: 19px"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="All" name="All">所有客�?/a></font></font></div>
	
	<div><img src="../img/point.jpg" width="11" height="11" align="absmiddle" style="border: 4px solid white; border-top: 7px solid white"><input type="checkbox" value="Mail" onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<Mail>','')+'<Mail>'}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<Mail>','')}" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="Mail" name="Mail">其它来源请求</a></font></font></div>
	
		</td>
	</tr>
	<tr>
		
		<td style="border: 1px outset; padding-left: 5px" bgcolor="#E1DFD9">
		
		<table width="20" border="0" cellspacing="0" cellpadding="0">
		  <tr>
			<td style="padding-right: 6px; padding-bottom: 1px"><img src="../img/point.jpg" width="11" height="11" align="absmiddle"></td>
			<td style="padding-bottom: 1px"><input type="checkbox" value="|A|" onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|A|>','')+'<|A|>'}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|A|>','')}" style="width: 19px; border: 0px"  id="dept" name="dept"></td>
			<td nowrap><b>&nbsp;优先�? 全部</b></td>
		  </tr>
		</table>
		</td>
	</tr>
	<tr>
		<td style="line-height: 19px; font-family: Tahoma; padding-left: 7px; padding-bottom: 2px" bgColor="white" nowrap>
		<table width="70%" border="0" cellspacing="2" cellpadding="0">
		<tr>
				<td nowrap style="padding-left: 12px"><input type="checkbox" value="||" onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1|>','')+'<|1|>'}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|1|>','')}" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="PRI1" name="PRI1">= 1</a></font></font></td>
				
				<td nowrap style="padding-left: 12px"><input type="checkbox" value="||" onClick="if(this.checked){window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|3|>','')+'<|3|>'}else{window.parent.document.getElementById('field3').value=window.parent.document.getElementById('field3').value.replace('<|3|>','')}" style="border: 0px; border-top: 5px solid white; width: 19px; border-left: 4px solid white"  id="dept" name="dept">&nbsp;<font style="padding: 1px"><font style="padding: 1px; background-color: white; cursor: hand"><a onMouseOver="this.style.borderBottom='1px solid'" onMouseOut="this.style.borderBottom='0px solid'" id="PRI3" name="PRI3">= 3</a></font></font></td>
				
		</table>
		
	</td>
  </tr>
</table>