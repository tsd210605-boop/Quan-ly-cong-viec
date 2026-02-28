<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>

<div class="container mt-4">
    <h2>Thống kê công việc</h2>

    <div class="row mt-4">

        <div class="col-md-3">
            <div class="card bg-primary text-white p-3">
                <h5>Tổng công việc</h5>
                <h3>${total}</h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card bg-warning text-dark p-3">
                <h5>Pending</h5>
                <h3>${pending}</h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card bg-info text-white p-3">
                <h5>In Progress</h5>
                <h3>${inProgress}</h3>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card bg-success text-white p-3">
                <h5>Completed</h5>
                <h3>${completed}</h3>
            </div>
        </div>

    </div>
</div>

</body>
</html>
    </div> 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>