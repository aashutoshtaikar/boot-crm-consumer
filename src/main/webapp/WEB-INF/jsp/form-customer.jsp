<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Add Customer</h2>
	<input type="button" onclick="window.location.href='list'" value="Home">
	<br><br>
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">
		
		<!-- need to associate this data with customer id for the update opertation-->
		<form:hidden path="id"/>
				
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name"/></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><form:input path="email"/></td>
			</tr>
			
			<tr>
				<td><label></label></td>
				<td><input type="submit" value="Save" class="save"></td>
			</tr>
		</table>
	
	</form:form>
</body>
</html>