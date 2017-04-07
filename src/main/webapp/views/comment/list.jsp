<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<display:table name="comments" id="row" requestURI="${requestUri}" class="table" keepStatus="true" pagesize="5" >
	
	<spring:message code="comment.moment" var="momentColumn" ></spring:message>
	<display:column title="${momentColumn}"><center><fmt:formatDate value="${row.moment}" pattern="dd-MM-yyyy HH:mm"/></center></display:column>
	
	<spring:message code="comment.numberOfStars" var="numberOfStarsColumn" ></spring:message>
	<display:column property="numberOfStars" title="${numberOfStarsColumn}"/>
	
	<spring:message code="comment.text" var="textColumn" ></spring:message>
	<display:column property="text" title="${textColumn}" />	
	
</display:table>