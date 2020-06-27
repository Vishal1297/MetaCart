<%@page import="org.mz.bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div id="cart">
		<%
			int productCount=0;
			if(session !=null){
				ArrayList<Product> productList = (ArrayList<Product>) (session.getAttribute("productList"));
				if(productList!=null) {
					productCount = productList.size();
				}
			}
		%>
		<a href="cart.jsp"><span><i class="fa fa-shopping-cart"></i> Cart : <%=productCount %></span></a>
	</div>
</body>
</html>