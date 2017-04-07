
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="budget/painter/edit.do" modelAttribute="budget">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="materials"/>
	<form:hidden path="request"/>
	<form:hidden path="status"/>
	<form:hidden path="painter"/>
	
  	<br>
  	
	
	<acme:textareaMD code="budget.edit.amount" path="amount"/>
	<acme:inputTextMD code="budget.description" path="description"/>
	
	 
	<acme:submitMD name="save" code="budget.edit.save" withButtonCancel="true"
					codeButtonCancel="budget.edit.cancel" />
	
	

	<jstl:if test="${budget.id!=0}">
		<acme:submitMD name="delete" code="budget.edit.delete"/>
	</jstl:if>
	
	

</form:form>


