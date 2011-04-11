<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="javascript" event="onerror(msg, url, line)" for="window">return true;</script>

<html>
<head>
<title>工程师服务请求历史</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript">
function Notice()
{

}
</script>
</head>
<body onmousedown="document.getElementById('TreeP').style.visibility='hidden'">
<table cellspacing=0 cellpadding=0 border=0 width="100%">
  <tr>
   <td width="1%" height="22" align="center" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;padding-left:5px; padding-right:5px;"><img src="../../images/modpass.gif" width="16" height="16"></td>
   <td width="98%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">工程师服务请求历史:</td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" height="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF">
      <tr>
        <td width="15%" height="12" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-right: 20px">标题:</td>
        <td width="85%" height="12" bgcolor="#EBF4F5" class="td-right-s"><input name="Report_Title" type="text" id="Title" style="width: 100%" value="所有工程师工作详细状态" maxlength="50"></td>
        <td width="1%" bgcolor="#EBF4F5" class="td-left-s" title="Read Only"><input name="checkbox" type="checkbox" style="border: 0px" onClick="if(this.checked){document.getElementById('Report_Value').readOnly=true;document.getElementById('Report_Value').style.color='gray'}else{document.getElementById('Report_Value').readOnly=false;document.getElementById('Report_Value').style.color='black'}" value="1" checked></td>
      </tr>
      <tr>
        <td width="15%" height="12" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-right: 20px">报表应用范围:</td>
        <td width="85%" height="12" colspan="2" bgcolor="#EBF4F5" class="td-right-s"><select size=1 style="width: 60%" name="RequDept" id="RequDept">
            <option value="">全部工程师</option>
            <option value="|6,">成都区域</option>
          </select>
        </td>
      </tr>
      <tr>
        <td nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-top: 5px; padding-right: 20px">报表参数:</td>
        <td colspan="2" bgcolor="#EBF4F5" class="td-right-s"><textarea name="Report_Value" readonly style="line-height: 22px; width: 100%; height: 100%; padding-right: 5px; padding-left: 5px; font-family: 'Courier New'; font-size: 11px; letter-spacing: 2px; color: gray">中新网5月28日电 综合报道 当地时间27日，奥巴马上台16个月后，首次发布了美国“国家安全战略报告”。这份报告有52页，其中一个重要变化是改变布什时期的单边主义做法，强调与外界合作对话。媒体分析称，尽管阿富汗和伊拉克战争还在继续，但该报告使奥巴马强调多边外交重于军事力量的想法正式化。</textarea>
        </td>
      </tr>
      <tr>
        <td width="15%" height="12" rowspan="2" nowrap bgcolor="#b5d6e6" class="td-left-s" style="padding-right: 20px">链接为子表:</td>
        <td width="85%" height="12" colspan="2" bgcolor="#EBF4F5" class="td-right-s"><select name="LinkTo" style="height: 20px; width: 100%">
            <option value="0">请选择父报表</option>
          
				   select * From Report_Mana where ID <> '2' and Report_Value like '%Tjdx=Inci%' and LinkTo = 0
				  
        </select>
        </td>
      </tr>
      <tr>
        <td width="85%" height="12" colspan="2" bgcolor="#EBF4F5" class="td-right-s"><select name="LinkItem" style="height: 20px; width: 50%">
            <option value="0">第几项</option>
            <option value="1" >1</option>
            <option value="2" >2</option>
            <option value="3" >3</option>
            <option value="4" >4</option>
            <option value="5" >5</option>
            <option value="6" >6</option>
            <option value="7" >7</option>
            <option value="8" >8</option>
            <option value="9" >9</option>
            <option value="10" >10</option>
            <option value="11" >11</option>
            <option value="12" >12</option>
            <option value="13" >13</option>
            <option value="14" >14</option>
            <option value="15" >15</option>
            <option value="16" >16</option>
            <option value="17" >17</option>
            <option value="18" >18</option>
            <option value="19" >19</option>
            <option value="20" >20</option>
            <option value="21" >21</option>
            <option value="22" >22</option>
            <option value="23" >23</option>
            <option value="24" >24</option>
            <option value="25" >25</option>
            <option value="26" >26</option>
            <option value="27" >27</option>
            <option value="28" >28</option>
            <option value="29" >29</option>
            <option value="30" >30</option>
            <option value="31" >31</option>
            <option value="32" >32</option>
            <option value="33" >33</option>
            <option value="34" >34</option>
            <option value="35" >35</option>
            <option value="36" >36</option>
            <option value="37" >37</option>
            <option value="38" >38</option>
            <option value="39" >39</option>
            <option value="40" >40</option>
            <option value="41" >41</option>
            <option value="42" >42</option>
            <option value="43" >43</option>
            <option value="44" >44</option>
            <option value="45" >45</option>
            <option value="46" >46</option>
            <option value="47" >47</option>
            <option value="48" >48</option>
            <option value="49" >49</option>
            <option value="50" >50</option>
            <option value="100" >Float</option>
          </select>        </td>
      </tr>
    </table>
      <table cellspacing=0 cellpadding=0 border=0 width="100%">
        <tr>
          <td height="12" align="center" nowrap style="padding-top: 8px"><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a>            <input type="button" value="保存报表" onClick="if(document.getElementById('Report_Title').value==''){alert('请输入报表标题！')}else{if(confirm('您确认要保存吗？')){myForm.submit()}}" class=mmBtn name="button"></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</body>
</html>
