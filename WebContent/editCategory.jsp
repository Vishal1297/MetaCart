<%@page import="org.mz.bean.Category"%>
<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Category</title>
	<link rel="shortcut icon" href="admin.ico" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		form {
			width: 100%;
			margin-top: 70px;
		}
		table, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		table {
			width: 40%;
			font-weight: bold;
			letter-spacing: 2px;
			margin: 0 auto;
		}
		td {
			padding: 18px 0px;
		}
		input {
			width: 80%;
			text-align: center;
			border-radius: 12px;
			padding: 10px 4px;
		}
		input[type=submit] {
			border: 1px solid grey;
			border-radius: 10px;
			padding: 3% 4%;
			font-size: 18px;
		}
	</style>
</head>
<body>
	<%@ include file="header.html" %>
	<%@ include file="menu.html" %>
	<div id="main">
		<%
			if (session != null) {
				if (session.getAttribute("userId") == null && session.getAttribute("password") == null) {
					response.sendRedirect("login.jsp");
				}
			}
		%>
		<% Category category = (Category)(request.getAttribute("category")); %>
		<form id="form" action="updateCategory.do" method="post">
			<table>
				<tr><td>Category ID :</td></tr>
				<tr><td><input type="number" placeholder="Enter Category ID" name="categoryId" value="<%=category.getCategoryId() %>"></td></tr>
				<tr><td>Category Name :</td></tr>
				<tr><td><input type="text" placeholder="Enter Category Name" name="categoryName" value="<%=category.getCategoryName()%>"></td></tr>
				<tr><td><input type="submit" value="Update"></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>