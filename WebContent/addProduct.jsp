<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Product</title>
	<link rel="shortcut icon" href="admin.ico" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		form {
			width: 100%;
			margin-top: 3%;
		}
		table, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		table {
			width: 45%;
			font-weight: bold;
			letter-spacing: 2px;
			margin: 0 auto;
		}
		td {
			padding: 18px 0px;
		}
		input {
			width: 70%;
			padding: 10px 4px;
		}
		textarea {
			resize: none;
			width: 70%;
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
		<form id="form" action="addProduct.do" method="post">
			<table>
				<tr><td>Product Name :</td><td><input type="text" placeholder="Enter Product Name" name="productName"></td></tr>
				<tr><td>Category Name :</td><td><input type="text" placeholder="Enter Category Name" name="categoryName"></td></tr>
				<tr><td>Price :</td><td><input type="number" min="0" placeholder="Enter Product Price"  name="productPrice"></td></tr>
				<tr><td>Description :</td><td><textarea rows="2" cols="15" placeholder="Enter Product Description" name="productDescription"></textarea></td></tr>
				<tr><td colspan="2"><input type="submit" onclick="submit()" value="Add Product"></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>