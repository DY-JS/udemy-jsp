<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, jsp.tagdemo.Student" %>
<html>
<head>
    <title>ForEach Student</title>
</head>
<%
    //create some data
    List<Student> students = new ArrayList<>();
    students.add(new Student("John", "Doe", false));
    students.add(new Student("Max", "Carlson", false));
    students.add(new Student("Mary", "Pauls", true));

    pageContext.setAttribute("myStudents", students);
%>
<body>
<table border="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gold Customer</th>
    </tr>

    <c:forEach var="student" items="$myStudents">
        <tr border="1">
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>
                <c:if test="${student.goldCustomer}">
                    Special Discount
                </c:if>

                <c:if test="${not student.goldCustomer}">
                    -
                </c:if>

<%--                <c:choose>--%>
<%--                    <c:when test="${student.goldCustomer}">--%>
<%--                        Special Discount--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        ---%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
