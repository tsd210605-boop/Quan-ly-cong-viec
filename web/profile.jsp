<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>

<jsp:include page="header.jsp"/>

<div class="container mt-4">
    <h2>Thông tin tài khoản</h2>

    <p><strong>ID:</strong> <%= user.getId() %></p>
    <p><strong>Username:</strong> <%= user.getUsername() %></p>
    <p><strong>Role:</strong> <%= user.getRole() %></p>
</div>
