
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="budget/customer/display.do" modelAttribute="budget">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="materials"/>
	<form:hidden path="request"/>
	<form:hidden path="status"/>
	<form:hidden path="painter"/>
	
  	<br>

	
	<acme:inputTextMD code="budget.amount" path="amount"/>
	<acme:textareaMD code="budget.description" path="description"/>
	
	<div class="col-xs-12 col-sm-4 pull-right">
					<a href="budget/customer/makePayPaypal.do">
						<img src="images/paypal.png" alt="Pay with paypal button" class="pull-right">
					</a>
			
			</div>
	 
	

</form:form>


