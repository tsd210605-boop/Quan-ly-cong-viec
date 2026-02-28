package controller;

import dao.TaskDAO;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    User currentUser = (User) session.getAttribute("user");

    String title = request.getParameter("title");
    String description = request.getParameter("description");
    String status = request.getParameter("status");

    if (status == null || status.isEmpty()) {
        status = "Pending";
    }

    String deadlineStr = request.getParameter("deadline");
    java.sql.Date deadline = null;

    if (deadlineStr != null && !deadlineStr.isEmpty()) {
        deadline = java.sql.Date.valueOf(deadlineStr);
    }

    int assignedUserId;

    if ("admin".equals(currentUser.getRole())) {
       
        String userIdParam = request.getParameter("userId");

        if (userIdParam != null && !userIdParam.isEmpty()) {
            assignedUserId = Integer.parseInt(userIdParam);
        } else {
            assignedUserId = currentUser.getId(); // fallback
        }
    } else {
       
        assignedUserId = currentUser.getId();
    }

    Task task = new Task();
    task.setTitle(title);
    task.setDescription(description);
    task.setStatus(status);
    task.setDeadline(deadline);
    task.setUserId(assignedUserId);

    TaskDAO dao = new TaskDAO();
    dao.addTask(task);

    response.sendRedirect(request.getContextPath() + "/tasks-page");
}
}
