<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
	<c:forEach items="${topsellproducts}" var="topsellproduct" varStatus="statu">
		<LI class="bj_blue"><a href="<html:rewrite action="/product/view"/>?productid=${topsellproduct.id}" target="_blank" >${topsellproduct.name}</a></LI>
	</c:forEach>			
