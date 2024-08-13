<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
</head>
<body>
	<%
	String status = (String) request.getAttribute("status");
	String message = (String) request.getAttribute("message");
	String redirectURL = (String) request.getAttribute("redirectURL");
	%>

	<h1><%=status%></h1>
	<h4><%=message%></h4>
	<a href="<%=redirectURL%>">Click here to redirect...</a>
</body>
</html>