<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Form</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
    <script type="text/javascript" src="js/student-validation.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Students</h2>
    </div>
</div>
<div id="container">
    <h3>Add Student</h3>
    <form action="StudentControllerServlet" method="GET"
          name="studentForm" onSubmit="return validateForm()">
        <input type="hidden" name="command" value="ADD"/>
        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><input type="text" name="firstName"/></td>
            </tr>
            <tr>
                <td><label>Last name:</label></td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td><label>Email:</label></td>
                <td><input type="text" name="email"/></td>
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
