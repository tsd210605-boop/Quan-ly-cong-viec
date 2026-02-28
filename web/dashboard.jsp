<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<h2 class="mb-4 fw-bold">Dashboard</h2>

<div class="row g-4">

    <!-- Total Tasks -->
    <div class="col-md-4">
        <div class="card text-white bg-primary h-100">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="text-uppercase">Total Tasks</h6>
                    <h2 class="fw-bold">${totalTasks}</h2>
                </div>
                <i class="bi bi-list-task fs-1 opacity-75"></i>
            </div>
        </div>
    </div>

    <!-- Completed -->
    <div class="col-md-4">
        <div class="card text-white bg-success h-100">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="text-uppercase">Completed</h6>
                    <h2 class="fw-bold">${completedTasks}</h2>
                </div>
                <i class="bi bi-check-circle fs-1 opacity-75"></i>
            </div>
        </div>
    </div>

    <!-- Pending -->
    <div class="col-md-4">
        <div class="card text-dark bg-warning h-100">
            <div class="card-body d-flex justify-content-between align-items-center">
                <div>
                    <h6 class="text-uppercase">Pending</h6>
                    <h2 class="fw-bold">${pendingTasks}</h2>
                </div>
                <i class="bi bi-hourglass-split fs-1 opacity-75"></i>
            </div>
        </div>
    </div>

</div>

</div> 

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>