<%@ page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--1. Create HTML form--%>
<%--2. Add new item to TodoList, если не пустой--%>
<%--3. Display All TodoList--%>
<%--4. Сделаем проверку TodoList на дубликаты(если сделать релоад - добавл. дубликат последнего эл-та) --%>
<body>
<%--1. Create HTML form--%>
<form action="todolist.jsp">
    Add new item: <input type="text" name="item" placeholder="Enter new item" />
    <input type="submit" value="Submit">
</form>
<br>
Item entered: <%= request.getParameter("item")%>
<%--2. Add new item to TodoList--%>
 <%   // get TODO items from the session
      // if the TODO items doesn't exist, then create a new one
      // see if there is form data to add
     List<String> todoItems = (List<String>) session.getAttribute("myTodoList");
     if (todoItems == null) {
         todoItems = new ArrayList<String>();
         session.setAttribute("myTodoList", todoItems);
     }
     String item = request.getParameter("item");
     boolean isItemNotEmpty = item != null && item.trim().length() >0;
     boolean isNotItemDublicate = item !=null && !todoItems.contains(item.trim());
     if (isItemNotEmpty && isNotItemDublicate) {
         todoItems.add(item);
     }
 %>
<hr>
<b>TODO List Items</b>
<br>
<ol>
    <%
        for (String todoItem : todoItems) {
            out.println("<li>" + todoItem + "</li>");
        }
    %>
</ol>

</body>
</html>
