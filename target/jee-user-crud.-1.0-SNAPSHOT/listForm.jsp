<%@page import="pl.coderslab.utils.User"%>
<%@page import="java.util.List"%>
<%@page import="pl.coderslab.users.UserList"%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista użytowników</title>
</head>
<body>

<a href="formAddUser.jsp">Formularz dodawania użytkownika</a><br>
<%List<User> users = (List<User>)request.getAttribute("users");
    if(users == null)

    { %>
Brak użytkowników
<% }
else{%>
<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
    </tr>
    <%
        for(int i=0; i< users.size(); i++) { %>
    <tr>
        <td><%= users.get(i).getUsername() %></td>
        <td><%= users.get(i).getEmail() %></td>
        <td><%= users.get(i).getPassword() %></td>
    </tr>
    <% }
    } %>

</table>
</body>
</html>