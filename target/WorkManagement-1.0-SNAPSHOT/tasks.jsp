
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<div class="container mt-4">

<h3>Danh sách công việc</h3>

<table class="table table-bordered table-hover">
    <thead class="table-dark">
        <tr>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Deadline</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${tasks}" var="t">
            <tr>
                <td>${t.title}</td>
                <td>${t.description}</td>
                <td>${t.deadline}</td>
                <td>${t.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/edit-task-page?id=${t.id}" class="btn btn-sm btn-info">Edit</a>
                   <a href="${pageContext.request.contextPath}/delete-task?id=${t.id}" 
                      class="btn btn-sm btn-danger">Xóa</a>

                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</div>
