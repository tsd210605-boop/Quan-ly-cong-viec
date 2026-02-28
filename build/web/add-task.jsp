<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<h3 class="mb-4 fw-bold">Thêm công việc</h3>

<div class="card p-4">

    <form action="${pageContext.request.contextPath}/add-task" 
          method="post" 
          class="row g-3">

        <div class="col-md-4">
            <label class="form-label">Tiêu đề</label>
            <input type="text" name="title" 
                   class="form-control" 
                   placeholder="Nhập tiêu đề..." required>
        </div>

       
        <div class="col-md-4">
            <label class="form-label">Mô tả</label>
            <input type="text" name="description" 
                   class="form-control" 
                   placeholder="Nhập mô tả...">
        </div>

     
        <div class="col-md-4">
            <label class="form-label">Trạng thái</label>
            <select name="status" class="form-select">
                <option value="Pending">Pending</option>
                <option value="In Progress">In Progress</option>
                <option value="Completed">Completed</option>
            </select>
        </div>

      <c:if test="${sessionScope.user.role == 'admin'}">
    <div class="col-md-6">
        <label class="form-label">Phân công cho</label>
        <select name="userId" class="form-select">
            <c:forEach var="u" items="${userList}">
                <option value="${u.id}">${u.username}</option>
            </c:forEach>
        </select>
    </div>
</c:if>
       

   
        <div class="col-md-6">
            <label class="form-label">Deadline</label>
            <input type="date" name="deadline" class="form-control">
        </div>

       
        <div class="col-12 text-end">
            <button type="submit" class="btn btn-primary px-4">
                <i class="bi bi-plus-circle"></i> Thêm công việc
            </button>
        </div>

    </form>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>