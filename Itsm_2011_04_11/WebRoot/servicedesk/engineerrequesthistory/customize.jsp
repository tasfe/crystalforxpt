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
   <td width="49%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;">请选择显示的列:</td>
   <td width="1%" background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><span style="padding: 1px; border-right: 0px; padding-top: 2px">
     <input type="checkbox" name="Default" value="1" style="border: 0px">
   </span></td>
   <td width="1%" nowrap background="../../images/main20100521_582.gif" style="color:#FFFFFF; font-weight:bold;"><span style="padding: 1px; border-left: 0px; padding-top: 2px">设为默认页&nbsp;</span></td>
  </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#6d9db4">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#FFFFFF" height="100%">
      <input type="hidden" value="RequObj" name="Field_0">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_0" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="服务类别" name="Name_0" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_0">
            <option value="15">15%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequPeop" name="Field_1">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_1" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="申请人" name="Name_1" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_1">
            <option value="12">12%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequTrueTime" name="Field_2">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_2" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="申请时间" name="Name_2" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_2">
            <option value="12">12%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="WorkTimes" name="Field_3">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_3" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="结束时间" name="Name_3" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_3">
            <option value="12">12%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Approver" name="Field_4">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_4" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="派单人" name="Name_4" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_4">
            <option value="12">12%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="ITPrinc" name="Field_5">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_5" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="工程师" name="Name_5" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_5">
            <option value="13">13%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="WorkTimes" name="Field_6">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_6" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="处理总时长" name="Name_6" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_6">
            <option value="$@15">15%</option>
            <option value="$@10">10%</option>
            <option value="$@15">15%</option>
            <option value="$@20">20%</option>
            <option value="$@25">25%</option>
            <option value="$@30">30%</option>
            <option value="$@35">35%</option>
            <option value="$@40">40%</option>
            <option value="$@45">45%</option>
            <option value="$@50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequNo" name="Field_7">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_7" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="工单号" name="Name_7" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_7">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequDept" name="Field_8">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_8" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="部门" name="Name_8" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_8">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Score" name="Field_9">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_9" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="方案得分" name="Name_9" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_9">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Message" name="Field_10">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_10" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="严重程度" name="Name_10" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_10">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="EndTime" name="Field_11">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_11" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="开始工作时间" name="Name_11" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_11">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="ClientIde" name="Field_12">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_12" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="用户反馈" name="Name_12" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_12">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="NeedTime" name="Field_13">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_13" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="承诺完成时间" name="Name_13" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_13">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Succors" name="Field_14">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_14" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="提交人" name="Name_14" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_14">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="AddToKnowledge" name="Field_15">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_15" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="知识库" name="Name_15" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_15">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="ResponTime" name="Field_16">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_16" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="承诺响应时间" name="Name_16" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_16">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="TransTime" name="Field_17">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_17" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="自动升级时间" name="Name_17" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_17">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="PRI" name="Field_18">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_18" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="优先级" name="Name_18" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_18">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequLoca" name="Field_19">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_19" type="checkbox" value="1" style="border: 0px" checked>
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="地域" name="Name_19" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_19">
            <option value="10">10%</option>
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="OrderTime" name="Field_32">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_32" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="分配时间" name="Name_32" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_32">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="RequDetail" name="Field_35">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_35" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="描述" name="Name_35" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_35">
            <option value="$10">10%</option>
            <option value="$15">15%</option>
            <option value="$20">20%</option>
            <option value="$25">25%</option>
            <option value="$30">30%</option>
            <option value="$35">35%</option>
            <option value="$40">40%</option>
            <option value="$45">45%</option>
            <option value="$50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Login" name="Field_37">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_37" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="申请人登录名" name="Name_37" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_37">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Essential" name="Field_42">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_42" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="影响度" name="Name_42" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_42">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="Emergency" name="Field_43">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_43" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="紧急度" name="Name_43" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_43">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="ErroCause" name="Field_45">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_45" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="错误原因" name="Name_45" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_45">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="ServiceTitle" name="Field_46">
      <tr>
        <td width="1%" bgcolor="#b5d6e6" class="td-left"><input name="Chose_46" type="checkbox" value="1" style="border: 0px">
        </td>
        <td width="99%" bgcolor="#EBF4F5" class="td-right"><input type="text" value="标题概要" name="Name_46" style="width: 100%">
        </td>
        <td bgcolor="#EBF4F5" class="td-right"><select name="Width_46">
            <option value="10">10%</option>
            <option value="15">15%</option>
            <option value="20">20%</option>
            <option value="25">25%</option>
            <option value="30">30%</option>
            <option value="35">35%</option>
            <option value="40">40%</option>
            <option value="45">45%</option>
            <option value="50">50%</option>
          </select>
        </td>
      </tr>
      <input type="hidden" value="67" name="Total">
    </table></td>
  </tr>
</table>
<table cellspacing=0 cellpadding=0 border=0 width="100%">
		  <tr>
			<td height="12" align="center" nowrap style="padding-top: 8px"><a id="htmlclock" style="font-size: 20px; font-family: 'Arial Black'; color: green"></a>
			  <input type="button" value="确定" onClick="myForm.submit()" class=mmBtn name="button">			</td>
		  </tr>
		</table>
</body>
</html>
