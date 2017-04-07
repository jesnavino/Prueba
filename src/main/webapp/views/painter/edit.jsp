
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="painter/register.do" modelAttribute="painterForm">

	<form:hidden path="averageStar"/>

	<acme:inputTextMD code="painter.username" path="username"/>	
	<acme:inputPassMD code="painter.password" path="password"/>
	<acme:inputPassMD code="painter.passRepeated" path="repeatedPassword"/>		
  	
	<acme:inputTextMD code="painter.name" path="name"/>	
	<acme:inputTextMD code="painter.surname" path="surname"/>	
	<acme:inputTextMD code="painter.postalCode" path="postalCode"/>	
	<acme:inputTextMD code="painter.email" path="email"/>	
	<acme:inputTextMD code="painter.phone" path="phone"/>
	<acme:inputTextMD code="painter.codeSS" path="codeSS"/>

	<form:checkbox path="hasAccepted"></form:checkbox>
	<spring:message code="painter.hasAccepted"/>
		
	
	<acme:submitMD name="save" code="painter.save" withButtonCancel="true"
					codeButtonCancel="painter.cancel" />
	

</form:form>


