<%@page import="org.mz.bean.Image"%>
<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Image</title>
	<link rel="shortcut icon" href="admin.ico" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		#main {
			margin: 0;
		}
		form {
			margin-top: 3%;
		}
		table, td {
			border: 1px solid black;
			border-collapse: collapse;
		}
		table {
			width: 55%;
			font-weight: bold;
			letter-spacing: 2px;
			margin: 0 auto;
		}
		td {
			padding: 20px 0px;
		}
		input {
			width: 70%;
			border-radius: 12px;
			padding: 12px 4px;
		}
		input[type=file]{
			width: 34%;
			font-size: 17px;
		}
		input[type=submit] {
			width: 75%;
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
		<% 																
			Image image = (Image)(request.getAttribute("image"));
		%>
		<form method="post" enctype="multipart/form-data" action="updateImage.do?image=<%=image.getImage() %>">
			<table>
				<tr><td>Product Name :</td></tr>
				<tr><td><input type="text" placeholder="Enter Product Name" name="productName" value="<%=image.getProductName() %>" /></td></tr>
				<tr><td>Image :</td></tr>
				<tr><td><input type="file" id="file" value="<%=image.getImage() %>" name="imagePath" accept="image/*" />
				<span id="fileName"><%=image.getImage() %></span></td>
				</tr>
				<tr><td colspan="2"><input type="submit" value="Update" /></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>