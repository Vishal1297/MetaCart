<%@page import="org.mz.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<style type="text/css">
.product-image {
	width: 50%;
	margin: 0.8% 0%;
	letter-spacing: normal;
}

.product-info {
	width: 50%;
	margin-top: 1.8%;
	letter-spacing: normal;
}

form {
	border: 1px solid grey;
	border-radius: 10px;
	background-color: powderblue;
	padding: 1% 4%;
}

table {
	width: 95%;
	margin: 0 auto;
	font-size: 20px;
}

td {
	padding: 4% 0%;
}

input {
	width: 40%;
	padding: 2% 1%;
}

label {
	font-size: 25px;
	font-weight: bold;
}

a button {
	width: 120px;
	border: 1px solid grey;
	border-radius: 16px;
	padding: 14px 0;
	font-size: 18px;
}

img {
	width: 60%;
	height: 420px;
}

span a {
	width: 4%;
	position: fixed;
	top: 16.5%;
	left: 4%;
	margin: 0;
}

span a button {
	width: 100%;
	border: 1px solid grey;
	border-radius: 16px;
	font-size: 26px;
	padding: 12% 0;
}
</style>
</head>
<body>
	<%@ include file="header.html"%>
	<%@ include file="cartMenu.jsp"%>
	<div id="main">
		<span><a href="index.jsp"><button>
					<i class="fa fa-chevron-circle-left"></i>
				</button></a></span>
		<%
			int quantity = 0;
		Product product = (Product) (request.getAttribute("product"));
		String image = request.getAttribute("imageName").toString();
		%>
		<div class="product-image">
			<p>
				<img src="images/<%=image%>" alt="product-image">
			</p>
		</div>
		<div class="product-info">
			<form method="post">
				<table>
					<tr>
						<td colspan="2"><label> <%=product.getProductName()%>
						</label></td>
					</tr>
					<tr>
						<td>Description :</td>
						<td><%=product.getDescription()%></td>
					</tr>
					<tr>
						<td>Price :</td>
						<td><%=product.getPrice()%> Rs</td>
					</tr>
					<tr>
						<td>Quantity :</td>
						<td><input type="number" id="quantity" value="1" min="1"></td>
					</tr>
					<tr>
						<td><a
							onclick="this.href='cart.do?productName=<%=product.getProductName()%>&price=<%=product.getPrice()%>&quantity='+document.getElementById('quantity').value"
							href="">
								<button type="button">Add to Cart</button>
						</a></td>
						<td><a href="buyProduct.do"><button type="button">Buy</button></a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>