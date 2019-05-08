<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Create a Field</title>
</head>
<body>
	<h3>Enter Credit Card Details</h3>
	<form:form method="POST" action="${pageContext.request.contextPath}/createPayment" modelAttribute="cardDetails">
		<table>
	         <tr>
				<td><form:label path="cardNumber">Card Number</form:label></td>
				<td><form:input path="cardNumber" /></td>
			</tr>
		    <tr>
				<td><form:label path="expMonth">Exp Month</form:label></td>
				<td><form:input path="expMonth" /></td>
			</tr>
			<tr>
				<td><form:label path="expYear">Exp Year</form:label></td>
				<td><form:input path="expYear" /></td>
			</tr>
			<tr>
				<td><form:label path="csv">CSV</form:label></td>
				<td><form:input path="csv" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	

</body>