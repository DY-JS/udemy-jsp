<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h2>Student Table</h2>
<hr>
<br>
<table border="1">

    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
    </tr>
    <c:forEach var="student" items="${student_list}">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.email}</td>
        </tr><br>
    </c:forEach>
</table>
</body>
</html>
