
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Confirmation</title>
</head>
<body>
<p>The student is confirmed: ${param.firstName} ${param.lastName} ${param.country}</p>
<p>Favorite languages</p>
<ul>
    <%
        String[] langs = request.getParameterValues("languages");
        if(langs!=null) { //eсли пользователь не выбрал чекбокс
            for (String lang : langs) {
                out.println("<li>" + lang + "</li>");
            }
        }
    %>
</ul>
</body>
</html>
