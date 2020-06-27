<%@ page language="java" errorPage="errorPage.jsp" import="java.util.ArrayList" import="org.mz.bean.Image" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>View Image</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		#main { 
			margin: 0;
		}
		form {
			width: 95%;
			margin-top: 1%;
			margin-bottom: 8%;
		}
		table {
			width: 80%;
		}
		table,th,td {
			border: 1px solid black;
			border-collapse: collapse;
			margin: 0 auto;
		}
		th {
			padding: 1em;
		}
		td {
			padding: 1em;
		}
		button {
			padding: 1em;
			width: 75px;
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
			ArrayList<Image> imageList = (ArrayList<Image>)(session.getAttribute("imageList"));
			if(imageList.isEmpty()){
		%>
			<h2>Image record not found !!</h2>
		<%
			}else {
		%>
		<form method="post">
			<table>
				<tr><th>Sr No.</th><th>Image ID</th><th>Image</th><th>Product Name</th><th colspan="2"></th></tr>
				<%
					int srNo=0;
					for(Image image : imageList ){
						srNo++;
				%>
					<tr>
						<td><%=srNo %></td><td><%=image.getImageId() %></td> <td><%=image.getImage() %> <td><%=image.getProductName() %></td> 
						<td><a href="editImage.do?id=<%=image.getImageId() %>"><button type="button">Edit</button></a>
						<td><a href="deleteImage.do?id=<%=image.getImageId() %>"><button onclick="return confirm('Are you sure you wish to delete?');" type="button">Delete</button></a></td>
					</tr>
				<% } %>	
			</table>
		</form>
		<% } %>	
	</div>
	<%@ include file="footer.html" %>
</body>
</html>