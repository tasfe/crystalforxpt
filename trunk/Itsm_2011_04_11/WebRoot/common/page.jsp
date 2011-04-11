<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
%>
<script Language="JavaScript" src="<%=path%>/js/pub.js"></script>
<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr height="24" vAlign="middle"  align="center">
		<td height="30" align="left" class="zczb_qua">
           <s:if test="%{pageBean.currentPage == 1}">第一页 上一页</s:if>
           <s:else>
           <a href="#" onclick="JavaScript:gotoPage(1)">第一页</a>
           <a href="#" onclick="JavaScript:gotoPage('<s:property value="%{pageBean.currentPage-1}"/>')">上一页</a>
           </s:else>
           <s:if test="%{pageBean.currentPage != pageBean.totalPage}">
         	<a href="#" onclick="JavaScript:gotoPage('<s:property value="%{pageBean.currentPage+1}"/>')">下一页</a>
           <a href="#" onclick="JavaScript:gotoPage('<s:property value="pageBean.totalPage"/>')">最后一页</a>
           </s:if>
           <s:else>下一页 最后一页</s:else>
		</td>
		<td valign="middle" align="right" class="zczb_qua">
			共<s:property value="pageBean.allRow"/> 条记录&nbsp;&nbsp;
			共<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;
			当前第<s:property value="pageBean.currentPage"/>页&nbsp;&nbsp;
			每页<s:property value="pageBean.pageSize"/>行&nbsp;&nbsp;
			每页
			<SELECT	style="COLOR: #191970; HEIGHT: 22px; BACKGROUND-COLOR: #E8FFBB"	name="pageSize" onchange="RowsPerPage(this.value)">
				<s:iterator var="counter" begin="10" end="pageBean.allRow+10" step="10" >
					<option value="<s:property value='#counter'/>"<s:if test="pageBean.pageSize == #counter"> selected</s:if>><s:property value='#counter'/></option>
				</s:iterator>
			</SELECT>
			行&nbsp; 
			转到第
			<SELECT style="COLOR: #191970; HEIGHT: 22px; BACKGROUND-COLOR: #E8FFBB" name="page" onchange="Jumping(this.value)">
				<s:iterator var="counter" begin="1" end="pageBean.totalPage" step="1" >
					<option value="<s:property value='#counter'/>" <s:if test="pageBean.currentPage == #counter"> selected</s:if>><s:property value='#counter'/></option>
				</s:iterator>
		    </SELECT>页
		</td>
	  </tr>
</table>