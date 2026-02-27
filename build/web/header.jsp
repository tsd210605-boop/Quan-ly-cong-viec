<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand"
           href="${pageContext.request.contextPath}/dashboard">
            Task Manager
        </a>

        <ul class="navbar-nav ms-auto">

            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/dashboard">
                    Trang chủ
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/add-task-page">
                    Thêm công việc
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/tasks-page">
                    Danh sách
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link"
                   href="${pageContext.request.contextPath}/profile-page">
                    Tài khoản
                </a>
            </li>
            
              <li class="nav-item">
                <a class="nav-link"
                   href ="${pageContext.request.contextPath}/statistics">
                    Thống kê
                </a>
            </li>


            <li class="nav-item">
                <a class="nav-link text-danger"
                   href="${pageContext.request.contextPath}/logout">
                    Đăng xuất
                </a>
            </li>
        </ul>
    </div>
</nav>
