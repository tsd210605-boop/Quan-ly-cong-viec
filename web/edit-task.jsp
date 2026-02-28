<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Task" %>

<jsp:include page="header.jsp"/>

<%
    Task task = (Task) request.getAttribute("task");
%>

<div class="container mt-4">
    <h2>Sửa công việc</h2>

   <form action="${pageContext.request.contextPath}/update-task" method="post">

    <input type="hidden" name="id" value="<%= task.getId() %>">

    <div class="mb-3">
        <label>Tiêu đề</label>
        <input type="text" name="title"
               class="form-control"
               value="<%= task.getTitle() %>" required>
    </div>

    <div class="mb-3">
        <label>Mô tả</label>
        <textarea name="description"
                  class="form-control"><%= task.getDescription() %></textarea>
    </div>

    <!-- TRẠNG THÁI -->
    <div class="mb-3">
        <label>Trạng thái</label>
        <select name="status" class="form-control">

            <option value="Pending"
                <%= "Pending".equals(task.getStatus()) ? "selected" : "" %>>
                Pending
            </option>

            <option value="In Progress"
                <%= "In Progress".equals(task.getStatus()) ? "selected" : "" %>>
                In Progress
            </option>

            <option value="Completed"
                <%= "Completed".equals(task.getStatus()) ? "selected" : "" %>>
                Completed
            </option>

        </select>
    </div>

    <!-- DEADLINE -->
    <div class="mb-3">
        <label>Deadline</label>
        <input type="date" name="deadline"
               class="form-control"
               value="<%= task.getDeadline() != null ? task.getDeadline().toString() : "" %>">
    </div>

    <button type="submit" class="btn btn-primary">
        Cập nhật
    </button>
</form>

</div>
        </div> 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
