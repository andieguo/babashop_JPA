<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>产品类别管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
//-->
</script>
<SCRIPT language=JavaScript src="<%=basePath%>/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<s:form action="list" namespace="/control/producttype" method="post">
<s:hidden name="page"/>
<s:hidden name="name"/>
<s:hidden name="query"/>

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="7"  bgcolor="6f8ac4" align="right">
    	<%@ include file="/WEB-INF/page/share/fenye.jsp" %>
   </td></tr>
    <tr>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="20%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品类别名称</font></div></td>
	  <td width="10%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">创建下级类别</font></div></td>
	  <td width="15%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">所属父类</font></div></td>
	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">备注</font></div></td>
	  <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">子类个数</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${entry.typeid }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">
	  <a href='<s:url action="editUI" namespace="/control/producttype"/>?typeid=${entry.typeid }'><img src="<%=basePath%>/images/edit.gif" width="15" height="16" border="0"></a></div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href='<s:url action="list" namespace="/control/producttype"/>?parentid=${entry.typeid }'>${entry.name }</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="center"><a href='<s:url action="addUI" namespace="/control/producttype"/>?parentid=${entry.typeid }'>创建子类别</a></div></td>
	  <td bgcolor="f5f5f5"><div align="center"><c:if test="${!empty entry.parent}">${entry.parent.name}</c:if></div></td>
	  <td bgcolor="f5f5f5"><div align="center">${entry.note }</div></td>
	  <td bgcolor="f5f5f5"><div align="center"><c:if test="${fn:length(entry.childtypes)>0}"><font color="red">${fn:length(entry.childtypes)}</c:if></font></div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="7" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
           <c:if test="${productTypeform.getQuery()==null}">
              <td width="85%">
              <input name="AddDic" type="button" class="frm_btn" id="AddDic"  onclick="javacript:window.location.href='<s:url action="addUI" namespace="/control/producttype"/>?method=addUI&parentid=${param.parentid}'" value="添加类别"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onclick="javacript:window.location.href='<s:url action="queryUI" namespace="/control/producttype"/>'" value=" 查 询 "> &nbsp;&nbsp;
			  </td>
            </c:if>
          </tr>
        </table></td>
    </tr>
  </table>
</s:form>
</body>
</html>