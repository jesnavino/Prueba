
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="request/customer/edit.do" modelAttribute="request">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="moment"/>
	<form:hidden path="customer"/>
	<form:hidden path="budgets"/>
  	
	<acme:inputTextMD code="request.edit.schedule" path="schedule"/>	
	<acme:inputTextMD code="request.edit.measures" path="measures"/>	
	<acme:inputTextMD code="request.edit.photos" path="photos"/>	
	<acme:textareaMD code="request.edit.description" path="description"/>	
	<acme:inputTextMD code="request.edit.address" path="address"/>	
	<acme:inputTextMD code="request.edit.work" path="work"/>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<h1><spring:message code="request.paidFormat"></spring:message>
	<small><spring:message code="request.creditTarjet"></spring:message></small></h1>
	
	
	<acme:inputTextMD code="request.edit.creditCard.holderName" path="creditCard.holderName"/>
	<acme:inputTextMD code="request.edit.creditCard.brandName" path="creditCard.brandName"/>
	<acme:inputTextMD code="request.edit.creditCard.number" path="creditCard.number"/>
	<acme:inputTextMD code="request.edit.creditCard.expirationMonth" path="creditCard.expirationMonth"/>
	<acme:inputTextMD code="request.edit.creditCard.expirationYear" path="creditCard.expirationYear"/>
	<acme:inputTextMD code="request.edit.creditCard.cvv" path="creditCard.cvvCode"/>
	
	<acme:submitMD name="save" code="request.edit.save" withButtonCancel="true"
					codeButtonCancel="request.edit.cancel" />
	

</form:form>


