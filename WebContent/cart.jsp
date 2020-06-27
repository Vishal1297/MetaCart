<%@page import="java.util.ArrayList"%>
<%@page import="org.mz.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Cart</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<style type="text/css">
		h2 {
			margin: 6% 0;
		}
		form {
			width: 90%;
			margin-top: 1%;
			margin-bottom: 8%;
		}
		table {
			width: 95%;
			text-align: center;
		}
		table,th,td {
			border: 1px solid black;
			border-collapse: collapse;
			margin: 0 auto;
		}
		th{
			padding: 2% 1%;
		}
		td {
			padding: 1% 0%;
		}
		th .fa-trash-o {
			font-size: 22px;
		}
		button {
			width: 105px;
			padding: 12px 0;
			border: 1px solid grey;
			border-radius: 12px;
			font-size: 14px;
			cursor: pointer;
		}
		#main p{
			margin: 2% 0;
		}
		p span {
			font-size: 18px;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<%@ include file="header.html"%>
	<%@ include file="cartMenu.jsp"%>
	<div id="main">
		<%
			int srNo=0,totalPrice=0;
			ArrayList<Product> productList=null;
			if (session != null) {
				productList = (ArrayList<Product>)(session.getAttribute("productList"));
			}
			if(productCount==0){
		%>
			<h2>No Product Added !!</h2>
		<%
			} else{
		%>
		<form method="post">
			<table>
				<tr>
					<th>Sr No.</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th><i class="fa fa-trash-o"></i></th>
				</tr>
				<%					
					for(Product product : productList ){
						srNo++;
						totalPrice+=product.getPrice()*product.getQuantity();
				%>
				<tr>
					<td><%=srNo %></td><td><%=product.getProductName() %></td><td><%=product.getPrice()*product.getQuantity() %></td><td><%=product.getQuantity() %></td>
					<td><a href="removeProduct.do?index=<%=srNo-1 %>"><button type="button">Remove</button></a></td>
				</tr>
				<% } %>	
			</table>
		</form>
		<p><span>Total Price: </span><%=totalPrice %> Rs</p>
			<p><a href="buyProduct.do"><button type="button">Buy</button></a>
		<%
			}
		%>
			<a href="categories.do"><button type="button">Continue</button></a></p>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>