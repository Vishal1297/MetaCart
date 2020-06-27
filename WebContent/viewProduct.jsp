<%@ page language="java" errorPage="errorPage.jsp" import="java.util.ArrayList" import="org.mz.bean.Product" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>View Product</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		#main {
			padding: 0;
			letter-spacing: normal;
		}
		form {
			width: 99%;
			margin-top: 1%;
			margin-bottom: 8%;
		}
		table {
			width: 100%;
			text-align: center;
		}
		table,th,td {
			border: 1px solid black;
			border-collapse: collapse;
			margin: 0 auto;
		}
		th{
			padding: 1em 1em;
		}
		td {
			padding: 1em 0;
		}
		button {
			width: 70px;
			padding: 1em 0;
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
			ArrayList<Product> productList  =(ArrayList<Product>)(session.getAttribute("productList"));
			if(productList.isEmpty()){
		%>
			<h2>Product record not found !!</h2>
		<%
			}else {
		%>
		<form method="post">
			<table>
				<tr><th>Sr No.</th><th>Product ID</th><th>Product Name</th><th>Price</th><th>Description</th><th>Category Name</th><th colspan="2"></th></tr>
				<%
					int srNo=0;
					for(Product product : productList ){
						srNo++;
				%>
					<tr><td><%=srNo %></td><td><%=product.getProductId() %></td> <td><%=product.getProductName() %></td><td><%=product.getPrice() %></td>
						<td><%=product.getDescription() %></td> <td><%=product.getCategoryName() %></td>
						<td><a href="editProduct.do?id=<%=product.getProductId() %>"><button type="button">Edit</button></a>
						<td><a href="deleteProduct.do?id=<%=product.getProductId() %>"><button onclick="return confirm('Are you sure you wish to delete?');" type="button">Delete</button></a></td>
					</tr>
				<% } %>	
			</table>
		</form>
		<% } %>	
	</div>
	<%@ include file="footer.html" %>
</body>
</html>