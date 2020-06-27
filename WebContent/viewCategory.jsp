<%@ page language="java" errorPage="errorPage.jsp" import="org.mz.bean.Category" import="java.util.ArrayList" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>View Category</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/menu.css">
	<style type="text/css">
		form {
			width: 90%;
			margin-bottom: 8%;
		}
		table {
			width: 90%;
		}
		table,th,td {
			border: 1px solid black;
			border-collapse: collapse;
			margin: 0 auto;
		}
		th ,td {
			padding: 1em 2em;
		}
		button {
			padding: 11px 18px;
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
			ArrayList<Category> categoryList = (ArrayList<Category>)(session.getAttribute("categoryList"));
			if(categoryList.isEmpty()){
		%>
			<h2>Category record not found !!</h2>
		<%
			}else {
		%>
		<form method="post">
			<table>
				<tr><th>Sr No.</th><th>Category ID</th><th>Category Name</th><th colspan="2"></th></tr>
				<%
					int srNo=0;
					
					for(Category category : categoryList ){
						srNo++;
				%>
					<tr>
						<td><%=srNo %></td><td><%=category.getCategoryId() %></td> <td><%=category.getCategoryName() %></td>
						<td><a href="editCategory.do?id=<%=category.getCategoryId() %>"><button type="button">Edit</button></a>
						<td><a href="deleteCategory.do?id=<%=category.getCategoryId() %>"><button type="button" onclick="return confirm('Are you sure you wish to delete?');" >Delete</button></a></td>
					</tr>
				<% } %>	
			</table>
		</form>
		<% } %>
	</div>
	<%@ include file="footer.html" %>
	<script src="js/js.js"></script>
</body>
</html>