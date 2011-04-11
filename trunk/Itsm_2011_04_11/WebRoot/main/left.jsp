<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<link rel="StyleSheet" href="../css/dtree.css" type="text/css" /> 
<script type="text/javascript" src="../js/dtree.js"></script>
<script type="text/javascript">
  d = new dTree('d');
  <s:iterator value="channel">
  d.add('<s:property value="id"/>','<s:property value="parentId"/>','<s:property value="name"/>','<s:property value="path"/>','<s:property value="logo"/>','mainFrame');
  </s:iterator>
  document.write(d);
</script>