<%--
  Created by IntelliJ IDEA.
  User: dmytroyelovets
  Date: 08.12.2022
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Confirmation</title>
</head>
<body>
<p><%= request.getParameter("firstName") %></p>
<%= request.getParameter("lastName") %>
</body>
</html>
