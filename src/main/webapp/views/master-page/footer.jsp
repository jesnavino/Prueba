<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:useBean id="date" class="java.util.Date" />

<b>Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" />
	Pintacme
</b>
-
<b><a href="terms/show.do"><spring:message
			code="master.page.terms" /></a></b>