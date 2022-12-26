
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h3>Training Portal</h3>
<%--read the favirite language cookie--%>
<%
    // default value - if there are no cookies
    String favLang = "Java";

    //get the cookies from browser request
    Cookie[] theCookies = request.getCookies();

    //find our favorite language cookie
    if (theCookies != null) {

        for (Cookie tempCookie : theCookies) {
            if ("myApp.favoriteLanguage".equals(tempCookie.getName())) {
                //decode cookie data ... handle case of languages with spaces in them
                favLang = URLDecoder.decode(tempCookie.getValue(), "UTF-8");
                break;
            }
        }
    }
 %>

<%--Show a personalized page ... use the "favLang" variable--%>
<h4>Latest books for <%= favLang%></h4>
<ul>
    <li>Book_1</li>
    <li>Book_2</li>
    <li>Book_3</li>
</ul>

<h4>Latest jobs for <%= favLang%></h4>
<ul>
    <li>Job_1</li>
    <li>Job_2</li>
    <li>Job_3</li>
</ul>
<hr>
<a href="cookies-personalize-form.html">Personalize this page</a>
</body>
</html>
