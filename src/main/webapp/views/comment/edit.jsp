
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="comment/customer/edit.do" modelAttribute="comment">
		
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="painter"/>
		<form:hidden path="moment"/>
		
		<acme:textareaMD code="comment.edit.text" path="text"/>
		<acme:inputTextMD code="comment.edit.numberOfStars" path="numberOfStars"/>	
		
		<acme:submitMD name="save" code="comment.edit.save" withButtonCancel="true"
					codeButtonCancel="comment.edit.cancel" />	
							
		
</form:form>
