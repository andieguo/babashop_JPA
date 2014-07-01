<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/page/share/taglib.jsp" %>
<div id="Head">
  <div id="HeadTop">
    <div id="Logo"><a href="http://www.babasport.com/" target=_top><img alt=中国最大、最安全的户外用品、体育用品网上交易平台！ src="<%=basePath%>/images/global/logo.gif" border=0 /></a> </div>
    <div id="HeadNavBar">
      <ul>
        <li class="NoSep"><a id="MyBuyCar"  href="<%=basePath%>/shopping/cart.do" ><font color="blue"><Strong>购物车</Strong></font></a> </li>
        <li><a href="<%=basePath%>/user/reg.do?method=regUI" >新用户注册</a> </li>
        <li><a href="<%=basePath%>/user/logon.do" >用户登录</a> </li>
         <c:if test="${!empty user}"> <li><a href="<%=basePath%>/user/logout.do" >退出登录</a> </li></c:if>
        <li><a href="">帮助中心</a> </li>
        <li class="phone">服务热线：010-64663070</li>
      </ul>
    </div>
  </div>
  <div id="ChannelMenu">
	<UL id="ChannelMenuItems">
		<LI id="MenuHome"><a href="http://www.babasport.com"><span>首页</span></a></LI>
		<LI id="ProducType1Home"><a href="<%=basePath%>/product/list.do?typeid=1"><span>瑜珈用品</span></a></LI>
		<LI id="ProducType2Home"><a href="<%=basePath%>/product/list.do?typeid=2"><span>户外用品</span></a></LI>
		<LI id="ProducType3Home"><a href="<%=basePath%>/product/list.do?typeid=37"><span>健身器材</span></a></LI>
		<LI id="ProducType8Home"><a href="<%=basePath%>/product/list.do?typeid=38"><span>运动鞋</span></a></LI>
		<LI id="MyAccountHome"><a href="<%=basePath%>/"><span>我的账户</span></a></LI>
	</UL>
<!--  SearchBox -->
<div id="SearchBox">
	  <div id="SearchBoxTop">
		  <div id="SearchForm">
			<form action="/product/query.do" method="post" name="search" id="search">

			 <span class="name">商品搜索: </span><input id="word" name="word" accesskey="s" size="100" maxlength="100" value="${param.word }"/>

			  <input type="submit" value="搜 索" id="DoSearch"/>
			</form>
		  </div>
	  </div>
      <div id="HotKeywords">
			<ul>
				<li><span>
					2008年07月29日&nbsp;&nbsp;
您好<SCRIPT language=JavaScript src="<%=basePath%>/js/welcome.js"></SCRIPT>，欢迎来到巴巴运动网！</span></li>
				<li>热门搜索：</li>
				
<li><a href="<%=basePath%>/q?word=%E5%B8%90%E7%AF%B7">帐篷</a></li>
<li><a href="<%=basePath%>/q?word=%E7%91%9C%E4%BC%BD%E7%90%83">瑜伽球</a></li>
<li><a href="<%=basePath%>/q?word=%E8%BF%9C%E9%98%B3%E7%91%9C%E4%BC%BD%E6%9C%8D">远阳瑜伽服</a></li>

			</ul>
      </div>
   </div>
</div><!-- End SearchBox -->
</div>
<!-- Head End -->