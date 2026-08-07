<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.jj.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome: ${user.name}
welcome: ${user.city}
new :${user.getName()}
<%
User user =(User)session.getAttribute("user");
%>

<%=user.getName() %>

<a href="logout">logout</a>
</body>
</html>