<html>
<head>
<title>User</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<Script Language="JavaScript">
function ChoseAll()
{
	var allLine=document.body.all.item("power");
	if (allLine!=null)
	{
		var count=allLine.length;
		if (count)
		{
			for (i=count-1; i>=0; i--)
			{
				if (document.getElementById("Table").checked==true)
				{
					allLine[i].checked=true;
				}
				else
				{
					allLine[i].checked=false;
				}
			}
		}
	}
}
</Script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="../../cn_css/custo.css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="background-repeat: repeat-x;overflow: hidden">
<a id="Layer2"></a>
<iframe width=0 height=0 name="sla" id="sla"></iframe>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="2%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">选择人员:</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr> 
    <td valign="top" id="mainright" height="100%"> 
      <table width="100%" border="0" cellpadding="10" cellspacing="0" class="tb-list" height="100%">
          <tr> 
            <td height="99%" valign="top" bgcolor="#FFFFFF" style="padding: 0px"><table width="100%" height="100%%" border="0" cellpadding="12" cellspacing="0">
              <tr> 
                <td valign="top" style="padding: 10px" bgcolor="#FFFFFF"> 
                  <table cellspacing=0 cellpadding=2 border=0 width="100%" height="100%">
                    <tr> 
                      <td height="320" colspan="2" style="border: 1px solid #E5E9EE"><iframe width=100% height=100% src="userandcompany.jsp" frameborder="0" scrolling="no"></iframe></td>
                    </tr>
                    <tr> 
                      <td class="subtitle">
					  <input name="ShiYR" type="text" id="ShiYR" style="width: 100%">
					  </td>
                      <td class="subtitle" width="1%" style="padding-left: 0px">
					  <input type="button" value="选定" class=mmBtn_sm name="button" style="font-weight: normal" onClick="window.opener.document.getElementById('Text_2').value='=Code_US:'+document.getElementById('ShiYR').value;window.opener.document.getElementById('Text_2_LB').value=document.getElementById('ShiYR').value;window.close()">
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
</body>
</html>