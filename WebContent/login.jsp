<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<link rel="shortcut icon" href="admin.ico" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<style type="text/css">
		#main {
			background-image: linear-gradient(to bottom right, white , grey);
			height: 84vh
		}
		form {
			width: 100%;
			margin-top: 3.8%;
		}
		table ,td{
			border: 1px solid #f15014;
			border-collapse: collapse;
		}
		table {
			width: 40%;
			background-color: #f15014;
			font-weight: bold;
			letter-spacing: 2px;
			margin: 0 auto;
		}
		td {
			padding: 18px 0px;
		}
		img {
			width: 20%;
			border-radius: 50%;
		}
		input {
			width: 75%;
			padding: 1em 2em;
		}
		input[type=submit] {
			width: 90%;
			border: 1px solid grey;
			border-radius: 10px;
			padding: 2em 4em;
			font-size: 1em;
		}
		a {
			width: 3.2%;
			position: fixed;
			right: 10%;
		}
		a button {
			width: 100%;
			border: 1px solid grey;
			border-radius: 50%;
			font-size: 25px;
			padding: 15% 2%;
		}
	</style>
</head>
<body>
	<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
	<%@ include file="header.html" %>
	<div id="main">
	<a href="index.jsp"><button><i class="fa fa-chevron-circle-left"></i></button></a>
		<form action="login.do" method="post">
			<table>
				<tr><td colspan="2"><p><img alt="admin" src="images/administrator.jpg"></p></td></tr>
				<tr><td>User ID :</td><td><input type="number" min="1" placeholder="Enter Your ID" name="userId"></td></tr>
				<tr><td>Password :</td><td><input type="password" placeholder="Enter Your Password" name="userPassword"></td></tr>
				<tr><td colspan="2"><input type="submit" value="Login"></td></tr>
			</table>
		</form>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>