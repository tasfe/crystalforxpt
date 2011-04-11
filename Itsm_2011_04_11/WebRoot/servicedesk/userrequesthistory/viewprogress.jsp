<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>IT Service Desk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<Script Language="JavaScript">
var i=0;
function NewTask(ThisItem)
{ 
	if (window.Draw.document.getElementById("NewTask").style.visibility=="visible")
	{
		window.Draw.document.getElementById("NewTask").style.visibility="hidden";
		ThisItem.style.border="1px solid #E6ECFA";
	}
	else
	{
		window.Draw.document.getElementById("NewTask").style.visibility="visible";
		ThisItem.style.border="1px inset";
	}
}
function WhenMouse(ThisItem,Status)
{ 
	if (window.Draw.document.getElementById("NewTask").style.visibility=="hidden")
	{
		if (Status=="over")
		{
			ThisItem.style.border="1px outset";
			ThisItem.style.borderTop="1px solid white";
			ThisItem.style.borderLeft="1px solid white";
		}
		if (Status=="out") ThisItem.style.border="1px solid #E6ECFA";
	}
}
function DelChose()
{ 
	var ChoseID=window.Draw.document.getElementById("ChoseID").value;
	var LineID=window.Draw.document.getElementById("LineID").value;
	var FlowID=window.Draw.document.getElementById("FlowID").value;
	if (ChoseID==""&&LineID=="") alert("请先选择需要删除的对象！");
	if (ChoseID!="")
	{
		if (confirm('确认要删除选定的任务吗？'))
		{
			window.Draw.document.getElementById('SaveData').style.visibility='visible';
			window.Draw.document.getElementById("ChoseID").value='';
			window.Draw.Save.location="../manage/?NowAction=db_wf&Type=DelTask&charsets=2&TaskID="+ChoseID+"&FlowID="+FlowID;
		}
	}
	if (LineID!="")
	{
		if (confirm('确认要删除选定的连接关系吗？'))
		{
			window.Draw.document.getElementById('SaveData').style.visibility='visible';
			window.Draw.document.getElementById("LineID").value='';
			window.Draw.Save.location="../manage/?NowAction=db_wf&Type=DelLine&charsets=2&LineID="+LineID+"&FlowID="+FlowID;
		}
	}
}
function ChangeStyle(Types,Values)
{ 
	var ChoseID=window.Draw.document.getElementById("ChoseID").value;
	var LineID=window.Draw.document.getElementById("LineID").value;
	if (ChoseID!="")
	{
		if (Types=="Font") window.Draw.document.getElementById(ChoseID+"_Text").style.fontFamily=Values;
		if (Types=="Color") window.Draw.document.getElementById(ChoseID+"_Text").style.color=Values;
		if (Types=="Icon") window.Draw.document.getElementById(ChoseID+"_Ico").src=Values;
	}
	if (Types=="Weight")
	{
		var allLine=window.Draw.document.body.all.item("LinkLine");
		if (allLine!=null&&LineID!="")
		{
			var count=allLine.length;
			if (count)
			{
				for (i=count-1; i>=0; i--)
				{
					if (allLine[i].OwnID==LineID)
					{
						allLine[i].Strokeweight=Values;
					}
				}
			}
		}
	}
	if (Types=="Fcolor")
	{
		var allLine=window.Draw.document.body.all.item("LinkLine");
		if (allLine!=null&&LineID!="")
		{
			var count=allLine.length;
			if (count)
			{
				for (i=count-1; i>=0; i--)
				{
					if (allLine[i].OwnID==LineID)
					{
						allLine[i].StrokeColor=Values;
					}
				}
			}
		}
	}
}
function Zoom_View(ID)
{
	if (ID=="B")
	{
		i++;
		window.Draw.document.getElementById("BackGrd").style.zoom=1+i/20;
		window.Draw.document.getElementById("WorkFlow").style.zoom=1+i/20;
		window.Draw.document.getElementById("ZoomData").style.visibility="visible";
	}
	if (ID=="D")
	{
		i--;
		window.Draw.document.getElementById("BackGrd").style.zoom=1+i/20;
		window.Draw.document.getElementById("WorkFlow").style.zoom=1+i/20;
		window.Draw.document.getElementById("ZoomData").style.visibility="visible";
	}
	if (ID=="R")
	{
		window.Draw.document.getElementById("BackGrd").style.zoom=1;
		window.Draw.document.getElementById("WorkFlow").style.zoom=1;
		window.Draw.document.getElementById("ZoomData").style.visibility="hidden";
		i=1;
	}
}
function Change()
{
	var OldWidth = document.getElementById('Help').style.width;
	if (OldWidth == "50%")
	{
		document.getElementById('Help').style.width='0%';
		document.getElementById('Help').style.height='0px';
	}
	else
	{
		document.getElementById('Help').style.width='50%';
		document.getElementById('Help').style.height='200px';
	}
}
</Script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow:hidden;">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看流程:</td>
  </tr>
