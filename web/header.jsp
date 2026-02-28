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
    <title>Work Management System</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f4f6f9;
            overflow-x: hidden;
        }

        /* Sidebar */
        .sidebar {
            height: 100vh;
            position: fixed;
            width: 240px;
            background: linear-gradient(180deg, #1e3c72, #2a5298);
            padding-top: 20px;
        }

        .sidebar h4 {
            color: white;
            text-align: center;
            margin-bottom: 30px;
        }

        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 12px 20px;
            margin: 5px 15px;
            border-radius: 8px;
            transition: 0.3s;
        }

        .sidebar a:hover {
            background-color: rgba(255,255,255,0.2);
        }

        .sidebar a i {
            margin-right: 10px;
        }

        /* Content */
        .content {
            margin-left: 240px;
            padding: 25px;
        }

        /* Topbar */
        .topbar {
            background: white;
            padding: 15px 25px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
            margin-bottom: 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card {
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.08);
            border: none;
        }

        .btn {
            border-radius: 8px;
        }
    </style>
</head>

<body>

<!-- Sidebar -->
<div class="sidebar">
    <h4>📊 Task Manager</h4>

    <a href="${pageContext.request.contextPath}/dashboard">
        <i class="bi bi-speedometer2"></i> Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/add-task-page">
        <i class="bi bi-plus-circle"></i> Thêm công việc
    </a>

    <a href="${pageContext.request.contextPath}/tasks-page">
        <i class="bi bi-list-task"></i> Danh sách
    </a>

    <a href="${pageContext.request.contextPath}/statistics">
        <i class="bi bi-bar-chart"></i> Thống kê
    </a>

    <a href="${pageContext.request.contextPath}/profile-page">
        <i class="bi bi-person"></i> Tài khoản
    </a>

    <a href="${pageContext.request.contextPath}/logout" class="text-warning">
        <i class="bi bi-box-arrow-right"></i> Đăng xuất
    </a>
</div>

<!-- Content Area -->
<div class="content">

    <!-- Topbar -->
    <div class="topbar">
        <h5 class="mb-0">Xin chào, <strong><%= user.getUsername() %></strong></h5>
        <span class="text-muted">Work Management System</span>
    </div>