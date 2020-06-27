<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Search Image</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
			#main {
				margin: 0;
			}
			form {
				width: 70%;
				margin-top: 3%;
			}
			label {
				font-weight: bold;
				font-size: 20px;
			}
			table, th,td {
				border: 1px solid black;
				border-collapse: collapse;
			}
			table {
				width: 60%;
				font-family: sans-serif;
				margin: 0 auto;
			}
			td ,th {
				padding: 3% 0;
			}
			input {
				width: 80%;
				padding: 3%;
				font-size: 15px;
			}	
			input[type=submit] {
				width: 90%;
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
		<form action="searchImage.do" method="post">
			<table>
				<tr><td colspan="2"><label>Search Image</label></td></tr>
				<tr><td>Image Id</td><td><input type="number" name="imageId" placeholder="Enter Image Id"></td></tr>
				<tr><td>Image Name </td><td><input type="text" name="image" placeholder="Enter Image Name"></td></tr>
				<tr><td>Product Id</td><td><input type="number" name="pdtId" placeholder="Enter Product Id"></td></tr>
				<tr><td colspan="2"><input type="submit" value="Search"></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>