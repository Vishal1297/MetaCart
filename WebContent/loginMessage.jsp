<%@ page language="java" errorPage="errorPage.jsp" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login Failed</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="shortcut icon" href="admin.ico" />
	<style type="text/css">
		h2 {
			margin: 10% 0;
			color: red;
		}
		a button {
			width: 10%;
			outline: none;
			padding: 14px 0px;
			font-size: 20px;
		}
	</style>
</head>
<body>
	<%@ include file="header.html" %>
	<div id="main">
		<h2>Login failed !!</h2>
		<a href="login.jsp"><button>Retry</button></a>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>