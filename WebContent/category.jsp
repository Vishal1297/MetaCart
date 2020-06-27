<%@page import="org.mz.bean.Image"%>
<%@page import="org.mz.dbservice.ProductDbservice"%>
<%@page import="org.mz.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.mz.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Product</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<style type="text/css">
		#content {
			margin: 0.5em;
			letter-spacing: normal;
		}
		#nav {
			width: 25%;
			padding: 5% 4%;
			margin-top: 4.5px;
			background-color: #7bbcf1;
			border-radius: 2%;
			color: white;
		}
		#list li {
			background-color: blanchedalmond;
			margin: 4% 0;
			border-radius: 8px;
		}
		#list li a {
			color: black;
		}
		h3 {
			margin: 10px 0;
		}
		h3 a {
			font-size: 22px;
		}
		.image p {
			margin: 0;
		}
		img {
			width: 47%;
			height: 185px;
		}
		span a {
				width: 4%;
				position: fixed;
				top: 16.5%;
				left: 4%;
				margin: 0;
		}
		a button {
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
	<span><a href="index.jsp"><button><i class="fa fa-chevron-circle-left"></i></button></a></span>
		<div id="nav">
			<ul id="list">
				<%
					try {
							ArrayList<Category> categoryList = (ArrayList<Category>) (request.getAttribute("categoryList"));
							for (Category category : categoryList) {
				%>
						<li>
							<a href="products.do?categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%></a>
						</li>
				<%
							}
						}catch (Exception e) {
            				out.println("No Category");
         				}
				%>
			</ul>
		</div>
		<div id="content">
			<%
				try {
						ArrayList<Product> productListTwo = (ArrayList<Product>) (request.getAttribute("productList"));
						for (Product product : productListTwo) {
							String image = ProductDbservice.getImage(product.getProductId());
			%>
					<div class="product">
						<div class="image">
							<p>
								<a href="getProduct.do?productId=<%=product.getProductId() %>"> <img alt="Image" src="images/<%=image%>"></a>
							</p>
						</div>
						<div class="info">
							<h3>
								<a href="getProduct.do?productId=<%=product.getProductId()%>"> <%=product.getProductName()%> </a>
							</h3>
							<p>
								<%=product.getDescription()%>
							</p>
							<h3>
								PRICE :<%=product.getPrice()%>
							</h3>
						</div>
					</div>
			<%
						}
				}catch (Exception e) {
    				out.println("No Products");
 				}
			%>
		</div>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>