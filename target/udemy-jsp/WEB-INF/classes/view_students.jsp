<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<c:forEach var="student" items="${student_list}">
${student}<br>
</c:forEach>
</body>
</html>
