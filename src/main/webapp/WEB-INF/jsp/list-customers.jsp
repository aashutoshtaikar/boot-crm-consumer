<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customers List</title>
</head>
<body>
	<h2>Customers List</h2>
	<input type="button" onclick="window.location.href='list'" value="Home">
	<input type="button" onclick="window.location.href='showAddForm'" value="Add Customer">
	<br><br>
	<c:if test="customerFound==false">
		<font color="red">Customers Not found</font>
	</c:if>
	<form action="searchCustomers">
		<input type="text" name="theSearchName"> 
		<input type="submit" value="Search">	
	</form>
		
	<c:forEach items="${customers}" var="customer">
		<table>
			<tr>
				<td>${customer.id}</td>
				<td>${customer.name}</td>
				<td>${customer.email}</td>
				<td><input type="button" onclick="window.location.href='showUpdateForm?id=${customer.id}'" value="Update"></td>
				<td><input type="button" onclick="window.location.href='deleteCustomer?id=${customer.id}'" value="Delete"> </td>
			</tr>
		</table>
	</c:forEach>
</body>
</html>