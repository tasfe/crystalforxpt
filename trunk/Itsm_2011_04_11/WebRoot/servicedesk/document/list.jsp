<%@ page language="java" contentType="text/html; charset=UTF-8"
         isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="tags" uri="/WEB-INF/tags.tld"%>
<html>
    <head>
        <title>文档列表</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="../js/zcms/zDialog.js">
        </script>
        <script type="text/javascript" src="../js/zcms/zDrag.js">
        </script>
        <script language="JavaScript" type="text/javascript"
                src="../js/DatePicker/WdatePicker.js">
        </script>
        <script type='text/javascript' src='../dwr/util.js'>
        </script>
        <script type='text/javascript' src='../dwr/interface/DepartmentDAO.js'>
        </script>
        <script type='text/javascript'
                src='../dwr/interface/AssetsTypeDAO.js'>
        </script>
        <script type='text/javascript'
                src='../dwr/interface/AssetsProducerDAO.js'>
        </script>

        <script type='text/javascript' src='../dwr/interface/LocationDAO.js'>
        </script>
        <script type='text/javascript' src='../dwr/interface/UserDAO.js'>
        </script>
        <script type='text/javascript' src='../dwr/engine.js'>
        </script>
        <script type="text/javascript">
            function loadTop() {
                clo();
                var topframe = parent.frames.topFrame;
                topframe.location = "top.action";
            }
            function newD() {
                window
                .open("add.action?pid=${pid}", "backup",
                "height=600,width=800,location=no,status=no,toolbar=no,resizable=yes");
            }

            function del() {
                var msgs = "确认删除记录吗？";
                if (confirm(msgs) == true) {
                    return true;
                } else {
                    return false;
                }
            }
            function clo() {
                var ag = '<s:property value="msg" escape="false" />';
                if (ag) {
                    alert("提示：" + ag);
                }
                DepartmentDAO.findAll(callbackdepartment);
                LocationDAO.findAll(callbackorg);
                UserDAO.findAll(callbackusers);
            }

            function callbackorg(data) { //显示出类别
                dwr.util.removeAllOptions("Location");
                dwr.util.addOptions("Location", {
                    '-1' : '--请选择--'
                });
                dwr.util.addOptions("Location", data, "id", "name");
                var a = "<s:property value="
                        workLogQurey.workLog.location.id" />";
                                if (typeof(a) != "undefined") {   
                                    dwr.util.setValue("Location",a);  
                                }
                            }
                            function callbackusers(data){  //显示出用户
                                dwr.util.removeAllOptions("users");
                                dwr.util.addOptions("users",{'-1':'--请选择--'});
                                dwr.util.addOptions("users",data,"id","truename");   
                                var a = "<s:property value="workLogQurey.workLog.users.id" />";
                                if (typeof(a) != "undefined") {   
                                    dwr.util.setValue("users",a);  
                                }
                            }
                            function callbackdepartment(data){  //显示出类别
                                dwr.util.removeAllOptions("parentDepart");
                                dwr.util.addOptions("parentDepart",{'-1':'--请选择--'});
                                dwr.util.addOptions("parentDepart",data,"id","name");  
                                var a = "<s:property value="workLogQurey.workLog.location.id" />";
                                if (typeof(a) != "undefined") {   
                                    dwr.util.setValue("parentDepart",a);  
                                } 
                            }



        </script>

    </head>

    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"
          onLoad="loadTop()"
          onmousedown="document.getElementById('Layer2').style.visibility='hidden'">

        <table width="99%" border="0" cellspacing="1" align="center"
               bgcolor="#b5d6e6">
            <s:form action="list" method='post' theme="simple" name="form"
                    namespace="/document">
                <s:hidden id="page" name="page"></s:hidden>
                <s:hidden id="state" name="state"></s:hidden>
                <s:hidden id="pageSize" name="pageSize"></s:hidden>
                <tr>
                    <td height="22" colspan="7" align="left"
                        background="../images/main20100521_58.gif" class="alllisttitle">
						文档搜索
                    </td>
                </tr>
                <tr>
                    <td width="11%" height="22" align="right" bgcolor="#deebf1">
						标题：
                    </td>
                    <td width="18%" bgcolor="#FFFFFF">
                        <s:textfield id="dc.title" name="dc.title" cssStyle="width: 99%"></s:textfield>
                    <td align="right" bgcolor="#deebf1">
                        <!--<img onClick="WdatePicker({el:$dp.$('workLogQurey.endDate')})" src="../js/DatePicker/skin/datePicker.gif" />-->
							部门：
                    </td>
                    <td align="right" bgcolor="#FFFFFF" width="36%">


                        <input id="department.name" type="text"
                               name="dc.department.name"
                               style="width: 79%; background-color: #FFFFFF; cursor: text"
                               readonly
                               value="<s:property value="dc.department.name"/>"
                               onClick="document.getElementById('Layer2').style.visibility='visible'">
                        <s:hidden id="department.id"
                                  name="dc.department.id"></s:hidden>

                            &nbsp;
                            <img src="../images/main20100521lsearch.gif" align="absmiddle"
                                 style="cursor: hand"
                                 onClick="document.getElementById('Layer2').style.visibility='visible'">
                            <br>

                            <div id="Layer2"
                                 style="position: absolute; width: 180%; height: 20px; z-index: 1; visibility: hidden; padding: 1px; border: 1px outset white; background-color: white">

                                <iframe frameborder="0" height="150" width="100%"
                                        scrolling="yes" src="../department/departmentList.action"
                                        style="border: 1px solid #E5E9EE"></iframe>

                            </div>

                        <%--<select id="parentDepart" name="dc.department.id"
								style="width: 99%">
							</select>
						--%>
                    </td>

                    <%--
                    <td width="14%" bgcolor="#deebf1" align="right"">
							作者：
						</td>
						<td width="16%" align="left" bgcolor="#FFFFFF">
						<input type="text" id="users" name="dc.users.username">
                    
							<select id="users" name="dc.users.id" style="width: 99%">
							</select>
						--%></td>
                    <td width="9%" rowspan="3" bgcolor="#FFFFFF" align="center">
                        <tags:button code="select" menu="25">
                            <input type="submit" style="height: 20px" class="mmBtn"
                                   value="搜索" />
                        </tags:button>
                    </td>
                </tr>

            </s:form>
        </table>
        <table width="99%" border="0" align="center" cellpadding="0"
               cellspacing="0">
            <tr>
                <td height="30" align="right">

                    <table width="60" border="0" cellpadding="0" cellspacing="0"
                           background="../images/addnew002.gif">
                        <tags:button code="add" menu="25">
                            <tr onClick="newD();" style="cursor: hand;">
                                <td>
                                    <img src="../images/addnew001.gif">
                                </td>
                                <td nowrap>
									新建文档
                                </td>
                                <td align="right">
                                    <img src="../images/addnew003.gif">
                                </td>
                            </tr>
                        </tags:button>
                    </table>
            </tr>
        </table>
        <table width="99%" border="0" align="center" cellpadding="0"
               cellspacing="1" bgcolor="#b5d6e6">
            <tr bgcolor="#FFFFFF">
                <td height="22" align="center"
                    background="../images/main20100521_58.gif" class="alllisttitle">
					序号
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					文档标题
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					作者
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					查看
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					修改
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					删除
                </td>
                <td align="center" background="../images/main20100521_58.gif"
                    class="alllisttitle">
					历史版本
                </td>
            </tr>
            <s:iterator value="pageBean.list" var="dc" status="stat">
                <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='#e3f0f7'"
                    onMouseOut="this.bgColor='#FFFFFF'">
                    <td height="19" align="center" class="zczb_qua">
                        ${stat.index+1}
                    </td>
                    <td align="center" class="zczb_qua">
                        ${title}
                    </td>
                    <td align="center" class="zczb_qua">
                        ${userName}
                    </td>
                    <td align="center">
                        <tags:button code="query" menu="25">
                            <img src="../images/edt.gif">
                            <a href="#"
                               onclick="window.open('show.action?document_id=${id}&pid=${pid}&page=${page}','newwindow','height=600, width=800, top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');">查看</a>
                        </tags:button>
                    </td>
                    <%-- <td align="center"><img src="../images/edt.gif"><a href="index_update.action?document_id=${dc.id}&pid=${pid}&page=${page}">修改</a></td>--%>
                    <td align="center">
                        <tags:button code="update" menu="25">
                            <img src="../images/edt.gif">
                            <a href="#"
                               onclick="window.open('index_update.action?document_id=${id}&pid=${pid}&page=${page}','backup','height=600,width=800,location=no,status=no,toolbar=no,resizable=no,crollbars=yes');">修改</a>
                        </tags:button>
                    </td>
                    <td align="center">
                        <tags:button code="delete" menu="25">
                            <img src="../images/del.gif">
                            <a href="delete.action?document_id=${id}&page=${page}&pid=${pid}"
                               onclick="javascript:return del()">删除</a>
                        </tags:button>
                    </td>
                    <td align="center">
                        <tags:button code="history" menu="25">
                            <img src="../images/edt.gif">
                            <a href="listhistory.action?dc.num=${num}">历史版本</a>
                        </tags:button>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <jsp:include page="/common/page.jsp" />
    </body>
</html>
