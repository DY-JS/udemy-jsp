<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<%
    //read from data - from cookies-personalize-form.html
    String favLang = request.getParameter("favoriteLanguage");

    //encode cookie data ... handle case of languages with spaces in them
    favLang = URLEncoder.encode(favLang, "UTF-8");

    //create the cookie
    Cookie theCookie = new Cookie("myApp.favoriteLanguage", favLang);

    //set the lifespan in sec
    theCookie.setMaxAge(60*60*24*365);

    //send cookie to browser
    response.addCookie(theCookie);
%>
<body>
<p>Thanks! We set your favorite language to: ${param.favoriteLanguage}</p>
<br>
<a href="cookies-homepage.jsp">Return to homepage</a>
</body>
</html>
