<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>
<form action="ChangePassword" method="post">
<%
session=request.getSession();
out.println("Balance = "+session.getAttribute("balance"));
%>
</h3>
<a href="HomePage.jsp">Redirect</a>
</form>
</body>
</html>