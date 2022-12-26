<%@ page import="java.util.Locale" %>
<html>
<body>
<p>------EXPRESSIONS-------</p>
Converting string: <%= new String("Hello world").toUpperCase()%>
<br>
25 multyplyed by 4 equals <%= 25*4%>
<br>
Is 75 less than 69? <%= 75<69%>
<p>------SCRIPTLET-------</p>
<%
    for(int i=1; i<=3; i++) {
       out.println("<br/> I really luv2code: " + i);
    }
%>
<p>------JSP Declarations-------</p>
<%!
    String makeItLower(String data) {
        return data.toLowerCase();
    }
%>
<p>String HELLO converted to <%= makeItLower("HELLO")%> </p>
</body>
</html>