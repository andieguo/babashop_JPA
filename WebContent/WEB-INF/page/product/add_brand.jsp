<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>添加产品品牌</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/css/vip.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>/js/FoshanRen.js"></script>
<script type="text/javascript">
	function checkfm(form){
	//	alert("只允许上传gif、jpg、bmp、png！");
	
		if (trim(form.name.value)==""){
			alert("品牌名称不能为空！");
			form.name.focus();
			return false;
		}
		var logofile = form.logofile.value;
		if(trim(logofile)!=""){
			var ext = logofile.substring(logofile.length-3).toLowerCase();//变为小写
			if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
				alert("只允许上传gif、jpg、bmp、png！");
				return false; 
			}
		}
		return true;
	}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<s:form action="add" namespace="/control/brand" enctype="multipart/form-data" onsubmit="return checkfm(this)" method="post">
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">添加品牌：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">品牌名称：</div></td>
      <td width="78%"> <s:textfield name="brand.name" size="50" maxlength="40"/>
        <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">Logo图片：</div></td>
      <td width="78%"> <input type="file" name="logo" size="50">
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
  <s:fielderror />
</s:form>
<br>
</body>
</html>