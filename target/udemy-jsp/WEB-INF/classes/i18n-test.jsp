<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="theLocale"
       value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
       scope="session" />

<fmt:setLocale value="${theLocale}" />

<fmt:setBundle basename="mylabels" var="lang"/>

<html>
<head>
    <title>i18N</title>
</head>
<body>
<a href="i18n-test.jsp?theLocale=en_US">English (US) |</a>
<a href="i18n-test.jsp?theLocale=es_ES">Spanish (ES) |</a>
<a href="i18n-test.jsp?theLocale=de_DE">German (DE)</a>
<br>
<fmt:message key="label.greeting" bundle="${lang}" />
<br>
<fmt:message key="label.firstname" bundle="${lang}"/> <i>John</i>
<br>
<fmt:message key="label.lastname" bundle="${lang}"/> <i>Doe</i>
<br>
<fmt:message key="label.welcome" bundle="${lang}"/>
<br>
<hr>
Selected locale: ${theLocale}
</body>
</html>
