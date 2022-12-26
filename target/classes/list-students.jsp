<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students from students_db</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%
    //get students from the request object (sent by servlet)
    List<Student> students = (List<Student>) request.getAttribute("student_list");
%>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Students</h2>
    </div>
    <br>
    <input
            type="button"
            value="Add Student"
            onclick="window.location.href='add-student-form.jsp'; return false;"
            class="add-student-button"
    />
    <br>
    <!--  add a search box -->
    <form action="StudentControllerServlet" method="GET">
        <input type="hidden" name="command" value="SEARCH" />
        Search student: <input type="text" name="theSearchName" />
        <input type="submit" value="Search" class="add-student-button" />

    </form>
    <div id="container">
        <div id="content">
            <table>
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                <%--                <% for (Student student : students) { %>--%>
                <%--                <tr>--%>
                <%--                    <td><%= student.getFirstName() %>--%>
                <%--                    </td>--%>
                <%--                    <td><%= student.getLastName() %>--%>
                <%--                    </td>--%>
                <%--                    <td><%= student.getEmail() %>--%>
                <%--                    </td>--%>
                <%--                </tr>--%>
                <%--                <% } %>--%>
                <c:forEach var="student" items="${student_list}">
                    <%--                set up a link for each student--%>
                    <c:url var="link" value="StudentControllerServlet">
                        <c:param name="command" value="GET-BY-ID"/>
                        <c:param name="studentId" value="${student.id}"/>
                    </c:url>
                    <%--                set up a link to delete student--%>
                    <c:url var="deleteLink" value="StudentControllerServlet">
                        <c:param name="command" value="DELETE"/>
                        <c:param name="studentId" value="${student.id}"/>
                    </c:url>
                    <tr>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>
                            <a href="${link}">Update</a>
                            |
                            <a href="${deleteLink}"
                            onclick="if (!(confirm('Are you sure to delete student'))) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</div>
</body>
</html>
