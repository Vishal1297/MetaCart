<%@ page language="java" isErrorPage="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Error</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		h2 {
			color: red;
			letter-spacing: 2px;
			margin: 8% 0;
		}
		button {
			width: 11%;
			font-size: 20px;
			border-radius: 11px;
			padding: 1% 0;
		}
	</style>
	</head>
<body>
	<%@ include file="header.html" %>
	<%@ include file="menu.html" %>
	<div id="main">
		<%
		String message = "";
		String link = "";
			try{
				message = request.getAttribute("message").toString();
				link = request.getAttribute("link").toString();
			}catch(Exception ex){
				ex.printStackTrace();
		%>
			<h2>Something Went Wrong</h2>
		<%
			}
		%>
		<h2><%=message%></h2>
		<%
			if(!link.isEmpty() && !link.equals("view")) {
		%>
			<a href="<%=link%>"><button>Retry</button></a>
		<%
			} else {
		%>
			<h2>Something Went Wrong</h2>
		<%
			}
		%>
	</div>
	<%@ include file="footer.html" %>
</body>
</html>