</table>
<div style="position: absolute; overflow-x: hidden; overflow-y: scroll; height:95%; width: 100%; padding-top: 5px;"><table width="99%" height="80%" border="0" align="center" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table  border="0" cellspacing="4" cellpadding="0">
      <tr>
        <td style="padding: 5px"><img src="../../img/arrowright.jpg" width="64" height="64"></td>
        <td><table border="0" cellspacing="1" cellpadding="0" style="border: 1px outset">
            <tr class="td-right">
              <td nowrap width="99%" style="padding-left: 2px" height="21"><b>提交..</b></td>
            </tr>
            <tr style="padding-left: 4px; padding-right: 5px">
              <td nowrap bgcolor="#F9F9F9">王琳</td>
            </tr>
            <tr style="padding-left: 4px; padding-right: 5px">
              <td nowrap bgcolor="#F9F9F9">2008-6-12 13:12:31</td>
            </tr>
        </table></td>
        <td style="padding: 5px"><img src="../../img/arrowright.jpg" width="64" height="64"></td>
        <td><table border="0" cellspacing="1" cellpadding="0" style="border: 1px outset; cursor: hand" onClick="window.location='DealDetail.jsp'">
          <tr class="td-right">
            <td nowrap width="99%" style="padding-left: 3px; padding-right: 10px" height="21" valign="top">本区域调动</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">冯驰</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">2008-6-11 18:39:10</td>
          </tr>
        </table></td>
        <td style="padding: 5px"><img src="../../img/arrowright.jpg" width="64" height="64"></td>
        <td><table border="0" cellspacing="1" cellpadding="0" style="border: 1px outset; cursor: hand" onClick="window.location='DealDetail.jsp'">
          <tr class="td-right">
            <td nowrap width="99%" style="padding-left: 3px; padding-right: 10px" height="21" valign="top">完成更新</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">杨岑</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">2008-6-11 18:41:27</td>
          </tr>
        </table></td>
        <td style="padding: 5px"><img src="../../img/arrowright.jpg" width="64" height="64"></td>
        <td style="padding: 5px"><table border="0" cellspacing="1" cellpadding="0" style="border: 1px outset; cursor: hand" onClick="window.location='DealDetail.jsp">
          <tr class="td-right">
            <td nowrap width="99%" style="padding-left: 3px; padding-right: 10px" height="21" valign="top">&nbsp;</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">王琳</td>
          </tr>
          <tr style="padding-left: 3px; padding-right: 5px">
            <td nowrap bgcolor="#F9F9F9">YYYY-MM-DD HH:MM:SS</td>
          </tr>
        </table></td>
        <td style="padding: 5px"><img src="../../img/arrowright.jpg" width="64" height="64"></td>
      </table></td>
  </tr>
</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
    <td align=center nowrap style="padding-top: 8px; padding-bottom: 0px"><input name="button" type="button" class="mmBtn" onClick="window.location='viewdetail.jsp'" value="详细信息"></td>
  </tr>
</table>
</div>
</body>
</html>
