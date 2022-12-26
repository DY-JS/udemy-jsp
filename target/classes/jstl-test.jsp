<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>JSTL-TEST</title>
</head>
<body>
<%
    //create some data
    String[] cities = {"Mumbai", "Singapore", "Philadelphia"};
    pageContext.setAttribute("myCities", cities);
    //response.sendRedirect(request.getContextPath() + "/helloworld");
    // редирект на /helloworld при загрузке страницы
%>
<c:forEach var="city" items="${myCities}">
    ${city}
    <br>
</c:forEach>
<c:set var="stuff" value="<%= new java.util.Date() %>" />
Time on the server is ${stuff}
<br>
<c:set var="data" value="luv2code" />
Length of string <b>${data}</b>: ${fn:length(data)}
<br>
Uppercase of string <b>${data}</b>: ${fn:toUpperCase(data)}
<br>
Does the string start with "luv"?: ${fn:startsWith(data, "luv")}
<br>
<c:set var="data" value="Mumbai,Singapore,Philadelphia" />
<c:set var="citiesArray" value="${fn:split(data, ',')}" />
<%-- Result: citiesArray = {Mumbai, Singapore, Philadelphia}--%>
<c:forEach var="city" items="${citiesArray}">
    ${city}
    <br>
</c:forEach>
<c:set var="fan" value="${fn:join(citiesArray, '*')}" />
<%-- Result:fan = "Mumbai*Singapore*Philadelphia"--%>
</body>
</html>
