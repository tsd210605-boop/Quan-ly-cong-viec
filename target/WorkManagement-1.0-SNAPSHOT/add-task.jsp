<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="container mt-4">

<h3>Thêm công việc</h3>

<form action="${pageContext.request.contextPath}/add-task" method="post" class="row g-2">

    <div class="col-md-3">
        <input type="text" name="title" class="form-control" placeholder="Tiêu đề" required>
    </div>

    <div class="col-md-3">
        <input type="text" name="description" class="form-control" placeholder="Mô tả">
    </div>

    <div class="col-md-2">
        <select name="status" class="form-select">
            <option value="Pending">Pending</option>
            <option value="In Progress">In Progress</option>
            <option value="Completed">Completed</option>
        </select>
    </div>

    <div class="col-md-2">
        <input type="date" name="deadline" class="form-control">
    </div>

    <div class="col-md-2">
        <button type="submit" class="btn btn-primary w-100">Thêm</button>
    </div>

</form>

</div>
