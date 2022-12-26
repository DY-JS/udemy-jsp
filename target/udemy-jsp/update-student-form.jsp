<%@ page import="model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Form</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Students</h2>
    </div>
</div>
<div id="container">
    <h3>Update Student</h3>
    <form action="StudentControllerServlet" method="GET">
        <input type="hidden" name="command" value="UPDATE"/>
        <input type="hidden" name="studentId" value="${STUDENT.id}"/>
        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><input type="text" name="firstName"
                           value="${STUDENT.firstName}"/></td>
            </tr>
<%-- request.setAttribute("STUDENT", student); STUDENT-такой папаметр приходит из сервлета --%>
            <tr>
                <td><label>Last name:</label></td>
                <td><input type="text" name="lastName"
                           value="${STUDENT.lastName}"/></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email"
                           value="${STUDENT.email}"/></td>
            </tr>
            </tbody>
        </table>
        <input type="submit" value="Save" class="save"/>
    </form>
</div>
<br>
<a href="StudentControllerServlet">Back to Student List</a>
</body>
</html>
