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
<body onmousedown="document.getElementById('TreeP').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">查看流程:</td>
  </tr>
</table>
<table width="100%" height="80%" border="0" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="80%" border="0" cellpadding="4" cellspacing="1" bgcolor="#b5d6e6">

      <tr>
        <td height="100%" valign="top" bgcolor="#FFFFFF"><div id="Noti" name="Noti" style="position: absolute; width: 100%; z-index: 1; visibility: xhidden;">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
            <tr>
              <td><img width="3"></td>
              <td width="1%" background="../img/cldbg.jpg" id="Help"><table width="100%" border="0" cellspacing="0" cellpadding="3" height="100%">
                  <tr onClick="Change()" style="cursor: hand">
                    <td height="12" style="padding-left: 1px; padding-bottom: 0px; font-weight: bold" nowrap><img src="../../img/afc.gif" align="absmiddle">&nbsp;帮助信息:</td>
                    <td height="12" width="1%" onClick="document.getElementById('Noti').style.visibility='hidden'" onMouseDown="document.getElementById('Down').style.border='1px inset'" title="Close.."><img src="../../img/closes.jpg" align="absmiddle" height="11" style="border: 1px outset" id="Down" width="16"></td>
                  </tr>
                  <tr>
                    <td height="99%" colspan="2"><iframe frameborder="0" height="100%" width="100%" scrolling="yes" src="../manage/?NowAction=preview&charsets=2&step=2&Parent=$$&Chan=&Desc=1" style="border: 1px inset"></iframe></td>
                  </tr>
              </table></td>
            </tr>
          </table>
        </div>
          <img src="../../img/progressmap2.jpg" width="684" height="275"></td>
      </tr>
    </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td style="padding-top: 2px; border-left: 1px solid #B3C3DB" background="../../img/cldbg.jpg"><table border="0" cellspacing="3" cellpadding="0" style="border-bottom: 1px solid #B3C3DB">
              <tr>
                <td style="padding-left: 5px"><img width="18" height="18" src="../../img/task.gif"></td>
                <td nowrap style="padding-right: 10px">员工离职IT信息管理注销审批流程&nbsp;</td>
              </tr>
          </table></td>
          <td width="99%" align="right"><table border="0" cellspacing="0" cellpadding="0" width="100%" background="../img/bt_bg.jpg">
              <tr>
                <td width="99%"><img src="../../img/bottom.jpg"></td>
                <td onClick="window.open(window.Draw.location+'&DO=1','','width=20,height=20,scrollbars=no,menubar=no,resizable=no,top=2000,left=2000,status=yes')" style="cursor: hand; padding-top: 6px" nowrap><img src="../../img/print.jpg" align="absmiddle">&nbsp;打印</td>
                <td><img width="10" height="1"></td>
                <td onClick="window.open(window.Draw.location+'&DO=2','','width=20,height=20,scrollbars=no,menubar=no,resizable=no,top=2000,left=2000,status=yes')" style="cursor: hand; padding-top: 7px" nowrap><img src="../../img/save.jpg" align="absmiddle">&nbsp;保存</td>
              </tr>
          </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
