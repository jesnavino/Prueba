<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div class="col-md-6 col-centered">
	<div class="well bs-component">
		<form:form action="user/register.do" modelAttribute="urf"
			class="form-horizontal" name="f1">
			<fieldset>
				<legend>
					<spring:message code="user.register" />
				</legend>

				<acme:inputTextMD code="user.username" path="username" />
				<acme:inputPassMD code="user.password" path="password" />
				<acme:inputPassMD code="user.verifypass" path="verifyPassword" />

				<form:checkbox path="acceptTerms" />
				<form:label path="acceptTerms">
					<spring:message code="register.acceptTerms" />
					<a target="_blank" href="terms/show.do"><spring:message
							code="master.page.terms" /></a>
				</form:label>
				<p class="error">
					<form:errors path="acceptTerms"></form:errors>
				</p>
				
				<acme:submitMD name="save" code="user.save" withButtonCancel="true"
					codeButtonCancel="user.cancel" />
			</fieldset>
		</form:form>
	</div>
</div>
