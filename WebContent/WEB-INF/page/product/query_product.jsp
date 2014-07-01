<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>查询产品</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="<%=basePath%>/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form action="list" namespace="/control/product" method="post">
<s:hidden name="typeid"/>
<s:hidden name="query" value="true"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">查询产品：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">是否推荐 ：</div></td>
      <td width="75%"> <input type="radio" name="commend" value="true" checked="checked">推荐<input type="radio" name="commend" value="false">不推荐
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">是否上架：</div></td>
      <td width="75%"> <input type="radio" name="visible" value="true" checked="checked">上架<input type="radio" name="visible" value="false">下架
    </tr>    
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品名称  ：</div></td>
      <td width="75%"> <s:textfield name="name" size="50" maxlength="40"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品类别  ：</div></td>
      <td width="75%"> <input type="text" name="v_type_name" disabled="true" size="30" value="${typename}"/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<s:url action="selectUI" namespace="/control/product"/>','列表',600,400)">
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">底(采购)价(元) ：</div></td>
      <td width="75%"> 
在<s:textfield name="startbaseprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
      与<s:textfield name="endbaseprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>之间
</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">销售价(元) ：</div></td> 
      <td width="75%"> 在<s:textfield name="startsellprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
      与<s:textfield name="endsellprice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>之间
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">货号 ：</div>
      <td width="75%"><s:textfield name="code" size="20" maxlength="30"/>(注:供货商提供的便于产品查找的编号)</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">品牌 ：</div></td>
      <td width="75%">
      <s:select name="brandid" list="brands" listKey="code" listValue="name" headerKey="" headerValue="***无***">
      </s:select>
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="edit" value=" 确 认 " class="frm_btn">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</s:form>
<br>
</body>
</html>