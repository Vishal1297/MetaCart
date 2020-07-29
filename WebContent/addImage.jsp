<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Images</title>
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
			width: 75%;
			padding: 11px 4px;
		}
		input[type=file]{
			font-size: 17px;
			text-align: center;
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
		<form method="post" enctype="multipart/form-data" action="addImage.do">
			<table>
				<tr><td>Product Name :</td></tr>
				<tr><td><input type="text" value="" placeholder="Enter Product Name" name="productName" /></td></tr>
				<tr><td>Image :</td></tr>
				<tr><td><input  type="file" accept="image/*" name="imagePath" /></td></tr>
				<tr><td colspan="2"><input type="submit" value="Add Image"/></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>