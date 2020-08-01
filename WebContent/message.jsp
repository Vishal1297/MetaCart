<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thanks For visiting</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<style type="text/css">
h2 {
	margin: 6% 0;
}

a button {
	width: 8%;
	margin: 40px 0;
	border: 1px solid grey;
	outline: none;
	padding: 14px 0px;
	font-size: 18px;
}
</style>
</head>
<body>
	<%@ include file="header.html"%>
	<div id="main">
		<h2>Thanks for visiting MetaCart</h2>
		<a href="categories.do"><button type="button">Continue</button></a>
	</div>
	<%@ include file="footer.html"%>
</body>
</html>