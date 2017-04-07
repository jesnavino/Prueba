<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>


<display:table name="budgets" id="row" requestURI="${requestUri}" class="table" keepStatus="false" pagesize="5"  >
	
	<spring:message code="budget.request.work" var="workColumn" ></spring:message>
	<display:column title="${workColumn}"><center><fmt:formatDate value="${row.request.work}" pattern="dd-MM-yyyy HH:mm"/></center></display:column>
	
	<spring:message code="budget.request.address" var="addressColumn" ></spring:message>
	<display:column property="request.address" title="${addressColumn}"/>
	
	<spring:message code="budget.status" var="statusColumn" ></spring:message>
	<display:column property="status" title="${statusColumn}"/>
	
	<spring:message code="budget.amount" var="amountColumn" ></spring:message>
	<display:column property="amount" title="${amountColumn}"/>
	
	
	<spring:message code="budget.description" var="descriptionColumn" ></spring:message>
	<display:column property="description" title="${descriptionColumn}"/>
	
	<display:column>
		<center><a href="material/painter/list.do?id=${row.id}">
			<spring:message code="budget.list.material"/>
		</a></center>
	</display:column>
	
	<security:authorize access="hasRole('PAINTER')">
	<display:column>
		<center><a href="budget/painter/edit.do?id=${row.id}">
			<spring:message code="budget.edit"/>
		</a></center>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column>
  <jstl:if test="${row.status=='PENDING'}">
  <center><a href="budget/customer/accept.do?id=${row.id}">
   <spring:message code="budget.list.accept"/>
  </a></center>
  </jstl:if>
 </display:column>
 </security:authorize>
 
 <security:authorize access="hasRole('CUSTOMER')">
 <display:column>
  <jstl:if test="${row.status=='PENDING'}">
  <center><a href="budget/customer/reject.do?id=${row.id}">
   <spring:message code="budget.list.reject"/>
  </a></center>
  </jstl:if>
 </display:column>
	</security:authorize>
	
	</display:table>